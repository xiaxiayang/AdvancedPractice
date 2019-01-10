package com.example.yx.advancedpractice.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.yx.advancedpractice.bean.MessageBean;
import com.example.yx.advancedpractice.recycleview.message.MessageViewHolderFactory;

import java.util.List;


/**
 * @author yangxia
 * @since 26/12/18 上午11:31
 */
public class MutilTypeFactoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    private List<MessageBean> items;

    /**
     * 事件监听
     */
    public interface onItemClickListener {
        void onItemLongClick(View view, int position);

        void onItemClick(View view, int position);
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MutilTypeFactoryAdapter(List<MessageBean> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return  MessageViewHolderFactory.register(viewType,context,parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MessageViewHolderFactory.bindView(getItemViewType(position),items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
     return MessageViewHolderFactory.getViewType(items.get(position));
    }

}