package com.champlink.common.domain.sale.stock;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/14 19:32
 **/
public class StockUp extends BaseBean {
    private String stockNo;

    private String salesman;

    private Long stockAddress;

    @JSONField(format = "yyyy-MM-dd")
    private Date stockDate;

    private String oaNo;

    private Long customer;

    private String customerName;

    private String contractNo;

    private Long paymentInfo;

    private String contractAddress;

    private BigDecimal sendNum;

    private String newComponentmodel;

    private String oldComponentmodel;

    private String componentType;

    private String batteryProcess;

    private BigDecimal packageNum;

    private String coating;

    private String componentLevel;

    private String singlePower;

    private Long typeA;

    private BigDecimal totalSendNum;

    private String unit;

    private Long deliveryMethod;

    private String mode;

    private String alProcess;

    private String junctionBox;

    private String shipmentsInspection;

    private String powerRequire;

    private String otherInstruction;

    private String isDeclaration;

    private String invoiceNo;

    private String customerRequirementExw;

    private String customerRequirementEtd;

    private String customerRequirementEta;

    private String goodsAgent;

    private String shipmentPort;

    private String destinationPort;

    private String customerAddress;

    private String contacts;

    private String telephone;

    private String destinationCountry;

    private String boatCompany;

    private String telex;

    private String isCover;

    private String alVender;

    private BigDecimal lineNum;

    private Long eva;

    private String glass;

    private String wiringHead;

    private String nameplate;

    private String bomid;

    private String power;

    private String tradeType;

    private String packageMethod;

    private String lineLength;

    private String holeLocation;

    @JSONField(format = "yyyy-MM-dd")
    private Date checkDate;

    private String sureOne;

    private String sureTwo;

    private Date planDate;

    private Date actualDate;

    private BigDecimal blockNum;

    private BigDecimal tileNum;

    private BigDecimal unsentNum;

    @JSONField(format = "yyyy-MM-dd")
    private Date declareTime;

    private String declareBroker;

    private String declareNo;

    @JSONField(format = "yyyy-MM-dd")
    private Date loadTime;

    @JSONField(format = "yyyy-MM-dd")
    private Date leaveTime;

    private String loadNo;

    private String invoice;

    private String billNo;

    private String invoiceBill;

    private String invoiceBillCopy;

    private String policy;

    private String dn;

    private String inv;

    private String bl;
    private String jcustomerName;

    public String getJcustomerName() {
        return jcustomerName;
    }

