package com.zhanghao.reader.ui.adapter;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDailyAllItem;
import com.zhanghao.reader.bean.GankDisplayItem;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.ui.adapter.base.ItemViewDelegate;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyAllDelegate implements ItemViewDelegate<GankDisplayItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.gankdaily_all_item;
    }

    @Override
    public boolean isForViewType(GankDisplayItem gankDisplayItem, int position) {
        return gankDisplayItem instanceof GankDailyAllItem.ResultsBean.AndroidBean ||
                gankDisplayItem instanceof GankDailyAllItem.ResultsBean.前端Bean ||
                gankDisplayItem instanceof GankDailyAllItem.ResultsBean.拓展资源Bean ||
                gankDisplayItem instanceof GankDailyAllItem.ResultsBean.IOSBean;
    }

    @Override
    public void convert(ComViewHolder viewHolder, GankDisplayItem gankDisplayItem, int position) {
        viewHolder.setText(R.id.gankdaily_all_tv, " * " + gankDisplayItem.getDesc());
    }
}
