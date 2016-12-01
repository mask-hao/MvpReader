package com.zhanghao.reader.test;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankItem;
import com.zhanghao.reader.bean.GankVideoItem;
import com.zhanghao.reader.contract.GankVideoContract;
import com.zhanghao.reader.presenter.GankVideoPresenterImpl;

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
public class GankVideoPresenterImplTest {

    @Mock
    private GankVideoContract.View view;

    private GankVideoPresenterImpl presenter;


    private GankService gankService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gankService=new GankApi().getService();
        presenter=new GankVideoPresenterImpl(view);
    }

    @Test
    public void getGankVideoDily() throws Exception {
        verify(view).setPresenter(presenter);
        TestSubscriber<GankVideoItem> testSubscriber=TestSubscriber.create();
        gankService.getGankVideoDaliy("11/19").subscribe(testSubscriber);
        List<GankVideoItem.ResultsBean> resultsBeenList=testSubscriber.getOnNextEvents().get(0).getResults();
        for (GankVideoItem.ResultsBean resultsBean:resultsBeenList)
            System.out.println(resultsBean.toString());




    }

}