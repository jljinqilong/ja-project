import React, { Component } from 'react';
import { connect } from 'dva';
import { Checkbox, Alert } from 'antd';
import Login from 'components/Login';
import styles from './Login.less';

import ModalChangePasswordForm from '../../components/System/User/ModalChangePasswordForm'

const { Tab, UserName, Password, Submit } = Login;

@connect(({ login, loading }) => ({
  login,
  submitting: loading.effects['login/login'],
}))
export default class LoginPage extends Component {
  state = {
    autoLogin: true,
    modalVisiblePassword: false,
  };

  handleSubmit = (err, values) => {
    const { dispatch } = this.props;
    if (!err) {
      dispatch({
        type: 'login/login',
        payload: {
          ...values,
        },
      });
    }
  };

  changeAutoLogin = e => {
    this.setState({
      autoLogin: e.target.checked,
    });
  };

  changePasswordView = (flag) => {
    this.setState({
      modalVisiblePassword: !!flag,
    });
  };

  renderMessage = content => {
    return <Alert style={{ marginBottom: 24 }} message={content} type="error" showIcon />;
  };

  render() {
    const { login, submitting } = this.props;
    const { autoLogin } = this.state;
    return (
      <div className={styles.main}>
        <Login defaultActiveKey="account" onSubmit={this.handleSubmit}>
          <Tab key="account" tab="账户密码登录">
            {login.status === 'fail' && !submitting && this.renderMessage('用户名不存在!')}
            {login.status === 'error' && !submitting && this.renderMessage('密码错误!')}
            {login.status === 'forbid' && !submitting && this.renderMessage('账号被禁用，请联系管理员!')}
            {login.status === 'pwdNull' && !submitting && this.renderMessage('请联系管理员设置密码!')}
            <UserName name="userName" placeholder="请输入用户名" />
            <Password name="password" placeholder="请输入密码" />
          </Tab>
          <div>
            <Checkbox checked={autoLogin} onChange={this.changeAutoLogin}>
              记住账号密码
            </Checkbox>
            <a style={{ float: 'right' }} onClick={this.changePasswordView}>修改密码</a>
          </div>
          <Submit loading={submitting}>登录</Submit>
        </Login>
        <ModalChangePasswordForm
          modalVisiblePassword={this.state.modalVisiblePassword}
          changePasswordView={this.changePasswordView.bind(this)}
        />
      </div>
    );
  }
}
