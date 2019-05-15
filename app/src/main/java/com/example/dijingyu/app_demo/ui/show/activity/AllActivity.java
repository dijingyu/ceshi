package com.example.dijingyu.app_demo.ui.show.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.AllBean;
import com.example.dijingyu.app_demo.mvp.presenter.AllP;
import com.example.dijingyu.app_demo.mvp.view.AllV;
import com.example.dijingyu.app_demo.ui.show.adapter.AllRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class AllActivity extends BaseActivity<AllP,AllV> implements AllV, View.OnClickListener {

    private ImageView mIvBack;

    private RecyclerView mRlv;
    private AllRlvAdapter mAdapter;
    private int page = 1;
    private String mToken;
    private int mId;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mRlv = findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<AllBean.ResultBean.ReviewsBean> list = new ArrayList<>();
        mAdapter = new AllRlvAdapter(list);
        mRlv.setAdapter(mAdapter);
        mPresenter.getData(mToken, mId,page+"");
    }

    @Override
    protected AllP initPresenter() {
        return new AllP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_all;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(AllBean bean) {
        List<AllBean.ResultBean.ReviewsBean> list = bean.getResult().getReviews();
        mAdapter.addData(list);
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
