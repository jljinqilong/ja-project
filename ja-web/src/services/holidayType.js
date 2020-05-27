import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/attendance/holidayType/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/holidayType/get/${id}`);
}

export async function addHolidayType(params) {
  return request(`${serverUrlPre}/attendance/holidayType/add?${stringify(params)}`);
}

export async function delHolidayType(id) {
  return request(`${serverUrlPre}/attendance/holidayType/del/${id}`);
}

export async function editHolidayType(params) {
  return request(`${serverUrlPre}/attendance/holidayType/update?${stringify(params)}`);
}

export async function selectAll() {
  return request(`${serverUrlPre}/attendance/holidayType/selectAll`);
}
