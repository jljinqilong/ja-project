package com.champlink.common.form.org.org;

import com.champlink.common.domain.BaseBean;

public class PositionForm extends BaseBean {

    /**
     * 职衔信息ID
     */
    private Long rowId;

    /**
     * 
     * 职衔类型
     */

    private Integer positionType;

    /**
     * 
     * 职衔名称
     * 
     */

    private String positionName;

    /**
     * 
     * 文件描述
     */

    private String fileDesc;

    /**
     * 编制人数
     * 
     * 
     */

    private Integer staffSize;

    /**
     * 职衔对应的职等/赋值名称
     */
    private String[] grades;

    /**
     * 职衔对应的职级
     */
    private String[] ranks;

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }

    public String[] getRanks() {
        return ranks;
    }

    public void setRanks(String[] ranks) {
        this.ranks = ranks;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public Integer getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(Integer staffSize) {
        this.staffSize = staffSize;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 是否删除：0：有效；1：无效
     */
    private Integer delFlag;

}