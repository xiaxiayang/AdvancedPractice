package com.example.yx.advancedpractice;

import android.content.Intent;
import android.view.View;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.activity.RecycleViewMainActivity;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseRvActivity {

    private CommonRvAdapter commonRvAdapter;
    private List<CommonDataBean> dataBeanList = new ArrayList<>();

    @Override
    protected void setAdapter() {
        commonRvAdapter = new CommonRvAdapter(dataBeanList,this);
        rvCommon.setAdapter(commonRvAdapter);
    }

    @Override
    public String setTitle() {
        return "advance practice";
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        commonRvAdapter.setOnItemClickListener(new CommonRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0:
                        startActivity( new Intent(MainActivity.this,RecycleViewMainActivity.class));
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    protected void initData() {
        dataBeanList.add(new CommonDataBean("RecycleView 练习"));
        dataBeanList.add(new CommonDataBean("启动模式"));
        dataBeanList.add(new CommonDataBean("Scroller 练习"));
        dataBeanList.add(new CommonDataBean("自定义 View"));
        commonRvAdapter.notifyDataSetChanged();
    }
}

