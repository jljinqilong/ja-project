package com.champlink.staff.service.baseInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.domain.BaseSelect;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.staff.baseInfo.WorkerCodeRule;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.ModuleLog;
import com.champlink.common.form.org.org.QueryCountBaseInfoByDeptId;
import com.champlink.common.form.staff.baseInfo.ImportBaseInfoForm;
import com.champlink.common.form.staff.baseInfo.ImportBaseInfoPositionForm;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.dao.baseInfo.BaseInfoMapper;
import com.champlink.staff.dao.baseInfo.WorkerCodeRuleDao;
import com.champlink.staff.service.call.OrgService;
import com.champlink.staff.service.call.SystemService;

/**
 * 
 * 员工基础信息
 * 
 * @author natyu
 * @date 2018年7月5日 上午10:23:01
 *
 */
@Service
public class BaseInfoService {

    @Autowired
    protected MessageSource messageSource; // 国际化
    @Autowired
    private BaseInfoMapper baseInfoMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactEmergencyService contactEmergencyService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private OuterExperienceService outerExperienceService;
    @Autowired
    private RelationshipInnerService relationshipInnerService;
    @Autowired
    private RelationshipSocialService relationshipSocialService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WorkerCodeRuleService workerCodeRuleService;

    @Autowired
    private WorkerCodeRuleDao workerCodeRuleDao;

    @Autowired
    private OrgService orgService;

    @Autowired
    private AdjustmentWorkService adjustmengWorkService;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfBaseInfo
     */
    @Transactional
    public void addBaseInfo(BaseInfo baseInfo) {

        baseInfo.setCreatedTime(new Date());
        baseInfo.setCreatedBy(RequestContext.get().getStaffId());

        String staffNo = baseInfo.getStaffNo();
        String identityNo = baseInfo.getIdentityNo();
        String mobile = baseInfo.getMobile();
        String email = baseInfo.getEmail();

        // 用户手动输入的员工工号已经存在
        if (checkStaffNo(staffNo)) {
            AppException.create(200000);
        }

        if (checkIdentityNoByJob(identityNo, null) > 0) {
            AppException.create(200001);
        }

        if (checkMobileByJob(mobile, null) > 0) {
            AppException.create(200009);
        }

        //邮箱不能重复
        //        if (email != "" && email != null) {
        //            if (checkEmailByJob(email, null) > 0) {
        //                AppException.create(200010);
        //            }
        //        }

        Integer currentId = baseInfo.getCurrentId();
        if (currentId != null) { // 如果是自动生成的编号就更新WorkerCodeRule表的currentId  如果是手输的不需要更新
            workerCodeRuleService.updateByDeptId(baseInfo.getBaseId(), currentId);
        }

        // 默认置为有效
        baseInfo.setDelFlag(Constant.DEL_FLAG_VALID);
        baseInfoMapper.insertBaseInfo(baseInfo);
        List<String> userList = new ArrayList<>();
        userList.add(staffNo);
        systemService.batchAdd(userList);
    }

    /**
     * 员工前台添加时自动生成员工编号
     * @param baseId
     * @param currentId
     * @param map
     */
    public void generateWorkerCode(Long baseId, int currentId, Map<String, String> map) {
        String WorkerCode = workerCodeRuleService.generateWorkerCode(baseId, currentId);

        if (checkStaffNo(WorkerCode)) {
            generateWorkerCode(baseId, currentId + 1, map);
        } else {
            map.put("staffNo", WorkerCode);
            map.put("currentId", String.valueOf(++currentId));
        }
    }

    /**
     * 根据staffId查询明细
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:14
     * @param staffId
     */
    public BaseInfo getBaseInfoByStaffId(Long staffId) {
        return baseInfoMapper.getBaseInfoByStaffId(staffId, null);
    }

    /**
     * 逻辑删除
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:14
     * @param staffId
     */
    @Transactional
    public void logicDelBaseInfoByStaffId(Long staffId) {
        BaseInfo baseInfo = baseInfoMapper.getBaseInfoByStaffId(staffId, Constant.DEL_FLAG_VALID);
        baseInfoMapper.logicDelBaseInfoByStaffId(staffId, Constant.DEL_FLAG_INVALID);

        addressService.delAddressListByStaffId(staffId);
        contactEmergencyService.delContactEmergencyListByStaffId(staffId);
        educationService.delEducationListByStaffId(staffId);
        outerExperienceService.delOuterExperienceListByStaffId(staffId);
        relationshipInnerService.delRelationshipInnerListByStaffId(staffId);
        relationshipSocialService.delRelationshipSocialListByStaffId(staffId);

        systemService.delByUserName(baseInfo.getStaffNo());
    }

