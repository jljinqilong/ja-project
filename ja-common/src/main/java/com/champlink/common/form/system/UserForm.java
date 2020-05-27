package com.champlink.common.form.system;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.champlink.common.form.BaseForm;

public class UserForm extends BaseForm {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 确认密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
    
    private List<String> staffNoList;

    public void setUserName(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            userName = userName.trim();
        }
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUserName() {
        return userName;
    }

	public List<String> getStaffNoList() {
		return staffNoList;
	}

	public void setStaffNoList(List<String> staffNoList) {
		this.staffNoList = staffNoList;
	}
    
}
