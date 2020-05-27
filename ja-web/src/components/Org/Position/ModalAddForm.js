import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, Select, Upload, Button, Icon, message, InputNumber } from 'antd';
import { addPosition } from '../../../services/position';

import { getByType } from '../../../services/grade';
import { getRankByGrade } from '../../../services/rank';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalAddForm extends PureComponent {
  state = {
    gradeTypeCode: [],
    positionGrade: [],
    positionRank: [],
    data: {},
    loading: false,
    filePath: '',
    fileList: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    /**
     *
     * 岗位赋值
     */
    getByType()
      .then(content => {
        this.setState({ gradeTypeCode: content.data });
      })
      .catch(e => {
        console.log(e);
      });
  }

  findRankByGrade = positionGrade => {
    let params = {};

    if (positionGrade.length !== 0) {
      params.positionGrade = positionGrade.toString();

      getRankByGrade(params)
        .then(data => {
          if (data.code === 200) {
            // message.success('查询职级成功');
            this.setState({ positionRank: data.data });
          } else if (data.code === 400) {
            message.error('职衔已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    } else {
      this.setState({ positionRank: [] });
    }
  };

  handleChange = value => {
    this.findRankByGrade(value);
    this.setState({
      positionGrade: value,
    });
    /*清空已经选中的职级*/
    const { form } = this.props;
    form.setFieldsValue({
      ranks: [],
    });
  };

  handleChangeFile = info => {
    let fileList = info.fileList;
    fileList = fileList.slice(-1);
    this.setState({ fileList });

    if (info.file.status === 'uploading') {
      this.setState({ loading: true });
      return;
    }
    if (info.file.status === 'done') {
      if (info.file.response != null) {
        if (info.file.response.code === 200) {
          getBase64(info.file.originFileObj, () =>
            this.setState({
              loading: false,
              filePath: info.file.response.data,
            })
          );
        }
      }
    }
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.fileDesc = this.state.filePath;
      form.resetFields();
      addPosition(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
            });
          } else if (data.code === 400) {
            message.error('职衔已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };

  render() {
    const token = getToken();
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加职衔"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔类别">
            {getFieldDecorator('positionType', {
              rules: [{ required: true, message: '职衔类别为必须' }],
            })(
              <Select style={{ width: 295 }} placeholder="请选择职衔类别">
                {this.props.positionTypeCode.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.typeName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔名称">
            {getFieldDecorator('positionName', {
              rules: [{ required: true, message: '职衔名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等/赋值名称">
            {getFieldDecorator('grades', {
              rules: [{ required: true, message: '下属职等/赋值名称为必须' }],
            })(
              <Select
                style={{ width: 295 }}
                mode={'multiple'}
                placeholder="请选择职等/赋值名称，支持多选"
                onChange={this.handleChange}
              >
                {this.state.gradeTypeCode.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.gradeName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级">
            {getFieldDecorator('ranks', {
              rules: [{ required: true, message: '下属职级为必须' }],
            })(
              <Select style={{ width: 295 }} mode={'multiple'} placeholder="请选择职级，支持多选">
                {this.state.positionRank.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.rankName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="编制人数">
            {getFieldDecorator('staffSize')(
              <InputNumber
                placeholder="请输入"
                min={0}
                max={999999}
                formatter={value => `${value}`.replace(/\D/g, '')}
                parser={value => value.replace(/\$\s?|(,*)/g, '')}
                maxLength="6"
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔说明书">
            <Upload
              name="file"
              action={`${serverUrlPre}/system/file/upload/single?token=${token}`}
              onChange={this.handleChangeFile}
              fileList={this.state.fileList}
            >
              <Button>
                <Icon type="upload" />点击上传职衔说明书
              </Button>
            </Upload>
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
