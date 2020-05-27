package com.champlink.org.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.OrgConstant;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.OrgAllBackup;
import com.champlink.common.domain.org.OrgAndOrgInfo;
import com.champlink.common.domain.org.OrgInfo;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.org.org.ImportOrgAndOrgInfoForm;
import com.champlink.common.form.org.org.QueryCountBaseInfoByDeptId;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.org.dao.OrgDao;
import com.champlink.org.dao.OrgInfoDao;
import com.champlink.org.service.call.StaffService;
import com.champlink.org.service.call.SystemService;
import com.github.pagehelper.util.StringUtil;

@Service
public class OrgService {

	@Autowired
	private OrgDao orgDao;

	@Autowired
	private OrgInfoDao orgInfoDao;

	@Autowired
	private StaffService staffService;

	@Autowired
	private OrgAllBackupService orgAllBackupService;

	@Autowired
	private SystemService systemService;

	/**
	 * 添加组织架构
	 * 
	 * @param Org_1
	 * @return
	 */
	@Transactional
	public boolean add(OrgAndOrgInfo orgAndOrgInfo) {

		/**
		 * 校验组织名称和组织简称是否重复
		 */
		if (orgAndOrgInfo.getIsOrg().intValue() == 0) {
			int queryBaseCountByName = orgDao.queryBaseCountByName(orgAndOrgInfo.getBaseOrDeptName());
			if (queryBaseCountByName > 0) {
				AppException.create(500002); // 新增基地名称已存在
			}

		} else if (orgAndOrgInfo.getIsOrg().intValue() == 1) {
			List<Org> returnBaseOrg = new ArrayList<Org>();
			getParentBaseOrg(orgAndOrgInfo.getParentId(), returnBaseOrg);

			// 判断部门名称是否重复
			List<Org> allList = orgDao.allList();
			List<Org> sonOrgList = new ArrayList<Org>();

			getOrgTree(allList, returnBaseOrg.get(0).getRowId(), sonOrgList, null);
			for (Org org : sonOrgList) {
				if (orgAndOrgInfo.getBaseOrDeptShortName().equals(org.getBaseOrDeptShortName())) {
					AppException.create(500003); // 新增部门简称已存在
				}
				if (orgAndOrgInfo.getBaseOrDeptName().equals(org.getBaseOrDeptName())) {
					AppException.create(500004); // 新增部门名称已存在
				}
			}
		}

		Org org = new Org();
		OrgInfo orgInfo = new OrgInfo();
		BeanUtils.copyProperties(orgAndOrgInfo, org);
		BeanUtils.copyProperties(orgAndOrgInfo, orgInfo);
		Org queryOrgByParentId = orgDao.queryOrgByParentId(org); // 查询上级对象

		org.setCreatedTime(new Date());

		String baseOrDeptCode = queryOrgByParentId.getBaseOrDeptCode();

		Org queryMaxCountByParent = orgDao.queryMaxCountByParent(org);
		if (queryMaxCountByParent == null) {
			org.setBaseOrDeptCode(baseOrDeptCode + "001");
		} else {
			String baseOrDeptCode2 = queryMaxCountByParent.getBaseOrDeptCode();
			String newStr = baseOrDeptCode2.substring(baseOrDeptCode2.length() - 3);
			int newCount = Integer.valueOf(newStr) + 1;
			String newBaseCode = String.format(baseOrDeptCode2.substring(0, baseOrDeptCode2.length() - 3) + "%03d",
					newCount);
			org.setBaseOrDeptCode(newBaseCode);
		}

		// 修改组织架构编码生成规则
		// TODO
		// int currentOrgCode = orgDao.queryOrgCount();
		// String str = systemService.generate(OrgConstant.ORG_CODE,
		// String.valueOf(currentOrgCode));
		// if (str != null) {
		// JSONObject parseObject = JSONObject.parseObject(str);
		// if ((Integer) parseObject.get("code") == 200) {
		// String codeResult = String.valueOf(parseObject.get("data"));
		// JSONObject parseObject1 = JSONObject.parseObject(codeResult);
		// String org_code = String.valueOf(parseObject1.get("codeResult"));
		// org.setBaseOrDeptCode(org_code);
		// }
		// }

		Long levelId = queryOrgByParentId.getLevelId();
		org.setLevelId(levelId + 1);

		if (orgInfo.getDeptNum() == null) {
			orgInfo.setDeptNum(0L);
		}

		if (orgDao.add(org) > 0) {
			orgInfo.setOrgId(org.getRowId());
			if (orgInfoDao.add(orgInfo) > 0) {

				// 更新组织编制人数, 添加子部门时，需要更新所有上级组织的编制人数
				Long deptNum = orgInfo.getDeptNum();
				updateOrgDeptNum(org, deptNum);

				List<Long> parentOrgIds = new ArrayList<Long>();
				searchParentOrg(org, parentOrgIds);
				systemService.addOrgToUpdateData(parentOrgIds);

				return true;
			}
		}
		return false;

	}

	public void getParentBaseOrg(Long rowId, List<Org> returnBaseOrg) {
		Org sonOrg = orgDao.findOne(rowId);
		if (sonOrg.getIsOrg().intValue() == 0) {
			returnBaseOrg.add(sonOrg);
		} else {
			// Org findOne = orgDao.findOne(sonOrg.getParentId());
			getParentBaseOrg(sonOrg.getParentId(), returnBaseOrg);
		}

	}

	/**
	 * 更新组织编制人数, 添加子部门时，需要更新所有上级组织的编制人数
	 */
	public void updateOrgDeptNum(Org sonOrg, Long differenceValue) {
		Org parentOrg = orgDao.findOne(sonOrg.getParentId());
		if (parentOrg != null) {
			OrgInfo parentOrgInfo = orgInfoDao.findOneByOrgId(parentOrg.getRowId());
			parentOrgInfo.setDeptNum(parentOrgInfo.getDeptNum() + differenceValue); // 添加子部门更新上级组织的编制人数
			orgInfoDao.updateByOrgId(parentOrgInfo);

			updateOrgDeptNum(parentOrg, differenceValue);
		}

	}

	/**
	 * 查询org所有上级组织
	 * 
	 * @param org
	 * @param orgIds
	 */
	public void searchParentOrg(Org org, List<Long> orgIds) {
		Org parentOrg = orgDao.findOne(org.getParentId());
		if (parentOrg != null) {
			orgIds.add(parentOrg.getRowId());
			searchParentOrg(parentOrg, orgIds);
		}
	}

	@Transactional
	public void del(Long orgId) {

		if (orgDao.delById(orgId) > 0) {
			if (orgInfoDao.delByOrgId(orgId) > 0) {

			}
		}
	}

