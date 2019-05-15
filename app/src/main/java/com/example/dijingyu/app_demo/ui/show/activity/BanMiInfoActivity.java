package com.example.dijingyu.app_demo.ui.show.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.mvp.presenter.BanMiP;
import com.example.dijingyu.app_demo.mvp.view.BanMiV;
import com.example.dijingyu.app_demo.ui.show.fragment.DongTaiFragment;
import com.example.dijingyu.app_demo.ui.show.fragment.PathFragment;
import com.example.dijingyu.app_demo.utils.SpUtil;

public class BanMiInfoActivity extends BaseActivity<BanMiP, BanMiV> implements BanMiV, View.OnClickListener {

    private ImageView mIvBack;
    /**
     * 伴米详情
     */
    private TextView mTvTop;
    private ImageView mIvShare;
    private ImageView mIvTou;
    private TextView mTvName;
    private TextView mTvFollows;
    private ImageView mIvIsFollow;
    private TextView mTvIsFollow;
    private TextView mTvCity;
    private TextView mTvJop;
    private TextView mTvText;
    private BanMiBean.ResultBean.BanmiBean mData;
    private String mToken;
    /**
     * 动态
     */
    private TextView mTvDong;
    /**
     * 线路
     */
    private TextView mTvPath;
    private View mViewDong;
    private View mViewXianlu;
    private FragmentManager mManager;
    private PathFragment mPathFragment;
    private DongTaiFragment mDongTaiFragment;

    @Override
    public void onSuccess(Object bean) {

    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mData = (BanMiBean.ResultBean.BanmiBean) intent.getSerializableExtra("data");
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mTvTop = findViewById(R.id.tv_top);
        mIvShare = findViewById(R.id.iv_share);
        mIvShare.setOnClickListener(this);
        mIvTou = findViewById(R.id.iv_tou);
        mTvName = findViewById(R.id.tv_name);
        mTvFollows = findViewById(R.id.tv_follows);
        mIvIsFollow = findViewById(R.id.iv_isFollow);
        mIvIsFollow.setOnClickListener(this);
        mTvIsFollow = findViewById(R.id.tv_isFollow);
        mTvCity = findViewById(R.id.tv_city);
        mTvJop = findViewById(R.id.tv_jop);
        mTvText = findViewById(R.id.tv_text);
        mTvDong = findViewById(R.id.tv_dong);
        mTvDong.setOnClickListener(this);
        mTvPath = findViewById(R.id.tv_path);
        mTvPath.setOnClickListener(this);
        mViewDong = findViewById(R.id.view_dong);
        mViewXianlu = findViewById(R.id.view_xianlu);
        initData();
    }

    private void initData() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        RoundedCorners tran = new RoundedCorners(20);
        RequestOptions request = RequestOptions.bitmapTransform(tran);
        Glide.with(this).load(mData.getPhoto()).apply(request).into(mIvTou);
        mTvName.setText(mData.getName());
        mTvCity.setText(mData.getLocation());
        mTvJop.setText(mData.getOccupation());
        mTvText.setText(mData.getIntroduction());
        if (mData.isIsFollowed()) {
            mIvIsFollow.setImageResource(R.mipmap.follow);
        } else {
            mIvIsFollow.setImageResource(R.mipmap.follow_unselected);
        }

        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        mDongTaiFragment = new DongTaiFragment();
        mPathFragment = PathFragment.newInstay(mData.getId()+"");
        transaction.add(R.id.fragment, mDongTaiFragment);
        transaction.add(R.id.fragment, mPathFragment);
        transaction.hide(mPathFragment);
        transaction.show(mDongTaiFragment);
        transaction.commit();
    }

    @Override
    protected BanMiP initPresenter() {
        return new BanMiP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_ban_mi_info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_isFollow:
                if (mData.isIsFollowed()) {
                    mPresenter.unFollow(mToken, mData.getId() + "");
                    mIvIsFollow.setImageResource(R.mipmap.follow_unselected);
                    mData.setIsFollowed(false);
                } else {
                    mPresenter.follow(mToken, mData.getId() + "");
                    mIvIsFollow.setImageResource(R.mipmap.follow);
                    mData.setIsFollowed(true);
                }
                break;
            case R.id.tv_dong:
                FragmentTransaction transaction = mManager.beginTransaction();
                transaction.hide(mPathFragment);
                transaction.show(mDongTaiFragment);
                transaction.commit();
                mTvDong.setTextColor(getResources().getColor(R.color.c_373737));
                mViewDong.setVisibility(View.VISIBLE);
                mTvPath.setTextColor(getResources().getColor(R.color.c_999999));
                mViewXianlu.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_path:
                FragmentTransaction transaction2 = mManager.beginTransaction();
                transaction2.hide(mDongTaiFragment);
                transaction2.show(mPathFragment);
                transaction2.commit();
                mTvPath.setTextColor(getResources().getColor(R.color.c_373737));
                mViewXianlu.setVisibility(View.VISIBLE);
                mTvDong.setTextColor(getResources().getColor(R.color.c_999999));
                mViewDong.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
