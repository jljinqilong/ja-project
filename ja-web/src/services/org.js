import { stringify } from 'qs';
import request, { serverUrlPre } from '../utils/request';
import { getLang, getToken } from '../utils/authority';

export async function getOrgTreeData() {
  return request(`${serverUrlPre}/org/org/findOrgChart`);
}

export async function findAllOrgChart() {
  return request(`${serverUrlPre}/org/org/findAllOrgChart`);
}

export async function getOrgHistoryTreeData(datetime) {
  return request(`${serverUrlPre}/org/org/findOrgHistoryChart?date=${datetime}`);
}

export async function backUpInfo() {
  return request(`${serverUrlPre}/org/org/backUpInfo`);
}

export async function getSonOrgTree(id) {
  return request(`${serverUrlPre}/org/org/getSonOrgTree/${id}`);
}

export async function getById(id) {
  return request(`${serverUrlPre}/org/org/get/${id}`);
}

export async function findOneOrgHistory(rowId, date) {
  return request(`${serverUrlPre}/org/org/findOneOrgHistory/${rowId}/${date}`);
}

export async function addOrg(params) {
  return request(`${serverUrlPre}/org/org/add?${stringify(params)}`);
}

export async function delOrg(id) {
  return request(`${serverUrlPre}/org/org/del/${id}`);
}

export async function editOrg(params) {
  return request(`${serverUrlPre}/org/org/update?${stringify(params)}`);
}

export async function orgMerge(sourceId, targetId) {
  return request(`${serverUrlPre}/org/org/orgMerge/${sourceId}/${targetId}`);
}

export async function orgMove(sourceId, targetId) {
  return request(`${serverUrlPre}/org/org/orgMove/${sourceId}/${targetId}`);
}
export async function baseList() {
  return request(`${serverUrlPre}/org/org/baseList`);
}

export async function getAllPosition() {
  return request(`${serverUrlPre}/org/position/getAllPosition`);
}

export async function getAllGradeByPosition(positionId) {
  return request(`${serverUrlPre}/org/position/getAllGradeByPosition/${positionId}`);
}

export async function getAllRankByPositionAndGrade(positionId, gradeId) {
  return request(
    `${serverUrlPre}/org/position/getAllRankByPositionAndGrade/${positionId}/${gradeId}`
  );
}

export async function deptList() {
  return request(`${serverUrlPre}/org/org/deptList`);
}

export async function exportExcel(params) {
  const lang = getLang();
  const token = getToken();
  window.open(
    `${serverUrlPre}/org/org/exportExcel?lang=${lang}&token=${token}&${stringify(params)}`
  );
}

export async function exportErrExcel(redisKey) {
  const lang = getLang();
  const token = getToken();
  window.location.href = `${serverUrlPre}/org/org/exportErrExcel?lang=${lang}&token=${token}&redisKey=${redisKey}`;
}

export async function hierarchyChart() {
  return request(`${serverUrlPre}/org/org/hierarchyChart`);
}

export async function hierarchyHistoryChart(datetime) {
  return request(`${serverUrlPre}/org/org/hierarchyHistoryChart/${datetime}`);
}

export async function findOrgChartByDisabled(rowId) {
  return request(`${serverUrlPre}/org/org/findOrgChartByDisabled/${rowId}`);
}
