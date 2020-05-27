import React from 'react';

export default ({
  expendAll = false,
  expandedRowRender,
  updateExpandRowKeys,
  initExpandedRowKeys,
}) => {
  return WrappedComponent => {
    return class extends React.PureComponent {
      constructor(props) {
        super(props);

        this.state = {
          expandedRowKeys: initExpandedRowKeys || [],
          expandRowByClick: false,
        };
      }

      componentDidMount() {
        if (initExpandedRowKeys) {
          this.onExpandedRowsChange(initExpandedRowKeys);
        }
      }

      onExpandedRowsChange = rows => {
        if (updateExpandRowKeys) updateExpandRowKeys(rows);
        this.setState({
          expandedRowKeys: rows,
        });
      };

      render() {
        const { expandedRowKeys } = this.state;
        // const expandRowByClick = true;
        return (
          <WrappedComponent
            expandedRowRender={expandedRowRender}
            defaultExpandAllRows={expendAll}
            expandedRowKeys={expandedRowKeys}
            onExpandedRowsChange={this.onExpandedRowsChange}
          />
        );
      }
    };
  };
};

// TODO 下种写法为什么不行
// export default ({expendColumns, expendDataIndex, expendAll=false}) => {
//   return (Comp) => {
//     const expandedRowRender = (record) => {
//       const dataSource = record[expendDataIndex];
//       return (
//         <Table
//           columns={expendColumns}
//           dataSource={dataSource}
//           pagination={false}
//         />
//       );
//     };
//
//     return (
//       <Comp
//         rowRender={expandedRowRender}
//         expandAllRows={expendAll}
//       />
//     )
//   }
// }
