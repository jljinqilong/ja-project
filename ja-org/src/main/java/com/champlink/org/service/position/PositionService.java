package com.champlink.org.service.position;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.PositionType;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.org.position.RefGradeRank;
import com.champlink.common.domain.org.position.RefPositionGrade;
import com.champlink.common.domain.org.position.RefPositionRank;
import com.champlink.common.form.org.position.ImportPositionForm;
import com.champlink.common.form.org.position.PositionForm;
import com.champlink.common.form.org.position.SearchPositionForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.vo.PositionVO;
import com.champlink.org.dao.position.GradeDao;
import com.champlink.org.dao.position.PositionDao;
import com.champlink.org.dao.position.PositionTypeDao;
import com.champlink.org.dao.position.RankDao;
import com.champlink.org.dao.position.RefGradeRankDao;
import com.champlink.org.dao.position.RefPositionGradeDao;
import com.champlink.org.dao.position.RefPositionRankDao;
import com.champlink.org.service.call.StaffService;
import com.github.pagehelper.Page;

@Service
public class PositionService {

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private RefPositionGradeDao refpositiongradeDao;

    @Autowired
    private RefPositionRankDao refPositionRankDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private RankDao rankDao;

    @Autowired
    private PositionTypeDao positionTypeDao;

    @Autowired
    private RefGradeRankDao refGradeRankDao;

