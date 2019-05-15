package com.example.dijingyu.app_demo.ui.show.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dijingyu.app_demo.R;

import java.util.List;


/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

class PathInfoItemRlvAdapter extends RecyclerView.Adapter{
    private final List<?> list;
    private Context mContext;

    public PathInfoItemRlvAdapter(List<?> images) {
        this.list = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String image = (String) list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(mContext).load(image).into(viewHolder.mIv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }

}
