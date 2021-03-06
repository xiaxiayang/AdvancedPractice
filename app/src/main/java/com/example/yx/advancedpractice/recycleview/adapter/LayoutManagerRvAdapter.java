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
public class LayoutManagerRvAdapter extends RecyclerView.Adapter<LayoutManagerRvAdapter.ViewHolder> {
    private final Context context;
    private List<CommonDataBean> items;

    /**
     *  事件监听
     */
    public interface onItemClickListener{
        void  onItemLongClick(View view, int position);
        void onItemClick(View view, int position);
    }

    private onItemClickListener onItemClickListener;

    public void  setOnItemClickListener (onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public LayoutManagerRvAdapter(List<CommonDataBean> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_manager, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CommonDataBean item = items.get(position);
        holder.tvItem.setText(item.getData());
        holder.llLayout.setPadding(5,5,5,5);
        holder.llLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_white_rect));
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
                if (onItemClickListener !=null){
                    onItemClickListener.onItemLongClick(view,position);
                }
                return true;
            }
        });
    }

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