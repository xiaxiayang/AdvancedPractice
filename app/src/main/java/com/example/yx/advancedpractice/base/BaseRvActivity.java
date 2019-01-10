package com.example.yx.advancedpractice.base;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.widget.XNCommonTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yangxia
 * @since 26/12/18 下午1:25
 */
public abstract class BaseRvActivity extends AppCompatActivity {

    @BindView(R.id.view_title_bar)
   public XNCommonTitleBar viewTitleBar;
    @BindView(R.id.rv_common)
    public   RecyclerView rvCommon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        parseIntent();
        initView();
        bindEvent();
        initData();
    }

    /**
     * 获取布局 id
     */
    protected int setLayoutId() {
        return R.layout.layout_common;
    }

    /**
     * view 绑定
     */
    protected void initView() {
        if (setLayoutManager() !=null){
            rvCommon.setLayoutManager(setLayoutManager());
        }

        if (setDivider() !=null){
            rvCommon.addItemDecoration(setDivider());
        }

        setAdapter();
    }



    /**
     * 设置分割线
     * @return
     */
    protected RecyclerView.ItemDecoration setDivider(){
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
       return itemDecoration;

    }

    /**
     * 设置 LayoutManager
     * @return
     */
    protected RecyclerView.LayoutManager setLayoutManager(){

        return new LinearLayoutManager(this);

    }

    /**
     * 事件绑定
     */
    protected void bindEvent() {
        viewTitleBar.setIvBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (setTitle() != null) {
            viewTitleBar.setTitle(setTitle());
        }




    }

    /**
     * intent 参数处理
     */
    protected void parseIntent() {

    }

    /**
     * 设置 adapter
     *
     * @return
     */
    protected abstract void setAdapter();


    /**
     * 设置页面标题
     *
     * @return
     */
    public abstract String setTitle();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置 LayoutManager 的 弹框
     */
    @TargetApi(23)
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
                rvCommon.setLayoutManager(new LinearLayoutManager(BaseRvActivity.this));

            }
        });
        tvGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCommon.setLayoutManager(new GridLayoutManager(BaseRvActivity.this,2));
            }
        });
        tvStra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCommon.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            }
        });



    }
}
