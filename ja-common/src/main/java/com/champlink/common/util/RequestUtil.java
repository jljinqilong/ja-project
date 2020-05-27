package com.champlink.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * request相关工具
 * 
 * @author natyu
 * @date 2018年7月9日 下午2:10:10
 *
 */
public final class RequestUtil {

    /**
     * 从request中获得可读的参数Map
     * 
     * @author natyu
     * @date 2018年7月9日 下午2:10:20
     * @param request
     * @return
     */
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}
