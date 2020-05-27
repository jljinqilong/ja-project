package com.champlink.common.util.error;

import org.apache.commons.lang3.StringUtils;
import com.champlink.common.util.i18n.MsgUtil;

/**
 * 获取错误信息的工具
 * 
 * @author natyu
 * @date 2018年7月9日 下午1:33:25
 *
 */
public final class ErrorUtil {

    private ErrorUtil() {
    }

    public static String getErrMsg(String errorCode) {
        String errMsg = getProperty(errorCode);
        if (StringUtils.isBlank(errMsg)) {
            // not defined exception
            errMsg = getProperty("10000");
        }

        return errMsg;
    }

    public static String getErrMsg(String errorCode, Object... args) {
        String errMsg = getProperty(errorCode);

        if (StringUtils.isBlank(errMsg)) {
            // not defined exception
            errMsg = getProperty("10000");
        } else {
            errMsg = String.format(errMsg, args);
        }
        return errMsg;
    }

    /**
     * @author natyu
     * @date 2018年7月9日 下午1:55:24
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return MsgUtil.getMsgByKey(key);
    }
}
