package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.PathCollectBean;
import com.example.dijingyu.app_demo.bean.PathInfoBean;
import com.example.dijingyu.app_demo.mvp.model.PathInfoM;
import com.example.dijingyu.app_demo.mvp.view.PathInfoV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class PathInfoP extends BasePresenter<PathInfoV> {

    private PathInfoM mModel;

    @Override
    protected void initModel() {
        mModel = new PathInfoM();
        mModels.add(mModel);
    }

    public void getPathInfo(String token,String id){
        mModel.getPathInfo(token,id, new ResultCallBack<PathInfoBean>() {
            @Override
            public void onSuccess(PathInfoBean bean) {
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

    public void collectPath(String token,String id) {
        mModel.collectPath(token,id, new ResultCallBack<PathCollectBean>() {
            @Override
            public void onSuccess(PathCollectBean bean) {
                assert mView !=null;
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                assert mView !=null;
                mView.onFail(msg);
            }
        });
    }

    public void cancelCollect(String token, String id) {
        mModel.cancelCollect(token,id, new ResultCallBack<PathCollectBean>() {
            @Override
            public void onSuccess(PathCollectBean bean) {
                assert mView !=null;
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                assert mView !=null;
                mView.onFail(msg);
            }
        });
    }
}
