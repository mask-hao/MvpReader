package com.zhanghao.reader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.contract.GankDailyContract;
import com.zhanghao.reader.ui.adapter.GankDailyAdapter;
import com.zhanghao.reader.ui.adapter.base.LoadMoreWrapper;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class GankDailyFragment extends Fragment implements GankDailyContract.View,GankDailyAdapter.OnItemClickListener {

    private static final String TAG = "PhotoFragment";
    View root;
    @BindView(R.id.photo_rlv)
    RecyclerView photoRlv;
    @BindView(R.id.photo_srl)
    SwipeRefreshLayout photoSrl;
    @BindView(R.id.photo_con)
    ContentLoadingProgressBar photoCon;
    private GankDailyContract.Presenter presenter;
    private int page=1;
    private List<DisplayItem> photoAll=new ArrayList<>();
    private GankDailyAdapter dailyAdapter;
    private LoadMoreWrapper loadMoreWrapper;

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
        photoRlv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        photoSrl.setColorSchemeResources(R.color.colorAccent);
        photoSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                photoAll.clear();
                presenter.getGankDaliy(1,false);
            }
        });
        presenter.getGankDaliy(page,true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void setUpData(List<GankDailyItem> gankDailyItems,boolean firstLoad) {
        if (firstLoad){

            photoAll.addAll(gankDailyItems);

            dailyAdapter=new GankDailyAdapter(getContext(),R.layout.gankdaily_item,photoAll);

            loadMoreWrapper=new LoadMoreWrapper(dailyAdapter);
            loadMoreWrapper.setLoadMoreView(R.layout.loadmore);
            photoRlv.setAdapter(loadMoreWrapper);
        }else{

            photoAll.addAll(gankDailyItems);

            loadMoreWrapper.notifyDataSetChanged();
            loadMoreWrapper.setIsLoadMore(false);
        }

        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void OnLoadMore() {
                loadMoreWrapper.setIsLoadMore(true);
                page++;
                presenter.getGankDaliy(page,false);
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
        Log.d(TAG, "showError: "+e.getMessage());
        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(GankDailyContract.Presenter presenter) {
        this.presenter=presenter;
    }


    @Override
    public void setUpGankItemDaily(List<GankDailyItem> gankDailyItems, boolean firstLoad) {
        if (photoSrl.isRefreshing()){
            photoSrl.setRefreshing(false);
            page=1;
        }
        setUpData(gankDailyItems,firstLoad);
    }



    @Override
    public void onPhotoClick(int position) {
        GankDailyItem gankDailyItem= (GankDailyItem) photoAll.get(position);
        String url=gankDailyItem.getUrl();
        Log.d(TAG, "onPhotoClick: "+url);
        ActivityUtil.toPhotoActivity(getContext(),url);
    }



    @Override
    public void onTitleClick(int position) {
        GankDailyItem gankDailyItem= (GankDailyItem) photoAll.get(position);
        String dateGet=gankDailyItem.getDate();
        String date= TimeUtils.getCurrentDate("yyyy-MM-dd","yyyy/MM/dd",dateGet);
        Log.d(TAG, "onTitleClick: "+date);
        ActivityUtil.toGankDailyActivity(getContext(),date);
    }
}
