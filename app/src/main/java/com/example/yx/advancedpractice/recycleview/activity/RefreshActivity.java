package com.example.yx.advancedpractice.recycleview.activity;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.HeaderAndFooterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 28/12/18 上午11:48
 * 下拉刷新 activity
 */
public class RefreshActivity extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private HeaderAndFooterAdapter adapter ;
    @Override
    protected void setAdapter() {
        adapter = new HeaderAndFooterAdapter(dataBeanList,this);
       rvCommon.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        super.initView();
        View headerView  = LayoutInflater.from(this).inflate(R.layout.item_header,rvCommon,false);
        adapter.setHeaderView(headerView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer,rvCommon,false);
        adapter.setFooterView(footerView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RefreshActivity.this, "I am a Header", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        adapter.setOnItemClickListener(new HeaderAndFooterAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int bodyPosition ;
                if (adapter.getHeaderView() !=null){
                    bodyPosition = position -1;
                }else {
                    bodyPosition = position;
                }
                Toast.makeText(RefreshActivity.this, dataBeanList.get(bodyPosition).getData(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public String setTitle() {
        return  "添加 footer 和 header";
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            dataBeanList.add(new CommonDataBean("数据"+i ));
        }
        adapter.notifyDataSetChanged();

    }

    @TargetApi(23)
    @Override
    public void  showSettingLayoutMangerDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.ppw_setting,null);
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAsDropDown(viewTitleBar, 0, 0,Gravity.RIGHT);

        TextView tvLinear,tvGrid,tvStra;
        tvLinear = view.findViewById(R.id.tv_linear);
        tvGrid = view.findViewById(R.id.tv_grid);
        tvStra = view.findViewById(R.id.tv_staggered);
        tvLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCommon.setLayoutManager(new LinearLayoutManager(RefreshActivity.this));
                adapter.notifyDataSetChanged();

            }
        });
        tvGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCommon.setLayoutManager(new GridLayoutManager(RefreshActivity.this,2));
                final GridLayoutManager manager = (GridLayoutManager) rvCommon.getLayoutManager();
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int i) {
                        return  adapter.getItemViewType(i) == adapter.ITEM_TYPE_HEADER || adapter.getItemViewType(i) == adapter.ITEM_TYPE_FOOTER ? manager.getSpanCount():1;
                    }
                });
            }
        });
        tvStra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCommon.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

            }
        });



    }


}
