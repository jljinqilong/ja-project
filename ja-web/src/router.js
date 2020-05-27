import React from 'react';
import { routerRedux, Route, Switch } from 'dva/router';
import { LocaleProvider } from 'antd';
import zhCN from 'antd/lib/locale-provider/zh_CN';
import { getRouterConfig } from './common/router';

const { ConnectedRouter } = routerRedux;

function RouterConfig({ history, app }) {
  const routerConfig = getRouterConfig(app);
  const UserLayout = routerConfig['/user'].component;
  const BasicLayout = routerConfig['/'].component;
  return (
    <LocaleProvider locale={zhCN}>
      <ConnectedRouter history={history}>
        <Switch>
          <Route path="/user" component={UserLayout}/>
          <Route
            path="/"
            render={props => <BasicLayout {...props} />}
            redirectPath="/user/login"
          />
        </Switch>
      </ConnectedRouter>
    </LocaleProvider>
  );
}

export default RouterConfig;
