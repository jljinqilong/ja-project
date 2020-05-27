import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/emolument/eltSalary/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/emolument/eltSalary/get/${id}`);
}

export async function addSalary(params) {
  return request(`${serverUrlPre}/emolument/eltSalary/add?${stringify(params)}`);
}

export async function delSalary(id) {
  return request(`${serverUrlPre}/emolument/eltSalary/del/${id}`);
}

export async function editSalary(params) {
  return request(`${serverUrlPre}/emolument/eltSalary/update?${stringify(params)}`);
}

export async function beachDel(ids) {
  return request(`${serverUrlPre}/emolument/eltSalary/beachDel/${ids}`);
}

export async function empty() {
  return request(`${serverUrlPre}/emolument/eltSalary/empty`);
}
