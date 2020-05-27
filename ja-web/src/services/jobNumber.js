import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// export async function pageList(params) {
//   return request(`${serverUrlPre}/emolument/eltAccumulationFund/list?${stringify(params)}`);
// }

export async function specialPageList(params) {
  return request(`${serverUrlPre}/attendance/jobNumber/specialList?${stringify(params)}`);
}

export async function commonPageList(params) {
  return request(`${serverUrlPre}/attendance/jobNumber/commonList?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/attendance/jobNumber/get/${id}`);
}

export async function addJobNumber(params) {
  return request(`${serverUrlPre}/attendance/jobNumber/add?${stringify(params)}`);
}

export async function delJobNumber(id) {
  return request(`${serverUrlPre}/attendance/jobNumber/del/${id}`);
}

export async function editJobNumber(params) {
  return request(`${serverUrlPre}/attendance/jobNumber/update?${stringify(params)}`);
}

export async function commonAllList(params) {
  return request(`${serverUrlPre}/attendance/jobNumber/commonAllList?${stringify(params)}`);
}
