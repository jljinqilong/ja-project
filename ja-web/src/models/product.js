import {
  delProduct,
  listProduct,
  addProduct,
  editProduct,
  updateProduct,
} from '../services/product';
import { getByTypeCode } from '../services/systemCode';

export default {
  namespace: 'product',

  state: {
    data: {
      list: [],
      pagination: {},
    },
    siliconTypeList: [],
    cellNumberList: [],
    muduleTypeList: [],
    muduleCodeList: [],
    cellTechnologyList: [],
  },

  effects: {
    *fetch({ payload }, { call, put }) {
      const response = yield call(listProduct, payload);
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

    *add({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addProduct, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    *del({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delProduct, payload.rowId);
      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editProduct, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    *update({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(updateProduct, payload.rowId, payload.status);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    // ==================数据字典============================
    *fetchSiliconType({ payload = { typeCode: 'SILICON_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveSiliconType',
        payload: response.data,
      });
    },

    *fetchCellNumber({ payload = { typeCode: 'CELL_NUMBER' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCellNumber',
        payload: response.data,
      });
    },

    *fetchMuduleType({ payload = { typeCode: 'MUDULE_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveMuduleType',
        payload: response.data,
      });
    },

    *fetchMuduleCode({ payload = { typeCode: 'MUDULE_CODE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveMuduleCode',
        payload: response.data,
      });
    },

    *fetchCellTechnology({ payload = { typeCode: 'CELL_TECHNOLOGY' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCellTechnology',
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

    // ==========数据字典=============
    saveSiliconType(state, action) {
      return {
        ...state,
        siliconTypeList: action.payload,
      };
    },

    saveCellNumber(state, action) {
      return {
        ...state,
        cellNumberList: action.payload,
      };
    },

    saveMuduleType(state, action) {
      return {
        ...state,
        muduleTypeList: action.payload,
      };
    },

    saveMuduleCode(state, action) {
      return {
        ...state,
        muduleCodeList: action.payload,
      };
    },

    saveCellTechnology(state, action) {
      return {
        ...state,
        cellTechnologyList: action.payload,
      };
    },
  },
};
