package com.champlink.common.domain.sale.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.champlink.common.domain.BaseBean;

/**
 * 客户联系人 1vN create by barrett in @Date
 **/
public class CustomerContacts extends BaseBean {

    /*
     * 更改次数
     */
    private int modifiction;
    /*
     * 客户ID
     */
    private long customerId;
    /*
     * 姓名
     */
    @NotBlank(message = "联系人姓名不能为空")
    private String name;
    /*
     * 职衔
     */
    private String position;
    /*
     * 手机号码
     */
    private String mobile;
    /*
     * 邮箱
     */
    @Email(message = "联系人邮箱格式错误")
    private String mail;
    /*
     * 固定电话
     */
    private String tel;
    /*
     * 传真
     */
    private String fax;

    public int getModifiction() {
        return this.modifiction;
    }

    public void setModifiction(int modifiction) {
        this.modifiction = modifiction;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
