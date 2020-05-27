package com.champlink.staff.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Award;
import com.champlink.common.form.staff.contract.AwardForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.dao.contract.AwardDao;

@Service
public class AwardService {

    @Autowired
    private AwardDao awardDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Award award) {
        if (awardDao.add(award) > 0) {
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
        if (awardDao.delById(rowId) > 0) {
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
    public boolean update(Award award) {
        if (awardDao.update(award) > 0) {
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
    public PageListVO<Award> pageList(AwardForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Award> pageListVO = PageListVO.newInstance(awardDao.pageList(paginater));
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
    public Award getById(Long rowId) {
        return awardDao.getById(rowId);
    }

    public int delAllByStaffId(Long staffId) {
    	return awardDao.delAllByStaffId(staffId);
    }
}