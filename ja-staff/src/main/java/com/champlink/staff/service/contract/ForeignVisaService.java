package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.ForeignVisa;
import com.champlink.common.form.staff.contract.ForeignVisaForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.ForeignVisaDao;

@Service
public class ForeignVisaService {

    @Autowired
    private ForeignVisaDao ForeignVisaDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(ForeignVisa foreignVisa) {
        if (ForeignVisaDao.add(foreignVisa) > 0) {
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
        if (ForeignVisaDao.delById(rowId) > 0) {
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
    public boolean update(ForeignVisa foreignVisa) {
        if (ForeignVisaDao.update(foreignVisa) > 0) {
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
    public PageListVO pageList(ForeignVisaForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(ForeignVisaDao.pageList(paginater));
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
    public ForeignVisa getById(Long rowId) {
        return ForeignVisaDao.getById(rowId);
    }

}