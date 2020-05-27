package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

public class RefUserResource extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long rowid;

	/** 资源ID */
	private Long resourceId;

	/** 用户ID */
	private Long userId;

	private User user;

	public RefUserResource() {

	}

	public RefUserResource(String resourceId, Long userId) {
		this.resourceId = Long.parseLong(resourceId);
		this.userId = userId;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
