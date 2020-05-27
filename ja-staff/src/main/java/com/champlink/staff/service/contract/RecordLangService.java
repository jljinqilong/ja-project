package com.champlink.staff.service.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.RecordLang;
import com.champlink.common.form.staff.contract.RecordLangForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.RecordLangDao;

@Service
public class RecordLangService {

    @Autowired
    private RecordLangDao RecordLangDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(RecordLang recordLang) {
        if (RecordLangDao.add(recordLang) > 0) {
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
        if (RecordLangDao.delById(rowId) > 0) {
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
    public boolean update(RecordLang recordLang) {
        if (RecordLangDao.update(recordLang) > 0) {
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
    public PageListVO pageList(RecordLangForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(RecordLangDao.pageList(paginater));
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
    public RecordLang getById(Long rowId) {
        return RecordLangDao.getById(rowId);
    }

    /**
     * 根据staffId查询明细
     * 
     * @author jinlin.tang
     * @date 2018年7月12日 下午12:34:32
     * @param staffId
     * @return
     */
    public List<RecordLang> getByStaffId(Long staffId) {
        return RecordLangDao.getByStaffId(staffId);
    }

}