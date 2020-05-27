package com.champlink.common.vo;

import com.champlink.common.domain.BaseBean;

public class PositionVO extends BaseBean {
    private Integer positionType;
    private String positionName;
    private String rankName;
    private Integer salaryMax;
    private Integer salaryMin;
    private String postAssignment;
    private Integer staffSize;
    private String fileDesc;
    private String gradeName;
    private String typeName;

    /**
     * 职等/赋值名称 字符串
     */
    String grades;
    /**
     * 职级字符串
     */
    String ranks;

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getPostAssignment() {
        return postAssignment;
    }

    public void setPostAssignment(String postAssignment) {
        this.postAssignment = postAssignment;
    }

    public Integer getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(Integer staffSize) {
        this.staffSize = staffSize;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

}
