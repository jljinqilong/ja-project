package com.champlink.common.vo;

import java.util.List;

import com.champlink.common.domain.BaseBean;
import com.github.pagehelper.Page;

public class PageListVO<T extends BaseBean> {

	private int pageNum;
	private int pageSize;
	private long total;
	private int pageTotal;
	private List<T> list;

	private PageListVO() {

	}

	public static <T extends BaseBean> PageListVO<T> newInstance(Page<T> page) {
		PageListVO<T> pageListVO = new PageListVO<T>();
		pageListVO.setPageNum(page.getPageNum());
		pageListVO.setPageSize(page.getPageSize());
		pageListVO.setTotal(page.getTotal());
		pageListVO.setList(page.getResult());
		pageListVO.setPageTotal(page.getPages());
		return pageListVO;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

}
