package com.champlink.common.form.org.position;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;

public class ImportPositionForm extends BaseForm {

    /**
     * 职衔类别
     */
    @Excel(title = "职衔类别", isNull = false)
    private String typeName;

    /**
     * 职级
     */
    @Excel(title = "职级", isNull = false)
    private String rankName;
    /**
     * 职衔名称
     */
    @Excel(title = "职衔名称", isNull = false)
    private String positionName;
    /**
     * 岗位赋值
     * 
     */
    @Excel(title = "岗位赋值", isNull = false)
    private String postAssignment;
    /**
     * 
     * 职等/赋值名称
     * 
     */
    @Excel(title = "职等/赋值名称", isNull = false)
    private String gradeName;
    /**
     * 
     * 
     * 薪资上限
     */
    @Excel(title = "薪资上限", isNull = false)
    private String salaryMax;
    /**
     * 
     * 薪资下限
     */
    @Excel(title = "薪资下限", isNull = false)
    private String salaryMin;
    /**
     * 
     * 编制人数
     */
    @Excel(title = "编制人数", isNull = false)
    private String staffSize;

    /**
     * 错误描述
     */
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPostAssignment() {
        return postAssignment;
    }

    public void setPostAssignment(String postAssignment) {
        this.postAssignment = postAssignment;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(String staffSize) {
        this.staffSize = staffSize;
    }

}