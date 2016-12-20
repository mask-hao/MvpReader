package com.zhanghao.reader.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhanghao on 2016/12/11.
 * 保存相关设置到SharedPreferences
 */

public class SpUtil {

    private static SharedPreferences sharedPreferences;

    public static void init(Context context,String FILE_NAME){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    public static boolean saveString(String Key,String Value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Key,Value);
        return editor.commit();
    }

    public static String getSavedString(String Key){
        return sharedPreferences.getString(Key,null);
    }

}
