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
public class TextLeftViewHolder extends MessageViewHolder {
    private View view;
    private TextView tvName;
    private TextView tvContent;
    public TextLeftViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        initView();
    }

    @Override
    public void bindView(MessageBean item) {


        tvName.setText(item.getFromName());
        tvContent.setText(item.getContent());
    }

    @Override
    void initView() {
        tvName = view.findViewById(R.id.tv_name);
        tvContent = view.findViewById(R.id.tv_content);
    }


}
