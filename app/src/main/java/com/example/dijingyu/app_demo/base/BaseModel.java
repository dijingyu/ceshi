package com.example.dijingyu.app_demo.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：邸某某
 * 时间：2019/4/30
 */

public class BaseModel {
    private CompositeDisposable mCompositeDisposable;

    public void onDestory() {
        if (mCompositeDisposable != null) {
            //切换所有的Disposable对象
            mCompositeDisposable.clear();
        }
    }

    public void addDisposable(Disposable d){
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }
}
