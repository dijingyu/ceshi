package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.bean.PathCollectBean;
import com.example.dijingyu.app_demo.bean.PathInfoBean;
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
 * 时间：2019/5/7
 */

public class PathInfoM extends BaseModel {
    public void getPathInfo(String token,String id, final ResultCallBack<PathInfoBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("banmi-app-token",token);
        Observable<PathInfoBean> pathfo = apiserver.getPathInfo(headMap, id);
        pathfo.compose(RxUtils.<PathInfoBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PathInfoBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PathInfoBean pathInfoBean) {
                        resultCallBack.onSuccess(pathInfoBean);
                    }
                });
    }

    public void collectPath(String token, String id, final ResultCallBack<PathCollectBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("banmi-app-token",token);
        Observable<PathCollectBean> pathfo = apiserver.collectPath(headMap, id);
        pathfo.compose(RxUtils.<PathCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PathCollectBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PathCollectBean pathInfoBean) {
                        resultCallBack.onSuccess(pathInfoBean);
                    }
                });
    }

    public void cancelCollect(String token, String id, final ResultCallBack<PathCollectBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("banmi-app-token",token);
        Observable<PathCollectBean> pathfo = apiserver.cancelCollect(headMap, id);
        pathfo.compose(RxUtils.<PathCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PathCollectBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PathCollectBean pathInfoBean) {
                        resultCallBack.onSuccess(pathInfoBean);
                    }
                });
    }
}
