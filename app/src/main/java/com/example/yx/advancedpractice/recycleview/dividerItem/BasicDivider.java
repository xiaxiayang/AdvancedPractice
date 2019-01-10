package com.example.yx.advancedpractice.recycleview.dividerItem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.yx.advancedpractice.R;


/**
 * @author yangxia
 * @since 28/12/18 上午11:32
 */
public class BasicDivider extends RecyclerView.ItemDecoration {

    private static final String TAG = "DividerItem";
    /**
     * 获取 item 在四个方向上的偏移量
     */
    private int leftOffset;
    private int topOffset;
    private int rightOffset;
    private int bottomOffset;

    private Paint paint;
    private Context context;

    public BasicDivider(Context context) {
        this(context,0,0,0,0);
    }

    public BasicDivider(Context context, int left, int top, int right, int bottom) {
        this.leftOffset = left;
        this.topOffset = top;
        this.rightOffset = right;
        this.bottomOffset = bottom;
        this.context = context;
        paint=  new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.black));
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        Log.d(TAG,"----onDraw---");
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()){
            left = 0;
            right = parent.getRight();
        }else {
            left = 0;
            right = parent.getRight();
        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int bottom = child.getBottom() + Math.round(child.getTranslationY())+bottomOffset;
            final int top = child.getBottom();
            canvas.drawRect(left,top,right,bottom,paint);
        }
        canvas.restore();
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        Log.d(TAG,"----onDrawOver---");
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        Log.d(TAG,"----getItemOffsets---");
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(leftOffset,topOffset,rightOffset,bottomOffset);
//        outRect.bottom = 10;
    }
}
