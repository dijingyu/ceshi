package com.example.dijingyu.app_demo.ui.show.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.MyCollectBean;
import com.example.dijingyu.app_demo.mvp.presenter.MyCollectP;
import com.example.dijingyu.app_demo.mvp.view.MyCollectV;
import com.example.dijingyu.app_demo.ui.show.adapter.MyCollectRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyCollectActivity extends BaseActivity<MyCollectP,MyCollectV> implements MyCollectV{
    private int page = 1;
    private String mToken;
    private RecyclerView mRlv;
    private MyCollectRlvAdapter mAdapter;


    @Override
    protected void initView() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mRlv = findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<MyCollectBean.ResultBean.CollectedRoutesBean> list = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        mAdapter = new MyCollectRlvAdapter(list);
        mRlv.setAdapter(mAdapter);
        mPresenter.getCollect(mToken,page+"");
    }

    @Override
    protected MyCollectP initPresenter() {
        return new MyCollectP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void onSuccess(MyCollectBean bean) {
        List<MyCollectBean.ResultBean.CollectedRoutesBean> list = bean.getResult().getCollectedRoutes();
        mAdapter.addData(list);
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
