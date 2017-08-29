package org.jon.lv.mapping.query;

import org.jon.lv.ESRestClient;
import org.jon.lv.query.GetHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Package org.jon.lv.mapping.query.GetHandleTest
 * @Description: GetHandleTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 9:43
 * version V1.0.0
 */
public class GetHandleTest {

    private GetHandle getHandle;

    @Before
    public void init(){
        getHandle = new GetHandle(ESRestClient.getClient(), "jon", "books");
    }

    @Test
    public void get() throws IOException{
        getHandle.get(1L);
    }

    @Test
    public void mget() throws IOException {
        List<Long> ids = Arrays.asList(1L, 2L);

        getHandle.mget(ids);
    }


    @After
    public void close() throws IOException {
        getHandle.restClient.close();
    }

}
