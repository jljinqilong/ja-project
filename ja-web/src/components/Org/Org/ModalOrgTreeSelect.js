import React, { Fragment }  from 'react';
import { TreeSelect } from 'antd';

export default class ModalOrgTreeSelect extends React.Component {
  state = {
    treeData: [],
    value: '',
    baseId: '',
  };

  /**
   * 初始化
   */
  componentDidMount() {
    if (!!this.props.onRef) {
      this.props.onRef(this);
    }
    this.setState({
      value: String(this.props.deptId),
    });
  }

  /**
   * 绘制树形
   * @param data
   * @returns {*}
   */
  renderTreeNodes = data => {
    return data.map(item => {
      if (item.children) {
        if (item.isOrg != 1) {
          return {
            label: item.title,
            key: item.key,
            value: String(item.key),
            children: this.renderTreeNodes(item.children),
          };
        } else {
          return {
            label: item.title,
            key: item.key,
            value: String(item.key),
            children: this.renderTreeNodes(item.children),
          };
        }
      }
      if (item.isOrg != 1) {
        return {
          label: item.title,
          key: item.key,
          value: String(item.key),
        };
      } else {
        return {
          label: item.title,
          key: item.key,
          value: String(item.key),
        };
      }
    });
  };

  onChange = value => {
    this.setState({ value });
    console.log('son deptId===>>' + value);
    if (!!this.props.handleSetOrg) {
      this.props.handleSetOrg(value);
    }
  };

  render() {
    const treeData = this.renderTreeNodes(this.props.treeData);
    const { disabled } = this.props;
    const { value } = this.state;
    return (
      <Fragment>
      {!!disabled ?
        <TreeSelect
            disabled
            showSearch
            allowClear={false}
            treeDefaultExpandAll
            treeData={treeData}
            value={value}
            onChange={this.onChange}
            searchPlaceholder="请选择..."
            style={{ width: 300 }}
            dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
          />
      :
        <TreeSelect
          showSearch
          allowClear={false}
          treeDefaultExpandAll
          treeData={treeData}
          value={value}
          onChange={this.onChange}
          searchPlaceholder="请选择..."
          style={{ width: 300 }}
          dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
        />}
      </Fragment>
    );
  }
}
