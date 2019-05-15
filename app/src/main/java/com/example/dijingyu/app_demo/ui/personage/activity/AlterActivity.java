package com.example.dijingyu.app_demo.ui.personage.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.BaseApp;
import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.UpDataBean;
import com.example.dijingyu.app_demo.mvp.presenter.UpDataP;
import com.example.dijingyu.app_demo.mvp.view.UpDataV;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.utils.ToastUtil;

public class AlterActivity extends BaseActivity<UpDataP,UpDataV> implements UpDataV, View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvTop;
    /**
     * 完成
     */
    private TextView mTvSuccess;
    private EditText mEdUp;
    private TextView mTvNum;
    private String mType;
    private String mToken;
    private Intent mIntent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mIntent = getIntent();
        mType = mIntent.getStringExtra("type");
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mTvTop = findViewById(R.id.tv_top);
        mTvSuccess = findViewById(R.id.tv_success);
        mTvSuccess.setOnClickListener(this);
        mEdUp = findViewById(R.id.ed_up);
        mTvNum = findViewById(R.id.tv_num);
        mTvNum.setText("27/27");
        if (Constants.NAME.equals(mType)){
            mTvTop.setText(BaseApp.getRes().getText(R.string.username));
            String name = (String) SpUtil.getParam(Constants.USERNAME, "");
            mEdUp.setHint(name);
        }else if (Constants.QIANMING.equals(mType)){
            mTvTop.setText(BaseApp.getRes().getText(R.string.qianming));
            String qianming = (String) SpUtil.getParam(Constants.DESCRIPTION, "");
            mEdUp.setHint(qianming);
        }else {
            Toast.makeText(this,"type有误",Toast.LENGTH_SHORT).show();
        }
        mEdUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                int length = mEdUp.getText().length();
                mTvNum.setText(27-length+"/27");
            }
        });
    }

    @Override
    protected UpDataP initPresenter() {
        return new UpDataP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_alter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_success:
                showLoading();
                String trim = mEdUp.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    ToastUtil.showShort("不能为空");
                }else {
                    if (Constants.NAME.equals(mType)) {
                        mPresenter.upData(mToken, trim, Constants.NAME);
                    } else if (Constants.QIANMING.equals(mType)) {
                        mPresenter.upData(mToken, trim, Constants.QIANMING);
                    }
                }
                break;
        }
    }

    @Override
    public void onSuccess(UpDataBean bean) {
        hideLoading();
        setResult(2,mIntent);
        finish();
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
}
