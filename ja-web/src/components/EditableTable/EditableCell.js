import React from 'react';
import { Form, Select, DatePicker, Input, InputNumber } from 'antd';
import EditableContext from './EditableContext';

const FormItem = Form.Item;

// 可编辑列
export default class EditableCell extends React.Component {
  getInput = () => {
    const { datasource, inputtype } = this.props;
    if (inputtype === 'select') {
      return (
        <Select
          placeholder="请选择"
          style={{ width: '100%' }}
          onChange={value => this.handleUpdate(value)}
        >
          {datasource.map(d => (
            <Select.Option key={d.rowId} value={d.rowId}>
              {d.name}
            </Select.Option>
          ))}
        </Select>
      );
    } else if (inputtype === 'input') {
      return <Input placeholder="请输入" onChange={e => this.handleUpdate(e.target.value)} />;
    } else if (inputtype === 'date') {
      return (
        <DatePicker
          placeholder="请选择"
          onChange={(date, dateString) => this.handleUpdate(dateString)}
        />
      );
    } else if (inputtype === 'number') {
      return (
        <InputNumber
          placeholder="请输入"
          style={{ width: '100%' }}
          min={0}
          max={999999999.99}
          onChange={value => this.handleUpdate(value)}
        />
      );
    }
  };

  handleUpdate = value => {
    const { onChange, record, dataIndex } = this.props;
    record[dataIndex] = value;
    if (onChange) {
      onChange(record);
    }
  };

  render() {
    const { editing, dataIndex, rules, record, ...restProps } = this.props;
    return (
      <EditableContext.Consumer>
        {form => {
          const { getFieldDecorator } = form;
          return (
            <td {...restProps}>
              {editing ? (
                <FormItem style={{ margin: 0 }}>
                  {getFieldDecorator(`${dataIndex}_${record.rowKey}`, {
                    rules,
                    initialValue: record[dataIndex],
                  })(this.getInput())}
                </FormItem>
              ) : (
                restProps.children
              )}
            </td>
          );
        }}
      </EditableContext.Consumer>
    );
  }
}
