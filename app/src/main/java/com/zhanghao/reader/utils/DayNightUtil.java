package com.zhanghao.reader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by zhanghao on 2016/12/11.
 * 日夜主题切换
 */

public class DayNightUtil{

    private static final String TAG = "DayNightUtil";
    private final static String FILE_NAME="setting";
    private final static String MODE="day_night_mode";
    private SharedPreferences sharedPreferences;

    public DayNightUtil(Context context){
        this.sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);

    }

    /**
     * 保存模式设置
     * @param mode 模式
     * @return 保存是否成功
     */
    public boolean setMode(DayNight mode){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(MODE,mode.getName());
        return editor.commit();
    }
    /**
     *
     * @return 是否为夜间模式
     */
    public  boolean isNight(){
        String mode=sharedPreferences.getString(MODE,DayNight.DAY.getName());
        Log.d(TAG, "isNight: "+mode);
        return mode.equals(DayNight.NIGHT.getName());

    }

    /**
     *
     * @return 是否为日间模式
     */
    public boolean isDay(){
        String  mode=sharedPreferences.getString(MODE,DayNight.DAY.getName());
        Log.d(TAG, "isDay: "+mode);
        return mode.equals(DayNight.DAY.getName());
    }




}
