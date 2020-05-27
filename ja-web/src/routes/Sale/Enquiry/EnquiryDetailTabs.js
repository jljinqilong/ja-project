import React, { PureComponent } from 'react';
import { Card, Button, Tabs } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import { getEnquiryById } from '../../../services/enquiry';
import EnquiryInfoTab from '../../../components/Sale/Enquiry/EnquiryInfoTab';
import EnquiryHistoryTab from '../../../components/Sale/Enquiry/EnquiryHistoryTab';

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
  get = enquiryId => {
    getEnquiryById(enquiryId)
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
        pathname: '/sale/enquiry',
      })
    );
  };

  render() {
    const { data } = this.state;
    const salesman = data ? data.salesman : '';
    const enquiryId = data.rowId;

    return (
      <PageHeaderLayout
        title="询价详情"
        action={
          <Button type="primary" onClick={this.back}>
            返回
          </Button>
        }
      >
        <Tabs type="card" defaultActivityKey="1">
          <Tabs.TabPane tab="基本信息" key="1">
            <Card title={data ? data.inquiryTitle : ''}>
              <p
                style={{
                  fontSize: 14,
                  // color: 'rgba(0, 0, 0, 0.85)',
                  marginBottom: 16,
                  fontWeight: 500,
                }}
              >
                负责人: {salesman}
              </p>
              {/* 基本信息 */}
              <EnquiryInfoTab data={data} />
            </Card>
          </Tabs.TabPane>
          <Tabs.TabPane tab="询价历史记录" key="2">
            <Card
              title={data ? data.inquiryTitle : ''}
              extra={
                <Button type="primary" onClick={this.back}>
                  返回
                </Button>
              }
            >
              {/* 询价历史记录 */}
              <EnquiryHistoryTab enquiryId={enquiryId} />
            </Card>
          </Tabs.TabPane>
        </Tabs>
      </PageHeaderLayout>
    );
  }
}
