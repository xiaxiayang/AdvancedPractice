package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.CommonDataBean;
import com.example.yx.advancedpractice.recycleview.adapter.DragRvAdapter;
import com.example.yx.advancedpractice.recycleview.itemTouchHelper.DragItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 28/12/18 上午11:48
 *  拖拽排序 activity
 */
public class DragRvActivity extends BaseRvActivity {
    private List<CommonDataBean> dataBeanList = new ArrayList<>();
    private DragRvAdapter adapter;
    private ItemTouchHelper helper;
    private DragItemTouchHelperCallback callback;
    private static final String TAG = "DragRvActivity";

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(this,4);
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return null;
    }

    @Override
    protected void setAdapter() {
        adapter = new DragRvAdapter(dataBeanList,this);
        callback = new DragItemTouchHelperCallback(adapter,this);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvCommon);
        rvCommon.setAdapter(adapter);

    }

    @Override
    protected void initView() {
        super.initView();
        rvCommon.setBackgroundColor(getResources().getColor(R.color.white));


    }


    @Override
    protected void bindEvent() {
        super.bindEvent();
      adapter.setOnItemClickListener(new DragRvAdapter.onItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
              Toast.makeText(DragRvActivity.this, "onItemClick"+dataBeanList.get(position).getData(), Toast.LENGTH_SHORT).show();
          }
      });

    }

    @Override
    public String setTitle() {
        return "拖拽排序";
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            dataBeanList.add(new CommonDataBean("数据" + i));
        }
        adapter.notifyDataSetChanged();

    }
}
