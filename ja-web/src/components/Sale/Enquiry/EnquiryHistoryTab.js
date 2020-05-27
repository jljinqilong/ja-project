import React, { PureComponent } from 'react';
import { connect } from 'dva/index';
import SimplePage from '../../SimplePage/SimplePage';

@connect(({ enquiry }) => ({ enquiry }))
export default class EnquiryInfoTab extends PureComponent {
  // ============ 查询 ===============
  handleSearch = () => {
    const { enquiryId } = this.props;
    const { dispatch } = this.props;
    dispatch({
      type: 'enquiry/fetchEnquiryHistory',
      payload: enquiryId,
    });
  };

  render() {
    const {
      enquiry: { enquiryHistoryList },
    } = this.props;

    const columns = [
      {
        title: '操作时间',
        dataIndex: 'createdTime',
      },
      {
        title: '询价人员',
        dataIndex: 'inquiryPerson',
      },
      {
        title: '产品类型',
        dataIndex: 'productNo',
      },
      {
        title: '客户名称',
        dataIndex: 'customerName',
      },
      {
        title: '价格',
        dataIndex: 'price',
      },
      {
        title: '备注',
        dataIndex: 'remark',
      },
    ];

    return (
      <SimplePage
        onSearch={this.handleSearch}
        columns={columns}
        data={enquiryHistoryList}
        op={false}
        toolbar={false}
      />
    );
  }
}
