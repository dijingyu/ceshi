package com.example.dijingyu.app_demo.ui.show.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.bean.MyCollectBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/12
 */

public class MyCollectRlvAdapter extends RecyclerView.Adapter{

    private final ArrayList<MyCollectBean.ResultBean.CollectedRoutesBean> list;
    private Context mContext;

    public MyCollectRlvAdapter(ArrayList<MyCollectBean.ResultBean.CollectedRoutesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_mycollect_item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTitle.setText(list.get(position).getTitle());
        viewHolder.mText.setText(list.get(position).getIntro());
        RoundedCorners corners = new RoundedCorners(10);
        RequestOptions request = RequestOptions.bitmapTransform(corners);
        Glide.with(mContext).load(list.get(position).getCardURL()).apply(request).into(viewHolder.mTou);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView mTou;
        private final TextView mTitle;
        private final TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mTou = itemView.findViewById(R.id.iv_tou);
            mTitle = itemView.findViewById(R.id.tv_title);
            mText = itemView.findViewById(R.id.tv_text);
        }
    }

    public void addData(List<MyCollectBean.ResultBean.CollectedRoutesBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
