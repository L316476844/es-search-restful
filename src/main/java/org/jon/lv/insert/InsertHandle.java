package org.jon.lv.insert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;
import org.jon.lv.constant.Constant;
import org.jon.lv.utils.BuildPath;

import java.io.IOException;

/**
 * @Package org.jon.lv.insert.InsertHandle
 * @Description: InsertHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 17:16
 * version V1.0.0
 */
public class InsertHandle extends BaseHandle {

    public InsertHandle(RestClient restClient) {
        super(restClient);
    }

    public String insert(String index, String type, JSONObject object) throws IOException{

        if(!object.containsKey(Constant.ID)){
            return null;
        }

        String url = BuildPath.build(index, type, object.getString("id"));

        return requestPretty(RequestMethod.POST, url, object.toJSONString());
    }

    public String batchInsert(String index, String type, JSONArray jsonArray) throws IOException {

        String url = BuildPath.build(index, type, Constant._BULK);
        String content = assembleContent(jsonArray);
        if(content == null) return null;

        return requestPretty(RequestMethod.POST, url, content);
    }

    private String assembleContent(JSONArray jsonArray)throws RuntimeException{

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            if(!json.containsKey(Constant.ID)){
                return null;
            }
            sb.append("{ \"index\": { \"_id\":"+ json.get("id") +"}}").append("\n");
            sb.append(json.toJSONString()).append("\n");
        }

        return sb.toString();
    }
}
