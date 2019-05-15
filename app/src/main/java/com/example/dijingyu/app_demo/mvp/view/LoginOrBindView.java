package com.example.dijingyu.app_demo.mvp.view;

import android.app.Activity;
import android.content.Context;

import com.example.dijingyu.app_demo.base.BaseView;
import com.example.dijingyu.app_demo.bean.LoginInfo;


/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public interface LoginOrBindView extends BaseView<LoginInfo> {
    String getPhone();
    Activity getAct();

    void go2MainActivity();

    void setData(String data);
}
