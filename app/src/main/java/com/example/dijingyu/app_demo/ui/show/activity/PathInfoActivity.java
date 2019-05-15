package com.example.dijingyu.app_demo.ui.show.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.PathCollectBean;
import com.example.dijingyu.app_demo.bean.PathInfoBean;
import com.example.dijingyu.app_demo.mvp.presenter.PathInfoP;
import com.example.dijingyu.app_demo.mvp.view.PathInfoV;
import com.example.dijingyu.app_demo.ui.show.adapter.PathInfoRlvAdapter;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.utils.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PathInfoActivity extends BaseActivity<PathInfoP, PathInfoV> implements PathInfoV, View.OnClickListener {


    private TextView mTvCity;
    private TextView mTvNow;
    private TextView mTvTitle;
    private ImageView mIvClose;
    private Banner mBanner;
    private ImageView mIvTou;
    private TextView mTvName;
    private TextView mTvJop;
    private TextView mTvCityNow;
    private TextView mTvText;
    private RecyclerView mRlv;
    private TextView mTvAll;
    /**
     * 分享
     */
    private Button mBtShare;
    /**
     * 收藏
     */
    private Button mBtCollect;
    /**
     * 预览路线
     */
    private Button mBtPath;
    private Button mBtBuy;
    private String mToken;
    private PathInfoRlvAdapter mAdapter;
    private int mId;
    private boolean mIsCollected;
    private PathInfoBean mInfoBean;

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        mTvCity = findViewById(R.id.tv_city);
        mTvNow = findViewById(R.id.tv_now);
        mTvTitle = findViewById(R.id.tv_title);
        mIvClose = findViewById(R.id.iv_close);
        mIvClose.setOnClickListener(this);
        mBanner = findViewById(R.id.banner);
        mIvTou = findViewById(R.id.iv_tou);
        mTvName = findViewById(R.id.tv_name);
        mTvJop = findViewById(R.id.tv_jop);
        mTvCityNow = findViewById(R.id.tv_city_now);
        mTvText = findViewById(R.id.tv_text);
        mRlv = findViewById(R.id.rlv);
        mTvAll = findViewById(R.id.tv_all);
        mTvAll.setOnClickListener(this);
        mBtShare = findViewById(R.id.bt_share);
        mBtShare.setOnClickListener(this);
        mBtCollect = findViewById(R.id.bt_collect);
        mBtCollect.setOnClickListener(this);
        mBtPath = findViewById(R.id.bt_path);
        mBtPath.setOnClickListener(this);
        mBtBuy = findViewById(R.id.bt_buy);
        mBtBuy.setOnClickListener(this);
        mPresenter.getPathInfo(mToken, mId +"");
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<PathInfoBean.ResultBean.ReviewsBean> list = new ArrayList<>();
        mAdapter = new PathInfoRlvAdapter(list);
        mRlv.setAdapter(mAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void initData(PathInfoBean bean) {
        List<String> banner = bean.getResult().getCarousel();
        mBanner.setImages(banner).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();

        mIsCollected = bean.getResult().getRoute().isIsCollected();
        if (mIsCollected){
            mBtCollect.setText("已收藏");
        }else {
            mBtCollect.setText("收藏");
        }

        mTvCity.setText(bean.getResult().getRoute().getCity());
        mTvNow.setText(bean.getResult().getRoute().getTitle());
        mTvTitle.setText(bean.getResult().getRoute().getIntro());
        mTvCityNow.setText(bean.getResult().getBanmi().getLocation());
        mTvName.setText(bean.getResult().getBanmi().getName());
        RequestOptions request = new RequestOptions().circleCrop();
        Glide.with(PathInfoActivity.this).load(bean.getResult().getBanmi().getPhoto()).apply(request).into(mIvTou);
        mTvJop.setText(bean.getResult().getBanmi().getOccupation());
        mTvText.setText(bean.getResult().getBanmi().getIntroduction());
        mBtBuy.setText("¥ "+bean.getResult().getRoute().getPriceInCents());
        mAdapter.addData(bean.getResult().getReviews());
    }

    @Override
    protected PathInfoP initPresenter() {
        return new PathInfoP();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_path_info;
    }

    @Override
    public void onSuccess(Object bean) {
        if (bean instanceof PathInfoBean){
            mInfoBean = (PathInfoBean) bean;
            initData(mInfoBean);
        }else if (bean instanceof PathCollectBean){
            PathCollectBean collectBean = (PathCollectBean) bean;
            if (collectBean.getCode() == 0){
                hideLoading();
                String trim = mBtCollect.getText().toString().trim();
                if (trim.equals("收藏")){
                    mBtCollect.setText("已收藏");
                }else {
                    mBtCollect.setText("收藏");
                }
                toastShort(collectBean.getDesc());
            }
        }
    }

    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {
        ToastUtil.showLong(string);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.bt_share:
                share();
                break;
            case R.id.bt_collect:
                showLoading();
                String trim = mBtCollect.getText().toString().trim();
                if (trim.equals("收藏")){
                    mPresenter.collectPath(mToken,mId+"");
                }else {
                    mPresenter.cancelCollect(mToken,mId+"");
                }
                break;
            case R.id.bt_path:
                break;
            case R.id.bt_buy:
                break;
            case R.id.tv_all:
                Intent intent = new Intent(this, AllActivity.class);
                intent.putExtra("id",mId);
                startActivity(intent);
                break;
        }
    }

    private void share() {
        UMImage umimage = new UMImage(PathInfoActivity.this,mInfoBean.getResult().getRoute().getCardURL());
        new ShareAction(PathInfoActivity.this)
                .withMedia(umimage)
                .withText(mInfoBean.getResult().getRoute().getDescription())
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ)
                .setCallback(shareListener).open();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(PathInfoActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(PathInfoActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PathInfoActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

}
