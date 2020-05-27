package com.champlink.sale.service.contract;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.SaleConstant;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.contract.*;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.payment.PaymentPlan;
import com.champlink.common.domain.sale.payment.PaymentPlanDetail;
import com.champlink.common.domain.sale.product.Product;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.contract.InvoiceExcelForm;
import com.champlink.common.form.sale.contract.SaleContractDetailExcelForm;
import com.champlink.common.form.sale.contract.SaleContractExcelForm;
import com.champlink.common.form.sale.contract.SaleContractForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.date.DateUtils;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.dao.area.AreaDao;
import com.champlink.sale.dao.area.CountryProvCityDao;
import com.champlink.sale.dao.contract.SaleContractDao;
import com.champlink.sale.dao.customer.CustomerDao;
import com.champlink.sale.dao.payment.PaymentDao;
import com.champlink.sale.dao.payment.PaymentPlanDao;
import com.champlink.sale.dao.product.ProductDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/4 11:48
 **/
@Service
public class SaleContractService {

    @Autowired
    private SaleContractDao saleContractDao;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private StaffService staffService;
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private PaymentPlanDao paymentPlanDao;
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CountryProvCityDao countryProvCityDao;

    /**
     * @Author jsl
     * @Date 17:47 2018/8/4
     * @Description 合同查询
     **/
    public PageListVO<SaleContract> searchSaleContractList(SaleContractForm saleContractForm) {
        Paginater paginater = Paginater.newInstance(saleContractForm);
        //区域list
        // List<SaleContract> saleContractList = listVO.getList();
        //
        // //转换我方签约人
        // for(SaleContract saleContract : saleContractList){
        //
        //     String userInfo = staffService.getUserInfo(saleContract.getCreatedBy());
        //     BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
        //     saleContract.setOurSignatory(user.getStaffName());
        // }
        return PageListVO.newInstance(saleContractDao.searchSaleContractList(paginater));
    }

    /**
     * 合同增加
     *
     * @Author jsl
     * @Date 17:47 2018/8/4
     * @Description
     **/
    @Transactional
    public void add(SaleContract saleContract) {

        String saleContractNo = "";

        //新增合同表
        //1.未回款金额 = 合同总额
        saleContract.setUnPaymentAmount(saleContract.getTotalAmount());
        saleContract.setPaymentAmount(new BigDecimal(0.00));
        //2.未开票金额 = 合同总额
        saleContract.setUnTicketAmount(saleContract.getTotalAmount());
        saleContract.setTicketAmount(new BigDecimal(0.00));

        //3.未计划总金额 = 合同总额
        saleContract.setUnPlanedAmount(saleContract.getTotalAmount());
        saleContract.setPlanedAmount(new BigDecimal(0.0));

        //合同号判重
        saleContractNo = saleContract.getContractNo();
        int saleContractNoNum =  saleContractDao.getIsnewContract(saleContractNo);
        //存在
        if(saleContractNoNum>0){
            //合同号已经存在
            AppException.create(330001);
        }

        //合同标题判重
        String saleContractTitle = saleContract.getContractTitle();
        int saleContractTitleNum =  saleContractDao.getIsnewContractTitle(saleContractTitle);
        if(saleContractTitleNum>0){
            //合同标题已经存在
            AppException.create(330002);
        }


        int num = saleContractDao.insertSaleContract(saleContract);

        //新增合同--产品明细表   lsit
        List<SaleContractDetail> saleContractDetailList = saleContract.getSaleContractDetailList();

        if (saleContractDetailList != null ) {
            saleContractDao.insertSaleContractDetail(saleContract.getRowId(), saleContractDetailList);
        }
        //新增付款计划     list
//            List<SalePayPlan> salePayPlanList = saleContract.getSalePayPlanList();
//            if (salePayPlanList != null) {
//                saleContractDao.insertSalePayPlan(saleContract.getRowId(), salePayPlanList);
//            }
        //交期信息
        List<SaleDelivery> saleDeliveryList = saleContract.getSaleDeliveryList();
        if(saleDeliveryList != null && saleDeliveryList.size()>0){
            saleContractDao.insertSaleDelivery(saleContract.getRowId(),saleDeliveryList);
        }

    }

