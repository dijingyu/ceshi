package com.example.dijingyu.app_demo.ui.show.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseFragment;
import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.bean.FollowBean;
import com.example.dijingyu.app_demo.mvp.presenter.BanMiP;
import com.example.dijingyu.app_demo.mvp.view.BanMiV;
import com.example.dijingyu.app_demo.ui.show.adapter.BanMiRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class BanMiFragment extends BaseFragment<BanMiP,BanMiV> implements BanMiV, OnLoadMoreListener, OnRefreshListener {

    private RecyclerView mRlv;
    private BanMiRlvAdapter mAdapter;
    private int page = 1;
    private SmartRefreshLayout mSmart;
    private String mToken;

    @Override
    protected void initView(View inflate) {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mRlv = inflate.findViewById(R.id.rlv);
        mSmart = inflate.findViewById(R.id.smart);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<BanMiBean.ResultBean.BanmiBean> list = new ArrayList<>();
        mAdapter = new BanMiRlvAdapter(list);
        mRlv.setAdapter(mAdapter);
        mPresenter.getData(mToken,page+"");
        mSmart.setOnLoadMoreListener(this);
        mSmart.setOnRefreshListener(this);
        mAdapter.setOnClickListener(new BanMiRlvAdapter.OnClickListener() {
            @Override
            public void onClick(boolean isFollow, int id,int position) {
                if (isFollow){
                    mPresenter.unFollow(mToken,id+"");
                }else {
                    mPresenter.follow(mToken,id+"");
                }
            }
        });
    }

    @Override
    protected BanMiP initPresenter() {
        return new BanMiP();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_banmi;
    }

    @Override
    public void onSuccess(Object bean) {
        if (bean instanceof BanMiBean) {
            BanMiBean banMiBean = (BanMiBean) bean;
            mAdapter.addData(banMiBean.getResult().getBanmi());
            if (page == 1) {
                mSmart.finishRefresh();
            } else {
                mSmart.finishLoadMore();
            }
        }else if (bean instanceof FollowBean){
            FollowBean followBean = (FollowBean) bean;
            String message = followBean.getResult().getMessage();
            toastShort(message);
        }
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        mPresenter.getData(mToken,page+"");
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=1;
        mPresenter.getData(mToken,page+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getData(mToken,page+"");
    }
}
