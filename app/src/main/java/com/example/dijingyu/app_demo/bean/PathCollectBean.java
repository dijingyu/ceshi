package com.example.dijingyu.app_demo.bean;

/**
 * 作者：邸某某
 * 时间：2019/5/8
 */

public class PathCollectBean {
    /**
     * code : 0
     * desc : 用户已收藏线路
     * result : {}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
    }
}
