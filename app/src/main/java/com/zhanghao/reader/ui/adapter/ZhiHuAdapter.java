package com.zhanghao.reader.ui.adapter;

import android.content.Context;

import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.ui.adapter.base.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/21.
 */

public class ZhiHuAdapter extends MultiItemTypeAdapter<DisplayItem>{

    public ZhiHuAdapter(Context mContext, List<DisplayItem> mData) {
        super(mContext, mData);
        addItemViewDelegate(new SectionDelegate());
        addItemViewDelegate(new ZhiHuItemDelegate());
    }
}
