package org.jon.lv;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.jon.lv.constant.ESEnvironment;
import org.jon.lv.constant.LoaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

/**
 * @Package org.jon.lv.ESRestClientFactory
 * @Description: ESRestClientFactory
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 15:19
 * version V1.0.0
 */
public class ESRestClientFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESRestClientFactory.class);

    // 单例
    public static RestClient getInstance(){
        return ESRestClientFactory.ESRestClientFactoryHold.INSTANCE.getRestClient();
    }

    // 关闭
    public static void closeRestClient(){
        ESRestClientFactory.ESRestClientFactoryHold.INSTANCE.closeRestClient();
    }

    private static class ESRestClientFactoryHold{
        private static final ESRestClient INSTANCE = new ESRestClient();

        private ESRestClientFactoryHold() {
        }

        static {
            /**
             * 首先加载classpath下的mail-env.properties配置文件，
             * 加载失败则加载工具包内的配置文件
             */
            Properties properties = new Properties();
            URL url = ClassLoader.getSystemResource(ESEnvironment.DEFAULT_CONFIGURATION_FILE);

            if(url == null){
                LOGGER.warn("Please add the email configuration file to class path");
                url = LoaderUtils.getResource(ESEnvironment.DEFAULT_CONFIGURATION_FILE);
            }

            try {
                properties.load(new FileInputStream(url.getFile()));
            } catch (IOException e) {
                LOGGER.error("Caught Exception while in Loader.get properties.", e);
            }

            try {
                String hosts =  properties.getProperty(ESEnvironment.HTTP_HOSTS);
                String[] hostsArr = hosts.split(",");
                HttpHost[] httpHosts = new HttpHost[hostsArr.length];
                for (int i = 0; i < hostsArr.length; i++) {
                    String[] tmp = hostsArr[i].split(":");
                    httpHosts[i]= new HttpHost(tmp[0], Integer.valueOf(tmp[1]), HttpHost.DEFAULT_SCHEME_NAME);
                }
                INSTANCE.setHttpHosts(httpHosts);
            } catch (Exception e) {
                LOGGER.error("Caught Exception get properties es.http.hosts can not be null");
            }

            String connectTimeout = properties.getProperty(ESEnvironment.HTTP_CONNECT_TIMEOUT);
            if(connectTimeout != null){
                INSTANCE.setConnectTimeout(Integer.valueOf(connectTimeout));
            }

            String socketTimeout = properties.getProperty(ESEnvironment.HTTP_SOCKET_TIMEOUT);
            if(socketTimeout != null){
                INSTANCE.setSocketTimeout(Integer.valueOf(socketTimeout));
            }

            String maxRetryTimeoutMillis = properties.getProperty(ESEnvironment.MAX_RETRY_TIMEOUT);
            if(maxRetryTimeoutMillis != null){
                INSTANCE.setMaxRetryTimeoutMillis(Integer.valueOf(maxRetryTimeoutMillis));
            }
        }
    }
}
