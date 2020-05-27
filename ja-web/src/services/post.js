import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList1(params) {
  return request(`${serverUrlPre}/org/position/list1?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/org/position/get/${id}`);
}

export async function addPosition(params) {
  return request(`${serverUrlPre}/org/position/add?${stringify(params)}`);
}

export async function delPosition(id) {
  return request(`${serverUrlPre}/org/position/del/${id}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/org/system/user/change/${id}?status=${status}`);
}

export async function editPosition(params) {
  return request(`${serverUrlPre}/org/position/update?${stringify(params)}`);
}
