import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function getUserMenuData(params) {
  return request(`${serverUrlPre}/system/resource/tree/menuRes?${stringify(params)}`);
}

export async function getUserTreeData(params) {
  return request(`${serverUrlPre}/system/resource/tree/userRes?${stringify(params)}`);
}

export async function getRoleTreeData(params) {
  return request(`${serverUrlPre}/system/resource/tree/roleRes?${stringify(params)}`);
}

export async function saveUserResource(params) {
  return request(`${serverUrlPre}/system/resource/save/userRes?${stringify(params)}`);
}

export async function saveRoleResource(params) {
  return request(`${serverUrlPre}/system/resource/save/roleRes?${stringify(params)}`);
}
