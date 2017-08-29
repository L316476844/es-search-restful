package org.jon.lv.query.term;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.term.TermHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 9:01
 * version V1.0.0
 */
public class TermHandle extends BaseHandle {
    public TermHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }


//    { "term": { "age":    26           }}
//    { "term": { "date":   "2014-09-01" }}
//    { "term": { "public": true         }}
//    { "term": { "tag":    "full_text"  }}
//
//    {
//        "query": {
//        "term": {
//            "hostname": "saaap.wangpos.com"
//        }
//    }
//    }

//  terms 跟 term 有点类似，但 terms 允许指定多个匹配条件。 如果某个字段指定了多个值，那么文档需要一起去做匹配：
//    {
//        "query": {
//        "terms": {
//            "status": [
//                  304,
//                  302
//            ]
//        }
//    }
//    }

}
