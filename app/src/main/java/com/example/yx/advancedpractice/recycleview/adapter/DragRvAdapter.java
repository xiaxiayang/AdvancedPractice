package com.example.yx.advancedpractice.recycleview.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.bean.DataType;
import com.example.yx.advancedpractice.bean.DragDataBean;
import com.example.yx.advancedpractice.recycleview.itemTouchHelper.DragItemTouchHelperCallback;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yangxia
 * @since 26/12/18 上午11:31
 * 拖拽排序 adapter
 */
public class DragRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DragItemTouchHelperCallback.TouchHelperCallBack {
    private final Context context;
    private List<DragDataBean> items;
    /**
     * 我的频道 标题type
     */
    public static final int ITEM_TYPE_TV_SELECTED = 0;
    /**
     *  我的频道，默认频道
     */
    public static final int ITEM_TYPE_DEFAULT = 1;
    /**
     *  我的频道，已选择的频道
     */
    public static final int ITEM_TYPE_DATA_SELECTED =2;
    /**
     *  频道推荐
     */
    public static final int ITEM_TYPE_TV_ADD = 3;
    /**
     * 频道推荐，数据
     */
    public static final int ITEM_TYPE_DATA_ADD = 4;
    /**
     * 是否处于编辑状态
     */
    private boolean isEditMode ;

    public boolean isEditMode() {
        return isEditMode;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
//        notifyDataSetChanged();
    }

    /**
     *  事件监听
     */
    private onItemClickListener onItemClickListener;
    private onItemLongClickListener onItemLongClickListener;

    /**
     * 排序回调监听
     * @param from  起始位置
     * @param to  目标位置
     */
    @Override
    public void onExchange(int from, int to) {
        Log.d("onExchange", "onExchange: " + getItemViewType(from)+"    "+getItemViewType(to));
        if (getItemViewType(from) != ITEM_TYPE_DATA_SELECTED ||  getItemViewType(to) != ITEM_TYPE_DATA_SELECTED){
            return;
        }
        Collections.swap(items, from, to);
        notifyItemMoved(from, to);

    }

    /**
     * 拖拽完成
     */
    @Override
    public void onDragFinished() {

    }


    public interface onItemClickListener{
        void  onItemClick(View view, int position);
    }
    public interface onItemLongClickListener{
        void  onItemLongClick(RecyclerView.ViewHolder viewHolder,View view, int position);
    }

    public void  setOnItemClickListener (onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void  setOnItemLongClickListener (onItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 构造函数
     * @param items
     * @param context
     */
    public DragRvAdapter(List<DragDataBean> items, Context context) {
        this.items = items;
        this.context = context;
    }

    /**
     * 实例化 ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_TV_ADD || viewType == ITEM_TYPE_TV_SELECTED){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_txt, parent, false);
            return new TvSelectedHolder(v);
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(v);
    }

    /**
     * 更新 View 内容
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DragDataBean item = items.get(position);
        int viewType = getItemViewType(position);
        switch (viewType){
            case ITEM_TYPE_TV_SELECTED:
            case ITEM_TYPE_TV_ADD:
                TvSelectedHolder tvSelectedHolder = (TvSelectedHolder) holder;
                tvSelectedHolder.tvDrag.setText(item.getData());
                if (viewType == ITEM_TYPE_TV_SELECTED && isEditMode){
                    tvSelectedHolder.tvEdit.setVisibility(View.VISIBLE);
                }else {
                    tvSelectedHolder.tvEdit.setVisibility(View.GONE);
                }
                break;
            case ITEM_TYPE_DATA_SELECTED:
            case ITEM_TYPE_DEFAULT:
                final ViewHolder viewHolder = (ViewHolder) holder;
                if (viewType == ITEM_TYPE_DEFAULT){
                    viewHolder.tvItem.setTextColor(context.getResources().getColor(R.color.gray_deep));
                }else {
                    viewHolder.tvItem.setTextColor(context.getResources().getColor(R.color.black));
                }
                viewHolder.tvItem.setText(item.getData());
                viewHolder.llLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener !=null){
                            onItemClickListener.onItemClick(view,position);
                        }
                    }
                });

                viewHolder.llLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (onItemLongClickListener !=null){
                            onItemLongClickListener.onItemLongClick(viewHolder,view,position);
                        }
                        return true;
                    }
                });
                break;
            case ITEM_TYPE_DATA_ADD:
                final ViewHolder addHolder = (ViewHolder) holder;
                addHolder.tvItem.setBackgroundResource(R.drawable.shape_white_rect);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                    addHolder.llLayout.setElevation(10);
                }
                addHolder.tvItem.setText("+"+item.getData());
                addHolder.llLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener !=null){
                            onItemClickListener.onItemClick(view,position);
                        }
                    }
                });
                break;
            default:
                break;
        }
        Log.d("adapter", "onBindViewHolder: " + position + "   "+ viewType);

    }

    @Override
    public int getItemViewType(int position) {
        DragDataBean dataBean = items.get(position);
        if (dataBean.getType() ==null){
            return -1;
        }
        if (dataBean.getType() == DataType.TV_SELECTED){
            return  ITEM_TYPE_TV_SELECTED;
        }
        if (dataBean.getType() == DataType.TV_ADD){
            return  ITEM_TYPE_TV_ADD;
        }
        if (dataBean.getType() == DataType.DATA_SELECTED){
            return  ITEM_TYPE_DATA_SELECTED;
        }
        if (dataBean.getType() == DataType.DATA_ADD){
            return  ITEM_TYPE_DATA_ADD;
        }
        if (dataBean.getType() == DataType.DATA_DEFAULT){
            return  ITEM_TYPE_DEFAULT;
        }
        return super.getItemViewType(position);
    }

    /**
     * 获取 item 的总数
     * @return
     */
    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        @BindView(R.id.rl_item)
        RelativeLayout llLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

   public static  class TvSelectedHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_drag)
        TextView tvDrag;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        public TvSelectedHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}