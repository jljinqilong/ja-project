import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function listStockUp(params) {
  return request(`${serverUrlPre}/sale/stockUp/list?${stringify(params)}`);
}

export async function getStockUp(id) {
  return request(`${serverUrlPre}/sale/stockUp/get/${id}`);
}

export async function delStockUp(id) {
  return request(`${serverUrlPre}/sale/stockUp/del/${id}`, {
    method: 'POST',
  });
}

export async function addStockUp(params) {
  return request(`${serverUrlPre}/sale/stockUp/add`, {
    method: 'POST',
    body: params,
  });
}

export async function editStockUp(params) {
  return request(`${serverUrlPre}/sale/stockUp/update`, {
    method: 'POST',
    body: params,
  });
}
