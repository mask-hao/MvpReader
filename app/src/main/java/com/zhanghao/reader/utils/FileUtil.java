package com.zhanghao.reader.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.widget.Toast;

import com.zhanghao.reader.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by zhanghao on 2016/12/23.
 */

public class FileUtil {


    public static boolean saveBitmap2file(Bitmap bmp,String FILE_PATH)
    {
        CompressFormat format = Bitmap.CompressFormat.PNG;
        int quality = 100;
        OutputStream stream = null;
        try
        {
            // 判断SDcard状态
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            {
                // 错误提示
                return false;
            }

            // 检查SDcard空间
            File SDCardRoot = Environment.getExternalStorageDirectory();
            if (SDCardRoot.getFreeSpace() < 10000)
            {
                // 弹出对话框提示用户空间不够
//                Log.e("Utils", "存储空间不够");
                Toast.makeText(MyApplication.getContext(),"存储空间不足！",Toast.LENGTH_SHORT).show();
                return false;
            }

            // 在SDcard创建文件夹及文件
            File bitmapFile = new File(SDCardRoot.getPath() + FILE_PATH);
            bitmapFile.getParentFile().mkdirs();// 创建文件夹
            stream = new FileOutputStream(SDCardRoot.getPath() + FILE_PATH);// "/sdcard/"
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }
}
