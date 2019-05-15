package com.example.dijingyu.app_demo.ui.show.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseFragment;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.HomeBean;
import com.example.dijingyu.app_demo.mvp.presenter.HomeP;
import com.example.dijingyu.app_demo.mvp.view.HomeV;
import com.example.dijingyu.app_demo.ui.show.activity.PathInfoActivity;
import com.example.dijingyu.app_demo.ui.show.activity.WebActivity;
import com.example.dijingyu.app_demo.ui.show.adapter.HomeRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class HomeFragment extends BaseFragment<HomeP,HomeV> implements HomeV, OnLoadMoreListener, OnRefreshListener {

    private RecyclerView mRlv;
    private HomeRlvAdapter mAdapter;
    private SmartRefreshLayout mSmart;
    private String mToken;
    private int page =1;

    @Override
    protected void initView(View inflate) {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mRlv = inflate.findViewById(R.id.rlv);
        mSmart = inflate.findViewById(R.id.smart);
        ArrayList<HomeBean.ResultBean.BannersBean> banner = new ArrayList<>();
        ArrayList<HomeBean.ResultBean.RoutesBean> list = new ArrayList<>();
        mAdapter = new HomeRlvAdapter(banner, list);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.setAdapter(mAdapter);
        mPresenter.getData(mToken,page+"");
        mSmart.setOnLoadMoreListener(this);
        mSmart.setOnRefreshListener(this);
        mAdapter.setOnClickListener(new HomeRlvAdapter.OnClickListener() {
            @Override
            public void onClick(int finalNewPosition, int type) {
                if (type == 1) {
                    int id = mAdapter.getList().get(finalNewPosition).getId();
                    Intent intent = new Intent(getContext(), PathInfoActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else if (type == 2){
                    String url = mAdapter.getList().get(finalNewPosition).getContentURL();
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected HomeP initPresenter() {
        return new HomeP();
    }


    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onSuccess(HomeBean bean) {
        List<HomeBean.ResultBean.BannersBean> banner = bean.getResult().getBanners();
        List<HomeBean.ResultBean.RoutesBean> list = bean.getResult().getRoutes();
        mAdapter.addData(banner,list);
        if (page==1){
            mSmart.finishRefresh();
        }else {
            mSmart.finishLoadMore();
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
        mAdapter.clear();
    }
}
