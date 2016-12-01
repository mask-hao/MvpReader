package com.zhanghao.reader.test;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankItem;
import com.zhanghao.reader.contract.GankIODailyContract;
import com.zhanghao.reader.presenter.GankIOPresenterImpl;

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
public class GankIOPresenterImplTest {

    @Mock
    private GankIODailyContract.View view;

    private GankService gankService;

    private GankIOPresenterImpl presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gankService=new GankApi().getService();
        presenter=new GankIOPresenterImpl(view);
    }

    @Test
    public void getGankDaliyTest() throws Exception {
        verify(view).setPresenter(presenter);
        TestSubscriber<GankItem> testSubscriber=TestSubscriber.create();
        gankService.getGankDaliy("iOS","11/19").subscribe(testSubscriber);
        List<GankItem.ResultsBean> gankItemList=testSubscriber.getOnNextEvents().get(0).getResults();
        for (GankItem.ResultsBean resultsBean:gankItemList)
            System.out.println(resultsBean.toString());

    }

}