package com.example.dijingyu.app_demo.ui.show.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.mvp.model.EmptyM;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.view.EmptyV;
import com.example.dijingyu.app_demo.ui.main.AndroidtoJs;
import com.example.dijingyu.app_demo.utils.ToastUtil;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebActivity extends BaseActivity<EmptyP, EmptyV> implements EmptyV {

    private TextView mTvCityInfo;
    private Toolbar mToolbar;
    private WebView mWeb;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mTvCityInfo = findViewById(R.id.tv_city_info);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.back_white);
        mToolbar.setTitle("");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(mToolbar);
        mWeb = findViewById(R.id.web);
        mWeb.loadUrl(url+"?os=android");

        //声明WebSettings子类
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
//                mTvCityInfo.setText(title);
            }
        });

        mWeb.addJavascriptInterface(new AndroidtoJs(this),"android");

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWeb.canGoBack()) {
            mWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected EmptyP initPresenter() {
        return null;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onPause() {
        mWeb.onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mWeb.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mWeb.destroy();
        super.onDestroy();
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
