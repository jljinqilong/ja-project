package com.champlink.staff.controller.contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.OrgConstant;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.staff.contract.Agreement;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.ModuleLog;
import com.champlink.common.form.staff.contract.AgreementForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.service.baseInfo.BaseInfoService;
import com.champlink.staff.service.call.OrgService;
import com.champlink.staff.service.call.SystemService;
import com.champlink.staff.service.contract.AgreementService;

@RestController
@RequestMapping("/agreement")
public class AgreementController extends BaseController {

    /**
     * 员工协议信息
     */
    @Autowired
    private AgreementService agreementService;

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OrgService orgService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(Agreement agreement) {
        String renewStatus = agreement.getRenewStatus();
        agreement.setRenewStatus("0");
        if (agreement.getAgreementNo() == null || agreement.getAgreementNo() == "") {
            String count = "0";
            String num = agreementService.getLastAgreementNo();
            if (num != null) {
                count = num;
            }
            String str = systemService.generate(StaffConstant.AGREEMENT_NO, count);
            if (str != null) {
                JSONObject parseObject = JSONObject.parseObject(str);
                if ((Integer) parseObject.get("code") == 200) {
                    String codeResult = String.valueOf(parseObject.get("data"));
                    JSONObject parseObject1 = JSONObject.parseObject(codeResult);
                    String agreementNo = String.valueOf(parseObject1.get("codeResult"));
                    agreement.setAgreementNo(agreementNo);
                }
            }
        }
        if (agreementService.add(agreement)) {
            if (agreement.getStatus() != null) {
                agreementService.updateRenewStauts(agreement.getRowId(), renewStatus);
                String optType = Constant.OPT_RENEW;
                String appCode = Constant.APP_CODE_STAFF_AGREEMENT;
                Long tableId = agreement.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe("续签协议号为：" + agreement.getAgreementNo());
                systemService.add(moduleLog);
            }
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (agreementService.del(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(Agreement agreement) {
        String optDescribe = "";
        if (agreement.getStatus() == 1) {
            List<Agreement> list1 = new ArrayList<>();
            list1.add(agreement);
            List<Agreement> list = agreementService.getById(agreement.getRowId());
            String relevanceContract = agreement.getRelevanceContract();
            String file = agreement.getFile();
            String describe = agreement.getDescribe();
            String allCode = systemService.getAllCode();
            if (allCode != null) {
                JSONObject parseObject = JSONObject.parseObject(allCode);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    list = super.translateIdToName(list, allCodeList, new String[] {"agreementType,rowId,name"});
                    list1 = super.translateIdToName(list1, allCodeList, new String[] {"agreementType,rowId,name"});
                }
            }
            if (agreement.getAgreementState().equals("1")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date signDate = agreement.getSignDate();
                Date agreementDateStart = agreement.getAgreementDateStart();
                Date agreementDateEnd = agreement.getAgreementDateEnd();
                String owner = agreement.getOwner();
                String agreementType = (String) list.get(0).getTransNames().get("agreementType_name");
                String agreementType1 = (String) list1.get(0).getTransNames().get("agreementType_name");
                if (!agreementType.equals(agreementType1)) {
                    optDescribe = optDescribe + "协议类型:" + agreementType + "改为" + agreementType1 + ";";
                }
                if (signDate != null && !list.get(0).getSignDate().equals(signDate)) {
                    optDescribe = optDescribe + "协议签订日期:" + sdf.format(list.get(0).getSignDate()) + "改为" + sdf.format(signDate) + ";";
                }
                if (agreementDateStart != null && !list.get(0).getAgreementDateStart().equals(agreementDateStart)) {
                    optDescribe = optDescribe + "协议生效日期:" + sdf.format(list.get(0).getAgreementDateStart()) + "改为" + sdf.format(agreementDateStart) + ";";
                }
                if (agreementDateEnd != null && !list.get(0).getAgreementDateEnd().equals(agreementDateEnd)) {
                    optDescribe = optDescribe + "协议终止日期:" + sdf.format(list.get(0).getAgreementDateEnd()) + "改为" + sdf.format(agreementDateEnd) + ";";
                }
                if (owner != "" && owner != null && !list.get(0).getOwner().equals(owner)) {
                    optDescribe = optDescribe + "签订单位:" + list.get(0).getOwner() + "改为" + owner + ";";
                }
            }
            if (relevanceContract != "" && relevanceContract != null && !relevanceContract.equals(list.get(0).getRelevanceContract())) {
                optDescribe = optDescribe + "关联合同编号:" + list.get(0).getRelevanceContract() + "改为" + relevanceContract + ";";
            }
            if (file != "" && file != null && !list.get(0).getFile().equals(file)) {
                optDescribe = optDescribe + "上传文件路径:" + list.get(0).getFile() + "改为" + file + ";";
            }
            if (describe != "" && describe != null && list.get(0).getDescribe() == null) {
                optDescribe = optDescribe + "添加备注信息:" + describe + ";";
            } else if (describe != "" && describe != null && !list.get(0).getDescribe().equals(describe)) {
                optDescribe = optDescribe + "备注信息:" + list.get(0).getDescribe() + "改为" + describe + ";";
            }
        } else if (agreement.getStatus() == 2) {
            optDescribe = "解除原因是：" + agreement.getRelieveReason();
        } else if (agreement.getStatus() == 3) {
            optDescribe = "终止原因是：" + agreement.getEndReason();
        } else if (agreement.getStatus() == 4) {//中止
            optDescribe = "中止原因是：" + agreement.getDiscontinueReason();
        }
        if (agreementService.update(agreement)) {
            if (agreement.getStatus() == 1) {
                if (optDescribe != "") {
                    String optType = Constant.OPT_UPDATE;
                    String appCode = Constant.APP_CODE_STAFF_AGREEMENT;
                    Long tableId = agreement.getRowId();
                    ModuleLog moduleLog = new ModuleLog();
                    moduleLog.setTableId(tableId);
                    moduleLog.setOptType(optType);
                    moduleLog.setAppCode(appCode);
                    moduleLog.setOptDescribe(optDescribe);
                    systemService.add(moduleLog);
                }
            } else if (agreement.getStatus() == 2) {
                String optType = Constant.OPT_RELIEVE;
                String appCode = Constant.APP_CODE_STAFF_AGREEMENT;
                Long tableId = agreement.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe(optDescribe);
                systemService.add(moduleLog);
            } else if (agreement.getStatus() == 3) {
                String optType = Constant.OPT_END;
                String appCode = Constant.APP_CODE_STAFF_AGREEMENT;
                Long tableId = agreement.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe(optDescribe);
                systemService.add(moduleLog);
            } else if (agreement.getStatus() == 4) {
                String optType = Constant.OPT_DISCONTINUE;
                String appCode = Constant.APP_CODE_STAFF_CONTRACT;
                Long tableId = agreement.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe(optDescribe);
                systemService.add(moduleLog);
            }
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询列表
     * 
     * @param AgreementForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(AgreementForm agreementForm) {
        if (agreementForm.getAgreementNo() != null) {
            agreementForm.setAgreementNo(agreementForm.getAgreementNo().trim());
        }
        if (agreementForm.getStaffNo() != null) {
            agreementForm.setStaffNo(agreementForm.getStaffNo().trim());
        }
        if (agreementForm.getStaffName() != null) {
            agreementForm.setStaffName(agreementForm.getStaffName().trim());
        }

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        agreementForm.setStaffIdList(staffIdList);

        PageListVO<Agreement> pageListVO = agreementService.pageList(agreementForm);
        List<Long> staffIds = pageListVO.getList().stream().map(contract -> contract.getStaffId()).collect(Collectors.toList());
        List<BaseInfo> baseInfoList = baseInfoService.getBaseInfoByIds(staffIds);
        List<Agreement> list = super.translateIdToName(pageListVO.getList(), baseInfoList, new String[] {"staffId,rowId,staffName;staffNo"});
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"agreementType,rowId,name"});
            }
        }
        String agreementCode = systemService.getByTpye(StaffConstant.AGREEMENT_STATE);
        if (agreementCode != null) {
            JSONObject parseObject = JSONObject.parseObject(agreementCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> agreementCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, agreementCodeList, new String[] {"agreementState,code,name"});
            }
        }
        String renewCode = systemService.getByTpye(StaffConstant.RENEW_STATUS);
        if (renewCode != null) {
            JSONObject parseObject = JSONObject.parseObject(renewCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> agreementCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, agreementCodeList, new String[] {"renewStatus,code,name"});
            }
        }
        pageListVO.setList(list);
        return getSuccessJson(pageListVO);
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        List<Agreement> agreement = agreementService.getById(rowId);
        BaseInfo baseInfo = baseInfoService.getBaseInfoByStaffId(agreement.get(0).getStaffId());
        List<BaseInfo> list = new ArrayList<>();
        list.add(baseInfo);
        //职衔
        String positonJson = orgService.getAllPosition();
        List<Position> positionList = new ArrayList<>();
        if (positonJson != null) {
            JSONObject parseObject = JSONObject.parseObject(positonJson);
            if ((Integer) parseObject.get("code") == 200) {
                positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                list = super.translateIdToName(list, positionList, new String[] {"positionId,rowId,positionName"});
            }
        }

