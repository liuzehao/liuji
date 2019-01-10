/*
SQLyog Ultimate v10.42 
MySQL - 5.5.28 : Database - leo_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`leo_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `leo_test`;

/*Table structure for table `acl_permission` */

DROP TABLE IF EXISTS `acl_permission`;

CREATE TABLE `acl_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_token` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `perm_token` (`perm_token`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `acl_role_permission` */

DROP TABLE IF EXISTS `acl_role_permission`;

CREATE TABLE `acl_role_permission` (
  `role_id` varchar(36) DEFAULT NULL,
  `perm_id` varchar(36) DEFAULT NULL,
  `perm_token` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `acl_user_permission` */

DROP TABLE IF EXISTS `acl_user_permission`;

CREATE TABLE `acl_user_permission` (
  `user_id` int(11) DEFAULT NULL,
  `perm_token` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `conflict_course` */

DROP TABLE IF EXISTS `conflict_course`;

CREATE TABLE `conflict_course` (
  `sno` varchar(10) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `semester` varchar(15) DEFAULT NULL,
  `cno` varchar(10) DEFAULT NULL,
  `courseNo` varchar(10) DEFAULT NULL,
  `courseName` varchar(40) DEFAULT NULL,
  `usualGrade` varchar(10) DEFAULT NULL,
  `experGrade` varchar(10) DEFAULT NULL,
  `totalGrade` varchar(10) DEFAULT NULL,
  `gradeId` varchar(10) DEFAULT NULL,
  `coursePro` varchar(20) DEFAULT NULL,
  `courseAttr` varchar(10) DEFAULT NULL,
  `period` varchar(10) DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `gpa` varchar(10) DEFAULT NULL,
  `courseDepart` varchar(30) DEFAULT NULL,
  `typeMan` varchar(20) DEFAULT NULL,
  `examPro` varchar(10) DEFAULT NULL,
  `reSemester` varchar(15) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `fail_course` */

DROP TABLE IF EXISTS `fail_course`;

CREATE TABLE `fail_course` (
  `sno` varchar(10) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `semester` varchar(15) DEFAULT NULL,
  `cno` varchar(10) DEFAULT NULL,
  `courseNo` varchar(10) DEFAULT NULL,
  `courseName` varchar(40) DEFAULT NULL,
  `usualGrade` varchar(10) DEFAULT NULL,
  `experGrade` varchar(10) DEFAULT NULL,
  `totalGrade` varchar(10) DEFAULT NULL,
  `gradeId` varchar(10) DEFAULT NULL,
  `coursePro` varchar(20) DEFAULT NULL,
  `courseAttr` varchar(10) DEFAULT NULL,
  `period` varchar(10) DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `gpa` varchar(10) DEFAULT NULL,
  `courseDepart` varchar(30) DEFAULT NULL,
  `typeMan` varchar(20) DEFAULT NULL,
  `examPro` varchar(10) DEFAULT NULL,
  `reSemester` varchar(15) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `isReplace` tinyint(1) DEFAULT '0',
  `replaceCourseName` varchar(40) DEFAULT NULL,
  `replaceGrade` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stu_grade` */

DROP TABLE IF EXISTS `stu_grade`;

CREATE TABLE `stu_grade` (
  `sno` varchar(10) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `semester` varchar(15) DEFAULT NULL,
  `cno` varchar(10) DEFAULT NULL,
  `courseNo` varchar(10) DEFAULT NULL,
  `courseName` varchar(40) DEFAULT NULL,
  `usualGrade` varchar(10) DEFAULT NULL,
  `experGrade` varchar(10) DEFAULT NULL,
  `totalGrade` varchar(10) DEFAULT NULL,
  `gradeId` varchar(10) DEFAULT NULL,
  `coursePro` varchar(20) DEFAULT NULL,
  `courseAttr` varchar(10) DEFAULT NULL,
  `period` varchar(10) DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `gpa` varchar(10) DEFAULT NULL,
  `courseDepart` varchar(30) DEFAULT NULL,
  `typeMan` varchar(20) DEFAULT NULL,
  `examPro` varchar(10) DEFAULT NULL,
  `reSemester` varchar(15) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stu_info` */

DROP TABLE IF EXISTS `stu_info`;

CREATE TABLE `stu_info` (
  `sno` varchar(10) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `college` varchar(40) DEFAULT NULL,
  `major` varchar(60) DEFAULT NULL,
  `cno` varchar(10) DEFAULT NULL,
  `rollStatus` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stu_plan` */

DROP TABLE IF EXISTS `stu_plan`;

CREATE TABLE `stu_plan` (
  `semester` varchar(15) DEFAULT NULL,
  `college` varchar(20) DEFAULT NULL,
  `th` varchar(10) DEFAULT NULL,
  `major` varchar(60) DEFAULT NULL,
  `courseNo` varchar(10) DEFAULT NULL,
  `courseName` varchar(40) DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `courseAttr` varchar(10) DEFAULT NULL,
  `courseDepart` varchar(30) DEFAULT NULL,
  `cno` varchar(10) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stu_wrong` */

DROP TABLE IF EXISTS `stu_wrong`;

CREATE TABLE `stu_wrong` (
  `sno` varchar(10) NOT NULL DEFAULT '',
  `name` varchar(40) DEFAULT NULL,
  `cno` varchar(10) DEFAULT NULL,
  `grade6` float DEFAULT NULL,
  `grade5` float DEFAULT NULL,
  `grade4` float DEFAULT NULL,
  `grade3` float DEFAULT NULL,
  `grade2` float DEFAULT NULL,
  `grade1` float DEFAULT NULL,
  `validGrade6` tinyint(1) DEFAULT '0',
  `validGrade5` tinyint(1) DEFAULT '0',
  `validGrade4` tinyint(1) DEFAULT '0',
  `validGrade3` tinyint(1) DEFAULT '0',
  `validGrade2` tinyint(1) DEFAULT '0',
  `validGrade1` tinyint(1) DEFAULT '0',
  `tolscore` float DEFAULT NULL,
  `degrade` tinyint(1) DEFAULT '0',
  `peohandle` tinyint(1) DEFAULT NULL,
  `wrongLevel` int(11) DEFAULT NULL,
  `wasWrong` tinyint(1) DEFAULT '0',
  `roll` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_description` varchar(200) DEFAULT NULL COMMENT '角色介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '登录名称',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `user_telephone` varchar(50) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `sno` varchar(10) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `semester` varchar(15) DEFAULT NULL,
  `courseNo` varchar(10) DEFAULT NULL,
  `courseName` varchar(40) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `credit` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
