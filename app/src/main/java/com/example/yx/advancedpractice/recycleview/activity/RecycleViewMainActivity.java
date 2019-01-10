package com.example.yx.advancedpractice.recycleview.activity;

import android.content.Intent;
import android.view.View;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewMainActivity extends BaseRvActivity {

    private CommonRvAdapter commonRvAdapter;
    private List<CommonDataBean> dataBeanList = new ArrayList<>();

    @Override
    protected void setAdapter() {
        commonRvAdapter = new CommonRvAdapter(dataBeanList,this);
        rvCommon.setAdapter(commonRvAdapter);
    }

    @Override
    public String setTitle() {
        return getResources().getString(R.string.rv_practice);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        commonRvAdapter.setOnItemClickListener(new CommonRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0:
                       startActivity( new Intent(RecycleViewMainActivity.this,BasicRvActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(RecycleViewMainActivity.this,DividerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(RecycleViewMainActivity.this,MutilTypeActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(RecycleViewMainActivity.this,LayoutManagerActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(RecycleViewMainActivity.this,HeaderAndFooterActivity.class));
                    case 5:
                        startActivity(new Intent(RecycleViewMainActivity.this,SwipeRvActivity.class));
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    protected void initData() {
        dataBeanList.add(new CommonDataBean("基础功能练习-数据展示"));
        dataBeanList.add(new CommonDataBean("基础功能练习-分割线"));
        dataBeanList.add(new CommonDataBean("基础功能练习-多 type 类型展示"));
        dataBeanList.add(new CommonDataBean("基础功能练习- 设置 LayoutManager"));
        dataBeanList.add(new CommonDataBean("基础功能练习-添加 header 和 footer"));
        dataBeanList.add(new CommonDataBean("基础功能练习-侧滑删除"));
        dataBeanList.add(new CommonDataBean("基础功能练习-拖动排序"));
        dataBeanList.add(new CommonDataBean("基础功能练习-下拉刷新上拉加载"));
        dataBeanList.add(new CommonDataBean("基础功能练习-刷新方式及动画设置"));
        dataBeanList.add(new CommonDataBean("进阶练习-自定义LayoutManager"));



        commonRvAdapter.notifyDataSetChanged();
    }
}
