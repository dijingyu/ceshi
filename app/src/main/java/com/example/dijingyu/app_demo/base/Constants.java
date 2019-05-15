package com.example.dijingyu.app_demo.base;

import android.os.Environment;

import java.io.File;

/**
 * 作者：邸某某
 * 时间：2019/3/5
 */

public interface Constants {
    //是否为debug状态,正式上线版本需要改为false
    boolean isDebug = true;


    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "codeest" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = BaseApp.getInstance().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";


    String TOKEN = "token";
    String DESC = "description";
    String USERNAME = "userName";
    String GENDER = "gender";
    String EMAIL = "email";
    String PHOTO = "photo";
    String DESCRIPTION = "description";
    String PHONE = "phone";
    String NAME = "name";
    String SEX = "sex";
    String QIANMING = "qianming";
    String VERIFY_CODE = "verify_code";
    String ISFIRST = "isfirst";
}
