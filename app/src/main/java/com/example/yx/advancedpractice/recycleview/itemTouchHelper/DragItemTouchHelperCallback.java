package com.example.yx.advancedpractice.recycleview.itemTouchHelper;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * @author yangxia
 * @since 10/1/19 上午11:46
 */
public class DragItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = "DragCallback";
    private TouchHelperCallBack touchHelperCallBack;
    private Context context;

    /**
     * 拖拽回调
     */
    public interface TouchHelperCallBack {
        /**
         * 拖拽回调函数
         *
         * @param fromPosition 起始位置
         * @param toPosition   目标位置
         */
        void onExchange(int fromPosition, int toPosition);

        /**
         * 拖拽完成
         */
        void onDragFinished();
    }

    public DragItemTouchHelperCallback(TouchHelperCallBack touchHelperCallBack, Context context) {
        this.touchHelperCallBack = touchHelperCallBack;
        this.context = context;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int direction = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, direction);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        Log.d(TAG, "onMove: ");
        if (touchHelperCallBack != null) {
            touchHelperCallBack.onExchange(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        }
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
//        return super.isLongPressDragEnabled();
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 侧滑
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder.itemView.setScaleY(1.2f);
            viewHolder.itemView.setScaleX(1.2f);
        }
        super.onSelectedChanged(viewHolder, actionState);

        Log.d(TAG, "onSelectedChanged: " + actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.d(TAG, "clearView: ");
        viewHolder.itemView.setScaleY(1.0f);
        viewHolder.itemView.setScaleX(1.0f);
        if (touchHelperCallBack != null) {
            touchHelperCallBack.onDragFinished();
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDraw: " + dX + "   " + dY);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if ((int) (viewHolder.itemView.getTop() + dY) < 0) {
            dY = 0;
        }
        Log.d(TAG, "onChildDrawOver: " + (int) (viewHolder.itemView.getTop() + dY) + "  " + viewHolder.itemView.getPaddingTop() + "  " + dY);

        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        Log.d(TAG, "onChildDrawOver:dX =  "+dX + "  dY="+ dY + " isCurrentlyActive = " +isCurrentlyActive + "  actionState = "+ actionState);

    }


    @Override
    public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        Log.d(TAG, "onMoved: ");
    }
}
