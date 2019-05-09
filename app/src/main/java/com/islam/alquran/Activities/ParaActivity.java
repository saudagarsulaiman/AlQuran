package com.islam.alquran.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.islam.alquran.Adapters.ParaListRAdapter;
import com.islam.alquran.Models.ParaName;
import com.islam.alquran.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParaActivity extends AppCompatActivity {

    @BindView(R.id.rview_para)
    RecyclerView rview_para;
    @BindView(R.id.img_tlbr_back)
    ImageView img_tlbr_back;
    @BindView(R.id.txt_tlbr_title)
    TextView txt_tlbr_title;
    LinearLayoutManager linearLayoutManager;
    ParaListRAdapter paraListRAdapter;
    ArrayList<ParaName> allParaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para);
        ButterKnife.bind(this);
        img_tlbr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txt_tlbr_title.setText(getResources().getText(R.string.read_quran));
        allParaList = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            ParaName paraName = new ParaName();
            paraName.setParaName("para" + i);
            paraName.setParaNum(i);
            allParaList.add(paraName);
        }
        linearLayoutManager = new LinearLayoutManager(ParaActivity.this, LinearLayoutManager.VERTICAL, false);
        rview_para.setLayoutManager(linearLayoutManager);
        paraListRAdapter = new ParaListRAdapter(ParaActivity.this, allParaList);
        rview_para.setAdapter(paraListRAdapter);
    }
}