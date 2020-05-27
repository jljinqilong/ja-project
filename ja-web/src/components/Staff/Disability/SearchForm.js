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
            <Form.Item label="员工编号">
              {getFieldDecorator('staffId')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item label="残疾类别">
              {getFieldDecorator('disabilityType')(
                <Select style={{ width: 295 }}>
                  <Select.Option value="1">智力</Select.Option>
                  <Select.Option value="2">精神</Select.Option>
                  <Select.Option value="3">综合</Select.Option>
                  <Select.Option value="4">肢体</Select.Option>
                  <Select.Option value="5">视力</Select.Option>
                  <Select.Option value="6">其他</Select.Option>
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
