import React, { PureComponent } from 'react';
import { Form, Row, Col, Input, Button, Select, DatePicker } from 'antd';
import styles from './SearchForm.less';
class SearchForm extends PureComponent {
  state = {
    startValue: '',
    endValue: '',
    startDate: '',
    endDate: '',
  };

  /**
   * 初始化
   */
  componentDidMount() {
    if (!!this.props.refreshTreeData) {
      this.props.refreshTreeData(this);
    }
  }

  /**
   * 查询表单
   * @param e
   */
  handleSearch = e => {
    e.preventDefault();
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.startDate = this.state.startDate,
      fieldsValue.endDate = this.state.endDate,
      fieldsValue.deptId = this.state.deptId;
      const values = {
        ...fieldsValue,
      };
      this.props.handleSearchTable(values);
    });
  };
  /**
   * 重置查询表单
   */
  handleFormReset = () => {
    const { form } = this.props;
    form.resetFields();
    this.setState({
      startDate: '',
      endDate: '',
    })
  };

  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      startDate: dateString,
    });
    this.onChange('startValue', date);
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      endDate: dateString,
    });
    this.onChange('endValue', date);
  };

  disabledStartDate = startValue => {
    const endValue = this.state.endValue;
    if (!startValue || !endValue) {
      return false;
    }
    return startValue.valueOf() > endValue.valueOf();
  };

  disabledEndDate = endValue => {
    const startValue = this.state.startValue;
    if (!endValue || !startValue) {
      return false;
    }
    return endValue.valueOf() <= startValue.valueOf();
  };

  onChange = (field, value) => {
    this.setState({
      [field]: value,
    });
  };

  render() {
    const { startValue, endValue } = this.state;
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 48, lg: 48, xl: 48 }}>
          <Col md={8} sm={24}>
            <Form.Item className={styles.test} label="创建开始时间">
              {getFieldDecorator('startDate', {
              })(
                <DatePicker
                  onChange={this.handleDatePickerChangeStartDate}
                  format="YYYY-MM-DD"
                  setFieldsValue={startValue}
                  disabledDate={this.disabledStartDate}
                />
              )}
            </Form.Item>
          </Col>
          < Col md={8} sm={24}>
            <Form.Item className={styles.test} label="创建结束时间">
              {getFieldDecorator('endDate', {
              })(
                <DatePicker
                  onChange={this.handleDatePickerChangeEndDate}
                  format="YYYY-MM-DD"
                  setFieldsValue={endValue}
                  disabledDate={this.disabledEndDate}
                />
              )}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item className={styles.test} label="姓名">
              {getFieldDecorator('staffName')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={{ md: 48, lg: 48, xl: 48 }}>
          <Col md={8} sm={24}>
            <Form.Item className={styles.test} label="工号">
              {getFieldDecorator('staffNo')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
        </Row>
        <div style={{ overflow: 'hidden' }}>
          <span style={{ float: 'right', marginBottom: 24 }}>
            <Button type="primary" htmlType="submit">
              查询
            </Button>
            <Button style={{ marginLeft: 8 }} onClick={this.handleFormReset}>
              重置
            </Button>
          </span>
        </div>
      </Form>
    );
  }
}

export default Form.create({})(SearchForm);
