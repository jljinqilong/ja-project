package com.champlink.emolument.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.constant.SalaryConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltRefStaffEmolument;
import com.champlink.common.domain.emolument.EltSalary;
import com.champlink.common.form.emolument.EltSalaryForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.dao.EltRefStaffEmolumentDao;
import com.champlink.emolument.dao.EltSalaryDao;

@Service
public class EltSalaryService {

    @Autowired
    private EltSalaryDao eltSalaryDao;

    @Autowired
    private EltRefStaffEmolumentDao eltRefStaffEmolumentDao;

    /**
     * 添加新
     *
     * @param eltSalary
     * @return
     */
    public boolean add(EltSalary eltSalary) {

        EltSalary exit = eltSalaryDao.getByName(eltSalary);
        if (exit != null) {
            return false;
        }
        if (eltSalaryDao.add(eltSalary) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除
     *
     * @param eltSalaryId
     * @return
     */
    public boolean del(Long eltSalaryId) {
        if (eltSalaryDao.delById(eltSalaryId) > 0) {
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
        if (eltSalaryDao.beachDel(ids) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空
     * @return
     */
    public boolean empty() {
        if (eltSalaryDao.empty() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param eltSalary
     * @return
     */
    public boolean update(EltSalary eltSalary) {
        if (eltSalaryDao.update(eltSalary) > 0) {
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
    public PageListVO pageList(EltSalaryForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(eltSalaryDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * 获取列表
     *
     * @param id
     * @return
     */
    public EltSalary findOne(Long id) {
        EltSalary eltSalary = eltSalaryDao.getById(id);
        return eltSalary;
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
                EltRefStaffEmolument eltRefStaffEmolument = new EltRefStaffEmolument(ruleId, Long.valueOf(split[i]), SalaryConstant.ELT_Salary);
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
