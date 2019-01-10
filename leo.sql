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

/*Data for the table `acl_permission` */

insert  into `acl_permission`(`id`,`perm_token`,`description`) values (1,'admin','系统设置'),(21,'user_admin','用户管理员'),(22,'role_admin','角色管理员'),(23,'perm_admin','权限管理员'),(24,'fill_admin','文件管理员'),(25,'theme_admin','领域管理员');

/*Table structure for table `acl_role_permission` */

DROP TABLE IF EXISTS `acl_role_permission`;

CREATE TABLE `acl_role_permission` (
  `role_id` varchar(36) DEFAULT NULL,
  `perm_id` varchar(36) DEFAULT NULL,
  `perm_token` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `acl_role_permission` */

insert  into `acl_role_permission`(`role_id`,`perm_id`,`perm_token`) values ('36','1','admin'),('36','22','role_admin'),('37','1','admin'),('37','23','perm_admin'),('38','1','admin'),('38','21','user_admin'),('38','22','role_admin'),('38','23','perm_admin'),('35','1','admin'),('35','21','user_admin'),('35','24','file_admin'),('38','25','theme_admin'),('40','1','admin'),('40','25','theme_admin'),('36','1','admin'),('36','22','role_admin'),('36','24','file_admin'),('38','24','file_admin');

/*Table structure for table `acl_user_permission` */

DROP TABLE IF EXISTS `acl_user_permission`;

CREATE TABLE `acl_user_permission` (
  `user_id` int(11) DEFAULT NULL,
  `perm_token` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `acl_user_permission` */

insert  into `acl_user_permission`(`user_id`,`perm_token`) values (15,'admin');

/*Table structure for table `stu_wrong` */

DROP TABLE IF EXISTS `stu_wrong`;

CREATE TABLE `stu_wrong` (
  `sno` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `cno` int(11) DEFAULT NULL,
  `grade5` int(11) DEFAULT NULL,
  `grade4` int(11) DEFAULT NULL,
  `grade3` int(11) DEFAULT NULL,
  `grade2` int(11) DEFAULT NULL,
  `grade1` int(11) DEFAULT NULL,
  `tolscore` int(11) DEFAULT NULL,
  `degrade` tinyint(1) DEFAULT NULL,
  `peohandle` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stu_wrong` */

insert  into `stu_wrong`(`sno`,`name`,`cno`,`grade5`,`grade4`,`grade3`,`grade2`,`grade1`,`tolscore`,`degrade`,`peohandle`) values (1,'nn',11,11,11,11,11,11,11,1,1),(2,'bb',2,222,22,22,22,22,22,1,0),(3,'vv',33,33,33,33,33,33,33,0,0),(4,'tt',44,44,44,44,44,44,44,0,0),(5,'uu',55,55,55,555,55,555,555,0,0),(6,'yy',66,66,66,66,66,66,66,0,0),(7,'ii',77,77,77,77,77,77,777,0,1),(8,'oo',88,888,8,88,88,88,88,1,0),(9,'pp',5,5,5,5,5,5,5,1,0),(10,'rr',9,9,9,9,9,9,9,0,0),(11,'99',9,99,9,9,9,9,9,1,1),(12,'11',11,11,11,11,11,11,11,1,1),(13,'11',11,11,11,11,111,111,11,1,1),(14,'11',11,1,1,1,1,1,1,1,1),(15,'1',1,1,1,1,1,1,1,1,1),(16,'1',1,11,1,1,1,1,1,1,1),(17,'22',2,2,2,2,2,2,2,1,1),(18,'1',1,1,1,1,1,1,1,1,1),(19,'22',11,1,1,1,1,1,1,1,1),(20,'1',1,1,1,1,1,1,1,1,1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_description` varchar(200) DEFAULT NULL COMMENT '角色介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_description`) values (35,'用户管理','用户管理员'),(36,'角色管理员','角色管理员'),(37,'权限管理员','权限管理员'),(38,'系统管理员','系统管理员'),(39,'文件管理员','文件管理员'),(40,'领域管理员','领域管理员');

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

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`login_name`,`user_name`,`password`,`email`,`user_telephone`) values (27,'admin','管理员','admin','506039239@qq.com','33333');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (27,38);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
