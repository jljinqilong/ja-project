package com.champlink.common.domain.sale.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;
import com.champlink.common.domain.sale.area.CountryProvCity;

//销售合同
public class SaleContract extends BaseBean {

    /**
     * @Author jsl
     * @Date 16:38 2018/8/4
     * @Description 创建时间
     **/
    @JSONField(format = "yyyy-MM-dd")
    private Date createdDate;

    /**
     * @Author jsl
     * @Date 16:39 2018/8/4
     * @Description    创建人
     **/
    private String userName;

    /**
     * @Author jsl
     * @Date 部门id 2018/8/7
     * @Description
     **/
    @NotBlank(message = "部门不能为空")
    private Long departId;
    private String deptName;        //部门名称

    /**
     * 基地id
     */
    @NotBlank(message = "基地不能为空")
    private Long orgId;
    private String orgName; //基地名称
    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 合同类型
     */
    @NotBlank(message = "合同类型不能为空")
    private Long contractType;
    private String contractTypeName;    //合同类型名称

    /**
     * 合同标题
     */
    private String contractTitle;

    /**
     * 客户id
     */
    @NotBlank(message = "客户不能为空")
    private Long customer;
    /**
     * @Author jsl
     * @Date 9:07 2018/8/6
     * @Description    客户名称
     **/
    private String customerName;

    /**
     * 付款方式id
     */
    @NotBlank(message = "付款方式不能为空")
    private Long payMethod;
    private String payMethodName;   //付款方式名称

    /**
     * 客户方签约人
     */
    private String clientContractor;

    /**
     * @Author jsl
     * @Date 客户签约人姓名 2018/8/7
     * @Description
     **/

    private String clientName;

    /**
     * 我方签约人
     */
    private String ourSignatory;

    /**
     * 签约时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date signDate;

    /**
     * 已回款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 未回款金额
     */
    private BigDecimal unPaymentAmount;

    /**
     * 开票金额
     */
    private BigDecimal ticketAmount;

    /**
     * 未开票金额
     */
    private BigDecimal unTicketAmount;

    /**
     * 合同总金额
     */
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 合同起始时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date contractDateS;

    /**
     * 合同截至时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date contractDateE;

    private String token;

    /**
     * @Author jsl
     * @Date 15:41 2018/8/6
     * @Description     合同产品明细
     **/
    private List<SaleContractDetail> saleContractDetailList;
    /**
     * @Author jsl
     * @Date 付款计划明细 2018/8/6
     * @Description
     **/
    private List<SalePayPlan> salePayPlanList;
    /**
     * @Author jsl
     * @Date 发票集合 2018/8/7
     * @Description
     **/
    private List<InvoiceForm> invoiceList;

    /**
     * @Author jsl
     * @Date 发票信息 2018/8/7
     * @Description
     **/
    private InvoiceForm invoiceForm;

    /**
     * @Author jsl
     * @Date 下次更新时间 2018/8/9
     * @Description
     **/
    @JSONField(format = "yyyy-MM-dd")
    private Date nextTime;

    /**
     * @Author jsl
     * @Date 币别 2018/8/10
     * @Description
     **/
    private Long saleCurrency;
    /**
     * @Author jsl
     * @Date 取号日期 2018/8/10
     * @Description
     **/
    @JSONField(format = "yyyy-MM-dd")
    private Date getNoDate;

    /**
     * @Author jsl
     * @Date 业务人员 2018/8/10
     * @Description
     **/
    private String businessPerson;
    /**
     * @Author jsl
     * @Date 所属区域 2018/8/10
     * @Description
     **/
    private Long areaId;

    /**
     * @Author jsl
     * @Date 区域名称 2018/8/13
     * @Description
     **/
    private String regionName;

    /**
     * @Author jsl
     * @Date 项目所在地 2018/8/10
     * @Description
     **/
    private Long projectArea;
    /**
     * @Author jsl
     * @Date 国家 2018/8/10
     * @Description
     **/
    private Long countryId;

    /**
     * @Author jsl
     * @Date 总功率 2018/8/10
     * @Description
     **/
    private String totalPower;

    /**
     * @Author jsl
     * @Date 已计划的回款总金额 2018/8/13
     * @Description
     **/
    private BigDecimal planedAmount;

    /**
     * @Author jsl
     * @Date 未计划的金额 2018/8/13
     * @Description
     **/
    private BigDecimal unPlanedAmount;

    /**
     * @Author jsl
     * @Date 产品明细 2018/8/13
     * @Description
     **/
    private List<SaleContractDetail> productList;


    /**
     * @Author jsl
     * @Date  2018/8/27
     * @Description     省份
     **/
    private Long provinceId;

    /**
     * @Author jsl
     * @Date  2018/8/27
     * @Description     市
     **/
    private Long cityId;

    private String countryName;
    private String provinceName;
    private String cityName;


    /**
     * @Author jsl
     * @Date 16:10 2018/8/27
     * @Description 发货地
     **/
    private Long deliveryAddress;

    /**
     * @Author jsl
     * @Date 16:10 2018/8/27
     * @Description    交期列表
     **/
    private List<SaleDelivery> saleDeliveryList;

    private List<CountryProvCity> countryList;

    private List<CountryProvCity> provinceList;

    private List<CountryProvCity> cityList;

    //询单id
    private Long inquiriesId;
    //询单编号
    private String inquiriesNo;

    public Long getInquiriesId() {
        return inquiriesId;
    }

