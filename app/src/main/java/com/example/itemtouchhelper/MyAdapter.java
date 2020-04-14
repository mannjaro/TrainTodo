package com.example.itemtouchhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> dataArray = new ArrayList<>();
    private List<String> detailArray = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;
        TextView mDetail;

        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_view);
            mImageView = (ImageView) v.findViewById(R.id.image_view);
            mDetail = (TextView) v.findViewById(R.id.detail);
        }
    }

    MyAdapter(List<String> dataset, List<String> detail) {
        dataArray = dataset;
        detailArray = detail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                onItemClick(v, position, dataArray.get(position), detailArray.get(position));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(dataArray.get(position));
        holder.mDetail.setText(detailArray.get(position));
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }

    void onItemClick(View v, int position, String itemData, String detail) {
        //OverRide
    }
}
