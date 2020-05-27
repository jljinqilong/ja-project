package com.champlink.common.form.sale.contract;

import com.champlink.common.form.BaseForm;

//销售合同
public class SaleContractForm extends BaseForm {

    /**
     * jsl
     * 2018-08-03
     * 签约起始日期
     */
    private String contractDateQ;

    /**
     * jsl
     * 2018-08-03
     * 签约结束日期
     */
    private String contractDateZ;

    /**
     * 合同号
     * jsl
     * 2018-08-03
     */
    private String contractNo;

    /**
     * 客户id
     * jsl
     * 2018-08-03
     */
    private Long customer;

    /**
     * 付款方式
     * jsl
     * 2018-08-03
     */
    private Long payMethod;

    /**
     * 基地Id
     *jsl
     * 2018-08-03
     */
    private Long orgId;

    /**
     * 合同类型
     * jsl
     * 2018-08-03
     */
    private Long contractType;

    /*
     * @Author jsl
     * @Date 部门 2018/8/8
     * @Description    
     **/
    private Long deptId;

    /**
     * @Author jsl
     * @Date 负责人 2018/8/8
     * @Description
     **/
    private Long ourSignatory;

    /**
     * @Author jsl
     * @Date 合同标题 2018/8/10
     * @Description
     **/
    private String contractTitle;

    private Long transferMan;

    public Long getTransferMan() {
        return transferMan;
    }

    public void setTransferMan(Long transferMan) {
        this.transferMan = transferMan;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle != null ? contractTitle.trim():null ;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getOurSignatory() {
        return ourSignatory;
    }

    public void setOurSignatory(Long ourSignatory) {
        this.ourSignatory = ourSignatory;
    }

    public String getContractDateQ() {
        return contractDateQ;
    }

    public void setContractDateQ(String contractDateQ) {
        this.contractDateQ = contractDateQ;
    }

    public String getContractDateZ() {
        return contractDateZ;
    }

    public void setContractDateZ(String contractDateZ) {
        this.contractDateZ = contractDateZ;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo !=null ? contractNo.trim():null;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getContractType() {
        return contractType;
    }

    public void setContractType(Long contractType) {
        this.contractType = contractType;
    }

    @Override
    public String toString() {
        return "SaleContractForm{" +
                "contractDateQ=" + contractDateQ +
                ", contractDateZ=" + contractDateZ +
                ", contractNo='" + contractNo + '\'' +
                ", customer=" + customer +
                ", payMethod=" + payMethod +
                ", orgId=" + orgId +
                ", contractType=" + contractType +
                '}';
    }
}
