package com.champlink.common.util.i18n;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import com.champlink.common.util.context.ContextUtils;

@Component
public class MsgUtil {

    @Autowired
    private MessageSource messageSource;

    /** 工具类私有构造 */
    public MsgUtil() {
    }

    /**
     * 根据key获取资源文件中的消息
     * 
     * @author natyu
     * @date 2018年7月9日 下午1:55:35
     * @param key
     * @return
     */
    public static String getMsgByKey(long key) {
        return getMsgByKey(String.valueOf(key));
    }

    /**
     * 根据key获取资源文件中的消息
     * 
     * @author natyu
     * @date 2018年7月9日 下午1:54:40
     * @param i18nKey
     * @return
     */
    public static String getMsgByKey(String i18nKey) {
        return getMsgUtil().messageSource.getMessage(i18nKey, null, i18nKey, LocaleContextHolder.getLocale());
    }

    /**
     * 根据key和参数获取信息
     * 
     * @author natyu
     * @date 2018年7月9日 下午1:54:34
     * @param i18nKey
     * @param param
     * @return
     */
    public static String getMsgWithParam(String i18nKey, String param) {
        return getMsgUtil().messageSource.getMessage(i18nKey, new Object[] {param}, i18nKey, LocaleContextHolder.getLocale());
    }

    public static String getMsgWithParams(String i18nKey, List<String> params) {
        int size = params.size();
        Object[] objs = new Object[size];
        for (int i = 0; i > size; i++) {
            objs[i] = params.get(i);
        }
        return getMsgUtil().messageSource.getMessage(i18nKey, objs, i18nKey, LocaleContextHolder.getLocale());
    }

    public static MsgUtil getMsgUtil() {
        System.err.println(ContextUtils.getApplicationContext());
        System.err.println(ContextUtils.getApplicationContext().getBean("msgUtil"));
        return (MsgUtil) ContextUtils.getApplicationContext().getBean("msgUtil");
    }

}
