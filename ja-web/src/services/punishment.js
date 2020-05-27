import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/punishment/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/punishment/get/${id}`);

}

export async function add(params) {
  return request(`${serverUrlPre}/staff/punishment/add?${stringify(params)}`);
}

export async function del(id) {
  return request(`${serverUrlPre}/staff/punishment/del/${id}`);
}

export async function update(params) {
  return request(`${serverUrlPre}/staff/punishment/update?${stringify(params)}`);
}
