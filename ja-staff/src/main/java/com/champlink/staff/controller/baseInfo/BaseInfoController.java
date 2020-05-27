package com.champlink.staff.controller.baseInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.BaseSelect;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.position.Grade;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.staff.baseInfo.Address;
import com.champlink.common.domain.staff.baseInfo.AdjustmentWork;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.staff.baseInfo.ContactEmergency;
import com.champlink.common.domain.staff.baseInfo.Education;
import com.champlink.common.domain.staff.baseInfo.OuterExperience;
import com.champlink.common.domain.staff.baseInfo.ProjectExperience;
import com.champlink.common.domain.staff.baseInfo.RelationshipInner;
import com.champlink.common.domain.staff.baseInfo.RelationshipSocial;
import com.champlink.common.domain.staff.contract.Award;
import com.champlink.common.domain.staff.contract.RecordProfession;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.CodeType;
import com.champlink.common.form.org.org.QueryCountBaseInfoByDeptId;
import com.champlink.common.form.staff.baseInfo.ImportBaseInfoForm;
import com.champlink.common.form.staff.baseInfo.ImportBaseInfoPositionForm;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.context.ContextUtils;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.staff.dao.baseInfo.AdjustmentWorkMapper;
import com.champlink.staff.dao.baseInfo.BaseInfoMapper;
import com.champlink.staff.jxAppBean.JaHrEmpCommunicationInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpEducationExperienceBean;
import com.champlink.staff.jxAppBean.JaHrEmpEmerInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpHonorInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpProjectInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpPromoteInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpSateInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpSkllCertficateInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmpSoclalHistoryInfoBean;
import com.champlink.staff.jxAppBean.JaHrEmployeeBean;
import com.champlink.staff.jxAppBean.JaHrempKinsfolkRelationInfoBean;
import com.champlink.staff.service.baseInfo.AddressService;
import com.champlink.staff.service.baseInfo.AdjustmentWorkService;
import com.champlink.staff.service.baseInfo.BaseInfoService;
import com.champlink.staff.service.baseInfo.ContactEmergencyService;
import com.champlink.staff.service.baseInfo.EducationService;
import com.champlink.staff.service.baseInfo.OuterExperienceService;
import com.champlink.staff.service.baseInfo.ProjectExperienceService;
import com.champlink.staff.service.baseInfo.RelationshipInnerService;
import com.champlink.staff.service.baseInfo.RelationshipSocialService;
import com.champlink.staff.service.call.OrgService;
import com.champlink.staff.service.call.SystemService;
import com.champlink.staff.service.contract.AwardService;
import com.champlink.staff.service.contract.RecordProfessionService;

/**
 * 
 * 员工Controller
 * 
 * @author natyu
 * @date 2018年7月14日 下午2:45:22
 *
 */
@Controller
@RestController
@RequestMapping("/baseInfo")
public class BaseInfoController extends BaseController {

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private RelationshipSocialService relationshipSocialService;
    @Autowired
    private ContactEmergencyService contactEmergencyService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private OuterExperienceService outerExperienceService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private RelationshipInnerService relationshipInnerService;
    @Autowired
    private RecordProfessionService recordProfessionService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private ProjectExperienceService projectExperienceService;
    @Autowired
    private AdjustmentWorkMapper adjustmentWorkMapper;
    @Autowired
    private AdjustmentWorkService adjustmentWorkService;
    @Autowired
    private BaseInfoMapper BaseInfoMapper;

