package com.champlink.org.service.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.org.position.RefGradeRank;
import com.champlink.common.domain.org.position.RefPositionGrade;
import com.champlink.common.form.org.position.GradeForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.org.dao.position.GradeDao;
import com.champlink.org.dao.position.RankDao;
import com.champlink.org.dao.position.RefGradeRankDao;
import com.champlink.org.dao.position.RefPositionGradeDao;
import com.champlink.org.dao.position.RefPositionRankDao;
import com.github.pagehelper.Page;

@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private RefGradeRankDao refgraderankDao;

    @Autowired
    private RankDao rankDao;

    @Autowired
    private RefPositionGradeDao refPositionGradeDao;

    @Autowired
    private RefPositionRankDao refPositionRankDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Grade grade) {

        List<Grade> allGrade = gradeDao.getAllGrade();

        for (Grade grade2 : allGrade) {
            if (grade2.getGradeName().equals(grade.getGradeName())) {
                AppException.create(500009);
            }
        }

        if (gradeDao.add(grade) > 0) {
            RefGradeRank refgraderank = new RefGradeRank();
            for (Long s : grade.getGradeRank()) {
                refgraderank.setGradeId(grade.getRowId());
                refgraderank.setRankId(Long.valueOf(s));
                refgraderankDao.addRefGradeRank(refgraderank);
            }
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

        List<RefPositionGrade> allList = refPositionGradeDao.allList();
        for (RefPositionGrade refPositionGrade : allList) {
            if (refPositionGrade.getGradeId().longValue() == rowId.longValue()) {
                AppException.create(500008);
            }
        }
        if (gradeDao.delById(rowId) > 0) {
            //			// 删除职等职级关系表数据
            //			int delByGradeId = refgraderankDao.delByGradeId(rowId);
            //			if(delByGradeId > 0) {
            //				return true;
            //			}
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
    @Transactional
    public boolean update(Grade grade) {

        Grade dbGrade = gradeDao.getById(grade.getRowId());
        if (!dbGrade.getGradeName().equals(grade.getGradeName())) {
            List<Grade> allGrade = gradeDao.getAllGrade();
            for (Grade grade2 : allGrade) {
                if (grade2.getGradeName().equals(grade.getGradeName())) {
                    AppException.create(500009);
                }
            }
        }

        if (gradeDao.update(grade) > 0) {
            List<RefGradeRank> refGradeRankList = refgraderankDao.getByGradeId(grade.getRowId());
            if (refGradeRankList != null && refGradeRankList.size() > 0) {
                List<RefPositionGrade> refPositionGradeByGradeId = refPositionGradeDao.getByGradeId(grade.getRowId());
                if (refPositionGradeByGradeId != null && refPositionGradeByGradeId.size() > 0) {
                    Long[] ranks = new Long[refGradeRankList.size()]; // 原先职等/赋值名称对应的职级
                    for (int i = 0; i < refGradeRankList.size(); i++) {
                        ranks[i] = refGradeRankList.get(i).getRankId();
                    }

                    Long[] ranks2 = grade.getGradeRank(); //更新后职等/赋值名称对应的职级
                    List<Long> oldRanks = Arrays.asList(ranks);
                    List<Long> newRanks = Arrays.asList(ranks2);
                    List<Long> oldRanksList = new ArrayList<Long>(oldRanks);
                    List<Long> newRanksList = new ArrayList<Long>(newRanks);

                    Collection<Long> c = CollectionUtils.intersection(oldRanksList, newRanksList);
                    boolean removeAll = oldRanksList.removeAll((List<Long>) c);
                    for (RefPositionGrade refPositionGrade : refPositionGradeByGradeId) {

                        for (Long long1 : oldRanksList) {
                            Long positionId = refPositionGrade.getPositionId();
                            refPositionRankDao.delByPositionIdAndRankId(positionId, long1); // 删除职等/赋值名称下原先与职衔有关系的职级
                        }

                    }

                }

            }

            // 删除职等/赋值名称，职级关系表数据
            refgraderankDao.delByGradeId(grade.getRowId());
            // 删除原来的职等/赋值名称职级关系表数据后，重新添加职等/赋值名称，职级关系
            RefGradeRank refgraderank = new RefGradeRank();
            for (Long s : grade.getGradeRank()) {
                refgraderank.setGradeId(grade.getRowId());
                refgraderank.setRankId(s);
                refgraderankDao.addRefGradeRank(refgraderank);
            }

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
    public PageListVO pageList(GradeForm form) {
        Paginater paginater = Paginater.newInstance(form);
        Page<Grade> pageList = gradeDao.pageList(paginater);
        List<Grade> result = pageList.getResult();
        for (Grade grade : result) {
            String ranks = "";
            List<RefGradeRank> refGradeRankbyGradeId = refgraderankDao.getByGradeId(grade.getRowId());
            for (int i = 0; i < refGradeRankbyGradeId.size(); i++) {
                Long rankId = refGradeRankbyGradeId.get(i).getRankId();
                Rank rankbyId = rankDao.getById(rankId);
                if (rankbyId != null) {
                    String rankName = rankbyId.getRankName();
                    if (i != refGradeRankbyGradeId.size() - 1) {
                        ranks += rankName + " ,  ";
                    } else {
                        ranks += rankName;
                    }
                }

            }

            grade.setRanks(ranks);
        }

        PageListVO pageListVO = PageListVO.newInstance(pageList);
        return pageListVO;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Grade findOne(Long id) {
        Grade grade = gradeDao.getById(id);
        if (grade != null) {
            List<RefGradeRank> refGradeRankList = refgraderankDao.getByGradeId(grade.getRowId());
            if (refGradeRankList != null) {
                Long[] gradeRanks = new Long[refGradeRankList.size()];
                for (int i = 0; i < refGradeRankList.size(); i++) {
                    gradeRanks[i] = refGradeRankList.get(i).getRankId();
                }
                grade.setGradeRank(gradeRanks);
            }
        }

        return grade;

    }

    public List<Grade> getByType() {
        return gradeDao.getByType();
    }

    public List<Grade> getAllGrade() {
        List<Grade> allGrade = gradeDao.getAllGrade();
        return allGrade;
    }
}
