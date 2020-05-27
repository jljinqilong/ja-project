import React, { PureComponent } from 'react';
import { Tabs, Card, Button, message, Badge } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import {
  getSaleContractById,
  getSaleContractList,
  editTransferMan,
} from '../../../services/saleContract';
import SaleContactInfoTab from '../../../components/Sale/SaleContract/SaleContractInfoTab';
import SaleContractPaymentPlanTab from '../../../components/Sale/SaleContract/SaleContractPaymentPlanTab';
import SaleContractInvoiceTab from '../../../components/Sale/SaleContract/SaleContractInvoiceTab';
import StaffTransferModal from '../../../components/Sale/Common/StaffTransferModal';
import { hasAccessKey } from '../../../utils/authority';

const saleContractStatusMap = ['error', 'success', 'error'];
const saleContractStatusKeys = [0, 1, 2];
const saleContractStatusValues = ['未完成', '完成', '逾期未完成'];

export default class SaleContractTabs extends PureComponent {
  state = {
    detail: {},
    count: 0,
    modalVisible: false,
    customerList: [],
    title: '转移合同',
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    const saleContractId = params ? params.id : null;
    this.get(saleContractId);
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
        pathname: '/sale/saleContract',
      })
    );
  };

  get = saleContractId => {
    if (saleContractId) {
      // 获取合同明细
      getSaleContractById(saleContractId)
        .then(resp => resp.data)
        .then(data => {
          this.setState({
            detail: data,
          });
        })
        .catch(() => {
          message.error('查询合同明细失败');
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

  handleSearchTransferDetailData = params => {
    const { staffId } = params;
    getSaleContractList(staffId)
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

  handleTransfer = params => {
    const { staffId, targetId } = params;
    editTransferMan(staffId, targetId).then(resp => {
      if (resp.code === 200) {
        message.success('转移成功！');
        this.back();
      }
    });
  };

  render() {
    const { detail, modalVisible, customerList, title } = this.state;
    const saleContractId = detail.rowId;
    const contractTitle = detail.contractTitle || '';
    const createName = detail.ourSignatory || '';

    const columns = [
      {
        title: '合同编号',
        dataIndex: 'contractNo',
        width: 250,
      },
      {
        title: '合同标题',
        dataIndex: 'contractTitle',
        width: 250,
      },
      {
        title: '合同类型',
        dataIndex: 'transNames.contractType_name',
        width: 200,
      },
      {
        title: '合同状态',
        dataIndex: 'status',
        width: 200,
        render(val) {
          return (
            <Badge
              status={saleContractStatusMap[val]}
              text={saleContractStatusValues[saleContractStatusKeys.indexOf(val)]}
            />
          );
        },
      },
    ];

    const extra =hasAccessKey('sale.saleContract.updateTransferMan')?
      (
        <Button type="primary" onClick={() => this.handleModalVisible(true)}>
          转移给他人
        </Button>
      )
      : null;


    return (
      <PageHeaderLayout
        title="合同详情"
        action={
          <Button type="primary" onClick={this.back} style={{ marginRight: '24px' }}>
            返回
          </Button>
        }
      >
        <Card
          title={contractTitle}
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
            <Tabs.TabPane tab="基本信息" key="1">
              <SaleContactInfoTab detail={detail} />
            </Tabs.TabPane>
            <Tabs.TabPane tab="回款" key="2">
              <SaleContractPaymentPlanTab saleContractId={saleContractId} />
            </Tabs.TabPane>
            <Tabs.TabPane tab="开票" key="3">
              <SaleContractInvoiceTab saleContractId={saleContractId} />
            </Tabs.TabPane>
            <Tabs.TabPane tab="物流" key="4">
              敬请期待
            </Tabs.TabPane>
            <Tabs.TabPane tab="报关" key="5">
              敬请期待
            </Tabs.TabPane>
          </Tabs>
        </Card>
        <StaffTransferModal
          columns={columns}
          data={customerList}
          targetId={saleContractId}
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
