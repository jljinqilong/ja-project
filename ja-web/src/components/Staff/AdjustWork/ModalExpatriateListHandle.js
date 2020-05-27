import React, { PureComponent, Fragment } from 'react';
import { Form, Card, Tabs, DatePicker, Row, Col, Button, Modal, message } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import { queryAdjustmentWorkByChangeType, updateStaffMove } from '../../../services/adjustWork';
import { hasAccessKey } from '../../../utils/authority';

class ModalExpatriateList extends PureComponent {
  state = {
    staffId: this.props.staffId,
    data: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    startDate:'',
    endDate:'',
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();
    let params = this.state.searchFormValues;
    params.staffId = this.state.staffId;
    params.changeType = 'EXPATRIATE';
    queryAdjustmentWorkByChangeType(params)
      .then(response => {
        if (response.data.list.length >0) {
          this.setState({
            startDate:response.data.list[0].expatriateStartDate,
            endDate:response.data.list[0].expatriateEndDate,
            rowId:response.data.list[0].rowId,
            staffId:response.data.list[0].staffId,
            originalStaffNo:response.data.list[0].originalStaffNo,
            newStaffNo:response.data.list[0].newStaffNo,
          });
        }
      })
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    params.staffId = this.state.staffId;
    params.changeType = 'EXPATRIATE';
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
        }
      })
  };


  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      expatriateEndDate: dateString,
    });
  };

  handleDatePickerChangeRealEndDate = (date, dateString) => {
    this.setState({
      realEndTime: dateString,
    });
  };


  handleSubmit1 = () => {
    // e.preventDefault();

    if(this.state.data.list.length <= 0 ){
      message.error('没有外派信息,不能延长!');
      return;
    }

    this.props.form.validateFields(['expatriateEndDate'],(err, values) => {
      if (err) return;

      if(this.state.expatriateEndDate <= this.state.endDate ){
        message.warn('延长结束时间不能小于等于结束时间!');
        return;
      }
      const params ={};
      params.changeType = 'EXPATRIATE';
      params.expatriateEndDate = this.state.expatriateEndDate;

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
      message.error('没有外派信息,不能结束!');
      return;
    }
    this.props.form.validateFields(['realEndDate'],(err, values) => {
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
        params.changeType = 'EXPATRIATE';
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

  render() {
    const { loading } = this.props;
    const TabPane = Tabs.TabPane;
    const {
      form: { getFieldDecorator },
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';
    const columns = [
      {
        title: '外派类型',
        dataIndex: 'expatriateType',
      },
      {
        title: '外派基地',
        dataIndex: 'expatriateBase',
      },
      {
        title: '外派部门',
        dataIndex: 'expatriateDept',
      },
      {
        title: '外派职衔',
        dataIndex: 'expatriatePosition',
      },
      {
        title: '开始时间',
        render: (text, record) => {
          return moment(record.expatriateStartDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '结束时间',
        render: (text, record) => {
          return moment(record.expatriateEndDate).format('YYYY-MM-DD');
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
        title: '工作内容',
        dataIndex: 'jobContent',
      },
    ];

    return (
      <Card title={'外派信息'} bordered={false}>
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
                    {getFieldDecorator('expatriateEndDate', {
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
            <Form >
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

export default Form.create({})(ModalExpatriateList);
