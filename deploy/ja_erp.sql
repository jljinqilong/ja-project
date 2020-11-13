/*
 Navicat Premium Data Transfer

 Source Server         : jinlong-huawei
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 121.36.102.155:3306
 Source Schema         : ja_erp

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 06/06/2020 17:20:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for atd_attendance_record
-- ----------------------------
DROP TABLE IF EXISTS `atd_attendance_record`;
CREATE TABLE `atd_attendance_record` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `staff_no` varchar(100) DEFAULT NULL COMMENT '工号',
  `staff_name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `base_id` bigint(10) DEFAULT NULL COMMENT '基地ID（org_id）',
  `base_name` varchar(100) DEFAULT NULL COMMENT '基地名称',
  `dept_id` bigint(10) DEFAULT NULL COMMENT '部门ID（org_id）',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `job_num_id` bigint(10) DEFAULT NULL COMMENT '班次ID（atd_job_number）',
  `job_num_name` varchar(100) DEFAULT NULL COMMENT '班次名称',
  `operating_post_id` bigint(20) DEFAULT NULL COMMENT '岗位（sys_code）',
  `operating_post_name` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `position_id` bigint(10) DEFAULT NULL COMMENT '职位ID（org_position）',
  `position_name` varchar(100) DEFAULT NULL COMMENT '职位名称',
  `rank_id` bigint(10) DEFAULT NULL COMMENT '职级ID（org_rank）',
  `rank_name` varchar(100) DEFAULT NULL COMMENT '职级名称',
  `grade_id` bigint(10) DEFAULT NULL COMMENT '职等ID（org_grade）',
  `grade_name` varchar(100) DEFAULT NULL COMMENT '职等名称',
  `internal_rank_id` bigint(10) DEFAULT NULL COMMENT '内部职级ID（org_rank）',
  `internal_rank__name` varchar(100) DEFAULT NULL COMMENT '内部职级名称',
  `entry_date` datetime DEFAULT NULL COMMENT '入职日期',
  `total_attendance` double(5,2) DEFAULT NULL COMMENT '总出勤',
  `attendance_days` double(5,2) DEFAULT NULL COMMENT '应出勤天数',
  `before_night` double(5,2) DEFAULT NULL COMMENT '前夜',
  `late_night` double(5,2) DEFAULT NULL COMMENT '后夜',
  `overtime_days` double(5,2) DEFAULT NULL COMMENT '加班天数',
  `duty_days` double(5,2) DEFAULT NULL COMMENT '值班天数',
  `holiday_overtime` double(5,2) DEFAULT NULL COMMENT '节假日加班',
  `duty_holidays` double(5,2) DEFAULT NULL COMMENT '节假日值班',
  `compassionate_leave` double(5,2) DEFAULT NULL COMMENT '事假',
  `paid_sick_leave` double(5,2) DEFAULT NULL COMMENT '带薪病假',
  `sick_leave` double(5,2) DEFAULT NULL COMMENT '病假',
  `absenteeism` double(5,2) DEFAULT NULL COMMENT '旷工',
  `bereavement` double(5,2) DEFAULT NULL COMMENT '丧假',
  `marriage_holiday` double(5,2) DEFAULT NULL COMMENT '婚假',
  `maternity_leave` double(5,2) DEFAULT NULL COMMENT '产假',
  `injury_job` double(5,2) DEFAULT NULL COMMENT '工伤',
  `annual_leave` double(5,2) DEFAULT NULL COMMENT '年假',
  `night_shift_days` double(5,2) DEFAULT NULL COMMENT '夜班餐补天数',
  `overtime_pay_during_holidays` double(5,2) DEFAULT NULL COMMENT '节假日加班工资',
  `duty_pay_holidays` double(5,2) DEFAULT NULL COMMENT '节假日值班工资',
  `before_night_shift_subsidy` double(5,2) DEFAULT NULL COMMENT '前夜班补助',
  `late_night_shift_subsidy` double(5,2) DEFAULT NULL COMMENT '后夜班补助',
  `daily_overtime` double(5,2) DEFAULT NULL COMMENT '日常加班',
  `attendance_before_correction` double(5,2) DEFAULT NULL COMMENT '转正前出勤',
  `attendance_after_correction` double(5,2) DEFAULT NULL COMMENT '转正后出勤',
  `overtime_before_correction` double(5,2) DEFAULT NULL COMMENT '转正前加班',
  `overtime_after_correction` double(5,2) DEFAULT NULL COMMENT '转正后加班',
  `attendance_should_before_correction` double(5,2) DEFAULT NULL COMMENT '转正前应出勤',
  `attendance_should_after_correction` double(5,2) DEFAULT NULL COMMENT '转正后应出勤',
  `working_overtime_before_holidays` double(5,2) DEFAULT NULL COMMENT '转正前节假日加班',
  `working_overtime_after_holidays` double(5,2) DEFAULT NULL COMMENT '转正后节假日加班',
  `late` double(5,2) DEFAULT NULL COMMENT '迟到',
  `early_retreat` double(5,2) DEFAULT NULL COMMENT '早退',
  `production_inspection` double(5,2) DEFAULT NULL COMMENT '产检',
  `lactation_leave` double(5,2) DEFAULT NULL COMMENT '哺乳假',
  `visit_family` double(5,2) DEFAULT NULL COMMENT '探亲',
  `del_flag` int(11) DEFAULT '0' COMMENT '是否有效（0：有效；1：无效）',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='考勤查询';

-- ----------------------------
-- Records of atd_attendance_record
-- ----------------------------
BEGIN;
INSERT INTO `atd_attendance_record` VALUES (1, 'ASZ00022', '3', 4, '5', 6, '7', 8, '9', 10, '11', 12, '13', 14, '15', 16, '17', 18, '19', '2018-09-12 14:50:18', 20.00, 21.00, 22.00, 23.00, 24.00, 25.00, 26.00, 27.00, 28.00, 29.00, 30.00, 31.00, 32.00, 33.00, 34.00, 35.00, 36.00, 37.00, 38.00, 39.00, 40.00, 41.00, 42.00, 43.00, 44.00, 45.00, 46.00, 47.00, 48.00, 56.00, 50.00, 51.00, 52.00, 53.00, 54.00, 55.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_business_travel
-- ----------------------------
DROP TABLE IF EXISTS `atd_business_travel`;
CREATE TABLE `atd_business_travel` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `staff_no` varchar(50) DEFAULT NULL COMMENT '员工工号',
  `staff_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `job_no_id` bigint(10) DEFAULT NULL COMMENT '班次ID',
  `job_no_name` varchar(50) DEFAULT NULL COMMENT '班次名称',
  `dept_id` bigint(10) DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `grade_id` bigint(10) DEFAULT NULL COMMENT '职等ID',
  `grade_name` varchar(50) DEFAULT NULL COMMENT '职等名称',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `agent_staff_id` bigint(10) DEFAULT NULL COMMENT '代理人ID',
  `agent_staff_no` varchar(50) DEFAULT NULL COMMENT '代理人工号',
  `agent_staff_name` varchar(50) DEFAULT NULL COMMENT '代理人姓名',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `hours` double(20,10) DEFAULT NULL COMMENT '时长',
  `together_name` varchar(50) DEFAULT NULL COMMENT '同行人',
  `travel_mode` bigint(10) DEFAULT NULL COMMENT '出行方式',
  `country_id` bigint(10) DEFAULT NULL COMMENT '国家',
  `province_id` bigint(255) DEFAULT NULL COMMENT '省/州',
  `city_id` bigint(50) DEFAULT NULL COMMENT '城市',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='出差表';

-- ----------------------------
-- Records of atd_business_travel
-- ----------------------------
BEGIN;
INSERT INTO `atd_business_travel` VALUES (10, 0, '2018-09-14 15:52:11', 0, '2018-09-17 10:57:00', 112, 'ASZ00022', '李四', 17, '345', 161, '南京部门', NULL, '总经理', 1, 153, 'BJJD000013', '张铭', '2018-09-28 15:51:00', '2018-09-29 15:51:00', 24.0000000000, 'king222', 3, 233, 2834, -1, 1);
INSERT INTO `atd_business_travel` VALUES (11, 0, '2018-09-26 09:56:54', 0, '2018-09-27 19:04:28', 112, 'ASZ00022', '李四', 13, '2', 161, '南京部门', NULL, '总经理', 1, 109, 'ASZ00020', 'test1', '2018-09-13 09:56:00', '2018-09-14 09:56:00', 24.0000000000, NULL, 2, 14, 478, -1, 0);
INSERT INTO `atd_business_travel` VALUES (12, 0, '2018-09-26 09:57:07', 0, '2018-09-29 14:08:02', 114, 'ASZ00024', '李四1', NULL, '2', 169, '北京部门2', NULL, '总经理', NULL, 114, 'ASZ00024', '李四1', '2018-09-22 09:56:00', '2018-09-26 09:56:00', 96.0000000000, NULL, 2, 7, 248, 3039, 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_credit_card_log
-- ----------------------------
DROP TABLE IF EXISTS `atd_credit_card_log`;
CREATE TABLE `atd_credit_card_log` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `staff_no` varchar(50) DEFAULT NULL COMMENT '员工工号',
  `staff_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `org_id` bigint(10) DEFAULT NULL COMMENT '组织架构ID',
  `org_name` varchar(50) DEFAULT NULL COMMENT '组织架构名称',
  `status` int(2) DEFAULT NULL COMMENT '状态：0：有效，1：无效',
  `machine_id` bigint(20) DEFAULT NULL COMMENT '考勤机id',
  `machine_name` varchar(255) DEFAULT NULL COMMENT '考勤机名称',
  `time` datetime DEFAULT NULL COMMENT '刷卡时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='刷卡日志表';

-- ----------------------------
-- Records of atd_credit_card_log
-- ----------------------------
BEGIN;
INSERT INTO `atd_credit_card_log` VALUES (1, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, '北京部门2', 111, NULL, '3', '2018-08-09 00:00:00', 0);
INSERT INTO `atd_credit_card_log` VALUES (2, NULL, NULL, NULL, NULL, NULL, 'ASZ00023', '李四', NULL, '南京部门', 222, NULL, '4', '2018-01-10 00:00:00', 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_exemptions
-- ----------------------------
DROP TABLE IF EXISTS `atd_exemptions`;
CREATE TABLE `atd_exemptions` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='豁免考勤表';

-- ----------------------------
-- Records of atd_exemptions
-- ----------------------------
BEGIN;
INSERT INTO `atd_exemptions` VALUES (6, NULL, NULL, NULL, NULL, 116, 1);
INSERT INTO `atd_exemptions` VALUES (7, NULL, NULL, NULL, NULL, 122, 0);
INSERT INTO `atd_exemptions` VALUES (8, NULL, NULL, NULL, NULL, 135, 0);
INSERT INTO `atd_exemptions` VALUES (9, NULL, NULL, NULL, NULL, 135, 0);
INSERT INTO `atd_exemptions` VALUES (10, NULL, NULL, NULL, NULL, 116, 0);
INSERT INTO `atd_exemptions` VALUES (11, NULL, NULL, NULL, NULL, 128, 0);
INSERT INTO `atd_exemptions` VALUES (12, NULL, NULL, NULL, NULL, 117, 0);
INSERT INTO `atd_exemptions` VALUES (13, NULL, NULL, NULL, NULL, 117, 0);
INSERT INTO `atd_exemptions` VALUES (14, NULL, NULL, NULL, NULL, 118, 0);
INSERT INTO `atd_exemptions` VALUES (15, NULL, NULL, NULL, NULL, 140, 0);
INSERT INTO `atd_exemptions` VALUES (16, NULL, NULL, NULL, NULL, 131, 0);
INSERT INTO `atd_exemptions` VALUES (17, NULL, '2018-09-13 14:30:47', NULL, NULL, 147, 0);
INSERT INTO `atd_exemptions` VALUES (18, 0, '2018-09-25 17:15:02', NULL, NULL, 115, 0);
INSERT INTO `atd_exemptions` VALUES (19, 0, '2018-09-25 17:23:56', NULL, NULL, 130, 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_holiday_type
-- ----------------------------
DROP TABLE IF EXISTS `atd_holiday_type`;
CREATE TABLE `atd_holiday_type` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `type_name` varchar(50) DEFAULT NULL COMMENT '请假类型名称',
  `status` int(2) DEFAULT '0' COMMENT '是否启用 0:是， 1：否',
  `unit` int(2) DEFAULT NULL COMMENT '请假时间单位',
  `min_leave_time` double(5,0) DEFAULT NULL COMMENT '最小请假时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除：0：否，1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='请假类型表';

-- ----------------------------
-- Records of atd_holiday_type
-- ----------------------------
BEGIN;
INSERT INTO `atd_holiday_type` VALUES (1, NULL, NULL, NULL, NULL, '事假', 0, 3, 2, 1);
INSERT INTO `atd_holiday_type` VALUES (2, NULL, NULL, NULL, NULL, '事假', 0, 3, 12, 0);
INSERT INTO `atd_holiday_type` VALUES (3, NULL, NULL, NULL, NULL, '病假', 1, 3, 1, 0);
INSERT INTO `atd_holiday_type` VALUES (4, NULL, '2018-09-14 09:53:04', NULL, NULL, '婚假', 0, 3, 5, 0);
INSERT INTO `atd_holiday_type` VALUES (5, 118, '2018-09-14 10:12:29', NULL, NULL, '年假', 0, 3, 50, 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_job_number
-- ----------------------------
DROP TABLE IF EXISTS `atd_job_number`;
CREATE TABLE `atd_job_number` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `job_no_name` varchar(50) DEFAULT NULL COMMENT '班次名称',
  `type_id` varchar(20) DEFAULT NULL COMMENT '班次类型',
  `on_work_time` varchar(10) DEFAULT NULL COMMENT '上班时间设置',
  `off_work_time` varchar(10) DEFAULT NULL COMMENT '下班时间设置',
  `noon_break` bigint(5) DEFAULT NULL COMMENT '午休时间（分钟）',
  `effective_punching` bigint(5) DEFAULT NULL COMMENT '有效打卡时间（小时）/需要工作时间（分钟）',
  `earliest_time` varchar(10) DEFAULT NULL COMMENT '最早上班时间',
  `latest_time` varchar(10) DEFAULT NULL COMMENT '最晚上班时间',
  `is_special` int(2) DEFAULT NULL COMMENT '是否特殊班次：0：是，1：否',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='班次(特殊班次)表';

-- ----------------------------
-- Records of atd_job_number
-- ----------------------------
BEGIN;
INSERT INTO `atd_job_number` VALUES (1, NULL, NULL, NULL, NULL, '21', '21', '21', '21', 21, 21, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (2, NULL, NULL, NULL, NULL, '1', '1', '1', '1', 1, 1, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (3, NULL, NULL, NULL, NULL, '2', '2', '2', '2', 2, 2, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (4, NULL, NULL, NULL, NULL, '12', NULL, NULL, NULL, NULL, 12, '12', '12', 0, 1);
INSERT INTO `atd_job_number` VALUES (5, NULL, NULL, NULL, NULL, '3', NULL, NULL, NULL, NULL, 3, '3', '3', 0, 1);
INSERT INTO `atd_job_number` VALUES (6, NULL, NULL, NULL, NULL, '4', NULL, NULL, NULL, NULL, 4, '4', '4', 0, 1);
INSERT INTO `atd_job_number` VALUES (7, NULL, NULL, NULL, NULL, '3', '3', '3', '3', 3, 3, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (8, NULL, NULL, NULL, NULL, '1', '2', '01:00', '00:36', NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (9, NULL, NULL, NULL, NULL, '6', '6', '03:00', '02:36', 6, 6, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (10, NULL, NULL, NULL, NULL, '9', '9', '03:00', '02:40', 9, 9, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (11, NULL, NULL, NULL, NULL, '1', '2', '14:05', '16:07', 123, 12, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (12, NULL, NULL, NULL, NULL, '101', '101', '20:09', '22:08', 11, 31, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (13, NULL, NULL, NULL, NULL, '2', '2', '15:45', '17:45', 3, 4, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (14, NULL, NULL, NULL, NULL, '1', '10', '14:49', '19:50', 2, 2, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (15, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 11, '03:00', '19:00', 0, 0);
INSERT INTO `atd_job_number` VALUES (16, NULL, NULL, NULL, NULL, '10', NULL, NULL, NULL, NULL, 23, '04:00', '21:03', 0, 0);
INSERT INTO `atd_job_number` VALUES (17, NULL, NULL, NULL, NULL, '345', '54', '01:01', '02:02', 35, 4, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (18, 0, '2018-09-20 14:28:10', NULL, NULL, '10234', '432', '', '', NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `atd_job_number` VALUES (19, 0, '2018-09-20 14:30:24', NULL, NULL, '432', NULL, NULL, NULL, NULL, 43, '02:00', '03:02', 0, 0);
INSERT INTO `atd_job_number` VALUES (20, 0, '2018-09-25 17:18:57', NULL, NULL, 'fsda', '10', '13:02', '20:03', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (21, 0, '2018-09-26 10:59:33', 0, '2018-09-26 10:59:47', '阿飞啊', '阿飞啊', '11:58', '11:55', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (22, 0, '2018-09-26 11:18:56', NULL, NULL, 'AfD', 'fSSS', '11:59', '11:59', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `atd_job_number` VALUES (23, 0, '2018-09-26 15:25:21', NULL, NULL, 'gsfgdsfgfds gfdsgfds', 'gfdsg', '17:07', '17:07', NULL, NULL, NULL, NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_machine
-- ----------------------------
DROP TABLE IF EXISTS `atd_machine`;
CREATE TABLE `atd_machine` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `machine_name` varchar(50) DEFAULT NULL COMMENT '考勤机名称',
  `base_id` bigint(10) DEFAULT NULL COMMENT '基地ID',
  `factory_id` bigint(10) DEFAULT NULL COMMENT '厂别ID',
  `seq` bigint(10) DEFAULT NULL COMMENT '序列号',
  `earliest_sign_in` bigint(5) DEFAULT NULL COMMENT '最早提前签到（分钟）',
  `latest_sign_in` bigint(5) DEFAULT NULL COMMENT '最晚推迟签到',
  `earliest_sign_off` bigint(5) DEFAULT NULL COMMENT '最早提前签退（分钟）',
  `latest_sign_off` bigint(5) DEFAULT NULL COMMENT '最晚推迟签退（分钟）',
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='考勤机表';

-- ----------------------------
-- Records of atd_machine
-- ----------------------------
BEGIN;
INSERT INTO `atd_machine` VALUES (1, NULL, NULL, NULL, NULL, '22', 155, 12, 12, 12, 12, 12, 12, 1);
INSERT INTO `atd_machine` VALUES (2, NULL, NULL, NULL, NULL, '22', 155, 2, 2, 2, 2, 2, 2, 1);
INSERT INTO `atd_machine` VALUES (3, NULL, NULL, NULL, NULL, '44', 187, 3, 3, 3, 3, 3, 3, 1);
INSERT INTO `atd_machine` VALUES (4, NULL, NULL, NULL, NULL, '1', 187, 2, 1, 1, 1, 1, 1, 1);
INSERT INTO `atd_machine` VALUES (5, NULL, NULL, NULL, NULL, '2', 155, 3, 3, 3, 3, 3, 3, 0);
INSERT INTO `atd_machine` VALUES (6, NULL, NULL, NULL, NULL, '3', 187, 3, 3, 3, 3, 3, 3, 0);
INSERT INTO `atd_machine` VALUES (7, NULL, NULL, NULL, NULL, '4', 155, 2, 4, 4, 4, 4, 4, 0);
INSERT INTO `atd_machine` VALUES (8, 0, '2018-09-20 14:43:00', NULL, NULL, 'WRE', 187, 2, 4234, NULL, NULL, NULL, NULL, 0);
INSERT INTO `atd_machine` VALUES (9, 0, '2018-09-26 10:34:21', NULL, NULL, '2343', 155, NULL, NULL, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for atd_overtime_sheet
-- ----------------------------
DROP TABLE IF EXISTS `atd_overtime_sheet`;
CREATE TABLE `atd_overtime_sheet` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `base_id` bigint(10) DEFAULT NULL COMMENT '基地ID',
  `base_name` varchar(50) DEFAULT NULL COMMENT '基地名称',
  `factory_id` bigint(10) DEFAULT NULL COMMENT '厂别ID',
  `factory_name` varchar(50) DEFAULT NULL COMMENT '厂别名称',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `staff_no` varchar(50) DEFAULT NULL COMMENT '员工工号',
  `staff_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `job_no_id` bigint(10) DEFAULT NULL COMMENT '班次ID',
  `job_no_name` varchar(50) DEFAULT NULL COMMENT '班次名称',
  `dept_id` bigint(10) DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `overtime_time` double(20,2) DEFAULT NULL COMMENT '加班时长',
  `reason` varchar(500) DEFAULT NULL COMMENT '加班原因',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除：0：否，1：是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='加班单表';

-- ----------------------------
-- Records of atd_overtime_sheet
-- ----------------------------
BEGIN;
INSERT INTO `atd_overtime_sheet` VALUES (1, NULL, NULL, NULL, NULL, 155, NULL, NULL, NULL, 133, NULL, NULL, 14, NULL, 176, NULL, '2018-08-28 16:25:00', '2018-08-29 16:32:00', 24.01, '12564023', 1);
INSERT INTO `atd_overtime_sheet` VALUES (2, NULL, NULL, NULL, NULL, 155, NULL, NULL, NULL, 133, NULL, NULL, 13, NULL, 169, NULL, '2018-08-08 11:50:00', '2018-08-19 11:54:00', 264.06, NULL, 1);
INSERT INTO `atd_overtime_sheet` VALUES (3, NULL, NULL, 0, '2018-09-26 11:53:45', 155, '北京基地', NULL, NULL, 133, 'BJJD000006', '李四9', 14, '1', 176, '北子部23', '2018-08-18 13:08:00', '2018-08-31 17:10:00', 316.03, '1321324131323', 0);
INSERT INTO `atd_overtime_sheet` VALUES (4, NULL, NULL, NULL, NULL, 187, '南京基地', NULL, NULL, 116, 'ASZ00026', '李四', 13, '2', 190, '南京部门', '2018-08-08 13:09:00', '2018-08-16 12:09:00', 191.00, '46544654', 0);
INSERT INTO `atd_overtime_sheet` VALUES (5, NULL, NULL, 0, '2018-09-26 11:54:14', 187, '南京基地', NULL, NULL, 152, 'CP00000002', 'jsl', 17, '345', 200, 'test1', '2018-09-20 11:53:00', '2018-09-22 11:53:00', 48.00, NULL, 0);
INSERT INTO `atd_overtime_sheet` VALUES (6, 0, '2018-09-26 11:47:45', 0, '2018-09-26 11:53:56', 187, '北京基地', NULL, NULL, 144, 'ja12345679', '大师傅1', NULL, NULL, 191, '北京部门', NULL, NULL, NULL, NULL, 0);
INSERT INTO `atd_overtime_sheet` VALUES (7, 0, '2018-09-27 17:55:45', NULL, NULL, 155, 'null', NULL, NULL, 117, 'ASZ00027', '李四5', 13, '2', 161, '北京部门2', '2018-10-14 17:55:00', '2018-10-26 17:55:00', 288.00, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for customer_contacts
-- ----------------------------
DROP TABLE IF EXISTS `customer_contacts`;
CREATE TABLE `customer_contacts` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODIFICTION` int(11) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `POSITION` varchar(50) DEFAULT NULL,
  `MOBILE` varchar(50) DEFAULT NULL,
  `MAIL` varchar(50) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for elt_accumulation_fund
-- ----------------------------
DROP TABLE IF EXISTS `elt_accumulation_fund`;
CREATE TABLE `elt_accumulation_fund` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BASE_ID` bigint(20) DEFAULT NULL,
  `RULE_NAME` varchar(50) DEFAULT NULL,
  `SOCIAL_INSURANCE_BASE` varchar(50) DEFAULT NULL,
  `PERSONAL_PROPORTION` varchar(50) DEFAULT NULL,
  `PERSONAL_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_RATIO` varchar(50) DEFAULT NULL,
  `COMPANY_AMOUNT` varchar(50) DEFAULT NULL,
  `PERSONAL_SUPPLEMENTARY_RATIO` varchar(50) DEFAULT NULL,
  `PERSONAL_SUPPLEMENTARY_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_SUPPLEMENTARY_PROPORTION` varchar(50) DEFAULT NULL,
  `COMPANY_SUPPLEMENTARY_AMOUNT` varchar(50) DEFAULT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elt_accumulation_fund
-- ----------------------------
BEGIN;
INSERT INTO `elt_accumulation_fund` VALUES (1, 325, '222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2020-06-01 18:14:33', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for elt_allowance
-- ----------------------------
DROP TABLE IF EXISTS `elt_allowance`;
CREATE TABLE `elt_allowance` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BASE_ID` bigint(20) DEFAULT NULL,
  `ALLOWANCE_CATEGORY` varchar(50) DEFAULT NULL,
  `MONEY` varchar(50) DEFAULT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elt_allowance
-- ----------------------------
BEGIN;
INSERT INTO `elt_allowance` VALUES (1, 155, '北京基地', '10000', '北京基地-后沙峪', 0, NULL, '2020-06-01 18:17:54', NULL, '2020-06-01 18:18:14');
COMMIT;

-- ----------------------------
-- Table structure for elt_performance
-- ----------------------------
DROP TABLE IF EXISTS `elt_performance`;
CREATE TABLE `elt_performance` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STAFF_NO` varchar(50) DEFAULT NULL,
  `STAFF_ID` bigint(20) DEFAULT NULL,
  `MONTH` int(11) DEFAULT NULL,
  `AMOUNT_OF_PERFORMANCE` float DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `BASE_ID` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for elt_ref_staff_emolument
-- ----------------------------
DROP TABLE IF EXISTS `elt_ref_staff_emolument`;
CREATE TABLE `elt_ref_staff_emolument` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STAFF_ID` bigint(20) DEFAULT NULL,
  `RULE_ID` bigint(20) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for elt_salary
-- ----------------------------
DROP TABLE IF EXISTS `elt_salary`;
CREATE TABLE `elt_salary` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BASE_ID` bigint(20) DEFAULT NULL,
  `WAGE_CATEGORY` varchar(50) DEFAULT NULL,
  `PAY_THE_AMOUNT` varchar(50) DEFAULT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for elt_social_security
-- ----------------------------
DROP TABLE IF EXISTS `elt_social_security`;
CREATE TABLE `elt_social_security` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BASE_ID` bigint(20) DEFAULT NULL,
  `RULE_NAME` varchar(50) DEFAULT NULL,
  `SOCIAL_INSURANCE_BASE` varchar(50) DEFAULT NULL,
  `IINDIVIDUAL_PERSON_RATIO` varchar(50) DEFAULT NULL,
  `PERSONAL_PENSION_AMOUNT` varchar(50) DEFAULT NULL,
  `PERSONAL_MEDICAL_RATIO` varchar(50) DEFAULT NULL,
  `PERSONAL_MEDICAL_AMOUNT` varchar(50) DEFAULT NULL,
  `PERSONAL_INJURY_RATIO` varchar(50) DEFAULT NULL,
  `PERSONAL_INJURY_AMOUNT` varchar(50) DEFAULT NULL,
  `PERSONAL_UNEMPLOYMENT_RATIO` varchar(50) DEFAULT NULL,
  `PERSONAL_UNEMPLOYMENT_AMOUNT` varchar(50) DEFAULT NULL,
  `PERSONAL_FERTILITY_RATIO` varchar(50) DEFAULT NULL,
  `PERSONAL_FERTILITY_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_PENSION_RATIO` varchar(50) DEFAULT NULL,
  `COMPANY_PENSION_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_MEDICAL_RATIO` varchar(50) DEFAULT NULL,
  `COMPANY_MEDICAL_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_INJURY_RATIO` varchar(50) DEFAULT NULL,
  `COMPANY_INJURY_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_UNEMPLOYMENT_RATIO` varchar(50) DEFAULT NULL,
  `COMPANY_UNEMPLOYMENT_AMOUNT` varchar(50) DEFAULT NULL,
  `COMPANY_BIRTH_RATIO` varchar(50) DEFAULT NULL,
  `COMPANY_BIRTH_AMOUNT` varchar(50) DEFAULT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for elt_subsidy
-- ----------------------------
DROP TABLE IF EXISTS `elt_subsidy`;
CREATE TABLE `elt_subsidy` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BASE_ID` bigint(20) DEFAULT NULL,
  `SUBSIDY_TYPE` varchar(50) DEFAULT NULL,
  `SUBSIDIES` varchar(50) DEFAULT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elt_subsidy
-- ----------------------------
BEGIN;
INSERT INTO `elt_subsidy` VALUES (1, 306, '东海基地', '12000', '东海基地', 1, NULL, '2020-06-01 18:18:37', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织架构id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(255) DEFAULT NULL COMMENT '上级id',
  `function_type` bigint(20) DEFAULT NULL COMMENT '职能属性',
  `base_or_dept_name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `base_or_dept_code` varchar(255) DEFAULT NULL COMMENT '组织编号',
  `base_or_dept_short_name` varchar(255) DEFAULT NULL COMMENT '组织简称',
  `establish_date` datetime DEFAULT NULL COMMENT '成立时间',
  `effective_date` datetime DEFAULT NULL COMMENT '生效时间',
  `business_unit` varchar(255) DEFAULT NULL COMMENT '业务单元',
  `level_id` bigint(20) DEFAULT NULL COMMENT '层级',
  `address` varchar(255) DEFAULT NULL COMMENT '地点',
  `is_fictitious` int(11) DEFAULT NULL COMMENT '是否虚拟部门',
  `dept_duty` varchar(255) DEFAULT NULL COMMENT '部门职责',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_org` int(11) DEFAULT NULL COMMENT '-1:总公司，0:基地，1:部门',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(20) DEFAULT '0' COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=340 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织架构表';

-- ----------------------------
-- Records of org
-- ----------------------------
BEGIN;
INSERT INTO `org` VALUES (1, NULL, 0, NULL, '南京部门', '001001', '14', '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, -1, 0, NULL, 0, '2019-01-09 16:50:09');
INSERT INTO `org` VALUES (2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, 1, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 15:00:43');
INSERT INTO `org` VALUES (169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 15:00:43');
INSERT INTO `org` VALUES (187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 1, NULL, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 1, NULL, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, '2018-10-15 14:58:47');
INSERT INTO `org` VALUES (327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL, 0, '2018-10-15 14:54:12');
INSERT INTO `org` VALUES (331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, NULL);
INSERT INTO `org` VALUES (332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, 0, NULL);
INSERT INTO `org` VALUES (333, '2018-10-18 09:10:47', 1, NULL, 'dfsvfd', '001012', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 0, '2018-10-18 09:20:03');
INSERT INTO `org` VALUES (336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, 0, NULL);
INSERT INTO `org` VALUES (337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 1, 0, 0, NULL);
INSERT INTO `org` VALUES (338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, 1, 1, 0, 0, NULL);
INSERT INTO `org` VALUES (339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for org_all_backup
-- ----------------------------
DROP TABLE IF EXISTS `org_all_backup`;
CREATE TABLE `org_all_backup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织备份id',
  `row_id` bigint(20) NOT NULL COMMENT '组织id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(255) DEFAULT NULL COMMENT '父id',
  `function_type` bigint(20) DEFAULT NULL COMMENT '职能属性',
  `base_or_dept_name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `base_or_dept_code` varchar(255) DEFAULT NULL COMMENT '组织编号',
  `base_or_dept_short_name` varchar(255) DEFAULT NULL COMMENT '组织简称',
  `establish_date` datetime DEFAULT NULL COMMENT '成立日期',
  `effective_date` datetime DEFAULT NULL COMMENT '生效日期',
  `business_unit` varchar(255) DEFAULT NULL COMMENT '业务单元',
  `level_id` bigint(20) DEFAULT NULL COMMENT '层级',
  `address` varchar(255) DEFAULT NULL COMMENT '地点',
  `is_fictitious` int(11) DEFAULT NULL COMMENT '是否虚拟部门',
  `dept_duty` varchar(255) DEFAULT NULL COMMENT '部门职责',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `report_latitude` varchar(255) DEFAULT NULL COMMENT '汇报关系-纬度',
  `report_superior` varchar(255) DEFAULT NULL COMMENT '汇报关系-上级',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系方式-联系电话',
  `contact_facsimile` varchar(255) DEFAULT NULL COMMENT '联系方式-传真',
  `contact_zipcode` varchar(255) DEFAULT NULL COMMENT '联系方式-邮编',
  `contact_url` varchar(255) DEFAULT NULL COMMENT '联系方式-网址',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系方式-地址',
  `leader_no` varchar(255) DEFAULT NULL COMMENT '总负责人-工号',
  `leader_name` varchar(255) DEFAULT NULL COMMENT '总负责人-姓名',
  `leader_phone` varchar(255) DEFAULT NULL COMMENT '总负责人-联系方式',
  `is_valid` int(11) DEFAULT '0' COMMENT '是否有效   0:有效,1:无效',
  `dept_num` varchar(255) DEFAULT NULL COMMENT '编制人数',
  `is_org` int(11) DEFAULT NULL COMMENT '-1：公司；0：基地；1：部门',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `back_up_time` datetime NOT NULL COMMENT '备份时间',
  `total_staff_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=996 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织备份表';

-- ----------------------------
-- Records of org_all_backup
-- ----------------------------
BEGIN;
INSERT INTO `org_all_backup` VALUES (470, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, '12345678', '大师傅1', '13333333333', 0, '1000', -1, 0, '2018-08-01 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (471, 76, '2018-08-06 20:40:52', 1, NULL, '北京基地', 'JAORG000040', NULL, '2018-08-06 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, '无上级', NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 0, 0, '2018-08-01 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (472, 104, '2018-08-08 11:18:16', 1, NULL, '南京基地123', 'JAORG000068', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 0, 0, '2018-08-01 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (473, 111, '2018-08-08 14:32:54', 1, NULL, '海淀基地', 'JAORG000075', NULL, '2018-08-02 00:00:00', '2018-08-09 00:00:00', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 0, 0, '2018-08-01 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (474, 135, '2018-08-09 15:15:29', 1, NULL, '晶澳添加', 'JAORG000099', NULL, '2018-08-02 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 0, 0, '2018-08-01 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (475, 143, '2018-08-09 15:31:02', 76, NULL, '北京部门', 'JAORG000107', '2323', '2018-08-01 00:00:00', '2018-08-08 00:00:00', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '2220', 1, 0, '2018-08-01 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (476, 151, '2018-08-09 16:10:10', 143, NULL, '北京部门', 'JAORG000115', '333', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '53', 1, 0, '2018-08-01 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (493, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, '12345678', '大师傅1', '13333333333', 0, '1000', -1, 0, '2018-08-10 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (500, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00021', '李四', '13333333333', 0, '11283', -1, 0, '2018-08-14 00:00:00', 17);
INSERT INTO `org_all_backup` VALUES (501, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', 'JAORG000119', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李四', NULL, NULL, '10283', 0, 0, '2018-08-14 00:00:00', 17);
INSERT INTO `org_all_backup` VALUES (502, 158, '2018-08-13 11:42:37', 161, NULL, '北子部2', 'JAORG000122', '被子', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '140', 1, 0, '2018-08-14 00:00:00', 13);
INSERT INTO `org_all_backup` VALUES (503, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', 'JAORG000125', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10283', 1, 0, '2018-08-14 00:00:00', 17);
INSERT INTO `org_all_backup` VALUES (504, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', 'JAORG000133', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '133', 1, 0, '2018-08-14 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (505, 175, '2018-08-13 20:03:04', 1, NULL, '南京基地', 'JAORG000139', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-08-14 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (506, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', 'JAORG000140', '北子部2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-08-14 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (507, 177, '2018-08-14 16:18:59', 175, NULL, '南京部门', 'JAORG000141', '363352', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-08-14 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (508, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11370', -1, 0, '2018-08-27 00:00:00', 24);
INSERT INTO `org_all_backup` VALUES (509, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', 'JAORG000119', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-08-27 00:00:00', 19);
INSERT INTO `org_all_backup` VALUES (510, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', 'JAORG000125', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-08-27 00:00:00', 18);
INSERT INTO `org_all_backup` VALUES (511, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', 'JAORG000133', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-08-27 00:00:00', 12);
INSERT INTO `org_all_backup` VALUES (512, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', 'JAORG000140', '北子部234', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-08-27 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (513, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', 'JAORG000151', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'king', NULL, NULL, '1100', 0, 0, '2018-08-27 00:00:00', 5);
INSERT INTO `org_all_backup` VALUES (514, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', 'JAORG000154', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1100', 1, 0, '2018-08-27 00:00:00', 5);
INSERT INTO `org_all_backup` VALUES (515, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', 'JAORG000155', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1100', 1, 0, '2018-08-27 00:00:00', 3);
INSERT INTO `org_all_backup` VALUES (516, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', 'JAORG000164', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '100', 1, 0, '2018-08-27 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (541, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11370', -1, 0, '2018-08-28 00:00:00', 26);
INSERT INTO `org_all_backup` VALUES (542, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', 'JAORG000119', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-08-28 00:00:00', 20);
INSERT INTO `org_all_backup` VALUES (543, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', 'JAORG000125', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-08-28 00:00:00', 19);
INSERT INTO `org_all_backup` VALUES (544, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', 'JAORG000133', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-08-28 00:00:00', 13);
INSERT INTO `org_all_backup` VALUES (545, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', 'JAORG000140', '北子部234', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-08-28 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (546, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', 'JAORG000151', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'king', NULL, NULL, '1100', 0, 0, '2018-08-28 00:00:00', 6);
INSERT INTO `org_all_backup` VALUES (547, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', 'JAORG000154', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1100', 1, 0, '2018-08-28 00:00:00', 6);
INSERT INTO `org_all_backup` VALUES (548, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', 'JAORG000155', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1100', 1, 0, '2018-08-28 00:00:00', 4);
INSERT INTO `org_all_backup` VALUES (549, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', 'JAORG000164', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '100', 1, 0, '2018-08-28 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (550, 271, '2018-08-27 19:21:12', 1, NULL, '总部', 'JAORG000234', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-08-28 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (551, 282, '2018-08-27 19:38:33', 1, NULL, '马来基地', 'JAORG000245', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-08-28 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (552, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', 'JAORG000246', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-08-28 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (601, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11380', -1, 0, '2018-09-06 00:00:00', 29);
INSERT INTO `org_all_backup` VALUES (602, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', 'JAORG000119', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-09-06 00:00:00', 21);
INSERT INTO `org_all_backup` VALUES (603, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', 'JAORG000125', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-09-06 00:00:00', 20);
INSERT INTO `org_all_backup` VALUES (604, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', 'JAORG000133', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-09-06 00:00:00', 12);
INSERT INTO `org_all_backup` VALUES (605, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', 'JAORG000140', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-09-06 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (606, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', 'JAORG000151', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'king', NULL, NULL, '1110', 0, 0, '2018-09-06 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (607, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', 'JAORG000154', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2018-09-06 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (608, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', 'JAORG000155', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2018-09-06 00:00:00', 5);
INSERT INTO `org_all_backup` VALUES (609, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', 'JAORG000164', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2018-09-06 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (610, 271, '2018-08-27 19:21:12', 1, NULL, '销售事业部-东南亚、太平洋地区', 'JAORG000234', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (611, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', 'JAORG000246', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2018-09-06 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (612, 293, '2018-08-30 16:31:07', 1, NULL, '5362', 'JAORG000256', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (613, 294, '2018-08-30 16:31:19', 1, NULL, '569', 'JAORG000257', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (614, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', 'JAORG000258', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (615, 296, '2018-09-04 14:12:12', 1, NULL, '1', 'JAORG000259', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (616, 297, '2018-09-04 14:12:15', 1, NULL, '2', 'JAORG000260', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (617, 298, '2018-09-04 14:12:17', 1, NULL, '3', 'JAORG000261', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (618, 299, '2018-09-04 14:12:20', 1, NULL, '4', 'JAORG000262', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (619, 300, '2018-09-04 14:12:24', 1, NULL, '5', 'JAORG000263', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (620, 301, '2018-09-04 14:12:27', 1, NULL, '6', 'JAORG000264', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (621, 302, '2018-09-04 14:12:30', 1, NULL, '7', 'JAORG000265', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (622, 303, '2018-09-04 14:12:32', 1, NULL, '8', 'JAORG000266', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (623, 304, '2018-09-04 14:12:35', 1, NULL, '9', 'JAORG000267', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (624, 305, '2018-09-04 14:12:39', 1, NULL, '10', 'JAORG000268', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (625, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', 'JAORG000269', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-06 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (626, 307, '2018-09-06 15:03:04', 306, NULL, '人力资源部', 'JAORG000270', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-09-06 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (627, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', NULL, NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11380', -1, 0, '2018-09-29 00:00:00', 38);
INSERT INTO `org_all_backup` VALUES (628, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', 'JAORG000119', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-09-29 00:00:00', 28);
INSERT INTO `org_all_backup` VALUES (629, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', 'JAORG000125', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-09-29 00:00:00', 27);
INSERT INTO `org_all_backup` VALUES (630, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', 'JAORG000133', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-09-29 00:00:00', 14);
INSERT INTO `org_all_backup` VALUES (631, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', 'JAORG000140', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-09-29 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (632, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', 'JAORG000151', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2018-09-29 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (633, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', 'JAORG000154', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2018-09-29 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (634, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', 'JAORG000155', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2018-09-29 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (635, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', 'JAORG000164', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2018-09-29 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (636, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', 'JAORG000246', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2018-09-29 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (637, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', 'JAORG000258', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (638, 297, '2018-09-04 14:12:15', 1, NULL, '2', 'JAORG000260', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (639, 302, '2018-09-04 14:12:30', 1, NULL, '7', 'JAORG000265', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (640, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', 'JAORG000269', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (641, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', 'JAORG000273', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (642, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', 'JAORG000277', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (643, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', 'JAORG000270', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (644, 2, '2018-09-19 16:20:43', 1, NULL, '总部', 'JAORG000280', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (645, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', 'JAORG000019', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-09-29 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (681, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', '2', NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11581', -1, 0, '2018-10-23 00:00:00', 48);
INSERT INTO `org_all_backup` VALUES (682, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-10-23 00:00:00', 35);
INSERT INTO `org_all_backup` VALUES (683, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-10-23 00:00:00', 34);
INSERT INTO `org_all_backup` VALUES (684, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-10-23 00:00:00', 20);
INSERT INTO `org_all_backup` VALUES (685, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (686, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2018-10-23 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (687, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2018-10-23 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (688, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2018-10-23 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (689, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (690, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (691, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (692, 297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (693, 302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (694, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (695, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (696, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (697, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (698, 2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (699, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (700, 320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (701, 321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (702, 322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (703, 324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (704, 325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (705, 326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (706, 327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (707, 328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (708, 329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (709, 330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (710, 331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (711, 332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (712, 336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (713, 337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (714, 338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (715, 339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-10-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (716, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', '2', NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11581', -1, 0, '2018-11-09 00:00:00', 45);
INSERT INTO `org_all_backup` VALUES (717, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-11-09 00:00:00', 33);
INSERT INTO `org_all_backup` VALUES (718, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-11-09 00:00:00', 32);
INSERT INTO `org_all_backup` VALUES (719, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-11-09 00:00:00', 18);
INSERT INTO `org_all_backup` VALUES (720, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (721, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2018-11-09 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (722, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2018-11-09 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (723, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2018-11-09 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (724, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (725, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (726, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (727, 297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (728, 302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (729, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (730, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (731, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (732, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (733, 2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (734, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (735, 320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (736, 321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (737, 322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (738, 324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (739, 325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (740, 326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (741, 327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (742, 328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (743, 329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (744, 330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (745, 331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (746, 332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (747, 336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (748, 337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (749, 338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (750, 339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-09 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (786, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', '2', NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11581', -1, 0, '2018-11-12 00:00:00', 45);
INSERT INTO `org_all_backup` VALUES (787, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-11-12 00:00:00', 33);
INSERT INTO `org_all_backup` VALUES (788, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-11-12 00:00:00', 32);
INSERT INTO `org_all_backup` VALUES (789, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-11-12 00:00:00', 18);
INSERT INTO `org_all_backup` VALUES (790, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (791, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2018-11-12 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (792, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2018-11-12 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (793, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2018-11-12 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (794, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (795, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (796, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (797, 297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (798, 302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (799, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (800, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (801, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (802, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (803, 2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (804, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (805, 320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (806, 321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (807, 322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (808, 324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (809, 325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (810, 326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (811, 327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (812, 328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (813, 329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (814, 330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (815, 331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (816, 332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (817, 336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (818, 337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (819, 338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (820, 339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-12 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (821, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', '2', NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11581', -1, 0, '2018-11-13 00:00:00', 45);
INSERT INTO `org_all_backup` VALUES (822, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2018-11-13 00:00:00', 33);
INSERT INTO `org_all_backup` VALUES (823, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2018-11-13 00:00:00', 32);
INSERT INTO `org_all_backup` VALUES (824, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2018-11-13 00:00:00', 18);
INSERT INTO `org_all_backup` VALUES (825, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (826, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2018-11-13 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (827, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2018-11-13 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (828, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2018-11-13 00:00:00', 7);
INSERT INTO `org_all_backup` VALUES (829, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (830, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (831, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (832, 297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (833, 302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (834, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (835, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (836, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (837, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (838, 2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (839, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (840, 320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (841, 321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (842, 322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (843, 324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (844, 325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (845, 326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (846, 327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (847, 328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (848, 329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (849, 330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (850, 331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (851, 332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (852, 336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (853, 337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (854, 338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (855, 339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2018-11-13 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (926, 1, NULL, 0, NULL, '晶澳太阳能股份有限公司', '2', NULL, '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'ASZ00020', 'test1', '13333333333', 0, '11581', -1, 0, '2019-01-08 00:00:00', 45);
INSERT INTO `org_all_backup` VALUES (927, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2019-01-08 00:00:00', 33);
INSERT INTO `org_all_backup` VALUES (928, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2019-01-08 00:00:00', 32);
INSERT INTO `org_all_backup` VALUES (929, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2019-01-08 00:00:00', 18);
INSERT INTO `org_all_backup` VALUES (930, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (931, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2019-01-08 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (932, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2019-01-08 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (933, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2019-01-08 00:00:00', 8);
INSERT INTO `org_all_backup` VALUES (934, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (935, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (936, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (937, 297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (938, 302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (939, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (940, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (941, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (942, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (943, 2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (944, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (945, 320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (946, 321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (947, 322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (948, 324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (949, 325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (950, 326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (951, 327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (952, 328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (953, 329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (954, 330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (955, 331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (956, 332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (957, 336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (958, 337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (959, 338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (960, 339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-08 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (961, 1, NULL, 0, NULL, '南京部门', '001001', '14', '2018-07-10 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'BJJD00004', '李四2', '13333333333', 0, '10070', -1, 0, '2019-01-23 00:00:00', 45);
INSERT INTO `org_all_backup` VALUES (962, 155, '2018-08-10 14:28:33', 1, NULL, '北京基地', '001002', NULL, '2018-08-09 00:00:00', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0, 0, '2019-01-23 00:00:00', 33);
INSERT INTO `org_all_backup` VALUES (963, 161, '2018-08-13 14:22:48', 155, NULL, '南京部门', '001001', '14', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 1, 0, '2019-01-23 00:00:00', 32);
INSERT INTO `org_all_backup` VALUES (964, 169, '2018-08-13 17:58:48', 161, NULL, '北京部门2', '2', '北部2', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 1, 0, '2019-01-23 00:00:00', 18);
INSERT INTO `org_all_backup` VALUES (965, 176, '2018-08-14 16:18:16', 155, NULL, '北子部23', '001002', '北子部234', '2018-08-15 00:00:00', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 1, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (966, 187, '2018-08-15 19:42:21', 1, NULL, '南京基地', '001003', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0, 0, '2019-01-23 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (967, 190, '2018-08-15 20:22:03', 187, NULL, '南京部门', '2', '南京部门1', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 1, 0, '2019-01-23 00:00:00', 10);
INSERT INTO `org_all_backup` VALUES (968, 191, '2018-08-15 20:23:25', 190, NULL, '北京部门', '2', '北子部234', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 1, 0, '2019-01-23 00:00:00', 8);
INSERT INTO `org_all_backup` VALUES (969, 200, '2018-08-16 21:47:29', 191, NULL, '北京部门新增', '2', '北新增', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 1, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (970, 283, '2018-08-27 20:10:37', 200, NULL, 'test1', '2', 'test1', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (971, 295, '2018-08-30 16:44:22', 1, NULL, '南京部门', '001004', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (972, 297, '2018-09-04 14:12:15', 1, NULL, '2', '001005', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (973, 302, '2018-09-04 14:12:30', 1, NULL, '7', '001006', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (974, 306, '2018-09-06 15:02:26', 1, NULL, '东海基地', '001007', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (975, 310, '2018-09-10 17:02:18', 190, NULL, '北京基地', '2', '北子部23455555', '2018-08-28 00:00:00', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (976, 314, '2018-09-17 19:47:25', 283, NULL, 'test2', '2', 'test2', NULL, NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (977, 3, '2018-09-06 15:03:04', 2, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (978, 2, '2018-09-19 16:20:43', 1, NULL, '总部', '001001', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (979, 319, '2018-09-19 19:09:23', 187, NULL, '南京部门78', '2', '北京部78', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (980, 320, '2018-10-10 11:28:27', 1, NULL, '邢台基地', '001008', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (981, 321, '2018-10-10 11:30:31', 320, NULL, '人力资源部', '2', '人力资源部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (982, 322, '2018-10-10 14:28:27', 321, NULL, '的发达省份的', '2', '的发达省份的', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (983, 324, '2018-10-10 14:45:39', 320, NULL, '发生的', '2', '发生的', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (984, 325, '2018-10-10 14:48:01', 1, NULL, '合肥基地', '001009', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (985, 326, '2018-10-10 14:53:52', 1, NULL, '芜湖基地', '001010', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (986, 327, '2018-10-10 14:54:41', 326, NULL, '芜湖部门', '2', '芜湖部门', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (987, 328, '2018-10-10 14:55:21', 326, NULL, '芜湖部门2', '2', '芜湖部门2', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (988, 329, '2018-10-10 14:56:14', 327, NULL, '芜湖子一部', '2', '芜湖子一部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (989, 330, '2018-10-10 14:59:38', 328, NULL, '芜湖子二部', '2', '芜湖子二部', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (990, 331, '2018-10-15 17:21:34', 1, NULL, '宁晋基地', '001011', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (991, 332, '2018-10-15 17:22:27', 331, NULL, '采购部', '001011001', '采购部', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 1);
INSERT INTO `org_all_backup` VALUES (992, 336, '2018-10-18 10:15:18', 1, NULL, 'fsdafsd', '001013', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (993, 337, '2018-10-18 10:16:10', 336, NULL, 'rdfdsa', '001013001', 'rdfdsa', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (994, 338, '2018-10-18 10:17:26', 336, NULL, 'dfsafc', '001013002', 'dfsafc', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2019-01-23 00:00:00', 0);
INSERT INTO `org_all_backup` VALUES (995, 339, '2018-10-18 10:30:37', 1, NULL, 'aaa', '001014', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0, 0, '2019-01-23 00:00:00', 0);
COMMIT;

-- ----------------------------
-- Table structure for org_copy1
-- ----------------------------
DROP TABLE IF EXISTS `org_copy1`;
CREATE TABLE `org_copy1` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织架构id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(255) DEFAULT NULL COMMENT '上级id',
  `function_type` bigint(20) DEFAULT NULL COMMENT '职能属性',
  `base_or_dept_name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `base_or_dept_code` varchar(255) DEFAULT NULL COMMENT '组织编号',
  `base_or_dept_short_name` varchar(255) DEFAULT NULL COMMENT '组织简称',
  `establish_date` datetime DEFAULT NULL COMMENT '成立时间',
  `effective_date` datetime DEFAULT NULL COMMENT '生效时间',
  `business_unit` varchar(255) DEFAULT NULL COMMENT '业务单元',
  `level_id` bigint(20) DEFAULT NULL COMMENT '层级',
  `address` varchar(255) DEFAULT NULL COMMENT '地点',
  `is_fictitious` int(11) DEFAULT NULL COMMENT '是否虚拟部门',
  `dept_duty` varchar(255) DEFAULT NULL COMMENT '部门职责',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_org` int(11) DEFAULT NULL COMMENT '-1:总公司，0:基地，1:部门',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_update_by` datetime DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织架构表';

-- ----------------------------
-- Table structure for org_copy2
-- ----------------------------
DROP TABLE IF EXISTS `org_copy2`;
CREATE TABLE `org_copy2` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织架构id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(255) DEFAULT NULL COMMENT '上级id',
  `function_type` bigint(20) DEFAULT NULL COMMENT '职能属性',
  `base_or_dept_name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `base_or_dept_code` varchar(255) DEFAULT NULL COMMENT '组织编号',
  `base_or_dept_short_name` varchar(255) DEFAULT NULL COMMENT '组织简称',
  `establish_date` datetime DEFAULT NULL COMMENT '成立时间',
  `effective_date` datetime DEFAULT NULL COMMENT '生效时间',
  `business_unit` varchar(255) DEFAULT NULL COMMENT '业务单元',
  `level_id` bigint(20) DEFAULT NULL COMMENT '层级',
  `address` varchar(255) DEFAULT NULL COMMENT '地点',
  `is_fictitious` int(11) DEFAULT NULL COMMENT '是否虚拟部门',
  `dept_duty` varchar(255) DEFAULT NULL COMMENT '部门职责',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_org` int(11) DEFAULT NULL COMMENT '-1:总公司，0:基地，1:部门',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_update_by` datetime DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织架构表';

-- ----------------------------
-- Table structure for org_grade
-- ----------------------------
DROP TABLE IF EXISTS `org_grade`;
CREATE TABLE `org_grade` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '职等id',
  `grade_name` varchar(255) DEFAULT NULL COMMENT '职等名称/赋值名称',
  `grade_desc` varchar(255) DEFAULT NULL COMMENT '职等/赋值名称描述',
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `post_assignment` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职等/赋值名称表';

-- ----------------------------
-- Records of org_grade
-- ----------------------------
BEGIN;
INSERT INTO `org_grade` VALUES (42, '经理', '经理职位', 0, '=', 110, '2018-09-18 10:23:37', 200, '2018-11-14 11:01:00');
INSERT INTO `org_grade` VALUES (43, '111', '111', 1, '-', 111, '2018-09-18 10:23:39', NULL, '2018-09-18 10:23:39');
INSERT INTO `org_grade` VALUES (44, '13', '13', 1, '=', 112, '2018-09-18 10:23:41', NULL, '2018-09-18 10:23:41');
INSERT INTO `org_grade` VALUES (45, '副总经理', '副总经理职位', 0, '+', 113, '2018-09-18 10:23:44', 200, '2018-11-14 13:46:59');
INSERT INTO `org_grade` VALUES (46, '五', '职等五', 0, '+', 114, '2018-09-18 10:23:46', NULL, '2018-09-18 10:23:46');
INSERT INTO `org_grade` VALUES (47, '总经理', '职等六', 0, '=', 115, '2018-09-18 10:23:49', NULL, '2018-09-18 10:23:49');
COMMIT;

-- ----------------------------
-- Table structure for org_info
-- ----------------------------
DROP TABLE IF EXISTS `org_info`;
CREATE TABLE `org_info` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织副表id',
  `org_id` int(11) DEFAULT NULL COMMENT '所属组织id',
  `report_latitude` varchar(255) DEFAULT NULL COMMENT 'reportLatitude',
  `report_superior` varchar(255) DEFAULT NULL COMMENT 'reportSuperior',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系方式-联系电话',
  `contact_facsimile` varchar(255) DEFAULT NULL COMMENT '联系方式-传真',
  `contact_zipcode` varchar(255) DEFAULT NULL COMMENT '联系方式-邮编',
  `contact_url` varchar(255) DEFAULT NULL COMMENT '联系方式-网址',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系方式-地址',
  `leader_no` varchar(50) DEFAULT NULL COMMENT '总负责人-工号',
  `leader_name` varchar(255) DEFAULT NULL COMMENT '总负责人-姓名',
  `leader_phone` varchar(255) DEFAULT NULL COMMENT '总负责人-联系方式',
  `is_valid` int(11) DEFAULT '0' COMMENT '是否有效 0:有效,1:无效',
  `dept_num` varchar(255) DEFAULT '0' COMMENT '编制人数',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=332 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织架构副表';

-- ----------------------------
-- Records of org_info
-- ----------------------------
BEGIN;
INSERT INTO `org_info` VALUES (5, 1, '行政单位', '无上级', NULL, NULL, NULL, 'www.baidu.com', NULL, 'BJJD00004', '李四2', '13333333333', 0, '10070', 0);
INSERT INTO `org_info` VALUES (6, 14, NULL, '晶澳太阳能股份有限公司', NULL, NULL, NULL, 'www.baidu.com', NULL, '1', 'test', '13333333333', 0, '200', 1);
INSERT INTO `org_info` VALUES (7, 16, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '100', 1);
INSERT INTO `org_info` VALUES (8, 17, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '100', 1);
INSERT INTO `org_info` VALUES (9, 19, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '100', 1);
INSERT INTO `org_info` VALUES (10, 20, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (11, 21, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '100', 1);
INSERT INTO `org_info` VALUES (12, 22, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (13, 23, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (14, 24, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (16, 26, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (17, 27, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (18, 28, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (19, 29, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (20, 30, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (21, 31, NULL, NULL, NULL, NULL, NULL, 'www.baidu.com', NULL, '123123', 'king', '13333333333', 0, '50', 1);
INSERT INTO `org_info` VALUES (23, 33, '1', '1', NULL, NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (26, 36, '1', '1', NULL, NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (27, 37, '1234', '1234', NULL, NULL, NULL, NULL, NULL, NULL, '1234', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (28, 38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1234', NULL, NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (29, 39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (34, 44, NULL, '宁普晶澳', NULL, NULL, NULL, NULL, NULL, '6', '6', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (35, 45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (46, 56, '1', '1', NULL, NULL, NULL, NULL, NULL, '1', '1', NULL, 1, '1', 1);
INSERT INTO `org_info` VALUES (47, 57, '1', '1', NULL, NULL, NULL, NULL, NULL, '1', '1', NULL, 1, '1', 1);
INSERT INTO `org_info` VALUES (48, 58, '2', '2', NULL, NULL, NULL, NULL, NULL, '2', '2', NULL, 2, '2', 1);
INSERT INTO `org_info` VALUES (49, 59, '3', '3', NULL, NULL, NULL, NULL, NULL, '3', '3', NULL, 3, '3', 1);
INSERT INTO `org_info` VALUES (50, 60, '4', '4', NULL, NULL, NULL, NULL, NULL, '4', '4', NULL, 4, '4', 1);
INSERT INTO `org_info` VALUES (51, 61, '5', '5', NULL, NULL, NULL, NULL, NULL, '50', '50', NULL, 5, '50', 1);
INSERT INTO `org_info` VALUES (52, 62, NULL, '1', NULL, NULL, NULL, NULL, NULL, '10', '10', NULL, 1, '10', 1);
INSERT INTO `org_info` VALUES (53, 63, NULL, '4', NULL, NULL, NULL, NULL, NULL, '40', '40', NULL, 0, '40', 0);
INSERT INTO `org_info` VALUES (55, 64, NULL, '11', NULL, NULL, NULL, NULL, NULL, '1', 'test', NULL, 1, '1', 1);
INSERT INTO `org_info` VALUES (60, 69, NULL, '宁普晶澳子部门1', '110110110', NULL, NULL, NULL, NULL, '1', 'test', '11111111', 0, '99', 1);
INSERT INTO `org_info` VALUES (61, 70, NULL, NULL, '120120120', NULL, NULL, NULL, NULL, '8', '8', '120120120', 0, '98', 1);
INSERT INTO `org_info` VALUES (62, 71, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '6', '6', NULL, 0, '53', 1);
INSERT INTO `org_info` VALUES (63, 72, NULL, NULL, '1111', '1111', '1111', '1', '111', '8', '8', '11111', 0, '20', 1);
INSERT INTO `org_info` VALUES (64, 73, NULL, '123', NULL, NULL, NULL, NULL, NULL, '6', '6', NULL, 1, '1', 1);
INSERT INTO `org_info` VALUES (65, 74, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '6', '6', NULL, 1, '1', 1);
INSERT INTO `org_info` VALUES (66, 75, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (67, 76, NULL, '无上级', NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (68, 77, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '8', '8', NULL, 0, '1230', 1);
INSERT INTO `org_info` VALUES (69, 78, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '8', '8', NULL, 0, '1230', 1);
INSERT INTO `org_info` VALUES (70, 79, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '7', '7', NULL, 0, '1230', 1);
INSERT INTO `org_info` VALUES (71, 80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, 0, '1', 1);
INSERT INTO `org_info` VALUES (72, 81, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '1', 1);
INSERT INTO `org_info` VALUES (73, 82, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (74, 83, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (75, 84, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123456789123456789', '大师傅1', NULL, NULL, '123465', 0);
INSERT INTO `org_info` VALUES (76, 85, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 1);
INSERT INTO `org_info` VALUES (77, 86, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123456789123456789', '大师傅1', NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (78, 87, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (79, 88, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (80, 89, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '23', 0);
INSERT INTO `org_info` VALUES (81, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '16', 1);
INSERT INTO `org_info` VALUES (82, 91, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '56', 0);
INSERT INTO `org_info` VALUES (83, 92, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123456789123456789', '大师傅1', NULL, NULL, '112', 1);
INSERT INTO `org_info` VALUES (84, 93, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (85, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '123', 1);
INSERT INTO `org_info` VALUES (86, 95, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '56', 0);
INSERT INTO `org_info` VALUES (87, 96, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58', 1);
INSERT INTO `org_info` VALUES (88, 97, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '46', 1);
INSERT INTO `org_info` VALUES (89, 98, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (90, 99, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '111', 1);
INSERT INTO `org_info` VALUES (91, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (92, 101, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2345', 1);
INSERT INTO `org_info` VALUES (93, 102, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1236', 1);
INSERT INTO `org_info` VALUES (94, 103, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '555', 1);
INSERT INTO `org_info` VALUES (95, 104, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (96, 105, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '56', 1);
INSERT INTO `org_info` VALUES (97, 106, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123456789123456789', '大师傅1', NULL, NULL, '56', 1);
INSERT INTO `org_info` VALUES (98, 107, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '100', 1);
INSERT INTO `org_info` VALUES (99, 108, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123456789123456789', '大师傅1', NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (100, 109, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (101, 110, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '1001', 1);
INSERT INTO `org_info` VALUES (102, 111, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (103, 112, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (104, 113, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (105, 114, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (106, 115, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (107, 116, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (108, 117, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '15', 1);
INSERT INTO `org_info` VALUES (109, 118, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, 0, '1001', 1);
INSERT INTO `org_info` VALUES (110, 119, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, 0, '1234', 1);
INSERT INTO `org_info` VALUES (111, 120, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, 0, '1235', 1);
INSERT INTO `org_info` VALUES (112, 121, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (113, 122, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (114, 123, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (115, 124, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (116, 125, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '100', 0);
INSERT INTO `org_info` VALUES (117, 126, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, 0, '1234', 0);
INSERT INTO `org_info` VALUES (118, 127, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, 0, '1235', 1);
INSERT INTO `org_info` VALUES (119, 128, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, 0, '1569', 1);
INSERT INTO `org_info` VALUES (120, 129, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (121, 130, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '531', 1);
INSERT INTO `org_info` VALUES (122, 131, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (123, 132, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (124, 133, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (125, 134, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '6500', 0);
INSERT INTO `org_info` VALUES (126, 135, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (127, 136, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '12', 0);
INSERT INTO `org_info` VALUES (128, 137, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '23', 0);
INSERT INTO `org_info` VALUES (129, 138, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '333', 0);
INSERT INTO `org_info` VALUES (130, 139, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '34', 0);
INSERT INTO `org_info` VALUES (131, 140, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (132, 141, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (133, 142, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '34', 0);
INSERT INTO `org_info` VALUES (134, 143, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '2220', 1);
INSERT INTO `org_info` VALUES (135, 144, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '100', 0);
INSERT INTO `org_info` VALUES (136, 145, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (137, 146, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (138, 147, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '12', 1);
INSERT INTO `org_info` VALUES (139, 148, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '34', 0);
INSERT INTO `org_info` VALUES (140, 149, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅1', NULL, NULL, '34', 0);
INSERT INTO `org_info` VALUES (141, 150, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '345', 0);
INSERT INTO `org_info` VALUES (142, 151, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '53', 1);
INSERT INTO `org_info` VALUES (143, 152, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅132434', NULL, NULL, '45', 1);
INSERT INTO `org_info` VALUES (144, 153, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (145, 154, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '1006', 1);
INSERT INTO `org_info` VALUES (146, 155, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10170', 0);
INSERT INTO `org_info` VALUES (147, 156, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李四', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (148, 157, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李四', NULL, 0, '140', 0);
INSERT INTO `org_info` VALUES (149, 158, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12345678', '大师傅132434', NULL, NULL, '140', 0);
INSERT INTO `org_info` VALUES (150, 159, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李四', NULL, NULL, '5', 1);
INSERT INTO `org_info` VALUES (151, 160, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (152, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '10070', 0);
INSERT INTO `org_info` VALUES (153, 162, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (154, 163, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (155, 164, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李三', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (156, 165, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '李三', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (157, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (158, 167, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (159, 168, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (160, 169, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, 0, '200', 0);
INSERT INTO `org_info` VALUES (161, 170, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (162, 171, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (163, 172, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (164, 173, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (165, 174, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (166, 175, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (167, 176, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '100', 0);
INSERT INTO `org_info` VALUES (168, 177, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (169, 178, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (170, 179, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (171, 180, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (172, 181, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (173, 182, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '100', 1);
INSERT INTO `org_info` VALUES (174, 183, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (175, 184, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (176, 185, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'test', NULL, NULL, '100', 1);
INSERT INTO `org_info` VALUES (177, 186, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'test', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (178, 187, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', 'test1', NULL, NULL, '1110', 0);
INSERT INTO `org_info` VALUES (179, 188, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '李三', NULL, NULL, '100', 1);
INSERT INTO `org_info` VALUES (180, 189, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '李三', NULL, NULL, '10', 1);
INSERT INTO `org_info` VALUES (181, 190, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '1110', 0);
INSERT INTO `org_info` VALUES (182, 191, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00018', 'test', NULL, NULL, '1110', 0);
INSERT INTO `org_info` VALUES (183, 192, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李三', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (184, 193, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (185, 194, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00021', '李三', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (186, 195, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (187, 196, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222233', 'king', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (188, 197, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00019', '320124191919191912', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (189, 198, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00023', '李四', NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (190, 199, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00020', NULL, NULL, 0, '0', 0);
INSERT INTO `org_info` VALUES (191, 200, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, 0, '110', 0);
INSERT INTO `org_info` VALUES (192, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '董事', NULL, 0, '0', 0);
INSERT INTO `org_info` VALUES (193, 202, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ASZ00022', '李四', NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (194, 203, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (195, 204, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (196, 205, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (197, 206, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (198, 207, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (199, 208, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (200, 209, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (201, 210, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (202, 211, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (203, 212, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (204, 213, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (205, 214, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (206, 215, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (207, 216, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (208, 217, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (209, 218, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (210, 219, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (211, 220, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (212, 221, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (213, 222, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (214, 223, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (215, 224, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (216, 225, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (217, 226, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (218, 227, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (219, 228, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (220, 229, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (221, 230, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (222, 231, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (223, 232, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (224, 233, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (225, 234, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (226, 235, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (227, 236, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (228, 237, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (229, 238, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (230, 239, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (231, 240, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (232, 241, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (233, 242, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (234, 243, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (235, 244, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (236, 245, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (237, 246, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (238, 247, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (239, 248, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (240, 249, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (241, 250, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (242, 251, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (243, 252, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (244, 253, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (245, 254, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (246, 255, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (247, 256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (248, 257, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (249, 258, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (250, 259, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (251, 260, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (252, 261, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (253, 262, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (254, 263, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (255, 264, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (256, 265, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (257, 266, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (258, 267, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (259, 268, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (260, 269, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (261, 270, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 1);
INSERT INTO `org_info` VALUES (262, 271, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (263, 272, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (264, 273, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (265, 274, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (266, 275, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (267, 276, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (268, 277, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (269, 278, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (270, 279, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (271, 280, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (272, 281, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (273, 282, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (274, 283, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '10', 0);
INSERT INTO `org_info` VALUES (275, 284, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (276, 285, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (277, 286, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (278, 287, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (279, 288, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (280, 289, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (281, 290, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (282, 291, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (283, 292, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (284, 293, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (285, 294, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (286, 295, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (287, 296, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (288, 297, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (289, 298, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (290, 299, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (291, 300, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (292, 301, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (293, 302, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (294, 303, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (295, 304, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (296, 305, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (297, 306, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (298, 307, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (299, 308, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (300, 309, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (301, 310, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (302, 311, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (303, 312, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (304, 313, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (305, 314, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0);
INSERT INTO `org_info` VALUES (306, 315, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (307, 316, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (308, 317, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (309, 3, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0);
INSERT INTO `org_info` VALUES (310, 2, '309', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', 0);
INSERT INTO `org_info` VALUES (311, 319, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (312, 320, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (313, 321, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (314, 322, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (315, 323, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (316, 324, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (317, 325, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (318, 326, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '201', 0);
INSERT INTO `org_info` VALUES (319, 327, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 0);
INSERT INTO `org_info` VALUES (320, 328, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '101', 0);
INSERT INTO `org_info` VALUES (321, 329, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', 0);
INSERT INTO `org_info` VALUES (322, 330, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '101', 0);
INSERT INTO `org_info` VALUES (323, 331, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (324, 332, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
INSERT INTO `org_info` VALUES (325, 333, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (328, 336, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (329, 337, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (330, 338, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1);
INSERT INTO `org_info` VALUES (331, 339, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 0);
COMMIT;

-- ----------------------------
-- Table structure for org_position
-- ----------------------------
DROP TABLE IF EXISTS `org_position`;
CREATE TABLE `org_position` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '职位id',
  `position_type` bigint(255) DEFAULT NULL COMMENT '职衔类型',
  `position_name` varchar(255) DEFAULT NULL COMMENT '职衔名称',
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `staff_size` int(11) DEFAULT NULL COMMENT '编制人数',
  `file_desc` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职衔表';

-- ----------------------------
-- Records of org_position
-- ----------------------------
BEGIN;
INSERT INTO `org_position` VALUES (2, 3, '职位14555', 0, 0, '', 110, '2018-09-18 10:51:54', NULL, '2018-09-18 10:51:54');
INSERT INTO `org_position` VALUES (3, 2, '职位2', 1, 20, NULL, 110, '2018-09-18 10:51:54', NULL, '2018-09-18 10:51:54');
INSERT INTO `org_position` VALUES (13, 3, '职位3', 1, 50, '', 110, '2018-09-18 10:51:55', NULL, '2018-09-18 10:51:55');
INSERT INTO `org_position` VALUES (14, 4, '444', 0, 1201, '', 110, '2018-09-18 10:51:55', NULL, '2018-09-18 10:51:55');
INSERT INTO `org_position` VALUES (15, 3, '5555', 1, 999, '', 110, '2018-09-18 10:51:56', NULL, '2018-09-18 10:51:56');
INSERT INTO `org_position` VALUES (16, 2, '职位2', 0, 99999, '', 110, '2018-09-18 10:51:56', 200, '2019-01-09 16:46:45');
INSERT INTO `org_position` VALUES (17, 2, '444', 1, NULL, 'group1/M00/00/01/rB0PK1t0VWCADqoNAAAktBeBGWo726.sql', 110, '2018-09-18 10:51:56', NULL, '2018-09-18 10:51:56');
INSERT INTO `org_position` VALUES (18, 3, '222', 0, 0, 'group1/M00/00/01/rB0PK1t1d9WAHy_nAAJF9YDy0iY550.sql', 110, '2018-09-18 10:51:57', NULL, '2018-09-18 10:51:57');
INSERT INTO `org_position` VALUES (19, 3, '1111', 0, 0, '', 110, '2018-09-18 10:51:58', NULL, '2018-09-18 10:51:58');
INSERT INTO `org_position` VALUES (20, 3, '22222', 0, 0, '', 110, '2018-09-18 10:51:58', NULL, '2018-09-18 10:51:58');
INSERT INTO `org_position` VALUES (21, 4, '756', 0, 0, '', 110, '2018-09-18 10:51:59', NULL, '2018-09-18 10:51:59');
INSERT INTO `org_position` VALUES (22, 3, '768', 0, 0, '', 0, '2018-09-18 13:57:55', NULL, '2018-09-18 13:57:55');
INSERT INTO `org_position` VALUES (23, 3, '573737', 0, 2, '', 0, '2018-09-27 09:49:21', 0, '2018-09-27 09:50:23');
INSERT INTO `org_position` VALUES (24, 3, '78375', 0, 0, '', 0, '2018-09-18 13:57:53', NULL, '2018-09-18 13:57:53');
INSERT INTO `org_position` VALUES (25, 4, '735571', 0, 0, '', 0, '2018-09-18 13:57:52', NULL, '2018-09-18 13:57:52');
INSERT INTO `org_position` VALUES (26, 3, '22245', 0, 0, '', 0, '2018-09-18 13:57:52', NULL, '2018-09-18 13:57:52');
INSERT INTO `org_position` VALUES (27, 3, '222w3442', 0, 0, '', 200, '2018-11-23 12:19:58', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for org_position_type
-- ----------------------------
DROP TABLE IF EXISTS `org_position_type`;
CREATE TABLE `org_position_type` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位类别id',
  `type_name` varchar(255) DEFAULT NULL COMMENT '职衔类型名称',
  `type_desc` varchar(255) DEFAULT NULL COMMENT '职衔类型描述',
  `del_flag` bigint(11) DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职衔类别表';

-- ----------------------------
-- Records of org_position_type
-- ----------------------------
BEGIN;
INSERT INTO `org_position_type` VALUES (2, '管理类', '管理类别名称', 0, 110, '2018-09-18 10:59:36', NULL, '2018-09-18 10:59:36');
INSERT INTO `org_position_type` VALUES (3, '专业类', '专业类别名称', 0, 110, '2018-09-18 10:59:37', NULL, '2018-09-18 10:59:37');
INSERT INTO `org_position_type` VALUES (4, '机械类', '机械类别名称', 0, 110, '2018-09-18 10:59:37', NULL, '2018-09-18 10:59:37');
INSERT INTO `org_position_type` VALUES (5, '33', '33', 1, 110, '2018-09-18 10:59:38', NULL, '2018-09-18 10:59:38');
INSERT INTO `org_position_type` VALUES (6, '2', '11', 1, 110, '2018-09-18 10:59:39', NULL, '2018-09-18 10:59:39');
INSERT INTO `org_position_type` VALUES (7, '1', '1', 1, 0, '2018-09-18 12:30:34', NULL, '2018-09-18 12:30:34');
COMMIT;

-- ----------------------------
-- Table structure for org_rank
-- ----------------------------
DROP TABLE IF EXISTS `org_rank`;
CREATE TABLE `org_rank` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '职级id',
  `rank_name` varchar(255) DEFAULT NULL COMMENT '职级名称',
  `rank_desc` varchar(255) DEFAULT NULL COMMENT '职级描述',
  `del_flag` bigint(11) DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  `salary_max` double(20,2) DEFAULT NULL COMMENT '薪资上限',
  `salary_min` double(20,2) DEFAULT NULL COMMENT '薪资下限',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职级表';

-- ----------------------------
-- Records of org_rank
-- ----------------------------
BEGIN;
INSERT INTO `org_rank` VALUES (10, 'M6', '124', 1, 30005.00, 20006.00, 110, '2018-09-18 10:59:08', NULL, '2018-09-18 10:59:08');
INSERT INTO `org_rank` VALUES (11, 'M5', '12', 1, 400000.00, 30000.00, 110, '2018-09-18 10:59:08', NULL, '2018-09-18 10:59:08');
INSERT INTO `org_rank` VALUES (12, 'M7', '13', 0, 5800.66, 5000.55, 110, '2018-09-18 10:59:10', NULL, '2018-09-18 10:59:10');
INSERT INTO `org_rank` VALUES (13, 'M8', '14', 0, 1001110.11, 0.11, 110, '2018-09-18 10:59:10', NULL, '2018-09-18 10:59:10');
INSERT INTO `org_rank` VALUES (14, 'M9', '15', 0, 60000.00, 10000.00, 110, '2018-09-18 10:59:11', NULL, '2018-09-18 10:59:11');
INSERT INTO `org_rank` VALUES (15, 'M10', '1', 0, 1.00, 2.00, 110, '2018-09-18 10:59:12', NULL, '2018-09-18 10:59:12');
INSERT INTO `org_rank` VALUES (16, 'M11', '1', 0, 1.00, 2.00, 110, '2018-09-18 10:59:14', NULL, '2018-09-18 10:59:14');
INSERT INTO `org_rank` VALUES (17, '3', '3', 1, 50.00, 100.00, 110, '2018-09-18 10:59:15', NULL, '2018-09-18 10:59:15');
INSERT INTO `org_rank` VALUES (18, 'M99', 'M99', 0, 10.00, 20.00, 110, '2018-09-18 10:59:16', NULL, '2018-09-18 10:59:16');
INSERT INTO `org_rank` VALUES (19, 'M802', 'M802', 0, 123656.00, 16.00, 110, '2018-09-18 10:59:16', NULL, '2018-09-18 10:59:16');
INSERT INTO `org_rank` VALUES (20, 'M723', 'M723', 0, 231365.00, 1156.37, 110, '2018-09-18 10:59:17', NULL, '2018-09-18 10:59:17');
INSERT INTO `org_rank` VALUES (21, 'M756', 'M756', 0, 2.66, 0.00, 110, '2018-09-18 10:59:17', NULL, '2018-09-18 10:59:17');
INSERT INTO `org_rank` VALUES (22, 'M436', 'M436', 0, 3.70, 2.65, 110, '2018-09-18 10:59:18', NULL, '2018-09-18 10:59:18');
INSERT INTO `org_rank` VALUES (23, 'M8036', 'M8036', 0, 3.65, 3.22, 110, '2018-09-18 10:59:19', NULL, '2018-09-18 10:59:19');
INSERT INTO `org_rank` VALUES (24, 'M416', 'M416', 0, 13.30, 13.30, 110, '2018-09-18 10:59:19', NULL, '2018-09-18 10:59:19');
INSERT INTO `org_rank` VALUES (25, 'M460', 'M460', 0, 13.31, 13.30, 110, '2018-09-18 10:59:20', NULL, '2018-09-18 10:59:20');
INSERT INTO `org_rank` VALUES (26, 'M88', 'M88', 0, 100000.22, 52000.22, 110, '2018-09-18 10:59:20', NULL, '2018-09-18 10:59:20');
INSERT INTO `org_rank` VALUES (27, 'M809', 'M863', 0, 100000.32, 50000.32, 110, '2018-09-18 10:59:21', NULL, '2018-09-18 10:59:21');
COMMIT;

-- ----------------------------
-- Table structure for org_ref_grade_rank
-- ----------------------------
DROP TABLE IF EXISTS `org_ref_grade_rank`;
CREATE TABLE `org_ref_grade_rank` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '职等职级关系表id',
  `grade_id` bigint(20) DEFAULT NULL COMMENT '职等id',
  `rank_id` bigint(20) DEFAULT NULL COMMENT '职级id',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  PRIMARY KEY (`row_id`) USING BTREE,
  UNIQUE KEY `uniq_gradeId_rankId` (`grade_id`,`rank_id`) USING BTREE COMMENT 'grade_id和rank_id联合唯一'
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职等职级关系表';

-- ----------------------------
-- Records of org_ref_grade_rank
-- ----------------------------
BEGIN;
INSERT INTO `org_ref_grade_rank` VALUES (3, 43, 11, 0);
INSERT INTO `org_ref_grade_rank` VALUES (73, 47, 12, 0);
INSERT INTO `org_ref_grade_rank` VALUES (74, 47, 14, 0);
INSERT INTO `org_ref_grade_rank` VALUES (75, 47, 15, 0);
INSERT INTO `org_ref_grade_rank` VALUES (76, 47, 16, 0);
INSERT INTO `org_ref_grade_rank` VALUES (87, 46, 10, 0);
INSERT INTO `org_ref_grade_rank` VALUES (88, 46, 12, 0);
INSERT INTO `org_ref_grade_rank` VALUES (103, 42, 13, 0);
INSERT INTO `org_ref_grade_rank` VALUES (104, 42, 14, 0);
INSERT INTO `org_ref_grade_rank` VALUES (105, 42, 15, 0);
INSERT INTO `org_ref_grade_rank` VALUES (106, 42, 16, 0);
INSERT INTO `org_ref_grade_rank` VALUES (107, 45, 10, 0);
INSERT INTO `org_ref_grade_rank` VALUES (108, 45, 12, 0);
INSERT INTO `org_ref_grade_rank` VALUES (109, 45, 14, 0);
COMMIT;

-- ----------------------------
-- Table structure for org_ref_position_grade
-- ----------------------------
DROP TABLE IF EXISTS `org_ref_position_grade`;
CREATE TABLE `org_ref_position_grade` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '职位职等关系表id',
  `position_id` bigint(20) DEFAULT NULL COMMENT '职衔id',
  `grade_id` bigint(20) DEFAULT NULL COMMENT '职等id',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职衔职等关系表';

-- ----------------------------
-- Records of org_ref_position_grade
-- ----------------------------
BEGIN;
INSERT INTO `org_ref_position_grade` VALUES (3, 3, 43, 0);
INSERT INTO `org_ref_position_grade` VALUES (105, 17, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (118, 18, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (119, 18, 46, 0);
INSERT INTO `org_ref_position_grade` VALUES (133, 14, 42, 0);
INSERT INTO `org_ref_position_grade` VALUES (134, 14, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (141, 2, 42, 0);
INSERT INTO `org_ref_position_grade` VALUES (142, 2, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (144, 20, 42, 0);
INSERT INTO `org_ref_position_grade` VALUES (145, 19, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (146, 22, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (147, 21, 47, 0);
INSERT INTO `org_ref_position_grade` VALUES (149, 24, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (150, 25, 46, 0);
INSERT INTO `org_ref_position_grade` VALUES (151, 26, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (152, 23, 46, 0);
INSERT INTO `org_ref_position_grade` VALUES (153, 27, 45, 0);
INSERT INTO `org_ref_position_grade` VALUES (154, 16, 46, 0);
COMMIT;

-- ----------------------------
-- Table structure for org_ref_position_rank
-- ----------------------------
DROP TABLE IF EXISTS `org_ref_position_rank`;
CREATE TABLE `org_ref_position_rank` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位职级关系表id',
  `position_id` int(11) DEFAULT NULL COMMENT '职位id',
  `rank_id` int(11) DEFAULT NULL COMMENT '职级id',
  `del_flag` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='职位职级关系表';

-- ----------------------------
-- Records of org_ref_position_rank
-- ----------------------------
BEGIN;
INSERT INTO `org_ref_position_rank` VALUES (78, 17, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (91, 18, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (92, 18, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (109, 14, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (122, 2, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (123, 2, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (124, 2, 13, '0');
INSERT INTO `org_ref_position_rank` VALUES (125, 2, 15, '0');
INSERT INTO `org_ref_position_rank` VALUES (127, 20, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (128, 19, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (129, 22, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (130, 21, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (132, 24, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (133, 25, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (134, 26, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (135, 23, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (136, 27, 14, '0');
INSERT INTO `org_ref_position_rank` VALUES (137, 27, 12, '0');
INSERT INTO `org_ref_position_rank` VALUES (138, 16, 12, '0');
COMMIT;

-- ----------------------------
-- Table structure for ref_role_column_filter
-- ----------------------------
DROP TABLE IF EXISTS `ref_role_column_filter`;
CREATE TABLE `ref_role_column_filter` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id_` bigint(20) DEFAULT NULL COMMENT '角色id',
  `column_key_` varchar(32) DEFAULT NULL COMMENT '业务模块名',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色与字段关系';

-- ----------------------------
-- Records of ref_role_column_filter
-- ----------------------------
BEGIN;
INSERT INTO `ref_role_column_filter` VALUES (21, 1, 'product_price');
INSERT INTO `ref_role_column_filter` VALUES (23, 16, 'product_price');
COMMIT;

-- ----------------------------
-- Table structure for ref_role_data_filter
-- ----------------------------
DROP TABLE IF EXISTS `ref_role_data_filter`;
CREATE TABLE `ref_role_data_filter` (
  `id_` bigint(25) NOT NULL AUTO_INCREMENT,
  `role_id_` bigint(20) DEFAULT NULL,
  `auth_module_` varchar(32) DEFAULT NULL,
  `key_` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=416 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与数据权限关系';

-- ----------------------------
-- Records of ref_role_data_filter
-- ----------------------------
BEGIN;
INSERT INTO `ref_role_data_filter` VALUES (320, 1, 'sale_order', 'self');
INSERT INTO `ref_role_data_filter` VALUES (321, 1, 'customer', 'global');
INSERT INTO `ref_role_data_filter` VALUES (323, 1, 'supplier', 'global');
INSERT INTO `ref_role_data_filter` VALUES (324, 1, 'storage_admin', 'global');
INSERT INTO `ref_role_data_filter` VALUES (325, 1, 'tag', '1');
INSERT INTO `ref_role_data_filter` VALUES (326, 1, 'tag', '2');
INSERT INTO `ref_role_data_filter` VALUES (327, 1, 'document_level', 'global');
INSERT INTO `ref_role_data_filter` VALUES (328, 1, 'document_level', '1');
INSERT INTO `ref_role_data_filter` VALUES (329, 1, 'document_level', '7');
INSERT INTO `ref_role_data_filter` VALUES (330, 1, 'document_level', '8');
INSERT INTO `ref_role_data_filter` VALUES (331, 1, 'document_level', '4');
INSERT INTO `ref_role_data_filter` VALUES (332, 1, 'document_level', '1');
INSERT INTO `ref_role_data_filter` VALUES (333, 1, 'document_level', '7');
INSERT INTO `ref_role_data_filter` VALUES (334, 1, 'document_level', '8');
INSERT INTO `ref_role_data_filter` VALUES (352, 63, 'sale_order', 'global');
INSERT INTO `ref_role_data_filter` VALUES (353, 16, 'purchase_order', 'global');
INSERT INTO `ref_role_data_filter` VALUES (354, 16, 'supplier', 'global');
INSERT INTO `ref_role_data_filter` VALUES (355, 16, 'tag', 'global');
INSERT INTO `ref_role_data_filter` VALUES (362, 15, 'customer', 'global');
INSERT INTO `ref_role_data_filter` VALUES (363, 15, 'storage_admin', 'global');
INSERT INTO `ref_role_data_filter` VALUES (406, 71, 'sale_order', 'global');
INSERT INTO `ref_role_data_filter` VALUES (407, 71, 'customer', 'global');
INSERT INTO `ref_role_data_filter` VALUES (408, 71, 'purchase_order', 'global');
INSERT INTO `ref_role_data_filter` VALUES (409, 71, 'supplier', 'global');
INSERT INTO `ref_role_data_filter` VALUES (410, 71, 'storage_admin', 'global');
INSERT INTO `ref_role_data_filter` VALUES (411, 71, 'tag', 'global');
INSERT INTO `ref_role_data_filter` VALUES (412, 71, 'document_level', 'global');
INSERT INTO `ref_role_data_filter` VALUES (413, 14, 'sale_order', 'global');
INSERT INTO `ref_role_data_filter` VALUES (414, 14, 'customer', 'global');
INSERT INTO `ref_role_data_filter` VALUES (415, 14, 'document_level', 'global');
COMMIT;

-- ----------------------------
-- Table structure for ref_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `ref_role_resource`;
CREATE TABLE `ref_role_resource` (
  `id_` bigint(25) NOT NULL AUTO_INCREMENT,
  `role_id_` bigint(20) NOT NULL COMMENT '角色id',
  `resource_id_` bigint(25) NOT NULL COMMENT '资源id',
  `created_by_` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time_` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色与资源关系';

-- ----------------------------
-- Records of ref_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `ref_role_resource` VALUES (422, 1, 1, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (423, 1, 2, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (424, 1, 101, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (425, 1, 102, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (426, 1, 103, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (427, 1, 104, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (428, 1, 201, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (429, 1, 202, 0, '2019-01-16');
INSERT INTO `ref_role_resource` VALUES (789, 55, 901, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (790, 55, 902, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (791, 55, 80903, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (792, 55, 80902, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (793, 55, 8, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (794, 55, 9, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (795, 55, 10, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (796, 55, 11, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (797, 55, 100301, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (798, 55, 1101, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (799, 55, 1102, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (800, 55, 140109, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (801, 55, 90201, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (802, 55, 90203, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (803, 55, 90202, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (804, 55, 90204, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (805, 55, 801, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (806, 55, 802, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (807, 55, 803, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (808, 55, 804, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (809, 55, 805, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (810, 55, 806, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (811, 55, 807, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (812, 55, 808, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (813, 55, 81001, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (814, 55, 809, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (815, 55, 1001, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (816, 55, 810, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (817, 55, 1002, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (818, 55, 81003, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (819, 55, 1003, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (820, 55, 81004, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (821, 55, 90101, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (822, 55, 90103, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (823, 55, 90102, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (824, 55, 90105, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (825, 55, 90104, 0, '2019-01-30');
INSERT INTO `ref_role_resource` VALUES (830, 64, 10101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (831, 64, 102, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (832, 63, 10101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (833, 16, 10101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (834, 16, 102, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (835, 59, 2, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (836, 59, 3, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (837, 59, 4, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (838, 59, 5, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (839, 59, 6, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (840, 59, 80903, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (841, 59, 7, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (842, 59, 80902, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (843, 59, 8, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (844, 59, 9, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (845, 59, 10, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (846, 59, 11, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (847, 59, 12, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (848, 59, 801, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (849, 59, 802, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (850, 59, 803, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (851, 59, 804, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (852, 59, 805, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (853, 59, 30501, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (854, 59, 806, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (855, 59, 30502, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (856, 59, 807, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (857, 59, 30503, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (858, 59, 808, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (859, 59, 809, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (860, 59, 810, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (861, 59, 301, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (862, 59, 302, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (863, 59, 304, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (864, 59, 305, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (865, 59, 306, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (866, 59, 1101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (867, 59, 20301, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (868, 59, 1102, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (869, 59, 20302, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (870, 59, 90201, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (871, 59, 90203, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (872, 59, 603, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (873, 59, 90202, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (874, 59, 604, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (875, 59, 605, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (876, 59, 90204, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (877, 59, 606, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (878, 59, 608, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (879, 59, 609, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (880, 59, 610, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (881, 59, 612, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (882, 59, 102, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (883, 59, 614, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (884, 59, 103, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (885, 59, 615, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (886, 59, 104, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (887, 59, 81001, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (888, 59, 81005, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (889, 59, 81006, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (890, 59, 50801, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (891, 59, 50802, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (892, 59, 50803, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (893, 59, 10101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (894, 59, 8100603, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (895, 59, 8100602, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (896, 59, 8100601, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (897, 59, 901, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (898, 59, 902, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (899, 59, 8100604, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (900, 59, 401, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (901, 59, 402, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (902, 59, 403, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (903, 59, 404, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (904, 59, 1201, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (905, 59, 1202, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (906, 59, 1203, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (907, 59, 1204, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (908, 59, 1205, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (909, 59, 1206, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (910, 59, 60601, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (911, 59, 60602, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (912, 59, 701, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (913, 59, 702, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (914, 59, 703, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (915, 59, 704, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (916, 59, 705, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (917, 59, 706, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (918, 59, 70601, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (919, 59, 201, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (920, 59, 202, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (921, 59, 70603, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (922, 59, 203, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (923, 59, 204, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (924, 59, 70605, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (925, 59, 100301, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (926, 59, 70604, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (927, 59, 40401, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (928, 59, 40402, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (929, 59, 40403, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (930, 59, 61401, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (931, 59, 61402, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (932, 59, 61403, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (933, 59, 61404, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (934, 59, 61408, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (935, 59, 1001, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (936, 59, 1002, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (937, 59, 1003, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (938, 59, 60401, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (939, 59, 60402, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (940, 59, 60403, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (941, 59, 60404, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (942, 59, 90101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (943, 59, 501, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (944, 59, 502, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (945, 59, 90103, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (946, 59, 503, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (947, 59, 90102, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (948, 59, 504, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (949, 59, 90105, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (950, 59, 505, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (951, 59, 90104, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (952, 59, 506, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (953, 59, 507, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (954, 59, 508, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (955, 53, 5, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (956, 53, 102, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (957, 53, 103, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (958, 53, 104, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (959, 53, 50801, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (960, 53, 50802, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (961, 53, 50803, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (962, 53, 501, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (963, 53, 10101, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (964, 53, 502, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (965, 53, 503, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (966, 53, 504, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (967, 53, 505, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (968, 53, 506, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (969, 53, 507, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (970, 53, 508, 0, '2019-04-01');
INSERT INTO `ref_role_resource` VALUES (999, 15, 101, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1000, 15, 103, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1001, 15, 104, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1002, 15, 201, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1003, 15, 20302, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1004, 69, 1, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1005, 69, 101, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1006, 69, 10101, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1007, 69, 102, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1008, 69, 10102, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1009, 69, 103, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1010, 69, 104, 0, '2019-04-04');
INSERT INTO `ref_role_resource` VALUES (1014, 70, 101, 0, '2019-04-08');
INSERT INTO `ref_role_resource` VALUES (1076, 71, 13, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1077, 71, 301, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1078, 71, 5040001, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1079, 71, 302, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1080, 71, 2010001, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1081, 71, 3020001, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1082, 71, 1301, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1083, 71, 503, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1084, 71, 504, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1085, 71, 130105, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1086, 71, 7030001, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1087, 71, 3010001, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1088, 71, 5030001, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1089, 71, 703, 0, '2019-04-12');
INSERT INTO `ref_role_resource` VALUES (1090, 14, 704, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1091, 14, 1, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1092, 14, 705, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1093, 14, 2, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1094, 14, 706, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1095, 14, 20101, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1096, 14, 20102, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1097, 14, 7, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1098, 14, 70601, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1099, 14, 201, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1100, 14, 202, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1101, 14, 70603, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1102, 14, 203, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1103, 14, 204, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1104, 14, 70605, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1105, 14, 20301, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1106, 14, 70604, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1107, 14, 20302, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1108, 14, 2010001, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1109, 14, 7030001, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1110, 14, 101, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1111, 14, 102, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1112, 14, 103, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1113, 14, 104, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1114, 14, 10101, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1115, 14, 10102, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1116, 14, 701, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1117, 14, 702, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1118, 14, 703, 0, '2019-04-15');
INSERT INTO `ref_role_resource` VALUES (1119, 76, 1, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1120, 76, 101, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1121, 76, 10101, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1122, 76, 102, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1123, 76, 10102, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1124, 76, 103, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1125, 76, 104, 0, '2019-04-25');
INSERT INTO `ref_role_resource` VALUES (1126, 78, 1, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1127, 78, 2, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1128, 78, 3, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1129, 78, 4, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1130, 78, 5, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1131, 78, 6, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1132, 78, 80903, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1133, 78, 7, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1134, 78, 80902, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1135, 78, 8, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1136, 78, 9, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1137, 78, 9010304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1138, 78, 10, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1139, 78, 11, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1140, 78, 12, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1141, 78, 140302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1142, 78, 13, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1143, 78, 140303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1144, 78, 14, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1145, 78, 140301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1146, 78, 140306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1147, 78, 140304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1148, 78, 140305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1149, 78, 50201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1150, 78, 6060101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1151, 78, 50202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1152, 78, 50204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1153, 78, 50207, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1154, 78, 50208, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1155, 78, 50209, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1156, 78, 50210, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1157, 78, 50211, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1158, 78, 50212, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1159, 78, 130101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1160, 78, 130103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1161, 78, 130102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1162, 78, 130105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1163, 78, 130104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1164, 78, 130107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1165, 78, 130106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1166, 78, 14040301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1167, 78, 14010601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1168, 78, 10301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1169, 78, 61501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1170, 78, 10302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1171, 78, 61502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1172, 78, 10303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1173, 78, 61503, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1174, 78, 10304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1175, 78, 10305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1176, 78, 10306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1177, 78, 10307, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1178, 78, 10308, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1179, 78, 10309, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1180, 78, 1101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1181, 78, 1102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1182, 78, 14010501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1183, 78, 60501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1184, 78, 60504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1185, 78, 90201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1186, 78, 60505, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1187, 78, 60506, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1188, 78, 90203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1189, 78, 60507, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1190, 78, 90202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1191, 78, 60508, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1192, 78, 90204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1193, 78, 14040201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1194, 78, 5080109, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1195, 78, 5080108, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1196, 78, 5080105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1197, 78, 101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1198, 78, 5080104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1199, 78, 102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1200, 78, 5080107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1201, 78, 103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1202, 78, 5080106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1203, 78, 104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1204, 78, 81001, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1205, 78, 5080101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1206, 78, 9010401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1207, 78, 5080103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1208, 78, 9010403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1209, 78, 5080102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1210, 78, 9010402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1211, 78, 81005, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1212, 78, 9010404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1213, 78, 81006, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1214, 78, 140402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1215, 78, 140403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1216, 78, 140401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1217, 78, 50301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1218, 78, 7060502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1219, 78, 50302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1220, 78, 50303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1221, 78, 50304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1222, 78, 50305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1223, 78, 50307, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1224, 78, 50308, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1225, 78, 50309, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1226, 78, 9010201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1227, 78, 9010203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1228, 78, 9010202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1229, 78, 6140104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1230, 78, 9010204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1231, 78, 6140101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1232, 78, 6140103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1233, 78, 6140102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1234, 78, 5080301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1235, 78, 5080302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1236, 78, 40101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1237, 78, 40102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1238, 78, 40103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1239, 78, 40104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1240, 78, 40105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1241, 78, 40106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1242, 78, 40107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1243, 78, 1201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1244, 78, 1202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1245, 78, 1203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1246, 78, 14080101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1247, 78, 1204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1248, 78, 1205, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1249, 78, 1206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1250, 78, 60601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1251, 78, 60602, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1252, 78, 5080204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1253, 78, 5080201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1254, 78, 5080203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1255, 78, 5080202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1256, 78, 201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1257, 78, 202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1258, 78, 203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1259, 78, 204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1260, 78, 140502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1261, 78, 140501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1262, 78, 50401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1263, 78, 50402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1264, 78, 50403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1265, 78, 50404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1266, 78, 50405, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1267, 78, 50407, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1268, 78, 50408, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1269, 78, 50409, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1270, 78, 14040101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1271, 78, 9010301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1272, 78, 14010401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1273, 78, 6060201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1274, 78, 9010303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1275, 78, 9010302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1276, 78, 810060104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1277, 78, 40201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1278, 78, 40202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1279, 78, 40203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1280, 78, 14030301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1281, 78, 40204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1282, 78, 40205, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1283, 78, 810060101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1284, 78, 40206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1285, 78, 810060102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1286, 78, 40207, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1287, 78, 810060103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1288, 78, 40208, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1289, 78, 1301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1290, 78, 120102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1291, 78, 120104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1292, 78, 301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1293, 78, 302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1294, 78, 304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1295, 78, 305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1296, 78, 306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1297, 78, 140602, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1298, 78, 140603, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1299, 78, 140601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1300, 78, 140604, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1301, 78, 140605, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1302, 78, 50501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1303, 78, 50502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1304, 78, 50503, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1305, 78, 50504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1306, 78, 80201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1307, 78, 50505, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1308, 78, 50506, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1309, 78, 80203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1310, 78, 80202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1311, 78, 50508, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1312, 78, 80205, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1313, 78, 80204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1314, 78, 80207, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1315, 78, 80206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1316, 78, 80208, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1317, 78, 14010801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1318, 78, 40301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1319, 78, 40302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1320, 78, 40303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1321, 78, 40304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1322, 78, 40305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1323, 78, 6140204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1324, 78, 40306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1325, 78, 40307, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1326, 78, 6140206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1327, 78, 8100104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1328, 78, 40308, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1329, 78, 6140201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1330, 78, 40309, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1331, 78, 40310, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1332, 78, 6140203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1333, 78, 6140202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1334, 78, 1401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1335, 78, 1402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1336, 78, 1403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1337, 78, 1404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1338, 78, 8100103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1339, 78, 1405, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1340, 78, 8100102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1341, 78, 1406, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1342, 78, 8100101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1343, 78, 1407, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1344, 78, 1408, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1345, 78, 60801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1346, 78, 60802, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1347, 78, 60803, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1348, 78, 14070101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1349, 78, 60804, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1350, 78, 60805, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1351, 78, 120203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1352, 78, 120202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1353, 78, 120204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1354, 78, 401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1355, 78, 402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1356, 78, 403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1357, 78, 404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1358, 78, 30101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1359, 78, 4040104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1360, 78, 30102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1361, 78, 30103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1362, 78, 30104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1363, 78, 4040101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1364, 78, 14010701, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1365, 78, 30105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1366, 78, 30106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1367, 78, 4040103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1368, 78, 4040102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1369, 78, 140701, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1370, 78, 50601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1371, 78, 6140404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1372, 78, 50602, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1373, 78, 50603, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1374, 78, 50604, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1375, 78, 80301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1376, 78, 6140401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1377, 78, 50605, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1378, 78, 6140403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1379, 78, 6140402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1380, 78, 80305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1381, 78, 9010504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1382, 78, 6140312, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1383, 78, 1030601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1384, 78, 6140309, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1385, 78, 6140308, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1386, 78, 6140311, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1387, 78, 6140310, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1388, 78, 6140305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1389, 78, 9010501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1390, 78, 6140304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1391, 78, 6140307, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1392, 78, 9010503, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1393, 78, 6140306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1394, 78, 9010502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1395, 78, 6140301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1396, 78, 40401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1397, 78, 40402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1398, 78, 6140303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1399, 78, 40403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1400, 78, 6140302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1401, 78, 70101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1402, 78, 810060201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1403, 78, 810060202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1404, 78, 810060203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1405, 78, 810060204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1406, 78, 60901, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1407, 78, 60902, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1408, 78, 60903, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1409, 78, 60904, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1410, 78, 60905, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1411, 78, 120302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1412, 78, 120304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1413, 78, 120307, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1414, 78, 120306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1415, 78, 501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1416, 78, 120308, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1417, 78, 502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1418, 78, 503, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1419, 78, 504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1420, 78, 505, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1421, 78, 30201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1422, 78, 506, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1423, 78, 30202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1424, 78, 507, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1425, 78, 30203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1426, 78, 508, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1427, 78, 30204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1428, 78, 30205, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1429, 78, 30206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1430, 78, 140801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1431, 78, 2030104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1432, 78, 2030102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1433, 78, 2030103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1434, 78, 2030101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1435, 78, 50701, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1436, 78, 50703, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1437, 78, 50704, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1438, 78, 80401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1439, 78, 80403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1440, 78, 80402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1441, 78, 80405, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1442, 78, 110101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1443, 78, 80404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1444, 78, 110103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1445, 78, 110102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1446, 78, 110105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1447, 78, 14020301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1448, 78, 110104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1449, 78, 110107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1450, 78, 110106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1451, 78, 8090203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1452, 78, 14010101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1453, 78, 8090202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1454, 78, 8090201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1455, 78, 14010102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1456, 78, 8090204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1457, 78, 4040204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1458, 78, 4040201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1459, 78, 4040203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1460, 78, 4040202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1461, 78, 70201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1462, 78, 810060401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1463, 78, 70203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1464, 78, 810060402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1465, 78, 70202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1466, 78, 810060403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1467, 78, 810060404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1468, 78, 70204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1469, 78, 9020105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1470, 78, 9020107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1471, 78, 9020106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1472, 78, 9020109, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1473, 78, 9020108, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1474, 78, 810060301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1475, 78, 9020111, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1476, 78, 810060302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1477, 78, 9020110, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1478, 78, 810060303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1479, 78, 61001, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1480, 78, 61002, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1481, 78, 61003, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1482, 78, 61004, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1483, 78, 9020101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1484, 78, 61005, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1485, 78, 9020103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1486, 78, 9020102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1487, 78, 120401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1488, 78, 4040301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1489, 78, 4040302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1490, 78, 120404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1491, 78, 120407, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1492, 78, 3050104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1493, 78, 120406, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1494, 78, 3050102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1495, 78, 9020113, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1496, 78, 810060304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1497, 78, 3050103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1498, 78, 9020112, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1499, 78, 9020115, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1500, 78, 603, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1501, 78, 3050101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1502, 78, 9020114, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1503, 78, 604, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1504, 78, 9020117, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1505, 78, 605, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1506, 78, 9020116, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1507, 78, 606, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1508, 78, 608, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1509, 78, 609, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1510, 78, 610, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1511, 78, 612, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1512, 78, 2030202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1513, 78, 614, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1514, 78, 615, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1515, 78, 2030201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1516, 78, 50801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1517, 78, 50802, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1518, 78, 50803, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1519, 78, 14030501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1520, 78, 80501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1521, 78, 6040104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1522, 78, 6040105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1523, 78, 80503, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1524, 78, 80502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1525, 78, 80505, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1526, 78, 80504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1527, 78, 6040101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1528, 78, 110203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1529, 78, 6040102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1530, 78, 110202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1531, 78, 6040103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1532, 78, 110204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1533, 78, 14060201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1534, 78, 8100603, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1535, 78, 8100602, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1536, 78, 8100601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1537, 78, 20101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1538, 78, 20102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1539, 78, 20103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1540, 78, 8100604, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1541, 78, 20104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1542, 78, 20105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1543, 78, 20106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1544, 78, 20107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1545, 78, 20108, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1546, 78, 14060101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1547, 78, 14030401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1548, 78, 70301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1549, 78, 70303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1550, 78, 70302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1551, 78, 70305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1552, 78, 70304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1553, 78, 6040304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1554, 78, 6040301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1555, 78, 6040302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1556, 78, 6040303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1557, 78, 120504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1558, 78, 14020201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1559, 78, 701, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1560, 78, 702, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1561, 78, 703, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1562, 78, 704, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1563, 78, 705, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1564, 78, 30401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1565, 78, 706, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1566, 78, 30402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1567, 78, 30403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1568, 78, 30404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1569, 78, 30405, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1570, 78, 30406, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1571, 78, 8090301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1572, 78, 30407, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1573, 78, 14020101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1574, 78, 6040201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1575, 78, 6040202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1576, 78, 80601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1577, 78, 80603, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1578, 78, 80602, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1579, 78, 80605, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1580, 78, 80604, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1581, 78, 7060104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1582, 78, 8100504, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1583, 78, 20201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1584, 78, 20202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1585, 78, 20203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1586, 78, 20204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1587, 78, 7060103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1588, 78, 20205, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1589, 78, 7060102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1590, 78, 20206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1591, 78, 7060101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1592, 78, 20207, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1593, 78, 20208, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1594, 78, 3050204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1595, 78, 3050202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1596, 78, 3050203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1597, 78, 3050201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1598, 78, 70401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1599, 78, 70403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1600, 78, 70402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1601, 78, 14060501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1602, 78, 70405, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1603, 78, 100101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1604, 78, 9020301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1605, 78, 70404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1606, 78, 9020303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1607, 78, 100102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1608, 78, 9020302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1609, 78, 100105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1610, 78, 6040404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1611, 78, 100107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1612, 78, 100106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1613, 78, 6040401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1614, 78, 6040402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1615, 78, 6040403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1616, 78, 3050302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1617, 78, 61201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1618, 78, 3050301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1619, 78, 9020305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1620, 78, 9020304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1621, 78, 9020306, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1622, 78, 801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1623, 78, 802, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1624, 78, 803, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1625, 78, 804, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1626, 78, 7060303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1627, 78, 805, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1628, 78, 30501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1629, 78, 7060302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1630, 78, 806, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1631, 78, 30502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1632, 78, 7060301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1633, 78, 807, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1634, 78, 30503, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1635, 78, 808, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1636, 78, 809, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1637, 78, 810, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1638, 78, 9010104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1639, 78, 7060304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1640, 78, 80701, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1641, 78, 80703, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1642, 78, 9010103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1643, 78, 80702, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1644, 78, 9010102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1645, 78, 80705, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1646, 78, 80704, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1647, 78, 140102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1648, 78, 140103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1649, 78, 140101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1650, 78, 140106, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1651, 78, 140107, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1652, 78, 140104, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1653, 78, 140105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1654, 78, 20301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1655, 78, 20302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1656, 78, 140108, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1657, 78, 14050201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1658, 78, 70501, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1659, 78, 70502, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1660, 78, 100201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1661, 78, 14010301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1662, 78, 100203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1663, 78, 100202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1664, 78, 100204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1665, 78, 10101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1666, 78, 10102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1667, 78, 901, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1668, 78, 902, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1669, 78, 14010201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1670, 78, 60301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1671, 78, 60302, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1672, 78, 60303, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1673, 78, 14010202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1674, 78, 60304, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1675, 78, 902045, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1676, 78, 60305, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1677, 78, 902044, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1678, 78, 902047, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1679, 78, 902046, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1680, 78, 902041, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1681, 78, 902043, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1682, 78, 902042, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1683, 78, 80801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1684, 78, 9020201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1685, 78, 80803, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1686, 78, 80802, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1687, 78, 9020202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1688, 78, 80805, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1689, 78, 9020205, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1690, 78, 80804, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1691, 78, 9020204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1692, 78, 9020207, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1693, 78, 9020206, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1694, 78, 14060401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1695, 78, 140202, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1696, 78, 140203, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1697, 78, 140201, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1698, 78, 140204, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1699, 78, 9020217, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1700, 78, 9020216, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1701, 78, 9020209, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1702, 78, 9020211, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1703, 78, 9020210, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1704, 78, 9020213, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1705, 78, 9020212, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1706, 78, 9020215, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1707, 78, 9020214, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1708, 78, 70601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1709, 78, 70603, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1710, 78, 70605, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1711, 78, 100301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1712, 78, 70604, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1713, 78, 7060403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1714, 78, 61401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1715, 78, 7060402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1716, 78, 61402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1717, 78, 61403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1718, 78, 14060301, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1719, 78, 61404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1720, 78, 6140801, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1721, 78, 6140802, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1722, 78, 7060404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1723, 78, 14030601, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1724, 78, 61408, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1725, 78, 14050101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1726, 78, 14020401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1727, 78, 1001, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1728, 78, 1002, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1729, 78, 1003, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1730, 78, 60401, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1731, 78, 60402, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1732, 78, 60403, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1733, 78, 60404, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1734, 78, 90101, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1735, 78, 90103, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1736, 78, 90102, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1737, 78, 90105, 0, '2019-04-30');
INSERT INTO `ref_role_resource` VALUES (1738, 78, 90104, 0, '2019-04-30');
COMMIT;

-- ----------------------------
-- Table structure for ref_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_resource`;
CREATE TABLE `ref_user_resource` (
  `id_` bigint(25) NOT NULL AUTO_INCREMENT,
  `user_id_` bigint(20) NOT NULL COMMENT '用户id',
  `resource_id_` bigint(20) NOT NULL COMMENT '资源id',
  `order_num_` int(11) DEFAULT NULL COMMENT '排序',
  `created_by_` bigint(20) DEFAULT '0' COMMENT '创建人',
  `created_time_` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20834 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户与资源关系';

-- ----------------------------
-- Records of ref_user_resource
-- ----------------------------
BEGIN;
INSERT INTO `ref_user_resource` VALUES (20221, 0, 1, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20222, 0, 2, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20223, 0, 3, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20224, 0, 4, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20225, 0, 5, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20226, 0, 6, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20227, 0, 7, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20228, 0, 8, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20229, 0, 9, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20230, 0, 10, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20231, 0, 11, 12, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20232, 0, 12, 13, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20233, 0, 13, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20234, 0, 14, 14, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20235, 0, 101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20236, 0, 102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20237, 0, 103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20238, 0, 104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20239, 0, 201, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20240, 0, 202, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20241, 0, 203, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20242, 0, 204, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20243, 0, 301, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20244, 0, 302, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20245, 0, 304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20246, 0, 305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20247, 0, 306, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20248, 0, 401, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20249, 0, 402, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20250, 0, 403, 33, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20251, 0, 404, 44, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20252, 0, 501, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20253, 0, 502, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20254, 0, 503, 33, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20255, 0, 504, 44, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20256, 0, 505, 55, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20257, 0, 506, 66, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20258, 0, 507, 77, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20259, 0, 508, 88, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20260, 0, 603, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20261, 0, 604, 33, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20262, 0, 605, 44, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20263, 0, 606, 55, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20264, 0, 608, 77, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20265, 0, 609, 99, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20266, 0, 610, 88, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20267, 0, 612, 222, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20268, 0, 614, 444, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20269, 0, 615, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20270, 0, 701, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20271, 0, 702, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20272, 0, 703, 33, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20273, 0, 704, 44, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20274, 0, 705, 55, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20275, 0, 706, 66, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20276, 0, 801, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20277, 0, 802, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20278, 0, 803, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20279, 0, 804, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20280, 0, 805, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20281, 0, 806, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20282, 0, 807, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20283, 0, 808, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20284, 0, 809, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20285, 0, 810, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20286, 0, 901, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20287, 0, 902, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20288, 0, 1001, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20289, 0, 1002, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20290, 0, 1003, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20291, 0, 1101, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20292, 0, 1102, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20293, 0, 1201, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20294, 0, 1202, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20295, 0, 1203, 33, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20296, 0, 1204, 44, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20297, 0, 1205, 55, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20298, 0, 1206, 66, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20299, 0, 1301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20300, 0, 1401, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20301, 0, 1402, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20302, 0, 1403, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20303, 0, 1404, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20304, 0, 1405, 50, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20305, 0, 1406, 60, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20306, 0, 1407, 70, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20307, 0, 1408, 80, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20308, 0, 10101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20309, 0, 10102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20310, 0, 10301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20311, 0, 10302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20312, 0, 10303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20313, 0, 10304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20314, 0, 10305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20315, 0, 10306, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20316, 0, 10307, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20317, 0, 10308, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20318, 0, 10309, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20319, 0, 20101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20320, 0, 20102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20321, 0, 20103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20322, 0, 20104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20323, 0, 20105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20324, 0, 20106, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20325, 0, 20107, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20326, 0, 20108, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20327, 0, 20201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20328, 0, 20202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20329, 0, 20203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20330, 0, 20204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20331, 0, 20205, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20332, 0, 20206, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20333, 0, 20207, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20334, 0, 20208, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20335, 0, 20301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20336, 0, 20302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20337, 0, 30101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20338, 0, 30102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20339, 0, 30103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20340, 0, 30104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20341, 0, 30105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20342, 0, 30106, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20343, 0, 30201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20344, 0, 30202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20345, 0, 30203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20346, 0, 30204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20347, 0, 30205, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20348, 0, 30206, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20349, 0, 30401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20350, 0, 30402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20351, 0, 30403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20352, 0, 30404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20353, 0, 30405, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20354, 0, 30406, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20355, 0, 30407, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20356, 0, 30501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20357, 0, 30502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20358, 0, 30503, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20359, 0, 40101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20360, 0, 40102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20361, 0, 40103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20362, 0, 40104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20363, 0, 40105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20364, 0, 40106, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20365, 0, 40107, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20366, 0, 40201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20367, 0, 40202, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20368, 0, 40203, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20369, 0, 40204, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20370, 0, 40205, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20371, 0, 40206, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20372, 0, 40207, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20373, 0, 40208, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20374, 0, 40301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20375, 0, 40302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20376, 0, 40303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20377, 0, 40304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20378, 0, 40305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20379, 0, 40306, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20380, 0, 40307, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20381, 0, 40308, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20382, 0, 40309, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20383, 0, 40310, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20384, 0, 40401, 100, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20385, 0, 40402, 200, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20386, 0, 40403, 300, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20387, 0, 50201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20388, 0, 50202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20389, 0, 50204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20390, 0, 50207, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20391, 0, 50208, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20392, 0, 50209, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20393, 0, 50210, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20394, 0, 50211, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20395, 0, 50212, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20396, 0, 50301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20397, 0, 50302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20398, 0, 50303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20399, 0, 50304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20400, 0, 50305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20401, 0, 50307, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20402, 0, 50308, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20403, 0, 50309, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20404, 0, 50401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20405, 0, 50402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20406, 0, 50403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20407, 0, 50404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20408, 0, 50405, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20409, 0, 50407, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20410, 0, 50408, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20411, 0, 50409, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20412, 0, 50501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20413, 0, 50502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20414, 0, 50503, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20415, 0, 50504, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20416, 0, 50505, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20417, 0, 50506, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20418, 0, 50508, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20419, 0, 50601, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20420, 0, 50602, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20421, 0, 50603, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20422, 0, 50604, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20423, 0, 50605, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20424, 0, 50701, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20425, 0, 50703, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20426, 0, 50704, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20427, 0, 50801, 100, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20428, 0, 50802, 200, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20429, 0, 50803, 300, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20430, 0, 60301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20431, 0, 60302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20432, 0, 60303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20433, 0, 60304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20434, 0, 60305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20435, 0, 60401, 100, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20436, 0, 60402, 200, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20437, 0, 60403, 300, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20438, 0, 60404, 400, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20439, 0, 60501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20440, 0, 60504, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20441, 0, 60505, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20442, 0, 60506, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20443, 0, 60507, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20444, 0, 60508, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20445, 0, 60601, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20446, 0, 60602, 22, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20447, 0, 60801, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20448, 0, 60802, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20449, 0, 60803, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20450, 0, 60804, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20451, 0, 60805, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20452, 0, 60901, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20453, 0, 60902, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20454, 0, 60903, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20455, 0, 60904, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20456, 0, 60905, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20457, 0, 61001, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20458, 0, 61002, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20459, 0, 61003, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20460, 0, 61004, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20461, 0, 61005, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20462, 0, 61201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20463, 0, 61401, 1000, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20464, 0, 61402, 1001, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20465, 0, 61403, 1002, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20466, 0, 61404, 1003, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20467, 0, 61408, 1007, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20468, 0, 61501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20469, 0, 61502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20470, 0, 61503, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20471, 0, 70101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20472, 0, 70201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20473, 0, 70202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20474, 0, 70203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20475, 0, 70204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20476, 0, 70301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20477, 0, 70302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20478, 0, 70303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20479, 0, 70304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20480, 0, 70305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20481, 0, 70401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20482, 0, 70402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20483, 0, 70403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20484, 0, 70404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20485, 0, 70405, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20486, 0, 70501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20487, 0, 70502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20488, 0, 70601, 100, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20489, 0, 70603, 300, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20490, 0, 70604, 400, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20491, 0, 70605, 500, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20492, 0, 80201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20493, 0, 80202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20494, 0, 80203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20495, 0, 80204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20496, 0, 80205, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20497, 0, 80206, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20498, 0, 80207, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20499, 0, 80208, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20500, 0, 80301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20501, 0, 80305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20502, 0, 80401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20503, 0, 80402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20504, 0, 80403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20505, 0, 80404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20506, 0, 80405, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20507, 0, 80501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20508, 0, 80502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20509, 0, 80503, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20510, 0, 80504, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20511, 0, 80505, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20512, 0, 80601, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20513, 0, 80602, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20514, 0, 80603, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20515, 0, 80604, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20516, 0, 80605, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20517, 0, 80701, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20518, 0, 80702, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20519, 0, 80703, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20520, 0, 80704, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20521, 0, 80705, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20522, 0, 80801, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20523, 0, 80802, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20524, 0, 80803, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20525, 0, 80804, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20526, 0, 80805, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20527, 0, 80902, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20528, 0, 80903, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20529, 0, 81001, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20530, 0, 81005, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20531, 0, 81006, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20532, 0, 90101, 100, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20533, 0, 90102, 200, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20534, 0, 90103, 300, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20535, 0, 90104, 400, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20536, 0, 90105, 500, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20537, 0, 90201, 100, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20538, 0, 90202, 200, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20539, 0, 90203, 300, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20540, 0, 90204, 400, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20541, 0, 100101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20542, 0, 100102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20543, 0, 100105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20544, 0, 100106, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20545, 0, 100107, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20546, 0, 100201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20547, 0, 100202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20548, 0, 100203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20549, 0, 100204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20550, 0, 100301, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20551, 0, 110101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20552, 0, 110102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20553, 0, 110103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20554, 0, 110104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20555, 0, 110105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20556, 0, 110106, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20557, 0, 110107, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20558, 0, 110202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20559, 0, 110203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20560, 0, 110204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20561, 0, 120102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20562, 0, 120104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20563, 0, 120202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20564, 0, 120203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20565, 0, 120204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20566, 0, 120302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20567, 0, 120304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20568, 0, 120306, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20569, 0, 120307, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20570, 0, 120308, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20571, 0, 120401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20572, 0, 120404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20573, 0, 120406, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20574, 0, 120407, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20575, 0, 120504, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20576, 0, 130101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20577, 0, 130102, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20578, 0, 130103, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20579, 0, 130104, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20580, 0, 130105, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20581, 0, 130106, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20582, 0, 130107, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20583, 0, 140101, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20584, 0, 140102, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20585, 0, 140103, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20586, 0, 140104, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20587, 0, 140105, 50, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20588, 0, 140106, 60, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20589, 0, 140107, 70, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20590, 0, 140108, 80, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20591, 0, 140201, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20592, 0, 140202, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20593, 0, 140203, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20594, 0, 140204, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20595, 0, 140301, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20596, 0, 140302, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20597, 0, 140303, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20598, 0, 140304, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20599, 0, 140305, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20600, 0, 140306, 50, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20601, 0, 140401, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20602, 0, 140402, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20603, 0, 140403, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20604, 0, 140501, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20605, 0, 140502, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20606, 0, 140601, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20607, 0, 140602, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20608, 0, 140603, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20609, 0, 140604, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20610, 0, 140605, 50, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20611, 0, 140701, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20612, 0, 140801, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20613, 0, 902041, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20614, 0, 902042, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20615, 0, 902043, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20616, 0, 902044, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20617, 0, 902045, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20618, 0, 902046, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20619, 0, 902047, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20620, 0, 1030601, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20621, 0, 2030101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20622, 0, 2030102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20623, 0, 2030103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20624, 0, 2030104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20625, 0, 2030201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20626, 0, 2030202, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20627, 0, 3050101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20628, 0, 3050102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20629, 0, 3050103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20630, 0, 3050104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20631, 0, 3050201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20632, 0, 3050202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20633, 0, 3050203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20634, 0, 3050204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20635, 0, 3050301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20636, 0, 3050302, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20637, 0, 4040101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20638, 0, 4040102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20639, 0, 4040103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20640, 0, 4040104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20641, 0, 4040201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20642, 0, 4040202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20643, 0, 4040203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20644, 0, 4040204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20645, 0, 4040301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20646, 0, 4040302, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20647, 0, 5080101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20648, 0, 5080102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20649, 0, 5080103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20650, 0, 5080104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20651, 0, 5080105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20652, 0, 5080106, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20653, 0, 5080107, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20654, 0, 5080108, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20655, 0, 5080109, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20656, 0, 5080201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20657, 0, 5080202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20658, 0, 5080203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20659, 0, 5080204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20660, 0, 5080301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20661, 0, 5080302, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20662, 0, 6040101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20663, 0, 6040102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20664, 0, 6040103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20665, 0, 6040104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20666, 0, 6040105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20667, 0, 6040201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20668, 0, 6040202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20669, 0, 6040301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20670, 0, 6040302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20671, 0, 6040303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20672, 0, 6040304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20673, 0, 6040401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20674, 0, 6040402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20675, 0, 6040403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20676, 0, 6040404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20677, 0, 6060101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20678, 0, 6060201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20679, 0, 6140101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20680, 0, 6140102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20681, 0, 6140103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20682, 0, 6140104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20683, 0, 6140201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20684, 0, 6140202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20685, 0, 6140203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20686, 0, 6140204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20687, 0, 6140206, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20688, 0, 6140301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20689, 0, 6140302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20690, 0, 6140303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20691, 0, 6140304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20692, 0, 6140305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20693, 0, 6140306, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20694, 0, 6140307, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20695, 0, 6140308, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20696, 0, 6140309, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20697, 0, 6140310, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20698, 0, 6140311, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20699, 0, 6140312, 12, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20700, 0, 6140401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20701, 0, 6140402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20702, 0, 6140403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20703, 0, 6140404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20704, 0, 6140801, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20705, 0, 6140802, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20706, 0, 7060101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20707, 0, 7060102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20708, 0, 7060103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20709, 0, 7060104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20710, 0, 7060301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20711, 0, 7060302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20712, 0, 7060303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20713, 0, 7060304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20714, 0, 7060402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20715, 0, 7060403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20716, 0, 7060404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20717, 0, 7060502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20718, 0, 8090201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20719, 0, 8090202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20720, 0, 8090203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20721, 0, 8090204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20722, 0, 8090301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20723, 0, 8100101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20724, 0, 8100102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20725, 0, 8100103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20726, 0, 8100104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20727, 0, 8100504, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20728, 0, 8100601, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20729, 0, 8100602, 20, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20730, 0, 8100603, 30, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20731, 0, 8100604, 40, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20732, 0, 9010102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20733, 0, 9010103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20734, 0, 9010104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20735, 0, 9010201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20736, 0, 9010202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20737, 0, 9010203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20738, 0, 9010204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20739, 0, 9010301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20740, 0, 9010302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20741, 0, 9010303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20742, 0, 9010304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20743, 0, 9010401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20744, 0, 9010402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20745, 0, 9010403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20746, 0, 9010404, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20747, 0, 9010501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20748, 0, 9010502, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20749, 0, 9010503, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20750, 0, 9010504, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20751, 0, 9020101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20752, 0, 9020102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20753, 0, 9020103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20754, 0, 9020105, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20755, 0, 9020106, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20756, 0, 9020107, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20757, 0, 9020108, 8, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20758, 0, 9020109, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20759, 0, 9020110, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20760, 0, 9020111, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20761, 0, 9020112, 12, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20762, 0, 9020113, 13, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20763, 0, 9020114, 14, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20764, 0, 9020115, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20765, 0, 9020116, 15, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20766, 0, 9020117, 16, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20767, 0, 9020201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20768, 0, 9020202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20769, 0, 9020204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20770, 0, 9020205, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20771, 0, 9020206, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20772, 0, 9020207, 7, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20773, 0, 9020209, 9, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20774, 0, 9020210, 10, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20775, 0, 9020211, 11, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20776, 0, 9020212, 12, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20777, 0, 9020213, 13, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20778, 0, 9020214, 14, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20779, 0, 9020215, 15, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20780, 0, 9020216, 16, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20781, 0, 9020217, 17, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20782, 0, 9020301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20783, 0, 9020302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20784, 0, 9020303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20785, 0, 9020304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20786, 0, 9020305, 5, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20787, 0, 9020306, 6, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20788, 0, 14010101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20789, 0, 14010102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20790, 0, 14010201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20791, 0, 14010202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20792, 0, 14010301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20793, 0, 14010401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20794, 0, 14010501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20795, 0, 14010601, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20796, 0, 14010701, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20797, 0, 14010801, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20798, 0, 14020101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20799, 0, 14020201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20800, 0, 14020301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20801, 0, 14020401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20802, 0, 14030301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20803, 0, 14030401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20804, 0, 14030501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20805, 0, 14030601, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20806, 0, 14040101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20807, 0, 14040201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20808, 0, 14040301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20809, 0, 14050101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20810, 0, 14050201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20811, 0, 14060101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20812, 0, 14060201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20813, 0, 14060301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20814, 0, 14060401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20815, 0, 14060501, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20816, 0, 14070101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20817, 0, 14080101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20818, 0, 810060101, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20819, 0, 810060102, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20820, 0, 810060103, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20821, 0, 810060104, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20822, 0, 810060201, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20823, 0, 810060202, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20824, 0, 810060203, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20825, 0, 810060204, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20826, 0, 810060301, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20827, 0, 810060302, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20828, 0, 810060303, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20829, 0, 810060304, 4, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20830, 0, 810060401, 1, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20831, 0, 810060402, 2, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20832, 0, 810060403, 3, 1, '2019-04-30 17:29:20');
INSERT INTO `ref_user_resource` VALUES (20833, 0, 810060404, 4, 1, '2019-04-30 17:29:20');
COMMIT;

-- ----------------------------
-- Table structure for ref_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_role`;
CREATE TABLE `ref_user_role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id_` bigint(20) NOT NULL COMMENT '用户id',
  `role_id_` bigint(20) NOT NULL COMMENT '角色id',
  `created_by_` bigint(20) NOT NULL COMMENT '创建人',
  `created_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户与角色关系';

-- ----------------------------
-- Records of ref_user_role
-- ----------------------------
BEGIN;
INSERT INTO `ref_user_role` VALUES (28, 0, 14, 0, '2018-12-13 10:55:26');
INSERT INTO `ref_user_role` VALUES (29, 0, 15, 0, '2018-12-13 10:55:26');
INSERT INTO `ref_user_role` VALUES (30, 0, 16, 0, '2018-12-13 10:55:26');
INSERT INTO `ref_user_role` VALUES (33, 3, 39, 0, '2019-01-03 04:56:03');
INSERT INTO `ref_user_role` VALUES (41, 4, 1, 0, '2019-01-14 06:45:40');
INSERT INTO `ref_user_role` VALUES (42, 4, 0, 0, '2019-01-14 06:45:40');
INSERT INTO `ref_user_role` VALUES (43, 5, 1, 0, '2019-01-14 06:46:19');
INSERT INTO `ref_user_role` VALUES (44, 5, 0, 0, '2019-01-14 06:46:19');
INSERT INTO `ref_user_role` VALUES (49, 6, 0, 0, '2019-01-16 08:14:28');
INSERT INTO `ref_user_role` VALUES (53, 1, 1, 0, '2019-01-22 10:35:40');
INSERT INTO `ref_user_role` VALUES (56, 20, 1, 0, '2019-01-25 11:20:03');
INSERT INTO `ref_user_role` VALUES (78, 2, 0, 0, '2019-01-29 13:55:15');
INSERT INTO `ref_user_role` VALUES (79, 2, 15, 0, '2019-01-29 13:55:15');
INSERT INTO `ref_user_role` VALUES (80, 2, 16, 0, '2019-01-29 13:55:15');
INSERT INTO `ref_user_role` VALUES (98, 22, 2, 0, '2019-02-21 10:15:59');
INSERT INTO `ref_user_role` VALUES (100, 13, 1, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (101, 13, 53, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (102, 13, 14, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (103, 13, 52, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (104, 13, 56, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (105, 13, 55, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (106, 13, 16, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (107, 13, 15, 0, '2019-03-25 13:37:48');
INSERT INTO `ref_user_role` VALUES (138, 19, 1, 0, '2019-03-28 17:23:45');
INSERT INTO `ref_user_role` VALUES (139, 19, 62, 0, '2019-03-28 17:23:45');
INSERT INTO `ref_user_role` VALUES (140, 19, 61, 0, '2019-03-28 17:23:45');
INSERT INTO `ref_user_role` VALUES (145, 23, 52, 0, '2019-03-29 13:42:40');
INSERT INTO `ref_user_role` VALUES (146, 23, 60, 0, '2019-03-29 13:42:40');
INSERT INTO `ref_user_role` VALUES (147, 23, 56, 0, '2019-03-29 13:42:40');
INSERT INTO `ref_user_role` VALUES (148, 23, 16, 0, '2019-03-29 13:42:40');
INSERT INTO `ref_user_role` VALUES (149, 23, 62, 0, '2019-03-29 13:42:40');
INSERT INTO `ref_user_role` VALUES (150, 24, 2, 0, '2019-03-30 15:19:10');
INSERT INTO `ref_user_role` VALUES (151, 25, 2, 0, '2019-04-01 10:55:30');
INSERT INTO `ref_user_role` VALUES (154, 9, 14, 0, '2019-04-04 09:28:07');
INSERT INTO `ref_user_role` VALUES (155, 9, 52, 0, '2019-04-04 09:28:07');
INSERT INTO `ref_user_role` VALUES (157, 7, 14, 0, '2019-04-04 09:41:41');
INSERT INTO `ref_user_role` VALUES (158, 7, 64, 0, '2019-04-04 09:41:41');
INSERT INTO `ref_user_role` VALUES (159, 21, 69, 0, '2019-04-04 17:37:58');
INSERT INTO `ref_user_role` VALUES (160, 26, 69, 0, '2019-04-08 11:00:57');
INSERT INTO `ref_user_role` VALUES (162, 27, 71, 0, '2019-04-12 11:09:26');
INSERT INTO `ref_user_role` VALUES (163, 31, 2, 0, '2019-04-17 17:35:26');
INSERT INTO `ref_user_role` VALUES (164, 32, 2, 0, '2019-04-17 17:39:43');
INSERT INTO `ref_user_role` VALUES (165, 33, 2, 0, '2019-04-18 14:55:39');
COMMIT;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid_` bigint(20) DEFAULT NULL,
  `name_` varchar(32) NOT NULL,
  `key_` varchar(128) DEFAULT NULL,
  `pattern_` varchar(128) DEFAULT NULL,
  `method_` varchar(16) DEFAULT NULL,
  `type_` int(11) NOT NULL DEFAULT '1',
  `icon_` varchar(128) DEFAULT NULL,
  `order_num_` int(11) DEFAULT '0',
  `usable_status_` int(8) DEFAULT '1',
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=810060405 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='资源';

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
INSERT INTO `resource` VALUES (1, 0, '首页', 'home', '/home', 'POST', 1, 'home', 1, 1);
INSERT INTO `resource` VALUES (2, 0, '销售', 'sale', '/sale', 'POST', 1, 'sale', 2, 1);
INSERT INTO `resource` VALUES (3, 0, '采购', 'purchase', '/purchase', 'POST', 1, 'purchase', 3, 1);
INSERT INTO `resource` VALUES (4, 0, '产品', 'product', '/product', 'POST', 1, 'product', 5, 1);
INSERT INTO `resource` VALUES (5, 0, '仓储', 'wms', '/wms', 'POST', 1, 'wms', 6, 1);
INSERT INTO `resource` VALUES (6, 0, '生产', 'mes', '/mes', 'POST', 1, 'mes', 7, 1);
INSERT INTO `resource` VALUES (7, 0, '品控', 'qc', '/qc', 'POST', 1, 'qc', 8, 1);
INSERT INTO `resource` VALUES (8, 0, '设备', 'equipment', '/equipment', 'POST', 1, 'equipment', 9, 1);
INSERT INTO `resource` VALUES (9, 0, '人事', 'hr', '/hr', 'POST', 1, 'hr', 10, 1);
INSERT INTO `resource` VALUES (10, 0, '财务', 'finance', '/finance', 'POST', 1, 'finance', 11, 1);
INSERT INTO `resource` VALUES (11, 0, '文档', 'document', '/document', 'POST', 1, 'document', 12, 1);
INSERT INTO `resource` VALUES (12, 0, '配置', 'setting', '/setting', 'POST', 1, 'setting', 13, 1);
INSERT INTO `resource` VALUES (13, 0, '计划', 'plan', '/plan', 'POST', 1, 'plan', 4, 1);
INSERT INTO `resource` VALUES (14, 0, '报表', 'report', '/report', 'POST', 1, 'report', 14, 1);
INSERT INTO `resource` VALUES (101, 1, '系统首页', 'home.page', '/home/page', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (102, 1, '我的任务', 'home.mytask', '/home/mytask', 'POST', 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (103, 1, '消息中心', 'home.message', '/home/message', 'POST', 1, '', 3, 1);
INSERT INTO `resource` VALUES (104, 1, '数据分析', 'home.dashboard', '/home/dashboard', 'POST', 1, NULL, 4, 1);
INSERT INTO `resource` VALUES (201, 2, '销售订单', 'sale.order', '/sale/order', 'POST', 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (202, 2, '客户管理', 'sale.customer', '/sale/customer', 'POST', 1, NULL, 3, 1);
INSERT INTO `resource` VALUES (203, 2, '业务设置', 'sale.setting', '/sale/setting', 'POST', 1, NULL, 4, 1);
INSERT INTO `resource` VALUES (204, 2, '业务概况', 'sale.dashboard', '/sale/dashboard', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (301, 3, '采购订单', 'purchase.order', '/purchase/order', 'POST', 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (302, 3, '采购申请', 'purchase.list', '/purchase/list', 'POST', 1, NULL, 3, 1);
INSERT INTO `resource` VALUES (304, 3, '供应商', 'purchase.provider', '/purchase/provider', 'POST', 1, NULL, 4, 1);
INSERT INTO `resource` VALUES (305, 3, '业务配置', 'purchase.setting', '/purchase/setting', 'POST', 1, NULL, 5, 1);
INSERT INTO `resource` VALUES (306, 3, '业务概况', 'purchase.dashboard', '/purchase/dashboard', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (401, 4, '产品管理', 'product.manage', '/product/manage', 'POST', 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (402, 4, '产品谱系', 'product.family', '/product/family', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (403, 4, '工艺工序', 'product.process_craft', '/product/process_craft', 'POST', 1, NULL, 33, 1);
INSERT INTO `resource` VALUES (404, 4, '业务配置', 'product.setting', '/product/setting', 'POST', 1, NULL, 44, 1);
INSERT INTO `resource` VALUES (501, 5, '业务概况', 'wms.dashboard', '/wms/dashboard', NULL, 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (502, 5, '库存管理', 'wms.storage_list', '/wms/storage_list', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (503, 5, '入库管理', 'wms.input', '/wms/input', 'POST', 1, NULL, 33, 1);
INSERT INTO `resource` VALUES (504, 5, '出库管理', 'wms.output', '/wms/output', 'POST', 1, NULL, 44, 1);
INSERT INTO `resource` VALUES (505, 5, '转库管理', 'wms.transfer', '/wms/transfer', 'POST', 1, NULL, 55, 1);
INSERT INTO `resource` VALUES (506, 5, '库存锁定', 'wms.storage_lock', '/wms/storage_lock', 'POST', 1, NULL, 66, 1);
INSERT INTO `resource` VALUES (507, 5, '库存盘点', 'wms.storage_check', '/wms/storage_check', 'POST', 1, NULL, 77, 1);
INSERT INTO `resource` VALUES (508, 5, '业务配置', 'wms.setting', '/wms/setting', 'POST', 1, NULL, 88, 1);
INSERT INTO `resource` VALUES (603, 6, '生产计划', 'mes.plan', '/mes/plan', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (604, 6, '生产排程', 'mes.scheme', '/mes/scheme', 'POST', 1, NULL, 33, 1);
INSERT INTO `resource` VALUES (605, 6, '生产工单', 'mes.work_order', '/mes/work_order', 'POST', 1, NULL, 44, 1);
INSERT INTO `resource` VALUES (606, 6, '生产供应监测', 'mes.monitor', '/mes/monitor', 'POST', 1, NULL, 55, 1);
INSERT INTO `resource` VALUES (608, 6, '生产领料', 'mes.material_receive', '/mes/material_receive', 'POST', 1, NULL, 77, 1);
INSERT INTO `resource` VALUES (609, 6, '生产入库', 'mes.input_wms', '/mes/input_wms', 'POST', 1, NULL, 99, 1);
INSERT INTO `resource` VALUES (610, 6, '生产退料', 'mes.recede_wms', '/mes/recede_wms', 'POST', 1, NULL, 88, 1);
INSERT INTO `resource` VALUES (612, 6, '生产统计', 'mes.statistics', '/mes/statistics', NULL, 1, NULL, 222, 1);
INSERT INTO `resource` VALUES (614, 6, '业务配置', 'mes.setting', '/mes/setting', 'POST', 1, NULL, 444, 1);
INSERT INTO `resource` VALUES (615, 6, '仪表板', 'mes.dashboard', '/mes/dashboard', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (701, 7, '业务概况', 'qc.dashboard', '/qc/dashboard', 'POST', 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (702, 7, '质检任务', 'qc.task', '/qc/task', NULL, 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (703, 7, '质检计划', 'qc.plan', '/qc/plan', 'POST', 1, NULL, 33, 1);
INSERT INTO `resource` VALUES (704, 7, '质检规范', 'qc.specification', '/qc/specification', 'POST', 1, NULL, 44, 1);
INSERT INTO `resource` VALUES (705, 7, '质检结果', 'qc.mes_check', '/qc/mes_check', 'POST', 1, NULL, 55, 1);
INSERT INTO `resource` VALUES (706, 7, '业务配置', 'qc.setting', '/qc/setting', 'POST', 1, NULL, 66, 1);
INSERT INTO `resource` VALUES (801, 8, '业务概况', 'equipment.dashboard', '/equipment/dashboard', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (802, 8, '设备配置', 'equipment.list', '/equipment/list', 'POST', 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (803, 8, '设备监测', 'equipment.monitor', '/equipment/monitor', NULL, 1, NULL, 4, 1);
INSERT INTO `resource` VALUES (804, 8, '设备维修', 'equipment.repair', '/equipment/repair', 'POST', 1, NULL, 5, 1);
INSERT INTO `resource` VALUES (805, 8, '保养计划', 'equipment.maintain_plan', '/equipment/maintain_plan', 'POST', 1, NULL, 6, 1);
INSERT INTO `resource` VALUES (806, 8, '保养任务', 'equipment.maintain_task', '/equipment/maintain_task', NULL, 1, NULL, 7, 1);
INSERT INTO `resource` VALUES (807, 8, '设备安装', 'equipment.installation', '/equipment/installation', 'POST', 1, NULL, 8, 1);
INSERT INTO `resource` VALUES (808, 8, '设备报废', 'equipment.scrap', '/equipment/scrap', NULL, 1, NULL, 9, 1);
INSERT INTO `resource` VALUES (809, 8, 'IOT管理', 'equipment.iot_list', '/equipment/iot_list', NULL, 1, NULL, 3, 1);
INSERT INTO `resource` VALUES (810, 8, '业务配置', 'equipment.setting', '/equipment/setting', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (901, 9, '组织架构', 'hr.org', '/hr/org', 'POST', 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (902, 9, '员工管理', 'hr.staff', '/hr/staff', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (1001, 10, '财务对账', 'finance.business', '/finance/business', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (1002, 10, '银行账户', 'finance.bank', '/finance/bank', 'POST', 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (1003, 10, '业务配置', 'finance.setting', '/finance/setting', '', 1, NULL, 3, 1);
INSERT INTO `resource` VALUES (1101, 11, '文档管理', 'document.document', '/document/document', 'POST', 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (1102, 11, '文档等级', 'document.rank', '/document/rank', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (1201, 12, '数据字典', 'setting.dictionary', '/setting/dictionary', 'POST', 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (1202, 12, '操作界面', 'setting.operation_template', '/setting/operation_template', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (1203, 12, '角色配置', 'setting.role', '/setting/role/list', 'POST', 1, NULL, 33, 1);
INSERT INTO `resource` VALUES (1204, 12, '用户配置', 'setting.user', '/setting/user/list', 'POST', 1, NULL, 44, 1);
INSERT INTO `resource` VALUES (1205, 12, '签核维护', 'setting.bpm_model', '/setting/bpm_model', 'POST', 1, NULL, 55, 1);
INSERT INTO `resource` VALUES (1206, 12, '修改密码', 'setting.password', '/setting/password/edit', 'POST', 1, NULL, 66, 1);
INSERT INTO `resource` VALUES (1301, 13, '需求计划', 'plan.mrp', '/plan/mrp', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (1401, 14, '生产报表', 'report.mes', '/report/mes', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (1402, 14, '品控报表', 'report.qc', '/report/qc', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (1403, 14, '销售报表', 'report.sale', '/report/sale', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (1404, 14, '采购报表', 'report.purchase', '/report/purchase', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (1405, 14, '仓储报表', 'report.wms', '/report/wms', 'POST', 1, NULL, 50, 1);
INSERT INTO `resource` VALUES (1406, 14, '设备报表', 'report.equipment', '/report/equipment', 'POST', 1, NULL, 60, 1);
INSERT INTO `resource` VALUES (1407, 14, '财务报表', 'report.finance', '/report/finance', 'POST', 1, NULL, 70, 1);
INSERT INTO `resource` VALUES (1408, 14, '报表配置', 'report.setting', '/report/setting', 'POST', 1, NULL, 80, 1);
INSERT INTO `resource` VALUES (10101, 101, '横幅显示', 'home.page.banner.show', '', '', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (10102, 101, '横幅配置', 'home.page.banner.setting', '', '', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (10301, 103, '消息查询', 'home.message.info.list', '/setting/info/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (10302, 103, '消息添加', 'home.message.info.add', '/setting/info/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (10303, 103, '消息删除', 'home.message.info.del', '/setting/info/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (10304, 103, '消息修改', 'home.message.info.update', '/setting/info/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (10305, 103, '消息详情', 'home.message.info.detail', '/setting/info/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (10306, 103, '管理', 'home.message.manage', '/home/message/manage', NULL, 2, '', 3, 1);
INSERT INTO `resource` VALUES (10307, 103, '标签添加', 'home.message.tag.add', '/setting/tag/add', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (10308, 103, '标签删除', 'home.message.tag.del', '/setting/tag/del', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (10309, 103, '标签修改', 'home.message.tag.update', '/setting/tag/update', 'POST', 2, NULL, 9, 1);
INSERT INTO `resource` VALUES (20101, 201, '查询', 'sale.order.list', '/sale/saleOrder/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (20102, 201, '新增', 'sale.order.add', '/sale/saleOrder/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (20103, 201, '删除', 'sale.order.del', '/sale/saleOrder/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (20104, 201, '编辑', 'sale.order.update', '/sale/saleOrder/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (20105, 201, '查看', 'sale.order.detail', '/sale/saleOrder/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20106, 201, '发货记录', 'sale.order.deliverylist', '/sale/applyDelivery/list', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20107, 201, '退货记录', 'sale.order.returnlist', '/sale/returnGoods/list', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20108, 201, '提交', 'sale.order.commit', '/sale/saleOrder/updateStatus', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20201, 202, '查询', 'sale.customer.list', '/sale/customer/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (20202, 202, '新增', 'sale.customer.add', '/sale/customer/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (20203, 202, '删除', 'sale.customer.del', '/sale/customer/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (20204, 202, '编辑', 'sale.customer.update', '/sale/customer/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (20205, 202, '查看', 'sale.customer.detail', '/sale/customer/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20206, 202, '导入', 'sale.customer.import', '/sale/customer/import', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20207, 202, '导出', 'sale.customer.export', '/sale/customer/exportExcel', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20208, 202, '历史订单记录', 'sale.customer.orderlist', '/sale/saleOrder/listByCustomerId', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (20301, 203, '客户等级', 'sale.setting.rank', '/sale/setting/rank', NULL, 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (20302, 203, '编号配置', 'sale.setting.code_rule', '/sale/setting/code_rule', NULL, 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (30101, 301, '查询', 'purchase.order.list', '/purchase/pur/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (30102, 301, '新增', 'purchase.order.add', '/purchase/pur/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (30103, 301, '删除', 'purchase.order.del', '/purchase/pur/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (30104, 301, '编辑', 'purchase.order.update', '/purchase/pur/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (30105, 301, '查看', 'purchase.order.detail', '/purchase/pur/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (30106, 301, '提交', 'purchase.order.commit', '/purchase/pur/commitbyid', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (30201, 302, '查询', 'purchase.list.list', '/purchase/list/getlist', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (30202, 302, '新增', 'purchase.list.add', '/purchase/list/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (30203, 302, '删除', 'purchase.list.del', '/purchase/list/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (30204, 302, '编辑', 'purchase.list.update', '/purchase/list/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (30205, 302, '查看', 'purchase.list.detail', '/purchase/list/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (30206, 302, '提交', 'purchase.list.commit', '/purchase/list/commitbyid', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (30401, 304, '查询', 'purchase.supplier.list', '/purchase/supplier/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (30402, 304, '新增', 'purchase.supplier.add', '/purchase/supplier/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (30403, 304, '删除', 'purchase.supplier.del', '/purchase/supplier/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (30404, 304, '编辑', 'purchase.supplier.update', '/purchase/supplier/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (30405, 304, '查看', 'purchase.supplier.detail', '/purchase/supplier/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (30406, 304, '导入', 'purchase.supplier.import', '/purchase/supplier/import', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (30407, 304, '导出', 'purchase.supplier.export', '/purchase/supplier/export', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (30501, 305, '地址配置', 'purchase.setting.address', '/purchase/setting/address', 'POST', 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (30502, 305, '供应商等级', 'purchase.setting.rank', '/purchase/setting/rank', NULL, 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (30503, 305, '编号配置', 'purchase.setting.code_rule', '/purchase/setting/code_rule', NULL, 1, NULL, 3, 1);
INSERT INTO `resource` VALUES (40101, 401, '产品查询', 'product.manage.list', '/product/pro/getlist', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (40102, 401, '新增', 'product.manage.add', '/product/pro/addproduct', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (40103, 401, '删除', 'product.manage.del', '/product/pro/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (40104, 401, '编辑', 'product.manage.update', '/product/pro/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (40105, 401, '查看', 'product.manage.detail', 'product/pro/getbyid', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (40106, 401, '导入', 'product.manage.import', '/product/pro/importExcel', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (40107, 401, '导出', 'product.manage.export', '/product/pro/export', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (40201, 402, '谱系树查询', 'product.family.list', '/product/node/gettree', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (40202, 402, '节点下产品查询', 'product.family.list', '/product/node/getnodeproducts', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (40203, 402, '新增', 'product.family.add', '/product/node/addnode', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (40204, 402, '删除', 'product.family.del', '/product/node/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (40205, 402, '编辑', 'product.family.update', '/product/node/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (40206, 402, '复制黏贴', 'product.family.update', '/product/node/copy', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (40207, 402, '剪切黏贴', 'product.family.update', '/product/node/cut', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (40208, 402, '查看', 'product.family.detail', '/product/node/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (40301, 403, '工艺查询', 'product.process_craft.craft.list', '/mes/technology/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (40302, 403, '工艺添加', 'product.process_craft.craft.add', '/mes/technology/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (40303, 403, '工艺删除', 'product.process_craft.craft.del', '/mes/technology/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (40304, 403, '工艺修改', 'product.process_craft.craft.update', '/mes/technology/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (40305, 403, '工艺详情', 'product.process_craft.craft.detail', '/mes/technology/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (40306, 403, '工序查询', 'product.process_craft.process.list', '/mes/process/list', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (40307, 403, '工序添加', 'product.process_craft.process.add', '/mes/process/add', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (40308, 403, '工序删除', 'product.process_craft.process.del', '/mes/process/del', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (40309, 403, '工序修改', 'product.process_craft.process.update', '/mes/process/update', 'POST', 2, NULL, 9, 1);
INSERT INTO `resource` VALUES (40310, 403, '工序详情', 'product.process_craft.process.detail', '/mes/process/detail', 'POST', 2, NULL, 10, 1);
INSERT INTO `resource` VALUES (40401, 404, '分类配置', 'product.setting.category', '/product/setting/category', 'POST', 1, NULL, 100, 1);
INSERT INTO `resource` VALUES (40402, 404, '规格模板', 'product.setting.spec_template', '/product/setting/spec_template', 'POST', 1, NULL, 200, 1);
INSERT INTO `resource` VALUES (40403, 404, '编号配置', 'product.setting.code_rule', '/product/setting/code_rule', 'POST', 1, NULL, 300, 1);
INSERT INTO `resource` VALUES (50201, 502, '查询', 'wms.storage_list.list', '/wms/manager/storagelist', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (50202, 502, '查看详细', 'wms.storage_list.detail', '/wms/manager/listlocation', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (50204, 502, '安全库存配置', 'wms.storage_list.storagelist', '/wms/setting/safesetting', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (50207, 502, '发起转库', 'wms.storage_list.transfer', '/wms/transfer/add', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50208, 502, '发起盘点', 'wms.storage_list.check', '/wms/check/add', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50209, 502, '发起锁库', 'wms.storage_list.lock', '/wms/lock/add', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50210, 502, '发起出库', 'wms.storage_list.out', '/wms/out/add', NULL, 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (50211, 502, '导入', 'wms.storage_list.import', '/wms/manager/importlocationdetail', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (50212, 502, '导出', 'wms.storage_list.export', '/wms/lock/export', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (50301, 503, '查询', 'wms.input.list', '/wms/in/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (50302, 503, '新增', 'wms.input.add', '/wms/in/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (50303, 503, '删除', 'wms.input.del', '/wms/in/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (50304, 503, '编辑', 'wms.input.update', '/wms/in/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (50305, 503, '查看', 'wms.input.detail', '/wms/in/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50307, 503, '分配仓库', 'wms.input.arrange', '/wms/in/arrange', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50308, 503, '提交', 'wms.input.commit', '/wms/in/commitbyid', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50309, 503, '入库操作', 'wms.input.setin', '/wms/in/setin', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50401, 504, '查询', 'wms.output.list', '/wms/out/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (50402, 504, '新增', 'wms.output.add', '/wms/out/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (50403, 504, '删除', 'wms.output.del', '/wms/out/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (50404, 504, '编辑', 'wms.output.update', '/wms/out/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (50405, 504, '查看', 'wms.output.detail', '/wms/out/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50407, 504, '分配仓库', 'wms.output.arrange', '/wms/out/arrange', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50408, 504, '提交', 'wms.output.commit', '/wms/out/commitbyid', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50409, 504, '入库操作', 'wms.output.setout', '/wms/out/setout', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50501, 505, '查询', 'wms.transfer.list', '/wms/transfer/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (50502, 505, '新增', 'wms.transfer.add', '/wms/transfer/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (50503, 505, '删除', 'wms.transfer.del', '/wms/transfer/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (50504, 505, '编辑', 'wms.transfer.update', '/wms/transfer/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (50505, 505, '查看', 'wms.transfer.detail', '/wms/transfer/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50506, 505, '提交', 'wms.transfer.commit', '/wms/transfer/commitbyid', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50508, 505, '转库', 'wms.transfer.trans', '/wms/transfer/settrans', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (50601, 506, '查询', 'wms.storage_lock.list', '/wms/lock/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (50602, 506, '删除', 'wms.storage_lock.del', '/wms/lock/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (50603, 506, '编辑', 'wms.storage_lock.update', '/wms/lock/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (50604, 506, '查看', 'wms.storage_lock.detail', '/wms/lock/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50605, 506, '提交', 'wms.storage_lock.commitbyid', '/wms/lock/commitbyid', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50701, 507, '查询', 'wms.storage_check.list', '/wms/transfer/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (50703, 507, '盘点', 'wms.storage_check.update', '/wms/check/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (50704, 507, '查看', 'wms.storage_check.detail', '/wms/check/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (50801, 508, '仓储配置', 'wms.setting.storage_position', '/wms/setting/storage_position', 'POST', 1, NULL, 100, 1);
INSERT INTO `resource` VALUES (50802, 508, '仓储分类', 'wms.setting.storage_category', '/wms/setting/storage_category', 'POST', 1, NULL, 200, 1);
INSERT INTO `resource` VALUES (50803, 508, '编号配置', 'wms.setting.code_rule', '/wms/setting/code_rule', 'POST', 1, NULL, 300, 1);
INSERT INTO `resource` VALUES (60301, 603, '查询', 'mes.plan.list', '/mes/productionPlan/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (60302, 603, '新增', 'mes.plan.add', '/mes/productionPlan/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (60303, 603, '删除', 'mes.plan.del', '/mes/productionPlan/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (60304, 603, '编辑', 'mes.plan.update', '/mes/productionPlan/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (60305, 603, '查看', 'mes.plan.detail', '/mes/productionPlan/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (60401, 604, '排程管理', 'mes.scheme.list', '/mes/scheme/list', 'POST', 1, NULL, 100, 1);
INSERT INTO `resource` VALUES (60402, 604, '人员排班', 'mes.scheme.schedule', '/mes/scheme/schedule', 'POST', 1, NULL, 200, 1);
INSERT INTO `resource` VALUES (60403, 604, '班次配置', 'mes.scheme.second', '/mes/scheme/second', 'POST', 1, NULL, 300, 1);
INSERT INTO `resource` VALUES (60404, 604, '班组配置', 'mes.scheme.group', '/mes/scheme/group', 'POST', 1, NULL, 400, 1);
INSERT INTO `resource` VALUES (60501, 605, '查询', 'mes.work_order.list', '/mes/productionOrder/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (60504, 605, '编辑', 'mes.work_order.update', '/mes/productionOrder/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (60505, 605, '查看', 'mes.work_order.detail', '/mes/productionOrder/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (60506, 605, '启动暂停终止工单', 'mes.work_order.operation', '/mes/productionOrder/orderOperation', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (60507, 605, '领料', 'mes.work_order.materialreceive', '/mes/material_receive/add', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (60508, 605, '作业', 'mes.work_order.workorder', '/mes/work_order/operation', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (60601, 606, '消耗监测', 'mes.monitor.material', '/mes/monitor/material', 'POST', 1, NULL, 11, 1);
INSERT INTO `resource` VALUES (60602, 606, '设备监测', 'mes.monitor.equipment', '/mes/monitor/equipment', 'POST', 1, NULL, 22, 1);
INSERT INTO `resource` VALUES (60801, 608, '查询', 'mes.material_receive.list', '/mes/productionMaterialPicking/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (60802, 608, '新增', 'mes.material_receive.add', '/mes/productionMaterialPicking/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (60803, 608, '删除', 'mes.material_receive.del', '/mes/productionMaterialPicking/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (60804, 608, '编辑', 'mes.material_receive.update', '/mes/productionMaterialPicking/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (60805, 608, '查看', 'mes.material_receive.detail', '/mes/productionMaterialPicking/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (60901, 609, '查询', 'mes.input_wms.list', '/mes/productionInStorage/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (60902, 609, '新增', 'mes.input_wms.add', '/mes/productionInStorage/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (60903, 609, '删除', 'mes.input_wms.del', '/mes/productionInStorage/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (60904, 609, '编辑', 'mes.input_wms.update', '/mes/productionInStorage/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (60905, 609, '查看', 'mes.input_wms.detail', '/mes/productionInStorage/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (61001, 610, '查询', 'mes.recede_wms.list', '/mes/productionMaterialReturn/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (61002, 610, '新增', 'mes.recede_wms.add', '/mes/productionMaterialReturn/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (61003, 610, '删除', 'mes.recede_wms.del', '/mes/productionMaterialReturn/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (61004, 610, '编辑', 'mes.recede_wms.update', '/mes/productionMaterialReturn/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (61005, 610, '查看', 'mes.recede_wms.detail', '/mes/productionMaterialReturn/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (61201, 612, '查询', 'mes.statistics.list', '/mes/productionLineData/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (61401, 614, '优先级别', 'mes.setting.priority', '/mes/setting/priority', 'POST', 1, NULL, 1000, 1);
INSERT INTO `resource` VALUES (61402, 614, '工单配置', 'mes.setting.work_order', '/mes/setting/work_order', 'POST', 1, NULL, 1001, 1);
INSERT INTO `resource` VALUES (61403, 614, '厂别产线', 'mes.setting.factory_line', '/mes/setting/factory_line', 'POST', 1, NULL, 1002, 1);
INSERT INTO `resource` VALUES (61404, 614, '退料原因', 'mes.setting.recede_reason', '/mes/setting/recede_reason', 'POST', 1, NULL, 1003, 1);
INSERT INTO `resource` VALUES (61408, 614, '编号配置', 'mes.setting.code_rule', '/mes/setting/code_rule', 'POST', 1, NULL, 1007, 1);
INSERT INTO `resource` VALUES (61501, 615, '查询', 'mes.dashboard.list', '/mes/productionPanel/getProductionPanel', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (61502, 615, '生产看板', 'mes.dashboard.dv.mes', '/dv/mes', NULL, 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (61503, 615, '设备看板', 'mes.dashboard.dv.equipment', '/dv/equipment', NULL, 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (70101, 701, '查询', 'qc.dashboard.list', '/qc/plan/execution/statistic', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (70201, 702, '入库查询', 'qc.plan.in.list', '/wms/in/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (70202, 702, '入库详情', 'qc.plan.in.detail', '/wms/in/get', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (70203, 702, '出库查询', 'qc.plan.out.list', '/wms/out/list', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (70204, 702, '出库详情', 'qc.plan.out.detail', '/wms/out/get', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (70301, 703, '查询', 'qc.task.list', '/qc/plan/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (70302, 703, '新增', 'qc.task.add', '/qc/plan/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (70303, 703, '删除', 'qc.task.del', '/qc/plan/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (70304, 703, '编辑', 'qc.task.update', '/qc/plan/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (70305, 703, '查看', 'qc.task.detail', '/qc/plan/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (70401, 704, '查询', 'qc.specification.list', '/qc/criterion/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (70402, 704, '新增', 'qc.specification.add', '/qc/criterion/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (70403, 704, '删除', 'qc.specification.del', '/qc/criterion/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (70404, 704, '编辑', 'qc.specification.update', '/qc/criterion/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (70405, 704, '查看', 'qc.specification.detail', '/qc/criterion/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (70501, 705, '查询', 'qc.mes_check.list', '/qc/plan/result/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (70502, 705, '查看', 'qc.mes_check.detail', '/qc/plan/result/detail', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (70601, 706, '质检错误码', 'qc.setting.error_code', '/qc/setting/error_code', 'POST', 1, NULL, 100, 1);
INSERT INTO `resource` VALUES (70603, 706, '错误码等级', 'qc.setting.error_rank', '/qc/setting/error_rank', 'POST', 1, NULL, 300, 1);
INSERT INTO `resource` VALUES (70604, 706, '处理方式', 'qc.setting.handle_method', '/qc/setting/handle_method', 'POST', 1, NULL, 400, 1);
INSERT INTO `resource` VALUES (70605, 706, '编号规则', 'qc.setting.code_rule', '/qc/setting/code_rule', 'POST', 1, NULL, 500, 1);
INSERT INTO `resource` VALUES (80201, 802, '查询', 'equipment.list.list', '/equipment/equipment/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80202, 802, '新增', 'equipment.list.add', '/equipment/equipment/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (80203, 802, '删除', 'equipment.list.del', '/equipment/equipment/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (80204, 802, '编辑', 'equipment.list.update', '/equipment/equipment/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (80205, 802, '查看', 'equipment.list.detail', '/equipment/equipment/getOne', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80206, 802, '禁用', 'equipment.list.deactive', '/equipment/equipment/edit', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (80207, 802, '启用', 'equipment.list.active', '/equipment/equipment/edit', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (80208, 802, '导入', 'equipment.list.import', '/equipment/equipment/importExcel', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (80301, 803, '查询', 'equipment.monitor.list', '/equipment/equipment/getEquipmentMonitor', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80305, 803, '查看', 'equipment.monitor.detail', '/equipment/equipment/getEquipmentMonitorByEquipmentId', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80401, 804, '查询', 'equipment.repair.list', '/equipment/equipmentRepair/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80402, 804, '新增', 'equipment.repair.add', '/equipment/equipmentRepair/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (80403, 804, '删除', 'equipment.repair.del', '/equipment/equipmentRepair/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (80404, 804, '编辑', 'equipment.repair.update', '/equipment/equipmentRepair/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (80405, 804, '查看', 'equipment.repair.detail', '/equipment/equipmentRepair/getOne', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80501, 805, '查询', 'equipment.maintain_plan.list', '/equipment/maintainPlan/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80502, 805, '新增', 'equipment.maintain_plan.add', '/equipment/maintainPlan/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (80503, 805, '删除', 'equipment.maintain_plan.del', '/equipment/maintainPlan/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (80504, 805, '编辑', 'equipment.maintain_plan.update', '/equipment/maintainPlan/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (80505, 805, '查看', 'equipment.maintain_plan.detail', '/equipment/maintainPlan/getOne', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80601, 806, '查询', 'equipment.maintain_task.list', '/equipment/maintenanceTask/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80602, 806, '新增', 'equipment.maintain_task.add', '/equipment/maintenanceTask/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (80603, 806, '删除', 'equipment.maintain_task.del', '/equipment/maintenanceTask/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (80604, 806, '编辑', 'equipment.maintain_task.update', '/equipment/maintenanceTask/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (80605, 806, '查看', 'equipment.maintain_task.detail', '/equipment/maintenanceTask/getOne', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80701, 807, '查询', 'equipment.installation.list', '/equipment/equipmentInstall/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80702, 807, '新增', 'equipment.installation.add', '/equipment/equipmentInstall/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (80703, 807, '删除', 'equipment.installation.del', '/equipment/equipmentInstall/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (80704, 807, '编辑', 'equipment.installation.update', '/equipment/equipmentInstall/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (80705, 807, '查看', 'equipment.installation.detail', '/equipment/equipmentInstall/getOne', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80801, 808, '查询', 'equipment.scrap.list', '/equipment/equipmentScrap/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (80802, 808, '新增', 'equipment.scrap.add', '/equipment/equipmentScrap/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (80803, 808, '删除', 'equipment.scrap.del', '/equipment/equipmentScrap/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (80804, 808, '编辑', 'equipment.scrap.update', '/equipment/equipmentScrap/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (80805, 808, '查看', 'equipment.scrap.detail', '/equipment/equipmentScrap/getOne', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (80902, 809, '模板配置', 'equipment.iot_list.template', '/equipment/iot_list/template', NULL, 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (80903, 809, '监测信息', 'equipment.iot_list.monitor', '/equipment/iot_list/monitor', NULL, 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (81001, 810, '分类配置', 'equipment.setting.classify', '/equipment/setting/classify', NULL, 1, NULL, 3, 1);
INSERT INTO `resource` VALUES (81005, 810, '编号规则', 'equipment.setting.code_rule', '/equipment/setting/code_rule', NULL, 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (81006, 810, '映射管理', 'equipment.setting.mappings', '/equipment/setting/mappings', NULL, 1, NULL, 1, 1);
INSERT INTO `resource` VALUES (90101, 901, '组织树', 'hr.org.tree', '/hr/org/tree', 'POST', 1, NULL, 100, 1);
INSERT INTO `resource` VALUES (90102, 901, '职位配置', 'hr.org.position', '/hr/org/position', 'POST', 1, NULL, 200, 1);
INSERT INTO `resource` VALUES (90103, 901, '职等配置', 'hr.org.grade', '/hr/org/grade', 'POST', 1, NULL, 300, 1);
INSERT INTO `resource` VALUES (90104, 901, '职级配置', 'hr.org.rank', '/hr/org/rank', 'POST', 1, NULL, 400, 1);
INSERT INTO `resource` VALUES (90105, 901, '职位类别', 'hr.org.position_type', '/hr/org/position_type', 'POST', 1, NULL, 500, 1);
INSERT INTO `resource` VALUES (90201, 902, '员工信息', 'hr.staff.base_info', '/hr/staff/base_info', 'POST', 1, NULL, 100, 1);
INSERT INTO `resource` VALUES (90202, 902, '合同协议', 'hr.staff.contract_tabs', 'hr/staff/contract_tabs', 'POST', 1, NULL, 200, 1);
INSERT INTO `resource` VALUES (90203, 902, '员工异动', 'hr.staff.transfer', '/hr/staff/transfer', 'POST', 1, NULL, 300, 1);
INSERT INTO `resource` VALUES (90204, 902, '异动查询', 'hr.staff.transfer_list', '/hr/staff/transfer_list', 'POST', 1, '', 400, 1);
INSERT INTO `resource` VALUES (100101, 1001, '查询', 'finance.business.list', '/finance/accountCheck/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (100102, 1001, '新增', 'finance.business.add', '/finance/accountCheck/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (100105, 1001, '查看', 'finance.business.detail', '/finance/accountCheck/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (100106, 1001, '核销详情', 'finance.business.check', NULL, 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (100107, 1001, '撤销', 'finance.business.cancel', '/finance/accountCheck/del', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (100201, 1002, '查询', 'finance.bank.list', '/finance/bankAccount/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (100202, 1002, '新增', 'finance.bank.add', '/finance/bankAccount/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (100203, 1002, '删除', 'finance.bank.del', '/finance/bankAccount/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (100204, 1002, '编辑', 'finance.bank.update', '/finance/bankAccount/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (100301, 1003, '编码配置', 'finance.setting.code_rule', '/finance/setting/code_rule', 'POST', 1, NULL, 2, 1);
INSERT INTO `resource` VALUES (110101, 1101, '查询', 'document.document.list', '/document/document/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (110102, 1101, '新增', 'document.document.add', '/document/document/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (110103, 1101, '删除', 'document.document.del', '/document/document/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (110104, 1101, '编辑', 'document.document.update', '/document/document/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (110105, 1101, '查看', 'document.document.detail', '/document/document/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (110106, 1101, '发布', 'document.document.publish', '/document/document/publish', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (110107, 1101, '作废', 'document.document.cancel', '/document/document/cancel', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (110202, 1102, '新增', 'document.rank.add', '/document/scope/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (110203, 1102, '删除', 'document.rank.del', '/document/scope/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (110204, 1102, '编辑', 'document.rank.update', '/document/scope/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (120102, 1201, '新增', 'setting.dictionary.add', '/setting/dictionary/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (120104, 1201, '编辑', 'setting.dictionary.update', '/setting/dictionary/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (120202, 1202, '新增', 'setting.operation_template.add', '/setting/operation_template/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (120203, 1202, '删除', 'setting.operation_template.del', '/setting/operation_template/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (120204, 1202, '编辑', 'setting.operation_template.update', '/setting/operation_template/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (120302, 1203, '新增', 'setting.role.add', '/setting/role/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (120304, 1203, '编辑', 'setting.role.update', '/setting/role/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (120306, 1203, '禁用', 'setting.role.deactive', '/auth/user/role/updaterole', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (120307, 1203, '复制', 'setting.role.copy', '/auth/user/role/copy', NULL, 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (120308, 1203, '保存', 'setting.role.rr', '/auth/resource/save/rr', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (120401, 1204, '查询', 'setting.user.list', '/setting/user/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (120404, 1204, '编辑', 'setting.user.update', '/setting/user/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (120406, 1204, '禁用', 'setting.user.deactive', '/auth/user/updateuser', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (120407, 1204, '密码重置', 'setting.user.detail', '/auth/user/initpassword', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (120504, 1205, '编辑', 'setting.bpm_model.update', '/setting/bpm_model/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (130101, 1301, '订单视图', 'plan.mrp.orderlist', '/purchase/plan/listbyorder', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (130102, 1301, '产品视图', 'plan.mrp.prodlist', '/purchase/plan/listbyprod', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (130103, 1301, '完成', 'plan.mrp.finish', '/purchase/plan/finish', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (130104, 1301, '作废', 'plan.mrp.cancel', '/purchase/plan/cancel', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (130105, 1301, '导入', 'plan.mrp.import', '/purchase/plan/import', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (130106, 1301, '查询', 'plan.sale.list', '/sale/saleOrder/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (130107, 1301, '从销售单生成', 'plan.mrp.add', '/purchase/plan/addsaleplan', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (140101, 1401, '生产追溯卡', 'report.mes.run_card', '/report/mes/run_card', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (140102, 1401, '员工产能', 'report.mes.staff_capacity', '/report/mes/staff_capacity', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (140103, 1401, '设备产能表', 'report.mes.equipment_capacity', '/report/mes/equipment_capacity', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (140104, 1401, '生产达成率', 'report.mes.achieving', '/report/mes/achieving', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (140105, 1401, '生产日报', 'report.mes.daily', '/report/mes/daily', 'POST', 1, NULL, 50, 1);
INSERT INTO `resource` VALUES (140106, 1401, '不良明细报表', 'report.mes.bad_detail', '/report/mes/bad_detail', 'POST', 1, NULL, 60, 1);
INSERT INTO `resource` VALUES (140107, 1401, '生产透视', 'report.mes.statistics', '/report/mes/statistics', 'POST', 1, NULL, 70, 1);
INSERT INTO `resource` VALUES (140108, 1401, 'OEE', 'report.mes.oee', '/report/mes/oee', 'POST', 1, NULL, 80, 1);
INSERT INTO `resource` VALUES (140201, 1402, '成品良率', 'report.qc.good_rate', '/report/qc/good_rate', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (140202, 1402, 'OBA合格率', 'report.qc.oba_qualification', '/report/qc/oba_qualification', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (140203, 1402, '出入库质检报表', 'report.qc.wms_check', '/report/qc/wms_check', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (140204, 1402, '成品错误码', 'report.qc.error_detail', '/report/qc/error_detail', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (140301, 1403, '销售合计报表', 'report.sale.statistics', '/report/sale/statistics', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (140302, 1403, '查询', 'report.sale.statistics.list', '/sale/saleOrder/getcustomerproductchart', 'POST', 2, NULL, 10, 1);
INSERT INTO `resource` VALUES (140303, 1403, '产品价格趋势图', 'report.sale.pro_price', '/report/sale/pro_price', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (140304, 1403, '销售日报表', 'report.sale.daily', '/report/sale/daily', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (140305, 1403, '月销售金额趋势', 'report.sale.monthly', '/report/sale/monthly', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (140306, 1403, '退货比率', 'report.sale.recede', '/report/sale/recede', 'POST', 1, NULL, 50, 1);
INSERT INTO `resource` VALUES (140401, 1404, '供应商产品采购合计报表', 'report.purchase.statistics', '/report/purchase/statistics', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (140402, 1404, '月采购金额趋势', 'report.purchase.monthly', '/report/purchase/monthly', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (140403, 1404, '产品价格趋势图', 'report.purchase.pro_price', '/report/purchase/pro_price', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (140501, 1405, '出入库日报', 'report.wms.daily', '/report/wms/daily', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (140502, 1405, '库存周转率', 'report.wms.transfer_rate', '/report/wms/transfer_rate', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (140601, 1406, '设备完好率', 'report.equipment.good_rate', '/report/equipment/good_rate', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (140602, 1406, '平均故障时间', 'report.equipment.bad_time', '/report/equipment/bad_time', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (140603, 1406, '平均修理时间', 'report.equipment.repair_time', '/report/equipment/repair_time', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (140604, 1406, '设备停机率', 'report.equipment.stop_rate', '/report/equipment/stop_rate', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (140605, 1406, '维修一次合格率', 'report.equipment.once_repair_rate', '/report/equipment/once_repair_rate', 'POST', 1, NULL, 50, 1);
INSERT INTO `resource` VALUES (140701, 1407, '收付款报表（按月）', 'report.finance.monthly', '/report/finance/monthly', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (140801, 1408, '生成器', 'report.setting.creator', '/report/setting/creator', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (902041, 90204, '内部调动', 'staff.transactionQuery.INNER_MOBILIZATION', '/staff/transactionQuery/INNER_MOBILIZATION', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (902042, 90204, '借调', 'staff.transactionQuery.TEMPORARILY', '/staff/transactionQuery/TEMPORARILY', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (902043, 90204, '外派', 'staff.transactionQuery.EXPATRIATE', '/staff/transactionQuery/EXPATRIATE', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (902044, 90204, '退休', 'staff.transactionQuery.RETIRE', '/staff/transactionQuery/RETIRE', '2', 0, NULL, 4, 1);
INSERT INTO `resource` VALUES (902045, 90204, '离职', 'staff.transactionQuery.DIMISSION', '/staff/transactionQuery/DIMISSION', '2', 0, NULL, 5, 1);
INSERT INTO `resource` VALUES (902046, 90204, '重新雇佣', 'staff.transactionQuery.REHIRE', '/staff/transactionQuery/REHIRE', '2', 0, NULL, 6, 1);
INSERT INTO `resource` VALUES (902047, 90204, '返聘', 'staff.transactionQuery.RETURN_REHIRE', '/staff/transactionQuery/RETURN_REHIRE', '2', 0, NULL, 1, 1);
INSERT INTO `resource` VALUES (1030601, 10306, '查询', 'home.message.manage.list', '/setting/info/list', 'POST', 2, '', 3, 1);
INSERT INTO `resource` VALUES (2030101, 20301, '查询', 'sale.setting.rank.list', '/sale/grade/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (2030102, 20301, '新增', 'sale.setting.rank.add', '/sale/grade/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (2030103, 20301, '删除', 'sale.setting.rank.del', '/sale/grade/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (2030104, 20301, '编辑', 'sale.setting.rank.update', '/sale/grade/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (2030201, 20302, '查询', 'sale.setting.code_rule.list', '/setting/settingCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (2030202, 20302, '编辑', 'sale.setting.code_rule.update', '/setting/settingCode/update', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (3050101, 30501, '查询', 'purchase.setting.address.list', '/purchase/addr/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (3050102, 30501, '新增', 'purchase.setting.address.add', '/purchase/addr/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (3050103, 30501, '删除', 'purchase.setting.address.del', '/purchase/addr/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (3050104, 30501, '编辑', 'purchase.setting.address.update', '/purchase/addr/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (3050201, 30502, '查询', 'purchase.setting.rank.list', '/purchase/grade/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (3050202, 30502, '新增', 'purchase.setting.rank.add', '/purchase/grade/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (3050203, 30502, '删除', 'purchase.setting.rank.del', '/purchase/grade/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (3050204, 30502, '编辑', 'purchase.setting.rank.update', '/purchase/grade/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (3050301, 30503, '查询', 'purchase.setting.code_rule.list', '/setting/settingCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (3050302, 30503, '编辑', 'purchase.setting.code_rule.update', '/setting/settingCode/update', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (4040101, 40401, '查询', 'product.setting.category.list', '/product/cate/getlist', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (4040102, 40401, '新增', 'product.setting.category.add', '/product/cate/addcategory', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (4040103, 40401, '删除', 'product.setting.category.del', '/product/cate/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (4040104, 40401, '编辑', 'product.setting.category.update', '/product/cate/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (4040201, 40402, '查询', 'product.setting.spec_template.list', '/product/temp/getlist', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (4040202, 40402, '新增', 'product.setting.spec_template.add', '/product/temp/addtemplate', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (4040203, 40402, '删除', 'product.setting.spec_template.del', '/product/temp/delbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (4040204, 40402, '编辑', 'product.setting.spec_template.update', '/product/temp/updatebyid', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (4040301, 40403, '查询', 'purchase.setting.code_rule.list', '/setting/settingCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (4040302, 40403, '查看', 'purchase.setting.code_rule.update', '/setting/settingCode/update', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (5080101, 50801, '仓库查询', 'wms.setting.storage_position.list', '/wms/setting/liststorage', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (5080102, 50801, '仓库添加', 'wms.setting.storage_position.add', '/wms/setting/addstorage', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (5080103, 50801, '仓库删除', 'wms.setting.storage_position.del', '/wms/setting/delstoragebyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (5080104, 50801, '仓库修改', 'wms.setting.storage_position.update', '/wms/setting/updatestorage', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (5080105, 50801, '仓库详情', 'wms.setting.storage_position.detail', '/wms/setting/getstorage', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (5080106, 50801, '储位查询', 'wms.setting.storage_location.list', '/wms/setting/listlocation', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (5080107, 50801, '储位添加', 'wms.setting.storage_location.add', '/wms/setting/addlocation', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (5080108, 50801, '储位删除', 'wms.setting.storage_location.del', '/wms/setting/dellocationbyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (5080109, 50801, '储位修改', 'wms.setting.storage_location.update', '/wms/setting/updatelocation', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (5080201, 50802, '查询', 'wms.setting.storage_category.list', '/wms/setting/listcate', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (5080202, 50802, '新增', 'wms.setting.storage_category.add', '/wms/setting/addcategory', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (5080203, 50802, '删除', 'wms.setting.storage_category.del', '/wms/setting/delcatebyids', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (5080204, 50802, '编辑', 'wms.setting.storage_category.update', '/wms/setting/updatecategory', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (5080301, 50803, '查询', 'wms.setting.code_rule.list', '/setting/settingCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (5080302, 50803, '编辑', 'wms.setting.code_rule.update', '/setting/settingCode/update', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (6040101, 60401, '查询', 'mes.scheme.list.list', '/mes/productionSchedule/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6040102, 60401, '新增', 'mes.scheme.list.add', '/mes/productionSchedule/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6040103, 60401, '删除', 'mes.scheme.list.del', '/mes/productionSchedule/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6040104, 60401, '编辑', 'mes.scheme.list.update', '/mes/productionSchedule/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6040105, 60401, '查看', 'mes.scheme.list.detail', '/mes/productionSchedule/detail', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (6040201, 60402, '查询', 'mes.scheme.schedule.list', '/mes/scheduling/searchScheduling', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6040202, 60402, '新增', 'mes.scheme.schedule.add', '/mes/scheduling/addList', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6040301, 60403, '查询', 'mes.scheme.second.list', '/mes/jobClasses/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6040302, 60403, '新增', 'mes.scheme.second.add', '/mes/jobClasses/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6040303, 60403, '删除', 'mes.scheme.second.del', '/mes/jobClasses/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6040304, 60403, '编辑', 'mes.scheme.second.update', '/mes/jobClasses/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6040401, 60404, '查询', 'mes.scheme.group.list', '/mes/scheduleRule/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6040402, 60404, '新增', 'mes.scheme.group.add', '/mes/scheduleRule/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6040403, 60404, '删除', 'mes.scheme.group.del', '/mes/scheduleRule/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6040404, 60404, '编辑', 'mes.scheme.group.update', '/mes/scheduleRule/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6060101, 60601, '查询', 'mes.monitor.material.monitor', '/mes/materialWarningSetting/monitor', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6060201, 60602, '查询', 'mes.monitor.equipment.list', '/mes/monitor/equipment/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6140101, 61401, '查询', 'mes.setting.priority.list', '/mes/priority/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6140102, 61401, '新增', 'mes.setting.priority.add', '/mes/priority/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6140103, 61401, '删除', 'mes.setting.priority.del', '/mes/priority/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6140104, 61401, '编辑', 'mes.setting.priority.update', '/mes/priority/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6140201, 61402, '查询', 'mes.setting.work_order.list', '/mes/orderNumSetting/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6140202, 61402, '新增', 'mes.setting.work_order.add', '/mes/orderNumSetting/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6140203, 61402, '删除', 'mes.setting.work_order.del', '/mes/orderNumSetting/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6140204, 61402, '编辑', 'mes.setting.work_order.update', '/mes/orderNumSetting/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6140206, 61402, '生产上限配置', 'mes.setting.work_order.detail', '/mes/orderNumSetting/getDefaultData', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (6140301, 61403, '厂别查询', 'mes.setting.factory_line.factory.list', '/mes/factory/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6140302, 61403, '厂别添加', 'mes.setting.factory_line.factory.add', '/mes/factory/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6140303, 61403, '厂别删除', 'mes.setting.factory_line.factory.del', '/mes/factory/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6140304, 61403, '厂别修改', 'mes.setting.factory_line.factory.update', '/mes/factory/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6140305, 61403, '线别查询', 'mes.setting.factory_line.line.list', '/mes/factory/list', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (6140306, 61403, '线别添加', 'mes.setting.factory_line.line.add', '/mes/line/add', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (6140307, 61403, '线别删除', 'mes.setting.factory_line.line.del', '/mes/line/delBatch', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (6140308, 61403, '线别修改', 'mes.setting.factory_line.line.update', '/mes/line/update', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (6140309, 61403, '工位查询', 'mes.setting.factory_line.station.list', '/mes/station/list', 'POST', 2, NULL, 9, 1);
INSERT INTO `resource` VALUES (6140310, 61403, '工位添加', 'mes.setting.factory_line.station.add', '/mes/station/add', 'POST', 2, NULL, 10, 1);
INSERT INTO `resource` VALUES (6140311, 61403, '工位删除', 'mes.setting.factory_line.station.del', '/mes/station/delBatch', 'POST', 2, NULL, 11, 1);
INSERT INTO `resource` VALUES (6140312, 61403, '工位修改', 'mes.setting.factory_line.station.update', '/mes/station/update', 'POST', 2, NULL, 12, 1);
INSERT INTO `resource` VALUES (6140401, 61404, '查询', 'mes.setting.recede_reason.list', '/mes/materialReturnReason/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6140402, 61404, '新增', 'mes.setting.recede_reason.add', '/mes/materialReturnReason/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (6140403, 61404, '删除', 'mes.setting.recede_reason.del', '/mes/materialReturnReason/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (6140404, 61404, '编辑', 'mes.setting.recede_reason.update', '/mes/materialReturnReason/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (6140801, 61408, '查询', 'mes.setting.code_rule.list', '/setting/settingCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (6140802, 61408, '编辑', 'mes.setting.code_rule.update', '/setting/settingCode/update', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (7060101, 70601, '查询', 'qc.setting.error_code.list', '/qc/qcerror/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (7060102, 70601, '新增', 'qc.setting.error_code.add', '/qc/qcerror/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (7060103, 70601, '删除', 'qc.setting.error_code.del', '/qc/qcerror/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (7060104, 70601, '编辑', 'qc.setting.error_code.update', '/qc/qcerror/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (7060301, 70603, '查询', 'qc.setting.error_rank.list', '/qc/qcerror/level/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (7060302, 70603, '新增', 'qc.setting.error_rank.add', '/qc/qcerror/level/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (7060303, 70603, '删除', 'qc.setting.error_rank.del', '/qc/qcerror/level/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (7060304, 70603, '编辑', 'qc.setting.error_rank.update', '/qc/qcerror/level/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (7060402, 70604, '新增', 'qc.setting.handle_method.add', '/qc/qcerror/treatment/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (7060403, 70604, '删除', 'qc.setting.handle_method.del', '/qc/qcerror/treatment/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (7060404, 70604, '编辑', 'qc.setting.handle_method.update', '/qc/qcerror/treatment/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (7060502, 70605, '编辑', 'qc.setting.code_rule.update', '/setting/settingCode/update', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (8090201, 80902, '查询', 'equipment.iot_list.template.list', '/equipment/template/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (8090202, 80903, '编辑', 'equipment.iot_list.template.edit', '', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (8090203, 80902, '删除', 'equipment.iot_list.template.del', '', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (8090204, 80902, '加载通道', 'equipment.iot_list.template.chanel', '', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (8090301, 80903, '查询', 'equipment.iot_list.monitor.list', '/equipment/equipment/getEquipmentChanne', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (8100101, 81001, '查询', 'equipment.setting.classify.list', '/equipment/equipmentCategory/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (8100102, 81001, '新增', 'equipment.setting.classify.add', '/equipment/equipmentCategory/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (8100103, 81001, '删除', 'equipment.setting.classify.del', '/equipment/equipmentCategory/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (8100104, 81001, '编辑', 'equipment.setting.classify.update', '/equipment/equipmentCategory/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (8100504, 81005, '编辑', 'equipment.setting.code_rule.update', '/setting/createCode/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (8100601, 81006, '设备参数', 'equipment.setting.mappings.parameter', '/equipment/setting/mappings/parameter', 'POST', 1, NULL, 10, 1);
INSERT INTO `resource` VALUES (8100602, 81006, '设备状态', 'equipment.setting.mappings.status', '/equipment/setting/mappings/status', 'POST', 1, NULL, 20, 1);
INSERT INTO `resource` VALUES (8100603, 81006, '配置故障码等级', 'equipment.setting.mappings.fault_code_level', '/equipment/setting/mappings/fault_code_level', 'POST', 1, NULL, 30, 1);
INSERT INTO `resource` VALUES (8100604, 81006, '等级配置', 'equipment.setting.mappings.rank', '/equipment/setting/mappings/rank', 'POST', 1, NULL, 40, 1);
INSERT INTO `resource` VALUES (9010102, 90101, '新增', 'hr.org.tree.add', '/org/org/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9010103, 90101, '删除', 'hr.org.tree.del', '/org/org/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9010104, 90101, '编辑', 'hr.org.tree.update', '/org/org/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9010201, 90102, '查询', 'hr.org.position.list', '/org/position/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9010202, 90102, '新增', 'hr.org.position.add', '/org/position/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9010203, 90102, '删除', 'hr.org.position.del', '/org/position/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9010204, 90102, '编辑', 'hr.org.position.update', '/org/position/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9010301, 90103, '查询', 'hr.org.grade.list', '/org/grade/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9010302, 90103, '新增', 'hr.org.grade.add', '/org/grade/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9010303, 90103, '删除', 'hr.org.grade.del', '/org/grade/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9010304, 90103, '编辑', 'hr.org.grade.update', '/org/grade/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9010401, 90104, '查询', 'hr.org.rank.list', '/org/rank/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9010402, 90104, '新增', 'hr.org.rank.add', '/org/rank/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9010403, 90104, '删除', 'hr.org.rank.del', '/org/rank/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9010404, 90104, '编辑', 'hr.org.rank.update', '/org/rank/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9010501, 90105, '查询', 'hr.org.position_type.list', '/org/positionType/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9010502, 90105, '新增', 'hr.org.position_type.add', '/org/positionType/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9010503, 90105, '删除', 'hr.org.position_type.del', '/org/positionType/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9010504, 90105, '编辑', 'hr.org.position_type.update', '/org/positionType/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9020101, 90201, '查询', 'hr.staff.base_info.list', '/org/baseInfo/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9020102, 90201, '新增', 'hr.staff.base_info.add', '/org/baseInfo/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9020103, 90201, '删除', 'hr.staff.base_info.del', '/org/baseInfo/del', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9020105, 90201, '查看', 'hr.staff.base_info.detail', '/org/baseInfo/get', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (9020106, 90201, '内部履历', 'hr.staff.base_info.adjustmentWork', '/org/adjustmentWork/list', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (9020107, 90201, '能力档案', 'hr.staff.base_info.recordProfession', '/org/recordProfession/list', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (9020108, 90201, '荣誉信息', 'hr.staff.base_info.award', '/org/award/list', 'POST', 2, NULL, 8, 1);
INSERT INTO `resource` VALUES (9020109, 90201, '惩处信息', 'hr.staff.base_info.punishment', '/org/punishment/list', 'POST', 2, NULL, 9, 1);
INSERT INTO `resource` VALUES (9020110, 90201, '残疾信息', 'hr.staff.base_info.disability', '/org/disability/list', 'POST', 2, NULL, 10, 1);
INSERT INTO `resource` VALUES (9020111, 90201, '工伤信息', 'hr.staff.base_info.occInjury', '/org/occInjury/list', 'POST', 2, NULL, 11, 1);
INSERT INTO `resource` VALUES (9020112, 90201, '工作许可证信息', 'hr.staff.base_info.foreignVisa', '/org/foreignVisa/list', 'POST', 2, NULL, 12, 1);
INSERT INTO `resource` VALUES (9020113, 90201, '居留签注证信息', 'hr.staff.base_info.foreignReside', '/org/foreignReside/list', 'POST', 2, NULL, 13, 1);
INSERT INTO `resource` VALUES (9020114, 90201, '档案信息', 'hr.staff.base_info.companyRecord', '/org/companyRecord/list', 'POST', 2, NULL, 14, 1);
INSERT INTO `resource` VALUES (9020115, 90201, '编辑', 'hr.staff.base_info.update', '/org/baseInfo/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9020116, 90201, '导入', 'hr.staff.base_info.import', '/org/baseInfo/importExcel', 'POST', 2, NULL, 15, 1);
INSERT INTO `resource` VALUES (9020117, 90201, '导出', 'hr.staff.base_info.export', '/org/baseInfo/exportExcel', 'POST', 2, NULL, 16, 1);
INSERT INTO `resource` VALUES (9020201, 90202, '合同查询', 'staff.contract_tabs.contract.list', '/staff/contract/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9020202, 90202, '合同添加', 'staff.contract_tabs.contract.add', '/staff/contract/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9020204, 90202, '合同修改', 'staff.contract_tabs.contract.update', '/staff/contract/update', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9020205, 90202, '协议查询', 'staff.contract_tabs.agreement.list', '/staff/agreement/list', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (9020206, 90202, '协议添加', 'staff.contract_tabs.agreement.add', '/staff/agreement/add', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (9020207, 90202, '协议修改', 'staff.contract_tabs.agreement.update', '/staff/agreement/update', 'POST', 2, NULL, 7, 1);
INSERT INTO `resource` VALUES (9020209, 90202, '续签', 'staff.contract_tabs.contract.renew', '/staff/contract/renew', 'POST', 2, NULL, 9, 1);
INSERT INTO `resource` VALUES (9020210, 90202, '终止', 'staff.contract_tabs.contract.termination', '/staff/contract/termination', 'POST', 2, NULL, 10, 1);
INSERT INTO `resource` VALUES (9020211, 90202, '中止', 'staff.contract_tabs.contract.suspend', '/staff/contract/suspend', 'POST', 2, NULL, 11, 1);
INSERT INTO `resource` VALUES (9020212, 90202, '解除', 'staff.contract_tabs.contract.remove', '/staff/contract/remove', 'POST', 2, NULL, 12, 1);
INSERT INTO `resource` VALUES (9020213, 90202, '续签', 'staff.contract_tabs.agreement.renew', '/staff/agreement/renew', 'POST', 2, NULL, 13, 1);
INSERT INTO `resource` VALUES (9020214, 90202, '终止', 'staff.contract_tabs.agreement.termination', '/staff/agreement/termination', 'POST', 2, NULL, 14, 1);
INSERT INTO `resource` VALUES (9020215, 90202, '中止', 'staff.contract_tabs.agreement.suspend', '/staff/agreement/suspend', 'POST', 2, NULL, 15, 1);
INSERT INTO `resource` VALUES (9020216, 90202, '解除', 'staff.contract_tabs.agreement.remove', '/staff/agreement/remove', 'POST', 2, NULL, 16, 1);
INSERT INTO `resource` VALUES (9020217, 90202, '导入', 'staff.contract_tabs.contract.import', '/org/contract/importExcel', 'POST', 2, NULL, 17, 1);
INSERT INTO `resource` VALUES (9020301, 90203, '查询', 'hr.staff.transaction.list', '/staff/transaction/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (9020302, 90203, '异动办理', 'hr.staff.transaction.moveTransact', '/staff/transaction/moveTransact', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (9020303, 90203, '处理到期', 'hr.staff.transaction.disposeExpire', '/staff/transaction/disposeExpire', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (9020304, 90203, '异动查询', 'hr.staff.transaction.moveQuery', '/staff/transaction/moveQuery', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (9020305, 90203, '导出', 'hr.staff.transaction.export', '/staff/transaction/export', 'POST', 2, NULL, 5, 1);
INSERT INTO `resource` VALUES (9020306, 90203, '导入', 'hr.staff.transaction.import', '/staff/transaction/import', 'POST', 2, NULL, 6, 1);
INSERT INTO `resource` VALUES (14010101, 140101, '查询', 'report.mes.run_card.list', '/mes/runCard/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010102, 140101, '查看', 'report.mes.run_card.detail', '/mes/runCard/detailBySN', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (14010201, 140102, '查询', 'report.mes.staff_capacity.list', '/mes/employeeProductivity/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010202, 140102, '导出', 'report.mes.staff_capacity.export', '/mes/employeeProductivity/exportExcel', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (14010301, 140103, '查询', 'report.mes.equipment_capacity.list', '/report/mes/equipment_capacity/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010401, 140104, '查询', 'report.mes.achieving.list', '/mes/productionYield/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010501, 140105, '查询', 'report.mes.daily.list', '/report/mes/daily/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010601, 140106, '查询', 'report.mes.bad_detail.list', '/report/mes/bad_detail/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010701, 140107, '查询', 'report.mes.statistics.list', '/mes/report/productionPerspective', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14010801, 140108, '查询', 'report.mes.oee.list', '/report/mes/oee/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14020101, 140201, '查询', 'report.qc.good_rate.list', '/report/qc/good_rate/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14020201, 140202, '查询', 'report.qc.oba_qualification.list', '/report/qc/oba_qualification/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14020301, 140203, '查询', 'report.qc.wms_check.list', '/report/qc/wms_checklist', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14020401, 140204, '查询', 'report.qc.error_detail.list', '/report/qc/error_detail/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14030301, 140303, '查询', 'report.sale.pro_price.list', '/sale/saleOrder/getporductaverprice', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14030401, 140304, '查询', 'report.sale.daily.list', '/sale/saleOrder/getdailyreportchart', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14030501, 140305, '查询', 'report.sale.monthly.list', '/sale/saleOrder/getmonthtotalchart', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14030601, 140306, '查询', 'report.sale.recede.list', '/sale/saleOrder/getreturnratechart', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14040101, 140401, '查询', 'report.purchase.statistics.list', '/purchase/pur/getsupplierproductchart', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14040201, 140402, '查询', 'report.purchase.monthly.list', '/purchase/pur/getmonthtotalchart', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14040301, 140403, '查询', 'report.purchase.pro_price.list', '/purchase/pur/getporductaverprice', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14050101, 140501, '查询', 'report.wms.daily.list', '/wms/manager/getdailyreport', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14050201, 140502, '查询', 'report.wms.transfer_rate.list', '/wms/manager/getturnoverrate', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14060101, 140601, '查询', 'report.equipment.good_rate.list', '/report/equipment/good_rate/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14060201, 140602, '查询', 'report.equipment.bad_time.list', '/report/equipment/bad_time/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14060301, 140603, '查询', 'report.equipment.repair_time.list', '/report/equipment/repair_time/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14060401, 140604, '查询', 'report.equipment.stop_rate.list', '/report/equipment/stop_rate/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14060501, 140605, '查询', 'report.equipment.once_repair_rate.list', '/report/equipment.once_repair_rate/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14070101, 140701, '查询', 'report.finance.monthly.list', '/finance/accountCheck/sumMoney', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (14080101, 140801, '查询', 'report.setting.creator.list', '/report/setting/creator/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (810060101, 8100601, '查询', 'equipment.setting.mappings.parameter.list', '/setting/settingCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (810060102, 8100601, '新增', 'equipment.setting.mappings.parameter.add', '/equipment/equipmentParameter/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (810060103, 8100601, '删除', 'equipment.setting.mappings.parameter.del', '/equipment/equipmentParameter/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (810060104, 8100601, '编辑', 'equipment.setting.mappings.parameter.update', '/equipment/equipmentParameter/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (810060201, 8100602, '查询', 'equipment.setting.mappings.status.list', '/equipment/equipmentStatus/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (810060202, 8100602, '新增', 'equipment.setting.mappings.status.add', '/equipment/equipmentStatus/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (810060203, 8100602, '删除', 'equipment.setting.mappings.status.del', '/equipment/equipmentStatus/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (810060204, 8100602, '编辑', 'equipment.setting.mappings.status.update', '/equipment/equipmentStatus/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (810060301, 8100603, '查询', 'equipment.setting.mappings.fault_code_level.list', '/equipment/troubleCode/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (810060302, 8100603, '新增', 'equipment.setting.mappings.fault_code_level.add', '/equipment/troubleCode/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (810060303, 8100603, '删除', 'equipment.setting.mappings.fault_code_level.del', '/equipment/troubleCode/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (810060304, 8100603, '编辑', 'equipment.setting.mappings.fault_code_level.update', '/equipment/troubleCode/edit', 'POST', 2, NULL, 4, 1);
INSERT INTO `resource` VALUES (810060401, 8100604, '查询', 'equipment.setting.mappings.rank.list', '/equipment/troubleRank/list', 'POST', 2, NULL, 1, 1);
INSERT INTO `resource` VALUES (810060402, 8100604, '新增', 'equipment.setting.mappings.rank.add', '/equipment/troubleRank/add', 'POST', 2, NULL, 2, 1);
INSERT INTO `resource` VALUES (810060403, 8100604, '删除', 'equipment.setting.mappings.rank.del', '/equipment/troubleRank/delBatch', 'POST', 2, NULL, 3, 1);
INSERT INTO `resource` VALUES (810060404, 8100604, '编辑', 'equipment.setting.mappings.rank.update', '/equipment/troubleRank/edit', 'POST', 2, NULL, 4, 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `desc_` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `status_` int(11) DEFAULT NULL COMMENT '角色状态，1启用，-1禁用',
  `created_by_` bigint(20) DEFAULT NULL,
  `created_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '超级管理员', '超级管理员', 1, 0, '2019-01-14 14:35:32', '2019-01-24 16:18:27', 0);
INSERT INTO `role` VALUES (2, '基本角色', '1', 1, 0, '2019-01-22 06:44:49', '2019-04-04 09:49:27', 0);
INSERT INTO `role` VALUES (14, '测试角色A', 'test', 1, 0, '2018-12-13 10:51:16', '2019-04-04 10:29:38', 0);
INSERT INTO `role` VALUES (15, '测试角色B', 'test', 1, 0, '2018-12-13 10:51:40', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (16, '测试角色C', 'test', 1, 0, '2018-12-13 10:51:43', '2019-01-16 13:08:29', 0);
INSERT INTO `role` VALUES (52, '仓库主管', '仓库主管', 1, 0, '2019-01-08 08:40:02', '2019-04-04 09:46:43', 0);
INSERT INTO `role` VALUES (53, '仓储主管', '仓储主管', -1, 0, '2019-01-08 08:40:31', '2019-01-08 16:42:16', 0);
INSERT INTO `role` VALUES (55, '角色E', 'f', -1, 0, '2019-01-15 06:21:23', '2019-04-04 09:44:11', 0);
INSERT INTO `role` VALUES (56, '角色F', 'ff', 1, 0, '2019-01-15 06:21:29', '2019-04-04 09:44:23', 0);
INSERT INTO `role` VALUES (58, '角色G', '222', 1, 0, '2019-01-25 16:48:29', '2019-04-04 09:46:44', 0);
INSERT INTO `role` VALUES (59, '角色H', 'kingaa', 1, 0, '2019-01-29 10:50:49', '2019-04-04 09:46:45', 0);
INSERT INTO `role` VALUES (60, '角色I', 'fff', 1, 0, '2019-03-11 17:14:28', '2019-04-04 16:47:53', 0);
INSERT INTO `role` VALUES (61, '角色J', 'ff', 1, 0, '2019-03-11 17:14:44', '2019-04-04 09:44:33', 0);
INSERT INTO `role` VALUES (62, '角色K', 'ff', 1, 0, '2019-03-11 17:42:18', '2019-04-04 09:44:35', 0);
INSERT INTO `role` VALUES (63, '角色L', 'ff', 1, 0, '2019-03-11 18:08:42', '2019-04-04 09:44:37', 0);
INSERT INTO `role` VALUES (64, '测试角色D', 'test', 1, 0, '2019-03-11 18:09:36', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (65, '角色M', 'a', 1, 0, '2019-04-01 14:40:24', '2019-04-04 09:44:43', 0);
INSERT INTO `role` VALUES (66, '角色N', '1', 1, 0, '2019-04-01 14:40:33', '2019-04-04 09:44:45', 0);
INSERT INTO `role` VALUES (67, '角色O', '2', -1, 0, '2019-04-02 10:32:49', '2019-04-04 16:46:22', 0);
INSERT INTO `role` VALUES (69, '人王', '穷奇', 1, 0, '2019-04-04 16:45:16', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (70, '人王2', '穷奇', -1, 0, '2019-04-04 16:53:06', '2019-04-15 19:19:58', 0);
INSERT INTO `role` VALUES (71, '物料计划', '物料计划生产按钮测试', -1, 0, '2019-04-12 11:07:45', '2019-04-25 14:22:04', 0);
INSERT INTO `role` VALUES (72, '1231', '12', 1, 0, '2019-04-18 10:17:26', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (73, '12', '1', 1, 0, '2019-04-18 10:20:22', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (74, '1', '1', 1, 0, '2019-04-18 10:22:02', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (75, '15', '1', 1, 0, '2019-04-18 10:22:40', '2019-04-18 10:24:09', 0);
INSERT INTO `role` VALUES (76, '人王', '穷奇', 1, 0, '2019-04-25 14:24:24', '2020-05-27 19:59:35', 0);
INSERT INTO `role` VALUES (77, 'admin', '测试一', -1, 0, '2019-04-30 10:53:46', '2019-04-30 10:53:53', 0);
INSERT INTO `role` VALUES (78, 'admin', '测试一', 1, 0, '2019-04-30 10:53:45', '2020-05-27 19:59:35', 0);
COMMIT;

-- ----------------------------
-- Table structure for sale_area_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_area_detail`;
CREATE TABLE `sale_area_detail` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REGION_ID` bigint(20) DEFAULT NULL,
  `CONTINENT_ID` bigint(20) DEFAULT NULL,
  `CONTINENT_NAME` varchar(50) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `Z_CURRENCY_ID` bigint(20) DEFAULT NULL,
  `Z_CURRENCY_NAME` varchar(50) DEFAULT NULL,
  `F_CURRENCY_ID` bigint(20) DEFAULT NULL,
  `F_CURRENCY_NAME` varchar(50) DEFAULT NULL,
  `PRICE` decimal(19,2) DEFAULT NULL,
  `REGION_NAME` varchar(50) DEFAULT NULL,
  `COUNTRY_NUM` int(11) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_contract
-- ----------------------------
DROP TABLE IF EXISTS `sale_contract`;
CREATE TABLE `sale_contract` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `DEPART_ID` bigint(20) DEFAULT NULL,
  `DEPT_NAME` varchar(50) DEFAULT NULL,
  `ORG_ID` bigint(20) DEFAULT NULL,
  `ORG_NAME` varchar(50) DEFAULT NULL,
  `CONTRACT_NO` varchar(50) DEFAULT NULL,
  `CONTRACT_TYPE` bigint(20) DEFAULT NULL,
  `CONTRACT_TYPE_NAME` varchar(50) DEFAULT NULL,
  `CONTRACT_TITLE` varchar(50) DEFAULT NULL,
  `CUSTOMER` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `PAY_METHOD` bigint(20) DEFAULT NULL,
  `PAY_METHOD_NAME` varchar(50) DEFAULT NULL,
  `CLIENT_CONTRACTOR` varchar(50) DEFAULT NULL,
  `CLIENT_NAME` varchar(50) DEFAULT NULL,
  `OUR_SIGNATORY` varchar(50) DEFAULT NULL,
  `SIGN_DATE` datetime DEFAULT NULL,
  `PAYMENT_AMOUNT` decimal(19,2) DEFAULT NULL,
  `UNPAYMENT_AMOUNT` decimal(19,2) DEFAULT NULL,
  `TICKET_AMOUNT` decimal(19,2) DEFAULT NULL,
  `UNTICKET_AMOUNT` decimal(19,2) DEFAULT NULL,
  `TOTAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `CONTRACT_DATE_S` datetime DEFAULT NULL,
  `CONTRACT_DATE_E` datetime DEFAULT NULL,
  `TOKEN` varchar(50) DEFAULT NULL,
  `NEXT_TIME` datetime DEFAULT NULL,
  `SALE_CURRENCY` bigint(20) DEFAULT NULL,
  `GET_NO_DATE` datetime DEFAULT NULL,
  `BUSINESS_PERSON` varchar(50) DEFAULT NULL,
  `AREA_ID` bigint(20) DEFAULT NULL,
  `REGION_NAME` varchar(50) DEFAULT NULL,
  `PROJECT_AREA` bigint(20) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `TOTAL_POWER` varchar(50) DEFAULT NULL,
  `PLANED_AMOUNT` decimal(19,2) DEFAULT NULL,
  `UNPLANED_AMOUNT` decimal(19,2) DEFAULT NULL,
  `PROVINCE_ID` bigint(20) DEFAULT NULL,
  `CITY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `CITY_NAME` varchar(50) DEFAULT NULL,
  `DELIVERY_ADDRESS` bigint(20) DEFAULT NULL,
  `INQUIRIES_ID` bigint(20) DEFAULT NULL,
  `INQUIRIES_NO` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_contract_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_contract_detail`;
CREATE TABLE `sale_contract_detail` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SALE_CONTRACT_ID` bigint(20) DEFAULT NULL,
  `PRODUCTION_ID` bigint(20) DEFAULT NULL,
  `PRODUCTION_NO` varchar(50) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `NUM` decimal(19,2) DEFAULT NULL,
  `UNIT_PRICE` decimal(19,2) DEFAULT NULL,
  `UNIT` bigint(20) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `PRESENT_NUM` varchar(50) DEFAULT NULL,
  `POWER` varchar(50) DEFAULT NULL,
  `TOTAL_POWER` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_country_prov_city
-- ----------------------------
DROP TABLE IF EXISTS `sale_country_prov_city`;
CREATE TABLE `sale_country_prov_city` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `IS_LEAF` bit(1) DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `PID` int(11) DEFAULT NULL,
  `LEVEL_TMP` int(11) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_customer
-- ----------------------------
DROP TABLE IF EXISTS `sale_customer`;
CREATE TABLE `sale_customer` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SERIAL_VERSION_U_I_D` bigint(20) DEFAULT NULL,
  `MODIFICTION` int(11) DEFAULT NULL,
  `IS_VALID` varchar(50) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `SHORT_NAME` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(50) DEFAULT NULL,
  `PAYMENT_DAYS` int(11) DEFAULT NULL,
  `ACCOUNT_UNIT_ID` bigint(20) DEFAULT NULL,
  `AREA_ID` bigint(20) DEFAULT NULL,
  `REGION_NAME` varchar(50) DEFAULT NULL,
  `POSTAL_CODE` varchar(50) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `CUSTOMER_LEVEL_ID` bigint(20) DEFAULT NULL,
  `DUTY_PARAGRAPH` varchar(50) DEFAULT NULL,
  `INVOICE_SENDING_ADDRESS` varchar(50) DEFAULT NULL,
  `INVOICE_SENDING_PCODE` varchar(50) DEFAULT NULL,
  `DELIVERY_ADDRESS` varchar(50) DEFAULT NULL,
  `BANK_ACCOUNT` varchar(50) DEFAULT NULL,
  `REG_TIME` varchar(50) DEFAULT NULL,
  `REG_CAPITAL` varchar(50) DEFAULT NULL,
  `PAYMENT_TYPE_ID` bigint(20) DEFAULT NULL,
  `WEB_SITE` varchar(50) DEFAULT NULL,
  `CUSTOMER_INFO` varchar(50) DEFAULT NULL,
  `CURRENCY_ID` bigint(20) DEFAULT NULL,
  `DEBT_LIMIT` double DEFAULT NULL,
  `MOBILE` varchar(50) DEFAULT NULL,
  `FIXED_PHONE` varchar(50) DEFAULT NULL,
  `CUSTOMER_STATUS` int(11) DEFAULT NULL,
  `SHORT_EN_NAME` varchar(50) DEFAULT NULL,
  `JA_CURRENCY_ID` bigint(20) DEFAULT NULL,
  `ZXB_ARREARS` decimal(19,2) DEFAULT NULL,
  `ZXB_CURRENCY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_customer
-- ----------------------------
BEGIN;
INSERT INTO `sale_customer` VALUES (1, NULL, NULL, NULL, '晶澳太阳能', '晶澳', NULL, 1, 284, 3, NULL, '234300', NULL, 143, NULL, NULL, NULL, NULL, NULL, '', NULL, 139, NULL, NULL, NULL, 0, NULL, NULL, NULL, 'jing-ao', 134, 0.00, 134, NULL, 0, NULL, 200, NULL, '2020-06-01 19:42:51', NULL, NULL, NULL);
INSERT INTO `sale_customer` VALUES (2, NULL, NULL, NULL, '采普数据系统公司', '采普数据', '江苏省南京市江宁区将军大道168号', 300, 137, 3, NULL, '234100', NULL, 143, NULL, NULL, NULL, NULL, NULL, '', NULL, 139, NULL, NULL, NULL, 0, NULL, NULL, NULL, 'champlink', 134, 0.00, 134, NULL, 0, NULL, 200, NULL, '2020-06-06 17:11:17', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_customer_contacts
-- ----------------------------
DROP TABLE IF EXISTS `sale_customer_contacts`;
CREATE TABLE `sale_customer_contacts` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODIFICTION` int(11) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `POSITION` varchar(50) DEFAULT NULL,
  `MOBILE` varchar(50) DEFAULT NULL,
  `MAIL` varchar(50) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_customer_contacts
-- ----------------------------
BEGIN;
INSERT INTO `sale_customer_contacts` VALUES (1, 0, 1, '晶澳负责人', '项目经理', '13033037527', '', '', NULL, 0, NULL, 200, NULL, '2020-06-01 19:42:51', NULL, NULL, NULL);
INSERT INTO `sale_customer_contacts` VALUES (2, 0, 2, 'juery', '通讯员', '13075278656', '', '7490595', NULL, 0, NULL, 200, NULL, '2020-06-06 17:11:17', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_delivery
-- ----------------------------
DROP TABLE IF EXISTS `sale_delivery`;
CREATE TABLE `sale_delivery` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SALE_CONTRACT_ID` bigint(20) DEFAULT NULL,
  `DELIVERY_TIME` datetime DEFAULT NULL,
  `DELIVERY_NUM` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_inquiries
-- ----------------------------
DROP TABLE IF EXISTS `sale_inquiries`;
CREATE TABLE `sale_inquiries` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INQUIRY_NO` varchar(50) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `ORG_ID` bigint(20) DEFAULT NULL,
  `ORG_NAME` varchar(50) DEFAULT NULL,
  `PRODUCT_TYPE_ID` bigint(20) DEFAULT NULL,
  `PRODUCT_TYPE_NAME` varchar(50) DEFAULT NULL,
  `PRODUCT_NO` varchar(50) DEFAULT NULL,
  `ORDER_TOTAL` decimal(19,2) DEFAULT NULL,
  `SELLING_PRICE` decimal(19,2) DEFAULT NULL,
  `TRADE_MODE_ID` bigint(20) DEFAULT NULL,
  `TRADE_MODE_NAME` varchar(50) DEFAULT NULL,
  `FIRST_YEAR_ATTENUATION` varchar(50) DEFAULT NULL,
  `PAYMENT_TERM` varchar(50) DEFAULT NULL,
  `CERTIFICATION_REQUIRE` varchar(50) DEFAULT NULL,
  `OUTPUT_POWER` varchar(50) DEFAULT NULL,
  `PLATE_REQUIRE` varchar(50) DEFAULT NULL,
  `WARRANTY_REQUIRE` varchar(50) DEFAULT NULL,
  `SPECIAL_REQUIRE` varchar(50) DEFAULT NULL,
  `SALE_AREA_ID` bigint(20) DEFAULT NULL,
  `SALE_AREA_NAME` varchar(50) DEFAULT NULL,
  `SALESMAN_ID` bigint(20) DEFAULT NULL,
  `SALESMAN_NAME` varchar(50) DEFAULT NULL,
  `VIOLATE_CLAUSE` varchar(50) DEFAULT NULL,
  `INQUIRY_TIME` varchar(50) DEFAULT NULL,
  `NUMBER` bigint(20) DEFAULT NULL,
  `BOM_REQUIRE` varchar(50) DEFAULT NULL,
  `COST` varchar(50) DEFAULT NULL,
  `GROSS_PROFIT_RATIO` varchar(50) DEFAULT NULL,
  `POWER` varchar(50) DEFAULT NULL,
  `BATTERY_TYPE_ID` bigint(20) DEFAULT NULL,
  `MAIN_GATE_NUMBER` decimal(19,2) DEFAULT NULL,
  `BORDER_COLOR_ID` bigint(20) DEFAULT NULL,
  `BORDER_SPECIFICATION_ID` bigint(20) DEFAULT NULL,
  `BACKBOARD_COLOR_ID` bigint(20) DEFAULT NULL,
  `BACKBOARD_MATERIAL_ID` bigint(20) DEFAULT NULL,
  `JUNCTION_BOX_ID` bigint(20) DEFAULT NULL,
  `GLASS` varchar(50) DEFAULT NULL,
  `EVA_ID` bigint(20) DEFAULT NULL,
  `INQUIRY_TITLE` varchar(50) DEFAULT NULL,
  `ESTIMATED_SALES_AMOUNT` double DEFAULT NULL,
  `ESTIMATED_SIGNING_DATE` varchar(50) DEFAULT NULL,
  `SALES_PHASE_ID` bigint(20) DEFAULT NULL,
  `SALES_PHASE_NAME` varchar(50) DEFAULT NULL,
  `ACTUAL_FOLLOW_TIME` varchar(50) DEFAULT NULL,
  `CONTRACT_STATUS` int(11) DEFAULT NULL,
  `CONTRACT_NO` varchar(50) DEFAULT NULL,
  `CONTRACT_ID` bigint(20) DEFAULT NULL,
  `PROJECT_ADDRESS_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `PROVINCE_ID` bigint(20) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `CITY_ID` bigint(20) DEFAULT NULL,
  `CITY_NAME` varchar(50) DEFAULT NULL,
  `DISPATCH_PLACE_ID` bigint(20) DEFAULT NULL,
  `STATUS_ID` bigint(20) DEFAULT NULL,
  `DESTINATION_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `SALESMAN` varchar(50) DEFAULT NULL,
  `INQUIRY_ID` bigint(20) DEFAULT NULL,
  `INQUIRIES_ID` bigint(20) DEFAULT NULL,
  `DESTINATION_COUNTRY` varchar(50) DEFAULT NULL,
  `PROJECT_ADDRESS` varchar(50) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `PROJECT_DISTRIBUTION_ID` bigint(20) DEFAULT NULL,
  `CURRENCY_ID` bigint(20) DEFAULT NULL,
  `TOTAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  `TOTAL_POWER` decimal(19,2) DEFAULT NULL,
  `COUNTRY_PROV_CITY` varchar(50) DEFAULT NULL,
  `STATUS_NAME` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT '1',
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_inquiries
-- ----------------------------
BEGIN;
INSERT INTO `sale_inquiries` VALUES (1, 'JA200601001', 1, NULL, NULL, NULL, 781, NULL, NULL, NULL, NULL, 154, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '南方询问', NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, 287, 291, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 289, 134, 1500.00, 10000.00, NULL, NULL, 0, '1', 200, NULL, '2020-06-01 19:44:20', NULL, NULL, NULL);
INSERT INTO `sale_inquiries` VALUES (2, 'JA200606001', 2, NULL, NULL, NULL, 781, NULL, NULL, NULL, NULL, 154, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '采普订单跟踪', NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, 287, 292, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 290, 134, 8400.00, 21000.00, NULL, NULL, 0, '1', 200, NULL, '2020-06-06 17:12:58', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_inquiries_delivery_time
-- ----------------------------
DROP TABLE IF EXISTS `sale_inquiries_delivery_time`;
CREATE TABLE `sale_inquiries_delivery_time` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DELIVERY_TIME` varchar(50) DEFAULT NULL,
  `NUM` bigint(20) DEFAULT NULL,
  `INQUIRIES_ID` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_inquiries_delivery_time
-- ----------------------------
BEGIN;
INSERT INTO `sale_inquiries_delivery_time` VALUES (1, '2020-07-23', 50, 1, 0, NULL, NULL, NULL, '2020-06-01 19:44:20', NULL, NULL, NULL);
INSERT INTO `sale_inquiries_delivery_time` VALUES (2, '2020-06-17T16:00:00.000Z', 130, 2, 0, NULL, NULL, NULL, '2020-06-06 17:12:58', NULL, NULL, NULL);
INSERT INTO `sale_inquiries_delivery_time` VALUES (3, '2020-07-06', 120, 2, 0, NULL, NULL, NULL, '2020-06-06 17:12:58', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_inquiries_history
-- ----------------------------
DROP TABLE IF EXISTS `sale_inquiries_history`;
CREATE TABLE `sale_inquiries_history` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INQUIRY_NO` varchar(50) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `ORG_ID` bigint(20) DEFAULT NULL,
  `ORG_NAME` varchar(50) DEFAULT NULL,
  `PRODUCT_TYPE_ID` bigint(20) DEFAULT NULL,
  `PRODUCT_TYPE_NAME` varchar(50) DEFAULT NULL,
  `PRODUCT_NO` varchar(50) DEFAULT NULL,
  `ORDER_TOTAL` decimal(19,2) DEFAULT NULL,
  `SELLING_PRICE` decimal(19,2) DEFAULT NULL,
  `TRADE_MODE_ID` bigint(20) DEFAULT NULL,
  `TRADE_MODE_NAME` varchar(50) DEFAULT NULL,
  `FIRST_YEAR_ATTENUATION` varchar(50) DEFAULT NULL,
  `PAYMENT_TERM` varchar(50) DEFAULT NULL,
  `CERTIFICATION_REQUIRE` varchar(50) DEFAULT NULL,
  `OUTPUT_POWER` varchar(50) DEFAULT NULL,
  `PLATE_REQUIRE` varchar(50) DEFAULT NULL,
  `WARRANTY_REQUIRE` varchar(50) DEFAULT NULL,
  `SPECIAL_REQUIRE` varchar(50) DEFAULT NULL,
  `SALE_AREA_ID` bigint(20) DEFAULT NULL,
  `SALE_AREA_NAME` varchar(50) DEFAULT NULL,
  `SALESMAN_ID` bigint(20) DEFAULT NULL,
  `SALESMAN_NAME` varchar(50) DEFAULT NULL,
  `VIOLATE_CLAUSE` varchar(50) DEFAULT NULL,
  `INQUIRY_TIME` varchar(50) DEFAULT NULL,
  `NUMBER` bigint(20) DEFAULT NULL,
  `BOM_REQUIRE` varchar(50) DEFAULT NULL,
  `COST` varchar(50) DEFAULT NULL,
  `GROSS_PROFIT_RATIO` varchar(50) DEFAULT NULL,
  `POWER` varchar(50) DEFAULT NULL,
  `BATTERY_TYPE_ID` bigint(20) DEFAULT NULL,
  `MAIN_GATE_NUMBER` decimal(19,2) DEFAULT NULL,
  `BORDER_COLOR_ID` bigint(20) DEFAULT NULL,
  `BORDER_SPECIFICATION_ID` bigint(20) DEFAULT NULL,
  `BACKBOARD_COLOR_ID` bigint(20) DEFAULT NULL,
  `BACKBOARD_MATERIAL_ID` bigint(20) DEFAULT NULL,
  `JUNCTION_BOX_ID` bigint(20) DEFAULT NULL,
  `GLASS` varchar(50) DEFAULT NULL,
  `EVA_ID` bigint(20) DEFAULT NULL,
  `INQUIRY_TITLE` varchar(50) DEFAULT NULL,
  `ESTIMATED_SALES_AMOUNT` double DEFAULT NULL,
  `ESTIMATED_SIGNING_DATE` varchar(50) DEFAULT NULL,
  `SALES_PHASE_ID` bigint(20) DEFAULT NULL,
  `SALES_PHASE_NAME` varchar(50) DEFAULT NULL,
  `ACTUAL_FOLLOW_TIME` varchar(50) DEFAULT NULL,
  `CONTRACT_STATUS` int(11) DEFAULT NULL,
  `CONTRACT_NO` varchar(50) DEFAULT NULL,
  `CONTRACT_ID` bigint(20) DEFAULT NULL,
  `PROJECT_ADDRESS_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_ID` bigint(20) DEFAULT NULL,
  `COUNTRY_NAME` varchar(50) DEFAULT NULL,
  `PROVINCE_ID` bigint(20) DEFAULT NULL,
  `PROVINCE_NAME` varchar(50) DEFAULT NULL,
  `CITY_ID` bigint(20) DEFAULT NULL,
  `CITY_NAME` varchar(50) DEFAULT NULL,
  `DISPATCH_PLACE_ID` bigint(20) DEFAULT NULL,
  `STATUS_ID` bigint(20) DEFAULT NULL,
  `DESTINATION_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `SALESMAN` varchar(50) DEFAULT NULL,
  `INQUIRY_ID` bigint(20) DEFAULT NULL,
  `INQUIRIES_ID` bigint(20) DEFAULT NULL,
  `DESTINATION_COUNTRY` varchar(50) DEFAULT NULL,
  `PROJECT_ADDRESS` varchar(50) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `PROJECT_DISTRIBUTION_ID` bigint(20) DEFAULT NULL,
  `CURRENCY_ID` bigint(20) DEFAULT NULL,
  `TOTAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  `TOTAL_POWER` decimal(19,2) DEFAULT NULL,
  `COUNTRY_PROV_CITY` varchar(50) DEFAULT NULL,
  `STATUS_NAME` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_inquiries_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_inquiries_product`;
CREATE TABLE `sale_inquiries_product` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NO` varchar(50) DEFAULT NULL,
  `PRODUCT_ID` bigint(20) DEFAULT NULL,
  `CELL_NUMBER_ID` bigint(20) DEFAULT NULL,
  `CELL_NUMBER` int(11) DEFAULT NULL,
  `POWER` varchar(50) DEFAULT NULL,
  `UNIT_PRICE` decimal(19,2) DEFAULT NULL,
  `UNIT_ID` bigint(20) DEFAULT NULL,
  `NUM` bigint(20) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `BATTERY_TYPE_ID` bigint(20) DEFAULT NULL,
  `BATTERY_TYPE_NAME` varchar(50) DEFAULT NULL,
  `INQUIRIES_ID` bigint(20) DEFAULT NULL,
  `TOTAL_POWER` varchar(50) DEFAULT NULL,
  `GIVE_NUM` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_inquiries_product
-- ----------------------------
BEGIN;
INSERT INTO `sale_inquiries_product` VALUES (1, NULL, 1, 173, NULL, '100', 15.00, 278, 100, 1500.00, 156, NULL, 1, '10000', 0, 0, NULL, NULL, NULL, '2020-06-01 19:44:20', NULL, NULL, NULL);
INSERT INTO `sale_inquiries_product` VALUES (2, NULL, 1, 174, NULL, '100', 40.00, 278, 230, 8400.00, 155, NULL, 2, '21000', 20, 0, NULL, NULL, NULL, '2020-06-06 17:12:58', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_inquiry_record
-- ----------------------------
DROP TABLE IF EXISTS `sale_inquiry_record`;
CREATE TABLE `sale_inquiry_record` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INQUIRY_NO` varchar(50) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `ORG_ID` bigint(20) DEFAULT NULL,
  `ORG_NAME` varchar(50) DEFAULT NULL,
  `PRODUCT_TYPE_ID` bigint(20) DEFAULT NULL,
  `PRODUCT_NO` varchar(50) DEFAULT NULL,
  `PRODUCT_PRICE` decimal(19,2) DEFAULT NULL,
  `ORDER_TOTAL` decimal(19,2) DEFAULT NULL,
  `SELLING_PRICE` decimal(19,2) DEFAULT NULL,
  `TRADE_MODE_ID` bigint(20) DEFAULT NULL,
  `FIRST_YEAR_ATTENUATION` varchar(50) DEFAULT NULL,
  `PAYMENT_TERM` varchar(50) DEFAULT NULL,
  `CERTIFICATION_REQUIRE` varchar(50) DEFAULT NULL,
  `OUTPUT_POWER` varchar(50) DEFAULT NULL,
  `PLATE_REQUIRE` varchar(50) DEFAULT NULL,
  `WARRANTY_REQUIRE` varchar(50) DEFAULT NULL,
  `SPECIAL_REQUIRE` varchar(50) DEFAULT NULL,
  `SALE_AREA_ID` bigint(20) DEFAULT NULL,
  `SALE_AREA_NAME` varchar(50) DEFAULT NULL,
  `SALESMAN_ID` bigint(20) DEFAULT NULL,
  `SALESMAN_NAME` varchar(50) DEFAULT NULL,
  `VIOLATE_CLAUSE` varchar(50) DEFAULT NULL,
  `INQUIRY_TIME` varchar(50) DEFAULT NULL,
  `NUMBER` bigint(20) DEFAULT NULL,
  `BOM_REQUIRE` varchar(50) DEFAULT NULL,
  `COST` varchar(50) DEFAULT NULL,
  `GROSS_PROFIT_RATIO` varchar(50) DEFAULT NULL,
  `POWER` varchar(50) DEFAULT NULL,
  `BATTERY_TYPE_ID` bigint(20) DEFAULT NULL,
  `MAIN_GATE_NUMBER` decimal(19,2) DEFAULT NULL,
  `BORDER_COLOR_ID` bigint(20) DEFAULT NULL,
  `BORDER_SPECIFICATION_ID` bigint(20) DEFAULT NULL,
  `BACKBOARD_COLOR_ID` bigint(20) DEFAULT NULL,
  `BACKBOARD_MATERIAL_ID` bigint(20) DEFAULT NULL,
  `JUNCTION_BOX_ID` bigint(20) DEFAULT NULL,
  `GLASS` varchar(50) DEFAULT NULL,
  `EVA_ID` bigint(20) DEFAULT NULL,
  `INQUIRY_TITLE` varchar(50) DEFAULT NULL,
  `ESTIMATED_SALES_AMOUNT` double DEFAULT NULL,
  `ESTIMATED_SIGNING_DATE` datetime DEFAULT NULL,
  `SALES_PHASE_ID` bigint(20) DEFAULT NULL,
  `ACTUAL_FOLLOW_TIME` datetime DEFAULT NULL,
  `CONTRACT_STATUS` int(11) DEFAULT NULL,
  `CONTRACT_NO` varchar(50) DEFAULT NULL,
  `CONTRACT_ID` bigint(20) DEFAULT NULL,
  `PROJECT_ADDRESS_ID` bigint(20) DEFAULT NULL,
  `PROJECT_ADDRESS` varchar(50) DEFAULT NULL,
  `DESTINATION_COUNTRY_ID` bigint(20) DEFAULT NULL,
  `DESTINATION_COUNTRY` varchar(50) DEFAULT NULL,
  `SALESMAN` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_inquiry_record_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_inquiry_record_detail`;
CREATE TABLE `sale_inquiry_record_detail` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INQUIRY_ID` bigint(20) DEFAULT NULL,
  `INQUIRY_PERSON` varchar(50) DEFAULT NULL,
  `PRODUCT_ID` bigint(20) DEFAULT NULL,
  `PRODUCT_NO` varchar(50) DEFAULT NULL,
  `PRODUCT_NAME` varchar(50) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `PRICE` decimal(19,2) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_invoice_form
-- ----------------------------
DROP TABLE IF EXISTS `sale_invoice_form`;
CREATE TABLE `sale_invoice_form` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SALE_CONTRACT_ID` bigint(20) DEFAULT NULL,
  `KPRQ` datetime DEFAULT NULL,
  `INVOICE_CONTEXT` varchar(50) DEFAULT NULL,
  `INVOICE_AMOUNT` decimal(19,2) DEFAULT NULL,
  `INVOICE_TYPE_ID` bigint(20) DEFAULT NULL,
  `INVOICE_TYPE_NAME` varchar(50) DEFAULT NULL,
  `INVOICE_NO` varchar(50) DEFAULT NULL,
  `INVOICE_USER_ID` bigint(20) DEFAULT NULL,
  `INVOICE_USER_NAME` varchar(50) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_pay_plan
-- ----------------------------
DROP TABLE IF EXISTS `sale_pay_plan`;
CREATE TABLE `sale_pay_plan` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SALE_CONTRACT_ID` bigint(20) DEFAULT NULL,
  `SALE_CONTRACT_NO` varchar(50) DEFAULT NULL,
  `PAY_DATE` datetime DEFAULT NULL,
  `PAY_AMOUNT` decimal(19,2) DEFAULT NULL,
  `PAY_METHOD` bigint(20) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `period` varchar(20) DEFAULT NULL,
  `plan_payment_amount` bigint(20) DEFAULT NULL,
  `plan_unPayment_amount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_pay_plan_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_pay_plan_detail`;
CREATE TABLE `sale_pay_plan_detail` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PAY_PLAN_ID` bigint(20) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `PERIOD` int(11) DEFAULT NULL,
  `PAY_METHOD` bigint(20) DEFAULT NULL,
  `PAY_METHOD_NAME` varchar(50) DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  `TYPE_NAME` varchar(50) DEFAULT NULL,
  `PAYEE` bigint(20) DEFAULT NULL,
  `PAYEE_NAME` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `sale_contract_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_payment_plan
-- ----------------------------
DROP TABLE IF EXISTS `sale_payment_plan`;
CREATE TABLE `sale_payment_plan` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SALE_CONTRACT_ID` bigint(20) DEFAULT NULL,
  `SALE_CONTRACT_NO` varchar(50) DEFAULT NULL,
  `PERIOD` varchar(50) DEFAULT NULL,
  `PAY_DATE` datetime DEFAULT NULL,
  `PAY_AMOUNT` decimal(19,2) DEFAULT NULL,
  `PAY_METHOD` bigint(20) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `CUSTOMER_ID` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `CONTRACT_TITLE_ID` bigint(20) DEFAULT NULL,
  `OVER_DATE` varchar(50) DEFAULT NULL,
  `_APPLICABLE_AMOUNT` decimal(19,2) DEFAULT NULL,
  `PLAN_PAYMENT_AMOUNT` decimal(19,2) DEFAULT NULL,
  `PLAN_UN_PAYMENT_AMOUNT` decimal(19,2) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_payment_plan_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_payment_plan_detail`;
CREATE TABLE `sale_payment_plan_detail` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PAY_PLAN_ID` bigint(20) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `PERIOD` varchar(50) DEFAULT NULL,
  `PAY_METHOD` bigint(20) DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  `PAYEE` bigint(20) DEFAULT NULL,
  `PAYEE_NAME` varchar(50) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `SALE_CONTRACT_ID` bigint(20) DEFAULT NULL,
  `CONTRACT_TITLE` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_product`;
CREATE TABLE `sale_product` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BOM_ID` bigint(20) DEFAULT NULL,
  `BOM_NO` varchar(50) DEFAULT NULL,
  `PRODUCT_NO` varchar(50) DEFAULT NULL,
  `SILICON_TYPE` varchar(50) DEFAULT NULL,
  `SILICON_TYPE_ID` bigint(20) DEFAULT NULL,
  `CELL_NUMBER` varchar(50) DEFAULT NULL,
  `CELL_NUMBER_ID` bigint(20) DEFAULT NULL,
  `MUDULE_TYPE` varchar(50) DEFAULT NULL,
  `MUDULE_TYPE_ID` bigint(20) DEFAULT NULL,
  `MUDULE_CODE` varchar(50) DEFAULT NULL,
  `MUDULE_CODE_ID` bigint(20) DEFAULT NULL,
  `RATED_POWER` varchar(50) DEFAULT NULL,
  `CELL_TECHNOLOGY` varchar(50) DEFAULT NULL,
  `CELL_TECHNOLOGY_ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_product
-- ----------------------------
BEGIN;
INSERT INTO `sale_product` VALUES (1, NULL, NULL, 'JAP60S06-100/HT', NULL, 171, NULL, 173, NULL, 176, NULL, 183, '100', NULL, 188, NULL, 0, '0', 1, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_region
-- ----------------------------
DROP TABLE IF EXISTS `sale_region`;
CREATE TABLE `sale_region` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `REGION_NAME` varchar(50) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `AREAS_NAME` varchar(50) DEFAULT NULL,
  `COUNTRY_NAMES` varchar(50) DEFAULT NULL,
  `TOKEN` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_region
-- ----------------------------
BEGIN;
INSERT INTO `sale_region` VALUES (3, NULL, '中国南方区域', '2020-06-01 19:26:56', NULL, NULL, NULL, 0, NULL, 200, NULL, '2020-06-01 19:26:56', NULL, NULL, NULL);
INSERT INTO `sale_region` VALUES (4, NULL, '中国北方区域', '2020-06-01 19:39:55', NULL, NULL, NULL, 0, NULL, 200, NULL, '2020-06-01 19:39:55', NULL, NULL, NULL);
INSERT INTO `sale_region` VALUES (5, NULL, '美国东部', '2020-06-01 19:40:13', NULL, NULL, NULL, 0, NULL, 200, NULL, '2020-06-01 19:40:13', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_region_dtls
-- ----------------------------
DROP TABLE IF EXISTS `sale_region_dtls`;
CREATE TABLE `sale_region_dtls` (
  `region_id` bigint(20) DEFAULT NULL,
  `continent_id` bigint(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `z_currency_id` bigint(20) DEFAULT NULL,
  `f_currency_id` bigint(20) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `row_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_region_dtls
-- ----------------------------
BEGIN;
INSERT INTO `sale_region_dtls` VALUES (3, 130, 132, 134, NULL, 50, 0, NULL, NULL, NULL, '2020-06-01 19:39:36', NULL, NULL, NULL, NULL);
INSERT INTO `sale_region_dtls` VALUES (4, 130, 132, 134, NULL, 100, 0, NULL, 200, NULL, '2020-06-01 19:39:55', NULL, NULL, NULL, NULL);
INSERT INTO `sale_region_dtls` VALUES (5, 131, 133, 135, NULL, 25, 0, NULL, NULL, NULL, '2020-06-01 19:40:34', NULL, NULL, NULL, NULL);
INSERT INTO `sale_region_dtls` VALUES (5, 136, 207, 135, NULL, 30, 0, NULL, NULL, NULL, '2020-06-01 19:40:34', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sale_stockup
-- ----------------------------
DROP TABLE IF EXISTS `sale_stockup`;
CREATE TABLE `sale_stockup` (
  `ROW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STOCK_NO` varchar(50) DEFAULT NULL,
  `SALESMAN` varchar(50) DEFAULT NULL,
  `STOCK_ADDRESS` bigint(20) DEFAULT NULL,
  `STOCK_DATE` datetime DEFAULT NULL,
  `OA_NO` varchar(50) DEFAULT NULL,
  `CUSTOMER` bigint(20) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `CONTRACT_NO` varchar(50) DEFAULT NULL,
  `PAYMENT_INFO` bigint(20) DEFAULT NULL,
  `CONTRACT_ADDRESS` varchar(50) DEFAULT NULL,
  `SEND_NUM` decimal(19,2) DEFAULT NULL,
  `NEW_COMPONENTMODEL` varchar(50) DEFAULT NULL,
  `OLD_COMPONENTMODEL` varchar(50) DEFAULT NULL,
  `COMPONENT_TYPE` varchar(50) DEFAULT NULL,
  `BATTERY_PROCESS` varchar(50) DEFAULT NULL,
  `PACKAGE_NUM` decimal(19,2) DEFAULT NULL,
  `COATING` varchar(50) DEFAULT NULL,
  `COMPONENT_LEVEL` varchar(50) DEFAULT NULL,
  `SINGLE_POWER` varchar(50) DEFAULT NULL,
  `TYPE_A` bigint(20) DEFAULT NULL,
  `TOTAL_SEND_NUM` decimal(19,2) DEFAULT NULL,
  `UNIT` varchar(50) DEFAULT NULL,
  `DELIVERY_METHOD` bigint(20) DEFAULT NULL,
  `MODE` varchar(50) DEFAULT NULL,
  `AL_PROCESS` varchar(50) DEFAULT NULL,
  `JUNCTION_BOX` varchar(50) DEFAULT NULL,
  `SHIPMENTS_INSPECTION` varchar(50) DEFAULT NULL,
  `POWER_REQUIRE` varchar(50) DEFAULT NULL,
  `OTHER_INSTRUCTION` varchar(50) DEFAULT NULL,
  `IS_DECLARATION` varchar(50) DEFAULT NULL,
  `INVOICE_NO` varchar(50) DEFAULT NULL,
  `CUSTOMER_REQUIREMENT_EXW` varchar(50) DEFAULT NULL,
  `CUSTOMER_REQUIREMENT_ETD` varchar(50) DEFAULT NULL,
  `CUSTOMER_REQUIREMENT_ETA` varchar(50) DEFAULT NULL,
  `GOODS_AGENT` varchar(50) DEFAULT NULL,
  `SHIPMENT_PORT` varchar(50) DEFAULT NULL,
  `DESTINATION_PORT` varchar(50) DEFAULT NULL,
  `CUSTOMER_ADDRESS` varchar(50) DEFAULT NULL,
  `CONTACTS` varchar(50) DEFAULT NULL,
  `TELEPHONE` varchar(50) DEFAULT NULL,
  `DESTINATION_COUNTRY` varchar(50) DEFAULT NULL,
  `BOAT_COMPANY` varchar(50) DEFAULT NULL,
  `TELEX` varchar(50) DEFAULT NULL,
  `IS_COVER` varchar(50) DEFAULT NULL,
  `AL_VENDER` varchar(50) DEFAULT NULL,
  `LINE_NUM` decimal(19,2) DEFAULT NULL,
  `EVA` bigint(20) DEFAULT NULL,
  `GLASS` varchar(50) DEFAULT NULL,
  `WIRING_HEAD` varchar(50) DEFAULT NULL,
  `NAMEPLATE` varchar(50) DEFAULT NULL,
  `BOMID` varchar(50) DEFAULT NULL,
  `POWER` varchar(50) DEFAULT NULL,
  `TRADE_TYPE` varchar(50) DEFAULT NULL,
  `PACKAGE_METHOD` varchar(50) DEFAULT NULL,
  `LINE_LENGTH` varchar(50) DEFAULT NULL,
  `HOLE_LOCATION` varchar(50) DEFAULT NULL,
  `CHECK_DATE` datetime DEFAULT NULL,
  `SURE_ONE` varchar(50) DEFAULT NULL,
  `SURE_TWO` varchar(50) DEFAULT NULL,
  `PLAN_DATE` datetime DEFAULT NULL,
  `ACTUAL_DATE` datetime DEFAULT NULL,
  `BLOCK_NUM` decimal(19,2) DEFAULT NULL,
  `TILE_NUM` decimal(19,2) DEFAULT NULL,
  `UNSENT_NUM` decimal(19,2) DEFAULT NULL,
  `DECLARE_TIME` datetime DEFAULT NULL,
  `DECLARE_BROKER` varchar(50) DEFAULT NULL,
  `DECLARE_NO` varchar(50) DEFAULT NULL,
  `LOAD_TIME` datetime DEFAULT NULL,
  `LEAVE_TIME` datetime DEFAULT NULL,
  `LOAD_NO` varchar(50) DEFAULT NULL,
  `INVOICE` varchar(50) DEFAULT NULL,
  `BILL_NO` varchar(50) DEFAULT NULL,
  `INVOICE_BILL` varchar(50) DEFAULT NULL,
  `INVOICE_BILL_COPY` varchar(50) DEFAULT NULL,
  `POLICY` varchar(50) DEFAULT NULL,
  `DN` varchar(50) DEFAULT NULL,
  `INV` varchar(50) DEFAULT NULL,
  `BL` varchar(50) DEFAULT NULL,
  `JCUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `del_flag` int(10) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_name` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update_by` bigint(20) DEFAULT NULL,
  `update_name` varchar(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `cellphone_` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  `verifycode_` varchar(64) DEFAULT NULL COMMENT '验证码',
  `sms_content_` varchar(128) DEFAULT NULL COMMENT '短信内容',
  `valid_time_` datetime DEFAULT NULL COMMENT '有效截止时间',
  `sended_time_` datetime DEFAULT NULL COMMENT '短信发出时间',
  `status_` int(2) DEFAULT '-1' COMMENT '是否有效，1有效，-1无效',
  `created_time_` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='短信';

-- ----------------------------
-- Records of sms
-- ----------------------------
BEGIN;
INSERT INTO `sms` VALUES (1, '13212312323', '123456', '这是一条验证码：123456', '2018-10-23 15:23:19', '2019-03-18 15:37:58', 1, NULL);
INSERT INTO `sms` VALUES (2, '13233333334', '773793', '修改密码验证码：773793，5分钟内有效', '2019-03-18 15:42:58', '2019-03-18 15:37:58', -1, NULL);
INSERT INTO `sms` VALUES (3, '13233333334', '448342', '修改密码验证码：448342，5分钟内有效', '2019-03-18 15:43:26', '2019-03-18 15:38:26', -1, NULL);
INSERT INTO `sms` VALUES (4, '13233333334', '143329', '登录验证码：143329，5分钟内有效', '2019-03-18 15:45:22', '2019-03-18 15:40:22', -1, NULL);
INSERT INTO `sms` VALUES (5, '13233333334', '336934', '修改密码验证码：336934，5分钟内有效', '2019-03-18 17:27:33', '2019-03-18 17:22:33', -1, NULL);
INSERT INTO `sms` VALUES (6, '13233333334', '318097', '修改密码验证码：318097，5分钟内有效', '2019-03-18 17:28:10', '2019-03-18 17:23:10', -1, NULL);
INSERT INTO `sms` VALUES (7, '13233333334', '207159', '修改密码验证码：207159，5分钟内有效', '2019-03-18 17:29:07', '2019-03-18 17:24:07', -1, NULL);
INSERT INTO `sms` VALUES (8, '13233333334', '800180', '修改密码验证码：800180，5分钟内有效', '2019-03-18 17:30:03', '2019-03-18 17:25:03', -1, NULL);
INSERT INTO `sms` VALUES (9, '13233333334', '547301', '修改密码验证码：547301，5分钟内有效', '2019-03-18 17:39:55', '2019-03-18 17:34:55', -1, NULL);
INSERT INTO `sms` VALUES (10, '13233333334', '800165', '修改密码验证码：800165，5分钟内有效', '2019-03-18 17:42:08', '2019-03-18 17:37:08', -1, NULL);
INSERT INTO `sms` VALUES (11, '13233333334', '617756', '修改密码验证码：617756，5分钟内有效', '2019-03-18 17:43:22', '2019-03-18 17:38:22', -1, NULL);
INSERT INTO `sms` VALUES (12, '13233333334', '190575', '修改密码验证码：190575，5分钟内有效', '2019-03-18 17:44:13', '2019-03-18 17:39:13', -1, NULL);
INSERT INTO `sms` VALUES (13, '13233333334', '454970', '修改密码验证码：454970，5分钟内有效', '2019-03-19 11:43:24', '2019-03-19 11:38:24', -1, NULL);
INSERT INTO `sms` VALUES (14, '13233333334', '358987', '修改密码验证码：358987，5分钟内有效', '2019-03-19 11:47:49', '2019-03-19 11:42:49', -1, NULL);
INSERT INTO `sms` VALUES (15, '13233333334', '684536', '修改密码验证码：684536，5分钟内有效', '2019-03-19 11:48:22', '2019-03-19 11:43:22', -1, NULL);
INSERT INTO `sms` VALUES (16, '13233333334', '523089', '修改密码验证码：523089，5分钟内有效', '2019-03-19 11:49:06', '2019-03-19 11:44:06', -1, NULL);
INSERT INTO `sms` VALUES (17, '13233333334', '971673', '修改密码验证码：971673，5分钟内有效', '2019-03-19 11:50:33', '2019-03-19 11:45:33', -1, NULL);
INSERT INTO `sms` VALUES (18, '13233333334', '356691', '修改密码验证码：356691，5分钟内有效', '2019-03-19 11:51:02', '2019-03-19 11:46:02', -1, NULL);
INSERT INTO `sms` VALUES (19, '13233333334', '211691', '修改密码验证码：211691，5分钟内有效', '2019-03-19 11:52:25', '2019-03-19 11:47:25', -1, NULL);
INSERT INTO `sms` VALUES (20, '13233333334', '081132', '修改密码验证码：081132，5分钟内有效', '2019-03-19 15:07:28', '2019-03-19 15:02:28', -1, NULL);
INSERT INTO `sms` VALUES (21, '13233333334', '130942', '修改密码验证码：130942，5分钟内有效', '2019-03-19 15:07:59', '2019-03-19 15:02:59', -1, NULL);
INSERT INTO `sms` VALUES (22, '13233333334', '702346', '修改密码验证码：702346，5分钟内有效', '2019-03-19 15:09:19', '2019-03-19 15:04:19', -1, NULL);
INSERT INTO `sms` VALUES (23, '13233333334', '074946', '修改密码验证码：074946，5分钟内有效', '2019-03-19 15:12:03', '2019-03-19 15:07:03', -1, NULL);
INSERT INTO `sms` VALUES (24, '13233333334', '766868', '修改密码验证码：766868，5分钟内有效', '2019-03-19 15:12:27', '2019-03-19 15:07:27', -1, NULL);
INSERT INTO `sms` VALUES (25, '13233333334', '163518', '修改密码验证码：163518，5分钟内有效', '2019-03-19 15:15:21', '2019-03-19 15:10:21', -1, NULL);
INSERT INTO `sms` VALUES (26, '13233333334', '303170', '修改密码验证码：303170，5分钟内有效', '2019-03-19 15:22:05', '2019-03-19 15:17:05', -1, NULL);
INSERT INTO `sms` VALUES (27, '13233333334', '312150', '修改密码验证码：312150，5分钟内有效', '2019-03-19 15:23:11', '2019-03-19 15:18:11', -1, NULL);
INSERT INTO `sms` VALUES (28, '13233333334', '226180', '修改密码验证码：226180，5分钟内有效', '2019-03-19 15:26:00', '2019-03-19 15:21:00', -1, NULL);
INSERT INTO `sms` VALUES (29, '13233333334', '253082', '修改密码验证码：253082，5分钟内有效', '2019-03-19 15:27:11', '2019-03-19 15:22:11', -1, NULL);
INSERT INTO `sms` VALUES (30, '13233333334', '039091', '修改密码验证码：039091，5分钟内有效', '2019-03-20 13:38:24', '2019-03-20 13:33:24', -1, NULL);
INSERT INTO `sms` VALUES (31, '13233333334', '644448', '修改密码验证码：644448，5分钟内有效', '2019-03-20 16:28:27', '2019-03-20 16:23:27', -1, NULL);
INSERT INTO `sms` VALUES (32, '13233333334', '809095', '登录验证码：809095，5分钟内有效', '2019-03-20 16:29:01', '2019-03-20 16:24:01', -1, NULL);
INSERT INTO `sms` VALUES (33, '13233333334', '129081', '登录验证码：129081，5分钟内有效', '2019-03-20 16:35:29', '2019-03-20 16:30:29', -1, NULL);
INSERT INTO `sms` VALUES (34, '13233333334', '548174', '登录验证码：548174，5分钟内有效', '2019-03-20 16:49:29', '2019-03-20 16:44:29', -1, NULL);
INSERT INTO `sms` VALUES (35, '13233333334', '847565', '登录验证码：847565，5分钟内有效', '2019-03-20 17:13:21', '2019-03-20 17:08:21', -1, NULL);
INSERT INTO `sms` VALUES (36, '13233333334', '108272', '登录验证码：108272，5分钟内有效', '2019-03-20 17:28:03', '2019-03-20 17:23:03', -1, NULL);
INSERT INTO `sms` VALUES (37, '13233333334', '914865', '登录验证码：914865，5分钟内有效', '2019-03-20 17:28:16', '2019-03-20 17:23:16', -1, NULL);
INSERT INTO `sms` VALUES (38, '13233333334', '629311', '登录验证码：629311，5分钟内有效', '2019-03-20 17:34:02', '2019-03-20 17:29:02', -1, NULL);
INSERT INTO `sms` VALUES (39, '13233333334', '251034', '登录验证码：251034，5分钟内有效', '2019-03-20 17:35:59', '2019-03-20 17:30:59', -1, NULL);
INSERT INTO `sms` VALUES (40, '13233333334', '210857', '登录验证码：210857，5分钟内有效', '2019-03-20 17:37:51', '2019-03-20 17:32:51', -1, NULL);
INSERT INTO `sms` VALUES (41, '13233333334', '583307', '登录验证码：583307，5分钟内有效', '2019-03-20 17:39:42', '2019-03-20 17:34:42', -1, NULL);
INSERT INTO `sms` VALUES (42, 'test1', '743269', '登录验证码：743269，5分钟内有效', '2019-03-20 17:40:23', '2019-03-20 17:35:23', 1, NULL);
INSERT INTO `sms` VALUES (43, '13233333334', '809989', '登录验证码：809989，5分钟内有效', '2019-03-20 17:56:15', '2019-03-20 17:51:15', -1, NULL);
INSERT INTO `sms` VALUES (44, '13233333334', '973461', '登录验证码：973461，5分钟内有效', '2019-03-20 18:12:54', '2019-03-20 18:07:54', -1, NULL);
INSERT INTO `sms` VALUES (45, '13233333334', '901797', '登录验证码：901797，5分钟内有效', '2019-03-21 09:22:34', '2019-03-21 09:17:34', -1, NULL);
INSERT INTO `sms` VALUES (46, '13233333334', '110737', '登录验证码：110737，5分钟内有效', '2019-03-22 15:04:40', '2019-03-22 14:59:40', -1, NULL);
INSERT INTO `sms` VALUES (47, '13233333334', '024021', '登录验证码：024021，5分钟内有效', '2019-03-25 14:26:52', '2019-03-25 14:21:52', 1, NULL);
INSERT INTO `sms` VALUES (48, '15651680895', '672695', '登录验证码：672695，5分钟内有效', '2019-03-26 14:11:57', '2019-03-26 14:06:57', -1, NULL);
INSERT INTO `sms` VALUES (49, '18761626864', '177776', '登录验证码：177776，5分钟内有效', '2019-03-26 14:13:41', '2019-03-26 14:08:41', 1, NULL);
INSERT INTO `sms` VALUES (50, '17755355877', '831273', '登录验证码：831273，5分钟内有效', '2019-03-26 14:14:32', '2019-03-26 14:09:32', 1, NULL);
INSERT INTO `sms` VALUES (51, '15651680895', '916236', '修改密码验证码：916236，5分钟内有效', '2019-03-26 14:16:50', '2019-03-26 14:11:50', -1, NULL);
INSERT INTO `sms` VALUES (52, '15651680895', '591247', '登录验证码：591247，5分钟内有效', '2019-04-09 17:04:22', '2019-04-09 16:59:22', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for stf_address
-- ----------------------------
DROP TABLE IF EXISTS `stf_address`;
CREATE TABLE `stf_address` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '通讯信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `work_phone` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `mobile` varchar(100) DEFAULT NULL COMMENT '移动电话',
  `home_phone` varchar(100) DEFAULT NULL COMMENT '家庭电话',
  `email_work` varchar(100) DEFAULT NULL COMMENT '电子邮件（工作）',
  `home_address` varchar(100) DEFAULT NULL COMMENT '家庭地址',
  `email_personal` varchar(100) DEFAULT NULL COMMENT '电子邮件（个人）',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='通讯信息表';

-- ----------------------------
-- Records of stf_address
-- ----------------------------
BEGIN;
INSERT INTO `stf_address` VALUES (3, 1, '1', '1', '1', '1', '1', '1', 0);
INSERT INTO `stf_address` VALUES (4, 95, '3', '13', '3', '3', '3', '3', 0);
INSERT INTO `stf_address` VALUES (6, 110, NULL, '12345678901', NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_address` VALUES (7, 158, '', '15950773352', '', 'liman@jasolar.com', '江苏省赣榆县罗阳镇', '', 0);
INSERT INTO `stf_address` VALUES (8, 160, '', '18805125211', '', 'yaoxq@jasolar.com', '江苏省东海县牛山镇振兴南路28号3幢一单元302室', '', 0);
INSERT INTO `stf_address` VALUES (9, 199, '', '18632098983', '', NULL, '', '', 0);
INSERT INTO `stf_address` VALUES (11, 198, NULL, NULL, NULL, '1936243887', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_adjustment_work
-- ----------------------------
DROP TABLE IF EXISTS `stf_adjustment_work`;
CREATE TABLE `stf_adjustment_work` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '异动ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `change_type` varchar(255) DEFAULT NULL COMMENT '变动类型:1、内部调动:INNER_MOBILIZATION；2、借调:TEMPORARILY；3、外派:EXPATRIATE；4、退休:RETIRE；5、离职:DIMISSION；6、重新雇佣:REHIRE；7、返聘:RETURN_REHIRE；',
  `change_date` datetime DEFAULT NULL COMMENT '内部调动时间',
  `change_reason` varchar(100) DEFAULT NULL COMMENT '变动原因/描述',
  `original_base` varchar(100) DEFAULT NULL COMMENT '原基地',
  `original_dept` varchar(100) DEFAULT NULL COMMENT '原部门',
  `original_position` varchar(100) DEFAULT NULL COMMENT '原职衔',
  `original_grade` varchar(100) DEFAULT NULL COMMENT '原职等',
  `original_rank` varchar(100) DEFAULT NULL COMMENT '原职级',
  `new_base` varchar(100) DEFAULT NULL COMMENT '新基地',
  `new_dept` varchar(100) DEFAULT NULL COMMENT '新部门',
  `new_position` varchar(100) DEFAULT NULL COMMENT '新职衔',
  `new_grade` varchar(100) DEFAULT NULL COMMENT '新职等',
  `new_rank` varchar(100) DEFAULT NULL COMMENT '新职级',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  `is_wage_adjustment` varchar(255) DEFAULT NULL COMMENT '是否调薪',
  `temporarily_type` varchar(255) DEFAULT NULL COMMENT '借调类型',
  `temporarily_start_date` datetime DEFAULT NULL COMMENT '借调开始日期',
  `temporarily_end_date` datetime DEFAULT NULL COMMENT '借调结束日期',
  `expatriate_type` varchar(255) DEFAULT NULL COMMENT '外派类型',
  `expatriate_start_date` datetime DEFAULT NULL COMMENT '外派开始日期',
  `expatriate_end_date` datetime DEFAULT NULL COMMENT '外派结束日期',
  `job_content` varchar(255) DEFAULT NULL COMMENT '工作内容',
  `retire_type` varchar(255) DEFAULT NULL COMMENT '退休类型',
  `retire_date` datetime DEFAULT NULL COMMENT '退休日期',
  `retire_management_unit` varchar(255) DEFAULT NULL COMMENT '退休后管理单位',
  `examine_unit` varchar(255) DEFAULT NULL COMMENT '审批单位',
  `approval_number` varchar(255) DEFAULT NULL COMMENT '批准文号',
  `other_explain` varchar(255) DEFAULT NULL COMMENT '其他说明',
  `leave_type` varchar(255) DEFAULT NULL COMMENT '离职类型',
  `leave_reason` varchar(255) DEFAULT NULL COMMENT '离职原因',
  `leave_date` datetime DEFAULT NULL COMMENT '离职日期',
  `leave_direction` varchar(255) DEFAULT NULL COMMENT '离职后去向',
  `is_blacklist` varchar(255) DEFAULT NULL COMMENT '加入黑名单',
  `blacklist_reason` varchar(255) DEFAULT NULL COMMENT '加入黑名单原因',
  `is_lieu_notice_wages` varchar(255) DEFAULT NULL COMMENT '是否有代通知金',
  `is_compensatory_payment` varchar(255) DEFAULT NULL COMMENT '是否有补偿金',
  `compensation_month` varchar(255) DEFAULT NULL COMMENT '补偿月数',
  `amount_compensation` varchar(255) DEFAULT NULL COMMENT '补偿金额',
  `is_perform_competitive_restriction` varchar(255) DEFAULT NULL COMMENT '是否履行竞业限制',
  `start_perform_date` datetime DEFAULT NULL COMMENT '开始履行时间',
  `end_perform_date` datetime DEFAULT NULL COMMENT '结束履行时间',
  `is_train_agreement` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '是否签订培训协议',
  `under_service_compensate` varchar(255) DEFAULT NULL COMMENT '未满服务期赔偿金额',
  `job_status` varchar(255) DEFAULT NULL COMMENT '在职状态',
  `work_date` datetime DEFAULT NULL COMMENT '用工日期',
  `entry_before_age` varchar(255) DEFAULT NULL COMMENT '入职前工龄',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `real_end_time` datetime DEFAULT NULL COMMENT '实际结束时间',
  `job_title` varchar(255) DEFAULT NULL COMMENT '职称',
  `staff_classify` varchar(255) DEFAULT NULL COMMENT '员工分类',
  `original_staff_no` varchar(100) DEFAULT NULL COMMENT '原员工工号',
  `new_staff_no` varchar(100) DEFAULT NULL COMMENT '现员工工号',
  `new_dept_id` bigint(10) DEFAULT NULL COMMENT '部门id',
  `status` int(2) DEFAULT '0' COMMENT '是否处理(0未处理,1已处理)',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工异动记录表';

-- ----------------------------
-- Records of stf_adjustment_work
-- ----------------------------
BEGIN;
INSERT INTO `stf_adjustment_work` VALUES (1, 108, 'DIMISSION', NULL, NULL, '南京基地', '北京部门', '职位14555', '经理', 'M10', '南京基地', '北京部门', '职位14555', '经理', 'M10', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '主动离职', '不适应工作环境', '2018-10-09 00:00:00', '1', '否', NULL, '是', '否', NULL, NULL, '是', '2018-10-09 00:00:00', '2018-10-09 00:00:00', '是', '10', NULL, NULL, NULL, 0, '2018-10-09 16:47:22', NULL, NULL, NULL, 'BJJD000021', 'BJJD000021', 191, 0);
INSERT INTO `stf_adjustment_work` VALUES (2, 107, 'RETIRE', NULL, NULL, '北京基地', '南京部门', '444', '副总经理', 'M7', '总部', '人力资源部', '职位14555', '经理', 'M8', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '在职', '2018-10-09 00:00:00', '2', 0, '2018-10-09 16:48:32', '2018-10-10 10:03:28', NULL, '白领', '12345678', '12345678', 3, 0);
INSERT INTO `stf_adjustment_work` VALUES (3, 108, 'DIMISSION', '2018-10-10 10:08:46', '', '南京基地', '北京部门', '职位14555', '经理', 'M10', '南京基地', '北京部门', '职位14555', '经理', 'M10', 0, '', '', '2018-10-10 10:09:03', '2018-10-10 10:09:07', '', '2018-10-10 10:09:14', '2018-10-10 10:09:17', '', '', '2018-10-10 10:09:31', '', '', '', '', '主动离职', '不适应工作环境', '2018-10-09 00:00:00', '1', '否', '', '是', '否', '', '', '是', '2018-10-09 00:00:00', '2018-10-09 00:00:00', '是', '10', '', '2018-10-10 10:10:05', '', 0, '2018-10-09 16:47:22', '2018-10-10 10:10:17', '', '', 'BJJD000021', 'BJJD000021', 191, 0);
INSERT INTO `stf_adjustment_work` VALUES (4, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (5, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (6, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (7, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (8, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (9, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (10, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (11, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (12, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (13, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (14, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (15, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (16, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (17, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (18, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (19, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (20, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (21, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (22, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (23, 170, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (24, 171, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (25, 171, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (26, 171, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (27, 171, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (28, 172, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (29, 172, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (30, 172, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (31, 172, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (32, 177, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (33, 177, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (34, 177, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (35, 177, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (36, 178, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (37, 178, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (38, 178, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (39, 178, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (40, 179, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (41, 179, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (42, 179, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (43, 179, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (44, 180, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (45, 180, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (46, 180, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (47, 180, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (48, 181, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (49, 181, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (50, 181, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (51, 181, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (52, 182, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (53, 182, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (54, 182, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (55, 182, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (56, 183, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (57, 183, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (58, 183, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (59, 183, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (60, 184, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (61, 184, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (62, 184, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (63, 184, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (64, 185, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (65, 185, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (66, 185, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (67, 185, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (68, 188, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (69, 188, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (70, 188, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (71, 188, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (72, 189, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (73, 189, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (74, 189, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (75, 189, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (76, 190, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (77, 190, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (78, 190, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (79, 190, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (80, 191, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (81, 191, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (82, 191, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (83, 191, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (84, 195, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (85, 195, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (86, 195, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (87, 195, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (88, 196, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (89, 196, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (90, 196, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (91, 196, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (92, 197, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (93, 197, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (94, 197, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (95, 197, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (96, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (97, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (98, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (99, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (100, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (101, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (102, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (103, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-11 14:10:41', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (104, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:38:37', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (105, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:38:37', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (106, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:38:37', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (107, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:38:37', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (108, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:43:48', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (109, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:43:48', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (110, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:43:48', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (111, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:43:48', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (112, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:45:04', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (113, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:45:04', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (114, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:45:04', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (115, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:45:04', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (116, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:49:29', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (117, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:49:29', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (118, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:49:29', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (119, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 10:49:29', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (120, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:04:14', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (121, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:04:14', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (122, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:04:14', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (123, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:04:14', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (124, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:05:06', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (125, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:05:06', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (126, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:05:06', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (127, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:05:06', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (128, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:06:57', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (129, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:06:57', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (130, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:06:57', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (131, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:06:57', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (132, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:07:54', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (133, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:07:54', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (134, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:07:54', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (135, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 17:07:54', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (136, 198, 'INNER_MOBILIZATION', '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, '邢台基地', '人力资源部', '薪酬福利专员 ', NULL, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, '2018-10-16 00:00:00', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (137, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (138, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (139, 198, 'INNER_MOBILIZATION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '请选择', '', '', NULL, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 198, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (140, 95, 'DIMISSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '测试', '2018-10-31 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, '2018-10-31 11:18:17', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (141, 95, 'DIMISSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '测试', '2018-10-31 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, '2018-10-31 13:53:40', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (142, 109, 'DIMISSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '测试', '2018-10-31 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, '2018-10-31 15:46:52', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (143, 111, 'DIMISSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '测试', '2018-10-31 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, '2018-10-31 15:46:52', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_adjustment_work` VALUES (144, 110, 'TEMPORARILY', NULL, NULL, '南京基地', '北京部门', '职位14555', '经理', 'M10', '南京基地', '南京部门', '职位2', '总经理', 'M9', 0, NULL, '系统内', '2018-11-29 00:00:00', '2018-11-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 200, '2018-11-19 14:44:16', NULL, NULL, '蓝领非一线', '222222233', 'AS0005', 191, 0);
INSERT INTO `stf_adjustment_work` VALUES (145, 115, 'INNER_MOBILIZATION', '2018-11-08 00:00:00', NULL, '南京基地', '南京部门', '444', '副总经理', 'M7', '南京基地', '北京部门', '444', '副总经理', 'M7', 0, '否', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 200, '2018-11-20 09:30:59', NULL, NULL, '蓝领非一线', 'ASZ00025', 'AS0006', 191, 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_agreement
-- ----------------------------
DROP TABLE IF EXISTS `stf_agreement`;
CREATE TABLE `stf_agreement` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '协议信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `agreement_no` varchar(255) DEFAULT NULL COMMENT '协议编号',
  `agreement_type` bigint(11) DEFAULT NULL COMMENT '协议类型',
  `sign_date` datetime DEFAULT NULL COMMENT '签订日期',
  `agreement_date_start` datetime DEFAULT NULL COMMENT '协议生效日期（YYYY-MM-DD）',
  `agreement_date_end` datetime DEFAULT NULL COMMENT '协议终止日期（YYYY-MM-DD）',
  `agreement_period` int(11) DEFAULT NULL COMMENT '合同期限',
  `work_place` varchar(255) DEFAULT NULL COMMENT '工作地点',
  `owner` varchar(255) DEFAULT NULL COMMENT '甲方（展示所有基地名称）',
  `file` varchar(255) DEFAULT NULL COMMENT '附件',
  `describe_` varchar(255) DEFAULT NULL COMMENT '描述',
  `sign_time` int(11) DEFAULT NULL COMMENT '连续签订次数',
  `end_date` datetime DEFAULT NULL COMMENT '终止日期',
  `responsible_person` varchar(255) DEFAULT NULL COMMENT '经办人',
  `end_reason` varchar(255) DEFAULT NULL COMMENT '终止原因',
  `relieve_date` datetime DEFAULT NULL COMMENT '解除日期',
  `relieve_reason` varchar(255) DEFAULT NULL COMMENT '解除原因',
  `agreement_state` bigint(11) DEFAULT NULL COMMENT '协议状态（未生效 、履行中 、已过期、已解除 、已终止）',
  `renew_status` bigint(11) DEFAULT '0' COMMENT '续签状态（1.已续签 、0.未续签）',
  `relevance_contract` varchar(255) DEFAULT NULL COMMENT '关联合同',
  `del_flag` int(11) DEFAULT '0',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `discontinue_date` datetime DEFAULT NULL COMMENT '中止日期',
  `discontinue_reason` varchar(255) DEFAULT NULL COMMENT '中止原因',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='协议表';

-- ----------------------------
-- Records of stf_agreement
-- ----------------------------
BEGIN;
INSERT INTO `stf_agreement` VALUES (23, 111, 'JAXY000001', 102, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-08-08 00:00:00', 0, NULL, '南京基地', 'group1/M00/00/01/rB0PK1uWBDmAaJ1GAAAAeRvnyQM619.sql', 'wadaw', NULL, '2018-08-15 00:00:00', 'awfawf', 'afwafw', '2018-10-31 00:00:00', '测试', 4, 0, 'EMPCON000002', 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (24, 111, 'JAXY000024', 103, '2018-08-09 00:00:00', '2018-08-10 00:00:00', '2018-08-30 00:00:00', 0, NULL, '海淀基地', '', '312', NULL, NULL, '321', NULL, '2018-10-31 00:00:00', '测试', 4, 1, 'EMPCON000002', 0, 110, NULL, 0, '2018-09-17 17:31:51', NULL, NULL);
INSERT INTO `stf_agreement` VALUES (25, 135, 'JAXY000025', 102, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', 0, NULL, '南京基地', '', 'xxxxx', NULL, '2018-08-15 00:00:00', 'sssss', 'adadadada', '2018-08-15 00:00:00', 'aaaaa', 3, 0, NULL, 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (26, 95, 'JAXY000026', 103, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', 0, NULL, '海淀基地', 'group1/M00/00/00/rB0PK1tz5mGAZZceAAW7k2udh5k479.jpg', 'dddd', NULL, '2018-08-16 00:00:00', 'adaddd', 'awd', '2018-10-31 00:00:00', '测试', 4, 0, NULL, 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (27, 108, 'JAXY000027', 102, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', 0, NULL, '南京基地', 'group1/M00/00/00/rB0PK1tz5o-ADZyXAAowGJPhNHo191.jpg', 'efsf', NULL, NULL, NULL, NULL, '2018-10-09 00:00:00', '不适应工作环境', 4, 1, NULL, 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (28, 95, 'adawdawd', 102, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', 0, NULL, '北京基地2', '', 'trgsef', NULL, NULL, NULL, NULL, '2018-10-31 00:00:00', '测试', 4, 1, NULL, 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (29, 108, 'JAXY000029', 102, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-17 00:00:00', 0, NULL, '北京基地2', 'group1/M00/00/00/rB0PK1tz6ECAUvQsAAMa5fPfr_E196.sql', 'dadadd', NULL, NULL, 'ddaaa', NULL, '2018-10-09 00:00:00', '不适应工作环境', 4, 0, NULL, 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (30, 135, 'JAXY000030', 101, '2018-08-15 00:00:00', '2018-08-01 00:00:00', '2018-08-25 00:00:00', 0, NULL, '南京基地', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, 0, 'EMPCON000061', 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (31, 95, 'JAXY000031', 103, '2018-08-02 00:00:00', '2018-08-01 00:00:00', '2018-08-01 00:00:00', 0, NULL, '南京基地', '', NULL, NULL, '2018-08-15 00:00:00', '2222', '2222', '2018-10-31 00:00:00', '测试', 4, 0, NULL, 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (32, 107, '12345679896', 102, '2018-08-16 00:00:00', '2018-08-16 00:00:00', '2018-08-17 00:00:00', 0, NULL, '南京基地', '', 'sef', NULL, NULL, 'ef', NULL, '2018-09-26 00:00:00', '员工退休', 4, 0, 'EMPCON000062', 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (33, 95, 'JAXY000033', 102, '2018-08-17 00:00:00', '2018-08-18 00:00:00', '2018-08-25 00:00:00', 0, NULL, '北京基地', '', NULL, NULL, NULL, NULL, NULL, '2018-10-31 00:00:00', '测试', 4, 0, 'EMPCON000064', 0, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_agreement` VALUES (34, 166, 'JAXY000034', 102, '2018-09-14 00:00:00', '2018-09-14 00:00:00', '2018-09-30 00:00:00', 0, NULL, '北京基地', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 0, 'EMPCON000084', 0, 110, '2018-09-14 17:05:20', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for stf_award
-- ----------------------------
DROP TABLE IF EXISTS `stf_award`;
CREATE TABLE `stf_award` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '奖励信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `award_name` varchar(255) DEFAULT NULL COMMENT '荣誉名称',
  `award_type` bigint(10) DEFAULT NULL COMMENT '奖励类型',
  `award_rank` bigint(11) DEFAULT NULL COMMENT '奖励级别',
  `award_time` datetime DEFAULT NULL COMMENT '获奖时间',
  `year` datetime DEFAULT NULL COMMENT '年度（选到年即可）',
  `ratify_unit` varchar(255) DEFAULT NULL COMMENT '批准单位',
  `award_cause` varchar(255) DEFAULT NULL COMMENT '奖励事由',
  `award_gist` varchar(255) DEFAULT NULL COMMENT '奖励依据',
  `award_measure` varchar(255) DEFAULT NULL COMMENT '奖励措施',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `file` varchar(255) DEFAULT NULL COMMENT '文件上传',
  `del_flag` int(11) DEFAULT '0',
  `credential_issue_date` datetime DEFAULT NULL COMMENT '证书颁发日期',
  `credential_issue_org` varchar(255) DEFAULT NULL COMMENT '证书颁发机构',
  `reward_form` varchar(255) DEFAULT NULL COMMENT '奖励形式',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='奖励信息表';

-- ----------------------------
-- Records of stf_award
-- ----------------------------
BEGIN;
INSERT INTO `stf_award` VALUES (1, 1, '加班奖励', 1, 41, '2018-07-20 00:00:00', '2018-01-01 00:00:00', '公司', '加班', '加班', '调休', NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `stf_award` VALUES (2, 95, '加班奖励', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `stf_award` VALUES (3, 95, '加班奖励', 201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `stf_award` VALUES (4, 95, '加班奖励', 202, 44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `stf_award` VALUES (5, 95, '加班奖励', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `stf_award` VALUES (6, 110, '加班奖励', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', 0, '2018-09-04 00:00:00', '123', '321');
INSERT INTO `stf_award` VALUES (7, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (8, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (9, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (10, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (11, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (12, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (13, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (14, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (15, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (16, 170, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (17, 171, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (18, 171, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (19, 172, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (20, 172, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (21, 177, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (22, 177, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (23, 178, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (24, 178, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (25, 179, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (26, 179, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (27, 180, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (28, 180, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (29, 181, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (30, 181, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (31, 182, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (32, 182, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (33, 183, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (34, 183, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (35, 184, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (36, 184, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (37, 185, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (38, 185, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (39, 188, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (40, 188, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (41, 189, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (42, 189, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (43, 190, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (44, 190, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (45, 191, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (46, 191, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (47, 195, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (48, 195, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (49, 196, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (50, 196, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (51, 197, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (52, 197, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (53, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (54, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (55, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (56, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (57, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (58, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (59, 199, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (60, 199, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (61, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (62, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (63, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (64, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (65, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (66, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (67, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (68, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (69, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (70, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (71, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (72, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (73, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (74, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', '');
INSERT INTO `stf_award` VALUES (75, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
INSERT INTO `stf_award` VALUES (76, 198, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '', '');
COMMIT;

-- ----------------------------
-- Table structure for stf_base_info
-- ----------------------------
DROP TABLE IF EXISTS `stf_base_info`;
CREATE TABLE `stf_base_info` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `staff_no` varchar(100) DEFAULT NULL COMMENT '工号',
  `staff_name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `sex` bigint(10) DEFAULT NULL COMMENT '性别',
  `base_id` bigint(10) DEFAULT NULL COMMENT '基地ID',
  `dept_id` bigint(10) DEFAULT NULL COMMENT '部门ID',
  `position_id` bigint(10) DEFAULT NULL COMMENT '职位ID',
  `rank_id` bigint(10) DEFAULT NULL COMMENT '职级ID',
  `grade_id` bigint(10) DEFAULT NULL COMMENT '职等ID',
  `identity_type_id` bigint(10) DEFAULT NULL COMMENT '证件类型',
  `identity_no` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `identity_valid_date` datetime DEFAULT NULL COMMENT '证件有效期',
  `birthdate` datetime DEFAULT NULL COMMENT '出生日期',
  `nationality` bigint(10) DEFAULT NULL COMMENT '国籍',
  `nation` bigint(10) DEFAULT NULL COMMENT '民族',
  `registered_residence` varchar(100) DEFAULT NULL COMMENT '户口所在地',
  `native_place` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `fertility_status` bigint(10) DEFAULT NULL COMMENT '生育状况',
  `marital_status` bigint(10) DEFAULT NULL COMMENT '婚姻状况',
  `political_status` bigint(10) DEFAULT NULL COMMENT '政治面貌',
  `first_working_time` datetime DEFAULT NULL COMMENT '首次工作时间',
  `staff_type` bigint(10) DEFAULT NULL COMMENT '员工类型',
  `social_security_type` bigint(10) DEFAULT NULL COMMENT '社保类型',
  `cost_center` bigint(10) DEFAULT NULL COMMENT '成本中心',
  `office_place` varchar(100) DEFAULT NULL COMMENT '办公地点',
  `entry_date` datetime DEFAULT NULL COMMENT '入职日期',
  `work_type` bigint(10) DEFAULT NULL COMMENT '用工类型',
  `job_status` bigint(10) DEFAULT NULL COMMENT '在职状态',
  `leave_date` datetime DEFAULT NULL COMMENT '离职时间',
  `is_blacklist` bigint(10) DEFAULT NULL COMMENT '是否黑名单',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  `lines_` bigint(10) DEFAULT NULL COMMENT '线别',
  `staff_classify` bigint(10) DEFAULT NULL COMMENT '员工分类',
  `operating_post` bigint(10) DEFAULT NULL COMMENT '工作岗位',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `factory_category` bigint(10) DEFAULT NULL COMMENT '厂别',
  `classes` varchar(255) DEFAULT NULL COMMENT '班次',
  `recruitment_channel` bigint(10) DEFAULT NULL COMMENT '招聘渠道',
  `work_card` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '工卡卡号',
  `dormitory_no` varchar(255) DEFAULT NULL COMMENT '宿舍号',
  `locker_shoebox` varchar(255) DEFAULT NULL COMMENT '更衣箱鞋柜',
  `speciality` varchar(255) DEFAULT NULL COMMENT '特长',
  `job_title` varchar(255) DEFAULT NULL COMMENT '职称',
  `lunar_solar_calendar` varchar(255) DEFAULT NULL COMMENT '阴历阳历',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工基础信息表';

-- ----------------------------
-- Records of stf_base_info
-- ----------------------------
BEGIN;
INSERT INTO `stf_base_info` VALUES (95, '12345678', '大师傅132434', 7, 2, 3, 14, 12, 45, 17, '3201241919191919112', '2018-07-20 00:00:00', '2010-07-20 00:00:00', 211, 215, '江苏省南京市', '江苏省南京市', 1, 20, 25, '2018-07-20 00:00:00', 29, 37, 175, '1', '2018-07-19 00:00:00', 49, 10, '2018-10-31 00:00:00', 1, 'test', 0, NULL, 1, 299, '18761616165', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 95, NULL, 0, '2018-10-31 10:59:12');
INSERT INTO `stf_base_info` VALUES (107, 'AS0003', 'test', 7, 187, 191, 14, 12, 45, 17, '320124199510124512', '2018-08-09 00:00:00', '1995-10-12 00:00:00', 1018, 1021, '1', '1', 1, 19, 21, '2018-08-09 00:00:00', 27, 37, 76, '1', '2018-08-09 00:00:00', 39, 286, '2018-09-18 00:00:00', 1, NULL, 0, NULL, 1, 299, '18375214561', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 107, NULL, 0, '2018-10-31 10:59:16');
INSERT INTO `stf_base_info` VALUES (108, 'BJJD000021', '320124191919191912', 7, 187, 191, 14, 12, 45, 17, '320124191919191912', '2018-08-09 00:00:00', '2018-08-09 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-09 00:00:00', NULL, 10, '2018-10-09 00:00:00', 2, NULL, 0, NULL, 1, 300, '18761616161', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 108, NULL, 0, '2018-10-31 10:59:16');
INSERT INTO `stf_base_info` VALUES (109, 'ASZ00020', 'test1', 7, 76, 169, 14, 12, 45, 17, '320124191919191912', '2018-08-09 00:00:00', '2018-08-09 00:00:00', 211, 215, '1', '1', 1, 19, 21, '2018-08-09 00:00:00', 27, 32, 76, '1', '2018-08-09 00:00:00', 39, 10, '2018-10-31 00:00:00', 1, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 109, NULL, 0, '2018-10-31 10:59:16');
INSERT INTO `stf_base_info` VALUES (110, '222222233', 'king', 8, 187, 191, 2, 15, 42, 17, '21564196418578', '2018-08-07 00:00:00', '2018-08-10 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, 104, NULL, '2018-08-01 00:00:00', NULL, 9, '2018-09-19 00:00:00', NULL, NULL, 0, NULL, 3, 299, '18761616161', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 110, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (111, 'ASZ00021', '李三', 7, 76, 169, 16, 12, 47, 17, '320124191919191912', '2018-08-09 00:00:00', '2018-08-09 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-09 00:00:00', NULL, 10, '2018-10-31 00:00:00', 1, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 111, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (112, 'BJJD000025', '李四', 7, 187, 190, 16, 14, 47, 17, '32012419191919191', '2018-08-09 00:00:00', '2018-08-09 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, '2018-08-09 00:00:00', NULL, NULL, 76, NULL, '2018-08-09 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 2, 300, '18761616161', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 112, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (113, 'ASZ00023', '李四', 7, 155, 161, 14, 12, 45, 17, '3201241919191919', '2018-08-10 00:00:00', '2018-08-10 00:00:00', 783, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-10 00:00:00', NULL, 9, '2018-09-10 00:00:00', 1, NULL, 0, NULL, 1, 299, '18761616161', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 113, NULL, 0, '2018-10-31 11:03:17');
INSERT INTO `stf_base_info` VALUES (114, 'ASZ00024', '李四1', 7, 155, 169, 14, 12, 45, 18, '32012419191919', '2018-08-10 00:00:00', '2018-08-10 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 114, NULL, 0, '2018-10-31 11:03:17');
INSERT INTO `stf_base_info` VALUES (115, 'AS0006', '李四2', 7, 187, 191, 14, 12, 45, 17, '3201241919191', '2018-08-10 00:00:00', '2018-08-10 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 3, 300, '18761616163', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 115, NULL, 0, '2018-10-31 11:03:17');
INSERT INTO `stf_base_info` VALUES (116, 'ASZ00026', '李四', 7, 187, 190, 14, 12, 45, 17, '320124', '2018-08-10 00:00:00', '2018-08-10 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 116, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (117, 'ASZ00027', '李四5', 7, 76, 169, 16, 12, 47, 17, '320124191919191912123', '2018-08-10 00:00:00', '2018-08-10 00:00:00', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 117, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (118, 'admin1', '管理员', 7, 76, 169, 16, 12, 47, 17, '320124191919191912123', '2018-08-10 10:42:17', '2018-08-10 10:42:19', 211, 215, NULL, NULL, 1, 19, NULL, NULL, NULL, NULL, 76, NULL, '2018-08-10 10:42:37', NULL, 9, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 118, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (122, 'BJJD00001', '李四', 7, 155, 161, 16, 12, 47, 17, '123456', '2016-08-08 00:00:00', '2015-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2015-08-04 00:00:00', NULL, 9, NULL, 2, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 122, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (125, 'BJJD00002', '李四', 7, 155, 161, 16, 12, 47, 17, '3201241', '2018-08-13 00:00:00', '2018-08-11 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-13 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 125, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (126, 'BJJD00003', '李四1', 7, 155, 169, 16, 10, 45, 17, '320124191919', '2018-08-13 00:00:00', '2014-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-06 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 126, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (127, 'BJJD00004', '李四2', 7, 155, 169, 16, 12, 47, 17, '32012419', '2018-08-13 00:00:00', '2018-08-01 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-13 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 127, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (128, 'BJJD000001', '2', 7, 155, 161, 2, 12, 45, 17, '32012419191919191211', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 128, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (129, 'BJJD000002', '5', 7, 155, 161, 16, 12, 47, 17, '32012419191919191122', '2018-08-14 00:00:00', '2018-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-13 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 129, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (130, 'BJJD000003', '李四5', 7, 155, 169, 16, 12, 45, 17, '320124191919191913', '2018-08-14 00:00:00', '2018-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 130, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (131, 'BJJD000004', '李四7', 7, 155, 169, 2, 14, 45, 17, '3201241919191919113', '2018-08-14 00:00:00', '2018-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 131, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (132, 'BJJD000005', '李四8', 7, 155, 169, 16, 12, 45, 17, '3201241919191919125', '2018-08-14 00:00:00', '2018-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 132, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (133, 'BJJD000006', '李四9', 7, 155, 176, 16, 12, 45, 17, '3201241919191919126', '2018-08-14 00:00:00', '2018-08-13 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 133, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (134, 'BJJD000007', '李四10', 8, 155, 169, 16, 12, 45, 17, '3201241919191919129', '2018-08-13 00:00:00', '2018-08-09 00:00:00', 211, 215, NULL, NULL, 1, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-13 00:00:00', NULL, 9, '2018-08-15 00:00:00', 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 134, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (135, 'BJJD000008', 'kingkingking', 8, 155, 169, 2, 12, 45, 17, '215641964185', '2018-08-17 00:00:00', '2018-08-09 00:00:00', 211, 215, NULL, NULL, NULL, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-09 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 135, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (136, 'S0111', '王二', 8, 155, 169, 17, 12, 45, 17, '123', '2019-09-09 00:00:00', '2010-09-09 00:00:00', 211, 215, '南京', '江苏省南京市', 198, 19, 25, '2012-09-01 00:00:00', 27, 32, 187, '南京', '2018-08-01 00:00:00', NULL, 9, NULL, 2, '备注', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 136, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (140, 'CP00000001', '00', 7, 187, 191, 2, 14, 42, 17, '2156419641855615', '2018-08-02 00:00:00', '2018-08-11 00:00:00', 211, 215, NULL, NULL, 198, 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-08-17 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 140, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (144, 'ja12345679', '大师傅1', 7, 155, 191, 2, 13, 42, 17, '32012419191919142X', '2018-07-20 00:00:00', '2010-07-20 00:00:00', 211, 215, '江苏省南京市', '江苏省南京市', NULL, 20, 25, '2018-07-20 00:00:00', 29, 37, 155, '1', '2018-07-19 00:00:00', 49, 9, '2018-07-20 00:00:00', 2, 'test', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 144, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (145, 'ja12345680', '大师傅1', 7, 155, 191, 2, 13, 42, 17, '320124191919191421', '2018-07-20 00:00:00', '2010-07-20 00:00:00', 211, 215, '江苏省南京市', '江苏省南京市', NULL, 20, 25, '2018-07-20 00:00:00', 29, 37, 155, '1', '2018-07-19 00:00:00', 49, 9, '2018-07-20 00:00:00', 2, 'test', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 145, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (146, 'BJJD000009', 'test01', 7, 155, 169, 14, 12, 45, 17, '320124199508124512', '2022-08-22 00:00:00', '1995-08-12 00:00:00', 211, 215, '北京', '北京', 198, 20, 23, '2016-08-22 00:00:00', 28, 32, 155, NULL, '2018-08-22 00:00:00', 39, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 146, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (147, 'BJJD000010', 'test02', 7, 155, 169, 2, 13, 42, 17, '320124199508124513', '2018-08-31 00:00:00', '1995-08-12 00:00:00', 211, 215, '北京', '北京', 198, 20, 23, '2018-08-01 00:00:00', 28, 32, 155, '北京', '2018-08-22 00:00:00', 39, 9, NULL, NULL, '123456', 0, 96, 1, 282, '13115211551', '123456789@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 147, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (150, 'BJJD000011', '李四', 7, 155, 161, 2, 14, 42, 17, '320124195506083222', '2018-08-23 00:00:00', '1955-06-08 00:00:00', 211, 215, NULL, NULL, 198, 20, NULL, NULL, 27, NULL, NULL, NULL, '2018-08-23 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 150, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (151, 'BJJD000012', '张铭', 7, 155, 169, 2, 13, 42, 17, '320', '2018-08-27 00:00:00', '2018-08-01 00:00:00', 211, 215, NULL, NULL, 198, 19, NULL, NULL, 27, NULL, NULL, NULL, '2018-08-27 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 282, '13706128502', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 151, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (152, 'CP00000002', 'jsl', 7, 187, 283, 2, 13, 42, 17, '1111', '2018-08-27 00:00:00', '2012-08-27 00:00:00', 211, 215, '111', '111', 198, 20, 21, NULL, 27, NULL, NULL, NULL, '2018-08-27 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 299, '13111111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 152, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (153, 'BJJD000013', '张铭', 7, 155, 169, 2, 13, 42, 17, '33', '2018-08-30 00:00:00', '2018-08-01 00:00:00', 211, 215, NULL, NULL, 198, 20, NULL, NULL, 27, NULL, NULL, NULL, '2018-08-28 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 2, 299, '13706128502', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 153, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (154, 'BJJD000014', 'test', 7, 155, 161, 2, 13, 42, 17, '342921199402144515', '2018-08-30 00:00:00', '1994-02-14 00:00:00', 1018, 1021, NULL, NULL, 198, 20, NULL, NULL, 27, NULL, NULL, NULL, '2018-08-30 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 299, '13115211552', '123456780@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 154, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (155, 'BJJD000015', 'asdasd', 7, 155, 161, 2, 13, 42, 17, '342921199402144517', '2018-09-04 00:00:00', '1994-02-14 00:00:00', 1018, 1021, NULL, NULL, 198, 20, NULL, NULL, 27, NULL, NULL, NULL, '2018-09-04 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 299, '13015214512', NULL, NULL, '123', NULL, '123123123123', NULL, NULL, NULL, NULL, '阴历', 155, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (157, 'BJJD000020', 'test03', 7, 187, 191, 14, 12, 45, 17, '341221199503154415', '2018-10-12 00:00:00', '1995-03-15 00:00:00', 1018, 1021, '北京', '北京', 198, 20, 23, '2018-02-14 00:00:00', 28, 32, 155, NULL, '2018-08-22 00:00:00', NULL, 9, '2018-09-01 00:00:00', 1, NULL, 0, 96, 1, 368, '17715211451', '1234567890@qq.com', 275, '二班', 1087, '123123123', '123', '123', '123', '123', '阳历', 157, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (158, 'DH3241', '李曼', 8, 76, 169, 14, 12, 45, NULL, '320721198705052048', '2018-09-04 00:00:00', '1987-05-05 00:00:00', NULL, 1021, '江苏省-连云港市-晶奥太阳能', '江苏省-连云港市-晶奥太阳能', 198, 19, 25, NULL, NULL, NULL, NULL, NULL, '2016-09-04 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 279, 591, '15950773352', '', NULL, '', NULL, '', '', '', '', '', NULL, 157, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (159, 'DH0574', '丁加金', 7, 76, 169, 14, 12, 45, NULL, '320722198701070819', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 0, NULL, 281, 709, '15950775250', '', NULL, '', NULL, '', '', '', '', '', NULL, 157, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (160, 'DH0180', '姚晓庆', 8, 76, 169, 14, 12, 45, NULL, '320831198211020028', '2018-09-04 00:00:00', '1982-11-02 00:00:00', NULL, 1021, '江苏省-连云港市-晶奥太阳能', '江苏省-淮安市-晶奥太阳能', 199, 19, 25, NULL, NULL, NULL, NULL, NULL, '2016-09-04 00:00:00', NULL, 9, NULL, NULL, NULL, 0, 98, 279, 464, '18805125211', '', 275, '', 1091, '', '', '', '', '', NULL, 157, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (161, '1', '李四', 7, 155, 161, 2, 13, 42, 17, '132229196502080091', '2018-09-10 00:00:00', '1965-02-08 00:00:00', 1018, 1021, NULL, NULL, 198, 19, NULL, NULL, 27, NULL, NULL, NULL, '2018-09-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 299, '18761616162', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 161, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (162, 'CP00000003', 'test1234', 7, 187, 191, 20, 14, 42, 17, '342921199402141234', '2018-09-10 00:00:00', '1994-02-14 00:00:00', NULL, NULL, NULL, NULL, 198, 20, NULL, NULL, 28, NULL, NULL, NULL, '2018-09-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 2, 369, '13015214516', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 162, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (163, 'S00001', '测试', 7, 155, 169, 14, 12, 45, 17, '320124199404181111', '2018-08-08 00:00:00', '2018-08-08 00:00:00', 1018, 1021, '1', '1', 198, 20, 21, '2018-08-08 00:00:00', 27, 32, NULL, '1', '2018-08-08 00:00:00', NULL, 9, NULL, 2, '1', 0, 96, 1, 299, '18768686868', 'test@123.com', 275, '1', 1087, '1', '1', '1', '1', '1', '阳历', 163, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (165, 'BJJD000016', 'test333', 7, 155, 161, 14, 12, 45, 17, '342921198805124512', '2025-09-14 00:00:00', '1988-05-12 00:00:00', 1018, 1021, NULL, NULL, 198, 20, NULL, NULL, 30, NULL, NULL, NULL, '2018-09-01 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 2, 299, '13115211546', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 165, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (166, 'BJJD000017', 'test2333', 7, 155, 161, 16, 15, 47, 17, '342921199010124512', '2028-09-14 00:00:00', '1990-10-12 00:00:00', 1018, 1021, NULL, NULL, 198, 20, NULL, NULL, 30, NULL, NULL, NULL, '2018-09-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 2, 299, '18245611851', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 166, NULL, NULL, NULL);
INSERT INTO `stf_base_info` VALUES (167, 'BJJD000018', 'test233', 7, 155, 161, 18, 14, 45, 17, '342921199702154515', '2018-09-14 00:00:00', '1997-02-15 00:00:00', 1018, 1021, NULL, NULL, 198, 20, NULL, NULL, 30, NULL, NULL, NULL, '2018-09-14 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 2, 299, '13115235416', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 167, '2018-09-14 17:07:23', NULL, NULL);
INSERT INTO `stf_base_info` VALUES (168, 'BJJD000019', '李四10', 7, 155, 161, 2, 13, 42, 17, '320124199509124512', '2018-09-18 00:00:00', '1995-09-12 00:00:00', 1018, 1021, '312', '321', 198, 19, NULL, NULL, 27, NULL, NULL, NULL, '2018-09-18 00:00:00', 39, 9, NULL, NULL, NULL, 0, NULL, 1, 301, '18761616175', '123456789@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阳历', 168, '2018-09-18 12:30:55', 0, '2018-09-19 17:24:47');
INSERT INTO `stf_base_info` VALUES (169, 'BJJD000022', 'kingking', 8, 155, 169, 16, 14, 47, 17, '342225199510047418', '2018-09-11 00:00:00', '1995-10-04 00:00:00', 1018, 1021, NULL, NULL, 198, 1083, 24, NULL, 29, NULL, NULL, NULL, '2018-09-06 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 3, 303, '18600426068', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阴历', 0, '2018-09-28 09:35:39', NULL, NULL);
INSERT INTO `stf_base_info` VALUES (176, 'BJJD000026', '赵', 7, 155, 161, 2, 13, 42, 17, '2156419641851', '2018-10-10 00:00:00', '2018-10-09 00:00:00', 1018, 1021, NULL, NULL, 198, 20, NULL, NULL, 27, NULL, NULL, NULL, '2018-10-10 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 299, '18761626162', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '阴历', 0, '2018-10-10 15:41:14', NULL, NULL);
INSERT INTO `stf_base_info` VALUES (198, 'XT0026', '赵媛媛', 8, 320, 321, NULL, NULL, NULL, NULL, '130502198709240622', '2036-07-13 00:00:00', '1987-09-24 00:00:00', 1018, 1021, '邢台市宁晋县', '邢台市', 199, 19, 25, '2012-10-01 00:00:00', NULL, NULL, NULL, NULL, '2015-09-26 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, 679, '15531961916', '1936243887@qq.com', NULL, '', NULL, '', '', '', '特长一:;特长二:', '职称一:无;', NULL, 198, '2018-10-10 18:45:52', NULL, '2018-10-16 17:09:51');
INSERT INTO `stf_base_info` VALUES (199, '8999', '张胜辉', 7, 331, 332, NULL, NULL, NULL, 17, '132401197812150639', '2029-10-15 00:00:00', '1978-12-15 00:00:00', 1018, 1021, '河北省-保定市-定州市', '河北省-保定市-定州市', 200, 19, 25, '2003-07-01 00:00:00', NULL, NULL, NULL, NULL, '2011-03-28 00:00:00', NULL, 9, NULL, NULL, NULL, 0, NULL, 1, NULL, '18632098983', '', NULL, '', NULL, '', '', '', '', '无', NULL, 199, '2018-10-15 17:30:12', NULL, NULL);
INSERT INTO `stf_base_info` VALUES (200, 'admin', '超级管理员', NULL, 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for stf_company_record
-- ----------------------------
DROP TABLE IF EXISTS `stf_company_record`;
CREATE TABLE `stf_company_record` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '行号',
  `staff_id` bigint(20) DEFAULT NULL COMMENT '员工ID',
  `job_application_a` int(11) DEFAULT NULL COMMENT '职位申请表A',
  `competition_agreement` int(11) DEFAULT NULL COMMENT '竞业协议',
  `staff_register_b` int(11) DEFAULT NULL COMMENT '员工登记表B',
  `hr_read_receipt` int(11) DEFAULT NULL COMMENT 'HR制度阅读回执',
  `hire_chack` int(11) DEFAULT NULL COMMENT '录用审核表',
  `staff_handbook` int(11) DEFAULT NULL COMMENT '员工手册',
  `resume` int(11) DEFAULT NULL COMMENT '个人简历',
  `photo` int(11) DEFAULT NULL COMMENT '照片',
  `id_card_copies` int(11) DEFAULT NULL COMMENT '身份证或护照复印件',
  `pay_card_info` int(11) DEFAULT NULL COMMENT '工资卡信息',
  `residence_booklet_copies` int(11) DEFAULT NULL COMMENT '户口本复印件',
  `position_description` int(11) DEFAULT NULL COMMENT '岗位说明书',
  `graduation_certificate_copies` int(11) DEFAULT NULL COMMENT '毕业证书复印件',
  `student_id_card_copies` int(11) DEFAULT NULL COMMENT '学生证复印件',
  `diploma_copies` int(11) DEFAULT NULL COMMENT '学位证书复印件',
  `internship_contract` int(11) DEFAULT NULL COMMENT '实习协议',
  `relevant_certificate_copies` int(11) DEFAULT NULL COMMENT '相关证书复印件',
  `labour_agreement` int(11) DEFAULT NULL COMMENT '劳务协议',
  `medical_examination_report` int(11) DEFAULT NULL COMMENT '体检报告',
  `back_survey_report` int(11) DEFAULT NULL COMMENT '背调报告',
  `before_company_dimission` int(11) DEFAULT NULL COMMENT '上家公司离职证明',
  `labor_contract` int(11) DEFAULT NULL COMMENT '劳动合同',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `intellectual_property_agreement` int(11) DEFAULT NULL COMMENT '诚信行为暨知识产权协议书',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='公司档案表';

-- ----------------------------
-- Records of stf_company_record
-- ----------------------------
BEGIN;
INSERT INTO `stf_company_record` VALUES (1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '1', 1, 0);
INSERT INTO `stf_company_record` VALUES (2, 95, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, NULL, 1, 1);
INSERT INTO `stf_company_record` VALUES (3, 95, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, NULL, 1, 1);
INSERT INTO `stf_company_record` VALUES (4, 95, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, '1111111111111111111111111111111111111111111111111111111111111111111111111111112222222222222222222233', 2, 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_contact_emergency
-- ----------------------------
DROP TABLE IF EXISTS `stf_contact_emergency`;
CREATE TABLE `stf_contact_emergency` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '紧急联系人ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `contact_name` varchar(100) DEFAULT NULL COMMENT '紧急联系人姓名',
  `relationship` varchar(100) DEFAULT NULL COMMENT '与本人关系',
  `mobile` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `wechat_qq` varchar(255) DEFAULT NULL COMMENT '微信/QQ',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='紧急联系人表';

-- ----------------------------
-- Records of stf_contact_emergency
-- ----------------------------
BEGIN;
INSERT INTO `stf_contact_emergency` VALUES (2, 1, '2', '1', '1', '1', NULL, 0);
INSERT INTO `stf_contact_emergency` VALUES (3, 1, '2', '2', '2', '2', NULL, 0);
INSERT INTO `stf_contact_emergency` VALUES (7, 95, '11111111111111111111111111111111111111111111111111', '1', '11111111122', '12222222222222222222211111111111111111111111111112', NULL, 0);
INSERT INTO `stf_contact_emergency` VALUES (9, 95, '2', '2', '3', '3', NULL, 0);
INSERT INTO `stf_contact_emergency` VALUES (10, 158, '陈山', '配偶', '15151293388', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (11, 160, '姚丽', '兄弟姐妹', '18662985858', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (16, 170, '阎瑞冬', '配偶', '17731925959', NULL, '', 1);
INSERT INTO `stf_contact_emergency` VALUES (17, 171, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (18, 172, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (19, 177, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (20, 178, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (21, 179, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (22, 180, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (23, 181, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (24, 182, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (25, 183, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (26, 184, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (27, 185, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (28, 188, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (29, 189, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (30, 190, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (31, 191, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (32, 195, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (33, 196, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (34, 197, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (38, 199, '刘嘉', '配偶', '18632098902', NULL, '', 0);
INSERT INTO `stf_contact_emergency` VALUES (46, 198, '阎瑞冬', '配偶', '17731925959', NULL, '', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_contract
-- ----------------------------
DROP TABLE IF EXISTS `stf_contract`;
CREATE TABLE `stf_contract` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '合同基本信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `contract_no` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `contract_type` bigint(11) DEFAULT NULL COMMENT '合同类型',
  `contract_period_type` bigint(11) DEFAULT NULL COMMENT '合同期限类型(固定期限 、无固定期限 、以完成一定工作任务为期限)',
  `sign_date` datetime DEFAULT NULL COMMENT '签订日期',
  `contract_date_start` datetime DEFAULT NULL COMMENT '合同生效日期（YYYY-MM-DD）',
  `contract_date_end` datetime DEFAULT NULL COMMENT '合同终止日期（YYYY-MM-DD）',
  `contract_period` int(11) DEFAULT NULL COMMENT '合同期限',
  `work_place` varchar(255) DEFAULT NULL COMMENT '工作地点',
  `owner` varchar(255) DEFAULT NULL COMMENT '甲方（展示所有基地名称）',
  `getContract_backups` bigint(11) DEFAULT NULL COMMENT '员工已领取合同备份（是、否）',
  `sign_agreement` bigint(11) DEFAULT NULL COMMENT '需要签订相关协议(是、否)',
  `file` varchar(255) DEFAULT NULL COMMENT '附件',
  `describe_` varchar(255) DEFAULT NULL COMMENT '描述',
  `sign_time` int(11) DEFAULT NULL COMMENT '连续签订次数',
  `end_date` datetime DEFAULT NULL COMMENT '终止日期',
  `responsible_person` varchar(255) DEFAULT NULL COMMENT '经办人',
  `end_reason` varchar(255) DEFAULT NULL COMMENT '终止原因',
  `relieve_date` datetime DEFAULT NULL COMMENT '解除日期',
  `relieve_reason` varchar(255) DEFAULT NULL COMMENT '解除原因',
  `contract_state` bigint(11) DEFAULT NULL COMMENT '合同状态（未生效 、履行中 、已过期、已解除 、已终止、已中止）',
  `renew_status` bigint(11) DEFAULT '0' COMMENT '续签状态（已续签 、未续签）',
  `version_number` bigint(20) DEFAULT NULL COMMENT '合同版本号',
  `del_flag` int(11) DEFAULT '0',
  `relevance_agreement` varchar(255) DEFAULT NULL COMMENT '关联协议',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `discontinue_date` datetime DEFAULT NULL COMMENT '中止日期',
  `discontinue_reason` varchar(255) DEFAULT NULL COMMENT '中止原因',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='合同信息表';

-- ----------------------------
-- Records of stf_contract
-- ----------------------------
BEGIN;
INSERT INTO `stf_contract` VALUES (56, 111, 'EMPCON000002', 13, 16, '2018-08-09 00:00:00', '2018-08-09 00:00:00', '2018-08-29 00:00:00', 0, '123', '南京基地', 2, 2, '', '0001', NULL, NULL, '0001', NULL, '2018-10-31 00:00:00', '测试', 4, 0, 1, 0, NULL, 110, NULL, 0, '2018-09-18 11:08:20', '2018-09-17 00:00:00', '12333');
INSERT INTO `stf_contract` VALUES (57, 110, 'EMPCON000057', 13, 16, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-08-08 00:00:00', 0, '333', '南京基地', 2, 2, '', '2323', NULL, NULL, NULL, NULL, NULL, NULL, 3, 1, 1, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (58, 110, 'EMPCON000058', 13, 15, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-08-08 00:00:00', 0, '000', '北京基地', 1, 1, '', '123', NULL, NULL, NULL, NULL, NULL, NULL, 3, 1, 2, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (59, 110, 'EMPCON000059', 13, 15, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-09-09 00:00:00', 1, '132', '北京基地', 1, 1, '', 'asssssd', NULL, NULL, 'aaaaaaaaa', NULL, '2018-08-16 00:00:00', 'saaa', 3, 1, 3, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (60, 110, 'EMPCON000060', 13, 15, '2018-08-15 00:00:00', '2018-08-01 00:00:00', '2018-08-31 00:00:00', 0, 'buil', '南京基地', 2, 2, '', '111111111', NULL, '2018-08-15 00:00:00', '111111111', 'awdwa', '2018-09-19 00:00:00', '111111111111', 4, 0, 4, 0, NULL, 110, NULL, 0, '2018-09-19 17:53:40', NULL, NULL);
INSERT INTO `stf_contract` VALUES (61, 135, 'EMPCON000061', 13, 16, '2018-08-15 00:00:00', '2018-07-31 00:00:00', '2018-08-03 00:00:00', 1, NULL, '南京基地', 1, 2, 'group1/M00/00/00/rB0PK1t0EdyASxM4AAA5iBVSTdc67.xlsx', '22222222222', NULL, '2018-09-19 00:00:00', '22222222222', '22222222222', NULL, NULL, 5, 0, 1, 0, NULL, 110, NULL, 0, '2018-09-19 18:03:25', NULL, NULL);
INSERT INTO `stf_contract` VALUES (62, 107, 'EMPCON000062', 13, 15, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', 0, NULL, '南京基地', 2, 2, '', 'adw', NULL, NULL, 'awd', NULL, '2018-09-26 00:00:00', '员工退休', 4, 0, 1, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (63, 95, 'EMPCON000063', 12, 15, '2018-08-16 00:00:00', '2018-08-01 00:00:00', '2018-08-31 00:00:00', 0, '123', '北京基地', 1, 1, '', 'xzzxXXz', NULL, NULL, 'ZZZzxzx', NULL, '2018-10-31 00:00:00', '测试', 4, 0, 1, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (64, 95, 'EMPCON000064', 12, 15, '2018-08-16 00:00:00', '2018-08-18 00:00:00', '2018-08-31 00:00:00', 0, '321', '北京基地', 2, 2, '', NULL, NULL, NULL, NULL, NULL, '2018-10-31 00:00:00', '测试', 4, 0, 2, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (65, 117, 'EMPCON000065', 13, 15, '2018-08-02 00:00:00', '2018-08-25 00:00:00', '2018-08-31 00:00:00', 0, NULL, '南京基地', 2, 2, '', '123', NULL, NULL, '132', NULL, NULL, NULL, 6, 0, 1, 0, NULL, 110, NULL, 0, '2018-09-17 16:39:10', '2018-09-17 00:00:00', '123');
INSERT INTO `stf_contract` VALUES (66, NULL, 'EMPCON000066', 12, 14, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-05 00:00:00', 0, NULL, '北京基地', 2, 2, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 0, 1, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (67, NULL, 'EMPCON000067', 12, 15, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-05 00:00:00', 0, NULL, '南京基地', 2, 2, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 0, 1, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (68, 153, 'EMPCON000068', 13, 15, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-04 00:00:00', 0, NULL, '南京基地', 2, 2, '', '333333333333', NULL, '2018-09-19 00:00:00', '333333333333', '333333333333', NULL, NULL, 5, 0, 1, 0, NULL, 110, NULL, 0, '2018-09-19 18:44:15', NULL, NULL);
INSERT INTO `stf_contract` VALUES (69, 152, 'EMPCON000069', 13, 14, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-04 00:00:00', 0, NULL, '北京基地', 2, 2, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 0, 1, 0, NULL, 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (70, 110, 'EMPCON000074', 13, 15, '2018-08-15 00:00:00', '2018-08-01 00:00:00', '2018-08-31 00:00:00', 0, 'buil', '南京基地', 2, 2, '', 'awd', NULL, '2018-08-15 00:00:00', 'awd', 'awdwa', '2018-08-16 00:00:00', 'awdawd', 3, 0, 4, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (71, 111, 'EMPCON000070', 13, 16, '2018-08-09 00:00:00', '2018-08-09 00:00:00', '2018-08-29 00:00:00', 0, '123', '南京基地', 2, 2, '', '00001', NULL, '2018-08-16 00:00:00', '1', '', '2018-10-31 00:00:00', '测试', 4, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (72, 110, 'EMPCON000073', 13, 15, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-09-09 00:00:00', 1, '132', '北京基地', 1, 1, '', 'asssssd', NULL, '2018-08-16 00:00:00', 'aaaaaaaaa', '', '2018-08-16 00:00:00', 'saaa', 3, 1, 3, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (73, 107, 'EMPCON000076', 13, 15, '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', 0, '', '南京基地', 2, 2, '', 'adw', NULL, '2018-08-16 00:00:00', 'awd', '', '2018-09-26 00:00:00', '员工退休', 4, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (74, 95, 'EMPCON000077', 12, 15, '2018-08-16 00:00:00', '2018-08-01 00:00:00', '2018-08-31 00:00:00', 0, '123', '北京基地', 1, 1, '', 'xzzxXXz', NULL, '2018-08-16 00:00:00', 'ZZZzxzx', '', '2018-10-31 00:00:00', '测试', 4, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (75, 110, 'EMPCON000071', 13, 16, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-08-08 00:00:00', 0, '333', '南京基地', 2, 2, '', '2323', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 1, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (76, 110, 'EMPCON000072', 13, 15, '2018-08-09 00:00:00', '2018-08-01 00:00:00', '2018-08-08 00:00:00', 0, '000', '北京基地', 1, 1, '', '123', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 1, 2, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (77, 135, 'EMPCON000075', 13, 16, '2018-08-15 00:00:00', '2018-07-31 00:00:00', '2018-08-03 00:00:00', 1, '', '南京基地', 1, 2, 'group1/M00/00/00/rB0PK1t0EdyASxM4AAA5iBVSTdc67.xlsx', 'sefsef', NULL, '2018-08-16 00:00:00', 'esfse', 'sfse', '2018-08-16 00:00:00', '', 3, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (78, 95, 'EMPCON000078', 12, 15, '2018-08-16 00:00:00', '2018-08-18 00:00:00', '2018-08-31 00:00:00', 0, '321', '北京基地', 2, 2, '', '', NULL, '2018-08-16 00:00:00', '', '', '2018-10-31 00:00:00', '测试', 4, 0, 2, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (79, 117, 'EMPCON000079', 13, 15, '2018-08-02 00:00:00', '2018-08-25 00:00:00', '2018-08-31 00:00:00', 0, '', '南京基地', 2, 2, '', '', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (80, NULL, 'EMPCON000080', 12, 14, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-05 00:00:00', 0, '', '北京基地', 2, 2, '', '', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (81, NULL, 'EMPCON000081', 12, 15, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-05 00:00:00', 0, '', '南京基地', 2, 2, '', '', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (82, 153, 'EMPCON000082', 13, 15, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-04 00:00:00', 0, '', '南京基地', 2, 2, '', '', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (83, 152, 'EMPCON000083', 13, 14, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '2018-09-04 00:00:00', 0, '', '北京基地', 2, 2, '', '', NULL, '2018-08-16 00:00:00', '', '', '2018-08-16 00:00:00', '', 3, 0, 1, 0, '', 110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stf_contract` VALUES (84, 166, 'EMPCON000084', 12, 14, '2018-09-14 00:00:00', '2018-09-14 00:00:00', '2018-09-22 00:00:00', 0, NULL, '北京基地', 2, 2, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 0, 1, 0, NULL, 110, '2018-09-14 17:03:54', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for stf_disability
-- ----------------------------
DROP TABLE IF EXISTS `stf_disability`;
CREATE TABLE `stf_disability` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '残疾信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `disability_type` bigint(11) DEFAULT NULL COMMENT '残疾类别',
  `disability_rank` varchar(255) DEFAULT NULL COMMENT '残疾等级',
  `disability_no` varchar(255) DEFAULT NULL COMMENT '残疾证号',
  `disability_card_date` datetime DEFAULT NULL COMMENT '残疾证发放日期',
  `validity_certificate_start` datetime DEFAULT NULL COMMENT '证件有效期开始时间',
  `validity_certificate_end` datetime DEFAULT NULL COMMENT '证件有效期结束时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='残疾信息表';

-- ----------------------------
-- Records of stf_disability
-- ----------------------------
BEGIN;
INSERT INTO `stf_disability` VALUES (1, 1, 51, '一级1', '123456', '2018-07-25 00:00:00', '2018-07-20 00:00:00', NULL, NULL, 0);
INSERT INTO `stf_disability` VALUES (2, 95, 51, '一级', '123456', '2018-08-13 00:00:00', '2018-08-13 00:00:00', '2018-08-13 00:00:00', '11111111', 0);
INSERT INTO `stf_disability` VALUES (3, 95, 52, 'asdaw', 'sadasd', '2018-08-16 00:00:00', '2018-08-01 00:00:00', '2018-08-23 00:00:00', 'add', 0);
INSERT INTO `stf_disability` VALUES (4, 95, 52, '一级1', '123456', '2018-08-16 00:00:00', '2018-08-16 00:00:00', '2018-08-16 00:00:00', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_education
-- ----------------------------
DROP TABLE IF EXISTS `stf_education`;
CREATE TABLE `stf_education` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '教育经历ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `school_name` varchar(100) DEFAULT NULL COMMENT '学校名称',
  `major_name` varchar(100) DEFAULT NULL COMMENT '专业/方向',
  `entrance_time` datetime DEFAULT NULL COMMENT '入学时间',
  `graduate_time` datetime DEFAULT NULL COMMENT '毕业时间',
  `education` bigint(10) DEFAULT NULL COMMENT '学位',
  `degree` bigint(10) DEFAULT NULL COMMENT '学历',
  `graduation_situation` bigint(10) DEFAULT NULL COMMENT '毕业情况',
  `schooling_documents_type` bigint(10) DEFAULT NULL COMMENT '所获学历证书类',
  `degree_country` bigint(10) DEFAULT NULL COMMENT '学位授予国家',
  `learning_style` bigint(10) DEFAULT NULL COMMENT '学习方式',
  `is_highest_degree` bigint(10) DEFAULT NULL COMMENT '是否最高学历',
  `is_highest_education` bigint(10) DEFAULT NULL COMMENT '是否最高学位',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='教育经历表';

-- ----------------------------
-- Records of stf_education
-- ----------------------------
BEGIN;
INSERT INTO `stf_education` VALUES (7, 95, '1', '', '2018-08-13 00:00:00', '2018-08-13 00:00:00', 227, 231, 237, 241, 243, 265, 1, 1, 0);
INSERT INTO `stf_education` VALUES (8, 95, '2', '2', '2018-08-13 00:00:00', '2018-08-17 00:00:00', 227, 231, 237, 241, 243, 265, 1, 1, 0);
INSERT INTO `stf_education` VALUES (9, 95, '3', '3', '2018-08-16 00:00:00', '2018-08-16 00:00:00', 227, 231, 237, 241, 243, 265, 1, 1, 0);
INSERT INTO `stf_education` VALUES (10, 95, 'awda', 'awdawd', '2018-08-21 00:00:00', '2018-08-21 00:00:00', 223, 229, 237, 241, 243, 265, 1, 1, 0);
INSERT INTO `stf_education` VALUES (11, 110, '1', NULL, '2018-08-28 00:00:00', '2018-08-28 00:00:00', 223, 229, 237, 241, 243, 265, 1, 1, 0);
INSERT INTO `stf_education` VALUES (12, 110, '2', NULL, '2018-08-28 00:00:00', '2018-08-28 00:00:00', 224, 230, 238, 242, 244, 266, 2, 2, 0);
INSERT INTO `stf_education` VALUES (13, 158, '济南大学', '社会工作', '2015-09-04 00:00:00', '2018-09-04 00:00:00', NULL, NULL, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (14, 160, '江苏教育学院', '英语教育', '2015-09-04 00:00:00', '2019-09-04 00:00:00', 223, 232, 238, 241, 249, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (23, 170, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (24, 170, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (25, 171, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (26, 171, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (27, 172, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (28, 172, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (29, 177, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (30, 177, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (31, 178, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (32, 178, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (33, 179, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (34, 179, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (35, 180, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (36, 180, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (37, 181, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (38, 181, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (39, 182, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (40, 182, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (41, 183, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (42, 183, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (43, 184, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (44, 184, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (45, 185, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (46, 185, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (47, 188, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (48, 188, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (49, 189, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (50, 189, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (51, 190, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (52, 190, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (53, 191, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (54, 191, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (55, 195, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (56, 195, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (57, 196, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (58, 196, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (59, 197, '河北经贸大学', '刑法学 ', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (60, 197, '河北科技大学', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
INSERT INTO `stf_education` VALUES (67, 199, '河北理工学院', '', '1999-09-01 00:00:00', '2003-06-30 00:00:00', 227, 231, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (82, 198, '河北经贸大学', '刑法学', '2009-09-01 00:00:00', '2012-06-20 00:00:00', 226, 230, 237, 241, 243, NULL, 1, 1, 0);
INSERT INTO `stf_education` VALUES (83, 198, '河北科技大学 ', '法学', '2005-09-01 00:00:00', '2009-06-20 00:00:00', 227, 231, 237, 241, 243, NULL, 2, 2, 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_foreign_reside
-- ----------------------------
DROP TABLE IF EXISTS `stf_foreign_reside`;
CREATE TABLE `stf_foreign_reside` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '在华外籍员工居留许可证信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `work_permit_no` varchar(255) DEFAULT NULL COMMENT '工作许可编号',
  `certifying_date` datetime DEFAULT NULL COMMENT '发证日期',
  `certifying_authority` varchar(255) DEFAULT NULL COMMENT '发证机关',
  `start_date` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `file` varchar(255) DEFAULT NULL COMMENT '附件',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='在华外籍员工居留许可证信息表';

-- ----------------------------
-- Records of stf_foreign_reside
-- ----------------------------
BEGIN;
INSERT INTO `stf_foreign_reside` VALUES (1, 1, '1', '2018-07-20 00:00:00', '1', '2018-07-20 00:00:00', '2018-07-20 00:00:00', NULL, 0);
INSERT INTO `stf_foreign_reside` VALUES (2, 95, 'tset', '2018-08-15 00:00:00', 'drhd', '2017-08-01 00:00:00', '2017-09-15 00:00:00', '', 0);
INSERT INTO `stf_foreign_reside` VALUES (3, 95, 'ygy', '2018-08-15 00:00:00', 'fgyjkg', '2018-08-01 00:00:00', '2018-08-23 00:00:00', '', 0);
INSERT INTO `stf_foreign_reside` VALUES (4, 95, '1234', '2018-08-15 00:00:00', '755', '2018-08-01 00:00:00', '2018-08-16 00:00:00', 'group1/M00/00/01/rB0PK1t0RZWAakZMAAQaAE1hOl4478.xls', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_foreign_visa
-- ----------------------------
DROP TABLE IF EXISTS `stf_foreign_visa`;
CREATE TABLE `stf_foreign_visa` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '在华外籍员工工作许可证信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `work_permit_no` varchar(255) DEFAULT NULL COMMENT '工作许可编号',
  `certifying_date` datetime DEFAULT NULL COMMENT '发证日期',
  `certifying_authority` varchar(255) DEFAULT NULL COMMENT '发证机关',
  `start_date` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `annual_inspection_date` datetime DEFAULT NULL COMMENT '年检时间',
  `file` varchar(255) DEFAULT NULL COMMENT '附件',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='在华外籍员工工作许可证信息表';

-- ----------------------------
-- Records of stf_foreign_visa
-- ----------------------------
BEGIN;
INSERT INTO `stf_foreign_visa` VALUES (1, 1, '123', '2018-07-20 00:00:00', '123', '2018-07-20 00:00:00', '2018-07-20 00:00:00', '2018-07-20 00:00:00', NULL, 0);
INSERT INTO `stf_foreign_visa` VALUES (2, 95, '123', '2018-08-10 00:00:00', '123', '2018-08-10 00:00:00', '2018-08-10 00:00:00', '2018-08-10 00:00:00', 'group1/M00/00/00/rB0PK1ttA9OAa1WHAAtkwxOwdy0422.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (3, 95, '123', '2018-08-10 00:00:00', '454', '2018-08-10 00:00:00', '2018-08-10 00:00:00', '2018-08-10 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (4, 95, '123', '2018-08-14 00:00:00', '4141', NULL, NULL, '2018-08-14 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (5, 95, '1234', '2018-08-14 00:00:00', '8888', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (6, 95, '123', '2018-08-14 00:00:00', 'hgjftjh', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 'group1/M00/00/00/rB0PK1typ_2Ady9UAAtkwxOwdy0268.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (7, 95, '1234', '2018-08-14 00:00:00', 'yuik', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 'group1/M00/00/00/rB0PK1typ_2Ady9UAAtkwxOwdy0268.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (8, 95, '98762', '2018-08-14 00:00:00', 'uyhtgfds', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 'group1/M00/00/00/rB0PK1tyqQOAGnYMAAW7k2udh5k539.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (9, 95, '789', '2018-08-14 00:00:00', '7852ojh', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 'group1/M00/00/00/rB0PK1tyqQOAGnYMAAW7k2udh5k539.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (10, 95, '741', '2018-08-14 00:00:00', 'frgh414', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 'group1/M00/00/00/rB0PK1tyqbOAAn6mAAVAxZRqjiM683.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (11, 95, '21574', '2018-08-14 00:00:00', 'jpojjhg', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (12, 95, 'dfb', '2018-08-14 00:00:00', 'zsdfgvzsd', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', 'group1/M00/00/00/rB0PK1tyqemAaGBNAAHKTAa1C1Y624.jpg', 1);
INSERT INTO `stf_foreign_visa` VALUES (13, 95, 'trjh', '2018-08-14 00:00:00', 'sgrv', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '2018-08-14 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (14, 95, '1234', '2018-08-09 00:00:00', '755', '2018-08-02 00:00:00', '2018-07-31 00:00:00', NULL, '', 1);
INSERT INTO `stf_foreign_visa` VALUES (15, 95, '74578', '2018-08-15 00:00:00', '52452', '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (16, 95, '75754', '2018-08-15 00:00:00', '7457457', '2018-08-15 00:00:00', NULL, '2018-08-15 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (17, 95, '123', '2018-08-15 00:00:00', 'JUJHH', '2018-08-15 00:00:00', NULL, '2018-08-15 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (18, 95, 'VIU', '2018-08-15 00:00:00', 'RSDFGV', '2018-08-15 00:00:00', NULL, '2018-08-15 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (19, 95, 'SDRGVBSD', '2018-08-15 00:00:00', 'SFVSDFV', '2018-08-15 00:00:00', '2018-08-15 00:00:00', '2018-08-15 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (20, 95, '123', '2018-08-15 00:00:00', '755', NULL, NULL, '2018-08-08 00:00:00', '', 1);
INSERT INTO `stf_foreign_visa` VALUES (21, 95, '1234', '2018-08-15 00:00:00', '755', '2018-08-01 00:00:00', '2018-08-31 00:00:00', '2018-08-31 00:00:00', 'group1/M00/00/01/rB0PK1t7s2uAYu-DAAAu4ssu8qM87.xlsx', 0);
INSERT INTO `stf_foreign_visa` VALUES (22, 95, '1234', '2018-08-15 00:00:00', '755', '2018-08-15 00:00:00', '2018-08-30 00:00:00', '2018-08-15 00:00:00', '', 0);
INSERT INTO `stf_foreign_visa` VALUES (23, 95, '1234sxc', '2018-08-15 00:00:00', 'asd', '2018-08-01 00:00:00', '2018-08-30 00:00:00', '2018-08-15 00:00:00', '', 0);
INSERT INTO `stf_foreign_visa` VALUES (24, 95, 'sdrrgs', '2018-08-15 00:00:00', 'asfsa', '2018-07-31 00:00:00', '2018-08-31 00:00:00', '2018-08-02 00:00:00', '', 0);
INSERT INTO `stf_foreign_visa` VALUES (25, 95, 'rdgb', '2018-08-15 00:00:00', 'fasew', '2018-08-15 00:00:00', '2018-08-22 00:00:00', '2018-08-15 00:00:00', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_occupational_injury
-- ----------------------------
DROP TABLE IF EXISTS `stf_occupational_injury`;
CREATE TABLE `stf_occupational_injury` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '工伤信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `occupational_injury_tyle` bigint(11) DEFAULT NULL COMMENT '工伤类别',
  `occupational_injury_start_date` datetime DEFAULT NULL COMMENT '工伤发生日期',
  `disability_identification_time` datetime DEFAULT NULL COMMENT '伤残程度鉴定时间',
  `disability_level` bigint(11) DEFAULT NULL COMMENT '伤残等级',
  `part_name` varchar(255) DEFAULT NULL COMMENT '认定部位或名称',
  `occupational_injury_time` datetime DEFAULT NULL COMMENT '工伤认定时间',
  `nurse_level` bigint(11) DEFAULT NULL COMMENT '护理程度级别',
  `occupational_injury_no` varchar(255) DEFAULT NULL COMMENT '工伤号',
  `before_injury_salary` double(12,2) DEFAULT NULL,
  `accident_state` bigint(11) DEFAULT NULL COMMENT '事故类别',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='工伤信息表';

-- ----------------------------
-- Records of stf_occupational_injury
-- ----------------------------
BEGIN;
INSERT INTO `stf_occupational_injury` VALUES (1, 1, 94, '2018-07-20 00:00:00', '2018-07-20 00:00:00', 57, '22', '2018-07-20 00:00:00', 67, '22', 2333.00, 82, 0);
INSERT INTO `stf_occupational_injury` VALUES (2, 95, 94, '2018-08-15 00:00:00', '2018-08-16 00:00:00', 59, 'awedfwd', '2018-08-15 00:00:00', 68, 'gsefse', 12312.00, 76, 1);
INSERT INTO `stf_occupational_injury` VALUES (3, 95, 95, '2018-08-02 00:00:00', '2018-08-17 00:00:00', 61, NULL, '2018-08-07 00:00:00', NULL, NULL, NULL, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (4, 95, 95, '2018-08-16 00:00:00', '2018-08-21 00:00:00', 58, '2333', '2018-08-30 00:00:00', NULL, NULL, NULL, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (5, 95, 94, '2018-08-16 00:00:00', '2018-08-21 00:00:00', NULL, NULL, '2018-08-30 00:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_occupational_injury` VALUES (6, 95, 94, '2018-08-16 00:00:00', '2018-08-21 00:00:00', NULL, NULL, '2018-08-30 00:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_occupational_injury` VALUES (7, 95, 95, '2018-08-01 00:00:00', '2018-08-02 00:00:00', NULL, NULL, '2018-08-02 00:00:00', NULL, NULL, NULL, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (8, 95, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (9, 95, 95, '2018-08-16 00:00:00', '2018-08-16 00:00:00', NULL, NULL, '2018-08-16 00:00:00', NULL, NULL, NULL, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (10, 95, 95, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (11, 95, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 124578.00, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (12, 95, 95, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 21455.00, NULL, 1);
INSERT INTO `stf_occupational_injury` VALUES (13, 95, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12345.00, NULL, 0);
INSERT INTO `stf_occupational_injury` VALUES (14, 95, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12345.00, NULL, 0);
INSERT INTO `stf_occupational_injury` VALUES (15, 95, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12345.22, NULL, 0);
INSERT INTO `stf_occupational_injury` VALUES (16, 110, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 123123.00, NULL, 0);
INSERT INTO `stf_occupational_injury` VALUES (17, 110, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1000000000.00, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_outer_experience
-- ----------------------------
DROP TABLE IF EXISTS `stf_outer_experience`;
CREATE TABLE `stf_outer_experience` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '外部工作经历ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `work_unit` varchar(100) DEFAULT NULL COMMENT '工作单位',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '任职部门',
  `position_name` varchar(100) DEFAULT NULL COMMENT '任职职位',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `proof_person_and_duty` varchar(100) DEFAULT NULL COMMENT '证明人及职务',
  `proof_contact` varchar(100) DEFAULT NULL COMMENT '证明人联系方式',
  `salary` varchar(100) DEFAULT NULL COMMENT '薪酬状况',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='外部工作经历表';

-- ----------------------------
-- Records of stf_outer_experience
-- ----------------------------
BEGIN;
INSERT INTO `stf_outer_experience` VALUES (5, 1, '5', '5', '5', '2018-07-18 00:00:00', '2018-07-18 00:00:00', '5', '5', '5', 0);
INSERT INTO `stf_outer_experience` VALUES (22, 1, '2', '2', '2', '2018-07-26 00:00:00', '2018-07-26 00:00:00', '2', '2', '2', 0);
INSERT INTO `stf_outer_experience` VALUES (25, 1, '1', '1', '1', '2018-07-26 00:00:00', '2018-07-26 00:00:00', '1', '1', '1', 0);
INSERT INTO `stf_outer_experience` VALUES (27, 11, '2', '12', '2', '2018-07-31 00:00:00', '2018-07-31 00:00:00', '2', '2', '2', 0);
INSERT INTO `stf_outer_experience` VALUES (42, 95, '3', '3', '3', NULL, '2018-08-15 00:00:00', '3', '3', '3', 0);
INSERT INTO `stf_outer_experience` VALUES (43, 95, '2', '2', '2', NULL, '2018-08-16 00:00:00', '2', '2', '2', 0);
INSERT INTO `stf_outer_experience` VALUES (46, 95, '1', NULL, NULL, '2018-08-21 00:00:00', '2018-08-21 00:00:00', '1', NULL, NULL, 0);
INSERT INTO `stf_outer_experience` VALUES (48, 110, 'qweq', 'qwe', NULL, NULL, NULL, 'qwe', NULL, NULL, 0);
INSERT INTO `stf_outer_experience` VALUES (49, 110, 'erw', 'wqerwe', '', NULL, NULL, 'wer', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (50, 110, 'w4rw', '', '', NULL, NULL, 'wrw3', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (51, 110, 'fws3rw', '', '', NULL, NULL, 'werw', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (52, 110, 'werew', '', '', NULL, NULL, 'werw', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (53, 110, 'awdawd', NULL, NULL, NULL, NULL, 'awdaw', NULL, NULL, 0);
INSERT INTO `stf_outer_experience` VALUES (54, 110, 'awdawd', NULL, NULL, NULL, NULL, 'adwd', NULL, NULL, 0);
INSERT INTO `stf_outer_experience` VALUES (55, 110, '123', NULL, NULL, NULL, NULL, '123', NULL, '123', 0);
INSERT INTO `stf_outer_experience` VALUES (56, 158, '中国农业科学东海农业试验站', '综合处', '办公室主任', '2012-06-01 00:00:00', '2014-06-14 00:00:00', '王海', '13555555555', '60000', 0);
INSERT INTO `stf_outer_experience` VALUES (57, 158, '江苏歌得诺贝', '人力资源部', '专员', '2009-08-01 00:00:00', '2012-06-01 00:00:00', '刘总', '13521144744', '8000', 0);
INSERT INTO `stf_outer_experience` VALUES (58, 160, '海陵中学', '高中部', '英语老师', '2005-09-01 00:00:00', '2009-12-01 00:00:00', '王鹏', '13131331313', '80000', 0);
INSERT INTO `stf_outer_experience` VALUES (82, 170, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (83, 170, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (84, 170, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (85, 170, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (86, 170, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (87, 171, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (88, 171, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (89, 171, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (90, 171, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (91, 171, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (92, 172, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (93, 172, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (94, 172, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (95, 172, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (96, 172, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (97, 177, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (98, 177, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (99, 177, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (100, 177, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (101, 177, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (102, 178, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (103, 178, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (104, 178, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (105, 178, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (106, 178, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (107, 179, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (108, 179, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (109, 179, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (110, 179, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (111, 179, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (112, 180, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (113, 180, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (114, 180, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (115, 180, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (116, 180, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (117, 181, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (118, 181, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (119, 181, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (120, 181, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (121, 181, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (122, 182, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (123, 182, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (124, 182, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (125, 182, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (126, 182, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (127, 183, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (128, 183, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (129, 183, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (130, 183, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (131, 183, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (132, 184, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (133, 184, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (134, 184, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (135, 184, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (136, 184, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (137, 185, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (138, 185, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (139, 185, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (140, 185, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (141, 185, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (142, 188, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (143, 188, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (144, 188, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (145, 188, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (146, 188, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (147, 189, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (148, 189, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (149, 189, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (150, 189, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (151, 189, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (152, 190, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (153, 190, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (154, 190, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (155, 190, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (156, 190, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (157, 191, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (158, 191, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (159, 191, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (160, 191, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (161, 191, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (162, 195, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (163, 195, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (164, 195, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (165, 195, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (166, 195, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (167, 196, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (168, 196, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (169, 196, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (170, 196, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (171, 196, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (172, 197, '邢台市临西县政府', '临西镇政府', '助理', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (173, 197, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (174, 197, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (175, 197, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (176, 197, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (227, 198, '邢台市临西县政府', '临西镇政府 ', '助理 ', '2012-10-01 00:00:00', '2015-07-13 00:00:00', '于亚明', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (228, 198, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (229, 198, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (230, 198, '', '', '', NULL, NULL, '', '', '', 0);
INSERT INTO `stf_outer_experience` VALUES (231, 198, '', '', '', NULL, NULL, '', '', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_project_experience
-- ----------------------------
DROP TABLE IF EXISTS `stf_project_experience`;
CREATE TABLE `stf_project_experience` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '行号',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `fill_post` varchar(255) DEFAULT NULL COMMENT '担任职务',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `responsible_content` varchar(255) DEFAULT NULL COMMENT '负责内容',
  `project_result` varchar(255) DEFAULT NULL COMMENT '项目结果',
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目经历';

-- ----------------------------
-- Records of stf_project_experience
-- ----------------------------
BEGIN;
INSERT INTO `stf_project_experience` VALUES (2, 110, '222221', '222223333333333333333333333333', '2018-09-05 00:00:00', '2018-09-05 00:00:00', '23333', '23333', NULL);
INSERT INTO `stf_project_experience` VALUES (3, 95, '1', '1', '2018-09-05 00:00:00', '2018-09-07 00:00:00', '1', '1', NULL);
INSERT INTO `stf_project_experience` VALUES (16, 170, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (17, 170, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (18, 170, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (19, 171, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (20, 171, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (21, 171, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (22, 172, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (23, 172, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (24, 172, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (25, 177, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (26, 177, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (27, 177, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (28, 178, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (29, 178, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (30, 178, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (31, 179, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (32, 179, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (33, 179, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (34, 180, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (35, 180, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (36, 180, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (37, 181, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (38, 181, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (39, 181, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (40, 182, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (41, 182, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (42, 182, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (43, 183, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (44, 183, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (45, 183, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (46, 184, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (47, 184, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (48, 184, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (49, 185, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (50, 185, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (51, 185, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (52, 188, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (53, 188, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (54, 188, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (55, 189, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (56, 189, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (57, 189, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (58, 190, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (59, 190, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (60, 190, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (61, 191, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (62, 191, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (63, 191, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (64, 195, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (65, 195, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (66, 195, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (67, 196, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (68, 196, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (69, 196, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (70, 197, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (71, 197, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (72, 197, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (103, 198, '', '', NULL, NULL, '', '', NULL);
INSERT INTO `stf_project_experience` VALUES (104, 198, ' ', ' ', NULL, NULL, ' ', ' ', NULL);
INSERT INTO `stf_project_experience` VALUES (105, 198, '', '', NULL, NULL, '', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for stf_punishment
-- ----------------------------
DROP TABLE IF EXISTS `stf_punishment`;
CREATE TABLE `stf_punishment` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '惩处信息ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `punishment_name` varchar(255) DEFAULT NULL COMMENT '惩处名称',
  `punishment_type` varchar(255) DEFAULT NULL COMMENT '惩处类别',
  `punishment_date` datetime DEFAULT NULL COMMENT '受惩处时间',
  `punishment_gist` varchar(255) DEFAULT NULL COMMENT '惩处依据',
  `punishment_cause` varchar(255) DEFAULT NULL COMMENT '惩处事由',
  `punishment_measure` varchar(255) DEFAULT NULL COMMENT '惩处措施',
  `punishment_deadline` varchar(11) DEFAULT NULL COMMENT '惩处期限',
  `ratify_unit` varchar(255) DEFAULT NULL COMMENT '批准单位',
  `revocation_punishment_date` datetime DEFAULT NULL COMMENT '撤销处分时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `file` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='惩处信息表';

-- ----------------------------
-- Records of stf_punishment
-- ----------------------------
BEGIN;
INSERT INTO `stf_punishment` VALUES (1, 1, '业绩未完成', '普通', NULL, '业绩', '业绩未完成', '扣绩效', '1', '公司', '2018-07-20 00:00:00', NULL, NULL, 1);
INSERT INTO `stf_punishment` VALUES (2, 1, '1', '1', '2018-07-20 00:00:00', '1', '1', '1', '1', '1', '2018-07-20 00:00:00', NULL, NULL, 1);
INSERT INTO `stf_punishment` VALUES (3, 1, '业绩未完成', '普通', '2018-07-23 00:00:00', '业绩', '业绩未完成', '扣绩效', '1', '公司', '2018-07-31 00:00:00', NULL, NULL, 0);
INSERT INTO `stf_punishment` VALUES (4, 95, '业绩未完成', '普通', '2018-08-15 00:00:00', '业绩', '业绩未完成', NULL, '一天', NULL, NULL, NULL, NULL, 0);
INSERT INTO `stf_punishment` VALUES (5, 95, '业绩未完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'group1/M00/00/01/rB0PK1t7u0iATBXgAAQaAE1hOl4660.xls', 0);
INSERT INTO `stf_punishment` VALUES (6, 95, '业绩未完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'group1/M00/00/01/rB0PK1t7u1yAP8PjAAAq83fEIzE69.xlsx', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_record_it
-- ----------------------------
DROP TABLE IF EXISTS `stf_record_it`;
CREATE TABLE `stf_record_it` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '计算机能力ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `certificate_name` varchar(255) DEFAULT NULL COMMENT '证书名称',
  `computer_rank` varchar(10) DEFAULT NULL COMMENT '计算机等级',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='计算机能力表';

-- ----------------------------
-- Records of stf_record_it
-- ----------------------------
BEGIN;
INSERT INTO `stf_record_it` VALUES (1, 1, '计算机证书', '一级', 0);
INSERT INTO `stf_record_it` VALUES (2, 1, '计算机证书', '二级', 0);
INSERT INTO `stf_record_it` VALUES (3, 2, '计算机证书', '三级', 0);
INSERT INTO `stf_record_it` VALUES (4, 1, '计算机证书', '三级', 0);
INSERT INTO `stf_record_it` VALUES (5, 95, 'test', 'test', 1);
INSERT INTO `stf_record_it` VALUES (6, 95, '1111111111', 'test', 1);
INSERT INTO `stf_record_it` VALUES (7, 95, 'test', 'test', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_record_lang
-- ----------------------------
DROP TABLE IF EXISTS `stf_record_lang`;
CREATE TABLE `stf_record_lang` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `certificate_name` varchar(255) DEFAULT NULL COMMENT '证书名称',
  `language` varchar(255) DEFAULT NULL COMMENT '语种',
  `rank` varchar(255) DEFAULT NULL COMMENT '等级程度',
  `grade` varchar(255) DEFAULT NULL COMMENT '考试分数',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='语言能力表';

-- ----------------------------
-- Records of stf_record_lang
-- ----------------------------
BEGIN;
INSERT INTO `stf_record_lang` VALUES (1, 1, 'test', '英语', '四级', '360', 0);
INSERT INTO `stf_record_lang` VALUES (3, 95, 'test', '中文', '精通', '100', 1);
INSERT INTO `stf_record_lang` VALUES (4, 95, 'test', '中文', '精通', '100', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_record_profession
-- ----------------------------
DROP TABLE IF EXISTS `stf_record_profession`;
CREATE TABLE `stf_record_profession` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '专业技术职务资格ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '人员信息ID',
  `certificate_name` varchar(255) DEFAULT NULL COMMENT '证书名称',
  `professional_title_rank` varchar(255) DEFAULT NULL COMMENT '职称级别',
  `qualify_time` datetime DEFAULT NULL COMMENT '取得资格时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT '0',
  `certificate_start_date` datetime DEFAULT NULL COMMENT '证书发放日期',
  `certificate_end_date` datetime DEFAULT NULL COMMENT '证书有效期限',
  `grant_org` varchar(255) DEFAULT NULL COMMENT '发放机构',
  `grant_level` varchar(255) DEFAULT NULL COMMENT '发放级别',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='专业技术职务资格表';

-- ----------------------------
-- Records of stf_record_profession
-- ----------------------------
BEGIN;
INSERT INTO `stf_record_profession` VALUES (1, 1, 'test', '123', '2018-07-20 00:00:00', '', 0, NULL, NULL, NULL, NULL);
INSERT INTO `stf_record_profession` VALUES (2, 95, 'test', '1', '2018-09-01 00:00:00', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stf_record_profession` VALUES (3, 95, 'awdwa', 'awdawd', '2018-08-06 00:00:00', 'rthgt', 0, NULL, NULL, NULL, NULL);
INSERT INTO `stf_record_profession` VALUES (4, 95, 'efdes', 'wedawd', NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stf_record_profession` VALUES (5, 95, 'test', 'gggg', '2018-08-16 00:00:00', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stf_record_profession` VALUES (6, 95, 'fgygygy', 'gygygy', NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stf_record_profession` VALUES (7, 110, 'test', '123', '2018-09-04 00:00:00', NULL, 0, '2018-09-04 00:00:00', '2018-09-04 00:00:00', '321', '456');
INSERT INTO `stf_record_profession` VALUES (8, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (9, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (10, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (11, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (12, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (13, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (14, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (15, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (16, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (17, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (18, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (19, 170, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (20, 170, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (21, 170, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (22, 170, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (23, 171, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (24, 171, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (25, 171, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (26, 172, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (27, 172, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (28, 172, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (29, 177, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (30, 177, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (31, 177, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (32, 178, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (33, 178, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (34, 178, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (35, 179, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (36, 179, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (37, 179, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (38, 180, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (39, 180, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (40, 180, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (41, 181, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (42, 181, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (43, 181, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (44, 182, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (45, 182, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (46, 182, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (47, 183, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (48, 183, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (49, 183, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (50, 184, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (51, 184, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (52, 184, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (53, 185, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (54, 185, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (55, 185, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (56, 188, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (57, 188, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (58, 188, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (59, 189, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (60, 189, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (61, 189, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (62, 190, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (63, 190, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (64, 190, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (65, 191, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (66, 191, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (67, 191, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (68, 195, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (69, 195, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (70, 195, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (71, 196, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (72, 196, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (73, 196, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (74, 197, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (75, 197, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (76, 197, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (77, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (78, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (79, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (80, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (81, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (82, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (83, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (84, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (85, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (86, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (87, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (88, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (89, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (90, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (91, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (92, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (93, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (94, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (95, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (96, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (97, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (98, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (99, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (100, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (101, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (102, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (103, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (104, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (105, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (106, 198, '', NULL, NULL, NULL, 1, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (107, 198, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (108, 198, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
INSERT INTO `stf_record_profession` VALUES (109, 198, '', NULL, NULL, NULL, 0, NULL, NULL, '', '');
COMMIT;

-- ----------------------------
-- Table structure for stf_relationship_inner
-- ----------------------------
DROP TABLE IF EXISTS `stf_relationship_inner`;
CREATE TABLE `stf_relationship_inner` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '内部亲属关系ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `relative_id` varchar(255) DEFAULT NULL COMMENT '亲属工号',
  `relative_name` varchar(100) DEFAULT NULL COMMENT '亲属姓名',
  `relationship` varchar(100) DEFAULT NULL COMMENT '与本人关系',
  `company` varchar(100) DEFAULT NULL COMMENT '所在单位',
  `dept` varchar(100) DEFAULT NULL COMMENT '所在部门',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='内部亲属关系表';

-- ----------------------------
-- Records of stf_relationship_inner
-- ----------------------------
BEGIN;
INSERT INTO `stf_relationship_inner` VALUES (2, 1, NULL, '1', '1', '1', '1', '1', 0);
INSERT INTO `stf_relationship_inner` VALUES (5, 95, NULL, '2', '2', '2', '2', '2', 0);
INSERT INTO `stf_relationship_inner` VALUES (22, 170, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (23, 170, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (24, 170, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (25, 170, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (26, 171, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (27, 171, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (28, 171, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (29, 171, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (30, 172, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (31, 172, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (32, 172, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (33, 172, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (34, 177, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (35, 177, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (36, 177, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (37, 177, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (38, 178, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (39, 178, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (40, 178, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (41, 178, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (42, 179, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (43, 179, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (44, 179, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (45, 179, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (46, 180, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (47, 180, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (48, 180, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (49, 180, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (50, 181, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (51, 181, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (52, 181, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (53, 181, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (54, 182, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (55, 182, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (56, 182, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (57, 182, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (58, 183, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (59, 183, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (60, 183, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (61, 183, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (62, 184, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (63, 184, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (64, 184, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (65, 184, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (66, 185, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (67, 185, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (68, 185, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (69, 185, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (70, 188, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (71, 188, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (72, 188, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (73, 188, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (74, 189, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (75, 189, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (76, 189, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (77, 189, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (78, 190, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (79, 190, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (80, 190, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (81, 190, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (82, 191, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (83, 191, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (84, 191, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (85, 191, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (86, 195, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (87, 195, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (88, 195, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (89, 195, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (90, 196, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (91, 196, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (92, 196, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (93, 196, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (94, 197, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (95, 197, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (96, 197, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (97, 197, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (138, 198, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (139, 198, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (140, 198, '', '', '请选择', '请选择', '', '', 0);
INSERT INTO `stf_relationship_inner` VALUES (141, 198, '', '', '请选择', '请选择', '', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_relationship_social
-- ----------------------------
DROP TABLE IF EXISTS `stf_relationship_social`;
CREATE TABLE `stf_relationship_social` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '社会关系ID',
  `staff_id` bigint(10) DEFAULT NULL COMMENT '员工ID',
  `relative_name` varchar(100) DEFAULT NULL COMMENT '亲属姓名',
  `sex` bigint(10) DEFAULT NULL COMMENT '性别',
  `rs_birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `relationship` varchar(100) DEFAULT NULL COMMENT '与本人关系',
  `identity_no` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `permanent_address` varchar(100) DEFAULT NULL COMMENT '常住地址',
  `company` varchar(100) DEFAULT NULL COMMENT '所在单位',
  `position` varchar(100) DEFAULT NULL COMMENT '亲属职位',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='社会关系表';

-- ----------------------------
-- Records of stf_relationship_social
-- ----------------------------
BEGIN;
INSERT INTO `stf_relationship_social` VALUES (7, 95, '2', 7, NULL, '', '', '', '', '', '2', 0);
INSERT INTO `stf_relationship_social` VALUES (8, 95, '3', 8, '2018-08-16 00:00:00', '3', '3', '3', '3', '3', '3', 0);
INSERT INTO `stf_relationship_social` VALUES (9, 95, '5', NULL, '2018-08-16 00:00:00', '5', '5', '5', '5', NULL, '5', 0);
INSERT INTO `stf_relationship_social` VALUES (10, 95, '2', NULL, '2018-08-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '2', 0);
INSERT INTO `stf_relationship_social` VALUES (11, 95, '3', NULL, '2018-08-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '3', 0);
INSERT INTO `stf_relationship_social` VALUES (12, 95, '3', NULL, '2018-08-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '3', 0);
INSERT INTO `stf_relationship_social` VALUES (14, 110, '1', 7, NULL, NULL, NULL, NULL, NULL, NULL, '1', 0);
INSERT INTO `stf_relationship_social` VALUES (15, 110, '2', 7, NULL, NULL, NULL, NULL, NULL, NULL, '2', 0);
INSERT INTO `stf_relationship_social` VALUES (16, 110, '3', 8, NULL, NULL, NULL, NULL, NULL, NULL, '3', 0);
INSERT INTO `stf_relationship_social` VALUES (17, 158, '陈山', 7, '2005-09-04 00:00:00', '配偶', NULL, '连云港市东海县翡翠湾', '晶奥太阳能', '老板', '15151293388', 0);
INSERT INTO `stf_relationship_social` VALUES (18, 160, '姚丽', 8, '2016-09-04 00:00:00', '兄弟姐妹', NULL, '淮安市金湖线东方明珠', '北京', '老师', '18662985858', 0);
INSERT INTO `stf_relationship_social` VALUES (23, 170, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (24, 171, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (25, 172, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (26, 177, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (27, 178, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (28, 179, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (29, 180, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (30, 181, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (31, 182, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (32, 183, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (33, 184, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (34, 185, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (35, 188, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (36, 189, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (37, 190, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (38, 191, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (39, 195, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (40, 196, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (41, 197, '阎瑞冬', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959', 0);
INSERT INTO `stf_relationship_social` VALUES (52, 198, '阎瑞冬 ', 7, '1986-10-25 00:00:00', '配偶', NULL, '', '', '', '17731925959 ', 0);
COMMIT;

-- ----------------------------
-- Table structure for stf_worker_code_rule
-- ----------------------------
DROP TABLE IF EXISTS `stf_worker_code_rule`;
CREATE TABLE `stf_worker_code_rule` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_id` bigint(20) NOT NULL COMMENT ' 基地名称',
  `worker_code_prefix` varchar(255) DEFAULT NULL COMMENT '工号前缀',
  `worker_code_len` int(11) DEFAULT NULL COMMENT '工号长度',
  `format` varchar(255) DEFAULT NULL COMMENT '格式',
  `usable` int(1) DEFAULT NULL COMMENT '是否可用， 0：不可用，1：可用',
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标记， 0：正常， 1：删除',
  `current_id` int(11) NOT NULL DEFAULT '0' COMMENT '当前基地下员工的最近Id',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='工号编码规则';

-- ----------------------------
-- Records of stf_worker_code_rule
-- ----------------------------
BEGIN;
INSERT INTO `stf_worker_code_rule` VALUES (10, 76, 'ASZ', 8, 'ASZXXXXX', 0, 1, 27);
INSERT INTO `stf_worker_code_rule` VALUES (15, 155, 'BJJD', 10, 'BJJDXXXXXX', 0, 1, 26);
INSERT INTO `stf_worker_code_rule` VALUES (16, 160, '1', 1, '1', 1, 1, 0);
INSERT INTO `stf_worker_code_rule` VALUES (17, 175, 'NJ', 8, 'NJXXXXXX', 0, 1, 0);
INSERT INTO `stf_worker_code_rule` VALUES (18, 155, 'BJJD', 10, 'BJJDXXXXXX', 0, 0, 26);
INSERT INTO `stf_worker_code_rule` VALUES (19, 175, 'AS', 9, 'ASXXXXXXX', 1, 1, 0);
INSERT INTO `stf_worker_code_rule` VALUES (20, 187, 'BJJD', 10, 'BJJDXXXXXX', 0, 1, 6);
INSERT INTO `stf_worker_code_rule` VALUES (21, 187, 'AS', 8, 'ASXXXXXX', 0, 1, 6);
INSERT INTO `stf_worker_code_rule` VALUES (22, 187, 'CP', 10, 'CPXXXXXXXX', 0, 1, 6);
INSERT INTO `stf_worker_code_rule` VALUES (23, 187, 'AS', 6, 'ASXXXX', 0, 0, 6);
INSERT INTO `stf_worker_code_rule` VALUES (24, 309, 'CP', 1, 'CP', 0, 1, 0);
INSERT INTO `stf_worker_code_rule` VALUES (25, 309, 'CP', 8, 'CPXXXXXX', 0, 1, 0);
INSERT INTO `stf_worker_code_rule` VALUES (26, 306, 'AS', 8, 'ASXXXXXX', 0, 0, 0);
INSERT INTO `stf_worker_code_rule` VALUES (27, 303, 'BJJD', 11, 'BJJDXXXXXXX', 0, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) DEFAULT NULL COMMENT '编码类型ID',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `desc_` varchar(255) DEFAULT NULL COMMENT '描述',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1118 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='编码表';

-- ----------------------------
-- Records of sys_code
-- ----------------------------
BEGIN;
INSERT INTO `sys_code` VALUES (1, 1, '1', '是', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (2, 1, '0', '否', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (3, 0, '{RD:8}', '随机八位数字', NULL, 1, 0, 1);
INSERT INTO `sys_code` VALUES (4, 0, '{ID:8}', '自增八为数字', '3', 4, 0, 1);
INSERT INTO `sys_code` VALUES (5, 0, '{RS:5}', '随机五位字符串', '2', 5, 0, 1);
INSERT INTO `sys_code` VALUES (7, 2, '1', '男', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (8, 2, '2', '女', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (9, 3, '1', '在职', '在职', 1, 0, 0);
INSERT INTO `sys_code` VALUES (10, 3, '2', '离职', '离职', 2, 0, 0);
INSERT INTO `sys_code` VALUES (11, 2, '3', '保密', '1', 3, 0, 1);
INSERT INTO `sys_code` VALUES (12, 4, '1', '劳动合同', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (13, 4, '2', '聘用合同', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (14, 5, '1', '固定期限', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (15, 5, '2', '无固定期限', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (16, 5, '3', '以完成一定工作任务为期限', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (17, 6, '1', '身份证', '身份证', 1, 0, 0);
INSERT INTO `sys_code` VALUES (18, 6, '2', '护照', '护照', 2, 0, 0);
INSERT INTO `sys_code` VALUES (19, 7, '1', '已婚', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (20, 7, '2', '未婚', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (21, 8, '1', '中共党员', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (22, 8, '2', '中共预备党员', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (23, 8, '3', '共青团员', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (24, 8, '4', '无党派民主人士', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (25, 8, '5', '群众', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (26, 8, '6', '民主党派', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (27, 9, '1', '管理', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (28, 9, '2', '技术', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (29, 9, '3', '专业支持', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (30, 9, '4', '操作', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (31, 9, '5', '残疾', NULL, 5, 0, 1);
INSERT INTO `sys_code` VALUES (32, 10, '1', '社保', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (33, 10, '2', '异地社保', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (34, 10, '3', '外籍社保', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (35, 10, '4', '实习', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (36, 10, '5', '退休', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (37, 10, '6', '劳务', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (38, 10, '7', '其他', NULL, 7, 0, 0);
INSERT INTO `sys_code` VALUES (39, 12, '1', '固定职工', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (40, 12, '2', '合同制职工', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (41, 13, '1', '部门级', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (42, 12, '3', '临时职工', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (43, 12, '4', '计划外职工', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (44, 13, '2', '公司级', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (45, 13, '3', '集团', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (46, 12, '6', '其他', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (47, 13, '4', '市级', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (48, 13, '5', '省部级', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (49, 12, '5', '顾问员工', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (50, 13, '6', '国家级', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (51, 14, '1', '智力', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (52, 14, '2', '精神', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (53, 14, '3', '综合', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (54, 14, '4', '肢体', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (55, 14, '5', '视力', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (56, 14, '5', '其他', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (57, 15, '1', '一级', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (58, 15, '2', '二级', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (59, 15, '3', '三级', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (60, 15, '4', '四级', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (61, 15, '5', '五级', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (62, 15, '6', '六级', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (63, 15, '7', '七级', NULL, 7, 0, 0);
INSERT INTO `sys_code` VALUES (64, 15, '8', '八级', NULL, 8, 0, 0);
INSERT INTO `sys_code` VALUES (65, 15, '9', '九级', NULL, 9, 0, 0);
INSERT INTO `sys_code` VALUES (66, 15, '10', '十级', NULL, 10, 0, 0);
INSERT INTO `sys_code` VALUES (67, 16, '1', '一级', '', 1, 0, 0);
INSERT INTO `sys_code` VALUES (68, 16, '2', '二级', '', 2, 0, 0);
INSERT INTO `sys_code` VALUES (69, 16, '3', '三级', '', 3, 0, 0);
INSERT INTO `sys_code` VALUES (70, 16, '4', '四级', '', 4, 0, 0);
INSERT INTO `sys_code` VALUES (71, 16, '5', '五级', '', 5, 0, 0);
INSERT INTO `sys_code` VALUES (72, 16, '6', '六级', '', 6, 0, 0);
INSERT INTO `sys_code` VALUES (73, 16, '7', '七级', '', 7, 0, 0);
INSERT INTO `sys_code` VALUES (74, 16, '8', '八级', '', 8, 0, 0);
INSERT INTO `sys_code` VALUES (75, 17, '1', '物体打击', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (76, 17, '2', '车辆伤害', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (77, 17, '3', '机械伤害', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (78, 17, '4', '起重伤害', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (79, 17, '5', '触电', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (80, 17, '6', '淹溺', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (81, 17, '7', '灼伤', NULL, 7, 0, 0);
INSERT INTO `sys_code` VALUES (82, 17, '8', '火灾', NULL, 8, 0, 0);
INSERT INTO `sys_code` VALUES (83, 17, '9', '高空坠落', NULL, 9, 0, 0);
INSERT INTO `sys_code` VALUES (84, 17, '10', '坍塌', NULL, 10, 0, 0);
INSERT INTO `sys_code` VALUES (85, 17, '11', '冒顶片帮', NULL, 11, 0, 0);
INSERT INTO `sys_code` VALUES (86, 17, '12', '透水', NULL, 12, 0, 0);
INSERT INTO `sys_code` VALUES (87, 17, '13', '放炮', NULL, 13, 0, 0);
INSERT INTO `sys_code` VALUES (88, 17, '14', '火药爆炸', NULL, 14, 0, 0);
INSERT INTO `sys_code` VALUES (89, 17, '15', '瓦斯煤层爆炸', NULL, 15, 0, 0);
INSERT INTO `sys_code` VALUES (90, 17, '16', '其他爆炸', NULL, 16, 0, 0);
INSERT INTO `sys_code` VALUES (91, 17, '17', '煤与瓦斯冲突', NULL, 17, 0, 0);
INSERT INTO `sys_code` VALUES (92, 17, '18', '中毒与窒息', NULL, 18, 0, 0);
INSERT INTO `sys_code` VALUES (93, 17, '19', '交通事故', NULL, 19, 0, 0);
INSERT INTO `sys_code` VALUES (94, 18, '1', '工伤1', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (95, 18, '2', '工伤2', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (96, 19, '1', '一线', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (97, 19, '2', '二线', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (98, 19, '3', '三线', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (99, 19, '4', '四线', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (100, 19, '5', '五线', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (101, 20, '1', '实习协议', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (102, 20, '2', '上岗协议', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (103, 20, '3', '竞业限制协议', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (104, 21, '1', '未生效', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (105, 21, '2', '履行中', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (106, 21, '3', '已过期', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (107, 21, '4', '已解除', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (108, 22, 'EMPCON', '合同编号固定编号', '', 1, 0, 0);
INSERT INTO `sys_code` VALUES (109, 22, '{ID:6}', '合同编号自增数字', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (111, 23, 'JAXY', '协议编号固定编号', '', 1, 0, 0);
INSERT INTO `sys_code` VALUES (112, 23, '{ID:6}', '协议编号自增数字', '', 2, 0, 0);
INSERT INTO `sys_code` VALUES (113, 24, 'JA', '晶澳总部固定编号', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (114, 24, '{ID:6}', '晶澳总部自增数字', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (115, 25, 'HF', '合肥基地固定编号', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (116, 25, '{ID:6}', '合肥基地自增数字', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (117, 21, '5', '已终止', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (118, 26, 'JAORG', '组织编号固定编号', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (119, 26, '{ID:6}', '组织编号自增数字', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (120, 27, '0', '未续签', NULL, 0, 0, 0);
INSERT INTO `sys_code` VALUES (121, 27, '1', '已续签', '', 1, 0, 0);
INSERT INTO `sys_code` VALUES (122, 28, '1', '未生效', '', 1, 0, 0);
INSERT INTO `sys_code` VALUES (123, 28, '2', '履行中', '', 2, 0, 0);
INSERT INTO `sys_code` VALUES (124, 28, '3', '已过期', '', 3, 0, 0);
INSERT INTO `sys_code` VALUES (125, 28, '4', '已解除', '', 4, 0, 0);
INSERT INTO `sys_code` VALUES (127, 28, '5', '已终止', '', 5, 0, 0);
INSERT INTO `sys_code` VALUES (129, 29, '1', '离职', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (130, 30, '1', '亚洲', '亚洲', 1, 0, 0);
INSERT INTO `sys_code` VALUES (131, 30, '2', '美洲', '美洲', 1, 0, 0);
INSERT INTO `sys_code` VALUES (132, 31, '1', '中国', '中国', 1, 0, 0);
INSERT INTO `sys_code` VALUES (133, 31, '2', '美国', '美国', 1, 0, 0);
INSERT INTO `sys_code` VALUES (134, 32, '1', '人民币', '人民币', 1, 0, 0);
INSERT INTO `sys_code` VALUES (135, 32, '2', '美元', '美元', 1, 0, 0);
INSERT INTO `sys_code` VALUES (136, 30, '3', '非洲', '非洲', 1, 0, 0);
INSERT INTO `sys_code` VALUES (137, 33, 'days', '天', NULL, 0, 0, 0);
INSERT INTO `sys_code` VALUES (138, 33, 'month', '月', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (139, 34, 'wt', '电汇', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (140, 34, 'cash', '现金', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (141, 36, 'new', '新增', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (142, 36, 'signing', '签核中', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (143, 37, 'one', '一级客户', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (144, 37, 'tow', '二级客户', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (147, 38, '00', '框架意向合同', '销售合同类型1', 1, 0, 0);
INSERT INTO `sys_code` VALUES (148, 38, '01', '组件销售合同', '销售合同类型2', 2, 0, 0);
INSERT INTO `sys_code` VALUES (149, 40, '1', '销售合同标题1', '销售合同标题1', 1, 0, 0);
INSERT INTO `sys_code` VALUES (150, 40, '1', '销售合同标题2', '销售合同标题2', 2, 0, 0);
INSERT INTO `sys_code` VALUES (151, 42, '1', '增值税普通发票', '增值税普通发票', 1, 0, 0);
INSERT INTO `sys_code` VALUES (152, 42, '2', '增值税专用发票', '增值税专用发票', 2, 0, 0);
INSERT INTO `sys_code` VALUES (153, 43, '001', '未完税交货', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (154, 43, '002', '完税后交货', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (155, 44, '156SK-PR-4BB', '156SK-PR-4BB', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (156, 44, '156SK-C3-4BB', '156SK-C3-4BB', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (157, 45, 'white', '白色', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (158, 45, 'black', '黑色', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (159, 46, '1', '标准', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (160, 46, '2', '45x35', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (161, 47, '1', '黑色', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (162, 47, '2', '白色', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (163, 48, '1', '标准', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (164, 48, '2', '1500V', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (165, 49, '1', '安费诺', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (166, 49, '2', 'Tigo底座+中环连接器', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (167, 50, '1', '标准', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (168, 50, '2', '普利司通', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (169, 51, '1', '正常回款', '正常回款', 1, 0, 0);
INSERT INTO `sys_code` VALUES (170, 52, 'M', '单晶Mono', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (171, 52, 'P', '多晶Poly', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (172, 52, 'Q', '准单晶Quasi-Mono', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (173, 53, '60', '60', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (174, 53, '72', '72', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (175, 54, 'D', '双玻组件Dual Glass', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (176, 54, 'S', '单玻组件Single Glass', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (177, 55, '00', '无背板no backsheet', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (178, 55, '01', '白背板white backsheet', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (179, 55, '02', '黑背板black backsheet', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (180, 55, '03', '半片白背板half cell&white BS', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (181, 55, '04', 'TG线盒白背板', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (182, 55, '05', 'SE线盒白背板', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (183, 55, '06', 'TG线盒黑背板', NULL, 7, 0, 0);
INSERT INTO `sys_code` VALUES (184, 55, '07', 'SE线盒黑背板', NULL, 8, 0, 0);
INSERT INTO `sys_code` VALUES (185, 56, 'SC', 'Standard Cell', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (186, 56, 'PR', 'PERC', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (187, 56, 'BP', 'Bifacial PERC', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (188, 56, 'HT', 'HTI', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (189, 56, 'IB', 'IBC', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (190, 56, 'MW', 'MWT', NULL, 6, 0, 0);
INSERT INTO `sys_code` VALUES (191, 57, '1', '初步洽谈', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (192, 57, '2', '需求确定', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (193, 58, '1', 'W', '瓦', 1, 0, 0);
INSERT INTO `sys_code` VALUES (194, 59, '1', '高效', '高效', 1, 0, 0);
INSERT INTO `sys_code` VALUES (195, 59, '2', '低效', '低效', 1, 0, 0);
INSERT INTO `sys_code` VALUES (196, 60, '1', '交货方式1', '交货方式', 1, 0, 0);
INSERT INTO `sys_code` VALUES (197, 60, '2', '交货方式2', '交货方式2', 1, 0, 0);
INSERT INTO `sys_code` VALUES (198, 61, '1', '未育', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (199, 61, '4', '已育一胎', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (200, 61, '5', '已育二胎', '', 3, 0, 0);
INSERT INTO `sys_code` VALUES (201, 62, '1', '嘉奖', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (202, 62, '2', '记功', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (203, 62, '3', '记大功', NULL, 3, 0, 0);
INSERT INTO `sys_code` VALUES (204, 62, '4', '加薪', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (205, 62, '5', '升职', NULL, 5, 0, 0);
INSERT INTO `sys_code` VALUES (207, 31, '3', '南非', '南非', 3, 0, 0);
INSERT INTO `sys_code` VALUES (208, 30, '4', '欧洲', '欧洲', 4, 0, 0);
INSERT INTO `sys_code` VALUES (209, 31, '4', '意大利', '意大利', 4, 0, 0);
INSERT INTO `sys_code` VALUES (210, 63, '1', '北京', '北京', 1, 0, 0);
INSERT INTO `sys_code` VALUES (221, 51, '2', '退款', '退款', 2, 0, 0);
INSERT INTO `sys_code` VALUES (222, 51, '3', '定金', '定金', 3, 0, 0);
INSERT INTO `sys_code` VALUES (223, 66, '1', '博士后', '博士后', 1, 0, 0);
INSERT INTO `sys_code` VALUES (224, 66, '2', '名誉博士', '名誉博士', 2, 0, 0);
INSERT INTO `sys_code` VALUES (225, 66, '3', '博士', '博士', 3, 0, 0);
INSERT INTO `sys_code` VALUES (226, 66, '4', '硕士', '硕士', 4, 0, 0);
INSERT INTO `sys_code` VALUES (227, 66, '5', '学士', '学士', 5, 0, 0);
INSERT INTO `sys_code` VALUES (228, 66, '6', '其他', '其他', 6, 0, 0);
INSERT INTO `sys_code` VALUES (229, 67, '1', '博士', '博士', 1, 0, 0);
INSERT INTO `sys_code` VALUES (230, 67, '2', '硕士', '硕士', 2, 0, 0);
INSERT INTO `sys_code` VALUES (231, 67, '3', '本科', '本科', 3, 0, 0);
INSERT INTO `sys_code` VALUES (232, 67, '4', '专科', '专科', 4, 0, 0);
INSERT INTO `sys_code` VALUES (233, 67, '5', '中专或技工学校', '中专或技工学校', 5, 0, 0);
INSERT INTO `sys_code` VALUES (234, 67, '6', '高中', '高中', 6, 0, 0);
INSERT INTO `sys_code` VALUES (235, 67, '7', '初中', '初中', 7, 0, 0);
INSERT INTO `sys_code` VALUES (236, 67, '8', '小学', '小学', 8, 0, 0);
INSERT INTO `sys_code` VALUES (237, 68, '1', '毕业', '毕业', 1, 0, 0);
INSERT INTO `sys_code` VALUES (238, 68, '2', '结业', '结业', 2, 0, 0);
INSERT INTO `sys_code` VALUES (239, 68, '3', '未就业', '未就业', 3, 0, 0);
INSERT INTO `sys_code` VALUES (240, 68, '4', '肄业', '肄业', 4, 0, 0);
INSERT INTO `sys_code` VALUES (241, 69, '1', '毕业证书', '毕业证书', 1, 0, 0);
INSERT INTO `sys_code` VALUES (242, 69, '2', '结业证书', '结业证书', 2, 0, 0);
INSERT INTO `sys_code` VALUES (243, 70, '1', '中国大陆', '中国大陆', 1, 0, 0);
INSERT INTO `sys_code` VALUES (244, 70, '2', '香港', '香港', 2, 0, 0);
INSERT INTO `sys_code` VALUES (245, 70, '3', '台湾', '台湾', 3, 0, 0);
INSERT INTO `sys_code` VALUES (246, 70, '4', '澳门', '澳门', 4, 0, 0);
INSERT INTO `sys_code` VALUES (247, 70, '5', '阿根廷', '阿根廷', 5, 0, 0);
INSERT INTO `sys_code` VALUES (248, 70, '6', '澳大利亚', '澳大利亚', 6, 0, 0);
INSERT INTO `sys_code` VALUES (249, 70, '7', '巴西', '巴西', 7, 0, 0);
INSERT INTO `sys_code` VALUES (250, 70, '8', '俄罗斯', '俄罗斯', 8, 0, 0);
INSERT INTO `sys_code` VALUES (251, 70, '9', '德国', '德国', 9, 0, 0);
INSERT INTO `sys_code` VALUES (252, 70, '10', '加拿大', '加拿大', 10, 0, 0);
INSERT INTO `sys_code` VALUES (253, 70, '11', '马来西亚', '马来西亚', 11, 0, 0);
INSERT INTO `sys_code` VALUES (254, 70, '12', '墨西哥', '墨西哥', 12, 0, 0);
INSERT INTO `sys_code` VALUES (255, 70, '13', '美国', '美国', 13, 0, 0);
INSERT INTO `sys_code` VALUES (256, 70, '14', '日本', '日本', 14, 0, 0);
INSERT INTO `sys_code` VALUES (257, 70, '15', '瑞典', '瑞典', 15, 0, 0);
INSERT INTO `sys_code` VALUES (258, 70, '16', '瑞士', '瑞士', 16, 0, 0);
INSERT INTO `sys_code` VALUES (259, 70, '17', '新西兰', '新西兰', 17, 0, 0);
INSERT INTO `sys_code` VALUES (260, 70, '18', '新加坡', '新加坡', 18, 0, 0);
INSERT INTO `sys_code` VALUES (261, 70, '19', '西班牙', '西班牙', 19, 0, 0);
INSERT INTO `sys_code` VALUES (262, 70, '20', '英国', '英国', 20, 0, 0);
INSERT INTO `sys_code` VALUES (263, 70, '21', '越南', '越南', 21, 0, 0);
INSERT INTO `sys_code` VALUES (264, 70, '22', '其他', '其他', 22, 0, 0);
INSERT INTO `sys_code` VALUES (265, 71, '1', '全日制', '全日制', 1, 0, 0);
INSERT INTO `sys_code` VALUES (266, 71, '2', '正规高等院校', '正规高等院校', 2, 0, 0);
INSERT INTO `sys_code` VALUES (267, 71, '3', '部分民办高校', '部分民办高校', 3, 0, 0);
INSERT INTO `sys_code` VALUES (268, 71, '4', '成人高校（函授，夜大）', '成人高校（函授，夜大）', 4, 0, 0);
INSERT INTO `sys_code` VALUES (269, 71, '5', '自考', '自考', 5, 0, 0);
INSERT INTO `sys_code` VALUES (270, 71, '6', '远程教育（即网络教育）', '远程教育（即网络教育）', 6, 0, 0);
INSERT INTO `sys_code` VALUES (271, 71, '7', '电大', '电大', 7, 0, 0);
INSERT INTO `sys_code` VALUES (272, 71, '8', '其他', '其他', 8, 0, 0);
INSERT INTO `sys_code` VALUES (273, 72, 'payment', '已付', '已付', 1, 0, 0);
INSERT INTO `sys_code` VALUES (274, 72, 'unPayment', '未付', '未付', 2, 0, 0);
INSERT INTO `sys_code` VALUES (275, 73, '1', '一厂', '一厂', 1, 0, 0);
INSERT INTO `sys_code` VALUES (276, 73, '2', '二厂', '二厂', 2, 0, 0);
INSERT INTO `sys_code` VALUES (277, 73, '3', '三厂', '三厂', 3, 0, 0);
INSERT INTO `sys_code` VALUES (278, 58, '2', 'PCS', '片', 2, 0, 0);
INSERT INTO `sys_code` VALUES (279, 74, '1', '白领', '白领', 1, 0, 0);
INSERT INTO `sys_code` VALUES (280, 74, '2', '蓝领一线', '蓝领一线', 2, 0, 0);
INSERT INTO `sys_code` VALUES (281, 74, '3', '蓝领非一线', '蓝领非一线', 3, 0, 0);
INSERT INTO `sys_code` VALUES (284, 33, 'year', '年', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (285, 3, '3', '待岗', '待岗', 3, 0, 0);
INSERT INTO `sys_code` VALUES (286, 3, '4', '退休', '退休', 4, 0, 0);
INSERT INTO `sys_code` VALUES (287, 76, 'gn', '国内', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (288, 76, 'gw', '国外', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (289, 77, 'project', '项目', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (290, 77, 'distribution', '分销', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (291, 78, '1', '初步接洽', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (292, 78, '2', '需求确定', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (293, 79, 'power', '功率超过1000w需要评审', '1000', 1, 0, 0);
INSERT INTO `sys_code` VALUES (294, 79, 'product', '指定产品需要评审', 'JAP72D02-270/BP', 2, 0, 0);
INSERT INTO `sys_code` VALUES (295, 80, '1', '小时', '小时', 1, 0, 0);
INSERT INTO `sys_code` VALUES (296, 80, '2', '天', '天', 2, 0, 0);
INSERT INTO `sys_code` VALUES (297, 80, '3', '周', '周', 3, 0, 0);
INSERT INTO `sys_code` VALUES (298, 80, '4', '月', '月', 4, 0, 0);
INSERT INTO `sys_code` VALUES (299, 75, '1', '操作工', '操作工', 1, 0, 0);
INSERT INTO `sys_code` VALUES (300, 75, '2', 'CQE工程师', 'CQE工程师', 2, 0, 0);
INSERT INTO `sys_code` VALUES (301, 75, '3', 'EHS工程师', 'EHS工程师', 3, 0, 0);
INSERT INTO `sys_code` VALUES (302, 75, '4', 'EHS主任工程师', 'EHS主任工程师', 4, 0, 0);
INSERT INTO `sys_code` VALUES (303, 75, '5', 'EL', 'EL', 5, 0, 0);
INSERT INTO `sys_code` VALUES (304, 75, '6', 'IE工程师', 'IE工程师', 6, 0, 0);
INSERT INTO `sys_code` VALUES (305, 75, '7', 'IE主任工程师', 'IE主任工程师', 7, 0, 0);
INSERT INTO `sys_code` VALUES (306, 75, '8', 'IE助理工程师', 'IE助理工程师', 8, 0, 0);
INSERT INTO `sys_code` VALUES (307, 75, '9', 'IQC专员', 'IQC专员', 9, 0, 0);
INSERT INTO `sys_code` VALUES (308, 75, '10', 'IT工程师', 'IT工程师', 10, 0, 0);
INSERT INTO `sys_code` VALUES (309, 75, '11', 'IT主管', 'IT主管', 11, 0, 0);
INSERT INTO `sys_code` VALUES (310, 75, '12', 'IT主任工程师', 'IT主任工程师', 12, 0, 0);
INSERT INTO `sys_code` VALUES (311, 75, '13', 'IV测试', 'IV测试', 13, 0, 0);
INSERT INTO `sys_code` VALUES (312, 75, '14', 'MES工程师', 'MES工程师', 14, 0, 0);
INSERT INTO `sys_code` VALUES (313, 75, '15', 'OBA', 'OBA', 15, 0, 0);
INSERT INTO `sys_code` VALUES (314, 75, '16', 'OBA专员', 'OBA专员', 16, 0, 0);
INSERT INTO `sys_code` VALUES (315, 75, '17', 'OEM计划专员', 'OEM计划专员', 17, 0, 0);
INSERT INTO `sys_code` VALUES (316, 75, '18', 'OEM统计', 'OEM统计', 18, 0, 0);
INSERT INTO `sys_code` VALUES (317, 75, '19', 'OQA主管', 'OQA主管', 19, 0, 0);
INSERT INTO `sys_code` VALUES (318, 75, '20', 'PDE工程师', 'PDE工程师', 20, 0, 0);
INSERT INTO `sys_code` VALUES (319, 75, '21', 'PIE工程师', 'PIE工程师', 21, 0, 0);
INSERT INTO `sys_code` VALUES (320, 75, '22', 'PMC主管', 'PMC主管', 22, 0, 0);
INSERT INTO `sys_code` VALUES (321, 75, '23', 'PQE工程师', 'PQE工程师', 23, 0, 0);
INSERT INTO `sys_code` VALUES (322, 75, '24', 'PQE技术员', 'PQE技术员', 24, 0, 0);
INSERT INTO `sys_code` VALUES (323, 75, '25', 'PQE领班', 'PQE领班', 25, 0, 0);
INSERT INTO `sys_code` VALUES (324, 75, '26', 'PQE主管', 'PQE主管', 26, 0, 0);
INSERT INTO `sys_code` VALUES (325, 75, '27', 'QSM主管', 'QSM主管', 27, 0, 0);
INSERT INTO `sys_code` VALUES (326, 75, '28', 'SQE工程师', 'SQE工程师', 28, 0, 0);
INSERT INTO `sys_code` VALUES (327, 75, '29', 'WMS高级专员', 'WMS高级专员', 29, 0, 0);
INSERT INTO `sys_code` VALUES (328, 75, '30', '安环工程师', '安环工程师', 30, 0, 0);
INSERT INTO `sys_code` VALUES (329, 75, '31', '安环主管', '安环主管', 31, 0, 0);
INSERT INTO `sys_code` VALUES (330, 75, '32', '安环专员', '安环专员', 32, 0, 0);
INSERT INTO `sys_code` VALUES (331, 75, '33', '安检', '安检', 33, 0, 0);
INSERT INTO `sys_code` VALUES (332, 75, '34', '安检班长', '安检班长', 34, 0, 0);
INSERT INTO `sys_code` VALUES (333, 75, '35', '安检员', '安检员', 35, 0, 0);
INSERT INTO `sys_code` VALUES (334, 75, '36', '安检组长', '安检组长', 36, 0, 0);
INSERT INTO `sys_code` VALUES (335, 75, '37', '安全工程师', '安全工程师', 37, 0, 0);
INSERT INTO `sys_code` VALUES (336, 75, '38', '安全助理工程师', '安全助理工程师', 38, 0, 0);
INSERT INTO `sys_code` VALUES (337, 75, '39', '班长', '班长', 39, 0, 0);
INSERT INTO `sys_code` VALUES (339, 75, '41', '班组长', '班组长', 41, 0, 0);
INSERT INTO `sys_code` VALUES (340, 75, '42', '包装组组长', '包装组组长', 42, 0, 0);
INSERT INTO `sys_code` VALUES (341, 75, '43', '保安', '保安', 43, 0, 0);
INSERT INTO `sys_code` VALUES (342, 75, '44', '保洁', '保洁', 44, 0, 0);
INSERT INTO `sys_code` VALUES (343, 75, '45', '保险专员', '保险专员', 45, 0, 0);
INSERT INTO `sys_code` VALUES (344, 75, '46', '保养技术员', '保养技术员', 46, 0, 0);
INSERT INTO `sys_code` VALUES (345, 75, '47', '备品备件采购专员', '备品备件采购专员', 47, 0, 0);
INSERT INTO `sys_code` VALUES (346, 75, '48', '备品采购员', '备品采购员', 48, 0, 0);
INSERT INTO `sys_code` VALUES (347, 75, '49', '标板管理员', '标板管理员', 49, 0, 0);
INSERT INTO `sys_code` VALUES (348, 75, '50', '部门经理', '部门经理', 50, 0, 0);
INSERT INTO `sys_code` VALUES (349, 75, '51', '材料工程师', '材料工程师', 51, 0, 0);
INSERT INTO `sys_code` VALUES (350, 75, '52', '材料统计', '材料统计', 52, 0, 0);
INSERT INTO `sys_code` VALUES (351, 75, '53', '财务部经理', '财务部经理', 53, 0, 0);
INSERT INTO `sys_code` VALUES (352, 75, '54', '财务经理', '财务经理', 54, 0, 0);
INSERT INTO `sys_code` VALUES (353, 75, '55', '财务经理助理', '财务经理助理', 55, 0, 0);
INSERT INTO `sys_code` VALUES (354, 75, '56', '财务主管', '财务主管', 56, 0, 0);
INSERT INTO `sys_code` VALUES (355, 75, '57', '财务主管及总账会计', '财务主管及总账会计', 57, 0, 0);
INSERT INTO `sys_code` VALUES (356, 75, '58', '财务助理', '财务助理', 58, 0, 0);
INSERT INTO `sys_code` VALUES (357, 75, '59', '采购副经理', '采购副经理', 59, 0, 0);
INSERT INTO `sys_code` VALUES (358, 75, '60', '采购内勤', '采购内勤', 60, 0, 0);
INSERT INTO `sys_code` VALUES (359, 75, '61', '采购员', '采购员', 61, 0, 0);
INSERT INTO `sys_code` VALUES (360, 75, '62', '采购主管', '采购主管', 62, 0, 0);
INSERT INTO `sys_code` VALUES (361, 75, '63', '采购助理', '采购助理', 63, 0, 0);
INSERT INTO `sys_code` VALUES (362, 75, '64', '采购专员', '采购专员', 64, 0, 0);
INSERT INTO `sys_code` VALUES (363, 75, '65', '仓管管理员', '仓管管理员', 65, 0, 0);
INSERT INTO `sys_code` VALUES (364, 75, '66', '仓管员', '仓管员', 66, 0, 0);
INSERT INTO `sys_code` VALUES (365, 75, '67', '仓库管理', '仓库管理', 67, 0, 0);
INSERT INTO `sys_code` VALUES (366, 75, '68', '仓库管理员', '仓库管理员', 68, 0, 0);
INSERT INTO `sys_code` VALUES (367, 75, '69', '仓库主管', '仓库主管', 69, 0, 0);
INSERT INTO `sys_code` VALUES (369, 75, '71', '测试工程师', '测试工程师', 71, 0, 0);
INSERT INTO `sys_code` VALUES (370, 75, '72', '层压', '层压', 72, 0, 0);
INSERT INTO `sys_code` VALUES (371, 75, '73', '叉车工', '叉车工', 73, 0, 0);
INSERT INTO `sys_code` VALUES (372, 75, '74', '叉车司机', '叉车司机', 74, 0, 0);
INSERT INTO `sys_code` VALUES (373, 75, '75', '缠膜', '缠膜', 75, 0, 0);
INSERT INTO `sys_code` VALUES (374, 75, '76', '缠膜后打包', '缠膜后打包', 76, 0, 0);
INSERT INTO `sys_code` VALUES (375, 75, '77', '缠膜前打包', '缠膜前打包', 77, 0, 0);
INSERT INTO `sys_code` VALUES (376, 75, '78', '产成品库管', '产成品库管', 78, 0, 0);
INSERT INTO `sys_code` VALUES (377, 75, '79', '常务副总', '常务副总', 79, 0, 0);
INSERT INTO `sys_code` VALUES (378, 75, '80', '常务副总经理', '常务副总经理', 80, 0, 0);
INSERT INTO `sys_code` VALUES (379, 75, '81', '厂务助理工程师', '厂务助理工程师', 81, 0, 0);
INSERT INTO `sys_code` VALUES (380, 75, '82', '厂长', '厂长', 82, 0, 0);
INSERT INTO `sys_code` VALUES (381, 75, '83', '车队队长', '车队队长', 83, 0, 0);
INSERT INTO `sys_code` VALUES (382, 75, '84', '车间班长', '车间班长', 84, 0, 0);
INSERT INTO `sys_code` VALUES (383, 75, '85', '车间副主任', '车间副主任', 85, 0, 0);
INSERT INTO `sys_code` VALUES (384, 75, '86', '车间工艺主管', '车间工艺主管', 86, 0, 0);
INSERT INTO `sys_code` VALUES (385, 75, '87', '车间主管', '车间主管', 87, 0, 0);
INSERT INTO `sys_code` VALUES (386, 75, '88', '车间主任', '车间主任', 88, 0, 0);
INSERT INTO `sys_code` VALUES (387, 75, '89', '成本会计', '成本会计', 89, 0, 0);
INSERT INTO `sys_code` VALUES (388, 75, '90', '成本及出口退税会计', '成本及出口退税会计', 90, 0, 0);
INSERT INTO `sys_code` VALUES (389, 75, '91', '成品班长', '成品班长', 91, 0, 0);
INSERT INTO `sys_code` VALUES (390, 75, '92', '成品仓叉车司机', '成品仓叉车司机', 92, 0, 0);
INSERT INTO `sys_code` VALUES (391, 75, '93', '成品仓管理员', '成品仓管理员', 93, 0, 0);
INSERT INTO `sys_code` VALUES (392, 75, '94', '成品仓统计员', '成品仓统计员', 94, 0, 0);
INSERT INTO `sys_code` VALUES (393, 75, '95', '成品库管员', '成品库管员', 95, 0, 0);
INSERT INTO `sys_code` VALUES (394, 75, '96', '成品中心主管', '成品中心主管', 96, 0, 0);
INSERT INTO `sys_code` VALUES (395, 75, '97', '抽检员', '抽检员', 97, 0, 0);
INSERT INTO `sys_code` VALUES (396, 75, '98', '出货检验班长', '出货检验班长', 98, 0, 0);
INSERT INTO `sys_code` VALUES (397, 75, '99', '出货检验员', '出货检验员', 99, 0, 0);
INSERT INTO `sys_code` VALUES (398, 75, '100', '出货数据统计员', '出货数据统计员', 100, 0, 0);
INSERT INTO `sys_code` VALUES (399, 75, '101', '出货主管', '出货主管', 101, 0, 0);
INSERT INTO `sys_code` VALUES (400, 75, '102', '出口专员', '出口专员', 102, 0, 0);
INSERT INTO `sys_code` VALUES (401, 75, '103', '出纳', '出纳', 103, 0, 0);
INSERT INTO `sys_code` VALUES (402, 75, '104', '初级工程师', '初级工程师', 104, 0, 0);
INSERT INTO `sys_code` VALUES (403, 75, '105', '初级技师', '初级技师', 105, 0, 0);
INSERT INTO `sys_code` VALUES (404, 75, '106', '初级技术员', '初级技术员', 106, 0, 0);
INSERT INTO `sys_code` VALUES (405, 75, '107', '初级检验员', '初级检验员', 107, 0, 0);
INSERT INTO `sys_code` VALUES (406, 75, '108', '初级库管员', '初级库管员', 108, 0, 0);
INSERT INTO `sys_code` VALUES (407, 75, '109', '初级维修工', '初级维修工', 109, 0, 0);
INSERT INTO `sys_code` VALUES (408, 75, '110', '初级运行工', '初级运行工', 110, 0, 0);
INSERT INTO `sys_code` VALUES (409, 75, '111', '初级助理', '初级助理', 111, 0, 0);
INSERT INTO `sys_code` VALUES (410, 75, '112', '初级专员', '初级专员', 112, 0, 0);
INSERT INTO `sys_code` VALUES (411, 75, '113', '储备干部', '储备干部', 113, 0, 0);
INSERT INTO `sys_code` VALUES (412, 75, '114', '穿引线', '穿引线', 114, 0, 0);
INSERT INTO `sys_code` VALUES (413, 75, '115', '代理班长', '代理班长', 115, 0, 0);
INSERT INTO `sys_code` VALUES (414, 75, '116', '代理主管', '代理主管', 116, 0, 0);
INSERT INTO `sys_code` VALUES (415, 75, '117', '代理组长', '代理组长', 117, 0, 0);
INSERT INTO `sys_code` VALUES (416, 75, '118', '单晶打票员', '单晶打票员', 118, 0, 0);
INSERT INTO `sys_code` VALUES (417, 75, '119', '单晶加工操作工', '单晶加工操作工', 119, 0, 0);
INSERT INTO `sys_code` VALUES (418, 75, '120', '单晶加工车间班长', '单晶加工车间班长', 120, 0, 0);
INSERT INTO `sys_code` VALUES (419, 75, '121', '单晶加工车间主任', '单晶加工车间主任', 121, 0, 0);
INSERT INTO `sys_code` VALUES (420, 75, '122', '单晶加工滚磨', '单晶加工滚磨', 122, 0, 0);
INSERT INTO `sys_code` VALUES (421, 75, '123', '单晶加工截断', '单晶加工截断', 123, 0, 0);
INSERT INTO `sys_code` VALUES (422, 75, '124', '单晶加工开方', '单晶加工开方', 124, 0, 0);
INSERT INTO `sys_code` VALUES (423, 75, '125', '单晶品管测试', '单晶品管测试', 125, 0, 0);
INSERT INTO `sys_code` VALUES (424, 75, '126', '单晶品管初检', '单晶品管初检', 126, 0, 0);
INSERT INTO `sys_code` VALUES (425, 75, '127', '单晶品管方检', '单晶品管方检', 127, 0, 0);
INSERT INTO `sys_code` VALUES (426, 75, '128', '单晶品管科班长', '单晶品管科班长', 128, 0, 0);
INSERT INTO `sys_code` VALUES (427, 75, '129', '单晶品管科主任', '单晶品管科主任', 129, 0, 0);
INSERT INTO `sys_code` VALUES (428, 75, '130', '单晶品管配刀', '单晶品管配刀', 130, 0, 0);
INSERT INTO `sys_code` VALUES (429, 75, '131', '导入工程师', '导入工程师', 131, 0, 0);
INSERT INTO `sys_code` VALUES (430, 75, '132', '电工', '电工', 132, 0, 0);
INSERT INTO `sys_code` VALUES (431, 75, '133', '电力工程师', '电力工程师', 133, 0, 0);
INSERT INTO `sys_code` VALUES (432, 75, '134', '电力主管', '电力主管', 134, 0, 0);
INSERT INTO `sys_code` VALUES (433, 75, '135', '电力主任工程师', '电力主任工程师', 135, 0, 0);
INSERT INTO `sys_code` VALUES (434, 75, '136', '电气维护工程师', '电气维护工程师', 136, 0, 0);
INSERT INTO `sys_code` VALUES (435, 75, '137', '电气助理工程师', '电气助理工程师', 137, 0, 0);
INSERT INTO `sys_code` VALUES (436, 75, '138', '叠层', '叠层', 138, 0, 0);
INSERT INTO `sys_code` VALUES (437, 75, '139', '顶岗实习生', '顶岗实习生', 139, 0, 0);
INSERT INTO `sys_code` VALUES (438, 75, '140', '动力部经理助理', '动力部经理助理', 140, 0, 0);
INSERT INTO `sys_code` VALUES (439, 75, '141', '动力工程师', '动力工程师', 141, 0, 0);
INSERT INTO `sys_code` VALUES (440, 75, '142', '动力经理', '动力经理', 142, 0, 0);
INSERT INTO `sys_code` VALUES (441, 75, '143', '动力运行工', '动力运行工', 143, 0, 0);
INSERT INTO `sys_code` VALUES (442, 75, '144', '多晶加工车间班长', '多晶加工车间班长', 144, 0, 0);
INSERT INTO `sys_code` VALUES (443, 75, '145', '多晶加工车间主任', '多晶加工车间主任', 145, 0, 0);
INSERT INTO `sys_code` VALUES (444, 75, '146', '多晶加工倒角', '多晶加工倒角', 146, 0, 0);
INSERT INTO `sys_code` VALUES (445, 75, '147', '多晶加工开方', '多晶加工开方', 147, 0, 0);
INSERT INTO `sys_code` VALUES (446, 75, '148', '多晶加工毛刷', '多晶加工毛刷', 148, 0, 0);
INSERT INTO `sys_code` VALUES (447, 75, '149', '多晶加工平磨', '多晶加工平磨', 149, 0, 0);
INSERT INTO `sys_code` VALUES (448, 75, '150', '多晶加工切断', '多晶加工切断', 150, 0, 0);
INSERT INTO `sys_code` VALUES (449, 75, '151', '多晶品管开方检', '多晶品管开方检', 151, 0, 0);
INSERT INTO `sys_code` VALUES (450, 75, '152', '多晶品管科主任', '多晶品管科主任', 152, 0, 0);
INSERT INTO `sys_code` VALUES (451, 75, '153', '多晶品管配刀', '多晶品管配刀', 153, 0, 0);
INSERT INTO `sys_code` VALUES (452, 75, '154', '多晶品管研磨检', '多晶品管研磨检', 154, 0, 0);
INSERT INTO `sys_code` VALUES (453, 75, '155', '多晶研磨车间主任', '多晶研磨车间主任', 155, 0, 0);
INSERT INTO `sys_code` VALUES (454, 75, '156', '二级品、等外品外销专员', '二级品、等外品外销专员', 156, 0, 0);
INSERT INTO `sys_code` VALUES (455, 75, '157', '返箱小组', '返箱小组', 157, 0, 0);
INSERT INTO `sys_code` VALUES (456, 75, '158', '返修', '返修', 158, 0, 0);
INSERT INTO `sys_code` VALUES (457, 75, '159', '费用会计', '费用会计', 159, 0, 0);
INSERT INTO `sys_code` VALUES (458, 75, '160', '分选机操作工', '分选机操作工', 160, 0, 0);
INSERT INTO `sys_code` VALUES (459, 75, '161', '封装工', '封装工', 161, 0, 0);
INSERT INTO `sys_code` VALUES (460, 75, '162', '辅材加工部经理', '辅材加工部经理', 162, 0, 0);
INSERT INTO `sys_code` VALUES (461, 75, '163', '辅料备件库管', '辅料备件库管', 163, 0, 0);
INSERT INTO `sys_code` VALUES (462, 75, '164', '辅助工', '辅助工', 164, 0, 0);
INSERT INTO `sys_code` VALUES (463, 75, '165', '副班长', '副班长', 165, 0, 0);
INSERT INTO `sys_code` VALUES (464, 75, '166', '副经理', '副经理', 166, 0, 0);
INSERT INTO `sys_code` VALUES (465, 75, '167', '副经理兼工会主席', '副经理兼工会主席', 167, 0, 0);
INSERT INTO `sys_code` VALUES (466, 75, '168', '副主管', '副主管', 168, 0, 0);
INSERT INTO `sys_code` VALUES (467, 75, '169', '副主任', '副主任', 169, 0, 0);
INSERT INTO `sys_code` VALUES (468, 75, '170', '副总经理', '副总经理', 170, 0, 0);
INSERT INTO `sys_code` VALUES (469, 75, '171', '改善工程师', '改善工程师', 171, 0, 0);
INSERT INTO `sys_code` VALUES (470, 75, '172', '高级工程师', '高级工程师', 172, 0, 0);
INSERT INTO `sys_code` VALUES (471, 75, '173', '高级会计', '高级会计', 173, 0, 0);
INSERT INTO `sys_code` VALUES (472, 75, '174', '高级经理', '高级经理', 174, 0, 0);
INSERT INTO `sys_code` VALUES (473, 75, '175', '高级主管', '高级主管', 175, 0, 0);
INSERT INTO `sys_code` VALUES (474, 75, '176', '高级助理', '高级助理', 176, 0, 0);
INSERT INTO `sys_code` VALUES (475, 75, '177', '高级专员', '高级专员', 177, 0, 0);
INSERT INTO `sys_code` VALUES (476, 75, '178', '工程师', '工程师', 178, 0, 0);
INSERT INTO `sys_code` VALUES (477, 75, '179', '工程专员', '工程专员', 179, 0, 0);
INSERT INTO `sys_code` VALUES (478, 75, '180', '工会及文体专员', '工会及文体专员', 180, 0, 0);
INSERT INTO `sys_code` VALUES (479, 75, '181', '工会内勤', '工会内勤', 181, 0, 0);
INSERT INTO `sys_code` VALUES (480, 75, '182', '工艺PIE主管', '工艺PIE主管', 182, 0, 0);
INSERT INTO `sys_code` VALUES (481, 75, '183', '工艺班长', '工艺班长', 183, 0, 0);
INSERT INTO `sys_code` VALUES (482, 75, '184', '工艺初级技术员', '工艺初级技术员', 184, 0, 0);
INSERT INTO `sys_code` VALUES (483, 75, '185', '工艺副经理', '工艺副经理', 185, 0, 0);
INSERT INTO `sys_code` VALUES (484, 75, '186', '工艺工程师', '工艺工程师', 186, 0, 0);
INSERT INTO `sys_code` VALUES (485, 75, '187', '工艺技术员', '工艺技术员', 187, 0, 0);
INSERT INTO `sys_code` VALUES (486, 75, '188', '工艺经理', '工艺经理', 188, 0, 0);
INSERT INTO `sys_code` VALUES (487, 75, '189', '工艺经理助理', '工艺经理助理', 189, 0, 0);
INSERT INTO `sys_code` VALUES (488, 75, '190', '工艺文员', '工艺文员', 190, 0, 0);
INSERT INTO `sys_code` VALUES (489, 75, '191', '工艺员', '工艺员', 191, 0, 0);
INSERT INTO `sys_code` VALUES (490, 75, '192', '工艺主管', '工艺主管', 192, 0, 0);
INSERT INTO `sys_code` VALUES (491, 75, '193', '工艺主管工程师', '工艺主管工程师', 193, 0, 0);
INSERT INTO `sys_code` VALUES (492, 75, '194', '工艺助理工程师', '工艺助理工程师', 194, 0, 0);
INSERT INTO `sys_code` VALUES (493, 75, '195', '工装安装', '工装安装', 195, 0, 0);
INSERT INTO `sys_code` VALUES (494, 75, '196', '供应商质量工程师', '供应商质量工程师', 196, 0, 0);
INSERT INTO `sys_code` VALUES (495, 75, '197', '固定资产/税务会计', '固定资产/税务会计', 197, 0, 0);
INSERT INTO `sys_code` VALUES (496, 75, '198', '固定资产会计', '固定资产会计', 198, 0, 0);
INSERT INTO `sys_code` VALUES (497, 75, '199', '规划工程师', '规划工程师', 199, 0, 0);
INSERT INTO `sys_code` VALUES (498, 75, '200', '规划主管', '规划主管', 200, 0, 0);
INSERT INTO `sys_code` VALUES (499, 75, '201', '硅棒、硅锭内采专员', '硅棒、硅锭内采专员', 201, 0, 0);
INSERT INTO `sys_code` VALUES (500, 75, '202', '硅片内销专员', '硅片内销专员', 202, 0, 0);
INSERT INTO `sys_code` VALUES (501, 75, '203', '硅片外销专员', '硅片外销专员', 203, 0, 0);
INSERT INTO `sys_code` VALUES (502, 75, '204', '过程工程师', '过程工程师', 204, 0, 0);
INSERT INTO `sys_code` VALUES (503, 75, '205', '过程检验班长', '过程检验班长', 205, 0, 0);
INSERT INTO `sys_code` VALUES (504, 75, '206', '过程检验员', '过程检验员', 206, 0, 0);
INSERT INTO `sys_code` VALUES (505, 75, '207', '过程主管', '过程主管', 207, 0, 0);
INSERT INTO `sys_code` VALUES (506, 75, '208', '焊机', '焊机', 208, 0, 0);
INSERT INTO `sys_code` VALUES (507, 75, '209', '行政主管', '行政主管', 209, 0, 0);
INSERT INTO `sys_code` VALUES (508, 75, '210', '行政助理', '行政助理', 210, 0, 0);
INSERT INTO `sys_code` VALUES (509, 75, '211', '行政专员', '行政专员', 211, 0, 0);
INSERT INTO `sys_code` VALUES (510, 75, '212', '行政总监', '行政总监', 212, 0, 0);
INSERT INTO `sys_code` VALUES (511, 75, '213', '耗材库管员', '耗材库管员', 213, 0, 0);
INSERT INTO `sys_code` VALUES (512, 75, '214', '合同管理、内勤员', '合同管理、内勤员', 214, 0, 0);
INSERT INTO `sys_code` VALUES (513, 75, '215', '合同管理员', '合同管理员', 215, 0, 0);
INSERT INTO `sys_code` VALUES (514, 75, '216', '后勤主管', '后勤主管', 216, 0, 0);
INSERT INTO `sys_code` VALUES (515, 75, '217', '化学实验室化验员', '化学实验室化验员', 217, 0, 0);
INSERT INTO `sys_code` VALUES (516, 75, '218', '换板工', '换板工', 218, 0, 0);
INSERT INTO `sys_code` VALUES (517, 75, '219', '会计', '会计', 219, 0, 0);
INSERT INTO `sys_code` VALUES (518, 75, '220', '活动中心管理员', '活动中心管理员', 220, 0, 0);
INSERT INTO `sys_code` VALUES (519, 75, '221', '货车司机', '货车司机', 221, 0, 0);
INSERT INTO `sys_code` VALUES (520, 75, '222', '计划主管', '计划主管', 222, 0, 0);
INSERT INTO `sys_code` VALUES (521, 75, '223', '计划专员', '计划专员', 223, 0, 0);
INSERT INTO `sys_code` VALUES (522, 75, '224', '计量工程师', '计量工程师', 224, 0, 0);
INSERT INTO `sys_code` VALUES (523, 75, '225', '技师', '技师', 225, 0, 0);
INSERT INTO `sys_code` VALUES (524, 75, '226', '技术工程师', '技术工程师', 226, 0, 0);
INSERT INTO `sys_code` VALUES (525, 75, '227', '技术员', '技术员', 227, 0, 0);
INSERT INTO `sys_code` VALUES (526, 75, '228', '技术总监', '技术总监', 228, 0, 0);
INSERT INTO `sys_code` VALUES (527, 75, '229', '绩效专员', '绩效专员', 229, 0, 0);
INSERT INTO `sys_code` VALUES (528, 75, '230', '加工车间主任', '加工车间主任', 230, 0, 0);
INSERT INTO `sys_code` VALUES (529, 75, '231', '驾驶员', '驾驶员', 231, 0, 0);
INSERT INTO `sys_code` VALUES (530, 75, '232', '检测工程师', '检测工程师', 232, 0, 0);
INSERT INTO `sys_code` VALUES (531, 75, '233', '检测员', '检测员', 233, 0, 0);
INSERT INTO `sys_code` VALUES (532, 75, '234', '检料工', '检料工', 234, 0, 0);
INSERT INTO `sys_code` VALUES (533, 75, '235', '检验员', '检验员', 235, 0, 0);
INSERT INTO `sys_code` VALUES (534, 75, '236', '接线盒焊接', '接线盒焊接', 236, 0, 0);
INSERT INTO `sys_code` VALUES (535, 75, '237', '截断工', '截断工', 237, 0, 0);
INSERT INTO `sys_code` VALUES (536, 75, '238', '借调', '借调', 238, 0, 0);
INSERT INTO `sys_code` VALUES (537, 75, '239', '进出口专员', '进出口专员', 239, 0, 0);
INSERT INTO `sys_code` VALUES (538, 75, '240', '进料检验班长', '进料检验班长', 240, 0, 0);
INSERT INTO `sys_code` VALUES (539, 75, '241', '进料检验工程师', '进料检验工程师', 241, 0, 0);
INSERT INTO `sys_code` VALUES (540, 75, '242', '进料检验员', '进料检验员', 242, 0, 0);
INSERT INTO `sys_code` VALUES (541, 75, '243', '经理', '经理', 243, 0, 0);
INSERT INTO `sys_code` VALUES (542, 75, '244', '经理助理', '经理助理', 244, 0, 0);
INSERT INTO `sys_code` VALUES (543, 75, '245', '经理助理兼安环主管', '经理助理兼安环主管', 245, 0, 0);
INSERT INTO `sys_code` VALUES (544, 75, '246', '镜检', '镜检', 246, 0, 0);
INSERT INTO `sys_code` VALUES (545, 75, '247', '开槽工', '开槽工', 247, 0, 0);
INSERT INTO `sys_code` VALUES (546, 75, '248', '可靠性实验室检验员', '可靠性实验室检验员', 248, 0, 0);
INSERT INTO `sys_code` VALUES (547, 75, '249', '客服&实验室主管', '客服&实验室主管', 249, 0, 0);
INSERT INTO `sys_code` VALUES (548, 75, '250', '客服工程师', '客服工程师', 250, 0, 0);
INSERT INTO `sys_code` VALUES (549, 75, '251', '客服实验室工程师', '客服实验室工程师', 251, 0, 0);
INSERT INTO `sys_code` VALUES (550, 75, '252', '客服专员', '客服专员', 252, 0, 0);
INSERT INTO `sys_code` VALUES (551, 75, '253', '库管员', '库管员', 253, 0, 0);
INSERT INTO `sys_code` VALUES (552, 75, '254', '拉晶操作工', '拉晶操作工', 254, 0, 0);
INSERT INTO `sys_code` VALUES (553, 75, '255', '拉晶二车间班长', '拉晶二车间班长', 255, 0, 0);
INSERT INTO `sys_code` VALUES (554, 75, '256', '拉晶二车间主任', '拉晶二车间主任', 256, 0, 0);
INSERT INTO `sys_code` VALUES (555, 75, '257', '拉晶清炉工', '拉晶清炉工', 257, 0, 0);
INSERT INTO `sys_code` VALUES (556, 75, '258', '拉晶统计', '拉晶统计', 258, 0, 0);
INSERT INTO `sys_code` VALUES (557, 75, '259', '拉晶物料员', '拉晶物料员', 259, 0, 0);
INSERT INTO `sys_code` VALUES (558, 75, '260', '拉晶新员工', '拉晶新员工', 260, 0, 0);
INSERT INTO `sys_code` VALUES (559, 75, '261', '拉晶一车间班长', '拉晶一车间班长', 261, 0, 0);
INSERT INTO `sys_code` VALUES (560, 75, '262', '拉晶一车间主任', '拉晶一车间主任', 262, 0, 0);
INSERT INTO `sys_code` VALUES (561, 75, '263', '拉晶引径工', '拉晶引径工', 263, 0, 0);
INSERT INTO `sys_code` VALUES (562, 75, '264', '拉晶装料工', '拉晶装料工', 264, 0, 0);
INSERT INTO `sys_code` VALUES (563, 75, '265', '来料检测', '来料检测', 265, 0, 0);
INSERT INTO `sys_code` VALUES (564, 75, '266', '来料检测员', '来料检测员', 266, 0, 0);
INSERT INTO `sys_code` VALUES (565, 75, '267', '劳动关系专员', '劳动关系专员', 267, 0, 0);
INSERT INTO `sys_code` VALUES (566, 75, '268', '理货员', '理货员', 268, 0, 0);
INSERT INTO `sys_code` VALUES (567, 75, '269', '领班', '领班', 269, 0, 0);
INSERT INTO `sys_code` VALUES (568, 75, '270', '领料工', '领料工', 270, 0, 0);
INSERT INTO `sys_code` VALUES (569, 75, '271', '铭牌', '铭牌', 271, 0, 0);
INSERT INTO `sys_code` VALUES (570, 75, '272', '磨刀工', '磨刀工', 272, 0, 0);
INSERT INTO `sys_code` VALUES (571, 75, '273', '暖通高级技术员', '暖通高级技术员', 273, 0, 0);
INSERT INTO `sys_code` VALUES (572, 75, '274', '暖通工', '暖通工', 274, 0, 0);
INSERT INTO `sys_code` VALUES (573, 75, '275', '暖通工程师', '暖通工程师', 275, 0, 0);
INSERT INTO `sys_code` VALUES (574, 75, '276', '暖通技术员', '暖通技术员', 276, 0, 0);
INSERT INTO `sys_code` VALUES (575, 75, '277', '暖通维护工程师', '暖通维护工程师', 277, 0, 0);
INSERT INTO `sys_code` VALUES (576, 75, '278', '暖通主任工程师', '暖通主任工程师', 278, 0, 0);
INSERT INTO `sys_code` VALUES (577, 75, '279', '暖通助理工程师', '暖通助理工程师', 279, 0, 0);
INSERT INTO `sys_code` VALUES (578, 75, '280', '培训主管', '培训主管', 280, 0, 0);
INSERT INTO `sys_code` VALUES (579, 75, '281', '培训专员', '培训专员', 281, 0, 0);
INSERT INTO `sys_code` VALUES (580, 75, '282', '配料工', '配料工', 282, 0, 0);
INSERT INTO `sys_code` VALUES (581, 75, '283', '喷涂车间班长', '喷涂车间班长', 283, 0, 0);
INSERT INTO `sys_code` VALUES (582, 75, '284', '喷涂工', '喷涂工', 284, 0, 0);
INSERT INTO `sys_code` VALUES (583, 75, '285', '前EL', '前EL', 285, 0, 0);
INSERT INTO `sys_code` VALUES (584, 75, '286', '前台', '前台', 286, 0, 0);
INSERT INTO `sys_code` VALUES (585, 75, '287', '切割组组长', '切割组组长', 287, 0, 0);
INSERT INTO `sys_code` VALUES (586, 75, '288', '清洗', '清洗', 288, 0, 0);
INSERT INTO `sys_code` VALUES (587, 75, '289', '清洗工', '清洗工', 289, 0, 0);
INSERT INTO `sys_code` VALUES (588, 75, '290', '人力主管', '人力主管', 290, 0, 0);
INSERT INTO `sys_code` VALUES (589, 75, '291', '人力资源主管', '人力资源主管', 291, 0, 0);
INSERT INTO `sys_code` VALUES (590, 75, '292', '人事招聘专员', '人事招聘专员', 292, 0, 0);
INSERT INTO `sys_code` VALUES (591, 75, '293', '人事专员', '人事专员', 293, 0, 0);
INSERT INTO `sys_code` VALUES (592, 75, '294', '人资专员', '人资专员', 294, 0, 0);
INSERT INTO `sys_code` VALUES (593, 75, '295', '认证工程师', '认证工程师', 295, 0, 0);
INSERT INTO `sys_code` VALUES (594, 75, '296', '入托', '入托', 296, 0, 0);
INSERT INTO `sys_code` VALUES (595, 75, '297', '砂浆班长', '砂浆班长', 297, 0, 0);
INSERT INTO `sys_code` VALUES (596, 75, '298', '商务专员', '商务专员', 298, 0, 0);
INSERT INTO `sys_code` VALUES (597, 75, '299', '上料', '上料', 299, 0, 0);
INSERT INTO `sys_code` VALUES (598, 75, '300', '设备部副经理', '设备部副经理', 300, 0, 0);
INSERT INTO `sys_code` VALUES (599, 75, '301', '设备初级技术员', '设备初级技术员', 301, 0, 0);
INSERT INTO `sys_code` VALUES (600, 75, '302', '设备高级经理', '设备高级经理', 302, 0, 0);
INSERT INTO `sys_code` VALUES (601, 75, '303', '设备工程师', '设备工程师', 303, 0, 0);
INSERT INTO `sys_code` VALUES (602, 75, '304', '设备管理员', '设备管理员', 304, 0, 0);
INSERT INTO `sys_code` VALUES (603, 75, '305', '设备维护主管', '设备维护主管', 305, 0, 0);
INSERT INTO `sys_code` VALUES (604, 75, '306', '设备文员', '设备文员', 306, 0, 0);
INSERT INTO `sys_code` VALUES (605, 75, '307', '设备项目经理', '设备项目经理', 307, 0, 0);
INSERT INTO `sys_code` VALUES (606, 75, '308', '设备主管', '设备主管', 308, 0, 0);
INSERT INTO `sys_code` VALUES (607, 75, '309', '设备主任工程师', '设备主任工程师', 309, 0, 0);
INSERT INTO `sys_code` VALUES (608, 75, '310', '设备助理工程师', '设备助理工程师', 310, 0, 0);
INSERT INTO `sys_code` VALUES (609, 75, '311', '生产班长', '生产班长', 311, 0, 0);
INSERT INTO `sys_code` VALUES (610, 75, '312', '生产储干', '生产储干', 312, 0, 0);
INSERT INTO `sys_code` VALUES (611, 75, '313', '生产副经理', '生产副经理', 313, 0, 0);
INSERT INTO `sys_code` VALUES (612, 75, '314', '生产高级经理', '生产高级经理', 314, 0, 0);
INSERT INTO `sys_code` VALUES (613, 75, '315', '生产计划专员', '生产计划专员', 315, 0, 0);
INSERT INTO `sys_code` VALUES (614, 75, '316', '生产经理', '生产经理', 316, 0, 0);
INSERT INTO `sys_code` VALUES (615, 75, '317', '生产运营副总', '生产运营副总', 317, 0, 0);
INSERT INTO `sys_code` VALUES (616, 75, '318', '实习生', '实习生', 318, 0, 0);
INSERT INTO `sys_code` VALUES (617, 75, '319', '实验检验员', '实验检验员', 319, 0, 0);
INSERT INTO `sys_code` VALUES (618, 75, '320', '实验室工程师', '实验室工程师', 320, 0, 0);
INSERT INTO `sys_code` VALUES (619, 75, '321', '实验室经理助理', '实验室经理助理', 321, 0, 0);
INSERT INTO `sys_code` VALUES (620, 75, '322', '食堂超市用品采购专员', '食堂超市用品采购专员', 322, 0, 0);
INSERT INTO `sys_code` VALUES (621, 75, '323', '试产员', '试产员', 323, 0, 0);
INSERT INTO `sys_code` VALUES (622, 75, '324', '试验数据专员', '试验数据专员', 324, 0, 0);
INSERT INTO `sys_code` VALUES (623, 75, '325', '试验员', '试验员', 325, 0, 0);
INSERT INTO `sys_code` VALUES (624, 75, '326', '熟练操作工', '熟练操作工', 326, 0, 0);
INSERT INTO `sys_code` VALUES (625, 75, '327', '数据', '数据', 327, 0, 0);
INSERT INTO `sys_code` VALUES (626, 75, '328', '数据分析员', '数据分析员', 328, 0, 0);
INSERT INTO `sys_code` VALUES (627, 75, '329', '数据体系专员', '数据体系专员', 329, 0, 0);
INSERT INTO `sys_code` VALUES (628, 75, '330', '数据统计员', '数据统计员', 330, 0, 0);
INSERT INTO `sys_code` VALUES (629, 75, '331', '税务会计', '税务会计', 331, 0, 0);
INSERT INTO `sys_code` VALUES (630, 75, '332', '司机', '司机', 332, 0, 0);
INSERT INTO `sys_code` VALUES (631, 75, '333', '司机班长', '司机班长', 333, 0, 0);
INSERT INTO `sys_code` VALUES (632, 75, '334', '套围板', '套围板', 334, 0, 0);
INSERT INTO `sys_code` VALUES (633, 75, '335', '体系工程师', '体系工程师', 335, 0, 0);
INSERT INTO `sys_code` VALUES (634, 75, '336', '体系专员', '体系专员', 336, 0, 0);
INSERT INTO `sys_code` VALUES (635, 75, '337', '调度专员', '调度专员', 337, 0, 0);
INSERT INTO `sys_code` VALUES (636, 75, '338', '统计', '统计', 338, 0, 0);
INSERT INTO `sys_code` VALUES (637, 75, '339', '统计班长', '统计班长', 339, 0, 0);
INSERT INTO `sys_code` VALUES (638, 75, '340', '统计员', '统计员', 340, 0, 0);
INSERT INTO `sys_code` VALUES (639, 75, '341', '土建工程师', '土建工程师', 341, 0, 0);
INSERT INTO `sys_code` VALUES (640, 75, '342', '土建中级技术员', '土建中级技术员', 342, 0, 0);
INSERT INTO `sys_code` VALUES (641, 75, '343', '土建助理工程师', '土建助理工程师', 343, 0, 0);
INSERT INTO `sys_code` VALUES (642, 75, '344', '脱胶工', '脱胶工', 344, 0, 0);
INSERT INTO `sys_code` VALUES (643, 75, '345', '外汇会计', '外汇会计', 345, 0, 0);
INSERT INTO `sys_code` VALUES (644, 75, '346', '网络工程师', '网络工程师', 346, 0, 0);
INSERT INTO `sys_code` VALUES (645, 75, '347', '维护主管', '维护主管', 347, 0, 0);
INSERT INTO `sys_code` VALUES (646, 75, '348', '维修班长', '维修班长', 348, 0, 0);
INSERT INTO `sys_code` VALUES (647, 75, '349', '维修工', '维修工', 349, 0, 0);
INSERT INTO `sys_code` VALUES (648, 75, '350', '维修工程师', '维修工程师', 350, 0, 0);
INSERT INTO `sys_code` VALUES (649, 75, '351', '维修主管', '维修主管', 351, 0, 0);
INSERT INTO `sys_code` VALUES (650, 75, '352', '维修主任工程师', '维修主任工程师', 352, 0, 0);
INSERT INTO `sys_code` VALUES (651, 75, '353', '维修组组长', '维修组组长', 353, 0, 0);
INSERT INTO `sys_code` VALUES (652, 75, '354', '委外加工业务员', '委外加工业务员', 354, 0, 0);
INSERT INTO `sys_code` VALUES (653, 75, '355', '文件管理员', '文件管理员', 355, 0, 0);
INSERT INTO `sys_code` VALUES (654, 75, '356', '文体专员', '文体专员', 356, 0, 0);
INSERT INTO `sys_code` VALUES (655, 75, '357', '文宣专员', '文宣专员', 357, 0, 0);
INSERT INTO `sys_code` VALUES (656, 75, '358', '文员', '文员', 358, 0, 0);
INSERT INTO `sys_code` VALUES (657, 75, '359', '污水处理工', '污水处理工', 359, 0, 0);
INSERT INTO `sys_code` VALUES (658, 75, '360', '物控主管', '物控主管', 360, 0, 0);
INSERT INTO `sys_code` VALUES (659, 75, '361', '物控专员', '物控专员', 361, 0, 0);
INSERT INTO `sys_code` VALUES (660, 75, '362', '物料采购员', '物料采购员', 362, 0, 0);
INSERT INTO `sys_code` VALUES (661, 75, '363', '物料采购主管', '物料采购主管', 363, 0, 0);
INSERT INTO `sys_code` VALUES (662, 75, '364', '物料验收员', '物料验收员', 364, 0, 0);
INSERT INTO `sys_code` VALUES (663, 75, '365', '物料员', '物料员', 365, 0, 0);
INSERT INTO `sys_code` VALUES (664, 75, '366', '物料中心主管', '物料中心主管', 366, 0, 0);
INSERT INTO `sys_code` VALUES (665, 75, '367', '物流主管', '物流主管', 367, 0, 0);
INSERT INTO `sys_code` VALUES (666, 75, '368', '物流专员', '物流专员', 368, 0, 0);
INSERT INTO `sys_code` VALUES (667, 75, '369', '物资回收库管员', '物资回收库管员', 369, 0, 0);
INSERT INTO `sys_code` VALUES (668, 75, '370', '物资回收专员', '物资回收专员', 370, 0, 0);
INSERT INTO `sys_code` VALUES (669, 75, '371', '洗料工', '洗料工', 371, 0, 0);
INSERT INTO `sys_code` VALUES (670, 75, '372', '洗衣工', '洗衣工', 372, 0, 0);
INSERT INTO `sys_code` VALUES (671, 75, '373', '系统工程师', '系统工程师', 373, 0, 0);
INSERT INTO `sys_code` VALUES (672, 75, '374', '系统管理员', '系统管理员', 374, 0, 0);
INSERT INTO `sys_code` VALUES (673, 75, '375', '线切工', '线切工', 375, 0, 0);
INSERT INTO `sys_code` VALUES (674, 75, '376', '线长', '线长', 376, 0, 0);
INSERT INTO `sys_code` VALUES (675, 75, '377', '项目部经理', '项目部经理', 377, 0, 0);
INSERT INTO `sys_code` VALUES (676, 75, '378', '项目主管', '项目主管', 378, 0, 0);
INSERT INTO `sys_code` VALUES (677, 75, '379', '项目总监', '项目总监', 379, 0, 0);
INSERT INTO `sys_code` VALUES (678, 75, '380', '新材料主管', '新材料主管', 380, 0, 0);
INSERT INTO `sys_code` VALUES (679, 75, '381', '薪酬福利专员', '薪酬福利专员', 381, 0, 0);
INSERT INTO `sys_code` VALUES (680, 75, '382', '薪酬专员', '薪酬专员', 382, 0, 0);
INSERT INTO `sys_code` VALUES (681, 75, '383', '宿管', '宿管', 383, 0, 0);
INSERT INTO `sys_code` VALUES (682, 75, '384', '宿管员', '宿管员', 384, 0, 0);
INSERT INTO `sys_code` VALUES (683, 75, '385', '宣传及企划专员', '宣传及企划专员', 385, 0, 0);
INSERT INTO `sys_code` VALUES (684, 75, '386', '巡检', '巡检', 386, 0, 0);
INSERT INTO `sys_code` VALUES (685, 75, '387', '巡检员', '巡检员', 387, 0, 0);
INSERT INTO `sys_code` VALUES (686, 75, '388', '压滤工', '压滤工', 388, 0, 0);
INSERT INTO `sys_code` VALUES (687, 75, '389', '研发工程师', '研发工程师', 389, 0, 0);
INSERT INTO `sys_code` VALUES (688, 75, '390', '应付/预算会计', '应付/预算会计', 390, 0, 0);
INSERT INTO `sys_code` VALUES (689, 75, '391', '应付会计', '应付会计', 391, 0, 0);
INSERT INTO `sys_code` VALUES (690, 75, '392', '应收/合同会计', '应收/合同会计', 392, 0, 0);
INSERT INTO `sys_code` VALUES (691, 75, '393', '应收会计', '应收会计', 393, 0, 0);
INSERT INTO `sys_code` VALUES (692, 75, '394', '应收及外汇收支会计', '应收及外汇收支会计', 394, 0, 0);
INSERT INTO `sys_code` VALUES (693, 75, '395', '应收应付会计', '应收应付会计', 395, 0, 0);
INSERT INTO `sys_code` VALUES (694, 75, '396', '预清洗', '预清洗', 396, 0, 0);
INSERT INTO `sys_code` VALUES (695, 75, '397', '预算会计', '预算会计', 397, 0, 0);
INSERT INTO `sys_code` VALUES (696, 75, '398', '员工', '员工', 398, 0, 0);
INSERT INTO `sys_code` VALUES (697, 75, '399', '员工关系助理', '员工关系助理', 399, 0, 0);
INSERT INTO `sys_code` VALUES (698, 75, '400', '员工关系专员', '员工关系专员', 400, 0, 0);
INSERT INTO `sys_code` VALUES (699, 75, '401', '原材料库管', '原材料库管', 401, 0, 0);
INSERT INTO `sys_code` VALUES (700, 75, '402', '原料仓班长', '原料仓班长', 402, 0, 0);
INSERT INTO `sys_code` VALUES (701, 75, '403', '原料仓叉车司机', '原料仓叉车司机', 403, 0, 0);
INSERT INTO `sys_code` VALUES (702, 75, '404', '原料仓管理员', '原料仓管理员', 404, 0, 0);
INSERT INTO `sys_code` VALUES (703, 75, '405', '原料仓库主管', '原料仓库主管', 405, 0, 0);
INSERT INTO `sys_code` VALUES (704, 75, '406', '原料仓统计员', '原料仓统计员', 406, 0, 0);
INSERT INTO `sys_code` VALUES (705, 75, '407', '原料库叉车司机', '原料库叉车司机', 407, 0, 0);
INSERT INTO `sys_code` VALUES (706, 75, '408', '越南总经理', '越南总经理', 408, 0, 0);
INSERT INTO `sys_code` VALUES (707, 75, '409', '运行A班工程师', '运行A班工程师', 409, 0, 0);
INSERT INTO `sys_code` VALUES (708, 75, '410', '运行B班工程师', '运行B班工程师', 410, 0, 0);
INSERT INTO `sys_code` VALUES (709, 75, '411', '运行班长', '运行班长', 411, 0, 0);
INSERT INTO `sys_code` VALUES (710, 75, '412', '运行工', '运行工', 412, 0, 0);
INSERT INTO `sys_code` VALUES (711, 75, '413', '运行工程师', '运行工程师', 413, 0, 0);
INSERT INTO `sys_code` VALUES (712, 75, '414', '运行人员', '运行人员', 414, 0, 0);
INSERT INTO `sys_code` VALUES (713, 75, '415', '运行主任', '运行主任', 415, 0, 0);
INSERT INTO `sys_code` VALUES (714, 75, '416', '运输中心主管', '运输中心主管', 416, 0, 0);
INSERT INTO `sys_code` VALUES (715, 75, '417', '运营计划副经理', '运营计划副经理', 417, 0, 0);
INSERT INTO `sys_code` VALUES (716, 75, '418', '运营计划高级经理', '运营计划高级经理', 418, 0, 0);
INSERT INTO `sys_code` VALUES (717, 75, '419', '运营计划经理助理', '运营计划经理助理', 419, 0, 0);
INSERT INTO `sys_code` VALUES (718, 75, '420', '粘料工', '粘料工', 420, 0, 0);
INSERT INTO `sys_code` VALUES (719, 75, '421', '长/短边框组长', '长/短边框组长', 421, 0, 0);
INSERT INTO `sys_code` VALUES (720, 75, '422', '招聘专员', '招聘专员', 422, 0, 0);
INSERT INTO `sys_code` VALUES (721, 75, '423', '正面检', '正面检', 423, 0, 0);
INSERT INTO `sys_code` VALUES (722, 75, '424', '值班主任', '值班主任', 424, 0, 0);
INSERT INTO `sys_code` VALUES (723, 75, '425', '职员', '职员', 425, 0, 0);
INSERT INTO `sys_code` VALUES (724, 75, '426', '质检员', '质检员', 426, 0, 0);
INSERT INTO `sys_code` VALUES (725, 75, '427', '质量副经理', '质量副经理', 427, 0, 0);
INSERT INTO `sys_code` VALUES (726, 75, '428', '质量工程师', '质量工程师', 428, 0, 0);
INSERT INTO `sys_code` VALUES (727, 75, '429', '质量经理助理', '质量经理助理', 429, 0, 0);
INSERT INTO `sys_code` VALUES (728, 75, '430', '质量文员', '质量文员', 430, 0, 0);
INSERT INTO `sys_code` VALUES (729, 75, '431', '质量总监', '质量总监', 431, 0, 0);
INSERT INTO `sys_code` VALUES (730, 75, '432', '中转库', '中转库', 432, 0, 0);
INSERT INTO `sys_code` VALUES (731, 75, '433', '中转库员', '中转库员', 433, 0, 0);
INSERT INTO `sys_code` VALUES (732, 75, '434', '主管', '主管', 434, 0, 0);
INSERT INTO `sys_code` VALUES (733, 75, '435', '主管工程师', '主管工程师', 435, 0, 0);
INSERT INTO `sys_code` VALUES (734, 75, '436', '主任', '主任', 436, 0, 0);
INSERT INTO `sys_code` VALUES (735, 75, '437', '主任工程师', '主任工程师', 437, 0, 0);
INSERT INTO `sys_code` VALUES (736, 75, '438', '主任助理', '主任助理', 438, 0, 0);
INSERT INTO `sys_code` VALUES (737, 75, '439', '主任助理(借调越南）', '主任助理(借调越南）', 439, 0, 0);
INSERT INTO `sys_code` VALUES (738, 75, '440', '主任专员', '主任专员', 440, 0, 0);
INSERT INTO `sys_code` VALUES (739, 75, '441', '主要耗材采购专员', '主要耗材采购专员', 441, 0, 0);
INSERT INTO `sys_code` VALUES (740, 75, '442', '助理', '助理', 442, 0, 0);
INSERT INTO `sys_code` VALUES (741, 75, '443', '助理安全工程师', '助理安全工程师', 443, 0, 0);
INSERT INTO `sys_code` VALUES (742, 75, '444', '助理高级工程师', '助理高级工程师', 444, 0, 0);
INSERT INTO `sys_code` VALUES (743, 75, '445', '助理工程师', '助理工程师', 445, 0, 0);
INSERT INTO `sys_code` VALUES (744, 75, '446', '驻场', '驻场', 446, 0, 0);
INSERT INTO `sys_code` VALUES (745, 75, '447', '铸锭车间班长', '铸锭车间班长', 447, 0, 0);
INSERT INTO `sys_code` VALUES (746, 75, '448', '铸锭车间主任', '铸锭车间主任', 448, 0, 0);
INSERT INTO `sys_code` VALUES (747, 75, '449', '铸锭工', '铸锭工', 449, 0, 0);
INSERT INTO `sys_code` VALUES (748, 75, '450', '专案改善主管', '专案改善主管', 450, 0, 0);
INSERT INTO `sys_code` VALUES (749, 75, '451', '专案工程师', '专案工程师', 451, 0, 0);
INSERT INTO `sys_code` VALUES (750, 75, '452', '专员', '专员', 452, 0, 0);
INSERT INTO `sys_code` VALUES (751, 75, '453', '装框一体机', '装框一体机', 453, 0, 0);
INSERT INTO `sys_code` VALUES (752, 75, '454', '准备车间主任', '准备车间主任', 454, 0, 0);
INSERT INTO `sys_code` VALUES (753, 75, '455', '资产管理及融资会计', '资产管理及融资会计', 455, 0, 0);
INSERT INTO `sys_code` VALUES (754, 75, '456', '资金会计', '资金会计', 456, 0, 0);
INSERT INTO `sys_code` VALUES (755, 75, '457', '资料员', '资料员', 457, 0, 0);
INSERT INTO `sys_code` VALUES (756, 75, '458', '资深车间班长', '资深车间班长', 458, 0, 0);
INSERT INTO `sys_code` VALUES (757, 75, '459', '资深工程师', '资深工程师', 459, 0, 0);
INSERT INTO `sys_code` VALUES (758, 75, '460', '资深技师', '资深技师', 460, 0, 0);
INSERT INTO `sys_code` VALUES (759, 75, '461', '资深技术员', '资深技术员', 461, 0, 0);
INSERT INTO `sys_code` VALUES (760, 75, '462', '资深库管员', '资深库管员', 462, 0, 0);
INSERT INTO `sys_code` VALUES (761, 75, '463', '资深理货员', '资深理货员', 463, 0, 0);
INSERT INTO `sys_code` VALUES (762, 75, '464', '资深统计员', '资深统计员', 464, 0, 0);
INSERT INTO `sys_code` VALUES (763, 75, '465', '资深专员', '资深专员', 465, 0, 0);
INSERT INTO `sys_code` VALUES (764, 75, '466', '综合部副经理', '综合部副经理', 466, 0, 0);
INSERT INTO `sys_code` VALUES (765, 75, '467', '综合部经理', '综合部经理', 467, 0, 0);
INSERT INTO `sys_code` VALUES (766, 75, '468', '综合部项目经理', '综合部项目经理', 468, 0, 0);
INSERT INTO `sys_code` VALUES (767, 75, '469', '综合部总监', '综合部总监', 469, 0, 0);
INSERT INTO `sys_code` VALUES (768, 75, '470', '综合主任', '综合主任', 470, 0, 0);
INSERT INTO `sys_code` VALUES (769, 75, '471', '综合专员', '综合专员', 471, 0, 0);
INSERT INTO `sys_code` VALUES (770, 75, '472', '总监', '总监', 472, 0, 0);
INSERT INTO `sys_code` VALUES (771, 75, '473', '总经理', '总经理', 473, 0, 0);
INSERT INTO `sys_code` VALUES (772, 75, '474', '总经理助理', '总经理助理', 474, 0, 0);
INSERT INTO `sys_code` VALUES (773, 75, '475', '总账会计', '总账会计', 475, 0, 0);
INSERT INTO `sys_code` VALUES (774, 75, '476', '组件实验室检验员', '组件实验室检验员', 476, 0, 0);
INSERT INTO `sys_code` VALUES (775, 75, '477', '组长', '组长', 477, 0, 0);
INSERT INTO `sys_code` VALUES (776, 81, '1', '系统内', '系统内', 1, 0, 0);
INSERT INTO `sys_code` VALUES (777, 81, '2', '系统外', '系统外', 2, 0, 0);
INSERT INTO `sys_code` VALUES (778, 82, '1', '离休', '离休', 1, 0, 0);
INSERT INTO `sys_code` VALUES (779, 82, '2', '退休', '退休', 2, 0, 0);
INSERT INTO `sys_code` VALUES (780, 82, '3', '退职', '退职', 3, 0, 0);
INSERT INTO `sys_code` VALUES (781, 83, '1', '电池', NULL, 1, 0, 0);
INSERT INTO `sys_code` VALUES (782, 83, '2', '组件', NULL, 2, 0, 0);
INSERT INTO `sys_code` VALUES (783, 64, '004', '阿富汗', '阿富汗', 1, 0, 0);
INSERT INTO `sys_code` VALUES (784, 64, '008', '阿尔巴尼亚', '阿尔巴尼亚', 2, 0, 0);
INSERT INTO `sys_code` VALUES (785, 64, '012', '阿尔及利亚', '阿尔及利亚', 3, 0, 0);
INSERT INTO `sys_code` VALUES (786, 64, '014', '安道尔', '安道尔', 4, 0, 0);
INSERT INTO `sys_code` VALUES (787, 64, '024', '安哥拉（Angola）', '安哥拉（Angola）', 5, 0, 0);
INSERT INTO `sys_code` VALUES (788, 64, '028', '安提瓜和巴布达', '安提瓜和巴布达', 6, 0, 0);
INSERT INTO `sys_code` VALUES (789, 64, '031', '阿塞拜疆', '阿塞拜疆', 7, 0, 0);
INSERT INTO `sys_code` VALUES (790, 64, '032', '阿根廷', '阿根廷', 8, 0, 0);
INSERT INTO `sys_code` VALUES (791, 64, '036', '澳大利亚', '澳大利亚', 9, 0, 0);
INSERT INTO `sys_code` VALUES (792, 64, '040', '奥地利', '奥地利', 10, 0, 0);
INSERT INTO `sys_code` VALUES (793, 64, '231', '埃塞俄比亚', '埃塞俄比亚', 11, 0, 0);
INSERT INTO `sys_code` VALUES (794, 64, '233', '爱沙尼亚', '爱沙尼亚', 12, 0, 0);
INSERT INTO `sys_code` VALUES (795, 64, '372', '爱尔兰', '爱尔兰', 13, 0, 0);
INSERT INTO `sys_code` VALUES (796, 64, '446', '澳门', '澳门', 14, 0, 0);
INSERT INTO `sys_code` VALUES (797, 64, '512', '阿曼', '阿曼', 15, 0, 0);
INSERT INTO `sys_code` VALUES (798, 64, '533', '阿鲁巴', '阿鲁巴', 16, 0, 0);
INSERT INTO `sys_code` VALUES (799, 64, '660', '安圭拉(Anguilla)', '安圭拉(Anguilla)', 17, 0, 0);
INSERT INTO `sys_code` VALUES (800, 64, '784', '阿联酋', '阿联酋', 18, 0, 0);
INSERT INTO `sys_code` VALUES (801, 64, '818', '埃及', '埃及', 19, 0, 0);
INSERT INTO `sys_code` VALUES (802, 64, '044', '巴哈马', '巴哈马', 20, 0, 0);
INSERT INTO `sys_code` VALUES (803, 64, '048', '巴林', '巴林', 21, 0, 0);
INSERT INTO `sys_code` VALUES (804, 64, '052', '巴巴多斯', '巴巴多斯', 22, 0, 0);
INSERT INTO `sys_code` VALUES (805, 64, '056', '比利时', '比利时', 23, 0, 0);
INSERT INTO `sys_code` VALUES (806, 64, '060', '百慕大', '百慕大', 24, 0, 0);
INSERT INTO `sys_code` VALUES (807, 64, '064', '不丹', '不丹', 25, 0, 0);
INSERT INTO `sys_code` VALUES (808, 64, '068', '玻利维亚', '玻利维亚', 26, 0, 0);
INSERT INTO `sys_code` VALUES (809, 64, '070', '波黑', '波黑', 27, 0, 0);
INSERT INTO `sys_code` VALUES (810, 64, '072', '博茨瓦纳', '博茨瓦纳', 28, 0, 0);
INSERT INTO `sys_code` VALUES (811, 64, '074', '布维岛', '布维岛', 29, 0, 0);
INSERT INTO `sys_code` VALUES (812, 64, '076', '巴西', '巴西', 30, 0, 0);
INSERT INTO `sys_code` VALUES (813, 64, '084', '伯利兹', '伯利兹', 31, 0, 0);
INSERT INTO `sys_code` VALUES (814, 64, '100', '保加利亚', '保加利亚', 32, 0, 0);
INSERT INTO `sys_code` VALUES (815, 64, '108', '布隆迪', '布隆迪', 33, 0, 0);
INSERT INTO `sys_code` VALUES (816, 64, '112', '白俄罗斯', '白俄罗斯', 34, 0, 0);
INSERT INTO `sys_code` VALUES (817, 64, '204', '贝宁', '贝宁', 35, 0, 0);
INSERT INTO `sys_code` VALUES (818, 64, '275', '巴勒斯坦', '巴勒斯坦', 36, 0, 0);
INSERT INTO `sys_code` VALUES (819, 64, '352', '冰岛', '冰岛', 37, 0, 0);
INSERT INTO `sys_code` VALUES (820, 64, '580', '北马里亚纳', '北马里亚纳', 38, 0, 0);
INSERT INTO `sys_code` VALUES (821, 64, '586', '巴基斯坦', '巴基斯坦', 39, 0, 0);
INSERT INTO `sys_code` VALUES (822, 64, '591', '巴拿马', '巴拿马', 40, 0, 0);
INSERT INTO `sys_code` VALUES (823, 64, '598', '巴布亚新几内亚', '巴布亚新几内亚', 41, 0, 0);
INSERT INTO `sys_code` VALUES (824, 64, '600', '巴拉圭', '巴拉圭', 42, 0, 0);
INSERT INTO `sys_code` VALUES (825, 64, '616', '波兰', '波兰', 43, 0, 0);
INSERT INTO `sys_code` VALUES (826, 64, '630', '波多黎各', '波多黎各', 44, 0, 0);
INSERT INTO `sys_code` VALUES (827, 64, '854', '布基纳法索', '布基纳法索', 45, 0, 0);
INSERT INTO `sys_code` VALUES (828, 64, '226', '赤道几内亚', '赤道几内亚', 46, 0, 0);
INSERT INTO `sys_code` VALUES (829, 64, '408', '朝鲜', '朝鲜', 47, 0, 0);
INSERT INTO `sys_code` VALUES (830, 64, '208', '丹麦', '丹麦', 48, 0, 0);
INSERT INTO `sys_code` VALUES (831, 64, '212', '多米尼克', '多米尼克', 49, 0, 0);
INSERT INTO `sys_code` VALUES (832, 64, '214', '多米尼加', '多米尼加', 50, 0, 0);
INSERT INTO `sys_code` VALUES (833, 64, '276', '德国', '德国', 51, 0, 0);
INSERT INTO `sys_code` VALUES (834, 64, '626', '东帝汶', '东帝汶', 52, 0, 0);
INSERT INTO `sys_code` VALUES (835, 64, '768', '多哥', '多哥', 53, 0, 0);
INSERT INTO `sys_code` VALUES (836, 64, '218', '厄瓜多尔', '厄瓜多尔', 54, 0, 0);
INSERT INTO `sys_code` VALUES (837, 64, '232', '厄立特里亚', '厄立特里亚', 55, 0, 0);
INSERT INTO `sys_code` VALUES (838, 64, '132', '佛得角', '佛得角', 56, 0, 0);
INSERT INTO `sys_code` VALUES (839, 64, '234', '法罗群岛', '法罗群岛', 57, 0, 0);
INSERT INTO `sys_code` VALUES (840, 64, '238', '福克兰群岛(马尔维纳斯)', '福克兰群岛(马尔维纳斯)', 58, 0, 0);
INSERT INTO `sys_code` VALUES (841, 64, '242', '斐济', '斐济', 59, 0, 0);
INSERT INTO `sys_code` VALUES (842, 64, '246', '芬兰', '芬兰', 60, 0, 0);
INSERT INTO `sys_code` VALUES (843, 64, '250', '法国', '法国', 61, 0, 0);
INSERT INTO `sys_code` VALUES (844, 64, '254', '法属圭亚那', '法属圭亚那', 62, 0, 0);
INSERT INTO `sys_code` VALUES (845, 64, '258', '法属波利尼西亚', '法属波利尼西亚', 63, 0, 0);
INSERT INTO `sys_code` VALUES (846, 64, '260', '法属南部领地', '法属南部领地', 64, 0, 0);
INSERT INTO `sys_code` VALUES (847, 64, '336', '梵蒂冈', '梵蒂冈', 65, 0, 0);
INSERT INTO `sys_code` VALUES (848, 64, '608', '菲律宾', '菲律宾', 66, 0, 0);
INSERT INTO `sys_code` VALUES (849, 64, '170', '哥伦比亚', '哥伦比亚', 67, 0, 0);
INSERT INTO `sys_code` VALUES (850, 64, '178', '刚果（布）', '刚果（布）', 68, 0, 0);
INSERT INTO `sys_code` VALUES (851, 64, '180', '刚果（金）', '刚果（金）', 69, 0, 0);
INSERT INTO `sys_code` VALUES (852, 64, '188', '哥斯达黎加', '哥斯达黎加', 70, 0, 0);
INSERT INTO `sys_code` VALUES (853, 64, '192', '古巴', '古巴', 71, 0, 0);
INSERT INTO `sys_code` VALUES (854, 64, '268', '格鲁吉亚', '格鲁吉亚', 72, 0, 0);
INSERT INTO `sys_code` VALUES (855, 64, '270', '冈比亚', '冈比亚', 73, 0, 0);
INSERT INTO `sys_code` VALUES (856, 64, '304', '格陵兰', '格陵兰', 74, 0, 0);
INSERT INTO `sys_code` VALUES (857, 64, '308', '格林纳达', '格林纳达', 75, 0, 0);
INSERT INTO `sys_code` VALUES (858, 64, '312', '瓜德罗普', '瓜德罗普', 76, 0, 0);
INSERT INTO `sys_code` VALUES (859, 64, '316', '关岛', '关岛', 77, 0, 0);
INSERT INTO `sys_code` VALUES (860, 64, '328', '圭亚那', '圭亚那', 78, 0, 0);
INSERT INTO `sys_code` VALUES (861, 64, '332', '海地', '海地', 79, 0, 0);
INSERT INTO `sys_code` VALUES (862, 64, '334', '赫德岛和麦克唐纳岛', '赫德岛和麦克唐纳岛', 80, 0, 0);
INSERT INTO `sys_code` VALUES (863, 64, '340', '洪都拉斯', '洪都拉斯', 81, 0, 0);
INSERT INTO `sys_code` VALUES (864, 64, '398', '哈萨克斯坦', '哈萨克斯坦', 82, 0, 0);
INSERT INTO `sys_code` VALUES (865, 64, '410', '韩国', '韩国', 83, 0, 0);
INSERT INTO `sys_code` VALUES (866, 64, '528', '荷兰', '荷兰', 84, 0, 0);
INSERT INTO `sys_code` VALUES (867, 64, '530', '荷属安的列斯', '荷属安的列斯', 85, 0, 0);
INSERT INTO `sys_code` VALUES (868, 64, '116', '柬埔寨', '柬埔寨', 86, 0, 0);
INSERT INTO `sys_code` VALUES (869, 64, '124', '加拿大', '加拿大', 87, 0, 0);
INSERT INTO `sys_code` VALUES (870, 64, '203', '捷克', '捷克', 88, 0, 0);
INSERT INTO `sys_code` VALUES (871, 64, '262', '吉布提', '吉布提', 89, 0, 0);
INSERT INTO `sys_code` VALUES (872, 64, '266', '加蓬', '加蓬', 90, 0, 0);
INSERT INTO `sys_code` VALUES (873, 64, '288', '加纳', '加纳', 91, 0, 0);
INSERT INTO `sys_code` VALUES (874, 64, '296', '基里巴斯', '基里巴斯', 92, 0, 0);
INSERT INTO `sys_code` VALUES (875, 64, '324', '几内亚', '几内亚', 93, 0, 0);
INSERT INTO `sys_code` VALUES (876, 64, '417', '吉尔吉斯斯坦', '吉尔吉斯斯坦', 94, 0, 0);
INSERT INTO `sys_code` VALUES (877, 64, '624', '几内亚比绍', '几内亚比绍', 95, 0, 0);
INSERT INTO `sys_code` VALUES (878, 64, '716', '津巴布韦', '津巴布韦', 96, 0, 0);
INSERT INTO `sys_code` VALUES (879, 64, '120', '喀麦隆', '喀麦隆', 97, 0, 0);
INSERT INTO `sys_code` VALUES (880, 64, '136', '开曼群岛', '开曼群岛', 98, 0, 0);
INSERT INTO `sys_code` VALUES (881, 64, '166', '科科斯(基林)群岛', '科科斯(基林)群岛', 99, 0, 0);
INSERT INTO `sys_code` VALUES (882, 64, '174', '科摩罗', '科摩罗', 100, 0, 0);
INSERT INTO `sys_code` VALUES (883, 64, '184', '库克群岛', '库克群岛', 101, 0, 0);
INSERT INTO `sys_code` VALUES (884, 64, '191', '克罗地亚', '克罗地亚', 102, 0, 0);
INSERT INTO `sys_code` VALUES (885, 64, '384', '科特迪瓦', '科特迪瓦', 103, 0, 0);
INSERT INTO `sys_code` VALUES (886, 64, '404', '肯尼亚', '肯尼亚', 104, 0, 0);
INSERT INTO `sys_code` VALUES (887, 64, '414', '科威持', '科威持', 105, 0, 0);
INSERT INTO `sys_code` VALUES (888, 64, '634', '卡塔尔', '卡塔尔', 106, 0, 0);
INSERT INTO `sys_code` VALUES (889, 64, '418', '老挝', '老挝', 107, 0, 0);
INSERT INTO `sys_code` VALUES (890, 64, '422', '黎巴嫩', '黎巴嫩', 108, 0, 0);
INSERT INTO `sys_code` VALUES (891, 64, '426', '莱索托', '莱索托', 109, 0, 0);
INSERT INTO `sys_code` VALUES (892, 64, '428', '拉脱维亚', '拉脱维亚', 110, 0, 0);
INSERT INTO `sys_code` VALUES (893, 64, '430', '利比里亚', '利比里亚', 111, 0, 0);
INSERT INTO `sys_code` VALUES (894, 64, '434', '利比亚', '利比亚', 112, 0, 0);
INSERT INTO `sys_code` VALUES (895, 64, '438', '列支敦土登', '列支敦土登', 113, 0, 0);
INSERT INTO `sys_code` VALUES (896, 64, '440', '立陶宛', '立陶宛', 114, 0, 0);
INSERT INTO `sys_code` VALUES (897, 64, '442', '卢森堡', '卢森堡', 115, 0, 0);
INSERT INTO `sys_code` VALUES (898, 64, '638', '留尼汪', '留尼汪', 116, 0, 0);
INSERT INTO `sys_code` VALUES (899, 64, '642', '罗马尼亚', '罗马尼亚', 117, 0, 0);
INSERT INTO `sys_code` VALUES (900, 64, '646', '卢旺达', '卢旺达', 118, 0, 0);
INSERT INTO `sys_code` VALUES (901, 64, '016', '美属萨摩亚', '美属萨摩亚', 119, 0, 0);
INSERT INTO `sys_code` VALUES (902, 64, '050', '孟加拉国', '孟加拉国', 120, 0, 0);
INSERT INTO `sys_code` VALUES (903, 64, '104', '缅甸', '缅甸', 121, 0, 0);
INSERT INTO `sys_code` VALUES (904, 64, '175', '马约特', '马约特', 122, 0, 0);
INSERT INTO `sys_code` VALUES (905, 64, '450', '马达加斯加', '马达加斯加', 123, 0, 0);
INSERT INTO `sys_code` VALUES (906, 64, '454', '马拉维', '马拉维', 124, 0, 0);
INSERT INTO `sys_code` VALUES (907, 64, '458', '马来西亚', '马来西亚', 125, 0, 0);
INSERT INTO `sys_code` VALUES (908, 64, '462', '马尔代夫', '马尔代夫', 126, 0, 0);
INSERT INTO `sys_code` VALUES (909, 64, '466', '马里', '马里', 127, 0, 0);
INSERT INTO `sys_code` VALUES (910, 64, '470', '马耳他', '马耳他', 128, 0, 0);
INSERT INTO `sys_code` VALUES (911, 64, '474', '马提尼克', '马提尼克', 129, 0, 0);
INSERT INTO `sys_code` VALUES (912, 64, '478', '毛里塔尼亚', '毛里塔尼亚', 130, 0, 0);
INSERT INTO `sys_code` VALUES (913, 64, '480', '毛里求斯', '毛里求斯', 131, 0, 0);
INSERT INTO `sys_code` VALUES (914, 64, '484', '墨西哥', '墨西哥', 132, 0, 0);
INSERT INTO `sys_code` VALUES (915, 64, '492', '摩纳哥', '摩纳哥', 133, 0, 0);
INSERT INTO `sys_code` VALUES (916, 64, '496', '蒙古', '蒙古', 134, 0, 0);
INSERT INTO `sys_code` VALUES (917, 64, '498', '摩尔多瓦', '摩尔多瓦', 135, 0, 0);
INSERT INTO `sys_code` VALUES (918, 64, '500', '蒙特塞拉特', '蒙特塞拉特', 136, 0, 0);
INSERT INTO `sys_code` VALUES (919, 64, '504', '摩洛哥', '摩洛哥', 137, 0, 0);
INSERT INTO `sys_code` VALUES (920, 64, '508', '莫桑比克', '莫桑比克', 138, 0, 0);
INSERT INTO `sys_code` VALUES (921, 64, '581', '美国本土外小岛屿', '美国本土外小岛屿', 139, 0, 0);
INSERT INTO `sys_code` VALUES (922, 64, '583', '密克罗尼西亚联邦', '密克罗尼西亚联邦', 140, 0, 0);
INSERT INTO `sys_code` VALUES (923, 64, '584', '马绍尔群岛', '马绍尔群岛', 141, 0, 0);
INSERT INTO `sys_code` VALUES (924, 64, '604', '秘鲁', '秘鲁', 142, 0, 0);
INSERT INTO `sys_code` VALUES (925, 64, '840', '美国', '美国', 143, 0, 0);
INSERT INTO `sys_code` VALUES (926, 64, '850', '美属维尔京群岛', '美属维尔京群岛', 144, 0, 0);
INSERT INTO `sys_code` VALUES (927, 64, '010', '南极洲', '南极洲', 145, 0, 0);
INSERT INTO `sys_code` VALUES (928, 64, '239', '南乔治亚岛和南桑德韦奇岛', '南乔治亚岛和南桑德韦奇岛', 146, 0, 0);
INSERT INTO `sys_code` VALUES (929, 64, '516', '纳米比亚', '纳米比亚', 147, 0, 0);
INSERT INTO `sys_code` VALUES (930, 64, '520', '瑙鲁', '瑙鲁', 148, 0, 0);
INSERT INTO `sys_code` VALUES (931, 64, '524', '尼泊尔', '尼泊尔', 149, 0, 0);
INSERT INTO `sys_code` VALUES (932, 64, '558', '尼加拉瓜', '尼加拉瓜', 150, 0, 0);
INSERT INTO `sys_code` VALUES (933, 64, '562', '尼日尔', '尼日尔', 151, 0, 0);
INSERT INTO `sys_code` VALUES (934, 64, '566', '尼日利亚', '尼日利亚', 152, 0, 0);
INSERT INTO `sys_code` VALUES (935, 64, '570', '纽埃', '纽埃', 153, 0, 0);
INSERT INTO `sys_code` VALUES (936, 64, '574', '诺福克岛', '诺福克岛', 154, 0, 0);
INSERT INTO `sys_code` VALUES (937, 64, '578', '挪威', '挪威', 155, 0, 0);
INSERT INTO `sys_code` VALUES (938, 64, '710', '南非', '南非', 156, 0, 0);
INSERT INTO `sys_code` VALUES (939, 64, '891', '南斯拉夫', '南斯拉夫', 157, 0, 0);
INSERT INTO `sys_code` VALUES (940, 64, '585', '帕劳', '帕劳', 158, 0, 0);
INSERT INTO `sys_code` VALUES (941, 64, '612', '皮特凯恩', '皮特凯恩', 159, 0, 0);
INSERT INTO `sys_code` VALUES (942, 64, '620', '葡萄牙', '葡萄牙', 160, 0, 0);
INSERT INTO `sys_code` VALUES (943, 64, '807', '前南马其顿', '前南马其顿', 161, 0, 0);
INSERT INTO `sys_code` VALUES (944, 64, '392', '日本', '日本', 162, 0, 0);
INSERT INTO `sys_code` VALUES (945, 64, '752', '瑞典', '瑞典', 163, 0, 0);
INSERT INTO `sys_code` VALUES (946, 64, '756', '瑞士', '瑞士', 164, 0, 0);
INSERT INTO `sys_code` VALUES (947, 64, '090', '所罗门群岛', '所罗门群岛', 165, 0, 0);
INSERT INTO `sys_code` VALUES (948, 64, '144', '斯里兰卡', '斯里兰卡', 166, 0, 0);
INSERT INTO `sys_code` VALUES (949, 64, '162', '圣诞岛', '圣诞岛', 167, 0, 0);
INSERT INTO `sys_code` VALUES (950, 64, '196', '塞浦路斯', '塞浦路斯', 168, 0, 0);
INSERT INTO `sys_code` VALUES (951, 64, '222', '萨尔瓦多', '萨尔瓦多', 169, 0, 0);
INSERT INTO `sys_code` VALUES (952, 64, '654', '圣赫勒拿', '圣赫勒拿', 170, 0, 0);
INSERT INTO `sys_code` VALUES (953, 64, '659', '圣基茨和尼维斯', '圣基茨和尼维斯', 171, 0, 0);
INSERT INTO `sys_code` VALUES (954, 64, '662', '圣卢西亚', '圣卢西亚', 172, 0, 0);
INSERT INTO `sys_code` VALUES (955, 64, '666', '圣皮埃尔和密克隆', '圣皮埃尔和密克隆', 173, 0, 0);
INSERT INTO `sys_code` VALUES (956, 64, '670', '圣文森特和格林纳丁斯', '圣文森特和格林纳丁斯', 174, 0, 0);
INSERT INTO `sys_code` VALUES (957, 64, '674', '圣马力诺', '圣马力诺', 175, 0, 0);
INSERT INTO `sys_code` VALUES (958, 64, '678', '圣多美和普林西比', '圣多美和普林西比', 176, 0, 0);
INSERT INTO `sys_code` VALUES (959, 64, '682', '沙特阿拉伯', '沙特阿拉伯', 177, 0, 0);
INSERT INTO `sys_code` VALUES (960, 64, '686', '塞内加尔', '塞内加尔', 178, 0, 0);
INSERT INTO `sys_code` VALUES (961, 64, '690', '塞舌尔', '塞舌尔', 179, 0, 0);
INSERT INTO `sys_code` VALUES (962, 64, '694', '塞拉利昂', '塞拉利昂', 180, 0, 0);
INSERT INTO `sys_code` VALUES (963, 64, '703', '斯洛伐克', '斯洛伐克', 181, 0, 0);
INSERT INTO `sys_code` VALUES (964, 64, '705', '斯洛文尼亚', '斯洛文尼亚', 182, 0, 0);
INSERT INTO `sys_code` VALUES (965, 64, '706', '索马里', '索马里', 183, 0, 0);
INSERT INTO `sys_code` VALUES (966, 64, '736', '苏丹', '苏丹', 184, 0, 0);
INSERT INTO `sys_code` VALUES (967, 64, '740', '苏里南', '苏里南', 185, 0, 0);
INSERT INTO `sys_code` VALUES (968, 64, '744', '斯瓦尔巴岛和扬马延岛', '斯瓦尔巴岛和扬马延岛', 186, 0, 0);
INSERT INTO `sys_code` VALUES (969, 64, '748', '斯威士兰', '斯威士兰', 187, 0, 0);
INSERT INTO `sys_code` VALUES (970, 64, '882', '萨摩亚', '萨摩亚', 188, 0, 0);
INSERT INTO `sys_code` VALUES (971, 64, '792', '土耳其', '土耳其', 189, 0, 0);
INSERT INTO `sys_code` VALUES (972, 64, '158', '台湾', '台湾', 190, 0, 0);
INSERT INTO `sys_code` VALUES (973, 64, '762', '塔吉克斯坦', '塔吉克斯坦', 191, 0, 0);
INSERT INTO `sys_code` VALUES (974, 64, '764', '泰国', '泰国', 192, 0, 0);
INSERT INTO `sys_code` VALUES (975, 64, '772', '托克劳', '托克劳', 193, 0, 0);
INSERT INTO `sys_code` VALUES (976, 64, '776', '汤加', '汤加', 194, 0, 0);
INSERT INTO `sys_code` VALUES (977, 64, '780', '特立尼达和多巴哥', '特立尼达和多巴哥', 195, 0, 0);
INSERT INTO `sys_code` VALUES (978, 64, '788', '突尼斯', '突尼斯', 196, 0, 0);
INSERT INTO `sys_code` VALUES (979, 64, '795', '土库曼斯坦', '土库曼斯坦', 197, 0, 0);
INSERT INTO `sys_code` VALUES (980, 64, '796', '特克斯和凯科斯群岛', '特克斯和凯科斯群岛', 198, 0, 0);
INSERT INTO `sys_code` VALUES (981, 64, '798', '图瓦卢', '图瓦卢', 199, 0, 0);
INSERT INTO `sys_code` VALUES (982, 64, '834', '坦桑尼亚', '坦桑尼亚', 200, 0, 0);
INSERT INTO `sys_code` VALUES (983, 64, '096', '文莱', '文莱', 201, 0, 0);
INSERT INTO `sys_code` VALUES (984, 64, '320', '危地马拉', '危地马拉', 202, 0, 0);
INSERT INTO `sys_code` VALUES (985, 64, '548', '瓦努阿图', '瓦努阿图', 203, 0, 0);
INSERT INTO `sys_code` VALUES (986, 64, '800', '乌干达', '乌干达', 204, 0, 0);
INSERT INTO `sys_code` VALUES (987, 64, '804', '乌克兰', '乌克兰', 205, 0, 0);
INSERT INTO `sys_code` VALUES (988, 64, '858', '乌拉圭', '乌拉圭', 206, 0, 0);
INSERT INTO `sys_code` VALUES (989, 64, '860', '乌兹别克斯坦', '乌兹别克斯坦', 207, 0, 0);
INSERT INTO `sys_code` VALUES (990, 64, '862', '委内瑞拉', '委内瑞拉', 208, 0, 0);
INSERT INTO `sys_code` VALUES (991, 64, '876', '瓦利斯和富图纳', '瓦利斯和富图纳', 209, 0, 0);
INSERT INTO `sys_code` VALUES (992, 64, '300', '希腊', '希腊', 210, 0, 0);
INSERT INTO `sys_code` VALUES (993, 64, '344', '香港', '香港', 211, 0, 0);
INSERT INTO `sys_code` VALUES (994, 64, '348', '匈牙利', '匈牙利', 212, 0, 0);
INSERT INTO `sys_code` VALUES (995, 64, '540', '新喀里多尼亚', '新喀里多尼亚', 213, 0, 0);
INSERT INTO `sys_code` VALUES (996, 64, '554', '新西兰', '新西兰', 214, 0, 0);
INSERT INTO `sys_code` VALUES (997, 64, '702', '新加坡', '新加坡', 215, 0, 0);
INSERT INTO `sys_code` VALUES (998, 64, '724', '西班牙', '西班牙', 216, 0, 0);
INSERT INTO `sys_code` VALUES (999, 64, '732', '西撤哈拉', '西撤哈拉', 217, 0, 0);
INSERT INTO `sys_code` VALUES (1000, 64, '760', '叙利亚', '叙利亚', 218, 0, 0);
INSERT INTO `sys_code` VALUES (1001, 64, '051', '亚美尼亚', '亚美尼亚', 219, 0, 0);
INSERT INTO `sys_code` VALUES (1002, 64, '086', '英属印度洋领土', '英属印度洋领土', 220, 0, 0);
INSERT INTO `sys_code` VALUES (1003, 64, '092', '英属维尔京群岛', '英属维尔京群岛', 221, 0, 0);
INSERT INTO `sys_code` VALUES (1004, 64, '356', '印度', '印度', 222, 0, 0);
INSERT INTO `sys_code` VALUES (1005, 64, '360', '印度尼西亚', '印度尼西亚', 223, 0, 0);
INSERT INTO `sys_code` VALUES (1006, 64, '364', '伊朗', '伊朗', 224, 0, 0);
INSERT INTO `sys_code` VALUES (1007, 64, '368', '伊拉克', '伊拉克', 225, 0, 0);
INSERT INTO `sys_code` VALUES (1008, 64, '376', '以色列', '以色列', 226, 0, 0);
INSERT INTO `sys_code` VALUES (1009, 64, '380', '意大利', '意大利', 227, 0, 0);
INSERT INTO `sys_code` VALUES (1010, 64, '388', '牙买加', '牙买加', 228, 0, 0);
INSERT INTO `sys_code` VALUES (1011, 64, '400', '约旦', '约旦', 229, 0, 0);
INSERT INTO `sys_code` VALUES (1012, 64, '704', '越南', '越南', 230, 0, 0);
INSERT INTO `sys_code` VALUES (1013, 64, '826', '英国', '英国', 231, 0, 0);
INSERT INTO `sys_code` VALUES (1014, 64, '887', '也门', '也门', 232, 0, 0);
INSERT INTO `sys_code` VALUES (1015, 64, '140', '中非', '中非', 233, 0, 0);
INSERT INTO `sys_code` VALUES (1016, 64, '148', '乍得', '乍得', 234, 0, 0);
INSERT INTO `sys_code` VALUES (1017, 64, '152', '智利', '智利', 235, 0, 0);
INSERT INTO `sys_code` VALUES (1018, 64, '156', '中国大陆', '中国', 236, 0, 0);
INSERT INTO `sys_code` VALUES (1019, 64, '292', '直布罗陀', '直布罗陀', 237, 0, 0);
INSERT INTO `sys_code` VALUES (1020, 64, '894', '赞比亚', '赞比亚', 238, 0, 0);
INSERT INTO `sys_code` VALUES (1021, 65, '1', '汉族', '汉族', 1, 0, 0);
INSERT INTO `sys_code` VALUES (1022, 65, '2', '蒙古族', '蒙古族', 2, 0, 0);
INSERT INTO `sys_code` VALUES (1023, 65, '3', '回族', '回族', 3, 0, 0);
INSERT INTO `sys_code` VALUES (1024, 65, '4', '藏族', '藏族', 4, 0, 0);
INSERT INTO `sys_code` VALUES (1025, 65, '5', '维吾尔族', '维吾尔族', 5, 0, 0);
INSERT INTO `sys_code` VALUES (1026, 65, '6', '苗族', '苗族', 6, 0, 0);
INSERT INTO `sys_code` VALUES (1027, 65, '7', '彝族', '彝族', 7, 0, 0);
INSERT INTO `sys_code` VALUES (1028, 65, '8', '壮族', '壮族', 8, 0, 0);
INSERT INTO `sys_code` VALUES (1029, 65, '9', '布依族', '布依族', 9, 0, 0);
INSERT INTO `sys_code` VALUES (1030, 65, '10', '朝鲜族', '朝鲜族', 10, 0, 0);
INSERT INTO `sys_code` VALUES (1031, 65, '11', '满族', '满族', 11, 0, 0);
INSERT INTO `sys_code` VALUES (1032, 65, '12', '侗族', '侗族', 12, 0, 0);
INSERT INTO `sys_code` VALUES (1033, 65, '13', '瑶族', '瑶族', 13, 0, 0);
INSERT INTO `sys_code` VALUES (1034, 65, '14', '白族', '白族', 14, 0, 0);
INSERT INTO `sys_code` VALUES (1035, 65, '15', '土家族', '土家族', 15, 0, 0);
INSERT INTO `sys_code` VALUES (1036, 65, '16', '哈尼族', '哈尼族', 16, 0, 0);
INSERT INTO `sys_code` VALUES (1037, 65, '17', '哈萨克族', '哈萨克族', 17, 0, 0);
INSERT INTO `sys_code` VALUES (1038, 65, '18', '傣族', '傣族', 18, 0, 0);
INSERT INTO `sys_code` VALUES (1039, 65, '19', '黎族', '黎族', 19, 0, 0);
INSERT INTO `sys_code` VALUES (1040, 65, '20', '傈傈族', '傈傈族', 20, 0, 0);
INSERT INTO `sys_code` VALUES (1041, 65, '21', '佤族', '佤族', 21, 0, 0);
INSERT INTO `sys_code` VALUES (1042, 65, '22', '畲族', '畲族', 22, 0, 0);
INSERT INTO `sys_code` VALUES (1043, 65, '23', '高山族', '高山族', 23, 0, 0);
INSERT INTO `sys_code` VALUES (1044, 65, '24', '拉祜族', '拉祜族', 24, 0, 0);
INSERT INTO `sys_code` VALUES (1045, 65, '25', '水族', '水族', 25, 0, 0);
INSERT INTO `sys_code` VALUES (1046, 65, '26', '东乡族', '东乡族', 26, 0, 0);
INSERT INTO `sys_code` VALUES (1047, 65, '27', '纳西族', '纳西族', 27, 0, 0);
INSERT INTO `sys_code` VALUES (1048, 65, '28', '景颇族', '景颇族', 28, 0, 0);
INSERT INTO `sys_code` VALUES (1049, 65, '29', '柯尔克孜', '柯尔克孜', 29, 0, 0);
INSERT INTO `sys_code` VALUES (1050, 65, '30', '土族', '土族', 30, 0, 0);
INSERT INTO `sys_code` VALUES (1051, 65, '31', '达斡尔族', '达斡尔族', 31, 0, 0);
INSERT INTO `sys_code` VALUES (1052, 65, '32', '仫佬族', '仫佬族', 32, 0, 0);
INSERT INTO `sys_code` VALUES (1053, 65, '33', '羌族', '羌族', 33, 0, 0);
INSERT INTO `sys_code` VALUES (1054, 65, '34', '布朗族', '布朗族', 34, 0, 0);
INSERT INTO `sys_code` VALUES (1055, 65, '35', '撤拉族', '撤拉族', 35, 0, 0);
INSERT INTO `sys_code` VALUES (1056, 65, '36', '毛难族', '毛难族', 36, 0, 0);
INSERT INTO `sys_code` VALUES (1057, 65, '37', '仡佬族', '仡佬族', 37, 0, 0);
INSERT INTO `sys_code` VALUES (1058, 65, '38', '锡伯族', '锡伯族', 38, 0, 0);
INSERT INTO `sys_code` VALUES (1059, 65, '39', '阿昌族', '阿昌族', 39, 0, 0);
INSERT INTO `sys_code` VALUES (1060, 65, '40', '普米族', '普米族', 40, 0, 0);
INSERT INTO `sys_code` VALUES (1061, 65, '41', '塔吉克族', '塔吉克族', 41, 0, 0);
INSERT INTO `sys_code` VALUES (1062, 65, '42', '怒族', '怒族', 42, 0, 0);
INSERT INTO `sys_code` VALUES (1063, 65, '43', '乌孜别克', '乌孜别克', 43, 0, 0);
INSERT INTO `sys_code` VALUES (1064, 65, '44', '俄罗斯族', '俄罗斯族', 44, 0, 0);
INSERT INTO `sys_code` VALUES (1065, 65, '45', '鄂温克族', '鄂温克族', 45, 0, 0);
INSERT INTO `sys_code` VALUES (1066, 65, '46', '崩龙族', '崩龙族', 46, 0, 0);
INSERT INTO `sys_code` VALUES (1067, 65, '47', '保安族', '保安族', 47, 0, 0);
INSERT INTO `sys_code` VALUES (1068, 65, '48', '裕固族', '裕固族', 48, 0, 0);
INSERT INTO `sys_code` VALUES (1069, 65, '49', '京族', '京族', 49, 0, 0);
INSERT INTO `sys_code` VALUES (1070, 65, '50', '塔塔尔族', '塔塔尔族', 50, 0, 0);
INSERT INTO `sys_code` VALUES (1071, 65, '51', '独龙族', '独龙族', 51, 0, 0);
INSERT INTO `sys_code` VALUES (1072, 65, '52', '鄂伦春族', '鄂伦春族', 52, 0, 0);
INSERT INTO `sys_code` VALUES (1073, 65, '53', '赫哲族', '赫哲族', 53, 0, 0);
INSERT INTO `sys_code` VALUES (1074, 65, '54', '门巴族', '门巴族', 54, 0, 0);
INSERT INTO `sys_code` VALUES (1075, 65, '55', '珞巴族', '珞巴族', 55, 0, 0);
INSERT INTO `sys_code` VALUES (1076, 65, '56', '基诺族', '基诺族', 56, 0, 0);
INSERT INTO `sys_code` VALUES (1077, 65, '57', '其他', '其他', 57, 0, 0);
INSERT INTO `sys_code` VALUES (1078, 65, '58', '外国血统中国籍人士', '外国血统中国籍人士', 58, 0, 0);
INSERT INTO `sys_code` VALUES (1081, 61, '6', '已育三胎', NULL, 4, 0, 0);
INSERT INTO `sys_code` VALUES (1087, 84, '1', '网络招聘', '网络招聘', 1, 0, 0);
INSERT INTO `sys_code` VALUES (1088, 84, '2', '社会招聘', '社会招聘', 2, 0, 0);
INSERT INTO `sys_code` VALUES (1089, 84, '3', '校园招聘', '校园招聘', 3, 0, 0);
INSERT INTO `sys_code` VALUES (1090, 84, '4', '内部推荐', '内部推荐', 4, 0, 0);
INSERT INTO `sys_code` VALUES (1091, 84, '5', '猎头', '猎头', 5, 0, 0);
INSERT INTO `sys_code` VALUES (1092, 84, '6', '其他', '其他', 6, 0, 0);
INSERT INTO `sys_code` VALUES (1093, 85, '0', '内销合同', '内销合同', 1, 0, 0);
INSERT INTO `sys_code` VALUES (1094, 85, '1', '外销合同', '外销合同', 2, 0, 0);
INSERT INTO `sys_code` VALUES (1095, 38, '02', '电池销售合同', '电池销售合同', 3, 0, 0);
INSERT INTO `sys_code` VALUES (1096, 38, '03', '其他类合同', '其他类合同', 4, 0, 0);
INSERT INTO `sys_code` VALUES (1097, 86, '00', '晶澳太阳能有限公司', '晶澳太阳能有限公司', 1, 0, 0);
INSERT INTO `sys_code` VALUES (1098, 86, '01', '晶澳（邢台）太阳能有限公司', '晶澳（邢台）太阳能有限公司', 2, 0, 0);
INSERT INTO `sys_code` VALUES (1099, 86, '02', '合肥晶澳太阳能科技有限公司', '合肥晶澳太阳能科技有限公司\n', 3, 0, 0);
INSERT INTO `sys_code` VALUES (1100, 86, '03', '上海晶澳太阳能科技有限公司', '上海晶澳太阳能科技有限公司\n', 4, 0, 0);
INSERT INTO `sys_code` VALUES (1101, 86, '04', ' 晶澳（扬州）太阳能科技有限公司', ' 晶澳（扬州）太阳能科技有限公司\n', 5, 0, 0);
INSERT INTO `sys_code` VALUES (1102, 86, '05', '晶澳（德国）公司   ', '晶澳（德国）公司   \n', 6, 0, 0);
INSERT INTO `sys_code` VALUES (1103, 86, '06', '晶澳（美国）公司 ', '晶澳（美国）公司 \n', 7, 0, 0);
INSERT INTO `sys_code` VALUES (1104, 86, '07', '晶澳太阳能国际有限公司', '晶澳太阳能国际有限公司\n', 8, 0, 0);
INSERT INTO `sys_code` VALUES (1105, 86, '08', '晶澳（日本）公司   ', '晶澳（日本）公司   \n', 9, 0, 0);
INSERT INTO `sys_code` VALUES (1106, 86, '09', '晶澳（澳大利亚）公司  ', '晶澳（澳大利亚）公司  \n', 10, 0, 0);
INSERT INTO `sys_code` VALUES (1107, 86, '10', '晶澳（韩国）公司/晶澳太阳能韩国株式会社', '晶澳（韩国）公司/晶澳太阳能韩国株式会社\n', 11, 0, 0);
INSERT INTO `sys_code` VALUES (1108, 86, '11', '晶澳（巴西）公司 ', '晶澳（巴西）公司 \n', 12, 0, 0);
INSERT INTO `sys_code` VALUES (1109, 86, '12', '晶澳（土耳其）贸易公司', '晶澳（土耳其）贸易公司\n', 13, 0, 0);
INSERT INTO `sys_code` VALUES (1110, 86, '13', '晶澳保威南非合资有限公司JA Power', '晶澳保威南非合资有限公司JA Powerway SA\n', 14, 0, 0);
INSERT INTO `sys_code` VALUES (1111, 86, '14', '其他 ', '其他 \n', 15, 0, 0);
INSERT INTO `sys_code` VALUES (1112, 87, '00', '无补充协议', '无补充协议\n', 1, 0, 0);
INSERT INTO `sys_code` VALUES (1113, 87, '01', '第一个补充协议', '第一个补充协议\n', 2, 0, 0);
INSERT INTO `sys_code` VALUES (1114, 87, '02', '第二个补充协议', '第二个补充协议\n', 3, 0, 0);
INSERT INTO `sys_code` VALUES (1115, 87, '03', '第三个补充协议', '第三个补充协议\n', 4, 0, 0);
INSERT INTO `sys_code` VALUES (1116, 21, '6', '已中止', '已中止', 6, 0, 0);
INSERT INTO `sys_code` VALUES (1117, 28, '6', '已中止', '已中止', 6, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_code_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_type`;
CREATE TABLE `sys_code_type` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '编码类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `desc_` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='编码类型表';

-- ----------------------------
-- Records of sys_code_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_code_type` VALUES (1, 'YES_OR_NO', '是否选项', '是否选项');
INSERT INTO `sys_code_type` VALUES (2, 'MALE_OR_FEMALE', '性别', '性别');
INSERT INTO `sys_code_type` VALUES (3, 'IS_ON_JOB', '在职状态', '在职状态');
INSERT INTO `sys_code_type` VALUES (4, 'CONTRACT_TYPE', '合同类型', '合同类型');
INSERT INTO `sys_code_type` VALUES (5, 'CONTRACT_PERIOD_TYPE', '合同期限类型', '合同期限类型');
INSERT INTO `sys_code_type` VALUES (6, 'IDENTITY_TYPE', '证件类型', '证件类型');
INSERT INTO `sys_code_type` VALUES (7, 'MARITAL_STATUS', '婚姻状况', '婚姻状况');
INSERT INTO `sys_code_type` VALUES (8, 'POLITICAL_STATUS', '政治面貌', '政治面貌');
INSERT INTO `sys_code_type` VALUES (9, 'STAFF_TYPE', '员工类型', '员工类型');
INSERT INTO `sys_code_type` VALUES (10, 'SOCIAL_SECURITY_TYPE', '社保类型', '社保类型');
INSERT INTO `sys_code_type` VALUES (12, 'TYPE_OF_LABOR', '用工类型', '用工类型');
INSERT INTO `sys_code_type` VALUES (13, 'AWARD_RANK', '奖励级别', '奖励级别');
INSERT INTO `sys_code_type` VALUES (14, 'DISABILITY_TYPE', '残疾类别', '残疾类别');
INSERT INTO `sys_code_type` VALUES (15, 'DISABILITY_LEVEL', '伤残等级', '伤残等级');
INSERT INTO `sys_code_type` VALUES (16, 'NURSE_LEVEL', '护理程度', '护理程度');
INSERT INTO `sys_code_type` VALUES (17, 'ACCIDENT_STATE', '事故类别', '事故类别');
INSERT INTO `sys_code_type` VALUES (18, 'OCCUPATIONAL_INJURY_TYPE', '工伤类别', '工伤类别');
INSERT INTO `sys_code_type` VALUES (19, 'LINES', '线别', '线别');
INSERT INTO `sys_code_type` VALUES (20, 'AGREEMENT_TYPE', '协议类型', '协议类型');
INSERT INTO `sys_code_type` VALUES (21, 'CONTRACT_STATE', '合同状态', '合同状态');
INSERT INTO `sys_code_type` VALUES (22, 'CONTRACT_NO', '合同编号', '合同编号');
INSERT INTO `sys_code_type` VALUES (23, 'AGREEMENT_NO', '协议编号', '协议编号');
INSERT INTO `sys_code_type` VALUES (24, 'JA_STAFF_CODE', '晶澳总部员工工号生成前缀', '晶澳总部员工工号生成前缀');
INSERT INTO `sys_code_type` VALUES (25, 'HF_STAFF_CODE', '合肥基地员工工号生成后缀', '合肥基地员工工号生成后缀');
INSERT INTO `sys_code_type` VALUES (26, 'ORG_CODE', '组织编号', '组织编号');
INSERT INTO `sys_code_type` VALUES (27, 'RENEW_STATUS', '续签状态', '续签状态');
INSERT INTO `sys_code_type` VALUES (28, 'AGREEMENT_STATE', '协议状态', '协议状态');
INSERT INTO `sys_code_type` VALUES (29, 'STAFF_CHANGE_TYPE', '员工变动类型', '员工变动类型');
INSERT INTO `sys_code_type` VALUES (30, 'CONTINENT', '洲', '洲');
INSERT INTO `sys_code_type` VALUES (31, 'COUNTRY', '国家', '国家');
INSERT INTO `sys_code_type` VALUES (32, 'CURRENCY', '币别', '币别');
INSERT INTO `sys_code_type` VALUES (33, 'ACCOUNT_UNIT', '账期单位', '账期单位');
INSERT INTO `sys_code_type` VALUES (34, 'PAYMENT_TYPE', '付款方式', '付款方式');
INSERT INTO `sys_code_type` VALUES (36, 'CUSTOMER_STATUS', '客户状态', '客户状态');
INSERT INTO `sys_code_type` VALUES (37, 'CUSTOMER_LEVEL', '客户等级', '客户等级');
INSERT INTO `sys_code_type` VALUES (38, 'SALE_CONTRACT_TYPE', '销售合同类型', '销售合同类型');
INSERT INTO `sys_code_type` VALUES (40, 'SALE_CONTRACT_TITLE', '销售合同标题', '销售合同标题');
INSERT INTO `sys_code_type` VALUES (42, 'INVOICE_TYPE', '销售发票类型', '销售发票类型');
INSERT INTO `sys_code_type` VALUES (43, 'TRADE_MODE', '贸易方式', '贸易方式');
INSERT INTO `sys_code_type` VALUES (44, 'BATTERY_TYPE', '电池类型', '电池类型');
INSERT INTO `sys_code_type` VALUES (45, 'BORDER_COLOR', '边框颜色', '边框颜色');
INSERT INTO `sys_code_type` VALUES (46, 'BORDER_SPECIFICATION', '边框规格', '边框规格');
INSERT INTO `sys_code_type` VALUES (47, 'BACKBOARD_COLOR', '背板颜色', '背板颜色');
INSERT INTO `sys_code_type` VALUES (48, 'BACKBOARD_MATERIAL', '背板材质', '背板材质');
INSERT INTO `sys_code_type` VALUES (49, 'JUNCTION_BOX', '接线盒', '接线盒');
INSERT INTO `sys_code_type` VALUES (50, 'EVA', 'EVA', 'EVA');
INSERT INTO `sys_code_type` VALUES (51, 'PAY_TYPE', '回款类型', '回款类型');
INSERT INTO `sys_code_type` VALUES (52, 'SILICON_TYPE', '硅片类型', '硅片类型');
INSERT INTO `sys_code_type` VALUES (53, 'CELL_NUMBER', '电池片数', '电池片数');
INSERT INTO `sys_code_type` VALUES (54, 'MUDULE_TYPE', '组件类型', '组件类型');
INSERT INTO `sys_code_type` VALUES (55, 'MUDULE_CODE', '组件关键信息码', '组件关键信息码');
INSERT INTO `sys_code_type` VALUES (56, 'CELL_TECHNOLOGY', '电池片技术', '电池片技术');
INSERT INTO `sys_code_type` VALUES (57, 'SALES_PHASE', '销售阶段', '销售阶段');
INSERT INTO `sys_code_type` VALUES (58, 'PRODUCT_UNIT', '产品单位', '产品单位');
INSERT INTO `sys_code_type` VALUES (59, 'CATEGORY_A', 'A类片', 'A类片');
INSERT INTO `sys_code_type` VALUES (60, 'DELIVERY_METHOD', '交货方式', '交货方式');
INSERT INTO `sys_code_type` VALUES (61, 'FERTILITY_STATUS', '生育状况', '生育状况');
INSERT INTO `sys_code_type` VALUES (62, 'AWARD_TYPE', '奖励类别', '奖励类别');
INSERT INTO `sys_code_type` VALUES (63, 'STOCK_ADDRESS', '备货地点', '备货地点');
INSERT INTO `sys_code_type` VALUES (64, 'NATIONALITY', '国籍', '国籍');
INSERT INTO `sys_code_type` VALUES (65, 'NATION', '民族', '民族');
INSERT INTO `sys_code_type` VALUES (66, 'EDUCATION', '学位', '学位');
INSERT INTO `sys_code_type` VALUES (67, 'DEGREE', '学历', '学历');
INSERT INTO `sys_code_type` VALUES (68, 'GRADUATION_SITUATION', '毕业情况', '毕业情况');
INSERT INTO `sys_code_type` VALUES (69, 'SCHOOLING_DOCUMENTS_TYPE', '所获学历证书类型', '所获学历证书类型');
INSERT INTO `sys_code_type` VALUES (70, 'DEGREE_COUNTRY', '学位授予国家', '学位授予国家');
INSERT INTO `sys_code_type` VALUES (71, 'LEARNING_STYLE', '学习方式', '学习方式');
INSERT INTO `sys_code_type` VALUES (72, 'CUSTOMER_PAYMENT', '客户付款信息', '客户付款信息');
INSERT INTO `sys_code_type` VALUES (73, 'FACTORY_CATEGORY', '厂别', '厂别');
INSERT INTO `sys_code_type` VALUES (74, 'STAFF_CLASSIFY', '员工分类', '员工分类');
INSERT INTO `sys_code_type` VALUES (75, 'OPERATING_POST', '工作岗位', '工作岗位');
INSERT INTO `sys_code_type` VALUES (76, 'DISPATCH_PLACE', '发货地', '发货地');
INSERT INTO `sys_code_type` VALUES (77, 'PROJECT_DISTRIBUTION', '项目/分销', '项目/分销');
INSERT INTO `sys_code_type` VALUES (78, 'INQUIRIES_STATUS', '询单状态', '询单状态');
INSERT INTO `sys_code_type` VALUES (79, 'REVIEW_RULES', '评审规则', '评审规则');
INSERT INTO `sys_code_type` VALUES (80, 'TIME_TYPE', '时间类型', '时间类型');
INSERT INTO `sys_code_type` VALUES (81, 'TEMPORARILY_TYPE', '借调类型', '借调类型');
INSERT INTO `sys_code_type` VALUES (82, 'RETIRE_TYPE', '退休类型', '退休类型');
INSERT INTO `sys_code_type` VALUES (83, 'PRODUCT_TYPE', '产品类型', '产品类型');
INSERT INTO `sys_code_type` VALUES (84, 'RECRUITMENT_CHANNEL', '招聘渠道', '招聘渠道');
INSERT INTO `sys_code_type` VALUES (85, 'SELL_INSIDE_OUTSIDE', '内外销', '内外销');
INSERT INTO `sys_code_type` VALUES (86, 'SIGN_SUBJECT', '签约主体', '签约主体');
INSERT INTO `sys_code_type` VALUES (87, 'SIDE_AGREEMENT', '补充协议', '补充协议');
COMMIT;

-- ----------------------------
-- Table structure for sys_module_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_module_log`;
CREATE TABLE `sys_module_log` (
  `row_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_staff_id` bigint(10) DEFAULT NULL COMMENT '操作人ID',
  `opt_staff_no` varchar(45) DEFAULT NULL COMMENT '操作人工号',
  `opt_staff_name` varchar(45) DEFAULT NULL COMMENT '操作人姓名',
  `opt_type` varchar(20) DEFAULT NULL COMMENT '操作类型',
  `opt_describe` varchar(1000) DEFAULT NULL COMMENT '操作描述',
  `app_code` varchar(50) DEFAULT NULL COMMENT '应用编码',
  `table_id` bigint(10) DEFAULT NULL COMMENT '相关业务ID',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统应用操作日志表';

-- ----------------------------
-- Records of sys_module_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_module_log` VALUES (1, '2018-07-23 00:00:00', NULL, '12345678', 'test', 'update', '...', 'staff', 95, 0);
INSERT INTO `sys_module_log` VALUES (2, '2018-08-08 16:48:49', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 17, 0);
INSERT INTO `sys_module_log` VALUES (3, '2018-08-08 17:57:03', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 17, 0);
INSERT INTO `sys_module_log` VALUES (4, '2018-08-09 09:30:06', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 16, 0);
INSERT INTO `sys_module_log` VALUES (5, '2018-08-09 09:30:19', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 17, 0);
INSERT INTO `sys_module_log` VALUES (6, '2018-08-09 09:30:40', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000017改为EMPCON000018;', 'staff_agreement', 9, 0);
INSERT INTO `sys_module_log` VALUES (7, '2018-08-09 09:33:01', NULL, NULL, NULL, 'update', '', 'staff_agreement', 9, 0);
INSERT INTO `sys_module_log` VALUES (8, '2018-08-09 14:57:48', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 16, 0);
INSERT INTO `sys_module_log` VALUES (9, '2018-08-09 15:50:10', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 16, 0);
INSERT INTO `sys_module_log` VALUES (10, '2018-08-09 15:50:27', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 16, 0);
INSERT INTO `sys_module_log` VALUES (11, '2018-08-09 15:50:48', NULL, NULL, NULL, 'update', '', 'staff_contract', 21, 0);
INSERT INTO `sys_module_log` VALUES (12, '2018-08-09 16:09:05', NULL, NULL, NULL, 'update', '添加备注信息:123;', 'staff_contract', 16, 0);
INSERT INTO `sys_module_log` VALUES (13, '2018-08-09 16:12:47', NULL, NULL, NULL, 'update', '添加备注信息:1231;', 'staff_contract', 23, 0);
INSERT INTO `sys_module_log` VALUES (14, '2018-08-09 16:14:25', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;备注信息:34改为346666;', 'staff_contract', 27, 0);
INSERT INTO `sys_module_log` VALUES (15, '2018-08-09 16:17:40', NULL, NULL, NULL, 'update', '', 'staff_agreement', 13, 0);
INSERT INTO `sys_module_log` VALUES (16, '2018-08-09 16:17:53', NULL, NULL, NULL, 'update', '', 'staff_agreement', 13, 0);
INSERT INTO `sys_module_log` VALUES (17, '2018-08-09 16:19:30', NULL, NULL, NULL, 'update', '', 'staff_agreement', 16, 0);
INSERT INTO `sys_module_log` VALUES (18, '2018-08-09 16:21:35', NULL, NULL, NULL, 'update', '备注信息:123333改为12333377777;', 'staff_agreement', 18, 0);
INSERT INTO `sys_module_log` VALUES (19, '2018-08-09 16:22:12', NULL, NULL, NULL, 'update', '关联合同编号:3434改为EMPCON000025;备注信息:12333377777改为789;', 'staff_agreement', 18, 0);
INSERT INTO `sys_module_log` VALUES (20, '2018-08-09 16:22:36', NULL, NULL, NULL, 'update', '备注信息:789改为789111;', 'staff_agreement', 19, 0);
INSERT INTO `sys_module_log` VALUES (21, '2018-08-09 16:23:23', NULL, NULL, NULL, 'update', '备注信息:1改为145;', 'staff_agreement', 20, 0);
INSERT INTO `sys_module_log` VALUES (22, '2018-08-09 16:25:41', NULL, NULL, NULL, 'update', '备注信息:89改为789;', 'staff_agreement', 16, 0);
INSERT INTO `sys_module_log` VALUES (23, '2018-08-09 16:41:30', NULL, NULL, NULL, 'update', '备注信息:963改为789987;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (24, '2018-08-09 16:41:55', NULL, NULL, NULL, 'update', '备注信息:789987改为777;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (25, '2018-08-09 17:32:09', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000032改为EMPCON000002;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (26, '2018-08-09 17:34:58', NULL, NULL, NULL, 'update', '', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (27, '2018-08-09 17:35:13', NULL, NULL, NULL, 'update', '', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (28, '2018-08-09 17:35:34', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000032改为EMPCON000002;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (29, '2018-08-09 17:35:58', NULL, NULL, NULL, 'update', '', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (30, '2018-08-09 17:43:45', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000032改为EMPCON000002;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (31, '2018-08-09 17:44:38', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000002改为EMPCON000032;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (32, '2018-08-09 17:45:29', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000032改为EMPCON000002;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (33, '2018-08-09 17:45:43', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000002改为EMPCON000032;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (34, '2018-08-09 17:46:07', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000032改为EMPCON000002;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (35, '2018-08-09 17:46:12', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000002改为EMPCON000032;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (36, '2018-08-09 18:11:47', NULL, NULL, NULL, 'update', '关联合同编号:EMPCON000032改为EMPCON000002;', 'staff_agreement', 21, 0);
INSERT INTO `sys_module_log` VALUES (37, '2018-08-09 19:22:19', NULL, NULL, NULL, 'update', '', 'staff_contract', 34, 0);
INSERT INTO `sys_module_log` VALUES (38, '2018-08-09 23:33:52', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (39, '2018-08-14 20:20:41', NULL, NULL, NULL, 'relieve', '解除原因是：测试111', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (40, '2018-08-14 20:48:01', NULL, NULL, NULL, 'renew', '续签合同号为：EMPCON000060', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (41, '2018-08-15 14:27:33', NULL, NULL, NULL, 'update', '备注信息:2323改为23232323;', 'staff_agreement', 24, 0);
INSERT INTO `sys_module_log` VALUES (42, '2018-08-15 14:27:47', NULL, NULL, NULL, 'update', '添加备注信息:123;', 'staff_agreement', 25, 0);
INSERT INTO `sys_module_log` VALUES (43, '2018-08-15 16:08:26', NULL, NULL, NULL, 'end', '终止原因是：wsda', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (44, '2018-08-15 16:08:27', NULL, NULL, NULL, 'end', '终止原因是：wsda', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (45, '2018-08-15 16:09:31', NULL, NULL, NULL, 'end', '终止原因是：weaf', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (46, '2018-08-15 16:11:21', NULL, NULL, NULL, 'relieve', '解除原因是：wadaw', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (47, '2018-08-15 16:11:42', NULL, NULL, NULL, 'end', '终止原因是：awdwa', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (48, '2018-08-15 16:15:17', NULL, NULL, NULL, 'end', '终止原因是：afwafw', 'staff_agreement', 23, 0);
INSERT INTO `sys_module_log` VALUES (49, '2018-08-15 16:15:35', NULL, NULL, NULL, 'end', '终止原因是：adadadada', 'staff_agreement', 25, 0);
INSERT INTO `sys_module_log` VALUES (50, '2018-08-15 16:15:59', NULL, NULL, NULL, 'relieve', '解除原因是：aaaaa', 'staff_agreement', 25, 0);
INSERT INTO `sys_module_log` VALUES (51, '2018-08-15 16:46:36', NULL, NULL, NULL, 'renew', '续签协议号为：JAXY000029', 'staff_agreement', 27, 0);
INSERT INTO `sys_module_log` VALUES (52, '2018-08-15 17:09:11', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;添加备注信息:123;', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (53, '2018-08-15 17:09:42', NULL, NULL, NULL, 'update', '', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (54, '2018-08-15 17:11:35', NULL, NULL, NULL, 'update', '', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (55, '2018-08-15 19:44:54', NULL, NULL, NULL, 'update', '', 'staff_agreement', 30, 0);
INSERT INTO `sys_module_log` VALUES (56, '2018-08-15 19:45:29', NULL, NULL, NULL, 'renew', '续签协议号为：JAXY000031', 'staff_agreement', 28, 0);
INSERT INTO `sys_module_log` VALUES (57, '2018-08-15 19:45:40', NULL, NULL, NULL, 'end', '终止原因是：2222', 'staff_agreement', 31, 0);
INSERT INTO `sys_module_log` VALUES (58, '2018-08-15 23:09:31', NULL, NULL, NULL, 'update', '', 'staff_agreement', 30, 0);
INSERT INTO `sys_module_log` VALUES (59, '2018-08-16 01:01:14', NULL, NULL, NULL, 'end', '终止原因是：sef', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (60, '2018-08-16 01:01:24', NULL, NULL, NULL, 'relieve', '解除原因是：saxcaw', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (61, '2018-08-16 01:23:31', NULL, NULL, NULL, 'end', '终止原因是：seg', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (62, '2018-08-16 10:55:28', NULL, NULL, NULL, 'end', '终止原因是：zsc', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (63, '2018-08-16 10:55:46', NULL, NULL, NULL, 'end', '终止原因是：wad', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (64, '2018-08-16 11:25:56', NULL, NULL, NULL, 'end', '终止原因是：sfse', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (65, '2018-08-16 11:26:20', NULL, NULL, NULL, 'relieve', '解除原因是：awdawd', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (66, '2018-08-16 11:26:36', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (67, '2018-08-16 11:27:09', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 58, 0);
INSERT INTO `sys_module_log` VALUES (68, '2018-08-16 11:27:37', NULL, NULL, NULL, 'relieve', '解除原因是：efww', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (69, '2018-08-16 11:28:05', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (70, '2018-08-16 11:34:18', NULL, NULL, NULL, 'relieve', '解除原因是：asd', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (71, '2018-08-16 11:34:39', NULL, NULL, NULL, 'update', '领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (72, '2018-08-16 11:40:16', NULL, NULL, NULL, 'relieve', '解除原因是：saaa', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (73, '2018-08-16 11:40:25', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 59, 0);
INSERT INTO `sys_module_log` VALUES (74, '2018-08-16 14:49:01', NULL, NULL, NULL, 'relieve', '解除原因是：wad', 'staff_contract', 62, 0);
INSERT INTO `sys_module_log` VALUES (75, '2018-08-16 15:18:17', NULL, NULL, NULL, 'update', '', 'staff_contract', 63, 0);
INSERT INTO `sys_module_log` VALUES (76, '2018-08-16 15:18:40', NULL, NULL, NULL, 'update', '', 'staff_contract', 63, 0);
INSERT INTO `sys_module_log` VALUES (77, '2018-08-16 15:29:53', NULL, NULL, NULL, 'update', '', 'staff_contract', 63, 0);
INSERT INTO `sys_module_log` VALUES (78, '2018-08-16 15:39:19', NULL, NULL, NULL, 'update', '', 'staff_contract', 63, 0);
INSERT INTO `sys_module_log` VALUES (79, '2018-08-16 15:39:43', NULL, NULL, NULL, 'update', '', 'staff_agreement', 24, 0);
INSERT INTO `sys_module_log` VALUES (80, '2018-08-16 15:39:55', NULL, NULL, NULL, 'relieve', '解除原因是：esf', 'staff_agreement', 32, 0);
INSERT INTO `sys_module_log` VALUES (81, '2018-08-16 15:40:05', NULL, NULL, NULL, 'end', '终止原因是：awd', 'staff_agreement', 26, 0);
INSERT INTO `sys_module_log` VALUES (82, '2018-08-16 15:40:14', NULL, NULL, NULL, 'relieve', '解除原因是：adadad', 'staff_agreement', 29, 0);
INSERT INTO `sys_module_log` VALUES (83, '2018-08-16 15:40:26', NULL, NULL, NULL, 'update', '备注信息:23232323改为23232323wwwwww;', 'staff_agreement', 24, 0);
INSERT INTO `sys_module_log` VALUES (84, '2018-08-16 15:40:37', NULL, NULL, NULL, 'relieve', '解除原因是：adwadw', 'staff_agreement', 24, 0);
INSERT INTO `sys_module_log` VALUES (85, '2018-08-16 16:35:29', NULL, NULL, NULL, 'relieve', '解除原因是：zzZ', 'staff_contract', 63, 0);
INSERT INTO `sys_module_log` VALUES (86, '2018-08-16 16:38:54', NULL, NULL, NULL, 'update', '领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 64, 0);
INSERT INTO `sys_module_log` VALUES (87, '2018-08-16 16:39:23', NULL, NULL, NULL, 'update', '', 'staff_contract', 64, 0);
INSERT INTO `sys_module_log` VALUES (88, '2018-08-16 16:46:58', NULL, NULL, NULL, 'update', '', 'staff_contract', 64, 0);
INSERT INTO `sys_module_log` VALUES (89, '2018-08-16 16:47:03', NULL, NULL, NULL, 'update', '', 'staff_contract', 64, 0);
INSERT INTO `sys_module_log` VALUES (90, '2018-08-16 16:53:13', NULL, NULL, NULL, 'update', '合同类型:劳动合同改为劳动合同;合同终止日期:Thu Aug 23 00:00:00 CST 2018改为Fri Aug 24 00:00:00 CST 2018;', 'staff_contract', 64, 0);
INSERT INTO `sys_module_log` VALUES (91, '2018-08-16 17:01:56', NULL, NULL, NULL, 'update', '添加工作地点:123;领取合同备份:否改为是;签订相关协议:否改为是;', 'staff_contract', 63, 0);
INSERT INTO `sys_module_log` VALUES (92, '2018-08-16 17:02:41', NULL, NULL, NULL, 'update', '合同类型:聘用合同改为聘用合同;合同期限类型:以完成一定工作任务为期限改为无固定期限;合同签订日期:2018-08-17改为2018-08-16;合同生效日期:2018-08-17改为2018-08-18;合同终止日期:2018-08-24改为2018-08-31;甲方:南京基地改为北京基地;添加工作地点:321;领取合同备份:是改为否;签订相关协议:是改为否;', 'staff_contract', 64, 0);
INSERT INTO `sys_module_log` VALUES (93, '2018-08-16 17:13:42', NULL, NULL, NULL, 'update', '协议类型:上岗协议改为竞业限制协议;协议签订日期:2018-08-16改为2018-08-17;协议生效日期:2018-08-17改为2018-08-18;协议终止日期:2018-08-24改为2018-08-25;签订单位:南京基地改为北京基地;关联合同编号:EMPCON000063改为EMPCON000064;', 'staff_agreement', 33, 0);
INSERT INTO `sys_module_log` VALUES (94, '2018-08-21 15:10:39', NULL, NULL, NULL, 'update', '协议类型:竞业限制协议改为上岗协议;', 'staff_agreement', 33, 0);
INSERT INTO `sys_module_log` VALUES (95, '2018-08-23 19:50:18', NULL, NULL, NULL, 'update', '部门：由“北京部门2”变为“”职位：由“职位14555”变为“职位2”职等：由“副总经理”变为“总经理”国籍：由“中国”变为“”民族：由“汉族”变为“”证件有效期：由“Thu Aug 16 00:00:00 CST 2018”变为“Thu Aug 16 00:00:00 CST 2018”出生日期：由“Fri Aug 10 00:00:00 CST 2018”变为“Fri Aug 10 00:00:00 CST 2018”入职日期：由“Wed Aug 01 00:00:00 CST 2018”变为“Wed Aug 01 00:00:00 CST 2018”', 'staff', 110, 0);
INSERT INTO `sys_module_log` VALUES (96, '2018-08-24 15:28:41', NULL, NULL, NULL, 'update', '基地：由“”变为“北京基地”部门：由“北京部门2”变为“南京部门”国籍：由“中国”变为“”民族：由“汉族”变为“”证件有效期：由“Thu Aug 16 00:00:00 CST 2018”变为“Thu Aug 16 00:00:00 CST 2018”出生日期：由“Fri Aug 10 00:00:00 CST 2018”变为“Fri Aug 10 00:00:00 CST 2018”入职日期：由“Wed Aug 01 00:00:00 CST 2018”变为“Wed Aug 01 00:00:00 CST 2018”', 'staff', 110, 0);
INSERT INTO `sys_module_log` VALUES (97, '2018-08-28 13:52:50', NULL, NULL, NULL, 'update', '基地：由“北京基地”变为“”部门：由“南京部门”变为“”国籍：由“中国”变为“”民族：由“汉族”变为“”证件号码：由“21564196418578”变为“21564196418578”证件有效期：由“2018-08-16”变为“2018-08-16”出生日期：由“2018-08-10”变为“2018-08-10”入职日期：由“2018-08-01”变为“2018-08-01”', 'staff', 110, 0);
INSERT INTO `sys_module_log` VALUES (98, '2018-08-29 14:34:40', 118, 'admin', '管理员', 'update', '基地：由“北京基地”变为“”部门：由“南京部门”变为“”国籍：由“中国”变为“”民族：由“汉族”变为“”证件号码：由“21564196418578”变为“21564196418578”证件有效期：由“2018-08-16”变为“2018-08-07”出生日期：由“2018-08-10”变为“2018-08-10”入职日期：由“2018-08-01”变为“2018-08-01”', 'staff', 110, 0);
INSERT INTO `sys_module_log` VALUES (99, '2018-08-29 14:37:44', NULL, NULL, NULL, 'update', '基地：由“北京基地”变为“”部门：由“北京部门2”变为“”生育状况：由“未育”变为“”国籍：由“中国”变为“”民族：由“汉族”变为“”证件号码：由“33”变为“33”证件有效期：由“2018-08-30”变为“2018-08-30”出生日期：由“2018-08-01”变为“2018-08-01”入职日期：由“2018-08-28”变为“2018-08-28”', 'staff', 153, 0);
INSERT INTO `sys_module_log` VALUES (100, '2018-08-29 14:37:51', NULL, NULL, NULL, 'update', '基地：由“北京基地”变为“”部门：由“北京部门2”变为“”生育状况：由“未育”变为“”国籍：由“中国”变为“”民族：由“汉族”变为“”证件号码：由“33”变为“33”证件有效期：由“2018-08-30”变为“2018-08-30”出生日期：由“2018-08-01”变为“2018-08-01”入职日期：由“2018-08-28”变为“2018-08-28”', 'staff', 153, 0);
INSERT INTO `sys_module_log` VALUES (101, '2018-09-04 16:33:14', 112, 'ASZ00022', '李四', 'update', '基地：由“”变为“南京基地”;部门：由“北京部门2”变为“北京部门新增”;员工分类：由“”变为“”;工作岗位：由“”变为“CQE工程师”;国籍：由“”变为“”;民族：由“”变为“”;证件有效期：由“2018-08-09”变为“2018-08-09”;出生日期：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;离职日期：由“2018-08-22”变为“2018-08-22”;', 'staff', 108, 0);
INSERT INTO `sys_module_log` VALUES (102, '2018-09-04 18:36:51', NULL, NULL, NULL, 'update', '基地：由“”变为“北京基地”;部门：由“北京部门2”变为“南京部门”;员工分类：由“”变为“”;工作岗位：由“”变为“CQE工程师”;国籍：由“”变为“”;民族：由“”变为“”;证件有效期：由“2018-08-09”变为“2018-08-09”;出生日期：由“2018-08-09”变为“2018-08-09”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;', 'staff', 112, 0);
INSERT INTO `sys_module_log` VALUES (103, '2018-09-06 13:39:26', NULL, NULL, NULL, 'relieve', '解除原因是：1', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (104, '2018-09-07 13:56:27', NULL, NULL, NULL, 'update', '基地：由“北京基地”变为“”;部门：由“南京部门”变为“”;员工分类：由“”变为“”;工作岗位：由“”变为“操作工”;国籍：由“”变为“阿富汗”;民族：由“”变为“”;证件有效期：由“2018-08-10”变为“2018-08-10”;出生日期：由“2018-08-10”变为“2018-08-10”;入职日期：由“2018-08-10”变为“2018-08-10”;', 'staff', 113, 0);
INSERT INTO `sys_module_log` VALUES (105, '2018-09-10 13:42:21', NULL, NULL, NULL, 'update', '上传文件路径:改为group1/M00/00/01/rB0PK1uWBDmAaJ1GAAAAeRvnyQM619.sql;', 'staff_agreement', 23, 0);
INSERT INTO `sys_module_log` VALUES (106, '2018-09-10 13:43:18', NULL, NULL, NULL, 'update', '基地：由“南京基地”变为“”;部门：由“南京部门”变为“”;员工分类：由“”变为“”;工作岗位：由“”变为“CQE工程师”;国籍：由“”变为“”;民族：由“”变为“”;证件有效期：由“2018-08-10”变为“2018-08-10”;出生日期：由“2018-08-10”变为“2018-08-10”;入职日期：由“2018-08-10”变为“2018-08-10”;', 'staff', 115, 0);
INSERT INTO `sys_module_log` VALUES (107, '2018-09-10 13:43:53', NULL, NULL, NULL, 'update', '基地：由“南京基地”变为“”;部门：由“南京部门”变为“”;员工分类：由“”变为“”;工作岗位：由“CQE工程师”变为“”;国籍：由“”变为“”;民族：由“”变为“”;证件有效期：由“2018-08-10”变为“2018-08-10”;出生日期：由“2018-08-10”变为“2018-08-10”;入职日期：由“2018-08-10”变为“2018-08-10”;', 'staff', 115, 0);
INSERT INTO `sys_module_log` VALUES (108, '2018-09-10 14:37:20', NULL, NULL, NULL, 'update', '基地：由“北京基地”变为“”;部门：由“南京部门”变为“”;成本中心：由“”变为“”;员工分类：由“”变为“”;工作岗位：由“”变为“操作工”;国籍：由“”变为“”;民族：由“”变为“”;证件有效期：由“2018-07-20”变为“2018-07-20”;出生日期：由“2010-07-20”变为“2010-07-20”;首次工作时间：由“2018-07-20”变为“2018-07-20”;入职日期：由“2018-07-19”变为“2018-07-19”;离职日期：由“2018-08-22”变为“2018-08-22”;', 'staff', 95, 0);
INSERT INTO `sys_module_log` VALUES (109, '2018-09-10 19:37:01', NULL, NULL, NULL, 'update', '基地：由“北京基地”变为“”;部门：由“南京部门”变为“”;生育状况：由“未育”变为“”;员工分类：由“”变为“”;工作岗位：由“EHS工程师”变为“操作工”;国籍：由“中国”变为“”;民族：由“汉族”变为“”;证件有效期：由“2018-09-10”变为“2018-09-10”;出生日期：由“1965-02-08”变为“1965-02-08”;入职日期：由“2018-09-10”变为“2018-09-10”;', 'staff', 161, 0);
INSERT INTO `sys_module_log` VALUES (110, '2018-09-17 16:05:07', NULL, NULL, NULL, 'end', '终止原因是：null', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (111, '2018-09-17 16:39:10', NULL, NULL, NULL, 'discontinue', '中止原因是：123', 'staff_contract', 65, 0);
INSERT INTO `sys_module_log` VALUES (112, '2018-09-18 10:23:03', NULL, NULL, NULL, 'relieve', '解除原因是：000001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (113, '2018-09-18 10:28:21', NULL, NULL, NULL, 'relieve', '解除原因是：00001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (114, '2018-09-18 10:34:17', NULL, NULL, NULL, 'relieve', '解除原因是：00001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (115, '2018-09-18 10:41:42', NULL, NULL, NULL, 'relieve', '解除原因是：00001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (116, '2018-09-18 10:50:37', NULL, NULL, NULL, 'relieve', '解除原因是：00001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (117, '2018-09-18 10:57:12', NULL, NULL, NULL, 'relieve', '解除原因是：0001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (118, '2018-09-18 11:08:44', NULL, NULL, NULL, 'relieve', '解除原因是：0001', 'staff_contract', 56, 0);
INSERT INTO `sys_module_log` VALUES (119, '2018-09-18 12:28:36', 0, 'admin', '超级管理员', 'update', '基地：由“南京基地”变为“”;部门：由“”变为“”;生育状况：由“”变为“未育”;员工分类：由“”变为“”;工作岗位：由“”变为“操作工”;国籍：由“”变为“阿富汗”;民族：由“”变为“”;证件到期时间：由“2018-08-09”变为“2018-08-09”;出生日期：由“2018-08-09”变为“2018-08-09”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;', 'staff', 107, 0);
INSERT INTO `sys_module_log` VALUES (120, '2018-09-18 13:40:37', 0, 'admin', '超级管理员', 'update', '基地：由“北京基地”变为“”;部门：由“南京部门”变为“”;成本中心：由“”变为“”;员工分类：由“”变为“”;工作岗位：由“操作工”变为“”;国籍：由“”变为“中国”;民族：由“”变为“汉族”;证件到期时间：由“2018-07-20”变为“2018-07-20”;出生日期：由“2010-07-20”变为“2010-07-20”;首次工作时间：由“2018-07-20”变为“2018-07-20”;入职日期：由“2018-07-19”变为“2018-07-19”;离职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 95, 0);
INSERT INTO `sys_module_log` VALUES (121, '2018-09-18 13:45:15', 0, 'admin', '超级管理员', 'update', '基地：由“南京基地”变为“”;部门：由“”变为“”;员工分类：由“”变为“”;工作岗位：由“”变为“操作工”;国籍：由“”变为“中国”;民族：由“”变为“汉族”;证件到期时间：由“2018-08-09”变为“2018-08-09”;出生日期：由“2018-08-09”变为“2018-08-09”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;', 'staff', 107, 0);
INSERT INTO `sys_module_log` VALUES (122, '2018-09-19 17:24:48', 0, 'admin', '超级管理员', 'update', '基地：由“北京基地”变为“”;部门：由“南京部门”变为“”;生育状况：由“未育”变为“”;用工类型：由“”变为“固定职工”;员工分类：由“白领”变为“”;工作岗位：由“EHS工程师”变为“”;厂别：由“”变为“一厂”;招聘渠道：由“”变为“网络招聘”;国籍：由“中国”变为“”;民族：由“汉族”变为“”;证件号码：由“32012419191919199”变为“320124199509124512”;户口所在地：由“”变为“312”;籍贯：由“”变为“312”;邮箱：由“”变为“123456789@qq.com”;证件到期时间：由“2018-09-18”变为“2018-09-18”;出生日期：由“2018-09-10”变为“1995-09-12”;入职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 168, 0);
INSERT INTO `sys_module_log` VALUES (123, '2018-09-19 17:53:40', NULL, NULL, NULL, 'relieve', '解除原因是：111111111111', 'staff_contract', 60, 0);
INSERT INTO `sys_module_log` VALUES (124, '2018-09-19 18:02:00', NULL, NULL, NULL, 'end', '终止原因是：2222222222', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (125, '2018-09-19 18:03:25', NULL, NULL, NULL, 'end', '终止原因是：22222222222', 'staff_contract', 61, 0);
INSERT INTO `sys_module_log` VALUES (126, '2018-09-19 18:44:15', NULL, NULL, NULL, 'end', '终止原因是：333333333333', 'staff_contract', 68, 0);
INSERT INTO `sys_module_log` VALUES (127, '2018-09-19 19:30:24', 0, 'admin', '超级管理员', 'update', '基地：由“南京基地”变为“”;部门：由“北京部门”变为“”;员工分类：由“白领”变为“”;工作岗位：由“操作工”变为“”;是否加入黑名单：由“是”变为“”;国籍：由“中国”变为“”;民族：由“汉族”变为“”;证件号码：由“320124191919191911”变为“320124199510124512”;阴历阳历：由“”变为“阳历”;证件到期时间：由“2018-08-09”变为“2018-08-09”;出生日期：由“2018-08-09”变为“1995-10-12”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;离职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 107, 0);
INSERT INTO `sys_module_log` VALUES (128, '2018-09-20 15:11:40', 0, 'admin', '超级管理员', 'update', '基地：由“”变为“”;部门：由“”变为“”;员工分类：由“白领”变为“”;工作岗位：由“操作工”变为“”;是否加入黑名单：由“是”变为“”;国籍：由“中国”变为“”;民族：由“汉族”变为“”;阴历阳历：由“”变为“阳历”;证件到期时间：由“2018-08-09”变为“2018-08-09”;出生日期：由“1995-10-12”变为“1995-10-12”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;离职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 107, 0);
INSERT INTO `sys_module_log` VALUES (129, '2018-09-20 16:55:24', 0, 'admin', '超级管理员', 'update', '基地：由“”变为“”;部门：由“”变为“”;员工分类：由“白领”变为“”;工作岗位：由“操作工”变为“”;是否加入黑名单：由“是”变为“”;国籍：由“中国”变为“”;民族：由“汉族”变为“”;阴历阳历：由“阳历”变为“阴历”;证件到期时间：由“2018-08-09”变为“2018-08-09”;出生日期：由“1995-10-12”变为“1995-10-12”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;离职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 107, 0);
INSERT INTO `sys_module_log` VALUES (130, '2018-09-20 17:15:49', 0, 'admin', '超级管理员', 'update', '员工分类：由“白领”变为“”;是否加入黑名单：由“是”变为“”;阴历阳历：由“阴历”变为“阳历”;证件到期时间：由“2018-08-09”变为“2018-08-09”;出生日期：由“1995-10-12”变为“1995-10-12”;首次工作时间：由“2018-08-09”变为“2018-08-09”;入职日期：由“2018-08-09”变为“2018-08-09”;离职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 107, 0);
INSERT INTO `sys_module_log` VALUES (131, '2018-09-21 10:55:24', 0, 'admin', '超级管理员', 'update', '基地：由“北京基地”变为“”;部门：由“南京部门”变为“”;成本中心：由“”变为“”;员工分类：由“白领”变为“”;工作岗位：由“操作工”变为“”;国籍：由“”变为“”;民族：由“”变为“”;阴历阳历：由“”变为“阳历”;证件到期时间：由“2018-07-20”变为“2018-07-20”;出生日期：由“2010-07-20”变为“2010-07-20”;首次工作时间：由“2018-07-20”变为“2018-07-20”;入职日期：由“2018-07-19”变为“2018-07-19”;离职日期：由“2018-09-18”变为“2018-09-18”;', 'staff', 95, 0);
INSERT INTO `sys_module_log` VALUES (144, '2018-10-31 11:03:17', 0, 'admin', '超级管理员', 'update', '职衔：由“职位2”变为“444”;职等/赋值名称：由“总经理”变为“副总经理”;', 'staff', 113, 0);
INSERT INTO `sys_module_log` VALUES (145, '2018-10-31 11:03:17', 0, 'admin', '超级管理员', 'update', '职衔：由“职位2”变为“444”;职等/赋值名称：由“总经理”变为“副总经理”;', 'staff', 114, 0);
INSERT INTO `sys_module_log` VALUES (146, '2018-10-31 11:03:17', 0, 'admin', '超级管理员', 'update', '职衔：由“职位2”变为“444”;职等/赋值名称：由“总经理”变为“副总经理”;职级：由“M9”变为“M7”;', 'staff', 115, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_ref_role_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_ref_role_data`;
CREATE TABLE `sys_ref_role_data` (
  `row_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) DEFAULT NULL COMMENT '角色ID',
  `org_id` bigint(11) DEFAULT NULL COMMENT '组织架构ID',
  `is_half` int(11) DEFAULT NULL COMMENT '是否是全选中，0:不是，1是',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1480 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色数据权限关系（关联组织架构）';

-- ----------------------------
-- Records of sys_ref_role_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_ref_role_data` VALUES (644, 12, 2, 1);
INSERT INTO `sys_ref_role_data` VALUES (645, 12, 3, 1);
INSERT INTO `sys_ref_role_data` VALUES (646, 12, 155, 1);
INSERT INTO `sys_ref_role_data` VALUES (647, 12, 161, 1);
INSERT INTO `sys_ref_role_data` VALUES (648, 12, 169, 1);
INSERT INTO `sys_ref_role_data` VALUES (649, 12, 176, 1);
INSERT INTO `sys_ref_role_data` VALUES (650, 12, 187, 1);
INSERT INTO `sys_ref_role_data` VALUES (651, 12, 190, 1);
INSERT INTO `sys_ref_role_data` VALUES (652, 12, 191, 1);
INSERT INTO `sys_ref_role_data` VALUES (653, 12, 200, 1);
INSERT INTO `sys_ref_role_data` VALUES (654, 12, 283, 1);
INSERT INTO `sys_ref_role_data` VALUES (655, 12, 295, 1);
INSERT INTO `sys_ref_role_data` VALUES (656, 12, 306, 1);
INSERT INTO `sys_ref_role_data` VALUES (657, 12, 310, 1);
INSERT INTO `sys_ref_role_data` VALUES (658, 12, 314, 1);
INSERT INTO `sys_ref_role_data` VALUES (659, 12, 319, 1);
INSERT INTO `sys_ref_role_data` VALUES (660, 12, 1, 0);
INSERT INTO `sys_ref_role_data` VALUES (1433, 2, 2, 1);
INSERT INTO `sys_ref_role_data` VALUES (1434, 2, 3, 1);
INSERT INTO `sys_ref_role_data` VALUES (1435, 2, 155, 1);
INSERT INTO `sys_ref_role_data` VALUES (1436, 2, 161, 1);
INSERT INTO `sys_ref_role_data` VALUES (1437, 2, 169, 1);
INSERT INTO `sys_ref_role_data` VALUES (1438, 2, 176, 1);
INSERT INTO `sys_ref_role_data` VALUES (1439, 2, 187, 1);
INSERT INTO `sys_ref_role_data` VALUES (1440, 2, 190, 1);
INSERT INTO `sys_ref_role_data` VALUES (1441, 2, 191, 1);
INSERT INTO `sys_ref_role_data` VALUES (1442, 2, 200, 1);
INSERT INTO `sys_ref_role_data` VALUES (1443, 2, 283, 1);
INSERT INTO `sys_ref_role_data` VALUES (1444, 2, 295, 1);
INSERT INTO `sys_ref_role_data` VALUES (1445, 2, 297, 1);
INSERT INTO `sys_ref_role_data` VALUES (1446, 2, 302, 1);
INSERT INTO `sys_ref_role_data` VALUES (1447, 2, 306, 1);
INSERT INTO `sys_ref_role_data` VALUES (1448, 2, 310, 1);
INSERT INTO `sys_ref_role_data` VALUES (1449, 2, 314, 1);
INSERT INTO `sys_ref_role_data` VALUES (1450, 2, 319, 1);
INSERT INTO `sys_ref_role_data` VALUES (1451, 2, 320, 1);
INSERT INTO `sys_ref_role_data` VALUES (1452, 2, 321, 1);
INSERT INTO `sys_ref_role_data` VALUES (1453, 2, 322, 1);
INSERT INTO `sys_ref_role_data` VALUES (1454, 2, 324, 1);
INSERT INTO `sys_ref_role_data` VALUES (1455, 2, 325, 1);
INSERT INTO `sys_ref_role_data` VALUES (1456, 2, 326, 1);
INSERT INTO `sys_ref_role_data` VALUES (1457, 2, 327, 1);
INSERT INTO `sys_ref_role_data` VALUES (1458, 2, 328, 1);
INSERT INTO `sys_ref_role_data` VALUES (1459, 2, 329, 1);
INSERT INTO `sys_ref_role_data` VALUES (1460, 2, 330, 1);
INSERT INTO `sys_ref_role_data` VALUES (1461, 2, 331, 1);
INSERT INTO `sys_ref_role_data` VALUES (1462, 2, 332, 1);
INSERT INTO `sys_ref_role_data` VALUES (1463, 2, 336, 1);
INSERT INTO `sys_ref_role_data` VALUES (1464, 2, 337, 1);
INSERT INTO `sys_ref_role_data` VALUES (1465, 2, 338, 1);
INSERT INTO `sys_ref_role_data` VALUES (1466, 2, 1, 0);
INSERT INTO `sys_ref_role_data` VALUES (1467, 1, 155, 1);
INSERT INTO `sys_ref_role_data` VALUES (1468, 1, 161, 1);
INSERT INTO `sys_ref_role_data` VALUES (1469, 1, 169, 1);
INSERT INTO `sys_ref_role_data` VALUES (1470, 1, 176, 1);
INSERT INTO `sys_ref_role_data` VALUES (1471, 1, 187, 1);
INSERT INTO `sys_ref_role_data` VALUES (1472, 1, 190, 1);
INSERT INTO `sys_ref_role_data` VALUES (1473, 1, 191, 1);
INSERT INTO `sys_ref_role_data` VALUES (1474, 1, 200, 1);
INSERT INTO `sys_ref_role_data` VALUES (1475, 1, 283, 1);
INSERT INTO `sys_ref_role_data` VALUES (1476, 1, 310, 1);
INSERT INTO `sys_ref_role_data` VALUES (1477, 1, 314, 1);
INSERT INTO `sys_ref_role_data` VALUES (1478, 1, 319, 1);
INSERT INTO `sys_ref_role_data` VALUES (1479, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_ref_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_ref_role_resource`;
CREATE TABLE `sys_ref_role_resource` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10135 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_ref_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_ref_role_resource` VALUES (8951, 2, 1);
INSERT INTO `sys_ref_role_resource` VALUES (8952, 2, 2);
INSERT INTO `sys_ref_role_resource` VALUES (8953, 2, 4);
INSERT INTO `sys_ref_role_resource` VALUES (8954, 2, 5);
INSERT INTO `sys_ref_role_resource` VALUES (8955, 2, 6);
INSERT INTO `sys_ref_role_resource` VALUES (8956, 2, 8);
INSERT INTO `sys_ref_role_resource` VALUES (8957, 2, 9);
INSERT INTO `sys_ref_role_resource` VALUES (8958, 2, 10);
INSERT INTO `sys_ref_role_resource` VALUES (8959, 2, 11);
INSERT INTO `sys_ref_role_resource` VALUES (8960, 2, 12);
INSERT INTO `sys_ref_role_resource` VALUES (8961, 2, 13);
INSERT INTO `sys_ref_role_resource` VALUES (8962, 2, 14);
INSERT INTO `sys_ref_role_resource` VALUES (8963, 2, 15);
INSERT INTO `sys_ref_role_resource` VALUES (8964, 2, 16);
INSERT INTO `sys_ref_role_resource` VALUES (8965, 2, 17);
INSERT INTO `sys_ref_role_resource` VALUES (8966, 2, 18);
INSERT INTO `sys_ref_role_resource` VALUES (8967, 2, 19);
INSERT INTO `sys_ref_role_resource` VALUES (8968, 2, 20);
INSERT INTO `sys_ref_role_resource` VALUES (8969, 2, 21);
INSERT INTO `sys_ref_role_resource` VALUES (8970, 2, 22);
INSERT INTO `sys_ref_role_resource` VALUES (8971, 2, 23);
INSERT INTO `sys_ref_role_resource` VALUES (8972, 2, 24);
INSERT INTO `sys_ref_role_resource` VALUES (8973, 2, 25);
INSERT INTO `sys_ref_role_resource` VALUES (8974, 2, 26);
INSERT INTO `sys_ref_role_resource` VALUES (8975, 2, 27);
INSERT INTO `sys_ref_role_resource` VALUES (8976, 2, 28);
INSERT INTO `sys_ref_role_resource` VALUES (8977, 2, 30);
INSERT INTO `sys_ref_role_resource` VALUES (8978, 2, 31);
INSERT INTO `sys_ref_role_resource` VALUES (8979, 2, 33);
INSERT INTO `sys_ref_role_resource` VALUES (8980, 2, 34);
INSERT INTO `sys_ref_role_resource` VALUES (8981, 2, 35);
INSERT INTO `sys_ref_role_resource` VALUES (8982, 2, 36);
INSERT INTO `sys_ref_role_resource` VALUES (8983, 2, 37);
INSERT INTO `sys_ref_role_resource` VALUES (8984, 2, 38);
INSERT INTO `sys_ref_role_resource` VALUES (8985, 2, 39);
INSERT INTO `sys_ref_role_resource` VALUES (8986, 2, 40);
INSERT INTO `sys_ref_role_resource` VALUES (8987, 2, 41);
INSERT INTO `sys_ref_role_resource` VALUES (8988, 2, 42);
INSERT INTO `sys_ref_role_resource` VALUES (8989, 2, 43);
INSERT INTO `sys_ref_role_resource` VALUES (8990, 2, 44);
INSERT INTO `sys_ref_role_resource` VALUES (8991, 2, 45);
INSERT INTO `sys_ref_role_resource` VALUES (8992, 2, 46);
INSERT INTO `sys_ref_role_resource` VALUES (8993, 2, 47);
INSERT INTO `sys_ref_role_resource` VALUES (8994, 2, 48);
INSERT INTO `sys_ref_role_resource` VALUES (8995, 2, 49);
INSERT INTO `sys_ref_role_resource` VALUES (8996, 2, 50);
INSERT INTO `sys_ref_role_resource` VALUES (8997, 2, 51);
INSERT INTO `sys_ref_role_resource` VALUES (8998, 2, 52);
INSERT INTO `sys_ref_role_resource` VALUES (8999, 2, 53);
INSERT INTO `sys_ref_role_resource` VALUES (9000, 2, 54);
INSERT INTO `sys_ref_role_resource` VALUES (9001, 2, 55);
INSERT INTO `sys_ref_role_resource` VALUES (9002, 2, 56);
INSERT INTO `sys_ref_role_resource` VALUES (9003, 2, 57);
INSERT INTO `sys_ref_role_resource` VALUES (9004, 2, 58);
INSERT INTO `sys_ref_role_resource` VALUES (9005, 2, 59);
INSERT INTO `sys_ref_role_resource` VALUES (9006, 2, 60);
INSERT INTO `sys_ref_role_resource` VALUES (9007, 2, 61);
INSERT INTO `sys_ref_role_resource` VALUES (9008, 2, 62);
INSERT INTO `sys_ref_role_resource` VALUES (9009, 2, 63);
INSERT INTO `sys_ref_role_resource` VALUES (9010, 2, 64);
INSERT INTO `sys_ref_role_resource` VALUES (9011, 2, 65);
INSERT INTO `sys_ref_role_resource` VALUES (9012, 2, 66);
INSERT INTO `sys_ref_role_resource` VALUES (9013, 2, 67);
INSERT INTO `sys_ref_role_resource` VALUES (9014, 2, 68);
INSERT INTO `sys_ref_role_resource` VALUES (9015, 2, 69);
INSERT INTO `sys_ref_role_resource` VALUES (9016, 2, 70);
INSERT INTO `sys_ref_role_resource` VALUES (9017, 2, 71);
INSERT INTO `sys_ref_role_resource` VALUES (9018, 2, 72);
INSERT INTO `sys_ref_role_resource` VALUES (9019, 2, 109);
INSERT INTO `sys_ref_role_resource` VALUES (9020, 2, 110);
INSERT INTO `sys_ref_role_resource` VALUES (9021, 2, 113);
INSERT INTO `sys_ref_role_resource` VALUES (9022, 2, 115);
INSERT INTO `sys_ref_role_resource` VALUES (9023, 2, 116);
INSERT INTO `sys_ref_role_resource` VALUES (9024, 2, 117);
INSERT INTO `sys_ref_role_resource` VALUES (9025, 2, 118);
INSERT INTO `sys_ref_role_resource` VALUES (9026, 2, 119);
INSERT INTO `sys_ref_role_resource` VALUES (9027, 2, 120);
INSERT INTO `sys_ref_role_resource` VALUES (9028, 2, 121);
INSERT INTO `sys_ref_role_resource` VALUES (9029, 2, 122);
INSERT INTO `sys_ref_role_resource` VALUES (9030, 2, 127);
INSERT INTO `sys_ref_role_resource` VALUES (9031, 2, 128);
INSERT INTO `sys_ref_role_resource` VALUES (9032, 2, 129);
INSERT INTO `sys_ref_role_resource` VALUES (9033, 2, 130);
INSERT INTO `sys_ref_role_resource` VALUES (9034, 2, 131);
INSERT INTO `sys_ref_role_resource` VALUES (9035, 2, 132);
INSERT INTO `sys_ref_role_resource` VALUES (9036, 2, 133);
INSERT INTO `sys_ref_role_resource` VALUES (9037, 2, 134);
INSERT INTO `sys_ref_role_resource` VALUES (9038, 2, 135);
INSERT INTO `sys_ref_role_resource` VALUES (9039, 2, 136);
INSERT INTO `sys_ref_role_resource` VALUES (9040, 2, 137);
INSERT INTO `sys_ref_role_resource` VALUES (9041, 2, 138);
INSERT INTO `sys_ref_role_resource` VALUES (9042, 2, 150);
INSERT INTO `sys_ref_role_resource` VALUES (9043, 2, 151);
INSERT INTO `sys_ref_role_resource` VALUES (9044, 2, 152);
INSERT INTO `sys_ref_role_resource` VALUES (9045, 2, 153);
INSERT INTO `sys_ref_role_resource` VALUES (9046, 2, 154);
INSERT INTO `sys_ref_role_resource` VALUES (9047, 2, 155);
INSERT INTO `sys_ref_role_resource` VALUES (9048, 2, 156);
INSERT INTO `sys_ref_role_resource` VALUES (9049, 2, 157);
INSERT INTO `sys_ref_role_resource` VALUES (9050, 2, 158);
INSERT INTO `sys_ref_role_resource` VALUES (9051, 2, 159);
INSERT INTO `sys_ref_role_resource` VALUES (9052, 2, 160);
INSERT INTO `sys_ref_role_resource` VALUES (9053, 2, 161);
INSERT INTO `sys_ref_role_resource` VALUES (9054, 2, 162);
INSERT INTO `sys_ref_role_resource` VALUES (9055, 2, 166);
INSERT INTO `sys_ref_role_resource` VALUES (9056, 2, 167);
INSERT INTO `sys_ref_role_resource` VALUES (9057, 2, 168);
INSERT INTO `sys_ref_role_resource` VALUES (9058, 2, 169);
INSERT INTO `sys_ref_role_resource` VALUES (9059, 2, 170);
INSERT INTO `sys_ref_role_resource` VALUES (9060, 2, 172);
INSERT INTO `sys_ref_role_resource` VALUES (9061, 2, 173);
INSERT INTO `sys_ref_role_resource` VALUES (9062, 2, 174);
INSERT INTO `sys_ref_role_resource` VALUES (9063, 2, 175);
INSERT INTO `sys_ref_role_resource` VALUES (9064, 2, 176);
INSERT INTO `sys_ref_role_resource` VALUES (9065, 2, 177);
INSERT INTO `sys_ref_role_resource` VALUES (9066, 2, 178);
INSERT INTO `sys_ref_role_resource` VALUES (9067, 2, 179);
INSERT INTO `sys_ref_role_resource` VALUES (9068, 2, 180);
INSERT INTO `sys_ref_role_resource` VALUES (9069, 2, 181);
INSERT INTO `sys_ref_role_resource` VALUES (9070, 2, 182);
INSERT INTO `sys_ref_role_resource` VALUES (9071, 2, 183);
INSERT INTO `sys_ref_role_resource` VALUES (9072, 2, 184);
INSERT INTO `sys_ref_role_resource` VALUES (9073, 2, 185);
INSERT INTO `sys_ref_role_resource` VALUES (9074, 2, 186);
INSERT INTO `sys_ref_role_resource` VALUES (9075, 2, 187);
INSERT INTO `sys_ref_role_resource` VALUES (9076, 2, 188);
INSERT INTO `sys_ref_role_resource` VALUES (9077, 2, 189);
INSERT INTO `sys_ref_role_resource` VALUES (9078, 2, 190);
INSERT INTO `sys_ref_role_resource` VALUES (9079, 2, 191);
INSERT INTO `sys_ref_role_resource` VALUES (9080, 2, 192);
INSERT INTO `sys_ref_role_resource` VALUES (9081, 2, 193);
INSERT INTO `sys_ref_role_resource` VALUES (9082, 2, 194);
INSERT INTO `sys_ref_role_resource` VALUES (9083, 2, 195);
INSERT INTO `sys_ref_role_resource` VALUES (9084, 2, 196);
INSERT INTO `sys_ref_role_resource` VALUES (9085, 2, 197);
INSERT INTO `sys_ref_role_resource` VALUES (9086, 2, 198);
INSERT INTO `sys_ref_role_resource` VALUES (9087, 2, 199);
INSERT INTO `sys_ref_role_resource` VALUES (9088, 2, 200);
INSERT INTO `sys_ref_role_resource` VALUES (9089, 2, 201);
INSERT INTO `sys_ref_role_resource` VALUES (9090, 2, 202);
INSERT INTO `sys_ref_role_resource` VALUES (9091, 2, 203);
INSERT INTO `sys_ref_role_resource` VALUES (9092, 2, 204);
INSERT INTO `sys_ref_role_resource` VALUES (9093, 2, 205);
INSERT INTO `sys_ref_role_resource` VALUES (9094, 2, 206);
INSERT INTO `sys_ref_role_resource` VALUES (9095, 2, 207);
INSERT INTO `sys_ref_role_resource` VALUES (9096, 2, 208);
INSERT INTO `sys_ref_role_resource` VALUES (9097, 2, 209);
INSERT INTO `sys_ref_role_resource` VALUES (9098, 2, 210);
INSERT INTO `sys_ref_role_resource` VALUES (9099, 2, 211);
INSERT INTO `sys_ref_role_resource` VALUES (9100, 2, 212);
INSERT INTO `sys_ref_role_resource` VALUES (9101, 2, 213);
INSERT INTO `sys_ref_role_resource` VALUES (9102, 2, 214);
INSERT INTO `sys_ref_role_resource` VALUES (9103, 2, 215);
INSERT INTO `sys_ref_role_resource` VALUES (9104, 2, 216);
INSERT INTO `sys_ref_role_resource` VALUES (9105, 2, 217);
INSERT INTO `sys_ref_role_resource` VALUES (9106, 2, 218);
INSERT INTO `sys_ref_role_resource` VALUES (9107, 2, 219);
INSERT INTO `sys_ref_role_resource` VALUES (9108, 2, 220);
INSERT INTO `sys_ref_role_resource` VALUES (9109, 2, 221);
INSERT INTO `sys_ref_role_resource` VALUES (9110, 2, 222);
INSERT INTO `sys_ref_role_resource` VALUES (9111, 2, 224);
INSERT INTO `sys_ref_role_resource` VALUES (9112, 2, 225);
INSERT INTO `sys_ref_role_resource` VALUES (9113, 2, 226);
INSERT INTO `sys_ref_role_resource` VALUES (9114, 2, 227);
INSERT INTO `sys_ref_role_resource` VALUES (9115, 2, 228);
INSERT INTO `sys_ref_role_resource` VALUES (9116, 2, 229);
INSERT INTO `sys_ref_role_resource` VALUES (9117, 2, 230);
INSERT INTO `sys_ref_role_resource` VALUES (9118, 2, 231);
INSERT INTO `sys_ref_role_resource` VALUES (9119, 2, 232);
INSERT INTO `sys_ref_role_resource` VALUES (9120, 2, 234);
INSERT INTO `sys_ref_role_resource` VALUES (9121, 2, 235);
INSERT INTO `sys_ref_role_resource` VALUES (9122, 2, 236);
INSERT INTO `sys_ref_role_resource` VALUES (9123, 2, 237);
INSERT INTO `sys_ref_role_resource` VALUES (9124, 2, 238);
INSERT INTO `sys_ref_role_resource` VALUES (9125, 2, 240);
INSERT INTO `sys_ref_role_resource` VALUES (9126, 2, 243);
INSERT INTO `sys_ref_role_resource` VALUES (9127, 2, 244);
INSERT INTO `sys_ref_role_resource` VALUES (9128, 2, 245);
INSERT INTO `sys_ref_role_resource` VALUES (9129, 2, 246);
INSERT INTO `sys_ref_role_resource` VALUES (9130, 2, 247);
INSERT INTO `sys_ref_role_resource` VALUES (9131, 2, 248);
INSERT INTO `sys_ref_role_resource` VALUES (9132, 2, 249);
INSERT INTO `sys_ref_role_resource` VALUES (9133, 2, 3);
INSERT INTO `sys_ref_role_resource` VALUES (9134, 2, 7);
INSERT INTO `sys_ref_role_resource` VALUES (9135, 2, 29);
INSERT INTO `sys_ref_role_resource` VALUES (9136, 2, 108);
INSERT INTO `sys_ref_role_resource` VALUES (9137, 2, 112);
INSERT INTO `sys_ref_role_resource` VALUES (9138, 2, 114);
INSERT INTO `sys_ref_role_resource` VALUES (10003, 12, 1);
INSERT INTO `sys_ref_role_resource` VALUES (10004, 12, 2);
INSERT INTO `sys_ref_role_resource` VALUES (10005, 12, 9);
INSERT INTO `sys_ref_role_resource` VALUES (10006, 12, 13);
INSERT INTO `sys_ref_role_resource` VALUES (10007, 12, 17);
INSERT INTO `sys_ref_role_resource` VALUES (10008, 12, 36);
INSERT INTO `sys_ref_role_resource` VALUES (10009, 12, 40);
INSERT INTO `sys_ref_role_resource` VALUES (10010, 12, 44);
INSERT INTO `sys_ref_role_resource` VALUES (10011, 12, 236);
INSERT INTO `sys_ref_role_resource` VALUES (10012, 12, 243);
INSERT INTO `sys_ref_role_resource` VALUES (10013, 12, 3);
INSERT INTO `sys_ref_role_resource` VALUES (10014, 12, 4);
INSERT INTO `sys_ref_role_resource` VALUES (10015, 12, 5);
INSERT INTO `sys_ref_role_resource` VALUES (10016, 12, 6);
INSERT INTO `sys_ref_role_resource` VALUES (10017, 12, 29);
INSERT INTO `sys_ref_role_resource` VALUES (10018, 12, 30);
INSERT INTO `sys_ref_role_resource` VALUES (10019, 12, 33);
INSERT INTO `sys_ref_role_resource` VALUES (10020, 12, 34);
INSERT INTO `sys_ref_role_resource` VALUES (10021, 12, 35);
INSERT INTO `sys_ref_role_resource` VALUES (10022, 12, 240);
INSERT INTO `sys_ref_role_resource` VALUES (10023, 14, 1);
INSERT INTO `sys_ref_role_resource` VALUES (10024, 14, 2);
INSERT INTO `sys_ref_role_resource` VALUES (10025, 14, 3);
INSERT INTO `sys_ref_role_resource` VALUES (10026, 14, 4);
INSERT INTO `sys_ref_role_resource` VALUES (10027, 14, 5);
INSERT INTO `sys_ref_role_resource` VALUES (10028, 14, 6);
INSERT INTO `sys_ref_role_resource` VALUES (10029, 14, 7);
INSERT INTO `sys_ref_role_resource` VALUES (10030, 14, 8);
INSERT INTO `sys_ref_role_resource` VALUES (10031, 14, 9);
INSERT INTO `sys_ref_role_resource` VALUES (10032, 14, 10);
INSERT INTO `sys_ref_role_resource` VALUES (10033, 14, 11);
INSERT INTO `sys_ref_role_resource` VALUES (10034, 14, 12);
INSERT INTO `sys_ref_role_resource` VALUES (10035, 14, 13);
INSERT INTO `sys_ref_role_resource` VALUES (10036, 14, 14);
INSERT INTO `sys_ref_role_resource` VALUES (10037, 14, 15);
INSERT INTO `sys_ref_role_resource` VALUES (10038, 14, 16);
INSERT INTO `sys_ref_role_resource` VALUES (10039, 14, 17);
INSERT INTO `sys_ref_role_resource` VALUES (10040, 14, 18);
INSERT INTO `sys_ref_role_resource` VALUES (10041, 14, 19);
INSERT INTO `sys_ref_role_resource` VALUES (10042, 14, 20);
INSERT INTO `sys_ref_role_resource` VALUES (10043, 14, 21);
INSERT INTO `sys_ref_role_resource` VALUES (10044, 14, 22);
INSERT INTO `sys_ref_role_resource` VALUES (10045, 14, 23);
INSERT INTO `sys_ref_role_resource` VALUES (10046, 14, 24);
INSERT INTO `sys_ref_role_resource` VALUES (10047, 14, 25);
INSERT INTO `sys_ref_role_resource` VALUES (10048, 14, 26);
INSERT INTO `sys_ref_role_resource` VALUES (10049, 14, 27);
INSERT INTO `sys_ref_role_resource` VALUES (10050, 14, 28);
INSERT INTO `sys_ref_role_resource` VALUES (10051, 14, 30);
INSERT INTO `sys_ref_role_resource` VALUES (10052, 14, 33);
INSERT INTO `sys_ref_role_resource` VALUES (10053, 14, 34);
INSERT INTO `sys_ref_role_resource` VALUES (10054, 14, 35);
INSERT INTO `sys_ref_role_resource` VALUES (10055, 14, 36);
INSERT INTO `sys_ref_role_resource` VALUES (10056, 14, 37);
INSERT INTO `sys_ref_role_resource` VALUES (10057, 14, 38);
INSERT INTO `sys_ref_role_resource` VALUES (10058, 14, 39);
INSERT INTO `sys_ref_role_resource` VALUES (10059, 14, 40);
INSERT INTO `sys_ref_role_resource` VALUES (10060, 14, 41);
INSERT INTO `sys_ref_role_resource` VALUES (10061, 14, 42);
INSERT INTO `sys_ref_role_resource` VALUES (10062, 14, 43);
INSERT INTO `sys_ref_role_resource` VALUES (10063, 14, 44);
INSERT INTO `sys_ref_role_resource` VALUES (10064, 14, 45);
INSERT INTO `sys_ref_role_resource` VALUES (10065, 14, 46);
INSERT INTO `sys_ref_role_resource` VALUES (10066, 14, 47);
INSERT INTO `sys_ref_role_resource` VALUES (10067, 14, 150);
INSERT INTO `sys_ref_role_resource` VALUES (10068, 14, 151);
INSERT INTO `sys_ref_role_resource` VALUES (10069, 14, 166);
INSERT INTO `sys_ref_role_resource` VALUES (10070, 14, 167);
INSERT INTO `sys_ref_role_resource` VALUES (10071, 14, 168);
INSERT INTO `sys_ref_role_resource` VALUES (10072, 14, 169);
INSERT INTO `sys_ref_role_resource` VALUES (10073, 14, 224);
INSERT INTO `sys_ref_role_resource` VALUES (10074, 14, 228);
INSERT INTO `sys_ref_role_resource` VALUES (10075, 14, 229);
INSERT INTO `sys_ref_role_resource` VALUES (10076, 14, 230);
INSERT INTO `sys_ref_role_resource` VALUES (10077, 14, 231);
INSERT INTO `sys_ref_role_resource` VALUES (10078, 14, 232);
INSERT INTO `sys_ref_role_resource` VALUES (10079, 14, 236);
INSERT INTO `sys_ref_role_resource` VALUES (10080, 14, 240);
INSERT INTO `sys_ref_role_resource` VALUES (10081, 14, 242);
INSERT INTO `sys_ref_role_resource` VALUES (10082, 14, 243);
INSERT INTO `sys_ref_role_resource` VALUES (10083, 14, 244);
INSERT INTO `sys_ref_role_resource` VALUES (10084, 14, 245);
INSERT INTO `sys_ref_role_resource` VALUES (10085, 14, 246);
INSERT INTO `sys_ref_role_resource` VALUES (10086, 14, 247);
INSERT INTO `sys_ref_role_resource` VALUES (10087, 14, 248);
INSERT INTO `sys_ref_role_resource` VALUES (10088, 14, 249);
INSERT INTO `sys_ref_role_resource` VALUES (10089, 14, 251);
INSERT INTO `sys_ref_role_resource` VALUES (10090, 14, 252);
INSERT INTO `sys_ref_role_resource` VALUES (10091, 14, 253);
INSERT INTO `sys_ref_role_resource` VALUES (10092, 14, 254);
INSERT INTO `sys_ref_role_resource` VALUES (10093, 14, 255);
INSERT INTO `sys_ref_role_resource` VALUES (10094, 14, 256);
INSERT INTO `sys_ref_role_resource` VALUES (10095, 14, 257);
INSERT INTO `sys_ref_role_resource` VALUES (10096, 14, 258);
INSERT INTO `sys_ref_role_resource` VALUES (10097, 14, 29);
INSERT INTO `sys_ref_role_resource` VALUES (10098, 1, 1);
INSERT INTO `sys_ref_role_resource` VALUES (10099, 1, 2);
INSERT INTO `sys_ref_role_resource` VALUES (10100, 1, 31);
INSERT INTO `sys_ref_role_resource` VALUES (10101, 1, 48);
INSERT INTO `sys_ref_role_resource` VALUES (10102, 1, 49);
INSERT INTO `sys_ref_role_resource` VALUES (10103, 1, 50);
INSERT INTO `sys_ref_role_resource` VALUES (10104, 1, 51);
INSERT INTO `sys_ref_role_resource` VALUES (10105, 1, 52);
INSERT INTO `sys_ref_role_resource` VALUES (10106, 1, 53);
INSERT INTO `sys_ref_role_resource` VALUES (10107, 1, 54);
INSERT INTO `sys_ref_role_resource` VALUES (10108, 1, 55);
INSERT INTO `sys_ref_role_resource` VALUES (10109, 1, 56);
INSERT INTO `sys_ref_role_resource` VALUES (10110, 1, 57);
INSERT INTO `sys_ref_role_resource` VALUES (10111, 1, 58);
INSERT INTO `sys_ref_role_resource` VALUES (10112, 1, 59);
INSERT INTO `sys_ref_role_resource` VALUES (10113, 1, 60);
INSERT INTO `sys_ref_role_resource` VALUES (10114, 1, 61);
INSERT INTO `sys_ref_role_resource` VALUES (10115, 1, 62);
INSERT INTO `sys_ref_role_resource` VALUES (10116, 1, 63);
INSERT INTO `sys_ref_role_resource` VALUES (10117, 1, 64);
INSERT INTO `sys_ref_role_resource` VALUES (10118, 1, 65);
INSERT INTO `sys_ref_role_resource` VALUES (10119, 1, 66);
INSERT INTO `sys_ref_role_resource` VALUES (10120, 1, 67);
INSERT INTO `sys_ref_role_resource` VALUES (10121, 1, 68);
INSERT INTO `sys_ref_role_resource` VALUES (10122, 1, 69);
INSERT INTO `sys_ref_role_resource` VALUES (10123, 1, 70);
INSERT INTO `sys_ref_role_resource` VALUES (10124, 1, 71);
INSERT INTO `sys_ref_role_resource` VALUES (10125, 1, 72);
INSERT INTO `sys_ref_role_resource` VALUES (10126, 1, 152);
INSERT INTO `sys_ref_role_resource` VALUES (10127, 1, 153);
INSERT INTO `sys_ref_role_resource` VALUES (10128, 1, 154);
INSERT INTO `sys_ref_role_resource` VALUES (10129, 1, 155);
INSERT INTO `sys_ref_role_resource` VALUES (10130, 1, 156);
INSERT INTO `sys_ref_role_resource` VALUES (10131, 1, 236);
INSERT INTO `sys_ref_role_resource` VALUES (10132, 1, 237);
INSERT INTO `sys_ref_role_resource` VALUES (10133, 1, 238);
INSERT INTO `sys_ref_role_resource` VALUES (10134, 1, 29);
COMMIT;

-- ----------------------------
-- Table structure for sys_ref_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_ref_user_resource`;
CREATE TABLE `sys_ref_user_resource` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户权限表';

-- ----------------------------
-- Records of sys_ref_user_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_ref_user_resource` VALUES (1, 1, 1);
INSERT INTO `sys_ref_user_resource` VALUES (2, 2, 1);
INSERT INTO `sys_ref_user_resource` VALUES (3, 3, 1);
INSERT INTO `sys_ref_user_resource` VALUES (4, 4, 1);
INSERT INTO `sys_ref_user_resource` VALUES (5, 5, 1);
INSERT INTO `sys_ref_user_resource` VALUES (6, 6, 1);
INSERT INTO `sys_ref_user_resource` VALUES (7, 7, 1);
INSERT INTO `sys_ref_user_resource` VALUES (8, 8, 1);
INSERT INTO `sys_ref_user_resource` VALUES (9, 9, 1);
INSERT INTO `sys_ref_user_resource` VALUES (10, 10, 1);
INSERT INTO `sys_ref_user_resource` VALUES (11, 11, 1);
INSERT INTO `sys_ref_user_resource` VALUES (12, 12, 1);
INSERT INTO `sys_ref_user_resource` VALUES (13, 13, 1);
INSERT INTO `sys_ref_user_resource` VALUES (14, 14, 1);
INSERT INTO `sys_ref_user_resource` VALUES (15, 15, 1);
INSERT INTO `sys_ref_user_resource` VALUES (16, 16, 1);
INSERT INTO `sys_ref_user_resource` VALUES (17, 17, 1);
INSERT INTO `sys_ref_user_resource` VALUES (18, 18, 1);
INSERT INTO `sys_ref_user_resource` VALUES (19, 19, 1);
INSERT INTO `sys_ref_user_resource` VALUES (20, 20, 1);
INSERT INTO `sys_ref_user_resource` VALUES (21, 21, 1);
INSERT INTO `sys_ref_user_resource` VALUES (22, 22, 1);
INSERT INTO `sys_ref_user_resource` VALUES (23, 23, 1);
INSERT INTO `sys_ref_user_resource` VALUES (24, 24, 1);
INSERT INTO `sys_ref_user_resource` VALUES (25, 25, 1);
INSERT INTO `sys_ref_user_resource` VALUES (26, 26, 1);
INSERT INTO `sys_ref_user_resource` VALUES (27, 27, 1);
INSERT INTO `sys_ref_user_resource` VALUES (28, 28, 1);
INSERT INTO `sys_ref_user_resource` VALUES (29, 29, 1);
INSERT INTO `sys_ref_user_resource` VALUES (30, 30, 1);
INSERT INTO `sys_ref_user_resource` VALUES (31, 31, 1);
INSERT INTO `sys_ref_user_resource` VALUES (32, 32, 1);
INSERT INTO `sys_ref_user_resource` VALUES (33, 33, 1);
INSERT INTO `sys_ref_user_resource` VALUES (34, 34, 1);
INSERT INTO `sys_ref_user_resource` VALUES (35, 35, 1);
INSERT INTO `sys_ref_user_resource` VALUES (36, 36, 1);
INSERT INTO `sys_ref_user_resource` VALUES (37, 37, 1);
INSERT INTO `sys_ref_user_resource` VALUES (38, 38, 1);
INSERT INTO `sys_ref_user_resource` VALUES (39, 39, 1);
INSERT INTO `sys_ref_user_resource` VALUES (40, 40, 1);
INSERT INTO `sys_ref_user_resource` VALUES (41, 41, 1);
INSERT INTO `sys_ref_user_resource` VALUES (42, 42, 1);
INSERT INTO `sys_ref_user_resource` VALUES (43, 43, 1);
INSERT INTO `sys_ref_user_resource` VALUES (44, 44, 1);
INSERT INTO `sys_ref_user_resource` VALUES (45, 45, 1);
INSERT INTO `sys_ref_user_resource` VALUES (46, 46, 1);
INSERT INTO `sys_ref_user_resource` VALUES (47, 47, 1);
INSERT INTO `sys_ref_user_resource` VALUES (48, 48, 1);
INSERT INTO `sys_ref_user_resource` VALUES (49, 49, 1);
INSERT INTO `sys_ref_user_resource` VALUES (50, 50, 1);
INSERT INTO `sys_ref_user_resource` VALUES (51, 51, 1);
INSERT INTO `sys_ref_user_resource` VALUES (52, 52, 1);
INSERT INTO `sys_ref_user_resource` VALUES (53, 53, 1);
INSERT INTO `sys_ref_user_resource` VALUES (54, 54, 1);
INSERT INTO `sys_ref_user_resource` VALUES (55, 55, 1);
INSERT INTO `sys_ref_user_resource` VALUES (56, 56, 1);
INSERT INTO `sys_ref_user_resource` VALUES (57, 57, 1);
INSERT INTO `sys_ref_user_resource` VALUES (58, 58, 1);
INSERT INTO `sys_ref_user_resource` VALUES (59, 59, 1);
INSERT INTO `sys_ref_user_resource` VALUES (60, 60, 1);
INSERT INTO `sys_ref_user_resource` VALUES (61, 61, 1);
INSERT INTO `sys_ref_user_resource` VALUES (62, 62, 1);
INSERT INTO `sys_ref_user_resource` VALUES (63, 63, 1);
INSERT INTO `sys_ref_user_resource` VALUES (64, 64, 1);
INSERT INTO `sys_ref_user_resource` VALUES (65, 65, 1);
INSERT INTO `sys_ref_user_resource` VALUES (66, 66, 1);
INSERT INTO `sys_ref_user_resource` VALUES (67, 67, 1);
INSERT INTO `sys_ref_user_resource` VALUES (68, 68, 1);
INSERT INTO `sys_ref_user_resource` VALUES (69, 69, 1);
INSERT INTO `sys_ref_user_resource` VALUES (70, 70, 1);
INSERT INTO `sys_ref_user_resource` VALUES (71, 71, 1);
INSERT INTO `sys_ref_user_resource` VALUES (72, 72, 1);
INSERT INTO `sys_ref_user_resource` VALUES (73, 73, 1);
INSERT INTO `sys_ref_user_resource` VALUES (74, 74, 1);
INSERT INTO `sys_ref_user_resource` VALUES (75, 75, 1);
INSERT INTO `sys_ref_user_resource` VALUES (76, 76, 1);
INSERT INTO `sys_ref_user_resource` VALUES (77, 77, 1);
INSERT INTO `sys_ref_user_resource` VALUES (78, 78, 1);
INSERT INTO `sys_ref_user_resource` VALUES (79, 79, 1);
INSERT INTO `sys_ref_user_resource` VALUES (80, 80, 1);
INSERT INTO `sys_ref_user_resource` VALUES (81, 81, 1);
INSERT INTO `sys_ref_user_resource` VALUES (82, 82, 1);
INSERT INTO `sys_ref_user_resource` VALUES (83, 83, 1);
INSERT INTO `sys_ref_user_resource` VALUES (84, 84, 1);
INSERT INTO `sys_ref_user_resource` VALUES (85, 85, 1);
INSERT INTO `sys_ref_user_resource` VALUES (86, 86, 1);
INSERT INTO `sys_ref_user_resource` VALUES (87, 87, 1);
INSERT INTO `sys_ref_user_resource` VALUES (88, 88, 1);
INSERT INTO `sys_ref_user_resource` VALUES (89, 89, 1);
INSERT INTO `sys_ref_user_resource` VALUES (90, 90, 1);
INSERT INTO `sys_ref_user_resource` VALUES (91, 91, 1);
INSERT INTO `sys_ref_user_resource` VALUES (92, 92, 1);
INSERT INTO `sys_ref_user_resource` VALUES (93, 93, 1);
INSERT INTO `sys_ref_user_resource` VALUES (94, 94, 1);
INSERT INTO `sys_ref_user_resource` VALUES (95, 95, 1);
INSERT INTO `sys_ref_user_resource` VALUES (96, 96, 1);
INSERT INTO `sys_ref_user_resource` VALUES (97, 97, 1);
INSERT INTO `sys_ref_user_resource` VALUES (98, 98, 1);
INSERT INTO `sys_ref_user_resource` VALUES (99, 99, 1);
INSERT INTO `sys_ref_user_resource` VALUES (100, 100, 1);
INSERT INTO `sys_ref_user_resource` VALUES (101, 101, 1);
INSERT INTO `sys_ref_user_resource` VALUES (102, 102, 1);
INSERT INTO `sys_ref_user_resource` VALUES (103, 103, 1);
INSERT INTO `sys_ref_user_resource` VALUES (104, 104, 1);
INSERT INTO `sys_ref_user_resource` VALUES (105, 105, 1);
INSERT INTO `sys_ref_user_resource` VALUES (106, 106, 1);
INSERT INTO `sys_ref_user_resource` VALUES (107, 107, 1);
INSERT INTO `sys_ref_user_resource` VALUES (108, 108, 1);
INSERT INTO `sys_ref_user_resource` VALUES (109, 109, 1);
INSERT INTO `sys_ref_user_resource` VALUES (110, 110, 1);
INSERT INTO `sys_ref_user_resource` VALUES (111, 111, 1);
INSERT INTO `sys_ref_user_resource` VALUES (112, 112, 1);
INSERT INTO `sys_ref_user_resource` VALUES (113, 113, 1);
INSERT INTO `sys_ref_user_resource` VALUES (114, 114, 1);
INSERT INTO `sys_ref_user_resource` VALUES (115, 115, 1);
INSERT INTO `sys_ref_user_resource` VALUES (116, 116, 1);
INSERT INTO `sys_ref_user_resource` VALUES (117, 117, 1);
INSERT INTO `sys_ref_user_resource` VALUES (118, 118, 1);
INSERT INTO `sys_ref_user_resource` VALUES (119, 119, 1);
INSERT INTO `sys_ref_user_resource` VALUES (120, 120, 1);
INSERT INTO `sys_ref_user_resource` VALUES (121, 121, 1);
INSERT INTO `sys_ref_user_resource` VALUES (122, 122, 1);
INSERT INTO `sys_ref_user_resource` VALUES (123, 123, 1);
INSERT INTO `sys_ref_user_resource` VALUES (124, 124, 1);
INSERT INTO `sys_ref_user_resource` VALUES (125, 125, 1);
INSERT INTO `sys_ref_user_resource` VALUES (126, 126, 1);
INSERT INTO `sys_ref_user_resource` VALUES (127, 127, 1);
INSERT INTO `sys_ref_user_resource` VALUES (128, 128, 1);
INSERT INTO `sys_ref_user_resource` VALUES (129, 129, 1);
INSERT INTO `sys_ref_user_resource` VALUES (130, 130, 1);
INSERT INTO `sys_ref_user_resource` VALUES (131, 131, 1);
INSERT INTO `sys_ref_user_resource` VALUES (132, 132, 1);
INSERT INTO `sys_ref_user_resource` VALUES (133, 133, 1);
INSERT INTO `sys_ref_user_resource` VALUES (134, 134, 1);
INSERT INTO `sys_ref_user_resource` VALUES (135, 135, 1);
INSERT INTO `sys_ref_user_resource` VALUES (136, 136, 1);
INSERT INTO `sys_ref_user_resource` VALUES (137, 137, 1);
INSERT INTO `sys_ref_user_resource` VALUES (138, 138, 1);
INSERT INTO `sys_ref_user_resource` VALUES (139, 139, 1);
INSERT INTO `sys_ref_user_resource` VALUES (140, 140, 1);
INSERT INTO `sys_ref_user_resource` VALUES (141, 141, 1);
INSERT INTO `sys_ref_user_resource` VALUES (142, 142, 1);
INSERT INTO `sys_ref_user_resource` VALUES (143, 143, 1);
INSERT INTO `sys_ref_user_resource` VALUES (144, 144, 1);
INSERT INTO `sys_ref_user_resource` VALUES (145, 145, 1);
INSERT INTO `sys_ref_user_resource` VALUES (146, 146, 1);
INSERT INTO `sys_ref_user_resource` VALUES (147, 147, 1);
INSERT INTO `sys_ref_user_resource` VALUES (148, 148, 1);
INSERT INTO `sys_ref_user_resource` VALUES (149, 149, 1);
INSERT INTO `sys_ref_user_resource` VALUES (150, 150, 1);
INSERT INTO `sys_ref_user_resource` VALUES (151, 151, 1);
INSERT INTO `sys_ref_user_resource` VALUES (152, 152, 1);
INSERT INTO `sys_ref_user_resource` VALUES (153, 153, 1);
INSERT INTO `sys_ref_user_resource` VALUES (154, 154, 1);
INSERT INTO `sys_ref_user_resource` VALUES (155, 155, 1);
INSERT INTO `sys_ref_user_resource` VALUES (156, 156, 1);
INSERT INTO `sys_ref_user_resource` VALUES (157, 157, 1);
INSERT INTO `sys_ref_user_resource` VALUES (163, 163, 1);
INSERT INTO `sys_ref_user_resource` VALUES (164, 164, 1);
INSERT INTO `sys_ref_user_resource` VALUES (166, 166, 1);
INSERT INTO `sys_ref_user_resource` VALUES (167, 167, 1);
INSERT INTO `sys_ref_user_resource` VALUES (168, 168, 1);
INSERT INTO `sys_ref_user_resource` VALUES (169, 169, 1);
INSERT INTO `sys_ref_user_resource` VALUES (170, 170, 1);
INSERT INTO `sys_ref_user_resource` VALUES (173, 173, 1);
INSERT INTO `sys_ref_user_resource` VALUES (207, 207, 1);
INSERT INTO `sys_ref_user_resource` VALUES (224, 224, 1);
INSERT INTO `sys_ref_user_resource` VALUES (228, 228, 1);
INSERT INTO `sys_ref_user_resource` VALUES (229, 229, 1);
INSERT INTO `sys_ref_user_resource` VALUES (230, 230, 1);
INSERT INTO `sys_ref_user_resource` VALUES (231, 231, 1);
INSERT INTO `sys_ref_user_resource` VALUES (232, 232, 1);
INSERT INTO `sys_ref_user_resource` VALUES (237, 237, 1);
INSERT INTO `sys_ref_user_resource` VALUES (238, 238, 1);
INSERT INTO `sys_ref_user_resource` VALUES (240, 240, 1);
INSERT INTO `sys_ref_user_resource` VALUES (241, 241, 1);
INSERT INTO `sys_ref_user_resource` VALUES (242, 242, 1);
INSERT INTO `sys_ref_user_resource` VALUES (243, 243, 1);
INSERT INTO `sys_ref_user_resource` VALUES (244, 244, 1);
INSERT INTO `sys_ref_user_resource` VALUES (245, 245, 1);
INSERT INTO `sys_ref_user_resource` VALUES (246, 246, 1);
INSERT INTO `sys_ref_user_resource` VALUES (247, 247, 1);
INSERT INTO `sys_ref_user_resource` VALUES (248, 248, 1);
INSERT INTO `sys_ref_user_resource` VALUES (249, 249, 1);
INSERT INTO `sys_ref_user_resource` VALUES (250, 250, 1);
INSERT INTO `sys_ref_user_resource` VALUES (251, 251, 1);
INSERT INTO `sys_ref_user_resource` VALUES (252, 252, 1);
INSERT INTO `sys_ref_user_resource` VALUES (253, 253, 1);
INSERT INTO `sys_ref_user_resource` VALUES (254, 254, 1);
INSERT INTO `sys_ref_user_resource` VALUES (255, 255, 1);
INSERT INTO `sys_ref_user_resource` VALUES (256, 256, 1);
INSERT INTO `sys_ref_user_resource` VALUES (257, 257, 1);
INSERT INTO `sys_ref_user_resource` VALUES (258, 258, 1);
INSERT INTO `sys_ref_user_resource` VALUES (259, 259, 1);
INSERT INTO `sys_ref_user_resource` VALUES (260, 260, 1);
INSERT INTO `sys_ref_user_resource` VALUES (261, 261, 1);
INSERT INTO `sys_ref_user_resource` VALUES (262, 262, 1);
INSERT INTO `sys_ref_user_resource` VALUES (263, 263, 1);
INSERT INTO `sys_ref_user_resource` VALUES (264, 264, 1);
INSERT INTO `sys_ref_user_resource` VALUES (265, 265, 1);
INSERT INTO `sys_ref_user_resource` VALUES (266, 266, 1);
INSERT INTO `sys_ref_user_resource` VALUES (267, 267, 1);
INSERT INTO `sys_ref_user_resource` VALUES (268, 268, 1);
INSERT INTO `sys_ref_user_resource` VALUES (269, 269, 1);
INSERT INTO `sys_ref_user_resource` VALUES (270, 270, 1);
INSERT INTO `sys_ref_user_resource` VALUES (271, 271, 1);
INSERT INTO `sys_ref_user_resource` VALUES (272, 272, 1);
INSERT INTO `sys_ref_user_resource` VALUES (273, 273, 1);
INSERT INTO `sys_ref_user_resource` VALUES (274, 274, 1);
INSERT INTO `sys_ref_user_resource` VALUES (275, 275, 1);
INSERT INTO `sys_ref_user_resource` VALUES (276, 276, 1);
INSERT INTO `sys_ref_user_resource` VALUES (277, 277, 1);
INSERT INTO `sys_ref_user_resource` VALUES (278, 278, 1);
INSERT INTO `sys_ref_user_resource` VALUES (279, 279, 1);
INSERT INTO `sys_ref_user_resource` VALUES (280, 280, 1);
INSERT INTO `sys_ref_user_resource` VALUES (281, 281, 1);
INSERT INTO `sys_ref_user_resource` VALUES (282, 282, 1);
INSERT INTO `sys_ref_user_resource` VALUES (283, 283, 1);
INSERT INTO `sys_ref_user_resource` VALUES (284, 284, 1);
INSERT INTO `sys_ref_user_resource` VALUES (285, 285, 1);
INSERT INTO `sys_ref_user_resource` VALUES (286, 286, 1);
INSERT INTO `sys_ref_user_resource` VALUES (287, 287, 1);
INSERT INTO `sys_ref_user_resource` VALUES (288, 288, 1);
INSERT INTO `sys_ref_user_resource` VALUES (289, 289, 1);
INSERT INTO `sys_ref_user_resource` VALUES (290, 290, 1);
INSERT INTO `sys_ref_user_resource` VALUES (291, 291, 1);
INSERT INTO `sys_ref_user_resource` VALUES (292, 292, 1);
INSERT INTO `sys_ref_user_resource` VALUES (293, 293, 1);
INSERT INTO `sys_ref_user_resource` VALUES (294, 294, 1);
INSERT INTO `sys_ref_user_resource` VALUES (295, 295, 1);
INSERT INTO `sys_ref_user_resource` VALUES (296, 296, 1);
INSERT INTO `sys_ref_user_resource` VALUES (309, 33, 133);
INSERT INTO `sys_ref_user_resource` VALUES (310, 34, 133);
INSERT INTO `sys_ref_user_resource` VALUES (311, 35, 133);
INSERT INTO `sys_ref_user_resource` VALUES (312, 59, 133);
INSERT INTO `sys_ref_user_resource` VALUES (313, 60, 133);
INSERT INTO `sys_ref_user_resource` VALUES (314, 61, 133);
INSERT INTO `sys_ref_user_resource` VALUES (315, 62, 133);
INSERT INTO `sys_ref_user_resource` VALUES (316, 63, 133);
INSERT INTO `sys_ref_user_resource` VALUES (317, 64, 133);
INSERT INTO `sys_ref_user_resource` VALUES (318, 65, 133);
INSERT INTO `sys_ref_user_resource` VALUES (319, 70, 133);
INSERT INTO `sys_ref_user_resource` VALUES (320, 71, 133);
INSERT INTO `sys_ref_user_resource` VALUES (321, 72, 133);
INSERT INTO `sys_ref_user_resource` VALUES (322, 152, 133);
INSERT INTO `sys_ref_user_resource` VALUES (323, 153, 133);
INSERT INTO `sys_ref_user_resource` VALUES (324, 154, 133);
INSERT INTO `sys_ref_user_resource` VALUES (325, 155, 133);
INSERT INTO `sys_ref_user_resource` VALUES (326, 156, 133);
INSERT INTO `sys_ref_user_resource` VALUES (327, 237, 133);
INSERT INTO `sys_ref_user_resource` VALUES (328, 238, 133);
INSERT INTO `sys_ref_user_resource` VALUES (329, 240, 133);
INSERT INTO `sys_ref_user_resource` VALUES (330, 297, 1);
INSERT INTO `sys_ref_user_resource` VALUES (331, 299, 1);
INSERT INTO `sys_ref_user_resource` VALUES (332, 300, 1);
INSERT INTO `sys_ref_user_resource` VALUES (333, 301, 1);
INSERT INTO `sys_ref_user_resource` VALUES (334, 244, 132);
INSERT INTO `sys_ref_user_resource` VALUES (335, 245, 132);
INSERT INTO `sys_ref_user_resource` VALUES (336, 246, 132);
INSERT INTO `sys_ref_user_resource` VALUES (337, 247, 132);
INSERT INTO `sys_ref_user_resource` VALUES (338, 248, 132);
INSERT INTO `sys_ref_user_resource` VALUES (339, 249, 132);
INSERT INTO `sys_ref_user_resource` VALUES (340, 29, 132);
INSERT INTO `sys_ref_user_resource` VALUES (341, 30, 132);
INSERT INTO `sys_ref_user_resource` VALUES (342, 240, 132);
COMMIT;

-- ----------------------------
-- Table structure for sys_ref_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_ref_user_role`;
CREATE TABLE `sys_ref_user_role` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_ref_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_ref_user_role` VALUES (2, 1, 137);
INSERT INTO `sys_ref_user_role` VALUES (3, 1, 142);
INSERT INTO `sys_ref_user_role` VALUES (4, 1, 143);
INSERT INTO `sys_ref_user_role` VALUES (5, 1, 145);
INSERT INTO `sys_ref_user_role` VALUES (6, 1, 146);
INSERT INTO `sys_ref_user_role` VALUES (7, 1, 147);
INSERT INTO `sys_ref_user_role` VALUES (9, 1, 148);
INSERT INTO `sys_ref_user_role` VALUES (10, 1, 149);
INSERT INTO `sys_ref_user_role` VALUES (11, 1, 150);
INSERT INTO `sys_ref_user_role` VALUES (12, 1, 151);
INSERT INTO `sys_ref_user_role` VALUES (13, 1, 152);
INSERT INTO `sys_ref_user_role` VALUES (14, 1, 153);
INSERT INTO `sys_ref_user_role` VALUES (15, 1, 154);
INSERT INTO `sys_ref_user_role` VALUES (16, 1, 155);
INSERT INTO `sys_ref_user_role` VALUES (18, 1, 125);
INSERT INTO `sys_ref_user_role` VALUES (19, 2, 126);
INSERT INTO `sys_ref_user_role` VALUES (20, 1, 156);
INSERT INTO `sys_ref_user_role` VALUES (21, 1, 160);
INSERT INTO `sys_ref_user_role` VALUES (22, 1, 161);
INSERT INTO `sys_ref_user_role` VALUES (23, 1, 162);
INSERT INTO `sys_ref_user_role` VALUES (24, 1, 163);
INSERT INTO `sys_ref_user_role` VALUES (26, 1, 166);
INSERT INTO `sys_ref_user_role` VALUES (28, 1, 167);
INSERT INTO `sys_ref_user_role` VALUES (29, 1, 168);
INSERT INTO `sys_ref_user_role` VALUES (30, 1, 169);
INSERT INTO `sys_ref_user_role` VALUES (31, 1, 172);
INSERT INTO `sys_ref_user_role` VALUES (33, 1, 173);
INSERT INTO `sys_ref_user_role` VALUES (34, 2, 173);
INSERT INTO `sys_ref_user_role` VALUES (35, 1, 174);
INSERT INTO `sys_ref_user_role` VALUES (36, 1, 175);
INSERT INTO `sys_ref_user_role` VALUES (37, 1, 176);
INSERT INTO `sys_ref_user_role` VALUES (38, 1, 177);
INSERT INTO `sys_ref_user_role` VALUES (39, 1, 178);
INSERT INTO `sys_ref_user_role` VALUES (40, 1, 179);
INSERT INTO `sys_ref_user_role` VALUES (42, 1, 132);
INSERT INTO `sys_ref_user_role` VALUES (44, 1, 181);
INSERT INTO `sys_ref_user_role` VALUES (45, 1, 182);
INSERT INTO `sys_ref_user_role` VALUES (46, 1, 183);
INSERT INTO `sys_ref_user_role` VALUES (48, 2, 187);
INSERT INTO `sys_ref_user_role` VALUES (49, 1, 188);
INSERT INTO `sys_ref_user_role` VALUES (50, 1, 4);
INSERT INTO `sys_ref_user_role` VALUES (51, 1, 189);
INSERT INTO `sys_ref_user_role` VALUES (54, 1, 190);
INSERT INTO `sys_ref_user_role` VALUES (55, 1, 191);
INSERT INTO `sys_ref_user_role` VALUES (56, 1, 192);
INSERT INTO `sys_ref_user_role` VALUES (60, 1, 193);
INSERT INTO `sys_ref_user_role` VALUES (61, 2, 193);
INSERT INTO `sys_ref_user_role` VALUES (62, 1, 194);
INSERT INTO `sys_ref_user_role` VALUES (65, 1, 195);
INSERT INTO `sys_ref_user_role` VALUES (66, 1, 196);
INSERT INTO `sys_ref_user_role` VALUES (67, 1, 2);
INSERT INTO `sys_ref_user_role` VALUES (68, 1, 1);
INSERT INTO `sys_ref_user_role` VALUES (69, 2, 1);
INSERT INTO `sys_ref_user_role` VALUES (70, 1, 197);
INSERT INTO `sys_ref_user_role` VALUES (71, 1, 131);
INSERT INTO `sys_ref_user_role` VALUES (73, 1, 198);
INSERT INTO `sys_ref_user_role` VALUES (76, 1, 133);
INSERT INTO `sys_ref_user_role` VALUES (77, 14, 133);
INSERT INTO `sys_ref_user_role` VALUES (78, 1, 199);
INSERT INTO `sys_ref_user_role` VALUES (79, 1, 200);
INSERT INTO `sys_ref_user_role` VALUES (80, 1, 201);
INSERT INTO `sys_ref_user_role` VALUES (81, 1, 202);
INSERT INTO `sys_ref_user_role` VALUES (82, 1, 203);
INSERT INTO `sys_ref_user_role` VALUES (83, 1, 204);
INSERT INTO `sys_ref_user_role` VALUES (84, 1, 205);
INSERT INTO `sys_ref_user_role` VALUES (85, 1, 206);
INSERT INTO `sys_ref_user_role` VALUES (86, 1, 207);
INSERT INTO `sys_ref_user_role` VALUES (87, 1, 208);
INSERT INTO `sys_ref_user_role` VALUES (88, 1, 209);
INSERT INTO `sys_ref_user_role` VALUES (89, 1, 210);
INSERT INTO `sys_ref_user_role` VALUES (90, 1, 211);
INSERT INTO `sys_ref_user_role` VALUES (91, 1, 212);
INSERT INTO `sys_ref_user_role` VALUES (92, 1, 213);
INSERT INTO `sys_ref_user_role` VALUES (93, 1, 214);
INSERT INTO `sys_ref_user_role` VALUES (94, 1, 215);
INSERT INTO `sys_ref_user_role` VALUES (95, 1, 216);
INSERT INTO `sys_ref_user_role` VALUES (96, 1, 217);
INSERT INTO `sys_ref_user_role` VALUES (97, 1, 216);
INSERT INTO `sys_ref_user_role` VALUES (98, 1, 217);
INSERT INTO `sys_ref_user_role` VALUES (99, 1, 218);
INSERT INTO `sys_ref_user_role` VALUES (100, 1, 220);
INSERT INTO `sys_ref_user_role` VALUES (101, 1, 222);
INSERT INTO `sys_ref_user_role` VALUES (102, 1, 224);
INSERT INTO `sys_ref_user_role` VALUES (103, 1, 226);
INSERT INTO `sys_ref_user_role` VALUES (104, 1, 228);
INSERT INTO `sys_ref_user_role` VALUES (105, 1, 230);
INSERT INTO `sys_ref_user_role` VALUES (106, 1, 232);
INSERT INTO `sys_ref_user_role` VALUES (107, 1, 234);
INSERT INTO `sys_ref_user_role` VALUES (108, 1, 236);
INSERT INTO `sys_ref_user_role` VALUES (109, 1, 238);
INSERT INTO `sys_ref_user_role` VALUES (110, 1, 244);
INSERT INTO `sys_ref_user_role` VALUES (111, 1, 246);
INSERT INTO `sys_ref_user_role` VALUES (112, 1, 248);
INSERT INTO `sys_ref_user_role` VALUES (113, 1, 250);
INSERT INTO `sys_ref_user_role` VALUES (114, 1, 251);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `key_` varchar(255) DEFAULT NULL,
  `p_key` varchar(255) DEFAULT NULL,
  `type_` varchar(255) DEFAULT NULL,
  `name_zh` varchar(255) DEFAULT NULL COMMENT '资源名',
  `name_en` varchar(255) DEFAULT NULL COMMENT '资源名',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标样式',
  `order_num` int(11) DEFAULT '1',
  `del_flag` int(11) DEFAULT '0',
  `access_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, 0, 'home', 'root', '0', '个人中心', 'Person Center', 'home', 1, 0, '/home');
INSERT INTO `sys_resource` VALUES (2, 1, 'home.my', 'home', '1', '我的首页', 'My Notice', '', 1, 0, '/home/my');
INSERT INTO `sys_resource` VALUES (3, 0, 'system', 'root', '0', '系统设置', 'System Setting', 'setting', 10, 0, '/system');
INSERT INTO `sys_resource` VALUES (4, 3, 'system.codetype', 'system', '1', '编码类型', 'Code Type', '', 1, 0, '/system/codetype');
INSERT INTO `sys_resource` VALUES (5, 3, 'system.code', 'system', '1', '编码设置', 'Code Rule', '', 2, 0, '/system/code');
INSERT INTO `sys_resource` VALUES (6, 3, 'system.user', 'system', '1', '用户管理', 'User Management', '', 3, 0, '/system/user');
INSERT INTO `sys_resource` VALUES (7, 3, 'system.role', 'system', '1', '角色管理', 'Role Management', '', 4, 0, '/system/role');
INSERT INTO `sys_resource` VALUES (8, 3, 'system.workerCodeRule', 'system', '1', '工号编码规则', 'Worker Code Rule', '', 5, 0, '/system/workerCodeRule');
INSERT INTO `sys_resource` VALUES (9, 4, 'system.codetype.list', 'system.codetype', '2', '查询', 'list page', '', 1, 0, '/system/codetype/list');
INSERT INTO `sys_resource` VALUES (10, 4, 'system.codetype.add', 'system.codetype', '2', '添加', 'add codetype', '', 2, 0, '/system/codetype/add');
INSERT INTO `sys_resource` VALUES (11, 4, 'system.codetype.delete', 'system.codetype', '2', '删除', 'del codetype', '', 3, 0, '/system/codetype/delete');
INSERT INTO `sys_resource` VALUES (12, 4, 'system.codetype.update', 'system.codetype', '2', '修改', 'update codetype', '', 4, 0, '/system/codetype/update');
INSERT INTO `sys_resource` VALUES (13, 5, 'system.code.list', 'system.code', '2', '查询', 'list page', '', 1, 0, '/system/code/list');
INSERT INTO `sys_resource` VALUES (14, 5, 'system.code.add', 'system.code', '2', '添加', 'add code', '', 2, 0, '/system/code/add');
INSERT INTO `sys_resource` VALUES (15, 5, 'system.code.delete', 'system.code', '2', '删除', 'del code ', '', 3, 0, '/system/code/delete');
INSERT INTO `sys_resource` VALUES (16, 5, 'system.code.update', 'system.code', '2', '修改', 'update code', '', 4, 0, '/system/code/update');
INSERT INTO `sys_resource` VALUES (17, 6, 'system.user.list', 'system.user', '2', '查询', 'list page', '', 1, 0, '/system/user/list');
INSERT INTO `sys_resource` VALUES (18, 6, 'system.user.add', 'system.user', '2', '添加', 'add user', '', 2, 0, '/system/user/add');
INSERT INTO `sys_resource` VALUES (19, 6, 'system.user.delete', 'system.user', '2', '删除', 'del user', '', 3, 0, '/system/user/delete');
INSERT INTO `sys_resource` VALUES (20, 6, 'system.user.update', 'system.user', '2', '修改', 'update user', '', 4, 0, '/system/user/update');
INSERT INTO `sys_resource` VALUES (21, 7, 'system.role.list', 'system.role', '2', '查询', 'list page', '', 1, 0, '/system/role/list');
INSERT INTO `sys_resource` VALUES (22, 7, 'system.role.add', 'system.role', '2', '添加', 'add role', '', 2, 0, '/system/role/add');
INSERT INTO `sys_resource` VALUES (23, 7, 'system.role.delete', 'system.role', '2', '删除', 'del role', '', 3, 0, '/system/role/delete');
INSERT INTO `sys_resource` VALUES (24, 7, 'system.role.update', 'system.role', '2', '修改', 'update role', '', 4, 0, '/system/role/update');
INSERT INTO `sys_resource` VALUES (25, 8, 'system.workerCodeRule.list', 'system.workerCodeRule', '2', '查询', 'list page', '', 1, 0, '/system/workerCodeRule/list');
INSERT INTO `sys_resource` VALUES (26, 8, 'system.workerCodeRule.add', 'system.workerCodeRule', '2', '添加', 'add worker code rule', '', 2, 0, '/system/workerCodeRule/add');
INSERT INTO `sys_resource` VALUES (27, 8, 'system.workerCodeRule.delete', 'system.workerCodeRule', '2', '删除', 'del worker code rule', '', 3, 0, '/system/workerCodeRule/delete');
INSERT INTO `sys_resource` VALUES (28, 8, 'system.workerCodeRule.update', 'system.workerCodeRule', '2', '修改', 'update worker code rule', '', 4, 0, '/system/workerCodeRule/update');
INSERT INTO `sys_resource` VALUES (29, 0, 'hr', 'root', '0', '人事总务管理', 'HR Management', 'user', 2, 0, '/hr');
INSERT INTO `sys_resource` VALUES (30, 29, 'staff', 'hr', '1', '人事管理', 'Staff Management', 'user', 1, 0, '/staff');
INSERT INTO `sys_resource` VALUES (31, 29, 'org', 'hr', '1', '组织架构', 'Org', 'fork', 2, 0, '/org');
INSERT INTO `sys_resource` VALUES (32, 29, 'emolument', 'hr', '1', '薪酬管理', 'Emolument Management', 'pay-circle-o', 3, 0, '/emolument');
INSERT INTO `sys_resource` VALUES (33, 30, 'staff.baseInfo', 'staff', '1', '员工信息管理', 'Staff BaseInfo', '', 1, 0, '/staff/baseInfo');
INSERT INTO `sys_resource` VALUES (34, 30, 'staff.contract', 'staff', '1', '合同协议管理', 'Staff Contract', '', 2, 0, '/staff/contract');
INSERT INTO `sys_resource` VALUES (35, 30, 'staff.transaction', 'staff', '1', '员工异动', 'Staff Transaction', '', 3, 0, '/staff/transaction');
INSERT INTO `sys_resource` VALUES (36, 33, 'staff.baseInfo.list', 'staff.baseInfo', '2', '查询', 'list page', '', 1, 0, '/staff/baseInfo/list');
INSERT INTO `sys_resource` VALUES (37, 33, 'staff.baseInfo.add', 'staff.baseInfo', '2', '添加', 'add baseInfo', '', 2, 0, '/staff/baseInfo/add');
INSERT INTO `sys_resource` VALUES (38, 33, 'staff.baseInfo.delete', 'staff.baseInfo', '2', '删除', 'del baseInfo', '', 3, 0, '/staff/baseInfo/delete');
INSERT INTO `sys_resource` VALUES (39, 33, 'staff.baseInfo.update', 'staff.baseInfo', '2', '修改', 'update baseInfo', '', 4, 0, '/staff/baseInfo/update');
INSERT INTO `sys_resource` VALUES (40, 34, 'staff.contract.list', 'staff.contract', '2', '合同查询', 'list page', '', 1, 0, '/staff/contract/list');
INSERT INTO `sys_resource` VALUES (41, 34, 'staff.contract.add', 'staff.contract', '2', '合同添加', 'add contract', '', 2, 0, '/staff/contract/add');
INSERT INTO `sys_resource` VALUES (42, 34, 'staff.contract.delete', 'staff.contract', '2', '合同删除', 'del contract', '', 3, 0, '/staff/contract/delete');
INSERT INTO `sys_resource` VALUES (43, 34, 'staff.contract.update', 'staff.contract', '2', '合同修改', 'update contract', '', 4, 0, '/staff/contract/update');
INSERT INTO `sys_resource` VALUES (44, 35, 'staff.transaction.list', 'staff.transaction', '2', '查询', 'list page', '', 1, 0, '/staff/transaction/list');
INSERT INTO `sys_resource` VALUES (45, 35, 'staff.transaction.moveTransact', 'staff.transaction', '2', '异动办理', 'moveTransact', '', 2, 0, '/staff/transaction/moveTransact');
INSERT INTO `sys_resource` VALUES (46, 35, 'staff.transaction.disposeExpire', 'staff.transaction', '2', '处理到期', 'disposeExpire', '', 3, 0, '/staff/transaction/disposeExpire');
INSERT INTO `sys_resource` VALUES (47, 35, 'staff.transaction.moveQuery', 'staff.transaction', '2', '异动查询', 'moveQuery', '', 4, 0, '/staff/transaction/moveQuery');
INSERT INTO `sys_resource` VALUES (48, 31, 'org.org', 'org', '1', '组织管理', 'Org Management', '', 1, 0, '/org/org');
INSERT INTO `sys_resource` VALUES (49, 31, 'org.positiontype', 'org', '1', '职衔类别', 'Position Type', '', 2, 0, '/org/positiontype');
INSERT INTO `sys_resource` VALUES (50, 31, 'org.rank', 'org', '1', '职级设置', 'Rand', '', 3, 0, '/org/rank');
INSERT INTO `sys_resource` VALUES (51, 31, 'org.grade', 'org', '1', '职等/岗位赋值设置', 'Grade', '', 4, 0, '/org/grade');
INSERT INTO `sys_resource` VALUES (52, 31, 'org.position', 'org', '1', '职衔关系设置', 'Position', '', 5, 0, '/org/position');
INSERT INTO `sys_resource` VALUES (53, 48, 'org.org.list', 'org.org', '2', '查询', 'list page', '', 1, 0, '/org/org/list');
INSERT INTO `sys_resource` VALUES (54, 48, 'org.org.add', 'org.org', '2', '添加', 'add org', '', 2, 0, '/org/org/add');
INSERT INTO `sys_resource` VALUES (55, 48, 'org.org.delete', 'org.org', '2', '删除', 'del org', '', 3, 0, '/org/org/delete');
INSERT INTO `sys_resource` VALUES (56, 48, 'org.org.update', 'org.org', '2', '修改', 'update org', '', 4, 0, '/org/org/update');
INSERT INTO `sys_resource` VALUES (57, 49, 'org.positiontype.list', 'org.positiontype', '2', '查询', 'list page', '', 1, 0, '/org/positiontype/list');
INSERT INTO `sys_resource` VALUES (58, 49, 'org.positiontype.add', 'org.positiontype', '2', '添加', 'add position type', '', 2, 0, '/org/positiontype/add');
INSERT INTO `sys_resource` VALUES (59, 49, 'org.positiontype.delete', 'org.positiontype', '2', '删除', 'del position type', '', 3, 0, '/org/positiontype/delete');
INSERT INTO `sys_resource` VALUES (60, 49, 'org.positiontype.update', 'org.positiontype', '2', '修改', 'update position type', '', 4, 0, '/org/positiontype/update');
INSERT INTO `sys_resource` VALUES (61, 50, 'org.rank.list', 'org.rank', '2', '查询', 'list page', '', 1, 0, '/org/rank/list');
INSERT INTO `sys_resource` VALUES (62, 50, 'org.rank.add', 'org.rank', '2', '添加', 'add rank', '', 2, 0, '/org/rank/add');
INSERT INTO `sys_resource` VALUES (63, 50, 'org.rank.delete', 'org.rank', '2', '删除', 'del rank', '', 3, 0, '/org/rank/delete');
INSERT INTO `sys_resource` VALUES (64, 50, 'org.rank.update', 'org.rank', '2', '修改', 'update rank', '', 4, 0, '/org/rank/update');
INSERT INTO `sys_resource` VALUES (65, 51, 'org.grade.list', 'org.grade', '2', '查询', 'list page', '', 1, 0, '/org/grade/list');
INSERT INTO `sys_resource` VALUES (66, 51, 'org.grade.add', 'org.grade', '2', '添加', 'add grade', '', 2, 0, '/org/grade/add');
INSERT INTO `sys_resource` VALUES (67, 51, 'org.grade.delete', 'org.grade', '2', '删除', 'del grade', '', 3, 0, '/org/grade/delete');
INSERT INTO `sys_resource` VALUES (68, 51, 'org.grade.update', 'org.grade', '2', '修改', 'update grade', '', 4, 0, '/org/grade/update');
INSERT INTO `sys_resource` VALUES (69, 52, 'org.position.list', 'org.position', '2', '查询', 'list page', '', 1, 0, '/org/position/list');
INSERT INTO `sys_resource` VALUES (70, 52, 'org.position.add', 'org.position', '2', '添加', 'add position', '', 2, 0, '/org/position/add');
INSERT INTO `sys_resource` VALUES (71, 52, 'org.position.delete', 'org.position', '2', '删除', 'del position', '', 3, 0, '/org/position/delete');
INSERT INTO `sys_resource` VALUES (72, 52, 'org.position.update', 'org.position', '2', '修改', 'update position', '', 4, 0, '/org/position/update');
INSERT INTO `sys_resource` VALUES (73, 32, 'emolument.eltAccumulationFund', 'emolument', '1', '公积金规则设置', 'Accumulation Fund Setting', '', 1, 0, '/emolument/eltAccumulationFund');
INSERT INTO `sys_resource` VALUES (74, 32, 'emolument.eltSocialSecurity', 'emolument', '1', '社保规则设置', 'Social Security Setting', '', 2, 0, '/emolument/eltSocialSecurity');
INSERT INTO `sys_resource` VALUES (75, 32, 'emolument.eltSalary', 'emolument', '1', '固定工资设置', 'Salary Setting', '', 3, 0, '/emolument/eltSalary');
INSERT INTO `sys_resource` VALUES (76, 32, 'emolument.eltSubsidy', 'emolument', '1', '补贴规则设置', 'Subsidy Setting', '', 4, 0, '/emolument/eltSubsidy');
INSERT INTO `sys_resource` VALUES (77, 32, 'emolument.eltAllowance', 'emolument', '1', '岗位津贴设置', 'Allowance Setting', '', 5, 0, '/emolument/eltAllowance');
INSERT INTO `sys_resource` VALUES (78, 32, 'emolument.achievementsImport', 'emolument', '1', '绩效导入', 'Achievements Import', NULL, 6, 0, '/emolument/achievementsImport');
INSERT INTO `sys_resource` VALUES (79, 32, 'emolument.salaryRules', 'emolument', '1', '薪资规则', 'Salary Rules', NULL, 7, 0, '/emolument/salaryRules');
INSERT INTO `sys_resource` VALUES (80, 73, 'emolument.eltAccumulationFund.list', 'emolument.eltAccumulationFund', '2', '查询', 'list page', '', 1, 0, '/emolument/eltAccumulationFund/list');
INSERT INTO `sys_resource` VALUES (81, 73, 'emolument.eltAccumulationFund.add', 'emolument.eltAccumulationFund', '2', '添加', 'add accumulation', '', 2, 0, '/emolument/eltAccumulationFund/add');
INSERT INTO `sys_resource` VALUES (82, 73, 'emolument.eltAccumulationFund.delete', 'emolument.eltAccumulationFund', '2', '删除', 'del accumulation', '', 3, 0, '/emolument/eltAccumulationFund/delete');
INSERT INTO `sys_resource` VALUES (83, 73, 'emolument.eltAccumulationFund.update', 'emolument.eltAccumulationFund', '2', '修改', 'update accumulation', '', 4, 0, '/emolument/eltAccumulationFund/update');
INSERT INTO `sys_resource` VALUES (84, 74, 'emolument.eltSocialSecurity.list', 'emolument.eltSocialSecurity', '2', '查询', 'list page', '', 1, 0, '/emolument/eltSocialSecurity/list');
INSERT INTO `sys_resource` VALUES (85, 74, 'emolument.eltSocialSecurity.add', 'emolument.eltSocialSecurity', '2', '添加', 'add social security', '', 2, 0, '/emolument/eltSocialSecurity/add');
INSERT INTO `sys_resource` VALUES (86, 74, 'emolument.eltSocialSecurity.delete', 'emolument.eltSocialSecurity', '2', '删除', 'del social security', '', 3, 0, '/emolument/eltSocialSecurity/delete');
INSERT INTO `sys_resource` VALUES (87, 74, 'emolument.eltSocialSecurity.update', 'emolument.eltSocialSecurity', '2', '修改', 'update social security', '', 4, 0, '/emolument/eltSocialSecurity/update');
INSERT INTO `sys_resource` VALUES (88, 75, 'emolument.eltSalary.list', 'emolument.eltSalary', '2', '查询', 'list page', '', 1, 0, '/emolument/eltSalary/list');
INSERT INTO `sys_resource` VALUES (89, 75, 'emolument.eltSalary.add', 'emolument.eltSalary', '2', '添加', 'add salary', '', 2, 0, '/emolument/eltSalary/add');
INSERT INTO `sys_resource` VALUES (90, 75, 'emolument.eltSalary.delete', 'emolument.eltSalary', '2', '删除', 'del salary', '', 3, 0, '/emolument/eltSalary/delete');
INSERT INTO `sys_resource` VALUES (91, 75, 'emolument.eltSalary.update', 'emolument.eltSalary', '2', '修改', 'update salary', '', 4, 0, '/emolument/eltSalary/update');
INSERT INTO `sys_resource` VALUES (92, 76, 'emolument.eltSubsidy.list', 'emolument.eltSubsidy', '2', '查询', 'list page', '', 1, 0, '/emolument/eltSubsidy/list');
INSERT INTO `sys_resource` VALUES (93, 76, 'emolument.eltSubsidy.add', 'emolument.eltSubsidy', '2', '添加', 'add subsidy', '', 2, 0, '/emolument/eltSubsidy/add');
INSERT INTO `sys_resource` VALUES (94, 76, 'emolument.eltSubsidy.delete', 'emolument.eltSubsidy', '2', '删除', 'del subsidy', '', 3, 0, '/emolument/eltSubsidy/delete');
INSERT INTO `sys_resource` VALUES (95, 76, 'emolument.eltSubsidy.update', 'emolument.eltSubsidy', '2', '修改', 'update subsidy', '', 4, 0, '/emolument/eltSubsidy/update');
INSERT INTO `sys_resource` VALUES (96, 77, 'emolument.eltAllowance.list', 'emolument.eltAllowance', '2', '查询', 'list page', '', 1, 0, '/emolument/eltAllowance/list');
INSERT INTO `sys_resource` VALUES (97, 77, 'emolument.eltAllowance.add', 'emolument.eltAllowance', '2', '添加', 'add allowance', '', 2, 0, '/emolument/eltAllowance/add');
INSERT INTO `sys_resource` VALUES (98, 77, 'emolument.eltAllowance.delete', 'emolument.eltAllowance', '2', '删除', 'del allowance', '', 3, 0, '/emolument/eltAllowance/delete');
INSERT INTO `sys_resource` VALUES (99, 77, 'emolument.eltAllowance.update', 'emolument.eltAllowance', '2', '修改', 'update allowance', '', 4, 0, '/emolument/eltAllowance/update');
INSERT INTO `sys_resource` VALUES (100, 78, 'emolument.achievementsImport.list', 'emolument.achievementsImport', '2', '查询', 'list page', NULL, 1, 0, '/emolument/achievementsImport/list');
INSERT INTO `sys_resource` VALUES (101, 78, 'emolument.achievementsImport.add', 'emolument.achievementsImport', '2', '添加', 'add achievements import', NULL, 2, 0, '/emolument/achievementsImport/add');
INSERT INTO `sys_resource` VALUES (102, 78, 'emolument.achievementsImport.delete', 'emolument.achievementsImport', '2', '删除', 'del achievements import', NULL, 3, 0, '/emolument/achievementsImport/delete');
INSERT INTO `sys_resource` VALUES (103, 78, 'emolument.achievementsImport.update', 'emolument.achievementsImport', '2', '修改', 'update achievements import', NULL, 4, 0, '/emolument/achievementsImport/update');
INSERT INTO `sys_resource` VALUES (104, 79, 'emolument.salaryRules.list', 'emolument.salaryRules', '2', '查询', 'list page', NULL, 1, 0, '/emolument/salaryRules/list');
INSERT INTO `sys_resource` VALUES (105, 79, 'emolument.salaryRules.add', 'emolument.salaryRules', '2', '添加', 'add salary rules', NULL, 2, 0, '/emolument/salaryRules/add');
INSERT INTO `sys_resource` VALUES (106, 79, 'emolument.salaryRules.delete', 'emolument.salaryRules', '2', '删除', 'del salary rules', NULL, 3, 0, '/emolument/salaryRules/delete');
INSERT INTO `sys_resource` VALUES (107, 79, 'emolument.salaryRules.update', 'emolument.salaryRules', '2', '修改', 'update salary rules', NULL, 4, 0, '/emolument/salaryRules/update');
INSERT INTO `sys_resource` VALUES (108, 0, 'sale', 'root', '0', '销售管理', 'Sale Manage', '', 3, 0, '/sale');
INSERT INTO `sys_resource` VALUES (109, 108, 'sale.area', 'sale', '1', '区域管理', 'Area Management', '', 1, 0, '/sale/area');
INSERT INTO `sys_resource` VALUES (110, 108, 'sale.customer', 'sale', '1', '客户管理', 'Customer Management', '', 2, 0, '/sale/customer');
INSERT INTO `sys_resource` VALUES (112, 108, 'sale.returnMoney', 'sale', '1', '回款管理', 'ReturnMoney Management', '', 8, 0, '/sale/returnMoney');
INSERT INTO `sys_resource` VALUES (113, 108, 'sale.product', 'sale', '1', '产品管理', 'Product Management', '', 3, 0, '/sale/product');
INSERT INTO `sys_resource` VALUES (114, 108, 'sale.saleContract', 'sale', '1', '合同管理', 'Sale Contract Management', '', 7, 0, '/sale/saleContract');
INSERT INTO `sys_resource` VALUES (115, 109, 'sale.area.list', 'sale.area', '2', '查询', 'list page', '', 1, 0, '/sale/area/list');
INSERT INTO `sys_resource` VALUES (116, 109, 'sale.area.add', 'sale.area', '2', '添加', 'add area', '', 2, 0, '/sale/area/add');
INSERT INTO `sys_resource` VALUES (117, 109, 'sale.area.delete', 'sale.area', '2', '删除', 'del area', '', 3, 0, '/sale/area/del');
INSERT INTO `sys_resource` VALUES (118, 109, 'sale.area.update', 'sale.area', '2', '修改', 'update area', '', 4, 0, '/sale/area/update');
INSERT INTO `sys_resource` VALUES (119, 110, 'sale.customer.list', 'sale.customer', '2', '查询', 'list page', '', 1, 0, '/sale/customer/list');
INSERT INTO `sys_resource` VALUES (120, 110, 'sale.customer.add', 'sale.customer', '2', '添加', 'add customer', '', 2, 0, '/sale/customer/add');
INSERT INTO `sys_resource` VALUES (121, 110, 'sale.customer.delete', 'sale.customer', '2', '删除', 'del customer', '', 3, 0, '/sale/customer/del');
INSERT INTO `sys_resource` VALUES (122, 110, 'sale.customer.update', 'sale.customer', '2', '修改', 'update customer', '', 4, 0, '/sale/customer/update');
INSERT INTO `sys_resource` VALUES (127, 112, 'sale.paymentPlan.list', 'sale.paymentPlan', '2', '查询', 'list page', '', 1, 0, '/sale/paymentPlan/list');
INSERT INTO `sys_resource` VALUES (128, 112, 'sale.paymentPlan.add', 'sale.paymentPlan', '2', '添加', 'add paymentPlan', '', 2, 0, '/sale/paymentPlan/add');
INSERT INTO `sys_resource` VALUES (129, 112, 'sale.paymentPlan.delete', 'sale.paymentPlan', '2', '删除', 'del paymentPlan', '', 3, 0, '/sale/paymentPlan/del');
INSERT INTO `sys_resource` VALUES (130, 112, 'sale.paymentPlan.update', 'sale.paymentPlan', '2', '修改', 'update paymentPlan', '', 4, 0, '/sale/paymentPlan/update');
INSERT INTO `sys_resource` VALUES (131, 113, 'sale.product.list', 'sale.product', '2', '查询', 'list page', '', 1, 0, '/sale/product/list');
INSERT INTO `sys_resource` VALUES (132, 113, 'sale.product.add', 'sale.product', '2', '添加', 'add product', '', 2, 0, '/sale/product/add');
INSERT INTO `sys_resource` VALUES (133, 113, 'sale.product.delete', 'sale.product', '2', '删除', 'del product', '', 3, 0, '/sale/product/delete');
INSERT INTO `sys_resource` VALUES (134, 113, 'sale.product.update', 'sale.product', '2', '修改', 'update product', '', 4, 0, '/sale/product/update');
INSERT INTO `sys_resource` VALUES (135, 114, 'sale.saleContract.list', 'sale.saleContract', '2', '查询', 'Search Sale Contract', '', 1, 0, '/sale/saleContract/list');
INSERT INTO `sys_resource` VALUES (136, 114, 'sale.saleContract.add', 'sale.saleContract', '2', '添加', 'Add Sale Contract', '', 2, 0, '/sale/saleContract/add');
INSERT INTO `sys_resource` VALUES (137, 114, 'sale.saleContract.del', 'sale.saleContract', '2', '删除', 'Delete Sale Contract', '', 3, 0, '/sale/saleContract/del');
INSERT INTO `sys_resource` VALUES (138, 114, 'sale.saleContract.update', 'sale.saleContract', '2', '更新', 'Update Sale Contract', '', 4, 0, '/sale/saleContract/update');
INSERT INTO `sys_resource` VALUES (139, 29, 'attendance', 'hr', '1', '考勤管理', 'Attendance', 'calendar', 4, 0, '/attendance');
INSERT INTO `sys_resource` VALUES (140, 139, 'attendance.attendanceMachine', 'attendance', '1', '考勤机管理', 'Attendance Machine', NULL, 1, 0, '/attendance/attendanceMachine');
INSERT INTO `sys_resource` VALUES (141, 139, 'attendance.scheduling', 'attendance', '1', '排班管理', 'Scheduling', NULL, 2, 0, '/attendance/scheduling');
INSERT INTO `sys_resource` VALUES (142, 139, 'attendance.specialShift', 'attendance', '1', '特殊班次', 'Special Shift', NULL, 3, 0, '/attendance/specialShift');
INSERT INTO `sys_resource` VALUES (143, 139, 'attendance.exemptions', 'attendance', '1', '豁免考勤', 'Exemptions', NULL, 4, 0, '/attendance/exemptions');
INSERT INTO `sys_resource` VALUES (144, 139, 'attendance.holidayType', 'attendance', '1', '假期类型设置', 'Holiday Type', NULL, 5, 0, '/attendance/holidayType');
INSERT INTO `sys_resource` VALUES (145, 139, 'attendance.overtimeSheet', 'attendance', '1', '加班单管理', 'Overtime Sheet', NULL, 6, 0, '/attendance/overtimeSheet');
INSERT INTO `sys_resource` VALUES (146, 139, 'attendance.creditCardLog', 'attendance', '1', '刷卡日志', 'Credit Card Log', NULL, 7, 0, '/attendance/creditCardLog');
INSERT INTO `sys_resource` VALUES (147, 139, 'attendance.personalLeave', 'attendance', '1', '个人请假管理', 'Personal Leave', NULL, 8, 0, '/attendance/personalLeave');
INSERT INTO `sys_resource` VALUES (148, 139, 'attendance.sellOff', 'attendance', '1', '销假管理', 'Sell Off', NULL, 9, 0, '/attendance/sellOff');
INSERT INTO `sys_resource` VALUES (149, 139, 'attendance.businessTravel', 'attendance', '1', '出差管理', 'Business Travel', NULL, 10, 0, '/attendance/businessTravel');
INSERT INTO `sys_resource` VALUES (150, 33, 'staff.baseInfo.export', 'staff.baseInfo', '2', '导出', 'export baseInfo', '', 5, 0, '/staff/baseInfo/export');
INSERT INTO `sys_resource` VALUES (151, 33, 'staff.baseInfo.importInfo', 'staff.baseInfo', '2', '导入员工信息', 'importInfo baseInfo', NULL, 4, 0, '/staff/baseInfo/importInfo');
INSERT INTO `sys_resource` VALUES (152, 48, 'org.org.export', 'org.org', '2', '导出', 'export org', NULL, 5, 0, '/org/org/export');
INSERT INTO `sys_resource` VALUES (153, 48, 'org.org.import', 'org.org', '2', '导入', 'import org', NULL, 6, 0, '/org/org/import');
INSERT INTO `sys_resource` VALUES (154, 48, 'org.org.backups', 'org.org', '2', '备份', 'backups org', NULL, 7, 0, '/org/org/backUpInfo');
INSERT INTO `sys_resource` VALUES (155, 48, 'org.org.history', 'org.org', '2', '查看历史', 'history org', NULL, 8, 0, '/org/org/findOrgHistoryChart');
INSERT INTO `sys_resource` VALUES (156, 52, 'org.position.export', 'org.position', '2', '导出', 'export', NULL, 5, 0, '/org/position/export');
INSERT INTO `sys_resource` VALUES (157, 108, 'sale.stockUp', 'sale', '1', '备货计划', 'SALE STOCK UP', NULL, 9, 0, '/sale/stockUp');
INSERT INTO `sys_resource` VALUES (158, 157, 'sale.stockUp.list', 'sale.stockUp', '2', '查询', 'Search Sale Stock Up', NULL, 1, 0, '/sale/stockUp/list');
INSERT INTO `sys_resource` VALUES (159, 157, 'sale.stockUp.add', 'sale.stockUp', '2', '增加', 'Add Sale Stock Up', NULL, 2, 0, '/sale/stockUp/add');
INSERT INTO `sys_resource` VALUES (160, 157, 'sale.stockUp.del', 'sale.stockUp', '2', '删除', 'Delete Sale Stock Up', NULL, 3, 0, '/sale/stockUp/del');
INSERT INTO `sys_resource` VALUES (161, 157, 'sale.stockUp.update', 'sale.stockUp', '2', '更新', 'Update Sale Stock Up', NULL, 4, 0, '/sale/stockUp/update');
INSERT INTO `sys_resource` VALUES (162, 157, 'sale.stockUp.get', 'sale.stockUp', '2', '查询详情', 'Get Sale Stock Up', NULL, 5, 0, '/sale/stockUp/get');
INSERT INTO `sys_resource` VALUES (166, 34, 'staff.agreement.list', 'staff.contract', '2', '协议查询', 'list page', '', 9, 0, '/staff/agreement/list');
INSERT INTO `sys_resource` VALUES (167, 34, 'staff.agreement.add', 'staff.contract', '2', '协议添加', 'add agreement', '', 10, 0, '/staff/agreement/add');
INSERT INTO `sys_resource` VALUES (168, 34, 'staff.agreement.update', 'staff.contract', '2', '协议修改', 'update agreement', '', 12, 0, '/staff/agreement/update');
INSERT INTO `sys_resource` VALUES (169, 34, 'staff.agreement.delete', 'staff.contract', '2', '协议删除', 'delete agreement', '', 11, 0, '/staff/agreement/delete');
INSERT INTO `sys_resource` VALUES (170, 108, 'sale.inquiries', 'sale', '1', '询单记录单管理', 'search enquiry record history', NULL, 5, 0, '/sale/inquiries');
INSERT INTO `sys_resource` VALUES (172, 113, 'sale.product.get', 'sale.product', '2', '查询明细', 'get product', NULL, 5, 0, '/sale/product/get');
INSERT INTO `sys_resource` VALUES (173, 108, 'sale.inquiriesAppraisal', 'sale', '1', '询单评审记录单管理', 'search appraisal record', NULL, 6, 0, '/sale/inquiriesAppraisal');
INSERT INTO `sys_resource` VALUES (174, 109, 'sale.area.get', 'sale.area', '2', '查询明细', 'get area', NULL, 5, 0, '/sale/area/get');
INSERT INTO `sys_resource` VALUES (175, 109, 'sale.area.getAllArea', 'sale.area', '2', '查询所有区域', 'get all area', NULL, 6, 0, '/sale/area/getAllArea');
INSERT INTO `sys_resource` VALUES (176, 109, 'sale.area.detailList', 'sale.area', '2', '明细列表查询', 'get all detailList', NULL, 7, 0, '/sale/area/detailList');
INSERT INTO `sys_resource` VALUES (177, 109, 'sale.area.importExcel', 'sale.area', '2', '导入', 'import', NULL, 8, 0, '/sale/area/importExcel');
INSERT INTO `sys_resource` VALUES (178, 109, 'sale.area.download', 'sale.area', '2', '下载', 'download', NULL, 9, 0, '/sale/area/download');
INSERT INTO `sys_resource` VALUES (179, 110, 'sale.customer.get', 'sale.customer', '2', '明细', 'get customer', NULL, 5, 0, '/sale/customer/getCustomerAndContacts');
INSERT INTO `sys_resource` VALUES (180, 110, 'sale.customer.getAllCustomer', 'sale.customer', '2', '查询所有客户', 'get getAllCustomer', NULL, 6, 0, '/sale/customer/getAllCustomer');
INSERT INTO `sys_resource` VALUES (181, 110, 'sale.customer.importExcel', 'sale.customer', '2', '导入', 'importExcel', NULL, 7, 0, '/sale/customer/importExcel');
INSERT INTO `sys_resource` VALUES (182, 112, 'sale.paymentPlan.get', 'sale.paymentPlan', '2', '明细', 'get paymentPlan', NULL, 5, 0, '/sale/paymentPlan/get');
INSERT INTO `sys_resource` VALUES (183, 112, 'sale.paymentPlan.getUpdatePeriod', 'sale.paymentPlan', '2', '修改-获取期次和金额', 'get getPeriod', NULL, 6, 0, '/sale/paymentPlan/getUpdatePeriod');
INSERT INTO `sys_resource` VALUES (184, 112, 'sale.paymentDetail.list', 'sale.paymentDetail', '2', '回款记录查询', 'list', NULL, 7, 0, '/sale/paymentDetail/list');
INSERT INTO `sys_resource` VALUES (185, 112, 'sale.paymentDetail.add', 'sale.paymentDetail', '2', '增加回款记录', 'add', NULL, 8, 0, '/sale/paymentDetail/add');
INSERT INTO `sys_resource` VALUES (186, 112, 'sale.paymentDetail.del', 'sale.paymentDetail', '2', '删除回款记录', 'delete', NULL, 9, 0, '/sale/paymentDetail/del');
INSERT INTO `sys_resource` VALUES (187, 112, 'sale.paymentDetail.get', 'sale.paymentDetail', '2', '获取回款详情', 'get', NULL, 10, 0, '/sale/paymentDetail/get');
INSERT INTO `sys_resource` VALUES (188, 112, 'sale.paymentDetail.update', 'sale.paymentDetail', '2', '更新回款记录', 'update', NULL, 11, 0, '/sale/paymentDetail/update');
INSERT INTO `sys_resource` VALUES (189, 112, 'sale.paymentDetail.getPaymentDetail', 'sale.paymentDetail', '2', '查询回款记录', 'getPaymentDetail', NULL, 12, 0, '/sale/paymentDetail/getPaymentDetail');
INSERT INTO `sys_resource` VALUES (190, 113, 'sale.product.getAll', 'sale.product', '2', '查询所有产品', 'get all product', NULL, 6, 0, '/sale/product/getAll');
INSERT INTO `sys_resource` VALUES (191, 113, 'sale.product.importExcel', 'sale.product', '2', '导入', 'importExcel', NULL, 7, 0, '/sale/product/importExcel');
INSERT INTO `sys_resource` VALUES (192, 113, 'sale.product.exportExcel', 'sale.product', '2', '导出', 'exportExcel', NULL, 8, 0, '/sale/product/exportExcel');
INSERT INTO `sys_resource` VALUES (193, 113, 'sale.product.updateStatus', 'sale.product', '2', '更新状态', 'updateStatus', NULL, 9, 0, '/sale/product/updateStatus');
INSERT INTO `sys_resource` VALUES (194, 113, 'sale.product.download', 'sale.product', '2', '下载', 'download', NULL, 10, 0, '/sale/product/download');
INSERT INTO `sys_resource` VALUES (195, 114, 'sale.saleContract.get', 'sale.saleContract', '2', '明细', 'Get sale Contract', NULL, 5, 0, '/sale/saleContract/get');
INSERT INTO `sys_resource` VALUES (196, 114, 'sale.saleContract.getReturnMoney', 'sale.saleContract', '2', '合同下查询回款计划', 'get return money', NULL, 6, 0, '/sale/saleContract/getReturnMoney');
INSERT INTO `sys_resource` VALUES (197, 114, 'sale.saleContract.getInvoice', 'sale.saleContract', '2', '查询发票信息', 'get invoice', NULL, 7, 0, '/sale/saleContract/getInvoice');
INSERT INTO `sys_resource` VALUES (198, 114, 'sale.saleContract.getDeliveryPlan', 'sale.saleContract', '2', '查询备货计划', 'getDeliveryPlan', NULL, 8, 0, '/sale/saleContract/getDeliveryPlan');
INSERT INTO `sys_resource` VALUES (199, 114, 'sale.saleContract.getTrueDelivery', 'sale.saleContract', '2', '查询实际出货计划', 'getTrueDelivery', NULL, 9, 0, '/sale/saleContract/getTrueDelivery');
INSERT INTO `sys_resource` VALUES (200, 114, 'sale.saleContract.getLog', 'sale.saleContract', '2', '查询操作日志', 'getLog', NULL, 10, 0, '/sale/saleContract/getLog');
INSERT INTO `sys_resource` VALUES (201, 114, 'sale.saleContract.addInvoice', 'sale.saleContract', '2', '增加发票', 'addInvoice', NULL, 11, 0, '/sale/saleContract/addInvoice');
INSERT INTO `sys_resource` VALUES (202, 114, 'sale.saleContract.delInvoice', 'sale.saleContract', '2', '删除发票', 'delInvoice', NULL, 12, 0, '/sale/saleContract/delInvoice');
INSERT INTO `sys_resource` VALUES (203, 114, 'sale.saleContract.getSingleInvoice', 'sale.saleContract', '2', '明细', 'getSingleInvoice', NULL, 13, 0, '/sale/saleContract/getSingleInvoice');
INSERT INTO `sys_resource` VALUES (204, 114, 'sale.saleContract.updateInvoice', 'sale.saleContract', '2', '更新', 'updateInvoice', NULL, 14, 0, '/sale/saleContract/updateInvoice');
INSERT INTO `sys_resource` VALUES (205, 114, 'sale.saleContract.getSaleContractByCustomer', 'sale.saleContract', '2', '根据合同查询客户', 'getSaleContractByCustomer', NULL, 15, 0, '/sale/saleContract/getSaleContractByCustomer');
INSERT INTO `sys_resource` VALUES (206, 170, 'sale.inquiries.list', 'sale.inquiries', '2', '查询', 'search', NULL, 1, 0, '/sale/inquiries/list');
INSERT INTO `sys_resource` VALUES (207, 170, 'sale.inquiries.add', 'sale.inquiries', '2', '增加', 'add', NULL, 2, 0, '/sale/inquiries/add');
INSERT INTO `sys_resource` VALUES (208, 170, 'sale.inquiries.update', 'sale.inquiries', '2', '更新', 'update', NULL, 3, 0, '/sale/inquiries/update');
INSERT INTO `sys_resource` VALUES (209, 170, 'sale.inquiries.get', 'sale.inquiries', '2', '明细', 'get', NULL, 4, 0, '/sale/inquiries/get');
INSERT INTO `sys_resource` VALUES (210, 170, 'sale.inquiries.delete', 'sale.inquiries', '2', '删除', 'delete', NULL, 5, 0, '/sale/inquiries/delete');
INSERT INTO `sys_resource` VALUES (211, 170, 'sale.inquiries.listInquiriesHistory', 'sale.inquiries', '2', '查询询单历史记录', 'listInquiriesHistory', NULL, 6, 0, '/sale/inquiries/listInquiriesHistory');
INSERT INTO `sys_resource` VALUES (212, 170, 'sale.inquiries.confirmInquiries', 'sale.inquiries', '2', '确认', 'confirmInquiries', NULL, 7, 0, '/sale/inquiries/confirmInquiries');
INSERT INTO `sys_resource` VALUES (213, 170, 'sale.inquiries.transferContract', 'sale.inquiries', '2', '转合同', 'transferContract', NULL, 8, 0, '/sale/inquiries/transferContract');
INSERT INTO `sys_resource` VALUES (214, 173, 'sale.inquiriesAppraisal.list', 'sale.inquiriesAppraisal', '2', '查询', 'search', NULL, 1, 0, '/sale/inquiriesAppraisal/list');
INSERT INTO `sys_resource` VALUES (215, 173, 'sale.inquiriesAppraisal.get', 'sale.inquiriesAppraisal', '2', '明细', 'get', NULL, 2, 0, '/sale/inquiriesAppraisal/get');
INSERT INTO `sys_resource` VALUES (216, 173, 'sale.inquiriesAppraisal.listInquiriesAppraisalHistory', 'sale.inquiriesAppraisal', '2', '询单评审历史记录', 'search history', NULL, 3, 0, '/sale/inquiriesAppraisal/listInquiriesAppraisalHistory');
INSERT INTO `sys_resource` VALUES (217, 173, 'sale.inquiriesAppraisal.confirmInquiriesAppraisal', 'sale.inquiriesAppraisal', '2', '确认评审记录', 'confirm', NULL, 4, 0, '/sale/inquiriesAppraisal/confirmInquiriesAppraisal');
INSERT INTO `sys_resource` VALUES (218, 173, 'sale.inquiriesAppraisal.appraisalTransferContract', 'sale.inquiriesAppraisal', '2', '转合同', 'appraisalTransferContract', NULL, 5, 0, '/sale/inquiriesAppraisal/appraisalTransferContract');
INSERT INTO `sys_resource` VALUES (219, 110, 'sale.customer.download', 'sale.customer', '2', '下载', 'download', NULL, 8, 0, '/sale/customer/download');
INSERT INTO `sys_resource` VALUES (220, 110, 'sale.customer.getSalePersonList', 'sale.customer', '2', '查询销售部员工列表', 'getSalePersonList', NULL, 9, 0, '/sale/customer/getSalePersonList');
INSERT INTO `sys_resource` VALUES (221, 110, 'sale.customer.getCustomerList', 'sale.customer', '2', '查询员工下所有的客户', 'getCustomerList', NULL, 10, 0, '/sale/customer/getCustomerList');
INSERT INTO `sys_resource` VALUES (222, 110, 'sale.customer.updateTransferMan', 'sale.customer', '2', '更新转移人id', 'updateTransferMan', NULL, 11, 0, '/sale/customer/updateTransferMan');
INSERT INTO `sys_resource` VALUES (224, 5, 'system.code.enabled', 'system.code', '2', '禁用启用', 'Disable enabled', NULL, 5, 0, '/system/code/enabled');
INSERT INTO `sys_resource` VALUES (225, 170, 'sale.inquiries.inquiriesTurnAppraisal', 'sale', '2', '询单转评审', 'search enquiry transferContract', NULL, 10, 0, '/sale/inquiries/inquiriesTurnAppraisal');
INSERT INTO `sys_resource` VALUES (226, 170, 'sale.inquiries.supplementInquiries', 'sale', '2', '询单信息补齐', 'search enquiry supplementInquiries', NULL, 11, 0, '/sale/inquiries/supplementInquiries');
INSERT INTO `sys_resource` VALUES (227, 173, 'sale.inquiriesAppraisal.confirmInquiriesAppraisal', 'sale', '2', '评审通过或驳回操作', 'enquiry appraisal option', NULL, 7, 0, '/sale/inquiries/confirmInquiriesAppraisal');
INSERT INTO `sys_resource` VALUES (228, 6, 'system.user.enabled', 'system.user', '2', '禁用启用', 'Disable enabled', NULL, 5, 0, '/system/user/enabled');
INSERT INTO `sys_resource` VALUES (229, 6, 'system.user.authority', 'system.user', '2', '操作权限', 'authority', NULL, 6, 0, '/system/user/authority');
INSERT INTO `sys_resource` VALUES (230, 6, 'system.user.role', 'system.user', '2', '角色', 'role', NULL, 7, 0, '/system/user/role');
INSERT INTO `sys_resource` VALUES (231, 7, 'system.role.authority', 'system.role', '2', '操作权限', 'authority', NULL, 5, 0, '/system/role/authority');
INSERT INTO `sys_resource` VALUES (232, 7, 'system.role.enabled', 'system.role', '2', '禁用启用', 'Disable enabled', NULL, 6, 0, '/system/role/role');
INSERT INTO `sys_resource` VALUES (233, 112, 'sale.paymentPlan.getAddPeriod', 'sale.paymentPlan', '2', '增加-获取期次和金额', 'get getPeriod', NULL, 13, 0, '/sale/paymentPlan/getAddPeriod');
INSERT INTO `sys_resource` VALUES (234, 114, 'sale.saleContract.importExcel', 'sale.saleContract', '2', '导入', 'importExcel', NULL, 16, 0, '/sale/saleContract/importExcel');
INSERT INTO `sys_resource` VALUES (235, 114, 'sale.saleContract.download', 'sale.saleContract', '2', '下载', 'download', NULL, 17, 0, '/sale/saleContract/download');
INSERT INTO `sys_resource` VALUES (236, 2, 'home.my.show', 'home.my', '2', '我的首页查看', 'My Notice Show', NULL, 1, 0, '/home/my/show');
INSERT INTO `sys_resource` VALUES (237, 48, 'org.org.moveAndMerge', 'org.org', '2', '移动和合并', 'Move And Merge', NULL, 10, 0, '/org/org/moveAndMerge');
INSERT INTO `sys_resource` VALUES (238, 48, 'org.org.downloadTemple', 'org.org', '2', '下载导入模板', 'Download Temple', NULL, 11, 0, '/org/org/downloadTemple');
INSERT INTO `sys_resource` VALUES (239, 114, 'sale.saleContract.updateTransferMan', 'sale.saleContract', '2', '转移合同', 'Transfer', NULL, 18, 0, '/sale/saleContract/updateTransferMan');
INSERT INTO `sys_resource` VALUES (240, 30, 'staff.transactionQuery', 'staff', '1', '员工异动查询', 'Staff Transaction Query', '', 4, 0, '/staff/transactionQuery');
INSERT INTO `sys_resource` VALUES (242, 7, 'system.role.authorityData', 'system.role', '2', '数据权限', 'authority Data', NULL, 7, 0, '/system/role/suthorityData');
INSERT INTO `sys_resource` VALUES (243, 240, 'staff.transactionQuery.INNER_MOBILIZATION', 'staff.transactionQuery', '2', '内部调动查询', 'INNER MOBILIZATION', NULL, 1, 0, '/staff/transactionQuery/INNER_MOBILIZATION');
INSERT INTO `sys_resource` VALUES (244, 240, 'staff.transactionQuery.TEMPORARILY', 'staff.transactionQuery', '2', '借调查询', 'TEMPORARILY', NULL, 2, 0, '/staff/transactionQuery/TEMPORARILY');
INSERT INTO `sys_resource` VALUES (245, 240, 'staff.transactionQuery.EXPATRIATE', 'staff.transactionQuery', '2', '外派查询', 'EXPATRIATE', NULL, 3, 0, '/staff/transactionQuery/EXPATRIATE');
INSERT INTO `sys_resource` VALUES (246, 240, 'staff.transactionQuery.RETIRE', 'staff.transactionQuery', '2', '退休查询', 'RETIRE', NULL, 4, 0, '/staff/transactionQuery/RETIRE');
INSERT INTO `sys_resource` VALUES (247, 240, 'staff.transactionQuery.DIMISSION', 'staff.transactionQuery', '2', '离职查询', 'DIMISSION', NULL, 5, 0, '/staff/transactionQuery/DIMISSION');
INSERT INTO `sys_resource` VALUES (248, 240, 'staff.transactionQuery.REHIRE', 'staff.transactionQuery', '2', '重新雇佣查询', 'REHIRE', NULL, 6, 0, '/staff/transactionQuery/REHIRE');
INSERT INTO `sys_resource` VALUES (249, 240, 'staff.transactionQuery.RETURN_REHIRE', 'staff.transactionQuery', '2', '返聘查询', 'RETURN_REHIRE', NULL, 1, 0, '/staff/transactionQuery/RETURN_REHIRE');
INSERT INTO `sys_resource` VALUES (250, 139, 'attendance.record', 'attendance', '1', '考勤查询', 'record', NULL, 11, 0, '/attendance/record');
INSERT INTO `sys_resource` VALUES (251, 34, 'staff.contract.renew', 'staff.contract', '2', '合同续签', 'renew', NULL, 5, 0, '/staff/contract/renew');
INSERT INTO `sys_resource` VALUES (252, 34, 'staff.contract.termination', 'staff.contract', '2', '合同终止', 'termination', NULL, 6, 0, '/staff/contract/termination');
INSERT INTO `sys_resource` VALUES (253, 34, 'staff.contract.suspend', 'staff.contract', '2', '合同中止', 'suspend', NULL, 7, 0, '/staff/contract/suspend');
INSERT INTO `sys_resource` VALUES (254, 34, 'staff.contract.remove', 'staff.contract', '2', '合同解除', 'remove', NULL, 8, 0, '/staff/contract/remove');
INSERT INTO `sys_resource` VALUES (255, 34, 'staff.agreement.renew', 'staff.contract', '2', '协议续签', 'renew', NULL, 13, 0, '/staff/agreement/renew');
INSERT INTO `sys_resource` VALUES (256, 34, 'staff.agreement.termination', 'staff.contract', '2', '协议终止', 'termination', NULL, 14, 0, '/staff/agreement/termination');
INSERT INTO `sys_resource` VALUES (257, 34, 'staff.agreement.suspend', 'staff.contract', '2', '协议中止', 'suspend', NULL, 15, 0, '/staff/agreement/suspend');
INSERT INTO `sys_resource` VALUES (258, 34, 'staff.agreement.remove', 'staff.contract', '2', '协议解除', 'remove', NULL, 16, 0, '/staff/agreement/remove');
INSERT INTO `sys_resource` VALUES (259, 140, 'attendance.attendanceMachine.list', 'attendance.attendanceMachine', '2', '查看', 'list', NULL, 1, 0, '/attendance/attendanceMachine/list');
INSERT INTO `sys_resource` VALUES (260, 140, 'attendance.attendanceMachine.update', 'attendance.attendanceMachine', '2', '修改', 'update', NULL, 2, 0, '/attendance/attendanceMachine/update');
INSERT INTO `sys_resource` VALUES (261, 140, 'attendance.attendanceMachine.add', 'attendance.attendanceMachine', '2', '添加', 'add', NULL, 3, 0, '/attendance/attendanceMachine/add');
INSERT INTO `sys_resource` VALUES (262, 140, 'attendance.attendanceMachine.delete', 'attendance.attendanceMachine', '2', '删除', 'delete', NULL, 4, 0, '/attendance/attendanceMachine/delete');
INSERT INTO `sys_resource` VALUES (263, 141, 'attendance.scheduling.list', 'attendance.scheduling', '2', '查看', 'list', NULL, 1, 0, '/attendance/scheduling/list');
INSERT INTO `sys_resource` VALUES (264, 141, 'attendance.scheduling.update', 'attendance.scheduling', '2', '修改', 'update', NULL, 2, 0, '/attendance/scheduling/update');
INSERT INTO `sys_resource` VALUES (265, 141, 'attendance.scheduling.add', 'attendance.scheduling', '2', '添加', 'add', NULL, 3, 0, '/attendance/scheduling/add');
INSERT INTO `sys_resource` VALUES (266, 141, 'attendance.scheduling.delete', 'attendance.scheduling', '2', '删除', 'delete', NULL, 4, 0, '/attendance/scheduling/delete');
INSERT INTO `sys_resource` VALUES (267, 142, 'attendance.specialShift.list', 'attendance.specialShift', '2', '查看', 'list', NULL, 1, 0, '/attendance/specialShift/list');
INSERT INTO `sys_resource` VALUES (268, 142, 'attendance.specialShift.update', 'attendance.specialShift', '2', '修改', 'update', NULL, 2, 0, '/attendance/specialShift/update');
INSERT INTO `sys_resource` VALUES (269, 142, 'attendance.specialShift.add', 'attendance.specialShift', '2', '添加', 'add', NULL, 3, 0, '/attendance/specialShift/add');
INSERT INTO `sys_resource` VALUES (270, 142, 'attendance.specialShift.delete', 'attendance.specialShift', '2', '删除', 'delete', NULL, 4, 0, '/attendance/specialShift/delete');
INSERT INTO `sys_resource` VALUES (271, 143, 'attendance.exemptions.list', 'attendance.exemptions', '2', '查看', 'list', NULL, 1, 0, '/attendance/exemptions/list');
INSERT INTO `sys_resource` VALUES (273, 143, 'attendance.exemptions.add', 'attendance.exemptions', '2', '添加', 'add', NULL, 3, 0, '/attendance/exemptions/add');
INSERT INTO `sys_resource` VALUES (274, 143, 'attendance.exemptions.delete', 'attendance.exemptions', '2', '删除', 'delete', NULL, 4, 0, '/attendance/exemptions/delete');
INSERT INTO `sys_resource` VALUES (275, 144, 'attendance.holidayType.list', 'attendance.holidayType', '2', '查看', 'list', NULL, 1, 0, '/attendance/holidayType/list');
INSERT INTO `sys_resource` VALUES (276, 144, 'attendance.holidayType.update', 'attendance.holidayType', '2', '修改', 'update', NULL, 2, 0, '/attendance/holidayType/update');
INSERT INTO `sys_resource` VALUES (277, 144, 'attendance.holidayType.add', 'attendance.holidayType', '2', '添加', 'add', NULL, 3, 0, '/attendance/holidayType/add');
INSERT INTO `sys_resource` VALUES (278, 144, 'attendance.holidayType.delete', 'attendance.holidayType', '2', '删除', 'delete', NULL, 4, 0, '/attendance/holidayType/delete');
INSERT INTO `sys_resource` VALUES (279, 145, 'attendance.overtimeSheet.list', 'attendance.overtimeSheet', '2', '查看', 'list', NULL, 1, 0, '/attendance/overtimeSheet/list');
INSERT INTO `sys_resource` VALUES (280, 145, 'attendance.overtimeSheet.update', 'attendance.overtimeSheet', '2', '修改', 'update', NULL, 2, 0, '/attendance/overtimeSheet/update');
INSERT INTO `sys_resource` VALUES (281, 145, 'attendance.overtimeSheet.add', 'attendance.overtimeSheet', '2', '添加', 'add', NULL, 3, 0, '/attendance/overtimeSheet/add');
INSERT INTO `sys_resource` VALUES (282, 145, 'attendance.overtimeSheet.delete', 'attendance.overtimeSheet', '2', '删除', 'delete', NULL, 4, 0, '/attendance/overtimeSheet/delete');
INSERT INTO `sys_resource` VALUES (283, 146, 'attendance.creditCardLog.list', 'attendance.creditCardLog', '2', '查看', 'list', NULL, 1, 0, '/attendance/creditCardLog/list');
INSERT INTO `sys_resource` VALUES (284, 146, 'attendance.creditCardLog.import', 'attendance.creditCardLog', '2', '导入', 'import', NULL, 2, 0, '/attendance/creditCardLog/import');
INSERT INTO `sys_resource` VALUES (285, 147, 'attendance.personalLeave.list', 'attendance.personalLeave', '2', '查看', 'list', NULL, 1, 0, '/attendance/personalLeave/list');
INSERT INTO `sys_resource` VALUES (286, 147, 'attendance.personalLeave.update', 'attendance.personalLeave', '2', '修改', 'update', NULL, 2, 0, '/attendance/personalLeave/update');
INSERT INTO `sys_resource` VALUES (287, 147, 'attendance.personalLeave.add', 'attendance.personalLeave', '2', '添加', 'add', NULL, 3, 0, '/attendance/personalLeave/add');
INSERT INTO `sys_resource` VALUES (288, 147, 'attendance.personalLeave.delete', 'attendance.personalLeave', '2', '删除', 'delete', NULL, 4, 0, '/attendance/personalLeave/delete');
INSERT INTO `sys_resource` VALUES (289, 148, 'attendance.sellOff.list', 'attendance.sellOff', '2', '未销假查询', 'list', NULL, 2, 0, '/attendance/sellOff/list');
INSERT INTO `sys_resource` VALUES (290, 148, 'attendance.sellOff.list2', 'attendance.sellOff', '2', '已销假查询', 'list', NULL, 1, 0, '/attendance/.sellOff/list2');
INSERT INTO `sys_resource` VALUES (291, 148, 'attendance.sellOff.sellOff', 'attendance.sellOff', '2', '销假操作', 'sell off', NULL, 3, 0, '/attendance/sellOff/sellOff');
INSERT INTO `sys_resource` VALUES (292, 149, 'attendance.businessTravel.list', 'attendance.businessTravel', '2', '查看', 'list', NULL, 1, 0, '/attendance/businessTravel/list');
INSERT INTO `sys_resource` VALUES (293, 149, 'attendance.businessTravel.update', 'attendance.businessTravel', '2', '修改', 'update', NULL, 2, 0, '/attendance/businessTravel/update');
INSERT INTO `sys_resource` VALUES (294, 149, 'attendance.businessTravel.add', 'attendance.businessTravel', '2', '添加', 'add', NULL, 3, 0, '/attendance/businessTravel/add');
INSERT INTO `sys_resource` VALUES (295, 149, 'attendance.businessTravel.delete', 'attendance.businessTravel', '2', '删除', 'delete', NULL, 4, 0, '/attendance/businessTravel/delete');
INSERT INTO `sys_resource` VALUES (296, 250, 'attendance.record.list', 'attendance.record', '2', '查看', 'list', NULL, 1, 0, '/attendance/record/list');
INSERT INTO `sys_resource` VALUES (297, 33, 'staff.baseInfo.importPosition', 'staff.baseInfo', '2', '导入员工职衔', 'importPosition baseInfo', '', 4, 0, '/staff/baseInfo/importPosition');
INSERT INTO `sys_resource` VALUES (300, 35, 'staff.transaction.export', 'staff.transaction', '2', '导出', 'export transaction', '', 1, 0, '/staff/transaction/export');
INSERT INTO `sys_resource` VALUES (301, 35, 'staff.transaction.import', 'staff.transaction', '2', '导入', 'import transaction', '', 2, 0, '/staff/transaction/import');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(255) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 0, '基本角色', '基本角色，不可编辑', 0);
INSERT INTO `sys_role` VALUES (2, 0, '管理员', '最大权限', 0);
INSERT INTO `sys_role` VALUES (3, 0, '1', '1', 1);
INSERT INTO `sys_role` VALUES (4, 0, '人事部', NULL, 1);
INSERT INTO `sys_role` VALUES (5, 0, '阿斯顿发23', '23', 1);
INSERT INTO `sys_role` VALUES (6, 0, '23234123人文氛围二十多分23234123人文氛围二十多分23234123人文氛围二十多分23234123人文23234123人文氛围二十多分23234123人文氛围二十多分23234123人文氛围二十多分23234123人文氛围二十23234123人文氛围二十多分23234123人文氛围二十多分23234123人文氛围二十多分23234123人文氛围二十多分23234123人文氛围二十多分多分二十多分', NULL, 1);
INSERT INTO `sys_role` VALUES (7, 0, '薪酬薪酬薪薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬', '薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬薪酬', 1);
INSERT INTO `sys_role` VALUES (8, 0, '1', '1', 1);
INSERT INTO `sys_role` VALUES (9, 0, '1', NULL, 1);
INSERT INTO `sys_role` VALUES (10, 0, '1', NULL, 1);
INSERT INTO `sys_role` VALUES (11, 0, '1', NULL, 1);
INSERT INTO `sys_role` VALUES (12, 0, '测试', NULL, 0);
INSERT INTO `sys_role` VALUES (13, 0, '22', NULL, 1);
INSERT INTO `sys_role` VALUES (14, 0, '操作', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `row_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT '21218cca77804d2ba1922c33e0151105',
  `category` int(11) DEFAULT '1' COMMENT '用户类型',
  `cellphone_no` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件地址',
  `photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `status` int(11) DEFAULT '0',
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`row_id`,`user_name`) USING BTREE,
  UNIQUE KEY `unique_user_name` (`user_name`) USING BTREE COMMENT 'username唯一约束'
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '21218cca77804d2ba1922c33e0151105', 0, '138888888', NULL, 'http://www.iconpng.com/png/realvista/robot.png', 0, 0);
INSERT INTO `sys_user` VALUES (2, 'S0001', '21218cca77804d2ba1922c33e0151105', 1, '180150548', '', 'group1/M00/00/02/rB0PK1uYjFKAPsP7AAuU7swk5u0635.jpg', 0, 0);
INSERT INTO `sys_user` VALUES (4, 'S0003', '21218cca77804d2ba1922c33e0151105', NULL, '13333333336', '', 'group1/M00/00/01/rB0PK1uWJKqATKuYAACfaYpjY64563.jpg', 0, 0);
INSERT INTO `sys_user` VALUES (5, 'jlyuan', NULL, NULL, '18795957540', '24354325', '', 0, 1);
INSERT INTO `sys_user` VALUES (6, 'asdf', NULL, NULL, 'adsfsadfasdf', NULL, '', 0, 1);
INSERT INTO `sys_user` VALUES (7, 'S0014', '21218cca77804d2ba1922c33e0151105', NULL, '18015054847', 'shigui.yu@champlink-info.com', '', 0, 0);
INSERT INTO `sys_user` VALUES (9, 'S0004', '21218cca77804d2ba1922c33e0151105', NULL, '13333333333', 'test@test.com', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (10, 'S0005', '21218cca77804d2ba1922c33e0151105', NULL, '13333333333', 'test@test.com', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (11, 'S0006', '21218cca77804d2ba1922c33e0151105', NULL, '13333333333', 'test@test.com', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (111, 'S0007', '21218cca77804d2ba1922c33e0151105', NULL, '13333333333', 'test@test.com', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (113, 'S0008', '21218cca77804d2ba1922c33e0151105', NULL, '13333333333', 'test@test.com', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (114, 'S0009', '21218cca77804d2ba1922c33e0151105', NULL, '13333333333', 'test@test.com', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (117, 'S0010', '21218cca77804d2ba1922c33e0151105', NULL, '111', '111@', NULL, 0, 0);
INSERT INTO `sys_user` VALUES (118, 'S0011', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (121, 'S0012', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (122, 'S0013', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (123, 'S0015', '21218cca77804d2ba1922c33e0151105', NULL, '18761626864', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (124, 'S1000', '21218cca77804d2ba1922c33e0151105', NULL, '13333333', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (125, '123456789123456789', '21218cca77804d2ba1922c33e0151105', NULL, '1234', NULL, 'group1/M00/00/00/rB0PK1tqoRaAEPYGAACfaYpjY64901.jpg', 1, 0);
INSERT INTO `sys_user` VALUES (126, '123456789123456789123456789', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (129, 'S0002', '21218cca77804d2ba1922c33e0151105', NULL, '13706128502', NULL, '', 0, 1);
INSERT INTO `sys_user` VALUES (130, 'ASZ00021', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `sys_user` VALUES (131, 'BJJD000025', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (132, 'ASZ00023', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (133, 'ASZ00024', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (134, 'AS0006', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (135, 'ASZ00026', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (136, 'ASZ00027', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (137, 'BJJD00001', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (142, 'BJJD00002', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (143, 'BJJD00003', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (145, 'BJJD00004', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (146, 'BJJD000001', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (147, 'BJJD000002', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (148, 'BJJD000003', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (149, 'BJJD000004', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (150, 'BJJD000005', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (151, 'BJJD000006', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (152, 'BJJD000007', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (153, 'YU003', '21218cca77804d2ba1922c33e0151105', NULL, '12', NULL, 'group1/M00/00/00/rB0PK1tzfKqAUtttAAdmTXb2UJI603.png', 0, 0);
INSERT INTO `sys_user` VALUES (154, 'YU005', 'e10adc3949ba59abbe56e057f20f883e', NULL, '123456', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (155, 'BJJD000008', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (156, 'S0111', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (160, 'CP00000001', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (161, 'ja12345678', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (162, 'ja12345679', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (163, 'ja12345680', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (166, 'jinlong', 'c41ed9cf04c12c6cf8b60ce24fbc3ef0', NULL, '18600416069', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (167, 'BJJD000009', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (168, 'BJJD000010', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (169, 'BJJD000011', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (172, 'BJJD000012', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (173, 'CP00000002', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (174, 'BJJD000013', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (176, 'test1', '21218cca77804d2ba1922c33e0151105', NULL, '12345', NULL, '', 0, 1);
INSERT INTO `sys_user` VALUES (177, 'BJJD000014', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (178, 'BJJD000015', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (179, 'BJJD000020', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (181, 'DH3241', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (182, 'DH0574', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `sys_user` VALUES (183, 'DH0180', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (187, 'test', '21218cca77804d2ba1922c33e0151105', NULL, '1', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (188, '1', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (189, '111', '698d51a19d8a121ce581499d7b701668', NULL, '11111', '123', '', 0, 0);
INSERT INTO `sys_user` VALUES (190, 'CP00000003', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (191, 'S00001', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (192, 'S1212', '21218cca77804d2ba1922c33e0151105', NULL, '13333333337', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (193, 'ASZ00018', '21218cca77804d2ba1922c33e0151105', NULL, '158158', NULL, '', 0, 0);
INSERT INTO `sys_user` VALUES (194, 'BJJD000016', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (195, 'BJJD000017', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (196, 'BJJD000018', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (197, 'BJJD000019', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (198, 'BJJD000022', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (209, 'BJJD000026', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (250, 'XT0026', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_user` VALUES (251, '8999', '21218cca77804d2ba1922c33e0151105', NULL, NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `origin_name` varchar(255) DEFAULT NULL,
  `new_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='上传文件历史';

-- ----------------------------
-- Records of upload_file
-- ----------------------------
BEGIN;
INSERT INTO `upload_file` VALUES (1, NULL, '2018-07-24 10:11:02', 'single.jpg', 'group1/M00/00/00/wKgDiltW-zaASpawAAAw7WPqn2g070.jpg');
INSERT INTO `upload_file` VALUES (2, NULL, '2018-07-24 12:20:25', 'single.jpg', 'group1/M00/00/00/wKgDiltXGYmASOSxAAAw7WPqn2g593.jpg');
INSERT INTO `upload_file` VALUES (3, NULL, '2018-07-24 14:39:18', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXOhaAfWcLAAJoBnaDPxQ114.jpg');
INSERT INTO `upload_file` VALUES (4, NULL, '2018-07-24 14:39:36', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXOiiAb0D0AAJoBnaDPxQ241.jpg');
INSERT INTO `upload_file` VALUES (5, NULL, '2018-07-24 14:41:59', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXOreAd9seAAJoBnaDPxQ178.jpg');
INSERT INTO `upload_file` VALUES (6, NULL, '2018-07-24 14:42:02', 'nginx.conf', 'group1/M00/00/00/wKgDiltXOrmAVTWKAAAPcbVZ51s25.conf');
INSERT INTO `upload_file` VALUES (7, NULL, '2018-07-24 14:42:04', 'single.jpg', 'group1/M00/00/00/wKgDiltXOryAQkRGAAAw7WPqn2g935.jpg');
INSERT INTO `upload_file` VALUES (8, NULL, '2018-07-24 14:44:05', 'single.jpg', 'group1/M00/00/00/wKgDiltXOzWAQWbtAAAw7WPqn2g876.jpg');
INSERT INTO `upload_file` VALUES (9, NULL, '2018-07-24 14:48:37', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXPEWAFvmnAAJoBnaDPxQ844.jpg');
INSERT INTO `upload_file` VALUES (10, NULL, '2018-07-24 14:54:16', 'single.jpg', 'group1/M00/00/00/wKgDiltXPZiAdGYyAAAw7WPqn2g544.jpg');
INSERT INTO `upload_file` VALUES (11, NULL, '2018-07-24 14:54:20', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXPZyAfZHDAAJoBnaDPxQ873.jpg');
INSERT INTO `upload_file` VALUES (12, NULL, '2018-07-24 14:54:35', '6ff51fefly1fsbnqgq9wgj20j60pkn16.jpg', 'group1/M00/00/00/wKgDiltXPauALZB6AAJsk0eDxMs479.jpg');
INSERT INTO `upload_file` VALUES (13, NULL, '2018-07-24 15:01:55', 'single.jpg', 'group1/M00/00/00/wKgDiltXP2OAd2dUAAAw7WPqn2g554.jpg');
INSERT INTO `upload_file` VALUES (14, NULL, '2018-07-24 15:02:37', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXP4yAWcboAAJoBnaDPxQ331.jpg');
INSERT INTO `upload_file` VALUES (15, NULL, '2018-07-24 15:27:23', 'single.jpg', 'group1/M00/00/00/wKgDiltXRVuAVmPBAAAw7WPqn2g021.jpg');
INSERT INTO `upload_file` VALUES (16, NULL, '2018-07-24 15:28:22', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXRZaALelYAAJoBnaDPxQ641.jpg');
INSERT INTO `upload_file` VALUES (17, NULL, '2018-07-24 15:29:39', 'multiple.jpg', 'group1/M00/00/00/wKgDiltXReOAfyb6AAJoBnaDPxQ627.jpg');
INSERT INTO `upload_file` VALUES (18, NULL, '2018-07-27 10:03:52', '1d75d61b0958071b2a449a5bacefdf72.jpg', 'group1/M00/00/00/wKgDilta7giADSvJAAowGJPhNHo399.jpg');
INSERT INTO `upload_file` VALUES (19, NULL, '2018-07-27 10:25:47', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDilta8yuAdlX5AAVAxZRqjiM348.jpg');
INSERT INTO `upload_file` VALUES (20, NULL, '2018-07-27 10:28:17', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDilta88GAMfDJAAVAxZRqjiM118.jpg');
INSERT INTO `upload_file` VALUES (21, NULL, '2018-07-27 10:40:35', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDilta9qOABBBSAAtkwxOwdy0728.jpg');
INSERT INTO `upload_file` VALUES (22, NULL, '2018-07-27 10:40:38', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDilta9qaAKFfqAAtkwxOwdy0889.jpg');
INSERT INTO `upload_file` VALUES (23, NULL, '2018-07-27 10:40:42', '58203efff3bfa.jpg', 'group1/M00/00/00/wKgDilta9qqAU06VAAGlzmASZws982.jpg');
INSERT INTO `upload_file` VALUES (24, NULL, '2018-07-27 10:42:15', '1d75d61b0958071b2a449a5bacefdf72.jpg', 'group1/M00/00/00/wKgDilta9weAUlUKAAowGJPhNHo398.jpg');
INSERT INTO `upload_file` VALUES (25, NULL, '2018-07-27 10:46:43', '17.jpg', 'group1/M00/00/00/wKgDilta-BOAdwbjAAHKTAa1C1Y752.jpg');
INSERT INTO `upload_file` VALUES (26, NULL, '2018-07-27 10:47:06', '17.jpg', 'group1/M00/00/00/wKgDilta-CqAA0qcAAHKTAa1C1Y961.jpg');
INSERT INTO `upload_file` VALUES (27, NULL, '2018-07-27 11:11:23', 'single.jpg', 'group1/M00/00/00/wKgDilta_duAD-27AAAw7WPqn2g332.jpg');
INSERT INTO `upload_file` VALUES (28, NULL, '2018-07-27 11:22:10', 'single.jpg', 'group1/M00/00/00/wKgDiltbAGKAbLWwAAAw7WPqn2g595.jpg');
INSERT INTO `upload_file` VALUES (29, NULL, '2018-07-27 11:38:00', 'multiple.jpg', 'group1/M00/00/00/wKgDiltbBBeAPC1cAAJoBnaDPxQ119.jpg');
INSERT INTO `upload_file` VALUES (30, NULL, '2018-07-27 14:56:28', 'single.jpg', 'group1/M00/00/00/wKgDiltbMpyAW7xCAAAw7WPqn2g918.jpg');
INSERT INTO `upload_file` VALUES (31, NULL, '2018-07-27 14:57:16', '17.jpg', 'group1/M00/00/00/wKgDiltbMsyAXLgEAAHKTAa1C1Y064.jpg');
INSERT INTO `upload_file` VALUES (32, NULL, '2018-07-27 15:00:17', '17.jpg', 'group1/M00/00/00/wKgDiltbM4GAWdnqAAHKTAa1C1Y482.jpg');
INSERT INTO `upload_file` VALUES (33, NULL, '2018-07-27 15:21:39', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDiltbOIKAF5z_AAVAxZRqjiM471.jpg');
INSERT INTO `upload_file` VALUES (34, NULL, '2018-07-27 15:23:01', '133e619692097cbb1ad18470d32b7352.jpg', 'group1/M00/00/00/wKgDiltbONWAIBGRAAenjozlakY061.jpg');
INSERT INTO `upload_file` VALUES (35, NULL, '2018-07-27 15:25:40', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDiltbOXSAM5kSAAtkwxOwdy0357.jpg');
INSERT INTO `upload_file` VALUES (36, NULL, '2018-07-27 15:26:07', '测试.xlsx', 'group1/M00/00/00/wKgDiltbOY-Ad-liAAAqOR2p_9Y97.xlsx');
INSERT INTO `upload_file` VALUES (37, NULL, '2018-07-27 15:41:57', '测试.xlsx', 'group1/M00/00/00/wKgDiltbPUWAO4ZdAAAqOR2p_9Y62.xlsx');
INSERT INTO `upload_file` VALUES (38, NULL, '2018-07-27 15:42:38', '测试.xlsx', 'group1/M00/00/00/wKgDiltbPW2Abi8lAAAqOR2p_9Y06.xlsx');
INSERT INTO `upload_file` VALUES (39, NULL, '2018-07-27 15:43:40', '接口.xlsx', 'group1/M00/00/00/wKgDiltbPayAYdifAAAq83fEIzE84.xlsx');
INSERT INTO `upload_file` VALUES (40, NULL, '2018-07-27 15:46:18', '需求整理.xlsx', 'group1/M00/00/00/wKgDiltbPkqAaoHIAAAezsjAXX059.xlsx');
INSERT INTO `upload_file` VALUES (41, NULL, '2018-07-27 15:47:20', '测试.xlsx', 'group1/M00/00/00/wKgDiltbPoiANLYFAAAqOR2p_9Y77.xlsx');
INSERT INTO `upload_file` VALUES (42, NULL, '2018-07-27 15:49:47', '测试.xlsx', 'group1/M00/00/00/wKgDiltbPxuAOQ_AAAAqOR2p_9Y89.xlsx');
INSERT INTO `upload_file` VALUES (43, NULL, '2018-07-27 15:54:29', '17.jpg', 'group1/M00/00/00/wKgDiltbQDWAT7SGAAHKTAa1C1Y166.jpg');
INSERT INTO `upload_file` VALUES (44, NULL, '2018-07-27 15:57:55', '3-140Q6134U7.jpg', 'group1/M00/00/00/wKgDiltbQQOAQqn0AAW7k2udh5k579.jpg');
INSERT INTO `upload_file` VALUES (45, NULL, '2018-07-27 16:07:46', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDiltbQ1KAAkxRAAVAxZRqjiM406.jpg');
INSERT INTO `upload_file` VALUES (46, NULL, '2018-07-27 16:18:14', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDiltbRcaARb1cAAtkwxOwdy0462.jpg');
INSERT INTO `upload_file` VALUES (47, NULL, '2018-07-27 16:18:33', '6ff51fefly1fsbnqkslwyj20j60pjgqp.jpg', 'group1/M00/00/00/wKgDiltbRdmAAFMlAAMICF7Xgj8183.jpg');
INSERT INTO `upload_file` VALUES (48, NULL, '2018-07-27 16:19:01', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDiltbRfWAVgvXAAtkwxOwdy0410.jpg');
INSERT INTO `upload_file` VALUES (49, NULL, '2018-07-27 16:29:20', '6ff51fefly1fsbnqkebhgj20j60pkwid.jpg', 'group1/M00/00/00/wKgDiltbSGCALKzgAAJX8G6hsV8319.jpg');
INSERT INTO `upload_file` VALUES (50, NULL, '2018-07-27 16:30:40', '1d75d61b0958071b2a449a5bacefdf72.jpg', 'group1/M00/00/00/wKgDiltbSLCALkQCAAowGJPhNHo161.jpg');
INSERT INTO `upload_file` VALUES (51, NULL, '2018-07-28 13:11:19', '3-140Q6134U7.jpg', 'group1/M00/00/00/wKgDiltca3eADzhcAAW7k2udh5k403.jpg');
INSERT INTO `upload_file` VALUES (52, NULL, '2018-07-28 17:06:16', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDiltcooiALL_vAAtkwxOwdy0235.jpg');
INSERT INTO `upload_file` VALUES (53, NULL, '2018-07-28 17:15:16', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDiltcpKSAbuIGAAtkwxOwdy0098.jpg');
INSERT INTO `upload_file` VALUES (54, NULL, '2018-07-28 18:30:44', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/wKgDiltctlSABdURAAtkwxOwdy0236.jpg');
INSERT INTO `upload_file` VALUES (55, NULL, '2018-07-30 10:33:51', '3-140Q6134U7.jpg', 'group1/M00/00/00/wKgDilte6Y-AWgIFAAW7k2udh5k375.jpg');
INSERT INTO `upload_file` VALUES (56, NULL, '2018-07-30 10:35:16', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDilte6eSAJHMZAAVAxZRqjiM002.jpg');
INSERT INTO `upload_file` VALUES (57, NULL, '2018-07-30 10:37:58', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDilte6oaAKa9nAAVAxZRqjiM198.jpg');
INSERT INTO `upload_file` VALUES (58, NULL, '2018-07-30 10:39:27', '513d3f93b9a82.jpg', 'group1/M00/00/00/wKgDilte6t-Af8qdAAVAxZRqjiM462.jpg');
INSERT INTO `upload_file` VALUES (59, NULL, '2018-07-30 08:46:12', '新建文本文档 (2).txt', 'group1/M00/00/00/wKgDilte0FSAcJFbAAADcPfaWE4896.txt');
INSERT INTO `upload_file` VALUES (60, NULL, '2018-07-30 08:50:50', '新建文本文档.txt', 'group1/M00/00/00/wKgDilte0WqAetCGAAAG92yADeE340.txt');
INSERT INTO `upload_file` VALUES (61, NULL, '2018-07-30 12:27:35', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/wKgDiltfBDeAfHlwAAuU7swk5u0005.jpg');
INSERT INTO `upload_file` VALUES (62, NULL, '2018-08-06 09:42:16', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PKltnp3eAHQPDAAuU7swk5u0512.jpg');
INSERT INTO `upload_file` VALUES (63, NULL, '2018-08-06 09:42:47', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PKltnp5eAVHK7AACfaYpjY64414.jpg');
INSERT INTO `upload_file` VALUES (64, NULL, '2018-08-06 09:44:54', 'staffBaseInfoImport.zip', 'group1/M00/00/00/rB0PKltnqBaAI_XvAAAe4ukMdtw522.zip');
INSERT INTO `upload_file` VALUES (65, NULL, '2018-08-07 15:04:55', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tpRKqAGJ-dAAuU7swk5u0014.jpg');
INSERT INTO `upload_file` VALUES (66, NULL, '2018-08-07 16:10:01', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tpU-2AEIQ8AAuU7swk5u0626.jpg');
INSERT INTO `upload_file` VALUES (67, NULL, '2018-08-07 16:53:01', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1tpXf-AX-odAAtkwxOwdy0876.jpg');
INSERT INTO `upload_file` VALUES (68, NULL, '2018-08-08 15:12:59', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tqmA6AEo8GAACfaYpjY64511.jpg');
INSERT INTO `upload_file` VALUES (69, NULL, '2018-08-08 15:51:31', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tqoRaAEPYGAACfaYpjY64901.jpg');
INSERT INTO `upload_file` VALUES (70, NULL, '2018-08-09 14:30:12', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tr34eAIsafAACfaYpjY64553.jpg');
INSERT INTO `upload_file` VALUES (71, NULL, '2018-08-09 14:44:44', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tr4u6AcxwVAAuU7swk5u0918.jpg');
INSERT INTO `upload_file` VALUES (72, NULL, '2018-08-09 14:54:37', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tr5UCANRhFAACfaYpjY64557.jpg');
INSERT INTO `upload_file` VALUES (73, NULL, '2018-08-09 14:54:46', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tr5UmAEWuXAAuU7swk5u0191.jpg');
INSERT INTO `upload_file` VALUES (74, NULL, '2018-08-09 16:04:29', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tr9aCAB1oiAACfaYpjY64810.jpg');
INSERT INTO `upload_file` VALUES (75, NULL, '2018-08-09 16:06:28', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tr9heAH0AHAAuU7swk5u0180.jpg');
INSERT INTO `upload_file` VALUES (76, NULL, '2018-08-09 16:19:15', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tr-RaAVyJKAACfaYpjY64644.jpg');
INSERT INTO `upload_file` VALUES (77, NULL, '2018-08-09 20:13:01', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1tsL-CABkmvAAtkwxOwdy0643.jpg');
INSERT INTO `upload_file` VALUES (78, NULL, '2018-08-09 20:26:14', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1tsMvmAL1cQAAtkwxOwdy0510.jpg');
INSERT INTO `upload_file` VALUES (79, NULL, '2018-08-10 11:17:20', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1ttA9OAa1WHAAtkwxOwdy0422.jpg');
INSERT INTO `upload_file` VALUES (80, NULL, '2018-08-14 16:03:47', '生产流程.jpeg', 'group1/M00/00/00/rB0PK1tyjPSAQ72YAAFFwCc36Q401.jpeg');
INSERT INTO `upload_file` VALUES (81, NULL, '2018-08-14 16:05:40', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tyjWWAVD2lAAuU7swk5u0915.jpg');
INSERT INTO `upload_file` VALUES (82, NULL, '2018-08-14 16:05:51', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tyjXCAfSZPAAuU7swk5u0205.jpg');
INSERT INTO `upload_file` VALUES (83, NULL, '2018-08-14 16:07:10', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/00/rB0PK1tyjcCALYUXAAuU7swk5u0845.jpg');
INSERT INTO `upload_file` VALUES (84, NULL, '2018-08-14 17:58:01', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1typ7qAYe-bAAtkwxOwdy0948.jpg');
INSERT INTO `upload_file` VALUES (85, NULL, '2018-08-14 17:59:07', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1typ_2Ady9UAAtkwxOwdy0268.jpg');
INSERT INTO `upload_file` VALUES (86, NULL, '2018-08-14 18:03:30', '3-140Q6134U7.jpg', 'group1/M00/00/00/rB0PK1tyqQOAGnYMAAW7k2udh5k539.jpg');
INSERT INTO `upload_file` VALUES (87, NULL, '2018-08-14 18:06:26', '513d3f93b9a82.jpg', 'group1/M00/00/00/rB0PK1tyqbOAAn6mAAVAxZRqjiM683.jpg');
INSERT INTO `upload_file` VALUES (88, NULL, '2018-08-14 18:07:20', '17.jpg', 'group1/M00/00/00/rB0PK1tyqemAaGBNAAHKTAa1C1Y624.jpg');
INSERT INTO `upload_file` VALUES (89, NULL, '2018-08-15 09:00:57', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/00/rB0PK1tze1qAWEP4AACfaYpjY64375.jpg');
INSERT INTO `upload_file` VALUES (90, NULL, '2018-08-15 09:06:32', 'git.png', 'group1/M00/00/00/rB0PK1tzfKqAUtttAAdmTXb2UJI603.png');
INSERT INTO `upload_file` VALUES (91, NULL, '2018-08-15 14:54:58', 'b6288de7b370bd662a0bed5a2d1a0ce1.jpg', 'group1/M00/00/00/rB0PK1tzzlSANif5AAtkwxOwdy0013.jpg');
INSERT INTO `upload_file` VALUES (92, NULL, '2018-08-15 14:55:07', 'JAERPTestCase.xls', 'group1/M00/00/00/rB0PK1tzzl2AXAM7AAQaAE1hOl4249.xls');
INSERT INTO `upload_file` VALUES (93, NULL, '2018-08-15 16:35:51', '3-140Q6134U7.jpg', 'group1/M00/00/00/rB0PK1tz5fiAQZ5mAAW7k2udh5k386.jpg');
INSERT INTO `upload_file` VALUES (94, NULL, '2018-08-15 16:37:35', '3-140Q6134U7.jpg', 'group1/M00/00/00/rB0PK1tz5mGAZZceAAW7k2udh5k479.jpg');
INSERT INTO `upload_file` VALUES (95, NULL, '2018-08-15 16:38:21', '1d75d61b0958071b2a449a5bacefdf72.jpg', 'group1/M00/00/00/rB0PK1tz5o-ADZyXAAowGJPhNHo191.jpg');
INSERT INTO `upload_file` VALUES (96, NULL, '2018-08-15 16:39:33', '20180815上线前必须解决的bug.xlsx', 'group1/M00/00/00/rB0PK1tz5teAWTnuAAA9FOp0KcU44.xlsx');
INSERT INTO `upload_file` VALUES (97, NULL, '2018-08-15 16:45:34', '2018-08-07-ja_erp.sql', 'group1/M00/00/00/rB0PK1tz6ECAUvQsAAMa5fPfr_E196.sql');
INSERT INTO `upload_file` VALUES (98, NULL, '2018-08-15 19:43:06', '一期接口文档.xlsx', 'group1/M00/00/00/rB0PK1t0EdyASxM4AAA5iBVSTdc67.xlsx');
INSERT INTO `upload_file` VALUES (99, NULL, '2018-08-15 23:23:48', 'JAERPTestCase.xls', 'group1/M00/00/01/rB0PK1t0RZWAakZMAAQaAE1hOl4478.xls');
INSERT INTO `upload_file` VALUES (100, NULL, '2018-08-16 00:31:10', 'test - sys_code.sql', 'group1/M00/00/01/rB0PK1t0VWCADqoNAAAktBeBGWo726.sql');
INSERT INTO `upload_file` VALUES (101, NULL, '2018-08-16 00:59:44', '2018-07-31- ja_erp.sql', 'group1/M00/00/01/rB0PK1t0W_mASZ5mAAJF9YDy0iY260.sql');
INSERT INTO `upload_file` VALUES (102, NULL, '2018-08-16 01:00:15', '2018-07-31- ja_erp.sql', 'group1/M00/00/01/rB0PK1t0XDGAXtnoAAJF9YDy0iY384.sql');
INSERT INTO `upload_file` VALUES (103, NULL, '2018-08-16 01:15:53', 'orgInfoImport.xls', 'group1/M00/00/01/rB0PK1t0X9qAC7iSAABYAIMRI4c855.xls');
INSERT INTO `upload_file` VALUES (104, NULL, '2018-08-16 10:52:48', '2018-07-31- ja_erp.sql', 'group1/M00/00/01/rB0PK1t05xGAfKlGAAJF9YDy0iY965.sql');
INSERT INTO `upload_file` VALUES (105, NULL, '2018-08-16 21:10:28', '2018-07-31- ja_erp.sql', 'group1/M00/00/01/rB0PK1t1d9WAHy_nAAJF9YDy0iY550.sql');
INSERT INTO `upload_file` VALUES (106, NULL, '2018-08-21 14:37:34', '接口.xlsx', 'group1/M00/00/01/rB0PK1t7sz-AX_TiAAAq83fEIzE91.xlsx');
INSERT INTO `upload_file` VALUES (107, NULL, '2018-08-21 14:38:18', '员工导入case.xlsx', 'group1/M00/00/01/rB0PK1t7s2uAYu-DAAAu4ssu8qM87.xlsx');
INSERT INTO `upload_file` VALUES (108, NULL, '2018-08-21 15:03:42', '员工导入模板.xls', 'group1/M00/00/01/rB0PK1t7uV6ADeppAABMANFVrGA844.xls');
INSERT INTO `upload_file` VALUES (109, NULL, '2018-08-21 15:08:46', '员工导入模板.xls', 'group1/M00/00/01/rB0PK1t7uo6AIxjRAABMANFVrGA727.xls');
INSERT INTO `upload_file` VALUES (110, NULL, '2018-08-21 15:11:27', '员工导入模板.xls', 'group1/M00/00/01/rB0PK1t7uzCACS68AABMANFVrGA941.xls');
INSERT INTO `upload_file` VALUES (111, NULL, '2018-08-21 15:11:39', 'JAERPTestCase.xls', 'group1/M00/00/01/rB0PK1t7uzuAeQ6mAAQaAE1hOl4750.xls');
INSERT INTO `upload_file` VALUES (112, NULL, '2018-08-21 15:11:52', 'JAERPTestCase.xls', 'group1/M00/00/01/rB0PK1t7u0iATBXgAAQaAE1hOl4660.xls');
INSERT INTO `upload_file` VALUES (113, NULL, '2018-08-21 15:12:12', '接口.xlsx', 'group1/M00/00/01/rB0PK1t7u1yAP8PjAAAq83fEIzE69.xlsx');
INSERT INTO `upload_file` VALUES (114, NULL, '2018-09-10 13:41:20', '添加file.sql', 'group1/M00/00/01/rB0PK1uWBDmAaJ1GAAAAeRvnyQM619.sql');
INSERT INTO `upload_file` VALUES (115, NULL, '2018-09-10 15:33:06', '75936d3a61e886fbffcddd49663e64b3 (1).jpg', 'group1/M00/00/01/rB0PK1uWHmqASZNpAAuU7swk5u0709.jpg');
INSERT INTO `upload_file` VALUES (116, NULL, '2018-09-10 15:59:45', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/01/rB0PK1uWJKqATKuYAACfaYpjY64563.jpg');
INSERT INTO `upload_file` VALUES (117, NULL, '2018-09-12 11:44:03', 'git.png', 'group1/M00/00/01/rB0PK1uYi7uAY50KAAdmTXb2UJI438.png');
INSERT INTO `upload_file` VALUES (118, NULL, '2018-09-12 11:44:15', 'git.png', 'group1/M00/00/02/rB0PK1uYi8eAFFzZAAdmTXb2UJI078.png');
INSERT INTO `upload_file` VALUES (119, NULL, '2018-09-12 11:44:28', '75936d3a61e886fbffcddd49663e64b3.jpg', 'group1/M00/00/02/rB0PK1uYi9SAOhuSAAuU7swk5u0690.jpg');
INSERT INTO `upload_file` VALUES (120, NULL, '2018-09-12 11:44:30', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/02/rB0PK1uYi9aAXoalAACfaYpjY64303.jpg');
INSERT INTO `upload_file` VALUES (121, NULL, '2018-09-12 11:46:13', 'b20c67cfc28c992fec52927d880db337.jpg', 'group1/M00/00/02/rB0PK1uYjD6AGNJEAACfaYpjY64489.jpg');
INSERT INTO `upload_file` VALUES (122, NULL, '2018-09-12 11:46:21', 'git.png', 'group1/M00/00/02/rB0PK1uYjEWAbIviAAdmTXb2UJI377.png');
INSERT INTO `upload_file` VALUES (123, NULL, '2018-09-12 11:46:34', '75936d3a61e886fbffcddd49663e64b3 (1).jpg', 'group1/M00/00/02/rB0PK1uYjFKAPsP7AAuU7swk5u0635.jpg');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_` varchar(20) NOT NULL COMMENT '账号-工号',
  `password_` varchar(32) DEFAULT NULL COMMENT '密码',
  `name_` varchar(32) DEFAULT NULL COMMENT '姓名',
  `admin_` int(11) DEFAULT '-1' COMMENT '是否为管理员',
  `status_` int(11) DEFAULT '1' COMMENT '状态位1启用，-1禁用',
  `email_` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `cellphone_` varchar(32) DEFAULT NULL COMMENT '手机号',
  `created_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by_` bigint(20) DEFAULT NULL,
  `last_login_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by_` bigint(20) DEFAULT NULL,
  `del_flag_` int(11) DEFAULT '-1',
  `avatar_` varchar(256) DEFAULT '' COMMENT '头像地址',
  `open_id_` varchar(255) DEFAULT NULL COMMENT '微信用户ID',
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE KEY `account` (`account_`) USING BTREE,
  UNIQUE KEY `cellphone` (`cellphone_`) USING BTREE,
  UNIQUE KEY `email` (`email_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '1', 'e10adc3949ba59abbe56e057f20f883e', '1', -1, 1, '1', '1', '2019-01-03 04:40:16', 0, '2020-05-27 19:59:38', '2019-05-06 10:44:14', 0, -1, 'group1/M00/00/01/wKgDi1wuAfKAMAjVAAAeEuAmaxs761.png', NULL);
INSERT INTO `user` VALUES (2, '2', 'e10adc3949ba59abbe56e057f20f883e', '2', -1, 1, '2', '2', '2019-01-03 04:54:39', 0, '2020-05-27 19:59:38', '2019-05-06 10:45:14', 0, -1, 'group1/M00/00/01/wKgDi1wuBU6AR_G8AAAeEuAmaxs707.png', NULL);
INSERT INTO `user` VALUES (3, '3', 'e10adc3949ba59abbe56e057f20f883e', '3', -1, 1, '3', '3', '2019-01-03 04:56:03', 0, '2020-05-27 19:59:38', '2019-03-14 18:56:27', 0, 1, 'group1/M00/00/01/wKgDi1wuBaiANGOxAAANMBQPQro292.png', NULL);
INSERT INTO `user` VALUES (4, '4', 'e10adc3949ba59abbe56e057f20f883e', '4', -1, 1, '4', '4', '2019-01-04 15:34:14', 1, '2020-05-27 19:59:38', '2019-03-14 18:56:21', 0, 1, 'group1/M00/00/02/wKgDi1w8n8OADuX1AAAeEuAmaxs719.png', NULL);
INSERT INTO `user` VALUES (5, '5', 'e10adc3949ba59abbe56e057f20f883e', '5', -1, 1, '5', '5', '2019-01-04 15:34:14', 1, '2020-05-27 19:59:38', '2019-03-14 18:56:21', 0, 1, 'group1/M00/00/02/wKgDi1w8n-yAeF_-AAAeEuAmaxs113.png', NULL);
INSERT INTO `user` VALUES (6, 'njcp0001', 'e10adc3949ba59abbe56e057f20f883e', 'chenxiaoxiao', -1, 1, 'xiaoxiao.chen@champlink-info.com', '18761600393', '2019-01-08 08:45:26', 0, '2020-05-27 19:59:38', '2019-03-14 18:59:21', 0, -1, 'group1/M00/00/02/wKgDi1w_V5CAGEt-AAWXvWf0Aog85.jpeg', NULL);
INSERT INTO `user` VALUES (7, 'test1', 'e10adc3949ba59abbe56e057f20f883e', 'test1', 1, 1, 'test1', 'test1', '2019-01-14 07:31:15', 0, '2020-05-27 19:59:38', '2019-04-18 09:14:58', 0, -1, 'group1/M00/00/02/wKgDi1w8qmiAe1_iAAAyDIKEbOk059.png', NULL);
INSERT INTO `user` VALUES (9, 'miss', 'e10adc3949ba59abbe56e057f20f883e', 'miss', -1, 1, '1019294648@qq.com', '13812345678', '2019-01-16 08:16:14', 0, '2020-05-27 19:59:38', '2019-03-25 13:38:33', 0, -1, 'group1/M00/00/02/wKgDi1w_V_qAAJctAAUgMnwuitw178.png', NULL);
INSERT INTO `user` VALUES (13, '100001111', 'e10adc3949ba59abbe56e057f20f883e', 'test', -1, 1, '123@qq.com', '23333333', '2019-01-24 16:05:00', 0, '2020-05-27 19:59:38', '2019-03-14 19:21:06', 0, -1, 'group1/M00/00/04/wKgDi1xLH_qALnZWAAFxRIUzXN873.jpeg', NULL);
INSERT INTO `user` VALUES (19, '123123412313123', 'e10adc3949ba59abbe56e057f20f883e', '测试111', -1, 1, '123132@qq.com', '1234112312312', '2019-01-24 16:20:38', 0, '2020-05-27 19:59:38', '2019-01-25 14:45:03', 0, -1, 'group1/M00/00/04/wKgDi1xLIAqAMavAAAIgjg4n3qU634.jpg', NULL);
INSERT INTO `user` VALUES (20, '333333', 'e10adc3949ba59abbe56e057f20f883e', '33333', -1, 1, NULL, '3333333333333', '2019-01-25 11:20:03', 0, '2020-05-27 19:59:38', '2019-03-14 18:55:17', 0, -1, '', NULL);
INSERT INTO `user` VALUES (21, 'King..', 'e10adc3949ba59abbe56e057f20f883e', 'King..', -1, 1, 'jljinqilong@163.com', '13033037527', '2019-01-29 10:55:02', 0, '2020-05-27 19:59:38', '2019-04-15 13:35:31', 0, -1, '', NULL);
INSERT INTO `user` VALUES (22, 'K00001', 'e10adc3949ba59abbe56e057f20f883e', '测试', -1, 1, 'test@123.com', '18768686868', '2019-02-21 10:15:59', 0, '2020-05-27 19:59:38', '2019-03-25 13:49:36', 0, -1, '', NULL);
INSERT INTO `user` VALUES (23, '0001', 'e10adc3949ba59abbe56e057f20f883e', '测试删除', -1, 1, 'test@1231.com', '138000000', '2019-03-22 09:43:14', 0, '2020-05-27 19:59:38', '2019-03-29 13:42:33', 0, -1, '', NULL);
INSERT INTO `user` VALUES (24, '123456789', 'e10adc3949ba59abbe56e057f20f883e', '测试123', -1, 1, '12345@qq.com', '123456748901', '2019-03-30 15:19:10', 0, '2020-05-27 19:59:38', '2020-05-27 19:59:38', 0, -1, '', NULL);
INSERT INTO `user` VALUES (25, '0000010001', 'e10adc3949ba59abbe56e057f20f883e', '测试测试', -1, 1, '123456@qq.com', '138111111', '2019-04-01 10:55:30', 0, '2020-05-27 19:59:38', '2020-05-27 19:59:38', 0, -1, '', NULL);
INSERT INTO `user` VALUES (26, 'shiyu.chen', 'e10adc3949ba59abbe56e057f20f883e', '陈时雨', -1, 1, 'firework233@qq.com', '138888888', '2019-04-08 11:00:55', 0, '2020-05-27 19:59:38', '2020-05-27 19:59:38', 0, -1, '', NULL);
INSERT INTO `user` VALUES (27, '20190412', 'e10adc3949ba59abbe56e057f20f883e', 'wuyukang', -1, 1, 'piaosheng2000@163.com', '13801585489', '2019-04-12 11:03:45', 0, '2020-05-27 19:59:38', '2019-04-12 11:09:26', 0, -1, '', NULL);
INSERT INTO `user` VALUES (31, 'kkkk', 'e10adc3949ba59abbe56e057f20f883e', 'kkkk', -1, 1, 'jljinqilongs@163.com', '13033037529', '2019-04-17 17:35:25', 0, '2020-05-27 19:59:38', '2020-05-27 19:59:38', 0, -1, '', NULL);
INSERT INTO `user` VALUES (32, 'Duplicate', 'e10adc3949ba59abbe56e057f20f883e', 'Duplicate', -1, -1, 'jljinqilonssg@163.com', 'Duplicate', '2019-04-17 17:39:43', 0, '2020-05-27 19:59:38', '2019-04-25 14:26:10', 0, -1, '', NULL);
INSERT INTO `user` VALUES (33, 'HF18516', 'e10adc3949ba59abbe56e057f20f883e', '周慧伶', -1, 1, NULL, '13003030303', '2019-04-18 14:55:39', 0, '2020-05-27 19:59:38', '2020-05-27 19:59:38', 0, -1, '', NULL);
INSERT INTO `user` VALUES (34, 'test', 'e10adc3949ba59abbe56e057f20f883e', 'test哈哈', 1, 1, '11223344', '15651680895', '2020-05-27 19:59:38', 0, '2018-10-17 08:15:18', '2019-04-30 11:29:56', 0, -1, '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for usual_function
-- ----------------------------
DROP TABLE IF EXISTS `usual_function`;
CREATE TABLE `usual_function` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id_` bigint(20) DEFAULT NULL,
  `resource_id_` bigint(20) DEFAULT NULL,
  `order_num_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='常用功能';

-- ----------------------------
-- Records of usual_function
-- ----------------------------
BEGIN;
INSERT INTO `usual_function` VALUES (70, 0, 103, 1);
INSERT INTO `usual_function` VALUES (73, 0, 507, 3);
INSERT INTO `usual_function` VALUES (74, 0, 304, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
