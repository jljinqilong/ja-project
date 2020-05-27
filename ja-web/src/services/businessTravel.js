import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/attendance/businessTravel/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/businessTravel/get/${id}`);
}

export async function addBusinessTravel(params) {
  return request(`${serverUrlPre}/attendance/businessTravel/add?${stringify(params)}`);
}

export async function delBusinessTravel(id) {
  return request(`${serverUrlPre}/attendance/businessTravel/del/${id}`);
}

export async function editBusinessTravel(params) {
  return request(`${serverUrlPre}/attendance/businessTravel/update?${stringify(params)}`);
}

