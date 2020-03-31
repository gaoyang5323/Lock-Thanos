package com.kakuiwong.lockthanos.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class LockThanosLogUtil {

    private static final Logger LOG = LoggerFactory.getLogger("com.kakuiwong.lockthanos");

    public static void warning(String log, Throwable e) {
        if (LOG.isWarnEnabled()) {
            LOG.warn(log, e);
        }
    }
}
