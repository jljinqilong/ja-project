import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/staff/agreement/list?${stringify(params)}`);
}

export async function pageListAgreement(params) {
  return request(`${serverUrlPre}/staff/agreement/list?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/staff/agreement/get/${id}`);
}

export async function addAgreement(params) {
  return request(`${serverUrlPre}/staff/agreement/add?${stringify(params)}`);
}

export async function delAgreement(id) {
  return request(`${serverUrlPre}/staff/agreement/del/${id}`);
}

export async function updateAgreement(params) {
  return request(`${serverUrlPre}/staff/agreement/update?${stringify(params)}`);
}

export async function getAgreementByStaffId(staffId) {
  return request(`${serverUrlPre}/staff/agreement/getAgreementByStaffId/${staffId}`);
}

export async function getByAgreementNo(id) {
  return request(`${serverUrlPre}/staff/agreement/getByAgreementNo/${id}`);
}
