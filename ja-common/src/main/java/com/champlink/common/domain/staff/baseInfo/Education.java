package com.champlink.common.domain.staff.baseInfo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

/**
 * 教育经历
 * 
 * @author natyu
 * @date 2018年7月21日 上午10:57:00
 *
 */
public class Education extends BaseBean {

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 专业/方向
     */
    private String majorName;

    /**
     * 入学时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entranceTime;

    /**
     * 毕业时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduateTime;

    /**
     * 学位
     */
    private Long education;

    /**
     * 学历
     */
    private Long degree;

    /**
     * 毕业情况
     */
    private Long graduationSituation;

    /**
     * 所获学历证书类
     */
    private Long schoolingDocumentsType;

    /**
     * 学位授予国家
     */
    private Long degreeCountry;

    /**
     * 学习方式
     */
    private Long learningStyle;

    /**
     * 是否最高学位
     */
    private Long isHighestDegree;

    /**
     * 是否最高学历
     */
    private Long isHighestEducation;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 获取人员信息ID
     *
     * @return staff_id - 人员信息ID
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置人员信息ID
     *
     * @param staffId 人员信息ID
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取学校名称
     *
     * @return school_name - 学校名称
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置学校名称
     *
     * @param schoolName 学校名称
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取专业/方向
     *
     * @return major_name - 专业/方向
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * 设置专业/方向
     *
     * @param majorName 专业/方向
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    /**
     * 获取入学时间
     *
     * @return entrance_time - 入学时间
     */
    public Date getEntranceTime() {
        return entranceTime;
    }

    /**
     * 设置入学时间
     *
     * @param entranceTime 入学时间
     */
    public void setEntranceTime(Date entranceTime) {
        this.entranceTime = entranceTime;
    }

    /**
     * 获取毕业时间
     *
     * @return graduate_time - 毕业时间
     */
    public Date getGraduateTime() {
        return graduateTime;
    }

    /**
     * 设置毕业时间
     *
     * @param graduateTime 毕业时间
     */
    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }

    /**
     * 获取学位
     *
     * @return education - 学位
     */
    public Long getEducation() {
        return education;
    }

    /**
     * 设置学位
     *
     * @param education 学位
     */
    public void setEducation(Long education) {
        this.education = education;
    }

    /**
     * 获取学历
     *
     * @return degree - 学历
     */
    public Long getDegree() {
        return degree;
    }

    /**
     * 设置学历
     *
     * @param degree 学历
     */
    public void setDegree(Long degree) {
        this.degree = degree;
    }

    /**
     * 获取毕业情况
     *
     * @return graduation_situation - 毕业情况
     */
    public Long getGraduationSituation() {
        return graduationSituation;
    }

    /**
     * 设置毕业情况
     *
     * @param graduationSituation 毕业情况
     */
    public void setGraduationSituation(Long graduationSituation) {
        this.graduationSituation = graduationSituation;
    }

    /**
     * 获取所获学历证书类
     *
     * @return schooling_documents_type - 所获学历证书类
     */
    public Long getSchoolingDocumentsType() {
        return schoolingDocumentsType;
    }

    /**
     * 设置所获学历证书类
     *
     * @param schoolingDocumentsType 所获学历证书类
     */
    public void setSchoolingDocumentsType(Long schoolingDocumentsType) {
        this.schoolingDocumentsType = schoolingDocumentsType;
    }

    /**
     * 获取学位授予国家
     *
     * @return degree_country - 学位授予国家
     */
    public Long getDegreeCountry() {
        return degreeCountry;
    }

    /**
     * 设置学位授予国家
     *
     * @param degreeCountry 学位授予国家
     */
    public void setDegreeCountry(Long degreeCountry) {
        this.degreeCountry = degreeCountry;
    }

    /**
     * 获取学习方式
     *
     * @return learning_style - 学习方式
     */
    public Long getLearningStyle() {
        return learningStyle;
    }

    /**
     * 设置学习方式
     *
     * @param learningStyle 学习方式
     */
    public void setLearningStyle(Long learningStyle) {
        this.learningStyle = learningStyle;
    }

    /**
     * 获取是否最高学位
     *
     * @return is_highest_degree - 是否最高学位
     */
    public Long getIsHighestDegree() {
        return isHighestDegree;
    }

    /**
     * 设置是否最高学位
     *
     * @param isHighestDegree 是否最高学位
     */
    public void setIsHighestDegree(Long isHighestDegree) {
        this.isHighestDegree = isHighestDegree;
    }

    /**
     * 获取是否最高学历
     *
     * @return is_highest_education - 是否最高学历
     */
    public Long getIsHighestEducation() {
        return isHighestEducation;
    }

    /**
     * 设置是否最高学历
     *
     * @param isHighestEducation 是否最高学历
     */
    public void setIsHighestEducation(Long isHighestEducation) {
        this.isHighestEducation = isHighestEducation;
    }

}