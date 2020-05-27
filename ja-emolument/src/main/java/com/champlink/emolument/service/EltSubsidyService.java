package com.champlink.emolument.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.constant.SalaryConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltRefStaffEmolument;
import com.champlink.common.domain.emolument.EltSubsidy;
import com.champlink.common.form.emolument.EltSubsidyForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.dao.EltRefStaffEmolumentDao;
import com.champlink.emolument.dao.EltSubsidyDao;

@Service
public class EltSubsidyService {

    @Autowired
    private EltSubsidyDao eltSubsidyDao;

    @Autowired
    private EltRefStaffEmolumentDao eltRefStaffEmolumentDao;

    /**
     * 添加
     *
     * @param eltSubsidy
     * @return
     */
    public boolean add(EltSubsidy eltSubsidy) {

        EltSubsidy exit = eltSubsidyDao.getByName(eltSubsidy);
        if (exit != null) {
            return false;
        }
        if (eltSubsidyDao.add(eltSubsidy) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除
     *
     * @param eltSubsidyId
     * @return
     */
    public boolean del(Long eltSubsidyId) {
        if (eltSubsidyDao.delById(eltSubsidyId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public boolean beachDel(Long[] ids) {
        if (eltSubsidyDao.beachDel(ids) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空
     * @return
     */
    public boolean empty() {
        if (eltSubsidyDao.empty() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param eltSubsidy
     * @return
     */
    public boolean update(EltSubsidy eltSubsidy) {

        if (eltSubsidyDao.update(eltSubsidy) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取列表
     *
     * @param form
     * @return
     */
    public PageListVO pageList(EltSubsidyForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(eltSubsidyDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * @param id
     * @return
     */
    public EltSubsidy findOne(Long id) {
        EltSubsidy eltSubsidy = eltSubsidyDao.getById(id);
        return eltSubsidy;
    }

    /**
     * 为员工添加规则
     *
     * @param staffId
     * @param ruleId
     * @return
     */
    public Boolean addRule(String staffId, Long ruleId, int id) {

        if (id == 1) {
            eltRefStaffEmolumentDao.deleteRuleId(ruleId);
        }
        List<EltRefStaffEmolument> list = new ArrayList();
        if (!"".equals(staffId) && ruleId != null) {
            String[] split = staffId.split(",");
            for (int i = 0; i < split.length; i++) {
                EltRefStaffEmolument eltRefStaffEmolument = new EltRefStaffEmolument(ruleId, Long.valueOf(split[i]), SalaryConstant.ELT_SUBSIDY);
                list.add(eltRefStaffEmolument);
            }
            if (eltRefStaffEmolumentDao.insert(list) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除规则
     *
     * @param ruleId
     * @return
     */
    public Boolean deleteRule(Long staffId) {

        if (eltRefStaffEmolumentDao.deleteByStaffId(staffId) > 0) {
            return true;
        } else {
            return false;
        }
    }

}
