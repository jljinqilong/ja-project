import React, { Component } from 'react';

// 引入 ECharts 主模块
import echarts from 'echarts/lib/echarts';
// 引入柱状图
import  'echarts/lib/chart/tree';
// 引入提示框和标题组件
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

class EchartsTest extends Component {
  componentDidMount() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 绘制图表
    myChart.setOption({
      title : {
        text: '树图',
        subtext: '虚构数据'
      },
      toolbox: {
        show : true,
        feature : {
          mark : {show: true},
          dataView : {show: true, readOnly: false},
          restore : {show: true},
          saveAsImage : {show: true}
        }
      },
      calculable : false,

      series : [
        {
          name:'树图',
          type:'tree',
          orient: 'vertical',  // vertical horizontal
          rootLocation: {x: 'center',y: 50}, // 根节点位置  {x: 100, y: 'center'}
          nodePadding: 1,
          itemStyle: {
            normal: {
              label: {
                show: false,
                formatter: "{b}"
              },
              lineStyle: {
                color: '#48b',
                shadowColor: '#000',
                shadowBlur: 3,
                shadowOffsetX: 3,
                shadowOffsetY: 5,
                type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
              }
            },
            emphasis: {
              label: {
                show: true,
                position: 'inside',
              }
            }
          },
          label:{
            show:true
          },

          data: [
            {
              name: '根节点',
              value: 6,
              children: [
                {
                  name: '节点1',
                  value: 4,
                  children: [
                    {
                      name: '叶子节点1',
                      value: 4
                    },
                    {
                      name: '叶子节点2',
                      value: 4
                    },
                    {
                      name: '叶子节点3',
                      value: 2
                    },
                    {
                      name: '叶子节点4',
                      value: 2
                    },
                    {
                      name: '叶子节点5',
                      value: 2
                    },
                    {
                      name: '叶子节点6',
                      value: 4
                    }
                  ]
                },
                {
                  name: '节点2',
                  value: 4,
                  children: [{
                    name: '叶子节点7',
                    value: 4
                  },
                    {
                      name: '叶子节点8',
                      value: 4
                    }]
                },
                {
                  name: '节点3',
                  value: 1,
                  children: [
                    {
                      name: '叶子节点9',
                      value: 4
                    },
                    {
                      name: '叶子节点10',
                      value: 4
                    },
                    {
                      name: '叶子节点11',
                      value: 2
                    },
                    {
                      name: '叶子节点12',
                      value: 2
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    });
  }
  render() {
    return (
      <div id="main" style={{ width: 400, height: 400 }}></div>
    );
  }
}

export default EchartsTest;
