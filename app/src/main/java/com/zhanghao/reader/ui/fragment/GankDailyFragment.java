package com.zhanghao.reader.ui.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.bean.ThemeChangeMessage;
import com.zhanghao.reader.bean.ThemeViewPool;
import com.zhanghao.reader.contract.GankDailyContract;
import com.zhanghao.reader.ui.activity.MainActivity;
import com.zhanghao.reader.ui.adapter.GankDailyAdapter;
import com.zhanghao.reader.ui.adapter.base.LoadMoreWrapper;
import com.zhanghao.reader.ui.listener.RefreshUIListener;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.TimeUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class GankDailyFragment extends BaseFragment implements GankDailyContract.View, GankDailyAdapter.OnItemClickListener{

    private static final String TAG = "PhotoFragment";
    View root;
    @BindView(R.id.photo_rlv)
    RecyclerView photoRlv;
    @BindView(R.id.photo_srl)
    SwipeRefreshLayout photoSrl;
    @BindView(R.id.photo_con)
    ContentLoadingProgressBar photoCon;
    @BindView(R.id.gank_frag_cdl)
    CoordinatorLayout gankFragCdl;
    private GankDailyContract.Presenter presenter;
    private int page = 1;
    private List<DisplayItem> photoAll = new ArrayList<>();
    private GankDailyAdapter dailyAdapter;
    private LoadMoreWrapper loadMoreWrapper;

    private List<CoordinatorLayout> coordinatorLayoutList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gank_photo_frag, container, false);
        Log.d(TAG, "onCreateView: ");
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        coordinatorLayoutList.add(gankFragCdl);
        photoRlv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        photoSrl.setColorSchemeResources(R.color.colorAccent);
        photoSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getGankDaliy(1, false,true);//刷新

            }
        });
        presenter.getGankDaliy(page, true,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void setUpData(List<GankDailyItem> gankDailyItems, boolean firstLoad,boolean isRefresh) {

        if (isRefresh) photoAll.clear();

        if (firstLoad) {
            photoAll.addAll(gankDailyItems);
            dailyAdapter = new GankDailyAdapter(getContext(), R.layout.gankdaily_item, photoAll);
            loadMoreWrapper = new LoadMoreWrapper(dailyAdapter);
            loadMoreWrapper.setLoadMoreView(R.layout.loadmore);
            photoRlv.setAdapter(loadMoreWrapper);
//            photoRlv.
                 photoRlv.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         RefreshUI();//第一次加载时需要刷新UI
                     }
                 },500);
        } else {
            photoAll.addAll(gankDailyItems);
            loadMoreWrapper.notifyDataSetChanged();
            loadMoreWrapper.setIsLoadMore(false);
        }
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void OnLoadMore() {
                loadMoreWrapper.setIsLoadMore(true);
                page++;
                presenter.getGankDaliy(page, false,false);
            }
        });
        dailyAdapter.setOnGankDailyClickListener(this);

    }

    @Override
    public void showDialog() {
        photoCon.show();
    }

    @Override
    public void hideDialog() {
        photoCon.hide();
    }

    @Override
    public void showError(Throwable e) {
        Log.d(TAG, "showError: " + e.getMessage());
        snackbar=Snackbar.make(gankFragCdl,"加载失败！",Snackbar.LENGTH_INDEFINITE)
                .setAction("重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.getGankDaliy(1,true,false);
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
    }

    @Override
    public void setPresenter(GankDailyContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setUpGankItemDaily(List<GankDailyItem> gankDailyItems, boolean firstLoad,boolean isRefresh) {
        if (photoSrl.isRefreshing()) {
            photoSrl.setRefreshing(false);
            page = 1;
        }
        setUpData(gankDailyItems, firstLoad,isRefresh);
    }


    @Override
    public void onPhotoClick(View photo,int position) {
        GankDailyItem gankDailyItem = (GankDailyItem) photoAll.get(position);
        String url = gankDailyItem.getUrl();
        Log.d(TAG, "onPhotoClick: " + url);
        ActivityUtil.toPhotoActivity(getActivity(),url,photo);
    }


    @Override
    public void onTitleClick(int position) {
        GankDailyItem gankDailyItem = (GankDailyItem) photoAll.get(position);
        String dateGet = gankDailyItem.getDate();
        String date = TimeUtils.getCurrentDate("yyyy-MM-dd", "yyyy/MM/dd", dateGet);
        Log.d(TAG, "onTitleClick: " + date);
        ActivityUtil.toGankDailyActivity(getContext(), date);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ThemeChangeMessage message){
        if (message.isChange()){
            Log.e("xiaoxi", "gank onMessageEvent: "+message.isChange());
            RefreshUI();
        }
    }


    public void RefreshUI() {
        initTypedValues();
        for(CoordinatorLayout coordinatorLayout:coordinatorLayoutList)
            coordinatorLayout.setBackgroundResource(cdlBackgroundColor.resourceId);
        int childCount=photoRlv.getChildCount();
        for (int i=0;i<childCount;i++){
            ViewGroup childView= (ViewGroup) photoRlv.getChildAt(i);
            CardView cardView= (CardView) childView.findViewById(R.id.gank_item_cv);
            cardView.setCardBackgroundColor(cardBackgroundColor.resourceId);
            LinearLayout linear= (LinearLayout) childView.findViewById(R.id.gank_item_ll1);
            linear.setBackgroundResource(cardBackgroundColor.resourceId);
            TextView textView= (TextView) childView.findViewById(R.id.photo_tv);
            textView.setBackgroundResource(cardBackgroundColor.resourceId);
            textView.setTextColor(resources.getColor(textColor.resourceId));
        }
        RefreshRecyclerView(photoRlv);
    }
}
