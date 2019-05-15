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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class HomeRlvAdapter extends RecyclerView.Adapter{

    private static final int DUO = 1;
    private static final int SHAO = 2;
    private final ArrayList<HomeBean.ResultBean.RoutesBean> list;
    private final ArrayList<HomeBean.ResultBean.BannersBean> banner;
    private Context mContext;
    private OnClickListener listener;

    public HomeRlvAdapter(ArrayList<HomeBean.ResultBean.BannersBean> banner, ArrayList<HomeBean.ResultBean.RoutesBean> list) {
        this.list = list;
        this.banner = banner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == 0){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_item_banner, null, false);
            return new ViewHolderBanner(inflate);
        }else if (viewType == 1){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_item_list_d, null, false);
            return new ViewHolderD(inflate);
        }else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_item_list_s, null, false);
            return new ViewHolderS(inflate);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int newPosition = position;
        if (banner.size()>0){
            newPosition = position-1;
        }
        int type = getItemViewType(position);
        if (type == 0){
            ViewHolderBanner viewHolder = (ViewHolderBanner) holder;
            ArrayList<String> banners = new ArrayList<>();
            for (int i = 0; i < banner.size(); i++) {
                banners.add(banner.get(i).getImageURL());
            }
            viewHolder.mBanner.setImages(banners).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
        }else if (type == 1){
            final ViewHolderD viewHolderD = (ViewHolderD) holder;
            viewHolderD.mCity.setText(list.get(newPosition).getCity());
            viewHolderD.mCityInfo.setText(list.get(newPosition).getIntro());
            viewHolderD.mTitle.setText(list.get(newPosition).getTitle());
            if (list.get(newPosition).isIsPurchased()) {
                viewHolderD.mBtBuy.setBackgroundResource(R.drawable.bg_login_button);
                viewHolderD.mBtBuy.setText(R.string.yesBuy);
            }else {
                viewHolderD.mBtBuy.setText("¥" + list.get(newPosition).getPrice());
                viewHolderD.mBtBuy.setBackgroundResource(R.drawable.bg_login_button_on);
            }
            viewHolderD.mBtBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderD.mBtBuy.setBackgroundResource(R.drawable.bg_login_button);
                    viewHolderD.mBtBuy.setText(R.string.yesBuy);
                }
            });
            Glide.with(mContext).load(list.get(newPosition).getCardURL()).into(viewHolderD.mIv);
            final int finalNewPosition = newPosition;
            viewHolderD.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(finalNewPosition,DUO);
                }
            });
        }else {
            ViewHolderS viewHolderS = (ViewHolderS) holder;
            RoundedCorners bantam = new RoundedCorners(15);
            RequestOptions request = RequestOptions.bitmapTransform(bantam);
            Glide.with(mContext).load(list.get(newPosition).getCardURL()).apply(request).into(viewHolderS.mIv);
            final int finalNewPosition1 = newPosition;
            viewHolderS.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(finalNewPosition1,SHAO);
                }
            });
        }
    }

    public interface OnClickListener{
        void onClick(int finalNewPosition, int type);
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public ArrayList<HomeBean.ResultBean.RoutesBean> getList(){
        return list;
    }

    @Override
    public int getItemCount() {
        if (banner.size()>0) {
            return list.size()+1;
        }else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else if (list.get(position-1).getType().equals("route")){
            return 1;
        }else {
            return 2;
        }
    }

    public void addData(List<HomeBean.ResultBean.BannersBean> banner, List<HomeBean.ResultBean.RoutesBean> list) {
        if (banner.size()>0) {
            this.banner.addAll(banner);
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
    }


    class ViewHolderBanner extends RecyclerView.ViewHolder {

        private final Banner mBanner;

        public ViewHolderBanner(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner);
        }
    }

    class ViewHolderD extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final Button mBtBuy;
        private final TextView mCity;
        private final TextView mCityInfo;
        private final TextView mTitle;

        public ViewHolderD(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mBtBuy = itemView.findViewById(R.id.bt_buy);
            mCity = itemView.findViewById(R.id.tv_city);
            mTitle = itemView.findViewById(R.id.tv_title);
            mCityInfo = itemView.findViewById(R.id.tv_city_info);
        }
    }

    class ViewHolderS extends RecyclerView.ViewHolder {

        private final ImageView mIv;

        public ViewHolderS(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }

}
