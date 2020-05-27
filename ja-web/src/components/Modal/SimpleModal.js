import React from 'react';
import { Modal } from 'antd';

export default ({ children, ...props }) => (
  <Modal okText="保存" {...props}>
    {children}
  </Modal>
);
