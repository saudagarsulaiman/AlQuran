package com.islam.alquran.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.islam.alquran.R;
import com.islam.alquran.Utilities.CommonUtilities;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.lnr_listen_quran)
    LinearLayout lnr_listen_quran;
    @BindView(R.id.lnr_read_quran)
    LinearLayout lnr_read_quran;
    @BindView(R.id.lnr_listen_ayah)
    LinearLayout lnr_listen_ayah;
    @BindView(R.id.img_app_info)
    ImageView img_app_info;
    @BindView(R.id.img_share)
    ImageView img_share;
    @BindView(R.id.img_dev_info)
    ImageView img_dev_info;
    @BindView(R.id.img_dua)
    ImageView img_dua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        lnr_listen_quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SurahActivity.class);
                startActivity(intent);
            }
        });

        lnr_read_quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ParaActivity.class);
                startActivity(intent);
            }
        });
        lnr_listen_ayah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtilities.isConnectionAvailable(DashboardActivity.this)) {
                    Intent intent = new Intent(DashboardActivity.this, RecitersActivity.class);
                    startActivity(intent);
                } else {
                    CommonUtilities.ShowToastMessage(DashboardActivity.this, getResources().getString(R.string.internetconnection));
                }
            }
        });
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtilities.shareAddress(getResources().getText(R.string.app_url), DashboardActivity.this);
            }
        });
        img_app_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appInfoDialog();
            }
        });
        img_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duaDialog();
            }
        });
        img_dev_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devInfoDialog();
            }
        });
    }

    private void duaDialog() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dialog_dua);
        final DialogPlus dialog = DialogPlus.newDialog(DashboardActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)
                .setInAnimation(R.anim.slide_in_bottom)
                .setOutAnimation(R.anim.slide_out_bottom)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

//                Initializing Widgets
        View view = dialog.getHolderView();

        Button btn_close = view.findViewById(R.id.btn_close);
        TextView txt_more = view.findViewById(R.id.txt_more);
        txt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ParaViewerActivity.class);
                intent.putExtra("PARANAME", "duas");
                startActivity(intent);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


//                Displaying DialogPlus
        dialog.show();

    }

    private void appInfoDialog() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dialog_app_info);
        final DialogPlus dialog = DialogPlus.newDialog(DashboardActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)
                .setInAnimation(R.anim.slide_in_bottom)
                .setOutAnimation(R.anim.slide_out_bottom)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

//                Initializing Widgets
        View view = dialog.getHolderView();

        Button btn_close = view.findViewById(R.id.btn_close);
        TextView txt_privacy_policy = view.findViewById(R.id.txt_privacy_policy);
        TextView txt_website = view.findViewById(R.id.txt_website);


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtilities.isConnectionAvailable(DashboardActivity.this)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(getResources().getString(R.string.my_privacy_policy)));
                    startActivity(intent);
                } else {
                    CommonUtilities.ShowToastMessage(DashboardActivity.this, getResources().getString(R.string.internetconnection));
                }
            }
        });
        txt_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtilities.isConnectionAvailable(DashboardActivity.this)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(getResources().getString(R.string.my_website)));
                    startActivity(intent);
                } else {
                    CommonUtilities.ShowToastMessage(DashboardActivity.this, getResources().getString(R.string.internetconnection));
                }

            }
        });

//                Displaying DialogPlus
        dialog.show();

    }

    private void devInfoDialog() {
        ViewHolder viewHolder = new ViewHolder(R.layout.dialog_dev_info);
        final DialogPlus dialog = DialogPlus.newDialog(DashboardActivity.this)
                .setContentHolder(viewHolder)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)
                .setInAnimation(R.anim.slide_in_bottom)
                .setOutAnimation(R.anim.slide_out_bottom)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

//                Initializing Widgets
        View view = dialog.getHolderView();

        Button btn_close = view.findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


//                Displaying DialogPlus
        dialog.show();

    }
}
