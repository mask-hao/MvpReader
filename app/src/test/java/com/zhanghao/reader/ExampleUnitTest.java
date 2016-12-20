package com.zhanghao.reader;
import android.util.Log;

import com.zhanghao.reader.api.zhihu.ZhiHuApi;
import com.zhanghao.reader.api.zhihu.ZhiHuService;
import com.zhanghao.reader.contract.ZhiHuDailyContract;
import com.zhanghao.reader.presenter.ZhiHuNewsPresenterImpl;
import com.zhanghao.reader.utils.TimeUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
     // assertEquals(4, 2 + 2);
//        ZhiHuDailyContract.Presenter presenter=new ZhiHuNewsPresenterImpl();
//        presenter.getLatestZhiHuNews();
        final String url="http://download.fir.im/v2/app/install/5853fd48959d6934b200107f?download_token=0fbb5b687ed7bfd1625f57da4292a88d&source=update".replace("http://download.fir.im","");
        String pattern="http://download.fir.im*";
//        url.replace("http://download.fir.im/","");
        System.out.println(url);
//        Pattern pa=Pattern.compile(pattern);
//        Matcher matcher=pa.matcher(url);
//        System.out.println(matcher.find());
//        System.out.println(matcher.group());
    }
}