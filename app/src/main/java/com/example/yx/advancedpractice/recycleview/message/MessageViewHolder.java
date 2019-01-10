package com.example.yx.advancedpractice.recycleview.message;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yx.advancedpractice.bean.MessageBean;


/**
 * @author yangxia
 * @since 3/1/19 下午3:53
 */
public abstract class MessageViewHolder extends RecyclerView.ViewHolder {


    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract  void bindView(MessageBean messageBean);

    abstract void initView();
}
