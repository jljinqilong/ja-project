import React, { PureComponent } from 'react';
import { Form, Input, Modal, Select, message, Upload, Button, Icon, InputNumber } from 'antd';
import { editPosition } from '../../../services/position';
import { getByType } from '../../../services/grade';
import { getRankByGrade } from '../../../services/rank';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalEditForm extends PureComponent {
  state = {
    gradeTypeCode: [],
    positionRank: [],
    initialPositionRank: this.props.detailData.ranks,
    filePath: '',
  };

  componentDidMount() {
    getByType()
      .then(content => {
        this.setState({ gradeTypeCode: content.data });
      });

    this.findRankByGrade(this.props.detailData.grades);
  }

  findRankByGrade = positionGrade => {
    let params = {};

    if (positionGrade.length !== 0) {
      params.positionGrade = positionGrade.toString();

      getRankByGrade(params)
        .then(data => {
          if (data.code === 200) {
            this.setState({ positionRank: data.data });
          } else if (data.code === 400) {
            message.error('职衔已存在');
          }
        });
    } else {
      this.setState({ positionRank: [] });
    }
  };

  handleChange = value => {
    this.setState({ initialPositionRank: [] });
    /*清空已经选中的职级*/
    const { form } = this.props;
    form.setFieldsValue({
      ranks: [],
    });
    this.findRankByGrade(value);
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

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.rowId = this.props.currentEditUserId;
      fieldsValue.fileDesc = this.state.filePath;
      //业务逻辑写在这里
      editPosition(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('编辑成功');
          form.resetFields();
          this.props.handleModalVisible(false, 1, -1);
          this.props.refreshTable();
          this.setState({
            fileList: [],
            filePath: '',
          });
        }
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
        title="编辑职衔"
        visible={this.props.modalVisibleEdit}
        detailData={this.props.detailData}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔类别">
            {getFieldDecorator('positionType', {
              initialValue: this.props.detailData.positionType,
              rules: [{ required: true, message: '职衔类别为必须' }],
            })(
              <Select style={{ width: 295 }}>
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
              initialValue: this.props.detailData.positionName,
              rules: [{ required: true, message: '职衔名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等/赋值名称">
            {getFieldDecorator('grades', {
              initialValue: this.props.detailData.grades,
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
              initialValue: this.state.initialPositionRank,
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
            {getFieldDecorator('staffSize', {
              initialValue: this.props.detailData.staffSize,
              // rules: [{ required: true, message: '编制人数为必须' }],
            })(
              <InputNumber
                placeholder="请输入"
                min={0}
                max={999999}
                maxLength="6"
                formatter={value => `${value}`.replace(/\D/g, '')}
                parser={value => value.replace(/\$\s?|(,*)/g, '')}
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
            {this.props.detailData.fileDesc !== undefined &&
              this.props.detailData.fileDesc !== '' && (
                <a
                  href={`${serverUrlPre}/system/file/download?filePath=${
                    this.props.detailData.fileDesc
                  }&token=${token}`}
                  title={'职衔说明书下载'}
                  alt="file"
                >
                  职衔说明书下载
                </a>
              )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
