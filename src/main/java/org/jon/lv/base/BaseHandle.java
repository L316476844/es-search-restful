package org.jon.lv.base;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * @Package org.jon.lv.base.BaseHandle
 * @Description: BaseHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/21 10:37
 * version V1.0.0
 */
public abstract class BaseHandle {

    public String index;

    public String type;

    public RestClient restClient;

    public BaseHandle(RestClient restClient){
        this.restClient = restClient;
    }

    public BaseHandle(RestClient restClient, String index, String type){
        this.index = index;
        this.type = type;
        this.restClient = restClient;
    }

    /**
     * 请求
     * @param requestMethod
     * @param url
     * @param params
     * @param jsonStr
     * @param headers
     * @return
     * @throws IOException
     */
    public String request(RequestMethod requestMethod, String url, Map<String, String> params,
                          String jsonStr, Header... headers) throws IOException {

        HttpEntity entity = null;
        if(jsonStr != null && !"".equals(jsonStr)){
            entity = new NStringEntity(
                    jsonStr, ContentType.APPLICATION_JSON);
        }
        Response response =  restClient.performRequest(requestMethod.toString(), url, params, entity, headers);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 美化格式请求
     * @param requestMethod
     * @param url
     * @param jsonStr
     * @param headers
     * @return
     * @throws IOException
     */
    public String requestPretty(RequestMethod requestMethod, String url,
                                String jsonStr, Header... headers) throws IOException {
        return request(requestMethod, url, Collections.singletonMap("pretty", "true"), jsonStr, headers);
    }

    /**
     * 空参数请求
     * @param requestMethod
     * @param url
     * @param jsonStr
     * @param headers
     * @return
     * @throws IOException
     */
    public String requestEmpty(RequestMethod requestMethod, String url,
                               String jsonStr, Header... headers) throws IOException {
        return request(requestMethod, url, Collections.EMPTY_MAP, jsonStr, headers);
    }

    public enum RequestMethod{
        GET,POST,PUT,DELETE;
    }
}
