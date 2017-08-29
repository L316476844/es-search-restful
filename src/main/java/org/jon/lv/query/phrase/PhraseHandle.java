package org.jon.lv.query.phrase;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.phrase.PhraseHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 8:59
 * version V1.0.0
 */
public class PhraseHandle extends BaseHandle {

    public PhraseHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }
}
