package com.example.yx.advancedpractice.recycleview.activity;

import android.content.Intent;
import android.view.View;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 29/12/18 下午4:19
 * 分割线 练习
 */
public class DividerActivity extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private CommonRvAdapter adapter;
    @Override
    protected void setAdapter() {
        adapter = new CommonRvAdapter(dataBeanList,this);
       rvCommon.setAdapter(adapter);
    }

    @Override
    public String setTitle() {
        return getResources().getString(R.string.basic_divider) ;
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        adapter.setOnItemClickListener(new CommonRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(DividerActivity.this,BasicDividerActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(DividerActivity.this,TimeDividerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(DividerActivity.this,GroupDividerActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        dataBeanList.add(new CommonDataBean("基础样式分割线"));
        dataBeanList.add(new CommonDataBean("时间轴布局"));
        dataBeanList.add(new CommonDataBean("分组头部粘性布局"));
        adapter.notifyDataSetChanged();

    }
}
