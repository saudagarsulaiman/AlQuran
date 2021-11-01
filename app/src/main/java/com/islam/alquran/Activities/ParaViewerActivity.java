package com.islam.alquran.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.islam.alquran.R;
import com.islam.alquran.Utilities.CommonUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParaViewerActivity extends AppCompatActivity {

    @BindView(R.id.pdf_viewer)
    PDFView pdf_viewer;
    @BindView(R.id.img_tlbr_back)
    ImageView img_tlbr_back;
    @BindView(R.id.txt_tlbr_title)
    TextView txt_tlbr_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_viewer);
        ButterKnife.bind(this);
        String paraName = getIntent().getStringExtra("PARANAME");
        int paraNum = getIntent().getIntExtra("PARANUM", 1);
        img_tlbr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        int num = paraNum--;
        if (paraName.equals("duas")) {
            txt_tlbr_title.setText("Essential Duas");
            pdf_viewer.fromAsset("duas.pdf").load();
        } else {
            txt_tlbr_title.setText("(" + CommonUtilities.getParaArabicTitles(paraNum) + ")\t-\t(" + num + ")\t-\t(" + CommonUtilities.getParaRomanTitles(paraNum) + ")");
            pdf_viewer.fromAsset(paraName + ".pdf").load();
        }

    }
}
