package com.champlink.common.domain.org.position;

import com.champlink.common.domain.BaseBean;

public class Position extends BaseBean {

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

    private String[] positionGrade;

    /**
     * 编制人数
     * 
     * 
     */

    private Integer staffSize;

    private String postassignment;

    private Long[] grades;
    private Long[] ranks;

    public Long[] getGrades() {
        return grades;
    }

    public void setGrades(Long[] grades) {
        this.grades = grades;
    }

    public Long[] getRanks() {
        return ranks;
    }

    public void setRanks(Long[] ranks) {
        this.ranks = ranks;
    }

    public String getPostassignment() {
        return postassignment;
    }

    public void setPostassignment(String postassignment) {
        this.postassignment = postassignment;
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

    public String[] getPositionGrade() {
        return positionGrade;
    }

    public void setPositionGrade(String[] positionGrade) {
        this.positionGrade = positionGrade;
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