package org.jon.lv.mapping;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;
import org.jon.lv.constant.Constant;

import java.io.IOException;
import java.util.Collections;

/**
 * @Package org.jon.lv.mapping.MappingHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 16:42
 * version V1.0.0
 */
public class MappingHandle extends BaseHandle {

    public final static String QUERY_ALL_INDEX = "/_cat/indices?v";
    public final static String QUERY_ALL_MAPPING = "/_all/_mapping/";

    public MappingHandle(RestClient restClient) {
        super(restClient);
    }

    public String queryAllIndex() throws IOException {
        Response response =  restClient.performRequest("GET", QUERY_ALL_INDEX,
                Collections.singletonMap("pretty", "true"));
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 建立映射
     * @param index
     * @param type
     * @param properties
     * @return
     */
    public String createMapping(String index, String type, JSONObject properties) throws IOException {

        //twitter/_mapping/user
        String url = "/" + index + "/" + Constant._MAPPING + "/" + type ;

        // 字段属性
        JSONObject typeObj = new JSONObject();
        typeObj.put("properties", properties);

        HttpEntity entity = new NStringEntity(
                typeObj.toJSONString(), ContentType.APPLICATION_JSON);

        Response response =  restClient.performRequest("PUT", url,
                Collections.singletonMap("pretty", "true"),
                entity);

        return EntityUtils.toString(response.getEntity());
    }

    public String queryAllMapping() throws IOException {
        Response response =  restClient.performRequest("GET", QUERY_ALL_MAPPING,
                Collections.singletonMap("pretty", "true"));

        return EntityUtils.toString(response.getEntity());
    }

    public String getMapping(String index, String type) throws IOException {
        String url = "/" + index + "/" + type + "/" + Constant._MAPPING;

        Response response =  restClient.performRequest("GET", url,
                Collections.singletonMap("pretty", "true"));

        return EntityUtils.toString(response.getEntity());
    }

    public String deleteMapping(String index) throws IOException {
        String url = "/" + index;

        Response response =  restClient.performRequest("DELETE", url,
                Collections.EMPTY_MAP);

        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 文本: text - analyzer/not_analyzed search_analyzer, keyword
     * 数字: byte, short, integer, long
     * 浮点数: float, double
     * 布尔值: boolean
     * Date: date
     * dynamic 动态映射 true:默认值,动态添加字段; false:忽略新字段; strict:碰到陌生字段,抛出异常
     *
     * "type": "geo_point"  {"lat": 41.12,"lon": -71.34} / lat, lon
     * "type": "nested"
     * "copy_to": "full_content"
     *
     * "content": {
            "type": "text",
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word"
        }
     */

    /**
     * eg:映射例子： library（index）-books(type)
     * {
         "library": {
           "mappings": {
             "books": {
                 "properties": {
                     "name": {
                     "type": "string",
                     "index": "not_analyzed"
                     },
                     "number": {
                     "type": "object",
                     "dynamic": "true"
                     },
                     "price": {
                     "type": "double"
                     },
                     "publish_date": {
                     "type": "date",
                     "format": "dateOptionalTime"
                     },
                     "title": {
                     "type": "string"
                    }
            }
           }
          }
         }
     }
     */
}
