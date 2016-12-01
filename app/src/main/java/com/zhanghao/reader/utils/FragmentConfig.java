package com.zhanghao.reader.utils;

import java.util.ArrayList;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class FragmentConfig {
    public static ArrayList<String> getFragmentList() {
        return fragmentList;
    }

    public static void setFragmentList(ArrayList<String> fragmentList) {
        FragmentConfig.fragmentList = fragmentList;
    }

    private  static ArrayList<String> fragmentList;
    static{
        fragmentList=new ArrayList<>();
        fragmentList.add("zhihu");
        fragmentList.add("gank");
    }
}
