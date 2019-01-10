package com.example.yx.advancedpractice.recycleview.message;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.bean.MessageBean;

/**
 * @author yangxia
 * @since 3/1/19 下午3:55
 */
public class TextRightViewHolder extends MessageViewHolder {
    private View view;
    private TextView tvContent;

    public TextRightViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        initView();

    }

    @Override
    public void bindView(MessageBean messageBean) {

        tvContent.setText(messageBean.getContent());
    }

    @Override
    void initView() {
        tvContent = view.findViewById(R.id.tv_content);
    }


}
