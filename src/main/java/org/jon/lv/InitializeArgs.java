package org.jon.lv;

import org.apache.http.HttpHost;
import org.jon.lv.constant.ESEnvironment;
import org.jon.lv.constant.LoaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @Package org.jon.lv.InitializeArgs
 * @Description: 初始化es 参数
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/21 9:04
 * version V1.0.0
 */
public class InitializeArgs {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeArgs.class);

    private String hosts;

    private int connectTimeout = 5000;

    private int socketTimeout = 60000;

    private int maxRetryTimeoutMillis = 60000;

    private InitializeArgs(String hosts, int connectTimeout, int socketTimeout, int maxRetryTimeoutMillis) {
        this.hosts = hosts;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }

    public static InitializeArgs build(){
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

        String hosts =  properties.getProperty(ESEnvironment.HTTP_HOSTS);

        String connectTimeout = properties.getProperty(ESEnvironment.HTTP_CONNECT_TIMEOUT);

        connectTimeout = (connectTimeout == null) ? "5000" : connectTimeout;

        String socketTimeout = properties.getProperty(ESEnvironment.HTTP_SOCKET_TIMEOUT);

        socketTimeout = (socketTimeout == null) ? "60000" : socketTimeout;

        String maxRetryTimeoutMillis = properties.getProperty(ESEnvironment.MAX_RETRY_TIMEOUT);

        maxRetryTimeoutMillis = (maxRetryTimeoutMillis == null) ? "60000" : maxRetryTimeoutMillis;

        return new InitializeArgs(hosts, Integer.valueOf(connectTimeout),
                Integer.valueOf(socketTimeout), Integer.valueOf(maxRetryTimeoutMillis));
    }

    public String getHosts() {
        return hosts;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public int getMaxRetryTimeoutMillis() {
        return maxRetryTimeoutMillis;
    }
}
