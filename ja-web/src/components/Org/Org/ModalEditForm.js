import React, { PureComponent, Fragment } from 'react';
import {
  Form,
  Input,
  Row,
  Col,
  message,
  Card,
  Button,
  Divider,
  Modal,
  DatePicker,
  Menu,
  Upload,
  Icon,
  Dropdown,
  Select,
  InputNumber,
} from 'antd';
import {
  delOrg,
  getById,
  editOrg,
  exportExcel,
  exportErrExcel,
  findOneOrgHistory,
} from '../../../services/org';
import ModalMoveOrMergeForm from './ModalMoveOrMergeForm';
import DescriptionList from '../../../components/DescriptionList';
import ModalAddForm from './ModalAddForm';
import moment from 'moment';
import styles from './BasicForm.less';
import { queryBaseInfoForParams } from '../../../services/staffBaseInfo';
import { serverUrlPre } from '../../../utils/request';
import { getToken, hasAccessKey } from '../../../utils/authority';
import { getByTypeCode } from '../../../services/systemCode';

const { Description } = DescriptionList;
const ButtonGroup = Button.Group;

class ModalEditForm extends PureComponent {
  state = {
    modalVisibleAdd: false,
    modalVisibleMoveOrMerge: false,
    moveOrMergeType: '',
    editing: false,
    isBase: true,
    orgDetail: {},
    orgHistoryDetail: {},
    form: {},
    establishDate: '',
    effectiveDate: '',
    erportParams: '',
    staffAllList: [],
    staffHistoryList: [],
    orgInfoTemplateUrl: 'orgInfoImport.zip',
  };

