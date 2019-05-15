package com.example.dijingyu.app_demo.mvp.presenter;


import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.VerifyCodeBean;
import com.example.dijingyu.app_demo.mvp.model.LoginModel;
import com.example.dijingyu.app_demo.mvp.view.LoginView;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (mView !=null)
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
