package com.champlink.common.util.exception;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.champlink.common.util.error.ErrorUtil;
import com.champlink.common.util.i18n.MsgUtil;

/**
 * 异常类
 * 
 * @author natyu
 * @date 2018年7月9日 下午6:58:22
 *
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -6202759933628287239L;

    private static final String LOGGER_FORMAT = "操作执行异常：异常编码[%s],异常信息：[%s]";

    private static final String DEFAULT_ERROR_CODE = "10000";

    public static final long INSERT_ERROR_CODE = 100000;
    public static final long UPDATE_ERROR_CODE = 100001;
    public static final long DELETE_ERROR_CODE = 100002;
    public static final long SEARCH_ERROR_CODE = 100003;
    public static final long CONSTRAINT_ERROR_CODE = 110001;

    public static final long PARAMS_VERIFY_ERROR_CODE = 100004;

    private static Logger logger = LoggerFactory.getLogger(AppException.class);

    private String errorCode;

    private String errorMsg;

    /**
     * 抛出异常
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:58:31
     * @param code
     * @throws AppException
     */
    public static void create(long code) throws AppException {
        throw new AppException(code);
    }

    /**
     * 抛出异常
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:58:44
     * @param code
     * @param params
     * @throws AppException
     */
    public static void create(long code, String params) throws AppException {
        if (null == params) {
            throw new AppException(code);
        }
        throw new AppException(code, params);
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
    public AppException() {
        super();
        logger.info("操作执行异常", this);
    }

    /**
     * 根据异常信息构造异常
     * @param message
     */
    public AppException(String message) {
        super(message);
        this.errorMsg = message;
        this.errorCode = DEFAULT_ERROR_CODE;
        logger.info(String.format(LOGGER_FORMAT, DEFAULT_ERROR_CODE, message));
    }

    /**
     * 根据异常编号构造异常
     * @param errorCode
     */
    public AppException(long errorCode) {
        super(ErrorUtil.getErrMsg(String.valueOf(errorCode)));
        this.errorCode = String.valueOf(errorCode);
        this.errorMsg = ErrorUtil.getErrMsg(this.errorCode);
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    /**
     * 根据异常编号和参数构造异常
     * @param errorCode
     * @param 参数
     */
    public AppException(long errorCode, String params) {
        super(MsgUtil.getMsgWithParams(String.valueOf(errorCode), Arrays.asList(params.split(","))));
        String[] tparams = params.split(",");
        List<String> paramList = Arrays.asList(tparams);
        this.errorCode = String.valueOf(errorCode);
        this.errorMsg = MsgUtil.getMsgWithParams(this.errorCode, paramList);
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    /**
     * 根据异常编号和参数构造异常
     * @param errorCode
     * @param 参数
     */
    public AppException(long errorCode, List<String> params) {
        super(MsgUtil.getMsgWithParams(String.valueOf(errorCode), params));
        this.errorCode = String.valueOf(errorCode);
        this.errorMsg = MsgUtil.getMsgWithParams(this.errorCode, params);
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    /**
     * 根据异常信息和异常构造异常
     * @param msg
     * @param e
     */
    public AppException(String msg, Exception e) {
        super(msg, e);
        this.errorMsg = msg;
        this.errorCode = "10000";
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));

    }

    public AppException(String message, String errorCode) {
        super(message);
        this.errorMsg = ErrorUtil.getErrMsg(errorCode);
        this.errorCode = errorCode;
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    public AppException(Exception e, String errorCode) {
        super(e);
        this.errorCode = errorCode;
        this.errorMsg = ErrorUtil.getErrMsg(errorCode);
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    public AppException(Exception e) {
        super(e);
        this.errorCode = "10000";
        this.errorMsg = ErrorUtil.getErrMsg(errorCode);
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    public AppException(String message, Exception e, String errorCode) {
        super(message, e);
        this.errorCode = errorCode;
        this.errorMsg = ErrorUtil.getErrMsg(errorCode);
        logger.info(String.format(LOGGER_FORMAT, this.errorCode, this.errorMsg));
    }

    /**
     * 屏蔽异常堆栈
     */
    public Throwable fillInStackTrace() {
        return null;
    }
}
