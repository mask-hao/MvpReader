package com.zhanghao.reader.ui.adapter;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.ui.adapter.base.ItemViewDelegate;
import com.zhanghao.reader.utils.TimeUtils;

/**
 * Created by zhanghao on 2016/11/27.
 */

public class GankDailyDelegate implements ItemViewDelegate<DisplayItem>{

    private static final String TAG = "GankDailyDelegate";


    @Override
    public int getItemViewLayoutId() {
        return R.layout.gankdaily_item;
    }

    @Override
    public boolean isForViewType(DisplayItem displayItem, int position) {
        return displayItem instanceof GankDailyItem;
    }

    @Override
    public void convert(ComViewHolder viewHolder, DisplayItem displayItem, int position) {
        GankDailyItem gankDailyItem= (GankDailyItem) displayItem;
        String url= gankDailyItem.getUrl();
        String date= TimeUtils.getCurrentDate("yyyy-MM-dd","MM-dd",gankDailyItem.getDate());
        String title=gankDailyItem.getTitle();
        viewHolder.setGankImageResource(R.id.photo_iv,url);
        viewHolder.setTextAutoAdapter(R.id.photo_tv,date+"  "+title);
    }
}
