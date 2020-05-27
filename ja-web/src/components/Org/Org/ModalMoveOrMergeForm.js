import React, { PureComponent, Fragement } from 'react';
import { Form, Modal, message, Tree } from 'antd';
import { orgMerge, orgMove, findOrgChartByDisabled, getOrgTreeData } from '../../../services/org';
import styles from './BasicForm.less';

class ModalMoveOrMergeForm extends PureComponent {
  state = {
    rowId: '',
    orgDetail: {},
    moveOrMergeType: '',
    treeData: [],
  };

  /**
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    this.setState({
      orgDetail: nextProps.orgDetail,
      rowId: nextProps.orgDetail.rowId,
      moveOrMergeType: nextProps.moveOrMergeType,
    });
  }

  componentWillUnmount(){
    this.initTreeData();
  }

  /**
   * 初始化
   */
  componentDidMount() {
    if (!!this.props.refreshMoveOrMergeTreeData) {
      this.props.refreshMoveOrMergeTreeData(this);
    }
  }

  /**
   * 初始化树形数据
   */
  initTreeData = () => {
    const { rowId } = this.state;
    if (!!rowId) {
      findOrgChartByDisabled(rowId).then(data => {
        if (!!data && !!data.data) {
          this.setState({
            treeData: data.data,
          });
        }
      });
    } else {
      getOrgTreeData().then(data => {
        if (!!data && !!data.data) {
          this.setState({
            treeData: data.data,
          });
        }
      });
    }
  };

  /**
   * 绘制树形
   * @param data
   * @returns {*}
   */
  renderTreeNodes = data => {
    if (!!data) {
      return data.map(item => {
        if (item.children) {
          return (
            <Tree.TreeNode
              title={item.title}
              key={item.key}
              dataRef={item}
              disabled={item.disabled}
            >
              {this.renderTreeNodes(item.children)}
            </Tree.TreeNode>
          );
        }
        return <Tree.TreeNode {...item} />;
      });
    }
  };

  handleClickTreeNodeMoveOrMerge = (selectedKeys, event) => {
    if (selectedKeys.length > 0) {
      this.setState({ newRowId: selectedKeys[0] });
    }
  };

  handleMoveOrMerge = (rowId, moveOrMergeType) => {
    const { newRowId } = this.state;
    if (rowId > 0 && newRowId > 0) {
      if (moveOrMergeType === 2) {
        const { orgDetail } = this.state;
        orgDetail.parentId = newRowId;
        orgMove(rowId, newRowId)
          .then(data => {
            if (data.code === 200) {
              message.success('移动成功');
              this.props.handleRefreshTree();
              this.props.handleModalVisible(false, 2, 0);
            } else if (data.code === 400) {
              message.error(data.data);
            }
          });
      } else if (moveOrMergeType === 3) {
        orgMerge(rowId, newRowId)
          .then(data => {
            if (data.code === 200) {
              message.success('合并成功');
              this.props.handleRefreshTree();
              this.props.handleModalVisible(false, 3, 0);
            } else if (data.code === 400) {
              message.error(data.data);
            }
          });
      }
    }
  };

  render() {
    const { rowId } = this.state;
    const { moveOrMergeType } = this.state;

    return (
      <Modal
        title={
          moveOrMergeType === 2
            ? '移动组织架构'
            : moveOrMergeType === 3
              ? '合并组织架构'
              : '组织架构操作'
        }
        visible={this.props.modalVisibleMoveOrMerge}
        onOk={() => this.handleMoveOrMerge(rowId, moveOrMergeType)}
        okText={`保存`}
        width={700}
        onCancel={() => this.props.handleModalVisible(false, 3, -1)}
      >
        <Tree
          defaultExpandAll
          onSelect={this.handleClickTreeNodeMoveOrMerge}
        >
          {this.renderTreeNodes(this.state.treeData)}
        </Tree>
      </Modal>
    );
  }
}

export default Form.create({})(ModalMoveOrMergeForm);
