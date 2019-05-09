package com.islam.alquran.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.islam.alquran.Activities.ParaViewerActivity;
import com.islam.alquran.Models.ParaName;
import com.islam.alquran.R;
import com.islam.alquran.Utilities.AutoScrollableTextView;
import com.islam.alquran.Utilities.CommonUtilities;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParaListRAdapter extends RecyclerView.Adapter<ParaListRAdapter.ViewHolder> {

    Context context;
    ArrayList<ParaName> allParaList;

    public ParaListRAdapter(Context context, ArrayList<ParaName> allParaList) {
        this.context = context;
        this.allParaList = allParaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.para_lyt, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txt_pnum.setText("(" + allParaList.get(i).getParaNum() + ")");
        viewHolder.txt_romanName.setText(CommonUtilities.getParaRomanTitles(i));
        viewHolder.txt_arabicName.setText(CommonUtilities.getParaArabicTitles(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParaViewerActivity.class);
                intent.putExtra("PARANAME", allParaList.get(i).getParaName());
                intent.putExtra("PARANUM", allParaList.get(i).getParaNum());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return allParaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_arabicName)
        AutoScrollableTextView txt_arabicName;
        @BindView(R.id.txt_romanName)
        AutoScrollableTextView txt_romanName;
        @BindView(R.id.txt_pnum)
        AutoScrollableTextView txt_pnum;
        @BindView(R.id.lnr_item)
        LinearLayout lnr_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}