    /**
     * @Author jsl
     * @Date 14:41 2018/8/6
     * @Description 生成合同单号
     **/
    @Transactional
    public String getSaleContractNo(String prefix, String Suffix) {

        //合同号码
        String saleContractNo = "";
        //时间串
        String dateString = "";
        //顺序串
        String count = "";
        if (!"".equals(Suffix)) {
            count = String.valueOf(Integer.parseInt(Suffix) + 1);
        } else {
            count = "1";
        }


        if (count.length() == 1) {
            count = "000" + count;
        } else if (count.length() == 2) {
            count = "00" + count;
        } else if (count.length() == 3) {
            count = "0" + count;
        } else {
            count = count;
        }

        //拼接日期
        Calendar now = Calendar.getInstance();
        //使用Date
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dateString = sdf.format(d);

        saleContractNo = prefix + dateString + count;

        return saleContractNo;
    }

    /**
     * @Author jsl
     * @Date 删除合同信息 2018/8/6
     * @Description
     **/
    @Transactional
    public boolean del(Long rowId) {
        try {
            //删除
            //1.先删除 付款计划
            saleContractDao.delPayPlanById(rowId);
            //2.删除 产品明细
            saleContractDao.delSaleContractProdDetailById(rowId);

            //删除发票信息
            saleContractDao.delInvoieByid(rowId);

            //3.删除交期信息
            saleContractDao.delDeliveryById(rowId);
            //4.删除合同
            saleContractDao.delSaleContractById(rowId);

            return true;
        } catch (DataIntegrityViolationException e) {
            throw new AppException("当前合同被引用，无法删除");
        }
    }

    /**
     * @Author jsl
     * @Date 18:22 2018/8/6
     * @Description
     **/
    public SaleContract getSaleContractById(Long rowId) {
        SaleContract saleContract = new SaleContract();
        saleContract = saleContractDao.getSaleContractById(rowId);

        //转名称
        String userInfo = staffService.getUserInfo(saleContract.getCreatedBy());
        BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
        saleContract.setOurSignatory(user.getStaffName());

        //查询国家，省，市区 list
        saleContract.setCountryList(countryProvCityDao.selectRegionListByPId(SaleConstant.COUNTRY_ID));

        if(!StringUtils.isEmpty(saleContract.getCountryId())){
            saleContract.setProvinceList(countryProvCityDao.selectRegionListByPId(saleContract.getCountryId()));
        }
        if(!StringUtils.isEmpty(saleContract.getProvinceId())){
            saleContract.setCityList(countryProvCityDao.selectRegionListByPId(saleContract.getProvinceId()));
        }

        //计算总功率
        List<SaleContractDetail> productList = saleContract.getProductList();
        int totalPower = 0;
        int power = 0;
        int num = 0;

        if(productList != null) {
            for (SaleContractDetail saleContractDetail : productList) {
                power = Integer.parseInt(saleContractDetail.getProduct().getRatedPower());
                num = saleContractDetail.getNum().intValue();
                totalPower = totalPower + (power * num);
            }
        }
        //totalPower转String
        saleContract.setTotalPower(String.valueOf(totalPower));

        return saleContract;
    }

