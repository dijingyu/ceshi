package com.example.dijingyu.app_demo.utils;

import android.os.Environment;

import java.io.File;

public class SDUtils {

    //sd卡是否存在
    public static boolean sdCardIsAvailable(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    //获取sd卡路径
    public static String getSDPath(){
        File sdDir = null;
        if(sdCardIsAvailable()){
            sdDir = Environment.getExternalStorageDirectory(); //获取根目录
        }
        return sdDir.toString();
    }

}
