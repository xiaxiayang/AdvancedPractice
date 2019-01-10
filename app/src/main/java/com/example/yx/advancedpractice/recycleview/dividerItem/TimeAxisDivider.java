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
 *  时光轴样式分割线
 */
public class TimeAxisDivider extends RecyclerView.ItemDecoration {

    private static final String TAG = "TimeAxisDivider";
    /**
     * 设置 item  lef他方向的偏移量
     */
    private int leftOffset = 80;
    /**
     *  时光轴分割点距离item 顶部的距离
     */
    private int toTop = 20;
    /**
     * 画的小圆点的半径
     */
    private int circleRadius = 5;
    /**
     * 分割线宽度
     */
    private int dividerLine =1 ;
    private Context context;

    private Paint paint;

    public TimeAxisDivider(Context context) {
        this.context = context;
        paint=  new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.black));
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        Log.d(TAG,"----onDraw---");
        canvas.save();
        //先画分割线整体背景色
        canvas.drawColor(context.getResources().getColor(R.color.gray));
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            //1.先画 1 px 的竖线
             int startX = leftOffset-20;
             int startY = child.getTop();
             int stopX = startX;
             int stopY = child.getBottom()+dividerLine;
            paint.setColor(context.getResources().getColor(R.color.black));
            canvas.drawLine(startX,startY,stopX,stopY,paint);
            //2.画圆圈
            //设置不同颜色
            if (parent.getChildAdapterPosition(child) %2 ==0){
                paint.setColor(context.getResources().getColor(R.color.colorPrimary));
            }else {
                paint.setColor(context.getResources().getColor(R.color.colorAccent));
            }
            canvas.drawCircle(stopX,startY+toTop+circleRadius,circleRadius,paint);
            //3.画文字
            paint.setColor(context.getResources().getColor(R.color.black));
            canvas.drawText("12:01",5,startY+toTop+2*circleRadius,paint);
           //3.画item之间的分割线
            paint.setColor(context.getResources().getColor(R.color.gray));
            canvas.drawLine(leftOffset,child.getBottom()+1,child.getRight(),child.getBottom()+1,paint);
        }


        canvas.restore();
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = leftOffset;
        outRect.bottom = 1;
    }
}
