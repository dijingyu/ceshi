package com.example.dijingyu.app_demo.ui.show.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.PersonageInfoBean;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.presenter.PersonageInfoP;
import com.example.dijingyu.app_demo.mvp.view.PersonageInfoV;
import com.example.dijingyu.app_demo.ui.personage.activity.PersonageShowActivity;
import com.example.dijingyu.app_demo.ui.show.fragment.BanMiFragment;
import com.example.dijingyu.app_demo.ui.show.fragment.HomeFragment;
import com.example.dijingyu.app_demo.utils.SpUtil;

import java.util.ArrayList;


public class ShowActivity extends BaseActivity<PersonageInfoP, PersonageInfoV> implements PersonageInfoV, View.OnClickListener {


    private ArrayList<Fragment> mFragments;
    private DrawerLayout mDrawer;
    private NavigationView mNavigation;
    private FragmentManager mManager;
    /**
     * 首页
     */
    private RadioButton mRadioHome;
    /**
     * 伴米
     */
    private RadioButton mRadioBanmi;
    private String mToken;
    private ImageView mIv;

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mPresenter.getPersonageInfo(mToken);
        initFragment();
        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment, mFragments.get(0));
        transaction.add(R.id.fragment, mFragments.get(1));
        transaction.show(mFragments.get(0));
        transaction.hide(mFragments.get(1));
        transaction.commit();
        mDrawer = findViewById(R.id.drawer);
        mIv = findViewById(R.id.iv_tou);
        mNavigation = findViewById(R.id.navigation);
        mNavigation.setItemIconTintList(null);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });
        mRadioHome = findViewById(R.id.radio_home);
        mRadioHome.setOnClickListener(this);
        mRadioBanmi = findViewById(R.id.radio_banmi);
        mRadioBanmi.setOnClickListener(this);
    }

    private void initHead() {
        View inflate = mNavigation.getHeaderView(0);
        LinearLayout linearKa = inflate.findViewById(R.id.kaquan_linear);
        LinearLayout linearLv = inflate.findViewById(R.id.lvcheng_linear);
        LinearLayout linearCollect = inflate.findViewById(R.id.collect_linear);
        LinearLayout linearFollow = inflate.findViewById(R.id.follow_linear);
        RelativeLayout relativeUp = inflate.findViewById(R.id.up_relative);


        String photo = (String) SpUtil.getParam(Constants.PHOTO, "");
        String sName = (String) SpUtil.getParam(Constants.USERNAME, "");
        String sQianming = (String) SpUtil.getParam(Constants.DESCRIPTION, "");
        RequestOptions request = new RequestOptions().circleCrop();
        Glide.with(this).load(photo).apply(request).into(mIv);
        ImageView ivTou = inflate.findViewById(R.id.iv_tou);
        TextView name = inflate.findViewById(R.id.tv_name);
        name.setText(sName);
        TextView bianji = inflate.findViewById(R.id.tv_bianji);
        TextView qianming = inflate.findViewById(R.id.tv_qianming);
        qianming.setText(sQianming);
        Glide.with(this).load(photo).apply(request).into(ivTou);
        linearFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,MyFollowActivity.class));
            }
        });
        linearCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,MyCollectActivity.class));
            }
        });
        relativeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActivity.this, PersonageShowActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new BanMiFragment());
    }

    @Override
    protected PersonageInfoP initPresenter() {
        return new PersonageInfoP();
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_show;
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, ShowActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof PersonageInfoBean) {
            PersonageInfoBean bean = (PersonageInfoBean) o;
            String photo = bean.getResult().getPhoto();
            String userName = bean.getResult().getUserName();
            String gender = bean.getResult().getGender();
            String description = bean.getResult().getDescription();
            SpUtil.setParam(Constants.PHOTO, photo);
            SpUtil.setParam(Constants.USERNAME, userName);
            SpUtil.setParam(Constants.GENDER, gender);
            SpUtil.setParam(Constants.DESCRIPTION, description);
            initHead();
        }

    }
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START))
            mDrawer.closeDrawers();
        else
            super.onBackPressed();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        initHead();
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.radio_home:
                FragmentTransaction transaction = mManager.beginTransaction();
                transaction.show(mFragments.get(0));
                transaction.hide(mFragments.get(1));
                transaction.commit();
                break;
            case R.id.radio_banmi:
                FragmentTransaction transaction2 = mManager.beginTransaction();
                transaction2.show(mFragments.get(1));
                transaction2.hide(mFragments.get(0));
                transaction2.commit();
                break;
        }
    }
}
