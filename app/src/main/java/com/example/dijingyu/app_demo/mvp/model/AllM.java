package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.bean.AllBean;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.net.BaseObserver;
import com.example.dijingyu.app_demo.net.HttpUtils;
import com.example.dijingyu.app_demo.net.ResultCallBack;
import com.example.dijingyu.app_demo.net.RxUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：邸某某
 * 时间：2019/5/14
 */

public class AllM extends BaseModel {
    public void getData(String token, int id, String page, final ResultCallBack<AllBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("banmi-app-token",token);
        Observable<AllBean> banMiData = apiserver.getAll(headerMap,id+"",page);
        banMiData.compose(RxUtils.<AllBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AllBean banMiBean) {
                        resultCallBack.onSuccess(banMiBean);
                    }
                });
    }
}
