import { routerRedux } from 'dva/router';
import { accountLogin, accountLogout } from '../services/login';
import { getToken, setToken, setTokenExpired } from '../utils/authority';
import { reloadAuthorized } from '../utils/Authorized';

export default {
  namespace: 'login',

  state: {
    status: undefined,
  },

  effects: {
    * login({ payload }, { call, put }) {
      const response = yield call(accountLogin, payload);
      // Login successfully
      if (response.code === 200) {
        yield put({
          type: 'changeLoginStatus',
          payload: { ...response, status: true },
        });
        reloadAuthorized();
        yield put(routerRedux.push('/'));
      } else if (response.code === 10) {
        yield put({
          type: 'changeLoginStatus',
          payload: { ...response, status: 'fail' },
        });
      } else if (response.code === 11) {
        yield put({
          type: 'changeLoginStatus',
          payload: { ...response, status: 'error' },
        });
      } else if (response.code === 12) {
        yield put({
          type: 'changeLoginStatus',
          payload: { ...response, status: 'forbid' },
        });
      } else if (response.code === 13) {
        yield put({
          type: 'changeLoginStatus',
          payload: { ...response, status: 'pwdNull' },
        });
      }
    },
    * logout(_, { call,put }) {
      const token = getToken();
      if (token !== null) {
        const response = yield call(accountLogout, token);
        if (response === 200) {
          yield put({
            type: 'changeLoginStatus',
            payload: {
              status: false,
            },
          });
        }
      }
      reloadAuthorized();
      yield put(routerRedux.push('/user/login'));
    },
  },

  reducers: {
    changeLoginStatus(state, { payload }) {
      if (payload.data) {
        setToken(payload.data);
        return {
          ...state,
          status: payload.status
        };
      } else {
        setTokenExpired();
        return {
          ...state,
          status: payload.status
        };
      }
    },
  },
};
