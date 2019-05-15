package com.example.dijingyu.app_demo.ui.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseApp;
import com.example.dijingyu.app_demo.base.BaseFragment;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.VerifyCodeBean;
import com.example.dijingyu.app_demo.mvp.presenter.LoginPresenter;
import com.example.dijingyu.app_demo.mvp.view.LoginView;
import com.example.dijingyu.app_demo.ui.main.LoginActivity;
import com.example.dijingyu.app_demo.widget.IdentifyingCodeView;

/**
 * 作者：邸某某
 * 时间：2019/5/5
 */

public class VerifyFragment  extends BaseFragment<LoginPresenter,LoginView> implements LoginView {

    private ImageView mBack;
    private TextView mTime;

    private IdentifyingCodeView mIcv;
    private TextView mTv;
    private int time;
    private LoginFragment mFragment;

    public static VerifyFragment newInstance(String code){
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }

    @Override
    public void onSuccess(VerifyCodeBean bean) {
        String data = bean.getData();
        mFragment.mVerifyCode = data;
        mTv.setText(data);
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }

    public void setCountDownTime(int time) {
        this.time = time;
        if (getContext() != null){
            if (time != 0){
                String format = String.format(getResources().getString(R.string.again_send_verify) + "(%ss)", time);
                mTime.setText(format);
                mTime.setTextColor(getResources().getColor(R.color.c_999999));
            }else {
                mTime.setEnabled(true);
                mTime.setText(getResources().getString(R.string.again_send_verify));
                mTime.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    @Override
    protected void initView(View inflate) {
        mTv = inflate.findViewById(R.id.tv_tijiao);
        String code = getArguments().getString(Constants.VERIFY_CODE);
        setData(code);
        mBack = inflate.findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        mIcv = inflate.findViewById(R.id.icv);
        mIcv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
        mTime = inflate.findViewById(R.id.tv_time);
        mTime.setEnabled(false);
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time == 0){
                    mTime.setEnabled(false);
                    mPresenter.getVerifyCode();
                    //重新发起倒计时
                    mFragment = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    mFragment.countDown();
                }
            }
        });
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_verify;
    }

    private void autoLogin() {
        if (mIcv.getTextContent().length()>=4){
            //自动登录
            toastShort("自动登录");
            mIcv.setBackgroundEnter(false);
            mTv.setText(BaseApp.getRes().getString(R.string.wait_please));
        }
    }

    @SuppressLint("SetTextI18n")
    public void setData(String data) {
        if (!TextUtils.isEmpty(data) && mTv != null) {
            mTv.setText(data);
        }
    }
}
