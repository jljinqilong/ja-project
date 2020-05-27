import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/get/${id}`);
}

export async function addSocialSecurity(params) {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/add?${stringify(params)}`);
}

export async function delSocialSecurity(id) {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/del/${id}`);
}

export async function editSocialSecurity(params) {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/update?${stringify(params)}`);
}

export async function beachDel(ids) {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/beachDel/${ids}`);
}

export async function empty() {
  return request(`${serverUrlPre}/emolument/eltSocialSecurity/empty`);
}
