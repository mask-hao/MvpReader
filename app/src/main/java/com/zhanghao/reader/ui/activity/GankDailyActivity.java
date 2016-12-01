package com.zhanghao.reader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.GankDailyAllItem;
import com.zhanghao.reader.bean.GankDisplayItem;
import com.zhanghao.reader.bean.ItemSection;
import com.zhanghao.reader.contract.GankDailyAllContract;
import com.zhanghao.reader.presenter.GankDailyAllPresenterImpl;
import com.zhanghao.reader.ui.adapter.GankDailyAllAdapter;
import com.zhanghao.reader.ui.adapter.base.LoadMoreWrapper;
import com.zhanghao.reader.ui.adapter.base.MultiItemTypeAdapter;
import com.zhanghao.reader.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyActivity extends BaseActivity implements GankDailyAllContract.View, MultiItemTypeAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gankdaily_rv)
    RecyclerView gankdailyRv;
    @BindView(R.id.gank_activity_content_cp)
    ContentLoadingProgressBar gankActivityContentCp;
    private String date;
    private GankDailyAllContract.Presenter presenter;
    private List<GankDisplayItem> gankListAll = new ArrayList<>();
    private GankDailyAllAdapter gankDailyAllAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gank_daily_activity);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        setUpToolBar(date, toolbar, true, true);
        gankdailyRv.setLayoutManager(new LinearLayoutManager(this));
        presenter=new GankDailyAllPresenterImpl(this);
        presenter.getGankDailyAll(date, true);
    }

    private void initData() {
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
    }

    @Override
    public void setUpGankDailyAll(GankDailyAllItem gankDailyAll, boolean firstload) {
        initListAll(gankDailyAll);
        gankDailyAllAdapter = new GankDailyAllAdapter(this,gankListAll);
        gankdailyRv.setAdapter(gankDailyAllAdapter);
        gankDailyAllAdapter.setOnItemClickListener(this);
    }

    /**
     * 加入多有的数据
     *
     * @param gankDailyAll
     */
    private void initListAll(GankDailyAllItem gankDailyAll) {
        GankDailyAllItem.ResultsBean results=gankDailyAll.getResults();
        if (results.getAndroid()!=null){
            gankListAll.add(new ItemSection("Android"));
            gankListAll.addAll(results.getAndroid());
        }
        if (results.getIOS()!=null){
            gankListAll.add(new ItemSection("iOS"));
            gankListAll.addAll(results.getIOS());
        }
        if (results.get前端()!=null){
            gankListAll.add(new ItemSection("前端"));
            gankListAll.addAll(results.get前端());
        }
        if (results.get拓展资源()!=null){
            gankListAll.add(new ItemSection("拓展资源"));
            gankListAll.addAll(results.get拓展资源());
        }
    }


    @Override
    public void showDialog() {
        gankActivityContentCp.show();
    }

    @Override
    public void hideDialog() {
        gankActivityContentCp.hide();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(GankDailyAllContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubScribe();
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
        GankDisplayItem displayItem=gankListAll.get(position);
        if (displayItem instanceof ItemSection) return;
        String url=gankListAll.get(position).getUrl();
        String title=gankListAll.get(position).getDesc();
        ActivityUtil.toGankIoContent(this,url,title);
    }
}
