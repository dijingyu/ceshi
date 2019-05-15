package com.example.dijingyu.app_demo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.view.EmptyV;
import com.example.dijingyu.app_demo.ui.personage.activity.PersonageShowActivity;

public class MainActivity extends BaseActivity<EmptyP,EmptyV> implements EmptyV{

    private Toolbar mToolbar;
    private RelativeLayout mPersonage;
    private ImageView mIv;
    private TextView mName;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mToolbar = findViewById(R.id.toolbar);
        mPersonage = findViewById(R.id.personage_relative);
        mIv = findViewById(R.id.iv_tou);
        mName = findViewById(R.id.tv_name);
//        mName.setText(name);
        mPersonage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonageShowActivity.class);

                startActivity(intent);
            }
        });
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        RequestOptions requisition = new RequestOptions().circleCrop();
//        Glide.with(this).load(iconurl).apply(requisition).into(mIv);
    }

    @Override
    protected EmptyP initPresenter() {
        return null;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
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
