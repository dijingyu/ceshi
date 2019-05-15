package com.example.dijingyu.app_demo.ui.show.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.mvp.presenter.BanMiP;
import com.example.dijingyu.app_demo.mvp.view.BanMiV;
import com.example.dijingyu.app_demo.ui.show.adapter.BanMiRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;

import java.util.ArrayList;

public class MyFollowActivity extends BaseActivity<BanMiP,BanMiV> implements BanMiV{

    private RecyclerView mRlv;
    private BanMiRlvAdapter mAdapter;
    private String mToken;
    private int page = 1;

    @Override
    protected void initView() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mRlv = findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<BanMiBean.ResultBean.BanmiBean> list = new ArrayList<>();
        mAdapter = new BanMiRlvAdapter(list);
        mPresenter.getFollow(mToken,page+"");
        mAdapter.setOnClickListener(new BanMiRlvAdapter.OnClickListener() {
            @Override
            public void onClick(boolean isFollow, int id, int position) {
                if (isFollow){
                    mPresenter.unFollow(mToken,id+"");
                }else {
                    mPresenter.follow(mToken,id+"");
                }
            }
        });
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected BanMiP initPresenter() {
        return new BanMiP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_my_follow;
    }

    @Override
    public void onSuccess(Object bean) {
        if (bean instanceof BanMiBean){
            BanMiBean banMiBean = (BanMiBean) bean;
            mAdapter.addData(banMiBean.getResult().getBanmi());
        }
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
