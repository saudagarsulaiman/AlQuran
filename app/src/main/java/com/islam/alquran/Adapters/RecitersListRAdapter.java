package com.islam.alquran.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.islam.alquran.Models.RecitersName;
import com.islam.alquran.R;
import com.islam.alquran.ServiceAPIs.AyahApi;
import com.islam.alquran.Utilities.AlQuranApiClient;
import com.islam.alquran.Utilities.AutoScrollableTextView;
import com.islam.alquran.Utilities.CommonUtilities;
import com.orhanobut.dialogplus.DialogPlus;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecitersListRAdapter extends RecyclerView.Adapter<RecitersListRAdapter.ViewHolder> {

    Context context;
    ArrayList<RecitersName> allRecitersList;

    public RecitersListRAdapter(Context context, ArrayList<RecitersName> allRecitersList) {
        this.context = context;
        this.allRecitersList = allRecitersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.reciters_lyt, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        if (!allRecitersList.get(i).getStr_name().isEmpty())
            viewHolder.txt_name.setText(allRecitersList.get(i).getStr_name());
        if (!allRecitersList.get(i).getStr_englishName().isEmpty())
            viewHolder.txt_engName.setText(allRecitersList.get(i).getStr_englishName());
        if (!allRecitersList.get(i).getStr_language().isEmpty())
            viewHolder.txt_lang.setText(allRecitersList.get(i).getStr_language());

        viewHolder.lnr_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSearchDialog(allRecitersList.get(i).getStr_identifier(), allRecitersList.get(i).getStr_name(), context);
            }
        });

    }

    private void ShowSearchDialog(final String str_identifier, String str_name, final Context context) {
        com.orhanobut.dialogplus.ViewHolder viewHolder = new com.orhanobut.dialogplus.ViewHolder(R.layout.dialog_search_ayah);
        final DialogPlus dialog = DialogPlus.newDialog(context)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .setInAnimation(R.anim.slide_in_bottom)
                .setOutAnimation(R.anim.slide_out_bottom)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

//                Initializing Widgets
        View view = dialog.getHolderView();
        final EditText edt_ayat_num = view.findViewById(R.id.edt_ayat_num);
        final TextView txt_reciter = view.findViewById(R.id.txt_reciter);
        final Button btn_listen = view.findViewById(R.id.btn_listen);
        final ProgressBar pb = view.findViewById(R.id.pb);

        txt_reciter.setText(context.getResources().getText(R.string.reciter) + " : " + str_name);

        btn_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ayatNum = edt_ayat_num.getText().toString().trim();
                if (ayatNum.isEmpty()) {
                    CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.please_enter_ayah_num));
                } else {
                    ProgressDialog progressDialog = ProgressDialog.show(context, "", context.getResources().getString(R.string.reciting_ayat) + "  " + ayatNum, true);
                    fetchAyah(str_identifier, context, ayatNum, progressDialog);
                }
            }
        });

//                Displaying DialogPlus
        dialog.show();
    }

    private void fetchAyah(final String str_identifier, final Context context, final String ayatNum, final ProgressDialog progressDialog) {
        try {
            AyahApi apiService = AlQuranApiClient.getAClient().create(AyahApi.class);
            Call<ResponseBody> apiResponse = apiService.getAyah(str_identifier, ayatNum);
            apiResponse.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String responsevalue = response.body().string();
                        if (!responsevalue.isEmpty() && responsevalue != null) {
                            if (responsevalue.equals("Not found")) {
                                CommonUtilities.ShowToastMessage(context, responsevalue);
                                progressDialog.dismiss();
                            } else {
                                CommonUtilities.mediaPlayer = MediaPlayer.create(context, Uri.parse("http://cdn.alquran.cloud/media/audio/ayah/" + str_identifier + "/" + ayatNum));
                                if (!CommonUtilities.mediaPlayer.isPlaying()) {
                                    CommonUtilities.mediaPlayer.start();
                                }
                                CommonUtilities.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        } else {
                            CommonUtilities.ShowToastMessage(context, responsevalue);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.errortxt));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.Timeout));
                    } else if (t instanceof java.net.ConnectException) {
                        CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.networkerror));
                    } else {
                        CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.errortxt));
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.errortxt));
        }
    }

    @Override
    public int getItemCount() {
        return allRecitersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        AutoScrollableTextView txt_name;
        @BindView(R.id.txt_engName)
        AutoScrollableTextView txt_engName;
        @BindView(R.id.txt_lang)
        AutoScrollableTextView txt_lang;
        @BindView(R.id.lnr_item)
        LinearLayout lnr_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
