import React, { PureComponent } from 'react';
import { Form, Button } from 'antd';
import styles from './SimplePage.less';

export default class SimpleSearchForm extends PureComponent {
  render() {
    const { onSubmit, children, onReset } = this.props;

    return (
      <div className={styles.form}>
        <Form onSubmit={onSubmit} layout="inline">
          {children}
          <div style={{ overflow: 'hidden' }}>
            <span style={{ float: 'right', marginBottom: 24 }}>
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button style={{ marginLeft: 8 }} onClick={onReset}>
                重置
              </Button>
            </span>
          </div>
        </Form>
      </div>
    );
  }
}
