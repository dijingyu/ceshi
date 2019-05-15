package com.example.dijingyu.app_demo.bean;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class UpDataBean {
    /**
     * code : 0
     * desc :
     * results : {}
     */

    private int code;
    private String desc;
    private ResultsBean results;

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

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
    }
}
