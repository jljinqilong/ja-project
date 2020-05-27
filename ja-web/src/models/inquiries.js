import {
  listInquiries,
  delInquiries,
  addInquiries,
  editInquiries,
  affirmInquiries,
  listInquiriesRecordDetail,
  turnContract,
  supplementUpdate,
  turnAppraisal,
} from '../services/inquiries';
import { getByTypeCode } from '../services/systemCode';
import { allCustomer } from '../services/customer';
import { allArea, listCountryCascader } from '../services/area';
import { allProduct } from '../services/product';

export default {
  namespace: 'inquiries',

  state: {
    data: {
      list: [],
      pagination: {},
    },
    inquiriesHistoryList: [],
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
    productUnitList: [], // 产品单位
    cellNumberList: [], // 电池片数
    currencyList: [],
    inquiriesStatusList: [],
    dispatchPlaceList: [],
    projectDistributionList: [],
    productTypeList: [],
    countryCascaderList: [],
  },

  effects: {
    /* 分页 */
    *fetch({ payload }, { call, put }) {
      const response = yield call(listInquiries, payload);
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
      const response = yield call(delInquiries, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },
    /* 添加请求发起 */
    *add({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addInquiries, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },
    /* 编辑 */
    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editInquiries, payload);
      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },

    /* 询单确认请求发起 */
    *affrim({ rowId, successCallback, failCallback }, { call }) {
      const response = yield call(affirmInquiries, rowId);
      if (response && response.code === 200) {
        if (successCallback) successCallback(response);
      } else if (failCallback) failCallback(response);
    },

    /* 询单补齐信息 */
    *supplement({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(supplementUpdate, payload);
      if (response && response.code === 200) {
        if (successCallback) successCallback(response);
      } else if (failCallback) failCallback(response);
    },

    /* 转合同请求发起 */
    *turnContract({ rowId, successCallback, failCallback }, { call }) {
      const response = yield call(turnContract, rowId);
      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },

    /* 转评审请求发起 */
    *turnAppraisal({ rowId, successCallback, failCallback }, { call }) {
      const response = yield call(turnAppraisal, rowId);
      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (failCallback) failCallback();
    },

    // =========== 查询询单历史记录 ============
    *fetchInquiriesHistory({ payload }, { call, put }) {
      const response = yield call(listInquiriesRecordDetail, payload);
      yield put({
        type: 'saveInquiriesHistory',
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
      const response = yield call(allProduct, payload);
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
    // 获取国家所有
    *fetchCountryCascaderList({ payload }, { call, put }) {
      const response = yield call(listCountryCascader, payload);
      yield put({
        type: 'saveCountryCascader',
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
    *fetchProductUnitList({ payload = { typeCode: 'PRODUCT_UNIT' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveProductUnit',
        payload: response.data,
      });
    },
    *fetchCellNumberList({ payload = { typeCode: 'CELL_NUMBER' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCellNumber',
        payload: response.data,
      });
    },
    *fetchCurrencyList({ payload = { typeCode: 'CURRENCY' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCurrency',
        payload: response.data,
      });
    },
    *fetchInquiriesStatusList({ payload = { typeCode: 'INQUIRIES_STATUS' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveInquiriesStatus',
        payload: response.data,
      });
    },
    *fetchDispatchPlaceList({ payload = { typeCode: 'DISPATCH_PLACE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveDispatchPlace',
        payload: response.data,
      });
    },
    *fetchProjectDistributionList(
      { payload = { typeCode: 'PROJECT_DISTRIBUTION' } },
      { call, put }
    ) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveProjectDistribution',
        payload: response.data,
      });
    },
    *fetchProductTypeList({ payload = { typeCode: 'PRODUCT_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveProductType',
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
    saveInquiriesHistory(state, action) {
      return {
        ...state,
        inquiriesHistoryList: action.payload,
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
    saveProductUnit(state, action) {
      return {
        ...state,
        productUnitList: action.payload,
      };
    },
    saveCellNumber(state, action) {
      return {
        ...state,
        cellNumberList: action.payload,
      };
    },
    saveCurrency(state, action) {
      return {
        ...state,
        currencyList: action.payload,
      };
    },
    saveInquiriesStatus(state, action) {
      return {
        ...state,
        inquiriesStatusList: action.payload,
      };
    },
    saveDispatchPlace(state, action) {
      return {
        ...state,
        dispatchPlaceList: action.payload,
      };
    },
    saveProjectDistribution(state, action) {
      return {
        ...state,
        projectDistributionList: action.payload,
      };
    },
    saveProductType(state, action) {
      return {
        ...state,
        productTypeList: action.payload,
      };
    },
    saveCountryCascader(state, action) {
      return {
        ...state,
        countryCascaderList: action.payload,
      };
    },
  },
};
