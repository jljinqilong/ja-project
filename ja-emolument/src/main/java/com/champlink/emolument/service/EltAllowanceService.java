package com.champlink.emolument.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.constant.SalaryConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltAllowance;
import com.champlink.common.domain.emolument.EltRefStaffEmolument;
import com.champlink.common.form.emolument.EltAllowanceForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.dao.EltAllowanceDao;
import com.champlink.emolument.dao.EltRefStaffEmolumentDao;

@Service
public class EltAllowanceService {

    @Autowired
    private EltAllowanceDao eltAllowanceDao;

    @Autowired
    private EltRefStaffEmolumentDao eltRefStaffEmolumentDao;

    /**
     * 添加
     *
     * @param eltAllowance
     * @return
     */
    public boolean add(EltAllowance eltAllowance) {

        EltAllowance exit = eltAllowanceDao.getByName(eltAllowance);
        if (exit != null) {
            return false;
        }
        if (eltAllowanceDao.add(eltAllowance) > 0) {
            return true;
        }
        return false;

    }

    /**
     * 删除
     *
     * @param eltAllowanceId
     * @return
     */
    public boolean del(Long eltAllowanceId) {
        if (eltAllowanceDao.delById(eltAllowanceId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @author natyu
     * @date 2018年8月3日 上午10:15:51
     */
    public boolean beachDel(Long[] ids) {
        if (eltAllowanceDao.beachDel(ids) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空
     * @return
     */
    public boolean empty() {
        if (eltAllowanceDao.empty() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param eltAllowance
     * @return
     */
    public boolean update(EltAllowance eltAllowance) {

        if (eltAllowanceDao.update(eltAllowance) > 0) {
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
    public PageListVO pageList(EltAllowanceForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(eltAllowanceDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * @param id
     * @param
     * @return
     */
    public EltAllowance findOne(Long id) {
        EltAllowance eltAllowance = eltAllowanceDao.getById(id);
        return eltAllowance;
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
                EltRefStaffEmolument eltRefStaffEmolument = new EltRefStaffEmolument(ruleId, Long.valueOf(split[i]), SalaryConstant.ELT_ALLOWANCE);
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
