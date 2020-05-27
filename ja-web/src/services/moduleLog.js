import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

export async function list(params) {
  return request(`${serverUrlPre}/system/moduleLog/list?${stringify(params)}`);
}
