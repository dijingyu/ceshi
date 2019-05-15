package com.example.dijingyu.app_demo.ui.lead;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.WindowManager;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.view.EmptyV;
import com.example.dijingyu.app_demo.ui.main.LoginActivity;
import com.example.dijingyu.app_demo.ui.show.activity.ShowActivity;
import com.example.dijingyu.app_demo.utils.SpUtil;

public class SplashScreenActivity extends BaseActivity<EmptyP,EmptyV> implements EmptyV {


    private Handler mHandler;
    private int i;

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        i = 3;
        handler();
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    private void handler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                i--;
                if (i == 0) {
                    boolean isFirst = (boolean) SpUtil.getParam(Constants.ISFIRST, true);
                    if (isFirst){
                        startActivity(new Intent(SplashScreenActivity.this,LeadActivity.class));
                    }else {
                        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
                        if (!TextUtils.isEmpty(token)){
                            startActivity(new Intent(SplashScreenActivity.this, ShowActivity.class));
                        }else {
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        }
                    }
                    finish();
                    return;
                }
                handler();
            }
        },1000);
    }

    @Override
    protected EmptyP initPresenter() {
        return null;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void onSuccess(Object bean) {

    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
