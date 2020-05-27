import React, { PureComponent } from 'react';
import { Menu, Icon, Spin, Dropdown, Avatar, Row, Col } from 'antd';
import Debounce from 'lodash-decorators/debounce';
import styles from './index.less';
import { getToken } from '../../utils/authority';

import { serverUrlPre } from '../../utils/request';

export default class GlobalHeader extends PureComponent {
  componentWillUnmount() {
    this.triggerResizeEvent.cancel();
  }

  toggle = () => {
    const { collapsed, onCollapse } = this.props;
    onCollapse(!collapsed);
    this.triggerResizeEvent();
  };

  /* eslint-disable*/
  @Debounce(600)
  triggerResizeEvent() {
    const event = document.createEvent('HTMLEvents');
    event.initEvent('resize', true, false);
    window.dispatchEvent(event);
  }

  render() {
    const { currentUser, onMenuClick, menuData } = this.props;
    const {photo} = currentUser;
    const {userName} = currentUser;
    const token = getToken();
    const menu = (
      <Menu className={styles.menu} selectedKeys={[]} onClick={onMenuClick}>
        <Menu.Item disabled>
          <Icon type="user" />个人中心
        </Menu.Item>
        <Menu.Item disabled>
          <Icon type="setting" />设置
        </Menu.Item>
        <Menu.Divider />
        <Menu.Item key="logout">
          <Icon type="logout" />退出登录
        </Menu.Item>
      </Menu>
    );
    return (
      <div className={styles.header}>
        <Row>
          <Col span={16}>
            <Menu
              defaultSelectedKeys={['/home']}
              onClick={this.props.handleTopMenuClick}
              mode="horizontal"
              className={styles.height}
            >
              {menuData.map(item => <Menu.Item key={item.path}>{item.name}</Menu.Item>)}
            </Menu>
          </Col>
          <Col span={8}>
            <div className={styles.right}>
              {userName ? (
                <Dropdown overlay={menu}>
                  <span className={`${styles.action} ${styles.account}`}>
                    <Avatar
                      // size="small"
                      className={styles.avatar}
                      src={`${serverUrlPre}/system/file/download?filePath=${photo}&token=${token}`}
                    />
                    <span className={styles.name}>{userName}</span>
                  </span>
                </Dropdown>
              ) : (
                <Spin size="small" style={{ marginLeft: 8 }} />
              )}
            </div>
          </Col>
        </Row>
      </div>
    );
  }
}
