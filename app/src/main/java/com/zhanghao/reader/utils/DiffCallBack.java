package com.zhanghao.reader.utils;

import android.support.v7.util.DiffUtil;

import com.zhanghao.reader.bean.ZhiHuStories;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/22.
 */

public class DiffCallBack extends DiffUtil.Callback{


    private List<ZhiHuStories> oldList;
    private List<ZhiHuStories> newList;


    public DiffCallBack(List<ZhiHuStories> newList, List<ZhiHuStories> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()==newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ZhiHuStories zhiHuStoriesOld=oldList.get(oldItemPosition);
        ZhiHuStories zhiHuStoriesNew=newList.get(newItemPosition);
        return zhiHuStoriesOld.getTitle().equals(zhiHuStoriesNew.getTitle());
    }
}