        //职等/赋值名称
        String gradeJson = orgService.getAllGrade();
        List<Grade> gradeList = new ArrayList<>();
        if (gradeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(gradeJson);
            if ((Integer) parseObject.get("code") == 200) {
                gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                list = super.translateIdToName(list, gradeList, new String[] {"gradeId,rowId,gradeName"});
            }
        }

        //职级
        String rankJson = orgService.getAllRank();
        List<Rank> rankList = new ArrayList<>();
        if (rankJson != null) {
            JSONObject parseObject = JSONObject.parseObject(rankJson);
            if ((Integer) parseObject.get("code") == 200) {
                rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                list = super.translateIdToName(list, rankList, new String[] {"rankId,rowId,rankName"});
            }
        }
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"jobStatus,rowId,name"});
                agreement = super.translateIdToName(agreement, allCodeList, new String[] {"agreementType,rowId,name"});
            }
        }
        String allOrg = orgService.getAllOrg();
        if (allOrg != null) {
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                list = super.translateIdToName(list, allOrgList, new String[] {"deptId,rowId,baseOrDeptName", "baseId,rowId,baseOrDeptName"});
            }
        }
        String agreementCode = systemService.getByTpye(StaffConstant.AGREEMENT_STATE);
        if (agreementCode != null) {
            JSONObject parseObject = JSONObject.parseObject(agreementCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> agreementCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                agreement = super.translateIdToName(agreement, agreementCodeList, new String[] {"agreementState,code,name"});
            }
        }
        String renewCode = systemService.getByTpye(StaffConstant.RENEW_STATUS);
        if (renewCode != null) {
            JSONObject parseObject = JSONObject.parseObject(renewCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> agreementCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                agreement = super.translateIdToName(agreement, agreementCodeList, new String[] {"renewStatus,code,name"});
            }
        }
        agreement.get(0).setBaseInfo(list.get(0));
        return getSuccessJson(agreement);
    }

    /**
     * 查询信息
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/getAgreementByStaffId/{staffId}", produces = "text/json;charset=UTF-8")
    public String getAgreementByStaffId(@PathVariable("staffId") Long straffId) {
        List<Agreement> agreement = agreementService.getAgreementByStaffId(straffId);
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                agreement = super.translateIdToName(agreement, allCodeList, new String[] {"agreementType,rowId,name"});
            }
        }
        String agreementCode = systemService.getByTpye(StaffConstant.AGREEMENT_STATE);
        if (agreementCode != null) {
            JSONObject parseObject = JSONObject.parseObject(agreementCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> agreementCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                agreement = super.translateIdToName(agreement, agreementCodeList, new String[] {"agreementState,code,name"});
            }
        }
        return getSuccessJson(agreement);
    }

    /**
     * 合同详情查看关联协议
     * 
     * @param agreementNo
     * @return
     */
    @RequestMapping(value = "/getByAgreementNo/{agreementNo}", produces = "text/json;charset=UTF-8")
    public String getByAgreementNo(@PathVariable("agreementNo") String agreementNo) {
        List<Agreement> agreement = agreementService.getByAgreementNo(agreementNo);
        BaseInfo baseInfo = baseInfoService.getBaseInfoByStaffId(agreement.get(0).getStaffId());
        List<BaseInfo> list = new ArrayList<>();
        list.add(baseInfo);
        //职衔
        String positonJson = orgService.getAllPosition();
        List<Position> positionList = new ArrayList<>();
        if (positonJson != null) {
            JSONObject parseObject = JSONObject.parseObject(positonJson);
            if ((Integer) parseObject.get("code") == 200) {
                positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                list = super.translateIdToName(list, positionList, new String[] {"positionId,rowId,positionName"});
            }
        }

        //职等/赋值名称
        String gradeJson = orgService.getAllGrade();
        List<Grade> gradeList = new ArrayList<>();
        if (gradeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(gradeJson);
            if ((Integer) parseObject.get("code") == 200) {
                gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                list = super.translateIdToName(list, gradeList, new String[] {"gradeId,rowId,gradeName"});
            }
        }

        //职级
        String rankJson = orgService.getAllRank();
        List<Rank> rankList = new ArrayList<>();
        if (rankJson != null) {
            JSONObject parseObject = JSONObject.parseObject(rankJson);
            if ((Integer) parseObject.get("code") == 200) {
                rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                list = super.translateIdToName(list, rankList, new String[] {"rankId,rowId,rankName"});
            }
        }
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"jobStatus,rowId,name"});
                agreement = super.translateIdToName(agreement, allCodeList, new String[] {"agreementType,rowId,name"});
            }
        }
        String allOrg = orgService.getAllOrg();
        if (allOrg != null) {
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                list = super.translateIdToName(list, allOrgList, new String[] {"deptId,rowId,baseOrDeptName", "baseId,rowId,baseOrDeptName"});
            }
        }
        agreement.get(0).setBaseInfo(list.get(0));
        return getSuccessJson(agreement);
    }

}
