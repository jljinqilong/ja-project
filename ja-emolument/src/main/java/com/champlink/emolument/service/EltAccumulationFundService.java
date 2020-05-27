package com.champlink.emolument.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.SalaryConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.emolument.EltAccumulationFund;
import com.champlink.common.domain.emolument.EltRefStaffEmolument;
import com.champlink.common.domain.emolument.ExcelBean.EltAccumulationFundExcelBean;
import com.champlink.common.domain.org.Org;
import com.champlink.common.form.emolument.EltAccumulationFundForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.emolument.dao.EltAccumulationFundDao;
import com.champlink.emolument.dao.EltRefStaffEmolumentDao;
import com.champlink.emolument.service.call.OrgService;
import com.github.pagehelper.Page;

@Service
public class EltAccumulationFundService {

    @Autowired
    private EltAccumulationFundDao eltAccumulationFundDao;

    @Autowired
    private EltRefStaffEmolumentDao eltRefStaffEmolumentDao;

    @Autowired
    private OrgService orgService;

    /**
     * 添加公积金
     *
     * @param eltAccumulationFund
     * @return
     */
    public boolean addEltAccumulationFund(EltAccumulationFund eltAccumulationFund) {
        EltAccumulationFund exist = eltAccumulationFundDao.getByNameAndId(eltAccumulationFund);

        if (exist != null) {
            return false;
        }
        if (eltAccumulationFundDao.add(eltAccumulationFund) > 0) {
            return true;
        }

        return false;
    }

