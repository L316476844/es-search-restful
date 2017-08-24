package org.jon.lv.query.match;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.match.MatchHandle
 * @Description: TODO
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 8:58
 * version V1.0.0
 */
public class MatchHandle extends BaseHandle {
    public MatchHandle(RestClient restClient) {
        super(restClient);
    }
}
