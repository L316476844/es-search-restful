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
 * @Package org.jon.lv.query.MgetHandle
 * @Description: MgetHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 9:30
 * version V1.0.0
 */
public class MgetHandle extends BaseHandle {
    public MgetHandle(RestClient restClient) {
        super(restClient);
    }

    public String mget(String index, String type, List<Long> ids) throws IOException {
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
