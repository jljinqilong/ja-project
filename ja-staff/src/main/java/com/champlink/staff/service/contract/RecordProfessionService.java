package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.RecordProfession;
import com.champlink.common.form.staff.contract.RecordProfessionForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.RecordProfessionDao;

@Service
public class RecordProfessionService {

    @Autowired
    private RecordProfessionDao RecordProfessionDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(RecordProfession recordProfession) {
        if (RecordProfessionDao.add(recordProfession) > 0) {
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
        if (RecordProfessionDao.delById(rowId) > 0) {
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
    public boolean update(RecordProfession recordProfession) {
        if (RecordProfessionDao.update(recordProfession) > 0) {
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
    public PageListVO pageList(RecordProfessionForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(RecordProfessionDao.pageList(paginater));
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
    public RecordProfession getById(Long rowId) {
        return RecordProfessionDao.getById(rowId);
    }
    
    public int delAllByStaffId(Long staffId) {
    	return  RecordProfessionDao.delAllByStaffId(staffId);
    }

}