package com.example.dijingyu.app_demo.mvp.presenter;

import android.util.Log;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseApp;
import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.LoginInfo;
import com.example.dijingyu.app_demo.bean.VerifyCodeBean;
import com.example.dijingyu.app_demo.mvp.model.LoginModel;
import com.example.dijingyu.app_demo.mvp.model.LoginOrBindModel;
import com.example.dijingyu.app_demo.mvp.view.LoginOrBindView;
import com.example.dijingyu.app_demo.net.ResultCallBack;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.utils.ToastUtil;
import com.example.dijingyu.app_demo.widget.LoadingDialog;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import dalvik.system.PathClassLoader;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class LoginOrBindPresenter extends BasePresenter<LoginOrBindView> {
    private static final String TAG = "LoginOrBindPresenter";
    private LoginOrBindModel mLoginOrBindModel;
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginOrBindModel = new LoginOrBindModel();
        mModels.add(mLoginOrBindModel);
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void oauthLogin(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(mView.getAct());
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(mView.getAct(), type, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            logMap(data);
            //login();
            //只写微博的,微信的成功不了
            if (platform == SHARE_MEDIA.SINA) {
                loginSina(data.get("uid"));
            }
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_error));
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_cancel));
        }
    };

    /**
     * 新浪微博登录
     * @param uid
     */
    private void loginSina(String uid) {
        mLoginOrBindModel.loginSina(uid, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                //登录成功了,需要做什么
                //1.跳转主页面
                //2.保存用户信息
                if (bean.getResult() != null) {
                    saveUserInfo(bean.getResult());
                    if (mView != null){
                        mView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        mView.go2MainActivity();
                    }
                }else {
                    if (mView != null){
                        mView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView != null){
                    mView.toastShort(msg);
                }
            }
        });
    }

    /**
     * 保存用户信息
     * @param result
     */
    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN,result.getToken());
    }

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry:data.entrySet()){
            Log.d(TAG, "logMap: "+entry.getKey()+","+entry.getValue());
        }
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean != null){
                    if (mView != null){
                        mView.setData(bean.getData());
                    }
                }else {
                    if (mView != null){
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
