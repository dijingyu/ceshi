package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.UpDataBean;
import com.example.dijingyu.app_demo.mvp.model.UpDataM;
import com.example.dijingyu.app_demo.mvp.view.UpDataV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class UpDataP extends BasePresenter<UpDataV> {

    private UpDataM mModel;

    @Override
    protected void initModel() {
        mModel = new UpDataM();
        mModels.add(mModel);
    }

    public void upData(String token,String upData,String type){
        mModel.upData(token,upData,type, new ResultCallBack<UpDataBean>() {
            @Override
            public void onSuccess(UpDataBean bean) {
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
