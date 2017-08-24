package org.jon.lv.query.prefix;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.prefix.PrefixHandle
 * @Description: TODO
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 9:00
 * version V1.0.0
 */
public class PrefixHandle extends BaseHandle{
    public PrefixHandle(RestClient restClient) {
        super(restClient);
    }
}
