package com.champlink.attendance.service;

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
import com.champlink.attendance.dao.CreditCardLogMapper;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.CreditCardLog;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.attendance.CreditCardLogForm;
import com.champlink.common.form.attendance.ImportCreditCardLogForm;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.vo.PageListVO;
import com.github.pagehelper.Page;

@Service
public class CreditCardLogService {

	@Autowired
	private CreditCardLogMapper creditCardLogMapper;
	@Autowired
	private StaffService staffService;
	@Autowired
	private MachineService machineService;

	/**
	 * 添加一条
	 * @param creditCardLog
	 * @return
	 */
	public int add(CreditCardLog creditCardLog) {
		return creditCardLogMapper.insert(creditCardLog);
	}
	
	/**
	 * 批量插入
	 * @param creditCardLogList
	 * @return
	 */
	public int addList(List<CreditCardLog> creditCardLogList) {
		return creditCardLogMapper.insertList(creditCardLogList);
	}

	public PageListVO pageList(CreditCardLogForm creditCardLogForm) {
		Paginater paginater = Paginater.newInstance(creditCardLogForm);
		Page pageList = creditCardLogMapper.pageList(paginater);
		PageListVO pageListVO = PageListVO.newInstance(pageList);
		return pageListVO;
	}

	public List<CreditCardLog> allList() {
		List<CreditCardLog> selectAll = creditCardLogMapper.selectAll();
		return selectAll;
	}

	/**
	 * 批量导入刷卡日志
	 * 
	 * @author jinlong
	 * @date 2018年7月9日 下午3:12:09
	 * @param list
	 */
	@Transactional
	public List<ImportCreditCardLogForm> importExcel(List<ImportCreditCardLogForm> list) {

		List<ImportCreditCardLogForm> errorBaseInfos = new ArrayList<ImportCreditCardLogForm>();
		List<CreditCardLog> insertCreditCardLogList = new ArrayList<CreditCardLog>();
		boolean isExistErr = false; // 是否存在错误数据
		for (ImportCreditCardLogForm importCreditCardLogForm : list) {
			StringBuffer sb = new StringBuffer();
			String staffNo = importCreditCardLogForm.getStaffNo().trim();
			if (!staffService.checkStaffNo(staffNo)) {
				isExistErr = true;
				// 员工工号不存在
				sb.append("员工工号不存在;");
			}
			String machineName = importCreditCardLogForm.getMachineName().trim();
			if (!machineService.getCountByMachineName(machineName)) {
				isExistErr = true;
				sb.append("考勤机不存在！");
			}

			if (!StringUtils.isEmpty(sb.toString())) {
				isExistErr = true;
				importCreditCardLogForm.setErrMsg(sb.toString());
				errorBaseInfos.add(importCreditCardLogForm);
				continue;
			}

			String getBaseInfoByStaffNo = staffService.getBaseInfoByStaffNo(staffNo);
			CreditCardLog creditCardLog = new CreditCardLog();
			BeanUtils.copyProperties(importCreditCardLogForm, creditCardLog);
			
			if (getBaseInfoByStaffNo != null) {
				JSONObject parseObject = JSONObject.parseObject(getBaseInfoByStaffNo);
				if ((Integer) parseObject.get("code") == 200) {
					BaseInfo baseInfo = JSONObject.parseObject(parseObject.getString("data"), BaseInfo.class);
					if (baseInfo != null) {
						HashMap<String, Object> transNames = baseInfo.getTransNames();
						String deptId_baseOrDeptName = (String) transNames.get("deptId_baseOrDeptName");
						creditCardLog.setOrgName(deptId_baseOrDeptName); // 部门名称
						creditCardLog.setStaffName(baseInfo.getStaffName()); // 员工姓名
						creditCardLog.setStaffId(baseInfo.getRowId());//员工id
						insertCreditCardLogList.add(creditCardLog);
						errorBaseInfos.add(importCreditCardLogForm); // 正确数据也导出
					}
				}
			}

		}

		if (!isExistErr) {
			// 插入所有记录 insertCreditCardLogList 所有记录都没有错误时才会插入
			if(insertCreditCardLogList.size() > 0) {
				addList(insertCreditCardLogList); // 批量添加
			}
			return null;
		}

		return errorBaseInfos;
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
	public void exportErrExcel(HttpServletResponse response, List<ImportCreditCardLogForm> list, String lang) {

		// 表头
		List<String> headerList = new ArrayList<String>();
		// field
		List<String> fieldList = new ArrayList<String>();

		fieldList.add("staffNo");
		fieldList.add("status");
		fieldList.add("machineName");
		fieldList.add("time");
		fieldList.add("errMsg");

		String title = "";
		int size = list.size();
		String[] headers = new String[size];

		if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
			headerList.add("工号");
			headerList.add("状态");
			headerList.add("考勤机名称");
			headerList.add("刷卡时间");
			headerList.add("错误描述");
			title = "刷卡日志错误信息";

			headers = (String[]) headerList.toArray(new String[0]);

		} else if (lang.equalsIgnoreCase(Constant.EN)) {

			headerList.add("工号");
			headerList.add("状态");
			headerList.add("考勤机名称");
			headerList.add("刷卡时间");
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
