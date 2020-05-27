import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function listArea(params) {
  return request(`${serverUrlPre}/sale/area/detailList?${stringify(params)}`);
}

export async function allArea() {
  return request(`${serverUrlPre}/sale/area/getAllArea`);
}

export async function getAreaById(id) {
  return request(`${serverUrlPre}/sale/area/get/${id}`);
}

export async function addArea(params) {
  return request(`${serverUrlPre}/sale/area/add`, {
    method: 'POST',
    body: params,
  });
}

export async function delArea(id) {
  return request(`${serverUrlPre}/sale/area/del/${id}`, {
    method: 'POST',
  });
}

export async function editArea(params) {
  return request(`${serverUrlPre}/sale/area/update`, {
    method: 'POST',
    body: params,
  });
}

// 获取国家list,三级联动
export async function listCountryCascader(params) {
  return request(`${serverUrlPre}/sale/countryCascader/list`, {
    method: 'POST',
    body: params,
  });
}
