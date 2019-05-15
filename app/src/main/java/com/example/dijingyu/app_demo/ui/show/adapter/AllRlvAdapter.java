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
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.bean.AllBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：邸某某
 * 时间：2019/5/15
 */

public class AllRlvAdapter extends RecyclerView.Adapter{

    private final ArrayList<AllBean.ResultBean.ReviewsBean> list;
    private Context mContext;

    public AllRlvAdapter(ArrayList<AllBean.ResultBean.ReviewsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_all_item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mName.setText(list.get(position).getUserName());
        viewHolder.mText.setText(list.get(position).getContent());
        viewHolder.mTime.setText(list.get(position).getCreatedAt());
        RequestOptions request = new RequestOptions().circleCrop();
        Glide.with(mContext).load(list.get(position).getUserPhoto()).apply(request).into(viewHolder.mIv);
        if (position == list.size()-1){
            viewHolder.mView.setVisibility(View.GONE);
        }else {
            viewHolder.mView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<AllBean.ResultBean.ReviewsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mName;
        private final TextView mTime;
        private final TextView mText;
        private final View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv_tou);
            mName = itemView.findViewById(R.id.tv_name);
            mTime = itemView.findViewById(R.id.tv_time);
            mText = itemView.findViewById(R.id.tv_text);
            mView = itemView.findViewById(R.id.view);
        }
    }

}
