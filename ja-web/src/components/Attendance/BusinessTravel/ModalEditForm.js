import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, InputNumber, DatePicker, Row, Select, Col } from 'antd';
import moment from 'moment';
import { editBusinessTravel } from '../../../services/businessTravel';
import { getById } from '../../../services/staffBaseInfo';
import CascaderSelect from '../../Sale/Enquiry/CascaderSelect';
import { listCountryCascader } from '../../../services/area';

const RangePicker = DatePicker.RangePicker;

class ModalEditForm extends PureComponent {
  state = {
    baseId: '',
    hours: this.props.detailData.hours,
    agentStaffName:this.props.detailData.agentStaffName,
    countryCascaderList:[],
    countryProvCity:0,
    cascaderDefaultValue:[],
  };

  componentWillMount() {
    // TODO 获取国家 list
    listCountryCascader({ pid: 1 })
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          countryCascaderList: data,
        });

        const provId = this.props.detailData.provinceId;
        // 组装数据
        this.props.detailData.countryList.forEach(d => {
          if (d.rowId === this.props.detailData.countryId) {
            d.children = this.props.detailData.provinceList; // eslint-disable-line no-param-reassign
            this.props.detailData.provinceList.forEach(p => {
              if (p.rowId === provId) {
                p.children = this.props.detailData.cityList; // eslint-disable-line no-param-reassign
              }
            });
          }
        });

        const countryId = this.props.detailData.countryId;
        const cityId = this.props.detailData.cityId;
        console.log('countryId===>>>'+countryId);

        this.setState({
          countryCascaderList: this.props.detailData.countryList,
          cascaderDefaultValue: [countryId, provId, cityId],
          countryProvCity: this.props.detailData.cityId,

          countryId : this.props.detailData.countryId,
          provinceId : this.props.detailData.provinceId,
          cityId : this.props.detailData.cityId,
        });

        console.log('cascaderDefaultValue====>>>' + this.state.cascaderDefaultValue);

      });


  };

  // 三级联动
  headleChangeCountryProvCity = targetOption => {
    const { countryCascaderList } = this.state;

    // console.log(targetOption)
    const param = {
      pid: targetOption.rowId,
    };

    listCountryCascader(param)
      .then(resp => resp.data)
      .then(data => {
        targetOption.loading = false; // eslint-disable-line no-param-reassign
        targetOption.children = data; // eslint-disable-line no-param-reassign

        this.setState({
          countryCascaderList: [...countryCascaderList],
        });
      })
      .catch(() => {
        message.error('查询地区失败');
      });
  };

  // 获取省市区结果
  headleGetCountryProvCity = async value => {
    console.log('value=====>',value);
    await this.setState({
      countryProvCity: value.join(','),

      countryId:value[0]?value[0]:null,
      provinceId:value[1]?value[1]:null,
      cityId:value[2]?value[2]:null,
      cascaderDefaultValue: value,
    });
    const {countryProvCity,countryId,provinceId,cityId} = this.state;
    console.log('countryProvCity====>',countryProvCity);
    console.log('countryId====>',countryId);
    console.log('provinceId====>',provinceId);
    console.log('cityId====>',cityId);
  };


  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;

    if(this.state.countryProvCity !== 0){
      form.setFieldsValue({
        countryProvCity: this.state.countryProvCity,
      });
    }
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      fieldsValue.staffId = this.state.staffId;
      fieldsValue.jobNoName = this.state.jobNoName;
      fieldsValue.staffNo = this.state.staffNo;
      fieldsValue.holidayTypeName = this.state.holidayTypeName;
      fieldsValue.baseId = this.state.baseId;
      fieldsValue.baseName = this.state.baseName;
      fieldsValue.deptId = this.state.deptId;
      fieldsValue.agentStaffNo = this.state.agentStaffNo;
      fieldsValue.agentStaffId = this.state.agentStaffId;
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      fieldsValue.gradeName=this.state.gradeName;
      fieldsValue.countryId = this.state.countryId;
      fieldsValue.provinceId = this.state.provinceId;
      fieldsValue.cityId = this.state.cityId;

      //业务逻辑写在这里
      editBusinessTravel(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, this.props.currentEditUserId);
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

  selectTime = (dates, dateStrings) => {
    console.log('1111--' + dates[0] + '---222--' + dates[1]);
    /*开始时间，结束时间*/
    const hours = this.calculateTime(dates[0], dates[1]);
    this.setState({
      startDate: dateStrings[0],
      endDate: dateStrings[1],
      hours: hours,
    });
  };

  /*计算两个时间的时间差*/
  calculateTime = (faultDate, completeTime) => {
    const usedTime = completeTime - faultDate; //两个时间戳相差的毫秒数
    //计算出小时数
    const hours = Math.floor((usedTime / (3600 * 1000)) * 100) / 100;
    console.log('两个时间相差 ==>> ' + hours + '时');
    return hours;
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
          staffNo: data.data.staffNo,
          baseName: data.data.transNames.baseId_baseOrDeptName,
          deptId: data.data.deptId,
          deptName: data.data.transNames.deptId_baseOrDeptName,
          gradeId:data.data.gradeId,
          gradeName:data.data.transNames.gradeId_gradeName,
        });
      }
    });
  };

  handleChangeHolidayTypeName = (
    holidayTypeName,
  ) => {
    this.setState({
      holidayTypeName: holidayTypeName,
    });
  };


  /**
   * 下拉选择
   */
  handleChangeagentStaff = (
    staffId,
    staffName,
  ) => {
    this.setState({
      agentStaffId: staffId,
      agentStaffName: staffName,
    });

    getById(staffId).then(data => {
      if (data.code === 200) {
        this.setState({
          agentStaffNo: data.data.staffNo,
        });
      }
    });
  };





  render() {
    const leftLabelCol = { span: 5 };
    const wrapperCol = { span: 15 };
    const {
      form: { getFieldDecorator },
    } = this.props;
    const statusType = [{ id: 0, name: '出差' }, { id: 1, name: '未出差' }];
    const travelType = [{ id: 0, name: '飞机' }, { id: 1, name: '高铁' },{ id: 2, name: '徒步'},{ id: 3, name: '公交车'}];
    const { countryCascaderList, cascaderDefaultValue } = this.state;
    return (
      <Modal
        title="编辑出差单"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
        width={1200}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工信息">
                {getFieldDecorator('staffId', {
                  rules: [{ required: true, message: '员工信息' }],
                  initialValue: this.props.detailData.staffId + this.props.detailData.staffNo + this.props.detailData.staffName,
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选" disabled>
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
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="姓名">
                {getFieldDecorator('staffName', {
                  initialValue: this.props.detailData.staffName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            {/*<Col span={12}>*/}
            {/*<Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">*/}
            {/*{getFieldDecorator('baseName', {*/}
            {/*initialValue: this.state.baseName,*/}
            {/*})(<Input disabled/>)}*/}
            {/*</Form.Item>*/}
            {/*</Col>*/}
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等">
                {getFieldDecorator('gradeName', {
                  initialValue: this.props.detailData.gradeName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
                {getFieldDecorator('deptName', {
                  initialValue: this.props.detailData.deptName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
                {getFieldDecorator('jobNoId',{
                  initialValue: this.props.detailData.jobNoId,
                })(
                  <Select style={{ width: 295 }} disabled>
                    {this.props.commonAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() =>
                          this.handleChangejobNoName(
                            d.jobNoName,
                          )
                        }
                      >
                        {d.jobNoName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="状态" disabled>
                {getFieldDecorator('status', {
                  initialValue: this.props.detailData.status,
                })(<Select placeholder="请选择" style={{ width: 295 }}>
                  {statusType.map(d => (
                    <Select.Option key={d.id} value={d.id}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="代理人信息">
                {getFieldDecorator('agentStaffId', {
                  rules: [{ required: true, message: '代理人信息' }],
                  initialValue: this.props.detailData.agentStaffId + this.props.detailData.agentStaffNo + this.props.detailData.agentStaffName,
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选">
                    {this.props.staffAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId + d.staffNo + d.staffName}
                        onClick={() =>
                          this.handleChangeagentStaff(
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
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="代理人姓名">
                {getFieldDecorator('agentStaffName', {
                  initialValue: this.state.agentStaffName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="选择出差时间段">
                {getFieldDecorator('startDate',{
                  initialValue: [
                    moment(this.props.detailData.startDate),
                    moment(this.props.detailData.endDate),
                  ],
                  rules: [{ required: true, message: '时间段为必须' }],
                })(
                  <RangePicker
                    ranges={{
                      Today: [moment(), moment()],
                      'This Month': [moment(), moment().endOf('month')],
                    }}
                    showTime
                    format="YYYY/MM/DD HH:mm"
                    onChange={this.selectTime}
                  />,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="时长">
                {getFieldDecorator('hours', {
                  initialValue: this.state.hours,
                })(<Input placeholder="根据时间段自动计算" disabled />)}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="同行人">
                {getFieldDecorator('togetherName', {
                  initialValue: this.props.detailData.togetherName,
                })(<Input />)}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="出行方式">
                {getFieldDecorator('travelMode', {
                  initialValue: this.props.detailData.travelMode,
                  rules: [{ required: true, message: '出行方式为必须' }],
                })(<Select placeholder="请选择" style={{ width: 295 }}>
                  {travelType.map(d => (
                    <Select.Option key={d.id} value={d.id}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item label="出差地" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('countryProvCity', {
                  rules: [
                    {
                      required: true,
                      message: '请选择出差地',
                    },
                  ],
                })(
                  <CascaderSelect
                    countryCascaderList={countryCascaderList}
                    onLoad={this.headleChangeCountryProvCity}
                    getCascaderSelectValue={this.headleGetCountryProvCity}
                    initialValue={cascaderDefaultValue}
                  />
                )}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
