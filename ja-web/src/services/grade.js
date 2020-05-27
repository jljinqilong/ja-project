import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/org/grade/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/org/grade/get/${id}`);
}

export async function addGrade(params) {
  return request(`${serverUrlPre}/org/grade/add?${stringify(params)}`);
}

export async function delGrade(id) {
  return request(`${serverUrlPre}/org/grade/del/${id}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/org/system/user/change/${id}?status=${status}`);
}

export async function editGrade(params) {
  return request(`${serverUrlPre}/org/grade/update?${stringify(params)}`);
}

export async function getByType() {
  return request(`${serverUrlPre}/org/grade/getByType`);
}
