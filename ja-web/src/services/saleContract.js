import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// =========== 合同 ============
export async function listSaleContract(params) {
  return request(`${serverUrlPre}/sale/saleContract/list?${stringify(params)}`);
}

export async function getSaleContractById(id) {
  return request(`${serverUrlPre}/sale/saleContract/get/${id}`);
}

export async function listSaleContractByCustomerId(customerId) {
  return request(
    `${serverUrlPre}/sale/saleContract/getSaleContractByCustomer?customer=${customerId}`
  );
}

export async function addSaleContract(params) {
  return request(`${serverUrlPre}/sale/saleContract/add`, {
    method: 'POST',
    body: params,
  });
}

export async function delSaleContract(id) {
  return request(`${serverUrlPre}/sale/saleContract/del/${id}`, {
    method: 'POST',
  });
}

export async function editSaleContract(params) {
  return request(`${serverUrlPre}/sale/saleContract/update`, {
    method: 'POST',
    body: params,
  });
}

// =========== 回款计划 ============
export async function listPaymentPlanBySaleContractId(saleContractId) {
  return request(`${serverUrlPre}/sale/saleContract/getReturnMoney/${saleContractId}`);
}

export async function getPaymentPlanById(paymentPlanId) {
  return request(`${serverUrlPre}/sale/paymentPlan/get/${paymentPlanId}`);
}

export async function getAddPaymentPlanPeriodBySaleContractId(saleContractId) {
  return request(`${serverUrlPre}/sale/paymentPlan/getAddPeriod/${saleContractId}`);
}

export async function getUpdatePaymentPlanPeriodBySaleContractId(saleContractId, paymentPlanId) {
  return request(
    `${serverUrlPre}/sale/paymentPlan/getUpdatePeriod/${saleContractId}/${paymentPlanId}`
  );
}

export async function addPaymentPlan(params) {
  return request(`${serverUrlPre}/sale/paymentPlan/add`, {
    method: 'POST',
    body: params,
  });
}

export async function delPaymentPlan(paymentPlanId) {
  return request(`${serverUrlPre}/sale/paymentPlan/del/${paymentPlanId}`, {
    method: 'POST',
  });
}

export async function editPaymentPlan(params) {
  return request(`${serverUrlPre}/sale/paymentPlan/update`, {
    method: 'POST',
    body: params,
  });
}

// =========== 回款记录 ============
export async function listPaymentByPlanId(paymentPlanId) {
  return request(`${serverUrlPre}/sale/paymentDetail/getPaymentDetail/${paymentPlanId}`);
}

export async function getPaymentById(paymentId) {
  return request(`${serverUrlPre}/sale/paymentDetail/get/${paymentId}`);
}

export async function addPayment(params) {
  return request(`${serverUrlPre}/sale/paymentDetail/add`, {
    method: 'POST',
    body: params,
  });
}

export async function delPayment(paymentId) {
  return request(`${serverUrlPre}/sale/paymentDetail/del/${paymentId}`, {
    method: 'POST',
  });
}

export async function editPayment(params) {
  return request(`${serverUrlPre}/sale/paymentDetail/update`, {
    method: 'POST',
    body: params,
  });
}

// =========== 发票 ============
export async function listInvoiceBySaleContractId(saleContractId) {
  return request(`${serverUrlPre}/sale/saleContract/getInvoice/${saleContractId}`);
}

export async function getInvoiceById(invoiceId) {
  return request(`${serverUrlPre}/sale/saleContract/getSingleInvoice/${invoiceId}`);
}

export async function addInvoice(params) {
  return request(`${serverUrlPre}/sale/saleContract/addInvoice`, {
    method: 'POST',
    body: params,
  });
}

export async function delInvoice(invoiceId) {
  return request(`${serverUrlPre}/sale/saleContract/delInvoice/${invoiceId}`, {
    method: 'POST',
  });
}

export async function editInvoice(params) {
  return request(`${serverUrlPre}/sale/saleContract/updateInvoice`, {
    method: 'POST',
    body: params,
  });
}

export async function getSaleContractList(staffId) {
  return request(`${serverUrlPre}/sale/saleContract/getSaleContractList/${staffId}`);
}

export async function editTransferMan(staffId, rowId) {
  return request(`${serverUrlPre}/sale/saleContract/updateTransferMan/${staffId}/${rowId}`, {
    method: 'POST',
    body: {},
  });
}
