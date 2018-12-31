# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: www.liangxublog.xyz (MySQL 5.7.24-0ubuntu0.16.04.1)
# Database: project
# Generation Time: 2018-12-31 11:38:14 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tb_sys_access_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_access_log`;

CREATE TABLE `tb_sys_access_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方式',
  `url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求地址',
  `ip_addr` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '浏览器代理',
  `start_time` datetime(3) DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '结束时间',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统访问日志';

LOCK TABLES `tb_sys_access_log` WRITE;
/*!40000 ALTER TABLE `tb_sys_access_log` DISABLE KEYS */;

INSERT INTO `tb_sys_access_log` (`id`, `account`, `method`, `url`, `ip_addr`, `user_agent`, `start_time`, `end_time`, `isactive`, `inserttime`, `updatetime`)
VALUES
	(1,'lx','GET','/sys/user/menu','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:29:07.247','2018-12-31 18:29:07.535',1,'2018-12-31 18:29:07','2018-12-31 18:29:07'),
	(2,'lx','GET','/sys/user/info','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:29:07.247','2018-12-31 18:29:07.535',1,'2018-12-31 18:29:07','2018-12-31 18:29:07'),
	(3,'lx','POST','/sys/access/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:29:07.247','2018-12-31 18:29:07.614',1,'2018-12-31 18:29:07','2018-12-31 18:29:07'),
	(4,'lx','POST','/sys/user/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:49.449','2018-12-31 18:38:49.946',1,'2018-12-31 18:38:49','2018-12-31 18:38:50'),
	(5,'lx','GET','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:49.613','2018-12-31 18:38:49.949',1,'2018-12-31 18:38:49','2018-12-31 18:38:50'),
	(6,'lx','POST','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:51.366','2018-12-31 18:38:51.714',1,'2018-12-31 18:38:51','2018-12-31 18:38:51'),
	(7,'lx','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:51.424','2018-12-31 18:38:51.692',1,'2018-12-31 18:38:51','2018-12-31 18:38:51'),
	(8,'lx','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:53.247','2018-12-31 18:38:53.493',1,'2018-12-31 18:38:53','2018-12-31 18:38:53'),
	(9,'lx','POST','/sys/user/sign/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:55.339','2018-12-31 18:38:55.665',1,'2018-12-31 18:38:55','2018-12-31 18:38:55'),
	(10,'lx','POST','/sys/access/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 18:38:56.673','2018-12-31 18:38:56.986',1,'2018-12-31 18:38:56','2018-12-31 18:38:57'),
	(11,'lx','GET','/sys/user/signOut','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:13:14.000','2018-12-31 19:13:14.727',1,'2018-12-31 19:13:14','2018-12-31 19:13:14'),
	(12,NULL,'POST','/sys/user/signIn','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:15.316','2018-12-31 19:14:15.750',1,'2018-12-31 19:14:15','2018-12-31 19:14:15'),
	(13,'user','GET','/sys/user/menu','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:15.999','2018-12-31 19:14:16.470',1,'2018-12-31 19:14:16','2018-12-31 19:14:16'),
	(14,'user','GET','/sys/user/info','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:16.000','2018-12-31 19:14:16.382',1,'2018-12-31 19:14:16','2018-12-31 19:14:16'),
	(15,'user','POST','/sys/user/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:23.929','2018-12-31 19:14:24.240',1,'2018-12-31 19:14:23','2018-12-31 19:14:24'),
	(16,'user','GET','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:23.930','2018-12-31 19:14:24.175',1,'2018-12-31 19:14:23','2018-12-31 19:14:24'),
	(17,'user','POST','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:31.057','2018-12-31 19:14:31.329',1,'2018-12-31 19:14:31','2018-12-31 19:14:31'),
	(18,'user','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:31.058','2018-12-31 19:14:31.309',1,'2018-12-31 19:14:31','2018-12-31 19:14:31'),
	(19,'user','GET','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:57.081','2018-12-31 19:14:57.340',1,'2018-12-31 19:14:57','2018-12-31 19:14:57'),
	(20,'user','POST','/sys/user/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:57.082','2018-12-31 19:14:57.464',1,'2018-12-31 19:14:57','2018-12-31 19:14:57'),
	(21,'user','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:58.328','2018-12-31 19:14:58.561',1,'2018-12-31 19:14:58','2018-12-31 19:14:58'),
	(22,'user','POST','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:14:58.327','2018-12-31 19:14:58.620',1,'2018-12-31 19:14:58','2018-12-31 19:14:58'),
	(23,'user','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:00.487','2018-12-31 19:15:00.717',1,'2018-12-31 19:15:00','2018-12-31 19:15:00'),
	(24,'user','POST','/sys/user/sign/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:01.620','2018-12-31 19:15:01.952',1,'2018-12-31 19:15:01','2018-12-31 19:15:02'),
	(25,'user','POST','/sys/access/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:02.887','2018-12-31 19:15:03.185',1,'2018-12-31 19:15:02','2018-12-31 19:15:03'),
	(26,'user','POST','/sys/user/sign/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:06.599','2018-12-31 19:15:06.907',1,'2018-12-31 19:15:06','2018-12-31 19:15:06'),
	(27,'user','POST','/sys/access/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:30.823','2018-12-31 19:15:31.125',1,'2018-12-31 19:15:30','2018-12-31 19:15:31'),
	(28,'user','POST','/sys/access/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:34.112','2018-12-31 19:15:34.407',1,'2018-12-31 19:15:34','2018-12-31 19:15:34'),
	(29,'user','POST','/sys/user/sign/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:15:36.212','2018-12-31 19:15:36.545',1,'2018-12-31 19:15:36','2018-12-31 19:15:36'),
	(30,'user','POST','/sys/access/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:21:01.285','2018-12-31 19:21:01.744',1,'2018-12-31 19:21:01','2018-12-31 19:21:01'),
	(31,'user','POST','/sys/user/sign/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:21:02.854','2018-12-31 19:21:03.155',1,'2018-12-31 19:21:02','2018-12-31 19:21:03'),
	(32,'user','POST','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:21:05.381','2018-12-31 19:21:05.668',1,'2018-12-31 19:21:05','2018-12-31 19:21:05'),
	(33,'user','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:21:05.388','2018-12-31 19:21:05.657',1,'2018-12-31 19:21:05','2018-12-31 19:21:05'),
	(34,'user','POST','/sys/user/sign/log/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:21:07.202','2018-12-31 19:21:07.544',1,'2018-12-31 19:21:07','2018-12-31 19:21:07'),
	(35,'user','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:21:53.102','2018-12-31 19:21:53.321',1,'2018-12-31 19:21:53','2018-12-31 19:21:53'),
	(36,'user','POST','/sys/role/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:22:18.079','2018-12-31 19:22:18.337',1,'2018-12-31 19:22:18','2018-12-31 19:22:18'),
	(37,'user','GET','/sys/menu/list','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:22:18.080','2018-12-31 19:22:18.331',1,'2018-12-31 19:22:18','2018-12-31 19:22:18'),
	(38,'user','GET','/sys/user/signOut','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0','2018-12-31 19:22:53.927','2018-12-31 19:22:54.216',1,'2018-12-31 19:22:53','2018-12-31 19:22:54');

