package com.example.dijingyu.app_demo.ui.main.fragment;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseFragment;
import com.example.dijingyu.app_demo.bean.LoginInfo;
import com.example.dijingyu.app_demo.mvp.presenter.LoginOrBindPresenter;
import com.example.dijingyu.app_demo.mvp.view.LoginOrBindView;
import com.example.dijingyu.app_demo.ui.show.activity.ShowActivity;
import com.example.dijingyu.app_demo.ui.main.ProtocolActivity;
import com.example.dijingyu.app_demo.utils.Tools;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


/**
 * 作者：邸某某
 * 时间：2019/5/5
 */

public class LoginFragment extends BaseFragment<LoginOrBindPresenter, LoginOrBindView> implements LoginOrBindView {


    private TextView mTv;
    private LinearLayout mQq;
    private LinearLayout mSing;
    private EditText mEdPhone;
    private Button mSend;
    private String mString;
    private VerifyFragment mVerifyFragment;
    private static int COUNT_DOWN_TIME = 60;
    private int mTime = COUNT_DOWN_TIME;
    private Handler mHandler;
    public String mVerifyCode = "";

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())){
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //添加到回退栈
        fragmentTransaction.addToBackStack(null);
        mVerifyFragment = VerifyFragment.newInstance(mVerifyCode);
        fragmentTransaction.add(R.id.fragment, mVerifyFragment).commit();
        Tools.closeKeyBoard(getActivity());
    }


    @Override
    public void onSuccess(LoginInfo bean) {

    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }

    @Override
    public String getPhone() {
        return mEdPhone.getText().toString().trim();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void go2MainActivity() {
        ShowActivity.startAct(getContext());
        getActivity().finish();
    }

    @Override
    public void setData(String data) {
        this.mVerifyCode = data;
        if (mVerifyFragment != null){
            mVerifyFragment.setData(data);
        }
    }

    /**
     * 根据输入框中是否有内容,切换发送验证码的背景
     * @param s
     */
    private void switchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)){
            mSend.setBackgroundResource(R.drawable.bg_login_button);
            mSend.setEnabled(false);
        }else {
            mSend.setBackgroundResource(R.drawable.bg_login_button_on);
            mSend.setEnabled(true);
        }
    }

    @Override
    protected void initView(View inflate) {
        mTv = inflate.findViewById(R.id.login_tv_service);
        mEdPhone = inflate.findViewById(R.id.ed_phone);
            //文本发生改变监听
            mEdPhone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    switchBtnState(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        mQq = inflate.findViewById(R.id.qqLoginLinear);
        mSend = inflate.findViewById(R.id.bt_send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerifyCode();
                addVerifyFragment();
                time();
            }
        });
        final UMShareAPI umShareAPI = UMShareAPI.get(getContext());
        mQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                umShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);
            }
        });
        mSing = inflate.findViewById(R.id.singLoginLinear);
        mSing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
            }
        });
        mString = getResources().getString(R.string.login_service);
        SpannableString spannableString = new SpannableString(mString);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                ProtocolActivity.startAct(getContext());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(getContext(), R.color.service));//设置颜色
                ds.setUnderlineText(true);//去掉下划线
            }

        };
        spannableString.setSpan(clickableSpan2, mString.length()-4, mString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mTv.setMovementMethod(LinkMovementMethod.getInstance());
        mTv.setText(spannableString);
    }

    private void time() {
        //避免多次执行倒计时
        if (mTime>0 && mTime<COUNT_DOWN_TIME){
            return;
        }
        countDown();
    }

    /**
     * 倒计时,如果执行中,不要再调用
     */
    public void countDown() {
        if (mHandler  == null){
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //避免倒计时变成负的
                if (mTime <= 0){
                    mTime = COUNT_DOWN_TIME;
                    return;
                }
                mTime--;
                if (mVerifyFragment != null){
                    mVerifyFragment.setCountDownTime(mTime);
                }
                countDown();
            }
        },1000);
    }

    private void getVerifyCode() {
        //if (60s之内如果发送过,就不用发送)
        /*if (mTime>0 && mTime != COUNT_DOWN_TIME){
            //倒计时中
            return;
        }
        mPresenter.getVerifyCode();*/

        if (mTime>0 && mTime<COUNT_DOWN_TIME){
            //倒计时中
            return;
        }
        mVerifyCode = "";
        mPresenter.getVerifyCode();
    }


    @Override
    protected LoginOrBindPresenter initPresenter() {
        return new LoginOrBindPresenter();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_login;
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
            ShowActivity.startAct(getContext());
            Toast.makeText(getContext(), "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getContext(), "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
