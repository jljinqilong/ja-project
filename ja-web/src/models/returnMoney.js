import { listPaymentPlan, listPayment } from '../services/returnMoney';
import { getByTypeCode } from '../services/systemCode';

export default {
  namespace: 'returnMoney',

  state: {
    paymentPlanData: {
      list: [],
      pagination: {},
    },
    paymentData: {
      list: [],
      pagination: {},
    },
    payTypeList: [],
  },

  effects: {
    *fetchPaymentPlan({ payload }, { call, put }) {
      const response = yield call(listPaymentPlan, payload);
      yield put({
        type: 'savePaymentPlan',
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
    *fetchPayment({ payload }, { call, put }) {
      const response = yield call(listPayment, payload);
      yield put({
        type: 'savePayment',
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
    // =========== 数据字典 ============
    *fetchPayType({ payload = { typeCode: 'PAY_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'savePayType',
        payload: response.data,
      });
    },
  },

  reducers: {
    savePaymentPlan(state, action) {
      return {
        ...state,
        paymentPlanData: action.payload,
      };
    },
    savePayment(state, action) {
      return {
        ...state,
        paymentData: action.payload,
      };
    },
    savePayType(state, action) {
      return {
        ...state,
        payTypeList: action.payload,
      };
    },
  },
};
