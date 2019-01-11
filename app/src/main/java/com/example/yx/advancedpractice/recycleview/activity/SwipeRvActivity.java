package com.example.yx.advancedpractice.recycleview.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.SwipeRvAdapter;
import com.example.yx.advancedpractice.recycleview.itemTouchHelper.SwipeItemTouchHelper;
import com.example.yx.advancedpractice.recycleview.itemTouchHelper.SwipeItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 28/12/18 上午11:48
 * 侧滑删除 activity
 */
public class SwipeRvActivity extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private SwipeRvAdapter adapter;
    private SwipeItemTouchHelper helper;
    private SwipeItemTouchHelperCallback callback;
    private static final String TAG = "SwipeRvActivity";

    @Override
    protected void setAdapter() {
        adapter = new SwipeRvAdapter(dataBeanList,this);
        callback = new SwipeItemTouchHelperCallback(adapter,this);
        helper = new SwipeItemTouchHelper(callback);
        helper.attachToRecyclerView(rvCommon);
        rvCommon.setAdapter(adapter);
        rvCommon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        callback.closeSwipeView();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();

    }


    @Override
    protected void bindEvent() {
        super.bindEvent();
        adapter.setOnItemClickListener(new SwipeRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SwipeRvActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public String setTitle() {
        return "侧滑删除";
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            dataBeanList.add(new CommonDataBean("数据" + i));
        }
        adapter.notifyDataSetChanged();

    }
}
