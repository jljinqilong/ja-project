package com.champlink.org.service.position;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.RefPositionGrade;
import com.champlink.common.form.org.position.ImportPositionForm;
import com.champlink.common.form.org.position.PositionForm;
import com.champlink.common.form.org.position.SearchPositionForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.vo.PositionVO;
import com.champlink.org.dao.position.PositionDao;
import com.champlink.org.dao.position.RefPositionGradeDao;

@Service
public class PostService {

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private RefPositionGradeDao refpositiongradeDao;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Position position) {
        if (positionDao.add(position) > 0) {
            RefPositionGrade refpositiongrade = new RefPositionGrade();
            for (String s : position.getPositionGrade()) {
                refpositiongrade.setPositionId(position.getRowId());
                refpositiongrade.setGradeId(Long.valueOf(s));
                refpositiongradeDao.addPosition(refpositiongrade);
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
        if (positionDao.delById(rowId) > 0) {
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
    public boolean update(PositionVO position) {
        if (positionDao.update(position) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 职衔列表
     * 
     * @param form
     * @return
     */

    public PageListVO pageList1(PositionForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(positionDao.pageList(paginater));
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
        List<PositionVO> list = positionDao.queryPositionForParams(form);
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
}
