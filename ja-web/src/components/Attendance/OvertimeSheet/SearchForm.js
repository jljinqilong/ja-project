import React, { PureComponent } from 'react';
import { Form, Row, Col, Input, Select, Button } from 'antd';

const { Option } = Select;

class SearchForm extends PureComponent {
  state = {
    staffId: '',
  };

  /**
   * 查询表单
   * @param e
   */
  handleSearch = e => {
    e.preventDefault();
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.staffId = this.state.staffId;
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

    this.setState({
      staffId : '',
    });
    form.resetFields();
  };

  /**
   * 下拉选择
   */
  handleChangeStaffName = staffId => {
    this.setState({
      staffId: staffId,
    });
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <Form.Item label="基地">
              {getFieldDecorator('baseId')(
                <Select placeholder="请选择" style={{ width: '100%' }}>
                  <Option key="-1">全部</Option>
                  {this.props.orgBaseList.map(d => (
                    <Option key={d.rowId}>{d.baseOrDeptName}</Option>
                  ))}
                </Select>
              )}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item label="员工信息">
              {getFieldDecorator('staffId', {
                // rules: [{required: true, message: '员工信息'}],
              })(
                <Select showSearch placeholder="可按工号姓名筛选">
                  <Option key="-1" value="-1" onClick={() => this.handleChangeStaffName(-1)}>全部</Option>
                  {this.props.staffAllList.map(d => (
                    <Select.Option
                      key={d.rowId}
                      value={d.rowId + d.staffNo + d.staffName}
                      onClick={() => this.handleChangeStaffName(d.rowId)}
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
