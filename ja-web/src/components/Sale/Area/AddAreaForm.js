import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Icon, Divider, Popconfirm, message } from 'antd';
import EditableTable from '../../EditableTable/EditableTable';
import SimpleModal from '../../Modal/SimpleModal';

const FormItem = Form.Item;

@Form.create()
export default class AddAreaForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      details: [],
      count: 0,
    };

    this.columns = [
      {
        title: '洲别',
        dataIndex: 'continentId',
        width: '30%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入洲别',
          },
        ],
      },
      {
        title: '国家',
        dataIndex: 'countryId',
        width: '20%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入国家',
          },
        ],
      },
      {
        title: '币别',
        dataIndex: 'zCurrencyId',
        width: '20%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入主币别',
          },
        ],
      },
      {
        title: '单价',
        dataIndex: 'price',
        width: '20%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入单价',
          },
        ],
      },
      {
        title: '操作',
        width: '10%',
        render: (text, record, index) => (
          <Fragment>
            <Popconfirm title="确认删除?" onConfirm={() => this.handleDeleteItem(index)}>
              <a title="删除">
                <Icon type="delete" />
              </a>
            </Popconfirm>
          </Fragment>
        ),
      },
    ];
  }

  getNextSeq = () => {
    const { count } = this.state;
    this.setState({
      count: count + 1,
    });
    return count;
  };

  clear = () => {
    const { form } = this.props;

    form.resetFields();
    this.setState({
      details: [],
    });
  };

  handleOK = () => {
    const { form, handleAdd } = this.props;
    const { details } = this.state;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      if (!details || details.length === 0) {
        message.error('请先设置地区信息');
        return;
      }

      handleAdd({
        regionName: fieldsValue.regionName,
        areaDetailList: details,
      });
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.clear();
    handleModalVisible();
  };

  handleAddItem = () => {
    const { details } = this.state;

    const newData = {
      rowKey: this.getNextSeq(),
      continentId: '',
      countryId: '',
      zCurrencyId: '',
      price: '',
    };
    const newDetails = [...details, newData];
    this.updateDetails(newDetails);
  };

  handleDeleteItem = index => {
    const { details } = this.state;
    details.splice(index, 1);
    const newDetails = [...details];
    this.updateDetails(newDetails);
  };

  handleUpdateItem = record => {
    if (!record.rowKey) return; // 屏蔽 cell 自身的 onChange 事件
    const { details } = this.state;
    const rowKey = details.findIndex(detail => record.rowKey === detail.rowKey);
    if (rowKey > -1) {
      const item = details[rowKey];
      details.splice(rowKey, 1, {
        ...item,
        ...record,
      });
      const newDetails = [...details];
      this.updateDetails(newDetails);
    } else {
      const newDetails = [...details, record];
      this.updateDetails(newDetails);
    }
  };

  updateDetails = details => {
    this.setState({
      details,
    });
  };

  render() {
    const { details } = this.state;
    const { modalVisible, form, continentList, countryList, currencyList } = this.props;

    const columns = this.columns.map(col => {
      return {
        ...col,
        // 设置单元格属性
        // 如果自定义函数名会出现警告，如 warn: Invalid value for prop `updateitem` on <td> tag，Either remove it from the element, or pass a string or number value to keep it in the DOM. For details
        // 使用自定义的DOM属性作为函数名就不会有这个问题，这里使用 onChange 替代
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype: col.dataIndex === 'price' ? 'number' : 'select', // React 不建议使用 inputType
          form,
          rules: col.rules,
          datasource:
            col.dataIndex === 'continentId'
              ? continentList
              : col.dataIndex === 'countryId'
                ? countryList
                : currencyList,
          onChange: this.handleUpdateItem,
        }),
      };
    });

    return (
      <SimpleModal
        title="新增区域信息"
        width={800}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        <FormItem label="区域名称">
          {form.getFieldDecorator('regionName', {
            rules: [{ required: true, message: '请输入区域名称' }],
          })(<Input placeholder="请输入" maxLength={20} />)}
        </FormItem>
        <Divider />
        <EditableTable
          title="地区信息"
          dataSource={details}
          columns={columns}
          form={form}
          onAdd={() => this.handleAddItem()}
          onDelete={index => this.handleDeleteItem(index)}
        />
      </SimpleModal>
    );
  }
}
