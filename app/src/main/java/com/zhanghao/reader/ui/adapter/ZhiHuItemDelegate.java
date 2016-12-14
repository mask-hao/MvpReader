package com.zhanghao.reader.ui.adapter;

import android.support.v7.widget.CardView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.ZhiHuStories;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.ui.adapter.base.ItemViewDelegate;

/**
 * Created by zhanghao on 2016/11/25.
 */

public class ZhiHuItemDelegate implements ItemViewDelegate<DisplayItem>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.zhihu_item;
    }

    @Override
    public boolean isForViewType(DisplayItem displayItem, int position) {
        return displayItem instanceof ZhiHuStories;
    }

    @Override
    public void convert(ComViewHolder viewHolder, DisplayItem displayItem, int position) {
        ZhiHuStories zhiHuStories= (ZhiHuStories) displayItem;
        viewHolder.setImageResource(R.id.zhihu_img,zhiHuStories.getImages().get(0));
        viewHolder.setText(R.id.zhihu_title,zhiHuStories.getTitle());
        //// TODO: 2016/12/11 add view to viewList
    }
}
