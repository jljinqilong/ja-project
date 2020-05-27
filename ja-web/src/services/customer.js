import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// =========== 客户记录 ============
export async function listCustomer(params) {
  return request(`${serverUrlPre}/sale/customer/list?${stringify(params)}`);
}

export async function allCustomer() {
  return request(`${serverUrlPre}/sale/customer/getAllCustomer`);
}

export async function getCustomerById(id) {
  return request(`${serverUrlPre}/sale/customer/getCustomerAndContacts/${id}`);
}

export async function addCustomer(params) {
  return request(`${serverUrlPre}/sale/customer/add`, {
    method: 'POST',
    body: params,
  });
}

export async function delCustomer(id) {
  return request(`${serverUrlPre}/sale/customer/del/${id}`, {
    method: 'POST',
  });
}

export async function editCustomer(params) {
  return request(`${serverUrlPre}/sale/customer/update`, {
    method: 'POST',
    body: params,
  });
}

export async function getSalePersonList() {
  return request(`${serverUrlPre}/sale/customer/getSalePersonList`);
}

export async function getCustomerList(staffId) {
  return request(`${serverUrlPre}/sale/customer/getCustomerList/${staffId}`);
}

export async function editTransferMan(staffId, rowId) {
  return request(`${serverUrlPre}/sale/customer/updateTransferMan/${staffId}/${rowId}`, {
    method: 'POST',
    body: {},
  });
}
