import React, { Fragment } from 'react';
import { Link, Redirect, Switch, Route } from 'dva/router';
import DocumentTitle from 'react-document-title';
import { Icon, Spin } from 'antd';
import GlobalFooter from '../components/GlobalFooter';
import styles from './UserLayout.less';
import logo from '../assets/logo_login.png';
import { getRoutes } from '../utils/utils';

import AppNoMenu from '../components/_utils/AppNoMenu';

const links = [];

const copyright = (
  <Fragment>
    {/*Copyright <Icon type="copyright" /> 2018 */}
  </Fragment>
);

class UserLayout extends React.PureComponent {
  getPageTitle() {
    const { routerData, location } = this.props;
    const { pathname } = location;
    let title = '晶澳';
    if (routerData[pathname] && routerData[pathname].name) {
      title = `${routerData[pathname].name} - 晶澳`;
    }
    return title;
  }

  render() {
    const { routerData, match } = this.props;
    return (
      <DocumentTitle title={this.getPageTitle()}>
        <div className={styles.container}>
          <div className={styles.content}>
            <div className={styles.top}>
              <div className={styles.header}>
                {/*<Link to="/">*/}
                <img alt="logo" className={styles.logo} src={logo} />
                <span className={styles.title}>晶澳集团</span>
                {/*</Link>*/}
              </div>
              {/*<div className={styles.desc}>后台管理系统 - 授权登录</div>*/}
            </div>
            <Switch>
              {getRoutes(match.path, routerData).map(item => (
                <Route
                  key={item.key}
                  path={item.path}
                  component={item.component}
                  exact={item.exact}
                />
              ))}
              <Redirect exact from="/user" to="/user/login" />
            </Switch>
          </div>
          <GlobalFooter links={links} copyright={copyright} />
        </div>
      </DocumentTitle>
    );
  }
}

export default AppNoMenu(UserLayout);