    @RequestMapping(value = "/pushStaffInfo")
    @Transactional
    public @ResponseBody String pushStaffInfo(@RequestBody JaHrEmployeeBean jaHrEmployeeBean) throws Exception {

        //        System.err.println(jaHrEmployeeBean.getName());
        //
        //        System.err.println(jaHrEmployeeBean.getJobNum());

        String allCodeTypeList = systemService.allList();
        List<CodeType> codeTypeList = null;
        if (allCodeTypeList != null) {
            JSONObject parseObject = JSONObject.parseObject(allCodeTypeList);
            if ((Integer) parseObject.get("code") == 200) {
                codeTypeList = JSONObject.parseArray(parseObject.getString("data"), CodeType.class);
            }
        }
        String allCode = systemService.getAllCode();
        List<Code> codeAllList = null;
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                codeAllList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String jobNum = jaHrEmployeeBean.getJobNum();

        if (StringUtils.isEmpty(jobNum) || "".equals(jobNum.trim())) {
            AppException.create(200015); //工号不能为空
        }

        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setStaffNo(jaHrEmployeeBean.getJobNum());
        baseInfo.setStaffName(jaHrEmployeeBean.getName());

        List<Code> sexList = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.MALE_OR_FEMALE)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        sexList.add(code);
                    }
                }
                break;
            }
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getSex())) {
            for (Code code : sexList) {
                if (code.getName().equals(jaHrEmployeeBean.getSex())) {
                    baseInfo.setSex(code.getRowId()); // 性别
                    break;
                }
            }
        }

        List<Code> allCodeList = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.IDENTITY_TYPE)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList.add(code);
                    }
                }
                break;
            }
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getIdType())) {
            for (Code code : allCodeList) {
                if (code.getName().equals(jaHrEmployeeBean.getIdType())) {
                    baseInfo.setIdentityTypeId(code.getRowId()); // 证件类型
                    break;
                }
            }
        }

        baseInfo.setIdentityNo(jaHrEmployeeBean.getGlobalId());
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getGlobalIdEndDate())) {
            Date globalIdEndDate = simpleDateFormat.parse(jaHrEmployeeBean.getGlobalIdEndDate());
            baseInfo.setIdentityValidDate(globalIdEndDate); // 身份证有效截至日期
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getBirthDate())) {
            Date birthDate = simpleDateFormat.parse(jaHrEmployeeBean.getBirthDate());
            baseInfo.setBirthdate(birthDate); //出生日期
        }

        //    	阴历阳历
        baseInfo.setMobile(jaHrEmployeeBean.getMovePhoneNum()); //移动电话
        baseInfo.setRegisteredResidence(jaHrEmployeeBean.getPlaceDomicile()); // 户籍地

        List<Code> allCodeList2 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.NATION)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList2.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getNation())) {
            for (Code code : allCodeList2) {
                if (code.getName().equals(jaHrEmployeeBean.getNation())) {
                    baseInfo.setNation(code.getRowId()); //民族
                    break;
                }
            }
        }

        baseInfo.setNativePlace(jaHrEmployeeBean.getNationPlace()); //籍贯

        List<Code> allCodeList3 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.NATIONALITY)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList3.add(code);
                    }
                }
                break;
            }
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getNationality())) {
            for (Code code : allCodeList3) {
                if (code.getName().equals(jaHrEmployeeBean.getNationality())) {
                    baseInfo.setNationality(code.getRowId()); //国籍
                    break;
                }
            }
        }

        List<Code> allCodeList4 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.MARITAL_STATUS)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList4.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getMaritalStatus())) {
            for (Code code : allCodeList4) {
                if (code.getName().equals(jaHrEmployeeBean.getMaritalStatus())) {
                    baseInfo.setMaritalStatus(code.getRowId());//婚姻状况 
                    break;
                }
            }
        }

        List<Code> allCodeList5 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.FERTILITY_STATUS)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList5.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getFertilityStatus())) {
            for (Code code : allCodeList5) {
                if (code.getName().equals(jaHrEmployeeBean.getFertilityStatus())) {
                    baseInfo.setFertilityStatus(code.getRowId()); //生育状况
                    break;
                }
            }
        }

        List<Code> allCodeList6 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.POLITICAL_STATUS)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList6.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getPoliticsStatus())) {
            for (Code code : allCodeList6) {
                if (code.getName().equals(jaHrEmployeeBean.getPoliticsStatus())) {
                    baseInfo.setPoliticalStatus(code.getRowId()); //政治面貌 
                    break;
                }
            }
        }

        baseInfo.setJobTitle(jaHrEmployeeBean.getJobTitle()); //职称情况
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getUnit())) {
            String baseJson = orgService.baseList();
            if (baseJson != null) {
                JSONObject parseObject = JSONObject.parseObject(baseJson);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Org> baseList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                    if (StringUtils.isNotEmpty(jaHrEmployeeBean.getUnit())) {
                        boolean isExist = false;
                        for (Org org : baseList) {
                            if (org.getBaseOrDeptName().equals(jaHrEmployeeBean.getUnit())) {
                                baseInfo.setBaseId(org.getRowId()); // 基地
                                isExist = true;
                            }
                        }
                        if (!isExist) {
                            AppException.create(200016); //基地没有匹配到
                        }
                    }
                }
            }
        } else {
            AppException.create(200013); //基地不能为空
        }

        List<Code> allCodeList7 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.FACTORY_CATEGORY)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList7.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getFactory())) {
            for (Code code : allCodeList7) {
                if (code.getName().equals(jaHrEmployeeBean.getFactory())) {
                    baseInfo.setFactoryCategory(code.getRowId()); // 厂别
                    break;
                }
            }
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getDepartment())) {
            String deptJson = orgService.getAllSonOrg(baseInfo.getBaseId());
            if (deptJson != null) {
                JSONObject parseObject = JSONObject.parseObject(deptJson);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Org> deptList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                    boolean isExist = false;
                    for (Org org : deptList) {
                        if (org.getBaseOrDeptName().equals(jaHrEmployeeBean.getDepartment())) {
                            baseInfo.setDeptId(org.getRowId()); //部门
                            isExist = true;
                        }
                    }
                    if (!isExist) {
                        AppException.create(200017); // 抛出异常 部门没有匹配到
                    }
                }
            }
        } else {
            AppException.create(200012); // 抛出异常 部门不能为空
        }

        List<Code> allCodeList8 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.OPERATING_POST)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList8.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getOperatingPost())) {
            for (Code code : allCodeList8) {
                if (code.getName().equals(jaHrEmployeeBean.getOperatingPost())) {
                    baseInfo.setOperatingPost(code.getRowId()); // 工作岗位
                    break;
                }
            }
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getEnterFactoryDate())) {
            Date entryDate = simpleDateFormat.parse(jaHrEmployeeBean.getEnterFactoryDate());
            baseInfo.setEntryDate(entryDate); // 进厂日期 
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getWorkDate())) {
            Date entryDate = simpleDateFormat.parse(jaHrEmployeeBean.getWorkDate());
            baseInfo.setFirstWorkingTime(entryDate); // 首次工作时间
        }

        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getDimissionDate())) {
            Date leaveDate = simpleDateFormat.parse(jaHrEmployeeBean.getDimissionDate());
            baseInfo.setLeaveDate(leaveDate); // 离职日期
        }

        baseInfo.setWorkCard(jaHrEmployeeBean.getCardNum()); // 卡号
        baseInfo.setClasses(jaHrEmployeeBean.getFlightInformation()); // 班次信息
        baseInfo.setDormitoryNo(jaHrEmployeeBean.getDormitoryNum()); // 宿舍号
        baseInfo.setLockerShoebox(jaHrEmployeeBean.getLockerNum()); // 更衣箱/鞋柜

        //        if(StringUtils.isNotEmpty(jaHrEmployeeBean.getMailAddress())) {
        //        	baseInfo.setEmail(jaHrEmployeeBean.getMailAddress().substring(0, jaHrEmployeeBean.getMailAddress().indexOf("@"))); //设置员工邮箱
        //        }

        //        baseInfo.setEmail(jaHrEmployeeBean.getMailAddress()); // 邮件地址

        List<Code> allCodeList9 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.IS_ON_JOB)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList9.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isEmpty(jaHrEmployeeBean.getState())) {
            for (Code code : allCodeList9) {
                if (code.getName().equals("在职")) {
                    baseInfo.setJobStatus(code.getRowId()); // 状态
                    break;
                }
            }
        } else {
            for (Code code : allCodeList9) {
                if (code.getName().equals(jaHrEmployeeBean.getState())) {
                    baseInfo.setJobStatus(code.getRowId()); // 状态
                    break;
                }
            }
        }

        List<Code> allCodeList10 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.LINES)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList10.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getJobLines())) {
            for (Code code : allCodeList10) {
                if (code.getName().equals(jaHrEmployeeBean.getJobLines())) {
                    baseInfo.setLines(code.getRowId()); // 线别
                    break;
                }
            }
        }

        baseInfo.setRemark(jaHrEmployeeBean.getJobRemark()); // 备注
        baseInfo.setSpeciality(jaHrEmployeeBean.getCertficateName()); //特长信息

        List<Code> allCodeList11 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.RECRUITMENT_CHANNEL)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList11.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getRecruitmentChannel())) {
            for (Code code : allCodeList11) {
                if (code.getName().equals(jaHrEmployeeBean.getRecruitmentChannel())) {
                    baseInfo.setRecruitmentChannel(code.getRowId()); //招聘渠道
                    break;
                }
            }
        }

        List<Code> allCodeList12 = new ArrayList<Code>();
        for (CodeType codeType : codeTypeList) {
            if (codeType.getCode().equals(StaffConstant.STAFF_CLASSIFY)) {
                for (Code code : codeAllList) {
                    if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                        allCodeList12.add(code);
                    }
                }
                break;
            }
        }
        if (StringUtils.isNotEmpty(jaHrEmployeeBean.getCategoryEmp())) {
            for (Code code : allCodeList12) {
                if (code.getName().equals(jaHrEmployeeBean.getCategoryEmp())) {
                    baseInfo.setStaffClassify(code.getCode()); //员工分类
                    break;
                }
            }
        }

        List<JaHrEmpSateInfoBean> users2 = jaHrEmployeeBean.getUsers2(); // 附属信息 (社会关系)
        List<RelationshipSocial> relationshipSocialList = new ArrayList<RelationshipSocial>();
        for (JaHrEmpSateInfoBean jaHrEmpSateInfoBean : users2) {
            RelationshipSocial relationshipSocial = new RelationshipSocial();
            relationshipSocial.setRelativeName(jaHrEmpSateInfoBean.getKinsfolkName()); // 亲属姓名
            if (StringUtils.isNotEmpty(jaHrEmpSateInfoBean.getSex())) {
                for (Code code : sexList) {
                    if (code.getName().equals(jaHrEmpSateInfoBean.getSex())) {
                        relationshipSocial.setSex(code.getRowId()); // 性别
                        break;
                    }
                }
            }

            if (StringUtils.isNotEmpty(jaHrEmpSateInfoBean.getBirthDate())) {
                Date birthDate2 = simpleDateFormat.parse(jaHrEmpSateInfoBean.getBirthDate());
                relationshipSocial.setRsBirthday(birthDate2); // 出生日期
            }

            relationshipSocial.setRelationship(jaHrEmpSateInfoBean.getRelationship()); // 与本人关系
            relationshipSocial.setCompany(jaHrEmpSateInfoBean.getPlaceUnit()); //所在单位
            relationshipSocial.setPermanentAddress(jaHrEmpSateInfoBean.getAdress()); //常住地址
            relationshipSocial.setPosition(jaHrEmpSateInfoBean.getDuty()); //职务
            relationshipSocial.setContact(jaHrEmpSateInfoBean.getPhoneNum()); // 联系电话
            //    		relationshipSocial.setStaffId(staffId);  设置员工id

            //TODO 插入社会关系数据
            relationshipSocialList.add(relationshipSocial);

        }

        List<JaHrEmpEmerInfoBean> users3 = jaHrEmployeeBean.getUsers3(); // 紧急联系人 (紧急联系人)
        List<ContactEmergency> contactEmergencyList = new ArrayList<ContactEmergency>();
        for (JaHrEmpEmerInfoBean jaHrEmpEmerInfoBean : users3) {
            ContactEmergency contactEmergency = new ContactEmergency();
            contactEmergency.setContactName(jaHrEmpEmerInfoBean.getName()); // 联系人姓名
            contactEmergency.setRelationship(jaHrEmpEmerInfoBean.getRelationship()); // 与本人关系
            contactEmergency.setMobile(jaHrEmpEmerInfoBean.getPhoneNum()); // 电话号码
            contactEmergency.setWechatQq(jaHrEmpEmerInfoBean.getWechatQqNum()); // 微信/qq
            //    		contactEmergency.setStaffId(staffId); 设置员工id
            //TODO 插入紧急联系人数据
            contactEmergencyList.add(contactEmergency);
        }

        List<JaHrEmpEducationExperienceBean> users4 = jaHrEmployeeBean.getUsers4(); // 教育经历
        List<Education> educationList = new ArrayList<Education>();
        for (JaHrEmpEducationExperienceBean jaHrEmpEducationExperienceBean : users4) {
            Education education = new Education();
            education.setSchoolName(jaHrEmpEducationExperienceBean.getSchoolName()); // 毕业学校名称
            education.setMajorName(jaHrEmpEducationExperienceBean.getSpecialty()); // 专业/方向
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getSchoolStartDate())) {
                Date entranceTime = simpleDateFormat.parse(jaHrEmpEducationExperienceBean.getSchoolStartDate());
                education.setEntranceTime(entranceTime); // 入学时间
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getSchoolEndDate())) {
                Date graduateTime = simpleDateFormat.parse(jaHrEmpEducationExperienceBean.getSchoolEndDate());
                education.setGraduateTime(graduateTime); // 毕业时间
            }

            List<Code> allCodeList13 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.EDUCATION)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList13.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getDegree())) {
                for (Code code : allCodeList13) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getDegree())) {
                        education.setEducation(code.getRowId()); // 学位
                        break;
                    }
                }
            }

            List<Code> allCodeList14 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.DEGREE)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList14.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getEducation())) {
                for (Code code : allCodeList14) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getEducation())) {
                        education.setDegree(code.getRowId()); // 学历
                        break;
                    }
                }
            }

            List<Code> allCodeList15 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.GRADUATION_SITUATION)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList15.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getGraduationTitle())) {
                for (Code code : allCodeList15) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getGraduationTitle())) {
                        education.setGraduationSituation(code.getRowId()); // 毕业情况
                        break;
                    }
                }
            }

            List<Code> allCodeList16 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.SCHOOLING_DOCUMENTS_TYPE)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList16.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getEducationCertificate())) {
                for (Code code : allCodeList16) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getEducationCertificate())) {
                        education.setSchoolingDocumentsType(code.getRowId()); // 所获得学历证书类型
                        break;
                    }
                }
            }

            List<Code> allCodeList17 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.DEGREE_COUNTRY)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList17.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getDegreeAuthorizedCountry())) {
                for (Code code : allCodeList17) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getDegreeAuthorizedCountry())) {
                        education.setDegreeCountry(code.getRowId()); // 学位授权国家
                        break;
                    }
                }
            }

            List<Code> allCodeList18 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.DEGREE_COUNTRY)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList18.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getEducationWay())) {
                for (Code code : allCodeList18) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getEducationWay())) {
                        education.setLearningStyle(code.getRowId()); // 学习方式 
                        break;
                    }
                }
            }

            List<Code> allCodeList19 = new ArrayList<Code>();
            for (CodeType codeType : codeTypeList) {
                if (codeType.getCode().equals(StaffConstant.YES_OR_NO)) {
                    for (Code code : codeAllList) {
                        if (code.getTypeId().intValue() == codeType.getRowId().intValue()) {
                            allCodeList19.add(code);
                        }
                    }
                    break;
                }
            }
            if (StringUtils.isNotEmpty(jaHrEmpEducationExperienceBean.getHighDegree()) && StringUtils.isNoneEmpty(jaHrEmpEducationExperienceBean.getHighEducation())) {
                for (Code code : allCodeList19) {
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getHighDegree())) {
                        education.setIsHighestDegree(code.getRowId()); // 是否最高学位
                    }
                    if (code.getName().equals(jaHrEmpEducationExperienceBean.getHighEducation())) {
                        education.setIsHighestEducation(code.getRowId()); // 是否最高学位
                    }

                }
            }

            //    		Education.setStaffId(staffId); 设置员工id
            //TODO 插入教育经历数据
            educationList.add(education);
        }

        List<JaHrEmpSoclalHistoryInfoBean> users5 = jaHrEmployeeBean.getUsers5();
        List<OuterExperience> outerExperienceList = new ArrayList<OuterExperience>();
        for (JaHrEmpSoclalHistoryInfoBean jaHrEmpSoclalHistoryInfoBean : users5) {
            OuterExperience outerExperience = new OuterExperience();
            outerExperience.setWorkUnit(jaHrEmpSoclalHistoryInfoBean.getCorpName()); // 工作单位
            outerExperience.setDeptName(jaHrEmpSoclalHistoryInfoBean.getOfficeDepartment()); // 任职部门
            outerExperience.setPositionName(jaHrEmpSoclalHistoryInfoBean.getOfficeJob()); // 任职职衔
            if (StringUtils.isNotEmpty(jaHrEmpSoclalHistoryInfoBean.getJobStartDate())) {
                Date startDate = simpleDateFormat.parse(jaHrEmpSoclalHistoryInfoBean.getJobStartDate());
                outerExperience.setStartDate(startDate); // 开始时间
            }
            if (StringUtils.isNotEmpty(jaHrEmpSoclalHistoryInfoBean.getJobEndDate())) {
                Date endDate = simpleDateFormat.parse(jaHrEmpSoclalHistoryInfoBean.getJobEndDate());
                outerExperience.setEndDate(endDate); // 结束时间
            }
            outerExperience.setProofPersonAndDuty(jaHrEmpSoclalHistoryInfoBean.getCertifierName()); // 证明人
            outerExperience.setProofContact(jaHrEmpSoclalHistoryInfoBean.getCertifierPhoneNum()); // 证明人联系方式
            outerExperience.setSalary(jaHrEmpSoclalHistoryInfoBean.getSalary()); // 薪酬状况
            //    		outerExperience.setStaffId(staffId);
            //TODO 插入社会履历数据
            outerExperienceList.add(outerExperience);
        }

        List<JaHrEmpCommunicationInfoBean> users6 = jaHrEmployeeBean.getUsers6();
        List<Address> addressList = new ArrayList<Address>();
        if (users6 != null && users6.size() > 0) {
            JaHrEmpCommunicationInfoBean jaHrEmpCommunicationInfoBean = users6.get(0);
            String workEmail = jaHrEmpCommunicationInfoBean.getWorkEmail();
            if (StringUtils.isNotEmpty(workEmail)) {
                baseInfo.setEmail(workEmail); // 设置员工邮箱
            }
        }

        for (JaHrEmpCommunicationInfoBean jaHrEmpCommunicationInfoBean : users6) {
            Address address = new Address();
            address.setWorkPhone(jaHrEmpCommunicationInfoBean.getWorkPhoneNum()); // 办公电话 
            address.setMobile(jaHrEmpCommunicationInfoBean.getMovePhoneNum()); // 移动电话
            address.setHomePhone(jaHrEmpCommunicationInfoBean.getFamilyPhoneNum()); // 家庭电话

            String workEmail = jaHrEmpCommunicationInfoBean.getWorkEmail();
            if (StringUtils.isNotEmpty(workEmail)) {
                address.setEmailWork(workEmail.substring(0, workEmail.indexOf("@"))); // 电子邮箱/工作
            }

            address.setHomeAddress(jaHrEmpCommunicationInfoBean.getFamilyAddress()); // 家庭地址
            address.setEmailPersonal(jaHrEmpCommunicationInfoBean.getPersonallyEmail()); // 电子邮箱/个人
            //    		address.setStaffId(staffId);
            //TODO 插入员工通讯数据
            addressList.add(address);
        }

        List<JaHrempKinsfolkRelationInfoBean> users7 = jaHrEmployeeBean.getUsers7();
        List<RelationshipInner> relationshipInnerList = new ArrayList<RelationshipInner>();
        for (JaHrempKinsfolkRelationInfoBean jaHrempKinsfolkRelationInfoBean : users7) {
            RelationshipInner relationshipInner = new RelationshipInner();
            relationshipInner.setRelativeName(jaHrempKinsfolkRelationInfoBean.getKinsfolkName()); // 亲属姓名
            relationshipInner.setRelativeId(jaHrempKinsfolkRelationInfoBean.getJobNum()); // 亲属工号
            relationshipInner.setRelationship(jaHrempKinsfolkRelationInfoBean.getRelationship()); // 与本人关系
            relationshipInner.setCompany(jaHrempKinsfolkRelationInfoBean.getUnit()); // 所在单位
            relationshipInner.setDept(jaHrempKinsfolkRelationInfoBean.getDepartment()); //  所在部门
            relationshipInner.setContact(jaHrempKinsfolkRelationInfoBean.getPhoneNum()); // 联系方式
            //    		RelationshipInner.setStaffId(staffId);
            //TODO 插入内部亲属关系数据
            relationshipInnerList.add(relationshipInner);
        }

        List<JaHrEmpSkllCertficateInfoBean> users8 = jaHrEmployeeBean.getUsers8();
        List<RecordProfession> recordProfessionList = new ArrayList<RecordProfession>();
        for (JaHrEmpSkllCertficateInfoBean jaHrEmpSkllCertficateInfoBean : users8) {
            RecordProfession recordProfession = new RecordProfession();
            recordProfession.setCertificateName(jaHrEmpSkllCertficateInfoBean.getCertficateName()); // 证书名称
            if (StringUtils.isNotEmpty(jaHrEmpSkllCertficateInfoBean.getCertficateStartDate())) {
                Date certificateStartDate = simpleDateFormat.parse(jaHrEmpSkllCertficateInfoBean.getCertficateStartDate());
                recordProfession.setCertificateStartDate(certificateStartDate); // 证书发放日期
            }
            if (StringUtils.isNotEmpty(jaHrEmpSkllCertficateInfoBean.getCertficateEndDate())) {
                Date certificateEndDate = simpleDateFormat.parse(jaHrEmpSkllCertficateInfoBean.getCertficateEndDate());
                recordProfession.setCertificateEndDate(certificateEndDate); // 证书有效期限
            }
            recordProfession.setGrantOrg(jaHrEmpSkllCertficateInfoBean.getCertficateOrganization()); // 发放机构
            recordProfession.setGrantLevel(jaHrEmpSkllCertficateInfoBean.getCertficateLevel()); // 证书级别
            //    		recordProfession.setStaffId(staffId);
            //TODO 插入所获技能证书数据
            recordProfessionList.add(recordProfession);
        }

        List<JaHrEmpHonorInfoBean> users9 = jaHrEmployeeBean.getUsers9();
        List<Award> awardList = new ArrayList<Award>();
        for (JaHrEmpHonorInfoBean jaHrEmpHonorInfoBean : users9) {
            Award award = new Award();
            award.setAwardName(jaHrEmpHonorInfoBean.getHonorName()); // 荣誉名称
            if (StringUtils.isNotEmpty(jaHrEmpHonorInfoBean.getHonorDate())) {
                Date credentialIssueDate = simpleDateFormat.parse(jaHrEmpHonorInfoBean.getHonorDate());
                award.setCredentialIssueDate(credentialIssueDate); // 证书颁发日期
            }
            award.setCredentialIssueOrg(jaHrEmpHonorInfoBean.getHonorOrganization()); // 证书颁发机构
            award.setRewardForm(jaHrEmpHonorInfoBean.getRewardForm()); // 奖励形式
            //    		award.setStaffId(staffId);
            //TODO 插入所获荣誉情况数据
            awardList.add(award);
        }

        List<JaHrEmpProjectInfoBean> users10 = jaHrEmployeeBean.getUsers10();
        List<ProjectExperience> projectExperienceList = new ArrayList<ProjectExperience>();
        for (JaHrEmpProjectInfoBean jaHrEmpProjectInfoBean : users10) {
            ProjectExperience projectExperience = new ProjectExperience();
            projectExperience.setProjectName(jaHrEmpProjectInfoBean.getItemsName()); // 项目名称
            projectExperience.setFillPost(jaHrEmpProjectInfoBean.getHoldPost()); // 担任职务
            projectExperience.setResponsibleContent(jaHrEmpProjectInfoBean.getResponsibleContent()); // 负责内容
            if (StringUtils.isNotEmpty(jaHrEmpProjectInfoBean.getItemsStartDate())) {
                Date startDate = simpleDateFormat.parse(jaHrEmpProjectInfoBean.getItemsStartDate());
                projectExperience.setStartDate(startDate); // 项目开始时间
            }
            if (StringUtils.isNotEmpty(jaHrEmpProjectInfoBean.getItemsEndDate())) {
                Date endDate = simpleDateFormat.parse(jaHrEmpProjectInfoBean.getItemsEndDate());
                projectExperience.setEndDate(endDate); // 项目结束时间
            }
            projectExperience.setProjectResult(jaHrEmpProjectInfoBean.getItemsResult()); // 项目结果
            //    		ProjectExperience.setStaffId(staffId);
            //TODO 插入查找数据数据
            projectExperienceList.add(projectExperience);

        }

        List<JaHrEmpPromoteInfoBean> users11 = jaHrEmployeeBean.getUsers11();
        List<AdjustmentWork> adjustmentWorkList = new ArrayList<AdjustmentWork>();
        for (JaHrEmpPromoteInfoBean jaHrEmpPromoteInfoBean : users11) {
            AdjustmentWork adjustmentWork = new AdjustmentWork();
            if (StringUtils.isNotEmpty(jaHrEmpPromoteInfoBean.getStartDate())) {
                Date changeDate = simpleDateFormat.parse(jaHrEmpPromoteInfoBean.getStartDate());
                adjustmentWork.setChangeDate(changeDate); // 开始时间
            }
            if (StringUtils.isNotEmpty(jaHrEmpPromoteInfoBean.getEndDate())) {
                Date endDate = simpleDateFormat.parse(jaHrEmpPromoteInfoBean.getEndDate());
                adjustmentWork.setRealEndTime(endDate); // 结束时间
            }
            adjustmentWork.setNewBase(jaHrEmpPromoteInfoBean.getDase()); // 任职基地 
            adjustmentWork.setNewDept(jaHrEmpPromoteInfoBean.getDepartment()); // 任职部门
            adjustmentWork.setNewPosition(jaHrEmpPromoteInfoBean.getJobPosition()); // 职衔
            adjustmentWork.setNewRank(jaHrEmpPromoteInfoBean.getJobRank()); // 职级
            adjustmentWork.setNewGrade(jaHrEmpPromoteInfoBean.getJobEtc()); // 职等/赋值名称
            adjustmentWork.setAmountCompensation(jaHrEmpPromoteInfoBean.getJobName()); // 职称 需要新增
            adjustmentWork.setChangeType(Constant.INNER_MOBILIZATION);
            //                		adjustmentWork.setStaffId(staffId);
            //TODO 插入内部晋升履历数据
            adjustmentWorkList.add(adjustmentWork);
        }

        boolean checkStaffNo = baseInfoService.checkStaffNo(jaHrEmployeeBean.getJobNum());
        if (checkStaffNo) {
            // 更新数据
            BaseInfo baseInfoByStaffNo = baseInfoService.getBaseInfoByStaffNo(jaHrEmployeeBean.getJobNum());
            baseInfo.setRowId(baseInfoByStaffNo.getRowId());
            baseInfo.setLastUpdateBy(baseInfo.getRowId());;
            baseInfo.setLastUpdateTime(new Date());
            Long staffId = baseInfoByStaffNo.getRowId();
            BaseInfoMapper.updateBaseInfo(baseInfo); // 更新员工表

            if (relationshipSocialList.size() > 0) {
                relationshipSocialService.delAllByStaffId(staffId); //删除原始数据
                for (RelationshipSocial relationshipSocial : relationshipSocialList) {
                    relationshipSocial.setStaffId(staffId);
                    relationshipSocialService.addRelationshipSocial(relationshipSocial);
                }
            }

            if (contactEmergencyList.size() > 0) {
                contactEmergencyService.delAllStaffId(staffId); //删除原始数据
                for (ContactEmergency contactEmergency : contactEmergencyList) {
                    contactEmergency.setStaffId(staffId);
                    contactEmergencyService.addContactEmergency(contactEmergency);
                }
            }

            if (educationList.size() > 0) {
                educationService.delAllByStaffId(staffId); //删除原始数据
                for (Education education : educationList) {
                    education.setStaffId(staffId);
                    educationService.addEducation(education);
                }
            }

            if (outerExperienceList.size() > 0) {
                outerExperienceService.delAllByStaffId(staffId);
                for (OuterExperience outerExperience : outerExperienceList) {
                    outerExperience.setStaffId(staffId);
                    outerExperienceService.addOuterExperience(outerExperience);
                }
            }

            if (addressList.size() > 0) {
                addressService.delAllByStaffId(staffId);
                for (Address address : addressList) {
                    address.setStaffId(staffId);
                    addressService.addAddress(address);
                }
            }

            if (relationshipInnerList.size() > 0) {
                relationshipInnerService.delAllByStaffId(staffId);
                for (RelationshipInner relationshipInner : relationshipInnerList) {
                    relationshipInner.setStaffId(staffId);
                    relationshipInnerService.addRelationshipInner(relationshipInner);
                }
            }

            if (recordProfessionList.size() > 0) {
                recordProfessionService.delAllByStaffId(staffId);
                for (RecordProfession recordProfession : recordProfessionList) {
                    recordProfession.setStaffId(staffId);
                    recordProfessionService.add(recordProfession);
                }
            }

            if (awardList.size() > 0) {
                awardService.delAllByStaffId(staffId);
                for (Award award : awardList) {
                    award.setStaffId(staffId);
                    awardService.add(award);
                }
            }

            if (projectExperienceList.size() > 0) {
                projectExperienceService.delAllByStaffId(staffId);
                for (ProjectExperience projectExperience : projectExperienceList) {
                    projectExperience.setStaffId(staffId);
                    projectExperienceService.addProjectExperience(projectExperience);
                }
            }

            if (adjustmentWorkList.size() > 0) {
                adjustmentWorkService.delAllStaffId(staffId);
                for (AdjustmentWork adjustmentWork : adjustmentWorkList) {
                    adjustmentWork.setStaffId(staffId);
                    adjustmentWork.setCreatedBy(staffId);
                    adjustmentWorkMapper.addStaffMove(adjustmentWork);
                }
            }

        } else {
            // 插入数据
            baseInfoService.addBaseInfo(baseInfo);
            baseInfo.setCreatedBy(baseInfo.getRowId());
            baseInfo.setCreatedTime(new Date()); // 创建时间
            baseInfoService.update(baseInfo);

            Long staffId = baseInfo.getRowId();
            for (RelationshipSocial relationshipSocial : relationshipSocialList) {
                relationshipSocial.setStaffId(staffId);
                relationshipSocialService.addRelationshipSocial(relationshipSocial);
            }

            for (ContactEmergency contactEmergency : contactEmergencyList) {
                contactEmergency.setStaffId(staffId);
                contactEmergencyService.addContactEmergency(contactEmergency);
            }

            for (Education education : educationList) {
                education.setStaffId(staffId);
                educationService.addEducation(education);
            }

            for (OuterExperience outerExperience : outerExperienceList) {
                outerExperience.setStaffId(staffId);
                outerExperienceService.addOuterExperience(outerExperience);
            }

            for (Address address : addressList) {
                address.setStaffId(staffId);
                addressService.addAddress(address);
            }

            for (RelationshipInner relationshipInner : relationshipInnerList) {
                relationshipInner.setStaffId(staffId);
                relationshipInnerService.addRelationshipInner(relationshipInner);
            }

            for (RecordProfession recordProfession : recordProfessionList) {
                recordProfession.setStaffId(staffId);
                recordProfessionService.add(recordProfession);
            }

            for (Award award : awardList) {
                award.setStaffId(staffId);
                awardService.add(award);
            }

            for (ProjectExperience projectExperience : projectExperienceList) {
                projectExperience.setStaffId(staffId);
                projectExperienceService.addProjectExperience(projectExperience);
            }

            for (AdjustmentWork adjustmentWork : adjustmentWorkList) {
                adjustmentWork.setStaffId(staffId);
                adjustmentWork.setCreatedBy(baseInfo.getRowId()); //设置创建人
                adjustmentWorkMapper.addStaffMove(adjustmentWork);
            }

            List<String> userList = new ArrayList<>();
            userList.add(baseInfo.getStaffNo());
            systemService.batchAdd(userList); // 生成用用户

        }
        
        return getSuccessJson();

    }

    /**
     * 查询列表
     * 
     * @author natyu
     * @date 2018年7月5日 下午12:54:53
     * @param form
     * @return
     */
    @RequestMapping(value = "/list")
    public String searchStfBaseInfoList(SearchBaseInfoForm form) {
        if (form.getStaffNo() != null && form.getStaffNo() != "") {
            form.setStaffNo(form.getStaffNo().trim());
        }
        if (form.getStaffName() != null && form.getStaffName() != "") {
            form.setStaffName(form.getStaffName().trim());
        }
        if (form.getIdentityNo() != null && form.getIdentityNo() != "") {
            form.setIdentityNo(form.getIdentityNo().trim());
        }

        List<Long> staffIdList = RequestContext.get().getRoleAuthorityStaffIdList();
        form.setStaffIdList(staffIdList);

        PageListVO pageListVO = baseInfoService.getStfBaseInfoList(form);
        List<BaseInfo> list = pageListVO.getList();
        if (list != null && list.size() > 0) {
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
                    list = super.translateIdToName(list, allCodeList,
                        new String[] {"sex,rowId,name", "identityTypeId,rowId,name", "maritalStatus,rowId,name", "fertilityStatus,rowId,name", "nationality,rowId,name", "nation,rowId,name",
                            "politicalStatus,rowId,name", "staffType,rowId,name", "socialSecurityType,rowId,name", "workType,rowId,name", "jobStatus,rowId,name", "isBlacklist,rowId,name",
                            "lines,rowId,name", "staffClassify,rowId,name", "operatingPost,rowId,name", "factoryCategory,rowId,name", "recruitmentChannel,rowId,name"});
                }
            }
            String codeList = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
            if (codeList != null) {
                JSONObject parseObject = JSONObject.parseObject(codeList);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    list = super.translateIdToName(list, allCodeList, new String[] {"staffClassify,code,name"});
                }
            }
            String allOrg = orgService.getAllOrg();
            if (allOrg != null) {
                JSONObject parseObject = JSONObject.parseObject(allOrg);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                    list = super.translateIdToName(list, allOrgList, new String[] {"deptId,rowId,baseOrDeptName"});
                    list = super.translateIdToName(list, allOrgList, new String[] {"costCenter,rowId,baseOrDeptName"});
                    list = super.translateIdToName(list, allOrgList, new String[] {"baseId,rowId,baseOrDeptName"});
                }
            }
            pageListVO.setList(list);
        }
        return getSuccessJson(pageListVO);
    }

    /**
     * 获取信息
     * 
     * @author natyu
     * @date 2018年7月5日 下午1:20:24
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/get/{id}")
    public String getStfBaseInfo(@PathVariable Long id) {
        if (id == null) {
            return getFailJson();
        }
        BaseInfo baseInfo = baseInfoService.getBaseInfoByStaffId(id);

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
                list = super.translateIdToName(list, allCodeList,
                    new String[] {"sex,rowId,name", "identityTypeId,rowId,name", "maritalStatus,rowId,name", "fertilityStatus,rowId,name", "nationality,rowId,name", "nation,rowId,name",
                        "politicalStatus,rowId,name", "staffType,rowId,name", "socialSecurityType,rowId,name", "workType,rowId,name", "jobStatus,rowId,name", "isBlacklist,rowId,name",
                        "lines,rowId,name", "staffClassify,rowId,name", "operatingPost,rowId,name", "factoryCategory,rowId,name", "recruitmentChannel,rowId,name"});
            }
        }
        String codeList = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
        if (codeList != null) {
            JSONObject parseObject = JSONObject.parseObject(codeList);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"staffClassify,code,name"});
            }
        }
        String allOrg = orgService.getAllOrg();
        if (allOrg != null) {
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                list = super.translateIdToName(list, allOrgList, new String[] {"deptId,rowId,baseOrDeptName"});
                list = super.translateIdToName(list, allOrgList, new String[] {"baseId,rowId,baseOrDeptName"});
                list = super.translateIdToName(list, allOrgList, new String[] {"costCenter,rowId,baseOrDeptName"});
            }
        }
        return getSuccessJson(list.get(0));
    }

    @RequestMapping(value = "/queryBaseInfo/{deptId}")
    public String queryBaseInfo(@PathVariable Long deptId) {
        Integer baseinfo = baseInfoService.queryBaseInfo(deptId);
        return getSuccessJson(baseinfo);

    }

    /**
     * 根据staffNo/staffName/deptId/identityNo查询用户信息列表
     * 
     * @author natyu
     * @date 2018年7月23日 上午9:48:47
     * @param form
     * @return
     */
    @PostMapping(value = "/queryBaseInfoForParams")
    public String queryBaseInfoForParams(@RequestBody SearchBaseInfoForm form) {
        List<BaseInfo> list = baseInfoService.queryBaseInfoForParams(form);
        return getSuccessJson(list);
    }

    /**
     * 删除
     * 
     * @author natyu
     * @date 2018年7月5日 下午1:20:10
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/del/{id}")
    public String delStfBaseInfo(@PathVariable Long id) {
        baseInfoService.logicDelBaseInfoByStaffId(id);
        return getSuccessJson();
    }

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月30日 上午11:50:45
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public String addStfBaseInfo(BaseInfo baseInfo) throws Exception {
        baseInfoService.addBaseInfo(baseInfo);
        return getSuccessJson();
    }

    /**
     * 修改操作
     * 
     * @author natyu
     * @date 2018年7月30日 上午11:50:38
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    public String updateStfBaseInfo(BaseInfo baseInfo) throws Exception {
        baseInfoService.updateStfBaseInfo(baseInfo);
        return getSuccessJson();
    }

    /**
     * 导入
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:20:12
     * @param request
     * @param response
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importExcel")
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ImportBaseInfoForm> list = new ArrayList<ImportBaseInfoForm>();
        ImportExcelUtils.export(request, ImportBaseInfoForm.class, list);
        List<ImportBaseInfoForm> exportErrList = baseInfoService.importExcel(list);
        if (exportErrList == null || exportErrList.isEmpty()) {
            responseMsg(response);
        } else {
            Long redisKey = new Date().getTime();
            redisService.writeValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey, exportErrList, Constant.sessionTimeout * 60);
            request.getSession().setAttribute("excelContent", JSONArray.toJSON(exportErrList).toString());
            responseExcel(response, redisKey);

            System.err.println(redisService.readValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey));
        }
    }

    /**
     * 导出错误数据
     * 
     * @author natyu
     * @date 2018年7月27日 下午3:18:17
     * @param request
     * @param response
     * @param form
     * @param lang
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportErrExcel")
    public void exportErrExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang, @RequestParam String redisKey) throws Exception {
        System.err.println(redisService.readValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey));
        // 查询数据
        List<ImportBaseInfoForm> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey)), ImportBaseInfoForm.class);
        baseInfoService.exportErrExcel(response, list, lang);
    }

    /**
     * 导出Excel
     * 
     * @author natyu
     * @date 2018年7月27日 下午3:18:17
     * @param request
     * @param response
     * @param form
     * @param lang
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response, SearchBaseInfoForm form, @RequestParam String lang) throws Exception {
        // 查询数据
        List<BaseInfo> list = baseInfoService.queryExportExcelInfo(form);

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
                list = super.translateIdToName(list, gradeList, new String[] {"gradeId,rowId,gradeName", "gradeId,rowId,postAssignment"});
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
                list = super.translateIdToName(list, allCodeList,
                    new String[] {"sex,rowId,name", "identityTypeId,rowId,name", "maritalStatus,rowId,name", "fertilityStatus,rowId,name", "nationality,rowId,name", "nation,rowId,name",
                        "politicalStatus,rowId,name", "staffType,rowId,name", "socialSecurityType,rowId,name", "workType,rowId,name", "jobStatus,rowId,name", "isBlacklist,rowId,name",
                        "lines,rowId,name", "staffClassify,rowId,name", "operatingPost,rowId,name", "factoryCategory,rowId,name", "recruitmentChannel,rowId,name"});
            }
        }

        String codeList = systemService.getByTpye(StaffConstant.STAFF_CLASSIFY);
        if (codeList != null) {
            JSONObject parseObject = JSONObject.parseObject(codeList);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"staffClassify,code,name"});
            }
        }

        String allOrg = orgService.getAllOrg();
        if (allOrg != null) {
            JSONObject parseObject = JSONObject.parseObject(allOrg);
            if ((Integer) parseObject.get("code") == 200) {
                List<Org> allOrgList = JSONObject.parseArray(parseObject.getString("data"), Org.class);
                list = super.translateIdToName(list, allOrgList, new String[] {"deptId,rowId,baseOrDeptName", "costCenter,rowId,baseOrDeptName", "baseId,rowId,baseOrDeptName"});
                //                list = super.translateIdToName(list, allOrgList, new String[] {"costCenter,rowId,baseOrDeptName"});
                //                list = super.translateIdToName(list, allOrgList, new String[] {"baseId,rowId,baseOrDeptName"});
            }
        }

        baseInfoService.exportExcel(response, list, lang);
        return getSuccessJson();
    }

    /**
     * 查询用户列表信息根据staffIds
     * 
     * @author natyu
     * @date 2018年7月25日 上午11:48:31
     * @param staffIds
     * @return
     */
    @RequestMapping(value = "/getBaseInfoByStaffIdList")
    public List<BaseInfo> getBaseInfoByIds(List<Long> staffIds) {
        return baseInfoService.getBaseInfoByIds(staffIds);
    }

    /**
     * 员工原属于sourceId部门修改成属于targetId部门
     * @param sourceId
     * @param targetId
     * @return
     */
    @RequestMapping("/orgMerge/{sourceId}/{targetId}")
    public String orgMerge(@PathVariable("sourceId") Long sourceId, @PathVariable("targetId") Long targetId) {
        if (baseInfoService.orgMerge(sourceId, targetId) > 0) {
            return getSuccessJson();
        }
        return getSuccessJson();
    }

    /**
     * 将员工部门属于dept_Ids下的员工deptId设为null
     * @param dept_Ids
     * @return
     */
    @RequestMapping("/delDept_ids")
    public String delDept_ids(@RequestParam("dept_Ids") List<Long> dept_Ids) {
        if (baseInfoService.delDept_ids(dept_Ids) > 0) {
            return getSuccessJson();
        }
        return getSuccessJson();
    }

    /**
     * 文件下载
     * 
     * @author natyu
     * @date 2018年7月27日 上午10:37:37
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }

    /**
     * 校验工号是否存在
     * 
     * @author natyu
     * @date 2018年7月30日 上午11:40:57
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/checkStaffNo/{staffNo}")
    public boolean checkStaffNo(@PathVariable("staffNo") String staffNo) {
        return baseInfoService.checkStaffNo(staffNo);
    }

    /**
     * 根据身份证查询入职次数 此处为功能说明
     * 
     * @author jinlin.tang
     * @date 2018年8月3日 下午5:25:57
     * @param idNo
     * @return
     */
    @RequestMapping(value = "/getCountByIdNo/{idNo}")
    public int getCountByIdNo(@PathVariable("idNo") String idNo) {
        return baseInfoService.getCountByIdNo(idNo);
    }

    /**
     * 获取员工编号
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/getStaffNo/{baseId}")
    public String getStaffNo(@PathVariable("baseId") Long baseId) {
        Map<String, String> staffNOMap = baseInfoService.getStaffNo(baseId);
        if (staffNOMap == null) {
            AppException.create(200002);
        }
        return getSuccessJson(staffNOMap);
    }

    /**
     * @Author jsl
     * @Date 根据user_name 获取员工信息 2018/8/10
     * @Description
     **/

    @RequestMapping(value = "/getBaseInfo/{userName}")
    public String getBaseInfo(@PathVariable("userName") String userName) {
        BaseInfo baseInfo = baseInfoService.getBaseInfro(userName);
        return getSuccessJson(baseInfo);
    }

    @RequestMapping(value = "/getBaseInfoByStaffNo/{staffNo}")
    public String getBaseInfoByStaffNo(@PathVariable("staffNo") String staffNo) {
        BaseInfo baseInfoByStaffNo = baseInfoService.getBaseInfoByStaffNo(staffNo);
        return getSuccessJson(baseInfoByStaffNo);
    }

    /**
     * 查询该职衔下有多少个员工 包括离职员工
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/queryCountBaseInfoByPositionId/{positionId}")
    public String queryCountBaseInfoByPositionId(@PathVariable("positionId") Long positionId) {
        Integer queryCountBaseInfoByPositionId = baseInfoService.queryCountBaseInfoByPositionId(positionId);
        return getSuccessJson(queryCountBaseInfoByPositionId);
    }

    /*
     * 查询是否有离职人员
     * 
     * @author natyu
     * @date 2018年8月14日 下午7:05:33
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/checkHasLeave/{ids}")
    public String checkHasLeave(@PathVariable("ids") String ids) throws Exception {
        try {
            if (StringUtils.isNotEmpty(ids)) {
                String[] idsArr = ids.split(",");
                return getSuccessJson(baseInfoService.checkHasLeave(idsArr));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return getSuccessJson();
    }

    /**
     * 返回每个部门的在职人数map集合
     * @return //查询有员工的部门下的在职人数 [{"deptId": 161,"count": 4},{"deptId": 169,"count": 15}]
     */
    @RequestMapping(value = "/queryCountBaseInfoByDeptId")
    public List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId() {
        List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId = baseInfoService.queryCountBaseInfoByDeptId();
        return queryCountBaseInfoByDeptId;
    }

    /**
     * @Author jsl
     * @Date 12:15 2018/8/25
     * @Description 查询销售人员列表
     **/
    @RequestMapping(value = "/getSalePersonList")
    public String getSalePersonList() {
        List<BaseSelect> list = baseInfoService.getSalePersonList();
        return getSuccessJson(list);
    }

    /**
     * 根据row_id 获取员工姓名和工号
     * @Author created by barrett in 2018/9/5 09:09
     */
    @RequestMapping(value = "/getUserNameJobNumberById/{id}")
    public String getUserNameJobNumberById(@PathVariable Long id) {
        BaseInfo baseInfo = baseInfoService.getUserNameJobNumberById(id);
        return getSuccessJson(baseInfo);
    }

    /**
     * 根据组织架构ids 获取员工id
     * 
     * @author natyu
     * @date 2018年9月17日 下午3:48:12
     * @param orgIds
     * @return
     */
    @RequestMapping("/getStaffIdByOrgIds")
    public String getStaffIdByOrgIds(@RequestParam("list") List<Long> list) {
        List<Long> staffIds = baseInfoService.getStaffIdByOrgIds(list);
        return getSuccessJson(staffIds);
    }

    /**
     * 查询当前登录人数据权限对应所有员工的工号
     * @return
     */
    @RequestMapping("/getStaffNoBystaffIds")
    public String getStaffNoBystaffIds(@RequestParam("token") String token) {
        RedisService redisService = ContextUtils.getBean(RedisService.class);
        List<Long> staffIdList = redisService.readValue(Constant.dataAuthStaff + token, List.class);
        List<String> staffNoBystaffIds = baseInfoService.getStaffNoBystaffIds(staffIdList);
        return getSuccessJson(staffNoBystaffIds);
    }

    /**
     * 导入
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:20:12
     * @param request
     * @param response
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importPositionExcel")
    public void importPositionExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ImportBaseInfoPositionForm> list = new ArrayList<ImportBaseInfoPositionForm>();
        ImportExcelUtils.export(request, ImportBaseInfoPositionForm.class, list);
        List<ImportBaseInfoPositionForm> exportErrList = baseInfoService.importPositionExcel(list);
        if (exportErrList == null || exportErrList.isEmpty()) {
            responseMsg(response);
        } else {
            Long redisKey = new Date().getTime();
            redisService.writeValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey, exportErrList, Constant.sessionTimeout * 60);
            request.getSession().setAttribute("excelContent", JSONArray.toJSON(exportErrList).toString());
            responseExcel(response, redisKey);

            System.err.println(redisService.readValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey));
        }
    }

    /**
     * 导出错误数据
     * 
     * @author natyu
     * @date 2018年7月27日 下午3:18:17
     * @param request
     * @param response
     * @param form
     * @param lang
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportPositionErrExcel")
    public void exportPositionErrExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang, @RequestParam String redisKey) throws Exception {
        System.err.println(redisService.readValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey));
        // 查询数据
        List<ImportBaseInfoPositionForm> list = JSONArray.parseArray(JSONObject.toJSONString(redisService.readValue(Constant.ERROR_IMPORT_STAFF_BASE_INFO + redisKey)),
            ImportBaseInfoPositionForm.class);
        baseInfoService.exportPositionErrExcel(response, list, lang);
    }

}
