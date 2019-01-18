package com.example.yx.advancedpractice.bean;

import java.io.Serializable;

/**
 * @author yangxia
 * @since 17/1/19 上午10:19
 */
public class DragDataBean implements Serializable {
    public DragDataBean(String data, Enum type) {
        this.data = data;
        this.type = type;
    }

    /**
     * 数据
     */
    private String data;
    /**
     * 类型
     */
    private Enum type;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }
}




