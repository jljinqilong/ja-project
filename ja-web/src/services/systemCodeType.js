import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/system/codetype/list?${stringify(params)}`);
}

export async function allList() {
  return request(`${serverUrlPre}/system/codetype/allList`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/system/codetype/get/${id}`);
}

export async function edit(params) {
  return request(`${serverUrlPre}/system/codetype/update?${stringify(params)}`);
}
