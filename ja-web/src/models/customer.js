import { listCustomer, addCustomer, editCustomer, delCustomer } from '../services/customer';
import { listSaleContractByCustomerId } from '../services/saleContract';
import { listInquiries } from '../services/inquiries';
import { allArea } from '../services/area';
import { getByTypeCode } from '../services/systemCode';

export default {
  namespace: 'customer',

  state: {
    data: {
      list: [],
      pagination: {},
    },
    contractData: {
      list: [],
      pagination: {},
    },
    inquiriesData: {
      list: [],
      pagination: {},
    },
    accountUnitList: [],
    customerLevelList: [],
    paymentTypeList: [],
    currencyList: [],
    areaList: [],
  },

  effects: {
    *fetch({ payload }, { call, put }) {
      const response = yield call(listCustomer, payload);
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
    *fetchSaleContract({ payload }, { call, put }) {
      const response = yield call(listSaleContractByCustomerId, payload);
      yield put({
        type: 'saveContractData',
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
    *fetchInquiries({ payload }, { call, put }) {
      const response = yield call(listInquiries, payload);
      yield put({
        type: 'saveInquiriesData',
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
      const response = yield call(addCustomer, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editCustomer, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *del({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delCustomer, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *fetchAccountUnit({ payload = { typeCode: 'ACCOUNT_UNIT' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveAccountUnit',
        payload: response.data,
      });
    },
    *fetchCustomerLevel({ payload = { typeCode: 'CUSTOMER_LEVEL' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCustomerLevel',
        payload: response.data,
      });
    },
    *fetchPaymentType({ payload = { typeCode: 'PAYMENT_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'savePaymentType',
        payload: response.data,
      });
    },
    *fetchCurrency({ payload = { typeCode: 'CURRENCY' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCurrency',
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
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        data: action.payload,
      };
    },
    saveContractData(state, action) {
      return {
        ...state,
        contractData: action.payload,
      };
    },
    saveInquiriesData(state, action) {
      return {
        ...state,
        inquiriesData: action.payload,
      };
    },
    saveAccountUnit(state, action) {
      return {
        ...state,
        accountUnitList: action.payload,
      };
    },
    saveCustomerLevel(state, action) {
      return {
        ...state,
        customerLevelList: action.payload,
      };
    },
    savePaymentType(state, action) {
      return {
        ...state,
        paymentTypeList: action.payload,
      };
    },
    saveCurrency(state, action) {
      return {
        ...state,
        currencyList: action.payload,
      };
    },
    saveArea(state, action) {
      return {
        ...state,
        areaList: action.payload,
      };
    },
  },
};
