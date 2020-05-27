import { Modal } from 'antd';
import React from 'react';
import ViewInquiriesHistory from './ViewInquiriesHistory';

export default class ModelView extends React.Component {
  handleCancel = () => {
    const { handleCancle } = this.props;
    if (handleCancle) handleCancle(false);
  };

  render() {
    const { modalVisible, inquriesId } = this.props;

    return (
      <div>
        <Modal title="询单历史记录" visible={modalVisible} onCancel={this.handleCancel} width="70%">
          <ViewInquiriesHistory inquiriesId={inquriesId} key={inquriesId} />
        </Modal>
      </div>
    );
  }
}
