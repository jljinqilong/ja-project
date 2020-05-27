package com.champlink.common.constant;

public class Constant {

    public final static String ADMIN = "admin";

    /**
     * ERP会话过期时间（分钟）
     */
    public final static int sessionTimeout = 180;

    /**
     * 会话key开头
     */
    public final static String sessionKeyStart = "login_";

    /**
     * 当前登录人数据权限staffId
     */
    public final static String dataAuthStaff = "data_auth_staff_";

    /**
     * 当前登录人数据权限deptId
     */
    public final static String dataAuthDept = "data_auth_dept_";

    /**
     * 有效
     */
    public final static int DEL_FLAG_VALID = 0;
    /**
     * 无效
     */
    public final static int DEL_FLAG_INVALID = 1;

    /**
     * 资源列表，缓存变量名
     */
    public final static String CACHE_NAME_ALL_RESOURCE_TREE = "cache_name_all_resource_tree";
    /**
     * 用户资源树，缓存变量名
     */
    public final static String CACHE_NAME_USER_RESOURCE_TREE = "cache_name_user_resource_tree_";
    /**
     * 用户资源列表，缓存变量名
     */
    public final static String CACHE_NAME_USER_RESOURCE_LIST = "cache_name_user_resource_list_";
    /**
     * 角色资源树，缓存变量名
     */
    public final static String CACHE_NAME_ROLE_RESOURCE_TREE = "cache_name_role_resource_tree_";
    /**
     * 用户菜单树，缓存变量名
     */
    public final static String CACHE_NAME_MENU_RESOURCE_TREE = "cache_name_menu_resource_tree_";
    /**
     * 英文
     */
    public final static String EN = "en_US";
    /**
     * 中文
     */
    public final static String ZH = "zh_CN";

    /**
     * 资源菜单叶子节点
     */
    public final static int RESOURCE_TYPE_IS_LEAF = 2;

    /**
     * 资源菜单叶子节点
     */
    public final static int RESOURCE_TYPE_IS_LEAF_MENU = 1;
    /**
     * 资源菜单非叶子节点
     */
    public final static int RESOURCE_TYPE_NOT_LEAF_MENU = 0;

    /**
     * id重复或者id不存在
     */
    public final static boolean RepeatOrAbsent = true;

    /**
     * 添加操作
     */
    public final static String OPT_ADD = "add";
    /**
     * 修改操作
     */
    public final static String OPT_UPDATE = "update";
    /**
     * 解除合同/协议
     */
    public final static String OPT_RELIEVE = "relieve";
    /**
     * 终止合同/协议
     */
    public final static String OPT_END = "end";
    /**
     * 中止合同/协议
     */
    public final static String OPT_DISCONTINUE = "discontinue";
    /**
     * 续签合同/协议
     */
    public final static String OPT_RENEW = "renew";
    /**
     * 删除操作
     */
    public final static String OPT_DEL = "del";

    public final static String APP_CODE_ORG = "org";
    public final static String APP_CODE_STAFF = "staff";
    public final static String APP_CODE_SYSTEM = "system";

    public final static String APP_CODE_STAFF_CONTRACT = "staff_contract";
    public final static String APP_CODE_STAFF_AGREEMENT = "staff_agreement";

    /**
     * 员工导入异常数据，导出
     */
    public final static String ERROR_IMPORT_STAFF_BASE_INFO = "error_import_staff_base_info";

    /**
     * 批量处理离职异动异常数据，导出
     */
    public final static String ERROR_IMPORT_ADJUSTMENTWORK = "error_import_adjustmentWork";

    /**
     * 职衔导入异常数据
     */
    public final static String ERROR_IMPORT_POSITION = "error_import_position";
    /**
     * 组织架构导入异常数据，导出
     */
    public final static String ERROR_IMPORT_ORG_INFO = "error_import_org_info";

    /**
     * 公积金导入异常数据，导出
     */
    public final static String ERROR_IMPORT_ACCUMULATION_INFO = "error_import_accumulation_info";

    /**
     * 内部调动
     * 
     */
    public final static String INNER_MOBILIZATION = "INNER_MOBILIZATION";
    /**
     * 借调
     * 
     */
    public final static String TEMPORARILY = "TEMPORARILY";
    /**
     * 外派
     * 
     */
    public final static String EXPATRIATE = "EXPATRIATE";
    /**
     * 退休
     * 
     */
    public final static String RETIRE = "RETIRE";
    /**
     * 离职
     * 
     */
    public final static String DIMISSION = "DIMISSION";
    /**
     * 重新雇佣
     * 
     */
    public final static String REHIRE = "REHIRE";
    /**
     * 返聘
     * 
     */
    public final static String RETURN_REHIRE = "RETURN_REHIRE";

}
