package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

/**
 * @项目名称：Seraphim
 * @author mjzhang 2014-10-10
 * @类描述：SYS_USER表对应实体User
 */
public class User extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 超级管理员
     */
    public final static String USER_CATEGORY_ADMIN = "9";

    /**
     * 内部用户
     */
    public final static String USER_CATEGORY_INTERNAL = "0";

    /**
     * 外部用户
     */
    public final static String USER_CATEGORY_EXTERNAL = "1";

    /**
     * 供应商用户
     */
    public final static String USER_CATEGORY_SUPPLIER = "2";

    /** 登陆用户ID */
    private String userName;

    /** 登陆密码 */
    private String password;

    /** 用户类型 */
    private String category;

    /** 邮件地址 */
    private String email;

    /** 手机号 */
    private String cellphoneNo;

    /** 照片 */
    private String photo;

    public User() {
    }

    public User(String userName) {
        super();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
