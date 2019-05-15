package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.UpDataBean;
import com.example.dijingyu.app_demo.net.BaseObserver;
import com.example.dijingyu.app_demo.net.HttpUtils;
import com.example.dijingyu.app_demo.net.ResultCallBack;
import com.example.dijingyu.app_demo.net.RxUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class UpDataM extends BaseModel {

    private Observable<UpDataBean> mDataName;

    public void upData(String token, String upData, String type, final ResultCallBack<UpDataBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headMap = new HashMap<>();
        headMap.put("banmi-app-token",token);
        if (Constants.NAME.equals(type)){
            mDataName = apiserver.upDataName(headMap, upData);
        }else if (Constants.SEX.equals(type)){
            mDataName = apiserver.upDataSex(headMap, upData);
        }else if (Constants.QIANMING.equals(type)){
            mDataName = apiserver.upDataQianming(headMap, upData);
        }else {
            resultCallBack.onFail("参数有误");
        }
        if (mDataName != null){
            mDataName.compose(RxUtils.<UpDataBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<UpDataBean>() {
                        @Override
                        public void error(String msg) {
                            resultCallBack.onFail(msg);
                        }

                        @Override
                        protected void subscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UpDataBean upDataBean) {
                            resultCallBack.onSuccess(upDataBean);
                        }
                    });
        }
    }
}
