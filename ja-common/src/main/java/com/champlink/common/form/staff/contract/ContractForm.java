package com.champlink.common.form.staff.contract;

import java.util.List;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.BaseForm;

public class ContractForm extends BaseForm {

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同类型
     */
    private String contractType;

    /**
     * 合同状态（未生效 、履行中 、已解除 、已终止）
     */
    private Integer contractState;

    /**
     * 续签状态（已续签 、未续签）
     */
    private Integer renewStatus;

    /**
     * 工号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 基地
     */
    private Long baseId;

    private Long deptId;

    /**
     * 所有子部门
     */
    private List<Org> deptIds;

    private List<Long> staffIdList;
    
    public List<Org> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Org> deptIds) {
        this.deptIds = deptIds;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取合同编号
     *
     * @return contract_no - 合同编号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置合同编号
     *
     * @param contractNo 合同编号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * 获取合同类型
     *
     * @return contract_type - 合同类型
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * 设置合同类型
     *
     * @param contractType 合同类型
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Integer getContractState() {
        return contractState;
    }

    public void setContractState(Integer contractState) {
        this.contractState = contractState;
    }

    public Integer getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(Integer renewStatus) {
        this.renewStatus = renewStatus;
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

	public List<Long> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Long> staffIdList) {
		this.staffIdList = staffIdList;
	}

}
