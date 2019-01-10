package com.example.yx.advancedpractice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author yangxia
 * @since 26/12/18 下午1:25
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     *  intent 参数处理
     */
    protected void parseIntent(){

    }

    /**
     * 获取布局 id
     */
    protected abstract int   getLayoutId();

    /**
     *  view 绑定
     */
    protected abstract void initView();

    /**
     * 事件绑定
     */
    protected abstract void bindEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
