import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// =========== 询价 ============
export async function listEnqyiry(params) {
  return request(`${serverUrlPre}/sale/inquiryRecord/list?${stringify(params)}`);
}

export async function getEnquiryById(id) {
  return request(`${serverUrlPre}/sale/inquiryRecord/get/${id}`);
}
/* 询单历史记录 */
export async function listInquiryRecordDetail(id) {
  return request(`${serverUrlPre}/sale/inquiryRecordDetail/list?inquiryId=${id}`);
}
/* 添加 */
export async function addEnquiry(params) {
  return request(`${serverUrlPre}/sale/inquiryRecord/add`, {
    method: 'POST',
    body: params,
  });
}
/* 删除 */
export async function delEnquiry(ids) {
  return request(`${serverUrlPre}/sale/inquiryRecord/delete/${ids}`, {
    method: 'POST',
  });
}
/* 编辑 */
export async function editEnquiry(params) {
  return request(`${serverUrlPre}/sale/inquiryRecord/update`, {
    method: 'POST',
    body: params,
  });
}
