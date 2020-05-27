import React, { PureComponent, Fragment } from 'react';
import { Button, Upload, Icon, message } from 'antd';
import styles from './SimplePage.less';

export default class SimpleToolBar extends PureComponent {
  handleChange = info => {
    const { onRefresh } = this.props;
    if (info.file.response) {
      if (info.file.response.code === 200) {
        message.success('导入成功');
        onRefresh(true);
      } else if (info.file.response.code === 400) {
        message.error(info.file.response.data);
      } else {
        message.error(info.file.response.msg);
      }
    }
  };

  render() {
    const {
      add,
      onAdd,
      imp,
      impTplUrl,
      impUrl,
      exp,
      expUrl,
      accessKeys,
      hasPrivilege,
      toolbarAction,
    } = this.props;

    const { addKey = null, impKey = null, expKey = null } = accessKeys || {
      addKey: null,
      impKey: null,
      expKey: null,
    };

    return (
      <div className={styles.operator}>
        {add && hasPrivilege(addKey) ? (
          <Button icon="plus" type="primary" onClick={onAdd}>
            新建
          </Button>
        ) : null}
        {imp && hasPrivilege(impKey) ? (
          <Fragment>
            {impTplUrl ? (
              <Button icon="download" href={impTplUrl} style={{ marginRight: '16px' }}>
                下载导入模板
              </Button>
            ) : null}
            <Upload
              name="excel"
              action={impUrl}
              onChange={this.handleChange}
              showUploadList={false}
            >
              <Button>
                <Icon type="upload" /> 导入
              </Button>
            </Upload>
          </Fragment>
        ) : null}
        {exp && hasPrivilege(expKey) ? (
          <Button href={expUrl}>
            <Icon type="download" /> 导出
          </Button>
        ) : null}
        {typeof toolbarAction === 'function' ? toolbarAction() : toolbarAction}
      </div>
    );
  }
}
