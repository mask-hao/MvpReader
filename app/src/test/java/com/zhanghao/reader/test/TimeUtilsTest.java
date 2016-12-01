package com.zhanghao.reader.test;

import com.zhanghao.reader.utils.TimeUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanghao on 2016/11/22.
 */
public class TimeUtilsTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
    }

    @Test
    public void getBeforeDate() throws Exception {
        System.out.println(TimeUtils.getBeforeDate("20161122"));
        System.out.println(TimeUtils.getBeforeDate("20161122"));
        System.out.println(TimeUtils.getBeforeDate("20160101"));
        System.out.println(TimeUtils.getBeforeDate("20160301"));
    }

}