	@Transactional
	public void delOrgAndsonOrg(Long orgId) {

		Org org = orgDao.findOne(orgId);
		List<Org> allList = orgDao.allList();
		List<Org> orgAndsonOrg = new ArrayList<Org>();
		orgAndsonOrg.add(org);

		getOrgTree(allList, orgId, orgAndsonOrg, null);

		for (Org org2 : orgAndsonOrg) {
			String queryBaseInfo = staffService.queryBaseInfo(org2.getRowId());
			if (queryBaseInfo != null) {
				JSONObject parseObject = JSONObject.parseObject(queryBaseInfo);
				if ((int) parseObject.get("code") == 200) {
					int staffCount = (Integer) parseObject.get("data");
					if (staffCount > 0) {
						AppException.create(500001);
						break;
					}
				}
			}
		}

		Org findOne = orgDao.findOne(orgId);
		OrgInfo findOneByOrgId = orgInfoDao.findOneByOrgId(orgId);
		updateOrgDeptNum(findOne, 0 - findOneByOrgId.getDeptNum()); // 更新上级的编制人数
		// 所有子机构下都没有员工
		for (Org org2 : orgAndsonOrg) {
			del(org2.getRowId());
		}

	}

	/**
	 * 查询一个组织架构
	 * 
	 * @param orgId
	 * @return
	 */
	public OrgAndOrgInfo queryOne(Long orgId) {

		Org org = orgDao.findOne(orgId);
		OrgInfo orgInfo = orgInfoDao.findOneByOrgId(orgId);

		OrgAndOrgInfo orgAndOrgInfo = new OrgAndOrgInfo();

		if (orgInfo != null) {
			BeanUtils.copyProperties(orgInfo, orgAndOrgInfo);
		}
		if (org != null) {
			BeanUtils.copyProperties(org, orgAndOrgInfo);
			orgAndOrgInfo.setReportSuperior("无上级");
			if (org.getParentId().longValue() != 0) {
				Org findOne = orgDao.findOne(org.getParentId());
				orgAndOrgInfo.setReportSuperior(findOne.getBaseOrDeptName());
			}

			// 计算在编人数，超编人数，缺编人数
			Map<String, Integer> totalStaffNumMap = getTotalStaffNum(orgId);
			Integer totalStaffNum = totalStaffNumMap.get("totalStaffNum");
			Integer sonOrgNum = totalStaffNumMap.get("sonOrgNum");
			orgAndOrgInfo.setSonOrgNum(sonOrgNum); // 设置子机构个数

			orgAndOrgInfo.setTotalStaffNum(Long.valueOf(totalStaffNum));// 在编人数
			Long vacancyNum = orgAndOrgInfo.getDeptNum() - totalStaffNum;
			orgAndOrgInfo.setVacancyNum(vacancyNum > 0 ? vacancyNum : 0);// 缺编人数
			orgAndOrgInfo.setExcessNum(vacancyNum < 0 ? Math.abs(vacancyNum) : 0);// 超编人数

			if (!StringUtils.isEmpty(orgAndOrgInfo.getLeaderNo())) {

				String baseInfoByStaffNo = staffService.getBaseInfoByStaffNo(orgAndOrgInfo.getLeaderNo());
				if (baseInfoByStaffNo != null) {
					JSONObject parseObject = JSONObject.parseObject(baseInfoByStaffNo);
					if ((int) parseObject.get("code") == 200) {
						BaseInfo baseInfo = parseObject.getObject("data", BaseInfo.class);
						if (baseInfo != null) {
							orgAndOrgInfo.setLeaderName(baseInfo.getStaffName());
							if (!orgAndOrgInfo.getLeaderName().equals(baseInfo.getStaffName())) {
								update(orgAndOrgInfo); // 负责人姓名被修改，需要更新orgInfo表中的负责人姓名
							}
						}
					}
				}
			}

			return orgAndOrgInfo;
		}

		return null;
	}

	/**
	 * 返回该id下所有组织的在编人数,子机构个数的map集合
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	public Map<String, Integer> getTotalStaffNum(Long id) {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		Org org = orgDao.findOne(id);
		List<Org> OrgAndAllsonOrg = new ArrayList<Org>();
		OrgAndAllsonOrg.add(org);
		List<Org> allList = orgDao.allList();
		Map<String, Integer> recordNum = new HashMap<String, Integer>();
		recordNum.put("sonOrgNum", 0);
		getOrgTree(allList, org.getRowId(), OrgAndAllsonOrg, recordNum);
		returnMap.put("sonOrgNum", recordNum.get("sonOrgNum"));

		List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = staffService.queryCountBaseInfoByDeptId();
		if (queryCountBaseInfoByDeptId == null) {
			AppException.create(500016);
		}

		int totalStaffNum = 0;
		for (Org org2 : OrgAndAllsonOrg) {
			try {

				Long rowId2 = org2.getRowId();
				// String jsonStr = staffService.queryBaseInfo(rowId2);
				//
				// if (jsonStr != null) {
				// JSONObject parseObject = JSONObject.parseObject(jsonStr);
				// if ((int) parseObject.get("code") == 200) {
				// int staffCount = (Integer) parseObject.get("data");
				// totalStaffNum += staffCount;
				// }
				// }

				for (QueryCountBaseInfoByDeptId temp : queryCountBaseInfoByDeptId) {
					if (temp.getDeptId().longValue() == org2.getRowId().longValue()) {
						totalStaffNum += temp.getCount();
						break;
					}
				}

			} catch (Exception e) {
				// e.printStackTrace();
			}

		}
		returnMap.put("totalStaffNum", totalStaffNum);

		return returnMap;
	}

	/**
	 * 获取org rowId下的所有的组织集合
	 */
	private void getOrgTree(List<Org> list, Long id, List<Org> returnList, Map<String, Integer> map) {
		for (Org org : list) {
			Long parentId = org.getParentId();
			if (org.getParentId().longValue() == id.longValue()) {
				returnList.add(org);
				if (map != null) {
					map.put("sonOrgNum", map.get("sonOrgNum") + 1);
				}

				getOrgTree(list, org.getRowId(), returnList, map);
			}
		}

	}

