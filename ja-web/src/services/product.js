import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function listProduct(params) {
  return request(`${serverUrlPre}/sale/product/list?${stringify(params)}`);
}

export async function allProduct() {
  return request(`${serverUrlPre}/sale/product/getAll`);
}

export async function getProduct(id) {
  return request(`${serverUrlPre}/sale/product/get/${id}`);
}

export async function delProduct(id) {
  return request(`${serverUrlPre}/sale/product/del/${id}`, {
    method: 'POST',
  });
}

export async function addProduct(params) {
  return request(`${serverUrlPre}/sale/product/add`, {
    method: 'POST',
    body: params,
  });
}

export async function editProduct(params) {
  return request(`${serverUrlPre}/sale/product/update`, {
    method: 'POST',
    body: params,
  });
}

export async function updateProduct(id, status) {
  return request(`${serverUrlPre}/sale/product/updateStatus/${id}/${status}`, {
    method: 'POST',
    body: {},
  });
}
