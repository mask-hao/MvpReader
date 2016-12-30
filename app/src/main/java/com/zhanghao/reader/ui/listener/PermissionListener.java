package com.zhanghao.reader.ui.listener;

import java.util.List;

/**
 * Created by zhanghao on 2016/12/30.
 */

public interface PermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermissions);
}