  componentDidMount() {
    this.initOrgInfo(1, this.props.editing);
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content && !!content.data) {
        content.data.map(
          d =>
            d.name === '在职' &&
            this.setState({
              jobStatus: d.rowId,
            }),
        );
      }
    });
  }

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (this.props.rowId != nextProps.rowId && nextProps.rowId > 0) {
      if (this.props.historyDate != '' && this.props.isShowing === false) {
        this.initOrgHistoryInfo(nextProps.rowId, this.props.historyDate);
      } else {
        this.initOrgInfo(nextProps.rowId, nextProps.editing);
      }
    }
  }

  /**
   * 初始化
   * @param rowId
   * @param editing
   */
  initOrgInfo(rowId, editing) {
    getById(rowId).then(data => {
      if (data.code === 200) {
        this.setState({
          orgDetail: data.data,
        });
        const {
          orgDetail: { isOrg },
        } = this.state;
        if (isOrg === -1) {
          this.setState({ isBase: true });
        } else if (isOrg === 0) {
          this.setState({ isBase: true });
        } else if (isOrg === 1) {
          this.setState({ isBase: false });
        }
        this.setState({ editing: editing });
      } else if (data.code === 400) {
        message.error('查询失败');
      }
      //查询部门下人员
      const jobStatus = this.state.jobStatus;
      const deptId = this.state.orgDetail.rowId;
      queryBaseInfoForParams({ jobStatus: jobStatus, deptId: deptId }).then(content => {
        if (!!content && !!content.data) {
          this.setState({
            staffAllList: content.data,
          });
        }
      });
    });
  }

  initOrgHistoryInfo(rowId, date) {
    findOneOrgHistory(rowId, date).then(data => {
      if (data.code === 200) {
        this.setState({
          orgHistoryDetail: data.data,
        });
        const {
          orgHistoryDetail: { isOrg },
        } = this.state;
        if (isOrg === -1) {
          this.setState({ isBase: true });
        } else if (isOrg === 0) {
          this.setState({ isBase: true });
        } else if (isOrg === 1) {
          this.setState({ isBase: false });
        }
        // this.setState({editing:editing});
      } else if (data.code === 400) {
        message.error('查询失败');
      }
    });
  }

  /**
   * 导出
   * @param flag
   * @param type
   * @param rowId
   */
  handleExport = () => {
    exportExcel();
  };

  /**
   * 显示/隐藏弹窗
   * @param flag
   */
  handleModalVisible = (flag, type, rowId) => {
    if (type === 0) {
      //添加
      this.setState({
        modalVisibleAdd: !!flag,
      });
    } else if (type === 1) {
      //编辑
      this.setState({
        editing: !!flag,
        currentEditOrgId: rowId,
      });
    } else if (type === 2) {
      //移动
      this.setState({
        modalVisibleMoveOrMerge: !!flag,
        currentEditOrgId: rowId,
        moveOrMergeType: 2,
      });
      this.handleRefreshMoveOrMergeTree();
    } else if (type === 3) {
      //合并
      this.setState({
        modalVisibleMoveOrMerge: !!flag,
        currentEditOrgId: rowId,
        moveOrMergeType: 3,
      });
      this.handleRefreshMoveOrMergeTree();
    }
  };

  /**
   * 删除组织架构
   * @param rowId
   */
  showDelOrgConfirm = rowId => {
    console.log('删除');
    console.log(rowId);
    const { handleRefreshTree } = this.props;
    if (rowId > 0) {
      Modal.confirm({
        title: '删除确认',
        content: '确定删除此组织架构吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          console.log(rowId);
          delOrg(rowId).then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              handleRefreshTree();
            } else if (data.code === 400) {
              message.error('删除失败');
            }
          });
        },
        onCancel() {
        },
      });
    }
  };

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.getFieldsValue();
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.rowId = this.props.rowId;
      if(!!this.props.rowId || this.props.rowId === 0 ){
        fieldsValue.rowId = 1;
      }

      fieldsValue.establishDate = this.state.establishDate;
      fieldsValue.effectiveDate = this.state.effectiveDate;
      fieldsValue.leaderNo = this.state.leaderNo;
      console.log('rowId===>>>>'+fieldsValue.rowId);
      //业务逻辑写在这里
      editOrg(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('编辑成功');
          this.setState({
            editing: false,
          });
          this.initOrgInfo(fieldsValue.rowId, false);
          this.props.handleRefreshTree();
        }
      });
    });
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeEstablishDate = (date, dateString) => {
    this.setState({
      establishDate: dateString,
    });
  };
  handleDatePickerChangeEffectiveDate = (date, dateString) => {
    this.setState({
      effectiveDate: dateString,
    });
  };

  refreshMoveOrMergeTreeData = ref => {
    this.child = ref;
  };

  handleRefreshMoveOrMergeTree = e => {
    this.child.initTreeData();
  };

  handleEditOrCancel = () => {
    const { editing } = this.state;
    this.setState({ editing: !editing });
  };

  handleChange = info => {
    const { handleRefreshMoveOrMergeTree } = this;
    const { handleRefreshTree } = this.props;
    if (!!info.file.response) {
      if (info.file.response.code === 200) {
        message.success('导入成功');
        handleRefreshTree();
        handleRefreshMoveOrMergeTree();
      } else if (info.file.response.code === 205) {
        message.error('有部分异常数据导出');
        handleRefreshTree();
        handleRefreshMoveOrMergeTree();
        exportErrExcel(info.file.response.redisKey);
      } else {
        message.error(info.file.response.msg);
      }
    }
  };

  /**
   * 选择负责人
   * @param staffName
   */
  handleChangeStaffName = (staffName, staffNo) => {
    const { orgDetail } = this.state;
    orgDetail.leaderName = staffName;

    this.setState({
      leaderNo: staffNo,
    });
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    const { isSelectOrg } = this.props;
    const { orgDetail } = this.state;
    const { orgHistoryDetail } = this.state;
    const { editing } = this.state;
    const { moveOrMergeType } = this.state;
    const { isBase } = this.state;
    const isOrg = orgDetail.isOrg;
    const rowId = orgDetail.rowId;
    const dateFormat = 'YYYY-MM-DD';

    const token = getToken();
    const menu = (
      <Menu>
        <Menu.Item>
          <Upload
            name="excel"
            action={`${serverUrlPre}/org/org/importExcel?token=${token}`}
            onChange={this.handleChange}
          >
            <Button>
              <Icon type="upload"/>导入组织架构
            </Button>
          </Upload>
        </Menu.Item>
      </Menu>
    );

    const action = (
      <Fragment>
        {this.props.isShowing ? (
          isSelectOrg ? (
            editing ? (
              <div>
                <Button type="danger" onClick={this.handleEditOrCancel}>
                  取消
                </Button>&nbsp;&nbsp;
                <Button type="primary" onClick={this.handleSure}>
                  保存
                </Button>
              </div>
            ) : (
              <div>
                {hasAccessKey(`org.org.add`) && (
                  <Button type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                    增加子机构
                  </Button>
                )}&nbsp;&nbsp;
                {isOrg === -1 ? (
                  ''
                ) : isOrg === 0 ? (
                  <ButtonGroup>
                    {/*<Button onClick={() => this.handleModalVisible(true, 2, rowId)}>移动</Button>*/}
                    {/*<Button onClick={() => this.handleModalVisible(true, 3, rowId)}>合并</Button>*/}
                    {rowId > 0
                      ? hasAccessKey(`org.org.delete`) && (
                      <Button onClick={() => this.showDelOrgConfirm(rowId)}>删除</Button>
                    )
                      : ''}
                  </ButtonGroup>
                ) : (
                  <ButtonGroup>
                    {hasAccessKey('org.org.moveAndMerge') && (
                      <Button onClick={() => this.handleModalVisible(true, 2, rowId)}>移动</Button>
                    )}
                    {hasAccessKey('org.org.moveAndMerge') && (
                      <Button onClick={() => this.handleModalVisible(true, 3, rowId)}>合并</Button>
                    )}
                    {rowId > 0
                      ? hasAccessKey(`org.org.delete`) && (
                      <Button onClick={() => this.showDelOrgConfirm(rowId)}>删除</Button>
                    )
                      : ''}
                  </ButtonGroup>
                )}&nbsp;&nbsp;
                {hasAccessKey('org.org.update') && (
                  <ButtonGroup>
                    <Button type="primary" onClick={this.handleEditOrCancel}>
                      编辑
                    </Button>
                  </ButtonGroup>
                )}&nbsp;&nbsp;
                {hasAccessKey('org.org.import') && (
                  <Dropdown overlay={menu} placement="bottomRight">
                    <Button>导入组织架构</Button>
                  </Dropdown>
                )}&nbsp;&nbsp;
                {hasAccessKey('org.org.export') && (
                  <Button onClick={() => this.handleExport()}>导出</Button>
                )}&nbsp;&nbsp;
                {hasAccessKey('org.org.downloadTemple') && (
                  <Button
                    href={`${serverUrlPre}/org/org/download?filePath=${
                      this.state.orgInfoTemplateUrl
                      }&token=${token}`}
                  >
                    下载导入模板
                  </Button>
                )}
                <ModalAddForm
                  modalVisibleAdd={this.state.modalVisibleAdd}
                  handleModalVisible={this.handleModalVisible.bind(this)}
                  isOrg={isOrg}
                  rowId={rowId}
                  staffAllList={this.state.staffAllList}
                  handleRefreshTree={this.props.handleRefreshTree.bind(this)}
                  handleRefreshMoveOrMergeTree={this.handleRefreshMoveOrMergeTree.bind(this)}
                />
                <ModalMoveOrMergeForm
                  modalVisibleMoveOrMerge={this.state.modalVisibleMoveOrMerge}
                  handleModalVisible={this.handleModalVisible.bind(this)}
                  orgDetail={orgDetail}
                  moveOrMergeType={moveOrMergeType}
                  handleRefreshTree={this.props.handleRefreshTree.bind(this)}
                  refreshMoveOrMergeTreeData={this.refreshMoveOrMergeTreeData.bind(this)}
                />
              </div>
            )
          ) : (
            ''
          )
        ) : (
          ''
        )}
      </Fragment>
    );

    return (
      <Card
        title={isBase ? orgDetail.baseName : orgDetail.deptName}
        style={{ marginBottom: 24 }}
        bordered={false}
        extra={action}
      >
        {editing ? (
          isBase ? (
            <div>
              <Form>
                <Row gutter={24}>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="基地名称">
                      {getFieldDecorator('baseOrDeptName', {
                        initialValue: orgDetail.baseOrDeptName,
                        rules: [{ required: true, message: '基地名称必填' }],
                      })(<Input placeholder="请输入" maxLength="50"/>)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="成立日期">
                      {getFieldDecorator('establishDate', {
                        initialValue: moment(orgDetail.establishDate),
                      })(
                        <DatePicker
                          disabled
                          onChange={this.handleDatePickerChangeEstablishDate}
                          format={dateFormat}
                          placeholder="请输入"
                        />,
                      )}
                    </Form.Item>
                  </Col>
                </Row>
              </Form>
              <Divider style={{ marginBottom: 32 }}/>
              <div className={styles.title}>总负责人</div>
              <Form>
                <Row gutter={24}>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工信息">
                      {getFieldDecorator('leaderNo', {
                        initialValue: orgDetail.leaderNo,
                        // rules: [{required: true, message: '员工信息'}],
                      })(
                        <Select
                          showSearch
                          style={{ width: 295 }}
                          placeholder="可按工号姓名组织架构筛选"
                        >
                          {this.state.staffAllList.map(d => (
                            <Select.Option
                              key={d.rowId}
                              value={d.rowId + d.staffNo + d.staffName}
                              onClick={() => this.handleChangeStaffName(d.staffName, d.staffNo)}
                            >
                              {d.staffNo +
                              '           ' +
                              d.staffName +
                              (d.transNames === undefined ||
                              d.transNames.deptId_baseOrDeptName === undefined
                                ? ''
                                : '           ' + d.transNames.deptId_baseOrDeptName)}
                            </Select.Option>
                          ))}
                        </Select>,
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="姓名">
                      {getFieldDecorator('leaderName', {
                        initialValue: orgDetail.leaderName,
                      })(<Input disabled/>)}
                    </Form.Item>
                  </Col>
                </Row>
              </Form>
            </div>
          ) : (
            <div>
              <Form>
                <Row gutter={24}>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门名称">
                      {getFieldDecorator('baseOrDeptName', {
                        initialValue: orgDetail.baseOrDeptName,
                        rules: [{ required: true, message: '部门名称必填' }],
                      })(<Input placeholder="请输入" maxLength="50"/>)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门编号">
                      {getFieldDecorator('baseOrDeptCode', {
                        initialValue: orgDetail.baseOrDeptCode,
                      })(<Input placeholder="请输入" disabled="true" maxLength="50"/>)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门简称">
                      {getFieldDecorator('baseOrDeptShortName', {
                        initialValue: orgDetail.baseOrDeptShortName,
                        rules: [{ required: true, message: '部门简称必填' }],
                      })(<Input placeholder="请输入" maxLength="50"/>)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="成立日期">
                      {getFieldDecorator('establishDate', {
                        initialValue: moment(orgDetail.establishDate),
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeEstablishDate}
                          format={dateFormat}
                          placeholder="请输入"
                        />,
                      )}
                    </Form.Item>
                  </Col>
                  {/*<Col span={12}>*/}
                  {/*<Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="生效日期">*/}
                  {/*{getFieldDecorator('effectiveDate',{*/}
                  {/*initialValue: moment(orgDetail.effectiveDate),*/}
                  {/*})(<DatePicker onChange={this.handleDatePickerChangeEffectiveDate} format={dateFormat} placeholder="请输入" />)}*/}
                  {/*</Form.Item>*/}
                  {/*</Col>*/}
                </Row>
              </Form>
              <Divider style={{ marginBottom: 32 }}/>
              <div className={styles.title}>总负责人</div>
              <Form>
                <Row gutter={24}>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工信息">
                      {getFieldDecorator('leaderNo', {
                        initialValue: orgDetail.leaderNo,
                        // rules: [{required: true, message: '员工信息'}],
                      })(
                        <Select
                          showSearch
                          style={{ width: 295 }}
                          placeholder="可按工号姓名组织架构筛选"
                        >
                          {this.state.staffAllList.map(d => (
                            <Select.Option
                              key={d.rowId}
                              value={d.rowId + d.staffNo + d.staffName}
                              onClick={() => this.handleChangeStaffName(d.staffName, d.staffNo)}
                            >
                              {d.staffNo +
                              '           ' +
                              d.staffName +
                              (d.transNames === undefined ||
                              d.transNames.deptId_baseOrDeptName === undefined
                                ? ''
                                : '           ' + d.transNames.deptId_baseOrDeptName)}
                            </Select.Option>
                          ))}
                        </Select>,
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="姓名">
                      {getFieldDecorator('leaderName', {
                        initialValue: orgDetail.leaderName,
                      })(<Input disabled/>)}
                    </Form.Item>
                  </Col>
                </Row>
              </Form>
              <Divider style={{ marginBottom: 32 }}/>
              <div className={styles.title}>组织架构信息</div>
              <Form>
                <Row gutter={24}>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="编制人数">
                      {getFieldDecorator('deptNum', {
                        initialValue: orgDetail.deptNum,
                      })(
                        <InputNumber
                          min={0}
                          max={999999}
                          placeholder="请输入"
                          maxLength="6"
                          precision="int"
                        />,
                      )}
                    </Form.Item>
                  </Col>
                  {/*<Col span={12}>*/}
                  {/*<Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否有效">*/}
                  {/*{getFieldDecorator('isValid',{*/}
                  {/*initialValue: orgDetail.isValid,*/}
                  {/*})(*/}
                  {/*<Select showSearch style={{width: 295}} placeholder="请选择..." >*/}
                  {/*<Select.Option key={0} value={0}>是</Select.Option>*/}
                  {/*<Select.Option key={1} value={1}>否</Select.Option>*/}
                  {/*</Select>*/}
                  {/*)}*/}
                  {/*</Form.Item>*/}
                  {/*</Col>*/}
                </Row>
              </Form>
            </div>
          )
        ) : this.props.isShowing ? (
          isBase ? (
            <DescriptionList size={`small`} col={2} style={{ marginTop: 50 }}>
              <Description term="基地名称" className={styles.info}>{orgDetail.baseOrDeptName}</Description>
              <Description term="成立日期" className={styles.info}>{orgDetail.establishDate}</Description>
              <Divider style={{ marginBottom: 10 }}/>
              <div className={styles.title}>汇报关系</div>
              <Description term="上级" className={styles.info}>{orgDetail.reportSuperior}</Description>
              <Divider style={{ marginBottom: 10 }}/>
              <div className={styles.title}>总负责人</div>
              <Description term="工号" className={styles.info}>{orgDetail.leaderNo}</Description>
              <Description term="姓名" className={styles.info}>{orgDetail.leaderName}</Description>
              <Divider style={{ marginBottom: 10 }}/>
              <div className={styles.title}>组织架构信息</div>
              <Description term="部门个数" className={styles.info}>{orgDetail.sonOrgNum}</Description>
              <Description term="在职总人数" className={styles.info}>{orgDetail.totalStaffNum}</Description>
            </DescriptionList>
          ) : (
            <DescriptionList size={`small`} col={2} style={{ marginBottom: 50 }}>
              <Description term="部门名称" className={styles.info}>{orgDetail.baseOrDeptName}</Description>
              <Description term="部门编号" className={styles.info}>{orgDetail.baseOrDeptCode}</Description>
              <Description term="部门简称" className={styles.info}>{orgDetail.baseOrDeptShortName}</Description>
              <Description term="成立日期" className={styles.info}>{orgDetail.establishDate}</Description>
              {/*<Description term="生效日期" className={styles.info}>{orgDetail.effectiveDate}</Description>*/}
              <Divider style={{ marginBottom: 10 }}/>
              <div className={styles.title}>汇报关系</div>
              <Description term="上级" className={styles.info}>{orgDetail.reportSuperior}</Description>
              <Divider style={{ marginBottom: 10 }}/>
              <div className={styles.title}>总负责人</div>
              <Description term="工号" className={styles.info}>{orgDetail.leaderNo}</Description>
              <Description term="姓名" className={styles.info}>{orgDetail.leaderName}</Description>
              <Divider style={{ marginBottom: 10 }}/>
              <div className={styles.title}>组织架构信息</div>
              <Description term="编制人数" className={styles.info}>{orgDetail.deptNum}</Description>
              <Description term="在编人数" className={styles.info}>{orgDetail.totalStaffNum}</Description>
              <Description term="缺编人数" className={styles.info}>{orgDetail.vacancyNum}</Description>
              <Description term="超编人数" className={styles.info}>{orgDetail.excessNum}</Description>
              {/*<Description term="是否有效" className={styles.info}>{orgDetail.isValid}</Description>*/}
            </DescriptionList>
          )
        ) : isBase ? (
          <DescriptionList size={`small`} col={2} style={{ marginTop: 50 }}>
            <Description term="基地名称" className={styles.info}>{orgHistoryDetail.baseOrDeptName}</Description>
            <Description term="成立日期" className={styles.info}>{orgHistoryDetail.establishDate}</Description>
            <Divider style={{ marginBottom: 10 }}/>
            <div className={styles.title}>汇报关系</div>
            <Description term="上级" className={styles.info}>{orgHistoryDetail.reportSuperior}</Description>
            <Divider style={{ marginBottom: 10 }}/>
            <div className={styles.title}>总负责人</div>
            <Description term="工号" className={styles.info}>{orgHistoryDetail.leaderNo}</Description>
            <Description term="姓名" className={styles.info}>{orgHistoryDetail.leaderName}</Description>
            <Divider style={{ marginBottom: 10 }}/>
            <div className={styles.title}>组织架构信息</div>
            <Description term="编制人数" className={styles.info}>{orgHistoryDetail.deptNum}</Description>
            <Description term="在编人数" className={styles.info}>{orgHistoryDetail.totalStaffNum}</Description>
            <Description term="缺编人数" className={styles.info}>{orgHistoryDetail.vacancyNum}</Description>
            <Description term="超编人数" className={styles.info}>{orgHistoryDetail.excessNum}</Description>
          </DescriptionList>
        ) : (
          <DescriptionList size={`small`} col={2} style={{ marginBottom: 50 }}>
            <Description term="部门名称" className={styles.info}>{orgHistoryDetail.baseOrDeptName}</Description>
            <Description term="部门编号" className={styles.info}>{orgHistoryDetail.baseOrDeptCode}</Description>
            <Description term="部门简称" className={styles.info}>{orgHistoryDetail.baseOrDeptShortName}</Description>
            <Description term="成立日期" className={styles.info}>{orgHistoryDetail.establishDate}</Description>
            {/*<Description term="生效日期" className={styles.info}>{orgHistoryDetail.effectiveDate}</Description>*/}
            <Divider style={{ marginBottom: 10 }}/>
            <div className={styles.title}>汇报关系</div>
            <Description term="上级" className={styles.info}>{orgHistoryDetail.reportSuperior}</Description>
            <Divider style={{ marginBottom: 10 }}/>
            <div className={styles.title}>总负责人</div>
            <Description term="工号" className={styles.info}>{orgHistoryDetail.leaderNo}</Description>
            <Description term="姓名" className={styles.info}>{orgHistoryDetail.leaderName}</Description>
            <Divider style={{ marginBottom: 10 }}/>
            <div className={styles.title}>组织架构信息</div>
            <Description term="编制人数" className={styles.info}>{orgHistoryDetail.deptNum}</Description>
            <Description term="在编人数" className={styles.info}>{orgHistoryDetail.totalStaffNum}</Description>
            <Description term="缺编人数" className={styles.info}>{orgHistoryDetail.vacancyNum}</Description>
            <Description term="超编人数" className={styles.info}>{orgHistoryDetail.excessNum}</Description>
            {/*<Description term="是否有效" className={styles.info}>{orgDetail.isValid===0? '是' : '否'}</Description>*/}
          </DescriptionList>
        )}
      </Card>
    );
  }
}

export default Form.create({})(ModalEditForm);
