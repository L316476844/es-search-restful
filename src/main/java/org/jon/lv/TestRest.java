package org.jon.lv;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Collections;

/**
 * @Package org.jon.lv.TestRest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 15:54
 * version V1.0.0
 */
public class TestRest {

    public static void main(String[] args) throws IOException {
        RestClient restClient = ESRestClientFactory.getInstance();

        queryAll(restClient);
    }


    public static void queryAll(RestClient restClient) throws IOException {

        HttpEntity entity = new NStringEntity(
                "{\"query\": { \"match_all\": {} }, \"from\": 1, \"size\": 2}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest("GET", "/db_news/news/_search",
                Collections.singletonMap("pretty", "true"),
                entity);

        String str = EntityUtils.toString(response.getEntity());

        System.out.println(str);

        JSONObject jsonObject = JSON.parseObject(str);

    }
}
