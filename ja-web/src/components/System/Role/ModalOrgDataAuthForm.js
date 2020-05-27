import React, { PureComponent } from 'react';
import { Modal, message, Tree } from 'antd';
import { findAllOrgChart } from '../../../services/org';
import {saveRoleAuthorityData, searchRoleAuthorityData} from '../../../services/systemRole';

export default class ModalOrgDataAuthForm extends PureComponent {

  state = {
    rowId: '',
    treeData: [],
    roleData: [],
    halfCheckedKeys:[],
  };

  componentWillReceiveProps(nextProps) {
    if (this.props.currentDataRoleId !== nextProps.currentDataRoleId && nextProps.currentDataRoleId > 0) {
      this.initTreeData(nextProps.currentDataRoleId);
    }
  }

  /**
   * 初始化树形数据
   */
  initTreeData = (roleId) => {
    searchRoleAuthorityData(roleId).then(authData => {
      if (!!authData) {
        this.setState({
          roleData: authData.data,
        });
        findAllOrgChart().then(data => {
          if (!!data && !!data.data) {
            this.setState({
              treeData: data.data,
            });
          }
        });
      }
    });
  };

  /**
   * 绘制树形
   * @param data
   * @returns {*}
   */
  renderTreeNodes = data => {
    return data.map(item => {
      if (item.children) {
        return (
          <Tree.TreeNode title={item.title} key={item.key} dataRef={item}>
            {this.renderTreeNodes(item.children)}
          </Tree.TreeNode>
        );
      }
      return <Tree.TreeNode {...item} />;
    });
  };

  /**
   * 确定事件
   */
  handleSure = () => {
    const { roleData } = this.state;
    const roleId = this.props.currentDataRoleId;
    const {halfCheckedKeys} = this.state;
    saveRoleAuthorityData(roleData,halfCheckedKeys, roleId).then(data => {
      if (data.code === 200) {
        message.success('编辑成功');
        this.props.handleModalVisible(false, 3, -1);
      }
    });
  };

  /**
   * 复选框
   * @param checkedKeys
   * @param e
   */
  onCheck = (checkedKeys, e) => {
    console.log('halfCheckedKeys1==>>' + e.halfCheckedKeys);
    this.setState({
      roleData: checkedKeys,
      halfCheckedKeys:e.halfCheckedKeys,
    });
    console.log('halfCheckedKeys2==>>' + this.state.halfCheckedKeys);
  };

  render() {
    return (
      <Modal
        title={`设置数据权限`}
        visible={this.props.modalVisibleData}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 3, -1)}
      >
        <Tree
          defaultExpandedKeys={['1']}
          checkable
          onCheck={this.onCheck}
          checkedKeys={this.state.roleData}
        >
          {this.renderTreeNodes(this.state.treeData)}
        </Tree>
      </Modal>
    );
  }
}
