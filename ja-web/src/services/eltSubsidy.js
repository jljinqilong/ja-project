import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/emolument/eltSubsidy/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/emolument/eltSubsidy/get/${id}`);
}

export async function addSubsidy(params) {
  return request(`${serverUrlPre}/emolument/eltSubsidy/add?${stringify(params)}`);
}

export async function delSubsidy(id) {
  return request(`${serverUrlPre}/emolument/eltSubsidy/del/${id}`);
}

export async function editSubsidy(params) {
  return request(`${serverUrlPre}/emolument/eltSubsidy/update?${stringify(params)}`);
}

export async function beachDel(ids) {
  return request(`${serverUrlPre}/emolument/eltSubsidy/beachDel/${ids}`);
}

export async function empty() {
  return request(`${serverUrlPre}/emolument/eltSubsidy/empty`);
}
