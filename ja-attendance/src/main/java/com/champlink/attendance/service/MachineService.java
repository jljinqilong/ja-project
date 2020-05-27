package com.champlink.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.attendance.dao.MachineMapper;
import com.champlink.attendance.service.call.OrgService;
import com.champlink.attendance.service.call.SystemService;
import com.champlink.common.constant.AttendanceConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.attendance.Machine;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.attendance.MachineForm;
import com.champlink.common.vo.PageListVO;
import com.github.pagehelper.Page;


@Service
public class MachineService {

	@Autowired
	private MachineMapper machineDao;
	@Autowired
	private OrgService orgService;
	
	public int add(Machine machine) {
		return machineDao.insert(machine);
	}

	public int del(Long id) {
		return machineDao.deleteByPrimaryKey(id);
	}

	public Machine get(Long id) {
		
		return machineDao.selectByPrimaryKey(id);
	}

	public int update(Machine machine) {
		return machineDao.updateByPrimaryKey(machine);
	}

	public PageListVO pageList(MachineForm machineForm) {
		Paginater paginater = Paginater.newInstance(machineForm);
		Page pageList = machineDao.pageList(paginater);
		List<Machine> result = pageList.getResult();
		String baseList = orgService.baseList();
		 if(baseList != null) {
	        	JSONObject jsonObj = JSONObject.parseObject(baseList);
	        	if(jsonObj != null) {
	        		
	        		if(Integer.valueOf(jsonObj.getString("code")) == 200) {
	        			JSONArray parseArray = JSONObject.parseArray(jsonObj.getString("data"));
	        			if(parseArray != null && parseArray.size() > 0) {
	        				for (Object object : parseArray) {
	        					JSONObject jsonObjSon = (JSONObject)object;
	        					for (Machine machine : result) {
	        						Object obj = jsonObjSon.get("rowId");
	        						if(String.valueOf(jsonObjSon.get("rowId")).equals(machine.getBaseId())) {
	        							machine.setBaseId(String.valueOf(jsonObjSon.get("baseOrDeptName")));
	        						}
	        					}
	        				}
	        			}
	        			
	        		}
	        	}
	        	
	        }
		
		PageListVO pageListVO = PageListVO.newInstance(pageList);
		return pageListVO;
	}

	public List<Machine> allList() {
		List<Machine> selectAll = machineDao.selectAll();
		return selectAll;
	}
	
	
	public boolean getCountByMachineName(String machineName) {
		int machineByMachineName = machineDao.getCountByMachineName(machineName);
		if(machineByMachineName > 0) {
			return true;
		}
		return false;
	}
	
	public Machine getMachineByMachineName(String machineName) {
		return machineDao.getMachineByMachineName(machineName);
	}
	
}
