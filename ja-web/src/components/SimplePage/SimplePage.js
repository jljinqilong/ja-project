import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Divider, Modal, message } from 'antd';
import SimpleSearchForm from './SimpleSearchForm';
import SimpleToolBar from './SimpleToolBar';
import StandardTable from '../StandardTable';
import withExtendRow from '../AdvancedTable/withExtendRow';

import styles from './SimplePage.less';
import { hasAccessKey } from '../../utils/authority';

export default class SimplePage extends PureComponent {
  state = {
    pageNum: 1,
    pageSize: 10,
    formValues: {},
    selectedRows: [],
  };

  componentDidMount() {
    this.refreshTable();
  }

  // ============ 查询 ===============
  handleSearch = e => {
    e.preventDefault();

    const { form } = this.props;

    form.validateFields(async (err, fieldsValue) => {
      if (err) return;

      const values = {
        ...fieldsValue,
      };

      await this.setState({
        formValues: values,
      });

      this.refreshTable(true);
    });
  };

  handleFormReset = async () => {
    const { form, onReset } = this.props;
    form.resetFields();
    await this.setState({
      pageNum: 1,
      pageSize: 10,
      formValues: {},
    });
    if (onReset) onReset();
    this.refreshTable();
  };

  handleStandardTableChange = async pagination => {
    await this.setState({
      pageNum: pagination.current,
      pageSize: pagination.pageSize,
    });

    this.refreshTable();
  };

  refreshTable = (firstPage = false) => {
    const { onSearch } = this.props;
    const { pageNum, pageSize, formValues } = this.state;

    const params = {
      pageNum: firstPage ? 1 : pageNum,
      pageSize,
      ...formValues,
    };

    if (onSearch) onSearch(params);
  };

  handleSelectRows = rows => {
    this.setState({
      selectedRows: rows,
    });
  };

  // ============ 编辑 ===============
  handleEdit = rowId => {
    const { onEdit } = this.props;

    if (onEdit) {
      onEdit(rowId);
    }
  };

  // ============ 详情 ===============
  handleView = rowId => {
    const { onView } = this.props;

    if (onView) {
      onView(rowId);
    }
  };

  // ============ 删除 ===============
  handleDelete = rowId => {
    const { onDelete } = this.props;

    // TODO 每页10条数据，共21条数据，如果在第三页删除最后一条数据后，刷新第三页会发现没有任何数据，此时只有20条数据共2页，前端需要做计算，暂时强制回到第一页
    const refresh = () => this.refreshTable(true);

    Modal.confirm({
      title: '删除确认',
      content: '确定删除此记录吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const success = () => {
          message.success('删除成功');
          refresh();
        };

        const fail = () => {
          message.error('删除失败');
        };

        if (onDelete)
          onDelete({
            payload: { rowId },
            successCallback: success,
            failCallback: fail,
          });
      },
      onCancel() {},
    });
  };

  // ============ 权限检查 ===============
  hasPrivilege = key => {
    if (!key) return false;
    return hasAccessKey(key);
  };

  // ============ 计算操作列 ===============
  renderOpColumn = () => {
    const {
      opWidth = 120,
      opFixed,
      onView,
      onEdit,
      onDelete,
      opAction,
      opAccessCheckFunc,
      accessKeys,
    } = this.props;
    const { deleteKey = null, editKey = null, viewKey = null } = accessKeys || {
      deleteKey: null,
      editKey: null,
      viewKey: null,
    };

    if (
      this.hasPrivilege(viewKey) ||
      this.hasPrivilege(editKey) ||
      this.hasPrivilege(deleteKey) ||
      (opAccessCheckFunc && opAccessCheckFunc())
    ) {
      return {
        title: '操作',
        width: opWidth,
        ...opFixed,
        render: (text, record) => (
          <Fragment>
            {onView && this.hasPrivilege(viewKey) ? (
              <Fragment>
                <a title="详情" onClick={() => this.handleView(record.rowId)}>
                  <Icon type="file-text" />
                </a>
              </Fragment>
            ) : null}
            {onEdit && this.hasPrivilege(editKey) ? (
              <Fragment>
                {onView ? <Divider type="vertical" /> : null}
                <a title="编辑" onClick={() => this.handleEdit(record.rowId)}>
                  <Icon type="edit" />
                </a>
              </Fragment>
            ) : null}
            {onDelete && this.hasPrivilege(deleteKey) ? (
              <Fragment>
                {onEdit ? <Divider type="vertical" /> : null}
                <a title="删除" onClick={() => this.handleDelete(record.rowId)}>
                  <Icon type="delete" />
                </a>
              </Fragment>
            ) : null}
            {typeof opAction === 'function' ? opAction(record) : opAction}
          </Fragment>
        ),
      };
    }
  };

  render() {
    const { selectedRows } = this.state;
    const {
      loading = false,
      rowKey,
      data,
      scroll,
      onSearch,
      renderSearchForm,
      onExtend,
      op = true,
      toolbar = true,
      tblProps,
      children,
    } = this.props;
    let { columns } = this.props;

    if (columns && Array.isArray(columns) && op) {
      columns = [...columns, this.renderOpColumn()]
        .filter(column => !!column)
        .map(column => column);
    }

    const DecoratedTable = props => (
      <StandardTable
        rowKey={rowKey || 'rowId'}
        selectedRows={selectedRows}
        loading={loading}
        columns={columns}
        data={data}
        onSelectRow={this.handleSelectRows}
        onChange={this.handleStandardTableChange}
        scroll={scroll}
        expanded={props}
        tblProps={tblProps}
      />
    );

    const SimpleTable = onExtend ? withExtendRow(onExtend())(DecoratedTable) : DecoratedTable;

    return (
      <Card bordered={false}>
        <div className={styles.simplePage}>
          {onSearch && renderSearchForm ? (
            <SimpleSearchForm onSubmit={this.handleSearch} onReset={this.handleFormReset}>
              {renderSearchForm()}
            </SimpleSearchForm>
          ) : null}
          {toolbar ? (
            <SimpleToolBar
              {...this.props}
              onRefresh={this.refreshTable}
              hasPrivilege={this.hasPrivilege}
            />
          ) : null}
          <SimpleTable />
        </div>
        {children}
      </Card>
    );
  }
}