    /**
     * 删除公积金
     *
     * @param eltAccumulationFundId
     * @return
     */
    public boolean del(Long eltAccumulationFundId) {
        if (eltAccumulationFundDao.delById(eltAccumulationFundId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @author natyu
     * @date 2018年8月3日 上午10:15:51
     */
    public boolean beachDel(Long[] ids) {
        if (eltAccumulationFundDao.beachDel(ids) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空
     *
     * @return
     */
    public boolean empty() {
        if (eltAccumulationFundDao.empty() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新公积金
     *
     * @param eltAccumulationFund
     * @return
     */
    public boolean update(EltAccumulationFund eltAccumulationFund) {

        if (eltAccumulationFundDao.update(eltAccumulationFund) > 0) {
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
    public PageListVO pageList(EltAccumulationFundForm form) {
        Paginater paginater = Paginater.newInstance(form);
        Page<EltAccumulationFund> pageList = eltAccumulationFundDao.pageList(paginater);

        PageListVO pageListVO = PageListVO.newInstance(eltAccumulationFundDao.pageList(paginater));
        return pageListVO;
    }

    //		public <T> List<T> resetOrgName(List<T> list, List<Org> orgList){
    //			list.stream().map(t -> t.)
    //			return null;
    //		}

    /**
     * @param id
     * @return
     */
    public EltAccumulationFund findOne(Long id) {
        EltAccumulationFund eltAccumulationFund = eltAccumulationFundDao.getById(id);

        return eltAccumulationFund;
    }

    /**
     * 为员工添加规则
     *
     * @param staffId
     * @param ruleId
     * @return
     */
    @Transactional
    public Boolean addRule(String staffId, Long ruleId, int id) {

        if (id == 1) {
            eltRefStaffEmolumentDao.deleteRuleId(ruleId);
        }
        List<EltRefStaffEmolument> list = new ArrayList();
        if (!"".equals(staffId) && ruleId != null) {
            String[] split = staffId.split("\\,");
            for (int i = 0; i < split.length; i++) {
                EltRefStaffEmolument eltRefStaffEmolument = new EltRefStaffEmolument(ruleId, Long.valueOf(split[i]), SalaryConstant.ELT_ACCLUMLATION);
                list.add(eltRefStaffEmolument);
            }
            if (eltRefStaffEmolumentDao.insert(list) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除规则
     *
     * @param staffId
     * @return
     */
    public Boolean deleteByStaffId(Long staffId) {

        if (eltRefStaffEmolumentDao.deleteByStaffId(staffId) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 导入公积金Excel
     *
     * @param list
     * @return
     */
    @Transactional
    public List<EltAccumulationFundExcelBean> importExcel(List<EltAccumulationFundExcelBean> list) {

        //插入的集合
        List<EltAccumulationFund> insertEltAccumulationFundInfos = new ArrayList<EltAccumulationFund>();
        //更新的集合
        List<EltAccumulationFund> updateEltAccumulationFundInfos = new ArrayList<EltAccumulationFund>();

        List<EltAccumulationFundExcelBean> errorEltAccumulationFundInfos = new ArrayList<EltAccumulationFundExcelBean>();

        //获取所有的基地
        String baseJson = orgService.baseList();
        List<Org> baseList = new ArrayList<>();
        if (baseJson != null) {
            JSONObject parseObject = JSONObject.parseObject(baseJson);
            if ((Integer) parseObject.get("code") == 200) {
                baseList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
            }
        }

        //校验文件数据是否重复
        //基地map
        Map<String, String> baseNoList = new HashMap<>();
        //规则名称
        Map<String, String> ruleNameNoList = new HashMap<>();
        for (EltAccumulationFundExcelBean eltAccumulationFundExcelBean : list) {
            String baseNoFile = eltAccumulationFundExcelBean.getBase();
            String ruleNameNoFile = eltAccumulationFundExcelBean.getRuleName();
            baseNoList.put(baseNoFile, baseNoFile);
            ruleNameNoList.put(ruleNameNoFile, ruleNameNoFile);
        }
        if (baseNoList.size() != list.size()) {
            AppException.create(200007);
        }
        if (ruleNameNoList.size() != list.size()) {
            AppException.create(200008);
        }

        //为空校验
        List<String> AccumulationFoundList = new ArrayList<>();
        for (EltAccumulationFundExcelBean eltAccumulationFundInfo : list) {
            //定义错误字符串
            StringBuffer errMsg = new StringBuffer();
            //获取Excel值
            //基地
            String baseName = eltAccumulationFundInfo.getBase();
            //规则名称
            String ruleName = eltAccumulationFundInfo.getRuleName();
            //缴费基数
            String socialInsuranceBase = eltAccumulationFundInfo.getSocialInsuranceBase();
            //比例(个人)
            String personalProportion = eltAccumulationFundInfo.getPersonalProportion();
            //金额(个人)
            String personalAmount = eltAccumulationFundInfo.getPersonalAmount();
            //补充比例(个人)
            String personalSupplementaryRatio = eltAccumulationFundInfo.getPersonalSupplementaryRatio();
            //补充金额(个人)
            String personalSupplementaryAmount = eltAccumulationFundInfo.getPersonalSupplementaryAmount();
            //比例(公司)
            String companyRatio = eltAccumulationFundInfo.getCompanyRatio();
            //金额(公司)
            String companyAmount = eltAccumulationFundInfo.getCompanyAmount();
            //补充比例(公司)
            String companySupplementaryProportion = eltAccumulationFundInfo.getCompanySupplementaryProportion();
            //补充金额(公司)
            String companySupplementaryAmount = eltAccumulationFundInfo.getCompanySupplementaryAmount();

            //基地校验
            if (!StringUtils.isEmpty(baseName)) {
                for (Org base : baseList) {
                    if (base.getBaseOrDeptName().equals(baseName)) {
                        eltAccumulationFundInfo.setBaseId(base.getRowId());
                    }
                }
                if (StringUtils.isEmpty(eltAccumulationFundInfo.getBaseId())) {
                    errMsg.append("基地不存在；");
                }
            }

            //字符长度校验
            //规则名称长度
            if (ruleName != null && ruleName.trim().length() > 50) {
                errMsg.append("规则名称擦汗给长度不能超过50位；");
            }
            //缴费基数长度10
            if (socialInsuranceBase != null && socialInsuranceBase.trim().length() > 10) {
                errMsg.append("缴费基数长度不能超过10位；");
            }
            //个人比例长度10
            if (personalProportion != null && personalProportion.trim().length() > 10) {
                errMsg.append("个人比例长度不能超过10位；");
            }

            //个人金额长度10
            if (personalAmount != null && personalAmount.trim().length() > 10) {
                errMsg.append("个人金额长度不能超过10位；");
            }
            //个人补充比例长度10
            if (personalSupplementaryRatio != null && personalSupplementaryRatio.trim().length() > 10) {
                errMsg.append("个人补充比例长度不能超过10位；");
            }
            //个人补充金额长度10
            if (personalSupplementaryAmount != null && personalSupplementaryAmount.trim().length() > 10) {
                errMsg.append("个人不补充金额长度不能超过10位；");
            }
            //公司比例长度10
            if (companyRatio != null && companyRatio.trim().length() > 10) {
                errMsg.append("公司比例长度不能超过10位；");
            }
            //公司金额长度10
            if (companyAmount != null && companyAmount.trim().length() > 10) {
                errMsg.append("公司金额长度不能超过10位；");
            }
            //公司补充个比例长度10
            if (companySupplementaryProportion != null && companySupplementaryProportion.trim().length() > 10) {
                errMsg.append("公司补充个人比例长度不能超过10位；");
            }
            //公司补充金额长度10
            if (companySupplementaryAmount != null && companySupplementaryAmount.trim().length() > 10) {
                errMsg.append("公司补充金额长度不能超过10位；");
            }

            //EXCEL实体类和公积金实体类的赋值操作
            EltAccumulationFund temp = new EltAccumulationFund();
            BeanUtils.copyProperties(eltAccumulationFundInfo, temp);

            //判断是否有错误信息
            if (!StringUtils.isEmpty(errMsg.toString())) {
                eltAccumulationFundInfo.setErrMsg(errMsg.toString());
                errorEltAccumulationFundInfos.add(eltAccumulationFundInfo);
            } else {
                EltAccumulationFund eltAccumulationFundByNameAndId = eltAccumulationFundDao.getByNameAndId(temp);
                //如果基地名称和规则名称存在，进行更新操作
                if (eltAccumulationFundByNameAndId != null) {
                    eltAccumulationFundDao.update(temp);
                }
                //如果不存在，进行插入操作
                else {
                    eltAccumulationFundDao.add(temp);
                }
            }
        }
        return errorEltAccumulationFundInfos;
    }

    public void exportErrExcel(HttpServletResponse response, List<EltAccumulationFundExcelBean> list, String lang) {
        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("base");
        fieldList.add("ruleName");
        fieldList.add("socialInsuranceBase");
        fieldList.add("personalProportion");
        fieldList.add("personalAmount");
        fieldList.add("companyRatio");
        fieldList.add("companyAmount");
        fieldList.add("personalSupplementaryRatio");
        fieldList.add("personalSupplementaryAmount");
        fieldList.add("companySupplementaryProportion");
        fieldList.add("companySupplementaryAmount");
        fieldList.add("remarks");
        fieldList.add("errMsg");
        String title = "";
        int size = list.size();
        String[] headers = new String[size];

        if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
            headerList.add("基地");
            headerList.add("规则名称");
            headerList.add("缴费基数");
            headerList.add("个人比例");
            headerList.add("个人金额");
            headerList.add("公司比例");
            headerList.add("公司金额");
            headerList.add("个人补充比例");
            headerList.add("个人补充金额");
            headerList.add("公司补充比例");
            headerList.add("公司补充金额");
            headerList.add("备注");
            headerList.add("错误描述");

            title = "公积金规则";
            headers = (String[]) headerList.toArray(new String[0]);
        } else if (lang.equalsIgnoreCase(Constant.EN)) {
            headerList.add("Base");
            headerList.add("Rule Name");
            headerList.add("Social Insurance Base");
            headerList.add("Personal Proportion");
            headerList.add("Personal Amount");
            headerList.add("Company Proportion");
            headerList.add("Company Amount");
            headerList.add("Personal Replenish Proportion");
            headerList.add("Personal Replenish Amount");
            headerList.add("Company Replenish Proportion");
            headerList.add("Company Replenish Amountse");
            headerList.add("remarks");
            headerList.add("Error Description");
            //TODO
            title = "Provident Fund Rules";
            headers = (String[]) headerList.toArray(new String[0]);
        }

        // pojo字段
        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }
}
