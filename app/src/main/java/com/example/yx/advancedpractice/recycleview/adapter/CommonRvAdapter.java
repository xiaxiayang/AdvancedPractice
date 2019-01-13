package com.example.yx.advancedpractice.recycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.bean.CommonDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yangxia
 * @since 26/12/18 上午11:31
 */
public class CommonRvAdapter extends RecyclerView.Adapter<CommonRvAdapter.ViewHolder> {
    private final Context context;
    private List<CommonDataBean> items;

    /**
     *  事件监听
     */
    private onItemClickListener onItemClickListener;
    private onItemLongClickListener onItemLongClickListener;

    public interface onItemClickListener{
        void  onItemClick(View view, int position);
    }
    public interface onItemLongClickListener{
        void  onItemLongClick(View view, int position);
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
    public CommonRvAdapter(List<CommonDataBean> items, Context context) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(v);
    }

    /**
     * 更新 View 内容
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CommonDataBean item = items.get(position);
        holder.tvItem.setText(item.getData());
        holder.llLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener !=null){
                    onItemClickListener.onItemClick(view,position);
                }
            }
        });

        holder.llLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener !=null){
                    onItemLongClickListener.onItemLongClick(view,position);
                }
                return true;
            }
        });
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        @BindView(R.id.ll_layout)
        LinearLayout llLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}