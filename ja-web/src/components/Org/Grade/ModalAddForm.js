import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, Select, Tree, message } from 'antd';
import { addGrade } from '../../../services/grade';

class ModalAddForm extends PureComponent {
  state = {
    isLoading: true,
    treeData: [],
  };
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addGrade(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('职衔已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };

  renderTreeNodes = data => {
    return data.map(item => {
      if (item.children) {
        return (
          <Tree.TreeNode
            title={item.title + '(' + item.totalStaffNum + '/' + item.deptNum + ')'}
            key={item.key}
            dataRef={item}
          >
            {this.renderTreeNodes(item.children)}
          </Tree.TreeNode>
        );
      }
      return (
        <Tree.TreeNode
          title={item.title + '(' + item.totalStaffNum + '/' + item.deptNum + ')'}
          key={item.key}
        />
      );
    });
  };

  render() {
    const data = [{ id: 0, name: '+' }, { id: 1, name: '=' }, { id: 2, name: '-' }];
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加职等/赋值名称"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等名称/赋值名称">
            {getFieldDecorator('gradeName', {
              rules: [{ required: true, message: '职等名称/赋值名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等/赋值名称描述">
            {getFieldDecorator('gradeDesc', {
              rules: [{ required: true, message: '职等/赋值名称描述为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="所属职级">
            {getFieldDecorator('gradeRank', {
              rules: [{ required: true, message: '下属职级为必须' }],
            })(
              <Select placeholder="请选择" style={{ width: 295 }} mode="multiple">
                {this.props.RankAllList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.rankName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="岗位赋值">
            {getFieldDecorator('postAssignment', {})(
              <Select placeholder="请选择" style={{ width: 295 }}>
                <Select.Option key="无">无</Select.Option>
                {data.map(d => (
                  <Select.Option key={d.id} value={d.name}>
                    {d.name}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
