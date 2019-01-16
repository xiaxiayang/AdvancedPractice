package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.CommonRvAdapter;
import com.example.yx.advancedpractice.recycleview.dividerItem.BasicDivider;
import com.example.yx.advancedpractice.widget.DividerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 28/12/18 上午11:48
 * 简单的 设置 分割线 的 demo lei
 */
public class BasicDividerActivity  extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private CommonRvAdapter adapter ;
    @Override
    protected void setAdapter() {
        adapter = new CommonRvAdapter(dataBeanList,this);
       rvCommon.setAdapter(adapter);
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return  new BasicDivider(this);
    }

    @Override
    public String setTitle() {
        return getResources().getString(R.string.basic_divider) ;
    }

    @Override
    protected void initView() {
        super.initView();
        viewTitleBar.setTvRightTxt("设置", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DividerDialog dividerDialog = new DividerDialog(BasicDividerActivity.this);
                dividerDialog.setOnConfirmClickListener(new DividerDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(int left, int top, int right, int bottom) {
                        //移除之前的 divider
//                        rvCommon.removeItemDecorationAt(0);
                        //重新设置 divider
                        BasicDivider divider = new BasicDivider(BasicDividerActivity.this,left,top,right,bottom);
                        rvCommon.addItemDecoration(divider);
                    }
                });
                dividerDialog.show();
            }
        });
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            dataBeanList.add(new CommonDataBean("数据"+i ));
        }
        adapter.notifyDataSetChanged();

    }
}
