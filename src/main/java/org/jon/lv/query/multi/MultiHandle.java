package org.jon.lv.query.multi;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.multi.MultiHandle
 * @Copyright: Ceopyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 8:59
 * version V1.0.0
 */
public class MultiHandle extends BaseHandle{
    public MultiHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }
}
