package com.zhanghao.reader.ui.adapter.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.zhanghao.reader.utils.WrapperUtils;

/**
 * Created by zhanghao on 2016/11/21.
 */

public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int ITEM_TYPE_LOAD_MORE=Integer.MAX_VALUE-2;


    public  boolean isLoadMore() {
        return isLoadMore;
    }

    public  void setIsLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    public  boolean isLoadMore=false;


    private RecyclerView recyclerView;

    private RecyclerView.Adapter mInnerAdapter;

    private View mLoadMoreView;

    private int mLoadMoreLayoutId;

    public LoadMoreWrapper(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
    }


    /**
     * 是否有 加载更多的布局
     * @return
     */
    private boolean hasLoadMore(){
        return mLoadMoreView!=null || mLoadMoreLayoutId!=0;
    }


    /**
     * 是否在加载更多
     * @param position
     * @return
     */
    private boolean isShowLoadMore(int position){
        return hasLoadMore()&&(position>=mInnerAdapter.getItemCount());
    }


    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position))
            return ITEM_TYPE_LOAD_MORE;
        return mInnerAdapter.getItemViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ITEM_TYPE_LOAD_MORE){
            ComViewHolder holder;
            if (mLoadMoreView!=null)
                holder=ComViewHolder.createViewHolder(parent.getContext(),mLoadMoreView);
            else
                holder=ComViewHolder.createViewHolder(parent.getContext(),parent,mLoadMoreLayoutId);

            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)){
            if (isShowLoadMore(position))
                if (!isLoadMore){
                    if (onLoadMoreListener!=null){
                        onLoadMoreListener.OnLoadMore();
                    }
                }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder,position);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback()
        {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position)
            {
                if (isShowLoadMore(position))
                {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder)
    {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition()))
        {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder)
    {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams)
        {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount()+( hasLoadMore()? 1 : 0 );
    }








    public interface OnLoadMoreListener{
        void OnLoadMore();
    }


    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        if (onLoadMoreListener!=null)
            this.onLoadMoreListener = onLoadMoreListener;
        return this;
    }

    private OnLoadMoreListener onLoadMoreListener;

    public LoadMoreWrapper setLoadMoreView(View loadMoreView){
        this.mLoadMoreView=loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int loadMoreLayoutId){
        this.mLoadMoreLayoutId=loadMoreLayoutId;
        return this;
    }




}
