import React, { PureComponent } from 'react';
import { Form, Row, Col, Input, Select, Button } from 'antd';

const { Option } = Select;

class SearchForm extends PureComponent {
  /**
   * 查询表单
   * @param e
   */
  handleSearch = e => {
    e.preventDefault();
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
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
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <Form.Item label="姓名">
              {getFieldDecorator('staffName')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item label="工号">
              {getFieldDecorator('staffNo')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item label="协议编号">
              {getFieldDecorator('agreementNo')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议类型">
              {getFieldDecorator('agreementType', {})(
                <Select>
                  {this.props.agreementCode.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议状态">
              {getFieldDecorator('agreementState', {})(
                <Select>
                  {this.props.agreementStateCode.map(d => (
                    <Select.Option key={d.rowId} value={d.code}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="续签状态">
              {getFieldDecorator('renewStatus', {})(
                <Select>
                  {this.props.renewStatusCode.map(d => (
                    <Select.Option key={d.rowId} value={d.code}>
                      {d.name}
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
