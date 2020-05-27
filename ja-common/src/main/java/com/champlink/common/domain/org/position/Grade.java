package com.champlink.common.domain.org.position;

import com.champlink.common.domain.BaseBean;

public class Grade extends BaseBean {

    /**
     * 职等名称/赋值名称
     */
    private String gradeName;

    /**
     * 描述
     */
    private String gradeDesc;

    private Long[] gradeRank;
    /**
     * 
     * 岗位赋值
     * 
     */
    private String postAssignment;

    private String ranks;

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getPostAssignment() {
        return postAssignment;
    }

    public void setPostAssignment(String postAssignment) {
        this.postAssignment = postAssignment;
    }

    public Long[] getGradeRank() {
        return gradeRank;
    }

    public void setGradeRank(Long[] gradeRank) {
        this.gradeRank = gradeRank;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

}