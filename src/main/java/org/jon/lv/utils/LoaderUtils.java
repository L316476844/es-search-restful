package org.jon.lv.utils;

import org.apache.log4j.helpers.LogLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @Package org.jon.lv.utils.LoaderUtils
 * @Description: LoaderUtils
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/18 15:25
 * version V1.0.0
 */
public class LoaderUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoaderUtils.class);
    public static URL getResource(String resource) {
        ClassLoader classLoader = null;
        URL url = null;
        try {
            classLoader = LoaderUtils.class.getClassLoader();
            if (classLoader != null) {
                LOGGER.debug("Trying to find [" + resource + "] using " + classLoader + " class loader.");
                url = classLoader.getResource(resource);
                if (url != null) {
                    return url;
                }
            }
        } catch (Exception ex) {
            LOGGER.warn("Caught Exception while in Loader.getResource.", ex);
        }

        LogLog.debug("Trying to find [" + resource + "] using ClassLoader.getSystemResource().");
        return ClassLoader.getSystemResource(resource);
    }


    public static void main(String[] args) {
        URL url = getResource("es_env.properties");
        System.out.println(url);
    }
}
