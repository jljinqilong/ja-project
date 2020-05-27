package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.ForeignReside;
import com.champlink.common.form.staff.contract.ForeignResideForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.ForeignResideDao;

@Service
public class ForeignResideService {

    @Autowired
    private ForeignResideDao ForeignResideDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(ForeignReside foreignReside) {
        if (ForeignResideDao.add(foreignReside) > 0) {
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
        if (ForeignResideDao.delById(rowId) > 0) {
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
    public boolean update(ForeignReside foreignReside) {
        if (ForeignResideDao.update(foreignReside) > 0) {
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
    public PageListVO pageList(ForeignResideForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(ForeignResideDao.pageList(paginater));
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
    public ForeignReside getById(Long rowId) {
        return ForeignResideDao.getById(rowId);
    }

}