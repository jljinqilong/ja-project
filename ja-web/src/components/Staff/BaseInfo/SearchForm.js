import React, { PureComponent } from 'react';
import { Form, Row, Col, Input, Button, Select } from 'antd';
import { getByTypeCode } from '../../../services/systemCode';
import styles from './BasicForm.less';

class SearchForm extends PureComponent {
  state = {
    orgIds: [],
    isOnJobList: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    //是否在职
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          isOnJobList: content.data,
        });
        {
          content.data.map(
            d =>
              d.name === '在职' &&
              this.setState({
                jobStatus: d.rowId,
              })
          );
        }
      }
    });
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
      // fieldsValue.deptId = this.state.deptId;
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
        <Row gutter={{ md: 48, lg: 48, xl: 48 }}>
          <Col md={12} sm={24}>
            <Form.Item label="工号">
              {getFieldDecorator('staffNo')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
          <Col md={12} sm={24}>
            <Form.Item label="姓名">
              {getFieldDecorator('staffName')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={{ md: 36, lg: 36, xl: 48 }}>
          <Col md={12} sm={24}>
            <Form.Item label="证件号码">
              {getFieldDecorator('identityNo')(<Input placeholder="请输入" />)}
            </Form.Item>
          </Col>
          <Col md={12} sm={24}>
            <Form.Item label="在职状态">
              {getFieldDecorator('jobStatus', {
                initialValue: this.state.jobStatus,
              })(
                <Select>
                  <Select.Option key={1} value=''>
                    所有状态
                  </Select.Option>
                  {this.state.isOnJobList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
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
