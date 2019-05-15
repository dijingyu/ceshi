package com.example.dijingyu.app_demo.ui.show.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseFragment;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.InfoPathBean;
import com.example.dijingyu.app_demo.mvp.presenter.InfoPathP;
import com.example.dijingyu.app_demo.mvp.view.InfoPathV;
import com.example.dijingyu.app_demo.ui.show.adapter.PathFRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/13
 */

public class PathFragment extends BaseFragment<InfoPathP,InfoPathV> implements InfoPathV{

    private RecyclerView mRlv;
    private PathFRlvAdapter mAdaapter;

    public static PathFragment newInstay(String id){
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        PathFragment pathFragment = new PathFragment();
        pathFragment.setArguments(bundle);
        return pathFragment;
    }

    @Override
    protected void initView(View inflate) {
        String id = getArguments().getString("id");
        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        mRlv = inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<InfoPathBean.ResultBean.RoutesBean> list = new ArrayList<>();
        mAdaapter = new PathFRlvAdapter(list);
        mRlv.setAdapter(mAdaapter);
        mPresenter.getData(token,id,1+"");
    }

    @Override
    protected InfoPathP initPresenter() {
        return new InfoPathP();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_path;
    }

    @Override
    public void onSuccess(InfoPathBean bean) {
        List<InfoPathBean.ResultBean.RoutesBean> list = bean.getResult().getRoutes();
        mAdaapter.addData(list);
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
