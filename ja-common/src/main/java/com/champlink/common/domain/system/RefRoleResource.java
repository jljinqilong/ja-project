package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

public class RefRoleResource extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 角色ID */
	private Long roleId;

	/** 资源ID */
	private Long resourceId;

	private Resource resource;

	public RefRoleResource() {

	}

	public RefRoleResource(String resourcdId, Long roleId) {
		this.resourceId = Long.parseLong(resourcdId);
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
