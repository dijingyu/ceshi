package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.bean.MyCollectBean;
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
 * 时间：2019/5/12
 */

public class MyCollectM extends BaseModel {
    public void getCollect(String token, String pager, final ResultCallBack<MyCollectBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("banmi-app-token",token);
        Observable<MyCollectBean> collect = apiserver.getCollect(headMap, pager);
        collect.compose(RxUtils.<MyCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyCollectBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MyCollectBean myCollectBean) {
                        resultCallBack.onSuccess(myCollectBean);
                    }
                });

    }
}