	/**
	 * 更新org,orgInfo
	 * 
	 * @param orgAndOrgInfo
	 * @return
	 */
	@Transactional
	public boolean update(OrgAndOrgInfo orgAndOrgInfo) {
		Org dbOrg = orgDao.findOne(orgAndOrgInfo.getRowId());

		/**
		 * 校验组织名称和组织简称是否重复
		 */
		if (dbOrg.getIsOrg().intValue() == 0) {
			if (!dbOrg.getBaseOrDeptName().equals(orgAndOrgInfo.getBaseOrDeptName())) {
				int queryBaseCountByName = orgDao.queryBaseCountByName(orgAndOrgInfo.getBaseOrDeptName());
				if (queryBaseCountByName > 0) {
					AppException.create(500002); // 基地名称已存在
				}
			}
		} else if (dbOrg.getIsOrg().intValue() == 1) {
			List<Org> allList = orgDao.allList();
			List<Org> sonOrgList = new ArrayList<Org>();
			List<Org> returnBaseOrg = new ArrayList<Org>();
			getParentBaseOrg(dbOrg.getRowId(), returnBaseOrg);
			getOrgTree(allList, returnBaseOrg.get(0).getRowId(), sonOrgList, null);
			if (!dbOrg.getBaseOrDeptName().equals(orgAndOrgInfo.getBaseOrDeptName())) {
				// 判断部门名称是否重复
				for (Org org : sonOrgList) {
					if (orgAndOrgInfo.getBaseOrDeptName().equals(org.getBaseOrDeptName())) {
						AppException.create(500004); // 部门名称已存在
					}
				}

			}

			if (!dbOrg.getBaseOrDeptShortName().equals(orgAndOrgInfo.getBaseOrDeptShortName())) {
				// 判断部门简称是否重复
				for (Org org : sonOrgList) {
					if (orgAndOrgInfo.getBaseOrDeptShortName().equals(org.getBaseOrDeptShortName())) {
						AppException.create(500003); // 部门简称已存在
					}
				}
			}

		}

		Org org = new Org();
		OrgInfo orgInfo = new OrgInfo();
		BeanUtils.copyProperties(orgAndOrgInfo, org);
		BeanUtils.copyProperties(orgAndOrgInfo, orgInfo);

		orgDao.update(org); // 更新org表
		orgInfo.setOrgId(org.getRowId());
		OrgInfo byOrgId = orgInfoDao.findOneByOrgId(orgInfo.getOrgId());
		if (byOrgId != null) {
			if (dbOrg.getIsOrg().intValue() == 1) {
				// 获取以前的编制人数
				Long oldDeptNum = byOrgId.getDeptNum();
				Long newDeptNum = orgInfo.getDeptNum();
				Long differenceValue = null;
				if (newDeptNum == null) {
					newDeptNum = 0L;
				}

				if (oldDeptNum.longValue() != newDeptNum.longValue()) {
					differenceValue = newDeptNum - oldDeptNum;
				}

				if (differenceValue != null) {
					// 更新所有上级组织的编制人数
					Org findOne = orgDao.findOne(org.getRowId());
					updateOrgDeptNum(findOne, differenceValue);
				}
			}
			if (orgInfoDao.updateByOrgId(orgInfo) > 0) {
				return true;
			}
		} else {
			return true;
		}

		return true;
	}

	/**
	 * 查询组织架构树形结构，没有禁用
	 * 
	 * @return
	 */
	@Transactional
	public JSONArray findOrgChart() {

		List<Long> deptIdList = RequestContext.get().getRoleAuthorityDeptIdList();
		deptIdList.add(1L);
		List<Org> allList = orgDao.findListByRowIds(deptIdList);
		List<OrgInfo> orgInfoAllList = orgInfoDao.findListByOrgIds(deptIdList);

		// List<Org> allList = orgDao.allList();
		// List<OrgInfo> orgInfoAllList = orgInfoDao.allList();
		JSONArray orgTreeData = null;
		if (allList != null) {
			List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = staffService.queryCountBaseInfoByDeptId();
			if (queryCountBaseInfoByDeptId == null) {
				AppException.create(500016);
			}

			orgTreeData = getOrgTreeData(allList, 0L, orgInfoAllList, queryCountBaseInfoByDeptId);
		}
		return orgTreeData;
	}

	/**
	 * 查询组织架构树形结构，没有禁用
	 * 
	 * @return
	 */
	@Transactional
	public JSONArray findAllOrgChart() {

		List<Org> allList = orgDao.allList();
		List<OrgInfo> orgInfoAllList = orgInfoDao.allList();
		JSONArray orgTreeData = null;
		if (allList != null) {
			List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = staffService.queryCountBaseInfoByDeptId();
			if (queryCountBaseInfoByDeptId == null) {
				AppException.create(500016);
			}
			orgTreeData = getOrgTreeData(allList, 0L, orgInfoAllList, queryCountBaseInfoByDeptId);
		}
		return orgTreeData;
	}

	/**
	 * 生成树形结构
	 * 
	 * @param list
	 *            全部资源
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private JSONArray getOrgTreeData(List<Org> list, Long id, List<OrgInfo> orgInfoAllList,
			List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId) {

		if (queryCountBaseInfoByDeptId == null) {
			AppException.create(500016);
		}
		JSONArray jsonArr = new JSONArray();
		for (Org org : list) {
			if (org.getParentId().longValue() == id.longValue()) {
				JSONObject json = new JSONObject();
				json.put("title", org.getBaseOrDeptName());
				json.put("key", org.getRowId());
				json.put("value", org.getRowId());
				json.put("isOrg", org.getIsOrg());

				List<Org> OrgAndAllsonOrg = new ArrayList<Org>();
				OrgAndAllsonOrg.add(org);
				Map<String, Integer> recordNum = new HashMap<String, Integer>();
				recordNum.put("sonOrgNum", 0);
				getOrgTree(list, org.getRowId(), OrgAndAllsonOrg, recordNum);

				int totalStaffNum = 0;
				for (Org org2 : OrgAndAllsonOrg) {
					for (QueryCountBaseInfoByDeptId temp : queryCountBaseInfoByDeptId) {
						if (temp.getDeptId().longValue() == org2.getRowId().longValue()) {
							totalStaffNum += temp.getCount();
							break;
						}
					}
				}
				json.put("totalStaffNum", totalStaffNum); // 在编人数
				// OrgInfo findOneByOrgId = orgInfoDao.findOneByOrgId(org.getRowId());
				OrgInfo findOneByOrgId = null;
				for (OrgInfo orgInfo : orgInfoAllList) {
					if (orgInfo.getOrgId().longValue() == org.getRowId().longValue()) {
						findOneByOrgId = orgInfo;
						break;
					}
				}
				if (findOneByOrgId == null) {
					json.put("deptNum", 0); // 编制人数
				} else {
					json.put("deptNum", findOneByOrgId.getDeptNum() != null ? findOneByOrgId.getDeptNum() : 0); // 编制人数
					if (findOneByOrgId.getDeptNum() != null) {
						Long vacancyNum = findOneByOrgId.getDeptNum() - totalStaffNum;
						json.put("vacancyNum", vacancyNum > 0 ? vacancyNum : 0); // 缺编人数
						json.put("excessNum", vacancyNum < 0 ? Math.abs(vacancyNum) : 0); // 超编人数
					}
				}

				JSONArray children = getOrgTreeData(list, org.getRowId(), orgInfoAllList, queryCountBaseInfoByDeptId);
				if (children.size() > 0) {
					json.put("children", children);
				}
				jsonArr.add(json);
			}
		}
		return jsonArr;
	}

	/**
	 * 查询组织架构树形结构,包含禁用disableId下的所有子组织架构
	 * 
	 * @param disableId
	 *            禁用id
	 * @return
	 */
	@Transactional
	public JSONArray findOrgChartByDisabled(Long disableId) {
		List<Org> allList = orgDao.allList();
		JSONArray orgTreeData = null;
		if (allList != null) {
			List<Org> OrgAndAllsonOrg = new ArrayList<Org>();
			Org findOne = orgDao.findOne(disableId);
			if (findOne != null) {
				OrgAndAllsonOrg.add(findOne);
				Map<String, Integer> recordNum = new HashMap<String, Integer>();
				recordNum.put("sonOrgNum", 0);
				getOrgTree(allList, findOne.getRowId(), OrgAndAllsonOrg, recordNum);

				orgTreeData = getOrgTreeDataByDisabled(allList, 0L, OrgAndAllsonOrg);
			} else {
				return orgTreeData;
			}

		}
		return orgTreeData;
	}

