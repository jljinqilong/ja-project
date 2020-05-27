package com.champlink.sale.service.inquiry;

import com.champlink.common.constant.SaleConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.OrgAndOrgInfo;
import com.champlink.common.domain.sale.contract.SaleContract;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.customer.CustomerForm;
import com.champlink.common.form.sale.inquiry.InquiryRecordForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.CodeRules;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.dao.customer.CustomerDao;
import com.champlink.sale.dao.inquiry.InquiriesDao;
import com.champlink.sale.dao.inquiry.InquiryRecordDao;
import com.champlink.sale.service.call.OrgService;
import com.champlink.sale.service.call.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryRecordService {

    private static Logger logger = LoggerFactory.getLogger(InquiryRecordService.class);

    @Autowired
    private InquiryRecordDao inquiryRecordDao;
    @Autowired
    private OrgService orgService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private InquiriesDao inquiriesDao;
    @Autowired
    protected RedisService redisService;
    @Autowired
    private CustomerDao customerDao;

    /**
     * 获取登录人id
     * @Author created by barrett in 2018/8/13 20:13
     */
    public Long getLoginId(){
        Long rowId = null;
        try {
            User loginUser = redisService.getLoginUserByToken(RequestContext.get().getToken());
            String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
            rowId = MethodUtil.getStaffRowId(baseInfo);
        } catch (Exception e) {
            logger.error(">>> Exception{}" + e);
//            AppException.create(100004);
        }
        return rowId;
    }

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:13
     * @Param
     * @return
     */
    public PageListVO<InquiryRecord> pageList(InquiryRecordForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<InquiryRecord> pageListVO = PageListVO.newInstance(inquiryRecordDao.pageList(paginater));
        List<InquiryRecord> list = pageListVO.getList();
        for (InquiryRecord inquiryRecord : list) {
            try {
                //查询组织名称
                String org = orgService.getOrgById(inquiryRecord.getOrgId());
                OrgAndOrgInfo object = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(),org);
                inquiryRecord.setOrgName(object.getBaseOrDeptName());

                //查询用户名称
                String userInfo = staffService.getUserInfo(inquiryRecord.getSalesmanId());
                BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
                inquiryRecord.setSalesmanName(user.getStaffName());

            }catch (Exception e){
//                AppException.create(100004);
                e.printStackTrace();
            }
        }
        return pageListVO;
    }

    /**
     * 添加询价记录
     * @Author created by barrett in 18-8-6 下午6:04
     */
    public boolean add(InquiryRecord record) {
        //根据规则生成编码
        synchronized (this){
            record.setInquiryNo(getInquiryNo());
            record.setCreatedBy(getLoginId());
            //根据客户设置销售区域
            Customer customer = customerDao.getCustomerById(record.getCustomerId());
            record.setSaleAreaId(customer.getAreaId());
            if (inquiryRecordDao.insert(record) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据规则生成编码
     * @Author created by barrett in 18-8-6 下午8:03
     * @Param
     * @return
     */
    public String getInquiryNo(){
        try {
            String prefix = CodeRules.getInguiryCodePrefix();
            String maxInquiryNo = inquiryRecordDao.getMaxInquiryNo(prefix);
            String code = "";
            if(!StringUtils.isEmpty(maxInquiryNo)){
                //不为空表示今日有新增数据，需要累计
                int num = Integer.valueOf(maxInquiryNo.substring(maxInquiryNo.length()-3,maxInquiryNo.length()));
                code = CodeRules.getInguiryCode(prefix,num);
            }else{
                //从001开始
                code += prefix+"001";
            }
            return code;
        }catch (Exception e){
            //根据规则生成编码异常
            AppException.create(300002);
        }
        return null;
    }

    /**
     * 更新
     * @Author created by barrett in 18-8-6 下午9:06
     */
    public boolean update(InquiryRecord record) {
        record.setCreatedBy(getLoginId());
        if (inquiryRecordDao.update(record) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询询价信息
     * @Author created by barrett in 18-8-7 上午9:28
     * @Param
     * @return
     */
    public InquiryRecord getInquiryRecord(Long rowId) {
        InquiryRecord inquiryRecord = inquiryRecordDao.selectByRowId(rowId);

        try {
            //查询组织名称
            String org = orgService.getOrgById(inquiryRecord.getSaleAreaId());
            OrgAndOrgInfo object = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(),org);
            inquiryRecord.setSaleAreaName(object.getBaseOrDeptName());

            //查询创建人
            String userInfo2 = staffService.getUserInfo(inquiryRecord.getCreatedBy());
            BaseInfo user2 = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo2);
            inquiryRecord.setCreateName(user2.getStaffName());

        }catch (Exception e){
            logger.error(">>> Exception {}" + e.getMessage());
//            AppException.create(100004);
        }

        return inquiryRecord;
    }

    /**
     * （逻辑删除）询价记录
     * @Author created by barrett in 18-8-7 下午4:44
     * @Param
     * @return
     */
    @Transactional
    public boolean deleteByIds(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            Long loginId = getLoginId();
            inquiryRecordDao.updateDelFlag(idArr,loginId);
            return true;
        }
        return false;
    }

    /**
     * 确认询价
     * @Author created by barrett in 18-8-10 上午10:29
     */
    public boolean confirmInquiry(Long rowId) {
        InquiryRecord inquiryRecord = inquiryRecordDao.selectByRowId(rowId);
        if(inquiryRecord.getStatus() ==1){
            Long loginId = getLoginId();
            inquiryRecord.setLastUpdateBy(loginId);
            //2、修改状态为转询单
            inquiryRecordDao.updateInquiryStatus(rowId,SaleConstant.CONFIRM_INQUIRY,loginId);
            return true;
        }else{
            logger.error(">>> 确认询价状态不为1， status : "+inquiryRecord.getStatus());
            AppException.create(300003);
        }

        return false;
    }

    /**
     * 转询单
     * @Author created by barrett in 18-8-9 下午8:04
     */
    @Transactional
    public boolean turnInquiries(Long rowId) {

        InquiryRecord inquiryRecord = inquiryRecordDao.selectByRowId(rowId);
        //询价状态 1未确认，2已确认，3已转询单
        if(inquiryRecord.getStatus() == 2){
            Long loginId = getLoginId();
            //1、生成询单记录
            Inquiries inquiries = new Inquiries();
            BeanUtils.copyProperties(inquiryRecord, inquiries);
            //询单状态 1未确认，2已确认，3已转合同
            inquiries.setStatus(SaleConstant.UNCONFIRMED);
            inquiries.setInquiryId(rowId);//关联询价id
            inquiries.setDelFlag(0);
            inquiries.setCreatedBy(loginId);
            inquiriesDao.insert(inquiries);

            //2、修改状态为转询单
            inquiryRecordDao.updateInquiryStatus(rowId,SaleConstant.TURN_INQUIRIES,loginId);
            return true;
        }else{
            logger.error(">>> 询价转询单状态不正确");
            //询价状态未确认
            AppException.create(300003);
        }

        return false;
    }
}
