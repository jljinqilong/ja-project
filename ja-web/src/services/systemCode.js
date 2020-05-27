import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/system/code/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/system/code/get/${id}`);
}

export async function add(params) {
  return request(`${serverUrlPre}/system/code/add?${stringify(params)}`);
}

export async function del(params) {
  return request(`${serverUrlPre}/system/code/del?${stringify(params)}`);
}

export async function change(params) {
  return request(`${serverUrlPre}/system/code/change?${stringify(params)}`);
}

export async function edit(params) {
  return request(`${serverUrlPre}/system/code/update?${stringify(params)}`);
}

export async function getByTypeCode(params) {
  return request(`${serverUrlPre}/system/code/getByTpye?${stringify(params)}`);
}
