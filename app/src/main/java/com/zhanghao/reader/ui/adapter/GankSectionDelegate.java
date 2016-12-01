package com.zhanghao.reader.ui.adapter;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDisplayItem;
import com.zhanghao.reader.bean.ItemSection;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.ui.adapter.base.ItemViewDelegate;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankSectionDelegate implements ItemViewDelegate<GankDisplayItem>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.gank_section;
    }

    @Override
    public boolean isForViewType(GankDisplayItem gankDisplayItem, int position) {
        return gankDisplayItem instanceof ItemSection;
    }

    @Override
    public void convert(ComViewHolder viewHolder, GankDisplayItem gankDisplayItem, int position) {
        ItemSection itemSection= (ItemSection) gankDisplayItem;
        String section=itemSection.getSection();
        viewHolder.setText(R.id.gank_section_title,section);
    }
}
