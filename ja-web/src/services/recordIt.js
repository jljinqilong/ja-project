import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/recordIt/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/recordIt/get/${id}`);
}

export async function addRecordIt(params) {
  return request(`${serverUrlPre}/staff/recordIt/add?${stringify(params)}`);
}

export async function delRecordIt(id) {
  return request(`${serverUrlPre}/staff/recordIt/del/${id}`);
}

export async function updateRecordIt(params) {
  return request(`${serverUrlPre}/staff/recordIt/update?${stringify(params)}`);
}
