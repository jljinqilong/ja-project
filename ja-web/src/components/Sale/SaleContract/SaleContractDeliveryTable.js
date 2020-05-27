import React, { PureComponent, Fragment } from 'react';
import { Popconfirm, Icon } from 'antd';
import moment from 'moment';
import EditableTable from '../../EditableTable/EditableTable';

export default class SaleContractDeliveryTable extends PureComponent {
  constructor(props) {
    super(props);

    this.columns = [
      {
        title: '交期要求日期',
        dataIndex: 'deliveryTime',
        editable: true,
        width: 200,
        rules: [
          {
            required: true,
            message: '请输入交期要求日期',
          },
        ],
      },
      {
        title: '交期要求数量（MW）',
        dataIndex: 'deliveryNum',
        editable: true,
        width: 200,
        rules: [
          {
            required: true,
            message: '请输入交期要求数量',
          },
        ],
      },
      {
        title: '操作',
        width: 80,
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

  handleUpdateItem = record => {
    if (!record.rowKey) return;
    const { deliveries } = this.props;
    const rowKey = deliveries.findIndex(detail => record.rowKey === detail.rowKey);
    if (rowKey > -1) {
      const item = deliveries[rowKey];
      deliveries.splice(rowKey, 1, {
        ...item,
        ...record,
      });
      const newDeliveries = [...deliveries];
      this.updateDeliveries(newDeliveries);
    } else {
      const newProducts = [...deliveries, record];
      this.updateDeliveries(newProducts);
    }
  };

  handleAddItem = () => {
    const { deliveries } = this.props;

    const newDelivery = {
      rowKey: deliveries.length > 0 ? deliveries[deliveries.length - 1].rowKey + 1 : 1,
      deliveryTime: moment(),
      deliveryNum: '',
    };

    const newDeliveries = [...deliveries, newDelivery];
    this.updateDeliveries(newDeliveries);
  };

  handleDeleteItem = index => {
    const { deliveries } = this.props;
    deliveries.splice(index, 1);
    const newDeliveries = [...deliveries];
    this.updateDeliveries(newDeliveries);
  };

  updateDeliveries = deliveries => {
    const { onUpdate } = this.props;
    if (onUpdate) onUpdate(deliveries);
  };

  render() {
    const { form, deliveries } = this.props;

    const columns = this.columns.map(col => {
      return {
        ...col,
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype: col.dataIndex === 'deliveryTime' ? 'date' : 'number',
          form,
          rules: col.rules,
          onChange: this.handleUpdateItem,
        }),
      };
    });

    return (
      <Fragment>
        <EditableTable
          title="交期信息"
          dataSource={deliveries}
          form={form}
          columns={columns}
          onAdd={() => this.handleAddItem()}
          onDelete={index => this.handleDeleteItem(index)}
        />
      </Fragment>
    );
  }
}
