package com.zhanghao.reader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import com.zhanghao.reader.ui.activity.GankContentActivity;
import com.zhanghao.reader.ui.activity.GankDailyActivity;
import com.zhanghao.reader.ui.activity.MainActivity;
import com.zhanghao.reader.ui.activity.PhotoActivity;
import com.zhanghao.reader.ui.activity.SettingActivity;
import com.zhanghao.reader.ui.activity.ZhiHuContentActivity;

/**
 * Created by zhanghao on 2016/11/24.
 */

public class ActivityUtil {
    public static void toZhiHuNewContent(Context context,String id){
        Intent intent=new Intent(context, ZhiHuContentActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    public static void toGankIoContent(Context context,String url,String title){
        Intent intent=new Intent(context, GankContentActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    public static void toPhotoActivity(Activity context, String url, View view){
        Intent intent=new Intent(context, PhotoActivity.class);
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(context,view,PhotoActivity.TRANSIT_PIC);
        intent.putExtra("url",url);
        ActivityCompat.startActivity(context,intent,optionsCompat.toBundle());
//        context.startActivity(intent);
    }


    public static void toGankDailyActivity(Context context,String date){
        Intent intent=new Intent(context,GankDailyActivity.class);
        intent.putExtra("date",date);
        context.startActivity(intent);
    }

    public static void toMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void toSettingActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }
}
