import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber, Col, Row } from 'antd';
import { addExemptions } from '../../../services/exemptions';
import { getById } from '../../../services/staffBaseInfo';

class ModalAddForm extends PureComponent {
  state = {};

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.staffId = this.state.staffId;
      this.setState({
        staffId: '',
        staffName: '',
        baseId: '',
        deptId: '',
        positionName: '',
        rankName: '',
        gradeName: '',
      });
      addExemptions(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };

  /**
   * 下拉选择
   */
  handleChangeStaffName = (
    staffId,
    staffName,
  ) => {
    this.setState({
      staffId: staffId,
      staffName: staffName,
    });

    getById(staffId).then(data => {
      if (data.code === 200) {
        this.setState({
          baseId: data.data.baseId,
          deptId: data.data.deptId,
          baseName: data.data.transNames.baseId_baseOrDeptName,
          positionName: data.data.transNames.positionId_positionName,
          deptName: data.data.transNames.deptId_baseOrDeptName,
          rankName: data.data.transNames.rankId_rankName ,
          gradeName: data.data.transNames.gradeId_gradeName,
        })
        }
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加豁免考勤"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
        width={1200}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工信息">
                {getFieldDecorator('staffId', {
                  rules: [{ required: true, message: '员工信息' }],
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选">
                    {this.props.staffAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId + d.staffNo + d.staffName}
                        onClick={() =>
                          this.handleChangeStaffName(
                            d.rowId,
                            d.staffName,
                          )
                        }
                      >
                        {d.staffNo +
                          '           ' +
                          d.staffName +
                          '           ' +
                          (d.transNames === undefined ||
                          d.transNames.baseId_baseOrDeptName === undefined
                            ? ''
                            : ' ' + d.transNames.baseId_baseOrDeptName)}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="姓名">
                {getFieldDecorator('staffName', {
                  initialValue: this.state.staffName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
                {getFieldDecorator('baseName', {
                  initialValue: this.state.baseName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
                {getFieldDecorator('deptName', {
                  initialValue: this.state.deptName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔">
                {getFieldDecorator('positionName', {
                  initialValue: this.state.positionName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等/赋值名称">
                {getFieldDecorator('gradeName', {
                  initialValue: this.state.gradeName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级">
                {getFieldDecorator('rankName', {
                  initialValue: this.state.rankName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
