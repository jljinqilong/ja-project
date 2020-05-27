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
    this.props.handleSearchTable({});
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <Form.Item label="角色名称">
              {getFieldDecorator('roleName')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
          <Col md={8} sm={24}>
            <Form.Item label="使用状态">
              {getFieldDecorator('status')(
                <Select placeholder="请选择" style={{ width: '100%' }}>
                  <Option value="-1">全部</Option>
                  <Option value="0">正常</Option>
                  <Option value="1">禁用</Option>
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
