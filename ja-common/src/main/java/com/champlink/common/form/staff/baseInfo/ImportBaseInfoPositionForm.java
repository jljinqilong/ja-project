package com.champlink.common.form.staff.baseInfo;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;

public class ImportBaseInfoPositionForm extends BaseForm {

	
	/**
     * 工号
     */
    @Excel(title = "员工工号")
    private String staffNo;
	
    /**
     * 职衔ID
     */
    private Long positionId;

    @Excel(title = "职衔", isNull = false)
    private String positionStr;


    /**
     * 职等ID/赋值名称
     */
    private Long gradeId;

    @Excel(title = "职等/赋值名称", isNull = false)
    private String gradeStr;
    
    /**
     * 职级ID
     */
    private Long rankId;

    @Excel(title = "职级", isNull = false)
    private String rankStr;
    
    /**
     * 错误描述
     */
    private String errMsg;

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionStr() {
		return positionStr;
	}

	public void setPositionStr(String positionStr) {
		this.positionStr = positionStr;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeStr() {
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}

	public Long getRankId() {
		return rankId;
	}

	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}

	public String getRankStr() {
		return rankStr;
	}

	public void setRankStr(String rankStr) {
		this.rankStr = rankStr;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
    
}
