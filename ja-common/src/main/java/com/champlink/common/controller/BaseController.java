package com.champlink.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.BaseBean;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.jms.ActiveMQService;

public class BaseController {

    /**
     * 日志组件
     */
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MessageSource messageSource; // 国际化

    @Autowired
    protected RedisService redisService; // Redis缓存

    @Autowired
    protected ActiveMQService activeMQService; // 消息队列

    /**
     * 返回成功信息
     *
     * @return
     */
    protected String getSuccessJson() {
        return getSuccessJson(null);
    }

    /**
     * 返回成功信息
     *
     * @param object
     * @return
     */
    protected String getSuccessJson(Object object) {
        return createJson(200, getMessage("success"), object).toJSONString();
    }

    /**
     * 返回错误信息
     *
     * @return
     */
    protected String getFailJson() {
        return getFailJson(null);
    }

    /**
     * 返回失败信息
     *
     * @param object
     * @return
     */
    protected String getFailJson(Object object) {
        return createJson(400, getMessage("fail"), object).toJSONString();
    }

    /**
     * 返回错误信息
     *
     * @return
     */
    protected String getErrorJson() {
        return getErrorJson(null);
    }

    /**
     * 返回错误信息
     *
     * @param object
     * @return
     */
    protected String getErrorJson(Object object) {
        return createJson(500, getMessage("error"), object).toJSONString();
    }

    /**
     * 返回自定义消息
     *
     * @param code
     * @param msg
     * @return
     */
    protected String getCustomJson(int code, String msg) {
        return createJson(code, getMessage(msg), null).toJSONString();
    }

    /**
     * 返回自定义消息
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    protected String getCustomJson(int code, String msg, Object data) {
        return createJson(code, getMessage(msg), data).toJSONString();
    }

    /**
     * 生成JSON
     *
     * @param code
     * @param msg
     * @param object
     * @return
     */
    protected JSONObject createJson(int code, String msg, Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        if (object != null) {
            jsonObject.put("data", object);
        }
        return jsonObject;
    }

    /**
     * 获取国际化消息
     *
     * @param i18nKey
     * @return
     */
    private String getMessage(String i18nKey) {
        return messageSource.getMessage(i18nKey, null, i18nKey, LocaleContextHolder.getLocale());
    }

    /**
     * 导入成功
     *
     * @param response
     * @throws IOException
     * @author natyu
     * @date 2016年3月29日 上午11:23:59
     */
    protected void responseMsg(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("result", "ok");
        json.put("code", 200);
        json.put("msg", "操作成功");
        writer.write(json.toJSONString());
        writer.flush();
    }

    /**
     * 导入数据中有不规范数据
     *
     * @param response
     * @throws IOException
     * @author natyu
     * @date 2016年3月29日 上午11:24:09
     */
    protected void responseExcel(HttpServletResponse response, Long redisKey) throws IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("result", "ok");
        json.put("code", 205);
        json.put("redisKey", redisKey);
        json.put("msg", "有部分异常数据导入失败");
        writer.write(json.toJSONString());
        writer.flush();
    }

    /**
     * @param sourceList
     * @param diretory
     * @param idToNameColumns idColumn,idColumn,nameColumn;nameColumn
     * @return
     */
    protected <T extends BaseBean, R> List<T> translateIdToName(List<T> sourceList, List<R> dictionary, String[] idToNameColumns) {
        try {
            for (T t : sourceList) {
                translateIdToNameObj(t, dictionary, idToNameColumns);

//                for (R r : dictionary) {
//
//
//                    for (String ids : idToNameColumns) {
//                        try {
//                            String[] strs = ids.split(",");
//                            if (strs.length != 3) {
//                                break;
//                            }
//                            Method tmgId = t.getClass().getMethod("get" + strs[0].substring(0, 1).toUpperCase() + strs[0].substring(1));
//                            Object to = tmgId.invoke(t);
//                            Method rmgId = r.getClass().getMethod("get" + strs[1].substring(0, 1).toUpperCase() + strs[1].substring(1));
//                            Object ro = rmgId.invoke(r);
//                            if (to.equals(ro)) {
//                                Method tmg = t.getClass().getMethod("getTransNames");
//                                HashMap<String, Object> transNames = (HashMap<String, Object>) tmg.invoke(t);
//                                if (transNames == null) {
//                                    transNames = new HashMap<>();
//                                }
//                                String[] names = strs[2].split(";");
//                                for (String name : names) {
//                                    try {
//                                        Method rmg = r.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
//                                        transNames.put(strs[0] + "_" + name, rmg.invoke(r));
//                                        Method tms = t.getClass().getMethod("setTransNames", HashMap.class);
//                                        tms.invoke(t, transNames);
//                                    } catch (Exception e) {
//                                    }
//                                }
//                                break;
//                            }
//                        } catch (Exception e) {
//
//                        }
//                    }
//                }
            }
            return sourceList;
        } catch (Exception e) {
            log.error(e.getMessage());
            return sourceList;
        }
    }

    /**
     * 根据id转name(一个对象)
     * @Author created by barrett in 18-8-7 下午2:53
     * @Param
     * @return
     */
    protected <T extends BaseBean, R> Object translateIdToNameObj(Object obj, List<R> dictionary, String[] idToNameColumns) {
        try {
            Method tmg = obj.getClass().getMethod("getTransNames");
            HashMap<String, Object> transNames = (HashMap<String, Object>) tmg.invoke(obj);
            if (transNames == null) {
                transNames = new HashMap<>();
            }

            for (String ids : idToNameColumns) {
                for (R r : dictionary) {
                    try {
                        String[] strs = ids.split(",");
                        if (strs.length != 3) {
                            break;
                        }
                        Method tmgId = obj.getClass().getMethod("get" + strs[0].substring(0, 1).toUpperCase() + strs[0].substring(1));
                        Object to = tmgId.invoke(obj);
                        if(to == null){
                            break;
                        }
                        Method rmgId = r.getClass().getMethod("get" + strs[1].substring(0, 1).toUpperCase() + strs[1].substring(1));
                        Object ro = rmgId.invoke(r);
                        if (to.equals(ro)) {
                            String[] names = strs[2].split(";");
                            for (String name : names) {
                                try {
                                    Method rmg = r.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                                    transNames.put(strs[0] + "_" + name, rmg.invoke(r));
                                } catch (Exception e) {
                                }
                            }
                            break;
                        }
                    } catch (Exception e) {
                    }
                }
            }

            if (!transNames.isEmpty()) {
                Method tms = obj.getClass().getMethod("setTransNames", HashMap.class);
                tms.invoke(obj, transNames);
            }
            return obj;
        } catch (Exception e) {
            log.error(e.getMessage());
            return obj;
        }
    }

}
