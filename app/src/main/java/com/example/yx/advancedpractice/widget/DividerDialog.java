package com.example.yx.advancedpractice.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yangxia
 * @since 28/12/18 下午1:16
 */
public class DividerDialog extends Dialog  {

    @BindView(R.id.edt_left)
    EditText edtLeft;
    @BindView(R.id.edt_top)
    EditText edtTop;
    @BindView(R.id.edt_right)
    EditText edtRight;
    @BindView(R.id.edt_bottom)
    EditText edtBottom;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    private OnCancelClickListener cancelClickListener;
    private OnConfirmClickListener confirmClickListener;

    public interface OnCancelClickListener {
        /**
         * 取消点击接口
         */
        void onCancelClick();
    }

    public interface OnConfirmClickListener {

        void onConfirmClick(int left, int top, int right, int bottom);
    }

    public void  setOnConfirmClickListener( OnConfirmClickListener confirmClickListener){
        this.confirmClickListener = confirmClickListener;
    }

    public DividerDialog(@NonNull Context context) {
        super(context, R.style.AuthDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.divider_dialog);
        ButterKnife.bind(this);
        bindEvent();
    }

    private void bindEvent(){

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int  left = edtLeft.getText().toString().trim() .equals("")?0:Integer.parseInt(edtLeft.getText().toString().trim());
                int  top = edtTop.getText().toString().trim().equals("")?0:Integer.parseInt(edtTop.getText().toString().trim());
                int  right = edtRight.getText().toString().trim().equals("")?0:Integer.parseInt(edtRight.getText().toString().trim());
                int  bottom = edtBottom.getText().toString().trim() .equals("")?0:Integer.parseInt(edtBottom.getText().toString().trim());
                if (confirmClickListener !=null){
                    confirmClickListener.onConfirmClick(left,top,right,bottom);

                }

                dismiss();

            }
        });

    }
}
