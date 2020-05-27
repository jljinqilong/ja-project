import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/award/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/award/get/${id}`);
}

export async function add(params) {
  return request(`${serverUrlPre}/staff/award/add?${stringify(params)}`);
}

export async function del(id) {
  return request(`${serverUrlPre}/staff/award/del/${id}`);
}

export async function update(params) {
  return request(`${serverUrlPre}/staff/award/update?${stringify(params)}`);
}
