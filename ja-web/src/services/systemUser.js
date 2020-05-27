import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/system/user/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/system/user/get/${id}`);
}

export async function addUser(params) {
  return request(`${serverUrlPre}/system/user/add?${stringify(params)}`);
}

export async function delUser(params) {
  return request(`${serverUrlPre}/system/user/del?${stringify(params)}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/system/user/change/${id}?status=${status}`);
}

export async function editUser(params) {
  return request(`${serverUrlPre}/system/user/update?${stringify(params)}`);
}

export async function getCurrentLogin() {
  return request(`${serverUrlPre}/system/user/get/current?force=true`);
}

export async function changePassword(params) {
  return request(`${serverUrlPre}/system/user/changePassword`, {
    method: 'POST',
    body: params,
  });
}
