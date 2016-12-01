package com.zhanghao.reader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.ui.adapter.base.ComAdapter;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.utils.TimeUtils;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyAdapter extends ComAdapter<DisplayItem>{

    public GankDailyAdapter(Context mContext, int mLayoutId, List<DisplayItem> mDatas) {
        super(mContext, mLayoutId, mDatas);
    }

    @Override
    public void convert(final ComViewHolder comViewHolder, DisplayItem displayItem, final int position) {

        GankDailyItem gankDailyItem= (GankDailyItem) displayItem;
        ImageView photo_iv=comViewHolder.getView(R.id.photo_iv);
        TextView title_tv=comViewHolder.getView(R.id.photo_tv);

        String url= gankDailyItem.getUrl();
        String date= TimeUtils.getCurrentDate("yyyy-MM-dd","MM-dd",gankDailyItem.getDate());
        String title=gankDailyItem.getTitle();
        comViewHolder.setGankImageResource(R.id.photo_iv,url);
        comViewHolder.setText1(R.id.photo_tv,date+"  "+title);


        if (onItemClickListener!=null){
            photo_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=comViewHolder.getLayoutPosition();
                    onItemClickListener.onPhotoClick(pos);
                }
            });

            title_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=comViewHolder.getLayoutPosition();
                    onItemClickListener.onTitleClick(pos);
                }
            });
        }
    }


    public void setOnGankDailyClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener{
        void onPhotoClick(int position);
        void onTitleClick(int position);
    }
}
