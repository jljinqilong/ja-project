import React, { PureComponent } from 'react';
import { Button } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import { getInquiriesById } from '../../../services/inquiries';
import EnquiryInfoTab from '../../../components/Sale/Enquiry/EnquiryInfoTab';

export default class EnquiryDetailTabs extends PureComponent {
  state = {
    data: {},
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    const enquiryId = params.id;
    this.get(enquiryId);
  }

  /* 查询明细发起请求 */
  get = inquiriesId => {
    getInquiriesById(inquiriesId)
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          data,
        });
      });
  };

  /* 返回按钮 */
  back = () => {
    const { dispatch } = store;
    dispatch(
      routerRedux.push({
        pathname: '/sale/inquiriesAppraisal',
      })
    );
  };

  render() {
    const { data } = this.state;
    // const salesman = data ? data.salesman : '';
    // const enquiryId = data.rowId;

    return (
      <PageHeaderLayout
        title="询单评审记录详情"
        action={
          <Button type="primary" onClick={this.back}>
            返回
          </Button>
        }
      >
        <EnquiryInfoTab data={data} />
      </PageHeaderLayout>
    );
  }
}