	/**
	 * 生产树形结构 包含禁用
	 * 
	 * @param list
	 *            全部资源
	 * @param disabledList
	 *            禁用资源
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private JSONArray getOrgTreeDataByDisabled(List<Org> list, Long id, List<Org> disabledList) {
		JSONArray jsonArr = new JSONArray();
		for (Org org : list) {
			if (org.getParentId().longValue() == id.longValue()) {
				JSONObject json = new JSONObject();
				json.put("title", org.getBaseOrDeptName());
				json.put("key", org.getRowId());
				json.put("value", org.getRowId());
				json.put("isOrg", org.getIsOrg());

				if (disabledList != null) {
					for (Org org2 : disabledList) {
						if (org.getRowId().longValue() == org2.getRowId().longValue()) {
							json.put("disabled", true);
							break;
						}
					}
				}

				JSONArray children = getOrgTreeDataByDisabled(list, org.getRowId(), disabledList);
				if (children.size() > 0) {
					json.put("children", children);
				}
				jsonArr.add(json);
			}
		}
		return jsonArr;
	}

	@Transactional
	public JSONObject isRepeatOrIsAbsent(List<Long> ids) {
		JSONObject result = new JSONObject();
		for (int i = 0; i < ids.size() - 1; i++) {
			long temp = ids.get(i);
			for (int j = i + 1; j < ids.size(); j++) {
				if (temp == (ids.get(j))) {
					// ids中有id重复
					result.put("isRepeatOrIsAbsent", Constant.RepeatOrAbsent);
					return result;
				}
			}
		}

		JSONObject ids2 = new JSONObject();
		for (Long long1 : ids) {
			OrgAndOrgInfo orgAndOrgInfo = queryOne(long1);
			if (orgAndOrgInfo == null) {
				// ids中有id不存在
				JSONObject ids3 = new JSONObject();
				ids3.put("isRepeatOrIsAbsent", Constant.RepeatOrAbsent);
				return ids3;
			} else {

				ids2.put(String.valueOf(orgAndOrgInfo.getRowId()), orgAndOrgInfo.getBaseOrDeptName());
			}
		}

		// ids中有id无重复且都存在
		result.put("isRepeatOrIsAbsent", !Constant.RepeatOrAbsent);
		result.put("id--name", ids2);

		return result;
	}

	@Transactional
	public boolean orgMerge(Long sourceId, Long targetId) {
		Org org1 = orgDao.findOne(sourceId);
		List<Org> allList = orgDao.allList();
		List<Org> orgAndsonOrg = new ArrayList<Org>();
		orgAndsonOrg.add(org1);

		getOrgTree(allList, sourceId, orgAndsonOrg, null);

		for (Org org2 : orgAndsonOrg) {
			String queryBaseInfo = staffService.queryBaseInfo(org2.getRowId());
			if (queryBaseInfo != null) {
				JSONObject parseObject = JSONObject.parseObject(queryBaseInfo);
				if ((int) parseObject.get("code") == 200) {
					int staffCount = (Integer) parseObject.get("data");
					if (staffCount > 0) {
						AppException.create(500015);
						break;
					}
				}
			} else {
				AppException.create(500014); // 查询部门下在职员工个数失败
			}
		}

		Org sourceOrg = orgDao.findOne(sourceId);
		Org targetOrg = orgDao.findOne(targetId);

		OrgInfo sourceOrgInfo = orgInfoDao.findOneByOrgId(sourceOrg.getRowId());
		Long deptNum = sourceOrgInfo.getDeptNum();
		OrgInfo targetOrgInfo = orgInfoDao.findOneByOrgId(targetOrg.getRowId());
		targetOrgInfo.setDeptNum(targetOrgInfo.getDeptNum() + deptNum);
		orgInfoDao.updateByOrgId(targetOrgInfo);
		updateOrgDeptNum(targetOrg, deptNum); // 更新targetOrg所有上级的编制人数

		updateOrgDeptNum(sourceOrg, 0 - deptNum); // 更新sourceOrg所有上级的编制人数

		if (orgDao.delById(sourceId) > 0) {

			List<Org> listByParentId = orgDao.findListByParentId(sourceId);
			Org findOne = orgDao.findOne(targetId);
			if (listByParentId != null) {
				for (Org org : listByParentId) {

					org.setParentId(targetId);
					org.setLevelId(findOne.getLevelId() + 1);
					if (orgDao.update(org) <= 0) {
						return false;
					}
					updateOrgLevelId(org); // 更新树形结构的层次 levelId
				}
			}
		}

		// 把sourceId组织下第一层员工挂到targetId组织
		String jsonStr = staffService.orgMerge(sourceId, targetId);

		if (jsonStr != null) {
			JSONObject parseObject = JSONObject.parseObject(jsonStr);
			if ((Integer) parseObject.get("code") != 200) {
				// 调用staff接口失败

			}
		}

		return true;
	}

	@Transactional
	public boolean orgMove(Long sourceId, Long targetId) {

		Org org1 = orgDao.findOne(sourceId);
		List<Org> allList = orgDao.allList();
		List<Org> orgAndsonOrg = new ArrayList<Org>();
		orgAndsonOrg.add(org1);

		getOrgTree(allList, sourceId, orgAndsonOrg, null);

		for (Org org2 : orgAndsonOrg) {
			String queryBaseInfo = staffService.queryBaseInfo(org2.getRowId());
			if (queryBaseInfo != null) {
				JSONObject parseObject = JSONObject.parseObject(queryBaseInfo);
				if ((int) parseObject.get("code") == 200) {
					int staffCount = (Integer) parseObject.get("data");
					if (staffCount > 0) {
						AppException.create(500013);
						break;
					}
				}
			} else {
				AppException.create(500014); // 查询部门下在职员工个数失败
			}
		}

		Org sourceOrg = orgDao.findOne(sourceId);

		Org targetOrg = orgDao.findOne(targetId);
		if (sourceOrg != null) {
			OrgInfo sourceOrgInfo = orgInfoDao.findOneByOrgId(sourceOrg.getRowId());
			Long deptNum = sourceOrgInfo.getDeptNum();
			OrgInfo targetOrgInfo = orgInfoDao.findOneByOrgId(targetOrg.getRowId());
			targetOrgInfo.setDeptNum(targetOrgInfo.getDeptNum() + deptNum);
			orgInfoDao.updateByOrgId(targetOrgInfo);
			updateOrgDeptNum(targetOrg, deptNum); // 更新targetOrg所有上级的编制人数

			updateOrgDeptNum(sourceOrg, 0 - deptNum); // 更新sourceOrg所有上级的编制人数

			sourceOrg.setParentId(targetId);
			sourceOrg.setLevelId(targetOrg.getLevelId() + 1);
			if (orgDao.update(sourceOrg) > 0) {

				updateOrgLevelId(sourceOrg);
				return true;
			}
		}

		return false;
	}

	/**
	 * 更新树形结构的层次 levelId
	 * 
	 * @param org
	 */
	public void updateOrgLevelId(Org org) {
		List<Org> findListByParentId = orgDao.findListByParentId(org.getRowId());
		if (findListByParentId != null && !findListByParentId.isEmpty()) {
			for (Org org2 : findListByParentId) {
				org2.setLevelId(org.getLevelId() + 1);
				orgDao.update(org2);
				updateOrgLevelId(org2);
			}
		}

	}

