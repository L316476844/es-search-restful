package org.jon.lv.base;

import org.elasticsearch.client.RestClient;

/**
 * @Package org.jon.lv.base.BaseHandle
 * @Description: BaseHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/21 10:37
 * version V1.0.0
 */
public abstract class BaseHandle {

    public RestClient restClient;

    public BaseHandle(RestClient restClient){
        this.restClient = restClient;
    }
}
