package com.example.yx.advancedpractice.recycleview.itemTouchHelper;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.yx.advancedpractice.utils.ScreenUtil;

/**
 * @author yangxia
 * @since 10/1/19 上午11:46
 */
public class SwipeItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = "SwipeItemTouchHelperCal";
    private TouchHelperCallBack touchHelperCallBack;
    /**
     *  隐藏的 View 的宽度
     */
    private float hideViewWidth;
    /**
     * 屏幕宽度
     */
    private float screenWidth ;

    /**
     * 记录上一个处于侧滑状态的view
     */
    private View oldItemView;
    private boolean   isSwipeDirection  =true;

    private float oldX;


    private boolean isItemViewOpen = false;

    public boolean isItemViewOpen() {
        return isItemViewOpen;
    }

    public void setItemViewOpen(boolean itemViewOpen) {
        isItemViewOpen = itemViewOpen;
    }

    public interface TouchHelperCallBack {
        void onSwiped(int position);
    }

    public SwipeItemTouchHelperCallback(TouchHelperCallBack touchHelperCallBack, Context context) {
        this.touchHelperCallBack = touchHelperCallBack;
        hideViewWidth = ScreenUtil.dip2px(context,200);
        screenWidth = ScreenUtil.width;
    }

    /**
     * 关闭上个侧滑菜单
     */
    public  void closeSwipeView(){
        if (oldItemView !=null){
            oldItemView.scrollTo(0,0);
        }
        oldX  =0;
        setItemViewOpen(false);

    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP;
        int swipeFlag = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlag,swipeFlag);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        Log.d(TAG, "onMove: ");
        return false;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.d(TAG, "onSelectedChanged: "+actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.d(TAG, "clearView: ");
        setItemViewOpen(false);
        viewHolder.itemView.scrollTo(0,0);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDraw:dX =  "+dX + "oldX = " + oldX+ "  dY="+ dY + " isCurrentlyActive = " +isCurrentlyActive + "  actionState = "+ actionState);
        //判断是否在向左滑
        isSwipeDirection = oldX < dX  ;
        if ( Math.abs(dX)!=0 && Math.abs(dX) < hideViewWidth && isSwipeDirection && isCurrentlyActive){
            viewHolder.itemView.scrollTo((int) Math.abs(dX), 0);
        }

        oldX = dX;

//        else {
//            viewHolder.itemView.scrollTo((int) hideViewWidth, 0);
//        }


    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        Log.d(TAG, "onChildDrawOver:dX =  "+dX + "  dY="+ dY + " isCurrentlyActive = " +isCurrentlyActive + "  actionState = "+ actionState);

    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return  hideViewWidth*0.5f/screenWidth;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(TAG, "onSwiped: " +direction + "  "+ viewHolder.itemView.getScaleX());
        if (!isItemViewOpen){
            viewHolder.itemView.scrollTo((int) hideViewWidth, 0);
            oldItemView = viewHolder.itemView;
            setItemViewOpen(true);
        }

        if (touchHelperCallBack !=null){
            touchHelperCallBack.onSwiped(viewHolder.getAdapterPosition());
        }

    }

    @Override
    public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        Log.d(TAG, "onMoved: ");
    }
}
