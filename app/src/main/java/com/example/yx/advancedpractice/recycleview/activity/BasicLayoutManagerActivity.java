package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.LayoutManagerRvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 4/1/19 上午10:38
 */
public class BasicLayoutManagerActivity extends BaseRvActivity {

    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private LayoutManagerRvAdapter adapter ;
    @Override
    protected void  setAdapter() {
        adapter = new LayoutManagerRvAdapter(dataBeanList,this);
        rvCommon.setAdapter(adapter);
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return null;
    }

    @Override
    public String setTitle() {
        return "设置 LayoutManager";
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        viewTitleBar.setTvRightTxt("设置", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettingLayoutMangerDialog();
            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected void initData() {

        for (int i = 0; i <50 ; i++) {
            if (i % 2 ==0){
                dataBeanList.add(new CommonDataBean("数据很多很多,导致换行"+i+"\n\n"));
            }else {
                dataBeanList.add(new CommonDataBean("数据"+i));
            }
        }

        adapter.notifyDataSetChanged();

    }






}
