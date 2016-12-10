package com.zhanghao.reader.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.contract.GankDailyAllContract;
import com.zhanghao.reader.ui.view.GankPicTransformation;

/**
 * Created by zhanghao on 2016/11/21.
 */

public class ComViewHolder  extends RecyclerView.ViewHolder{

    private SparseArray<View> mViews;
    private View mConvertView;
    private Context context;


    public ComViewHolder(Context context, View itemView) {
        super(itemView);
        this.mConvertView=itemView;
        this.context=context;
        mViews=new SparseArray<>();
    }


    public static ComViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId){
        View itemView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        ComViewHolder holder=new ComViewHolder(context,itemView);
        return holder;
    }


    public static ComViewHolder createViewHolder(Context context, View itemView){
        ComViewHolder holder=new ComViewHolder(context,itemView);
        return holder;
    }

    /**
     * 通过控件获取控件
     * @param ViewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int ViewId){
        View view =mViews.get(ViewId);
        if (view==null){
            view=mConvertView.findViewById(ViewId);
            mViews.put(ViewId,view);
        }
        return (T) view;
    }



    public View getConvertView(){
        return mConvertView;
    }


    public ComViewHolder setText(int viewId,String text){
        TextView textView=getView(viewId);
        textView.setText(text);
        return this;
    }

    public ComViewHolder setTextAutoAdapter(int viewId, String text){
        TextView textView=getView(viewId);
        ViewGroup.LayoutParams lp=textView.getLayoutParams();
        lp.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        textView.setLayoutParams(lp);
        textView.setText(text);
        return this;
    }



    public ComViewHolder setImageResource(int viewId,String path){
        ImageView imageView=getView(viewId);
        Picasso.with(context)
                .load(path)
                .into(imageView);
        return this;
    }


    public ComViewHolder setGankImageResource(int viewId,String path){
        ImageView imageView=getView(viewId);
        Picasso.with(context)
                .load(path)
                .placeholder(R.drawable.default_pic_content_image_loading_light)
                .transform(new GankPicTransformation(imageView))
                .into(imageView);
        return this;
    }



}
