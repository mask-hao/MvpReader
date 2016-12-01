package com.zhanghao.reader.test;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankPicItem;
import com.zhanghao.reader.contract.GankPicContract;
import com.zhanghao.reader.presenter.GankPicPresentImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by zhanghao on 2016/11/19.
 */
public class GankPicPresentImplTest {

    @Mock
    private GankPicContract.View view;


    private GankPicContract.Presenter presenter;

    private GankService gankService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gankService=new GankApi().getService();
        presenter=new GankPicPresentImpl(view);
    }

    @Test
    public void getGankPicDaliy() throws Exception {
         verify(view).setPresenter(presenter);
        TestSubscriber<GankPicItem> testSubscriber=TestSubscriber.create();
        gankService.getGankPicDaliy("11/19").subscribe(testSubscriber);
        List<GankPicItem.ResultsBean> resultsBeenList=testSubscriber.getOnNextEvents().get(0).getResults();
        for (GankPicItem.ResultsBean resultsBean:resultsBeenList)
            System.out.println(resultsBean.toString());
    }

}