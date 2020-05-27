import React, { PureComponent } from 'react';
import { Card, Table } from 'antd';

export default class CustomerContactTab extends PureComponent {
  render() {
    const { data } = this.props;

    const columns = [
      {
        title: '联系人',
        dataIndex: 'name',
      },
      {
        title: '联系人职衔',
        dataIndex: 'position',
      },
      {
        title: '手机号码',
        dataIndex: 'mobile',
      },
      {
        title: '邮箱',
        dataIndex: 'mail',
      },
      {
        title: '固定电话',
        dataIndex: 'tel',
      },
    ];

    return (
      <Card bordered={false}>
        <Table rowKey="rowKey" columns={columns} dataSource={data} pagination={false} />
      </Card>
    );
  }
}
