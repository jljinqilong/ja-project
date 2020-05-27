package com.champlink.common.domain.staff.contract;

import com.champlink.common.domain.BaseBean;

public class CompanyRecord extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 职衔申请表A
     */
    private Integer jobApplicationA;

    /**
     * 竞业协议
     */
    private Integer competitionAgreement;

    /**
     * 员工登记表B
     */
    private Integer staffRegisterB;

    /**
     * HR制度阅读回执
     */
    private Integer hrReadReceipt;

    /**
     * 录用审核表
     */
    private Integer hireChack;

    /**
     * 员工手册
     */
    private Integer staffHandbook;

    /**
     * 个人简历
     */
    private Integer resume;

    /**
     * 照片
     */
    private Integer photo;

    /**
     * 身份证或护照复印件
     */
    private Integer idCardCopies;

    /**
     * 工资卡信息
     */
    private Integer payCardInfo;

    /**
     * 户口本复印件
     */
    private Integer residenceBookletCopies;

    /**
     * 岗位说明书
     */
    private Integer positionDescription;

    /**
     * 毕业证书复印件
     */
    private Integer graduationCertificateCopies;

    /**
     * 学生证复印件
     */
    private Integer studentIdCardCopies;

    /**
     * 学位证书复印件
     */
    private Integer diplomaCopies;

    /**
     * 实习协议
     */
    private Integer internshipContract;

    /**
     * 相关证书复印件
     */
    private Integer relevantCertificateCopies;

    /**
     * 劳务协议
     */
    private Integer labourAgreement;

    /**
     * 体检报告
     */
    private Integer medicalExaminationReport;

    /**
     * 背调报告
     */
    private Integer backSurveyReport;

    /**
     * 上家公司离职证明
     */
    private Integer beforeCompanyDimission;

    /**
     * 劳动合同
     */
    private Integer laborContract;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 诚信行为暨知识产权协议书
     */
    private Integer intellectualPropertyAgreement;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取职衔申请表A
     *
     * @return job_application_a - 职衔申请表A
     */
    public Integer getJobApplicationA() {
        return jobApplicationA;
    }

    /**
     * 设置职衔申请表A
     *
     * @param jobApplicationA 职衔申请表A
     */
    public void setJobApplicationA(Integer jobApplicationA) {
        this.jobApplicationA = jobApplicationA;
    }

    /**
     * 获取竞业协议
     *
     * @return competition agreement - 竞业协议
     */
    public Integer getCompetitionAgreement() {
        return competitionAgreement;
    }

    /**
     * 设置竞业协议
     *
     * @param competitionAgreement 竞业协议
     */
    public void setCompetitionAgreement(Integer competitionAgreement) {
        this.competitionAgreement = competitionAgreement;
    }

    /**
     * 获取员工登记表B
     *
     * @return staff_register_b - 员工登记表B
     */
    public Integer getStaffRegisterB() {
        return staffRegisterB;
    }

    /**
     * 设置员工登记表B
     *
     * @param staffRegisterB 员工登记表B
     */
    public void setStaffRegisterB(Integer staffRegisterB) {
        this.staffRegisterB = staffRegisterB;
    }

    /**
     * 获取HR制度阅读回执
     *
     * @return hr_read_receipt - HR制度阅读回执
     */
    public Integer getHrReadReceipt() {
        return hrReadReceipt;
    }

    /**
     * 设置HR制度阅读回执
     *
     * @param hrReadReceipt HR制度阅读回执
     */
    public void setHrReadReceipt(Integer hrReadReceipt) {
        this.hrReadReceipt = hrReadReceipt;
    }

    /**
     * 获取录用审核表
     *
     * @return hire_chack - 录用审核表
     */
    public Integer getHireChack() {
        return hireChack;
    }

    /**
     * 设置录用审核表
     *
     * @param hireChack 录用审核表
     */
    public void setHireChack(Integer hireChack) {
        this.hireChack = hireChack;
    }

    /**
     * 获取员工手册
     *
     * @return staff_handbook - 员工手册
     */
    public Integer getStaffHandbook() {
        return staffHandbook;
    }

    /**
     * 设置员工手册
     *
     * @param staffHandbook 员工手册
     */
    public void setStaffHandbook(Integer staffHandbook) {
        this.staffHandbook = staffHandbook;
    }

    /**
     * 获取个人简历
     *
     * @return resume - 个人简历
     */
    public Integer getResume() {
        return resume;
    }

    /**
     * 设置个人简历
     *
     * @param resume 个人简历
     */
    public void setResume(Integer resume) {
        this.resume = resume;
    }

    /**
     * 获取照片
     *
     * @return photo - 照片
     */
    public Integer getPhoto() {
        return photo;
    }

    /**
     * 设置照片
     *
     * @param photo 照片
     */
    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    /**
     * 获取身份证或护照复印件
     *
     * @return id_card_copies - 身份证或护照复印件
     */
    public Integer getIdCardCopies() {
        return idCardCopies;
    }

    /**
     * 设置身份证或护照复印件
     *
     * @param idCardCopies 身份证或护照复印件
     */
    public void setIdCardCopies(Integer idCardCopies) {
        this.idCardCopies = idCardCopies;
    }

    /**
     * 获取工资卡信息
     *
     * @return pay_card_info - 工资卡信息
     */
    public Integer getPayCardInfo() {
        return payCardInfo;
    }

    /**
     * 设置工资卡信息
     *
     * @param payCardInfo 工资卡信息
     */
    public void setPayCardInfo(Integer payCardInfo) {
        this.payCardInfo = payCardInfo;
    }

    /**
     * 获取户口本复印件
     *
     * @return residence_booklet_copies - 户口本复印件
     */
    public Integer getResidenceBookletCopies() {
        return residenceBookletCopies;
    }

    /**
     * 设置户口本复印件
     *
     * @param residenceBookletCopies 户口本复印件
     */
    public void setResidenceBookletCopies(Integer residenceBookletCopies) {
        this.residenceBookletCopies = residenceBookletCopies;
    }

    /**
     * 获取岗位说明书
     *
     * @return position_description - 岗位说明书
     */
    public Integer getPositionDescription() {
        return positionDescription;
    }

    /**
     * 设置岗位说明书
     *
     * @param positionDescription 岗位说明书
     */
    public void setPositionDescription(Integer positionDescription) {
        this.positionDescription = positionDescription;
    }

    /**
     * 获取毕业证书复印件
     *
     * @return graduation_certificate_copies - 毕业证书复印件
     */
    public Integer getGraduationCertificateCopies() {
        return graduationCertificateCopies;
    }

    /**
     * 设置毕业证书复印件
     *
     * @param graduationCertificateCopies 毕业证书复印件
     */
    public void setGraduationCertificateCopies(Integer graduationCertificateCopies) {
        this.graduationCertificateCopies = graduationCertificateCopies;
    }

    /**
     * 获取学生证复印件
     *
     * @return student_id_card_copies - 学生证复印件
     */
    public Integer getStudentIdCardCopies() {
        return studentIdCardCopies;
    }

    /**
     * 设置学生证复印件
     *
     * @param studentIdCardCopies 学生证复印件
     */
    public void setStudentIdCardCopies(Integer studentIdCardCopies) {
        this.studentIdCardCopies = studentIdCardCopies;
    }

    /**
     * 获取学位证书复印件
     *
     * @return diploma_copies - 学位证书复印件
     */
    public Integer getDiplomaCopies() {
        return diplomaCopies;
    }

    /**
     * 设置学位证书复印件
     *
     * @param diplomaCopies 学位证书复印件
     */
    public void setDiplomaCopies(Integer diplomaCopies) {
        this.diplomaCopies = diplomaCopies;
    }

    /**
     * 获取实习协议
     *
     * @return internship_contract - 实习协议
     */
    public Integer getInternshipContract() {
        return internshipContract;
    }

    /**
     * 设置实习协议
     *
     * @param internshipContract 实习协议
     */
    public void setInternshipContract(Integer internshipContract) {
        this.internshipContract = internshipContract;
    }

    /**
     * 获取相关证书复印件
     *
     * @return relevant_certificate_copies - 相关证书复印件
     */
    public Integer getRelevantCertificateCopies() {
        return relevantCertificateCopies;
    }

    /**
     * 设置相关证书复印件
     *
     * @param relevantCertificateCopies 相关证书复印件
     */
    public void setRelevantCertificateCopies(Integer relevantCertificateCopies) {
        this.relevantCertificateCopies = relevantCertificateCopies;
    }

    /**
     * 获取劳务协议
     *
     * @return labour_agreement - 劳务协议
     */
    public Integer getLabourAgreement() {
        return labourAgreement;
    }

    /**
     * 设置劳务协议
     *
     * @param labourAgreement 劳务协议
     */
    public void setLabourAgreement(Integer labourAgreement) {
        this.labourAgreement = labourAgreement;
    }

    /**
     * 获取体检报告
     *
     * @return medical_examination_report - 体检报告
     */
    public Integer getMedicalExaminationReport() {
        return medicalExaminationReport;
    }

    /**
     * 设置体检报告
     *
     * @param medicalExaminationReport 体检报告
     */
    public void setMedicalExaminationReport(Integer medicalExaminationReport) {
        this.medicalExaminationReport = medicalExaminationReport;
    }

    /**
     * 获取背调报告
     *
     * @return back_survey_report - 背调报告
     */
    public Integer getBackSurveyReport() {
        return backSurveyReport;
    }

    /**
     * 设置背调报告
     *
     * @param backSurveyReport 背调报告
     */
    public void setBackSurveyReport(Integer backSurveyReport) {
        this.backSurveyReport = backSurveyReport;
    }

    /**
     * 获取上家公司离职证明
     *
     * @return before_company_dimission - 上家公司离职证明
     */
    public Integer getBeforeCompanyDimission() {
        return beforeCompanyDimission;
    }

    /**
     * 设置上家公司离职证明
     *
     * @param beforeCompanyDimission 上家公司离职证明
     */
    public void setBeforeCompanyDimission(Integer beforeCompanyDimission) {
        this.beforeCompanyDimission = beforeCompanyDimission;
    }

    /**
     * 获取劳动合同
     *
     * @return labor_contract - 劳动合同
     */
    public Integer getLaborContract() {
        return laborContract;
    }

    /**
     * 设置劳动合同
     *
     * @param laborContract 劳动合同
     */
    public void setLaborContract(Integer laborContract) {
        this.laborContract = laborContract;
    }

    /**
     * 获取备注信息
     *
     * @return remark - 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     *
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取诚信行为暨知识产权协议书
     *
     * @return intellectual_property_agreement - 诚信行为暨知识产权协议书
     */
    public Integer getIntellectualPropertyAgreement() {
        return intellectualPropertyAgreement;
    }

    /**
     * 设置诚信行为暨知识产权协议书
     *
     * @param intellectualPropertyAgreement 诚信行为暨知识产权协议书
     */
    public void setIntellectualPropertyAgreement(Integer intellectualPropertyAgreement) {
        this.intellectualPropertyAgreement = intellectualPropertyAgreement;
    }

}