package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.FlexBoxLayoutAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yangxia
 * @since 4/1/19 下午5:30
 */
public class FlexBoxLayoutManagerActivity extends BaseRvActivity {

    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private FlexBoxLayoutAdapter adapter ;

    @Override
    protected void setAdapter() {
        adapter = new FlexBoxLayoutAdapter(dataBeanList,this);
        rvCommon.setAdapter(adapter);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        FlexboxLayoutManager manager = new FlexboxLayoutManager(this,FlexDirection.ROW);
        manager.setAlignItems(AlignItems.CENTER);
        manager.setFlexWrap(FlexWrap.WRAP);
        return  manager;
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return null;
    }

    @Override
    public String setTitle() {
        return " FlexBoxLayoutManager 设置";
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        adapter.setOnItemClickListener(new FlexBoxLayoutAdapter.onItemClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(FlexBoxLayoutManagerActivity.this, dataBeanList.get(position).getData(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {

        dataBeanList.add(new CommonDataBean("左手指月"));
        dataBeanList.add(new CommonDataBean("不染"));
        dataBeanList.add(new CommonDataBean("That Girl"));
        dataBeanList.add(new CommonDataBean("达尔文"));
        dataBeanList.add(new CommonDataBean("你要的全拿走+ 等+ 不染"));
        dataBeanList.add(new CommonDataBean("Run"));
        dataBeanList.add(new CommonDataBean("你要的全拿走"));
        dataBeanList.add(new CommonDataBean("That Girl"));
        dataBeanList.add(new CommonDataBean("等"));
        dataBeanList.add(new CommonDataBean("Run"));
        dataBeanList.add(new CommonDataBean("等"));
        dataBeanList.add(new CommonDataBean("That Girl"));
        dataBeanList.add(new CommonDataBean("Run"));
        dataBeanList.add(new CommonDataBean("等"));
        dataBeanList.add(new CommonDataBean("你要的全拿走+ 等+ 不染"));
        dataBeanList.add(new CommonDataBean("That Girl"));
        dataBeanList.add(new CommonDataBean("达尔文"));

        adapter.notifyDataSetChanged();




    }
}
