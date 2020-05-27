import React, { PureComponent, Fragement } from 'react';
import {
  Form,
  Input,
  Row,
  Col,
  Modal,
  Select,
  message,
  Divider,
  DatePicker,
  InputNumber,
} from 'antd';
import { addOrg } from '../../../services/org';
import styles from './BasicForm.less';
import { getByTypeCode } from '../../../services/systemCode';

class ModalAddForm extends PureComponent {
  state = {
    isOrg: '',
    isAddBase: '',
    rowId: '',
    establishDate: '',
    effectiveDate: '',
    staffAllList: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content && !!content.data) {
        content.data.map(d =>
          d.name === '在职' &&
          this.setState({
            jobStatus: d.rowId,
          })
        );
      }
    });
    this.setState({staffAllList:this.props.staffAllList})
  }

  /**
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    this.setState({
      isOrg: nextProps.isOrg,
      rowId: nextProps.rowId,
    });
  }

  handleSure = () => {
    const { form } = this.props;
    const { rowId } = this.state;
    const { isOrg } = this.state;
    if (isOrg === -1) {
      //添加基地简称不是必须
      // if (err) return;
      form.validateFields(['baseOrDeptName'],(err, fieldsValue) => {
          if (err) return;

        fieldsValue.parentId = rowId;
        fieldsValue.establishDate = this.state.establishDate;
        fieldsValue.effectiveDate = this.state.effectiveDate;
        fieldsValue.leaderNo = this.state.leaderNo;
        // fieldsValue.leaderNo = this.state.leaderNo;
        if (isOrg === -1) {
          fieldsValue.isOrg = 0;
        } else {
          fieldsValue.isOrg = 1;
        }

        addOrg(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('添加成功');
              form.resetFields();
              this.props.handleRefreshTree();
              this.props.handleModalVisible(false, 0, 0);
              this.props.handleRefreshMoveOrMergeTree();
              //  刷新树形结构
            } else if (data.code === 400) {
              message.error('用户名已存在');
            }
          })
          .catch(e => {
            message.error('添加失败！');
          });
      });
    } else {
      form.validateFields(['baseOrDeptName','baseOrDeptShortName'],(err, fieldsValue) => {
          if (err) return;

        fieldsValue.parentId = rowId;
        fieldsValue.establishDate = this.state.establishDate;
        fieldsValue.effectiveDate = this.state.effectiveDate;
        fieldsValue.leaderNo = this.state.leaderNo;
        // fieldsValue.leaderNo = this.state.leaderNo;
        if (isOrg === -1) {
          fieldsValue.isOrg = 0;
        } else {
          fieldsValue.isOrg = 1;
        }

        addOrg(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('添加成功');
              form.resetFields();
              this.props.handleRefreshTree();
              this.props.handleModalVisible(false, 0, 0);
              this.props.handleRefreshMoveOrMergeTree();
              //  刷新树形结构
            } else if (data.code === 400) {
              message.error('用户名已存在');
            }
          })
          .catch(e => {
            message.error('添加失败！');
          });
      });
    }
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeEstablishDate = (date, dateString) => {
    this.setState({
      establishDate: dateString,
    });
  };
  handleDatePickerChangeEffectiveDate = (date, dateString) => {
    this.setState({
      effectiveDate: dateString,
    });
  };

  /**
   * 下拉选择
   */
  handleChangeStaffName = (staffName, staffNo) => {
    this.setState({
      leaderName: staffName,
      leaderNo: staffNo,
    });
  };

  /**
   * 验证编制人数为正整数
   */
  // changeValue = (e) =>{
  //   console.log('==============')
  //   console.log(e.target.value);
  //   if(!(/(^[1-9]\d*$)/.test(e.target.value))){
  //     message.error('请输入正整数');
  //   }else{
  //     return false;
  //   }
  // }
  render() {
    const { getFieldDecorator } = this.props.form;
    const { isOrg } = this.state;
    const dateFormat = 'YYYY-MM-DD';

    if (isOrg === -1) {
      this.setState({ isAddBase: true });
    } else if (isOrg === 0) {
      this.setState({ isAddBase: false });
    } else if (isOrg === 1) {
      this.setState({ isAddBase: false });
    }
    const { isAddBase } = this.state;

    return (
      <Modal
        title="添加组织架构"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        {isAddBase ? (
          <div>
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="基地名称">
                    {getFieldDecorator('baseOrDeptName', {
                      rules: [{ required: true, message: '基地名称必填' }],
                    })(<Input placeholder="请输入" maxLength="50" />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="成立日期">
                    {getFieldDecorator('establishDate')(
                      <DatePicker
                        onChange={this.handleDatePickerChangeEstablishDate}
                        format={dateFormat}
                        placeholder="请输入"
                      />
                    )}
                  </Form.Item>
                </Col>
              </Row>
            </Form>
            <Divider style={{ marginBottom: 32 }} />
            <div className={styles.title}>总负责人</div>
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工信息">
                    {getFieldDecorator('leaderNo', {
                      // rules: [{required: true, message: '员工信息'}],
                    })(
                      <Select
                        showSearch
                        style={{ width: 295 }}
                        placeholder="可按工号姓名组织架构筛选"
                      >
                        {this.state.staffAllList.map(d => (
                          <Select.Option
                            key={d.rowId}
                            value={d.rowId + d.staffNo + d.staffName}
                            onClick={() => this.handleChangeStaffName(d.staffName, d.staffNo)}
                          >
                            {d.staffNo +
                              '           ' +
                              d.staffName +
                              (d.transNames === undefined ||
                              d.transNames.deptId_baseOrDeptName === undefined
                                ? ''
                                : '           ' + d.transNames.deptId_baseOrDeptName)}
                          </Select.Option>
                        ))}
                      </Select>
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="姓名">
                    {getFieldDecorator('leaderName', {
                      initialValue: this.state.leaderName,
                    })(<Input disabled="true" />)}
                  </Form.Item>
                </Col>
              </Row>
            </Form>
          </div>
        ) : (
          <div>
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门名称">
                    {getFieldDecorator('baseOrDeptName', {
                      rules: [{ required: true, message: '部门名称必填' }],
                    })(<Input placeholder="请输入" maxLength="50" />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门编号">
                    {getFieldDecorator('baseOrDeptCode')(
                      <Input placeholder="自动生成" disabled="true" maxLength="50" />
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门简称">
                    {getFieldDecorator('baseOrDeptShortName', {
                      rules: [{ required: true, message: '部门简称必填' }],
                    })(<Input placeholder="请输入" maxLength="50" />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="成立日期">
                    {getFieldDecorator('establishDate')(
                      <DatePicker
                        onChange={this.handleDatePickerChangeEstablishDate}
                        format={dateFormat}
                        placeholder="请输入"
                      />
                    )}
                  </Form.Item>
                </Col>
                {/*<Col span={12}>*/}
                {/*<Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="生效日期">*/}
                {/*{getFieldDecorator('effectiveDate')*/}
                {/*(<DatePicker onChange={this.handleDatePickerChangeEffectiveDate} format={dateFormat}  placeholder="请输入" />)}*/}
                {/*</Form.Item>*/}
                {/*</Col>*/}
              </Row>
            </Form>
            <Divider style={{ marginBottom: 32 }} />
            <div className={styles.title}>总负责人</div>
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工信息">
                    {getFieldDecorator('leaderNo', {
                      // rules: [{required: true, message: '员工信息'}],
                    })(
                      <Select
                        showSearch
                        style={{ width: 295 }}
                        placeholder="可按工号姓名组织架构筛选"
                      >
                        {this.state.staffAllList.map(d => (
                          <Select.Option
                            key={d.rowId}
                            value={d.rowId + d.staffNo + d.staffName}
                            onClick={() => this.handleChangeStaffName(d.staffName, d.staffNo)}
                          >
                            {d.staffNo +
                              '           ' +
                              d.staffName +
                              (d.transNames === undefined ||
                              d.transNames.deptId_baseOrDeptName === undefined
                                ? ''
                                : '           ' + d.transNames.deptId_baseOrDeptName)}
                          </Select.Option>
                        ))}
                      </Select>
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="姓名">
                    {getFieldDecorator('leaderName', {
                      initialValue: this.state.leaderName,
                    })(<Input disabled="true" />)}
                  </Form.Item>
                </Col>
              </Row>
            </Form>
            <Divider style={{ marginBottom: 32 }} />
            <div className={styles.title}>组织架构信息</div>
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="编制人数">
                    {getFieldDecorator('deptNum')(
                      <InputNumber min={0} max={999999} placeholder="请输入" precision="int" />
                    )}
                  </Form.Item>
                </Col>
                {/*<Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否有效">
                    {getFieldDecorator('isValid')(
                      <Select showSearch style={{width: 295}} placeholder="请选择..." >
                        <Select.Option key={0} value={0}>是</Select.Option>
                        <Select.Option key={1} value={1}>否</Select.Option>
                      </Select>
                    )}
                  </Form.Item>
                </Col>*/}
              </Row>
            </Form>
          </div>
        )}
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
