package com.champlink.common.form.org.org;

import java.util.Date;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;

public class ImportOrgAndOrgInfoForm extends BaseForm {

    /**
     * 基地或者部门名称
     */
    @Excel(title = "部门名称", isNull = false)
    private String baseOrDeptName;

    /**
     * 基地或者部门名称
     */
    @Excel(title = "部门简称", isNull = false)
    private String baseOrDeptShortName;
    
    /**
     * 成立日期
     */
    @Excel(title = "成立日期")
    private Date establishDate;


    /**
     * 上级基地名称
     */
    @Excel(title = "所属基地名称", isNull = false)
    private String parentBaseName;

    /**
     * 上级名称
     */
    @Excel(title = "上级组织机构名称", isNull = false)
    private String parentOrgname;
    

    /**
     * 总负责人-工号
     */
    @Excel(title = "总负责人-工号")
    private String leaderNo;

    
    /**
     * 编制人数
     */
    @Excel(title = "编制人数")
    private Long deptNum;

   

    /**
     * 错误描述
     */
    private String errMsg;

    /**********************************/

    public String getParentOrgname() {
        return parentOrgname;
    }

    public void setParentOrgname(String parentOrgname) {
        this.parentOrgname = parentOrgname;
    }

    public String getParentBaseName() {
        return parentBaseName;
    }

    public void setParentBaseName(String parentBaseName) {
        this.parentBaseName = parentBaseName;
    }

    public String getBaseOrDeptName() {
        return baseOrDeptName;
    }

    public void setBaseOrDeptName(String baseOrDeptName) {
        this.baseOrDeptName = baseOrDeptName;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getLeaderNo() {
        return leaderNo;
    }

    public void setLeaderNo(String leaderNo) {
        this.leaderNo = leaderNo;
    }


    public Long getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(Long deptNum) {
        this.deptNum = deptNum;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

	public String getBaseOrDeptShortName() {
		return baseOrDeptShortName;
	}

	public void setBaseOrDeptShortName(String baseOrDeptShortName) {
		this.baseOrDeptShortName = baseOrDeptShortName;
	}

    
}
