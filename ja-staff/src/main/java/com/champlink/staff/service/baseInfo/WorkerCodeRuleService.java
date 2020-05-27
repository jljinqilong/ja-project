package com.champlink.staff.service.baseInfo;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.baseInfo.WorkerCodeRule;
import com.champlink.common.form.staff.baseInfo.SearchWorkerCodeRule;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.baseInfo.WorkerCodeRuleDao;
import com.champlink.staff.service.call.OrgService;
import com.github.pagehelper.Page;

@Service
public class WorkerCodeRuleService {

	@Autowired
	private WorkerCodeRuleDao workerCodeRuleDao;

	@Autowired
	private OrgService orgService;

	public int add(WorkerCodeRule workerCodeRule) {

		// 判断该基地是否已经存在员工工号规则
		Integer queryCountByBaseId = workerCodeRuleDao.queryCountByBaseId(Long.valueOf(workerCodeRule.getBaseId()));
		if (queryCountByBaseId > 0) {
			AppException.create(200004);
		}
		Integer workerCodeLen = workerCodeRule.getWorkerCodeLen();
		String workerCodePrefix = workerCodeRule.getWorkerCodePrefix();
		if (workerCodePrefix != null) {
			int fill_x = workerCodeLen - workerCodePrefix.length();
			String format = workerCodePrefix;
			for (int i = 0; i < fill_x; i++) {
				format += "X";
			}
			workerCodeRule.setFormat(format); // JAXXXXXX
		}
		return workerCodeRuleDao.add(workerCodeRule);
	}

	public int del(Long rowId) {
		return workerCodeRuleDao.delByRowId(rowId);
	}

	public int update(WorkerCodeRule workerCodeRule) {
		Integer workerCodeLen = workerCodeRule.getWorkerCodeLen();
		String workerCodePrefix = workerCodeRule.getWorkerCodePrefix();
		if (workerCodePrefix != null) {
			int fill_x = workerCodeLen - workerCodePrefix.length();
			String format = workerCodePrefix;
			for (int i = 0; i < fill_x; i++) {
				format += "X";
			}
			workerCodeRule.setFormat(format); // JAXXXXXX
		}
		return workerCodeRuleDao.update(workerCodeRule);
	}

	public PageListVO getWorkerCodeRuleList(SearchWorkerCodeRule form) {
		Paginater paginater = Paginater.newInstance(form);
		Page<WorkerCodeRule> queryAll = workerCodeRuleDao.queryAll(paginater);
		List<WorkerCodeRule> WorkerCodeRuleList = queryAll.getResult();

		String baseList = orgService.baseList(); // 通过baseId查询 基地名称
		if (baseList != null) {
			JSONObject jsonObj = JSONObject.parseObject(baseList);
			if (jsonObj != null) {

				if (Integer.valueOf(jsonObj.getString("code")) == 200) {
					// TODO 没有基地时，需要调试
					JSONArray parseArray = JSONObject.parseArray(jsonObj.getString("data"));
					if (parseArray != null && parseArray.size() > 0) {

						for (Iterator<WorkerCodeRule> iterator = WorkerCodeRuleList.iterator() ; iterator.hasNext();) {
							boolean exit = false;
							WorkerCodeRule workerCodeRule = iterator.next();
							for (Object object : parseArray) {
								JSONObject jsonObjSon = (JSONObject) object;
								Object obj = jsonObjSon.get("rowId");
								if (String.valueOf(jsonObjSon.get("rowId")).equals(workerCodeRule.getBaseId())) {
									workerCodeRule.setBaseId(String.valueOf(jsonObjSon.get("baseOrDeptName")));
									exit = true;
									break;
								}
							}
							if(!exit) {
								// 基地被删除，不存在基地
								del(workerCodeRule.getRowId()); // 基地不存在，则删除该基地下的工号编码规则
								iterator.remove();
							}
						}
					}
				}
			}

		}

		PageListVO<WorkerCodeRule> pageListVO = PageListVO.newInstance(queryAll);
		return pageListVO;
	}

	/**
	 * 自动生成工号编码规则
	 * 
	 * @param baseId
	 *            :基地id
	 * @param currentId
	 *            : 当前员工id 自增
	 * @return
	 */
	public String generateWorkerCode(Long baseId, int currentId) {

		WorkerCodeRule queryOneByBaseId = workerCodeRuleDao.queryOneByBaseId(baseId);
		if (queryOneByBaseId != null) {
			String workerCodePrefix = queryOneByBaseId.getWorkerCodePrefix();
			Integer workerCodeLen = queryOneByBaseId.getWorkerCodeLen();
			int fill_digit = workerCodeLen - workerCodePrefix.length();
			String staffCode = String.format(workerCodePrefix + "%0" + fill_digit + "d", ++currentId);

			return staffCode;
		}

		return null;
	}

	public void updateByDeptId(Long baseId, int currentId) {
		workerCodeRuleDao.updateByDeptId(baseId, currentId);
	}

	public WorkerCodeRule queryOneByRowId(Long rowId) {
		return workerCodeRuleDao.queryOneByRowId(rowId);
	}

}
