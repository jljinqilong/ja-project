import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/attendance/exemptions/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/exemptions/get/${id}`);
}

export async function addExemptions(params) {
  return request(`${serverUrlPre}/attendance/exemptions/add?${stringify(params)}`);
}

export async function delExemptions(id) {
  return request(`${serverUrlPre}/attendance/exemptions/del/${id}`);
}
