package com.example.yx.advancedpractice.recycleview.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CityBean;
import com.example.yx.advancedpractice.recycleview.adapter.GroupDividerAdapter;
import com.example.yx.advancedpractice.recycleview.dividerItem.GroupDivider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yangxia
 * @since 29/12/18 下午5:11
 *  分割线练习--分组粘性头部
 */
public class GroupDividerActivity extends BaseRvActivity {

    private List<CityBean> cityBeanList = new ArrayList<>();
    private GroupDividerAdapter adapter ;
    @Override
    protected void setAdapter() {
        adapter = new GroupDividerAdapter(cityBeanList,this);
        rvCommon.setAdapter(adapter);
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return new GroupDivider(this,cityBeanList);
    }

    @Override
    public String setTitle() {
        return "分组粘性头部";
    }

    @Override
    protected void initData() {
        String cityJson = getJson("CityJson.json",this);
        try{
            List<CityBean> cityBeans = JSONArray.parseArray(cityJson, CityBean.class);
            if (cityBeans ==null && cityBeans.size() ==0){
                return;
            }
            for (int i = 0; i < cityBeans.size() ; i++) {
                CityBean cityBean = cityBeans.get(i);
                String firstLetter = cityBean.getPinyin() ==null? "#" :cityBean.getPinyin().substring(0,1);
                cityBean.setFirstLetter(firstLetter);
                if (i ==0){
                    cityBean.setGroupFirst(true);
                }else {
                    CityBean lastCityBean = cityBeans.get(i-1);
                    String lastLetter = lastCityBean.getPinyin() ==null? "#" :lastCityBean.getPinyin().substring(0,1);
                    if (firstLetter.equals(lastLetter)){
                        lastCityBean.setGroupLast(false);
                        cityBean.setGroupFirst(false);
                    }else {
                        cityBean.setGroupFirst(true);
                        lastCityBean.setGroupLast(true);
                    }
                }
                cityBeanList.add(cityBean);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 读取本地 jSON 文件
     * @param fileName
     * @param context
     * @return
     */
    public  String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
