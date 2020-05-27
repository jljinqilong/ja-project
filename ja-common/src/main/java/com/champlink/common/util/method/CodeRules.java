package com.champlink.common.util.method;

import com.champlink.common.constant.SaleConstant;
import com.champlink.common.util.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 编码规则
 * @Author created by barrett in 18-8-6 下午7:25
 * @Param
 * @return
 */
public class CodeRules {

    /**
     * 获取询价编号规则
     * @Author created by barrett in 18-8-6 下午7:30
     * @Param
     * @return
     */
    public static String getInguiryCode(String prefix,int flow){
        prefix += getNewFlow(flow);
        return prefix;
    }

    /**
     * 获取编号前缀信息
     * @Author created by barrett in 18-8-7 下午8:16
     * @Param
     * @return
     */
    public static String getInguiryCodePrefix(){
        String codeNO = SaleConstant.INQUIRY_PREFIX;
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
        java.util.Date dd = Calendar.getInstance().getTime();
        String date = sdf.format(dd);
        codeNO += date;
        return codeNO;
    }

    /**
     * 生成新的编号
     * @Author created by barrett in 18-8-7 下午8:09
     * @Param
     * @return
     */
    public static String getNewFlow(int flow){
        //判断今天是否生成
        String newFlow = "";
        flow++;
        if(flow < 10){
            newFlow = "00"+flow;
        }else if(flow < 100){
            newFlow = "0"+flow;

        }else if(flow < 1000){
            newFlow = ""+flow;
        }else{
            AppException.create(300002);
        }
        return newFlow;
    }

}
