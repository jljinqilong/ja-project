import React, { PureComponent, Fragment } from 'react';
import { message, Card, Table } from 'antd';
import DescriptionList from '../../../components/DescriptionList';
import styles from './InternalRecord.less';
import moment from 'moment';
import { getById } from '../../../services/staffBaseInfo';
import { getContractByStaffId } from '../../../services/staffContract';
import { getAgreementByStaffId } from '../../../services/agreement';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';
import { queryAdjustmentWorkByChangeType } from '../../../services/adjustWork';

const { Description } = DescriptionList;
export default class InternalRecord extends PureComponent {
  state = {
    staffId: this.props.staffId,
    searchFormValues: {
      pageNum: 1,
      staffId: this.props.staffId,
    changeType: 'INNER_MOBILIZATION',
    }
  };

  /**
   * 初始化
   */
  componentDidMount() {
    if (!!this.state.staffId) {
      getById(this.state.staffId).then(data => {
        if (!!data.data) {
          this.setState({
            staffNo: data.data.staffNo,
            staffName: data.data.staffName,
            staffType_name: data.data.transNames.staffType_name,
            baseId_baseOrDeptName: data.data.transNames.baseId_baseOrDeptName,
            deptId_baseOrDeptName: data.data.transNames.deptId_baseOrDeptName,
            positionName: data.data.transNames.positionId_positionName,
            rankName: data.data.transNames.rankId_rankName,
            gradeName: data.data.transNames.gradeId_gradeName,
            entryDate: data.data.entryDate,
          });
        }
      });

      //查询异动记录
      // allAdjustWork(this.state.staffId).then(data => {
      //   if (!!data.data) {
      //     this.setState({
      //       adjustWorkList: data.data,
      //     });
      //   }
      // });

      getContractByStaffId(this.state.staffId).then(data => {
        if (!!data.data) {
          this.setState({
            contractList: data.data,
          });
        }
      });

      getAgreementByStaffId(this.state.staffId).then(data => {
        if (!!data.data) {
          this.setState({
            agreementList: data.data,
          });
        }
      });

      queryAdjustmentWorkByChangeType(this.state.searchFormValues)
        .then(response => {
          if (response.data !== undefined) {
            this.setState({
              list: response.data.list,
            });
          }
        })
    }
  }

  render() {
    const token = getToken();
    const columns = [
      {
        title: '变动类型',
        // dataIndex: 'changeType',
        render: (text, record) => <Fragment>{record.changeType === 0 ? '离职' : ''}</Fragment>,
      },
      {
        title: '变动原因',
        dataIndex: 'changeReason',
      },
      {
        title: '变动日期',
        dataIndex: 'changeDate',
      },
      {
        title: '新公司',
        dataIndex: 'newCompany',
      },
      {
        title: '原公司',
        dataIndex: 'originalCompany',
      },
      {
        title: '新部门',
        dataIndex: 'newDept',
      },
      {
        title: '原部门',
        dataIndex: 'originalDapt',
      },
      {
        title: '新职衔',
        dataIndex: 'newPosition',
      },
      {
        title: '原职衔',
        dataIndex: 'originalPosition',
      },
      {
        title: '新职级',
        dataIndex: 'newDuty',
      },
      {
        title: '原职级',
        dataIndex: 'originalDuty',
      },
      {
        title: '新人员类别',
        dataIndex: 'newStaffType',
      },
      {
        title: '原人员类别',
        dataIndex: 'originalStaffType',
      },
    ];
    const columns1 = [
      {
        title: '合同编号',
        dataIndex: 'contractNo',
      },
      {
        title: '甲方',
        dataIndex: 'owner',
      },
      {
        title: '合同类型',
        dataIndex: 'transNames.contractType_name',
      },
      {
        title: '合同状态',
        dataIndex: 'transNames.contractState_name',
      },
      {
        title: '期限类型',
        dataIndex: 'transNames.contractPeriodType_name',
      },
      {
        title: '查看附件',
        render: (text, record) => {
          return (
            record.file !== undefined &&
            record.file !== '' && (
              <a
                href={
                  `${serverUrlPre}/system/file/download?filePath=` + record.file + `&token=${token}`
                }
                title={'附件下载'}
                alt="file"
              >
                附件下载
              </a>
            )
          );
        },
      },
      {
        title: '生效日期',
        dataIndex: 'contractDateStart',
      },
      {
        title: '终止日期',
        dataIndex: 'contractDateEnd',
      },
    ];
    const columns2 = [
      {
        title: '协议编号',
        dataIndex: 'agreementNo',
      },
      {
        title: '协议类型',
        dataIndex: 'transNames.agreementType_name',
      },
      {
        title: '生效日期',
        dataIndex: 'agreementDateStart',
      },
      {
        title: '终止日期',
        dataIndex: 'agreementDateEnd',
      },
      {
        title: '协议状态',
        dataIndex: 'transNames.agreementState_name',
      },
      {
        title: '签订单位',
        dataIndex: 'owner',
      },
    ];

    const columns3 = [
      {
        title: '基地',
        dataIndex: 'newBase',
      },
      {
        title: '部门',
        dataIndex: 'newDept',
      },
      {
        title: '职衔',
        dataIndex: 'newPosition',
      },
      {
        title: '职级',
        dataIndex: 'newRank',
      },
      {
        title: '职等/赋值名称',
        dataIndex: 'newGrade',
      },
      {
        title: '开始时间',
        dataIndex: 'changeDate',
      },
      {
        title: '结束时间',
        dataIndex: 'realEndTime',
      },
    ];
    // const data = this.state.adjustWorkList;
    const data1 = this.state.contractList;
    const data2 = this.state.agreementList;
    const data3 = this.state.list;
    return (
      <div>
        <Card title={'当前任职信息'} bordered={false}>
          <DescriptionList size="large" style={{ marginBottom: 32 }}>
            <Description term="员工类型">{this.state.staffType_name}</Description>
            <Description term="基地">{this.state.baseId_baseOrDeptName}</Description>
            <Description term="部门">{this.state.deptId_baseOrDeptName}</Description>
            <Description term="职衔">{this.state.positionName}</Description>
            <Description term="职级">{this.state.rankName}</Description>
            <Description term="职等/赋值名称">{this.state.gradeName}</Description>
            <Description term="任职日期">{this.state.entryDate}</Description>
          </DescriptionList>
        </Card>
        {/*<Card title={'内部工作变动'} bordered={false}>*/}
        {/*<Table columns={columns} dataSource={data} size="small" bordered pagination={false}/>*/}
        {/*</Card>*/}
        <Card title={'合同信息'} bordered={false}>
          <Table columns={columns1} dataSource={data1} size="small" bordered pagination={false} />
        </Card>
        <Card title={'协议信息'} bordered={false}>
          <Table columns={columns2} dataSource={data2} size="small" bordered pagination={false} />
        </Card>
        <Card title={'内部调动'} bordered={false}>
          <Table columns={columns3} dataSource={data3} size="small" bordered pagination={false} />
        </Card>
      </div>
    );
  }
}
