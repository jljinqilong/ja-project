import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// =========== 回款计划 ============
export async function listPaymentPlan(params) {
  return request(`${serverUrlPre}/sale/paymentPlan/list?${stringify(params)}`);
}

export async function getPaymentPlan(id) {
  return request(`${serverUrlPre}/sale/paymentPlan/get/${id}`);
}

// =========== 回款记录 ============
export async function listPayment(params) {
  return request(`${serverUrlPre}/sale/paymentDetail/list?${stringify(params)}`);
}

export async function getPayment(id) {
  return request(`${serverUrlPre}/sale/paymentDetail/get/${id}`);
}
