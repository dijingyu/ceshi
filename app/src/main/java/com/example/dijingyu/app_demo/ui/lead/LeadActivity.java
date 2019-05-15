package com.example.dijingyu.app_demo.ui.lead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.view.EmptyV;
import com.example.dijingyu.app_demo.ui.VpAdapterPager;
import com.example.dijingyu.app_demo.ui.main.LoginActivity;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.widget.PreviewIndicator;

import java.util.ArrayList;

public class LeadActivity extends BaseActivity<EmptyP,EmptyV> implements EmptyV {

    private ViewPager mVp;
    private PreviewIndicator mPer;
    private VpAdapterPager mAdapterPager;
    private Button mBt;
    private ArrayList<ImageView> mList;

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mVp = findViewById(R.id.vp);
        mBt = findViewById(R.id.bt);
        initData();
        mAdapterPager = new VpAdapterPager(mList);
        mVp.setAdapter(mAdapterPager);
        mPer = findViewById(R.id.per);
        mPer.initSize(80,32,6);
        mPer.setNumbers(3);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPer.setVisibility(View.VISIBLE);
                mBt.setVisibility(View.GONE);
                if (position == 2){
                    mPer.setVisibility(View.GONE);
                    mBt.setVisibility(View.VISIBLE);
                }
                mPer.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.setParam(Constants.ISFIRST, false);
                Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected EmptyP initPresenter() {
        return null;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_lead;
    }

    private void initData() {
        mList = new ArrayList<>();
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageDrawable(getResources().getDrawable(R.mipmap.guide_01));
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageDrawable(getResources().getDrawable(R.mipmap.guide_02));
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageDrawable(getResources().getDrawable(R.mipmap.guide_03));
        mList.add(imageView1);
        mList.add(imageView2);
        mList.add(imageView3);
    }

    @Override
    public void onSuccess(Object bean) {

    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
