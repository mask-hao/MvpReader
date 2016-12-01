package com.zhanghao.reader.test;

import com.zhanghao.reader.api.zhihu.ZhiHuApi;
import com.zhanghao.reader.api.zhihu.ZhiHuService;
import com.zhanghao.reader.bean.ZhiHuContent;
import com.zhanghao.reader.contract.ZhiHuContentContract;
import com.zhanghao.reader.presenter.ZhiHuNewsContentPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by zhanghao on 2016/11/19.
 */
public class ZhiHuNewsContentPresenterImplTest {


    private ZhiHuNewsContentPresenterImpl contentPresenter;
    @Mock
    private ZhiHuContentContract.View view;
    private ZhiHuService zhiHuService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        zhiHuService=new ZhiHuApi().getService();
        contentPresenter=new ZhiHuNewsContentPresenterImpl(view);
    }

    @Test
    public void ZhiHuContentTest(){
        verify(view).setPresenter(contentPresenter);
        TestSubscriber<ZhiHuContent> testSubscriber=TestSubscriber.create();

        zhiHuService.getNewContent("8986393").subscribe(testSubscriber);

        ZhiHuContent zhiHuContent=testSubscriber.getOnNextEvents().get(0);
        System.out.println(zhiHuContent.getBody());


    }



}