/*!40000 ALTER TABLE `tb_sys_access_log` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_menu`;

CREATE TABLE `tb_sys_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `path` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '路径',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级菜单',
  `seq` int(11) NOT NULL DEFAULT '1' COMMENT '顺序',
  `domain_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统菜单';

LOCK TABLES `tb_sys_menu` WRITE;
/*!40000 ALTER TABLE `tb_sys_menu` DISABLE KEYS */;

INSERT INTO `tb_sys_menu` (`id`, `name`, `icon`, `path`, `parent_id`, `seq`, `domain_name`, `isactive`, `inserttime`, `updatetime`)
VALUES
	(1,'首页','h-icon-home','Home',NULL,1,NULL,1,'2018-12-23 16:30:18','2018-12-27 21:33:28'),
	(2,'系统管理','h-icon-setting','sys',NULL,2,NULL,1,'2018-12-23 16:31:11','2018-12-27 21:32:00'),
	(3,'用户管理','h-icon-user','/sys/user',2,1,NULL,1,'2018-12-23 16:32:22','2018-12-27 23:26:27'),
	(4,'角色管理','h-icon-users','/sys/role',2,2,NULL,1,'2018-12-23 16:33:49','2018-12-27 22:54:29'),
	(5,'菜单管理','h-icon-menu','/sys/menu',2,3,NULL,1,'2018-12-23 16:34:27','2018-12-27 21:35:25'),
	(6,'登录日志','h-icon-task','/sys/sign-log',2,4,'lx',1,'2018-12-27 23:15:15','2018-12-30 21:30:41'),
	(52,'请求日志','h-icon-task','/sys/access-log',2,5,NULL,1,'2018-12-30 21:26:42','2018-12-30 21:29:37');

/*!40000 ALTER TABLE `tb_sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rname` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `domain_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色';

LOCK TABLES `tb_sys_role` WRITE;
/*!40000 ALTER TABLE `tb_sys_role` DISABLE KEYS */;

INSERT INTO `tb_sys_role` (`id`, `rname`, `description`, `domain_name`, `isactive`, `inserttime`, `updatetime`)
VALUES
	(1,'管理员','系统管理员','lx',1,'2018-12-23 16:28:28','2018-12-30 17:16:25'),
	(2,'普通用户','普通用户角色','happy',1,'2018-12-23 16:29:06','2018-12-23 16:29:06'),
	(10,'仓管','仓库管理员',NULL,1,'2018-12-30 17:16:45','2018-12-30 17:16:45');

/*!40000 ALTER TABLE `tb_sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_role_menu`;

CREATE TABLE `tb_sys_role_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_role_id` int(11) NOT NULL COMMENT '角色ID',
  `sys_menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `domain_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=1076 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色对应菜单';

LOCK TABLES `tb_sys_role_menu` WRITE;
/*!40000 ALTER TABLE `tb_sys_role_menu` DISABLE KEYS */;

