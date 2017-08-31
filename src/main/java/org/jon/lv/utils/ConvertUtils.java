package org.jon.lv.utils;

import org.jon.lv.bean.SortBean;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Package org.jon.lv.utils.ConvertUtils
 * @Description: 工具转换类
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/30 10:20
 * version V1.0.0
 */
public class ConvertUtils {

    /**
     * 集合转换成字符串
     * @param collection
     * @return
     */
    public static String collection2String(Collection<String> collection){
        if(collection == null || collection.size() == 0) return null;

        StringBuilder sb = new StringBuilder("[");

        Iterator<String> iterator = collection.iterator();

        while(iterator.hasNext()){
            sb.append("\"").append(iterator.next()).append("\"");
            if(iterator.hasNext()){
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public static String convertSort(SortBean... sort){
        if(sort == null || sort.length == 0) return null;

        StringBuilder sb = new StringBuilder("\"sort\": [");
        for (int i = 0; i < sort.length; i++) {
            SortBean bean = sort[i];
            sb.append("{").
                    append("\"" + bean.getDocFiled() +"\": {\"order\":\"" + bean.getSortOrder() +"\"}").
                    append("}");
            if(i != sort.length - 1){
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
