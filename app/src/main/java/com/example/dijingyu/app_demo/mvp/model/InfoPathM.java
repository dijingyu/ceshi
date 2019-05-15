package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.bean.HomeBean;
import com.example.dijingyu.app_demo.bean.InfoPathBean;
import com.example.dijingyu.app_demo.net.BaseObserver;
import com.example.dijingyu.app_demo.net.HttpUtils;
import com.example.dijingyu.app_demo.net.ResultCallBack;
import com.example.dijingyu.app_demo.net.RxUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：邸某某
 * 时间：2019/5/13
 */

public class InfoPathM extends BaseModel {
    public void getData(String token,String id,String page,final ResultCallBack<InfoPathBean> resultCallBack) {
        Map<String, String> handerMap = new HashMap<>();
        handerMap.put("banmi-app-token", token);
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        Observable<InfoPathBean> homeData = apiserver.getPath(handerMap,id,page);
        homeData.compose(RxUtils.<InfoPathBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<InfoPathBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InfoPathBean homeBean) {
                        resultCallBack.onSuccess(homeBean);
                    }
                });
    }
}
