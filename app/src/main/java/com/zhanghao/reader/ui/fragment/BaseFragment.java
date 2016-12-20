package com.zhanghao.reader.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.zhanghao.reader.R;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhanghao on 2016/11/25.
 */

public class BaseFragment extends Fragment{
    protected TypedValue backgroundColor;//背景色
    protected TypedValue cardBackgroundColor;//cardView背景色
    protected TypedValue cdlBackgroundColor;//coordinatelayout背景色
    protected TypedValue textColor;//TextView背景色
    protected TypedValue toolBarColor; //toolbar背景色
    protected TypedValue statusBarColor; //statusBar背景色
    protected Resources resources;

    protected Snackbar snackbar=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initTypedValues();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    protected void initTypedValues(){
        backgroundColor=new TypedValue();
        cardBackgroundColor=new TypedValue();
        cdlBackgroundColor=new TypedValue();
        textColor=new TypedValue();
        toolBarColor=new TypedValue();
        statusBarColor=new TypedValue();
        Resources.Theme theme= getActivity().getTheme();
        resources=getResources();
        theme.resolveAttribute(R.attr.colorBackground,backgroundColor,true);
        theme.resolveAttribute(R.attr.colorCardBackground,cardBackgroundColor,true);
        theme.resolveAttribute(R.attr.colorTextView,textColor,true);
        theme.resolveAttribute(R.attr.colorCdlBackBackground,cdlBackgroundColor,true);
        theme.resolveAttribute(R.attr.colorPrimary,toolBarColor,true);
        theme.resolveAttribute(R.attr.colorPrimaryDark,statusBarColor,true);

    }

    protected void RefreshRecyclerView(RecyclerView recyclerView){
        Class<RecyclerView> recyclerViewClass=RecyclerView.class;
        try {
            Field declaredField=recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);
            Method declaredMethod=Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear",(Class<?>[]) new Class[0]);
            declaredMethod.invoke(declaredField.get(recyclerView),new Object[0]);
            RecyclerView.RecycledViewPool recycledViewPool=recyclerView.getRecycledViewPool();
            recycledViewPool.clear();
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



}
