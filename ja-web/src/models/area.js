import { listArea, addArea, delArea, editArea } from '../services/area';
import { getByTypeCode } from '../services/systemCode';

export default {
  namespace: 'area',

  state: {
    data: {
      list: [],
      pagination: {},
    },
    continentList: [],
    countryList: [],
    currencyList: [],
  },

  effects: {
    *fetch({ payload }, { call, put }) {
      const response = yield call(listArea, payload);
      yield put({
        type: 'save',
        payload: {
          list: response.data.list,
          pagination: {
            total: response.data.total,
            pageNum: response.data.pageNum,
          },
        },
      });
    },
    *add({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(addArea, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *del({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(delArea, payload.rowId);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *edit({ payload, successCallback, failCallback }, { call }) {
      const response = yield call(editArea, payload);

      if (response && response.code === 200) {
        if (successCallback) successCallback();
      } else if (response.code === 400) {
        if (failCallback) failCallback();
      }
    },
    *fetchContinent({ payload = { typeCode: 'CONTINENT' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveContinent',
        payload: response.data,
      });
    },
    *fetchCountry({ payload = { typeCode: 'COUNTRY' } }, { call, put }) {
      const response = yield call(getByTypeCode, payload);
      yield put({
        type: 'saveCountry',
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
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        data: action.payload,
      };
    },
    saveContinent(state, action) {
      return {
        ...state,
        continentList: action.payload,
      };
    },
    saveCountry(state, action) {
      return {
        ...state,
        countryList: action.payload,
      };
    },
    saveCurrency(state, action) {
      return {
        ...state,
        currencyList: action.payload,
      };
    },
  },
};
