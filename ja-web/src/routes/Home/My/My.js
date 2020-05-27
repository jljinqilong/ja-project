import React, { PureComponent } from 'react';
import { Card, Row, Col,Divider } from 'antd';

export default class My extends PureComponent {

  render() {
    return (
      <div>
        <Row gutter={16}>
          <Col span={6}>
            <Card>
              消息组件，敬请期待<br/>
              消息组件，敬请期待<br/>
              消息组件，敬请期待
            </Card>
          </Col>
          <Col span={6}>
            <Card>
              消息组件，敬请期待<br/>
              消息组件，敬请期待<br/>
              消息组件，敬请期待
            </Card>
          </Col>
          <Col span={6}>
            <Card>
              消息组件，敬请期待<br/>
              消息组件，敬请期待<br/>
              消息组件，敬请期待
            </Card>
          </Col>
          <Col span={6}>
            <Card>
              消息组件，敬请期待<br/>
              消息组件，敬请期待<br/>
              消息组件，敬请期待
            </Card>
          </Col>
        </Row>
        <Divider/>
        <Row gutter={16}>
          <Col span={6}>
            <Card>
              统计组件，敬请期待<br/>
              统计组件，敬请期待<br/>
              统计组件，敬请期待
            </Card>
          </Col>
          <Col span={6}>
            <Card>
              统计组件，敬请期待<br/>
              统计组件，敬请期待<br/>
              统计组件，敬请期待
            </Card>
          </Col>
          <Col span={6}>
            <Card>
              统计组件，敬请期待<br/>
              统计组件，敬请期待<br/>
              统计组件，敬请期待
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}
