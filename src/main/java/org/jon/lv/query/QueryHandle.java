package org.jon.lv.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;
import org.jon.lv.constant.Constant;
import org.jon.lv.utils.BuildPath;
import org.jon.lv.utils.ConvertUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @Package org.jon.lv.query.QueryHandle
 * @Description: 查询
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/30 9:38
 * version V1.0.0
 */
public class QueryHandle extends BaseHandle {

    public QueryHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }

    /**
     * 自定义组装后的json请求字符串
     * @param queryJson
     * @return
     * @throws IOException
     */
    public String query(String queryJson) throws IOException{
        String url = BuildPath.build(index, type, Constant._SEARCH);

        JSONObject object = JSON.parseObject(requestPretty(RequestMethod.POST, url, queryJson));
        return object.getJSONObject(Constant.HITS).toJSONString();
    }


    /**
     * 必须在开启使用_all整合所有字段值的情况下才可以使用
     * @param content - 待查询字符串内容
     * @param from - 起始拉取位置
     * @param size - 拉取条数
     * @param rtnFields - 返回字段
     * @return
     * @throws IOException
     */
    public String matchAll(String content, int from , int size, Set<String> rtnFields) throws IOException {
        String query = "{\"query\":{\"multi_match\":{\"query\":\"" + content+"\",\"fields\":[\"_all\"]}}," +
                "\"from\":" + from + ",\"size\":"+size+"}";

        if(rtnFields != null && rtnFields.size() > 0){
            query = "{\"query\":{\"multi_match\":{\"query\":\"" + content+"\",\"fields\":[\"_all\"]}}," +
                    "\"from\":"+from+",\"size\":"+size+",\"_source\": "+ ConvertUtils.collection2String(rtnFields)+"}";
        }
        return query(query);
    }

    /**
     * 自定义多字段查询
     * @param content - 待查询字符串内容
     * @param from - 起始拉取位置
     * @param size - 拉取条数
     * @param docFields - 从文档中那些字段匹配 ---字段可以可以加权eg:["title", "summary^3"] ^3 Boosting summary field的权重调成3，这样就提高了其在结果中的权重
     * @param rtnFields - 返回字段
     * @return
     * @throws IOException
     */
    public String multiField(String content, int from , int size, Set<String> docFields ,Set<String> rtnFields) throws IOException {

        if(docFields == null || docFields.size() == 0) return null;

        String query = "{\"query\":{\"multi_match\":{\"query\":\"" + content+"\",\"fields\":"+ ConvertUtils.collection2String(docFields) +"}}," +
                "\"from\":" + from + ",\"size\":"+size+"}";

        if(rtnFields != null && rtnFields.size() > 0){
            query = "{\"query\":{\"multi_match\":{\"query\":\"" + content+"\",\"fields\":"+ConvertUtils.collection2String(docFields)+"}}," +
                    "\"from\":"+from+",\"size\":"+size+",\"_source\": "+ ConvertUtils.collection2String(rtnFields)+"}";
        }

        System.out.println("-------------------" + query);

        return query(query);
    }
}
