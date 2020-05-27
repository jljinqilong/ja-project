import React, { PureComponent, Fragment } from 'react';
import { Table, Modal, message } from 'antd';
import { getAllList, getUserList, saveUserRole } from '../../../services/systemRole';

const columns = [
  {
    title: '编号',
    dataIndex: 'rowId',
    width: 70,
  },
  {
    title: '名称',
    dataIndex: 'name',
    width: 150,
  },
  {
    title: '描述',
    dataIndex: 'desc',
    width: 200,
  },
];

export default class ModalRoleList extends PureComponent {
  state = {
    data: [],
    selectedRowKeys: [],
  };

  componentDidMount() {
    getAllList()
      .then(content => {
        if (content.code === 200) {
          this.setState({
            data: content.data,
          });
        }
      });
  }

  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentRoleUserId !== nextProps.currentRoleUserId &&
      nextProps.currentRoleUserId > 0
    ) {
      getUserList(nextProps.currentRoleUserId)
        .then(content => {
          if (content.code === 200) {
            this.setState({
              selectedRowKeys: content.data.map(role => {
                return role.rowId;
              }),
            });
          }
        });
    }
  }

  onSelectChange = selectedRowKeys => {
    this.setState({ selectedRowKeys });
  };

  /**
   * 确定事件
   */
  handleSure = () => {
    const roleIds = this.state.selectedRowKeys.join(',');
    const userId = this.props.currentRoleUserId;
    const handleMV = this.props.handleModalVisible;
    saveUserRole({
      roleIds,
      userId,
    })
      .then(data => {
        if (data.code === 200) {
          message.success('保存成功');
          handleMV(false, 3, -1);
        } else if (data.code === 400) {
          message.error('保存失败');
        }
      });
  };

  render() {
    const { selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
    return (
      <Modal
        title="选择角色"
        visible={this.props.modalVisibleRole}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 3, -1)}
      >
        <Table
          bordered
          pagination={false}
          rowKey="rowId"
          rowSelection={rowSelection}
          columns={columns}
          size="small"
          scroll={{ y: 400 }}
          dataSource={this.state.data}
        />
      </Modal>
    );
  }
}
