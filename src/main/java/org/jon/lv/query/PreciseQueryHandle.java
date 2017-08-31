package org.jon.lv.query;

import org.elasticsearch.client.RestClient;
import org.jon.lv.bean.RangeBean;
import org.jon.lv.bean.SortBean;
import org.jon.lv.utils.ConvertUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @Package org.jon.lv.query.PreciseQueryHandle
 * @Description: 精确查询
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/31 11:21
 * version V1.0.0
 */
public class PreciseQueryHandle{

    public QueryHandle queryHandle;

    public PreciseQueryHandle(RestClient restClient, String index, String type) {
        queryHandle = new QueryHandle(restClient, index, type);
    }

    /**
     * terms 精确查找
     * @param docField - 匹配doc的字段
     * @param values - 字段对应的值
     * @param from - 起始拉取位置
     * @param size size - 拉取条数
     * @param rtnFields - 返回字段
     * @param sort - 排序
     * @return
     * @throws IOException
     */
    public String termsQuery(String docField, Set<String> values, int from ,
                             int size, Set<String> rtnFields, SortBean... sort) throws IOException {
        StringBuilder query =  new StringBuilder("{\"query\":{\"terms\":{\"" + docField +"\":" + ConvertUtils.collection2String(values)+"}},");

        if(sort != null) query.append(ConvertUtils.convertSort(sort));
        query.append(",\"from\":" + from +",\"size\":" + size);

        if(rtnFields != null && rtnFields.size() > 0)
        query.append(",\"_source\":").append(ConvertUtils.collection2String(rtnFields));

        query.append("}");

        return queryHandle.query(query.toString());
    }

    /**
     * rang  区间范围精确查找
     * @param docField - 匹配doc的字段
     * @param rangeBeans - 字段对应的区间范围值
     * @param from - 起始拉取位置
     * @param size size - 拉取条数
     * @param rtnFields - 返回字段
     * @param sort - 排序
     * @return
     * @throws IOException
     */
    public String rangeQuery(String docField, Set<RangeBean> rangeBeans, int from,
                             int size, Set<String> rtnFields, SortBean... sort) throws IOException {
        StringBuilder query =  new StringBuilder("{\"query\":{\"range\":{\"" + docField +"\":" + ConvertUtils.convertRange(rangeBeans) +"}},");

        if(sort != null) query.append(ConvertUtils.convertSort(sort));
        query.append(",\"from\":" + from +",\"size\":" + size);

        if(rtnFields != null && rtnFields.size() > 0)
            query.append(",\"_source\":").append(ConvertUtils.collection2String(rtnFields));

        query.append("}");

        return queryHandle.query(query.toString());
    }


//    must:: 查询指定文档一定要被包含。
//    must_not:: 查询指定文档一定不要被包含。
//    should:: 查询指定文档，有则可以为文档相关性加分。 可配置参数 "minimum_should_match" : 1
//    filter:: 与must不同 忽略了评分，并考虑了用于缓存的子句。

    /* bool query应用举例
    POST /library/book/_search
    {
      "query": {
        "bool": {
          "must": [
             { "match_all": {}},
             {"range" : {
              "publish_date" : { "gte" : "2013-01-01", "lte" : "2015-04-05" }
            }}
          ],
         "filter": [
             {"multi_match" :{
                        "query": "Solr",
                        "fields": ["title","summary"]
              }}
          ],
          "must_not": [
            { "term": { "title": "elasticsearch" }},
            { "match": { "authors": "clinton gormley" }}
          ],
          "should": [
             {"multi_match" :{
                        "query": "guide",
                        "fields": ["summary"]
              }}
          ],
          "minimum_should_match" : 1
        }
      }
    }
     */

    public void boolQuery(){

    }
}
