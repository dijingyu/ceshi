package com.example.dijingyu.app_demo.ui.show.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.bean.PathInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class PathInfoRlvAdapter extends RecyclerView.Adapter{
    private final ArrayList<PathInfoBean.ResultBean.ReviewsBean> list;
    private Context mContext;
    private LinearLayoutManager mManager;
    private PathInfoItemRlvAdapter mAdapter;

    public PathInfoRlvAdapter(ArrayList<PathInfoBean.ResultBean.ReviewsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == 1){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_pathinfo_item_yes, null, false);
            return new ViewHolderYes(inflate);
        }else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_pathinfo_item_no, null, false);
            return new ViewHolderNo(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 1){
            ViewHolderYes viewHolderYes = (ViewHolderYes) holder;
            viewHolderYes.mName.setText(list.get(position).getUserName());
            viewHolderYes.mTime.setText(list.get(position).getCreatedAt());
            viewHolderYes.mText.setText(list.get(position).getContent());
            RequestOptions request = new RequestOptions().circleCrop();
            Glide.with(mContext).load(list.get(position).getUserPhoto()).apply(request).into(viewHolderYes.mTou);
            mManager = new LinearLayoutManager(mContext);
            mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolderYes.mRlv.setLayoutManager(mManager);
            mAdapter = new PathInfoItemRlvAdapter(list.get(position).getImages());
            viewHolderYes.mRlv.setAdapter(mAdapter);
        }else {
            ViewHolderNo viewHolderNo = (ViewHolderNo) holder;
            viewHolderNo.mName.setText(list.get(position).getUserName());
            viewHolderNo.mTime.setText(list.get(position).getCreatedAt());
            viewHolderNo.mText.setText(list.get(position).getContent());
            RequestOptions request = new RequestOptions().circleCrop();
            Glide.with(mContext).load(list.get(position).getUserPhoto()).apply(request).into(viewHolderNo.mTou);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImages().size()>0){
            return 1;
        }else {
            return 2;
        }
    }

    public void addData(List<PathInfoBean.ResultBean.ReviewsBean> reviews) {
        list.addAll(reviews);
        notifyDataSetChanged();
    }

    class ViewHolderNo extends RecyclerView.ViewHolder {

        private final ImageView mTou;
        private final TextView mName;
        private final TextView mTime;
        private final TextView mText;

        public ViewHolderNo(View itemView) {
            super(itemView);
            mTou = itemView.findViewById(R.id.iv_tou);
            mName = itemView.findViewById(R.id.tv_name);
            mTime = itemView.findViewById(R.id.tv_time);
            mText = itemView.findViewById(R.id.tv_text);
        }
    }

    class ViewHolderYes extends RecyclerView.ViewHolder {

        private final RecyclerView mRlv;
        private final TextView mText;
        private final TextView mTime;
        private final TextView mName;
        private final ImageView mTou;

        public ViewHolderYes(View itemView) {
            super(itemView);
            mTou = itemView.findViewById(R.id.iv_tou);
            mName = itemView.findViewById(R.id.tv_name);
            mTime = itemView.findViewById(R.id.tv_time);
            mText = itemView.findViewById(R.id.tv_text);
            mRlv = itemView.findViewById(R.id.rlv);
        }
    }

}
