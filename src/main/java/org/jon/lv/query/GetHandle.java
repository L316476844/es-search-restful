package org.jon.lv.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;
import org.jon.lv.constant.Constant;
import org.jon.lv.utils.BuildPath;

import java.io.IOException;
import java.util.List;

/**
 * @Package org.jon.lv.query.GetHandle
 * @Description: GetHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 9:30
 * version V1.0.0
 */
public class GetHandle extends BaseHandle {

    public GetHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }

    public String get(Long id) throws IOException {
        String url = BuildPath.build(index, type, String.valueOf(id));

        String str = requestPretty(RequestMethod.GET, url, null);
        JSONObject obj = JSON.parseObject(str);

        JSONObject object = obj.getJSONObject("_source");

        System.out.println(object);

        return object.toJSONString();
    }

    public String mget(List<Long> ids) throws IOException {
        String url = BuildPath.build(index, type, Constant._MGET);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ids", ids);

        String str = requestPretty(RequestMethod.GET, url, jsonObject.toJSONString());
        JSONObject obj = JSON.parseObject(str);

        JSONArray jsonArray = obj.getJSONArray("docs");

        JSONArray result = new JSONArray(jsonArray.size());

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject tmp = jsonArray.getJSONObject(i);

            result.add(tmp.getJSONObject("_source"));
        }

        System.out.println(result.toJSONString());

        return result.toJSONString();
    }
}
