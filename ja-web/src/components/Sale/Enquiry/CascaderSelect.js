import { Cascader } from 'antd';
import React from 'react';

export default class CascaderSelect extends React.Component {
  onChange = value => {
    const { getCascaderSelectValue } = this.props;
    getCascaderSelectValue(value);
  };

  loadData = selectedOptions => {
    const targetOption = selectedOptions[selectedOptions.length - 1];
    targetOption.loading = true;

    // console.log(selectedOptions.length)
    const { onLoad } = this.props;
    if (onLoad) onLoad(targetOption);
  };

  render() {
    const { countryCascaderList, initialValue } = this.props;

    return (
      <Cascader
        options={countryCascaderList}
        loadData={this.loadData}
        onChange={this.onChange}
        filedNames={{ label: 'name', value: 'rowId' }}
        changeOnSelect
        placeholder="请选择"
        value={initialValue}
      />
    );
  }
}
