package com.zhanghao.reader.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/21.
 */

public abstract class ComAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mLayoutInflater;

    public ComAdapter(Context mContext, final int mLayoutId, List<T> mDatas) {
        super(mContext,mDatas);
        this.mContext = mContext;
        mLayoutInflater=LayoutInflater.from(mContext);
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return mLayoutId;
            }

            @Override
            public boolean isForViewType(T t, int position) {
                return true;
            }

            @Override
            public void convert(ComViewHolder viewHolder, T t, int position) {
                ComAdapter.this.convert(viewHolder,t,position);
            }
        });

    }

    @Override
    public ComViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ComViewHolder comViewHolder=ComViewHolder.createViewHolder(mContext,parent,mLayoutId);
        setListener(parent,comViewHolder,viewType);
        return comViewHolder;
    }

    @Override
    public void onBindViewHolder(ComViewHolder holder, int position) {
        //holder.
        convert(holder,mDatas.get(position));
    }


    protected void setListener(final ViewGroup parent,final ComViewHolder viewHolder,int ViewType){
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    int pos=viewHolder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(pos);
                }
            }
        });
    }

    public abstract void convert(ComViewHolder comViewHolder,T t,int position);


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

}
