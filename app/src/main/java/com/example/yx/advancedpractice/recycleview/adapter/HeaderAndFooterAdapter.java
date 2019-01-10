package com.example.yx.advancedpractice.recycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
public class HeaderAndFooterAdapter extends RecyclerView.Adapter<HeaderAndFooterAdapter.ViewHolder> {
    private final Context context;
    private List<CommonDataBean> items;
    /**
     *  header
     */
    public  final int ITEM_TYPE_HEADER =   0;
    /**
     *  普通  item
     */
    public  final int ITEM_TYPE_BODY =   1;
    /**
     *  footer
     */
    public  final int ITEM_TYPE_FOOTER =   2;

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
    }

    private View headerView = null;
    private View footerView = null;

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
    public HeaderAndFooterAdapter(List<CommonDataBean> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER){
            return  new ViewHolder(getHeaderView());
        }
        if (viewType == ITEM_TYPE_FOOTER){
            return  new ViewHolder(getFooterView());
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        // header 和 footer 交给外部设置者处理
        if (viewType == ITEM_TYPE_HEADER || viewType == ITEM_TYPE_FOOTER){
            return;
        }
        CommonDataBean item =null ;
        if (getHeaderView() !=null ){
            item  = items.get(position -1);
        }else {
            item = items.get(position);
        }

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

    @Override
    public int getItemViewType(int position) {
       if (getHeaderView() !=null && position ==0){
           return ITEM_TYPE_HEADER;
       }
       if (getFooterView() !=null && position == getItemCount()-1){
           return  ITEM_TYPE_FOOTER;
       }
       return  ITEM_TYPE_BODY;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (getHeaderView() !=null){
            count ++;
        }
        if (getFooterView() !=null){
            count ++ ;
        }
        if (items != null) {
            count += items.size();
        }
        return count;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp !=null && lp instanceof  StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams)lp;
            if (holder.getItemViewType() == ITEM_TYPE_HEADER ||  holder.getItemViewType() == ITEM_TYPE_FOOTER){
                p.setFullSpan(true);
            }

        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        @BindView(R.id.ll_layout)
        LinearLayout llLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (getHeaderView() != null && getHeaderView() == itemView){
                //头布局交给外部处理
                return;
            }
            if (getFooterView() != null && getFooterView() == itemView){
                //footer布局交给外部处理
                return;
            }
            ButterKnife.bind(this,itemView);
        }
    }

}