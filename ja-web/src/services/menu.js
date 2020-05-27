import request, { serverUrlPre } from '../utils/request';

import * as menuConfig from '../common/menu';

export async function getAuthMenus() {

  return new Promise(resolve => {
    request(`${serverUrlPre}/system/resource/tree/menuRes`).then(data => {
      if (data !== undefined) {
        resolve(menuConfig.getMenuData(data.data));
      }
    });
  });
}

export async function getRouterData(routerConfig, menuData) {
  const flatMenuData = menuConfig.getFlatMenuData(menuData);
  return menuConfig.getRouterData(routerConfig, flatMenuData);
}

export function getRouterDataSync(routerConfig, menuData) {
  const flatMenuData = menuConfig.getFlatMenuData(menuData);
  return menuConfig.getRouterData(routerConfig, flatMenuData);
}

