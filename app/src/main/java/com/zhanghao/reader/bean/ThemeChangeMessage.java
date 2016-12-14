package com.zhanghao.reader.bean;

/**
 * Created by zhanghao on 2016/12/13.
 */

public  class ThemeChangeMessage{
    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    public ThemeChangeMessage(boolean isChange) {
        this.isChange = isChange;
    }

    private boolean isChange;
}
