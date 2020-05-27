import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/attendance/machine/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/machine/get/${id}`);
}

export async function addMachine(params) {
  return request(`${serverUrlPre}/attendance/machine/add?${stringify(params)}`);
}

export async function delMachine(id) {
  return request(`${serverUrlPre}/attendance/machine/del/${id}`);
}

export async function editMachine(params) {
  return request(`${serverUrlPre}/attendance/machine/update?${stringify(params)}`);
}

