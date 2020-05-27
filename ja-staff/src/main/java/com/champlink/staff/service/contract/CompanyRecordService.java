package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.CompanyRecord;
import com.champlink.common.form.staff.contract.CompanyRecordForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.CompanyRecordDao;

@Service
public class CompanyRecordService {

    @Autowired
    private CompanyRecordDao CompanyRecordDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(CompanyRecord companyRecord) {
        if (CompanyRecordDao.add(companyRecord) > 0) {
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
        if (CompanyRecordDao.delById(rowId) > 0) {
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
    public boolean update(CompanyRecord companyRecord) {
        if (CompanyRecordDao.update(companyRecord) > 0) {
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
    public PageListVO pageList(CompanyRecordForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(CompanyRecordDao.pageList(paginater));
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
    public CompanyRecord getById(Long rowId) {
        return CompanyRecordDao.getById(rowId);
    }

}