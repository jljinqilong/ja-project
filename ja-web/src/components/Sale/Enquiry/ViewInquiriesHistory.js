import React, { PureComponent } from 'react';
import { connect } from 'dva/index';
import SimplePage from '../../SimplePage/SimplePage';

@connect(({ inquiries, loading }) => ({ inquiries, loading: loading.models.inquiries }))
export default class ViewInquiriesHistory extends PureComponent {
  // ============ 查询 ===============
  handleSearch = params => {
    const { inquiriesId } = this.props;

    const payload = {
      inquiriesId,
      ...params,
    };

    if (inquiriesId) {
      const { dispatch } = this.props;
      dispatch({
        type: 'inquiries/fetchInquiriesHistory',
        payload,
      });
    }
  };

  render() {
    const {
      inquiries: { inquiriesHistoryList },
      loading,
    } = this.props;

    const columns = [
      {
        title: '编号',
        dataIndex: 'inquiryNo',
        fixed: 'left',
        width: 150,
      },
      {
        title: '状态',
        dataIndex: 'statusName',
        width: 200,
      },
      {
        title: '操作人',
        dataIndex: 'createName',
        width: 200,
      },
      {
        title: '操作时间',
        dataIndex: 'createdTime',
        width: 200,
      },
      {
        title: '客户名称',
        dataIndex: 'customerName',
        width: 200,
      },
      {
        title: '项目地',
        dataIndex: 'countryProvCity',
        width: 200,
      },
      {
        title: '产品类型',
        dataIndex: 'transNames.productTypeId_name',
        width: 200,
      },
      {
        title: '功率档位',
        dataIndex: 'power',
        width: 200,
      },
      {
        title: '订单总量(MW)',
        dataIndex: 'orderTotal',
        width: 200,
      },
      // {
      //   title: '电池类型',
      //   dataIndex: 'transNames.batteryTypeId_name',
      //   width: 200,
      // },
      {
        title: '销售价格',
        dataIndex: 'totalAmount',
        width: 200,
      },
      {
        title: '主根栅数',
        dataIndex: 'mainGateNumber',
        width: 200,
      },
      {
        title: '贸易方式',
        dataIndex: 'transNames.tradeModeId_name',
        width: 200,
      },
      {
        title: '首年衰减',
        dataIndex: 'firstYearAttenuation',
        width: 200,
      },
      {
        title: '付款条件',
        dataIndex: 'paymentTerm',
        width: 200,
      },
      {
        title: '认证要求',
        dataIndex: 'certificationRequire',
        width: 200,
      },
      {
        title: '送功率',
        dataIndex: 'outputPower',
        width: 200,
      },
      {
        title: '标板要求',
        dataIndex: 'plateRequire',
        width: 200,
      },
      {
        title: '质保要求',
        dataIndex: 'warrantyRequire',
        width: 200,
      },
      {
        title: '特殊要求',
        dataIndex: 'specialRequire',
        width: 200,
      },
      {
        title: '销售区域',
        dataIndex: 'saleAreaName',
        width: 200,
      },
      {
        title: '业务员',
        dataIndex: 'salesman',
        width: 200,
      },
      {
        title: '违约条款',
        dataIndex: 'violateClause',
        width: 200,
      },
      {
        title: 'BOM要求',
        dataIndex: 'bomRequire',
        width: 200,
      },
      {
        title: '边框颜色',
        dataIndex: 'transNames.borderColorId_name',
        width: 200,
      },
      {
        title: '边框规格',
        dataIndex: 'transNames.borderSpecificationId_name',
        width: 200,
      },
      {
        title: '背板颜色',
        dataIndex: 'transNames.backboardColorId_name',
        width: 200,
      },
      {
        title: '背板材质',
        dataIndex: 'transNames.backboardMaterialId_name',
        width: 200,
      },
      {
        title: '接线盒',
        dataIndex: 'transNames.junctionBoxId_name',
        width: 200,
      },
      {
        title: '玻璃',
        dataIndex: 'glass',
        width: 200,
      },
      {
        title: 'EVA',
        dataIndex: 'transNames.evaId_name',
        width: 200,
      },
      {
        title: '成本',
        dataIndex: 'cost',
        width: 200,
      },
      {
        title: '毛利润',
        dataIndex: 'grossProfitRatio',
        width: 200,
      },
    ];

    const scroll = { x: 7000 };
    return (
      <SimplePage
        ref={page => {
          this.page = page;
        }}
        onSearch={this.handleSearch}
        columns={columns}
        loading={loading}
        data={inquiriesHistoryList}
        op={false}
        toolbar={false}
        scroll={scroll}
        opFixed={{ fixed: 'right' }}
      />
    );
  }
}
