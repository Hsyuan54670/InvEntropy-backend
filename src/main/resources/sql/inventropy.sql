-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventropy
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE DATABASE IF NOT EXISTS inventropy DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;
USE inventropy;

--
-- Table structure for table `tb_user_account`
--

DROP TABLE IF EXISTS `tb_user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名（唯一）',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `user_type` varchar(20) NOT NULL COMMENT '用户类型（teacher/admin）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_account`
--

LOCK TABLES `tb_user_account` WRITE;
/*!40000 ALTER TABLE `tb_user_account` DISABLE KEYS */;
INSERT INTO `tb_user_account` VALUES (1,'admin','123456','admin'),(2,'NCHU13312341234','123456','user'),(3,'NCHU13343214321','654321','user'),(7,'NCHU13314921921','123456','user'),(9,'NCHU18112341234','123456','user'),(10,'NCHU13955555555','123456','user');
/*!40000 ALTER TABLE `tb_user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_info`
--

DROP TABLE IF EXISTS `tb_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_info` (
  `id` int NOT NULL COMMENT '主键ID，与tb_user_account.id一致，一对一',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `gender` char(1) DEFAULT NULL COMMENT '性别（M/F）',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号（唯一）',
  `college` varchar(100) NOT NULL COMMENT '学院/部门',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `idx_phone` (`phone`),
  CONSTRAINT `tb_user_info_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_user_account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_info`
--

LOCK TABLES `tb_user_info` WRITE;
/*!40000 ALTER TABLE `tb_user_info` DISABLE KEYS */;
INSERT INTO `tb_user_info` VALUES (1,'管理员1号','M',18,'1339876987','管理学院'),(2,'小明','M',33,'13312341234','信息工程学院'),(3,'小白','F',22,'13343214321','白学院'),(7,'张三','M',35,'13314921921','信息工程学院'),(9,'李四','F',27,'18112341234','艺设学院'),(10,'王五','M',55,'13955555555','外国语学院');
/*!40000 ALTER TABLE `tb_user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


--
-- Table structure for table `tb_project_info`
--

DROP TABLE IF EXISTS `tb_project_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_project_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `project_name` varchar(200) NOT NULL COMMENT '项目名称',
  `project_type` varchar(50) DEFAULT NULL COMMENT '项目类型',
  `applicant` varchar(50) NOT NULL COMMENT '申请人姓名',
  `applicant_id` int NOT NULL COMMENT '申请人ID（关联tb_user_info.id）',
  `funds` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '项目总经费（元）',
  `remaining_funds` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '当前剩余经费（元）',
  `content` text COMMENT '项目内容',
  `start_time` datetime DEFAULT NULL COMMENT '计划开始时间',
  `deadline` datetime DEFAULT NULL COMMENT '计划截止日期',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统最后更新时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-待审核,1-已驳回,2-已逾期,3-进行中,4-已结项,5-已废弃',
  `reason` text COMMENT '最近一次驳回的详细理由',
  PRIMARY KEY (`id`),
  KEY `idx_applicant_id` (`applicant_id`),
  KEY `idx_status` (`status`),
  KEY `idx_deadline` (`deadline`),
  CONSTRAINT `tb_project_info_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `tb_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_project_info`
--

LOCK TABLES `tb_project_info` WRITE;
/*!40000 ALTER TABLE `tb_project_info` DISABLE KEYS */;
INSERT INTO `tb_project_info` VALUES (1,'通过','纵向项目','小明',2,10000.00,5000.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目1.docx','2025-12-26 11:20:56','2026-01-01 00:00:00','2026-01-07 09:28:43',5,'暂未审批'),(2,'不通过','横向项目','小明',2,9999999.00,9999999.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目2.docx','2025-12-26 11:21:12','2025-12-29 00:00:00','2026-01-07 09:05:37',2,'没钱'),(3,'速通','横向项目','小明',2,100.00,100.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目3.docx','2025-12-26 12:48:05','2025-12-27 00:00:00','2026-01-04 09:59:26',2,'暂未审批'),(4,'我白学了吗','校内项目','小白',3,799484.00,797171.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目4.docx','2025-12-26 14:44:23','2026-01-01 00:00:00','2026-01-04 09:59:26',2,'暂未审批'),(5,'项目10','横向项目','小白',3,45673.00,45673.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目10.docx','2025-12-26 14:45:09','2025-12-31 00:00:00','2026-01-04 09:59:26',2,'暂未审批'),(6,'项目12','纵向项目','小白',3,324432.00,324432.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目12.docx','2024-12-26 14:45:33','2025-01-29 00:00:00','2026-01-07 09:19:24',5,'暂未审批'),(7,'项目23','横向项目','小白',3,2313.00,2313.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目20.docx','2025-12-26 15:33:09','2025-12-30 00:00:00','2026-01-04 09:59:26',2,'暂未审批'),(8,'项目18','纵向项目','小白',3,24324.00,24324.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2025/12/项目18.docx','2025-12-26 15:33:31','2025-12-29 00:00:00','2026-01-04 09:59:26',2,'暂未审批'),(9,'111','纵向项目','小白',2,5000.00,4900.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/资料提交要求_数据库原理课程设计.doc','2026-01-04 18:37:00','2026-01-15 00:00:00','2026-01-07 09:48:11',3,'暂未审批'),(10,'111','纵向项目','aaaa',2,111.00,111.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/计划书.docx','2026-01-04 19:52:59','2026-01-31 00:00:00','2026-01-04 11:52:59',0,'暂未审批'),(11,'项目1','纵向项目','小明',2,34243.00,34243.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目1.docx','2026-01-07 16:53:52','2026-01-08 10:58:16','2026-01-07 08:53:52',0,'暂未审批'),(12,'项目2','横向项目','小明',2,324324.00,324324.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目2.docx','2026-01-07 16:54:18','2026-03-12 00:00:00','2026-01-07 08:54:18',0,'暂未审批'),(13,'项目3','横向项目','小明',2,132423.00,132423.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目19.docx','2026-01-07 16:54:36','2026-06-27 00:00:00','2026-01-07 09:03:37',1,'不好'),(14,'项目3','校内项目','小明',2,43500.00,43500.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目3.docx','2026-01-07 16:55:05','2028-01-01 00:00:00','2026-01-07 09:03:43',3,'暂未审批'),(15,'项目4','校内项目','小明',2,4324.00,4324.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目4.docx','2026-01-07 16:55:36','2026-05-22 00:00:00','2026-01-07 09:50:52',3,'暂未审批'),(16,'项目5','纵向项目','小明',2,3242.00,3242.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目5.docx','2026-01-07 16:55:57','2026-08-28 00:00:00','2026-01-07 09:06:14',4,'暂未审批'),(17,'项目6','横向项目','小明',2,432432.00,432432.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目6.docx','2026-01-07 16:56:14','2028-01-14 00:00:00','2026-01-07 08:56:14',0,'暂未审批'),(18,'项目7','横向项目','小明',2,5435435.00,5435435.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目7.docx','2026-01-07 16:56:40','2026-04-10 00:00:00','2026-01-07 08:56:40',0,'暂未审批'),(19,'项目8','纵向项目','小明',2,354.00,354.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目8.docx','2026-01-07 16:57:29','2028-02-17 00:00:00','2026-01-07 08:57:29',0,'暂未审批'),(20,'项目9','纵向项目','小明',2,34325345.00,34325345.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目9.docx','2026-01-07 16:58:08','2026-04-30 00:00:00','2026-01-07 08:58:08',0,'暂未审批'),(21,'项目10','纵向项目','小明',2,324324.00,324324.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目10.docx','2026-01-07 16:58:30','2026-01-23 00:00:00','2026-01-07 08:58:30',0,'暂未审批'),(22,'项目11','校内项目','小明',2,213123.00,213123.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目11.docx','2026-01-07 16:59:00','2027-01-29 00:00:00','2026-01-07 08:59:00',0,'暂未审批'),(23,'项目12','校内项目','小明',2,213123.00,213123.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目12.docx','2026-01-07 16:59:19','2027-03-19 00:00:00','2026-01-07 09:50:55',3,'暂未审批'),(24,'项目14','横向项目','小明',2,2432432.00,2432432.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目14.docx','2026-01-07 16:59:39','2026-01-30 00:00:00','2026-01-07 08:59:39',0,'暂未审批'),(25,'项目15','横向项目','小明',2,4324324.00,4324324.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目15.docx','2026-01-07 16:59:56','2026-01-30 00:00:00','2026-01-07 08:59:56',0,'暂未审批'),(26,'项目16','纵向项目','小明',2,999100.00,999100.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目16.docx','2026-01-07 17:00:37','2026-01-31 00:00:00','2026-01-07 09:00:37',0,'暂未审批'),(27,'项目17','纵向项目','小明',2,123213.00,123213.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目17.docx','2026-01-07 17:00:52','2027-01-09 00:00:00','2026-01-07 09:00:52',0,'暂未审批'),(28,'项目18','纵向项目','小明',2,99910000.00,99910000.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目18.docx','2026-01-07 17:01:13','2027-01-16 00:00:00','2026-01-07 09:01:13',0,'暂未审批'),(29,'项目19','横向项目','小明',2,23100.00,23100.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目19.docx','2026-01-07 17:01:38','2028-01-14 00:00:00','2026-01-07 09:01:38',0,'暂未审批'),(30,'项目20','横向项目','小明',2,2341.00,2341.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目20.docx','2026-01-07 17:02:03','2026-04-25 00:00:00','2026-01-07 09:02:03',0,'暂未审批'),(31,'数据太少了怎么办','纵向项目','小明',2,2345.00,2345.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目16.docx','2026-01-07 17:50:30','2026-10-31 00:00:00','2026-01-07 09:50:30',0,'暂未审批'),(32,'项目24','纵向项目','李四',9,10000.00,10000.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目19.docx','2026-01-07 20:27:59','2026-01-23 00:00:00','2026-01-07 12:27:59',0,'暂未审批'),(33,'项目24','校内项目','李四',9,80000.00,80000.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目9.docx','2026-01-07 20:28:45','2026-02-06 00:00:00','2026-01-07 12:28:45',0,'暂未审批'),(34,'123','纵向项目','dq',2,111.00,111.00,'https://inventropy.oss-cn-hangzhou.aliyuncs.com/2026/01/项目1.docx','2026-01-07 21:37:36','2026-01-17 00:00:00','2026-01-07 13:37:36',0,'暂未审批');
/*!40000 ALTER TABLE `tb_project_info` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `tb_funds_log`
--
DROP TABLE IF EXISTS `tb_funds_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_funds_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `project_id` int NOT NULL COMMENT '关联的项目ID',
  `applicant_id` int NOT NULL COMMENT '申请人ID（关联tb_user_info.id）',
  `applied_funds` decimal(12,2) NOT NULL COMMENT '申请经费金额（元）',
  `reason` text COMMENT '申请理由',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '审批状态：0-待审核,1-已驳回,2-已通过',
  `applied_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间',
  `approver_id` int DEFAULT NULL COMMENT '审批人ID（关联tb_user_account.id，管理员）',
  `comment` text COMMENT '审批意见',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`),
  KEY `approver_id` (`approver_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_applicant_id` (`applicant_id`),
  KEY `idx_applied_time` (`applied_time`),
  KEY `idx_status` (`status`),
  CONSTRAINT `tb_funds_log_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tb_project_info` (`id`),
  CONSTRAINT `tb_funds_log_ibfk_2` FOREIGN KEY (`applicant_id`) REFERENCES `tb_user_info` (`id`),
  CONSTRAINT `tb_funds_log_ibfk_3` FOREIGN KEY (`approver_id`) REFERENCES `tb_user_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='经费申请与审批记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_funds_log`
--

LOCK TABLES `tb_funds_log` WRITE;
/*!40000 ALTER TABLE `tb_funds_log` DISABLE KEYS */;
INSERT INTO `tb_funds_log` VALUES (1,1,2,0.00,'5000',2,'2025-12-26 03:38:42',1,'可以','2025-12-26 03:38:50'),(2,1,2,5000.00,'要用',2,'2025-12-26 03:41:27',1,'可以','2025-12-26 03:41:37'),(3,4,3,79754.00,'我没白学',0,'2025-12-26 06:48:26',NULL,NULL,'2025-12-26 06:48:26'),(4,4,3,2313.00,'吃多点好的',2,'2025-12-26 06:49:33',1,'同意','2025-12-26 09:25:28'),(5,4,3,2312.00,'为',0,'2025-12-26 09:05:00',NULL,NULL,'2025-12-26 09:05:00'),(6,4,3,321321312.00,'为',2,'2025-12-26 09:05:21',1,'xx','2025-12-26 09:19:00'),(7,4,3,232432432.00,'为',2,'2025-12-26 09:05:40',1,'ww','2025-12-26 09:18:00'),(8,9,2,0.00,'',2,'2026-01-04 10:49:09',1,'行吧','2026-01-07 09:29:01'),(10,9,2,0.00,'11',0,'2026-01-04 12:13:58',NULL,NULL,'2026-01-04 12:13:58'),(12,9,2,100.00,'要花',1,'2026-01-07 09:46:48',1,'不合适','2026-01-07 09:46:57'),(13,9,2,100.00,'要花',2,'2026-01-07 09:48:00',1,'可以','2026-01-07 09:48:11'),(14,9,2,100.00,'要花',0,'2026-01-07 09:48:02',NULL,NULL,'2026-01-07 09:48:02'),(15,9,2,100.00,'要花',0,'2026-01-07 09:48:04',NULL,NULL,'2026-01-07 09:48:04'),(16,9,2,-1.00,'可以吗',1,'2026-01-07 09:54:14',1,'不行','2026-01-07 09:54:38'),(17,14,2,321.00,'好吧',0,'2026-01-07 09:56:28',NULL,NULL,'2026-01-07 09:56:28');
/*!40000 ALTER TABLE `tb_funds_log` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `tb_project_log`
--

DROP TABLE IF EXISTS `tb_project_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_project_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `project_id` int NOT NULL COMMENT '关联的项目ID',
  `old_status` tinyint NOT NULL COMMENT '变更前的状态',
  `new_status` tinyint NOT NULL COMMENT '变更后的状态',
  `reason` text NOT NULL COMMENT '状态变更的详细理由',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审批操作时间',
  `approver_id` int NOT NULL COMMENT '审批人ID（关联tb_user_account.id，管理员）',
  PRIMARY KEY (`id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_approver_id` (`approver_id`),
  KEY `idx_create_time` (`create_time`),
  CONSTRAINT `tb_project_log_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tb_project_info` (`id`),
  CONSTRAINT `tb_project_log_ibfk_2` FOREIGN KEY (`approver_id`) REFERENCES `tb_user_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目状态审批流水日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_project_log`
--

LOCK TABLES `tb_project_log` WRITE;
/*!40000 ALTER TABLE `tb_project_log` DISABLE KEYS */;
INSERT INTO `tb_project_log` VALUES (1,1,-1,0,'创建项目','2025-12-26 03:20:56',2),(2,2,-1,0,'创建项目','2025-12-26 03:21:12',2),(3,1,0,3,'审批通过','2025-12-26 03:37:29',1),(4,2,0,1,'没钱','2025-12-26 03:37:34',1),(5,3,-1,0,'创建项目','2025-12-26 04:48:05',2),(6,3,0,3,'审批通过','2025-12-26 04:48:12',1),(7,3,4,4,'经过讨论决定','2025-12-26 04:48:19',1),(8,4,-1,0,'创建项目','2025-12-26 06:44:23',3),(9,5,-1,0,'创建项目','2025-12-26 06:45:09',3),(10,6,-1,0,'创建项目','2025-12-26 06:45:32',3),(11,4,0,3,'审批通过','2025-12-26 06:45:42',1),(12,6,0,3,'审批通过','2025-12-26 06:45:45',1),(13,6,3,2,'超时过期','2025-12-26 07:13:06',1),(14,7,-1,0,'创建项目','2025-12-26 07:33:09',3),(15,8,-1,0,'创建项目','2025-12-26 07:33:31',3),(16,5,0,3,'审批通过','2025-12-26 07:36:06',1),(17,5,4,4,'经过讨论决定','2025-12-26 08:11:33',1),(18,7,0,3,'审批通过','2025-12-26 09:31:10',1),(19,8,0,2,'超时过期','2026-01-04 09:59:26',1),(20,2,1,2,'超时过期','2026-01-04 09:59:26',1),(21,1,3,2,'超时过期','2026-01-04 09:59:26',1),(22,4,3,2,'超时过期','2026-01-04 09:59:26',1),(23,7,3,2,'超时过期','2026-01-04 09:59:26',1),(24,3,4,2,'超时过期','2026-01-04 09:59:26',1),(25,5,4,2,'超时过期','2026-01-04 09:59:26',1),(26,9,-1,0,'创建项目','2026-01-04 10:36:59',2),(27,9,0,3,'审批通过','2026-01-04 10:48:33',2),(28,10,-1,0,'创建项目','2026-01-04 11:52:58',2),(29,11,-1,0,'创建项目','2026-01-07 08:53:51',2),(30,12,-1,0,'创建项目','2026-01-07 08:54:18',2),(31,13,-1,0,'创建项目','2026-01-07 08:54:36',2),(32,14,-1,0,'创建项目','2026-01-07 08:55:04',2),(33,15,-1,0,'创建项目','2026-01-07 08:55:36',2),(34,16,-1,0,'创建项目','2026-01-07 08:55:57',2),(35,17,-1,0,'创建项目','2026-01-07 08:56:13',2),(36,18,-1,0,'创建项目','2026-01-07 08:56:39',2),(37,19,-1,0,'创建项目','2026-01-07 08:57:28',2),(38,20,-1,0,'创建项目','2026-01-07 08:58:08',2),(39,21,-1,0,'创建项目','2026-01-07 08:58:29',2),(40,22,-1,0,'创建项目','2026-01-07 08:58:59',2),(41,23,-1,0,'创建项目','2026-01-07 08:59:19',2),(42,24,-1,0,'创建项目','2026-01-07 08:59:38',2),(43,25,-1,0,'创建项目','2026-01-07 08:59:56',2),(44,26,-1,0,'创建项目','2026-01-07 09:00:36',2),(45,27,-1,0,'创建项目','2026-01-07 09:00:51',2),(46,28,-1,0,'创建项目','2026-01-07 09:01:12',2),(47,29,-1,0,'创建项目','2026-01-07 09:01:37',2),(48,30,-1,0,'创建项目','2026-01-07 09:02:03',2),(49,13,0,1,'不好','2026-01-07 09:03:37',1),(50,14,0,3,'审批通过','2026-01-07 09:03:43',1),(51,16,0,3,'审批通过','2026-01-07 09:04:01',1),(52,1,5,5,'经过讨论决定','2026-01-07 09:05:27',1),(53,1,5,2,'超时过期','2026-01-07 09:05:27',1),(54,1,5,5,'经过讨论决定','2026-01-07 09:05:30',1),(55,1,5,2,'超时过期','2026-01-07 09:05:30',1),(56,2,5,5,'经过讨论决定','2026-01-07 09:05:35',1),(57,2,5,2,'超时过期','2026-01-07 09:05:35',1),(58,2,5,5,'经过讨论决定','2026-01-07 09:05:37',1),(59,2,5,2,'超时过期','2026-01-07 09:05:37',1),(60,16,4,4,'经过讨论决定','2026-01-07 09:06:14',1),(61,1,5,5,'经过讨论决定','2026-01-07 09:07:17',1),(62,1,5,2,'超时过期','2026-01-07 09:07:17',1),(63,6,5,5,'经过讨论决定','2026-01-07 09:14:55',1),(64,6,5,2,'超时过期','2026-01-07 09:14:55',1),(65,6,5,5,'经过讨论决定','2026-01-07 09:15:23',1),(66,6,5,2,'超时过期','2026-01-07 09:15:24',1),(67,6,5,5,'经过讨论决定','2026-01-07 09:19:24',1),(68,1,5,5,'经过讨论决定','2026-01-07 09:28:43',1),(69,31,-1,0,'创建项目','2026-01-07 09:50:29',2),(70,15,0,3,'审批通过','2026-01-07 09:50:52',1),(71,23,0,3,'审批通过','2026-01-07 09:50:55',1),(72,32,-1,0,'创建项目','2026-01-07 12:27:59',9),(73,33,-1,0,'创建项目','2026-01-07 12:28:45',9),(74,34,-1,0,'创建项目','2026-01-07 13:37:35',2);
/*!40000 ALTER TABLE `tb_project_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- ROUTINES FOR AUTOUPDATE
--
DELIMITER //

CREATE
    definer = root@localhost procedure get_project_info_with_status_update()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_project_id INT;
    DECLARE v_old_status INT;
    DECLARE cur CURSOR FOR
        SELECT id, status
        FROM tb_project_info
        WHERE deadline < NOW() AND status != 2 AND status != 5;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 打开游标，记录需要更新的项目
    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_project_id, v_old_status;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 更新项目状态
        UPDATE tb_project_info
        SET status = 2
        WHERE id = v_project_id;

        -- 记录日志（在存储过程中直接插入，避免触发器）
        INSERT INTO tb_project_log(
            project_id,
            old_status,
            new_status,
            reason,
            approver_id
        ) VALUES (
                     v_project_id,
                     v_old_status,
                     2,
                     '超时过期',
                     1  -- 系统操作
                 );
    END LOOP;

    CLOSE cur;

    -- 查询数据
    SELECT * FROM tb_project_info;
END//

DELIMITER ;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-05 17:34:16
