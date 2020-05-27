import { listStockUp, delStockUp, addStockUp, editStockUp } from '../services/stockUp';
import { allCustomer } from '../services/customer';
import { getByTypeCode } from '../services/systemCode';

export default {
  namespace: 'stockUp',

  state: {
    data: {
      list: [],
      pagination: {},
    },
    customerList: [],
    stockAddressList: [],
    customerPaymentList: [],
    categoryAList: [],
    deliveryMethodList: [],
  },

  effects: {
    *fetch({ payload }, { call, put }) {
      const response = yield call(listStockUp, payload);
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

    *del({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delStockUp, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    *add({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addStockUp, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editStockUp, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },

    *fetchAllCustomer({ payload }, { call, put }) {
      const response = yield call(allCustomer, payload);
      yield put({
        type: 'saveCustomer',
        payload: response.data,
      });
    },

    // =========== 数据字典 ============
    // 备货地点
    *fetchStockAddress({ payload = { typeCode: 'STOCK_ADDRESS' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveStockAddress',
        payload: response.data,
      });
    },

    // 客户付款信息
    *fetchCustomerPaymentInfo({ payload = { typeCode: 'CUSTOMER_PAYMENT' } }, { call, put }){
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCustomerPayment',
        payload: response.data,
      });
    },

    // A类片
    *fetchCategoryA({ payload = { typeCode: 'CATEGORY_A' } }, { call, put }){
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCategoryA',
        payload: response.data,
      });
    },

    // 交货方式
    *fetchDeliveryMethod({ payload = { typeCode: 'DELIVERY_METHOD' } }, { call, put }){
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveDeliveryMethod',
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
    saveCustomer(state, action) {
      return {
        ...state,
        customerList: action.payload,
      };
    },

    // =========== 数据字典 ============
    saveStockAddress(state, action) {
      return {
        ...state,
        stockAddressList: action.payload,
      };
    },

    saveCustomerPayment(state, action) {
      return {
        ...state,
        customerPaymentList: action.payload,
      };
    },

    saveCategoryA(state, action) {
      return {
        ...state,
        categoryAList: action.payload,
      };
    },

    saveDeliveryMethod(state, action) {
      return {
        ...state,
        deliveryMethodList: action.payload,
      };
    },

  },
};