    @Autowired
    private StaffService staffService;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    @Transactional
    public boolean add(com.champlink.common.form.org.org.PositionForm positionForm) {

        List<Position> allPosition = positionDao.getAllPosition();
        for (Position position : allPosition) {
            if (position.getPositionName().equals(positionForm.getPositionName())) {
                AppException.create(500010);
            }
        }

        Position position = new Position();
        BeanUtils.copyProperties(positionForm, position);

        int add = positionDao.add(position);
        if (add > 0) {
            Long positionId = position.getRowId();
            // 添加职衔与职等/赋值名称的关系
            String[] grades = positionForm.getGrades();
            for (String string : grades) {
                RefPositionGrade refpositiongrade = new RefPositionGrade();
                refpositiongrade.setPositionId(positionId);
                refpositiongrade.setGradeId(Long.valueOf(string));
                refpositiongradeDao.addPosition(refpositiongrade);
            }
            // 添加职衔与职级的关系
            String[] ranks = positionForm.getRanks();
            for (String string : ranks) {
                RefPositionRank refPositionRank = new RefPositionRank();
                refPositionRank.setPositionId(positionId);
                refPositionRank.setRankId(Long.valueOf(string));
                refPositionRankDao.addPosition(refPositionRank);
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
    @Transactional
    public boolean del(Long rowId) {

        String resultJson = staffService.queryCountBaseInfoByPositionId(rowId);
        if (resultJson != null) {
            JSONObject resultObject = JSONObject.parseObject(resultJson);
            if ((Integer) resultObject.get("code") == 200) {
                int staffCount = (Integer) resultObject.get("data");
                if (staffCount > 0) {
                    AppException.create(500012); //该职衔已经被使用！
                }
            }
        } else {
            AppException.create(500011); //查询职衔下的员工失败
        }

        if (positionDao.delById(rowId) > 0) {
            // 删除 职衔职等/赋值名称关系表数据
            refpositiongradeDao.delByPositionId(rowId);
            // 删除职衔职级关系表数据
            refPositionRankDao.delByPositionId(rowId);

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
    public boolean update(com.champlink.common.form.org.org.PositionForm positionForm) {
        Position dbPosition = positionDao.getById(positionForm.getRowId());
        if (!dbPosition.getPositionName().equals(positionForm.getPositionName())) {
            List<Position> allPosition = positionDao.getAllPosition();
            for (Position position : allPosition) {
                if (position.getPositionName().equals(positionForm.getPositionName())) {
                    AppException.create(500010);
                }
            }
        }

        PositionVO positionVo = new PositionVO();
        BeanUtils.copyProperties(positionForm, positionVo);
        positionDao.update(positionVo); // 更新职衔表信息

        String[] grades = positionForm.getGrades();
        String[] ranks = positionForm.getRanks();

        // 先删除旧数据
        refpositiongradeDao.delByPositionId(positionForm.getRowId());
        refPositionRankDao.delByPositionId(positionForm.getRowId());

        for (String string : grades) {
            RefPositionGrade refpositiongrade = new RefPositionGrade();
            refpositiongrade.setPositionId(positionForm.getRowId());
            refpositiongrade.setGradeId(Long.valueOf(string));
            refpositiongradeDao.addPosition(refpositiongrade);
        }
        // 添加职衔与职级的关系
        for (String string : ranks) {
            RefPositionRank refPositionRank = new RefPositionRank();
            refPositionRank.setPositionId(positionForm.getRowId());
            refPositionRank.setRankId(Long.valueOf(string));
            refPositionRankDao.addPosition(refPositionRank);
        }

        return true;
    }

    /**
     * 
     * 职衔列表
     * 
     * @param form
     * @return
     */

    public PageListVO pageList(PositionForm form) {
        Paginater paginater = Paginater.newInstance(form);
        Page<PositionVO> pageList = positionDao.pageList(paginater);
        List<PositionVO> result = pageList.getResult();

        for (PositionVO positionVO : result) {
            String grades = "";
            String ranks = "";
            Long positionId = positionVO.getRowId();
            List<RefPositionGrade> refPositionGradebyPositionId = refpositiongradeDao.getByPositionId(positionId);
            List<RefPositionRank> refPositionRankbyPositionId = refPositionRankDao.getByPositionId(positionId);

            for (int i = 0; i < refPositionGradebyPositionId.size(); i++) {
                Long gradeId = refPositionGradebyPositionId.get(i).getGradeId();
                Grade grade = gradeDao.getById(gradeId);
                if (grade != null) {
                    if (i == refPositionGradebyPositionId.size() - 1) {
                        grades += grade.getGradeName();
                    } else {
                        grades += grade.getGradeName() + " ,  ";
                    }
                }
            }

            for (int i = 0; i < refPositionRankbyPositionId.size(); i++) {
                Long rankId = refPositionRankbyPositionId.get(i).getRankId();
                Rank rank = rankDao.getById(rankId);
                if (rank != null) {
                    if (i == refPositionRankbyPositionId.size() - 1) {
                        ranks += rank.getRankName();
                    } else {
                        ranks += rank.getRankName() + " ,  ";
                    }
                }
            }
            positionVO.setRanks(ranks);
            positionVO.setGrades(grades);

            Integer positionType = positionVO.getPositionType();
            PositionType byId = positionTypeDao.getById(Long.valueOf(positionType));
            positionVO.setTypeName(byId.getTypeName());
        }

        PageListVO pageListVO = PageListVO.newInstance(pageList);
        return pageListVO;
    }

    /**
     * 获取所有
     * 
     * @param id
     * @return
     */

    public List<Position> getByPositionType() {
        return positionDao.getByPositionType();
    }

    /**
     * 
     * @param id
     * @return
     */
    public Position findOne(Long id) {
        Position position = positionDao.getById(id);
        List<RefPositionGrade> byPositionId = refpositiongradeDao.getByPositionId(id);
        if (byPositionId != null && byPositionId.size() > 0) {
            Long[] grades = new Long[byPositionId.size()];
            for (int i = 0; i < byPositionId.size(); i++) {
                grades[i] = byPositionId.get(i).getGradeId();
            }
            position.setGrades(grades);
        }
        List<RefPositionRank> refPositionRankByPositionId = refPositionRankDao.getByPositionId(id);
        if (refPositionRankByPositionId != null && refPositionRankByPositionId.size() > 0) {
            Long[] ranks = new Long[refPositionRankByPositionId.size()];
            for (int i = 0; i < refPositionRankByPositionId.size(); i++) {
                ranks[i] = refPositionRankByPositionId.get(i).getRankId();
            }
            position.setRanks(ranks);
        }

        return position;
    }

    /**
     * 
     * 
     * 导出
     * 
     * @param form
     * @return
     */

    public List<PositionVO> queryPositionForParams(SearchPositionForm form) {
        return positionDao.queryPositionForParams(form);
    }

    public void exportExcel(HttpServletResponse response, SearchPositionForm form) {
        // 查询数据
        List<PositionVO> list = positionDao.exportList(form);

        for (PositionVO positionVO : list) {
            String grades = "";
            String ranks = "";
            Long positionId = positionVO.getRowId();
            List<RefPositionGrade> refPositionGradebyPositionId = refpositiongradeDao.getByPositionId(positionId);
            List<RefPositionRank> refPositionRankbyPositionId = refPositionRankDao.getByPositionId(positionId);

            for (int i = 0; i < refPositionGradebyPositionId.size(); i++) {
                Long gradeId = refPositionGradebyPositionId.get(i).getGradeId();
                Grade grade = gradeDao.getById(gradeId);
                if (i == refPositionGradebyPositionId.size() - 1) {
                    grades += grade.getGradeName();
                } else {
                    grades += grade.getGradeName() + " ,  ";
                }
            }

            for (int i = 0; i < refPositionRankbyPositionId.size(); i++) {
                Long rankId = refPositionRankbyPositionId.get(i).getRankId();
                Rank rank = rankDao.getById(rankId);
                if (i == refPositionRankbyPositionId.size() - 1) {
                    ranks += rank.getRankName();
                } else {
                    ranks += rank.getRankName() + " ,  ";
                }
            }
            positionVO.setRanks(ranks);
            positionVO.setGrades(grades);

            Integer positionType = positionVO.getPositionType();
            PositionType byId = positionTypeDao.getById(Long.valueOf(positionType));
            positionVO.setTypeName(byId.getTypeName());
        }

        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("typeName");
        fieldList.add("positionName");
        fieldList.add("grades");
        fieldList.add("ranks");
        fieldList.add("staffSize");

        String title = "";
        int size = list.size();
        String[] headers = new String[size];
        headerList.add("职衔类别");
        headerList.add("职衔名称");
        headerList.add("职等/赋值名称");
        headerList.add("职级");
        headerList.add("编制人数");
        title = "职衔信息";
        headers = (String[]) headerList.toArray(new String[0]);

        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }

    /**
     * 
     * 
     * 导入
     * 
     * @param response
     * @param list
     * @param lang
     */

    public void exportErrExcel(HttpServletResponse response, List<ImportPositionForm> list) {

        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();

        fieldList.add("typeName");
        fieldList.add("rankName");
        fieldList.add("positionName");
        fieldList.add("postAssignment");
        fieldList.add("gradeName");
        fieldList.add("salaryMax");
        fieldList.add("salaryMin");
        fieldList.add("staffSize");

        String title = "";
        int size = list.size();
        String[] headers = new String[size];
        headerList.add("职衔类别");
        headerList.add("职级");
        headerList.add("职衔名称");
        headerList.add("岗位赋值");
        headerList.add("职等/赋值名称");
        headerList.add("薪资上限");
        headerList.add("薪资下限");
        headerList.add("编制人数");

        title = "职衔错误信息";

        headers = (String[]) headerList.toArray(new String[0]);

        // pojo字段
        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }

    /**
     * 
     * 
     * 导入
     * 
     * @param list
     * @return
     */
    @Transactional
    public List<ImportPositionForm> importExcel(List<ImportPositionForm> list) {
        List<PositionVO> insertBaseInfos = new ArrayList<PositionVO>();
        List<ImportPositionForm> errorBaseInfos = new ArrayList<ImportPositionForm>();
        for (ImportPositionForm position : list) {
            StringBuffer errMsg = new StringBuffer();
            // String typenameStr = position.getTypeName();
            // String rankStr = position.getRankName();
            // String positionnameStr = position.getPositionName();
            // String postassignmentStr = position.getPostAssignment();
            // String gradenameStr = position.getGradeName();
            // String salarymaxStr = position.getSalaryMax();
            // String salaryminStr = position.getSalaryMin();
            // String staffsizeStr = position.getStaffSize();
            if (StringUtils.isEmpty(position.getTypeName())) {
                errMsg.append("职衔类别不存在；");
            }

            if (StringUtils.isEmpty(position.getRankName())) {
                errMsg.append("职级不存在；");
            }

            if (StringUtils.isEmpty(position.getPositionName())) {
                errMsg.append("职衔名称不存在；");
            }
            if (StringUtils.isEmpty(position.getPostAssignment())) {
                errMsg.append("岗位名称不存在；");
            }

            if (StringUtils.isEmpty(position.getGradeName())) {
                errMsg.append("职等/赋值名称不存在");
            }
            if (StringUtils.isEmpty(position.getSalaryMax())) {
                errMsg.append("薪资上限不存在；");
            }

            if (StringUtils.isEmpty(position.getSalaryMin())) {
                errMsg.append("薪资下限不存在；");
            }

            if (StringUtils.isEmpty(position.getStaffSize())) {
                errMsg.append("编制人数不存在；");
            }

            PositionVO temp = new PositionVO();
            BeanUtils.copyProperties(position, temp);

            if (StringUtils.isEmpty(errMsg.toString())) {

                insertBaseInfos.add(temp);
            } else {
                position.setErrMsg(errMsg.toString());
                errorBaseInfos.add(position);
            }
        }
        if (insertBaseInfos != null && insertBaseInfos.size() > 0) {

            // 批量生成用户
            // systemService.batchAdd(userList);
        }
        return errorBaseInfos;
    }

    public List<Position> getAllPosition() {
        List<Position> allPosition = positionDao.getAllPosition();

        return allPosition;
    }

    public List<Grade> getAllGradeByPosition(Long positionId) {
        List<RefPositionGrade> refPositionGradebyPositionId = refpositiongradeDao.getByPositionId(positionId);
        if (refPositionGradebyPositionId != null) {
            List<Grade> gradeList = new ArrayList<Grade>();
            for (RefPositionGrade refPositionGrade : refPositionGradebyPositionId) {
                Long gradeId = refPositionGrade.getGradeId();
                Grade gradeById = gradeDao.getById(gradeId);
                gradeList.add(gradeById);

            }
            return gradeList;
        }

        return null;
    }

    public List<Rank> getAllRankByPositionAndGrade(Long positionId, Long gradeId) {
        List<RefGradeRank> refGradeRankByGradeId = refGradeRankDao.getByGradeId(gradeId);
        if (refGradeRankByGradeId != null) {
            List<Long> ranksWithGrade = new ArrayList<Long>();
            for (RefGradeRank refGradeRank : refGradeRankByGradeId) {
                ranksWithGrade.add(refGradeRank.getRankId());
            }

            List<RefPositionRank> refPositionRankByPositionId = refPositionRankDao.getByPositionId(positionId);
            List<Long> ranksWithPosition = new ArrayList<Long>();
            for (RefPositionRank refPositionRank : refPositionRankByPositionId) {
                ranksWithPosition.add(refPositionRank.getRankId());
            }

            ranksWithGrade.retainAll(ranksWithPosition);
            List<Rank> rankList = new ArrayList<Rank>();
            for (Long long1 : ranksWithGrade) {
                Rank rankById = rankDao.getById(long1);
                rankList.add(rankById);
            }
            return rankList;

        }

        return null;
    }
}
