import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/org/rank/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/org/rank/get/${id}`);
}

export async function addRank(params) {
  return request(`${serverUrlPre}/org/rank/add?${stringify(params)}`);
}

export async function delRank(id) {
  return request(`${serverUrlPre}/org/rank/del/${id}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/org/system/user/change/${id}?status=${status}`);
}

export async function editRank(params) {
  return request(`${serverUrlPre}/org/rank/update?${stringify(params)}`);
}
export async function getRankType() {
  return request(`${serverUrlPre}/org/rank/getRankType`);
}

export async function getRankByGrade(params) {
  return request(`${serverUrlPre}/org/rank/getRankByGrade?${stringify(params)}`);
}
