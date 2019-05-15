package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.HomeBean;
import com.example.dijingyu.app_demo.mvp.model.HomeM;
import com.example.dijingyu.app_demo.mvp.view.HomeV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/5
 */

public class HomeP extends BasePresenter<HomeV> {

    private HomeM mModel;

    @Override
    protected void initModel() {
        mModel = new HomeM();
        mModels.add(mModel);
    }

    public void getData(String token,String page) {
        mModel.getData(token,page, new ResultCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean bean) {
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
