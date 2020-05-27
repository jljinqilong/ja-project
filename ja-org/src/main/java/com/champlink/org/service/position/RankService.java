package com.champlink.org.service.position;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.org.position.RefGradeRank;
import com.champlink.common.form.org.position.RankForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.org.dao.position.RankDao;
import com.champlink.org.dao.position.RefGradeRankDao;
import com.champlink.org.dao.position.RefPositionRankDao;

@Service
public class RankService {

    @Autowired
    private RankDao rankDao;

    @Autowired
    private RefGradeRankDao refgraderankDao;

    @Autowired
    private RefPositionRankDao refPositionRankDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Rank rank) {
        List<Rank> allRank = rankDao.getAllRank();
        for (Rank rank2 : allRank) {
            if (rank2.getRankName().equals(rank.getRankName().trim())) {
                AppException.create(500006);
            }
        }

        if (rankDao.add(rank) > 0) {
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
    @Transactional
    public boolean del(Long rowId) {

        List<RefGradeRank> allList = refgraderankDao.allList();
        for (RefGradeRank refGradeRank : allList) {
            if (refGradeRank.getRankId().longValue() == rowId.longValue()) {
                AppException.create(500007);
            }
        }
        if (rankDao.delById(rowId) > 0) {
            //			int delByRankId = refgraderankDao.delByRankId(rowId); // 删除职级，需要连带把职等/赋值名称，职级关系表数据删除
            //			int delByRankId2 = refPositionRankDao.delByRankId(rowId); // 删除职级，连带把职衔职级关系数据删除

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
    public boolean update(Rank rank) {

        Rank dbRank = rankDao.getById(rank.getRowId());
        if (!dbRank.getRankName().equals(rank.getRankName())) {
            List<Rank> allRank = rankDao.getAllRank();
            for (Rank rank2 : allRank) {
                if (rank2.getRankName().equals(rank.getRankName().trim())) {
                    AppException.create(500006);
                }
            }
        }
        if (rankDao.update(rank) > 0) {
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
    public PageListVO pageList(RankForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(rankDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Rank findOne(Long id) {
        Rank rank = rankDao.getById(id);
        return rank;

    }

    public List<Rank> getByRankType() {
        return rankDao.getByRankType();
    }

    /**
     * 根据职等/赋值名称查询出下属的职级
     * @param positionGrade
     * @return
     */
    public List<Rank> getRankByGrade(List<Long> positionGrade) {

        List<Rank> ranks = rankDao.getRankByGrade(positionGrade);
        return ranks;
    }

    /**
     * 根据职等/赋值名称查询出下属的职级
     * @param positionGrade
     * @return
     */
    public List<Rank> getAllRank() {

        List<Rank> ranks = rankDao.getAllRank();
        return ranks;
    }
}
