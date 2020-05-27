package com.champlink.staff.jxAppBean;

public class JaHrEmpProjectInfoBean {

    private String guid;
    private String empGuid;
    /**
     * 项目名称
     */
    private String itemsName;
    /**
     * 担任职务
     */
    private String holdPost;
    /**
     * 负责内容
     */
    private String responsibleContent;
    /**
     * 项目开始时间
     */
    private String itemsStartDate;
    /**
     * 项目结束时间
     */
    private String itemsEndDate;
    /**
     * 项目结果
     */
    private String itemsResult;

    public JaHrEmpProjectInfoBean() {
        super();
    }

    public String getEmpGuid() {
        return empGuid;
    }

    public void setEmpGuid(String empGuid) {
        this.empGuid = empGuid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getHoldPost() {
        return holdPost;
    }

    public void setHoldPost(String holdPost) {
        this.holdPost = holdPost;
    }

    public String getResponsibleContent() {
        return responsibleContent;
    }

    public void setResponsibleContent(String responsibleContent) {
        this.responsibleContent = responsibleContent;
    }

    public String getItemsStartDate() {
        return itemsStartDate;
    }

    public void setItemsStartDate(String itemsStartDate) {
        this.itemsStartDate = itemsStartDate;
    }

    public String getItemsEndDate() {
        return itemsEndDate;
    }

    public void setItemsEndDate(String itemsEndDate) {
        this.itemsEndDate = itemsEndDate;
    }

    public String getItemsResult() {
        return itemsResult;
    }

    public void setItemsResult(String itemsResult) {
        this.itemsResult = itemsResult;
    }

    public JaHrEmpProjectInfoBean(String guid, String empGuid, String itemsName, String holdPost, String responsibleContent, String itemsStartDate, String itemsEndDate, String itemsResult) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.itemsName = itemsName;
        this.holdPost = holdPost;
        this.responsibleContent = responsibleContent;
        this.itemsStartDate = itemsStartDate;
        this.itemsEndDate = itemsEndDate;
        this.itemsResult = itemsResult;
    }

}
