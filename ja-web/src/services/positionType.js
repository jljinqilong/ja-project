import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/org/positiontype/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/org/positiontype/get/${id}`);
}

export async function addPositionType(params) {
  return request(`${serverUrlPre}/org/positiontype/add?${stringify(params)}`);
}

export async function delPositionType(id) {
  return request(`${serverUrlPre}/org/positiontype/del/${id}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/org/system/user/change/${id}?status=${status}`);
}

export async function editPositionType(params) {
  return request(`${serverUrlPre}/org/positiontype/update?${stringify(params)}`);


}
export async function getByPositionType(params) {
  return request(`${serverUrlPre}/org/positiontype/getByPositionType${stringify(params)}`);


}
