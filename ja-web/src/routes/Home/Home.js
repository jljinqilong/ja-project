import React, { Component } from 'react';
import { Card, Row, Col, Divider } from 'antd';
import $ from 'jquery';
import timg from '../../assets/timg.png';
// eslint-disable-next-line no-unused-vars
import styles from './Home.less';
import logo from '../../assets/logo_login.png';

export default class Home extends Component {
  /**
   * 初始化
   */
  componentDidMount() {
    $('.box').mousemove(function(e) {
      var offset = $(this).offset(),
        constante = 10,
        x = e.pageX - offset.left,
        y = e.pageY - offset.top,
        rx = (($(this).height() / 2 - y) / ($(this).height() / 2)) * constante,
        ry = ((-1 * ($(this).width() / 2 - x)) / ($(this).width() / 2)) * constante;

      $(this).css({
        transform: 'rotateX(' + rx + 'deg)' + ' ' + 'rotateY(' + ry + 'deg)',
      });
    });
    $('.box').mouseleave(function(e) {
      $(this).css({
        transform: 'rotateX(' + 0 + 'deg)' + ' ' + 'rotateY(' + 0 + 'deg)',
      });
    });
  }

  render() {
    return (
      <div>
        <Row gutter={16}>
          <Col span={9}>
            <Card>
              <div className="centered">
                <div className="container">
                  <div className="box">
                    <img src={timg} />
                  </div>
                </div>
              </div>
            </Card>
          </Col>
          <Col span={10}>
            <Card>
              <img src={timg} />
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}
