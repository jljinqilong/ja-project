import {
  listSaleContract,
  addSaleContract,
  editSaleContract,
  delSaleContract,
  listPaymentPlanBySaleContractId,
  addPaymentPlan,
  editPaymentPlan,
  delPaymentPlan,
  listInvoiceBySaleContractId,
  addPayment,
  editPayment,
  delPayment,
  addInvoice,
  editInvoice,
  delInvoice,
} from '../services/saleContract';
import { allCustomer } from '../services/customer';
import { getByTypeCode } from '../services/systemCode';
import { allProduct } from '../services/product';
import { allArea } from '../services/area';

export default {
  namespace: 'saleContract',

  state: {
    // 合同
    data: {
      list: [],
      pagination: {},
    },
    // 回款计划
    paymentPlanList: [],
    // 发票
    invoiceList: [],
    // 产品
    productList: [],
    // 区域
    areaList: [],
    // 数据字典
    paymentTypeList: [],
    contractTypeList: [],
    customerList: [],
    invoiceTypeList: [],
    payTypeList: [], // 回款类型
    productUnitList: [], // 产品单位
    currencyList: [], // 币别
    dispatchPlaceList: [], // 发货地
  },

  effects: {
    // =========== 合同 ============
    *fetch({ payload }, { call, put }) {
      const response = yield call(listSaleContract, payload);
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
      const response = yield call(addSaleContract, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editSaleContract, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *del({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delSaleContract, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    // =========== 回款计划 ============
    *fetchPaymentPlan({ payload }, { call, put }) {
      const response = yield call(listPaymentPlanBySaleContractId, payload);
      yield put({
        type: 'savePaymentPlan',
        payload: response.data,
      });
    },
    *addPaymentPlan({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addPaymentPlan, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *editPaymentPlan({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editPaymentPlan, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *delPaymentPlan({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delPaymentPlan, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    // =========== 回款记录 ============
    *addPayment({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addPayment, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *editPayment({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editPayment, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *delPayment({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delPayment, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    // =========== 发票 ============
    *fetchInvoice({ payload }, { call, put }) {
      const response = yield call(listInvoiceBySaleContractId, payload);
      yield put({
        type: 'saveInvoice',
        payload: response.data,
      });
    },
    *addInvoice({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addInvoice, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *editInvoice({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editInvoice, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *delInvoice({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delInvoice, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    // =========== 产品 ============
    *fetchProduct({ payload }, { call, put }) {
      const response = yield call(allProduct, payload);
      yield put({
        type: 'saveProduct',
        payload: response.data,
      });
    },
    // =========== 所属区域 ============
    *fetchArea({ payload }, { call, put }) {
      const response = yield call(allArea, payload);
      yield put({
        type: 'saveArea',
        payload: response.data,
      });
    },
    // =========== 数据字典 ============
    *fetchPaymentType({ payload = { typeCode: 'PAYMENT_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'savePaymentType',
        payload: response.data,
      });
    },
    *fetchSaleContractType({ payload = { typeCode: 'SALE_CONTRACT_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveSaleContractType',
        payload: response.data,
      });
    },
    *fetchAllCustomer({ payload }, { call, put }) {
      const response = yield call(allCustomer, payload);
      yield put({
        type: 'saveCustomer',
        payload: response.data,
      });
    },
    *fetchInvoiceType({ payload = { typeCode: 'INVOICE_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveInvoiceType',
        payload: response.data,
      });
    },
    *fetchPayType({ payload = { typeCode: 'PAY_TYPE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'savePayType',
        payload: response.data,
      });
    },
    *fetchProductUnit({ payload = { typeCode: 'PRODUCT_UNIT' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveProductUnit',
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
    *fetchDispatchPlace({ payload = { typeCode: 'DISPATCH_PLACE' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveDispatchPlace',
        payload: response.data,
      });
    },
  },

  reducers: {
    // =========== 合同 ============
    save(state, action) {
      return {
        ...state,
        data: action.payload,
      };
    },
    // =========== 回款计划 ============
    savePaymentPlan(state, action) {
      return {
        ...state,
        paymentPlanList: action.payload,
      };
    },
    // =========== 发票 ============
    saveInvoice(state, action) {
      return {
        ...state,
        invoiceList: action.payload,
      };
    },
    // =========== 产品 ============
    saveProduct(state, action) {
      return {
        ...state,
        productList: action.payload,
      };
    },
    // =========== 所属区域 ============
    saveArea(state, action) {
      return {
        ...state,
        areaList: action.payload,
      };
    },
    // =========== 数据字典 ============
    savePaymentType(state, action) {
      return {
        ...state,
        paymentTypeList: action.payload,
      };
    },
    saveSaleContractType(state, action) {
      return {
        ...state,
        contractTypeList: action.payload,
      };
    },
    saveCustomer(state, action) {
      return {
        ...state,
        customerList: action.payload,
      };
    },
    saveInvoiceType(state, action) {
      return {
        ...state,
        invoiceTypeList: action.payload,
      };
    },
    savePayType(state, action) {
      return {
        ...state,
        payTypeList: action.payload,
      };
    },
    saveProductUnit(state, action) {
      return {
        ...state,
        productUnitList: action.payload,
      };
    },
    saveCurrency(state, action) {
      return {
        ...state,
        currencyList: action.payload,
      };
    },
    saveDispatchPlace(state, action) {
      return {
        ...state,
        dispatchPlaceList: action.payload,
      };
    },
  },
};
