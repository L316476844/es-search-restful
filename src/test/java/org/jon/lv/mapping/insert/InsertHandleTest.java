package org.jon.lv.mapping.insert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jon.lv.ESRestClient;
import org.jon.lv.insert.InsertHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package org.jon.lv.mapping.insert.InsertHandleTest
 * @Description: InsertHandleTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 11:26
 * version V1.0.0
 */
public class InsertHandleTest {

    private InsertHandle bulkHandle;

    @Before
    public void init(){
        bulkHandle = new InsertHandle(ESRestClient.getClient());
    }

    @Test
    public void insert() throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("id", 23);
        obj.put("price", 89);
        obj.put("color", "green");
        obj.put("make", "Toyota");
        obj.put("sold", "2014-10-02");
        String json = bulkHandle.insert("cars", "transactions", obj);
        System.out.println("-------------------" + json);
    }


//    POST /library/book/_bulk
//    { "index": { "_id": 1 }}
//    { "title": "Elasticsearch: The Definitive Guide", "authors": ["clinton gormley", "zachary tong"], "summary" : "A distibuted real-time search and analytics engine", "publish_date" : "2015-02-07", "num_reviews": 20, "publisher": "oreilly" }
//    { "index": { "_id": 2 }}
//    { "title": "Taming Text: How to Find, Organize, and Manipulate It", "authors": ["grant ingersoll", "thomas morton", "drew farris"], "summary" : "organize text using approaches such as full-text search, proper name recognition, clustering, tagging, information extraction, and summarization", "publish_date" : "2013-01-24", "num_reviews": 12, "publisher": "manning" }
//    { "index": { "_id": 3 }}
//    { "title": "Elasticsearch in Action", "authors": ["radu gheorge", "matthew lee hinman", "roy russo"], "summary" : "build scalable search applications using Elasticsearch without having to do complex low-level programming or understand advanced data science algorithms", "publish_date" : "2015-12-03", "num_reviews": 18, "publisher": "manning" }
//    { "index": { "_id": 4 }}
//    { "title": "Solr in Action", "authors": ["trey grainger", "timothy potter"], "summary" : "Comprehensive guide to implementing a scalable search engine using Apache Solr", "publish_date" : "2014-04-05", "num_reviews": 23, "publisher": "manning" }

    @Test
    public void batchInsert() throws IOException {
        JSONArray array = new JSONArray();
        for (int i = 10; i < 21; i++) {
            //{ "id":1, "price" : 10000, "color" : "red", "make" : "honda", "sold" : "2014-10-28" }
            JSONObject obj = new JSONObject();
            obj.put("id", i);
            obj.put("price", i * 10000);
            obj.put("color", "red");
            obj.put("make", "honda");
            obj.put("sold", "2014-10-" + i);
            array.add(obj);
        }
        String json = bulkHandle.batchInsert("cars", "transactions", array);
        System.out.println("-------------------" + json);
    }

    @After
    public void close() throws IOException {
        bulkHandle.restClient.close();
    }


}
