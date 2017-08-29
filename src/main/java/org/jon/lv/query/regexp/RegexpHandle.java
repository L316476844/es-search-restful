package org.jon.lv.query.regexp;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.regexp.RegexpHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 9:00
 * version V1.0.0
 */
public class RegexpHandle extends BaseHandle{
    public RegexpHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }
}
