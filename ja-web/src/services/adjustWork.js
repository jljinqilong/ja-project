import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';
import {getLang, getToken} from "../utils/authority";

export async function addStaffMove(params) {
  return request(`${serverUrlPre}/staff/adjustmentWork/addStaffMove?${stringify(params)}`);
}

export async function allAdjustWork(id) {
  return request(`${serverUrlPre}/staff/adjustmentWork/list/${id}`);
}

export async function queryAdjustmentWorkByChangeType(params) {
  return request(`${serverUrlPre}/staff/adjustmentWork/list?${stringify(params)}`);
}

export async function getAdjustmentWorkList(staffId,changeType) {
  return request(`${serverUrlPre}/staff/adjustmentWork/getAdjustmentWorkList/${staffId}/${changeType}`);
}

export async function getAdjustmentBy(staffId,changeType) {
  return request(`${serverUrlPre}/staff/adjustmentWork/getAdjustmentBy/${staffId}/${changeType}`);
}

export async function updateStaffMove(params) {
  return request(`${serverUrlPre}/staff/adjustmentWork/updateStaffMove?${stringify(params)}`);
}

export async function exportErrExcel(redisKey) {
  const lang = getLang();
  const token = getToken();
  window.location.href = `${serverUrlPre}/staff/adjustmentWork/exportErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}
