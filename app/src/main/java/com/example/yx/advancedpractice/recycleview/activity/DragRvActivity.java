package com.example.yx.advancedpractice.recycleview.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.DataType;
import com.example.yx.advancedpractice.bean.DragDataBean;
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
    private List<DragDataBean> dataBeanList = new ArrayList<>();
    private DragRvAdapter adapter;
    private ItemTouchHelper helper;
    private DragItemTouchHelperCallback callback;
    private static final String TAG = "DragRvActivity";
    GridLayoutManager layoutManager;

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        layoutManager =  new GridLayoutManager(this,4);
        return layoutManager;
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
        rvCommon.setItemAnimator(new DefaultItemAnimator());

        rvCommon.setAdapter(adapter);

    }

    @Override
    protected void initView() {
        super.initView();
        rvCommon.setBackgroundColor(getResources().getColor(R.color.white));
        rvCommon.setPadding(0,0,0,200);
        rvCommon.setClipToPadding(false);



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
        adapter.setOnItemLongClickListener(new DragRvAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder,View view, int position) {
                int viewType = adapter.getItemViewType(position);
                if (viewType != DragRvAdapter.ITEM_TYPE_DATA_SELECTED){
                    return;
                }
//                if ( !adapter.isEditMode()){
//                    adapter.setEditMode(true);
//                    //更改头布局编辑按钮
//                    View header = rvCommon.getChildAt(0);
//                    DragRvAdapter.TvSelectedHolder headerHolder;
//                    if (rvCommon.getChildViewHolder(header) instanceof  DragRvAdapter.TvSelectedHolder){
//                        headerHolder = (DragRvAdapter.TvSelectedHolder) rvCommon.getChildViewHolder(header);
//                        headerHolder.itemView.findViewById(R.id.tv_edit).setVisibility(View.VISIBLE);
//                    }
//                    //更改我的频道的删除显示
//                    int childCount = rvCommon.getChildCount();
//                    for (int i = 0; i < childCount; i++) {
//                        View childView = rvCommon.getChildAt(i);
//                        if (adapter.getItemViewType(i) == DragRvAdapter.ITEM_TYPE_DATA_SELECTED){
//                            childView.findViewById(R.id.img_delete).setVisibility(View.VISIBLE);
//                        }
//                    }
//                }
                helper.startDrag(viewHolder);

            }
        });

    }

    @Override
    public String setTitle() {
        return "拖拽排序";
    }

    @Override
    protected void initData() {
        DragDataBean dataBean = new DragDataBean("我的频道",DataType.TV_SELECTED);
        dataBeanList.add(dataBean);
        dataBeanList.add(new DragDataBean("关注",DataType.DATA_DEFAULT));
        dataBeanList.add(new DragDataBean("推荐",DataType.DATA_DEFAULT));
        for (int i = 0; i < 10; i++) {
            dataBeanList.add(new DragDataBean("热点"+i,DataType.DATA_SELECTED));
        }
        dataBeanList.add(new DragDataBean("推荐频道",DataType.TV_ADD));
        for (int i = 0; i <100 ; i++) {
            dataBeanList.add(new DragDataBean("推荐"+i,DataType.DATA_ADD));
        }
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int viewType =  adapter.getItemViewType(i);
                return viewType == DragRvAdapter.ITEM_TYPE_TV_ADD || viewType == DragRvAdapter.ITEM_TYPE_TV_SELECTED
                        ? layoutManager.getSpanCount(): 1;
            }
        });

        adapter.notifyDataSetChanged();

    }
}
