package com.champlink.sale.service.inquiry;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.SaleConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.OrgAndOrgInfo;
import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.contract.SaleContract;
import com.champlink.common.domain.sale.contract.SaleContractDetail;
import com.champlink.common.domain.sale.contract.SaleDelivery;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.common.domain.sale.inquiry.InquiriesDeliveryTime;
import com.champlink.common.domain.sale.inquiry.InquiriesProduct;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.sale.inquiry.InquiriesForm;
import com.champlink.common.service.call.StaffServiceFacade;
import com.champlink.common.util.excel.ExportExcelUtil;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.CodeRules;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.dao.area.AreaDao;
import com.champlink.sale.dao.area.CountryProvCityDao;
import com.champlink.sale.dao.contract.SaleContractDao;
import com.champlink.sale.dao.customer.CustomerDao;
import com.champlink.sale.dao.inquiry.*;
import com.champlink.sale.service.area.CountryProvCityService;
import com.champlink.sale.service.call.OrgService;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InquiriesService {

    private static Logger logger = LoggerFactory.getLogger(InquiriesService.class);

    @Autowired
    private InquiriesDao inquiriesDao;
    @Autowired
    private InquiriesHistoryDao inquiriesHistoryDao;
    @Autowired
    private OrgService orgService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private InquiriesAppraisalDao inquiriesAppraisalDao;
    @Autowired
    private InquiryRecordDao inquiryRecordDao;
    @Autowired
    private SaleContractDao saleContractDao;
    @Autowired
    private InquiryRecordService inquiryRecordService;
    @Autowired
    private InquiriesAppraisalHistoryDao inquiriesAppraisalHistoryDao;
    @Autowired
    private InquiriesProductDao inquiriesProductDao;
    @Autowired
    private InquiriesDeliveryTimeDao inquiriesDeliveryTimeDao;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private CountryProvCityDao countryProvCityDao;
    @Autowired
    private CountryProvCityService countryProvCityService;
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private StaffServiceFacade staffServiceFacade;


    /**
     * 分页查询
     *
     * @return
     * @Author created by barrett in 18-8-7 下午5:13
     * @Param
     */
    public PageListVO<Inquiries> pageList(InquiriesForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Inquiries> pageListVO = PageListVO.newInstance(inquiriesDao.pageList(paginater));
        for (Inquiries inquiries : pageListVO.getList()) {

            transStatusName(inquiries);
            try {
//                String countryPrivCityName = countryProvCityService.getCountryPrivCityName(inquiries);
                String countryPrivCityName = "";
                if(!StringUtils.isEmpty(inquiries.getCountryName())){
                    countryPrivCityName += inquiries.getCountryName();
                }
                if(!StringUtils.isEmpty(inquiries.getProvinceName())){
                    countryPrivCityName += "/"+inquiries.getProvinceName();
                }
                if(!StringUtils.isEmpty(inquiries.getCityName())){
                    countryPrivCityName += "/"+inquiries.getCityName();
                }
                inquiries.setCountryProvCity(countryPrivCityName);

                //查询组织名称
               /* String org = orgService.getOrgById(inquiries.getOrgId());
                OrgAndOrgInfo object = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(),org);
                inquiries.setOrgName(object.getBaseOrDeptName());*/

                //查询用户名称
//                String userInfo = staffService.getUserInfo(inquiries.getCreatedBy());
//                BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
//                inquiries.setCreateName(user.getStaffName());

            } catch (Exception e) {
//                AppException.create(100004);
                e.printStackTrace();
            }
        }
        return pageListVO;
    }

    /**
     * 添加询单记录
     *
     * @Author created by barrett in 18-8-6 下午6:04
     */
    @Transactional
    public boolean add(Inquiries record) {
        //根据规则生成编码
        synchronized (this) {
            record.setInquiryNo(getInquiryNo());
            splitCountryProvCity(record);
            if (inquiriesDao.insert(record) > 0) {
                //添加产品
                List<InquiriesProduct> productList = record.getInquiriesProductList();
                if (productList != null && productList.size() != 0) {
                    for (InquiriesProduct product : productList) {
                        product.setInquiriesId(record.getRowId());
                    }
                    inquiriesProductDao.insertProductBatch(productList);
                }
                //添加交期
                List<InquiriesDeliveryTime> deliveryTimesList = record.getInquiriesDeliveryTimeList();
                if (deliveryTimesList != null && deliveryTimesList.size() != 0) {
                    for (InquiriesDeliveryTime time : deliveryTimesList) {
                        time.setInquiriesId(record.getRowId());
                    }
                    inquiriesDeliveryTimeDao.insertDeliveryTimesBatch(deliveryTimesList);
                }
                return true;
            }
        }
        return false;
    }

    // 拆分数据到 国家、省、市
    public void splitCountryProvCity(Inquiries record){
        String countryProvCity = record.getCountryProvCity();
        if(!StringUtils.isEmpty(countryProvCity)){
            String[] arr = countryProvCity.split(",");
            if(arr.length > 0){
                record.setCountryId(Long.valueOf(arr[0]));
            }
            if(arr.length > 1){
                record.setProvinceId(Long.valueOf(arr[1]));
            }
            if(arr.length > 2){
                record.setCityId(Long.valueOf(arr[2]));
            }else{
                record.setCityId(null);
            }
        }
    }
    /**
     * 根据规则生成编码
     *
     * @return
     * @Author created by barrett in 18-8-6 下午8:03
     * @Param
     */
    public String getInquiryNo() {
        try {
            String prefix = CodeRules.getInguiryCodePrefix();
            String maxInquiryNo = inquiriesDao.getMaxInquiryNo(prefix);
            String code = "";
            if (!StringUtils.isEmpty(maxInquiryNo)) {
                //不为空表示今日有新增数据，需要累计
                int num = Integer.valueOf(maxInquiryNo.substring(maxInquiryNo.length() - 3, maxInquiryNo.length()));
                code = CodeRules.getInguiryCode(prefix, num);
            } else {
                //从001开始
                code += prefix + "001";
            }
            return code;
        } catch (Exception e) {
            //根据规则生成编码异常
            AppException.create(300002);
        }
        return null;
    }

    /**
     * 更新
     *
     * @Author created by barrett in 18-8-6 下午9:06
     */
    @Transactional
    public boolean update(Inquiries record) {
        Inquiries inquiries = inquiriesDao.getInquiries(record.getRowId());
        //只有状态为1或7的才能修改
        if (inquiries.getStatus() != SaleConstant.UNCONFIRMED && inquiries.getStatus() != SaleConstant.INQUIRY_APPRAISAL_REJECT) {
            AppException.create(300006);
        }
        splitCountryProvCity(record);
        //1、修改询单记录
        if (inquiriesDao.update(record) > 0) {
            //更新询单产品
            List<InquiriesProduct> productList = record.getInquiriesProductList();
            if (productList != null && productList.size() != 0) {
                for (InquiriesProduct product : productList) {
                    product.setInquiriesId(record.getRowId());
                }
                inquiriesProductDao.delByinquiriesId(record.getRowId());
                inquiriesProductDao.insertProductBatch(productList);
            }

            //更新交期
            List<InquiriesDeliveryTime> deliveryTimesList = record.getInquiriesDeliveryTimeList();
            inquiriesDeliveryTimeDao.delByinquiriesId(record.getRowId());
            if (deliveryTimesList != null && deliveryTimesList.size() != 0) {
                for (InquiriesDeliveryTime time : deliveryTimesList) {
                    time.setInquiriesId(record.getRowId());
                }
                inquiriesDeliveryTimeDao.insertDeliveryTimesBatch(deliveryTimesList);
            }

            //添加历史记录
            inquiries.setCreatedBy(RequestContext.get().getStaffId());
            inquiries.setInquiriesId(inquiries.getRowId());
            inquiriesHistoryDao.insert(inquiries);
            return true;
        }
        return false;
    }

    /**
     * 查询询单
     *
     * @return
     * @Author created by barrett in 18-8-7 上午9:28
     * @Param
     */
    public Inquiries getInquirires(Long rowId) {
        Inquiries inquiries = inquiriesDao.getInquiries(rowId);
        if(inquiries == null){
            return null;
        }
        try{
            // 获取省市区
            getCountryProvCity(inquiries);
            // 询单状态翻译
            transStatusName(inquiries);
            inquiries.setCreateName(getUserName(inquiries.getCreatedBy()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return inquiries;
    }

    // 获取当前选择的国省市
    public void getCountryProvCity(Inquiries inquiries){
        inquiries.setCountryList(countryProvCityDao.selectRegionListByPId(SaleConstant.COUNTRY_ID));

        if(!StringUtils.isEmpty(inquiries.getCountryId())){
            inquiries.setProvinceList(countryProvCityDao.selectRegionListByPId(inquiries.getCountryId()));
        }
        if(!StringUtils.isEmpty(inquiries.getProvinceId())){
            inquiries.setCityList(countryProvCityDao.selectRegionListByPId(inquiries.getProvinceId()));
        }
    }

    /**
     * 询单状态翻译
     * @Author created by barrett in 2018/9/3 15:58
     */
    public static void transStatusName(Inquiries inquiries){
        String name = "";
        if (inquiries.getStatus() == 1) {
            name = "未确认";
        } else if (inquiries.getStatus() == 2) {
            name = "已确认";
        } else if (inquiries.getStatus() == 3) {
            name = "已转合同";
        } else if (inquiries.getStatus() == 4) {
            name = "已关闭";
        } else if (inquiries.getStatus() == 5) {
            name = "已确认待评审";
        } else if (inquiries.getStatus() == 6) {
            name = "评审通过";
        } else if (inquiries.getStatus() == 7) {
            name = "评审驳回";
        } else if (inquiries.getStatus() == 8) {
            name = "询单确认信息待补齐";
        } else if (inquiries.getStatus() == 9) {
            name = "信息已补齐待转评审";
        }
        inquiries.setStatusName(name);
    }
    /**
     * （逻辑删除）询单记录
     *
     * @return
     * @Author created by barrett in 18-8-7 下午4:44
     * @Param
     */
    @Transactional
    public boolean deleteByIds(String ids) {

        if (org.apache.commons.lang.StringUtils.isNotBlank(ids)) {
            Long loginId = inquiryRecordService.getLoginId();
            String[] idArr = ids.split(",");
            inquiriesDao.updateDelFlag(idArr, loginId);
            //重置询价状态为1，可转询单
            for (String s : idArr) {
                //先判断只有未确认之前的记录才能删除
                Inquiries inquiries = inquiriesDao.getInquiries(Long.valueOf(s));
                if (inquiries.getStatus() != 1) {
                    AppException.create(300005);//询单状态不是新建状态，不可删除
                }
                InquiryRecord inquiryRecord = inquiryRecordDao.selectByRowId(Long.valueOf(s));
                if (inquiryRecord != null) {
                    inquiryRecordDao.updateInquiryStatus(inquiryRecord.getRowId(), SaleConstant.UNCONFIRMED, loginId);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 确认询单（根据规则判断是否进入询单评审阶段）
     *
     * @Param status： 1未确认，2已确认，3已转合同，4关闭,5已确认待评审,6 评审通过，7 评审驳回
     * @Author created by barrett in 18-8-9 下午8:04
     */
    @Transactional
    public boolean confirmInquiries(Long rowId) {
        Inquiries record = inquiriesDao.getInquiries(rowId);
        if (record.getStatus() == SaleConstant.UNCONFIRMED || record.getStatus() == SaleConstant.INQUIRY_APPRAISAL_REJECT) {

            // 获取规则 （总功率、产品编号）判断是否需要转评审
            String byTpyeJson = saleCommonService.getByTpye(SaleConstant.REVIEW_RULES);
            List<Code> rulesList = new ArrayList<>();
            if (byTpyeJson != null) {
                JSONObject parseObject = JSONObject.parseObject(byTpyeJson);
                if ((Integer) parseObject.get("code") == 200) {
                    rulesList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                }
            }
            //峰值功率
            BigDecimal power = null;
            //产品名称
            List<String> pList = new ArrayList<>();
            for (Code c : rulesList) {
                if (SaleConstant.REVIEW_RULES_POWER.equals(c.getCode())) {
                    power = new BigDecimal(c.getDesc());
                } else if (SaleConstant.REVIEW_RULES_PRODUCT.equals(c.getCode())) {
                    pList.add(c.getDesc());
                }
            }
            String products = "";
            for (String s : pList) {
                products += s+",";
            }
            boolean flag = false;
            //规则中指定产品需要流转评审
            List<InquiriesProduct> productList = record.getInquiriesProductList();
            for (InquiriesProduct ip : productList) {
                if (products.indexOf(ip.getProductNo()) != -1) {
                    flag = true;
                }
            }
            //功率超出规则需要评审
            if (record.getTotalPower().compareTo(power) >= 0) {
                flag = true;
            }
            Long loginId = inquiryRecordService.getLoginId();
            record.setCreatedBy(loginId);

            if(flag) {
                inquiriesDao.updateStatus(rowId, SaleConstant.INQUIRY_APPRAISAL_SUPPLEMENT,loginId);
                record.setStatus(SaleConstant.INQUIRY_APPRAISAL_SUPPLEMENT);
            }else{
                //正常流程转询单
                inquiriesDao.updateStatus(rowId, SaleConstant.CONFIRM_INQUIRY, loginId);
                record.setStatus(SaleConstant.CONFIRM_INQUIRY);
            }
            // 添加询单历史记录
            record.setInquiriesId(record.getRowId());
            inquiriesHistoryDao.insert(record);
            return true;
        } else {
            logger.error(">>> 确认询单状态不为1， status : " + record.getStatus());
            AppException.create(300004);
        }
        return false;
    }

    /**
     * 转评审
     *
     * @Author created by barrett in 2018/8/27 10:35
     */
    public boolean inquiriesTurnAppraisal(Long rowId) {
        Inquiries record = inquiriesDao.getInquiries(rowId);
        if (record.getStatus() == SaleConstant.INQUIRY_APPRAISAL_WAIT) {
            Long loginId = inquiryRecordService.getLoginId();
            record.setCreatedBy(loginId);
            //转评审
            inquiriesDao.updateStatus(rowId, SaleConstant.INQUIRY_APPRAISAL, loginId);
            //添加评审信息
            inquiriesAppraisalDao.insert(record);
            // 添加询单历史记录
            record.setInquiriesId(record.getRowId());
            inquiriesHistoryDao.insert(record);
            return true;
        } else {
            logger.error(">>> 询单信息未补全， status : " + record.getStatus());
            AppException.create(300003);
        }
        return false;

    }

    /**
     * 询单转合同
     *
     * @Author created by barrett in 2018/8/13 16:55
     */
    @Transactional
    public boolean inquiriesTransferContract(Long rowId) {
        SaleContract contract = new SaleContract();
        Inquiries inquiries = inquiriesDao.getInquiries(rowId);
        //只有确认过的和评审通过的才能转合同
        if (inquiries.getStatus() == SaleConstant.CONFIRM_INQUIRY || inquiries.getStatus() == SaleConstant.INQUIRY_APPRAISAL_PASS) {
            Long loginId = inquiryRecordService.getLoginId();
            inquiries.setCreatedBy(loginId);
            //copy 字段
            transferParam(inquiries, contract);
            //添加合同
            saleContractDao.insertSaleContract(contract);

            //添加产品
            List<SaleContractDetail> saleContractDetail = new ArrayList<>();
            productCopy(inquiries.getInquiriesProductList(), saleContractDetail);
            saleContractDao.insertSaleContractDetail(contract.getRowId(), saleContractDetail);
            //添加交期
            List<SaleDelivery> saleDeliveries = new ArrayList<>();
            deliveryCopy(inquiries.getInquiriesDeliveryTimeList(), saleDeliveries);
            saleContractDao.insertSaleDelivery(contract.getRowId(),saleDeliveries);
            //修改状态为已转合同
            inquiriesDao.updateStatus(rowId, SaleConstant.TURN_INQUIRIES, loginId);
            //记录历史
            inquiries.setStatus(SaleConstant.TURN_INQUIRIES);
            inquiriesHistoryDao.insert(inquiries);
            return true;

        } else {
            logger.error("询单状态不正确 status: " + inquiries.getStatus());
            AppException.create(300004);
        }
        return false;
    }

    // 询单记录的产品转合同的产品
    private void productCopy(List<InquiriesProduct> productList, List<SaleContractDetail> saleContractDetail) {
        for (InquiriesProduct product : productList) {
            SaleContractDetail contractProd = new SaleContractDetail();
            contractProd.setAmount(product.getAmount());
            contractProd.setNum(BigDecimal.valueOf(product.getNum()));
            contractProd.setPower(product.getPower());
            contractProd.setProductionId(product.getProductId());
            contractProd.setTotalPower(product.getTotalPower());
            contractProd.setUnit(product.getUnitId());
            contractProd.setUnitPrice(product.getUnitPrice());
            contractProd.setPresentNum(product.getGiveNum().toString());

            saleContractDetail.add(contractProd);
        }
    }
    // 询单记录的交易
    private void deliveryCopy(List<InquiriesDeliveryTime> deliveryTimes, List<SaleDelivery> saleDeliveries) {
        for (InquiriesDeliveryTime times : deliveryTimes) {
            SaleDelivery delivery = new SaleDelivery();
            BeanUtils.copyProperties(times,delivery);
            delivery.setDeliveryNum(times.getNum().toString());
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

            try {
                delivery.setDeliveryTime(format1.parse(times.getDeliveryTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            saleDeliveries.add(delivery);
        }
    }

    /**
     * 将询单信息复制到合同信息中
     *
     * @Author created by barrett in 2018/8/13 17:36
     */
    public void transferParam(Inquiries inquiries, SaleContract contract) {

        contract.setCreatedBy(inquiries.getCreatedBy());
        //合同
        contract.setContractNo(inquiries.getContractNo());
        //基地
        contract.setOrgId(inquiries.getOrgId());
        //客户
        contract.setCustomer(inquiries.getCustomerId());
        //业务员
        contract.setBusinessPerson(inquiries.getSalesman());
        //区域
        contract.setAreaId(inquiries.getSaleAreaId());
        //项目地
        contract.setProjectArea(inquiries.getProjectAddressId());
        //目的国
        contract.setCountryId(inquiries.getCountryId());
        contract.setProvinceId(inquiries.getProvinceId());
        contract.setCityId(inquiries.getCityId());

        contract.setDelFlag(0);
        //询单id、编号
        contract.setInquiriesId(inquiries.getRowId());
        contract.setInquiriesNo(inquiries.getInquiryNo());
        //币别
        contract.setSaleCurrency(inquiries.getCurrencyId());
        //发货地
        contract.setDeliveryAddress(inquiries.getDispatchPlaceId());
    }

    /**
     * 询单历史记录分页
     *
     * @Author created by barrett in 2018/8/14 11:16
     */
    public PageListVO<Inquiries> pageListHistory(InquiriesForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Inquiries> pageListVO = PageListVO.newInstance(inquiriesHistoryDao.pageList(paginater));
        for (Inquiries inquiries : pageListVO.getList()) {

            transStatusName(inquiries);
            try {
                String countryPrivCityName = countryProvCityService.getCountryPrivCityName(inquiries);
                inquiries.setCountryProvCity(countryPrivCityName);
                //查询组织名称
//                String org = orgService.getOrgById(inquiries.getOrgId());
//                OrgAndOrgInfo object = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(), org);
//                inquiries.setOrgName(object.getBaseOrDeptName());

                //查询用户名称
                inquiries.setCreateName(getUserName(inquiries.getCreatedBy()));

            } catch (Exception e) {
//                AppException.create(100004);
                e.printStackTrace();
            }
        }
        return pageListVO;
    }

    /**
     * 补齐信息
     * @Author created by barrett in 2018/8/27 11:54
     */
    @Transactional
    public boolean supplementInquiries(Inquiries record) {
        Inquiries old = inquiriesDao.getInquiries(record.getRowId());
        if(old != null && old.getStatus().equals(SaleConstant.INQUIRY_APPRAISAL)){
            AppException.create(300008);//已转评审的操作不可修改
        }
        record.setStatus(SaleConstant.INQUIRY_APPRAISAL_WAIT);
        //补齐询单记录
        if (inquiriesDao.update(record) > 0) {
            //添加历史记录
            Inquiries inquiries = inquiriesDao.getInquiries(record.getRowId());
            inquiries.setCreatedBy(RequestContext.get().getStaffId());
            inquiries.setInquiriesId(inquiries.getRowId());
            inquiriesHistoryDao.insert(inquiries);
            return true;
        }
        return false;
    }

    // ============================= 询单评审 start ==================================

    /**
     * 询单评审分页
     *
     * @Author created by barrett in 2018/8/14 14:39
     */
    public PageListVO<Inquiries> pageListInquiriesAppraisal(InquiriesForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Inquiries> pageListVO = PageListVO.newInstance(inquiriesDao.appraisalPageList(paginater));


        for (Inquiries inquiries : pageListVO.getList()) {
            try {
                //查询用户名称 -- service call 调用时间过长，需要优化 --
                inquiries.setCreateName(getUserName(inquiries.getCreatedBy()));
                inquiries.setUpdateName(getUserName(inquiries.getLastUpdateBy()));
            } catch (Exception e) {
//                AppException.create(100004);
                e.printStackTrace();
            }
        }


        return pageListVO;
    }

    // 获取用户名称
    public String getUserName(Long rowId) {
        String name = null;
        String userInfo = staffServiceFacade.getUserNameById(rowId);
        BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
        if (!StringUtils.isEmpty(user)) {
            name = user.getStaffName();
        }
        return name;
    }

    /**
     * 确认评审 通过状态为2，驳回 1
     *
     * @Author created by barrett in 2018/8/13 16:26
     */
    @Transactional
    public boolean confirmInquiriesAppraisal(Long rowId, int status) {
        Inquiries appraisal = inquiriesDao.getInquiries(rowId);
        if (appraisal.getStatus() == 5) {
            Long loginId = inquiryRecordService.getLoginId();
            //评审通过
            if (status == SaleConstant.INQUIRY_APPRAISAL_PASS) {
                appraisal.setCreatedBy(loginId);
                inquiriesDao.updateStatus(rowId, SaleConstant.INQUIRY_APPRAISAL_PASS, loginId);
                appraisal.setStatus(SaleConstant.INQUIRY_APPRAISAL_PASS);
            } else if (status == SaleConstant.INQUIRY_APPRAISAL_REJECT) {
                //驳回
                appraisal.setCreatedBy(loginId);
                inquiriesDao.updateStatus(rowId, SaleConstant.INQUIRY_APPRAISAL_REJECT, loginId);
                appraisal.setStatus(SaleConstant.INQUIRY_APPRAISAL_REJECT);
            }
            //添加历史记录
            inquiriesHistoryDao.insert(appraisal);
            return true;
        } else {
            logger.error(">>> 确认询单状态不为5， status : " + appraisal.getStatus());
            AppException.create(300007);
        }
        return false;
    }

    /**
     * 询单评审转合同
     *
     * @Author created by barrett in 2018/8/13 20:06
     */
    @Transactional
    public boolean appraisalTransferContract(Long rowId) {
        SaleContract contract = new SaleContract();
        Inquiries appraisal = inquiriesAppraisalDao.selectByRowId(rowId);
        if (appraisal.getStatus() == 2) {
            Long loginId = inquiryRecordService.getLoginId();
            appraisal.setCreatedBy(loginId);
            appraisal.setStatus(SaleConstant.TURN_INQUIRIES);
            inquiriesAppraisalHistoryDao.insert(appraisal);
            //copy 字段
            transferParam(appraisal, contract);
            saleContractDao.insertSaleContract(contract);
            //修改状态为已转合同
            inquiriesAppraisalDao.updateStatus(rowId, SaleConstant.TURN_INQUIRIES, loginId);
            return true;

        } else {
            logger.error("询单状态不正确 status: " + appraisal.getStatus());
            AppException.create(300004);
        }
        return false;
    }

    /**
     * 查看询单评审详情
     *
     * @Author created by barrett in 2018/8/14 17:21
     */
    public Inquiries getInquiriresAppraisal(Long rowId) {
        Inquiries inquiries = inquiriesAppraisalDao.selectByRowId(rowId);
        return inquiries;
    }

    /**
     * 询单评审历史记录分页 -- 暂无调用
     *
     * @Author created by barrett in 2018/8/14 14:39
     */
    public PageListVO<Inquiries> pageListInquiriesAppraisalHistory(InquiriesForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Inquiries> pageListVO = PageListVO.newInstance(inquiriesAppraisalHistoryDao.pageList(paginater));
        for (Inquiries inquiries : pageListVO.getList()) {
            try {
                //查询组织名称
                String org = orgService.getOrgById(inquiries.getOrgId());
                OrgAndOrgInfo object = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(), org);
                inquiries.setOrgName(object.getBaseOrDeptName());

                //查询用户名称
                inquiries.setSalesmanName(getUserName(inquiries.getSalesmanId()));

            } catch (Exception e) {
//                AppException.create(100004);
                e.printStackTrace();
            }
        }
        return pageListVO;
    }

    // ============================= 询单评审 end ==================================


    // =============================  start ==================================

    /**
     * 导出Excel询单信息
     * @param request
     * @param response
     * @param inquiries
     */
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, Inquiries inquiries){
        //查询数据
        List<Inquiries> inquiriesByInquiries = inquiriesDao.getInquiriesByInquiries(inquiries);
        List<InquiriesProduct> inquiriesProducts = new ArrayList<>();
        List<InquiriesDeliveryTime> inquiriesDeliveryTimes = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        HashMap<Long,Object> map1 = new HashMap<Long,Object>();

        for (Inquiries inqu : inquiriesByInquiries) {
            List<InquiriesProduct> inquiriesProductList = inquiriesProductDao.getInquiriesProductList(inqu.getRowId());
            List<InquiriesDeliveryTime> inquiriesDeliveryTimeList = inquiriesDeliveryTimeDao.getInquiriesDeliveryTimeList(inqu.getRowId());
            inquiriesProducts.addAll(inquiriesProductList);
            inquiriesDeliveryTimes.addAll(inquiriesDeliveryTimeList);
        }
        List<Long> customerIds = new ArrayList<>();
        List<Long> orgIds = new ArrayList<>();

        for (Inquiries inquiries1 : inquiriesByInquiries) {
            if (inquiries1.getOrgId()!=null) orgIds.add(inquiries1.getOrgId());

            if (inquiries1.getCustomerId()!=null) customerIds.add(inquiries1.getCustomerId());

        }

        List<Area> areaByOrgIds = areaDao.getAreaByOrgIds(orgIds);
        List<Customer> customerByRowIds = customerDao.getCustomerByRowIds(customerIds);
        for ( Area area : areaByOrgIds) {
            map1.put(area.getRowId(),area.getRegionName());
        }
        for (Customer customer : customerByRowIds) {
            map1.put(customer.getRowId(),customer.getCustomerName());
        }


        //构建翻译以后的存储容器
        //字典翻译贸易方式
        String paymentType = saleCommonService.getByTpye("TRADE_MODE");
        JSONObject parseObject1 = JSONObject.parseObject(paymentType);
        List<Code> allCodeList1 = JSONObject.parseArray(parseObject1.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList1)){
            for (Code code : allCodeList1) {
                map.put("TRADE_MODE-"+code.getCode(), code.getName());
            }
        }

        //字典翻译销售阶段
        String salesPhase = saleCommonService.getByTpye("SALES_PHASE");
        JSONObject parseObject2 = JSONObject.parseObject(salesPhase);
        List<Code> allCodeList2 = JSONObject.parseArray(parseObject2.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList2)){
            for (Code code : allCodeList2) {
                map.put("SALES_PHASE-"+code.getCode(), code.getName());
            }
        }

        //字典翻译产品类型
        String producType = saleCommonService.getByTpye("PRODUCT_TYPE");
        JSONObject parseObject3 = JSONObject.parseObject(producType);
        List<Code> allCodeList3 = JSONObject.parseArray(parseObject3.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList3)){
            for (Code code : allCodeList3) {
                map.put("PRODUCT_TYPE-"+code.getCode(), code.getName());
            }
        }

        //字典翻译电池片数
        String cellNumber = saleCommonService.getByTpye("CELL_NUMBER");
        JSONObject parseObject4 = JSONObject.parseObject(cellNumber);
        List<Code> allCodeList4 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList4)){
            for (Code code : allCodeList4) {
                map.put("CELL_NUMBER-"+code.getCode(), code.getName());
            }
        }

        //字典翻译电类型
        String batteryType = saleCommonService.getByTpye("BATTERY_TYPE");
        JSONObject parseObject5 = JSONObject.parseObject(batteryType);
        List<Code> allCodeList5 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
        if(!CollectionUtils.isEmpty(allCodeList5)){
            for (Code code : allCodeList5) {
                map.put("BATTERY_TYPE-"+code.getCode(), code.getName());
            }
        }

        for (Inquiries inqui :inquiriesByInquiries) {

            Object oTradeModeId = map.get("ACCOUNT_UNIT-"+inqui.getTradeModeId());
            Object oSalesPhase = map.get("ACCOUNT_UNIT-"+inqui.getSalesPhaseId());
            Object oProducType = map.get("ACCOUNT_UNIT-"+inqui.getProductTypeId());
            Object oOrgId = map1.get(inqui.getOrgId());
            Object oCustomerId = map1.get(inqui.getCustomerId());

            inqui.setTradeModeName(oTradeModeId==null ? null: oTradeModeId.toString());
            inqui.setSalesPhaseName(oSalesPhase==null ? null : oSalesPhase.toString());
            inqui.setProductTypeName(oProducType==null ? null : oProducType.toString());
            inqui.setOrgName(oOrgId==null ? null : oOrgId.toString());
            inqui.setCustomerName(oCustomerId==null ? null : oCustomerId.toString());

        }
        for ( InquiriesProduct inquiriesProduct : inquiriesProducts) {
            Object oTradeModeId = map.get("BATTERY_TYPE-"+inquiriesProduct.getBatteryTypeId());
            inquiriesProduct.setBatteryTypeName(oTradeModeId==null ? null : oTradeModeId.toString());
        }

        String[] headers = {"询单编号","客户名称","区域名称","产品编号","产品类型","订单总量","销售价格","贸易方式",
                            "首年衰减","付款条件","询单标题","预计签单日期","销售阶段","实际跟进时间"
                           };
        String[] fields = {"rowId","customerName","orgName","productNo","productTypeName","orderTotal","sellingPrice","tradeModeName",
                           "firstYearAttenuation","paymentTerm","inquiryTitle","estimatedSigningDate","salesPhaseName","actualFollowTime"
                          };
        String title = "询单数据";

        String[] headers1 = {"询单编号","产品编号","电池片数","功率","单价","单位","数量",
                             "产品售价","电池型号","z总功率","赠送数量"
                            };
        String[] fields1 = {"inquiriesId","productNo","cellNumberId","power","unitPrice","unitId","num",
                            "amount","batteryTypeName","totalPower","giveNum"
                           };
        String title1 = "询单产品数据";

        String[] headers2 = {"询单编号","交期时间"};
        String[] fields2 = {"inquiriesId","deliveryTime"};
        String title2 = "询单交期数据";

        List<List<?>> dataList = new  ArrayList<List<?>>();
        dataList.add(inquiriesByInquiries);
        dataList.add(inquiriesProducts);
        dataList.add(inquiriesDeliveryTimes);

        List<String> titlelList = new ArrayList<>();
        titlelList.add(title);
        titlelList.add(title1);
        titlelList.add(title2);

        List<String[]> strHeaders = new ArrayList<String[]>();
        strHeaders.add(headers);
        strHeaders.add(headers1);
        strHeaders.add(headers2);

        List<String[]> strFields = new ArrayList<String[]>();
        strFields.add(fields);
        strFields.add(fields1);
        strFields.add(fields2);

        ExportExcelUtil.exportExcelMultipleSheet(response,dataList,"询单",titlelList,strHeaders,strFields,null);
    }

    /*public static void main(String[] args){
        List<Long> s = new ArrayList<>();
        s.add(1L);
        s.add(null);
        s.add(5L);
        System.out.println(s);
    }*/

}
