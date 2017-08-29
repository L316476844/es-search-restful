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

    private GetHandle mgetHandle;

    @Before
    public void init(){
        mgetHandle = new GetHandle(ESRestClient.getClient());
    }

    @Test
    public void get() throws IOException{
        mgetHandle.get("jon", "books", 1L);
    }

    @Test
    public void mget() throws IOException {
        List<Long> ids = Arrays.asList(1L, 2L);

        mgetHandle.mget("jon", "books", ids);
    }


    @After
    public void close() throws IOException {
        mgetHandle.restClient.close();
    }

}
