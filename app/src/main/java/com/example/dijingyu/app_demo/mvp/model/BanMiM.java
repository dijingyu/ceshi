package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.bean.FollowBean;
import com.example.dijingyu.app_demo.net.BaseObserver;
import com.example.dijingyu.app_demo.net.HttpUtils;
import com.example.dijingyu.app_demo.net.ResultCallBack;
import com.example.dijingyu.app_demo.net.RxUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class BanMiM extends BaseModel {
    public void getData(String token,String page, final ResultCallBack<BanMiBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("banmi-app-token",token);
        Observable<BanMiBean> banMiData = apiserver.getBanMiData(headerMap,page);
        banMiData.compose(RxUtils.<BanMiBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BanMiBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiBean banMiBean) {
                        resultCallBack.onSuccess(banMiBean);
                    }
                });
    }

    public void follow(String token, String id, final ResultCallBack<FollowBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("banmi-app-token",token);
        Observable<FollowBean> banMiData = apiserver.follow(headerMap,id);
        banMiData.compose(RxUtils.<FollowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FollowBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean banMiBean) {
                        resultCallBack.onSuccess(banMiBean);
                    }
                });
    }

    public void unFollow(String token, String id, final ResultCallBack<FollowBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("banmi-app-token",token);
        Observable<FollowBean> banMiData = apiserver.unFollow(headerMap,id);
        banMiData.compose(RxUtils.<FollowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FollowBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean banMiBean) {
                        resultCallBack.onSuccess(banMiBean);
                    }
                });
    }

    public void getFollow(String token, String page, final ResultCallBack<BanMiBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("banmi-app-token",token);
        Observable<BanMiBean> banMiData = apiserver.getFollow(headerMap,page);
        banMiData.compose(RxUtils.<BanMiBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BanMiBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BanMiBean banMiBean) {
                        resultCallBack.onSuccess(banMiBean);
                    }
                });
    }
}
