import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Spin } from 'antd';
import styles from '../../index.less';

const AppMenu = (WrappedComponent) => {
  @connect(state => ({
    global: state.global,
  }))
  class AppMenuInner extends PureComponent {

    componentDidMount() {
        this.props.dispatch({
          type: 'global/fetchMenus',
        });
    }

    render() {
      const leftMenus = this.props.global.leftMenus;
      const topMenus = this.props.global.topMenus;
      const routerData = this.props.global.routerData;
      const menus = this.props.global.menus;
      if (topMenus.length <= 0 || routerData.length <= 0) {
        return <Spin size="large" className={styles.globalSpin} />;
      } else {
        return <WrappedComponent {...this.props} leftMenus={leftMenus} topMenus={topMenus} menus={menus} routerData={routerData}/>;
      }
    }
  }

  return AppMenuInner;
};

export default AppMenu;
