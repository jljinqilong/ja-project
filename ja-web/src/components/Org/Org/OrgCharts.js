import React, { PureComponent, Fragment } from 'react';
import { Card, Row, Col } from 'antd';
import OrgChart from 'react-orgchart';
import 'react-orgchart/index.css';
import html2canvas from 'html2canvas';
import $ from 'jquery';
import { hierarchyChart, hierarchyHistoryChart } from '../../../services/org';
import styles from './OrgCharts.less';

require('./OrgCharts.less');

class OrgCharts extends PureComponent {
  state = {
    chartData: [],
    historyChartData: [],
    historyDate: '',
    isShowing: true,
  };

  getImage = () => {
    let that = this;
    let divDom = $('.reactOrgChart')[0];
    html2canvas(divDom).then(function(canvas) {
      console.log(canvas.toDataURL('image/png'));
      that.downloadFile('test.png', canvas.toDataURL('image/png'));
    });
  };

  //下载
  downloadFile(fileName, content) {
    let aLink = document.createElement('a');
    let blob = this.base64ToBlob(content); //new Blob([content]);

    let evt = document.createEvent('HTMLEvents');
    evt.initEvent('click', true, true); //initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
    aLink.download = fileName;
    aLink.href = URL.createObjectURL(blob);

    // aLink.dispatchEvent(evt);
    aLink.click();
  }
  //base64转blob
  base64ToBlob(code) {
    let parts = code.split(';base64,');
    let contentType = parts[0].split(':')[1];
    let raw = window.atob(parts[1]);
    let rawLength = raw.length;

    let uInt8Array = new Uint8Array(rawLength);

    for (let i = 0; i < rawLength; ++i) {
      uInt8Array[i] = raw.charCodeAt(i);
    }
    return new Blob([uInt8Array], { type: contentType });
  }

  componentDidMount() {
    if (this.props.isShowing) {
      hierarchyChart()
        .then(data => {
          console.dir(data.data[0]);
          this.setState({
            chartData: data.data[0],
          });
        });
    } else if (
      this.props.historyDate !== '' ||
      (this.props.historyDate !== null && !this.props.isShowing)
    ) {
      hierarchyHistoryChart(this.props.historyDate)
        .then(data => {
          this.setState({
            historyChartData: data.data[0],
          });
        });
    }
  }

  render() {

    const MyNodeComponent = ({ node }) => {
      let keys = '';
      if (node.level <= 3) {
        keys = `initechNode${node.level}`;
      } else {
        keys = 'initechNode3';
      }
      return (
        <div
          className={`${styles[keys]}`}>
          <div className={styles.con}>
            <div>
              <span className={styles.textCon}>{node.name}</span>
              <span className={styles.textCon}>
                {'(' + (node.totalStaffNum === undefined ? '' : node.totalStaffNum) + '/'}
                {(node.deptNum === undefined ? '' : node.deptNum) + ')'}
              </span>
            </div>
            <div>
              <span className={styles.textCon}>{node.leaderNo}</span>&nbsp;&nbsp;
              <span className={styles.textCon}>{node.leaderName}</span>
            </div>
          </div>
        </div>
      );
    };
    return (
      <Fragment>
        <Row>
          <Col span={24} justify="end">
            <span
              style={{
                float: 'right',
                display: 'inlineBlock',
                padding: '8px 16px',
                background: '#3C8CBB',
                cursor: 'pointer',
                color: '#dbe5ff',
                borderRadius: '4px',
                marginBottom: '20px',
              }}
              onClick={() => this.getImage()}
            >
              导出组织图
            </span>
          </Col>
        </Row>
        <Card className={styles.chartCon}>
          <OrgChart
            tree={
              this.props.isShowing
                ? this.state.chartData
                : this.state.historyChartData === undefined
                  ? ''
                  : this.state.historyChartData
            }
            NodeComponent={MyNodeComponent}
          />
        </Card>
      </Fragment>
    );
  }
}
export default OrgCharts;
