package com.example.yx.advancedpractice.recycleview.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yx.advancedpractice.base.BaseRvActivity;
import com.example.yx.advancedpractice.bean.MessageBean;
import com.example.yx.advancedpractice.bean.MessageEnum;
import com.example.yx.advancedpractice.recycleview.adapter.MutilTypeFactoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxia
 * @since 29/12/18 下午4:19
 *  多种 item 类型 练习
 */
public class MutilTypeActivity extends BaseRvActivity {
    private List<MessageBean> dataBeanList = new ArrayList<>();
    private MutilTypeFactoryAdapter adapter;
    @Override
    protected void setAdapter() {
        adapter = new MutilTypeFactoryAdapter(dataBeanList,this);
        rvCommon.setAdapter(adapter);
    }

    @Override
    protected RecyclerView.ItemDecoration setDivider() {
        return null;
    }

    @Override
    public String setTitle() {
        return "多 item 类型" ;
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        adapter.setOnItemClickListener(new MutilTypeFactoryAdapter.onItemClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MutilTypeActivity.this,BasicDividerActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MutilTypeActivity.this,TimeDividerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MutilTypeActivity.this,GroupDividerActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 50 ; i++) {
            MessageBean bean = new MessageBean();
            if(i %4 ==0){
                bean.setFromName("A");
                bean.setContent("hello, I am A! item " +i );
            }else {
                bean.setFromName("wenny");
                bean.setContent("hi,nice to meet you ！ item " +i);
            }
            bean.setMessageEnum(MessageEnum.TEXT);
            dataBeanList.add(bean);
        }
        adapter.notifyDataSetChanged();

    }
}
