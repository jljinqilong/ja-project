package com.champlink.sale.dao.contract;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.contract.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/4 11:48
 **/
@Mapper
public interface SaleContractDao {

    /**
     * @Author jsl
     * @Date 查询 2018/8/6 
     * @Description    
     **/
    Page<SaleContract> searchSaleContractList(Paginater paginater);

    /**
     * @Author jsl
     * @Date 获取最后一个合同单号 2018/8/6 
     * @Description    
     **/
    String getLastSaleContractNo();

    /**
     * @Author jsl
     * @Date 插入合同信息 2018/8/6
     * @Description    
     **/
    int insertSaleContract(SaleContract saleContract);

    /**
     * @Author jsl
     * @Date 插入产品明细表 2018/8/6 
     * @Description    
     **/
    void insertSaleContractDetail(@Param("saleContractId") Long saleContractId, @Param("saleContractDetailList") List<SaleContractDetail> saleContractDetailList);

    /**
     * @Author jsl
     * @Date 插入付款计划明细表 2018/8/6
     * @Description    
     **/
    void insertSalePayPlan(@Param("saleContractId") Long saleContractId, @Param("salePayPlanList") List<SalePayPlan> salePayPlanList);

    /**
     * @Author jsl
     * @Date 删除付款计划 2018/8/6
     * @Description
     **/
    void delPayPlanById(Long rowId);

    /**
     * @Author jsl
     * @Date 删除合同产品明细 2018/8/6 
     * @Description    
     **/
    void delSaleContractProdDetailById(Long rowId);

    /**
     * @Author jsl
     * @Date 删除合同 2018/8/6 
     * @Description    
     **/
    void delSaleContractById(Long rowId);

    /**
     * @Author jsl
     * @Date 根据Id查询销售合同信息 2018/8/6 
     * @Description    
     **/
    SaleContract getSaleContractById(Long rowId);

    void update(SaleContract saleContract);

    /**
     * @Author jsl
     * @Date 插入发票信息 2018/8/6 
     * @Description    
     **/
    void insertSaleInvoice(InvoiceForm invoice);

    /**
     * @Author jsl
     * @Date 根据合同id和发票号码查询 2018/8/6
     * @Description
     **/
    int selectInvoiceByIdAndNo(@Param("saleContractId") Long saleContractId, @Param("invoiceNo") String invoiceNo,@Param("rowId") Long rowId);

    /**
     * @Author jsl
     * @Date 根据销售单id查询发票信息 2018/8/7 
     * @Description    
     **/
    List<InvoiceForm> searchInvoiceById(Long saleContractId);

    /**
     * @Author jsl
     * @Date 删除发票信息 2018/8/7 
     * @Description    
     **/
    boolean delInvoieByid(Long rowId);

    /**
     * @Author jsl
     * @Date 根据发票id查询单张发票的明细信息 2018/8/7
     * @Description    
     **/
    InvoiceForm getSingleInvoiceById(Long invoiceId);

    /**
     * @Author jsl
     * @Date 根据rowId 和 saleCotractId查询记录 2018/8/7
     * @Description
     **/
    int searchInvoiceByInvoiceIdAndSaleContractId(@Param("rowId") Long rowId, @Param("saleContractId") Long saleContractId);

    /**
     * @Author jsl
     * @Date 更新发票信息 2018/8/7
     * @Description    
     **/
    void updateInvoice(@Param("rowId") Long rowId, @Param("saleContractId") Long saleContractId,@Param("invoiceForm") InvoiceForm invoiceForm);

    /**
     * @Author jsl
     * @Date 根据id查询合同总金额 2018/8/7
     * @Description    
     **/
    BigDecimal selectTotleAmountById(Long saleContractId);
    /**
     * @Author jsl
     * @Date 已存在的发票金额 2018/8/7
     * @Description
     **/
    BigDecimal selectExistsInvoiceAmountById(Long saleContractId);
    
    /**
     * @Author jsl
     * @Date 除此条记录以外的所有的发票之和 2018/8/7
     * @Description    
     **/

    BigDecimal selectTotalInvoiceAmountNoThis(@Param("saleContractId") Long saleContractId, @Param("rowId") Long rowId);

    /**
     * @Author jsl
     * @Date 根据维护的发票金额更新合同中的已开票和未开票金额 2018/8/8
     * @Description
     **/
    void updateContractFpAmount(@Param("saleContractId") Long saleContractId, @Param("invoiceAmount") BigDecimal invoiceAmount);

    /**
     * @Author jsl
     * @Date 根据合同id查询所有发票金额 2018/8/8
     * @Description    
     **/
    BigDecimal selectAllInvoiceAmount(Long saleContractId);

