import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Select, Row, Col } from 'antd';
import { add } from '../../../services/companyRecord';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      console.log(fieldsValue);
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;

    return (
      <Modal
        title="添加档案信息"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Row gutter={24}>
            <Form.Item>
              {getFieldDecorator('staffId', {
                initialValue: this.props.staffId,
              })(<Input type="hidden" disabled />)}
            </Form.Item>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="工号">
                <span>{this.props.staffNo}</span>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="职衔申请表A">
                {getFieldDecorator('jobApplicationA', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="竞业协议">
                {getFieldDecorator('competitionAgreement', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="员工登记表B">
                {getFieldDecorator('staffRegisterB', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="HR制度阅读回执">
                {getFieldDecorator('hrReadReceipt', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="录用审核表">
                {getFieldDecorator('hireChack', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="员工手册">
                {getFieldDecorator('staffHandbook', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="个人简历">
                {getFieldDecorator('resume', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="照片">
                {getFieldDecorator('photo', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
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
                labelCol={{ span: 12 }}
                wrapperCol={{ span: 12 }}
                label="身份证或护照复印件"
              >
                {getFieldDecorator('idCardCopies', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="工资卡信息">
                {getFieldDecorator('payCardInfo', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="户口本复印件">
                {getFieldDecorator('residenceBookletCopies', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="岗位说明书">
                {getFieldDecorator('positionDescription', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="毕业证书复印件">
                {getFieldDecorator('graduationCertificateCopies', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="学生证复印件">
                {getFieldDecorator('studentIdCardCopies', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="学位证书复印件">
                {getFieldDecorator('diplomaCopies', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="实习协议">
                {getFieldDecorator('internshipContract', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="相关证书复印件">
                {getFieldDecorator('relevantCertificateCopies', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="劳务协议">
                {getFieldDecorator('labourAgreement', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="体检报告">
                {getFieldDecorator('medicalExaminationReport', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="背调报告">
                {getFieldDecorator('backSurveyReport', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="上家公司离职证明">
                {getFieldDecorator('beforeCompanyDimission', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="劳动合同">
                {getFieldDecorator('laborContract', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
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
                labelCol={{ span: 12 }}
                wrapperCol={{ span: 12 }}
                label="诚信行为暨知识产权协议书"
              >
                {getFieldDecorator('intellectualPropertyAgreement', {
                  initialValue: 2,
                  rules: [{ required: true, message: '请选择' }],
                })(
                  <Select style={{ width: 235 }}>
                    {this.props.YesOrNoCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 12 }} wrapperCol={{ span: 12 }} label="备注信息">
                {getFieldDecorator('remark')(
                  <Input placeholder="请输入" style={{ width: 235 }} maxLength="100" />
                )}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