    public void setInquiriesId(Long inquiriesId) {
        this.inquiriesId = inquiriesId;
    }

    public String getInquiriesNo() {
        return inquiriesNo;
    }

    public void setInquiriesNo(String inquiriesNo) {
        this.inquiriesNo = inquiriesNo;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<CountryProvCity> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryProvCity> countryList) {
        this.countryList = countryList;
    }

    public List<CountryProvCity> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<CountryProvCity> provinceList) {
        this.provinceList = provinceList;
    }

    public List<CountryProvCity> getCityList() {
        return cityList;
    }

    public void setCityList(List<CountryProvCity> cityList) {
        this.cityList = cityList;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public List<SaleDelivery> getSaleDeliveryList() {
        return saleDeliveryList;
    }

    public void setSaleDeliveryList(List<SaleDelivery> saleDeliveryList) {
        this.saleDeliveryList = saleDeliveryList;
    }

    public BigDecimal getPlanedAmount() {
        return planedAmount;
    }

    public void setPlanedAmount(BigDecimal planedAmount) {
        this.planedAmount = planedAmount;
    }

    public BigDecimal getUnPlanedAmount() {
        return unPlanedAmount;
    }

    public void setUnPlanedAmount(BigDecimal unPlanedAmount) {
        this.unPlanedAmount = unPlanedAmount;
    }

    public List<SaleContractDetail> getProductList() {
        return productList;
    }

    public void setProductList(List<SaleContractDetail> productList) {
        this.productList = productList;
    }

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public String getBusinessPerson() {
        return businessPerson;
    }

    public void setBusinessPerson(String businessPerson) {
        this.businessPerson = businessPerson;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(Long projectArea) {
        this.projectArea = projectArea;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getSaleCurrency() {
        return saleCurrency;
    }

    public void setSaleCurrency(Long saleCurrency) {
        this.saleCurrency = saleCurrency;
    }

    public InvoiceForm getInvoiceForm() {
        return invoiceForm;
    }

    public void setInvoiceForm(InvoiceForm invoiceForm) {
        this.invoiceForm = invoiceForm;
    }

    public List<SalePayPlan> getSalePayPlanList() {
        return salePayPlanList;
    }

    public void setSalePayPlanList(List<SalePayPlan> salePayPlanList) {
        this.salePayPlanList = salePayPlanList;
    }

    public List<SaleContractDetail> getSaleContractDetailList() {
        return saleContractDetailList;
    }

    public void setSaleContractDetailList(List<SaleContractDetail> saleContractDetailList) {
        this.saleContractDetailList = saleContractDetailList;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Long getContractType() {
        return contractType;
    }

    public void setContractType(Long contractType) {
        this.contractType = contractType;
    }


    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Long payMethod) {
        this.payMethod = payMethod;
    }

    public String getOurSignatory() {
        return ourSignatory;
    }

    public void setOurSignatory(String ourSignatory) {
        this.ourSignatory = ourSignatory;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getUnPaymentAmount() {
        return unPaymentAmount;
    }

    public void setUnPaymentAmount(BigDecimal unPaymentAmount) {
        this.unPaymentAmount = unPaymentAmount;
    }

    public BigDecimal getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(BigDecimal ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public BigDecimal getUnTicketAmount() {
        return unTicketAmount;
    }

    public void setUnTicketAmount(BigDecimal unTicketAmount) {
        this.unTicketAmount = unTicketAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getContractDateS() {
        return contractDateS;
    }

    public void setContractDateS(Date contractDateS) {
        this.contractDateS = contractDateS;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getContractDateE() {
        return contractDateE;
    }

    public void setContractDateE(Date contractDateE) {
        this.contractDateE = contractDateE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }


    public String getPayMethodName() {
        return payMethodName;
    }

    public void setPayMethodName(String payMethodName) {
        this.payMethodName = payMethodName;
    }

    public List<InvoiceForm> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceForm> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public String getClientContractor() {
        return clientContractor;
    }

    public void setClientContractor(String clientContractor) {
        this.clientContractor = clientContractor;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Date getGetNoDate() {
        return getNoDate;
    }

    public void setGetNoDate(Date getNoDate) {
        this.getNoDate = getNoDate;
    }

    public Long getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Long deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "SaleContract{" +
                "createdDate=" + createdDate +
                ", userName='" + userName + '\'' +
                ", departId=" + departId +
                ", deptName='" + deptName + '\'' +
                ", orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractType=" + contractType +
                ", contractTypeName='" + contractTypeName + '\'' +
                ", contractTitle=" + contractTitle +
                ", customer=" + customer +
                ", customerName='" + customerName + '\'' +
                ", payMethod=" + payMethod +
                ", payMethodName='" + payMethodName + '\'' +
                ", clientContractor=" + clientContractor +
                ", clientName='" + clientName + '\'' +
                ", ourSignatory=" + ourSignatory +
                ", signDate=" + signDate +
                ", paymentAmount=" + paymentAmount +
                ", unPaymentAmount=" + unPaymentAmount +
                ", ticketAmount=" + ticketAmount +
                ", unTicketAmount=" + unTicketAmount +
                ", totalAmount=" + totalAmount +
                ", remark='" + remark + '\'' +
                ", contractDateS=" + contractDateS +
                ", contractDateE=" + contractDateE +
                ", token='" + token + '\'' +
                ", saleContractDetailList=" + saleContractDetailList +
                ", salePayPlanList=" + salePayPlanList +
                '}';
    }
}
