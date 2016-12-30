package com.zhanghao.reader.ui.fragment;
import android.app.ProgressDialog;
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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.DisplayItem;
import com.zhanghao.reader.bean.ItemSection;
import com.zhanghao.reader.bean.ThemeChangeMessage;
import com.zhanghao.reader.bean.ThemeViewPool;
import com.zhanghao.reader.bean.ZhiHuStories;
import com.zhanghao.reader.bean.ZhiHuTopStories;
import com.zhanghao.reader.contract.ZhiHuDailyContract;
import com.zhanghao.reader.ui.activity.MainActivity;
import com.zhanghao.reader.ui.adapter.ZhiHuAdapter;
import com.zhanghao.reader.ui.adapter.base.LoadMoreWrapper;
import com.zhanghao.reader.ui.adapter.base.MultiItemTypeAdapter;
import com.zhanghao.reader.ui.adapter.base.MyHeaderAndFooterWrapper;
import com.zhanghao.reader.ui.listener.RefreshUIListener;
import com.zhanghao.reader.ui.view.RollViewPager;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.CommonUtil;
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

public class ZhiHuFragment extends BaseFragment implements ZhiHuDailyContract.View, MultiItemTypeAdapter.OnItemClickListener{
    private static final String TAG = "ZhiHuFragment";
    View root;
    @BindView(R.id.zhihu_recycler)
    RecyclerView zhihuRecycler;
    @BindView(R.id.zhihu_srl)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.content_pr)
    ContentLoadingProgressBar contentPr;
    @BindView(R.id.zhihu_frag_cdl)
    CoordinatorLayout zhihuFragCdl;
    private RollViewPager rollViewPager;
    private ZhiHuAdapter zhiHuAdapter;
    private LoadMoreWrapper loadMoreWrapper;
    private MyHeaderAndFooterWrapper headerFooterWrapper;
    private ZhiHuDailyContract.Presenter presenter;
    private ProgressDialog dialog;
    private String now;
    private List<DisplayItem> listAll = new ArrayList<>();

    private List<CoordinatorLayout> coordinatorLayoutList=new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.zhihu_frag, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rollViewPager != null) rollViewPager.stopRoll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubScribe();
    }

    private void init() {
        now = TimeUtils.getCurrentDate("yyyyMMdd");
    }

    private void initView() {

        coordinatorLayoutList.add(zhihuFragCdl);

        refreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getLatestZhiHuNews(true);
            }
        });
        zhihuRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        zhihuRecycler.setItemAnimator(new DefaultItemAnimator());
        if (presenter!=null)
            presenter.getLatestZhiHuNews(false);
    }

    @Override
    public void setUpZhiHuNewsLastestList(List<ZhiHuStories> storiesBeanList, List<ZhiHuTopStories> storiesBeanTopList ,boolean isRefresh) {

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
            now = TimeUtils.getCurrentDate("yyyyMMdd");
        }
        if (isRefresh) listAll.clear();



        List<String> titles = new ArrayList<>();
        final List<String> images = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        for (ZhiHuTopStories zhiHuTopStories : storiesBeanTopList) {
            titles.add(zhiHuTopStories.getTitle());
            images.add(zhiHuTopStories.getImage());
            ids.add(zhiHuTopStories.getId());
        }

        View ZhiHuHeader = initZhihuHeader(storiesBeanTopList);//获取header

        listAll.add(new ItemSection("今日热文"));
        listAll.addAll(storiesBeanList);

        zhiHuAdapter = new ZhiHuAdapter(getContext(), listAll);

        headerFooterWrapper = new MyHeaderAndFooterWrapper(zhiHuAdapter);

        headerFooterWrapper.addHeaderView(ZhiHuHeader);

        loadMoreWrapper = new LoadMoreWrapper(headerFooterWrapper);

        loadMoreWrapper.setLoadMoreView(R.layout.loadmore);
        zhihuRecycler.setAdapter(loadMoreWrapper);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void OnLoadMore() {
                loadMoreWrapper.setIsLoadMore(true);
                now = TimeUtils.getBeforeDate("yyyyMMdd", now);
                presenter.getDailyNews(now);
            }
        });
        zhiHuAdapter.setOnItemClickListener(this);
    }

    @Override
    public void UpDateZhiHuNewsList(List<ZhiHuStories> storiesBeanList) {
        listAll.add(new ItemSection(TimeUtils.getSessionDate("yyyyMMdd", now)));
        listAll.addAll(storiesBeanList);
        // TODO: 2016/11/24 使用diffUtils Android 7.0 新特性
        loadMoreWrapper.notifyDataSetChanged();
        loadMoreWrapper.setIsLoadMore(false);
    }

    @Override
    public void showDialog() {
        contentPr.show();
    }

    @Override
    public void hideDialog() {
        contentPr.hide();
    }

    @Override
    public void showError(Throwable error) {
        ///Log.d(TAG, error.getCause().getMessage());
//        Toast.makeText(getContext(), "加载出错，请重试！\n", Toast.LENGTH_SHORT).show();
        snackbar=Snackbar.make(getActivity().getWindow().getDecorView(),"加载失败！",Snackbar.LENGTH_INDEFINITE)
                .setAction("重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.getLatestZhiHuNews(false);
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
    }

    @Override
    public void setPresenter(ZhiHuDailyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 初始化顶部布局
     *
     * @param storiesBeanList
     * @return
     */
    private View initZhihuHeader(final List<ZhiHuTopStories> storiesBeanList) {
        if (storiesBeanList != null && storiesBeanList.size() > 0) {
            View headerView = LayoutInflater.from(getContext()).inflate(R.layout.zhihu_header, null);
            FrameLayout flTop = (FrameLayout) headerView.findViewById(R.id.fl_top); // 放ViewPager的framelayout
            LinearLayout LlDots = (LinearLayout) headerView.findViewById(R.id.ll_dots);// 放小圆点的linerlayout
            // 初始化viewpager，小圆点
            rollViewPager = new RollViewPager(getContext(), initDots(LlDots, storiesBeanList.size()),
                    R.drawable.point_focured,
                    R.drawable.point_nomal, new RollViewPager.OnPagerClickCallback() {
                @Override
                public void onPagerClick(int position) {
                    ActivityUtil.toZhiHuNewContent(getContext(), String.valueOf(storiesBeanList.get(position).getId()));
                }
            });
            rollViewPager.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    CommonUtil.dip2px(getContext(), 240)));

            rollViewPager.setTopStories(storiesBeanList);
            rollViewPager.startRoll();

            // 将viewpager放进去
            flTop.removeAllViews();
            flTop.addView(rollViewPager);
            return headerView;
        }
        return null;
    }

    /**
     * 初始化顶部轮播小圆点
     *
     * @param LlDot 承载布局
     * @param size  圆点数
     * @return
     */
    private ArrayList<View> initDots(LinearLayout LlDot, int size) {
        ArrayList<View> dotList = new ArrayList<>();
        LlDot.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonUtil.dip2px(getContext(), 6), CommonUtil.dip2px(getContext(), 6));
            params.setMargins(5, 0, 5, 0);
            View m = new View(getContext());
            if (i == 0) {
                m.setBackgroundResource(R.drawable.point_focured);
            } else {
                m.setBackgroundResource(R.drawable.point_nomal);
            }
            m.setLayoutParams(params);
            LlDot.addView(m);
            dotList.add(m);
        }
        return dotList;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
        int RealPosition = position - 1;
        if (listAll.get(RealPosition) instanceof ItemSection) return;
        String id = String.valueOf(((ZhiHuStories) listAll.get(RealPosition)).getId());
        ActivityUtil.toZhiHuNewContent(getContext(), id);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ThemeChangeMessage message){
        if (message.isChange()) {
            Log.e("xiaoxi", " zhihu onMessageEvent: "+message.isChange());
            RefreshUI();
        }
    }


    public void RefreshUI(){
        initTypedValues();
        for(CoordinatorLayout coordinatorLayout:coordinatorLayoutList)
           coordinatorLayout.setBackgroundResource(cdlBackgroundColor.resourceId);

        int childCount=zhihuRecycler.getChildCount();
        for (int i=0;i<childCount;i++){
            ViewGroup childView= (ViewGroup) zhihuRecycler.getChildAt(i);

            String name=childView.getClass().getSimpleName();
            Log.d(TAG, "RefreshUI: Name : " +name);
            if (childView instanceof LinearLayout){

                LinearLayout linearLayout= (LinearLayout) childView.findViewById(R.id.zhihu_section_ll0);
                linearLayout.setBackgroundResource(cdlBackgroundColor.resourceId);

                TextView textView= (TextView) childView.findViewById(R.id.sec_tv);
                textView.setBackgroundResource(cdlBackgroundColor.resourceId);
                textView.setTextColor(getResources().getColor(textColor.resourceId));
            }
            if (childView instanceof RelativeLayout){
                RelativeLayout relativeLayout0= (RelativeLayout) childView.findViewById(R.id.zhihu_item_rl0);
                relativeLayout0.setBackgroundResource(cdlBackgroundColor.resourceId);

                CardView cardView= (CardView) childView.findViewById(R.id.zhihu_item_cardview);
                cardView.setCardBackgroundColor(cdlBackgroundColor.resourceId);

                RelativeLayout relativeLayout= (RelativeLayout) childView.findViewById(R.id.zhihu_item_rl1);
                relativeLayout.setBackgroundResource(backgroundColor.resourceId);

                TextView textView= (TextView) childView.findViewById(R.id.zhihu_title);
                textView.setBackgroundResource(cardBackgroundColor.resourceId);
                textView.setTextColor(resources.getColor(textColor.resourceId));
            }
        }
        RefreshRecyclerView(zhihuRecycler);
    }

}
