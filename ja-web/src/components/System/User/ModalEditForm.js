import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Upload, Icon } from 'antd';
import { getById, editUser } from '../../../services/systemUser';
import styles from './ModalEditForm.less';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

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

class ModalEditForm extends PureComponent {
  state = {
    userName: '',
    cellphoneNo: '',
    email: '',
    photo: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  // componentWillReceiveProps(nextProps) {
  //   if (this.props.currentEditUserId !== nextProps.currentEditUserId && nextProps.currentEditUserId > 0) {
  //     getById(nextProps.currentEditUserId)
  //       .then(data => {
  //         this.setState({
  //           rowId: data.data.rowId,
  //           userName: data.data.userName,
  //           password: data.data.password,
  //           cellphoneNo: data.data.cellphoneNo,
  //           email: data.data.email,
  //           photo: data.data.photo,
  //         });
  //       });
  //   }
  // }

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

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form, detailData } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.rowId = this.props.currentEditUserId;
      fieldsValue.photo = detailData.photo;
      if(!!this.state.photo){
        fieldsValue.photo = this.state.photo;
      }
      if(!!fieldsValue.email && fieldsValue.email != '' && fieldsValue.email.indexOf("@") == -1){
        message.error(`邮箱格式不正确`);
        return;
      }
      editUser(fieldsValue).then(data => {
        if (data.code === 200) {
          //业务逻辑写在这里
          form.resetFields();
          this.setState({
            userName: fieldsValue.userName,
            cellphoneNo: fieldsValue.cellphoneNo,
            email: fieldsValue.email,
          });
          message.success('编辑成功');
          this.props.handleModalVisible(false, 1, this.props.currentEditUserId);
          this.props.refreshTable();
        }
      });
    });
  };

  render() {
    const {
      form: { getFieldDecorator }, detailData
    } = this.props;
    const token = getToken();
    const uploadButton = (
      <div>
        <Icon type={this.state.loading ? 'loading' : 'plus'} />
        <div className="ant-upload-text">Upload</div>
      </div>
    );
    return (
      <Modal
        title="编辑用户"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.currentEditUserId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="用户名">
            {getFieldDecorator('userName', {
              initialValue: detailData.userName,
              rules: [{ required: true, message: '请输入用户名' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="密码">
            {getFieldDecorator('password', {
              initialValue: detailData.password,
              rules: [{ required: true, message: '请输入密码' }],
            })(<Input type={'password'} placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="头像">
            <Upload
              name="file"
              listType="picture-card"
              className={styles.uploader}
              showUploadList={false}
              action={`${serverUrlPre}/system/file/upload/single?token=${token}`}
              beforeUpload={beforeUpload}
              onChange={this.handleChange}
            >
              {detailData.photo ? (
                <img
                  className={styles.uploader}
                  src={`${serverUrlPre}/system/file/download?filePath=${
                    !!this.state.photo?this.state.photo:detailData.photo
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
              initialValue: detailData.cellphoneNo,
              rules: [{ required: true, message: '请输入手机号' }],
            })(<Input placeholder="请输入" maxLength={11} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="邮箱">
            {getFieldDecorator('email', {
              initialValue: detailData.email,
            })(<Input placeholder="请输入" maxLength={50} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
