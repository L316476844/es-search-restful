package org.jon.lv.mapping.mapping;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RestClient;
import org.jon.lv.ESRestClient;
import org.jon.lv.mapping.MappingHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package org.jon.lv.mapping.mapping.MappingHandleTest
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
    public void createMapping() throws IOException{
        JSONObject properties = new JSONObject();

        JSONObject name = new JSONObject();
        name.put("type", "text");
        name.put("index", true);
        name.put("analyzer", "ik_max_word");
        JSONObject price = new JSONObject();
        price.put("type", "double");
        price.put("store", "true");
        JSONObject publishDate = new JSONObject();
        publishDate.put("type", "date");
        publishDate.put("format", "yyyy-MM-dd HH:mm:ss");
        publishDate.put("store", "true");
        JSONObject title = new JSONObject();
        title.put("type", "keyword");
        title.put("store", "true");

        JSONObject plotName = new JSONObject();
        plotName.put("type", "keyword");
        plotName.put("store", "true");

        properties.put("name", name);
        properties.put("price", price);
        properties.put("publishDate", publishDate);
        properties.put("title", title);
        properties.put("plotName", plotName);

        String str = mappingHandle.createMapping("test1", "plot",properties);
        System.out.println(str);
    }

    @Test
    public void getMapping() throws IOException {
        String str = mappingHandle.getMapping("vinuxcart", "cart");
        System.out.println("-----------------------" + str);
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
