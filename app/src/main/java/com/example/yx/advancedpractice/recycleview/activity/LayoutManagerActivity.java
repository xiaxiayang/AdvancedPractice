package com.example.yx.advancedpractice.recycleview.activity;

import android.content.Intent;
import android.view.View;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 29/12/18 下午4:19
 * layoutManager 练习
 */
public class LayoutManagerActivity extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private CommonRvAdapter adapter;
    @Override
    protected void setAdapter() {
        adapter = new CommonRvAdapter(dataBeanList,this);
       rvCommon.setAdapter(adapter);
    }


    @Override
    public String setTitle() {
        return " LayoutManager 练习" ;
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        adapter.setOnItemClickListener(new CommonRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(LayoutManagerActivity.this,BasicLayoutManagerActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(LayoutManagerActivity.this,FlexBoxLayoutManagerActivity.class));
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        dataBeanList.add(new CommonDataBean("LayoutManager 基础设置"));
        dataBeanList.add(new CommonDataBean("FlexBoxLayoutManager 设置"));
        dataBeanList.add(new CommonDataBean("自定义 LayoutManager"));
        adapter.notifyDataSetChanged();

    }
}
