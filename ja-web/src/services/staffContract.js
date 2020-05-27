import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/contract/list?${stringify(params)}`);
}

export async function pageListContract(params) {
  return request(`${serverUrlPre}/staff/contract/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/contract/get/${id}`);
}

export async function addContract(params) {
  return request(`${serverUrlPre}/staff/contract/add?${stringify(params)}`);
}

export async function delContract(id) {
  return request(`${serverUrlPre}/staff/contract/del/${id}`);
}

export async function updateContract(params) {
  return request(`${serverUrlPre}/staff/contract/update?${stringify(params)}`);
}

export async function getContractByStaffId(staffId) {
  return request(`${serverUrlPre}/staff/contract/getContractByStaffId/${staffId}`);
}
