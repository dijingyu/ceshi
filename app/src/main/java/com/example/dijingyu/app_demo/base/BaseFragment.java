package com.example.dijingyu.app_demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dijingyu.app_demo.widget.LoadingDialog;

/**
 * 作者：邸某某
 * 时间：2019/4/30
 */

public abstract class BaseFragment<P extends BasePresenter , V extends BaseView> extends Fragment {

    protected P mPresenter;
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getFragmentLayoutId(),null,false);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.addView((V) this);
        }
        initView(inflate);
        return inflate;
    }

    protected abstract void initView(View inflate);

    protected abstract P initPresenter();

    protected abstract int getFragmentLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
        mPresenter.onDestory();
        mPresenter = null;
    }

    protected void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getContext());
        }
        mLoadingDialog.show();
    }

    protected void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }
}
