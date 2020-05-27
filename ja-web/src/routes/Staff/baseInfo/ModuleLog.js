import React, { PureComponent, Fragment } from 'react';
import { Card, Table } from 'antd';
import { allListLog } from '../../../services/systemModuleLog.js';

import styles from './ModuleLog.less';

export default class ModuleLog extends PureComponent {
  state = {
    data: [],
    moduleLog: {
      optType: 'update',
      appCode: 'staff',
      tableId: this.props.staffId,
    },
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();
  }

  /**
   * 请求数据
   */
  refreshTable = () => {
    allListLog(this.state.moduleLog)
      .then(data => {
        if (!!data) {
          this.setState({
            data: data.data,
          });
        }
      })
      .catch(e => {
        console.log(e);
      });
  };

  render() {
    const { data } = this.state;
    const columns = [
      // {
      //   title: '编号',
      //   dataIndex: 'rowId',
      //   width: 150,
      // },
      {
        title: '操作时间',
        dataIndex: 'optTime',
        width: 150,
      },
      {
        title: '操作人工号',
        dataIndex: 'optStaffNo',
        width: 150,
      },
      {
        title: '操作人姓名',
        dataIndex: 'optStaffName',
        width: 150,
      },
      {
        title: '操作类型',
        render: (text, record) => <Fragment>{record.optType === 'update' && '修改'}</Fragment>,
        width: 150,
      },
      {
        title: '操作描述',
        dataIndex: 'optDescribe',
      },
    ];

    return (
      <Card bordered={false}>
        <div className={styles.tableList}>
          <div className={styles.title}>变更记录</div>
          <Table
            bordered={true}
            pagination={false}
            dataSource={data}
            columns={columns}
            size={'small'}
          />
        </div>
      </Card>
    );
  }
}
