package org.jon.lv.bean;

/**
 * @Package org.jon.lv.bean.RangeBean
 * @Description: 范围
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/31 14:16
 * version V1.0.0
 */
public class RangeBean {

    /** 范围值 **/
    private Object value;
    /** 范围 **/
    private Range range;

    public RangeBean(Object value, Range range) {
        this.value = value;
        this.range = range;
    }

    public Object getValue() {
        return value;
    }

    public Range getRange() {
        return range;
    }

    public enum Range{
        // 小于-小于等于-大于-大于等于
        lt,lte,gt,gte;
    }
}
