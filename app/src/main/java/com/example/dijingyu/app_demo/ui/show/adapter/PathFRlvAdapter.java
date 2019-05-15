package com.example.dijingyu.app_demo.ui.show.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.bean.InfoPathBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/14
 */

public class PathFRlvAdapter extends RecyclerView.Adapter{
    private final ArrayList<InfoPathBean.ResultBean.RoutesBean> list;
    private Context mContext;

    public PathFRlvAdapter(ArrayList<InfoPathBean.ResultBean.RoutesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_item_list_d, null, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mBtBuy.setText("¥" + list.get(position).getPrice());
        viewHolder.mCity.setText(list.get(position).getTitle());
        viewHolder.mCity_info.setText(list.get(position).getIntro());
        viewHolder.mTitle.setText(list.get(position).getCity());
        Glide.with(mContext).load(list.get(position).getCardURL()).into(viewHolder.mIv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final Button mBtBuy;
        private final TextView mCity;
        private final TextView mCity_info;
        private final TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mBtBuy = itemView.findViewById(R.id.bt_buy);
            mCity = itemView.findViewById(R.id.tv_city);
            mCity_info = itemView.findViewById(R.id.tv_city_info);
            mTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public void addData(List<InfoPathBean.ResultBean.RoutesBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
