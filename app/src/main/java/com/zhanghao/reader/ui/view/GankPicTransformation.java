package com.zhanghao.reader.ui.view;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

/**
 * Created by zhanghao on 2016/12/4.
 */

public class GankPicTransformation implements Transformation{

    private ImageView targetIv;


    public GankPicTransformation(ImageView imageView) {
        this.targetIv=imageView;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int targetWidth=targetIv.getWidth();

        if (source.getWidth()==0) return source;

        if (source.getWidth()<targetWidth)
            return source;
        else {
            double aspectRatio=(double) source.getHeight()/(double)source.getWidth();
            int targetHeight=(int)(targetWidth*aspectRatio);
            if (targetHeight!=0&&targetWidth!=0){
                Bitmap result=Bitmap.createScaledBitmap(source,targetWidth,targetHeight,false);
                if (result!=source) source.recycle();
                    return result;
            }else
                return source;
        }
    }

    @Override
    public String key() {
        return "ddddd";
    }
}
