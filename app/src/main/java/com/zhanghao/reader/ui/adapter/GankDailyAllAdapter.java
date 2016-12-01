package com.zhanghao.reader.ui.adapter;

import android.content.Context;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDisplayItem;
import com.zhanghao.reader.bean.ItemSection;
import com.zhanghao.reader.ui.adapter.base.ComAdapter;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.ui.adapter.base.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyAllAdapter extends MultiItemTypeAdapter<GankDisplayItem>{
    public GankDailyAllAdapter(Context mContext, List<GankDisplayItem> mData) {
        super(mContext, mData);
        addItemViewDelegate(new GankSectionDelegate());
        addItemViewDelegate(new GankDailyAllDelegate());
    }
}
