package com.champlink.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 */
public class NativeUtil {
    //    private static final Logger logger = LogUtil.createLog();

    private NativeUtil() {
    }

    /**
     * 获取机器名
     */
    public static String getHostName() {
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            //            logger.error(e.getMessage(), e);
        }
        return hostName;
    }

    /**
     * 获取本机ip地址
     */
    public static final String getHostIp() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //            logger.error(e.getMessage(), e);
        }
        return ip;
    }
}
