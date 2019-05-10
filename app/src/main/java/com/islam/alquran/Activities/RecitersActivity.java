package com.islam.alquran.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.islam.alquran.Adapters.RecitersListRAdapter;
import com.islam.alquran.Models.RecitersName;
import com.islam.alquran.R;
import com.islam.alquran.ServiceAPIs.AyahApi;
import com.islam.alquran.Utilities.AlQuranApiClient;
import com.islam.alquran.Utilities.CommonUtilities;
import com.islam.alquran.Utilities.GsonUtils;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecitersActivity extends AppCompatActivity {

    @BindView(R.id.rview_reciters)
    RecyclerView rview_reciters;
    @BindView(R.id.img_tlbr_back)
    ImageView img_tlbr_back;
    @BindView(R.id.txt_tlbr_title)
    TextView txt_tlbr_title;
    LinearLayoutManager linearLayoutManager;
    RecitersListRAdapter recitersListRAdapter;
    ArrayList<RecitersName> allRecitersList;

    ProgressDialog progressDialog;
    String loginResponseCode, loginResponseMsg, loginResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciters);
        ButterKnife.bind(this);

        img_tlbr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txt_tlbr_title.setText(getResources().getText(R.string.reciters));
        allRecitersList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(RecitersActivity.this, LinearLayoutManager.VERTICAL, false);
        rview_reciters.setLayoutManager(linearLayoutManager);
        recitersListRAdapter = new RecitersListRAdapter(RecitersActivity.this, allRecitersList);
        rview_reciters.setAdapter(recitersListRAdapter);


        if (CommonUtilities.isConnectionAvailable(RecitersActivity.this)) {
            fetchReciters();
        } else {
            CommonUtilities.ShowToastMessage(RecitersActivity.this, getResources().getString(R.string.internetconnection));
        }

    }

    private void fetchReciters() {
        try {
            progressDialog = ProgressDialog.show(RecitersActivity.this, "", getResources().getString(R.string.please_wait), true);
            AyahApi apiService = AlQuranApiClient.getRClient().create(AyahApi.class);
            Call<ResponseBody> apiResponse = apiService.getReciters();
            apiResponse.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String responsevalue = response.body().string();
                        if (!responsevalue.isEmpty() && responsevalue != null) {
                            progressDialog.dismiss();
                            JSONObject jsonObject = new JSONObject(responsevalue);
                            loginResponseCode = jsonObject.getString("code");
                            loginResponseMsg = jsonObject.getString("status");
                            if (loginResponseMsg.equals("OK")) {
                                allRecitersList = new ArrayList<>();
                                loginResponseData = jsonObject.getString("data");
                                RecitersName[] coinsStringArray = GsonUtils.getInstance().fromJson(loginResponseData, RecitersName[].class);
                                allRecitersList = new ArrayList<RecitersName>(Arrays.asList(coinsStringArray));

                                recitersListRAdapter = new RecitersListRAdapter(RecitersActivity.this, allRecitersList);
                                rview_reciters.setAdapter(recitersListRAdapter);

                            } else {
                                CommonUtilities.ShowToastMessage(RecitersActivity.this, loginResponseMsg);
                            }
                        } else {
                            CommonUtilities.ShowToastMessage(RecitersActivity.this, loginResponseMsg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(RecitersActivity.this, getResources().getString(R.string.errortxt));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(RecitersActivity.this, getResources().getString(R.string.Timeout));
                    } else if (t instanceof java.net.ConnectException) {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(RecitersActivity.this, getResources().getString(R.string.networkerror));
                    } else {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(RecitersActivity.this, getResources().getString(R.string.errortxt));
                    }
                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            ex.printStackTrace();
            CommonUtilities.ShowToastMessage(RecitersActivity.this, getResources().getString(R.string.errortxt));
        }

    }

}
