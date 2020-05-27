import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Select, Row, Col } from 'antd';
import { getById, update } from '../../../services/companyRecord';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentEditUserId != nextProps.currentEditUserId &&
      nextProps.currentEditUserId > 0
    ) {
      getById(nextProps.currentEditUserId)
        .then(data => {
          this.setState({
            rowId: data.data.rowId,
            staffId: data.data.staffId,
            jobApplicationA: data.data.jobApplicationA,
            competitionAgreement: data.data.competitionAgreement,
            staffRegisterB: data.data.staffRegisterB,
            hrReadReceipt: data.data.hrReadReceipt,
            hireChack: data.data.hireChack,
            staffHandbook: data.data.staffHandbook,
            resume: data.data.resume,
            photo: data.data.photo,
            idCardCopies: data.data.idCardCopies,
            payCardInfo: data.data.payCardInfo,
            residenceBookletCopies: data.data.residenceBookletCopies,
            positionDescription: data.data.positionDescription,
            graduationCertificateCopies: data.data.graduationCertificateCopies,
            studentIdCardCopies: data.data.studentIdCardCopies,
            diplomaCopies: data.data.diplomaCopies,
            internshipContract: data.data.internshipContract,
            relevantCertificateCopies: data.data.relevantCertificateCopies,
            labourAgreement: data.data.labourAgreement,
            medicalExaminationReport: data.data.medicalExaminationReport,
            backSurveyReport: data.data.backSurveyReport,
            beforeCompanyDimission: data.data.beforeCompanyDimission,
            laborContract: data.data.laborContract,
            remark: data.data.remark,
            intellectualPropertyAgreement: data.data.intellectualPropertyAgreement,
          });
        })
        .catch(() => {
          message.error('查询信息失败');
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
      fieldsValue.rowId = this.props.currentEditUserId;
      // form.resetFields();
      //业务逻辑写在这里
      update(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable();
          } else {
            message.error('编辑失败：请稍后再试！');
          }
        })
        .catch(() => {
          message.error('编辑失败：请联系管理员!');
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="修改信息"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <Row gutter={24}>
            <Form.Item>
              {getFieldDecorator('staffId', {
                initialValue: this.state.staffId,
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
                  initialValue: this.state.jobApplicationA,
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
                  initialValue: this.state.competitionAgreement,
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
                  initialValue: this.state.staffRegisterB,
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
                  initialValue: this.state.hrReadReceipt,
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
                  initialValue: this.state.hireChack,
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
                  initialValue: this.state.staffHandbook,
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
                  initialValue: this.state.resume,
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
                  initialValue: this.state.photo,
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
                  initialValue: this.state.idCardCopies,
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
                  initialValue: this.state.payCardInfo,
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
                  initialValue: this.state.residenceBookletCopies,
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
                  initialValue: this.state.positionDescription,
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
                  initialValue: this.state.graduationCertificateCopies,
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
                  initialValue: this.state.studentIdCardCopies,
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
                  initialValue: this.state.diplomaCopies,
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
                  initialValue: this.state.internshipContract,
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
                  initialValue: this.state.relevantCertificateCopies,
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
                  initialValue: this.state.labourAgreement,
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
                  initialValue: this.state.medicalExaminationReport,
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
                  initialValue: this.state.backSurveyReport,
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
                  initialValue: this.state.beforeCompanyDimission,
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
                  initialValue: this.state.laborContract,
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
                  initialValue: this.state.intellectualPropertyAgreement,
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
                {getFieldDecorator('remark', {
                  initialValue: this.state.remark,
                })(<Input placeholder="请输入" style={{ width: 235 }} maxLength="100" />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
