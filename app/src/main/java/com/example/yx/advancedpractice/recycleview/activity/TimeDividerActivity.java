package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.RecyclerView;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;
import com.example.yx.advancedpractice.recycleview.dividerItem.TimeAxisDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 29/12/18 下午4:19
 * 分割线 练习 -- 时间轴样式
 */
public class TimeDividerActivity extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private CommonRvAdapter adapter;
    @Override
    protected void setAdapter() {
        adapter = new CommonRvAdapter(dataBeanList,this);
        rvCommon.setAdapter(adapter);
    }

    /**
     * 设置分割线样式
     * @return
     */
    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return new TimeAxisDivider(this);
    }

    @Override
    public String setTitle() {
        return "时间轴布局" ;
    }

    @Override
    protected void initData() {

        for (int i = 0; i < 50; i++) {
            CommonDataBean bean = new CommonDataBean("数据" + ( i %2 ==0?i +"\n\n\n": i));
            dataBeanList.add(bean);
        }
        adapter.notifyDataSetChanged();

    }
}
