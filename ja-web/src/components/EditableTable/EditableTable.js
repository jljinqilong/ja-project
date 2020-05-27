import React, { Fragment } from 'react';
import { Table, Button } from 'antd';
import EditableRow from './EditableRow';
import EditableCell from './EditableCell';

// 可编辑表格
export default class EditableTable extends React.Component {
  render() {
    const components = {
      body: {
        row: EditableRow,
        cell: EditableCell,
      },
    };

    const { title, columns, form, onAdd, dataSource } = this.props;

    return (
      <Fragment>
        <Button style={{ marginBottom: '24px' }} icon="plus" type="primary" onClick={() => onAdd()}>
          增加
        </Button>
        <Table
          title={() => title}
          bordered
          components={components}
          columns={columns}
          dataSource={dataSource}
          pagination={false}
          onRow={record => ({
            record,
            form,
          })}
          rowKey="rowKey"
        />
      </Fragment>
    );
  }
}
