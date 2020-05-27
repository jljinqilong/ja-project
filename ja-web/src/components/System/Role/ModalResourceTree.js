import React, { PureComponent } from 'react';
import { Tree, Modal, message } from 'antd';
import { getRoleTreeData, saveRoleResource } from '../../../services/systemResource';

export default class ModalResourceTree extends PureComponent {
  state = {
    checkedKeys: [],
    treeData: [],
    saveResIds: [],
  };

  componentWillReceiveProps(nextProps) {
    if (this.props.currentResourceRoleId !== nextProps.currentResourceRoleId &&nextProps.currentResourceRoleId > 0) {
      this.initTreeData(nextProps.currentResourceRoleId);
    }
  }

  /**
   * 选择事件
   * @param checkedKeys
   */
  onCheck = (checkedKeys, e) => {
    this.setState({
      checkedKeys: checkedKeys,
      saveResIds: checkedKeys.concat(e.halfCheckedKeys),
    });
  };

  /**
   * 初始化树形数据
   */
  initTreeData = roleId => {
    getRoleTreeData({
      roleId,
      force: true,
    }).then(data => {
      this.setState({
        treeData: data.data.treeData,
        checkedKeys: data.data.checkedKeys,
        saveResIds: data.data.roleResList,
      });
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
    const resIds = this.state.saveResIds.join(',');
    const roleId = this.props.currentResourceRoleId;
    const handleMV = this.props.handleModalVisible;
    saveRoleResource({
      resIds,
      roleId,
    }).then(data => {
      if (data.code === 200) {
        message.success('保存成功');
        handleMV(false, 2, -1);
      } else if (data.code === 400) {
        message.error('保存失败');
      }
    });
  };

  render() {
    return (
      <Modal
        title="编辑操作权限"
        visible={this.props.modalVisibleResource}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 2, -1)}
      >
        <Tree checkable onCheck={this.onCheck} checkedKeys={this.state.checkedKeys}>
          {this.renderTreeNodes(this.state.treeData)}
        </Tree>
      </Modal>
    );
  }
}
