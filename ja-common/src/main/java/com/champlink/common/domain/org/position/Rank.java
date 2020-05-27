package com.champlink.common.domain.org.position;

import com.champlink.common.domain.BaseBean;

public class Rank extends BaseBean {
    
	/**
	 * 职级名称
	 */
	private String rankName;

	/**
	 * 职级描述
	 */
	private String rankDesc;


	/**
	 * 
	 * 薪资上限
	 */

	private Double salaryMax;
	/**
	 * 
	 * 薪资下限
	 */
	private Double salaryMin;



	public Double getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Double salaryMax) {
		this.salaryMax = salaryMax;
	}

	public Double getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Double salaryMin) {
		this.salaryMin = salaryMin;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}



	public String getRankDesc() {
		return rankDesc;
	}

	public void setRankDesc(String rankDesc) {
		this.rankDesc = rankDesc;
	}


}