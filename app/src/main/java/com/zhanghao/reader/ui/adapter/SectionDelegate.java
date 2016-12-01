package com.zhanghao.reader.ui.adapter;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.ItemSection;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.ui.adapter.base.ItemViewDelegate;

/**
 * Created by zhanghao on 2016/11/25.
 */

public class SectionDelegate implements ItemViewDelegate<DisplayItem>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.zhihu_section;
    }

    @Override
    public boolean isForViewType(DisplayItem displayItem, int position) {
        return displayItem instanceof ItemSection;
    }

    @Override
    public void convert(ComViewHolder viewHolder, DisplayItem displayItem, int position) {
        ItemSection item= (ItemSection) displayItem;
        viewHolder.setText(R.id.sec_tv,item.getSection());
    }

}
