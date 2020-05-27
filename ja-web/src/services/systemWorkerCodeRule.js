import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/workerCodeRule/list?${stringify(params)}`);
}

export async function addWorkerCodeRule(params) {
  return request(`${serverUrlPre}/staff//workerCodeRule/add?${stringify(params)}`);
}

export async function delWorkerCodeRule(params) {
  return request(`${serverUrlPre}/staff/workerCodeRule/del?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/workerCodeRule/queryOneByRowId/${id}`);
}

export async function editWorkerCodeRule(params) {
  return request(`${serverUrlPre}/staff/workerCodeRule/update?${stringify(params)}`);
}
