import React, { PureComponent } from 'react';
import { Modal, Form, Select, message, Row } from 'antd';
import SimplePage from '../../SimplePage/SimplePage';
import { getSalePersonList } from '../../../services/customer';

const FormItem = Form.Item;

@Form.create()
export default class StaffTransferModal extends PureComponent {
  state = {
    staffId: 0,
    salePersonList: [],
  };

  componentDidMount() {
    this.getSalePersonList();
  }

  // ==========销售人员列表=================
  getSalePersonList = () => {
    getSalePersonList()
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          salePersonList: data,
        });
      })
      .catch(() => {
        message.error('查询销售人员失败');
      });
  };

  handleOK = () => {
    const { form, handleTransfer, targetId } = this.props;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const params = {
        ...fieldsValue,
        targetId,
      };

      handleTransfer(params);
    });
  };

  handleCancel = () => {
    const { form, handleModalVisible } = this.props;
    form.resetFields();
    handleModalVisible();
  };

  loadTargetDetail = async staffId => {
    await this.setState({
      staffId,
    });
    this.page.refreshTable();
  };

  handleSearch = params => {
    const { staffId } = this.state;
    if (!staffId) return;

    const { handleSearchTransferDetailData } = this.props;
    const payload = {
      ...params,
      staffId,
    };
    // 加载数据的接口
    handleSearchTransferDetailData(payload);
  };

  render() {
    const { form, modalVisible, columns, data, title } = this.props;
    const { getFieldDecorator } = form;
    const { salePersonList } = this.state;

    return (
      <Modal
        title={title}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        width={800}
      >
        <Row>
          <FormItem label="销售员" style={{ paddingLeft: 24, paddingRight: 24, marginBottom: 0 }}>
            {getFieldDecorator('staffId', {
              rules: [
                {
                  required: true,
                  message: '请选择销售员',
                },
              ],
            })(
              <Select
                placeholder="请选择"
                style={{ width: '100%' }}
                onChange={value => this.loadTargetDetail(value)}
              >
                {salePersonList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.name}
                  </Select.Option>
                ))}
              </Select>
            )}
          </FormItem>

          <SimplePage
            ref={page => {
              this.page = page;
            }}
            form={form}
            columns={columns}
            data={data}
            op={false}
            onSearch={this.handleSearch}
          />
        </Row>
      </Modal>
    );
  }
}