    /**
     * 修改
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:36
     * @param stfBaseInfo
     */
    public void updateStfBaseInfo(BaseInfo baseInfo) {

        baseInfo.setLastUpdateTime(new Date());
        baseInfo.setLastUpdateBy(RequestContext.get().getStaffId());

        String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        Long onJob = null;
        Long leaveJob = null;
        Long retire = null;
        Long awaitJob = null;
        if (isOnJob != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJob);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                if (isOnJobList != null && isOnJobList.size() > 0) {
                    for (Code code : isOnJobList) {
                        if (code.getName().equals("在职")) {
                            onJob = code.getRowId();
                        } else if (code.getName().equals("离职")) {
                            leaveJob = code.getRowId();
                        } else if (code.getName().equals("退休")) {
                            retire = code.getRowId();
                        } else if (code.getName().equals("待岗")) {
                            awaitJob = code.getRowId();
                        }
                    }
                }
            }
        }
        BaseInfo oldBaseInfo = baseInfoMapper.getBaseInfoByStaffId(baseInfo.getRowId(), Constant.DEL_FLAG_VALID);
        Long oldCheckJobStatus = oldBaseInfo.getJobStatus();
        String staffNo = baseInfo.getStaffNo();
        if (oldCheckJobStatus.equals(onJob) && baseInfo.getJobStatus().equals(leaveJob)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
        } else if (oldCheckJobStatus.equals(onJob) && baseInfo.getJobStatus().equals(retire)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
        } else if (oldCheckJobStatus.equals(leaveJob) && baseInfo.getJobStatus().equals(onJob)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_VALID);
        } else if (oldCheckJobStatus.equals(leaveJob) && baseInfo.getJobStatus().equals(awaitJob)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_VALID);
        } else if (oldCheckJobStatus.equals(retire) && baseInfo.getJobStatus().equals(onJob)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_VALID);
        } else if (oldCheckJobStatus.equals(retire) && baseInfo.getJobStatus().equals(awaitJob)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_VALID);
        } else if (oldCheckJobStatus.equals(awaitJob) && baseInfo.getJobStatus().equals(leaveJob)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
        } else if (oldCheckJobStatus.equals(awaitJob) && baseInfo.getJobStatus().equals(retire)) {
            systemService.changeByUserName(staffNo, Constant.DEL_FLAG_INVALID);
        }

        if (checkIdentityNoByJob(baseInfo.getIdentityNo(), baseInfo.getRowId()) > 0) {
            AppException.create(200001);
        }

        //生成变更记录
        StringBuffer updateMsg = new StringBuffer();
        //old
        Long oldSex = 0L;
        Long oldBaseId = 0L;
        Long oldDeptId = 0L;
        Long oldPositionId = 0L;
        Long oldRankId = 0L;
        Long oldGradeId = 0L;
        Long oldIdentityTypeId = 0L;
        Long oldMaritalStatus = 0L;
        Long oldPoliticalStatus = 0L;
        Long oldStaffType = 0L;
        Long oldSocialSecurityType = 0L;
        Long oldCostCenter = 0L;
        Long oldWorkType = 0L;
        Long oldJobStatus = 0L;
        Long oldIsBlacklist = 0L;
        Long oldNationality = 0L;
        Long oldNation = 0L;
        Long oldFertilityStatus = 0L;
        Long oldLines = 0L;
        Long oldOperatingPost = 0L;

        Long oldFactoryCategory = 0L;
        Long oldRecruitmentChannel = 0L;
        if (oldBaseInfo.getSex() != null) {
            oldSex = oldBaseInfo.getSex();
        }
        if (oldBaseInfo.getBaseId() != null) {
            oldBaseId = oldBaseInfo.getBaseId();
        }
        if (oldBaseInfo.getDeptId() != null) {
            oldDeptId = oldBaseInfo.getDeptId();
        }
        if (oldBaseInfo.getPositionId() != null) {
            oldPositionId = oldBaseInfo.getPositionId();
        }
        if (oldBaseInfo.getRankId() != null) {
            oldRankId = oldBaseInfo.getRankId();
        }
        if (oldBaseInfo.getGradeId() != null) {
            oldGradeId = oldBaseInfo.getGradeId();
        }
        if (oldBaseInfo.getIdentityTypeId() != null) {
            oldIdentityTypeId = oldBaseInfo.getIdentityTypeId();
        }
        if (oldBaseInfo.getMaritalStatus() != null) {
            oldMaritalStatus = oldBaseInfo.getMaritalStatus();
        }
        if (oldBaseInfo.getPoliticalStatus() != null) {
            oldPoliticalStatus = oldBaseInfo.getPoliticalStatus();
        }
        if (oldBaseInfo.getStaffType() != null) {
            oldStaffType = oldBaseInfo.getStaffType();
        }
        if (oldBaseInfo.getSocialSecurityType() != null) {
            oldSocialSecurityType = oldBaseInfo.getSocialSecurityType();
        }
        if (oldBaseInfo.getCostCenter() != null) {
            oldCostCenter = oldBaseInfo.getCostCenter();
        }
        if (oldBaseInfo.getWorkType() != null) {
            oldWorkType = oldBaseInfo.getWorkType();
        }
        if (oldBaseInfo.getJobStatus() != null) {
            oldJobStatus = oldBaseInfo.getJobStatus();
        }
        if (oldBaseInfo.getIsBlacklist() != null) {
            oldIsBlacklist = oldBaseInfo.getIsBlacklist();
        }
        if (oldBaseInfo.getNationality() != null) {
            oldNationality = oldBaseInfo.getNationality();
        }
        if (oldBaseInfo.getNation() != null) {
            oldNation = oldBaseInfo.getNation();
        }
        if (oldBaseInfo.getFertilityStatus() != null) {
            oldFertilityStatus = oldBaseInfo.getFertilityStatus();
        }
        if (oldBaseInfo.getLines() != null) {
            oldLines = oldBaseInfo.getLines();
        }
        if (oldBaseInfo.getOperatingPost() != null) {
            oldOperatingPost = oldBaseInfo.getOperatingPost();
        }
        String oldStaffClassify = "0";
        if (oldBaseInfo.getStaffClassify() != null) {
            oldStaffClassify = oldBaseInfo.getStaffClassify();
        }
        String oldMobile = oldBaseInfo.getMobile();
        String oldEmail = oldBaseInfo.getEmail();
        String oldStaffName = oldBaseInfo.getStaffName();
        String oldIdentityNo = oldBaseInfo.getIdentityNo();
        String oldRegisteredResidence = oldBaseInfo.getRegisteredResidence();
        String oldNativePlace = oldBaseInfo.getNativePlace();
        String oldOfficePlace = oldBaseInfo.getOfficePlace();
        String oldRemark = oldBaseInfo.getRemark();
        Date oldIdentityValidDate = oldBaseInfo.getIdentityValidDate();
        Date oldBirthdate = oldBaseInfo.getBirthdate();
        Date oldFirstWorkingTime = oldBaseInfo.getFirstWorkingTime();
        Date oldEntryDate = oldBaseInfo.getEntryDate();
        Date oldLeaveDate = oldBaseInfo.getLeaveDate();

        //新增字段
        if (oldBaseInfo.getFactoryCategory() != null) {
            oldFactoryCategory = oldBaseInfo.getFactoryCategory();
        }
        if (oldBaseInfo.getRecruitmentChannel() != null) {
            oldRecruitmentChannel = oldBaseInfo.getRecruitmentChannel();
        }
        String oldClasses = oldBaseInfo.getClasses();
        String oldWorkCard = oldBaseInfo.getWorkCard();
        String oldDormitoryNo = oldBaseInfo.getDormitoryNo();
        String oldLockerShoebox = oldBaseInfo.getLockerShoebox();
        String oldSpeciality = oldBaseInfo.getSpeciality();
        String oldJobTitle = oldBaseInfo.getJobTitle();
        String oldLunarSolarCalendar = oldBaseInfo.getLunarSolarCalendar();

        //new
        Long sex = 0L;
        Long baseId = 0L;
        Long deptId = 0L;
        Long positionId = 0L;
        Long rankId = 0L;
        Long gradeId = 0L;
        Long identityTypeId = 0L;
        Long maritalStatus = 0L;
        Long politicalStatus = 0L;
        Long staffType = 0L;
        Long socialSecurityType = 0L;
        Long costCenter = 0L;
        Long workType = 0L;
        Long jobStatus = 0L;
        Long isBlacklist = 0L;
        Long nationality = 0L;
        Long nation = 0L;
        Long fertilityStatus = 0L;
        Long lines = 0L;
        Long operatingPost = 0L;

        Long factoryCategory = 0L;
        Long recruitmentChannel = 0L;
        if (baseInfo.getSex() != null) {
            sex = baseInfo.getSex();
        }
        if (baseInfo.getBaseId() != null) {
            baseId = baseInfo.getBaseId();
        }
        if (baseInfo.getDeptId() != null) {
            deptId = baseInfo.getDeptId();
        }
        if (baseInfo.getPositionId() != null) {
            positionId = baseInfo.getPositionId();
        }
        if (baseInfo.getRankId() != null) {
            rankId = baseInfo.getRankId();
        }
        if (baseInfo.getGradeId() != null) {
            gradeId = baseInfo.getGradeId();
        }
        if (baseInfo.getIdentityTypeId() != null) {
            identityTypeId = baseInfo.getIdentityTypeId();
        }
        if (baseInfo.getMaritalStatus() != null) {
            maritalStatus = baseInfo.getMaritalStatus();
        }
        if (baseInfo.getPoliticalStatus() != null) {
            politicalStatus = baseInfo.getPoliticalStatus();
        }
        if (baseInfo.getStaffType() != null) {
            staffType = baseInfo.getStaffType();
        }
        if (baseInfo.getSocialSecurityType() != null) {
            socialSecurityType = baseInfo.getSocialSecurityType();
        }
        if (baseInfo.getCostCenter() != null) {
            costCenter = baseInfo.getCostCenter();
        }
        if (baseInfo.getWorkType() != null) {
            workType = baseInfo.getWorkType();
        }
        if (baseInfo.getJobStatus() != null) {
            jobStatus = baseInfo.getJobStatus();
        }
        if (baseInfo.getIsBlacklist() != null) {
            isBlacklist = baseInfo.getIsBlacklist();
        }
        if (baseInfo.getNationality() != null) {
            nationality = baseInfo.getNationality();
        }
        if (baseInfo.getNation() != null) {
            nation = baseInfo.getNation();
        }
        if (baseInfo.getFertilityStatus() != null) {
            fertilityStatus = baseInfo.getFertilityStatus();
        }
        if (baseInfo.getLines() != null) {
            lines = baseInfo.getLines();
        }
        if (baseInfo.getOperatingPost() != null) {
            operatingPost = baseInfo.getOperatingPost();
        }
        String staffClassify = "0";
        if (baseInfo.getStaffClassify() != null) {
            staffClassify = baseInfo.getStaffClassify();
        }
        String mobile = baseInfo.getMobile();
        String email = baseInfo.getEmail();
        String staffName = baseInfo.getStaffName();
        String identityNo = baseInfo.getIdentityNo();
        String registeredResidence = baseInfo.getRegisteredResidence();
        String nativePlace = baseInfo.getNativePlace();
        String officePlace = baseInfo.getOfficePlace();
        String remark = baseInfo.getRemark();
        Date identityValidDate = baseInfo.getIdentityValidDate();
        Date birthdate = baseInfo.getBirthdate();
        Date firstWorkingTime = baseInfo.getFirstWorkingTime();
        Date entryDate = baseInfo.getEntryDate();
        Date leaveDate = baseInfo.getLeaveDate();

        //新增字段
        if (baseInfo.getFactoryCategory() != null) {
            factoryCategory = baseInfo.getFactoryCategory();
        }
        if (baseInfo.getRecruitmentChannel() != null) {
            recruitmentChannel = baseInfo.getRecruitmentChannel();
        }
        String classes = baseInfo.getClasses();
        String workCard = baseInfo.getWorkCard();
        String dormitoryNo = baseInfo.getDormitoryNo();
        String lockerShoebox = baseInfo.getLockerShoebox();
        String speciality = baseInfo.getSpeciality();
        String jobTitle = baseInfo.getJobTitle();
        String lunarSolarCalendar = baseInfo.getLunarSolarCalendar();

        //男女

        if (oldSex.longValue() != sex.longValue()) {
            String maleOrFemaleJson = systemService.getByTpye(StaffConstant.MALE_OR_FEMALE);
            List<Code> maleOrFemaleList = new ArrayList<>();
            if (maleOrFemaleJson != null) {
                JSONObject parseObject = JSONObject.parseObject(maleOrFemaleJson);
                if ((Integer) parseObject.get("code") == 200) {
                    maleOrFemaleList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldSexStr = "";
            String sexStr = "";
            for (Code maleOrFemale : maleOrFemaleList) {
                if (maleOrFemale.getRowId().equals(oldSex)) {
                    oldSexStr = maleOrFemale.getName();
                } else if (maleOrFemale.getRowId().equals(sex)) {
                    sexStr = maleOrFemale.getName();
                }
            }
            updateMsg.append("性别：由“" + oldSexStr + "”变为“" + sexStr + "”;");
        }

        //基地
        if (oldBaseId.longValue() != baseId.longValue()) {
            String baseJson = orgService.baseList();
            List<Org> baseList = new ArrayList<>();
            if (baseJson != null) {
                JSONObject parseObject = JSONObject.parseObject(baseJson);
                if ((Integer) parseObject.get("code") == 200) {
                    baseList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                }
            }
            String oldBaseIdStr = "";
            String baseIdStr = "";
            for (Org base : baseList) {
                if (base.getRowId().equals(oldBaseId)) {
                    oldBaseIdStr = base.getBaseOrDeptName();
                } else if (base.getRowId().equals(baseId)) {
                    baseIdStr = base.getBaseOrDeptName();
                }
            }
            updateMsg.append("基地：由“" + oldBaseIdStr + "”变为“" + baseIdStr + "”;");
        }

        //部门
        if (oldDeptId.longValue() != deptId.longValue()) {
            String deptJson = orgService.deptList();
            List<Org> deptList = new ArrayList<>();
            if (deptJson != null) {
                JSONObject parseObject = JSONObject.parseObject(deptJson);
                if ((Integer) parseObject.get("code") == 200) {
                    deptList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                }
            }
            String oldDeptIdStr = "";
            String deptIdStr = "";
            for (Org dept : deptList) {
                if (dept.getRowId().equals(oldDeptId)) {
                    oldDeptIdStr = dept.getBaseOrDeptName();
                } else if (dept.getRowId().equals(deptId)) {
                    deptIdStr = dept.getBaseOrDeptName();
                }
            }
            updateMsg.append("部门：由“" + oldDeptIdStr + "”变为“" + deptIdStr + "”;");
        }

        //职衔
        if (oldPositionId.longValue() != positionId.longValue()) {
            String positonJson = orgService.getAllPosition();
            List<Position> positionList = new ArrayList<>();
            if (positonJson != null) {
                JSONObject parseObject = JSONObject.parseObject(positonJson);
                if ((Integer) parseObject.get("code") == 200) {
                    positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                }
            }
            String oldPositionIdStr = "";
            String positionIdStr = "";
            for (Position position : positionList) {
                if (position.getRowId().equals(oldPositionId)) {
                    oldPositionIdStr = position.getPositionName();
                } else if (position.getRowId().equals(positionId)) {
                    positionIdStr = position.getPositionName();
                }
            }
            updateMsg.append("职衔：由“" + oldPositionIdStr + "”变为“" + positionIdStr + "”;");
        }

        //职等/赋值名称
        if (oldGradeId.longValue() != gradeId.longValue()) {
            String gradeJson = orgService.getAllGrade();
            List<Grade> gradeList = new ArrayList<>();
            if (gradeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(gradeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                }
            }
            String oldGradeIdStr = "";
            String gradeIdStr = "";
            for (Grade grade : gradeList) {
                if (grade.getRowId().equals(oldGradeId)) {
                    oldGradeIdStr = grade.getGradeName();
                } else if (grade.getRowId().equals(gradeId)) {
                    gradeIdStr = grade.getGradeName();
                }
            }
            updateMsg.append("职等/赋值名称：由“" + oldGradeIdStr + "”变为“" + gradeIdStr + "”;");
        }

        //职级
        if (oldRankId.longValue() != rankId.longValue()) {
            String rankJson = orgService.getAllRank();
            List<Rank> rankList = new ArrayList<>();
            if (rankJson != null) {
                JSONObject parseObject = JSONObject.parseObject(rankJson);
                if ((Integer) parseObject.get("code") == 200) {
                    rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                }
            }
            String oldRankIdStr = "";
            String rankIdStr = "";
            for (Rank rank : rankList) {
                if (rank.getRowId().equals(oldRankId)) {
                    oldRankIdStr = rank.getRankName();
                } else if (rank.getRowId().equals(rankId)) {
                    rankIdStr = rank.getRankName();
                }
            }
            updateMsg.append("职级：由“" + oldRankIdStr + "”变为“" + rankIdStr + "”;");
        }

        //证件类型
        if (oldIdentityTypeId.longValue() != identityTypeId.longValue()) {
            String identityTypeJson = systemService.getByTpye(StaffConstant.IDENTITY_TYPE);
            List<Code> identityTypeList = new ArrayList<>();
            if (identityTypeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(identityTypeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    identityTypeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldIdentityTypeIdStr = "";
            String identityTypeIdStr = "";
            for (Code identityType : identityTypeList) {
                if (identityType.getRowId().equals(oldIdentityTypeId)) {
                    oldIdentityTypeIdStr = identityType.getName();
                } else if (identityType.getRowId().equals(identityTypeId)) {
                    identityTypeIdStr = identityType.getName();
                }
            }
            updateMsg.append("证件类型：由“" + oldIdentityTypeIdStr + "”变为“" + identityTypeIdStr + "”;");
        }

        if (oldFertilityStatus.longValue() != fertilityStatus.longValue()) {
            //生育状况
            String fertilityStatusJson = systemService.getByTpye(StaffConstant.FERTILITY_STATUS);
            List<Code> fertilityStatusList = new ArrayList<>();
            if (fertilityStatusJson != null) {
                JSONObject parseObject = JSONObject.parseObject(fertilityStatusJson);
                if ((Integer) parseObject.get("code") == 200) {
                    fertilityStatusList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldFertilityStatusStr = "";
            String fertilityStatusStr = "";
            for (Code code : fertilityStatusList) {
                if (code.getRowId().equals(oldFertilityStatus)) {
                    oldFertilityStatusStr = code.getName();
                } else if (code.getRowId().equals(fertilityStatus)) {
                    fertilityStatusStr = code.getName();
                }
            }
            updateMsg.append("生育状况：由“" + oldFertilityStatusStr + "”变为“" + fertilityStatusStr + "”;");
        }

        if (oldMaritalStatus.longValue() != maritalStatus.longValue()) {
            //婚姻状况
            String maritalStatusJson = systemService.getByTpye(StaffConstant.MARITAL_STATUS);
            List<Code> maritalStatusList = new ArrayList<>();
            if (maritalStatusJson != null) {
                JSONObject parseObject = JSONObject.parseObject(maritalStatusJson);
                if ((Integer) parseObject.get("code") == 200) {
                    maritalStatusList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldMaritalStatusStr = "";
            String maritalStatusStr = "";
            for (Code code : maritalStatusList) {
                if (code.getRowId().equals(oldMaritalStatus)) {
                    oldMaritalStatusStr = code.getName();
                } else if (code.getRowId().equals(maritalStatus)) {
                    maritalStatusStr = code.getName();
                }
            }
            updateMsg.append("婚姻状况：由“" + oldMaritalStatusStr + "”变为“" + maritalStatusStr + "”;");
        }

        if (oldPoliticalStatus.longValue() != politicalStatus.longValue()) {
            //政治面貌
            String politicalStatusJson = systemService.getByTpye(StaffConstant.POLITICAL_STATUS);
            List<Code> politicalStatusList = new ArrayList<>();
            if (politicalStatusJson != null) {
                JSONObject parseObject = JSONObject.parseObject(politicalStatusJson);
                if ((Integer) parseObject.get("code") == 200) {
                    politicalStatusList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldPoliticalStatusStr = "";
            String politicalStatusStr = "";
            for (Code code : politicalStatusList) {
                if (code.getRowId().equals(oldPoliticalStatus)) {
                    oldPoliticalStatusStr = code.getName();
                } else if (code.getRowId().equals(politicalStatus)) {
                    politicalStatusStr = code.getName();
                }
            }
            updateMsg.append("政治面貌：由“" + oldPoliticalStatusStr + "”变为“" + politicalStatusStr + "”;");
        }

        if (oldStaffType.longValue() != staffType.longValue()) {
            //员工类型
            String staffTypeJson = systemService.getByTpye(StaffConstant.STAFF_TYPE);
            List<Code> staffTypeList = new ArrayList<>();
            if (staffTypeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(staffTypeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    staffTypeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldStaffTypeStr = "";
            String staffTypeStr = "";
            for (Code code : staffTypeList) {
                if (code.getRowId().equals(oldStaffType)) {
                    oldStaffTypeStr = code.getName();
                } else if (code.getRowId().equals(staffType)) {
                    staffTypeStr = code.getName();
                }
            }
            updateMsg.append("员工类型：由“" + oldStaffTypeStr + "”变为“" + staffTypeStr + "”;");
        }

        if (oldSocialSecurityType.longValue() != socialSecurityType.longValue()) {
            //社保类型
            String socialSecurityTypeJson = systemService.getByTpye(StaffConstant.SOCIAL_SECURITY_TYPE);
            List<Code> socialSecurityTypeList = new ArrayList<>();
            if (socialSecurityTypeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(socialSecurityTypeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    socialSecurityTypeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldSocialSecurityTypeStr = "";
            String socialSecurityTypeStr = "";
            for (Code code : socialSecurityTypeList) {
                if (code.getRowId().equals(oldSocialSecurityType)) {
                    oldSocialSecurityTypeStr = code.getName();
                } else if (code.getRowId().equals(socialSecurityType)) {
                    socialSecurityTypeStr = code.getName();
                }
            }
            updateMsg.append("社保类型：由“" + oldSocialSecurityTypeStr + "”变为“" + socialSecurityTypeStr + "”;");
        }

        if (oldWorkType.longValue() != workType.longValue()) {
            //用工类型
            String typeOfLaborJson = systemService.getByTpye(StaffConstant.TYPE_OF_LABOR);
            List<Code> typeOfLaborList = new ArrayList<>();
            if (typeOfLaborJson != null) {
                JSONObject parseObject = JSONObject.parseObject(typeOfLaborJson);
                if ((Integer) parseObject.get("code") == 200) {
                    typeOfLaborList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldWorkTypeStr = "";
            String workTypeStr = "";
            for (Code code : typeOfLaborList) {
                if (code.getRowId().equals(oldWorkType)) {
                    oldWorkTypeStr = code.getName();
                } else if (code.getRowId().equals(workType)) {
                    workTypeStr = code.getName();
                }
            }
            updateMsg.append("用工类型：由“" + oldWorkTypeStr + "”变为“" + workTypeStr + "”;");
        }

        if (oldCostCenter.longValue() != costCenter.longValue()) {
            //成本中心
            String baseJson = orgService.baseList();
            List<Org> baseList = new ArrayList<>();
            if (baseJson != null) {
                JSONObject parseObject = JSONObject.parseObject(baseJson);
                if ((Integer) parseObject.get("code") == 200) {
                    baseList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                }
            }
            String oldCostCenterStr = "";
            String costCenterStr = "";
            for (Org base : baseList) {
                if (base.getRowId().equals(oldCostCenter)) {
                    oldCostCenterStr = base.getBaseOrDeptName();
                } else if (base.getRowId().equals(costCenter)) {
                    costCenterStr = base.getBaseOrDeptName();
                }
            }
            updateMsg.append("成本中心：由“" + oldCostCenterStr + "”变为“" + costCenterStr + "”;");
        }

        if (oldLines.longValue() != lines.longValue()) {
            //线别
            String lineJson = systemService.getByTpye(StaffConstant.LINES);
            List<Code> linesList = new ArrayList<>();
            if (lineJson != null) {
                JSONObject parseObject = JSONObject.parseObject(lineJson);
                if ((Integer) parseObject.get("code") == 200) {
                    linesList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldLinesStr = "";
            String linesStr = "";
            for (Code code : linesList) {
                if (code.getRowId().equals(oldLines)) {
                    oldLinesStr = code.getName();
                } else if (code.getRowId().equals(lines)) {
                    linesStr = code.getName();
                }
            }
            updateMsg.append("线别：由“" + oldLinesStr + "”变为“" + linesStr + "”;");
        }

        if (oldStaffClassify != staffClassify) {
            //员工分类
            String staffClassifyJson = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
            List<Code> staffClassifyList = new ArrayList<>();
            if (staffClassifyJson != null) {
                JSONObject parseObject = JSONObject.parseObject(staffClassifyJson);
                if ((Integer) parseObject.get("code") == 200) {
                    staffClassifyList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldStaffClassifyStr = "";
            String staffClassifyStr = "";
            for (Code code : staffClassifyList) {
                if (code.getCode().equals(oldStaffClassify)) {
                    oldStaffClassifyStr = code.getName();
                } else if (code.getCode().equals(staffClassify)) {
                    staffClassifyStr = code.getName();
                }
            }
            updateMsg.append("员工分类：由“" + oldStaffClassifyStr + "”变为“" + staffClassifyStr + "”;");
        }
        if (oldOperatingPost.longValue() != operatingPost.longValue()) {
            //工作岗位
            String operatingPostJson = systemService.getByTpye(StaffConstant.OPERATING_POST);
            List<Code> operatingPostList = new ArrayList<>();
            if (operatingPostJson != null) {
                JSONObject parseObject = JSONObject.parseObject(operatingPostJson);
                if ((Integer) parseObject.get("code") == 200) {
                    operatingPostList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldOperatingPostStr = "";
            String operatingPostStr = "";
            for (Code code : operatingPostList) {
                if (code.getRowId().equals(oldOperatingPost)) {
                    oldOperatingPostStr = code.getName();
                } else if (code.getRowId().equals(operatingPost)) {
                    operatingPostStr = code.getName();
                }
            }
            updateMsg.append("工作岗位：由“" + oldOperatingPostStr + "”变为“" + operatingPostStr + "”;");
        }
        //新增字段
        if (oldFactoryCategory.longValue() != factoryCategory.longValue()) {
            //厂别
            String factoryCategoryJson = systemService.getByTpye(StaffConstant.FACTORY_CATEGORY);
            List<Code> factoryCategoryList = new ArrayList<>();
            if (factoryCategoryJson != null) {
                JSONObject parseObject = JSONObject.parseObject(factoryCategoryJson);
                if ((Integer) parseObject.get("code") == 200) {
                    factoryCategoryList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldFactoryCategoryStr = "";
            String factoryCategoryStr = "";
            for (Code code : factoryCategoryList) {
                if (code.getRowId().equals(oldFactoryCategory)) {
                    oldFactoryCategoryStr = code.getName();
                } else if (code.getRowId().equals(factoryCategory)) {
                    factoryCategoryStr = code.getName();
                }
            }
            updateMsg.append("厂别：由“" + oldFactoryCategoryStr + "”变为“" + factoryCategoryStr + "”;");
        }
        if (oldRecruitmentChannel.longValue() != recruitmentChannel.longValue()) {
            //招聘渠道
            String recruitmentChannelJson = systemService.getByTpye(StaffConstant.RECRUITMENT_CHANNEL);
            List<Code> recruitmentChannelList = new ArrayList<>();
            if (recruitmentChannelJson != null) {
                JSONObject parseObject = JSONObject.parseObject(recruitmentChannelJson);
                if ((Integer) parseObject.get("code") == 200) {
                    recruitmentChannelList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldRecruitmentChannelStr = "";
            String recruitmentChannelStr = "";
            for (Code code : recruitmentChannelList) {
                if (code.getRowId().equals(oldRecruitmentChannel)) {
                    oldRecruitmentChannelStr = code.getName();
                } else if (code.getRowId().equals(recruitmentChannel)) {
                    recruitmentChannelStr = code.getName();
                }
            }
            updateMsg.append("招聘渠道：由“" + oldRecruitmentChannelStr + "”变为“" + recruitmentChannelStr + "”;");
        }

        if (oldJobStatus.longValue() != jobStatus.longValue()) {
            //是否在职
            String isOnJobJson = systemService.getByTpye(StaffConstant.IS_ON_JOB);
            List<Code> isOnJobList = new ArrayList<>();
            if (isOnJobJson != null) {
                JSONObject parseObject = JSONObject.parseObject(isOnJobJson);
                if ((Integer) parseObject.get("code") == 200) {
                    isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldJobStatusStr = "";
            String jobStatusStr = "";
            for (Code code : isOnJobList) {
                if (code.getRowId().equals(oldJobStatus)) {
                    oldJobStatusStr = code.getName();
                } else if (code.getRowId().equals(jobStatus)) {
                    jobStatusStr = code.getName();
                }
            }
            updateMsg.append("是否在职：由“" + oldJobStatusStr + "”变为“" + jobStatusStr + "”;");
            if (jobStatusStr.equals("离职")) {
                if (oldIsBlacklist.longValue() != isBlacklist.longValue()) {
                    //是否加入黑名单
                    String yesOrNoJson = systemService.getByTpye(StaffConstant.YES_OR_NO);
                    List<Code> yesOrNoList = new ArrayList<>();
                    if (yesOrNoJson != null) {
                        JSONObject parseObject = JSONObject.parseObject(yesOrNoJson);
                        if ((Integer) parseObject.get("code") == 200) {
                            yesOrNoList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                        }
                    }
                    String oldIsBlacklistStr = "";
                    String isBlacklistStr = "";
                    for (Code code : yesOrNoList) {
                        if (code.getRowId().equals(oldIsBlacklist)) {
                            oldIsBlacklistStr = code.getName();
                        } else if (code.getRowId().equals(isBlacklist)) {
                            isBlacklistStr = code.getName();
                        }
                    }
                    updateMsg.append("是否加入黑名单：由“" + oldIsBlacklistStr + "”变为“" + isBlacklistStr + "”;");
                }
            }
        }

        if (oldNationality.longValue() != nationality.longValue()) {
            //国籍
            String nationalityJson = systemService.getByTpye(StaffConstant.NATIONALITY);
            List<Code> nationalityList = new ArrayList<>();
            if (nationalityJson != null) {
                JSONObject parseObject = JSONObject.parseObject(nationalityJson);
                if ((Integer) parseObject.get("code") == 200) {
                    nationalityList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldNationalityStr = "";
            String nationalityStr = "";
            for (Code code : nationalityList) {
                if (code.getRowId().equals(oldNationality)) {
                    oldNationalityStr = code.getName();
                } else if (code.getRowId().equals(nationality)) {
                    nationalityStr = code.getName();
                }
            }
            updateMsg.append("国籍：由“" + oldNationalityStr + "”变为“" + nationalityStr + "”;");
        }
        if (oldNation.longValue() != nation.longValue()) {
            //民族
            String nationJson = systemService.getByTpye(StaffConstant.NATION);
            List<Code> nationList = new ArrayList<>();
            if (nationJson != null) {
                JSONObject parseObject = JSONObject.parseObject(nationJson);
                if ((Integer) parseObject.get("code") == 200) {
                    nationList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            String oldNationStr = "";
            String nationStr = "";
            for (Code code : nationList) {
                if (code.getRowId().equals(oldNation)) {
                    oldNationStr = code.getName();
                } else if (code.getRowId().equals(nation)) {
                    nationStr = code.getName();
                }
            }
            updateMsg.append("民族：由“" + oldNationStr + "”变为“" + nationStr + "”;");
        }
        //
        if ((StringUtils.isEmpty(oldStaffName) && !StringUtils.isEmpty(staffName)) || !StringUtils.isEmpty(oldStaffName) && StringUtils.isEmpty(staffName)) {
            updateMsg.append("姓名：由“" + oldStaffName + "”变为“" + staffName + "”;");
        } else {

        }
        if (!StringUtils.isEmpty(identityNo)) {
            if (!oldIdentityNo.equals(identityNo)) {
                updateMsg.append("证件号码：由“" + oldIdentityNo + "”变为“" + identityNo + "”;");
            }
        }
        //户口所在地
        if (registeredResidence != null && oldRegisteredResidence != null) {
            if (!oldRegisteredResidence.equals(registeredResidence)) {
                updateMsg.append("户口所在地：由“" + oldRegisteredResidence + "”变为“" + registeredResidence + "”;");
            }
        } else if (StringUtils.isEmpty(oldRegisteredResidence) && !StringUtils.isEmpty(registeredResidence)) {
            updateMsg.append("户口所在地：由“”变为“" + registeredResidence + "”;");
        } else if (!StringUtils.isEmpty(oldRegisteredResidence) && StringUtils.isEmpty(registeredResidence)) {
            updateMsg.append("户口所在地：由“" + oldRegisteredResidence + "”变为“”;");
        }
        //籍贯
        if (nativePlace != null && oldNativePlace != null) {
            if (!oldNativePlace.equals(nativePlace)) {
                updateMsg.append("籍贯：由“" + oldNativePlace + "”变为“" + nativePlace + "”;");
            }
        } else if (StringUtils.isEmpty(oldNativePlace) && !StringUtils.isEmpty(nativePlace)) {
            updateMsg.append("籍贯：由“”变为“" + registeredResidence + "”;");
        } else if (!StringUtils.isEmpty(oldNativePlace) && StringUtils.isEmpty(nativePlace)) {
            updateMsg.append("nativePlace：由“" + oldRegisteredResidence + "”变为“”;");
        }
        //办公地点
        if (officePlace != null && oldOfficePlace != null) {
            if (!oldOfficePlace.equals(officePlace)) {
                updateMsg.append("办公地点：由“" + oldOfficePlace + "”变为“" + officePlace + "”;");
            }
        } else if (StringUtils.isEmpty(oldOfficePlace) && !StringUtils.isEmpty(officePlace)) {
            updateMsg.append("办公地点：由“”变为“" + officePlace + "”;");
        } else if (!StringUtils.isEmpty(oldOfficePlace) && StringUtils.isEmpty(officePlace)) {
            updateMsg.append("办公地点：由“" + oldOfficePlace + "”变为“”;");
        }
        //备注
        if (remark != null && oldRemark != null) {
            if (!oldRemark.equals(remark)) {
                updateMsg.append("备注：由“" + oldRemark + "”变为“" + remark + "”;");
            }
        } else if (StringUtils.isEmpty(oldRemark) && !StringUtils.isEmpty(remark)) {
            updateMsg.append("备注：由“”变为“" + remark + "”;");
        } else if (!StringUtils.isEmpty(oldRemark) && StringUtils.isEmpty(remark)) {
            updateMsg.append("备注：由“" + oldRemark + "”变为“”;");
        }
        //新增字段
        if (!StringUtils.isEmpty(mobile)) {
            if (!oldMobile.equals(mobile)) {
                updateMsg.append("手机号：由“" + oldMobile + "”变为“" + mobile + "”;");
            }
        }
        if (email != null && oldEmail != null) {
            if (!oldEmail.equals(email)) {
                updateMsg.append("邮箱：由“" + oldEmail + "”变为“" + email + "”;");
            }
        } else if (StringUtils.isEmpty(oldEmail) && !StringUtils.isEmpty(email)) {
            updateMsg.append("邮箱：由“”变为“" + email + "”;");
        } else if (!StringUtils.isEmpty(oldEmail) && StringUtils.isEmpty(email)) {
            updateMsg.append("邮箱：由“" + oldEmail + "”变为“”;");
        }
        if (classes != null && oldClasses != null) {
            if (!oldClasses.equals(classes)) {
                updateMsg.append("班次：由“" + oldClasses + "”变为“" + classes + "”;");
            }
        } else if (StringUtils.isEmpty(oldClasses) && !StringUtils.isEmpty(classes)) {
            updateMsg.append("班次：由“”变为“" + classes + "”;");
        } else if (!StringUtils.isEmpty(oldClasses) && StringUtils.isEmpty(classes)) {
            updateMsg.append("班次：由“" + oldClasses + "”变为“”;");
        }
        if (workCard != null && oldWorkCard != null) {
            if (!oldWorkCard.equals(workCard)) {
                updateMsg.append("工卡卡号：由“" + oldWorkCard + "”变为“" + workCard + "”;");
            }
        } else if (StringUtils.isEmpty(oldWorkCard) && !StringUtils.isEmpty(workCard)) {
            updateMsg.append("工卡卡号：由“”变为“" + workCard + "”;");
        } else if (!StringUtils.isEmpty(oldWorkCard) && StringUtils.isEmpty(workCard)) {
            updateMsg.append("工卡卡号：由“" + oldWorkCard + "”变为“”;");
        }

        if (dormitoryNo != null && oldDormitoryNo != null) {
            if (!oldDormitoryNo.equals(dormitoryNo)) {
                updateMsg.append("宿舍号：由“" + oldDormitoryNo + "”变为“" + dormitoryNo + "”;");
            }
        } else if (StringUtils.isEmpty(oldDormitoryNo) && !StringUtils.isEmpty(dormitoryNo)) {
            updateMsg.append("宿舍号：由“”变为“" + dormitoryNo + "”;");
        } else if (!StringUtils.isEmpty(oldDormitoryNo) && StringUtils.isEmpty(dormitoryNo)) {
            updateMsg.append("宿舍号：由“" + oldDormitoryNo + "”变为“”;");
        }

        if (lockerShoebox != null && oldLockerShoebox != null) {
            if (!oldLockerShoebox.equals(lockerShoebox)) {
                updateMsg.append("更衣箱鞋柜：由“" + oldLockerShoebox + "”变为“" + lockerShoebox + "”;");
            }
        } else if (StringUtils.isEmpty(oldLockerShoebox) && !StringUtils.isEmpty(lockerShoebox)) {
            updateMsg.append("更衣箱鞋柜：由“”变为“" + lockerShoebox + "”;");
        } else if (!StringUtils.isEmpty(oldLockerShoebox) && StringUtils.isEmpty(lockerShoebox)) {
            updateMsg.append("更衣箱鞋柜：由“" + oldLockerShoebox + "”变为“”;");
        }

        if (speciality != null && oldSpeciality != null) {
            if (!oldSpeciality.equals(speciality)) {
                updateMsg.append("特长：由“" + oldSpeciality + "”变为“" + speciality + "”;");
            }
        } else if (StringUtils.isEmpty(oldSpeciality) && !StringUtils.isEmpty(speciality)) {
            updateMsg.append("特长：由“”变为“" + speciality + "”;");
        } else if (!StringUtils.isEmpty(oldSpeciality) && StringUtils.isEmpty(speciality)) {
            updateMsg.append("特长：由“" + oldSpeciality + "”变为“”;");
        }

        if (jobTitle != null && jobTitle != "" && oldJobTitle != null && oldJobTitle != "") {
            if (!oldJobTitle.equals(jobTitle)) {
                updateMsg.append("职称：由“" + oldJobTitle + "”变为“" + jobTitle + "”;");
            }
        } else if (StringUtils.isEmpty(oldJobTitle) && !StringUtils.isEmpty(jobTitle)) {
            updateMsg.append("职称：由“”变为“" + jobTitle + "”;");
        } else if (!StringUtils.isEmpty(oldJobTitle) && StringUtils.isEmpty(jobTitle)) {
            updateMsg.append("职称：由“" + oldJobTitle + "”变为“”;");
        }

        if (lunarSolarCalendar != null && lunarSolarCalendar != "" && oldLunarSolarCalendar != null && oldLunarSolarCalendar != "") {
            if (!oldLunarSolarCalendar.equals(lunarSolarCalendar)) {
                updateMsg.append("阴历阳历：由“" + oldLunarSolarCalendar + "”变为“" + lunarSolarCalendar + "”;");
            }
        } else if (StringUtils.isEmpty(oldLunarSolarCalendar) && !StringUtils.isEmpty(lunarSolarCalendar)) {
            updateMsg.append("阴历阳历：由“”变为“" + lunarSolarCalendar + "”;");
        } else if (!StringUtils.isEmpty(oldLunarSolarCalendar) && StringUtils.isEmpty(lunarSolarCalendar)) {
            updateMsg.append("阴历阳历：由“" + oldLunarSolarCalendar + "”变为“”;");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (oldIdentityValidDate != identityValidDate) {
            updateMsg.append("证件到期时间：由“" + sdf.format(oldIdentityValidDate) + "”变为“" + sdf.format(identityValidDate) + "”;");
        }
        if (oldBirthdate != birthdate) {
            updateMsg.append("出生日期：由“" + sdf.format(oldBirthdate) + "”变为“" + sdf.format(birthdate) + "”;");
        }
        if (oldFirstWorkingTime != firstWorkingTime) {
            updateMsg.append("首次工作时间：由“" + sdf.format(oldFirstWorkingTime) + "”变为“" + sdf.format(firstWorkingTime) + "”;");
        }
        if (oldEntryDate != entryDate) {
            updateMsg.append("入职日期：由“" + sdf.format(oldEntryDate) + "”变为“" + sdf.format(entryDate) + "”;");
        }
        if (oldLeaveDate != leaveDate) {
            updateMsg.append("离职日期：由“" + sdf.format(oldLeaveDate) + "”变为“" + sdf.format(leaveDate) + "”;");
        }

        ModuleLog moduleLog = new ModuleLog();
        moduleLog.setOptTime(new Date());
        //redis中取登录人信息
        moduleLog.setOptStaffId(RequestContext.get().getStaffId());
        moduleLog.setOptStaffNo(RequestContext.get().getStaffNo());
        moduleLog.setOptStaffName(RequestContext.get().getStaffName());
        moduleLog.setOptType(Constant.OPT_UPDATE);
        moduleLog.setOptDescribe(updateMsg.toString());
        moduleLog.setAppCode(Constant.APP_CODE_STAFF);
        moduleLog.setTableId(baseInfo.getRowId());
        systemService.add(moduleLog);
        baseInfoMapper.updateBaseInfo(baseInfo);
    }

    /**
     * 更新员工部门
     * 
     * @author jinlong
     * @date 2018年8月15日 上午10:12:19
     * @param baseInfo
     */
    public void update(BaseInfo baseInfo) {
        baseInfoMapper.update(baseInfo);
    }

    /**
     * 查询
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:13:01
     * @param staffId
     * @return
     */
    public PageListVO getStfBaseInfoList(SearchBaseInfoForm form) {
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
        PageListVO pageListVO = PageListVO.newInstance(baseInfoMapper.queryBaseInfoList(paginater));
        return pageListVO;
    }

    public Integer queryBaseInfo(Long deptId) {

        String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        Long isOnJobId = null;
        if (isOnJob != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJob);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                if (isOnJobList != null && isOnJobList.size() > 0) {
                    for (Code code : isOnJobList) {
                        if (code.getName().equals("在职")) {
                            isOnJobId = code.getRowId();
                        }
                    }
                }
            }
        }

        //查询该组织机构下的在职员工个数
        Integer baseInfo = baseInfoMapper.queryBaseInfo(deptId, isOnJobId);
        return baseInfo;
    }

    /**
     * 根据塞选条件查询员工信息
     * 
     * @author natyu
     * @date 2018年7月23日 上午9:33:14
     * @param form
     * @return
     */
    public List<BaseInfo> queryBaseInfoForParams(SearchBaseInfoForm form) {
        if (form != null) {
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
        }
        return baseInfoMapper.queryBaseInfoForParams(form);
    }

    /**
     * 根据塞选条件导出员工信息
     * 
     * @author tangjinlin
     * @return
     */
    public List<BaseInfo> queryExportExcelInfo(SearchBaseInfoForm form) {
        if (form != null) {
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
        }
        return baseInfoMapper.queryExportExcelInfo(form);
    }

    /**
     * 批量导入员工基本信息
     * 
     * @author natyu
     * @date 2018年7月9日 下午3:12:09
     * @param list
     */
    @Transactional
    public List<ImportBaseInfoForm> importExcel(List<ImportBaseInfoForm> list) {
        List<BaseInfo> insertBaseInfos = new ArrayList<BaseInfo>();
        List<BaseInfo> updateBaseInfos = new ArrayList<BaseInfo>();
        List<ImportBaseInfoForm> errorBaseInfos = new ArrayList<ImportBaseInfoForm>();

        //职衔
        String positonJson = orgService.getAllPosition();
        List<Position> positionList = new ArrayList<>();
        if (positonJson != null) {
            JSONObject parseObject = JSONObject.parseObject(positonJson);
            if ((Integer) parseObject.get("code") == 200) {
                positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
            }
        }

        //职等/赋值名称
        String gradeJson = orgService.getAllGrade();
        List<Grade> gradeList = new ArrayList<>();
        if (gradeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(gradeJson);
            if ((Integer) parseObject.get("code") == 200) {
                gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
            }
        }

        //职级
        String rankJson = orgService.getAllRank();
        List<Rank> rankList = new ArrayList<>();
        if (rankJson != null) {
            JSONObject parseObject = JSONObject.parseObject(rankJson);
            if ((Integer) parseObject.get("code") == 200) {
                rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
            }
        }

        //基地
        String baseJson = orgService.baseList();
        List<Org> baseList = new ArrayList<>();
        if (baseJson != null) {
            JSONObject parseObject = JSONObject.parseObject(baseJson);
            if ((Integer) parseObject.get("code") == 200) {
                baseList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
            }
        }

        //部门
        String deptJson = orgService.deptList();
        List<Org> deptList = new ArrayList<>();
        if (deptJson != null) {
            JSONObject parseObject = JSONObject.parseObject(deptJson);
            if ((Integer) parseObject.get("code") == 200) {
                deptList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
            }
        }

        //男女
        String maleOrFemaleJson = systemService.getByTpye(StaffConstant.MALE_OR_FEMALE);
        List<Code> maleOrFemaleList = new ArrayList<>();
        if (maleOrFemaleJson != null) {
            JSONObject parseObject = JSONObject.parseObject(maleOrFemaleJson);
            if ((Integer) parseObject.get("code") == 200) {
                maleOrFemaleList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //国籍
        String nationalityJson = systemService.getByTpye(StaffConstant.NATIONALITY);
        List<Code> nationalityList = new ArrayList<>();
        if (nationalityJson != null) {
            JSONObject parseObject = JSONObject.parseObject(nationalityJson);
            if ((Integer) parseObject.get("code") == 200) {
                nationalityList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //民族
        String nationJson = systemService.getByTpye(StaffConstant.NATION);
        List<Code> nationList = new ArrayList<>();
        if (nationJson != null) {
            JSONObject parseObject = JSONObject.parseObject(nationJson);
            if ((Integer) parseObject.get("code") == 200) {
                nationList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //线别
        String linesJson = systemService.getByTpye(StaffConstant.LINES);
        List<Code> linesList = new ArrayList<>();
        if (linesJson != null) {
            JSONObject parseObject = JSONObject.parseObject(linesJson);
            if ((Integer) parseObject.get("code") == 200) {
                linesList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        // 员工分类
        String staffClassifyJson = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
        List<Code> staffClassifyList = new ArrayList<>();
        if (staffClassifyJson != null) {
            JSONObject parseObject = JSONObject.parseObject(staffClassifyJson);
            if ((Integer) parseObject.get("code") == 200) {
                staffClassifyList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        // 工作岗位
        String operatingPostJson = systemService.getByTpye(StaffConstant.OPERATING_POST);
        List<Code> operatingPostList = new ArrayList<>();
        if (operatingPostJson != null) {
            JSONObject parseObject = JSONObject.parseObject(operatingPostJson);
            if ((Integer) parseObject.get("code") == 200) {
                operatingPostList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //证件类型
        String identityTypeJson = systemService.getByTpye(StaffConstant.IDENTITY_TYPE);
        List<Code> identityTypeList = new ArrayList<>();
        if (identityTypeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(identityTypeJson);
            if ((Integer) parseObject.get("code") == 200) {
                identityTypeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //婚姻状况
        String maritalStatusJson = systemService.getByTpye(StaffConstant.MARITAL_STATUS);
        List<Code> maritalStatusList = new ArrayList<>();
        if (maritalStatusJson != null) {
            JSONObject parseObject = JSONObject.parseObject(maritalStatusJson);
            if ((Integer) parseObject.get("code") == 200) {
                maritalStatusList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //生育状况
        String fertilityStatusJson = systemService.getByTpye(StaffConstant.FERTILITY_STATUS);
        List<Code> fertilityStatusList = new ArrayList<>();
        if (fertilityStatusJson != null) {
            JSONObject parseObject = JSONObject.parseObject(fertilityStatusJson);
            if ((Integer) parseObject.get("code") == 200) {
                fertilityStatusList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //政治面貌
        String politicalStatusJson = systemService.getByTpye(StaffConstant.POLITICAL_STATUS);
        List<Code> politicalStatusList = new ArrayList<>();
        if (politicalStatusJson != null) {
            JSONObject parseObject = JSONObject.parseObject(politicalStatusJson);
            if ((Integer) parseObject.get("code") == 200) {
                politicalStatusList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //员工类型
        String staffTypeJson = systemService.getByTpye(StaffConstant.STAFF_TYPE);
        List<Code> staffTypeList = new ArrayList<>();
        if (staffTypeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(staffTypeJson);
            if ((Integer) parseObject.get("code") == 200) {
                staffTypeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //社保类型
        String socialSecurityTypeJson = systemService.getByTpye(StaffConstant.SOCIAL_SECURITY_TYPE);
        List<Code> socialSecurityTypeList = new ArrayList<>();
        if (socialSecurityTypeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(socialSecurityTypeJson);
            if ((Integer) parseObject.get("code") == 200) {
                socialSecurityTypeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //用工类型
        String typeOfLaborJson = systemService.getByTpye(StaffConstant.TYPE_OF_LABOR);
        List<Code> typeOfLaborList = new ArrayList<>();
        if (typeOfLaborJson != null) {
            JSONObject parseObject = JSONObject.parseObject(typeOfLaborJson);
            if ((Integer) parseObject.get("code") == 200) {
                typeOfLaborList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }
        //厂别
        String factoryCategoryJson = systemService.getByTpye(StaffConstant.FACTORY_CATEGORY);
        List<Code> factoryCategoryList = new ArrayList<>();
        if (factoryCategoryJson != null) {
            JSONObject parseObject = JSONObject.parseObject(factoryCategoryJson);
            if ((Integer) parseObject.get("code") == 200) {
                factoryCategoryList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }
        //招聘渠道
        String recruitmentChannelJson = systemService.getByTpye(StaffConstant.RECRUITMENT_CHANNEL);
        List<Code> recruitmentChannelList = new ArrayList<>();
        if (recruitmentChannelJson != null) {
            JSONObject parseObject = JSONObject.parseObject(recruitmentChannelJson);
            if ((Integer) parseObject.get("code") == 200) {
                recruitmentChannelList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //是否在职
        String isOnJobJson = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        List<Code> isOnJobList = new ArrayList<>();
        if (isOnJobJson != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJobJson);
            if ((Integer) parseObject.get("code") == 200) {
                isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //是否
        String yesOrNoJson = systemService.getByTpye(StaffConstant.YES_OR_NO);
        List<Code> yesOrNoList = new ArrayList<>();
        if (yesOrNoJson != null) {
            JSONObject parseObject = JSONObject.parseObject(yesOrNoJson);
            if ((Integer) parseObject.get("code") == 200) {
                yesOrNoList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        //校验文件内数据是否重复
        Map<String, String> staffNoList = new HashMap<>();
        Map<String, String> identityNoList = new HashMap<>();
        for (ImportBaseInfoForm baseInfo : list) {
            String staffNoFile = baseInfo.getStaffNo();
            String identityNoFile = baseInfo.getIdentityNo();
            staffNoList.put(staffNoFile, staffNoFile);
            identityNoList.put(identityNoFile, identityNoFile);
        }
        if (staffNoList.size() != list.size()) {
            AppException.create(200007);
        }
        if (identityNoList.size() != list.size()) {
            AppException.create(200008);
        }

        List<String> userList = new ArrayList<>();
        for (ImportBaseInfoForm baseInfo : list) {

            StringBuffer errMsg = new StringBuffer();

            String positionStr = baseInfo.getPositionStr();
            String rankStr = baseInfo.getRankStr();
            String gradeStr = baseInfo.getGradeStr();
            String baseStr = baseInfo.getBaseStr();
            String deptStr = baseInfo.getDeptStr();
            String costCenterStr = baseInfo.getCostCenterStr();
            String sexStr = baseInfo.getSexStr();
            String identityTypeStr = baseInfo.getIdentityTypeStr();
            String maritalStatusStr = baseInfo.getMaritalStatusStr();
            String fertilityStatusStr = baseInfo.getFertilityStatusStr();
            String politicalStatusStr = baseInfo.getPoliticalStatusStr();
            String staffTypeStr = baseInfo.getStaffTypeStr();
            String socialSecurityTypeStr = baseInfo.getSocialSecurityTypeStr();
            String workTypeStr = baseInfo.getWorkTypeStr();
            String jobStatusStr = baseInfo.getJobStatusStr();
            String isBlacklistStr = baseInfo.getIsBlacklistStr();
            String nationalityStr = baseInfo.getNationalityStr();
            String nationStr = baseInfo.getNationStr();
            String linesStr = baseInfo.getLinesStr();
            String staffClassifyStr = baseInfo.getStaffClassifyStr();
            String operatingPostStr = baseInfo.getOperatingPostStr();
            String factoryCategoryStr = baseInfo.getFactoryCategoryStr();
            String recruitmentChannelStr = baseInfo.getRecruitmentChannelStr();

            //厂别
            if (!StringUtils.isEmpty(factoryCategoryStr)) {
                for (Code code : factoryCategoryList) {
                    if (code.getName().equals(factoryCategoryStr)) {
                        baseInfo.setFactoryCategory(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getFactoryCategory())) {
                    errMsg.append("厂别不存在；");
                }
            }
            //招聘渠道
            if (!StringUtils.isEmpty(recruitmentChannelStr)) {
                for (Code code : recruitmentChannelList) {
                    if (code.getName().equals(recruitmentChannelStr)) {
                        baseInfo.setRecruitmentChannel(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getRecruitmentChannel())) {
                    errMsg.append("招聘渠道不存在；");
                }
            }
            //国籍
            if (!StringUtils.isEmpty(nationalityStr)) {
                for (Code code : nationalityList) {
                    if (code.getName().equals(nationalityStr)) {
                        baseInfo.setNationality(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getNationality())) {
                    errMsg.append("国籍不存在；");
                }
            }

            //民族
            if (!StringUtils.isEmpty(nationStr)) {
                for (Code code : nationList) {
                    if (code.getName().equals(nationStr)) {
                        baseInfo.setNation(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getNation())) {
                    errMsg.append("民族不存在；");
                }
            }

            //职衔
            if (!StringUtils.isEmpty(positionStr)) {
                for (Position position : positionList) {
                    if (position.getPositionName().equals(positionStr)) {
                        baseInfo.setPositionId(position.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getPositionId())) {
                    errMsg.append("职衔不存在；");
                }
            }

            //职等/赋值名称
            if (!StringUtils.isEmpty(gradeStr)) {
                if (!StringUtils.isEmpty(baseInfo.getPositionId())) {
                    String allGrade = orgService.getAllGradeByPosition(baseInfo.getPositionId());
                    List<Grade> allGradeList = new ArrayList<>();
                    if (allGrade != null) {
                        JSONObject parseObject = JSONObject.parseObject(allGrade);
                        if ((Integer) parseObject.get("code") == 200) {
                            allGradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                        }
                    }
                    for (Grade grade : allGradeList) {
                        if (grade.getGradeName().equals(gradeStr)) {
                            baseInfo.setGradeId(grade.getRowId());
                        }
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getGradeId())) {
                    errMsg.append("职衔下该职等/赋值名称，职级不存在；");
                }
            }
            //职级
            if (!StringUtils.isEmpty(rankStr)) {
                if (!StringUtils.isEmpty(baseInfo.getGradeId())) {
                    String allRank = orgService.getAllRankByPositionAndGrade(baseInfo.getPositionId(), baseInfo.getGradeId());
                    List<Rank> allRankList = new ArrayList<>();
                    if (allRank != null) {
                        JSONObject parseObject = JSONObject.parseObject(allRank);
                        if ((Integer) parseObject.get("code") == 200) {
                            allRankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                        }
                    }
                    for (Rank rank : allRankList) {
                        if (rank.getRankName().equals(rankStr)) {
                            baseInfo.setRankId(rank.getRowId());
                        }
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getRankId())) {
                    errMsg.append("职等/赋值名称下该职级不存在；");
                }
            }

            //基地
            if (!StringUtils.isEmpty(baseStr)) {
                for (Org base : baseList) {
                    if (base.getBaseOrDeptName().equals(baseStr)) {
                        baseInfo.setBaseId(base.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getBaseId())) {
                    errMsg.append("基地不存在；");
                }
            }

            //部门
            if (!StringUtils.isEmpty(deptStr)) {
                if (!StringUtils.isEmpty(baseInfo.getBaseId())) {
                    String allSonOrg = orgService.getAllSonOrg(baseInfo.getBaseId());
                    List<Org> allSonOrgList = new ArrayList<>();
                    if (allSonOrg != null) {
                        JSONObject parseObject = JSONObject.parseObject(allSonOrg);
                        if ((Integer) parseObject.get("code") == 200) {
                            allSonOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                        }
                    }
                    for (Org org : allSonOrgList) {
                        if (org.getBaseOrDeptName().equals(deptStr)) {
                            baseInfo.setDeptId(org.getRowId());
                        }
                    }
                    if (StringUtils.isEmpty(baseInfo.getDeptId())) {
                        errMsg.append("基地下该部门不存在；");
                    }
                }
            }

            //线别
            if (!StringUtils.isEmpty(linesStr)) {
                for (Code code : linesList) {
                    if (code.getName().equals(linesStr)) {
                        baseInfo.setLines(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getLines())) {
                    errMsg.append("线别不存在；");
                }
            }

            //员工分类
            if (!StringUtils.isEmpty(staffClassifyStr)) {
                for (Code code : staffClassifyList) {
                    if (code.getName().equals(staffClassifyStr)) {
                        baseInfo.setStaffClassify(code.getCode());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getStaffClassify())) {
                    errMsg.append("员工分类不存在；");
                }
            }

            //工作岗位
            if (!StringUtils.isEmpty(operatingPostStr)) {
                for (Code code : operatingPostList) {
                    if (code.getName().equals(operatingPostStr)) {
                        baseInfo.setOperatingPost(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getOperatingPost())) {
                    errMsg.append("工作岗位不存在；");
                }
            }

            //男女
            if (!StringUtils.isEmpty(sexStr)) {
                for (Code code : maleOrFemaleList) {
                    if (code.getName().equals(sexStr)) {
                        baseInfo.setSex(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getSex())) {
                    errMsg.append("性别不存在；");
                }
            }

            //证件类型
            if (!StringUtils.isEmpty(identityTypeStr)) {
                for (Code code : identityTypeList) {
                    if (code.getName().equals(identityTypeStr)) {
                        baseInfo.setIdentityTypeId(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getIdentityTypeId())) {
                    errMsg.append("证件类型不存在；");
                }
            }
            //婚姻状况
            if (!StringUtils.isEmpty(maritalStatusStr)) {
                for (Code code : maritalStatusList) {
                    if (code.getName().equals(maritalStatusStr)) {
                        baseInfo.setMaritalStatus(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getMaritalStatus())) {
                    errMsg.append("婚姻状况不存在");
                }
            }

            //生育状况
            if (!StringUtils.isEmpty(fertilityStatusStr)) {
                for (Code code : fertilityStatusList) {
                    if (code.getName().equals(fertilityStatusStr)) {
                        baseInfo.setFertilityStatus(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getMaritalStatus())) {
                    errMsg.append("生育状况不存在");
                }
            }

            //政治面貌
            if (!StringUtils.isEmpty(politicalStatusStr)) {
                for (Code code : politicalStatusList) {
                    if (code.getName().equals(politicalStatusStr)) {
                        baseInfo.setPoliticalStatus(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getPoliticalStatus())) {
                    errMsg.append("政治面貌不存在；");
                }
            }

            //员工类型
            if (!StringUtils.isEmpty(staffTypeStr)) {
                for (Code code : staffTypeList) {
                    if (code.getName().equals(staffTypeStr)) {
                        baseInfo.setStaffType(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getStaffType())) {
                    errMsg.append("员工类型不存在；");
                }
            }

            //社保类型
            if (!StringUtils.isEmpty(socialSecurityTypeStr)) {
                for (Code code : socialSecurityTypeList) {
                    if (code.getName().equals(socialSecurityTypeStr)) {
                        baseInfo.setSocialSecurityType(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getSocialSecurityType())) {
                    errMsg.append("社保类型不存在；");
                }
            }

            //成本中心
            if (!StringUtils.isEmpty(costCenterStr)) {
                for (Org base : baseList) {
                    if (base.getBaseOrDeptName().equals(costCenterStr)) {
                        baseInfo.setCostCenter(base.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getCostCenter())) {
                    errMsg.append("成本中心不存在；");
                }
            }

            //用工类型
            if (!StringUtils.isEmpty(workTypeStr)) {
                for (Code code : typeOfLaborList) {
                    if (code.getName().equals(workTypeStr)) {
                        baseInfo.setWorkType(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getWorkType())) {
                    errMsg.append("用工类型不存在；");
                }
            }

            //在职状态
            if (!StringUtils.isEmpty(jobStatusStr)) {
                for (Code code : isOnJobList) {
                    if (code.getName().equals(jobStatusStr)) {
                        baseInfo.setJobStatus(code.getRowId());
                        if ("离职".equals(jobStatusStr)) {
                            if (null == baseInfo.getLeaveDate()) {
                                errMsg.append("在职状态为离职时，离职时间不能为空；");
                            }
                        }
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getJobStatus())) {
                    errMsg.append("在职状态不存在；");
                }
            }

            //是否加入黑名单
            if (!StringUtils.isEmpty(isBlacklistStr)) {
                for (Code code : yesOrNoList) {
                    if (code.getName().equals(isBlacklistStr)) {
                        baseInfo.setIsBlacklist(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getIsBlacklist())) {
                    errMsg.append("是否加入黑名单不存在；");
                }
            } else {
                for (Code code : yesOrNoList) {
                    if ("否".equals(code.getName())) {
                        baseInfo.setIsBlacklist(code.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getIsBlacklist())) {
                    errMsg.append("是否加入黑名单不存在；");
                }
            }

            // 字符长度校验
            String staffNoStr = baseInfo.getStaffNo();
            String staffNameStr = baseInfo.getStaffName();
            String identityNoStr = baseInfo.getIdentityNo();
            String registeredResidenceStr = baseInfo.getRegisteredResidence();
            String nativePlaceStr = baseInfo.getNativePlace();
            String officePlaceStr = baseInfo.getOfficePlace();
            String remarkStr = baseInfo.getRemark();
            String emailStr = baseInfo.getEmail();
            String classStr = baseInfo.getClasses();
            String workCardStr = baseInfo.getWorkCard();
            String dormitoryNoStr = baseInfo.getDormitoryNo();
            String lockerShoeboxStr = baseInfo.getLockerShoebox();
            String specialityStr = baseInfo.getSpeciality();
            String jobTitleStr = baseInfo.getJobTitle();
            // 班次：30
            if (classStr != null && classStr.trim().length() > 30) {
                errMsg.append("班次长度不能超过30位；");
            }
            // 工卡卡号：30
            if (workCardStr != null && workCardStr.trim().length() > 30) {
                errMsg.append("工卡卡号长度不能超过30位；");
            }
            // 宿舍号：30
            if (dormitoryNoStr != null && dormitoryNoStr.trim().length() > 30) {
                errMsg.append("宿舍号长度不能超过30位；");
            }
            // 更衣箱鞋柜：30
            if (lockerShoeboxStr != null && lockerShoeboxStr.trim().length() > 30) {
                errMsg.append("更衣箱鞋柜长度不能超过30位；");
            }
            // 特长：30
            if (specialityStr != null && specialityStr.trim().length() > 30) {
                errMsg.append("特长长度不能超过30位；");
            }
            // 职称：30
            if (jobTitleStr != null && jobTitleStr.trim().length() > 30) {
                errMsg.append("职称长度不能超过30位；");
            }
            // 工号：20
            if (staffNoStr != null && staffNoStr.trim().length() > 20) {
                errMsg.append("工号长度不能超过20位；");
            }
            // 姓名：50
            if (staffNameStr != null && staffNameStr.trim().length() > 50) {
                errMsg.append("姓名长度不能超过50位；");
            }
            // 证件号码：30
            if (identityNoStr != null && identityNoStr.trim().length() > 30) {
                errMsg.append("证件号码长度不能超过30位；");
            }
            // 户口所在地：50
            if (registeredResidenceStr != null && registeredResidenceStr.trim().length() > 30) {
                errMsg.append("户口所在地长度不能超过30位；");
            }
            // 籍贯：50
            if (nativePlaceStr != null && nativePlaceStr.trim().length() > 30) {
                errMsg.append("籍贯长度不能超过30位；");
            }
            // 办公地点：50
            if (officePlaceStr != null && officePlaceStr.trim().length() > 30) {
                errMsg.append("办公地点长度不能超过30位；");
            }
            // 备注：200
            if (remarkStr != null && remarkStr.trim().length() > 200) {
                errMsg.append("备注长度不能超过200位；");
            }
            // 邮箱：30
            if (emailStr != null && emailStr.trim().length() > 30) {
                errMsg.append("邮箱长度不能超过30位；");
            }

            BaseInfo temp = new BaseInfo();
            BeanUtils.copyProperties(baseInfo, temp);
            // TODO  员工编号自动生成
            if (baseInfo.getStaffNo() != null && baseInfo.getStaffNo().trim() != "") {
                if (checkStaffNo(baseInfo.getStaffNo())) {
                    BaseInfo baseInfo1 = baseInfoMapper.getBaseInfoByStaffNo(baseInfo.getStaffNo(), Constant.DEL_FLAG_VALID);
                    if (!baseInfo.getIdentityNo().trim().equals(baseInfo1.getIdentityNo())) {
                        errMsg.append("工号已存在,并且身份证号不一致；");
                    } else {
                        temp.setRowId(baseInfo1.getRowId());
                    }
                } else {
                    if (checkIdentityNo(baseInfo.getIdentityNo())) {
                        errMsg.append("身份证号已存在，并且工号不存在；");
                    }
                }
            } else {
                if (checkIdentityNo(baseInfo.getIdentityNo())) {
                    errMsg.append("身份证号已存在；");
                } else {
                    //                     自动生成员工编号
                    //TODO
                    //                    String staffNo = getStaffNo(0L);
                    //                    temp.setStaffNo(staffNo);
                    Map<String, String> staffNo = getStaffNo(temp.getBaseId());
                    temp.setStaffNo(staffNo.get("staffNo"));
                    String currentId = staffNo.get("currentId");
                    workerCodeRuleService.updateByDeptId(temp.getBaseId(), Integer.valueOf(currentId));
                }
            }
            if (!StringUtils.isEmpty(errMsg.toString())) {
                baseInfo.setErrMsg(errMsg.toString());
                errorBaseInfos.add(baseInfo);
            } else {
                if (temp.getRowId() != null) {
                    baseInfoMapper.updateBaseInfo(temp);
                } else {
                    insertBaseInfos.add(temp);
                    userList.add(temp.getStaffNo());
                }
            }
        }

        if (insertBaseInfos != null && insertBaseInfos.size() > 0) {
            baseInfoMapper.insertBaseInfoList(insertBaseInfos);
            //批量生成用户
            systemService.batchAdd(userList);
        }

        return errorBaseInfos;
    }

    /**
     * 校验工号是否存在
     * 
     * @author natyu
     * @date 2018年7月24日 下午7:10:19
     * @param staffNo
     * @return
     */
    public boolean checkStaffNo(String staffNo) {
        if (baseInfoMapper.checkStaffNo(staffNo, Constant.DEL_FLAG_VALID) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 所有员工中校验身份证号是否存在
     * 
     * @author natyu
     * @date 2018年7月24日 下午7:10:19
     * @param staffNo
     * @return
     */
    public boolean checkIdentityNo(String identityNo) {
        if (baseInfoMapper.checkIdentityNo(identityNo, Constant.DEL_FLAG_VALID) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 在职员工中校验身份证号是否存在
     * 
     * @author natyu
     * @date 2018年7月24日 下午7:10:19
     * @param staffNo
     * @return
     */
    public int checkIdentityNoByJob(String identityNo, Long rowId) {
        String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        Long isOnJobId = null;
        if (isOnJob != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJob);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                if (isOnJobList != null && isOnJobList.size() > 0) {
                    for (Code code : isOnJobList) {
                        if (code.getName().equals("在职")) {
                            isOnJobId = code.getRowId();
                        }
                    }
                }
            }
        }

        return baseInfoMapper.checkIdentityNoByJob(identityNo, Constant.DEL_FLAG_VALID, isOnJobId, rowId);
    }

    /**
     * 在职员工中校验手机号唯一
     * 
     * @author natyu
     * @date 2018年7月24日 下午7:10:19
     * @param staffNo
     * @return
     */
    public int checkMobileByJob(String mobile, Long rowId) {
        String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        Long isOnJobId = null;
        if (isOnJob != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJob);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                if (isOnJobList != null && isOnJobList.size() > 0) {
                    for (Code code : isOnJobList) {
                        if (code.getName().equals("在职")) {
                            isOnJobId = code.getRowId();
                        }
                    }
                }
            }
        }

        return baseInfoMapper.checkMobileByJob(mobile, Constant.DEL_FLAG_VALID, isOnJobId, rowId);
    }

    /**
     * 在职员工中校验邮箱唯一
     * 
     * @author natyu
     * @date 2018年7月24日 下午7:10:19
     * @param staffNo
     * @return
     */
    public int checkEmailByJob(String email, Long rowId) {
        String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        Long isOnJobId = null;
        if (isOnJob != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJob);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                if (isOnJobList != null && isOnJobList.size() > 0) {
                    for (Code code : isOnJobList) {
                        if (code.getName().equals("在职")) {
                            isOnJobId = code.getRowId();
                        }
                    }
                }
            }
        }

        return baseInfoMapper.checkEmailByJob(email, Constant.DEL_FLAG_VALID, isOnJobId, rowId);
    }

    /**
     * 导出员工导入的错误信息
     * 
     * @author natyu
     * @date 2018年7月27日 下午3:16:59
     * @param response
     * @param form
     * @param lang
     */
    public void exportErrExcel(HttpServletResponse response, List<ImportBaseInfoForm> list, String lang) {

        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();

        fieldList.add("staffNo");
        fieldList.add("staffName");
        fieldList.add("sexStr");
        fieldList.add("baseStr");
        fieldList.add("deptStr");
        fieldList.add("positionStr");
        fieldList.add("rankStr");
        fieldList.add("gradeStr");
        fieldList.add("identityTypeStr");
        fieldList.add("identityNo");
        fieldList.add("entryDate");
        fieldList.add("identityValidDate");
        fieldList.add("birthdate");
        fieldList.add("lunarSolarCalendar");
        fieldList.add("nationalityStr");
        fieldList.add("nationStr");
        fieldList.add("staffClassifyStr");
        fieldList.add("operatingPostStr");
        fieldList.add("mobile");
        fieldList.add("email");
        fieldList.add("registeredResidence");
        fieldList.add("nativePlace");
        fieldList.add("fertilityStatusStr");
        fieldList.add("maritalStatusStr");
        fieldList.add("politicalStatusStr");
        fieldList.add("firstWorkingTime");
        fieldList.add("staffTypeStr");
        fieldList.add("socialSecurityTypeStr");
        fieldList.add("costCenterStr");
        fieldList.add("officePlace");
        fieldList.add("entryDate");
        fieldList.add("workTypeStr");
        fieldList.add("jobStatusStr");
        fieldList.add("linesStr");
        fieldList.add("leaveDate");
        fieldList.add("isBlacklistStr");
        fieldList.add("factoryCategoryStr");
        fieldList.add("classes");
        fieldList.add("recruitmentChannelStr");
        fieldList.add("workCard");
        fieldList.add("dormitoryNo");
        fieldList.add("lockerShoebox");
        fieldList.add("speciality");
        fieldList.add("jobTitle");
        fieldList.add("remark");
        fieldList.add("errMsg");

        System.out.println("lang:" + lang);

        String title = "";
        int size = list.size();
        String[] headers = new String[size];

        if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
            headerList.add("工号");
            headerList.add("姓名");
            headerList.add("性别");
            headerList.add("基地");
            headerList.add("部门");
            headerList.add("职衔");
            headerList.add("职级");
            headerList.add("职等/赋值名称");
            headerList.add("证件类型");
            headerList.add("证件号码");
            headerList.add("入职次数");
            headerList.add("证件到期时间");
            headerList.add("出生日期");
            headerList.add("阴历阳历");
            headerList.add("国籍");
            headerList.add("民族");
            headerList.add("员工分类");
            headerList.add("工作岗位");
            headerList.add("手机号");
            headerList.add("邮箱");
            headerList.add("户口所在地");
            headerList.add("籍贯");
            headerList.add("生育状况");
            headerList.add("婚姻状况");
            headerList.add("政治面貌");
            headerList.add("首次工作时间");
            headerList.add("员工类型");
            headerList.add("社保类型");
            headerList.add("成本中心");
            headerList.add("办公地点");
            headerList.add("入职日期");
            headerList.add("用工类型");
            headerList.add("在职状态");
            headerList.add("线别");
            headerList.add("离职日期");
            headerList.add("是否加入黑名单");
            headerList.add("厂别");
            headerList.add("班次");
            headerList.add("招聘渠道");
            headerList.add("工卡卡号");
            headerList.add("宿舍号");
            headerList.add("更衣箱鞋柜");
            headerList.add("特长");
            headerList.add("职称");
            headerList.add("备注");
            headerList.add("错误描述");
            title = "员工错误信息";

            headers = (String[]) headerList.toArray(new String[0]);

        } else if (lang.equalsIgnoreCase(Constant.EN)) {

            headerList.add("Staff No");
            headerList.add("Staff Name");
            headerList.add("Sex");
            headerList.add("Department");
            headerList.add("职衔");
            headerList.add("职级");
            headerList.add("职等/赋值名称");
            headerList.add("证件类型");
            headerList.add("证件号码");
            headerList.add("入职次数");
            headerList.add("证件到期时间");
            headerList.add("出生日期");
            headerList.add("阴历阳历");
            headerList.add("国籍");
            headerList.add("民族");
            headerList.add("户口所在地");
            headerList.add("籍贯");
            headerList.add("生育状况");
            headerList.add("婚姻状况");
            headerList.add("政治面貌");
            headerList.add("首次工作时间");
            headerList.add("工作年限");
            headerList.add("员工类型");
            headerList.add("社保类型");
            headerList.add("成本中心");
            headerList.add("办公地点");
            headerList.add("入职日期");
            headerList.add("司龄");
            headerList.add("用工类型");
            headerList.add("在职状态");
            headerList.add("离职日期");
            headerList.add("是否加入黑名单");
            headerList.add("厂别");
            headerList.add("班次");
            headerList.add("招聘渠道");
            headerList.add("工卡卡号");
            headerList.add("宿舍号");
            headerList.add("更衣箱鞋柜");
            headerList.add("特长");
            headerList.add("职称");
            headerList.add("备注");
            headerList.add("Error Description");
            //TODO
            title = "Staff Base Information";
            headers = (String[]) headerList.toArray(new String[0]);
        }

        // pojo字段
        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }

    /**
     * 导出员工基础信息
     * 
     * @author natyu
     * @date 2018年7月27日 下午3:16:59
     * @param response
     * @param form
     * @param lang
     */
    public void exportExcel(HttpServletResponse response, List<BaseInfo> list, String lang) {

        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();

        fieldList.add("staffNo");
        fieldList.add("staffName");
        fieldList.add("transNames.sex_name");
        fieldList.add("transNames.baseId_baseOrDeptName");
        fieldList.add("transNames.deptId_baseOrDeptName");
        fieldList.add("transNames.positionId_positionName");
        fieldList.add("transNames.rankId_rankName");
        fieldList.add("transNames.gradeId_gradeName");
        fieldList.add("transNames.gradeId_postAssignment");
        fieldList.add("transNames.identityTypeId_name");
        fieldList.add("identityNo");
        fieldList.add("times");
        fieldList.add("identityValidDate");
        fieldList.add("birthdate");
        fieldList.add("lunarSolarCalendar");
        fieldList.add("transNames.nationality_name");
        fieldList.add("transNames.nation_name");
        fieldList.add("transNames.staffClassify_name");
        fieldList.add("transNames.operatingPost_name");
        fieldList.add("mobile");
        fieldList.add("email");
        fieldList.add("registeredResidence");
        fieldList.add("nativePlace");
        fieldList.add("transNames.fertilityStatus_name");
        fieldList.add("transNames.maritalStatus_name");
        fieldList.add("transNames.politicalStatus_name");
        fieldList.add("firstWorkingTime");
        fieldList.add("transNames.staffType_name");
        fieldList.add("transNames.socialSecurityType_name");
        fieldList.add("transNames.costCenter_baseOrDeptName");
        fieldList.add("officePlace");
        fieldList.add("entryDate");
        fieldList.add("transNames.workType_name");
        fieldList.add("transNames.jobStatus_name");
        fieldList.add("transNames.lines_name");
        fieldList.add("leaveDate");
        fieldList.add("transNames.isBlacklist_name");
        fieldList.add("transNames.factoryCategory_name");
        fieldList.add("classes");
        fieldList.add("transNames.recruitmentChannel_name");
        fieldList.add("workCard");
        fieldList.add("dormitoryNo");
        fieldList.add("lockerShoebox");
        fieldList.add("speciality");
        fieldList.add("jobTitle");
        fieldList.add("remark");

        System.out.println("lang:" + lang);

        String title = "";
        int size = list.size();
        String[] headers = new String[size];

        if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
            headerList.add("工号");
            headerList.add("姓名");
            headerList.add("性别");
            headerList.add("基地");
            headerList.add("部门");
            headerList.add("职衔");
            headerList.add("职级");
            headerList.add("职等/赋值名称");
            headerList.add("赋值");
            headerList.add("证件类型");
            headerList.add("证件号码");
            headerList.add("入职次数");
            headerList.add("证件到期时间");
            headerList.add("出生日期");
            headerList.add("阴历阳历");
            headerList.add("国籍");
            headerList.add("民族");
            headerList.add("员工分类");
            headerList.add("工作岗位");
            headerList.add("手机号");
            headerList.add("邮箱");
            headerList.add("户口所在地");
            headerList.add("籍贯");
            headerList.add("生育状况");
            headerList.add("婚姻状况");
            headerList.add("政治面貌");
            headerList.add("首次工作时间");
            headerList.add("员工类型");
            headerList.add("社保类型");
            headerList.add("成本中心");
            headerList.add("办公地点");
            headerList.add("入职日期");
            headerList.add("用工类型");
            headerList.add("在职状态");
            headerList.add("线别");
            headerList.add("离职日期");
            headerList.add("是否加入黑名单");
            headerList.add("厂别");
            headerList.add("班次");
            headerList.add("招聘渠道");
            headerList.add("工卡卡号");
            headerList.add("宿舍号");
            headerList.add("更衣箱鞋柜");
            headerList.add("特长");
            headerList.add("职称");
            headerList.add("备注");

            title = "员工信息";

            headers = (String[]) headerList.toArray(new String[0]);

        } else if (lang.equalsIgnoreCase(Constant.EN)) {

            headerList.add("工号");
            headerList.add("姓名");
            headerList.add("性别");
            headerList.add("基地");
            headerList.add("部门");
            headerList.add("职衔");
            headerList.add("职级");
            headerList.add("职等/赋值名称");
            headerList.add("赋值");
            headerList.add("证件类型");
            headerList.add("证件号码");
            headerList.add("入职次数");
            headerList.add("证件到期时间");
            headerList.add("出生日期");
            headerList.add("阴历阳历");
            headerList.add("国籍");
            headerList.add("民族");
            headerList.add("员工分类");
            headerList.add("工作岗位");
            headerList.add("手机号");
            headerList.add("邮箱");
            headerList.add("户口所在地");
            headerList.add("籍贯");
            headerList.add("生育状况");
            headerList.add("婚姻状况");
            headerList.add("政治面貌");
            headerList.add("首次工作时间");
            headerList.add("员工类型");
            headerList.add("社保类型");
            headerList.add("成本中心");
            headerList.add("办公地点");
            headerList.add("入职日期");
            headerList.add("用工类型");
            headerList.add("在职状态");
            headerList.add("线别");
            headerList.add("离职日期");
            headerList.add("是否加入黑名单");
            headerList.add("厂别");
            headerList.add("班次");
            headerList.add("招聘渠道");
            headerList.add("工卡卡号");
            headerList.add("宿舍号");
            headerList.add("更衣箱鞋柜");
            headerList.add("特长");
            headerList.add("职称");
            headerList.add("备注");
            //TODO
            title = "Staff Base Information";
            headers = (String[]) headerList.toArray(new String[0]);
        }

        // pojo字段
        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }

    /**
     * 查询用户列表信息根据staffIds
     * 
     * @author natyu
     * @date 2018年7月25日 上午11:38:28
     * @param staffIds
     * @return
     */
    public List<BaseInfo> getBaseInfoByIds(List<Long> staffIds) {
        if (staffIds == null || staffIds.size() == 0) {
            return null;
        }
        return baseInfoMapper.getBaseInfoByIds(staffIds);
    }

    public int orgMerge(Long sourceId, Long targetId) {

        return baseInfoMapper.orgMerge(sourceId, targetId);
    }

    public int delDept_ids(List<Long> dept_Ids) {
        List<Long> dept_Ids2 = new ArrayList<Long>();
        for (Long long1 : dept_Ids) {
            int count = baseInfoMapper.queryByDeptId(long1);
            if (count > 0) {
                dept_Ids2.add(long1);
            }
        }
        if (!dept_Ids2.isEmpty()) {
            return baseInfoMapper.delDept_ids(dept_Ids2);
        } else {
            return 0;
        }
    }

    public int getCountByIdNo(String idNo) {
        return baseInfoMapper.getCountByIdNo(idNo);
    }

    public Map<String, String> getStaffNo(Long baseId) {
        Map<String, String> map = new HashMap<String, String>();
        WorkerCodeRule queryOneByBaseId = workerCodeRuleDao.queryOneByBaseId(baseId);

        if (queryOneByBaseId != null) {
            generateWorkerCode(baseId, queryOneByBaseId.getCurrentId(), map);
            return map;
        }

        return null;

    }

    /**
     * 离职异动
     * 
     * @author natyu
     * @date 2018年8月3日 下午8:37:30
     * @param form
     * @return
     */
    public void updateLeaveOffice(String[] ids, Long isBlackList, Date leaveDate, Long isOnJob) {
        baseInfoMapper.updateLeaveOffice(ids, isBlackList, leaveDate, isOnJob);
    }

    /*
     * @Author jsl
     * @Date 根据userName 获取员工信息 2018/8/10
     * @Description
     **/
    public BaseInfo getBaseInfro(String userName) {
        BaseInfo baseInfo = baseInfoMapper.getBaseInfo(userName);
        return baseInfo;
    }

    /**
     * 根据员工编号查询员工
     */
    public BaseInfo getBaseInfoByStaffNo(String staffNo) {
        BaseInfo baseInfo = baseInfoMapper.getBaseInfoByStaffNo(staffNo, null);
        return baseInfo;
    }

    /**
     * 查询该职衔下有多少个员工
     * @param positionId
     * @return
     */
    public Integer queryCountBaseInfoByPositionId(Long positionId) {
        return baseInfoMapper.queryCountBaseInfoByPositionId(positionId);
    }

    /**
     * 查询是否有离职人员
     * 
     * @author natyu
     * @date 2018年8月14日 下午7:10:28
     * @param ids
     * @return
     */
    public int checkHasLeave(String[] ids) {
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
        return baseInfoMapper.checkHasLeave(ids, isOnJobId);
    }

    public List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId() {
        String isOnJob = systemService.getByTpye(StaffConstant.IS_ON_JOB);
        Long isOnJobId = null;
        if (isOnJob != null) {
            JSONObject parseObject = JSONObject.parseObject(isOnJob);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> isOnJobList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                if (isOnJobList != null && isOnJobList.size() > 0) {
                    for (Code code : isOnJobList) {
                        if (code.getName().equals("在职")) {
                            isOnJobId = code.getRowId();
                        }
                    }
                }
            }
        }
        List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = baseInfoMapper.queryCountBaseInfoByDeptId(isOnJobId);
        return queryCountBaseInfoByDeptId;
    }

    /**
     * @Author jsl
     * @Date 12:18 2018/8/25
     * @Description 查询销售人员列表 后期需要修改
     **/
    public List<BaseSelect> getSalePersonList() {
        List<BaseSelect> list = baseInfoMapper.getSalePersonList();
        return list;
    }

    /**
     * 根据row_id 获取员工姓名和工号
     * @Author created by barrett in 2018/9/5 09:18
     */
    public BaseInfo getUserNameJobNumberById(Long id) {
        BaseInfo info = baseInfoMapper.getUserNameJobNumberById(id);
        return info;
    }

    public int queryByBaseId(Long baseId) {
        return baseInfoMapper.queryByBaseId(baseId);
    }

    /**
     * 根据组织架构ids 获取员工id
     * 
     * @author natyu
     * @date 2018年9月17日 下午3:48:00
     * @param orgIds
     * @return
     */
    public List<Long> getStaffIdByOrgIds(List<Long> orgIds) {
        return baseInfoMapper.getStaffIdByOrgIds(orgIds);
    }

    /**
     * 合同解除修改员工信息 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年9月18日 上午11:03:48
     * @param baseInfo
     */
    public void updateBaseInfoByCotract(BaseInfo baseInfo) {
        baseInfoMapper.updateBaseInfo(baseInfo);
    }

    public List<String> getStaffNoBystaffIds(List<Long> ids) {
        return baseInfoMapper.getStaffNoBystaffIds(ids);
    }

    /**
     * 批量导入员工基本信息
     * 
     * @author natyu
     * @date 2018年7月9日 下午3:12:09
     * @param list
     */
    @Transactional
    public List<ImportBaseInfoPositionForm> importPositionExcel(List<ImportBaseInfoPositionForm> list) {
        List<BaseInfo> updateBaseInfos = new ArrayList<BaseInfo>();
        List<ImportBaseInfoPositionForm> errorBaseInfos = new ArrayList<ImportBaseInfoPositionForm>();

        //职衔
        String positonJson = orgService.getAllPosition();
        List<Position> positionList = new ArrayList<>();
        if (positonJson != null) {
            JSONObject parseObject = JSONObject.parseObject(positonJson);
            if ((Integer) parseObject.get("code") == 200) {
                positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
            }
        }

        //职等/赋值名称
        String gradeJson = orgService.getAllGrade();
        List<Grade> gradeList = new ArrayList<>();
        if (gradeJson != null) {
            JSONObject parseObject = JSONObject.parseObject(gradeJson);
            if ((Integer) parseObject.get("code") == 200) {
                gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
            }
        }

        //职级
        String rankJson = orgService.getAllRank();
        List<Rank> rankList = new ArrayList<>();
        if (rankJson != null) {
            JSONObject parseObject = JSONObject.parseObject(rankJson);
            if ((Integer) parseObject.get("code") == 200) {
                rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
            }
        }
        for (ImportBaseInfoPositionForm baseInfo : list) {

            StringBuffer errMsg = new StringBuffer();

            String positionStr = baseInfo.getPositionStr();
            String rankStr = baseInfo.getRankStr();
            String gradeStr = baseInfo.getGradeStr();

            boolean checkStaffNo = checkStaffNo(baseInfo.getStaffNo());

            if (!checkStaffNo) {
                errMsg.append("工号不存在；");
            }

            //职衔
            if (!StringUtils.isEmpty(positionStr)) {
                for (Position position : positionList) {
                    if (position.getPositionName().equals(positionStr)) {
                        baseInfo.setPositionId(position.getRowId());
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getPositionId())) {
                    errMsg.append("职衔不存在；");
                }
            }

            //职等/赋值名称
            if (!StringUtils.isEmpty(gradeStr)) {
                if (!StringUtils.isEmpty(baseInfo.getPositionId())) {
                    String allGrade = orgService.getAllGradeByPosition(baseInfo.getPositionId());
                    List<Grade> allGradeList = new ArrayList<>();
                    if (allGrade != null) {
                        JSONObject parseObject = JSONObject.parseObject(allGrade);
                        if ((Integer) parseObject.get("code") == 200) {
                            allGradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                        }
                    }
                    for (Grade grade : allGradeList) {
                        if (grade.getGradeName().equals(gradeStr)) {
                            baseInfo.setGradeId(grade.getRowId());
                        }
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getGradeId())) {
                    errMsg.append("职衔下该职等/赋值名称，职级不存在；");
                }
            }
            //职级
            if (!StringUtils.isEmpty(rankStr)) {
                if (!StringUtils.isEmpty(baseInfo.getGradeId())) {
                    String allRank = orgService.getAllRankByPositionAndGrade(baseInfo.getPositionId(), baseInfo.getGradeId());
                    List<Rank> allRankList = new ArrayList<>();
                    if (allRank != null) {
                        JSONObject parseObject = JSONObject.parseObject(allRank);
                        if ((Integer) parseObject.get("code") == 200) {
                            allRankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                        }
                    }
                    for (Rank rank : allRankList) {
                        if (rank.getRankName().equals(rankStr)) {
                            baseInfo.setRankId(rank.getRowId());
                        }
                    }
                }
                if (StringUtils.isEmpty(baseInfo.getRankId())) {
                    errMsg.append("职等/赋值名称下该职级不存在；");
                }
            }

            // 字符长度校验
            String staffNoStr = baseInfo.getStaffNo();

            BaseInfo temp = new BaseInfo();
            BeanUtils.copyProperties(baseInfo, temp);
            if (!StringUtils.isEmpty(errMsg.toString())) {
                baseInfo.setErrMsg(errMsg.toString());
                errorBaseInfos.add(baseInfo);
            } else {
                updateStfBaseInfoPosition(temp);
            }
        }

        return errorBaseInfos;
    }

    /**
     * 导出员工导入的错误信息
     * 
     * @author natyu
     * @date 2018年7月27日 下午3:16:59
     * @param response
     * @param form
     * @param lang
     */
    public void exportPositionErrExcel(HttpServletResponse response, List<ImportBaseInfoPositionForm> list, String lang) {

        // 表头
        List<String> headerList = new ArrayList<String>();
        // field
        List<String> fieldList = new ArrayList<String>();

        fieldList.add("staffNo");
        fieldList.add("positionStr");
        fieldList.add("gradeStr");
        fieldList.add("rankStr");
        fieldList.add("errMsg");

        System.out.println("lang:" + lang);

        String title = "";
        int size = list.size();
        String[] headers = new String[size];

        if (StringUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constant.ZH)) {
            headerList.add("工号");
            headerList.add("职衔");
            headerList.add("职等/赋值名称");
            headerList.add("职级");
            headerList.add("错误描述");
            title = "员工错误信息";

            headers = (String[]) headerList.toArray(new String[0]);

        } else if (lang.equalsIgnoreCase(Constant.EN)) {

            headerList.add("Staff No");
            headerList.add("职衔");
            headerList.add("职等/赋值名称");
            headerList.add("职级");
            headerList.add("Error Description");
            //TODO
            title = "Staff Base Information";
            headers = (String[]) headerList.toArray(new String[0]);
        }

        // pojo字段
        String[] fields = (String[]) fieldList.toArray(new String[0]);

        // 生成Excel表格
        ExportExcelUtil.exportExcel(response, list, title, headers, fields, null);
    }

    /**
     * 修改
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:36
     * @param stfBaseInfo
     */
    public void updateStfBaseInfoPosition(BaseInfo baseInfo) {

        baseInfo.setLastUpdateTime(new Date());
        baseInfo.setLastUpdateBy(RequestContext.get().getStaffId());

        BaseInfo oldBaseInfo = baseInfoMapper.getBaseInfoByStaffNo(baseInfo.getStaffNo(), Constant.DEL_FLAG_VALID);
        Long oldCheckJobStatus = oldBaseInfo.getJobStatus();
        String staffNo = baseInfo.getStaffNo();

        //生成变更记录
        StringBuffer updateMsg = new StringBuffer();
        //old
        Long oldPositionId = 0L;
        Long oldRankId = 0L;
        Long oldGradeId = 0L;
        if (oldBaseInfo.getPositionId() != null) {
            oldPositionId = oldBaseInfo.getPositionId();
        }
        if (oldBaseInfo.getRankId() != null) {
            oldRankId = oldBaseInfo.getRankId();
        }
        if (oldBaseInfo.getGradeId() != null) {
            oldGradeId = oldBaseInfo.getGradeId();
        }

        //new
        Long positionId = 0L;
        Long rankId = 0L;
        Long gradeId = 0L;
        if (baseInfo.getPositionId() != null) {
            positionId = baseInfo.getPositionId();
        }
        if (baseInfo.getRankId() != null) {
            rankId = baseInfo.getRankId();
        }
        if (baseInfo.getGradeId() != null) {
            gradeId = baseInfo.getGradeId();
        }

        //职衔
        if (oldPositionId.longValue() != positionId.longValue()) {
            String positonJson = orgService.getAllPosition();
            List<Position> positionList = new ArrayList<>();
            if (positonJson != null) {
                JSONObject parseObject = JSONObject.parseObject(positonJson);
                if ((Integer) parseObject.get("code") == 200) {
                    positionList = JSONObject.parseArray(parseObject.getString("data"), Position.class);
                }
            }
            String oldPositionIdStr = "";
            String positionIdStr = "";
            for (Position position : positionList) {
                if (position.getRowId().equals(oldPositionId)) {
                    oldPositionIdStr = position.getPositionName();
                } else if (position.getRowId().equals(positionId)) {
                    positionIdStr = position.getPositionName();
                }
            }
            updateMsg.append("职衔：由“" + oldPositionIdStr + "”变为“" + positionIdStr + "”;");
        }

        //职等/赋值名称
        if (oldGradeId.longValue() != gradeId.longValue()) {
            String gradeJson = orgService.getAllGrade();
            List<Grade> gradeList = new ArrayList<>();
            if (gradeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(gradeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    gradeList = JSONObject.parseArray(parseObject.getString("data"), Grade.class);
                }
            }
            String oldGradeIdStr = "";
            String gradeIdStr = "";
            for (Grade grade : gradeList) {
                if (grade.getRowId().equals(oldGradeId)) {
                    oldGradeIdStr = grade.getGradeName();
                } else if (grade.getRowId().equals(gradeId)) {
                    gradeIdStr = grade.getGradeName();
                }
            }
            updateMsg.append("职等/赋值名称：由“" + oldGradeIdStr + "”变为“" + gradeIdStr + "”;");
        }

        //职级
        if (oldRankId.longValue() != rankId.longValue()) {
            String rankJson = orgService.getAllRank();
            List<Rank> rankList = new ArrayList<>();
            if (rankJson != null) {
                JSONObject parseObject = JSONObject.parseObject(rankJson);
                if ((Integer) parseObject.get("code") == 200) {
                    rankList = JSONObject.parseArray(parseObject.getString("data"), Rank.class);
                }
            }
            String oldRankIdStr = "";
            String rankIdStr = "";
            for (Rank rank : rankList) {
                if (rank.getRowId().equals(oldRankId)) {
                    oldRankIdStr = rank.getRankName();
                } else if (rank.getRowId().equals(rankId)) {
                    rankIdStr = rank.getRankName();
                }
            }
            updateMsg.append("职级：由“" + oldRankIdStr + "”变为“" + rankIdStr + "”;");
        }

        ModuleLog moduleLog = new ModuleLog();
        moduleLog.setOptTime(new Date());
        baseInfo.setRowId(oldBaseInfo.getRowId());
        //redis中取登录人信息
        moduleLog.setOptStaffId(RequestContext.get().getStaffId());
        moduleLog.setOptStaffNo(RequestContext.get().getStaffNo());
        moduleLog.setOptStaffName(RequestContext.get().getStaffName());
        moduleLog.setOptType(Constant.OPT_UPDATE);
        moduleLog.setOptDescribe(updateMsg.toString());
        moduleLog.setAppCode(Constant.APP_CODE_STAFF);
        moduleLog.setTableId(baseInfo.getRowId());
        systemService.add(moduleLog);
        baseInfoMapper.updateBaseInfo(baseInfo);
    }

}
