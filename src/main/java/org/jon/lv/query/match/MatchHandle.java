package org.jon.lv.query.match;

import org.elasticsearch.client.RestClient;
import org.jon.lv.base.BaseHandle;

/**
 * @Package org.jon.lv.query.match.MatchHandle
 * @Description: MatchHandle
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/24 8:58
 * version V1.0.0
 */
public class MatchHandle extends BaseHandle {
    public MatchHandle(RestClient restClient, String index, String type) {
        super(restClient, index, type);
    }


//    {
//        "query": {
//        "match_all": {}
//    },
//        "from": 2,        // 从2条记录开始取
//            "size": 4,        // 取4条数据
//            "sort": {
//                "studentNo": {  // 按studentNo字段升序
//                    "order": "asc"// 降序为desc
//                }
//            }
//    }
}
