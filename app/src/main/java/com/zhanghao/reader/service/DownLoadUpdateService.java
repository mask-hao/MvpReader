package com.zhanghao.reader.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.zhanghao.reader.R;
import com.zhanghao.reader.api.fir.FirDownLoadApi;
import com.zhanghao.reader.api.fir.FirService;
import com.zhanghao.reader.bean.DownLoadBean;
import com.zhanghao.reader.contract.DownLoadAppContract;
import com.zhanghao.reader.presenter.DownLoadAppPresenterImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by zhanghao on 2016/12/18.
 */

public class DownLoadUpdateService extends IntentService {


    private static final String TAG = "DownLoadUpdateService";
    private DownLoadAppContract.Presenter presenter;
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;


    public DownLoadUpdateService() {
        super("download service");
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_book_white_24dp)
                .setContentTitle("下载")
                .setContentText("正在下载更新...")
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());
        String url = intent.getStringExtra("download_url");
        beginDownLoad(url);
    }


    private void beginDownLoad(String url) {
        FirService firService = new FirDownLoadApi().getService();
        Call<ResponseBody> responseBodyCall = firService.downloadNewApp2(url);
        ResponseBody responseBody = null;
        try {
            responseBody = responseBodyCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = responseBody.contentLength();
        InputStream inputStream = new BufferedInputStream(responseBody.byteStream(), 1024 * 8);
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ReaderNew.apk");
        try {
            OutputStream outputStream = new FileOutputStream(outputFile);
            long total = 0;
            long startTime = System.currentTimeMillis();
            int timeCount = 1;
            while ((count = inputStream.read(data)) != -1) {
                total += count;
                totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
                double current = Math.round(total / (Math.pow(1024, 2)));
                int progress = (int) ((total * 100) / fileSize);
                long currentTime = System.currentTimeMillis() - startTime;
                DownLoadBean downLoadBean = new DownLoadBean();
                downLoadBean.setCurrentFileSize(totalFileSize);
                if (currentTime > 1000 * timeCount) {
                    downLoadBean.setCurrentFileSize((int) current);
                    downLoadBean.setProgress(progress);
                    sendNotification(downLoadBean);
                    timeCount++;
                }
                outputStream.write(data, 0, count);
            }
            onDownLoadComplete();
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onDownLoadComplete() {
        DownLoadBean downLoadBean = new DownLoadBean();
        downLoadBean.setProgress(100);
        notificationManager.cancel(0);
        notificationBuilder.setProgress(0, 0, false);
        notificationBuilder.setContentText("文件下载完成!");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File downloadFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ReaderNew.apk");
        Uri uri = FileProvider.getUriForFile(this, "com.zhanghao.fileprovider", downloadFile);
//        intent.setDataAndType(Uri.fromFile(downloadFile),"application/vnd.android.package-archive");
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(0, notificationBuilder.build());
        notificationManager.cancel(0);
        startActivity(intent);
        stopSelf();
    }

    private void sendNotification(DownLoadBean downLoadBean) {
        notificationBuilder.setProgress(100, downLoadBean.getProgress(), false);
        notificationBuilder.setContentText("已下载: " + downLoadBean.getProgress() + "%" + " (" + downLoadBean.getTotalFileSize() + "MB" + ")");
        notificationManager.notify(0, notificationBuilder.build());
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }
}
