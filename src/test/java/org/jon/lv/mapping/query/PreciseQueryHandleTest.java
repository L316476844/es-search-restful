package org.jon.lv.mapping.query;

import org.jon.lv.ESRestClient;
import org.jon.lv.bean.RangeBean;
import org.jon.lv.bean.SortBean;
import org.jon.lv.query.PreciseQueryHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Package org.jon.lv.mapping.query.PreciseQueryHandleTest
 * @Description: PreciseQueryHandleTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 9:43
 * version V1.0.0
 */
public class PreciseQueryHandleTest {

    private PreciseQueryHandle preciseQueryHandle;

    @Before
    public void init(){
        preciseQueryHandle = new PreciseQueryHandle(ESRestClient.getClient(), "library", "book");
    }

    @Test
    public void termsQuery() throws IOException{

        Set<String> values = new HashSet<>();
        values.addAll(Arrays.asList("manning","oreilly", "packt"));
        SortBean[] sortBeans = {new SortBean("publish_date", SortBean.SortOrder.DESC), new SortBean("num_reviews", SortBean.SortOrder.ASC)};

        Set<String> rtnFields = new HashSet<>();
        rtnFields.addAll(Arrays.asList("title", "summary", "publish_date"));

        System.out.println(preciseQueryHandle.termsQuery("publisher", values, 0, 2, rtnFields, sortBeans));
    }

    @Test
    public void rangeQuery() throws IOException{

        Set<RangeBean> values = new HashSet<>();
        values.addAll(Arrays.asList(new RangeBean("2015-01-01", RangeBean.Range.gte),
                new RangeBean("2015-12-31", RangeBean.Range.lte)));

        SortBean[] sortBeans = {new SortBean("publish_date", SortBean.SortOrder.DESC),
                new SortBean("num_reviews", SortBean.SortOrder.ASC)};

        Set<String> rtnFields = new HashSet<>();
        rtnFields.addAll(Arrays.asList("title", "summary", "publish_date"));

        System.out.println(preciseQueryHandle.rangeQuery("publish_date", values, 0, 2, rtnFields, sortBeans));
    }

    @After
    public void close() throws IOException {
        preciseQueryHandle.queryHandle.restClient.close();
    }

}
