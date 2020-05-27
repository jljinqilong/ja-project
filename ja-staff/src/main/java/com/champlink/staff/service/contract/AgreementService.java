package com.champlink.staff.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.staff.contract.Agreement;
import com.champlink.common.form.staff.contract.AgreementForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.dao.contract.AgreementDao;
import com.champlink.staff.service.call.OrgService;

@Service
public class AgreementService {

    @Autowired
    private AgreementDao agreementDao;

    @Autowired
    private OrgService orgService;

    /**
     * 添加
     * 
     * @param
     * @return
     */
    public boolean add(Agreement agreement) {

        agreement.setCreatedTime(new Date());
        agreement.setCreatedBy(RequestContext.get().getStaffId());

        List<Agreement> allList = agreementDao.allList();

        for (Agreement agreement2 : allList) {
            if (agreement.getAgreementNo().equals(agreement2.getAgreementNo())) {
                AppException.create(200006);
            }
        }

        //判断当前添加合同状态
        System.err.println(agreement.getAgreementDateStart());
        Date date = new Date();
        System.err.println("========================" + date);
        //判断协议状态
        if (date.before(agreement.getAgreementDateStart())) {
            agreement.setAgreementState("1");
        } else if (date.before(agreement.getAgreementDateEnd()) && date.after(agreement.getAgreementDateStart())) {
            agreement.setAgreementState("2");
        } else if (date.after(agreement.getAgreementDateEnd())) {
            agreement.setAgreementState("3");
        }
        int months = (agreement.getAgreementDateEnd().getYear() - agreement.getAgreementDateStart().getYear()) * 12
            + (agreement.getAgreementDateEnd().getMonth() - agreement.getAgreementDateStart().getMonth());
        //计算协议期限
        agreement.setAgreementPeriod(months);
        if (agreementDao.add(agreement) > 0) {
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
        if (agreementDao.delById(rowId) > 0) {
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
    public boolean update(Agreement agreement) {

        agreement.setLastUpdateTime(new Date());
        agreement.setLastUpdateBy(RequestContext.get().getStaffId());

        if (agreement.getStatus() == 1) {
            Date date = new Date();
            System.err.println("========================" + date);
            //判断协议状态
            if (date.before(agreement.getAgreementDateStart())) {
                agreement.setAgreementState("1");
            } else if (date.before(agreement.getAgreementDateEnd()) && date.after(agreement.getAgreementDateStart())) {
                agreement.setAgreementState("2");
            } else if (date.after(agreement.getAgreementDateEnd())) {
                agreement.setAgreementState("3");
            }
            int months = (agreement.getAgreementDateEnd().getYear() - agreement.getAgreementDateStart().getYear()) * 12
                + (agreement.getAgreementDateEnd().getMonth() - agreement.getAgreementDateStart().getMonth());
            //计算协议期限
            agreement.setAgreementPeriod(months);
        }
        if (agreementDao.update(agreement) > 0) {
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
    public PageListVO<Agreement> pageList(AgreementForm form) {
        Long deptId = form.getDeptId();
        if (deptId != null) {
            String allOrg = orgService.getAllSonOrg(deptId);
            List<Org> orgList = new ArrayList<>();
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                orgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                Org dept = new Org();
                dept.setRowId(deptId);
                orgList.add(dept);
                form.setDeptIds(orgList);
            }
        }
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Agreement> pageListVO = PageListVO.newInstance(agreementDao.pageList(paginater));
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
    public List<Agreement> getById(Long rowId) {
        return agreementDao.getById(rowId);
    }

    /**
     * 查询协议信息
     * 
     * @param staffId
     * @return
     */
    public List<Agreement> getAgreementByStaffId(Long staffId) {
        return agreementDao.getAgreementByStaffId(staffId);
    }

    /**
     * 查询协议数量
     * 
     * 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年7月26日 下午3:00:19
     * @return
     */
    public String getLastAgreementNo() {
        return agreementDao.getLastAgreementNo();
    }

    /**
     * 关联协议详情 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年7月30日 下午4:54:02
     * @param relevance_agreement
     * @return
     */
    public List<Agreement> getAgreementByContract(String contractNo) {
        return agreementDao.getAgreementByContract(contractNo);
    }

    /**
     * 修改协议续签状态
     * 
     * @author jinlin.tang
     * @date 2018年8月9日 下午8:47:06
     * @param rowId
     * @param renewStatus
     */
    public void updateRenewStauts(Long rowId, String renewStatus) {
        agreementDao.updateRenewStauts(rowId, renewStatus);
    }

    /**
     * 合同详情查看关联协议
     * 
     * @author jinlin.tang
     * @date 2018年8月14日 下午3:56:14
     * @param agreementNo
     * @return
     */
    public List<Agreement> getByAgreementNo(String agreementNo) {
        return agreementDao.getByAgreementNo(agreementNo);
    }

    /**
     * 员工离职解除该员工的协议 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年9月18日 上午10:01:19
     * @param agreement
     */
    public void updateAgreementStateByStaffId(Agreement agreement) {
        agreementDao.updateAgreementStateByStaffId(agreement);
    }

    /**
     * 解除合同是关联协议解除 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年9月18日 上午10:02:12
     * @param agreement
     */
    public void updateAgreementStateByContractNo(Agreement agreement) {
        agreementDao.updateAgreementStateByContractNo(agreement);
    }

}
