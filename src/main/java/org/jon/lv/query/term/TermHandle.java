package org.jon.lv.query.term;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.term.TermHandle
 * @Description: TODO
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 9:01
 * version V1.0.0
 */
public class TermHandle extends BaseHandle {
    public TermHandle(RestClient restClient) {
        super(restClient);
    }
}
