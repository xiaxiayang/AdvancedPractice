package com.example.yx.advancedpractice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;


/**
 * <p/>
 * <pre>
 * ***************************************************************
 *  Copyright (c) 2014-2015 –苏州犀牛网络科技有限公司
 *  Package: com.xiniunet.xntalk.support.widget
 *  Description:
 *  @since 1.0.0
 *  @author yangxia
 *  @date 2018/5/21
 *  @time 12:47
 * ***************************************************************
 * </pre>
 */
public class XNCommonTitleBar extends RelativeLayout {

    /**
     * 返回箭头
     */
    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 标题右边的图片，如消息中免打扰图标
     */
    private ImageView ivTitleRight;
    /**
     * 最右边的图片
     */
    private ImageButton ivRight;
    /**
     * 最右边的文字
     */
    private TextView tvRight;
    /*********自定义属性开始*********/
    private String titleTXT ;//标题文字
    private String tvRightTXT;//最右边文字
    private int ivBackRes; //返回键图片资源id
    private int ivRightRes ;// 最右边图片资源id

    private boolean showIvBack  = true;//是否显示返回按钮,默认显示
    private boolean showTvRight  = false;//是否显示最右边文字,默认不显示
    private boolean showIvRight  = false;//是否显示最右边按钮图片,默认不显示
    private boolean showIvTitleRight = false;//是否显示标题右边的图片,默认不显示
    /**********自定义属性结束********/

    public XNCommonTitleBar(Context context) {
        super(context,null);
    }

    public XNCommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomActionBar);
        titleTXT = a.getString(R.styleable.CustomActionBar_action_title);
        tvRightTXT = a.getString(R.styleable.CustomActionBar_action_txt_right);
        ivBackRes = a.getResourceId(R.styleable.CustomActionBar_action_iv_back,0);
        ivRightRes = a.getResourceId(R.styleable.CustomActionBar_action_iv_right,0);

        showIvBack = a.getBoolean(R.styleable.CustomActionBar_action_iv_back_show,true);
        showTvRight = a.getBoolean(R.styleable.CustomActionBar_action_show_txt_right,false);
        showIvRight = a.getBoolean(R.styleable.CustomActionBar_action_show_iv_right,false);
        showIvTitleRight = a.getBoolean(R.styleable.CustomActionBar_action_show_iv_title_right,false);
        a.recycle();
        init();
    }

    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.layout_xn_titlebar, this);

        ivBack=(ImageView) findViewById(R.id.iv_back);
        ivTitleRight = (ImageView) findViewById(R.id.iv_title_right);
        ivRight  = (ImageButton) findViewById(R.id.iv_right);
        tvRight = (TextView)findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        if (titleTXT != null) {
            tvTitle.setText(titleTXT);
        }else {
            tvTitle.setText("");
        }

        if (showTvRight){
            if (tvRightTXT != null) {
                tvRight.setVisibility(VISIBLE);
                tvRight.setText(tvRightTXT);
            }
        }

        if (showIvTitleRight){
           ivTitleRight.setVisibility(VISIBLE);
        }

       if (showIvBack){
           if (ivBackRes !=0){
               ivBack.setImageResource(ivBackRes);
           }
       }else {
           ivBack.setVisibility(GONE);
       }

       if (showIvRight){
            if (ivRightRes !=0){
                ivRight.setVisibility(VISIBLE);
                ivRight.setImageResource(ivRightRes);
            }
        }

    }


    /**
     * 对外暴露的set方法
     */

    /**
     * 设置返回键是否显示
     * @param visibility
     */
    public void setIvBackVisibility(boolean visibility){
        ivBack.setVisibility(visibility?VISIBLE:GONE);
    }

    /**
     * 设置返回键图片资源id
     * @param resId
     */
    public void setIvBackResId(int resId){
        ivBack.setVisibility(VISIBLE);
        ivBack.setImageResource(resId);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setTitle( String title, OnClickListener onClickListener){
        tvTitle.setText(title);
        ivBack.setOnClickListener(onClickListener);
    }

    /**
     * 设置是否显示标题右边图片
     */
    public void setIvTitleRightVisibilty(boolean visibilty){
        ivTitleRight.setVisibility(visibilty ? VISIBLE :GONE);
    }
    /**
     * 设置标题右边图片资源id
     */
    public void setIvTitleRightResId(int resId){
        ivTitleRight.setVisibility(VISIBLE);
        ivTitleRight.setImageResource(resId);
    }

    /**
     * 设置最右边图片是否显示
     */
    public void setIvRightVisibility(boolean visibility){
        ivRight.setVisibility(visibility?VISIBLE:GONE);
    }

    /**
     * 设置最右边图片资源id
     */
    public void  setIvRightResId(int res){
        ivRight.setVisibility(VISIBLE);
        ivRight.setImageResource(res);
    }


    /**
     * 设置最右边图片资源id
     */
    public void  setIvRightResId(int res,OnClickListener onClickListener){
        ivRight.setVisibility(VISIBLE);
        ivRight.setImageResource(res);
        ivRight.setOnClickListener(onClickListener);
    }

    /**
     * 设置最右边文字是否显示
     */
    public void setTvRightVisibility(boolean visibility){
        tvRight.setVisibility(visibility?VISIBLE:GONE);
    }
    /**
     * 设置最右边文字内容
     */

    public void  setTvRightTxt(String txt){
        tvRight.setVisibility(VISIBLE);
        tvRight.setText(txt);
    }

    public void  setTvRightTxt(String txt, OnClickListener onClickListener){
        tvRight.setVisibility(VISIBLE);
        tvRight.setText(txt);
        tvRight.setOnClickListener(onClickListener);
    }
    public void  setTvRightTxt(String txt, int color, OnClickListener onClickListener){
        tvRight.setVisibility(VISIBLE);
        tvRight.setText(txt);
        tvRight.setTextColor(color);
        tvRight.setOnClickListener(onClickListener);
    }

    /**
     * 设置最右边文字颜色
     */

    public void setTvRightTxtColor(int colorId){
        tvRight.setTextColor(colorId);
    }

    /**
     * 设置返回键点击事件监听
     */
    public  void setIvBackListener(OnClickListener onClickListener){
        ivBack.setOnClickListener(onClickListener);
    }

}
