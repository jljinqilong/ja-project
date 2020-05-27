import React, { PureComponent } from 'react';
import { connect } from 'dva/index';
import SimplePage from '../../SimplePage/SimplePage';

@connect(({ customer, loading }) => ({
  customer,
  loading: loading.models.customer,
}))
export default class CustomerContactTab extends PureComponent {
  // ============ 查询 ===============
  handleSearch = () => {
    const { customerId, dispatch } = this.props;
    dispatch({
      type: 'customer/fetchSaleContract',
      payload: customerId,
    });
  };

  render() {
    const {
      loading,
      customer: { contractData },
    } = this.props;

    const columns = [
      {
        title: '合同编号',
        dataIndex: 'contractNo',
      },
      {
        title: '合同类型',
        dataIndex: 'transNames.contractType_name',
      },
      {
        title: '合同标题',
        dataIndex: 'contractTitle',
      },
      {
        title: '对应客户',
        dataIndex: 'customerName',
      },
      {
        title: '合同总金额',
        dataIndex: 'totalAmount',
      },
      {
        title: '签约日期',
        dataIndex: 'signDate',
      },
    ];

    return (
      <SimplePage
        loading={loading}
        onSearch={this.handleSearch}
        columns={columns}
        data={contractData}
        op={false}
        toolbar={false}
      />
    );
  }
}
