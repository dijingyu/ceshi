package com.example.dijingyu.app_demo.utils;
import android.util.Log;

import com.example.dijingyu.app_demo.base.Constants;


public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
}
