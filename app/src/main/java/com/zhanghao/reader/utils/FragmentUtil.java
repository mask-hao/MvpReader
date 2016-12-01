package com.zhanghao.reader.utils;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import com.zhanghao.reader.R;
import com.zhanghao.reader.presenter.GankDailyPresenterImpl;
import com.zhanghao.reader.presenter.ZhiHuNewsPresenterImpl;
import com.zhanghao.reader.ui.fragment.GankDailyFragment;
import com.zhanghao.reader.ui.fragment.ZhiHuFragment;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class FragmentUtil {
    private ZhiHuFragment zhiZhuFrg;
    private GankDailyFragment gankDailyFragment;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;

    public FragmentUtil(AppCompatActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        actionBar = activity.getSupportActionBar();
    }

    public void initFragment(String name) {
        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (name) {
            case "zhihu":
                actionBar.setTitle(R.string.frag_zhihu_title);
                if (zhiZhuFrg == null) {
                    zhiZhuFrg = new ZhiHuFragment();
                    new ZhiHuNewsPresenterImpl(zhiZhuFrg);
                    transaction.add(R.id.fragment_content, zhiZhuFrg);
                } else
                    transaction.show(zhiZhuFrg);
                break;

            case "gank":
                actionBar.setTitle(R.string.frag_gankio_title);
                if (gankDailyFragment == null) {
                    gankDailyFragment = new GankDailyFragment();
                    new GankDailyPresenterImpl(gankDailyFragment);
                    transaction.add(R.id.fragment_content, gankDailyFragment);
                } else
                    transaction.show(gankDailyFragment);
                break;

        }
        transaction.commit();
    }


    private void hideFragments(FragmentTransaction transaction) {
        if (zhiZhuFrg != null)
            transaction.hide(zhiZhuFrg);
        if (gankDailyFragment != null)
            transaction.hide(gankDailyFragment);
    }


}
