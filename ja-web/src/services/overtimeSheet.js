import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/attendance/overtimeSheet/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/overtimeSheet/get/${id}`);
}

export async function addOvertimeSheet(params) {
  return request(`${serverUrlPre}/attendance/overtimeSheet/add?${stringify(params)}`);
}

export async function delOvertimeSheet(id) {
  return request(`${serverUrlPre}/attendance/overtimeSheet/del/${id}`);
}

export async function editOvertimeSheet(params) {
  return request(`${serverUrlPre}/attendance/overtimeSheet/update?${stringify(params)}`);
}
