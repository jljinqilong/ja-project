import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';

// 询单记录

// 分页
export async function listInquiries(params) {
  return request(`${serverUrlPre}/sale/inquiries/list?${stringify(params)}`);
}
// 详情
export async function getInquiriesById(id) {
  return request(`${serverUrlPre}/sale/inquiries/get/${id}`);
}
// 询单历史记录
export async function listInquiriesRecordDetail(params) {
  return request(`${serverUrlPre}/sale/inquiries/listInquiriesHistory?${stringify(params)}`);
}
// 添加
export async function addInquiries(params) {
  return request(`${serverUrlPre}/sale/inquiries/add`, {
    method: 'POST',
    body: params,
  });
}
// 删除
export async function delInquiries(ids) {
  return request(`${serverUrlPre}/sale/inquiries/delete/${ids}`, {
    method: 'POST',
  });
}
// 编辑
export async function editInquiries(params) {
  return request(`${serverUrlPre}/sale/inquiries/update`, {
    method: 'POST',
    body: params,
  });
}
// 确认询单
export async function affirmInquiries(ids) {
  return request(`${serverUrlPre}/sale/inquiries/confirmInquiries/${ids}`);
}
// 转合同
export async function turnContract(ids) {
  return request(`${serverUrlPre}/sale/inquiries/transferContract/${ids}`);
}
// 转评审
export async function turnAppraisal(ids) {
  return request(`${serverUrlPre}/sale/inquiries/inquiriesTurnAppraisal/${ids}`);
}
// 询单补齐信息
export async function supplementUpdate(params) {
  return request(`${serverUrlPre}/sale/inquiries/supplementInquiries`, {
    method: 'POST',
    body: params,
  });
}
// ======================== 评审 =====================
// 详情 -- 用上面的详情
export async function getInquiriesAppraisalById(id) {
  return request(`${serverUrlPre}/sale/inquiriesAppraisal/get/${id}`);
}

// 询单评审分页list
export async function listInquiriesAppraisal(params) {
  return request(`${serverUrlPre}/sale/inquiriesAppraisal/list?${stringify(params)}`);
}

// 询单评审驳回或通过
export async function affrimAppraisal(params) {
  return request(`${serverUrlPre}/sale/inquiriesAppraisal/confirmInquiriesAppraisal`, {
    method: 'POST',
    body: params,
  });
}
