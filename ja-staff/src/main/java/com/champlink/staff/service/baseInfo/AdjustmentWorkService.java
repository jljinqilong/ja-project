package com.champlink.staff.service.baseInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.staff.baseInfo.AdjustmentWork;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.staff.contract.Agreement;
import com.champlink.common.domain.staff.contract.Contract;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.staff.baseInfo.ImportAdjustmentWork;
import com.champlink.common.form.staff.baseInfo.SearchAdjustmentWork;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.dao.baseInfo.AdjustmentWorkMapper;
import com.champlink.staff.dao.baseInfo.BaseInfoMapper;
import com.champlink.staff.service.call.OrgService;
import com.champlink.staff.service.call.SystemService;
import com.champlink.staff.service.contract.AgreementService;
import com.champlink.staff.service.contract.ContractService;

/**
 * 外部工作经历表
 * 
 * @author natyu
 * @date 2018年7月5日 上午10:20:46
 *
 */
@Service
public class AdjustmentWorkService {

    @Autowired
    private AdjustmentWorkMapper adjustmentWorkMapper;

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private BaseInfoMapper baseInfoMapper;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private AgreementService agreementService;

    @Autowired
    private WorkerCodeRuleService workerCodeRuleService;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfAdjustmentWork
     */
    public boolean addAdjustmentWork(String[] ids, AdjustmentWork adjustmentWork) {
        if (adjustmentWorkMapper.insertAdjustmentWork(ids, adjustmentWork) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 物理删除
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:14
     * @param staffId
     */
    public boolean delAdjustmentWorkListByStaffId(Long staffId) {
        if (adjustmentWorkMapper.delAdjustmentWorkListByStaffId(staffId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:13:01
     * @param staffId
     * @param changeType
     * @return
     */
    public List<AdjustmentWork> getAdjustmentWorkList(Long staffId, String changeType) {
        return adjustmentWorkMapper.queryAdjustmentWorkList(staffId, changeType);
    }

    public List<AdjustmentWork> getAdjustmentBy(Long staffId, String changeType) {
        return adjustmentWorkMapper.getAdjustmentBy(staffId, changeType);
    }

    public AdjustmentWork getByRowId(Long rowId) {
        return adjustmentWorkMapper.getByRowId(rowId);
    }

    /**
     * 离职异动
     * 
     * @author natyu
     * @date 2018年8月3日 下午8:37:30
     * @param form
     * @return
     */
    @Transactional
    public boolean staffLeaveOffice(SearchBaseInfoForm form) {
        try {
            String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
            Long isOnJobId = null;
            if (isOnJob != null) {
                JSONObject parseObject = JSONObject.parseObject(isOnJob);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (isOnJobList != null && isOnJobList.size() > 0) {
                        for (Code code : isOnJobList) {
                            if (code.getName().equals("离职")) {
                                isOnJobId = code.getRowId();
                            }
                        }
                    }
                }
            }

            String idsStr = form.getIds();
            String[] ids = idsStr.split(",");
            Long isBlackList = form.getIsBlacklist();
            Date leaveDate = form.getLeaveDate();

            baseInfoService.updateLeaveOffice(ids, isBlackList, leaveDate, isOnJobId);

            List<Long> staffIds = new ArrayList<>();
            for (String id : ids) {
                staffIds.add(Long.parseLong(id));
            }

            List<BaseInfo> staffList = baseInfoService.getBaseInfoByIds(staffIds);

            if (staffList != null && staffList.size() > 0) {
                List<String> staffNoList = new ArrayList<>();
                for (BaseInfo baseInfo : staffList) {
                    staffNoList.add(baseInfo.getStaffNo());
                }

                //用户置为无效
                systemService.changeByUserNameList(staffNoList, Constant.DEL_FLAG_INVALID);
            }

            AdjustmentWork adjustmentWork = new AdjustmentWork();
            adjustmentWork.setChangeType("DIMISSION");
            adjustmentWork.setChangeDate(form.getLeaveDate());
            adjustmentWorkMapper.insertAdjustmentWork(ids, adjustmentWork);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    /**
     * 
     * 员工异动
     * 
     * @author jinlin.tang
     * @date 2018年8月27日 上午10:23:13
     * @param adjustmentWork
     * @return
     */
    public boolean addStaffMove(AdjustmentWork adjustmentWork) {
        String staffNo = baseInfoService.getBaseInfoByStaffId(adjustmentWork.getStaffId()).getStaffNo();
        adjustmentWork.setCreatedTime(new Date());
        adjustmentWork.setCreatedBy(RequestContext.get().getStaffId());

        String changeType = adjustmentWork.getChangeType();
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setStaffNo(adjustmentWork.getNewStaffNo());
        baseInfo.setRowId(adjustmentWork.getStaffId());
        if (changeType.equals(Constant.INNER_MOBILIZATION) || changeType.equals(Constant.REHIRE) || changeType.equals(Constant.RETURN_REHIRE)) {
            baseInfo.setBaseId(Long.valueOf(adjustmentWork.getNewBase()));
            baseInfo.setDeptId(adjustmentWork.getNewDeptId());
            baseInfo.setPositionId(Long.valueOf(adjustmentWork.getNewPosition()));
            baseInfo.setGradeId(Long.valueOf(adjustmentWork.getNewGrade()));
            baseInfo.setRankId(Long.valueOf(adjustmentWork.getNewRank()));
            baseInfo.setStaffClassify(adjustmentWork.getStaffClassify());
        }
        if (!changeType.equals(Constant.RETIRE) && !changeType.equals(Constant.DIMISSION)) {
            Integer currentId = adjustmentWork.getCurrentId();
            if (currentId != null) { // 如果是自动生成的编号就更新WorkerCodeRule表的currentId  如果是手输的不需要更新
                workerCodeRuleService.updateByDeptId(baseInfo.getBaseId(), currentId);
            }
        }
        if (changeType.equals(Constant.TEMPORARILY) || changeType.equals(Constant.EXPATRIATE)) {
            //职衔
            String positonJson = orgService.getAllPosition();
            List<Position> positionList = new ArrayList<>();
            if (positonJson != null) {
                JSONObject parseObject = JSONObject.parseObject(positonJson);
                if ((Integer) parseObject.get("code") == 200) {
                    positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                    if (positionList != null && positionList.size() > 0) {
                        for (Position position : positionList) {
                            if (position.getRowId().equals(Long.valueOf(adjustmentWork.getNewPosition()))) {
                                adjustmentWork.setNewPosition(position.getPositionName());
                                break;
                            }
                        }
                    }
                }
            }

            //职等/赋值名称
            String gradeJson = orgService.getAllGrade();
            List<Grade> gradeList = new ArrayList<>();
            if (gradeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(gradeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                    if (gradeList != null && gradeList.size() > 0) {
                        for (Grade grade : gradeList) {
                            if (grade.getRowId().equals(Long.valueOf(adjustmentWork.getNewGrade()))) {
                                adjustmentWork.setNewGrade(grade.getGradeName());
                                break;
                            }
                        }
                    }
                }
            }

            //职级
            String rankJson = orgService.getAllRank();
            List<Rank> rankList = new ArrayList<>();
            if (rankJson != null) {
                JSONObject parseObject = JSONObject.parseObject(rankJson);
                if ((Integer) parseObject.get("code") == 200) {
                    rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                    if (rankList != null && rankList.size() > 0) {
                        for (Rank rank : rankList) {
                            if (rank.getRowId().equals(Long.valueOf(adjustmentWork.getNewRank()))) {
                                adjustmentWork.setNewRank(rank.getRankName());
                                break;
                            }
                        }
                    }
                }
            }
            String allOrg = orgService.getAllOrg();
            if (allOrg != null) {
                JSONObject parseObject = JSONObject.parseObject(allOrg);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                    if (allOrgList != null && allOrgList.size() > 0) {
                        for (Org org : allOrgList) {
                            if (org.getRowId().equals(Long.valueOf(adjustmentWork.getNewBase()))) {
                                adjustmentWork.setNewBase(org.getBaseOrDeptName());
                                break;
                            }
                        }
                        for (Org org : allOrgList) {
                            if (org.getRowId().equals(Long.valueOf(adjustmentWork.getNewDept()))) {
                                adjustmentWork.setNewDept(org.getBaseOrDeptName());
                                break;
                            }
                        }
                    }
                }
            }
            //员工分类
            String staffClassJson = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
            List<Code> staffClassList = new ArrayList<>();
            if (staffClassJson != null) {
                JSONObject parseObject = JSONObject.parseObject(staffClassJson);
                if ((Integer) parseObject.get("code") == 200) {
                    staffClassList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (staffClassList != null && staffClassList.size() > 0) {
                        for (Code code : staffClassList) {
                            if (code.getCode().equals(adjustmentWork.getStaffClassify())) {
                                adjustmentWork.setStaffClassify(code.getName());
                                break;
                            }
                        }
                    }
                }
            }
            adjustmentWorkMapper.addStaffMove(adjustmentWork);
            baseInfoMapper.updateBaseInfo(baseInfo);
            systemService.updateUserName(staffNo, adjustmentWork.getNewStaffNo());
        }
        if (changeType.equals(Constant.INNER_MOBILIZATION)) {
            //职衔
            String positonJson = orgService.getAllPosition();
            List<Position> positionList = new ArrayList<>();
            if (positonJson != null) {
                JSONObject parseObject = JSONObject.parseObject(positonJson);
                if ((Integer) parseObject.get("code") == 200) {
                    positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                    if (positionList != null && positionList.size() > 0) {
                        for (Position position : positionList) {
                            if (position.getRowId().equals(Long.valueOf(adjustmentWork.getNewPosition()))) {
                                adjustmentWork.setNewPosition(position.getPositionName());
                                break;
                            }
                        }
                    }
                }
            }

            //职等/赋值名称
            String gradeJson = orgService.getAllGrade();
            List<Grade> gradeList = new ArrayList<>();
            if (gradeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(gradeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                    if (gradeList != null && gradeList.size() > 0) {
                        for (Grade grade : gradeList) {
                            if (grade.getRowId().equals(Long.valueOf(adjustmentWork.getNewGrade()))) {
                                adjustmentWork.setNewGrade(grade.getGradeName());
                                break;
                            }
                        }
                    }
                }
            }

            //职级
            String rankJson = orgService.getAllRank();
            List<Rank> rankList = new ArrayList<>();
            if (rankJson != null) {
                JSONObject parseObject = JSONObject.parseObject(rankJson);
                if ((Integer) parseObject.get("code") == 200) {
                    rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                    if (rankList != null && rankList.size() > 0) {
                        for (Rank rank : rankList) {
                            if (rank.getRowId().equals(Long.valueOf(adjustmentWork.getNewRank()))) {
                                adjustmentWork.setNewRank(rank.getRankName());
                                break;
                            }
                        }
                    }
                }
            }
            String allOrg = orgService.getAllOrg();
            if (allOrg != null) {
                JSONObject parseObject = JSONObject.parseObject(allOrg);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                    if (allOrgList != null && allOrgList.size() > 0) {
                        for (Org org : allOrgList) {
                            if (org.getRowId().equals(Long.valueOf(adjustmentWork.getNewBase()))) {
                                adjustmentWork.setNewBase(org.getBaseOrDeptName());
                                break;
                            }
                        }
                        for (Org org : allOrgList) {
                            if (org.getRowId().equals(Long.valueOf(adjustmentWork.getNewDept()))) {
                                adjustmentWork.setNewDept(org.getBaseOrDeptName());
                                break;
                            }
                        }
                    }
                }
            }
            //员工分类
            String staffClassJson = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
            List<Code> staffClassList = new ArrayList<>();
            if (staffClassJson != null) {
                JSONObject parseObject = JSONObject.parseObject(staffClassJson);
                if ((Integer) parseObject.get("code") == 200) {
                    staffClassList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (staffClassList != null && staffClassList.size() > 0) {
                        for (Code code : staffClassList) {
                            if (code.getCode().equals(adjustmentWork.getStaffClassify())) {
                                adjustmentWork.setStaffClassify(code.getName());
                                break;
                            }
                        }
                    }
                }
            }
            adjustmentWorkMapper.addStaffMove(adjustmentWork);
            baseInfoMapper.updateBaseInfo(baseInfo);
            systemService.updateUserName(staffNo, adjustmentWork.getNewStaffNo());
        }
        if (changeType.equals(Constant.RETIRE)) {
            String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
            if (isOnJob != null) {
                JSONObject parseObject = JSONObject.parseObject(isOnJob);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (isOnJobList != null && isOnJobList.size() > 0) {
                        for (Code code : isOnJobList) {
                            if (code.getName().equals("退休")) {
                                baseInfo.setJobStatus(code.getRowId());
                            }
                        }
                    }
                }
            }
            adjustmentWorkMapper.addStaffMove(adjustmentWork);
            baseInfoMapper.updateBaseInfo(baseInfo);
            //用户置为无效
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
            Contract contract = new Contract();
            contract.setStaffId(adjustmentWork.getStaffId());
            contract.setRelieveDate(adjustmentWork.getRetireDate());
            contract.setRelieveReason("员工退休");
            contract.setContractState("4");
            contractService.updateContractStateByStaffId(contract);
            Agreement agreement = new Agreement();
            agreement.setStaffId(adjustmentWork.getStaffId());
            agreement.setRelieveDate(adjustmentWork.getRetireDate());
            agreement.setRelieveReason("员工退休");
            agreement.setAgreementState("4");
            agreementService.updateAgreementStateByStaffId(agreement);
        }
        if (changeType.equals(Constant.DIMISSION)) {
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
            String isBlacklist = systemService.getByTpye(StaffConstant.YES_OR_NO);
            if (isBlacklist != null) {
                JSONObject parseObject = JSONObject.parseObject(isBlacklist);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> list = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (list != null && list.size() > 0) {
                        for (Code code : list) {
                            if (code.getName().equals(adjustmentWork.getIsBlacklist())) {
                                baseInfo.setIsBlacklist(code.getRowId());
                            }
                        }
                    }
                }
            }
            adjustmentWorkMapper.addStaffMove(adjustmentWork);
            baseInfo.setLeaveDate(adjustmentWork.getLeaveDate());
            baseInfoMapper.updateBaseInfo(baseInfo);
            //用户置为无效
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
            Contract contract = new Contract();
            contract.setStaffId(adjustmentWork.getStaffId());
            contract.setRelieveDate(adjustmentWork.getLeaveDate());
            contract.setRelieveReason(adjustmentWork.getLeaveReason());
            contract.setContractState("4");
            contractService.updateContractStateByStaffId(contract);
            Agreement agreement = new Agreement();
            agreement.setStaffId(adjustmentWork.getStaffId());
            agreement.setRelieveDate(adjustmentWork.getLeaveDate());
            agreement.setRelieveReason(adjustmentWork.getLeaveReason());
            agreement.setAgreementState("4");
            agreementService.updateAgreementStateByStaffId(agreement);
        }
        if (changeType.equals(Constant.REHIRE) || changeType.equals(Constant.RETURN_REHIRE)) {
            //职衔
            String positonJson = orgService.getAllPosition();
            List<Position> positionList = new ArrayList<>();
            if (positonJson != null) {
                JSONObject parseObject = JSONObject.parseObject(positonJson);
                if ((Integer) parseObject.get("code") == 200) {
                    positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                    if (positionList != null && positionList.size() > 0) {
                        for (Position position : positionList) {
                            if (position.getRowId().equals(Long.valueOf(adjustmentWork.getNewPosition()))) {
                                adjustmentWork.setNewPosition(position.getPositionName());
                                break;
                            }
                        }
                    }
                }
            }

            //职等/赋值名称
            String gradeJson = orgService.getAllGrade();
            List<Grade> gradeList = new ArrayList<>();
            if (gradeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(gradeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                    if (gradeList != null && gradeList.size() > 0) {
                        for (Grade grade : gradeList) {
                            if (grade.getRowId().equals(Long.valueOf(adjustmentWork.getNewGrade()))) {
                                adjustmentWork.setNewGrade(grade.getGradeName());
                                break;
                            }
                        }
                    }
                }
            }

            //职级
            String rankJson = orgService.getAllRank();
            List<Rank> rankList = new ArrayList<>();
            if (rankJson != null) {
                JSONObject parseObject = JSONObject.parseObject(rankJson);
                if ((Integer) parseObject.get("code") == 200) {
                    rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                    if (rankList != null && rankList.size() > 0) {
                        for (Rank rank : rankList) {
                            if (rank.getRowId().equals(Long.valueOf(adjustmentWork.getNewRank()))) {
                                adjustmentWork.setNewRank(rank.getRankName());
                                break;
                            }
                        }
                    }
                }
            }
            String allOrg = orgService.getAllOrg();
            if (allOrg != null) {
                JSONObject parseObject = JSONObject.parseObject(allOrg);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                    if (allOrgList != null && allOrgList.size() > 0) {
                        for (Org org : allOrgList) {
                            if (org.getRowId().equals(Long.valueOf(adjustmentWork.getNewBase()))) {
                                adjustmentWork.setNewBase(org.getBaseOrDeptName());
                                break;
                            }
                        }
                        for (Org org : allOrgList) {
                            if (org.getRowId().equals(Long.valueOf(adjustmentWork.getNewDept()))) {
                                adjustmentWork.setNewDept(org.getBaseOrDeptName());
                                break;
                            }
                        }
                    }
                }
            }
            //员工分类
            String staffClassJson = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
            List<Code> staffClassList = new ArrayList<>();
            if (staffClassJson != null) {
                JSONObject parseObject = JSONObject.parseObject(staffClassJson);
                if ((Integer) parseObject.get("code") == 200) {
                    staffClassList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (staffClassList != null && staffClassList.size() > 0) {
                        for (Code code : staffClassList) {
                            if (code.getCode().equals(adjustmentWork.getStaffClassify())) {
                                adjustmentWork.setStaffClassify(code.getName());
                                break;
                            }
                        }
                    }
                }
            }
            String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
            if (isOnJob != null) {
                JSONObject parseObject = JSONObject.parseObject(isOnJob);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    if (isOnJobList != null && isOnJobList.size() > 0) {
                        for (Code code : isOnJobList) {
                            if (code.getName().equals(adjustmentWork.getJobStatus())) {
                                baseInfo.setJobStatus(code.getRowId());
                            }
                        }
                    }
                }
            }
            adjustmentWorkMapper.addStaffMove(adjustmentWork);
            baseInfoMapper.updateBaseInfo(baseInfo);
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_VALID);
            systemService.updateUserName(staffNo, adjustmentWork.getNewStaffNo());
        }
        return true;
    }

    public PageListVO<AdjustmentWork> pageList(SearchAdjustmentWork searchAdjustmentWork) {

        if (searchAdjustmentWork.getStaffNo() != null && searchAdjustmentWork.getStaffNo() != "") {
            searchAdjustmentWork.setStaffNo(searchAdjustmentWork.getStaffNo().trim());
        }
        if (searchAdjustmentWork.getStaffName() != null && searchAdjustmentWork.getStaffName() != "") {
            searchAdjustmentWork.setStaffName(searchAdjustmentWork.getStaffName().trim());
        }
        if (searchAdjustmentWork.getIdentityNo() != null && searchAdjustmentWork.getIdentityNo() != "") {
            searchAdjustmentWork.setIdentityNo(searchAdjustmentWork.getIdentityNo().trim());
        }

        Long deptId = searchAdjustmentWork.getDeptId();
        if (deptId != null) {
            String allOrg = orgService.getAllSonOrg(deptId);
            List<Org> orgList = new ArrayList<>();
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                orgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                Org dept = new Org();
                dept.setRowId(deptId);
                orgList.add(dept);
                searchAdjustmentWork.setDeptIds(orgList);
            }
        }
        Paginater paginater = Paginater.newInstance(searchAdjustmentWork);
        PageListVO<AdjustmentWork> pageListVO = PageListVO.newInstance(adjustmentWorkMapper.pageList(paginater));
        return pageListVO;
    }

    public boolean updateStaffMove(AdjustmentWork adjustmentWork) {
        if (adjustmentWork.getRealEndTime().equals(new Date()) || adjustmentWork.getRealEndTime().before(new Date())) {
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setStaffNo(adjustmentWork.getOriginalStaffNo());
            baseInfo.setRowId(adjustmentWork.getStaffId());
            baseInfoMapper.updateBaseInfo(baseInfo);
            systemService.updateUserName(adjustmentWork.getNewStaffNo(), adjustmentWork.getOriginalStaffNo());
            adjustmentWorkMapper.updateStatus(adjustmentWork.getRowId());
        }

        int update = adjustmentWorkMapper.updateAdjustmentWork(adjustmentWork);
        if (update > 0) {
            return true;
        }
        return false;
    }

    public int delAllStaffId(Long staffId) {
        return adjustmentWorkMapper.delAllStaffId(staffId);
    }

    /**
     * 
     * 批量离职
     * 
     * @author jinlin.tang
     * @date 2018年10月30日 下午4:55:11
     * @param list
     * @return
     */
    public List<ImportAdjustmentWork> importExcel(List<ImportAdjustmentWork> list) {

        List<ImportAdjustmentWork> errorImportAdjustmentWorks = new ArrayList<ImportAdjustmentWork>();

        for (ImportAdjustmentWork adjustmentWork : list) {
            StringBuffer errMsg = new StringBuffer();
            String leaveReason = adjustmentWork.getLeaveReason();
            if (leaveReason != null && leaveReason.trim().length() > 30) {
                errMsg.append("离职原因长度不能超过200位；");
            }
            if (!StringUtils.isEmpty(errMsg.toString())) {
                adjustmentWork.setErrMsg(errMsg.toString());
                errorImportAdjustmentWorks.add(adjustmentWork);
            } else {
                adjustmentWork.setCreatedTime(new Date());
                adjustmentWork.setCreatedBy(RequestContext.get().getStaffId());
                BaseInfo baseInfo = baseInfoService.getBaseInfoByStaffNo(adjustmentWork.getStaffNo());
                adjustmentWork.setStaffId(baseInfo.getRowId());
            }
        }
        if (list.size() > 0) {
            //批量插入异动数据
            adjustmentWorkMapper.insertStaffMove(list);
            BaseInfo baseInfo = new BaseInfo();
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

            for (ImportAdjustmentWork adjustmentWork : list) {
                baseInfo.setRowId(adjustmentWork.getStaffId());
                baseInfo.setLeaveDate(adjustmentWork.getLeaveDate());
                baseInfoMapper.updateBaseInfo(baseInfo);
                //用户置为无效
                systemService.changeByUserName(adjustmentWork.getStaffNo(), Constant.DEL_FLAG_INVALID);
                Contract contract = new Contract();
                contract.setStaffId(adjustmentWork.getStaffId());
                contract.setRelieveDate(adjustmentWork.getLeaveDate());
                contract.setRelieveReason(adjustmentWork.getLeaveReason());
                contract.setContractState("4");
                contractService.updateContractStateByStaffId(contract);
                Agreement agreement = new Agreement();
                agreement.setStaffId(adjustmentWork.getStaffId());
                agreement.setRelieveDate(adjustmentWork.getLeaveDate());
                agreement.setRelieveReason(adjustmentWork.getLeaveReason());
                agreement.setAgreementState("4");
                agreementService.updateAgreementStateByStaffId(agreement);
            }
        }
        return errorImportAdjustmentWorks;
    }

    public void exportErrExcel(HttpServletResponse response, List<ImportAdjustmentWork> list, String lang) {

        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();

        fieldList.add("staffNo");
        fieldList.add("leaveDate");
        fieldList.add("leaveReason");
        fieldList.add("errMsg");

        System.out.println("lang:" + lang);

        String title = "";
        int size = list.size();
        String[] headers = new String[size];

        if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
            headerList.add("工号");
            headerList.add("离职日期");
            headerList.add("离职原因");
            headerList.add("错误描述");

            title = "批量离职错误信息";

            headers = (String[]) headerList.toArray(new String[0]);

        } else if (lang.equalsIgnoreCase(Constant.EN)) {

            headerList.add("Staff No");
            headerList.add("Leave Date");
            headerList.add("Leave Reason");
            headerList.add("Error Description");
            //TODO
            title = "Batch exit error message";
            headers = (String[]) headerList.toArray(new String[0]);
        }

        // pojo字段
        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }

}
