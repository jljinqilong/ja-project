package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

public class RefRoleData extends BaseBean {

    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long roleId;

    /** 资源ID */
    private Long orgId;

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

}
