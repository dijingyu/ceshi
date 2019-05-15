package com.example.dijingyu.app_demo.ui.show.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseApp;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.ui.show.activity.BanMiInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class BanMiRlvAdapter extends RecyclerView.Adapter{

    private final ArrayList<BanMiBean.ResultBean.BanmiBean> list;
    private Context mContext;
    private OnClickListener listener;

    public BanMiRlvAdapter(ArrayList<BanMiBean.ResultBean.BanmiBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fargment_banmi_item, null, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(mContext).load(list.get(position).getPhoto()).into(viewHolder.mIvTou);
        viewHolder.mName.setText(list.get(position).getName());
        viewHolder.mCity.setText(list.get(position).getLocation());
        viewHolder.mFensi.setText(list.get(position).getFollowing()+ BaseApp.getRes().getString(R.string.follow));
        viewHolder.mNow.setText(list.get(position).getOccupation());
        if (list.get(position).isIsFollowed()){
            viewHolder.mIvCollect.setImageResource(R.mipmap.follow);
        }else {
            viewHolder.mIvCollect.setImageResource(R.mipmap.follow_unselected);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BanMiBean.ResultBean.BanmiBean banmiBean = list.get(position);
                Intent intent = new Intent(mContext, BanMiInfoActivity.class);
                intent.putExtra("data",banmiBean);
                mContext.startActivity(intent);
            }
        });

        viewHolder.mIvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(position).isIsFollowed(),list.get(position).getId(),position);
                if (list.get(position).isIsFollowed()){
                    viewHolder.mIvCollect.setImageResource(R.mipmap.follow_unselected);
                    list.get(position).setIsFollowed(false);
                    list.get(position).setFollowing(list.get(position).getFollowing()-1);
                    viewHolder.mFensi.setText(list.get(position).getFollowing()+ BaseApp.getRes().getString(R.string.follow));
                }else {
                    viewHolder.mIvCollect.setImageResource(R.mipmap.follow);
                    list.get(position).setIsFollowed(true);
                    list.get(position).setFollowing(list.get(position).getFollowing()+1);
                    viewHolder.mFensi.setText(list.get(position).getFollowing()+ BaseApp.getRes().getString(R.string.follow));
                }
            }
        });
    }

    public interface OnClickListener{
        void onClick(boolean isFollow,int id,int position);
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIvTou;
        private final ImageView mIvCollect;
        private final TextView mName;
        private final TextView mFensi;
        private final TextView mCity;
        private final TextView mNow;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvTou = itemView.findViewById(R.id.iv);
            mIvCollect = itemView.findViewById(R.id.iv_collect);
            mName = itemView.findViewById(R.id.tv_name);
            mFensi = itemView.findViewById(R.id.tv_fensi);
            mCity = itemView.findViewById(R.id.tv_city);
            mNow = itemView.findViewById(R.id.tv_now);
        }
    }

    public void addData(List<BanMiBean.ResultBean.BanmiBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
