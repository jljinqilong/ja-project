import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Icon, Divider, Popconfirm, message } from 'antd';
import { getAreaById } from '../../../services/area';
import EditableTable from '../../EditableTable/EditableTable';
import SimpleModal from '../../Modal/SimpleModal';

const FormItem = Form.Item;

@Form.create()
export default class EditAreaForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      detail: {},
      backup: {},
      count: 0,
    };

    this.columns = [
      {
        title: '洲别',
        dataIndex: 'continentId',
        width: '30%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入洲别',
          },
        ],
      },
      {
        title: '国家',
        dataIndex: 'countryId',
        width: '20%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入国家',
          },
        ],
      },
      {
        title: '币别',
        dataIndex: 'zCurrencyId',
        width: '20%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入主币别',
          },
        ],
      },
      {
        title: '单价',
        dataIndex: 'price',
        width: '20%',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入单价',
          },
        ],
      },
      {
        title: '操作',
        width: '10%',
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

  componentDidMount() {
    const { currentEditId: currentEditAreaId } = this.props; // EditAreaForm 视为一个 State 组件，采用key管理 state 的变化，自动做销毁和创建，简化内部状态的频繁判断更新
    this.get(currentEditAreaId);
  }

  getNextSeq = () => {
    const { count } = this.state;
    this.setState({
      count: count + 1,
    });
    return count;
  };

  get = rowId => {
    if (rowId) {
      // 异步
      getAreaById(rowId)
        .then(resp => {
          // TODO 考虑错误码
          const { data } = resp;
          const { areaDetailList } = data;
          if (areaDetailList && Array.isArray(areaDetailList)) {
            const newDetails = areaDetailList.map(item => {
              return {
                ...item,
                rowKey: this.getNextSeq(),
              };
            });

            return {
              rowId: data.rowId,
              regionName: data.regionName,
              details: newDetails,
            };
          }

          return data;
        })
        .then(data => {
          this.setState({
            detail: data,
            backup: { ...data },
          });
        })
        .catch(() => {
          message.error('查询区域明细失败');
        });
    }
  };

  clear = () => {
    const { form } = this.props;

    form.resetFields();
    this.updateDetails([]);
  };

  reset = () => {
    const { backup } = this.state;
    const { form } = this.props;
    form.resetFields();
    this.setState({ detail: { ...backup } });
  };

  handleOK = () => {
    const { form, handleEdit } = this.props;
    const { detail } = this.state;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      if (!detail.details || detail.details.length === 0) {
        message.error('请先设置地区信息');
        return;
      }

      const newDetail = {
        ...detail,
        regionName: fieldsValue.regionName,
        areaDetailList: detail.details,
      };

      this.setState({
        detail: newDetail,
        backup: newDetail,
      });

      handleEdit(newDetail);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.reset();
    handleModalVisible(false); // 不要随意修改key值，key值变化会重建组件，对性能有一定的损耗
  };

  handleAddItem = () => {
    const {
      detail: { details },
    } = this.state;
    const newData = {
      rowKey: this.getNextSeq(),
      continentId: '',
      countryId: '',
      zCurrencyId: '',
      price: '',
    };
    const newDetails = [...details, newData];
    this.updateDetails(newDetails);
  };

  handleDeleteItem = index => {
    const {
      detail: { details },
    } = this.state;
    details.splice(index, 1);
    const newDetails = [...details];
    this.updateDetails(newDetails);
  };

  handleUpdateItem = record => {
    if (!record.rowKey) return; // 屏蔽 cell 自身的 onChange 事件
    const {
      detail: { details },
    } = this.state;
    const rowKey = details.findIndex(detail => record.rowKey === detail.rowKey);
    if (rowKey > -1) {
      const item = details[rowKey];
      details.splice(rowKey, 1, {
        ...item,
        ...record,
      });
      const newDetails = [...details];
      this.updateDetails(newDetails);
    } else {
      const newDetails = [...details, record];
      this.updateDetails(newDetails);
    }
  };

  updateDetails = details => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        details,
      },
    });
  };

  render() {
    const { detail } = this.state;
    const { modalVisible, form, continentList, countryList, currencyList } = this.props;

    const columns = this.columns.map(col => {
      return {
        ...col,
        // 设置单元格属性
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype: col.dataIndex === 'price' ? 'number' : 'select', // React 不建议使用 inputType
          form,
          rules: col.rules,
          datasource:
            col.dataIndex === 'continentId'
              ? continentList
              : col.dataIndex === 'countryId'
                ? countryList
                : currencyList,
          onChange: this.handleUpdateItem,
        }),
      };
    });

    return (
      <SimpleModal
        title="修改区域信息"
        width={800}
        visible={modalVisible}
        destroyOnClose
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        <FormItem label="区域名称">
          {form.getFieldDecorator('regionName', {
            rules: [{ required: true, message: '请输入区域名称' }],
            initialValue: detail.regionName,
          })(<Input placeholder="请输入" maxLength={20} />)}
        </FormItem>
        <Divider />
        <EditableTable
          title="地区信息"
          dataSource={detail.details}
          columns={columns}
          form={form}
          onAdd={() => this.handleAddItem()}
          onDelete={index => this.handleDeleteItem(index)}
        />
      </SimpleModal>
    );
  }
}
