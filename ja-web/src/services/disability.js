import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/disability/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/disability/get/${id}`);
}

export async function add(params) {
  return request(`${serverUrlPre}/staff/disability/add?${stringify(params)}`);
}

export async function del(id) {
  return request(`${serverUrlPre}/staff/disability/del/${id}`);
}

export async function update(params) {
  return request(`${serverUrlPre}/staff/disability/update?${stringify(params)}`);
}
