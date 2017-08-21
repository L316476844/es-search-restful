package org.jon.lv;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;

/**
 * @Package org.jon.lv.ESRestClient
 * @Description: ESRestClient
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 15:09
 * version V1.0.0
 */
public class ESRestClient {

    private static RestClient restClient = null;

    private static final int connectTimeout = 5000;

    private static final int socketTimeout = 60000;

    private static final int maxRetryTimeoutMillis = 60000;

    /**
     * 获取TCP 客户端
     *
     * @return
     */
    public static synchronized RestClient getClient() {
        if (restClient == null) {
            InitializeArgs initializeArgs = InitializeArgs.build();
            build(initializeArgs.getHosts(), initializeArgs.getConnectTimeout(),
                    initializeArgs.getSocketTimeout(), initializeArgs.getMaxRetryTimeoutMillis());
        }
        return restClient;
    }

    /**
     * 获取TCP 客户端
     *
     * @return
     */
    public static synchronized RestClient getClient(String hosts, int connectTimeout,
                                                    int socketTimeout, int maxRetryTimeoutMillis) {
        if (restClient == null) {
            build(hosts, connectTimeout,
                    socketTimeout, maxRetryTimeoutMillis);
        }
        return restClient;
    }


    /**
     * 获取TCP 客户端
     *
     * @return
     */
    public static synchronized RestClient getClient(String hosts) {
        if (restClient == null) {
            build(hosts, connectTimeout, socketTimeout, maxRetryTimeoutMillis);
        }
        return restClient;
    }

    /**
     * 关闭客户端
     */
    public static void close(RestClient client){
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建立连接
     * @return
     */
    private static void build(String hosts, final int connectTimeout, final int socketTimeout,
                              final int maxRetryTimeoutMillis) {

        String[] hostsArr = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostsArr.length];
        for (int i = 0; i < hostsArr.length; i++) {
            String[] tmp = hostsArr[i].split(":");
            httpHosts[i] = new HttpHost(tmp[0], Integer.valueOf(tmp[1]), HttpHost.DEFAULT_SCHEME_NAME);
        }
        restClient = RestClient.builder(httpHosts)
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(connectTimeout)
                                .setSocketTimeout(socketTimeout);
                    }
                })
                .setMaxRetryTimeoutMillis(maxRetryTimeoutMillis)
                .build();
    }
}
