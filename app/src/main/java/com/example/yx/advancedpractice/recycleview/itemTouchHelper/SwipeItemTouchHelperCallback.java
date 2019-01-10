package com.example.yx.advancedpractice.recycleview.itemTouchHelper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * @author yangxia
 * @since 10/1/19 上午11:46
 */
public class SwipeItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = "SwipeItemTouchHelperCal";
    private TouchHelperCallBack touchHelperCallBack;
    public interface TouchHelperCallBack {
        void onSwiped(int position);
    }

    public SwipeItemTouchHelperCallback(TouchHelperCallBack touchHelperCallBack) {
        this.touchHelperCallBack = touchHelperCallBack;
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
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(TAG, "onSwiped: ");
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
