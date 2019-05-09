package com.islam.alquran.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.islam.alquran.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.lnr_listen_quran)
    LinearLayout lnr_listen_quran;
    @BindView(R.id.lnr_read_quran)
    LinearLayout lnr_read_quran;
    @BindView(R.id.lnr_listen_ayah)
    LinearLayout lnr_listen_ayah;

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
                Intent intent = new Intent(DashboardActivity.this, RecitersActivity.class);
                startActivity(intent);
            }
        });
    }
}
