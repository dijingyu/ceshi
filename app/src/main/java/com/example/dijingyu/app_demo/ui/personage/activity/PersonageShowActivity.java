package com.example.dijingyu.app_demo.ui.personage.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.PersonageInfoBean;
import com.example.dijingyu.app_demo.bean.UpDataBean;
import com.example.dijingyu.app_demo.mvp.presenter.PersonageInfoP;
import com.example.dijingyu.app_demo.mvp.view.PersonageInfoV;
import com.example.dijingyu.app_demo.ui.main.LoginActivity;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.utils.ToastUtil;

public class PersonageShowActivity extends BaseActivity<PersonageInfoP, PersonageInfoV> implements PersonageInfoV, View.OnClickListener {


    private ImageView mIvBack;
    private ImageView mIvTou;
    private RelativeLayout mRelTou;
    private TextView mTvName;
    private RelativeLayout mRelName;
    private TextView mTvSex;
    private RelativeLayout mRelSex;
    private TextView mTvQianming;
    private RelativeLayout mRelQianming;
    private boolean isUpData = false;

    /**
     * 退出登录
     */
    
    private Button mBtEditLogin;
    private String mToken;
    private PopupWindow mPopupWindow;

    /**
     * 个人信息
     */
    @Override
    protected void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mIvTou = findViewById(R.id.iv_tou);
        mRelTou = findViewById(R.id.rel_tou);
        mRelTou.setOnClickListener(this);
        mTvName = findViewById(R.id.tv_name);
        mRelName = findViewById(R.id.rel_name);
        mRelName.setOnClickListener(this);
        mTvSex = findViewById(R.id.tv_sex);
        mRelSex = findViewById(R.id.rel_sex);
        mRelSex.setOnClickListener(this);
        mTvQianming = findViewById(R.id.tv_qianming);
        mRelQianming = findViewById(R.id.rel_qianming);
        mRelQianming.setOnClickListener(this);
        mBtEditLogin = findViewById(R.id.bt_edit_login);
        mBtEditLogin.setOnClickListener(this);
        initData();
    }

    private void initData() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mPresenter.getPersonageInfo(mToken);
    }

    @Override
    protected PersonageInfoP initPresenter() {
        return new PersonageInfoP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_personage_show;
    }

    @Override
    public void onSuccess(Object bean) {
        if (bean instanceof PersonageInfoBean) {
            PersonageInfoBean infoBean = (PersonageInfoBean) bean;
            String photo = infoBean.getResult().getPhoto();
            String name = infoBean.getResult().getUserName();
            SpUtil.setParam(Constants.USERNAME, name);
            String sex = infoBean.getResult().getGender();
            SpUtil.setParam(Constants.GENDER, sex);
            String description = infoBean.getResult().getDescription();
            SpUtil.setParam(Constants.DESCRIPTION, description);
            RequestOptions request = new RequestOptions().circleCrop();
            Glide.with(this).load(photo).apply(request).into(mIvTou);
            mTvName.setText(name);
            mTvQianming.setText(description);
            assert sex != null;
            switch (sex) {
                case "M":
                    mTvSex.setText("男");
                    break;
                case "U":
                    mTvSex.setText("保密");
                    break;
                default:
                    mTvSex.setText("女");
                    break;
            }
            if (isUpData) {
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            }
            isUpData = false;
        }else if (bean instanceof UpDataBean){
            UpDataBean dataBean = (UpDataBean) bean;
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            popDismis(mPopupWindow);
            mPresenter.getPersonageInfo(mToken);
            hideLoading();
        }
    }

    @Override
    public void onFail(String tips) {
        toastShort(tips);
    }

    @Override
    public void toastShort(String string) {
        ToastUtil.showShort(string);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
            mPresenter.getPersonageInfo(mToken);
            isUpData = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_tou:
                startActivity(new Intent(this,UpDataTouActivity.class));
                break;
            case R.id.rel_name:
                Intent intent = new Intent(this, AlterActivity.class);
                intent.putExtra("type",Constants.NAME);
                startActivityForResult(intent,1);
                break;
            case R.id.rel_sex:
                pop();
                break;
            case R.id.rel_qianming:
                Intent intent2 = new Intent(this, AlterActivity.class);
                intent2.putExtra("type",Constants.QIANMING);
                startActivityForResult(intent2,1);
                break;
            case R.id.bt_edit_login:
                SpUtil.setParam(Constants.TOKEN,"");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void pop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.acntivity_pop_updata_sex, null, false);
        mPopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDismis(mPopupWindow);
            }
        });
        Button cancle = inflate.findViewById(R.id.bt_cancel);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDismis(mPopupWindow);
            }
        });
        Button sexM = inflate.findViewById(R.id.bt_m);
        sexM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upDataSex(mToken,"M",Constants.SEX);
                showLoading();
            }
        });

        final Button sexF = inflate.findViewById(R.id.bt_f);
        sexF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upDataSex(mToken,"F",Constants.SEX);
                showLoading();
            }
        });
        Button sexU = inflate.findViewById(R.id.bt_u);
        sexU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upDataSex(mToken,"U",Constants.SEX);
                showLoading();
            }
        });
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        mPopupWindow.showAsDropDown(mIvBack);
    }

    private void popDismis(PopupWindow popupWindow) {
        popupWindow.dismiss();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }
}
