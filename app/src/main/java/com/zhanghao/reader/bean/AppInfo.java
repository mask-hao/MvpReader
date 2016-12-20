package com.zhanghao.reader.bean;

import com.google.gson.Gson;

/**
 * Created by zhanghao on 2016/12/16.
 */

public class AppInfo {

    /**
     * name : Reader
     * version : 1
     * changelog : 个人学习应用，测试
     * updated_at : 1481899989
     * versionShort : 1.0
     * build : 1
     * installUrl : http://download.fir.im/v2/app/install/5853fd48959d6934b200107f?download_token=0fbb5b687ed7bfd1625f57da4292a88d&source=update
     * install_url : http://download.fir.im/v2/app/install/5853fd48959d6934b200107f?download_token=0fbb5b687ed7bfd1625f57da4292a88d&source=update
     * direct_install_url : http://download.fir.im/v2/app/install/5853fd48959d6934b200107f?download_token=0fbb5b687ed7bfd1625f57da4292a88d&source=update
     * update_url : http://fir.im/7tul
     * binary : {"fsize":2715791}
     */

    private String name;
    private String version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    private BinaryBean binary;

    public static AppInfo objectFromData(String str) {

        return new Gson().fromJson(str, AppInfo.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public BinaryBean getBinary() {
        return binary;
    }

    public void setBinary(BinaryBean binary) {
        this.binary = binary;
    }

    public static class BinaryBean {
        /**
         * fsize : 2715791
         */

        private int fsize;

        public static BinaryBean objectFromData(String str) {

            return new Gson().fromJson(str, BinaryBean.class);
        }

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }
}
