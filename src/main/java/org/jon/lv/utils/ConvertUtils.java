package org.jon.lv.utils;

import org.jon.lv.bean.RangeBean;
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

    /**
     * 区间范围值转换
     * @param collection
     * @return
     */
    public static String convertRange(Collection<RangeBean> collection){
        if(collection == null || collection.size() == 0) return null;

        StringBuilder sb = new StringBuilder("{");

        Iterator<RangeBean> iterator = collection.iterator();

        while(iterator.hasNext()){
            RangeBean bean = iterator.next();
            sb.append("\"").append(bean.getRange()).append("\":");
            sb.append("\"").append(bean.getValue()).append("\"");
            if(iterator.hasNext()){
                sb.append(",");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * 排序值转换
     * @param sort
     * @return
     */
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