INSERT INTO `tb_sys_role_menu` (`id`, `sys_role_id`, `sys_menu_id`, `domain_name`, `isactive`, `inserttime`, `updatetime`)
VALUES
	(1,1,1,'lx',0,'2018-12-23 16:41:26','2018-12-30 21:32:18'),
	(2,1,2,'lx',0,'2018-12-23 16:41:38','2018-12-30 21:32:18'),
	(3,1,3,'lx',0,'2018-12-23 16:41:49','2018-12-30 21:32:18'),
	(4,1,4,'lx',0,'2018-12-23 16:42:09','2018-12-30 21:32:18'),
	(5,1,5,'lx',0,'2018-12-27 23:19:56','2018-12-30 21:32:18'),
	(1063,2,1,'lx',0,'2018-12-30 18:46:38','2018-12-30 18:46:49'),
	(1064,2,2,'lx',0,'2018-12-30 18:46:38','2018-12-30 18:46:49'),
	(1065,2,3,'lx',0,'2018-12-30 18:46:39','2018-12-30 18:46:49'),
	(1066,2,4,'lx',0,'2018-12-30 18:46:39','2018-12-30 18:46:49'),
	(1067,2,5,'lx',0,'2018-12-30 18:46:39','2018-12-30 18:46:49'),
	(1068,2,1,'lx',1,'2018-12-30 18:46:50','2018-12-30 18:46:50'),
	(1069,1,1,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18'),
	(1070,1,2,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18'),
	(1071,1,3,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18'),
	(1072,1,4,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18'),
	(1073,1,5,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18'),
	(1074,1,6,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18'),
	(1075,1,52,'lx',1,'2018-12-30 21:32:18','2018-12-30 21:32:18');

/*!40000 ALTER TABLE `tb_sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_sign_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_sign_log`;

CREATE TABLE `tb_sys_sign_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `ip_addr` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
  `user_agent` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'UA',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否成功',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统登录日志';

LOCK TABLES `tb_sys_sign_log` WRITE;
/*!40000 ALTER TABLE `tb_sys_sign_log` DISABLE KEYS */;

INSERT INTO `tb_sys_sign_log` (`id`, `username`, `ip_addr`, `user_agent`, `status`, `isactive`, `inserttime`, `updatetime`)
VALUES
	(1,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-30 18:48:37','2018-12-31 19:14:15'),
	(2,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-30 18:49:23','2018-12-31 19:14:15'),
	(3,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-30 18:50:49','2018-12-31 19:14:15'),
	(4,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-30 18:57:55','2018-12-31 19:14:15'),
	(5,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-31 16:29:45','2018-12-31 19:14:15'),
	(6,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-31 18:24:10','2018-12-31 19:14:15'),
	(7,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-31 18:24:37','2018-12-31 19:14:15'),
	(8,'user','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0',1,1,'2018-12-31 19:14:15','2018-12-31 19:14:15');

/*!40000 ALTER TABLE `tb_sys_sign_log` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_user`;

CREATE TABLE `tb_sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别 0:女，1:男',
  `access_token` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录token',
  `domain_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作人',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `birthday` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户';

LOCK TABLES `tb_sys_user` WRITE;
/*!40000 ALTER TABLE `tb_sys_user` DISABLE KEYS */;

INSERT INTO `tb_sys_user` (`id`, `account`, `password`, `mobile`, `name`, `email`, `sex`, `access_token`, `domain_name`, `isactive`, `inserttime`, `updatetime`, `birthday`)
VALUES
	(1,'lx','12345','13012312313','梁','123@126.com',1,NULL,'lx',1,'2018-12-23 16:27:20','2018-12-31 19:37:19','2018-12-06'),
	(2,'tjm','Aa123456','1232131223333','唐i','123@126.com',1,'9E3CFEA2BF81FE588FB922B9B7716E26F2A414A577F4CF39751B696F763561BA','lx',1,'2018-12-23 16:38:10','2018-12-30 15:50:12','2018-12-06'),
	(3,'user','Aa123456','123123123123','user','@qq.com',0,NULL,'lx',1,'2018-12-23 16:40:03','2018-12-31 19:22:54','2018-12-27');

/*!40000 ALTER TABLE `tb_sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_sys_user_role`;

CREATE TABLE `tb_sys_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_user_id` int(11) NOT NULL COMMENT '用户ID',
  `sys_role_id` int(11) NOT NULL COMMENT '角色ID',
  `domain_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色';

LOCK TABLES `tb_sys_user_role` WRITE;
/*!40000 ALTER TABLE `tb_sys_user_role` DISABLE KEYS */;

INSERT INTO `tb_sys_user_role` (`id`, `sys_user_id`, `sys_role_id`, `domain_name`, `isactive`, `inserttime`, `updatetime`)
VALUES
	(1,1,1,'lx',1,'2018-12-23 16:39:00','2018-12-23 16:39:06'),
	(2,2,1,'lx',1,'2018-12-23 16:39:26','2018-12-23 16:39:31'),
	(3,3,1,'tjm',1,'2018-12-23 16:39:40','2018-12-31 16:31:53');

/*!40000 ALTER TABLE `tb_sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
