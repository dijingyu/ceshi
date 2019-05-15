package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.bean.FollowBean;
import com.example.dijingyu.app_demo.mvp.model.BanMiM;
import com.example.dijingyu.app_demo.mvp.view.BanMiV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class BanMiP extends BasePresenter<BanMiV> {

    private BanMiM mModel;

    @Override
    protected void initModel() {
        mModel = new BanMiM();
        mModels.add(mModel);
    }

    public void getData(String token,String page){
        mModel.getData(token,page,new ResultCallBack<BanMiBean>() {
            @Override
            public void onSuccess(BanMiBean bean) {
                if (mView !=null)
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                if (mView !=null)
                mView.onFail(msg);
            }
        });
    }

    public void getFollow(String token,String page){
        mModel.getFollow(token,page,new ResultCallBack<BanMiBean>() {
            @Override
            public void onSuccess(BanMiBean bean) {
                if (mView !=null)
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                if (mView !=null)
                mView.onFail(msg);
            }
        });
    }

    public void follow(String token,String id){
        mModel.follow(token,id,new ResultCallBack<FollowBean>() {
            @Override
            public void onSuccess(FollowBean bean) {
                if (mView !=null)
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                if (mView !=null)
                mView.onFail(msg);
            }
        });
    }


    public void unFollow(String token,String id){
        mModel.unFollow(token,id,new ResultCallBack<FollowBean>() {
            @Override
            public void onSuccess(FollowBean bean) {
                if (mView !=null)
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                if (mView !=null)
                mView.onFail(msg);
            }
        });
    }
}
