package com.example.dijingyu.app_demo.mvp.presenter;

import com.example.dijingyu.app_demo.base.BasePresenter;
import com.example.dijingyu.app_demo.bean.PersonageInfoBean;
import com.example.dijingyu.app_demo.bean.UpDataBean;
import com.example.dijingyu.app_demo.mvp.model.PersonageInfoM;
import com.example.dijingyu.app_demo.mvp.model.UpDataM;
import com.example.dijingyu.app_demo.mvp.view.PersonageInfoV;
import com.example.dijingyu.app_demo.net.ResultCallBack;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class PersonageInfoP extends BasePresenter<PersonageInfoV> {

    private PersonageInfoM mModel;
    private UpDataM mUpDataM;

    @Override
    protected void initModel() {
        mModel = new PersonageInfoM();
        mModels.add(mModel);
        mUpDataM = new UpDataM();
        mModels.add(mUpDataM);
    }

    public void getPersonageInfo(String token){
        mModel.getPersonageInfo(token, new ResultCallBack<PersonageInfoBean>() {
            @Override
            public void onSuccess(PersonageInfoBean bean) {
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
    public void upDataSex(String token,String updata,String type){
        mUpDataM.upData(token,updata,type, new ResultCallBack<UpDataBean>() {
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
