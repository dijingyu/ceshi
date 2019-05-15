package com.example.dijingyu.app_demo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.view.EmptyV;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

public class ProtocolActivity extends BaseActivity<EmptyP,EmptyV> implements EmptyV {

    private TextView mTvTitle;
    private Toolbar mToolbar;
    private LinearLayout mLinear;
    private AgentWeb mWith;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, ProtocolActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mTvTitle = findViewById(R.id.tv_city_info);
        mToolbar = findViewById(R.id.toolbar);
        mLinear = findViewById(R.id.linear);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(mToolbar);
        mWith = AgentWeb.with(this)
                .setAgentWebParent(mLinear, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");
        mWith.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    mTvTitle.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });
    }

    @Override
    protected EmptyP initPresenter() {
        return null;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void onPause() {
        mWith.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mWith.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mWith.getWebLifeCycle().onDestroy();
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