    /**
     * @Author jsl
     * @Date 根据合同id更新已开票和未开票金额 2018/8/8 
     * @Description    
     **/
    void updateContractFpAmountById(@Param("saleContractId") Long saleContractId, @Param("allInvoiceAmount") BigDecimal allInvoiceAmount);

    /**
     * @Author jsl
     * @Date 根据回款计划id查询已经回款的金额 2018/8/8
     * @Description
     **/
    BigDecimal getYfAmountById(Long payPlanId);

    /**
     * @Author jsl
     * @Date 根据计划单id，查询计划单信息 2018/8/8
     * @Description    
     **/
    SalePayPlan getSalePayPlanById(Long payPlanId);

    /**
     * @Author jsl
     * @Date 添加付款计划明细 2018/8/8
     * @Description    
     **/
    void insertSalePayPlanDetail(SalePayPlanDetail salePayPlanDetail);

    /**
     * @Author jsl
     * @Date 根据合同号，修改已回款金额和未回款金额 2018/8/8
     * @Description    
     **/
    void updatePayMentAmount(@Param("saleContractId") Long saleContractId, @Param("amount") BigDecimal amount);

    /**
     * @Author jsl
     * @Date 根据id查询单条计划付款明细 2018/8/8
     * @Description    
     **/
    SalePayPlanDetail getSalePayPlanDetailById(Long id);
    
    /**
     * @Author jsl
     * @Date 根据回款单id查询回款明细 2018/8/8
     * @Description    
     **/
    List<SalePayPlanDetail> getSalePayPlanDetail(Long payPlanId);

    /**
     * @Author jsl
     * @Date 根据明细id删除明细数据 2018/8/8
     * @Description    
     **/
    void delPayPlanDetailById(Long id);

    /**
     * @Author jsl
     * @Date 更新明细数据 2018/8/8
     * @Description
     **/
    void updateSalePayPlanDetail(SalePayPlanDetail salePayPlanDetail);

    /**
     * @Author jsl
     * @Date 获取实际回款总金额 2018/8/9
     * @Description
     **/
    BigDecimal getTotalPaymentAmount(Long saleContractId);

    /**
     * @Author jsl
     * @Date 更新合同中的已回款和未回款金额 2018/8/9 
     * @Description    
     **/
    void updatePayMentAmountById(@Param("saleContractId") Long saleContractId, @Param("totalAmount") BigDecimal totalAmount);

    /**
     * 查询客户的合同list
     * @Author created by barrett in 18-8-8 下午4:26
     */
    Page<SaleContract> searchSaleContractByCustomer(Paginater paginater);

    /**
     * @Author jsl
     * @Date 根据id 和计划单金额 更新合同中的已计划和未计划金额 2018/8/13
     * @Description
     **/
    void updatePlanedAmount(@Param("saleContractId") Long saleContractId, @Param("payAmount") BigDecimal payAmount);

    /**
     * @Author jsl
     * @Date 根据合同id更新已计划和未计划金额 2018/8/13
     * @Description
     **/
    void updatePlanedAmountById(@Param("saleContractId") Long saleContractId, @Param("allPlanedAmount") BigDecimal allPlanedAmount);

    /**
     * @Author jsl
     * @Date  2018/8/25
     * @Description   根据staffId查询合同列表
     **/
    Page getSaleContractListById(Paginater paginater);

    boolean updateTransferMan(@Param("staffId") Long staffId, @Param("rowId") Long rowId);

    
    /**
     * 批量插入销售合同信息
     * @param saleContracts
     */
    void insterBatchSaleContract(List<SaleContract> saleContracts);
    
    /**
     * 批量插入产品明细信息
     * @param saleContracts
     */
    void insterBatchSaleContractDetail(List<SaleContractDetail> saleContracts);
    
    /**
     * 批量插入开票信息
     * @param invoiceForms
     */
    void insterBatchSaleInvoice(List<InvoiceForm> invoiceForms);

    /**
     * @Author jsl
     * @Date 15:22 2018/8/27
     * @Description 查询是否存在合同号
     **/
    int getIsnewContract(String saleContractNo);

    /**
     * @Author jsl
     * @Date 15:28 2018/8/27
     * @Description    插入交期信息
     **/
    void insertSaleDelivery(@Param("rowId") Long rowId, @Param("saleDeliveryList") List<SaleDelivery> saleDeliveryList);

    /**
     * @Author jsl
     * @Date 15:56 2018/8/27
     * @Description 删除交期信息
     **/
    void delDeliveryById(Long rowId);

    /**
     * @Author jsl
     * @Date 17:15 2018/8/27
     * @Description     合同标题判重
     **/
    int getIsnewContractTitle(String saleContractTitle);
    
    /**
     * 通过合同编号查询是否有该合同
     * @param list
     * @return
     */
    List<SaleContract> checkContractNoIsExist(List<String> list);
}
