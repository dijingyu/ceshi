package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.InfoPathBean;
import com.example.dijingyu.app_demo.mvp.model.InfoPathM;
import com.example.dijingyu.app_demo.mvp.view.InfoPathV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/13
 */

public class InfoPathP extends BasePresenter<InfoPathV> {
    private InfoPathM mModel;

    @Override
    protected void initModel() {
        mModel = new InfoPathM();
        mModels.add(mModel);
    }

    public void getData(String token,String id,String page) {
        mModel.getData(token, id, page, new ResultCallBack<InfoPathBean>() {
            @Override
            public void onSuccess(InfoPathBean bean) {
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
