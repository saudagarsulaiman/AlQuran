package com.islam.alquran.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.islam.alquran.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.lnr_listen_quran)
    LinearLayout btn_listen;
    @BindView(R.id.lnr_read_quran)
    LinearLayout btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        btn_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SurahActivity.class);
                startActivity(intent);
            }
        });
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ParaActivity.class);
                startActivity(intent);
/*
                Toast.makeText(DashboardActivity.this, getResources().getText(R.string.coming_coon), Toast.LENGTH_SHORT).show();
*/
            }
        });
    }
}
