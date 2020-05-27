import { pageList } from '../services/positionType';

export default {
  namespace: 'positionType',
  state: {
    data: {
      list: [],
      pagination: {},
    },
  },
  effects: {
    *getPageList({ payload }, { call, put }) {
      const response = yield call(pageList, payload);
      if (response.data !== undefined) {
        yield put({
          type: 'getList',
          payload: {
            data: {
              list: response.data.list,
              pagination: {
                total: response.data.total,
              },
            },
          },
        });
      }
    },
  },
  reducers: {
    getList(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
