package com.champlink.common.domain.staff.baseInfo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class ProjectExperience extends BaseBean {

    /**
     * 人员信息ID
     */
    private Long staffId;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 担任职务
     */
    private String fillPost;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 负责内容
     */
    private String responsibleContent;

    /**
     * 项目结果
     */
    private String projectResult;

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
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取担任职务
     *
     * @return fill_post - 担任职务
     */
    public String getFillPost() {
        return fillPost;
    }

    /**
     * 设置担任职务
     *
     * @param fillPost 担任职务
     */
    public void setFillPost(String fillPost) {
        this.fillPost = fillPost;
    }

    /**
     * 获取开始时间
     *
     * @return start_date - 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始时间
     *
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取负责内容
     *
     * @return responsible_content - 负责内容
     */
    public String getResponsibleContent() {
        return responsibleContent;
    }

    /**
     * 设置负责内容
     *
     * @param responsibleContent 负责内容
     */
    public void setResponsibleContent(String responsibleContent) {
        this.responsibleContent = responsibleContent;
    }

    /**
     * 获取项目结果
     *
     * @return project_result - 项目结果
     */
    public String getProjectResult() {
        return projectResult;
    }

    /**
     * 设置项目结果
     *
     * @param projectResult 项目结果
     */
    public void setProjectResult(String projectResult) {
        this.projectResult = projectResult;
    }
}