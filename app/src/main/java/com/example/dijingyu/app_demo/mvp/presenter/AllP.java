package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.AllBean;
import com.example.dijingyu.app_demo.mvp.model.AllM;
import com.example.dijingyu.app_demo.mvp.view.AllV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/14
 */

public class AllP extends BasePresenter<AllV> {

    private AllM mModel;

    @Override
    protected void initModel() {
        mModel = new AllM();
        mModels.add(mModel);
    }

    public void getData(String token, int id, String page) {
        mModel.getData(token,id,page, new ResultCallBack<AllBean>() {
            @Override
            public void onSuccess(AllBean bean) {
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.onFail(msg);
            }
        });
    }
}
