import { queryNotices } from '../services/api';
import { getAuthMenus, getRouterData } from '../services/menu';

export default {
  namespace: 'global',

  state: {
    collapsed: false,
    notices: [],
    // 存储顶部菜单数据(全局缓存)
    topMenus: [],
    // 存储左侧菜单数据
    leftMenus: {},
    menus: [],
    // 存储路由数据(全局缓存)
    routerData: [],
  },

  effects: {
    *fetchMenus(_, { call, put, select }) {
      // 增加
      const menus = yield call(getAuthMenus);
      const routerConfig = yield select(state => state.global.routerConfig);
      const routerData = yield call(getRouterData, routerConfig, menus);
      const topMenus = [];
      const leftMenus = {};
      menus.forEach(menu => {
        topMenus.push(menu);
        if (menu.children !== undefined && menu.children.length > 0) {
          leftMenus[menu.path] = menu.children;
        }
      });
      yield put({
        type: 'saveMenus',
        payload: { menus, topMenus, leftMenus },
      });

      yield put({
        type: 'saveRouterData',
        payload: routerData,
      });
    },
    *fetchNotices(_, { call, put }) {
      const data = yield call(queryNotices);
      yield put({
        type: 'saveNotices',
        payload: data,
      });
      yield put({
        type: 'user/changeNotifyCount',
        payload: data.length,
      });
    },
    *clearNotices({ payload }, { put, select }) {
      yield put({
        type: 'saveClearedNotices',
        payload,
      });
      const count = yield select(state => state.global.notices.length);
      yield put({
        type: 'user/changeNotifyCount',
        payload: count,
      });
    },
  },

  reducers: {
    changeLayoutCollapsed(state, { payload }) {
      return {
        ...state,
        collapsed: payload,
      };
    },
    saveNotices(state, { payload }) {
      return {
        ...state,
        notices: payload,
      };
    },
    saveClearedNotices(state, { payload }) {
      return {
        ...state,
        notices: state.notices.filter(item => item.type !== payload),
      };
    },
    saveMenus(state, { payload }) {
      return {
        ...state,
        menus: payload.menus,
        topMenus: payload.topMenus,
        leftMenus: payload.leftMenus,
      };
    },
    saveRouterConfig(state, { payload }) {
      return {
        ...state,
        routerConfig: payload,
      };
    },
    saveRouterData(state, { payload }) {
      return {
        ...state,
        routerData: payload,
      };
    },
  },

  subscriptions: {
    setup({ history }) {
      // Subscribe history(url) change, trigger `load` action if pathname is `/`
      return history.listen(({ pathname, search }) => {
        if (typeof window.ga !== 'undefined') {
          window.ga('send', 'pageview', pathname + search);
        }
      });
    },
  },
};
