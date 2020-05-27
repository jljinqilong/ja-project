import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Row, Col, Modal, message, DatePicker, Select, Button, Icon } from 'antd';
import { addOrEditEducation, getEducation } from '../../../../services/staffBaseInfo';
import moment from 'moment';
import { getByTypeCode } from '../../../../services/systemCode';

class ModalAddForm extends PureComponent {
  state = {
    staffId: '',
    entranceTime: '',
    graduateTime: '',
    yesOrNoList: [],
    educationList: [],
    degreeList: [],
    graduationSituationList: [],
    schoolingDocumentsTypeList: [],
    degreeCountryList: [],
    learningStyleList: [],
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    this.setState({ staffId: nextProps.staffId });
    //是否
    getByTypeCode({ typeCode: 'YES_OR_NO' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          yesOrNoList: content.data,
        });
      }
    });
    //EDUCATION	学位
    getByTypeCode({ typeCode: 'EDUCATION' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          educationList: content.data,
        });
      }
    });
    //DEGREE	学历
    getByTypeCode({ typeCode: 'DEGREE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          degreeList: content.data,
        });
      }
    });
    //GRADUATION_SITUATION	毕业情况
    getByTypeCode({ typeCode: 'GRADUATION_SITUATION' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          graduationSituationList: content.data,
        });
      }
    });
    //SCHOOLING_DOCUMENTS_TYPE	所获学历证书类型
    getByTypeCode({ typeCode: 'SCHOOLING_DOCUMENTS_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          schoolingDocumentsTypeList: content.data,
        });
      }
    });
    //DEGREE_COUNTRY	学位授予国家
    getByTypeCode({ typeCode: 'DEGREE_COUNTRY' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          degreeCountryList: content.data,
        });
      }
    });
    //LEARNING_STYLE	学习方式
    getByTypeCode({ typeCode: 'LEARNING_STYLE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          learningStyleList: content.data,
        });
      }
    });
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    const { staffId } = this.state;
    const { rowId } = this.state;
    form.getFieldsValue();
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if (rowId > 0) {
        fieldsValue.rowId = rowId;
      }
      fieldsValue.staffId = staffId;
      fieldsValue.entranceTime = this.state.entranceTime;
      fieldsValue.graduateTime = this.state.graduateTime;
      if (this.state.entranceTime > this.state.graduateTime) {
        message.warn('入学时间不能大于毕业时间！');
        return;
      }
      //业务逻辑写在这里
      addOrEditEducation(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('操作成功');
            form.resetFields();
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable(staffId);
          } else {
            message.error('操作失败：请稍后再试！');
          }
        })
        .catch(() => {
          message.error('操作失败：请联系管理员!');
        });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    const { staffId } = this.state;
    const { rowId } = this.state;
    form.getFieldsValue();
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if (rowId > 0) {
        fieldsValue.rowId = rowId;
      }
      fieldsValue.staffId = staffId;
      fieldsValue.entranceTime = this.state.entranceTime;
      fieldsValue.graduateTime = this.state.graduateTime;
      if (this.state.entranceTime > this.state.graduateTime) {
        message.warn('入学时间不能大于毕业时间！');
        return;
      }
      //业务逻辑写在这里
      addOrEditEducation(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('操作成功');
            form.resetFields();
            this.props.refreshTable(staffId);
          } else {
            message.error('操作失败：请稍后再试！');
          }
        })
        .catch(() => {
          message.error('操作失败：请联系管理员!');
        });
    });
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeEntranceTime = (date, dateString) => {
    this.setState({
      entranceTime: dateString,
    });
  };
  handleDatePickerChangeGraduateTime = (date, dateString) => {
    this.setState({
      graduateTime: dateString,
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
      modalVisibleAdd,
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';

    return (
      <Modal
        title={'添加教育经历'}
        visible={modalVisibleAdd}
        onOk={this.handleSure}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={() => this.props.handleModalVisible(false, 1, -1)}
          >
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={this.handleSure}>
            保存
          </Button>,
          <Button type="primary" onClick={this.handleSureNext}>
            添加下一条<Icon type="right" />
          </Button>,
        ]}
      >
        <Form>
          <p>
            <Row gutter={24}>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="学校名称">
                  {getFieldDecorator('schoolName', {
                    rules: [{ required: true, message: '请输入学校名称' }],
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="专业/方向">
                  {getFieldDecorator('majorName', {})(
                    <Input placeholder="请输入" maxLength={50} />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="入学时间">
                  {getFieldDecorator('entranceTime', {
                    rules: [{ required: true, message: '请选择入学时间' }],
                  })(
                    <DatePicker
                      onChange={this.handleDatePickerChangeEntranceTime}
                      format={dateFormat}
                      placeholder="请输入"
                    />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="毕业时间">
                  {getFieldDecorator('graduateTime', {
                    rules: [{ required: true, message: '请选择毕业时间' }],
                  })(
                    <DatePicker
                      onChange={this.handleDatePickerChangeGraduateTime}
                      format={dateFormat}
                      placeholder="请输入"
                    />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="学位">
                  {getFieldDecorator('education', {})(
                    <Select style={{ width: 295 }}>
                      {this.state.educationList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="学历">
                  {getFieldDecorator('degree', {
                    rules: [{ required: true, message: '请输入学历' }],
                  })(
                    <Select style={{ width: 295 }}>
                      {this.state.degreeList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="毕业情况">
                  {getFieldDecorator('graduationSituation', {})(
                    <Select style={{ width: 295 }}>
                      {this.state.graduationSituationList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item
                  labelCol={{ span: 7 }}
                  wrapperCol={{ span: 17 }}
                  label="所获学历证书类型"
                >
                  {getFieldDecorator('schoolingDocumentsType', {})(
                    <Select style={{ width: 295 }}>
                      {this.state.schoolingDocumentsTypeList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="学位授予国家">
                  {getFieldDecorator('degreeCountry', {})(
                    <Select style={{ width: 295 }}>
                      {this.state.degreeCountryList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="学习方式">
                  {getFieldDecorator('learningStyle', {})(
                    <Select style={{ width: 295 }}>
                      {this.state.learningStyleList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否最高学位">
                  {getFieldDecorator('isHighestDegree')(
                    <Select style={{ width: 295 }}>
                      {this.state.yesOrNoList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否最高学历">
                  {getFieldDecorator('isHighestEducation')(
                    <Select style={{ width: 295 }}>
                      {this.state.yesOrNoList.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              </Col>
            </Row>
          </p>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
