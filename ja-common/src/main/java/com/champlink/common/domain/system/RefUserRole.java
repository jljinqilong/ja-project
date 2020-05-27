package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

public class RefUserRole extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private Long userId;

	private Role role;

	public RefUserRole() {
	}

	public RefUserRole(String roleId, Long userId) {
		this.roleId = Long.parseLong(roleId);
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
