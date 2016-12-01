package com.zhanghao.reader.test;
import com.zhanghao.reader.api.zhihu.ZhiHuApi;
import com.zhanghao.reader.api.zhihu.ZhiHuService;
import com.zhanghao.reader.bean.ZhiHuDailyItem;
import com.zhanghao.reader.bean.ZhiHuLatestItem;
import com.zhanghao.reader.bean.ZhiHuStories;
import com.zhanghao.reader.bean.ZhiHuTopStories;
import com.zhanghao.reader.contract.ZhiHuDailyContract;
import com.zhanghao.reader.presenter.ZhiHuNewsPresenterImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import rx.observers.TestSubscriber;
import static org.mockito.Mockito.verify;

/**
 * Created by zhanghao on 2016/11/17.
 */
public class ZhiHuNewsPresenterTest{

    private ZhiHuNewsPresenterImpl zhiHuNewsPresenter;

    @Mock
    private ZhiHuDailyContract.View view;
    private ZhiHuService zhiHuService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        zhiHuService=new ZhiHuApi().getService();
        zhiHuNewsPresenter=new ZhiHuNewsPresenterImpl(view);
    }

    @Test
    public void getLatestNewsTest(){

        verify(view).setPresenter(zhiHuNewsPresenter);
        //verify(view).showDialog();
        TestSubscriber<ZhiHuLatestItem> testSubscriber=TestSubscriber.create();

        zhiHuService.getLatestNews().subscribe(testSubscriber);
        List<ZhiHuTopStories> topStoriesBeanList=testSubscriber.getOnNextEvents().get(0).getTop_stories();
        for(int i=0;i<topStoriesBeanList.size();i++){
            System.out.println(topStoriesBeanList.get(i).toString());
        }
        //verify(view).hideDialog();

    }

    @Test
    public void getDailyTest(){

        verify(view).setPresenter(zhiHuNewsPresenter);

        //verify(view).showDialog();
        TestSubscriber<ZhiHuDailyItem> testSubscriber=TestSubscriber.create();

        zhiHuService.getDailyNews("20161110").subscribe(testSubscriber);
        List<ZhiHuStories> topStoriesBeanList=testSubscriber.getOnNextEvents().get(0).getStories();
        for(int i=0;i<topStoriesBeanList.size();i++){
            System.out.println(topStoriesBeanList.get(i).toString());
        }
        //verify(view).hideDialog();
    }



}
