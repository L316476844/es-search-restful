package org.jon.lv.utils;

/**
 * @Package org.jon.lv.utils.BuildPath
 * @Description: BuildPath
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/29 10:08
 * version V1.0.0
 */
public class BuildPath {

    /**
     * 构建url
     * @param index
     * @param type
     * @param action
     * @return
     */
    public static String build(String index, String type, String action){

        return buildUrl(index, type, action);
    }

    /**
     * 构建请求地址
     * @param args
     * @return
     */
    public static String buildUrl(String ...args){

        if(args == null || args.length == 0) return null;

        StringBuilder sb = new StringBuilder("/");

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if(i < args.length -1){
                sb.append("/");
            }
        }

        return sb.toString();
    }

}
