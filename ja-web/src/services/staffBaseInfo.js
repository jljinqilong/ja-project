import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';
import { getLang, getToken } from '../utils/authority';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/baseInfo/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/baseInfo/get/${id}`);
}

export async function getCountByIdNo(id) {
  return request(`${serverUrlPre}/staff/baseInfo/getCountByIdNo/${id}`);
}
export async function getStaffNoByBaseId(baseId) {
  return request(`${serverUrlPre}/staff/baseInfo/getStaffNo/${baseId}`);
}

export async function addStaff(params) {
  return request(`${serverUrlPre}/staff/baseInfo/add?${stringify(params)}`);
}

export async function delStaff(id) {
  return request(`${serverUrlPre}/staff/baseInfo/del/${id}`);
}

export async function editStaff(params) {
  return request(`${serverUrlPre}/staff/baseInfo/update?${stringify(params)}`);
}

export async function queryBaseInfoForParams(params) {
  return request(`${serverUrlPre}/staff/baseInfo/queryBaseInfoForParams`,{
    method:`POST`,
    body: params,
  });
}

export async function checkHasLeave(ids) {
  return request(`${serverUrlPre}/staff/baseInfo/checkHasLeave/${ids}`);
}

export async function exportExcel(params) {
  const lang = getLang();
  const token = getToken();
  window.open(
    `${serverUrlPre}/staff/baseInfo/exportExcel?lang=${lang}&token=${token}&${stringify(params)}`
  );
}

export async function exportErrExcel(redisKey) {
  const lang = getLang();
  const token = getToken();
  window.location.href = `${serverUrlPre}/staff/baseInfo/exportErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}

export async function exportPositionErrExcel(redisKey) {
  const lang = getLang();
  const token = getToken();
  window.location.href = `${serverUrlPre}/staff/baseInfo/exportPositionErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}

/**
 *
 */
export async function addOrEditAddress(params) {
  return request(`${serverUrlPre}/staff/address/addOrEdit?${stringify(params)}`);
}

export async function delAddress(id) {
  return request(`${serverUrlPre}/staff/address/del/${id}`);
}

export async function listAddress(id) {
  return request(`${serverUrlPre}/staff/address/list/${id}`);
}

export async function getAddress(id) {
  return request(`${serverUrlPre}/staff/address/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrProjectExperience(params) {
  return request(`${serverUrlPre}/staff/projectExperience/addOrEdit?${stringify(params)}`);
}

export async function delOrProjectExperience(id) {
  return request(`${serverUrlPre}/staff/projectExperience/del/${id}`);
}

export async function listOrProjectExperience(id) {
  return request(`${serverUrlPre}/staff/projectExperience/list/${id}`);
}

export async function getOrProjectExperience(id) {
  return request(`${serverUrlPre}/staff/projectExperience/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrEditAdjustmentWork(params) {
  return request(`${serverUrlPre}/staff/adjustmentWork/addOrEdit?${stringify(params)}`);
}

export async function delAdjustmentWork(id) {
  return request(`${serverUrlPre}/staff/adjustmentWork/del/${id}`);
}

export async function listAdjustmentWork(id) {
  return request(`${serverUrlPre}/staff/adjustmentWork/list/${id}`);
}

export async function getAdjustmentWork(id) {
  return request(`${serverUrlPre}/staff/adjustmentWork/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrEditContactEmergency(params) {
  return request(`${serverUrlPre}/staff/contactEmergency/addOrEdit?${stringify(params)}`);
}

export async function delContactEmergency(id) {
  return request(`${serverUrlPre}/staff/contactEmergency/del/${id}`);
}

export async function listContactEmergency(id) {
  return request(`${serverUrlPre}/staff/contactEmergency/list/${id}`);
}

export async function getContactEmergency(id) {
  return request(`${serverUrlPre}/staff/contactEmergency/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrEditEducation(params) {
  return request(`${serverUrlPre}/staff/education/addOrEdit?${stringify(params)}`);
}

export async function delEducation(id) {
  return request(`${serverUrlPre}/staff/education/del/${id}`);
}

export async function listEducation(id) {
  return request(`${serverUrlPre}/staff/education/list/${id}`);
}

export async function getEducation(id) {
  return request(`${serverUrlPre}/staff/education/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrEditOuterExperience(params) {
  return request(`${serverUrlPre}/staff/outerExperience/addOrEdit?${stringify(params)}`);
}

export async function delOuterExperience(id) {
  return request(`${serverUrlPre}/staff/outerExperience/del/${id}`);
}

export async function listOuterExperience(id) {
  return request(`${serverUrlPre}/staff/outerExperience/list/${id}`);
}

export async function getOuterExperience(id) {
  return request(`${serverUrlPre}/staff/outerExperience/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrEditRelationshipInner(params) {
  return request(`${serverUrlPre}/staff/relationshipInner/addOrEdit?${stringify(params)}`);
}

export async function delRelationshipInner(id) {
  return request(`${serverUrlPre}/staff/relationshipInner/del/${id}`);
}

export async function listRelationshipInner(id) {
  return request(`${serverUrlPre}/staff/relationshipInner/list/${id}`);
}

export async function getRelationshipInner(id) {
  return request(`${serverUrlPre}/staff/relationshipInner/getByRowId/${id}`);
}

/**
 *
 */
export async function addOrEditRelationshipSocial(params) {
  return request(`${serverUrlPre}/staff/relationshipSocial/addOrEdit?${stringify(params)}`);
}

export async function delRelationshipSocial(id) {
  return request(`${serverUrlPre}/staff/relationshipSocial/del/${id}`);
}

export async function listRelationshipSocial(id) {
  return request(`${serverUrlPre}/staff/relationshipSocial/list/${id}`);
}

export async function getRelationshipSocial(id) {
  return request(`${serverUrlPre}/staff/relationshipSocial/getByRowId/${id}`);
}

export async function findOneOrgHistory(rowId, date) {
  return request(`${serverUrlPre}/org/org/findOneOrgHistory?rowid=${rowId}&date=${date}`);
}
