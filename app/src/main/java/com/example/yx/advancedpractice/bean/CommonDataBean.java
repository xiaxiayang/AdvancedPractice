package com.example.yx.advancedpractice.bean;

import java.io.Serializable;

/**
 * @author yangxia
 * @since 26/12/18 上午11:23
 */
public class CommonDataBean implements Serializable {

    private String data;

    public CommonDataBean() {
    }

    public CommonDataBean(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
