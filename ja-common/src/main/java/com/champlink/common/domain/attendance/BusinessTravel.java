package com.champlink.common.domain.attendance;

import java.util.Date;
import java.util.List;

import com.champlink.common.domain.BaseBean;
import com.champlink.common.domain.sale.area.CountryProvCity;

/**
 * 出差
 * 
 * @author natyu
 * @date 2018年8月21日 下午6:37:35
 *
 */
public class BusinessTravel extends BaseBean {

    /**
     * 员工ID
     */
    private Long staffId;

    /**
     * 员工工号
     */
    private String staffNo;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 班次ID
     */
    private Long jobNoId;

    /**
     * 班次名称
     */
    private String jobNoName;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职等/赋值名称ID
     */
    private Long gradeId;

    /**
     * 职等/赋值名称名称
     */
    private String gradeName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 代理人ID
     */
    private Long agentStaffId;

    /**
     * 代理人工号
     */
    private String agentStaffNo;

    /**
     * 代理人姓名
     */
    private String agentStaffName;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 时长
     */
    private Double hours;

    /**
     * 同行人
     */
    private String togetherName;

    /**
     * 出行方式
     */
    private Long travelMode;

    /**
     * 国家
     */
    private Long countryId;
    /**
     * 省/州
     */
    private Long provinceId;
    
    /**
     * 城市
     */
    private Long cityId;

    private List<CountryProvCity> countryList;

    private List<CountryProvCity> provinceList;

    private List<CountryProvCity> cityList;

    
    /**
     * 获取员工ID
     *
     * @return staff_id - 员工ID
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置员工ID
     *
     * @param staffId 员工ID
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取员工工号
     *
     * @return staff_no - 员工工号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置员工工号
     *
     * @param staffNo 员工工号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取员工姓名
     *
     * @return staff_name - 员工姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置员工姓名
     *
     * @param staffName 员工姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取班次ID
     *
     * @return job_no_id - 班次ID
     */
    public Long getJobNoId() {
        return jobNoId;
    }

    /**
     * 设置班次ID
     *
     * @param jobNoId 班次ID
     */
    public void setJobNoId(Long jobNoId) {
        this.jobNoId = jobNoId;
    }

    /**
     * 获取班次名称
     *
     * @return job_no_name - 班次名称
     */
    public String getJobNoName() {
        return jobNoName;
    }

    /**
     * 设置班次名称
     *
     * @param jobNoName 班次名称
     */
    public void setJobNoName(String jobNoName) {
        this.jobNoName = jobNoName;
    }

    /**
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取部门名称
     *
     * @return dept_name - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取职等/赋值名称ID
     *
     * @return grade_id - 职等/赋值名称ID
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * 设置职等/赋值名称ID
     *
     * @param gradeId 职等/赋值名称ID
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 获取职等名称/赋值名称
     *
     * @return grade_name - 职等名称/赋值名称
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * 设置职等名称/赋值名称
     *
     * @param gradeName 职等名称/赋值名称
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取代理人ID
     *
     * @return agent_staff_id - 代理人ID
     */
    public Long getAgentStaffId() {
        return agentStaffId;
    }

    /**
     * 设置代理人ID
     *
     * @param agentStaffId 代理人ID
     */
    public void setAgentStaffId(Long agentStaffId) {
        this.agentStaffId = agentStaffId;
    }

    /**
     * 获取代理人工号
     *
     * @return agent_staff_no - 代理人工号
     */
    public String getAgentStaffNo() {
        return agentStaffNo;
    }

    /**
     * 设置代理人工号
     *
     * @param agentStaffNo 代理人工号
     */
    public void setAgentStaffNo(String agentStaffNo) {
        this.agentStaffNo = agentStaffNo;
    }

    /**
     * 获取代理人姓名
     *
     * @return agent_staff_name - 代理人姓名
     */
    public String getAgentStaffName() {
        return agentStaffName;
    }

    /**
     * 设置代理人姓名
     *
     * @param agentStaffName 代理人姓名
     */
    public void setAgentStaffName(String agentStaffName) {
        this.agentStaffName = agentStaffName;
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

    public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	/**
     * 获取同行人
     *
     * @return together_name - 同行人
     */
    public String getTogetherName() {
        return togetherName;
    }

    /**
     * 设置同行人
     *
     * @param togetherName 同行人
     */
    public void setTogetherName(String togetherName) {
        this.togetherName = togetherName;
    }

    /**
     * 获取出行方式
     *
     * @return travel_mode - 出行方式
     */
    public Long getTravelMode() {
        return travelMode;
    }

    /**
     * 设置出行方式
     *
     * @param travelMode 出行方式
     */
    public void setTravelMode(Long travelMode) {
        this.travelMode = travelMode;
    }

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public List<CountryProvCity> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryProvCity> countryList) {
		this.countryList = countryList;
	}

	public List<CountryProvCity> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<CountryProvCity> provinceList) {
		this.provinceList = provinceList;
	}

	public List<CountryProvCity> getCityList() {
		return cityList;
	}

	public void setCityList(List<CountryProvCity> cityList) {
		this.cityList = cityList;
	}
	
}