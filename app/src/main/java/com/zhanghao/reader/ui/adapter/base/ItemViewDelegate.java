package com.zhanghao.reader.ui.adapter.base;

/**
 * Created by zhanghao on 2016/11/25.
 */

public interface ItemViewDelegate<T>{
    int getItemViewLayoutId();
    boolean isForViewType(T t,int position);
    void convert(ComViewHolder viewHolder,T t,int position);
}
