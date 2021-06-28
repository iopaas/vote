/*
SQLyog Professional v12.5.0 (64 bit)
MySQL - 8.0.16 : Database - saas_vote
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`saas_vote` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `saas_vote`;

/*Table structure for table `ntc_account` */

DROP TABLE IF EXISTS `ntc_account`;

CREATE TABLE `ntc_account` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '账号',
  `account_pwdmd5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码(md5)',
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '姓名',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `role_id` bigint(16) DEFAULT '0' COMMENT '角色ID',
  `last_login_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登录时间',
  `status` int(2) DEFAULT '1' COMMENT '状态(1:启用;2:禁用;3:删除)',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改人',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_ACCOUNT_NUMBER` (`account_number`) USING BTREE,
  KEY `INDEX_ACCOUNT_NAME` (`account_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账号主表';

/*Data for the table `ntc_account` */

insert  into `ntc_account`(`id`,`account_number`,`account_pwdmd5`,`account_name`,`mobile`,`role_id`,`last_login_time`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values 
(1,'admin','e10adc3949ba59abbe56e057f20f883e','管理员','13804010103',100001,'2021-06-22 17:49:22',1,'system','2021-06-22 08:43:13','system','2021-06-22 10:43:13'),
(2,'guest','e10adc3949ba59abbe56e057f20f883e','游客','13804010118',100002,'2021-06-24 15:03:43',1,'system','2021-06-22 09:43:18','system','2021-06-22 01:53:57'),
(3,'user','e10adc3949ba59abbe56e057f20f883e','用户','13804010079',100002,'2021-06-23 10:09:25',1,'system','2021-06-22 14:41:58','system','2021-06-22 01:53:54');

/*Table structure for table `ntc_activity` */

DROP TABLE IF EXISTS `ntc_activity`;

CREATE TABLE `ntc_activity` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '活动主题',
  `activity_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '活动描述',
  `start_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '投票开始时间',
  `end_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '投票结束时间',
  `status` int(2) DEFAULT '1' COMMENT '状态(1:有效;2:无效)',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建时间',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `INDEX_COMPANY_NAME` (`activity_title`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='选举活动信息表';

/*Data for the table `ntc_activity` */

insert  into `ntc_activity`(`id`,`activity_title`,`activity_desc`,`start_time`,`end_time`,`status`,`create_time`,`update_time`) values 
(1,'【答题竞猜】','谁是2021年NBA最收欢迎的球星？','2021-06-22 00:00:00','2021-06-28 00:00:00',1,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(2,'【答题竞猜】','谁是2021年NBA常规赛MVP？','2021-06-23 15:00:00','2021-06-25 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12'),
(3,'【答题竞猜】','谁是2021年NBA常规赛得分王？','2021-06-26 00:00:00','2021-06-30 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12'),
(4,'【答题竞猜】','谁是2021年NBA常规赛助攻王？','2021-06-26 00:00:00','2021-06-30 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12'),
(5,'【有问必答】','谁是2021年NBA常规赛篮板王？','2021-06-26 00:00:00','2021-06-30 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12'),
(6,'【有问必答】','谁是2021年NBA常规赛抢断王？','2021-06-26 00:00:00','2021-06-30 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12'),
(7,'【有问必答】','谁是2021年NBA常规赛盖帽王？','2021-06-26 00:00:00','2021-06-30 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12'),
(8,'【有问必答】','谁是2021年NBA常规赛3分王？','2021-06-26 00:00:00','2021-06-30 00:00:00',1,'2018-05-14 10:42:12','2018-05-14 10:42:12');

/*Table structure for table `ntc_activity_member` */

DROP TABLE IF EXISTS `ntc_activity_member`;

CREATE TABLE `ntc_activity_member` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` bigint(16) DEFAULT '0' COMMENT '选举活动ID',
  `member_id` bigint(16) DEFAULT '0' COMMENT '被选举人ID',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建时间',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_user_number` (`activity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='活动被选举人表';

/*Data for the table `ntc_activity_member` */

insert  into `ntc_activity_member`(`id`,`activity_id`,`member_id`,`create_time`,`update_time`) values 
(1,1,1,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(2,1,2,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(3,1,3,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(4,8,3,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(5,8,4,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(6,8,5,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(7,8,6,'2021-06-23 10:42:12','2021-06-23 10:42:12'),
(8,2,3,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(9,2,6,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(10,3,1,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(11,3,4,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(12,3,7,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(13,4,1,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(14,4,2,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(15,4,3,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(16,4,4,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(17,4,5,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(18,5,4,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(19,5,5,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(20,5,6,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(21,5,7,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(22,6,3,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(23,6,8,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(24,7,1,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(25,7,2,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(26,7,3,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(27,7,4,'2021-06-23 17:42:12','2021-06-23 17:42:12'),
(28,7,5,'2021-06-23 17:42:12','2021-06-23 17:42:12');

/*Table structure for table `ntc_member` */

DROP TABLE IF EXISTS `ntc_member`;

CREATE TABLE `ntc_member` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `candidate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '候选人姓名',
  `candidate_photo` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '候选人电话',
  `candidate_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '候选人邮箱',
  `candidate_id_card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '候选人身份证',
  `status` int(2) DEFAULT '1' COMMENT '状态(1:启用;2:禁用)',
  `create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建时间',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='候选人信息表';

/*Data for the table `ntc_member` */

insert  into `ntc_member`(`id`,`candidate_name`,`candidate_photo`,`candidate_email`,`candidate_id_card`,`status`,`create_time`,`update_time`) values 
(1,'勒布朗·詹姆斯','http://img1.gtimg.com/sports/pics/hv1/102/147/1902/123715137.jpg','Lebron.James@hotmail.com','Z856780(1)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(2,'凯文·杜兰特','http://img1.gtimg.com/sports/pics/hv1/108/12/2149/139741893.jpg','Kevin.Durant@hotmail.com','A256780(4)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(3,'斯蒂芬·库里','http://img1.gtimg.com/sports/pics/hv1/80/151/1902/123716135.jpg','Stephen.Curry@hotmail.com','C156780(5)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(4,'克里斯·保罗','http://img1.gtimg.com/sports/pics/hv1/97/158/1902/123717937.jpg','Anthony.Davis@hotmail.com','J333568(0)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(5,'安东尼·戴维斯','http://img1.gtimg.com/sports/pics/hv1/254/163/1902/123719369.jpg','Anthony.Davis@hotmail.com','S589012(3)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(6,'姆斯·哈登','http://img1.gtimg.com/sports/pics/hv1/128/153/1902/123716693.jpg','James.Harden@hotmail.com','M996890(1)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(7,'凯里·欧文','http://img1.gtimg.com/sports/pics/hv1/236/160/1902/123718586.jpg','Kyrie.Irving@hotmail.com','V358098(2)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58'),
(8,'科怀·莱昂纳德','http://img1.gtimg.com/sports/pics/hv1/5/12/2149/139741790.jpg','Kawhi.Leonard@hotmail.com','P790673(0)',1,'2021-06-22 14:41:58','2021-06-22 14:41:58');

/*Table structure for table `ntc_role` */

DROP TABLE IF EXISTS `ntc_role`;

CREATE TABLE `ntc_role` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '角色名称',
  `role_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '角色描述',
  `role_count` int(10) DEFAULT '0' COMMENT '角色数量',
  `status` int(2) DEFAULT '1' COMMENT '状态(1:启用;2:禁用)',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改人',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `INDEX_ROLE_NAME` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统角色表';

/*Data for the table `ntc_role` */

insert  into `ntc_role`(`id`,`role_name`,`role_desc`,`role_count`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values 
(100001,'超级管理员','超级管理员',1,2,'system','2021-06-22 09:53:56','admin','2021-06-22 09:53:56'),
(100002,'管理员','管理员',1,1,'system','2021-06-22 09:53:56','admin','2021-06-22 09:53:56'),
(100003,'普通用户','普通用户',1,1,'system','2021-06-22 09:53:56','','2021-06-22 09:53:56');

/*Table structure for table `ntc_user` */

DROP TABLE IF EXISTS `ntc_user`;

CREATE TABLE `ntc_user` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '账号',
  `user_pwdmd5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码(md5)',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '姓名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '电话号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '电子邮箱',
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '身份证',
  `status` int(2) DEFAULT '1' COMMENT '状态(1:启用;2:禁用)',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_user_number` (`user_number`) USING BTREE,
  KEY `index_mobile` (`mobile`) USING BTREE,
  KEY `index_email` (`email`) USING BTREE,
  KEY `index_id_card` (`id_card`) USING BTREE,
  KEY `index_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';

/*Data for the table `ntc_user` */

insert  into `ntc_user`(`id`,`user_number`,`user_pwdmd5`,`user_name`,`mobile`,`email`,`id_card`,`status`,`create_time`,`update_time`) values 
(1,NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'a1234567@hk.com','A123456(7)',1,'2021-06-22 21:31:53','2021-06-22 21:31:53');

/*Table structure for table `ntc_user_vote` */

DROP TABLE IF EXISTS `ntc_user_vote`;

CREATE TABLE `ntc_user_vote` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(16) DEFAULT '0' COMMENT '用户ID',
  `activity_id` bigint(16) DEFAULT '0' COMMENT '选举活动ID',
  `member_id` bigint(16) DEFAULT '0' COMMENT '被選舉人ID',
  `vote_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '投票时间',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建时间',
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_member_id` (`member_id`),
  KEY `index_activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户投标记录表';

/*Data for the table `ntc_user_vote` */

insert  into `ntc_user_vote`(`id`,`user_id`,`activity_id`,`member_id`,`vote_time`,`create_time`,`update_time`) values 
(1,1,1,1,'2021-06-22 21:32:53','2021-06-22 21:32:5','2021-06-22 21:32:5');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
