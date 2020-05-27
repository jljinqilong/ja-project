import React, { PureComponent, Fragment } from 'react';
import { Popconfirm, Icon, Alert } from 'antd';
import EditableTable from '../../EditableTable/EditableTable';

export default class SaleContractProductTable extends PureComponent {
  constructor(props) {
    super(props);

    this.columns = [
      {
        title: '产品编号',
        dataIndex: 'productionId',
        editable: true,
        width: 200,
        rules: [
          {
            required: true,
            message: '请输入产品编号',
          },
        ],
      },
      {
        title: '标称功率',
        dataIndex: 'power',
        width: 100,
      },
      {
        title: '单位',
        dataIndex: 'unit',
        editable: true,
        width: 120,
        rules: [
          {
            required: true,
            message: '请输入单位',
          },
        ],
      },
      {
        title: '总数量',
        dataIndex: 'num',
        editable: true,
        width: 150,
        rules: [
          {
            required: true,
            message: '请输入总数量',
          },
        ],
      },
      {
        title: '赠送数量',
        dataIndex: 'presentNum',
        editable: true,
      },
      {
        title: '功率',
        dataIndex: 'totalPower',
        width: 100,
      },
      {
        title: '单价',
        dataIndex: 'unitPrice',
        editable: true,
        width: 120,
        rules: [
          {
            required: true,
            message: '请输入单价',
          },
        ],
      },
      {
        title: '金额',
        dataIndex: 'amount',
        width: 120,
      },
      {
        title: '操作',
        width: 80,
        render: (text, record, index) => (
          <Fragment>
            <Popconfirm title="确认删除?" onConfirm={() => this.handleDeleteItem(index)}>
              <a title="删除">
                <Icon type="delete" />
              </a>
            </Popconfirm>
          </Fragment>
        ),
      },
    ];
  }

  handleUpdateItem = record => {
    if (!record.rowKey) return;
    const { products } = this.props;
    const rowKey = products.findIndex(detail => record.rowKey === detail.rowKey);
    if (rowKey > -1) {
      const item = products[rowKey];
      products.splice(rowKey, 1, {
        ...item,
        ...this.handleCalculate(record),
      });
      const newProducts = [...products];
      this.updateProducts(newProducts);
    } else {
      const newProducts = [...products, record];
      this.updateProducts(newProducts);
    }
  };

  handleCalculate = item => {
    const { productList, productUnitList } = this.props;
    const { productionId, unit } = item;
    const product = productList.find(pro => pro.rowId === productionId);
    const productUnit = productUnitList.find(pro => pro.rowId === unit);
    if (!product) return item;

    // 计算功率档
    const { ratedPower: power } = product;
    // 计算总功率、总金额
    const availableNum = parseFloat(item.num || 0) - parseFloat(item.presentNum || 0); // 需要做校验
    const unitPrice = parseFloat(item.unitPrice || 0);
    const powerToInt = parseInt(power, 10);
    let amount = 0;
    let totalPower = 0;
    if (productUnit) {
      if (productUnit.name === 'W') {
        totalPower = availableNum * powerToInt;
        amount = (totalPower * unitPrice).toFixed(2);
      } else if (productUnit.name === 'PCS') {
        // PCS
        totalPower = availableNum * powerToInt;
        amount = (availableNum * unitPrice).toFixed(2);
      }
    }

    return {
      ...item,
      productionNo: product.name,
      power,
      totalPower,
      amount,
      availableNum,
    };
  };

  handleAddItem = () => {
    const { products } = this.props;

    const newProduct = {
      rowKey: products.length > 0 ? products[products.length - 1].rowKey + 1 : 1,
      productionId: '',
      productionNo: '',
      power: 0,
      totalPower: 0,
      amount: 0.0,
      presentNum: 0,
    };

    const newProducts = [...products, newProduct];
    this.updateProducts(newProducts);
  };

  handleDeleteItem = index => {
    const { products } = this.props;
    products.splice(index, 1);
    const newProducts = [...products];
    this.updateProducts(newProducts);
  };

  updateProducts = products => {
    const totalAmount = products
      .map(item => item.amount)
      .filter(item => !!item)
      .reduce((sum, current) => sum + parseFloat(current), 0);

    const totalPower = products
      .map(item => item.totalPower)
      .filter(item => !!item)
      .reduce((sum, current) => sum + parseFloat(current), 0);

    const { onUpdate } = this.props;
    if (onUpdate) onUpdate(products, totalAmount, totalPower);
  };

  render() {
    const { form, productUnitList, productList, totalAmount, totalPower, products } = this.props;

    const columns = this.columns.map(col => {
      return {
        ...col,
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype:
            col.dataIndex === 'productionId' || col.dataIndex === 'unit'
              ? 'select'
              : col.dataIndex === 'num' ||
                col.dataIndex === 'presentNum' ||
                col.dataIndex === 'unitPrice'
                ? 'number'
                : 'input',
          datasource:
            col.dataIndex === 'productionId'
              ? productList
              : col.dataIndex === 'unit'
                ? productUnitList
                : null,
          form,
          rules: col.rules,
          onChange: this.handleUpdateItem,
        }),
      };
    });

    return (
      <Fragment>
        {totalAmount < 0 || totalPower < 0 ? (
          <Alert
            message="赠送数量不能大于总数量，请检查"
            type="error"
            style={{ marginBottom: 20 }}
          />
        ) : null}
        <EditableTable
          title="产品信息"
          dataSource={products}
          form={form}
          columns={columns}
          onAdd={() => this.handleAddItem()}
          onDelete={index => this.handleDeleteItem(index)}
        />
        <h2 style={{ marginTop: '20px', float: 'right' }}>
          <span style={{ marginRight: '20px' }}>总功率: {totalPower || 0}</span>
          <span>总金额: {totalAmount ? totalAmount.toFixed(2) : 0.0}</span>
        </h2>
      </Fragment>
    );
  }
}
