package org.jon.lv.mapping.bulk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jon.lv.ESRestClient;
import org.jon.lv.bulk.BulkHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package org.jon.lv.mapping.bulk.BulkHandleTest
 * @Description: BulkHandleTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 11:26
 * version V1.0.0
 */
public class BulkHandleTest {

    private BulkHandle bulkHandle;

    @Before
    public void init(){
        bulkHandle = new BulkHandle(ESRestClient.getClient());
    }

    @Test
    public void batchInsert() throws IOException {
        JSONArray array = new JSONArray();
        for (int i = 10; i < 21; i++) {
            //{ "id":1, "price" : 10000, "color" : "red", "make" : "honda", "sold" : "2014-10-28" }
            JSONObject obj = new JSONObject();
            obj.put("id", i);
            obj.put("price", i * 10000);
            obj.put("color", "red");
            obj.put("make", "honda");
            obj.put("sold", "2014-10-" + i);
            array.add(obj);
        }
        String json = bulkHandle.batchInsert("cars", "transactions", array);
        System.out.println("-------------------" + json);
    }

    @After
    public void close() throws IOException {
        bulkHandle.restClient.close();
    }
}
