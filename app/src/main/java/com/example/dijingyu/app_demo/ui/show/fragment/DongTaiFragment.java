package com.example.dijingyu.app_demo.ui.show.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseFragment;
import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.ui.show.adapter.DongRlvAdapter;

/**
 * 作者：邸某某
 * 时间：2019/5/13
 */

public class DongTaiFragment extends BaseFragment {

    private RecyclerView mRlv;
    private DongRlvAdapter mAdapter;

    @Override
    protected void initView(View inflate) {
        mRlv = inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new DongRlvAdapter();
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_dongtai;
    }
}
