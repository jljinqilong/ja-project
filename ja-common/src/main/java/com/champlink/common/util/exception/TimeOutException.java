package com.champlink.common.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.champlink.common.util.error.ErrorUtil;

/**
 * 异常类
 * 
 * @author natyu
 * @date 2018年7月9日 下午6:58:22
 *
 */
public class TimeOutException extends RuntimeException {

    private static final long serialVersionUID = 2420387821442827108L;

    private static final String LOGGER_FORMAT = "会话超时";

    private static final String DEFAULT_ERROR_CODE = "10000";

    private static Logger logger = LoggerFactory.getLogger(TimeOutException.class);

    private String errorCode;

    private String errorMsg;

    /**
     * 抛出异常
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:58:31
     * @param code
     * @throws TimeOutException
     */
    public static void create(String errorMsg) throws TimeOutException {
        throw new TimeOutException(errorMsg);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 默认构造，一般用于获取动态方法堆栈
     */
    public TimeOutException() {
        super();
        logger.info("操作执行异常", this);
    }

    /**
     * 根据异常编号构造异常
     * @param errorCode
     */
    public TimeOutException(String errorMsg) {
        super(ErrorUtil.getErrMsg(String.valueOf(errorMsg)));
        this.errorMsg = errorMsg;
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    /**
     * 屏蔽异常堆栈
     */
    public Throwable fillInStackTrace() {
        return null;
    }
}
