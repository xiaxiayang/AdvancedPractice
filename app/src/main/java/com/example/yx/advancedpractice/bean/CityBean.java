package com.example.yx.advancedpractice.bean;

import java.io.Serializable;

/**
 * @author yangxia
 * @since 29/12/18 下午5:26
 *  城市实体类
 */
public class CityBean implements Serializable {

    /**
     * id : 340800
     * name : 安庆市
     * orderIndex : 340800
     * pinyin : anqingshi
     * provinceId : 340000
     * py : aqs
     * rowVersion : 0
     */

    private String id;
    private String name;
    private String orderIndex;
    private String pinyin;
    private String provinceId;
    private String py;
    private String rowVersion;
    /**
     * 首字母
     */
    private String firstLetter;
    /**
     * 是否是分组的第一个
     */
    private boolean isGroupFirst;
    /**
     * 是否是分组内元素最后一个
     */
    private  boolean isGroupLast;

    public boolean isGroupLast() {
        return isGroupLast;
    }

    public void setGroupLast(boolean groupLast) {
        isGroupLast = groupLast;
    }

    public boolean isGroupFirst() {
        return isGroupFirst;
    }

    public void setGroupFirst(boolean groupFirst) {
        this.isGroupFirst = groupFirst;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(String rowVersion) {
        this.rowVersion = rowVersion;
    }
}
