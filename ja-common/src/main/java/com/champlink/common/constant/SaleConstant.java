package com.champlink.common.constant;

public class SaleConstant {

    
    // 询价编码前缀
    public final static String INQUIRY_PREFIX = "JA";
    
    /**
     * 启用
     */
    public final static int ENABLED_FLAG_VALID = 0;
    /**
     * 禁用
     */
    public final static int DISABLED_FLAG_INVALID = 1;

    /**
     * 询价/询单/未确认状态
     * @Author created by barrett in 18-8-10 上午9:50
     */
    public final static int UNCONFIRMED = 1;
    /**
     * 确认：询价/询单/评审
     * @Author created by barrett in 18-8-9 下午8:18
     */
    public final static int CONFIRM_INQUIRY = 2;
    /**
     * 询价转询单/询单转合同/询单评审转合同
     * @Author created by barrett in 18-8-9 下午8:19
     */
    public final static int TURN_INQUIRIES = 3;
    /**
     * [询单/询价] 关闭状态
     * @Author created by barrett in 2018/8/13 15:33
     */
    public final static int INQUIRY_CLOSE = 4;
    /**
     * 询单已确认待评审
     * @Author created by barrett in 2018/8/13 15:46
     */
    public final static int INQUIRY_APPRAISAL = 5;
    /*
     * 询单评审通过
     */
    public final static int INQUIRY_APPRAISAL_PASS = 6;
    /**
     * 询单评审驳回
     * @Author created by barrett in 2018/8/24 09:39
     */
    public final static int INQUIRY_APPRAISAL_REJECT = 7;
    /**
     * 询单确认待补全状态
     * @Author created by barrett in 2018/8/24 09:39
     */
    public final static int INQUIRY_APPRAISAL_SUPPLEMENT = 8;
    /**
     * 询单信息已补齐待转评审
     * @Author created by barrett in 2018/8/27 11:50
     */
    public final static int INQUIRY_APPRAISAL_WAIT = 9;

    /**
     * 评审规则 key
     */
    public final static String REVIEW_RULES = "REVIEW_RULES";

    /**
     * 评审规则的功率 code
     * @Author created by barrett in 2018/8/27 09:54
     */
    public final static String REVIEW_RULES_POWER = "power";
    public final static String REVIEW_RULES_PRODUCT = "product";

    /**
     * 父级id, 用于指定首次加载的一级菜单
     * @Author created by barrett in 2018/8/30 10:07
     */
    public final static Long COUNTRY_ID = 1l;
}
