package com.champlink.common.util.method;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.sale.inquiry.InquiryRecordDetail;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.User;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.web.ctx.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 可公共调用的方法
 * @Author created by barrett in 18-8-7 下午2:41
 * @Param
 * @return
 */
public class MethodUtil {

    private static Logger logger = LoggerFactory.getLogger(MethodUtil.class);

    @Autowired
    private RedisService redisService;
    /**
     * 根据id获取name(call)
     * @Author created by barrett in 18-8-7 下午2:29
     * @Param
     * @return
     */
    public static Object getObject(String strName,String json){
        Object obj = null;

        try {
            obj = Class.forName(strName).newInstance();

            if (json != null) {
                JSONObject parseObject = JSONObject.parseObject(json);
                if ((Integer) parseObject.get("code") == 200) {
                    String data = parseObject.getString("data");
                    JSONObject testObejct = JSONObject.parseObject(data);
                    //将json对象转换为java对象
                    obj = JSONObject.toJavaObject(testObejct, obj.getClass());
                }else{
                    logger.error("service call -> id转name 服务调用数据为空或异常, json: " + json);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * @Author jsl
     * @Date 根据对象获取员工id 2018/8/10
     * @Description    
     **/
    public static Long getStaffRowId(String baseInfo){

        Long createdBy = null;
        if (baseInfo != null) {
            JSONObject parseObject = JSONObject.parseObject(baseInfo);
            if ((Integer) parseObject.get("code") == 200) {
                BaseInfo bi = JSONObject.parseObject(parseObject.getString("data"),BaseInfo.class);
                createdBy = bi.getRowId();
            }
        }
        return createdBy;
    }

    /**
     * @Author jsl
     * @Date 根据对象获取员工名 2018/8/10
     * @Description
     **/
    public static String getStaffName(String baseInfo){

        String staffName = null;
        if (baseInfo != null) {
            JSONObject parseObject = JSONObject.parseObject(baseInfo);
            if ((Integer) parseObject.get("code") == 200) {
                BaseInfo bi = JSONObject.parseObject(parseObject.getString("data"),BaseInfo.class);
                staffName = bi.getStaffName();
            }
        }
        return staffName;
    }
}
