package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Punishment;
import com.champlink.common.form.staff.contract.PunishmentForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.PunishmentDao;

@Service
public class PunishmentService {

    @Autowired
    private PunishmentDao PunishmentDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Punishment punishment) {
        if (PunishmentDao.add(punishment) > 0) {
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
        if (PunishmentDao.delById(rowId) > 0) {
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
    public boolean update(Punishment punishment) {
        if (PunishmentDao.update(punishment) > 0) {
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
    public PageListVO pageList(PunishmentForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(PunishmentDao.pageList(paginater));
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
    public Punishment getById(Long rowId) {
        return PunishmentDao.getById(rowId);
    }

}