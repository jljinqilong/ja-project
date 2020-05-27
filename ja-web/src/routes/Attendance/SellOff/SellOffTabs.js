import React, { PureComponent } from 'react';
import { Tabs } from 'antd';
import AlreadySellOff from './AlreadySellOff';
import SellOff from './SellOff';
import { hasAccessKey } from '../../../utils/authority';
import { pageList } from '../../../services/personalLeave';

export default class SellOffTabs extends PureComponent {

  state = {
    sellOffData:[],
    alreadySellOffData:[],
    tabKey:'1',
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
  };


  /**
   * 请求数据
   */
  refreshSellOffTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    params.status = '0';
    pageList(params)
      .then(response => {
        if (response.data !== undefined) {
          this.setState({
            sellOffData: {
              list: response.data.list,
              pagination: {
                total: response.data.total,
              },
            },
          });
        }
      })
      .catch(e => {
        console.log(e);
      });
  };


  /**
   * 请求数据
   */
  refreshAlreadySellOffTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    params.status = '1';
    pageList(params)
      .then(response => {
        if (response.data !== undefined) {
          this.setState({
            alreadySellOffData: {
              list: response.data.list,
              pagination: {
                total: response.data.total,
              },
            },
          });
        }
      })
      .catch(e => {
        console.log(e);
      });
  };

  callback = key => {
    this.setState({
      tabKey:key,
    })
    if(key === '1') {
      this.refreshSellOffTable();
    }
    if(key === '2') {
      this.refreshAlreadySellOffTable();
    }
  };



  render() {
    const TabPane = Tabs.TabPane;
    return (
      <Tabs
        onChange={this.callback}
        defaultActiveKey={
          hasAccessKey('attendance.sellOff.list')
            ? '1'
            : hasAccessKey('attendance.sellOff.list2')
            ? '2'
            : ''
        }
      >
        {hasAccessKey('attendance.sellOff.list') && (
          <TabPane tab="未销假" key="1">
            <SellOff
              refreshSellOffTable = {this.refreshSellOffTable}
              sellOffData={this.state.sellOffData}
            />
          </TabPane>
        )}
        {hasAccessKey('attendance.sellOff.list2') && (
          <TabPane tab="已销假" key="2">
            <AlreadySellOff
              refreshAlreadySellOffTable = {this.refreshAlreadySellOffTable}
              alreadySellOffData={this.state.alreadySellOffData}
            />
          </TabPane>
        )}
      </Tabs>
    );
  }
}
