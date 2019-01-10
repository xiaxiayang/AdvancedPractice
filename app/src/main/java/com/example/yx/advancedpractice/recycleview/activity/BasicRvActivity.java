package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.RecyclerView;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 27/12/18 下午2:42
 * 基础功能 demo 类
 */
public class BasicRvActivity extends BaseRvActivity {

   private CommonRvAdapter commonRvAdapter;
   private List<CommonDataBean> dataBeanList = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return super.setDivider();
    }

    @Override
    protected void setAdapter() {
        commonRvAdapter = new CommonRvAdapter(dataBeanList,this);
       rvCommon.setAdapter(commonRvAdapter);
    }

    @Override
    public String setTitle() {
        return getResources().getString(R.string.rv_practice_basic);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            dataBeanList.add(new CommonDataBean("数据" + i));
        }
        commonRvAdapter.notifyDataSetChanged();
    }
}
