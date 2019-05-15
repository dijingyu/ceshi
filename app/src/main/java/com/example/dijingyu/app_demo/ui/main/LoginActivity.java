package com.example.dijingyu.app_demo.ui.main;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.bean.VerifyCodeBean;
import com.example.dijingyu.app_demo.mvp.presenter.LoginPresenter;
import com.example.dijingyu.app_demo.mvp.view.LoginView;
import com.example.dijingyu.app_demo.ui.main.fragment.LoginFragment;
import com.umeng.socialize.UMShareAPI;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginView> implements LoginView {

    public static final String TAG = "loginFragment";

    @Override
    public void onSuccess(VerifyCodeBean bean) {

    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment,new LoginFragment(),TAG).commit();
        mPresenter.getVerifyCode();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }
}
