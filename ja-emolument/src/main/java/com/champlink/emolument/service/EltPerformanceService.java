package com.champlink.emolument.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.CreditCardLog;
import com.champlink.common.domain.emolument.EltPerformance;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.emolument.EltPerformanceForm;
import com.champlink.common.form.emolument.ImportEltPerformanceForm;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.dao.EltPerformanceDao;
import com.champlink.emolument.service.call.StaffService;

@Service
public class EltPerformanceService {

	
	
	@Autowired
	private EltPerformanceDao eltPerformanceDao;
	
	@Autowired
	private StaffService staffService;
	
	
	/**
	 * 获取列表
	 * 
	 * @param form
	 * @return
	 */
	public PageListVO pageList(EltPerformanceForm form) {
		Paginater paginater = Paginater.newInstance(form);
		PageListVO pageListVO = PageListVO.newInstance(eltPerformanceDao.pageList(paginater));
		return pageListVO;
	}
	
	
	public int insertList(List<EltPerformance> eltPerformanceList) {
		int updateCount = eltPerformanceDao.insertList(eltPerformanceList);
		return updateCount;
	}
	
	/**
	 * 批量导入绩效
	 * 
	 * @author jinlong
	 * @date 2018年7月9日 下午3:12:09
	 * @param list
	 */
	@Transactional
	public List<ImportEltPerformanceForm> importExcel(List<ImportEltPerformanceForm> list) {

		List<ImportEltPerformanceForm> errorEltPerformance = new ArrayList<ImportEltPerformanceForm>();
		List<EltPerformance> insertEltPerformanceList = new ArrayList<EltPerformance>();

		boolean isExistErr = false; // 是否存在错误数据
		for (ImportEltPerformanceForm importEltPerformanceForm : list) {
			StringBuffer sb = new StringBuffer();
			String staffNo = importEltPerformanceForm.getStaffNo().trim();
			if (!staffService.checkStaffNo(staffNo)) {
				isExistErr = true;
				// 员工工号不存在
				sb.append("员工工号不存在;");
			}

			if (!StringUtils.isEmpty(sb.toString())) {
				isExistErr = true;
				importEltPerformanceForm.setErrMsg(sb.toString());
				errorEltPerformance.add(importEltPerformanceForm);
				continue;
			}

			SearchBaseInfoForm form = new SearchBaseInfoForm();
			form.setStaffNo(staffNo);
			String queryBaseInfoForParams = staffService.queryBaseInfoForParams(form);
			EltPerformance eltPerformance = new EltPerformance();
			BeanUtils.copyProperties(importEltPerformanceForm, eltPerformance);
			if (queryBaseInfoForParams != null) {
				JSONObject parseObject = JSONObject.parseObject(queryBaseInfoForParams);
				if ((Integer) parseObject.get("code") == 200) {
					List<BaseInfo> baseInfoList = JSONObject.parseArray(parseObject.getString("data"), BaseInfo.class);
					if (baseInfoList != null && baseInfoList.size() > 0) {
						BaseInfo baseInfo = baseInfoList.get(0);
						eltPerformance.setBaseId(baseInfo.getBaseId());  // 获取员工所在的基地
						eltPerformance.setStaffId(baseInfo.getRowId());
						insertEltPerformanceList.add(eltPerformance);
						errorEltPerformance.add(importEltPerformanceForm); // 正确数据也导出
					}
				}
			}

		}

		if (!isExistErr) {
			// 插入所有记录 insertCreditCardLogList 所有记录都没有错误时才会插入
			if(insertEltPerformanceList.size() > 0) {
				insertList(insertEltPerformanceList); // 批量添加
			}
			return null;
		}
		
		
		
		return errorEltPerformance;
	}

	/**
	 * 导出，导入的员工错误信息
	 * 
	 * @author natyu
	 * @date 2018年7月27日 下午3:16:59
	 * @param response
	 * @param form
	 * @param lang
	 */
	public void exportErrExcel(HttpServletResponse response, List<ImportEltPerformanceForm> list, String lang) {

		// 表头
		List<String> headerList = new ArrayList<String>();
		// field
		List<String> fieldList = new ArrayList<String>();

		fieldList.add("staffNo");
		fieldList.add("month");
		fieldList.add("amountOfPerformance");
		fieldList.add("errMsg");

		String title = "";
		int size = list.size();
		String[] headers = new String[size];

		if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
			headerList.add("员工工号");
			headerList.add("月份");
			headerList.add("绩效金额");
			headerList.add("错误描述");
			title = "绩效错误信息";

			headers = (String[]) headerList.toArray(new String[0]);

		} else if (lang.equalsIgnoreCase(Constant.EN)) {

			headerList.add("员工工号");
			headerList.add("月份");
			headerList.add("绩效金额");
			headerList.add("Error Description");
			// TODO
			title = "Staff Base Information";
			headers = (String[]) headerList.toArray(new String[0]);
		}

		// pojo字段
		String[] fields = (String[]) fieldList.toArray(new String[0]);

		// 生成Excel表格
		ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
	}
	
	
}	
