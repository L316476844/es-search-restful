package org.jon.lv.bean;

/**
 * @Package org.jon.lv.bean.SortBean
 * @Description: SortBean --排序
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/31 11:29
 * version V1.0.0
 */
public class SortBean {
    /** 排序字段 **/
    private String docFiled;

    private SortOrder sortOrder;

    public SortBean(String docFiled, SortOrder sortOrder) {
        this.docFiled = docFiled;
        this.sortOrder = sortOrder;
    }

    public String getDocFiled() {
        return docFiled;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public enum SortOrder{
        ASC,DESC;
    }
}
