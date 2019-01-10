package com.example.yx.advancedpractice.recycleview.itemTouchHelper;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * @author yangxia
 * @since 10/1/19 上午11:32
 */
public class SwipeItemTouchHelper extends ItemTouchHelper {

    private static final String TAG = "SwipeItemTouchHelper";
    
    public SwipeItemTouchHelper(@NonNull Callback callback) {
        super(callback);
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        Log.d(TAG, "attachToRecyclerView: ");

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.d(TAG, "onDrawOver: state"+ state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.d(TAG, "onDraw: ");
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        super.onChildViewAttachedToWindow(view);
//        Log.d(TAG, "onChildViewAttachedToWindow: ");
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        super.onChildViewDetachedFromWindow(view);
//        Log.d(TAG, "onChildViewDetachedFromWindow: ");
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        Log.d(TAG, "getItemOffsets: ");
    }

    @Override
    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.startDrag(viewHolder);
        Log.d(TAG, "startDrag: ");
    }

    @Override
    public void startSwipe(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.startSwipe(viewHolder);
        Log.d(TAG, "startSwipe: ");
    }
}
