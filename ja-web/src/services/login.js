import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function accountLogin(params) {
  return request(`${serverUrlPre}/system/login?${stringify(params)}`);
}

export async function accountLogout() {
  return request(`${serverUrlPre}/system/logout`);
}
