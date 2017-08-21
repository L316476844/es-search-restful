package org.jon.lv.mapping;

import org.elasticsearch.client.RestClient;
import org.jon.lv.ESRestClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package org.jon.lv.mapping.MappingHandleTest
 * @Description: MappingHandleTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/21 11:22
 * version V1.0.0
 */
public class MappingHandleTest {

    private MappingHandle mappingHandle;

    @Before
    public void init(){
        mappingHandle = new MappingHandle(ESRestClient.getClient());
    }

    @Test
    public void queryAllIndex() throws IOException {
        String str = mappingHandle.queryAllIndex();
        System.out.println(str);
    }

    @Test
    public void queryAllMapping() throws IOException {
        String str = mappingHandle.queryAllMapping();
        System.out.println(str);
    }

    @Test
    public void getMapping() throws IOException {
        String str = mappingHandle.getMapping("vinuxcart", "cart");
        System.out.println(str);
    }

    @Test
    public void deleteMapping() throws IOException {
        String str = mappingHandle.deleteMapping("test");
        System.out.println(str);
    }

    @After
    public void close() throws IOException {
        mappingHandle.restClient.close();
    }
}
