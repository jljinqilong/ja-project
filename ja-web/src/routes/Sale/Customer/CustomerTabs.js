import React, { PureComponent } from 'react';
import { Tabs, Card, Button, message, Badge } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import { getCustomerById, getCustomerList, editTransferMan } from '../../../services/customer';
import CustomerInfoTab from '../../../components/Sale/Customer/CustomerInfoTab';
import CustomerContactTab from '../../../components/Sale/Customer/CustomerContactTab';
import CustomerInquiriesTab from '../../../components/Sale/Customer/CustomerInquiriesTab';
import CustomerContractTab from '../../../components/Sale/Customer/CustomerContractTab';
import StaffTransferModal from '../../../components/Sale/Common/StaffTransferModal';
import { hasAccessKey } from '../../../utils/authority';

const customerStatusMap = ['success', 'success', 'error', 'success'];
const customerStatusKeys = [1, 2, 3, 4];
const customerStatusValues = ['新增(未分配销售员)', '签核中', '签核不通过', '已分配销售员'];

export default class CustomerTabs extends PureComponent {
  state = {
    detail: {},
    count: 0,
    modalVisible: false,
    customerList: [],
    title: '转移客户',
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    const customerId = params ? params.id : null;
    this.get(customerId);
  }

  getNextSeq = () => {
    const { count } = this.state;
    this.setState({
      count: count + 1,
    });
    return count;
  };

  back = () => {
    const { dispatch } = store;

    dispatch(
      routerRedux.push({
        pathname: '/sale/customer',
      })
    );
  };

  get = rowId => {
    if (rowId) {
      getCustomerById(rowId)
        .then(resp => {
          const { data } = resp;
          const { customerContactsList } = data;
          if (customerContactsList && Array.isArray(customerContactsList)) {
            const newDetails = customerContactsList.map(item => {
              return {
                ...item,
                rowKey: this.getNextSeq(),
              };
            });

            return {
              ...data,
              customerContactsList: newDetails,
            };
          }

          return data;
        })
        .then(data => {
          this.setState({
            detail: data,
          });
        })
        .catch(() => {
          message.error('查询客户明细失败');
        });
    }
  };

  // =====================对话框=========================

  handleModalVisible = flag => {
    this.setState({
      modalVisible: !!flag,
      customerList: [],
    });
  };

  // ==========保存=====================
  handleTransfer = params => {
    const { staffId, targetId } = params;
    editTransferMan(staffId, targetId).then(resp => {
      if (resp.code === 200) {
        message.success('转移成功');
        this.back(false);
      }
    });
  };

  // ============change函数===========
  handleSearchTransferDetailData = params => {
    const { staffId } = params;
    getCustomerList(staffId)
      .then(resp => {
        const data = {
          list: resp.data.list,
          pagination: {
            total: resp.data.total,
            current: resp.data.pageNum,
            pageSize: resp.data.pageSize,
          },
        };

        this.setState({
          customerList: data,
        });
      })
      .catch(() => {
        message.error('查询销售人员失败');
      });
  };

  // ============ 刷新 ===============
  refresh = (firstPage = false) => {
    this.page.refreshTable(firstPage);
  };

  render() {
    const { detail, modalVisible, customerList, title } = this.state;
    const customerId = detail.rowId;
    const customerName = detail.customerName || '';
    const createName = detail.createName || '';
    const columns = [
      {
        title: '客户名称',
        dataIndex: 'customerName',
      },
      {
        title: '区域',
        dataIndex: 'regionName',
      },
      {
        title: '地址',
        dataIndex: 'address',
      },
      {
        title: '状态',
        dataIndex: 'customerStatus',
        render(val) {
          return (
            <Badge
              status={customerStatusMap[val]}
              text={customerStatusValues[customerStatusKeys.indexOf(val)]}
            />
          );
        },
      },
    ];


    const extra =
      hasAccessKey('sale.customer.updateTransferMan') ?
        (
          <Button type="primary" onClick={() => this.handleModalVisible(true)}>
            转移给他人
          </Button>
        )
      : null;

    return (
      <PageHeaderLayout
        title="客户详情"
        action={
          <Button type="primary" onClick={this.back} style={{ marginRight: '24px' }}>
            返回
          </Button>
        }
      >
        <Card
          title={customerName}
          extra={extra}
        >
          <p
            style={{
              fontSize: 14,
              color: 'rgba(0, 0, 0, 0.85)',
              marginBottom: 16,
              fontWeight: 500,
            }}
          >
            负责人: {createName}
          </p>
          <Tabs defaultActiveKey="1" type="card">
            <Tabs.TabPane tab="客户资料" key="1">
              <CustomerInfoTab detail={detail} />
            </Tabs.TabPane>
            <Tabs.TabPane tab="联系人" key="2">
              <CustomerContactTab data={detail.customerContactsList} />
            </Tabs.TabPane>
            <Tabs.TabPane tab="询单" key="3">
              <CustomerInquiriesTab customerId={customerId} />
            </Tabs.TabPane>
            <Tabs.TabPane tab="合同" key="4">
              <CustomerContractTab customerId={customerId} />
            </Tabs.TabPane>
          </Tabs>
        </Card>

        <StaffTransferModal
          columns={columns}
          data={customerList}
          targetId={customerId}
          modalVisible={modalVisible}
          handleSearchTransferDetailData={this.handleSearchTransferDetailData}
          handleModalVisible={() => this.handleModalVisible(false)}
          handleTransfer={params => this.handleTransfer(params)}
          title={title}
        />
      </PageHeaderLayout>
    );
  }
}
