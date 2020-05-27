import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Upload, Icon } from 'antd';
import { addUser } from '../../../services/systemUser';
import styles from './ModalAddForm.less';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

const token = getToken();

function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}

function beforeUpload(file) {
  const isJPG =
    file.type === 'image/jpg' ||
    file.type === 'image/jpeg' ||
    file.type === 'image/png' ||
    file.type === 'image/bpm';
  if (!isJPG) {
    message.error('请选择一张图片!');
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('图片必须小于2MB!');
  }
  return isJPG && isLt2M;
}

class ModalAddForm extends PureComponent {
  state = {
    loading: false,
    photo: '',
    userName: '',
    password: '',
  };

  handleChange = info => {
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
              photo: info.file.response.data,
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
      fieldsValue.photo = this.state.photo;
      if(!!fieldsValue.email && fieldsValue.email != '' && fieldsValue.email.indexOf("@") == -1){
        message.error(`邮箱格式不正确`);
        return;
      }
      addUser(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            form.resetFields();
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('用户名已存在');
          }
        });
    });
  };

  checkContent = (rule, value) => {
    if (!!value && value.length > 20) {
      message.warning('warning-不能超过20');
      message.warn('warn-不能超过20');
    }
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const uploadButton = (
      <div>
        <Icon type={this.state.loading ? 'loading' : 'plus'} />
        <div className="ant-upload-text">Upload</div>
      </div>
    );
    return (
      <Modal
        title="添加用户"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="用户名">
            {getFieldDecorator('userName', {
              initialValue: this.state.userName,
              rules: [{ required: true, message: '请输入用户名' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="密码">
            {getFieldDecorator('password', {
              initialValue: this.state.password,
              rules: [{ required: true, message: '请输入密码' }],
            })(<Input type={'password'} placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="头像">
            <Upload
              name="file"
              listType="picture-card"
              className={styles['avatar-uploader']}
              showUploadList={false}
              action={`${serverUrlPre}/system/file/upload/single?token=${token}`}
              beforeUpload={beforeUpload}
              onChange={this.handleChange}
            >
              {this.state.photo ? (
                <img
                  className={styles.uploader}
                  src={`${serverUrlPre}/system/file/download?filePath=${
                    this.state.photo
                  }&token=${token}`}
                  alt="file"
                />
              ) : (
                uploadButton
              )}
            </Upload>
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="手机号">
            {getFieldDecorator('cellphoneNo', {
              rules: [{ required: true, message: '请输入手机号' }],
            })(<Input placeholder="请输入" maxLength={11} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="邮箱">
            {getFieldDecorator('email')(<Input placeholder="请输入" maxLength={50} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
