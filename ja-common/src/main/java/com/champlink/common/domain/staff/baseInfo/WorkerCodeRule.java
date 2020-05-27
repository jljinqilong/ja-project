package com.champlink.common.domain.staff.baseInfo;

import com.champlink.common.domain.BaseBean;

public class WorkerCodeRule extends BaseBean {

	/**
	 * 基地id
	 */
    private String baseId;
    
    /**
     * 工号前缀
     */
    private String workerCodePrefix;
    
    /**
     * 工号长度
     */
    private Integer workerCodeLen;
    
    /**
     * 工号格式
     */
    private String format;
    
    private int currentId;
    
    /**
     * 是否可用  0:可用,1:不可用
     */
    private Integer usable;



	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getWorkerCodePrefix() {
		return workerCodePrefix;
	}

	public void setWorkerCodePrefix(String workerCodePrefix) {
		this.workerCodePrefix = workerCodePrefix;
	}

	public Integer getWorkerCodeLen() {
		return workerCodeLen;
	}

	public void setWorkerCodeLen(Integer workerCodeLen) {
		this.workerCodeLen = workerCodeLen;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getUsable() {
		return usable;
	}

	public void setUsable(Integer usable) {
		this.usable = usable;
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}
	
}