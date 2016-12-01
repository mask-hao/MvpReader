package com.zhanghao.reader.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/25.
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ComViewHolder>{

    protected Context mContext;
    protected List<T> mData;


    protected OnItemClickListener onItemClickListener;
    protected ItemViewDelegateManager mItemViewDelegateManager;

    public MultiItemTypeAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mItemViewDelegateManager=new ItemViewDelegateManager();

    }


    public void convert(ComViewHolder viewHolder,T t){
        mItemViewDelegateManager.convert(viewHolder,t,viewHolder.getAdapterPosition());
    }



    public boolean isEnabled(int type){
        return true;
    }



    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager())
            return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mData.get(position),position);
    }

    @Override
    public ComViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate=mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId=itemViewDelegate.getItemViewLayoutId();
        ComViewHolder comViewHolder=ComViewHolder.createViewHolder(mContext,parent,layoutId);
        onViewHolderCreated(comViewHolder,comViewHolder.getConvertView());
        //// TODO: 2016/11/25 set Listener
        setListener(parent,comViewHolder,viewType);
        return comViewHolder;
    }



    public void onViewHolderCreated(ComViewHolder holder,View itemView){

    }



    @Override
    public void onBindViewHolder(ComViewHolder holder, int position) {
        convert(holder,mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate){
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public  MultiItemTypeAdapter addItemViewDelegate(int viewType,ItemViewDelegate<T> itemViewDelegate){
        mItemViewDelegateManager.addDelegate(viewType,itemViewDelegate);
        return this;
    }



    protected boolean useItemViewDelegateManager(){
        return mItemViewDelegateManager.getItemViewDelegateCount()>0;
    }


    protected void setListener(ViewGroup parent, final ComViewHolder comViewHolder, int viewType){
        if (!isEnabled(viewType)) return;
        comViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    int pos=comViewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(view,comViewHolder,pos);
                }
            }
        });
    }



    public interface OnItemClickListener{
        void onItemClick(View view,RecyclerView.ViewHolder viewHolder,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
