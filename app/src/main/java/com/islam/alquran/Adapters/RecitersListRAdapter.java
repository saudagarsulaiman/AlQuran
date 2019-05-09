package com.islam.alquran.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.islam.alquran.Models.RecitersName;
import com.islam.alquran.R;
import com.islam.alquran.Utilities.AutoScrollableTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
            viewHolder.txt_lang.setText(allRecitersList.get(i).getStr_name());
        if (!allRecitersList.get(i).getStr_englishName().isEmpty())
            viewHolder.txt_lang.setText(allRecitersList.get(i).getStr_englishName());
        if (!allRecitersList.get(i).getStr_language().isEmpty())
            viewHolder.txt_lang.setText(allRecitersList.get(i).getStr_language());

        viewHolder.lnr_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSearchDialog(allRecitersList.get(i).getStr_identifier());
            }
        });

    }

    private void ShowSearchDialog(String str_identifier) {

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
