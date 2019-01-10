package com.example.yx.advancedpractice.recycleview.message;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.yx.advancedpractice.bean.MessageBean;


/**
 * @author yangxia
 * @since 3/1/19 下午3:56
 */
public class ImageViewHolder extends MessageViewHolder {

    private View view;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }



    @Override
    void bindView(MessageBean messageBean) {

    }

    @Override
    void initView() {

    }
}
