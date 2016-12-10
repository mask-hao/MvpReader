package com.zhanghao.reader.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.ui.adapter.base.ComAdapter;
import com.zhanghao.reader.ui.adapter.base.ComViewHolder;
import com.zhanghao.reader.utils.DensityUtil;
import com.zhanghao.reader.utils.TimeUtils;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyAdapter extends ComAdapter<DisplayItem>{

//    private float width,height;
//    private float sWidth;
//    private DensityUtil densityUtil;

    public GankDailyAdapter(Context mContext, int mLayoutId, List<DisplayItem> mDatas) {
        super(mContext, mLayoutId, mDatas);
//        densityUtil=new DensityUtil(mContext);
//        initWH();

    }

//    private void initWH() {
//        sWidth=densityUtil.getScreenWidth();
//        float card_margin=mContext.getResources().getDimension(R.dimen.card_margin);//dp
//        float image_height=mContext.getResources().getDimension(R.dimen.gank_photo_height);//dp
//        float subW=densityUtil.dip2px(card_margin*4);
//        width=(sWidth-subW)/2;
//        height=densityUtil.dip2px(image_height);
//    }

    @Override
    public void convert(final ComViewHolder comViewHolder, DisplayItem displayItem, final int position) {

        GankDailyItem gankDailyItem= (GankDailyItem) displayItem;
        ImageView photo_iv=comViewHolder.getView(R.id.photo_iv);
        TextView title_tv=comViewHolder.getView(R.id.photo_tv);

        String url= gankDailyItem.getUrl();
        String date= TimeUtils.getCurrentDate("yyyy-MM-dd","MM-dd",gankDailyItem.getDate());
        String title=gankDailyItem.getTitle();
        comViewHolder.setGankImageResource(R.id.photo_iv,url);
        comViewHolder.setTextAutoAdapter(R.id.photo_tv,date+"  "+title);


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
