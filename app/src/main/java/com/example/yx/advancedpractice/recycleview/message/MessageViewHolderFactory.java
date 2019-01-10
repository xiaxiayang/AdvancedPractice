package com.example.yx.advancedpractice.recycleview.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yx.advancedpractice.R;
import com.example.yx.advancedpractice.bean.MessageBean;


/**
 * @author yangxia
 * @since 3/1/19 下午3:53
 */
public class MessageViewHolderFactory {

    private static  final int ITEM_TYPE_UNDEFINE= -1;
    private static  final int ITEM_TYPE_TEXT_LEFT = 0;
    private static  final int ITEM_TYPE_TEXT_RIGHT = 1;

    private static  final int ITEM_TYPE_IMAGE = 1;
    private  static  RecyclerView.ViewHolder viewHolder = null;
    public static RecyclerView.ViewHolder register(int viewType, Context context, ViewGroup root){
        LayoutInflater layoutInflater =     LayoutInflater.from(context);

        switch (viewType){
            case ITEM_TYPE_TEXT_LEFT:
                viewHolder = new TextLeftViewHolder(layoutInflater.inflate(R.layout.item_muti_left,root,false));
                break;
            case ITEM_TYPE_TEXT_RIGHT:
                viewHolder = new TextRightViewHolder(layoutInflater.inflate(R.layout.item_muti_right,root,false));
                break;
                default:
                    break;
        }

        return  viewHolder;

    }

    public  static  void bindView(int viewType, MessageBean messageBean){
        switch (viewType){
            case ITEM_TYPE_TEXT_LEFT:
                if (viewHolder instanceof TextLeftViewHolder){
                    TextLeftViewHolder textLeftViewHolder = (TextLeftViewHolder) viewHolder;
                    textLeftViewHolder.bindView(messageBean);
                }

                break;
            case ITEM_TYPE_TEXT_RIGHT:
                if (viewHolder instanceof TextRightViewHolder){
                    TextRightViewHolder textRightViewHolder = (TextRightViewHolder) viewHolder;
                    textRightViewHolder.bindView(messageBean);
                }

                break;
            default:
                break;
        }
    }




   public static int  getViewType(MessageBean messageBean){
     if (messageBean.getMessageEnum() ==null){
         return ITEM_TYPE_UNDEFINE;
     }
     int type = ITEM_TYPE_UNDEFINE;
    switch (messageBean.getMessageEnum()){
        case TEXT:
            if (messageBean.getFromName().equals("A")){
                type = ITEM_TYPE_TEXT_LEFT;
            }else {
                type = ITEM_TYPE_TEXT_RIGHT;
            }
            break;
        case IMAGE:
            type = ITEM_TYPE_IMAGE;
            break;
            default:
                break;
    }

    return  type;

   }


}
