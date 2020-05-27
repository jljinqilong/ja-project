import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, Select, message } from 'antd';
import { getById, editGrade } from '../../../services/grade';
import { getRankType } from '../../../services/rank';

class ModalEditForm extends PureComponent {
  state = {
    base: '',
    RankAllList: [],
  };
  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentEditUserId !== nextProps.currentEditUserId &&
      nextProps.currentEditUserId > 0
    ) {
      console.log(nextProps.currentEditUserId);
      this.setState({ gradeRank: [] });
      getRankType().then(content => {
        this.setState({ RankAllList: content.data });
      });
    }
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      //业务逻辑写在这里
      console.log('fieldsValue.rowId ===' + fieldsValue.rowId);
      editGrade(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, fieldsValue.rowId);
            this.props.refreshTable();
          }
        })
        .catch(() => {
          message.error('编辑失败：请联系管理员!');
        });
    });
  };

  render() {
    const data = [
      { id: '无', name: '无' },
      { id: 0, name: '+' },
      { id: 1, name: '=' },
      { id: 2, name: '-' },
    ];
    const {
      form: { getFieldDecorator },
    } = this.props;
    const { rowId } = this.state;
    const { detailData } = this.state;
    // const { gradeName,gradeDesc , gradeRank ,postAssignment } = this.state;
    // const { gradeName,gradeDesc,gradeRank, postAssignment} = this.state;
    return (
      <Modal
        title="编辑职衔"
        rowId={rowId}
        detailData={this.props.detailData}
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        // onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等名称/赋值名称">
            {getFieldDecorator('gradeName', {
              rules: [{ required: true, message: '职等名称/赋值名称为必须' }],
              initialValue: this.props.detailData.gradeName,
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等/赋值名称描述">
            {getFieldDecorator('gradeDesc', {
              initialValue: this.props.detailData.gradeDesc,
              rules: [{ required: true, message: '职等/赋值名称描述为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="所属职级">
            {getFieldDecorator('gradeRank', {
              initialValue: this.props.detailData.gradeRank,
              rules: [{ required: true, message: '所属职级为必须' }],
            })(
              <Select style={{ width: 295 }} mode="multiple">
                {this.props.RankAllList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.rankName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="岗位赋值">
            {getFieldDecorator('postAssignment', {
              initialValue: this.props.detailData.postAssignment,
            })(
              <Select placeholder="请选择" style={{ width: 295 }}>
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

export default Form.create({})(ModalEditForm);
