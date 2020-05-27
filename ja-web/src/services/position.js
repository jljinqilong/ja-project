import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';
import { getLang, getToken } from '../utils/authority';

export async function pageList(params) {
  return request(`${serverUrlPre}/org/position/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/org/position/get/${id}`);
}

export async function addPosition(params) {
  return request(`${serverUrlPre}/org/position/add?${stringify(params)}`);
}

export async function delPosition(id) {
  return request(`${serverUrlPre}/org/position/del/${id}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/org/system/user/change/${id}?status=${status}`);
}

export async function editPosition(params) {
  return request(`${serverUrlPre}/org/position/update?${stringify(params)}`);
}

/**
 *
 *
 * @param params
 * @returns {Promise<Window | null>}
 */
export async function exportExcel() {
  const token = getToken();
  const lang = getLang();
  return window.open(
    `${serverUrlPre}/org/position/exportExcel?lang=${lang}&token=${token}`
  );
}

export async function exportErrExcel(redisKey) {
  const token = getToken();
  const lang = getLang();
  window.location.href = `${serverUrlPre}/org/position/exportErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}
