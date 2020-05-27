import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function pageList(params) {
  return request(`${serverUrlPre}/system/role/list?${stringify(params)}`);
}

export async function getAllList() {
  return request(`${serverUrlPre}/system/role/allList`);
}

export async function getUserList(userId) {
  return request(`${serverUrlPre}/system/role/userList?userId=${userId}`);
}

export async function saveUserRole(params) {
  return request(`${serverUrlPre}/system/role/save/userRole?${stringify(params)}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/system/role/get/${id}`);
}

export async function addRole(params) {
  return request(`${serverUrlPre}/system/role/add?${stringify(params)}`);
}

export async function delRole(params) {
  return request(`${serverUrlPre}/system/role/del?${stringify(params)}`);
}

export async function changeStatus(id, status) {
  return request(`${serverUrlPre}/system/role/change/${id}?status=${status}`);
}

export async function editRole(params) {
  return request(`${serverUrlPre}/system/role/update?${stringify(params)}`);
}

/**
 * 设置数据权限
 * @param params
 * @returns {Promise<Object>}
 */
export async function saveRoleAuthorityData(orgIds,halfOrgIds, roleId) {
  return request(`${serverUrlPre}/system/role/saveRoleAuthorityData`, {
    method: 'POST',
    body: {orgIds:orgIds,halfOrgIds:halfOrgIds,roleId:roleId},
  });
}

/**
 * 查询数据权限
 * @param roleId
 * @returns {Promise<Object>}
 */
export async function searchRoleAuthorityData(roleId) {
  return request(`${serverUrlPre}/system/role/searchRoleAuthorityData/${roleId}`);
}
