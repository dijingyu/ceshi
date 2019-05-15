package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.MyCollectBean;
import com.example.dijingyu.app_demo.mvp.model.MyCollectM;
import com.example.dijingyu.app_demo.mvp.view.MyCollectV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/12
 */

public class MyCollectP extends BasePresenter<MyCollectV> {

    private MyCollectM mModel;

    @Override
    protected void initModel() {
        mModel = new MyCollectM();
        mModels.add(mModel);
    }

    public void getCollect(String token , String page){
        mModel.getCollect(token,page,new ResultCallBack<MyCollectBean>() {
            @Override
            public void onSuccess(MyCollectBean bean) {
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
