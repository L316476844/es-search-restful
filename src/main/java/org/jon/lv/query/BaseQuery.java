package org.jon.lv.query;

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
 * @Package org.jon.lv.query.BaseQuery
 * @Description: base query
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 15:29
 * version V1.0.0
 */
public class BaseQuery {

    public static final String QUERY_ALL = "{\"query\": { \"match_all\": {} }}";

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
