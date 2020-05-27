package com.champlink.common.advice;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.exception.TimeOutException;

/**
 * 异常切面
 * @author fishcat
 * @date 2018/8/7 下午4:37
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    // 会话过期返回的状态
    private static final int SESSION_INVALID_STATUS = 499;

    /**
     * 400 缺少请求参数
     * @param e MissingServletRequestParameterException
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        LOGGER.error("缺少请求参数", e);
        return createJson(HttpStatus.BAD_REQUEST.value(), "缺少请求参数");
    }

    /**
     * 400 参数解析失败
     * @param e HttpMessageNotReadableException
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        LOGGER.error("参数解析失败", e);
        return createJson(HttpStatus.BAD_REQUEST.value(), "参数解析失败");
    }

    /**
     * 400 参数验证失败 <pre> 使用 @Validated 验证的情况，用于 @PathVariable 对路径参数直接验证等场景
     * 非官方JSR-303规范，是Spring实现的变体JSR-303规范，有很多的特性，比如分组验证，跨参数验证等等，这些都是比 @Valid 有优势的 </pre>
     * @param e ConstraintViolationException
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleServiceException(ConstraintViolationException e, HttpServletRequest request) {
        LOGGER.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = "parameter:" + violation.getMessage();
        return createJson(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 参数验证失败 <pre> 使用 @Valid 对 Bean 对象中的属性校验（JSR-303 规范） </pre>
     * @param e MethodArgumentNotValidException
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        LOGGER.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return createJson(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 参数绑定失败 <pre> 请求参数绑定失败 </pre>
     * @param e BindException
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException e, HttpServletRequest request) {
        LOGGER.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return createJson(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 500 服务运行异常
     * @param e AppException
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AppException.class)
    public String handleAppException(AppException e, HttpServletRequest request) {
        LOGGER.error(">>> AppException: ", e);
        return createJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getErrorMsg());
    }

    /**
     * 500 系统运行异常
     * @param e Exception
     * @param request HttpServletRequest
     * @return Error Response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {
        LOGGER.error(">>> Exception: ", e);
        return createJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 此处为功能说明
     * 
     * @author natyu
     * @date 2018年9月29日 上午10:10:46
     * @param e
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(TimeOutException.class)
    public String handleException(TimeOutException e, HttpServletRequest request) {
        LOGGER.error(">>> TimeOutException: ", e);
        return createJson(HttpStatus.GATEWAY_TIMEOUT.value(), e.getMessage());
    }

    private String createJson(int code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }
}
