import React, { PureComponent, Fragement } from 'react';
import { Transfer, Modal } from 'antd';

export default class ModalTransferForm extends PureComponent {
  state = {
    mockData: [],
    targetKeys: [],
  };

  componentDidMount() {
    this.getMock();
    console.log('shuishui')
    console.log(this.props.isViewing);
  }

  getMock = () => {
    const targetKeys = [];
    const mockData = [];
    for (let i = 0; i < 20; i++) {
      const data = {
        key: i.toString(),
        title: `content${i + 1}`,
        description: `description of content${i + 1}`,
        chosen: Math.random() * 2 > 1,
      };
      if (data.chosen) {
        targetKeys.push(data.key);
      }
      mockData.push(data);
    }
    this.setState({ mockData, targetKeys });
  };

  filterOption = (inputValue, option) => {
    return option.description.indexOf(inputValue) > -1;
  };

  handleChange = targetKeys => {
    this.setState({ targetKeys });
  };

  render() {
    return (
      <Modal
        title="添加公积金"
        visible={this.props.modalVisibleAddOne}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisibleOne(false, 3, -1)}
      >
        <Transfer
          dataSource={this.state.mockData}
          showSearch
          filterOption={this.filterOption}
          targetKeys={this.state.targetKeys}
          onChange={this.handleChange}
          render={item => item.title}
        />
      </Modal>
    );
  }
}