	@Transactional
	public List<Org> getBaseList() {
		List<Org> allList = orgDao.allList();
		List<Org> baseList = new ArrayList<Org>();
		if (allList != null) {
			for (Org org : allList) {
				if (org.getIsOrg().intValue() == 0) { // 判断组织是基地
					baseList.add(org);
				}
			}
		}
		if (baseList.isEmpty()) {
			return null;
		} else {
			return baseList;
		}
	}

	public List<Org> getDeptList() {
		List<Org> allList = orgDao.allList();
		List<Org> baseList = new ArrayList<Org>();
		if (allList != null) {
			for (Org org : allList) {
				if (org.getIsOrg().intValue() == 1) { // 判断组织是部门
					baseList.add(org);
				}
			}
		}
		if (baseList.isEmpty()) {
			return null;
		} else {
			return baseList;
		}
	}

	/**
	 * 获取所有组织架构
	 * 
	 * @author natyu
	 * @date 2018年7月25日 下午8:30:55
	 * @return
	 */
	public List<Org> getAllOrg() {
		return orgDao.allList();
	}

	/**
	 * 批量导入组织机构基本信息
	 * 
	 * @author jinlong
	 * @date 2018年7月9日 下午3:12:09
	 * @param list
	 */
	@Transactional
	public List<ImportOrgAndOrgInfoForm> importExcel(List<ImportOrgAndOrgInfoForm> list) {

		List<ImportOrgAndOrgInfoForm> errorBaseInfos = new ArrayList<ImportOrgAndOrgInfoForm>();

		for (ImportOrgAndOrgInfoForm orgAndOrgInfoForm : list) {
			List<Org> allList = orgDao.allList();
			String parentBaseName = orgAndOrgInfoForm.getParentBaseName().trim();
			String staffNo = orgAndOrgInfoForm.getLeaderNo();
			StringBuffer sb = new StringBuffer();
			if (!StringUtil.isEmpty(staffNo)) {
				if (!staffService.checkStaffNo(staffNo)) {
					sb.append("员工工号不存在！");
					// orgAndOrgInfoForm.setErrMsg("员工工号不存在！");
					// errorBaseInfos.add(orgAndOrgInfoForm);
				}
			}

			if (orgAndOrgInfoForm.getBaseOrDeptName().length() > 20) {
				sb.append("部门名称不能超过20个字符！");
				// orgAndOrgInfoForm.setErrMsg("部门名称不能超过20个字符！");
				// errorBaseInfos.add(orgAndOrgInfoForm);
				// continue;
			}
			if (orgAndOrgInfoForm.getBaseOrDeptShortName().length() > 20) {
				sb.append("部门简称不能超过20个字符！");
				// orgAndOrgInfoForm.setErrMsg("部门简称不能超过20个字符！");
				// errorBaseInfos.add(orgAndOrgInfoForm);
				// continue;
			}
			if (orgAndOrgInfoForm.getParentBaseName().length() > 20) {
				sb.append("所属基地名称不能超过20个字符！");
				// orgAndOrgInfoForm.setErrMsg("所属基地名称不能超过20个字符！");
				// errorBaseInfos.add(orgAndOrgInfoForm);
				// continue;
			}
			if (orgAndOrgInfoForm.getParentOrgname().length() > 20) {
				sb.append("上级组织机构名称不能超过20个字符！");
				// orgAndOrgInfoForm.setErrMsg("上级组织机构名称不能超过20个字符！");
				// errorBaseInfos.add(orgAndOrgInfoForm);
				// continue;
			}

			if (!StringUtils.isEmpty(sb.toString())) {
				orgAndOrgInfoForm.setErrMsg(sb.toString());
				errorBaseInfos.add(orgAndOrgInfoForm);
				continue;
			}

			boolean baseExist = false; // 基地是否存在
			for (Org org : allList) {

				if (org.getIsOrg().intValue() == 0 || org.getIsOrg().intValue() == -1) {
					// 基地
					if (org.getBaseOrDeptName().equals(parentBaseName)) {
						// 基地存在
						baseExist = true;
						String parentOrgname = orgAndOrgInfoForm.getParentOrgname().trim();
						boolean deptExist = false; // 部门是否存在
						List<Org> OrgAndAllsonOrg = new ArrayList<Org>();
						OrgAndAllsonOrg.add(org);// 特殊情况：所属基地就是上级组织
						Map<String, Integer> recordNum = new HashMap<String, Integer>();
						recordNum.put("sonOrgNum", 0);
						getOrgTree(allList, org.getRowId(), OrgAndAllsonOrg, recordNum); // 获取基地下面的所有层级的部门

						boolean parentOrgExist = false;
						for (Org org2 : OrgAndAllsonOrg) { // 判断基地下面的所有部门中是否有parentOrgname
							if (org2.getBaseOrDeptName().equals(parentOrgname)) {
								parentOrgExist = true;
								List<Org> OrgAndAllsonOrg2 = new ArrayList<Org>();
								OrgAndAllsonOrg2.add(org2);
								Map<String, Integer> recordNum2 = new HashMap<String, Integer>();
								recordNum2.put("sonOrgNum", 0);
								getOrgTree(allList, org2.getRowId(), OrgAndAllsonOrg2, recordNum2);

								OrgAndOrgInfo orgAndOrgInfo = new OrgAndOrgInfo();
								if (!StringUtils.isEmpty(staffNo)) {

									// 根据员工编号staffNo查询员工姓名
									String baseInfoByStaffNo = staffService.getBaseInfoByStaffNo(staffNo);
									if (baseInfoByStaffNo != null) {
										JSONObject parseObject = JSONObject.parseObject(baseInfoByStaffNo);
										if ((int) parseObject.get("code") == 200) {
											BaseInfo baseInfo = parseObject.getObject("data", BaseInfo.class);
											orgAndOrgInfo.setLeaderName(baseInfo.getStaffName());
										}
									}
								}

								BeanUtils.copyProperties(orgAndOrgInfoForm, orgAndOrgInfo);
								orgAndOrgInfo.setIsOrg(1);
								orgAndOrgInfo.setParentId(org2.getRowId());
								for (Org org3 : OrgAndAllsonOrg2) {
									if (org3.getBaseOrDeptName().equals(orgAndOrgInfoForm.getBaseOrDeptName().trim())) {
										deptExist = true;
										// parentId = org2.getRowId(); 部门存在 执行更新orgAndOrgInfoForm
										orgAndOrgInfo.setRowId(org3.getRowId());
										orgAndOrgInfo.setLevelId(org2.getLevelId() + 1);

										boolean shortNameRepetition = false; // 部门简称是否重复
										if (!org3.getBaseOrDeptShortName()
												.equals(orgAndOrgInfo.getBaseOrDeptShortName())) {
											// 判断部门名称是否重复
											List<Org> sonOrgList = new ArrayList<Org>();
											getOrgTree(allList, org.getRowId(), sonOrgList, null);
											for (Org org1 : sonOrgList) {
												if (orgAndOrgInfo.getBaseOrDeptShortName()
														.equals(org1.getBaseOrDeptShortName())) {
													orgAndOrgInfoForm.setErrMsg("部门简称已存在！");
													errorBaseInfos.add(orgAndOrgInfoForm);
													shortNameRepetition = true;
													break;
												}
											}
										}

										if (!shortNameRepetition) { // 部门简称不重复
											update(orgAndOrgInfo); // 更新部门
										}

										break;

									}
								}
								if (!deptExist) {
									// 部门不存在 执行新增 orgAndOrgInfoForm
									// parentId =org2.getRowId();
									orgAndOrgInfo.setLevelId(org2.getLevelId() + 1);
									orgAndOrgInfo.setIsValid(0); // 新增部门设为有效

									// 判断部门名称是否重复
									boolean shortNameRepetition = false; // 部门简称是否重复
									List<Org> sonOrgList = new ArrayList<Org>();
									getOrgTree(allList, org.getRowId(), sonOrgList, null);
									for (Org org1 : sonOrgList) {
										if (orgAndOrgInfo.getBaseOrDeptShortName()
												.equals(org1.getBaseOrDeptShortName())) {
											orgAndOrgInfoForm.setErrMsg("部门简称已存在！");
											errorBaseInfos.add(orgAndOrgInfoForm);
											shortNameRepetition = true;
											break;
										}
									}
									if (!shortNameRepetition) {
										add(orgAndOrgInfo); // 添加部门
									}
									break;

								}
								break;
							}
						}

						if (!parentOrgExist) {
							// 上级组织机构不存在
							orgAndOrgInfoForm.setErrMsg("上级组织机构不存在！");
							errorBaseInfos.add(orgAndOrgInfoForm);
						}

						break;
					}
				}
			}
			if (!baseExist) {
				// 所属基地不存在
				orgAndOrgInfoForm.setErrMsg("所属基地不存在！");
				errorBaseInfos.add(orgAndOrgInfoForm);
			}

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
	public void exportErrExcel(HttpServletResponse response, List<ImportOrgAndOrgInfoForm> list, String lang) {

		// 表头
		List<String> headerList = new ArrayList<String>();
		// field
		List<String> fieldList = new ArrayList<String>();

		fieldList.add("baseOrDeptName");
		fieldList.add("baseOrDeptShortName");
		fieldList.add("establishDate");
		// fieldList.add("effectiveDate");
		fieldList.add("parentBaseName");
		fieldList.add("parentOrgname");
		// fieldList.add("address");

		fieldList.add("leaderNo");
		// fieldList.add("leaderName");
		fieldList.add("deptNum");
		fieldList.add("errMsg");

		System.out.println("lang:" + lang);

		String title = "";
		int size = list.size();
		String[] headers = new String[size];

		if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
			headerList.add("部门名称");
			headerList.add("部门简称");

			headerList.add("成立日期");
			// headerList.add("生效日期");
			headerList.add("所属基地名称");
			headerList.add("上级组织机构名称");

			// headerList.add("地点");
			headerList.add("总负责人-工号");
			// headerList.add("总负责人-姓名");
			headerList.add("编制人数");
			headerList.add("错误描述");

			title = "员工错误信息";

			headers = (String[]) headerList.toArray(new String[0]);

		} else if (lang.equalsIgnoreCase(Constant.EN)) {

			headerList.add("部门名称");
			headerList.add("成立日期");
			// headerList.add("生效日期");
			headerList.add("所属基地名称");
			headerList.add("上级组织机构名称");

			// headerList.add("地点");
			headerList.add("总负责人-工号");
			// headerList.add("总负责人-姓名");
			headerList.add("编制人数");
			headerList.add("Error Description");
			// TODO
			title = "Staff Base Information";
			headers = (String[]) headerList.toArray(new String[0]);
		}

		String[] fields = (String[]) fieldList.toArray(new String[0]);

		// 生成Excel表格
		ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
	}

	/**
	 * 导出Excel
	 * 
	 * @date 2018年7月30日 上午11:23:48
	 * @param response
	 */
	public void exportExcel(HttpServletResponse response) {

		// 查询数据
		List<OrgAndOrgInfo> list = orgDao.queryOrgAndOrgInfoList();
		for (OrgAndOrgInfo orgAndOrgInfo : list) {
			Org parentOrg = orgDao.findOne(orgAndOrgInfo.getParentId());
			if (parentOrg != null) {
				orgAndOrgInfo.setReportSuperior(parentOrg.getBaseOrDeptName());
			} else {
				orgAndOrgInfo.setReportSuperior("无上级");
			}

			if (orgAndOrgInfo.getIsOrg().intValue() == -1) {
				orgAndOrgInfo.setOrgProperty("集团");
			} else if (orgAndOrgInfo.getIsOrg().intValue() == 0) {
				orgAndOrgInfo.setOrgProperty("基地");
			} else {
				orgAndOrgInfo.setOrgProperty("部门");
			}
		}

		// 表头
		List<String> headerList = new ArrayList<String>();
		// field
		List<String> fieldList = new ArrayList<String>();

		fieldList.add("baseOrDeptName");
		fieldList.add("baseOrDeptCode");
		fieldList.add("establishDate");
		// fieldList.add("effectiveDate");
		fieldList.add("reportSuperior");
		// fieldList.add("address");
		fieldList.add("leaderNo");
		fieldList.add("leaderName");
		fieldList.add("deptNum");
		fieldList.add("orgProperty");

		String title = "";
		int size = list.size();
		String[] renders = new String[0];
		String[] headers = new String[size];

		headerList.add("名称");
		headerList.add("编号");
		headerList.add("成立日期");
		// headerList.add("生效日期");
		headerList.add("上级组织名称");
		// headerList.add("地址");
		headerList.add("总负责人-工号");
		headerList.add("总负责人-姓名");
		headerList.add("编制人数");
		headerList.add("基地|部门");

		title = "组织架构信息";

		// 自定义渲染
		// renders = new String[] {"isOrg:-1-总公司,0-基地,1-部门"};

		headers = (String[]) headerList.toArray(new String[0]);

		// pojo字段
		String[] fields = (String[]) fieldList.toArray(new String[0]);

		// 生成Excel表格
		ExportExcelUtil.exportExcel(response, list, title, headers, fields, renders);
	}

	/**
	 * 备份数据 存放历史数据
	 */
	@Transactional
	public void backUpOrgAllInfo() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = simpleDateFormat.format(new Date());
		Date date = null;
		try {
			date = simpleDateFormat.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<OrgAllBackup> OrgAllBackupList = orgAllBackupService.findByBackUpTime(date); // 查询当天是否已经存在备份的数据
		if (OrgAllBackupList != null && !OrgAllBackupList.isEmpty()) {
			// 已备份数据就删除
			orgAllBackupService.del(date);
		}

		List<OrgAndOrgInfo> OrgAndOrgInfoList = orgDao.queryOrgAndOrgInfoList();

		if (OrgAndOrgInfoList != null) {
			List<OrgAllBackup> OrgAllbackupList = new ArrayList<OrgAllBackup>();
			for (OrgAndOrgInfo orgAndOrgInfo : OrgAndOrgInfoList) {
				OrgAllBackup orgAllBackup = new OrgAllBackup();
				BeanUtils.copyProperties(orgAndOrgInfo, orgAllBackup);
				orgAllBackup.setBackUpTime(date);
				OrgAllbackupList.add(orgAllBackup);
			}
			List<Org> allList = orgDao.allList();
			Map<Long, Integer> mapRecords = new HashMap<Long, Integer>();
			getOrgZBRSHistoryData(allList, 0L, mapRecords);
			for (OrgAllBackup orgAllBackup : OrgAllbackupList) {
				Integer totalStaffNum = mapRecords.get(orgAllBackup.getRowId()); // 获取在编人数
				orgAllBackup.setTotalStaffNum(totalStaffNum);
			}

			orgAllBackupService.addList(OrgAllbackupList);
		}

	}

	/**
	 * 备份时查询多个部门下的在编人数
	 * 
	 * @param list
	 * @param id
	 * @return 在编人数map集合
	 */
	@Transactional
	private void getOrgZBRSHistoryData(List<Org> list, Long id, Map<Long, Integer> mapRecords) {
		List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = staffService.queryCountBaseInfoByDeptId();
		if (queryCountBaseInfoByDeptId == null || queryCountBaseInfoByDeptId.size() == 0) {
			AppException.create(500016);
		}
		for (Org org : list) {
			if (org.getParentId().longValue() == id.longValue()) {

				List<Org> OrgAndAllsonOrg = new ArrayList<Org>();
				OrgAndAllsonOrg.add(org);
				Map<String, Integer> recordNum = new HashMap<String, Integer>();
				recordNum.put("sonOrgNum", 0);
				getOrgTree(list, org.getRowId(), OrgAndAllsonOrg, recordNum);

				int totalStaffNum = 0;
				for (Org org2 : OrgAndAllsonOrg) {

					// try {
					// Long rowId2 = org2.getRowId();
					// String jsonStr = staffService.queryBaseInfo(rowId2);
					// if (jsonStr != null) {
					// JSONObject parseObject = JSONObject.parseObject(jsonStr);
					// if ((int) parseObject.get("code") == 200) {
					// int staffCount = (Integer) parseObject.get("data");
					// totalStaffNum += staffCount;
					// }
					// }
					// } catch (Exception e) {
					// // e.printStackTrace();
					// }

					for (QueryCountBaseInfoByDeptId temp : queryCountBaseInfoByDeptId) {
						if (temp.getDeptId().longValue() == org2.getRowId().longValue()) {
							totalStaffNum += temp.getCount();
							break;
						}
					}

				}
				// json.put("totalStaffNum", totalStaffNum); // 在编人数
				mapRecords.put(org.getRowId(), totalStaffNum);
				getOrgZBRSHistoryData(list, org.getRowId(), mapRecords);

			}
		}
	}

	public JSONArray findOrgHistoryChart(Date date, List<Long> deptIdList) {

		List<OrgAllBackup> allList = orgAllBackupService.findByBackUpTimeAndDeptIdList(date, deptIdList);
		JSONArray orgTreeData = null;
		if (allList != null) {
			orgTreeData = getOrgTreeHistoryData(allList, 0L);
		}
		return orgTreeData;
	}

	/**
	 * 获取历史树形结构数据
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	private JSONArray getOrgTreeHistoryData(List<OrgAllBackup> list, Long id) {
		JSONArray jsonArr = new JSONArray();
		for (OrgAllBackup orgAllBackup : list) {
			if (Long.valueOf(orgAllBackup.getParentId()).longValue() == id.longValue()) {
				JSONObject json = new JSONObject();
				json.put("title", orgAllBackup.getBaseOrDeptName());
				json.put("key", orgAllBackup.getRowId());
				json.put("value", orgAllBackup.getRowId());
				json.put("isOrg", orgAllBackup.getIsOrg());
				json.put("level", orgAllBackup.getLevelId());
				json.put("totalStaffNum", orgAllBackup.getTotalStaffNum()); // 在编人数
				json.put("deptNum", orgAllBackup.getDeptNum());

				Long vacancyNum = orgAllBackup.getDeptNum() - orgAllBackup.getTotalStaffNum();
				json.put("vacancyNum", vacancyNum > 0 ? vacancyNum : 0); // 缺编人数
				json.put("excessNum", vacancyNum < 0 ? Math.abs(vacancyNum) : 0); // 超编人数

				JSONArray children = getOrgTreeHistoryData(list, orgAllBackup.getRowId());
				if (children.size() > 0) {
					json.put("children", children);
				}
				jsonArr.add(json);
			}
		}
		return jsonArr;
	}

	/**
	 * 获取层次图树形数据
	 * 
	 * @return
	 */
	public JSONArray findOrgHierarchyChart() {
		List<Org> allList = orgDao.allList();
		JSONArray orgHierarchyChart = null;
		if (allList != null) {
			orgHierarchyChart = getOrgHierarchyChart(allList, 0L);
		}
		return orgHierarchyChart;

	}

	private JSONArray getOrgHierarchyChart(List<Org> list, Long id) {
		JSONArray jsonArr = new JSONArray();
		for (Org org : list) {
			if (org.getParentId().longValue() == id.longValue()) {
				JSONObject json = new JSONObject();
				json.put("name", org.getBaseOrDeptName());
				json.put("id", org.getRowId());
				json.put("value", org.getRowId());
				json.put("level", org.getLevelId());

				OrgInfo orgInfo = orgInfoDao.findOneByOrgId(org.getRowId());

				json.put("leaderNo", orgInfo.getLeaderNo());
				json.put("leaderName", orgInfo.getLeaderName());
				Map<String, Integer> totalStaffNumMap = getTotalStaffNum(org.getRowId());
				Integer totalStaffNum = totalStaffNumMap.get("totalStaffNum");

				json.put("totalStaffNum", totalStaffNum);// 在编人数
				OrgInfo findOneByOrgId = orgInfoDao.findOneByOrgId(org.getRowId());
				json.put("deptNum", findOneByOrgId.getDeptNum()); // 编制人数

				JSONArray children = getOrgHierarchyChart(list, org.getRowId());
				if (children.size() > 0) {
					json.put("children", children);
				}
				jsonArr.add(json);
			}
		}
		return jsonArr;
	}

	/**
	 * 根据基地id,查询该基地下面的所有的部门
	 * 
	 * @param baseId
	 * @return
	 */
	public List<Org> queryAllSonOrg(Long baseId) {
		// List<Long> deptIdList = RequestContext.get().getRoleAuthorityDeptIdList();
		// deptIdList.add(1L);
		// List<Org> allList = orgDao.findListByRowIds(deptIdList);

		List<Org> allList = orgDao.allList();

		List<Org> returnList = new ArrayList<Org>();
		Map<String, Integer> recordNum = new HashMap<String, Integer>();
		recordNum.put("sonOrgNum", 0);
		getOrgTree(allList, baseId, returnList, recordNum);

		return returnList;
	}

	public JSONArray getSonOrgTree(Long baseId) {

		List<Org> allList = orgDao.allList();
		List<OrgInfo> orgInfoAllList = orgInfoDao.allList();
		List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = staffService.queryCountBaseInfoByDeptId();
		if (queryCountBaseInfoByDeptId == null) {
			AppException.create(500016);
		}
		JSONArray orgTreeData = getOrgTreeData(allList, baseId, orgInfoAllList, queryCountBaseInfoByDeptId);
		return orgTreeData;
	}

	public OrgAllBackup findOneByRowId(Long rowId, Date date) {
		OrgAllBackup findOneByRowId = orgAllBackupService.findOneByRowId(rowId, date);
		OrgAllBackup findOneByParentId = orgAllBackupService.findOneByParentId(findOneByRowId.getParentId(), date);
		if (findOneByParentId != null) {
			findOneByRowId.setReportSuperior(findOneByParentId.getBaseOrDeptName());

		} else {
			findOneByRowId.setReportSuperior("无上级");
		}

		// TODO
		// 计算在编人数，超编人数，缺编人数
		int totalStaffNum = findOneByRowId.getTotalStaffNum();
		Long vacancyNum = findOneByRowId.getDeptNum() - totalStaffNum;
		findOneByRowId.setVacancyNum(vacancyNum > 0 ? vacancyNum : 0);// 缺编人数
		findOneByRowId.setExcessNum(vacancyNum < 0 ? Math.abs(vacancyNum) : 0);// 超编人数
		return findOneByRowId;
	}

	public JSONArray findOrgHierarchyHistoryChart(Date date) {

		List<OrgAllBackup> allList = orgAllBackupService.findByBackUpTime(date);
		JSONArray orgTreeData = null;
		if (allList != null) {
			orgTreeData = getOrgHierarchyHistoryChart(allList, 0L);
		}
		return orgTreeData;
	}

	private JSONArray getOrgHierarchyHistoryChart(List<OrgAllBackup> allList, Long id) {
		JSONArray jsonArr = new JSONArray();
		for (OrgAllBackup orgAllBackup : allList) {
			if (Long.valueOf(orgAllBackup.getParentId()).longValue() == id.longValue()) {
				JSONObject json = new JSONObject();

				String getBaseOrDeptName = orgAllBackup.getBaseOrDeptName();
				json.put("name", orgAllBackup.getBaseOrDeptName());
				json.put("id", orgAllBackup.getRowId());
				json.put("value", orgAllBackup.getRowId());
				json.put("level", orgAllBackup.getLevelId());
				json.put("leaderNo", orgAllBackup.getLeaderNo());
				json.put("leaderName", orgAllBackup.getLeaderName());
				json.put("totalStaffNum", orgAllBackup.getTotalStaffNum()); // 在编人数
				json.put("deptNum", orgAllBackup.getDeptNum()); // 编制人数

				JSONArray children = getOrgHierarchyHistoryChart(allList, orgAllBackup.getRowId());
				if (children.size() > 0) {
					json.put("children", children);
				}
				jsonArr.add(json);
			}
		}
		return jsonArr;
	}

}
