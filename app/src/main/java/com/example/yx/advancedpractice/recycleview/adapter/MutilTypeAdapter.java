package com.example.yx.advancedpractice.recycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.bean.MessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yangxia
 * @since 26/12/18 上午11:31
 */
public class MutilTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    private List<MessageBean> items;
    /**
     * ITEM 类型 --  别人
     */
    private final int ITEM_TYPE_FRIEND = 0;
    /**
     * ITEM 类型 -- 自己
     */
    private final int ITEM_TYPE_SELF = 1;

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

    public MutilTypeAdapter(List<MessageBean> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_FRIEND) {
            return new FriendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_muti_left, parent, false));
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_muti_right, parent, false);
        return new SelfViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MessageBean item = items.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ITEM_TYPE_FRIEND:
                FriendViewHolder friendHolder = (FriendViewHolder) holder;
                friendHolder.tvName.setText(item.getFromName());
                friendHolder.tvContent.setText(item.getContent());
                break;
            case ITEM_TYPE_SELF:
                SelfViewHolder selfHolder = (SelfViewHolder) holder;
                selfHolder.tvContent.setText(item.getContent());
                break;
            default:
                break;
        }

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
        MessageBean messageBean = items.get(position);
        if (messageBean.getFromName() != null && messageBean.getFromName().equals("A")) {
            return ITEM_TYPE_FRIEND;
        }
        return ITEM_TYPE_SELF;
    }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SelfViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        public SelfViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}