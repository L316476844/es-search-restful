package org.jon.lv.constant;

/**
 * @Package org.jon.lv.constant.Constant
 * @Description: Constant
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 17:32
 * version V1.0.0
 */
public interface Constant {
    /** ----------- base ----------------- **/
    String _INDEX = "_index";
    String _TYPE = "_type";
    String _ID = "_id";
    String _ALL = "_all";
    String HIGHLIGHT = "highlight";
    String _SOURCE = "_source";

    /** ----------- action ----------------- **/
    String _SEARCH = "_search";
    String _MGET = "_mget";
    String _BULK = "_bulk";
    String _MAPPING = "_mapping";
    String _DELETE_BY_QUERY = "_delete_by_query";

    /** ----------- query ----------------- **/
    String ID= "id";
    String QUERY = "query";
    String FILTER = "filter";
    String BOOL = "bool";
    String MUST = "must";
    String MUST_NOT = "must_not";
    String MATCH = "match";
    String TERM = "term";
    String FIELDS = "fields";
    String MULTI_MATCH = "multi_match";
    String QUERY_STRING = "query_string"; // "query": "(saerch~1 algorithm~1) AND (grant ingersoll)  OR (tom morton)"
    String SIMPLE_QUERY_STRING = "simple_query_string"; //  +/|/- 分别替换AND/OR/NOT
    String MATCH_PHRASE_PREFIX = "match_phrase_prefix";

    /** ----------- result ----------------- **/
    String HITS = "hits";
}
