package com.champlink.staff.service.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.RecordIt;
import com.champlink.common.form.staff.contract.RecordItForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.RecordItDao;

@Service
public class RecordItService {

    @Autowired
    private RecordItDao RecordItDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(RecordIt recordIt) {
        if (RecordItDao.add(recordIt) > 0) {
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
        if (RecordItDao.delById(rowId) > 0) {
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
    public boolean update(RecordIt recordIt) {
        if (RecordItDao.update(recordIt) > 0) {
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
    public PageListVO pageList(RecordItForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(RecordItDao.pageList(paginater));
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
    public RecordIt getById(Long rowId) {
        return RecordItDao.getById(rowId);
    }

    /**
     * 根据staffId查询明细
     * 
     * @author jinlin.tang
     * @date 2018年7月12日 下午12:34:32
     * @param rowId
     * @return
     */
    public List<RecordIt> getByStaffId(Long staffId) {
        return RecordItDao.getByStaffId(staffId);
    }

}