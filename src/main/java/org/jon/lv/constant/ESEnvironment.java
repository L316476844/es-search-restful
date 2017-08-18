package org.jon.lv.constant;

/**
 * @Package org.jon.lv.constant.ESEnvironment
 * @Description: ESEnvironment
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 15:21
 * version V1.0.0
 */
public interface ESEnvironment {

    // 默认环境配置
    String DEFAULT_CONFIGURATION_FILE = "es_env.properties";

    // es 配置 hosts 多个用逗号分隔
    String HTTP_HOSTS = "es.http.hosts";

    // http链接超时
    String HTTP_CONNECT_TIMEOUT= "es.http.connect.timeout";

    // http socket 超时
    String HTTP_SOCKET_TIMEOUT= "es.http.socket.timeout";

    // http MaxRetryTimeoutMillis 超时
    String MAX_RETRY_TIMEOUT= "es.http.retry.timeout";
}
