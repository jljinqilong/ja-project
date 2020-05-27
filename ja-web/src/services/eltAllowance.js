import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// export async function pageList(params) {
//   return request(`${serverUrlPre}/emolument/eltAllowance/list?${stringify(params)}`);
// }

export async function list(params) {
  return request(`${serverUrlPre}/emolument/eltAllowance/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/emolument/eltAllowance/get/${id}`);
}

export async function addAllowance(params) {
  return request(`${serverUrlPre}/emolument/eltAllowance/add?${stringify(params)}`);
}

export async function delAllowance(id) {
  return request(`${serverUrlPre}/emolument/eltAllowance/del/${id}`);
}

export async function editAllowance(params) {
  return request(`${serverUrlPre}/emolument/eltAllowance/update?${stringify(params)}`);
}

export async function beachDel(ids) {
  return request(`${serverUrlPre}/emolument/eltAllowance/beachDel/${ids}`);
}

export async function empty() {
  return request(`${serverUrlPre}/emolument/eltAllowance/empty`);
}
