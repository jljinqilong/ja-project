import { getCurrentLogin } from '../services/systemUser';
import { setAuths, removeAuths } from '../utils/authority';

export default {
  namespace: 'systemUser',
  state: {
    currentUser: {},
  },

  effects: {
    * fetchCurrent(_, { call, put }) {
      removeAuths();
      const response = yield call(getCurrentLogin);
      if (response.code === 200) {
        setAuths(response.data.res);
        yield put({
          type: 'saveCurrentUser',
          payload: response.data,
        });
      }
    },
  },

  reducers: {
    saveCurrentUser(state, action) {
      return {
        ...state,
        currentUser: action.payload.user || {},
      };
    },
  },
};
