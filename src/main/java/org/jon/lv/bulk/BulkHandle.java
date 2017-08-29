package org.jon.lv.bulk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;
import org.jon.lv.constant.Constant;
import org.jon.lv.utils.BuildPath;

import java.io.IOException;

/**
 * @Package org.jon.lv.bulk.BulkHandle
 * @Description: BulkHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 17:16
 * version V1.0.0
 */
public class BulkHandle extends BaseHandle {

    private static final String  BASE_BULK = "{ \"index\": {}}";
    private static JSONObject object = JSON.parseObject(BASE_BULK);

    public BulkHandle(RestClient restClient) {
        super(restClient);
    }

    public static boolean batchInsert(String index, String type, JSONArray jsonArray) throws IOException {
        String url = BuildPath.build(index, type, Constant._BULK);
        String content = assembleContent(jsonArray);

        return true;
    }

    public static String assembleContent(JSONArray jsonArray)throws RuntimeException{

        return null;
    }
}
