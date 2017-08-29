package org.jon.lv.delete;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;
import org.jon.lv.constant.Constant;
import org.jon.lv.utils.BuildPath;

import java.io.IOException;

/**
 * @Package org.jon.lv.delete.DeleteHandle
 * @Description: DeleteHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 14:02
 * version V1.0.0
 */
public class DeleteHandle extends BaseHandle{

    private static final String match_all = "{\"query\":{ \"match_all\": {} }}";

    public DeleteHandle(RestClient restClient) {
        super(restClient);
    }

    public String deleteById(String index, String type, Long id) throws IOException {
        String url = BuildPath.build(index, type, String.valueOf(id));

        return requestPretty(RequestMethod.DELETE, url, null);
    }

    public String deleteByQuery(String index, String type, String queryJson) throws IOException {

        String url = BuildPath.build(index, type, Constant._DELETE_BY_QUERY);

        return requestPretty(RequestMethod.POST, url, queryJson);
    }

    public String deleteAll(String index, String type) throws IOException {

        String url = BuildPath.build(index, type, Constant._DELETE_BY_QUERY);

        return requestPretty(RequestMethod.POST, url, match_all);
    }
}
