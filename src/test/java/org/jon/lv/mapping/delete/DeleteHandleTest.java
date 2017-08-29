package org.jon.lv.mapping.delete;

import org.jon.lv.ESRestClient;
import org.jon.lv.delete.DeleteHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package org.jon.lv.mapping.delete.DeleteHandleTest
 * @Description: DeleteHandleTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 14:15
 * version V1.0.0
 */
public class DeleteHandleTest {

    private DeleteHandle deleteHandle;

    @Before
    public void init(){
        deleteHandle = new DeleteHandle(ESRestClient.getClient());
    }

    @Test
    public void deleteById() throws IOException {
        deleteHandle.deleteById("cars", "transactions", 2L);
    }

    @Test
    public void deleteAll() throws IOException {
        deleteHandle.deleteAll("cars", "transactions");
    }

    @After
    public void close() throws IOException {
        deleteHandle.restClient.close();
    }
}