    public void setJcustomerName(String jcustomerName) {
        this.jcustomerName = jcustomerName;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public Long getStockAddress() {
        return stockAddress;
    }

    public void setStockAddress(Long stockAddress) {
        this.stockAddress = stockAddress;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Long getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(Long paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public BigDecimal getSendNum() {
        return sendNum;
    }

    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }

    public String getNewComponentmodel() {
        return newComponentmodel;
    }

    public void setNewComponentmodel(String newComponentmodel) {
        this.newComponentmodel = newComponentmodel;
    }

    public String getOldComponentmodel() {
        return oldComponentmodel;
    }

    public void setOldComponentmodel(String oldComponentmodel) {
        this.oldComponentmodel = oldComponentmodel;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getBatteryProcess() {
        return batteryProcess;
    }

    public void setBatteryProcess(String batteryProcess) {
        this.batteryProcess = batteryProcess;
    }

    public BigDecimal getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(BigDecimal packageNum) {
        this.packageNum = packageNum;
    }

    public String getCoating() {
        return coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public String getComponentLevel() {
        return componentLevel;
    }

    public void setComponentLevel(String componentLevel) {
        this.componentLevel = componentLevel;
    }

    public String getSinglePower() {
        return singlePower;
    }

    public void setSinglePower(String singlePower) {
        this.singlePower = singlePower;
    }

    public Long getTypeA() {
        return typeA;
    }

    public void setTypeA(Long typeA) {
        this.typeA = typeA;
    }

    public BigDecimal getTotalSendNum() {
        return totalSendNum;
    }

    public void setTotalSendNum(BigDecimal totalSendNum) {
        this.totalSendNum = totalSendNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Long deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAlProcess() {
        return alProcess;
    }

    public void setAlProcess(String alProcess) {
        this.alProcess = alProcess;
    }

    public String getJunctionBox() {
        return junctionBox;
    }

    public void setJunctionBox(String junctionBox) {
        this.junctionBox = junctionBox;
    }

    public String getShipmentsInspection() {
        return shipmentsInspection;
    }

    public void setShipmentsInspection(String shipmentsInspection) {
        this.shipmentsInspection = shipmentsInspection;
    }

    public String getPowerRequire() {
        return powerRequire;
    }

    public void setPowerRequire(String powerRequire) {
        this.powerRequire = powerRequire;
    }

    public String getOtherInstruction() {
        return otherInstruction;
    }

    public void setOtherInstruction(String otherInstruction) {
        this.otherInstruction = otherInstruction;
    }

    public String getIsDeclaration() {
        return isDeclaration;
    }

    public void setIsDeclaration(String isDeclaration) {
        this.isDeclaration = isDeclaration;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerRequirementExw() {
        return customerRequirementExw;
    }

    public void setCustomerRequirementExw(String customerRequirementExw) {
        this.customerRequirementExw = customerRequirementExw;
    }

    public String getCustomerRequirementEtd() {
        return customerRequirementEtd;
    }

    public void setCustomerRequirementEtd(String customerRequirementEtd) {
        this.customerRequirementEtd = customerRequirementEtd;
    }

    public String getCustomerRequirementEta() {
        return customerRequirementEta;
    }

    public void setCustomerRequirementEta(String customerRequirementEta) {
        this.customerRequirementEta = customerRequirementEta;
    }

    public String getGoodsAgent() {
        return goodsAgent;
    }

    public void setGoodsAgent(String goodsAgent) {
        this.goodsAgent = goodsAgent;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getBoatCompany() {
        return boatCompany;
    }

    public void setBoatCompany(String boatCompany) {
        this.boatCompany = boatCompany;
    }

    public String getTelex() {
        return telex;
    }

    public void setTelex(String telex) {
        this.telex = telex;
    }

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public String getAlVender() {
        return alVender;
    }

    public void setAlVender(String alVender) {
        this.alVender = alVender;
    }

    public BigDecimal getLineNum() {
        return lineNum;
    }

    public void setLineNum(BigDecimal lineNum) {
        this.lineNum = lineNum;
    }

    public Long getEva() {
        return eva;
    }

    public void setEva(Long eva) {
        this.eva = eva;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getWiringHead() {
        return wiringHead;
    }

    public void setWiringHead(String wiringHead) {
        this.wiringHead = wiringHead;
    }

    public String getNameplate() {
        return nameplate;
    }

    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    public String getBomid() {
        return bomid;
    }

    public void setBomid(String bomid) {
        this.bomid = bomid;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPackageMethod() {
        return packageMethod;
    }

    public void setPackageMethod(String packageMethod) {
        this.packageMethod = packageMethod;
    }

    public String getLineLength() {
        return lineLength;
    }

    public void setLineLength(String lineLength) {
        this.lineLength = lineLength;
    }

    public String getHoleLocation() {
        return holeLocation;
    }

    public void setHoleLocation(String holeLocation) {
        this.holeLocation = holeLocation;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getSureOne() {
        return sureOne;
    }

    public void setSureOne(String sureOne) {
        this.sureOne = sureOne;
    }

    public String getSureTwo() {
        return sureTwo;
    }

    public void setSureTwo(String sureTwo) {
        this.sureTwo = sureTwo;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public BigDecimal getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(BigDecimal blockNum) {
        this.blockNum = blockNum;
    }

    public BigDecimal getTileNum() {
        return tileNum;
    }

    public void setTileNum(BigDecimal tileNum) {
        this.tileNum = tileNum;
    }

    public BigDecimal getUnsentNum() {
        return unsentNum;
    }

    public void setUnsentNum(BigDecimal unsentNum) {
        this.unsentNum = unsentNum;
    }

    public Date getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(Date declareTime) {
        this.declareTime = declareTime;
    }

    public String getDeclareBroker() {
        return declareBroker;
    }

    public void setDeclareBroker(String declareBroker) {
        this.declareBroker = declareBroker;
    }

    public String getDeclareNo() {
        return declareNo;
    }

    public void setDeclareNo(String declareNo) {
        this.declareNo = declareNo;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getLoadNo() {
        return loadNo;
    }

    public void setLoadNo(String loadNo) {
        this.loadNo = loadNo;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getInvoiceBill() {
        return invoiceBill;
    }

    public void setInvoiceBill(String invoiceBill) {
        this.invoiceBill = invoiceBill;
    }

    public String getInvoiceBillCopy() {
        return invoiceBillCopy;
    }

    public void setInvoiceBillCopy(String invoiceBillCopy) {
        this.invoiceBillCopy = invoiceBillCopy;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }
}
