package com.champlink.emolument.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.constant.SalaryConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltRefStaffEmolument;
import com.champlink.common.domain.emolument.EltSocialSecurity;
import com.champlink.common.form.emolument.EltSocialSecurityForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.dao.EltRefStaffEmolumentDao;
import com.champlink.emolument.dao.EltSocialSecurityDao;

@Service
public class EltSocialSecurityService {

    @Autowired
    private EltSocialSecurityDao eltSocialSecurityDao;

    @Autowired
    private EltRefStaffEmolumentDao eltRefStaffEmolumentDao;

    /**
     * 添加社保规则
     * 
     * @param eltSocialSecurity
     * @return
     */
    public boolean add(EltSocialSecurity eltSocialSecurity) {
        EltSocialSecurity exist = eltSocialSecurityDao.getByName(eltSocialSecurity.getRuleName());

        if (exist != null) {
            return false;
        }
        if (eltSocialSecurityDao.add(eltSocialSecurity) > 0) {
            return true;
        }

        return false;
    }

    /**
     * 删除
     * 
     * @param eltSocialSecurityId
     * @return
     */
    public boolean del(Long eltSocialSecurityId) {
        if (eltSocialSecurityDao.delById(eltSocialSecurityId) > 0) {
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
        if (eltSocialSecurityDao.beachDel(ids) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空
     * @return
     */
    public boolean empty() {
        if (eltSocialSecurityDao.empty() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新
     * 
     * @param eltSocialSecurity
     * @return
     */
    public boolean update(EltSocialSecurity eltSocialSecurity) {

        if (eltSocialSecurityDao.update(eltSocialSecurity) > 0) {
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
    public PageListVO pageList(EltSocialSecurityForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(eltSocialSecurityDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * 获取列表
     * 
     * @param id
     * @return
     */
    public EltSocialSecurity findOne(Long id) {
        EltSocialSecurity eltSocialSecurity = eltSocialSecurityDao.getById(id);
        return eltSocialSecurity;
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
                EltRefStaffEmolument eltRefStaffEmolument = new EltRefStaffEmolument(ruleId, Long.valueOf(split[i]), SalaryConstant.ELT_SOCIAL_SECURITY);
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
