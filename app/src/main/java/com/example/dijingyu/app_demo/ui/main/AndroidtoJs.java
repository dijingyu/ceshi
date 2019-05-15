package com.example.dijingyu.app_demo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.dijingyu.app_demo.ui.show.activity.PathInfoActivity;
import com.example.dijingyu.app_demo.ui.show.activity.WebActivity;

/**
 * 作者：邸某某
 * 时间：2019/5/14
 */

public class AndroidtoJs extends Object {

        private final Context mContext;

        public AndroidtoJs(WebActivity webActivity) {
                mContext = webActivity;
        }

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void callAndroid(String id) {
                Log.i("123456", "href: ====>"+id);

        }
        @JavascriptInterface
        public void callAndroid(String route_details,int id) {
                Intent intent = new Intent(mContext, PathInfoActivity.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
        }
}
