package com.example.dijingyu.app_demo.base;

import java.util.ArrayList;

/**
 * 作者：邸某某
 * 时间：2019/4/30
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V mView;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public void addView(V view) {
        mView = view;
        initModel();
    }

    protected abstract void initModel();

    public void onDestory() {
        //打断P层和V层的联系,
        mView = null;
        //掐断网络请求
        if (mModels.size()>0){
            for (BaseModel model :mModels) {
                model.onDestory();
            }
            mModels.clear();
        }
    }
}
