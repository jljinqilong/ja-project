import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/occInjury/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/occInjury/get/${id}`);
}

export async function add(params) {
  return request(`${serverUrlPre}/staff/occInjury/add?${stringify(params)}`);
}

export async function del(id) {
  return request(`${serverUrlPre}/staff/occInjury/del/${id}`);
}

export async function update(params) {
  return request(`${serverUrlPre}/staff/occInjury/update?${stringify(params)}`);
}
