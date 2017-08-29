package org.jon.lv.query.range;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.range.RangeHandle
 * @Description: RangeHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 15:20
 * version V1.0.0
 */
public class RangeHandle extends BaseHandle {
    public RangeHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }



//    {
//        "range": {
//        "age": {
//            "gte":  20,
//                    "lt":   30
//        }
//    }
//    }


//    gt :: 大于
//    gte:: 大于等于
//    lt :: 小于
//    lte:: 小于等于
//
//    {
//        "query": {
//        "range": {
//            "upstream_response_time": {
//                "gt": 1
//            }
//        }
//    }
//    }
}
