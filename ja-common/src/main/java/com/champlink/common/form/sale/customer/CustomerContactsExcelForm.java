package com.champlink.common.form.sale.customer;

import com.champlink.common.domain.BaseBean;
import com.champlink.common.util.annotations.Excel;

/**
 * 客户联系人模板
 * 
 **/
public class CustomerContactsExcelForm extends BaseBean{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 序列
	 */
	@Excel(title = "序列", isNull = false)
	private Integer order;
	
	/**
     * 客户姓名
     */
    @Excel(title = "客户姓名", isNull = false)
    private String customeName;
    /**
     * 联系人姓名
     */
    @Excel(title = "联系人姓名", isNull = false)
    private String name;
    /**
     * 职位
     */
	@Excel(title = "职位")
    private String position;
    /**
     * 手机号码
     */
	@Excel(title = "手机号码")
    private String mobile;
    /**
     * 邮箱
     */
	@Excel(title = "邮箱")
    private String mail;
    /**
     * 固定电话
     */
	@Excel(title = "固定电话")
    private String tel;
  
	public String getCustomeName() {
		return customeName;
	}
	public void setCustomeName(String customeName) {
		this.customeName = customeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
    
}
