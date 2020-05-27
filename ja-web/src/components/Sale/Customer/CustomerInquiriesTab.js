import React, { PureComponent } from 'react';
import { connect } from 'dva/index';
import SimplePage from '../../SimplePage/SimplePage';

@connect(({ customer, loading }) => ({
  customer,
  loading: loading.models.customer,
}))
export default class CustomerInquiriesTab extends PureComponent {
  // ============ 查询 ===============
  handleSearch = () => {
    const { customerId, dispatch } = this.props;
    dispatch({
      type: 'customer/fetchInquiries',
      payload: customerId,
    });
  };

  render() {
    const {
      loading,
      customer: { inquiriesData },
    } = this.props;

    const columns = [
      {
        title: '询价标题',
        dataIndex: 'inquiryTitle',
      },
      {
        title: '预计销售金额',
        dataIndex: 'estimatedSalesAmount',
      },
      {
        title: '预计签单日期',
        dataIndex: 'estimatedSigningDate',
      },
      {
        title: '销售阶段',
        dataIndex: 'transNames.salesPhaseId_name',
      },
      {
        title: '备注',
        dataIndex: 'remark',
      },
    ];

    return (
      <SimplePage
        loading={loading}
        onSearch={this.handleSearch}
        columns={columns}
        data={inquiriesData}
        op={false}
        toolbar={false}
      />
    );
  }
}
