package com.champlink.common.form.system;

import org.apache.commons.lang3.StringUtils;
import com.champlink.common.form.BaseForm;

public class RoleForm extends BaseForm {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        if (StringUtils.isNotEmpty(roleName)) {
            roleName = roleName.trim();
        }
        this.roleName = roleName;
    }

}
