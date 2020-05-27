import React, { PureComponent, Fragment } from 'react';
import { Form, Card, Tabs, DatePicker, Row, Col, Button , message,Modal} from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import { queryAdjustmentWorkByChangeType,updateStaffMove } from '../../../services/adjustWork';
import { hasAccessKey } from '../../../utils/authority';

class ModalToLoanList extends PureComponent {
  state = {
    staffId: this.props.staffId,
    data: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    display:true,
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();
    let params = this.state.searchFormValues;
    params.staffId = this.state.staffId;
    params.changeType = 'TEMPORARILY';
    queryAdjustmentWorkByChangeType(params)
      .then(response => {
        if (response.data.list.length >0) {
          this.setState({
            startDate:response.data.list[0].temporarilyStartDate,
            endDate:response.data.list[0].temporarilyEndDate,
            rowId:response.data.list[0].rowId,
            staffId:response.data.list[0].staffId,
            originalStaffNo:response.data.list[0].originalStaffNo,
            newStaffNo:response.data.list[0].newStaffNo,
          });
        }
      });
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    params.staffId = this.state.staffId;
    params.changeType = 'TEMPORARILY';
    queryAdjustmentWorkByChangeType(params)
      .then(response => {
        if (response.data !== undefined) {
          this.setState({
            data: {
              list: response.data.list,
              pagination: {
                total: response.data.total,
              },
            },
          });
          // if(response.data.list.length === 0){
          //   this.setState({
          //     display:true,
          //   });
          // }
        }
      });
  };

  handleSubmit1 = () => {

    if(this.state.data.list.length <= 0 ){
      message.error('没有借调信息,不能延长!');
      return;
    }

    this.props.form.validateFields(['temporarilyEndDate'],(err, values) => {
      if (err) return;

      if(this.state.temporarilyEndDate <= this.state.endDate ){
        message.warn('延长结束时间不能小于等于结束时间!');
        return;
      }

      const params ={};
      params.changeType = 'TEMPORARILY';
      params.temporarilyEndDate = this.state.temporarilyEndDate;
      const rt = this.refreshTable;
      Modal.confirm({
        title: '确认',
        content: '确定延长？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
      updateStaffMove(params)
        .then(response => {
          if (response.code === 200) {
            rt();
            message.success('延长成功!');
          }else {
            message.error('延长失败!');
          }
        })
        },
        onCancel() {},
      });
    });
  };

  handleSubmit2 = () => {
    // e.preventDefault();

    if(this.state.data.list.length <= 0 ){
      message.error('没有借调信息,不能结束!');
      return;
    }

    this.props.form.validateFields(['realEndTime'],(err, values) => {
      if (err) return;

      if(this.state.realEndTime < this.state.startDate || this.state.realEndTime > this.state.endDate){
        message.warn('实际结束时间不能小于开始时间且不能大于结束时间!');
        return;
      }

      const params ={};
      params.rowId = this.state.rowId,
      params.staffId = this.state.staffId,
      params.originalStaffNo = this.state.originalStaffNo,
      params.newStaffNo = this.state.newStaffNo,
      params.changeType = 'TEMPORARILY';
      params.realEndTime = this.state.realEndTime;
      const rt = this.refreshTable;
      Modal.confirm({
        title: '确认',
        content: '确定结束？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
      updateStaffMove(params)
        .then(response => {
          if (response.code === 200) {
            rt();
            message.success('结束成功!');
          }else {
            message.error('结束失败!');
          }
        })
        },
        onCancel() {},
      });
    });
  };

  /**
   * 翻页事件
   * @param pagination
   * @param filtersArg
   * @param sorter
   */
  handleStandardTableChange = pagination => {
    const sfvs = this.state.searchFormValues;
    sfvs.pageNum = pagination.current;
    sfvs.pageSize = pagination.pageSize;
    this.setState({
      searchFormValues: sfvs,
    });
    this.refreshTable();
  };

  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      temporarilyEndDate: dateString,
    });
  };

  handleDatePickerChangeRealEndDate = (date, dateString) => {
    this.setState({
      realEndTime: dateString,
    });
  };


  render() {
    const { loading } = this.props;
    const TabPane = Tabs.TabPane;
    const {
      form: { getFieldDecorator },
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';
    const action = (
      <Fragment>
        <Button type="primary" onClick={this.handleSure}>
          提交
        </Button>
      </Fragment>
    );
    const columns = [
      {
        title: '借调类型',
        dataIndex: 'temporarilyType',
      },
      {
        title: '借调基地',
        dataIndex: 'temporarilyBase',
      },
      {
        title: '借调部门',
        dataIndex: 'temporarilyDept',
      },
      {
        title: '借调职衔',
        dataIndex: 'temporarilyPosition',
      },
      {
        title: '开始时间',
        render: (text, record) => {
          return moment(record.temporarilyStartDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '结束时间',
        render: (text, record) => {
          return moment(record.temporarilyEndDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '实际结束时间',
        render: (text, record) => {
          if(!!record.realEndTime){
            return moment(record.realEndTime).format('YYYY-MM-DD');
          }else {
            return '';
          }
        },
      },
      {
        title: '描述',
        dataIndex: 'changeReason',
      },
    ];

    return (
      <Card title={'借调信息'} bordered={false}>
        <StandardTable
          rowKey="rowId"
          loading={loading}
          data={this.state.data}
          // data={[]}
          columns={columns}
          onChange={this.handleStandardTableChange}
        />
        <Tabs
          // defaultActiveKey={
          //   hasAccessKey('staff.contract.list')
          //     ? '1'
          //     : hasAccessKey('staff.agreement.list')
          //     ? '2'
          //     : ''
          // }
          defaultActiveKe="1"
        >
          <TabPane tab="延长" key="1">
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="结束时间">
                    {getFieldDecorator('temporarilyEndDate', {
                      rules: [{ required: true, message: '请选择结束时间' }],
                    })(
                      <DatePicker
                        style={{ width: 300 }}
                        onChange={this.handleDatePickerChangeEndDate}
                        format={dateFormat}
                        placeholder="请选择结束时间"
                      />,
                    )}&nbsp;&nbsp;
                    <Button type="primary" onClick={this.handleSubmit1}>
                      保存
                    </Button>
                  </Form.Item>
                </Col>
              </Row>
            </Form>
          </TabPane>

          <TabPane tab="结束" key="2">
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="实际结束时间">
                    {getFieldDecorator('realEndTime', {
                      rules: [{ required: true, message: '请选择结束时间' }],
                    })(
                      <DatePicker
                        style={{ width: 300 }}
                        onChange={this.handleDatePickerChangeRealEndDate}
                        format={dateFormat}
                        placeholder="请选择结束时间"
                      />,
                    )}&nbsp;&nbsp;
                    <Button type="primary" onClick={this.handleSubmit2}>
                      保存
                    </Button>
                  </Form.Item>
                </Col>
              </Row>
            </Form>
          </TabPane>

        </Tabs>
      </Card>
    );
  }
}

export default Form.create({})(ModalToLoanList);