    /**
     * @Author jsl
     * @Date 更新 2018/8/6
     * @Description
     **/
    @Transactional
    public boolean update(SaleContract saleContract) {
        try {
            //update  更新信息
            saleContractDao.update(saleContract);
            //产品明细更新  先删 在插
            saleContractDao.delSaleContractProdDetailById(saleContract.getRowId());
            if(saleContract.getSaleContractDetailList() != null) {
                saleContractDao.insertSaleContractDetail(saleContract.getRowId(), saleContract.getSaleContractDetailList());
            }

            //付款计划更新  先删 再插
//            saleContractDao.delPayPlanById(saleContract.getRowId());
//            if(saleContract.getSalePayPlanList() != null) {
//                saleContractDao.insertSalePayPlan(saleContract.getRowId(), saleContract.getSalePayPlanList());
//            }

            //交期信息更新
            saleContractDao.delDeliveryById(saleContract.getRowId());
            if(saleContract.getSaleDeliveryList() != null && saleContract.getSaleDeliveryList().size()>0){
                saleContractDao.insertSaleDelivery(saleContract.getRowId(),saleContract.getSaleDeliveryList());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Author jsl
     * @Date 增加发票 2018/8/6
     * @Description
     **/
    @Transactional
    public void addInvoice(InvoiceForm invoice) {
        //合同id
        Long saleContractId = invoice.getSaleContractId();
        //增加......
        //查询合同总金额
        BigDecimal totalAmount = saleContractDao.selectTotleAmountById(saleContractId);
        //已存在的发票金额
        BigDecimal existsInvoiceAmount = saleContractDao.selectExistsInvoiceAmountById(saleContractId);
        //发票总额
        BigDecimal fpTotalAmount = new BigDecimal(0.0);

//        for(InvoiceForm invoice:invoiceFormList){
        //计算发票金额
        fpTotalAmount = existsInvoiceAmount.add(invoice.getInvoiceAmount());

        //发票校验（同一个销售合同下不能存在相同的发票号码）
        String invoiceNo = invoice.getInvoiceNo();

        //查询发票是否存在
        int num = saleContractDao.selectInvoiceByIdAndNo(saleContractId, invoiceNo,null);
        if (num == 0) {
            //发票没重复，判断发票总额
            //1.合同金额大于等于发票总额
            if (totalAmount.compareTo(fpTotalAmount) >= 0) {
                saleContractDao.insertSaleInvoice(invoice);
                //更新合同的已开票金额和未开票金额
                saleContractDao.updateContractFpAmount(saleContractId, invoice.getInvoiceAmount());

            } else {
                AppException.create(310005);    //发票金额超过合同总金额
            }

        } else {
            AppException.create(310004);    //同一个合同下发票不能重复
        }
//        }
    }

    /**
     * @Author jsl
     * @Date 删除发票信息 2018/8/6
     * @Description
     **/
    @Transactional
    public boolean delInvoice(Long rowId) {
        //删除......
        //1.根据id查询除这个记录
        InvoiceForm invoice = new InvoiceForm();
        invoice = saleContractDao.getSingleInvoiceById(rowId);

        if (saleContractDao.delInvoieByid(rowId)) {
            //更新合同已开票和未开票金额
            //金额换算负数  做加法
            saleContractDao.updateContractFpAmount(invoice.getSaleContractId(), new BigDecimal(0.0).subtract(invoice.getInvoiceAmount()));
            return true;
        }

        return false;
    }

    /**
     * @Author jsl
     * @Date 根据发票id进行update 2018/8/6
     * @Description
     **/
    @Transactional
    public boolean updateInvoice(InvoiceForm invoiceForm) {
        //更新操作
        Long rowId = invoiceForm.getRowId();
        //发票号码
        String invoiceNo = invoiceForm.getInvoiceNo();
        //销售合同id
        Long saleContractId = invoiceForm.getSaleContractId();
        //该销售单下是否已经存在此发票
        int num = saleContractDao.selectInvoiceByIdAndNo(saleContractId, invoiceNo,rowId);
        //此条记录以外的所有发票金额之和
        BigDecimal totalInvoiceAmountNoThis = saleContractDao.selectTotalInvoiceAmountNoThis(saleContractId, rowId);
        //查询合同总金额
        BigDecimal totalAmount = saleContractDao.selectTotleAmountById(saleContractId);

        if (num > 0) {
            AppException.create(310004);            //发票已经存在
        } else {
            //更新发票信息
            //1.判断发票金额
            //查询除此条记录外的发票金额总和 + 此次修改的发票金额 不能大于合同总额
            if (totalAmount.compareTo(totalInvoiceAmountNoThis.add(invoiceForm.getInvoiceAmount())) >= 0) {
                saleContractDao.updateInvoice(rowId, saleContractId, invoiceForm);

                //更新合同已开票和未开票金额
                //1.查询当前合同所有发票总金额
                BigDecimal allInvoiceAmount = saleContractDao.selectAllInvoiceAmount(saleContractId);
                //2.更新合同中的已开票和未开票金额
                saleContractDao.updateContractFpAmountById(saleContractId, allInvoiceAmount);

            } else {
                AppException.create(310005);        //发票总金额不能大于合同总金额
            }
        }

        return true;
    }

    /**
     * @Author jsl
     * @Date 查询发票信息 2018/8/7
     * @Description
     **/
    public List<InvoiceForm> searchInvoiceById(Long saleContractId) {
        List<InvoiceForm> invoiceList = new ArrayList<InvoiceForm>();
        //根据销售单id查询发票信息
        invoiceList = saleContractDao.searchInvoiceById(saleContractId);
        return invoiceList;
    }

    /**
     * @Author jsl
     * @Date 根据发票id查询单张发票的明细 2018/8/7
     * @Description
     **/
    public InvoiceForm getSingleInvoiceById(Long invoiceId) {
        InvoiceForm invoiceForm = new InvoiceForm();
        invoiceForm = saleContractDao.getSingleInvoiceById(invoiceId);
        return invoiceForm;
    }

    /**
     * <<<<<<< HEAD
     *
     * @Author jsl
     * @Date 增加回款金额 2018/8/8
     * @Description
     **/
    @Transactional
    public boolean addPayPlanDetail(SalePayPlanDetail salePayPlanDetail) {
        //增加回款明细  判断明细总金额不能大于计划回款金额
        //1.先查询已存在的明细金额总和
        Long payPlanId = salePayPlanDetail.getPayPlanId();
        BigDecimal yfAmount = saleContractDao.getYfAmountById(payPlanId);

        //2.判断已存在总金额 + 本次回款金额 是否大于计划单总金额
        //2.1 查询计划单信息
        SalePayPlan salePayPlan = new SalePayPlan();
        salePayPlan = saleContractDao.getSalePayPlanById(payPlanId);
        BigDecimal payAmount = salePayPlan.getPayAmount();
        //2.2 比较金额大小
        if (payAmount.compareTo(yfAmount.add(salePayPlanDetail.getAmount())) >= 0) {
            //保存明细数据
            saleContractDao.insertSalePayPlanDetail(salePayPlanDetail);
            //更新合同中的已付金额和未付金额
            saleContractDao.updatePayMentAmount(salePayPlan.getSaleContractId(), salePayPlanDetail.getAmount());

        }

        return true;
    }

    /**
     * @Author jsl
     * @Date 回款计划明细删除 2018/8/8
     * @Description
     **/
    public boolean delPayPlanDetail(Long id) {

        //先查询合同id,回款计划id
        SalePayPlanDetail salePayPlanDetail = new SalePayPlanDetail();
        salePayPlanDetail = saleContractDao.getSalePayPlanDetailById(id);

        //回款单id
        Long payPlanId = salePayPlanDetail.getPayPlanId();
        //回款金额
        BigDecimal amount = salePayPlanDetail.getAmount();

        //获取合同id
        SalePayPlan salePayPlan = saleContractDao.getSalePayPlanById(payPlanId);
        //合同id
        Long saleContractId = salePayPlan.getSaleContractId();

        //1.更新合同已回款和未回款记录  转负数计算
        saleContractDao.updatePayMentAmount(saleContractId, new BigDecimal(0.0).subtract(amount));
        //2.删除回款明细记录
        saleContractDao.delPayPlanDetailById(id);

        return true;
    }

    /**
     * @Author jsl
     * @Date 根据id查询回款明细数据 2018/8/8
     * @Description
     **/
    public SalePayPlanDetail getPayPlanDetail(Long id) {
        SalePayPlanDetail salePayPlanDetail = saleContractDao.getSalePayPlanDetailById(id);
        return salePayPlanDetail;
    }

    /**
     * @Author jsl
     * @Date 更新明细数据 2018/8/8
     * @Description
     **/
    @Transactional
    public boolean updatePayPlanDetail(SalePayPlanDetail salePayPlanDetail) {
        //取合同id
        Long payPlanId = salePayPlanDetail.getPayPlanId();
        SalePayPlan salePayPlan = saleContractDao.getSalePayPlanById(payPlanId);
        //合同id
        Long saleContractId = salePayPlan.getSaleContractId();

        //先update 明细数据
        saleContractDao.updateSalePayPlanDetail(salePayPlanDetail);
        //更新合同里的已回款金额
        //1.先查询总的回款金额
        BigDecimal totalAmount = saleContractDao.getTotalPaymentAmount(salePayPlanDetail.getRowId());
        //2.更新合同里的回款金额
        saleContractDao.updatePayMentAmountById(saleContractId, totalAmount);
        return true;
    }

    /*
     * 查询客户的合同list
     * @Author created by barrett in 18-8-8 下午4:27
     */
    public PageListVO<SaleContract> searchSaleContractByCustomer(SaleContractForm saleContractForm) {
        Paginater paginater = Paginater.newInstance(saleContractForm);
        PageListVO<SaleContract> listVO = PageListVO.newInstance(saleContractDao.searchSaleContractByCustomer(paginater));
        return listVO;
    }


    /**
     * @Author jsl
     * @Date 根据合同id查询回款记录 2018/8/9
     * @Description    
     **/
    public List<PaymentPlan> getPaymentDetailBySaleContractId(Long saleContractId) {
        //查询
        List<PaymentPlan>  paymentPlanList  = paymentPlanDao.getContractInfoBy(saleContractId);
        return paymentPlanList;
    }


    public PageListVO<SaleContract> getSaleContractListById(SaleContractForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<SaleContract> pageListVO = PageListVO.newInstance(saleContractDao.getSaleContractListById(paginater));
        return pageListVO;
    }


    public boolean updateTransferMan(Long staffId, Long rowId) {
        if(saleContractDao.updateTransferMan(staffId,rowId)){
            return true;
        }
        return false;
    }
    
    /**
     * 导入合同信息
     * @param saleContractExcelForms
     */
    @Transactional
    public void importExcelSaleContract(List<SaleContractExcelForm> saleContractExcelForms){
    	//获取当前操作人
//		String token = RequestContext.get().getToken();
//		User loginUser = redisService.getLoginUserByToken(token);

        Long staffId = RequestContext.get().getStaffId();
		List<SaleContract> saleContracts = new ArrayList<SaleContract>();
		List<String> strs = new ArrayList<String>();

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		Set<String> set = new HashSet<String>();
		
		for (SaleContractExcelForm saleContractExcelForm : saleContractExcelForms) {
			set.add(saleContractExcelForm.getCustomerName());
		}
		List<String> customerNames = new ArrayList<String>(set);
		
		List<Customer> checkCustomerNoIsExist = customerDao.checkCustomerNoIsExist(customerNames);
		
		for (Customer customer : checkCustomerNoIsExist) {
			map.put("CUSTOMERNAME-"+customer.getCustomerName(), customer.getRowId());
		}
		

		// 字典翻译合同类型
		String contractTypeName = saleCommonService.getByTpye("SALE_CONTRACT_TYPE");
		JSONObject parseObject1 = JSONObject.parseObject(contractTypeName);
		List<Code> allCodeList1 = JSONObject.parseArray(parseObject1.getString("data"), Code.class);
		if (!CollectionUtils.isEmpty(allCodeList1)) {
			for (Code code : allCodeList1) {
				map.put("SALE_CONTRACT_TYPE-" + code.getCode(), code.getRowId());
			}
		}

		// 字典翻译付款方式
		String payMethodName = saleCommonService.getByTpye("PAYMENT_TYPE");
		JSONObject parseObject4 = JSONObject.parseObject(payMethodName);
		List<Code> allCodeList4 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
		if (!CollectionUtils.isEmpty(allCodeList4)) {
			for (Code code : allCodeList4) {
				map.put("PAYMENT_TYPE-" + code.getCode(), code.getRowId());
			}
		}

		// 字典翻译币别
		String saleCurrency = saleCommonService.getByTpye("CURRENCY");
		JSONObject parseObject5 = JSONObject.parseObject(saleCurrency);
		List<Code> allCodeList5 = JSONObject.parseArray(parseObject5.getString("data"), Code.class);
		if (!CollectionUtils.isEmpty(allCodeList5)) {
			for (Code code : allCodeList5) {
				map.put("CURRENCY-" + code.getCode(), code.getRowId());
			}
		}

		for (SaleContractExcelForm saleContractExcelForm : saleContractExcelForms) {
			SaleContract saleContract = new SaleContract();

			Object oContractTypeName = map.get("SALE_CONTRACT_TYPE-" + saleContractExcelForm.getContractTypeName());
			Object oPaymentTypeId = map.get("PAYMENT_TYPE-" + saleContractExcelForm.getPayMethodName());
			Object oSaleCurrency = map.get("CURRENCY-" + saleContractExcelForm.getSaleCurrency());
			Object oCustomerName = map.get("CUSTOMERNAME-" + saleContractExcelForm.getCustomerName());

			Area area = areaDao.getArea(saleContractExcelForm.getRegionName());


			if (oContractTypeName == null) {
				throw new AppException("序列为：" + saleContractExcelForm.getOrder() + " 的合同类型不合规范");
			}
			if (oPaymentTypeId == null) {
				throw new AppException("序列为：" + saleContractExcelForm.getOrder() + " 的付款方式不合规范");
			}
			if (oSaleCurrency == null) {
				throw new AppException("序列为：" + saleContractExcelForm.getOrder() + " 的币别不合规范");
			}
			if (area.getRowId() == null) {
				throw new AppException("序列为：" + saleContractExcelForm.getOrder() + " 的区域名称不合规范");
			}
			if (oCustomerName == null) {
				throw new AppException("序列为：" + saleContractExcelForm.getOrder() + " 客户名称不存在");
			}

//			saleContract.setCreatedBy(11111L);
			saleContract.setCreatedBy(staffId);
			saleContract.setContractNo(saleContractExcelForm.getContractNo());
			saleContract.setContractTitle(saleContractExcelForm.getContractTitle());
			saleContract.setContractType((Long) oContractTypeName);
//			saleContract.setCustomerName(saleContractExcelForm.getCustomerName());
			saleContract.setCustomer((Long) oCustomerName);
			saleContract.setPayMethod((Long) oPaymentTypeId);
			saleContract.setClientContractor(saleContractExcelForm.getClientContractor());
			saleContract.setSignDate(DateUtils.parseDate(saleContractExcelForm.getSignDate()));
			saleContract.setSaleCurrency((Long) oSaleCurrency);
			saleContract.setGetNoDate(DateUtils.parseDate(saleContractExcelForm.getGetNoDate()));
			saleContract.setTotalAmount((saleContractExcelForm.getTotalAmount() == null) ? null
					: (new BigDecimal(saleContractExcelForm.getTotalAmount())));

			saleContract.setPaymentAmount((saleContractExcelForm.getPaymentAmount() == null) ? null
					: (new BigDecimal(saleContractExcelForm.getPaymentAmount())));

			saleContract.setUnPaymentAmount((saleContractExcelForm.getUnPaymentAmount() == null) ? null
					: (new BigDecimal(saleContractExcelForm.getUnPaymentAmount())));

			saleContract.setTicketAmount((saleContractExcelForm.getTicketAmount() == null) ? null
					: (new BigDecimal(saleContractExcelForm.getTicketAmount())));

			saleContract.setUnTicketAmount((saleContractExcelForm.getUnTicketAmount() == null) ? null
					: (new BigDecimal(saleContractExcelForm.getUnTicketAmount())));

			saleContract.setBusinessPerson(saleContractExcelForm.getBusinessPerson());

			saleContract.setAreaId(area.getRowId());
			// saleContract.setCountryId(saleContractExcelForm.getCountryName());
			saleContract.setTotalPower(saleContractExcelForm.getTotalPower());

			saleContracts.add(saleContract);
			strs.add(saleContractExcelForm.getContractNo());
		}

		// 1.校验产品型号是否存在
		List<SaleContract> checkContractNoIsExist = saleContractDao.checkContractNoIsExist(strs);
		StringBuilder str = new StringBuilder();
		str.append("序列：");

		if (!CollectionUtils.isEmpty(checkContractNoIsExist)) {
			for (SaleContract saleContract : checkContractNoIsExist) {
				int indexOf = strs.indexOf(saleContract.getContractNo());
				str.append("" + (indexOf + 1) + ",");
			}
			String substring = str.substring(0, str.length() - 1);
			substring = substring + " 的合同编号已存在！";
			throw new AppException(substring);

		}
		// 2.批量导入产品信息
		saleContractDao.insterBatchSaleContract(saleContracts);

	}

	/**
	 * 导入产品明细
	 * 
	 * @param saleContractDetailExcelForms
	 */
	@Transactional
	public void importExcelSaleContractDetail(List<SaleContractDetailExcelForm> saleContractDetailExcelForms) {

		// 获取当前操作人
		 String token = RequestContext.get().getToken();
		 User loginUser = redisService.getLoginUserByToken(token);
        Long staffId = RequestContext.get().getStaffId();
		List<SaleContractDetail> saleContractDetails = new ArrayList<SaleContractDetail>();

		List<String> strs = new ArrayList<String>();

		HashMap<String, Object> map = new HashMap<String, Object>();

		HashMap<String, Object> newMap = new HashMap<String, Object>();

		Set<String> contractNos = new HashSet<String>();

		Set<String> productNos = new HashSet<String>();

		for (SaleContractDetailExcelForm saleContractDetailExcelForm : saleContractDetailExcelForms) {
			contractNos.add(saleContractDetailExcelForm.getSaleContractNo());
			productNos.add(saleContractDetailExcelForm.getProductionNo());
		}

		List<String> contractNos1 = new ArrayList<String>(contractNos);
		List<String> productNos1 = new ArrayList<String>(productNos);

		List<SaleContract> checkContractNoIsExist = saleContractDao.checkContractNoIsExist(contractNos1);
		List<Product> checkProductNoIsExist = productDao.checkProductNoIsExist(productNos1);

		for (SaleContract saleContract : checkContractNoIsExist) {
			newMap.put(saleContract.getContractNo(), saleContract.getRowId());
		}

		for (Product product : checkProductNoIsExist) {
			newMap.put(product.getProductNo(), product.getRowId());
		}

		// 字典翻译产品单位
		String productUnit = saleCommonService.getByTpye("PRODUCT_UNIT");
		JSONObject parseObject4 = JSONObject.parseObject(productUnit);
		List<Code> allCodeList4 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
		if (!CollectionUtils.isEmpty(allCodeList4)) {
			for (Code code : allCodeList4) {
				map.put("PRODUCT_UNIT-" + code.getCode(), code.getRowId());
			}
		}

		for (SaleContractDetailExcelForm saleContractDetailExcelForm : saleContractDetailExcelForms) {

			Object oContract = newMap.get(saleContractDetailExcelForm.getSaleContractNo());
			Object oProduct = newMap.get(saleContractDetailExcelForm.getProductionNo());
			Object oUnit = map.get("PRODUCT_UNIT-" + saleContractDetailExcelForm.getUnit());

			if (oContract == null) {
				throw new AppException("序列为：" + saleContractDetailExcelForm.getOrder() + "的合同编号不存在！");
			}

			if (oProduct == null) {
				throw new AppException("序列为：" + saleContractDetailExcelForm.getOrder() + "的产品型号不存在！");
			}
			if (oUnit == null) {
				throw new AppException("序列为：" + saleContractDetailExcelForm.getOrder() + "的单位不合规范！");
			}

			SaleContractDetail saleContractDetail = new SaleContractDetail();

//			saleContractDetail.setCreatedBy(11111L);
			 saleContractDetail.setCreatedBy(loginUser.getRowId());
			 saleContractDetail.setCreatedBy(staffId);
			saleContractDetail.setSaleContractId((Long) oContract);
			saleContractDetail.setProductionId((Long) oProduct);
			
			saleContractDetail.setNum((saleContractDetailExcelForm.getNum() == null) ? null
					: (new BigDecimal(saleContractDetailExcelForm.getNum())));

			saleContractDetail.setUnitPrice((saleContractDetailExcelForm.getUnitPrice() == null) ? null
					: (new BigDecimal(saleContractDetailExcelForm.getUnitPrice())));

			saleContractDetail.setUnit((Long) oUnit);
			saleContractDetail.setAmount((saleContractDetailExcelForm.getAmount() == null) ? null
					: (new BigDecimal(saleContractDetailExcelForm.getAmount())));

			saleContractDetail.setPower(saleContractDetailExcelForm.getPower());
			saleContractDetail.setTotalPower(saleContractDetailExcelForm.getTotalPower());
			saleContractDetails.add(saleContractDetail);

		}

		saleContractDao.insterBatchSaleContractDetail(saleContractDetails);
	}

	/**
	 * 导入开票信息
	 * 
	 * @param invoiceExcelForms
	 */
	@Transactional
	public void importExcelInvoice(List<InvoiceExcelForm> invoiceExcelForms) {

		// 获取当前操作人
//		 String token = RequestContext.get().getToken();
//		 User loginUser = redisService.getLoginUserByToken(token);

        if(!invoiceExcelForms.isEmpty()) {
            Long staffId = RequestContext.get().getStaffId();
            List<InvoiceForm> invoiceForms = new ArrayList<InvoiceForm>();

            HashMap<String, Object> map = new HashMap<String, Object>();
            HashMap<String, Object> newMap = new HashMap<String, Object>();

            Set<String> contractNos = new HashSet<String>();

            for (InvoiceExcelForm invoiceExcelForm : invoiceExcelForms) {
                contractNos.add(invoiceExcelForm.getSaleContractNo());
            }

            List<String> contractNos1 = new ArrayList<String>(contractNos);

            List<SaleContract> checkContractNoIsExist = saleContractDao.checkContractNoIsExist(contractNos1);

            for (SaleContract saleContract : checkContractNoIsExist) {
                newMap.put(saleContract.getContractNo(), saleContract.getRowId());
            }

            // 字典翻票据类型
            String productUnit = saleCommonService.getByTpye("INVOICE_TYPE");
            JSONObject parseObject4 = JSONObject.parseObject(productUnit);
            List<Code> allCodeList4 = JSONObject.parseArray(parseObject4.getString("data"), Code.class);
            if (!CollectionUtils.isEmpty(allCodeList4)) {
                for (Code code : allCodeList4) {
                    map.put("INVOICE_TYPE-" + code.getCode(), code.getRowId());
                }
            }

            for (InvoiceExcelForm invoiceExcelForm : invoiceExcelForms) {

                InvoiceForm invoiceForm = new InvoiceForm();

                Object oContract = newMap.get(invoiceExcelForm.getSaleContractNo());
                Object oInvoiceType = map.get("INVOICE_TYPE-" + invoiceExcelForm.getInvoiceTypeName());

                if (oContract == null) {
                    throw new AppException("序列为：" + invoiceExcelForm.getOrder() + "的合同编号不存在！");
                }
                if (oInvoiceType == null) {
                    throw new AppException("序列为：" + invoiceExcelForm.getOrder() + "的票据类型不合规范！");
                }

                invoiceForm.setSaleContractId((Long) oContract);
//			invoiceForm.setCreatedBy(1111L);
                invoiceForm.setCreatedBy(staffId);
                invoiceForm.setKprq(DateUtils.parseDate(invoiceExcelForm.getInvoiceDate()));
                invoiceForm.setInvoiceContext(invoiceExcelForm.getInvoiceContext());
                invoiceForm.setInvoiceAmount((invoiceExcelForm.getInvoiceAmount() == null) ? null
                        : (new BigDecimal(invoiceExcelForm.getInvoiceAmount())));

                invoiceForm.setInvoiceTypeId((Long) oInvoiceType);
                invoiceForm.setInvoiceNo(invoiceExcelForm.getInvoiceNo());
                invoiceForm.setInvoiceUserName(invoiceExcelForm.getInvoiceUserName());
                invoiceForm.setRemark(invoiceExcelForm.getRemark());
                invoiceForms.add(invoiceForm);
            }
            saleContractDao.insterBatchSaleInvoice(invoiceForms);
        }

	}
    
    
    
    
    
}
