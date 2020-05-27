package com.champlink.staff.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.staff.contract.Contract;
import com.champlink.common.form.staff.contract.ContractForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.dao.contract.ContractDao;
import com.champlink.staff.service.call.OrgService;

@Service
public class ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private OrgService orgService;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Contract contract) {

        contract.setCreatedTime(new Date());
        contract.setCreatedBy(RequestContext.get().getStaffId());

        List<Contract> allList = contractDao.allList();

        for (Contract contract2 : allList) {
            if (contract2.getContractNo().equals(contract.getContractNo())) {
                AppException.create(200005);
            }
        }

        //查询员工最新合同版本
        Long num = getVersionNuByStaffId(contract.getStaffId());
        //版本号+1最新版本
        //判断当前添加合同状态
        contract.setVersionNumber(num + 1);
        System.err.println(contract.getContractDateStart());
        Date date = new Date();
        System.err.println("========================" + date);
        //判断合同状态
        if (date.before(contract.getContractDateStart())) {
            contract.setContractState("1");
        } else if (date.before(contract.getContractDateEnd()) && date.after(contract.getContractDateStart())) {
            contract.setContractState("2");
        } else if (date.after(contract.getContractDateEnd())) {
            contract.setContractState("3");
        }
        int months = (contract.getContractDateEnd().getYear() - contract.getContractDateStart().getYear()) * 12
            + (contract.getContractDateEnd().getMonth() - contract.getContractDateStart().getMonth());
        //计算合同期限
        contract.setContractPeriod(months);
        if (contractDao.add(contract) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除
     * 
     * @param
     * @return
     */
    public boolean del(Long rowId) {
        if (contractDao.delById(rowId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新
     * 
     * @param
     * @return
     */
    public boolean update(Contract contract) {

        contract.setLastUpdateTime(new Date());
        contract.setLastUpdateBy(RequestContext.get().getStaffId());

        if (contract.getContractState().equals("1")) {
            Date date = new Date();
            System.err.println("========================" + date);
            //判断合同状态
            if (date.before(contract.getContractDateStart())) {
                contract.setContractState("1");
                System.err.println("未生效");
            } else if (date.before(contract.getContractDateEnd()) && date.after(contract.getContractDateStart())) {
                contract.setContractState("2");
                System.err.println("履行中");
            } else if (date.after(contract.getContractDateEnd())) {
                contract.setContractState("3");
                System.err.println("已过期");
            }
            int months = (contract.getContractDateEnd().getYear() - contract.getContractDateStart().getYear()) * 12
                + (contract.getContractDateEnd().getMonth() - contract.getContractDateStart().getMonth());
            //计算合同期限
            contract.setContractPeriod(months);
        }
        if (contractDao.update(contract) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询versionNumber员工合同最新版本号
     * 
     * @param staffId
     * @return
     */
    public Long getVersionNuByStaffId(Long staffId) {
        return contractDao.getVersionNuByStaffId(staffId);
    }

    /**
     * 查询合同信息
     * 
     * @param staffId
     * @return
     */
    public Contract getByStaffId(Long staffId) {
        return contractDao.getByStaffId(staffId);
    }

    /**
     * 获取列表
     * 
     * @param form
     * @return
     */
    public PageListVO<Contract> pageList(ContractForm form) {
        Long deptId = form.getDeptId();
        if (deptId != null) {
            String allOrg = orgService.getAllSonOrg(deptId);
            List<Org> orgList = new ArrayList<>();
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                orgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                Org dept = new Org();
                dept.setRowId(deptId);
                orgList.add(dept);
                form.setDeptIds(orgList);
            }
        }
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Contract> pageListVO = PageListVO.newInstance(contractDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * 根据rowId查询明细
     * 
     * @author jinlin.tang
     * @date 2018年7月12日 下午12:34:32
     * @param rowId
     * @return
     */
    public List<Contract> getById(Long rowId) {
        return contractDao.getById(rowId);
    }

    /**
     * 查询最后一份合同编号的数字
     * 
     * 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年7月26日 下午1:13:08
     * @return
     */
    public String getLastContractNo() {
        return contractDao.getLastContractNo();
    }

    /**
     * 根据员工号查询合同
     * 
     * @param
     * @return
     */
    public List<Contract> getContractByStaffId(Long staffId) {
        return contractDao.getContractByStaffId(staffId);
    }

    /**
     * 修改合同续签状态
     * 
     * @author jinlin.tang
     * @date 2018年8月9日 下午8:47:06
     * @param rowId
     * @param renewStatus
     */
    public void updateRenewStauts(Long rowId, String renewStatus) {
        contractDao.updateRenewStauts(rowId, renewStatus);
    }

    public void updateContractStateByStaffId(Contract contract) {
        contractDao.updateContractStateByStaffId(contract);
    }

}