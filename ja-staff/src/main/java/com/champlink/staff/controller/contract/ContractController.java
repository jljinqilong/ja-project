package com.champlink.staff.controller.contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.champlink.common.domain.staff.contract.Contract;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.ModuleLog;
import com.champlink.common.form.staff.contract.ContractForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.service.baseInfo.BaseInfoService;
import com.champlink.staff.service.call.OrgService;
import com.champlink.staff.service.call.SystemService;
import com.champlink.staff.service.contract.AgreementService;
import com.champlink.staff.service.contract.ContractService;

@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController {

    /**
     * 合同信息
     */
    @Autowired
    private ContractService contractService;

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
    @RequestMapping(value = "/add")
    public String add(Contract contract) {
        String renewStatus = contract.getRenewStatus();
        contract.setRenewStatus("0");
        if (contract.getContractNo() == null || contract.getContractNo() == "") {
            String count = "0";
            String num = contractService.getLastContractNo();
            if (num != null) {
                count = num;
            }
            String str = systemService.generate(StaffConstant.CONTRACT_NO, count);
            if (str != null) {
                JSONObject parseObject = JSONObject.parseObject(str);
                if ((Integer) parseObject.get("code") == 200) {
                    String codeResult = String.valueOf(parseObject.get("data"));
                    JSONObject parseObject1 = JSONObject.parseObject(codeResult);
                    String contractNo = String.valueOf(parseObject1.get("codeResult"));
                    contract.setContractNo(contractNo);
                }
            }

        }
        if (contractService.add(contract)) {
            if (contract.getStatus() != null) {
                contractService.updateRenewStauts(contract.getRowId(), renewStatus);
                String optType = Constant.OPT_RENEW;
                String appCode = Constant.APP_CODE_STAFF_CONTRACT;
                Long tableId = contract.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe("续签合同号为：" + contract.getContractNo());
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
        if (contractService.del(id)) {
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
    public String update(Contract contract) {
        String optDescribe = "";
        if (contract.getStatus() == 1) {//修改信息
            List<Contract> list1 = new ArrayList<>();
            list1.add(contract);
            List<Contract> list = contractService.getById(contract.getRowId());
            String workPlace = contract.getWorkPlace();
            String file = contract.getFile();
            String describe = contract.getDescribe();
            String allCode = systemService.getAllCode();
            if (allCode != null) {
                JSONObject parseObject = JSONObject.parseObject(allCode);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    list = super.translateIdToName(list, allCodeList, new String[] {"getcontractBackups,rowId,name"});
                    list = super.translateIdToName(list, allCodeList, new String[] {"signAgreement,rowId,name"});
                    list = super.translateIdToName(list, allCodeList, new String[] {"contractType,rowId,name", "contractPeriodType,rowId,name"});
                    list1 = super.translateIdToName(list1, allCodeList, new String[] {"getcontractBackups,rowId,name"});
                    list1 = super.translateIdToName(list1, allCodeList, new String[] {"signAgreement,rowId,name"});
                    list1 = super.translateIdToName(list1, allCodeList, new String[] {"contractType,rowId,name", "contractPeriodType,rowId,name"});
                }
            }
            String getcontractBackups = (String) list.get(0).getTransNames().get("getcontractBackups_name");
            String getcontractBackups1 = (String) list1.get(0).getTransNames().get("getcontractBackups_name");
            String signAgreement = (String) list.get(0).getTransNames().get("signAgreement_name");
            String signAgreement1 = (String) list1.get(0).getTransNames().get("signAgreement_name");
            if (contract.getContractState().equals("1")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date signDate = contract.getSignDate();
                Date contractDateStart = contract.getContractDateStart();
                Date contractDateEnd = contract.getContractDateEnd();
                String owner = contract.getOwner();
                String contractType = (String) list.get(0).getTransNames().get("contractType_name");
                String contractType1 = (String) list1.get(0).getTransNames().get("contractType_name");
                String contractPeriodType = (String) list.get(0).getTransNames().get("contractPeriodType_name");
                String contractPeriodType1 = (String) list1.get(0).getTransNames().get("contractPeriodType_name");
                if (!contractType.equals(contractType1)) {
                    optDescribe = optDescribe + "合同类型:" + contractType + "改为" + contractType1 + ";";
                }
                if (!contractPeriodType.equals(contractPeriodType1)) {
                    optDescribe = optDescribe + "合同期限类型:" + contractPeriodType + "改为" + contractPeriodType1 + ";";
                }
                if (signDate != null && !list.get(0).getSignDate().equals(signDate)) {
                    optDescribe = optDescribe + "合同签订日期:" + sdf.format(list.get(0).getSignDate()) + "改为" + sdf.format(signDate) + ";";
                }
                if (contractDateStart != null && !list.get(0).getContractDateStart().equals(contractDateStart)) {
                    optDescribe = optDescribe + "合同生效日期:" + sdf.format(list.get(0).getContractDateStart()) + "改为" + sdf.format(contractDateStart) + ";";
                }
                if (contractDateEnd != null && !list.get(0).getContractDateEnd().equals(contractDateEnd)) {
                    optDescribe = optDescribe + "合同终止日期:" + sdf.format(list.get(0).getContractDateEnd()) + "改为" + sdf.format(contractDateEnd) + ";";
                }
                if (owner != "" && owner != null && !list.get(0).getOwner().equals(owner)) {
                    optDescribe = optDescribe + "甲方:" + list.get(0).getOwner() + "改为" + owner + ";";
                }
            }
            if (workPlace != "" && workPlace != null && list.get(0).getWorkPlace() == null) {
                optDescribe = optDescribe + "添加工作地点:" + workPlace + ";";
            } else if (workPlace != "" && workPlace != null && !list.get(0).getWorkPlace().equals(workPlace)) {
                optDescribe = optDescribe + "工作地点:" + list.get(0).getWorkPlace() + "改为" + workPlace + ";";
            }
            if (!getcontractBackups.equals(getcontractBackups1)) {
                optDescribe = optDescribe + "领取合同备份:" + getcontractBackups + "改为" + getcontractBackups1 + ";";
            }
            if (!signAgreement.equals(signAgreement1)) {
                optDescribe = optDescribe + "签订相关协议:" + signAgreement + "改为" + signAgreement1 + ";";
            }
            if (file != "" && file != null && !list.get(0).getFile().equals(file)) {
                optDescribe = optDescribe + "上传文件路径:" + list.get(0).getFile() + "改为" + file + ";";
            }
            if (describe != "" && describe != null && list.get(0).getDescribe() == null) {
                optDescribe = optDescribe + "添加备注信息:" + describe + ";";
            } else if (describe != "" && describe != null && !list.get(0).getDescribe().equals(describe)) {
                optDescribe = optDescribe + "备注信息:" + list.get(0).getDescribe() + "改为" + describe + ";";
            }
        } else if (contract.getStatus() == 2) {//解除
            optDescribe = "解除原因是：" + contract.getRelieveReason();
        } else if (contract.getStatus() == 3) {//终止
            optDescribe = "终止原因是：" + contract.getEndReason();
        } else if (contract.getStatus() == 4) {//中止
            optDescribe = "中止原因是：" + contract.getDiscontinueReason();
        }
        if (contractService.update(contract)) {
            if (contract.getStatus() == 1) {
                if (optDescribe != "") {
                    String optType = Constant.OPT_UPDATE;
                    String appCode = Constant.APP_CODE_STAFF_CONTRACT;
                    Long tableId = contract.getRowId();
                    ModuleLog moduleLog = new ModuleLog();
                    moduleLog.setTableId(tableId);
                    moduleLog.setOptType(optType);
                    moduleLog.setAppCode(appCode);
                    moduleLog.setOptDescribe(optDescribe);
                    systemService.add(moduleLog);
                }
            } else if (contract.getStatus() == 2) {
                BaseInfo baseInfo = new BaseInfo();
                baseInfo.setRowId(contract.getStaffId());
                String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
                if (isOnJob != null) {
                    JSONObject parseObject = JSONObject.parseObject(isOnJob);
                    if ((Integer) parseObject.get("code") == 200) {
                        List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                        if (isOnJobList != null && isOnJobList.size() > 0) {
                            for (Code code : isOnJobList) {
                                if (code.getName().equals("离职")) {
                                    baseInfo.setJobStatus(code.getRowId());
                                }
                            }
                        }
                    }
                }
                baseInfo.setLeaveDate(contract.getRelieveDate());
                baseInfoService.updateBaseInfoByCotract(baseInfo);
                String staffNo = baseInfoService.getBaseInfoByStaffId(contract.getStaffId()).getStaffNo();
                systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
                Agreement agreement = new Agreement();
                agreement.setRelevanceContract(contract.getContractNo());
                agreement.setRelieveDate(contract.getRelieveDate());
                agreement.setRelieveReason(contract.getRelieveReason());
                agreement.setAgreementState("4");
                agreementService.updateAgreementStateByContractNo(agreement);
                String optType = Constant.OPT_RELIEVE;
                String appCode = Constant.APP_CODE_STAFF_CONTRACT;
                Long tableId = contract.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe(optDescribe);
                systemService.add(moduleLog);
            } else if (contract.getStatus() == 3) {
                BaseInfo baseInfo = new BaseInfo();
                baseInfo.setRowId(contract.getStaffId());
                String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
                if (isOnJob != null) {
                    JSONObject parseObject = JSONObject.parseObject(isOnJob);
                    if ((Integer) parseObject.get("code") == 200) {
                        List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                        if (isOnJobList != null && isOnJobList.size() > 0) {
                            for (Code code : isOnJobList) {
                                if (code.getName().equals("离职")) {
                                    baseInfo.setJobStatus(code.getRowId());
                                }
                            }
                        }
                    }
                }
                baseInfo.setLeaveDate(contract.getRelieveDate());
                baseInfoService.updateBaseInfoByCotract(baseInfo);
                String staffNo = baseInfoService.getBaseInfoByStaffId(contract.getStaffId()).getStaffNo();
                systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
                Agreement agreement = new Agreement();
                agreement.setRelevanceContract(contract.getContractNo());
                agreement.setEndDate(contract.getEndDate());
                agreement.setEndReason(contract.getEndReason());
                agreement.setAgreementState("5");
                agreementService.updateAgreementStateByContractNo(agreement);
                String optType = Constant.OPT_END;
                String appCode = Constant.APP_CODE_STAFF_CONTRACT;
                Long tableId = contract.getRowId();
                ModuleLog moduleLog = new ModuleLog();
                moduleLog.setTableId(tableId);
                moduleLog.setOptType(optType);
                moduleLog.setAppCode(appCode);
                moduleLog.setOptDescribe(optDescribe);
                systemService.add(moduleLog);
            } else if (contract.getStatus() == 4) {
                String optType = Constant.OPT_DISCONTINUE;
                String appCode = Constant.APP_CODE_STAFF_CONTRACT;
                Long tableId = contract.getRowId();
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
     * 查询合同信息
     * 
     * @param ContractFormForm
     * @return
     */
    @RequestMapping(value = "/getByStaffId", produces = "text/json;charset=UTF-8")
    public String getByStaffId(@RequestParam("straffId") Long straffId) {
        Contract contract = contractService.getByStaffId(straffId);
        return getSuccessJson(contract);
    }

    /**
     * 查询列表
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(ContractForm contractForm) {
        if (contractForm.getContractNo() != null) {
            contractForm.setContractNo(contractForm.getContractNo().trim());
        }
        if (contractForm.getStaffNo() != null) {
            contractForm.setStaffNo(contractForm.getStaffNo().trim());
        }
        if (contractForm.getStaffName() != null) {
            contractForm.setStaffName(contractForm.getStaffName().trim());
        }

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        contractForm.setStaffIdList(staffIdList);

        PageListVO<Contract> pageListVO = contractService.pageList(contractForm);
        List<Long> staffIds = pageListVO.getList().stream().map(contract -> contract.getStaffId()).collect(Collectors.toList());
        List<BaseInfo> baseInfoList = baseInfoService.getBaseInfoByIds(staffIds);
        List<Contract> list = super.translateIdToName(pageListVO.getList(), baseInfoList, new String[] {"staffId,rowId,staffName;staffNo"});
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"contractType,rowId,name", "contractPeriodType,rowId,name"});
            }
        }
        String contractCode = systemService.getByTpye(StaffConstant.CONTRACT_STATE);
        if (contractCode != null) {
            JSONObject parseObject = JSONObject.parseObject(contractCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> contractCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, contractCodeList, new String[] {"contractState,code,name"});
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
        List<Contract> contract = contractService.getById(rowId);
        BaseInfo baseInfo = baseInfoService.getBaseInfoByStaffId(contract.get(0).getStaffId());
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
        List<Agreement> list1 = agreementService.getAgreementByContract(contract.get(0).getContractNo());
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"jobStatus,rowId,name"});
                list1 = super.translateIdToName(list1, allCodeList, new String[] {"agreementType,rowId,name"});
                contract = super.translateIdToName(contract, allCodeList, new String[] {"contractType,rowId,name", "contractPeriodType,rowId,name", "getcontractBackups,rowId,name"});
                contract = super.translateIdToName(contract, allCodeList, new String[] {"signAgreement,rowId,name"});
            }
        }
        String agreementCode = systemService.getByTpye(StaffConstant.AGREEMENT_STATE);
        if (agreementCode != null) {
            JSONObject parseObject = JSONObject.parseObject(agreementCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> agreementCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list1 = super.translateIdToName(list1, agreementCodeList, new String[] {"agreementState,code,name"});
            }
        }
        String contractCode = systemService.getByTpye(StaffConstant.CONTRACT_STATE);
        if (contractCode != null) {
            JSONObject parseObject = JSONObject.parseObject(contractCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> contractCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                contract = super.translateIdToName(contract, contractCodeList, new String[] {"contractState,code,name"});
            }
        }
        String renewCode = systemService.getByTpye(StaffConstant.RENEW_STATUS);
        if (renewCode != null) {
            JSONObject parseObject = JSONObject.parseObject(renewCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> renewStatusCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                contract = super.translateIdToName(contract, renewStatusCodeList, new String[] {"renewStatus,code,name"});
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
        contract.get(0).setAgreement(list1);
        contract.get(0).setBaseInfo(list.get(0));
        return getSuccessJson(contract);
    }

    /**
     * 根据员工ID查询所有合同
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/getContractByStaffId/{staffId}", produces = "text/json;charset=UTF-8")
    public String getContractByStaffId(@PathVariable("staffId") Long staffId) {
        List<Contract> contract = contractService.getContractByStaffId(staffId);
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                contract = super.translateIdToName(contract, allCodeList, new String[] {"contractType,rowId,name", "contractPeriodType,rowId,name"});
            }
        }
        String contractCode = systemService.getByTpye(StaffConstant.CONTRACT_STATE);
        if (contractCode != null) {
            JSONObject parseObject = JSONObject.parseObject(contractCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> contractCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                contract = super.translateIdToName(contract, contractCodeList, new String[] {"contractState,code,name"});
            }
        }
        return getSuccessJson(contract);
    }

}