import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';
import { getLang, getToken } from '../utils/authority';

// export async function pageList(params) {
//   return request(`${serverUrlPre}/emolument/eltAccumulationFund/list?${stringify(params)}`);
// }

export async function creditCardLogPageList(params) {
  return request(`${serverUrlPre}/attendance/creditCardLog/pageList?${stringify(params)}`);
}

export async function exportErrExcel(redisKey) {
  const lang = getLang();
  const token = getToken();
  window.location.href= `${serverUrlPre}/attendance/creditCardLog/exportErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}

