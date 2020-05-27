package com.champlink.common.domain.attendance;

import com.champlink.common.domain.BaseBean;

/**
 * 豁免考勤
 * 
 * @author natyu
 * @date 2018年8月21日 下午6:38:27
 *
 */
public class Exemptions extends BaseBean {

	/**
	 * 员工ID
	 */
	private Long staffId;

	private String staffNo;

	private String staffName;

	private String positionName;

	private String gradeName;

	private String rankName;

	private String baseName;

	private String deptName;

	/**
	 * 获取员工ID
	 *
	 * @return staff_id - 员工ID
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * 设置员工ID
	 *
	 * @param staffId
	 *            员工ID
	 */
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}