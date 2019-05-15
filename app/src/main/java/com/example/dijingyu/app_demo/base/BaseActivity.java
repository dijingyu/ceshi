package com.example.dijingyu.app_demo.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.widget.LoadingDialog;
import com.jaeger.library.StatusBarUtil;

/**
 * 作者：邸某某
 * 时间：2019/4/30
 */

public abstract class BaseActivity<P extends BasePresenter, V extends BaseView> extends AppCompatActivity{

    protected P mPresenter;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(getActivityLayoutId());
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.addView((V)this);
        }
        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);
        initView();
    }

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int getActivityLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
            mPresenter = null;
        }
    }

    protected void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    protected void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }
}
