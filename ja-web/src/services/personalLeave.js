import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/attendance/personalLeave/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/personalLeave/get/${id}`);
}

export async function addPersonalLeave(params) {
  return request(`${serverUrlPre}/attendance/personalLeave/add?${stringify(params)}`);
}

export async function delPersonalLeave(id) {
  return request(`${serverUrlPre}/attendance/personalLeave/del/${id}`);
}

export async function editPersonalLeave(params) {
  return request(`${serverUrlPre}/attendance/personalLeave/update?${stringify(params)}`);
}

