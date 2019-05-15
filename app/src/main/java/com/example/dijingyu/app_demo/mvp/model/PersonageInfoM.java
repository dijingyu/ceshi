package com.example.dijingyu.app_demo.mvp.model;

import com.example.dijingyu.app_demo.base.ApiService;
import com.example.dijingyu.app_demo.base.BaseModel;
import com.example.dijingyu.app_demo.bean.PersonageInfoBean;
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

public class PersonageInfoM extends BaseModel {
    public void getPersonageInfo(String token, final ResultCallBack<PersonageInfoBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BASE_URL, ApiService.class);
        HashMap<String, String> headMap = new HashMap<>();
        headMap.put("banmi-app-token",token);
        Observable<PersonageInfoBean> info = apiserver.getPersonageInfo(headMap);
        info.compose(RxUtils.<PersonageInfoBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PersonageInfoBean>() {
                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonageInfoBean personageInfoBean) {
                        resultCallBack.onSuccess(personageInfoBean);
                    }
                });
    }
}
