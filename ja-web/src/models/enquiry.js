import {
  listEnqyiry,
  listInquiryRecordDetail,
  delEnquiry,
  addEnquiry,
  editEnquiry,
} from '../services/enquiry';
import { getByTypeCode } from '../services/systemCode';
import { allCustomer } from '../services/customer';
import { allArea } from '../services/area';

export default {
  namespace: 'enquiry',

  state: {
    data: {
      list: [],
      pagination: {},
    },
    enquiryHistoryList: [],
    // 数据字典：销售阶段
    salesPhaseList: [],
    tradeModeList: [],
    batteryTypeList: [],
    borderColorList: [],
    borderSpecificationList: [],
    backboardColorList: [],
    backboardMaterialList: [],
    junctionBoxList: [],
    evaList: [],
    // 所有客户
    customerList: [],
    // 所有产品
    productList: [],
    areaList: [],
  },

  effects: {
    *fetch({ payload }, { call, put }) {
      const response = yield call(listEnqyiry, payload);
      yield put({
        type: 'save',
        payload: {
          list: response.data.list,
          pagination: {
            total: response.data.total,
            current: response.data.pageNum,
            pageSize: response.data.pageSize,
          },
        },
      });
    },

    /* 删除请求发起 */
    *del({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delEnquiry, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },
    /* 添加请求发起 */
    *add({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addEnquiry, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },
    /* 编辑 */
    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editEnquiry, payload);
      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },

    // =========== 查询询单历史记录 ============
    *fetchEnquiryHistory({ payload }, { call, put }) {
      const response = yield call(listInquiryRecordDetail, payload);
      yield put({
        type: 'saveEnquiryHistory',
        payload: {
          list: response.data.list,
          pagination: {
            total: response.data.total,
            current: response.data.pageNum,
            pageSize: response.data.pageSize,
          },
        },
      });
    },

    // 查询客户list
    *fetchAllCustomer({ payload }, { call, put }) {
      const response = yield call(allCustomer, payload);
      yield put({
        type: 'saveCustomer',
        payload: response.data,
      });
    },
    // 产品 list
    *fetchProductList({ payload }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveProduct',
        payload: response.data,
      });
    },
    *fetchArea({ payload }, { call, put }) {
      const response = yield call(allArea, payload);
      yield put({
        type: 'saveArea',
        payload: response.data,
      });
    },
    // =========== 数据字典 ============
    // 销售阶段
    *fetchSalesPhase({ payload = { typeCode: 'SALES_PHASE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveSalesPhase',
        payload: response.data,
      });
    },
    // 电池类型
    *fetchBatteryTypeList({ payload = { typeCode: 'BATTERY_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveBatteryType',
        payload: response.data,
      });
    },
    // 贸易方式
    *fetchTradeModeList({ payload = { typeCode: 'TRADE_MODE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveTradeMode',
        payload: response.data,
      });
    },
    *fetchBorderColorList({ payload = { typeCode: 'BORDER_COLOR' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveBorderColor',
        payload: response.data,
      });
    },
    *fetchBorderSpecificationList(
      { payload = { typeCode: 'BORDER_SPECIFICATION' } },
      { call, put }
    ) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveBorderSpecification',
        payload: response.data,
      });
    },
    *fetchBackboardColorList({ payload = { typeCode: 'BACKBOARD_COLOR' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveBackboardColor',
        payload: response.data,
      });
    },
    *fetchBackboardMaterialList({ payload = { typeCode: 'BACKBOARD_MATERIAL' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveBackboardMaterial',
        payload: response.data,
      });
    },
    *fetchJunctionBoxList({ payload = { typeCode: 'JUNCTION_BOX' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveJunctionBox',
        payload: response.data,
      });
    },
    *fetchEVAList({ payload = { typeCode: 'EVA' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveEVA',
        payload: response.data,
      });
    },
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        data: action.payload,
      };
    },

    /* 查询询单历史 */
    saveEnquiryHistory(state, action) {
      return {
        ...state,
        enquiryHistoryList: action.payload,
      };
    },

    // 查询客户
    saveCustomer(state, action) {
      return {
        ...state,
        customerList: action.payload,
      };
    },

    // 查询产品
    saveProduct(state, action) {
      return {
        ...state,
        productList: action.payload,
      };
    },
    saveArea(state, action) {
      return {
        ...state,
        areaList: action.payload,
      };
    },
    // =========== 数据字典 ============
    saveSalesPhase(state, action) {
      return {
        ...state,
        salesPhaseList: action.payload,
      };
    },
    saveTradeMode(state, action) {
      return {
        ...state,
        tradeModeList: action.payload,
      };
    },
    saveBatteryType(state, action) {
      return {
        ...state,
        batteryTypeList: action.payload,
      };
    },
    saveBorderColor(state, action) {
      return {
        ...state,
        borderColorList: action.payload,
      };
    },
    saveBorderSpecification(state, action) {
      return {
        ...state,
        borderSpecificationList: action.payload,
      };
    },
    saveBackboardColor(state, action) {
      return {
        ...state,
        backboardColorList: action.payload,
      };
    },
    saveBackboardMaterial(state, action) {
      return {
        ...state,
        backboardMaterialList: action.payload,
      };
    },
    saveJunctionBox(state, action) {
      return {
        ...state,
        junctionBoxList: action.payload,
      };
    },
    saveEVA(state, action) {
      return {
        ...state,
        evaList: action.payload,
      };
    },
  },
};
