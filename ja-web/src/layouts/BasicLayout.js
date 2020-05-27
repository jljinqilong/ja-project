import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import { Layout, Icon } from 'antd';
import DocumentTitle from 'react-document-title';
import { connect } from 'dva';
import { Route, Switch, routerRedux,Redirect } from 'dva/router';
import { ContainerQuery } from 'react-container-query';
import classNames from 'classnames';
import { enquireScreen } from 'enquire-js';
import GlobalHeader from '../components/GlobalHeader';
import GlobalFooter from '../components/GlobalFooter';
import SiderMenu from '../components/SiderMenu';
import NotFound from '../routes/Exception/404';
import { getRoutes } from '../utils/utils';
import logo from '../assets/logo_login.png';
import Home from '../routes/Home/Home';

import AppMenu from '../components/_utils/AppMenu';

const { Content } = Layout;

const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
    maxWidth: 1599,
  },
  'screen-xxl': {
    minWidth: 1600,
  },
};

let isMobile;
enquireScreen(b => {
  isMobile = b;
});

class BasicLayout extends React.PureComponent {
  static childContextTypes = {
    location: PropTypes.object,
    breadcrumbNameMap: PropTypes.object,
  };

  state = {
    isMobile,
    currentTopMenu: '/home',
  };

  getChildContext() {
    const { location, routerData } = this.props;
    return {
      location,
      breadcrumbNameMap: routerData,
    };
  }

  componentDidMount() {
    enquireScreen(mobile => {
      this.setState({
        isMobile: mobile,
      });
    });
    const { dispatch } = this.props;
    dispatch({
      type: 'systemUser/fetchCurrent',
    });
  }

  getPageTitle() {
    const { routerData, location } = this.props;
    const { pathname } = location;
    let title = '晶澳';
    if (routerData[pathname] && routerData[pathname].name) {
      title = `${routerData[pathname].name} - 晶澳`;
    }
    return title;
  }

  handleMenuCollapse = collapsed => {
    const { dispatch } = this.props;
    dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    });
  };

  handleMenuClick = ({ key }) => {
    const { dispatch } = this.props;
    if (key === 'logout') {
      dispatch({
        type: 'login/logout',
      });
    }
  };

  handleTopMenuClick = (item) => {
    this.setState({
      currentTopMenu: item.key,
    });
    const { dispatch } = this.props;
    dispatch(routerRedux.push(item.key));
  };

  render() {
    const {
      systemUser: { currentUser },
      topMenus,
      leftMenus,
      routerData,
      match,
      location,
      collapsed,
    } = this.props;
    const layout = (
      <Layout>
        <SiderMenu
          // logo={logo}
          menuData={leftMenus[this.state.currentTopMenu]}
          location={location}
          isMobile={this.state.isMobile}
          collapsed={collapsed}
          onCollapse={this.handleMenuCollapse}
        />
        <Layout>
          <GlobalHeader
            logo={logo}
            currentUser={currentUser}
            isMobile={this.state.isMobile}
            menuData={topMenus}
            onMenuClick={this.handleMenuClick}
            handleTopMenuClick={this.handleTopMenuClick.bind(this)}
          />
          <Content style={{ margin: '24px 24px 0', height: '100%' }}>
            <Switch>
              <Route exact key="/" path="/" component={Home} />
              {getRoutes(match.path, routerData).map(item => (
                <Route
                  exact
                  key={item.key}
                  path={item.path}
                  component={item.component}
                />
              ))}
              <Route render={NotFound} />
            </Switch>
          </Content>
          <GlobalFooter
            links={[]}
            copyright={
              <Fragment>
                {/*Copyright <Icon type="copyright"/> 2018 */}
              </Fragment>
            }
          />
        </Layout>
      </Layout>
    );

    return (
      <DocumentTitle title={this.getPageTitle()}>
        <ContainerQuery query={query}>
          {params => <div className={classNames(params)}>{layout}</div>}
        </ContainerQuery>
      </DocumentTitle>
    );
  }
}

export default AppMenu(connect(({ systemUser, global }) => ({
  systemUser,
  collapsed: global.collapsed,
  routerConfig: global.routerConfig,
}))((BasicLayout)));
