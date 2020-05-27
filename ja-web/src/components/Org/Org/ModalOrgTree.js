import React, { PureComponent } from 'react';
import { Tree, Spin } from 'antd';
import { getOrgTreeData, } from '../../../services/org';
export default class ModalOrgTree extends PureComponent {
  state = {
    isLoading: true,
    treeData: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    if (!!this.props.refreshTreeData) {
      this.props.refreshTreeData(this);
    }
    this.initTreeData();
  }

  /**
   * 初始化树形数据
   */
  initTreeData = () => {
    getOrgTreeData().then(data => {
      if (!!data && !!data.data) {
        this.setState({
          treeData: data.data,
          isLoading: false,
        });
        if(!!this.props.refreshTable){
          this.props.refreshTable();
        }
      }
    });
  };

  /**
   * 绘制树形
   * @param data
   * @returns {*}
   */
  renderTreeNodes = data => {
    return data.map(item => {
      if (item.children) {
        return (
          <Tree.TreeNode
            title={item.title + '(' + item.totalStaffNum + '/' + item.deptNum + ')'}
            key={item.key}
            dataRef={item}
          >
            {this.renderTreeNodes(item.children)}
          </Tree.TreeNode>
        );
      }
      return (
        <Tree.TreeNode
          title={item.title + '(' + item.totalStaffNum + '/' + item.deptNum + ')'}
          key={item.key}
        />
      );
    });
  };

  render() {
    const { isLoading } = this.state;
    return (
      <div>
        {isLoading ? (
          <Spin />
        ) : (
          <Tree
            // defaultExpandAll
            defaultExpandedKeys={['1']}
            defaultSelectedKeys={['1']}
            onSelect={this.props.handleClickTreeNode}
          >
            {this.renderTreeNodes(
              this.props.isShowing === undefined
                ? this.state.treeData
                : this.props.isShowing
                  ? this.state.treeData
                  : this.props.treeHistoryData
            )}
          </Tree>
        )}
      </div>
    );
  }
}
