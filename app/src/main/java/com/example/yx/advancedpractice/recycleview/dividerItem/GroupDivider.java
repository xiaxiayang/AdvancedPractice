package com.example.yx.advancedpractice.recycleview.dividerItem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.bean.CityBean;

import java.util.List;

/**
 * @author yangxia
 * @since 28/12/18 上午11:32
 * 分组头部粘性 样式分割线
 */
public class GroupDivider extends RecyclerView.ItemDecoration {

    private static final String TAG = "GroupDivider";
    /**
     * 设置 item  top方向的偏移量
     */
    private int topOffset = 40;
    /**
     *  设置字体居中
     */
    private Paint.FontMetrics fontMetrics;

    private Context context;

    private Paint paint;
    private List<CityBean> cityBeanList;

    public GroupDivider(Context context, List<CityBean> cityBeanList) {
        this.context = context;
        this.cityBeanList = cityBeanList;
        paint=  new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.black));
        paint.setTextSize(17);
        paint.setTextAlign(Paint.Align.CENTER);
        fontMetrics = paint.getFontMetrics();

    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//        Log.d(TAG,"----onDraw---");
//        canvas.save();
//        final int childCount = parent.getChildCount();
//        //获取文字基准等信息
//        float topTxt = fontMetrics.top;
//        float bottomTxt = fontMetrics.bottom;
//        //基线中间点的y轴计算公式
//        int baseLineY = (int) (topTxt/2 + bottomTxt/2);
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            final  int position = parent.getChildAdapterPosition(child);
//            CityBean cityBean = cityBeanList.get(position);
//            final  String text =cityBean.getPinyin().substring(0,1).toUpperCase();
//            if (cityBean.isGroupFirst()){
//                canvas.drawText(text,30,child.getTop()-topOffset/2-baseLineY,paint);
//            }
//        }
//        canvas.restore();
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //https://blog.csdn.net/briblue/article/details/70211942
        canvas.save();
        //获取文字基准等信息
        float topTxt = fontMetrics.top;
        float bottomTxt = fontMetrics.bottom;
        int baseLineY = (int) (topTxt/2 + bottomTxt/2);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final  int position = parent.getChildAdapterPosition(child);
            CityBean cityBean = cityBeanList.get(position);
            final  String text = cityBean.getPinyin().substring(0,1).toUpperCase();
            //可见的第一条数据不管是不是分组的第一个，都要绘制悬停header
            if (i == 0){

                paint.setColor(context.getResources().getColor(R.color.gray));
                int top = parent.getPaddingTop();
                //是分组内最后一个元素
                if (cityBean.isGroupLast()){
                    int sectionTop = child.getBottom() - topOffset;
                    if (sectionTop <  top){
                        top = sectionTop;
                        paint.setColor(context.getResources().getColor(R.color.gray_light));

                    }
                }

                canvas.drawRect(0,top,parent.getRight(),topOffset,paint);
                paint.setColor(context.getResources().getColor(R.color.black));
                canvas.drawText(text,30,top+topOffset/2-baseLineY,paint);
            }else {
                //可见的非第一条数据，如果是分组的第一条数据，绘制header
                if (cityBean.isGroupFirst()){
                    paint.setColor(context.getResources().getColor(R.color.gray));
                    canvas.drawRect(0,child.getTop()-topOffset,parent.getRight(),child.getTop(),paint);
                    paint.setColor(context.getResources().getColor(R.color.black));
                    canvas.drawText(text,30,child.getTop()-topOffset/2-baseLineY,paint);
                }
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final  int position = parent.getChildAdapterPosition(view);
        final CityBean cityBean = cityBeanList.get(position);
        if ( cityBean .isGroupFirst()){
            outRect.top = topOffset;
        }else {
            outRect.top = 0;
        }
        outRect.bottom = 1;

    }
}
