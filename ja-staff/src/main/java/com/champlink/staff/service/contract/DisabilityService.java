package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Disability;
import com.champlink.common.form.staff.contract.DisabilityForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.DisabilityDao;

@Service
public class DisabilityService {

    @Autowired
    private DisabilityDao DisabilityDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Disability disability) {
        if (DisabilityDao.add(disability) > 0) {
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
        if (DisabilityDao.delById(rowId) > 0) {
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
    public boolean update(Disability disability) {
        if (DisabilityDao.update(disability) > 0) {
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
    public PageListVO<Disability> pageList(DisabilityForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Disability> pageListVO = PageListVO.newInstance(DisabilityDao.pageList(paginater));
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
    public Disability getById(Long rowId) {
        return DisabilityDao.getById(rowId);
    }

}