package org.jon.lv.query;

import org.elasticsearch.client.RestClient;
import org.jon.lv.bean.RangeBean;
import org.jon.lv.bean.SortBean;
import org.jon.lv.utils.ConvertUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @Package org.jon.lv.query.GeoQueryHandle - eg:http://blog.csdn.net/bingduanlbd/article/details/52253542
 * @Description: Elasticsearch地理位置总结 - https://www.elastic.co/guide/cn/elasticsearch/guide/current/geoloc.html
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/31 11:21
 * version V1.0.0
 */
public class GeoQueryHandle {

    /*
     geo_distance: 查找距离某个中心点距离在一定范围内的位置
     geo_bounding_box: 查找某个长方形区域内的位置
     geo_distance_range: 查找距离某个中心的距离在min和max之间的位置---(5.0之后废弃)使用距离聚合或排序。
     geo_polygon: 查找位于多边形内的地点。
     */
    public QueryHandle queryHandle;

    public GeoQueryHandle(RestClient restClient, String index, String type) {
        queryHandle = new QueryHandle(restClient, index, type);
    }

    /* 设置mapping 注意-5.4版本不支持删除具体索引映射（delete /cities/city/_mapping） 只能直接删除索引重建(delete /cities)
    PUT  cities
    {
        "mappings": {
            "city": {
                "properties": {
                    "city": {"type": "keyword"},
                    "state": {"type": "keyword"},
                    "location": {"type": "geo_point"}
                }
            }
        }
    }
     */

    /* 批量新增测试数据
    POST /cities/city/_bulk
    { "index": { "_id": 1 }}
    {"city": "Beijing", "state": "BJ","location": {"lat": "39.91667", "lon": "116.41667"}}
    { "index": { "_id": 2 }}
    {"city": "Shanghai", "state": "SH","location": {"lat": "34.50000", "lon": "121.43333"}}
    { "index": { "_id": 3 }}
    {"city": "Xiamen", "state": "FJ","location": {"lat": "24.46667", "lon": "118.10000"}}
    { "index": { "_id": 4 }}
    {"city": "Fuzhou", "state": "FJ","location": {"lat": "26.08333", "lon": "119.30000"}}
    { "index": { "_id": 5 }}
    {"city": "Guangzhou", "state": "GD","location": {"lat": "23.16667", "lon": "113.23333"}}
     */

    /* ES 5.4  查找距离某个中心点距离在一定范围内的位置
    POST /cities/city/_search
    {
      "query":{
          "bool": {
              "filter":{
                  "geo_distance": {
                     "distance": "3km",
                     "location": {
                       "lat":34.50000,
                       "lon":121.45333
                     }
                  }
              }
          }
      }
    }
     */


    /* ES 5.5  矩形范围内的点(top_left, bottom_right)|(top_right,bottom_left)
    POST /cities/city/_search
    {
      "query":{
          "bool": {
              "must" : {
                    "match_all" : {}
                },
              "filter":{
                 "geo_bounding_box": {
                    "location": {
                       "top_left": {
                          "lat":35.339461,
                           "lon":120.97017
                       },
                       "bottom_right": {
                          "lat":30.733494,
                           "lon":122.117701
                       }
                    }
                 }
              }
          }
      }
    }
     */

    /* ES 5.5 多边形中出现的命中的点查询
    POST /cities/city/_search
    {
      "query":{
          "bool": {
              "filter":{
                  "geo_polygon": {
                      "location": {
                          "points" : [
                             {
                               "lat":34.50000,
                               "lon": 121.45333
                             },
                             {
                               "lat":30.733494,
                               "lon":120.97017
                             },
                             {
                               "lat":31.733494,
                               "lon":120.97017
                             }
                         ]
                     }
                  }
              }
          }
      }
    }
     */

    /* es5.4 Geo Distance Aggregation  聚合函数距离范围内数量
    POST /cities/city/_search
    {
        "aggs" : {
            "rings" : {
                "geo_distance" : {
                    "field" : "location",
                    "origin" : "34.50000,121.45333",
                    "unit" : "km",
                    "ranges" : [
                        {"to":800},
                        { "from" : 1, "to" : 3 },
                        {"from":1000}
                    ]
                }
            }
        }
    }
     */
}
