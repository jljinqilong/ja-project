import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/recordLang/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/recordLang/get/${id}`);
}

export async function add(params) {
  return request(`${serverUrlPre}/staff/recordLang/add?${stringify(params)}`);
}

export async function del(id) {
  return request(`${serverUrlPre}/staff/recordLang/del/${id}`);
}

export async function update(params) {
  return request(`${serverUrlPre}/staff/recordLang/update?${stringify(params)}`);
}
