package org.jon.lv;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    private HttpHost[] httpHosts;

    private int connectTimeout = 5000;

    private int socketTimeout = 60000;

    private int maxRetryTimeoutMillis = 60000;

    public HttpHost[] getHttpHosts() {
        return httpHosts;
    }

    public void setHttpHosts(HttpHost[] httpHosts) {
        this.httpHosts = httpHosts;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxRetryTimeoutMillis() {
        return maxRetryTimeoutMillis;
    }

    public void setMaxRetryTimeoutMillis(int maxRetryTimeoutMillis) {
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }

    public ESRestClient(){
        super();
    }

    public ESRestClient(HttpHost[] httpHosts){
        this.httpHosts = httpHosts;
    }

    public ESRestClient(HttpHost[] httpHosts, int connectTimeout, int socketTimeout, int maxRetryTimeoutMillis) {
        this.httpHosts = httpHosts;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }

    /**
     * 获取rest client 客户端
     * @return
     */
    public RestClient getRestClient(){
        if(httpHosts == null){
            new RuntimeException("Caught Exception get properties es.http.hosts can not be null");
        }
        if(restClient == null){
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

        return restClient;
    }

    /**
     * 关闭rest client客户端
     */
    public void closeRestClient(){
        if(restClient != null){
            try {
                restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
