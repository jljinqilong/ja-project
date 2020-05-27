import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';
import { getLang, getToken } from '../utils/authority';

export async function pageList(params) {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/get/${id}`);
}

export async function addAccumulationFund(params) {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/add?${stringify(params)}`);
}

export async function delAccumulationFund(id) {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/del/${id}`);
}

export async function editAccumulationFund(params) {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/update?${stringify(params)}`);
}

export async function beachDel(ids) {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/beachDel/${ids}`);
}

export async function empty() {
  return request(`${serverUrlPre}/emolument/eltAccumulationFund/empty`);
}

export async function exportErrExcel(redisKey) {
  const lang = getLang();
  const token = getToken();
  window.location.href = `${serverUrlPre}/emolument/eltAccumulationFund/exportErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}
