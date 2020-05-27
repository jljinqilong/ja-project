package com.champlink.common.form.system;

import java.util.List;
import com.champlink.common.form.BaseForm;

public class RefRoleDataForm extends BaseForm {

    private List<Long> orgIds;
    private Long roleId;
    
    private List<Long> halfOrgIds;

    public List<Long> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Long> orgIds) {
        this.orgIds = orgIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

	public List<Long> getHalfOrgIds() {
		return halfOrgIds;
	}

	public void setHalfOrgIds(List<Long> halfOrgIds) {
		this.halfOrgIds = halfOrgIds;
	}

}
