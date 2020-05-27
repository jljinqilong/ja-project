import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/system/moduleLog/list?${stringify(params)}`);
}

export async function allListLog(params) {
  return request(`${serverUrlPre}/system/moduleLog/allList?${stringify(params)}`);
}

export async function add(params) {
  return request(`${serverUrlPre}/system/moduleLog/add?${stringify(params)}`);
}
