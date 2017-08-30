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
     * 必须在开启使用_all整合所有字段值的情况下才可以使用
     * @param content - 待查询字符串内容
     * @param from - 起始拉取位置
     * @param size - 拉取条数
     * @param rtnFields - 返回字段
     * @return
     * @throws IOException
     */
    public String matchAll(String content, int from , int size, Set<String> rtnFields) throws IOException {

        String url = BuildPath.build(index, type, Constant._SEARCH);

        String query = "{\"query\":{\"multi_match\":{\"query\":\"" + content+"\",\"fields\":[\"_all\"]}}," +
                "\"from\":" + from + ",\"size\":"+size+"}";

        if(rtnFields != null && rtnFields.size() > 0){
            query = "{\"query\":{\"multi_match\":{\"query\":\"" + content+"\",\"fields\":[\"_all\"]}}," +
                    "\"from\":"+from+",\"size\":"+size+",\"_source\": "+ ConvertUtils.collection2String(rtnFields)+"}";
        }

        JSONObject object = JSON.parseObject(requestPretty(RequestMethod.POST, url, query));

        return object.getJSONObject(Constant.HITS).toJSONString();
    }
}
