import { pageList } from '../services/eltSocialSecurity';

export default {
  namespace: 'eltSubsidy',
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
