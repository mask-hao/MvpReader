package com.zhanghao.reader.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhanghao on 2016/12/18.
 */

public class DownLoadBean implements Parcelable {

    public DownLoadBean(){};


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    private int progress;
    private int currentFileSize;
    private int totalFileSize;



    private DownLoadBean(Parcel in){
        progress=in.readInt();
        currentFileSize=in.readInt();
        currentFileSize=in.readInt();
    }



    public static final Parcelable.Creator<DownLoadBean> CREATOR=new Parcelable.Creator<DownLoadBean>(){

        @Override
        public DownLoadBean createFromParcel(Parcel source) {
            return new DownLoadBean(source);
        }

        @Override
        public DownLoadBean[] newArray(int size) {
            return new DownLoadBean[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(progress);
        dest.writeInt(currentFileSize);
        dest.writeInt(totalFileSize);
    }
}
