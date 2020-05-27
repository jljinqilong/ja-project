package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.OccupationalInjury;
import com.champlink.common.form.staff.contract.OccupationalInjuryForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.OccupationalInjuryDao;

@Service
public class OccupationalInjuryService {

    @Autowired
    private OccupationalInjuryDao OccupationalInjuryDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(OccupationalInjury occupationalInjury) {
        if (OccupationalInjuryDao.add(occupationalInjury) > 0) {
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
        if (OccupationalInjuryDao.delById(rowId) > 0) {
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
    public boolean update(OccupationalInjury occupationalInjury) {
        if (OccupationalInjuryDao.update(occupationalInjury) > 0) {
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
    public PageListVO<OccupationalInjury> pageList(OccupationalInjuryForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<OccupationalInjury> pageListVO = PageListVO.newInstance(OccupationalInjuryDao.pageList(paginater));
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
    public OccupationalInjury getById(Long rowId) {
        return OccupationalInjuryDao.getById(rowId);
    }

}