package com.example.dijingyu.app_demo.base;

/**
 * 作者：邸某某
 * 时间：2019/4/30
 */

public interface BaseView<T> {

    void onSuccess(T bean);
    void onFail(String tips);

    void toastShort(String string);
}
