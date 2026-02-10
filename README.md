# InvEntropy - 高校教师科研项目管理系统

## 项目简介
InvEntropy是一个基于Java技术栈开发的高校教师科研项目管理系统后端，
旨在解决传统科研项目管理中存在的流程繁琐、信息不透明、经费控制困难等问题。
该系统实现了项目全生命周期管理、经费申请审批、数据统计分析等功能，
为高校科研管理提供高效、透明的解决方案。

## 技术栈
- **后端框架**: Spring Boot 3.5.8
- **持久层**: MyBatis 3.0.3
- **数据库**: MySQL 8.0
- **缓存**: Redis 7.0
- **安全认证**: JWT + RSA加密
- **构建工具**: Maven 3.8.6
- **开发语言**: Java 21

## 核心功能

### 1. 用户管理模块
- 实现教师和管理员的身份认证（RSA加密传输密码）
- JWT令牌生成与验证，实现无状态登录
- 密码有效期验证和随机数防重放攻击
- 基于Redis的登录状态管理

### 2. 项目管理模块
- 教师在线申报科研项目，支持项目类型、经费预算等信息填写
- 项目全生命周期状态管理：待审核、已驳回、进行中、已结项、已废弃等
- 管理员审批流程，支持通过/驳回操作并记录审批意见
- 项目进度跟踪和截止日期自动逾期提醒

### 3. 经费管理模块
- 教师提交经费申请，系统自动验证申请金额是否超过项目剩余经费
- 经费审批流程，支持多级审批和审批意见记录
- 经费使用记录追踪，实时更新项目剩余经费
- 经费使用情况统计与分析

### 4. 统计分析模块
- 项目状态分布统计
- 经费使用情况统计
- 教师项目参与情况统计
- 自动生成各类数据报表

## 项目结构

```
src/main/
├── java/com/hsyuan/inventropy/
│   ├── controller/       # 控制器层，处理HTTP请求
│   ├── entity/           # 实体类，映射数据库表
│   ├── mapper/           # MyBatis映射接口
│   ├── pojo/             # 数据传输对象
│   ├── service/          # 业务逻辑层
│   │   └── impl/         # 业务逻辑实现
│   ├── utils/            # 工具类（JWT、RSA、Redis等）
│   ├── config/           # 配置类
│   ├── constants/        # 常量定义
│   ├── exception/        # 异常处理
│   └── interceptor/      # 拦截器
└── resources/
    ├── mapper/           # MyBatis XML映射文件
    ├── sql/              # SQL脚本
    ├── application.yml   # 系统配置
    └── static/           # 静态资源
```

## 核心技术亮点

1. **安全机制**：采用RSA非对称加密保护密码传输，JWT实现无状态认证，Redis防止重放攻击
2. **事务管理**：使用Spring声明式事务保证数据一致性，特别是在经费审批和项目状态变更时
3. **性能优化**：Redis缓存热点数据，MyBatis延迟加载减少数据库查询，合理的索引设计
4. **代码质量**：采用分层架构，严格遵循SOLID原则，代码注释完善，便于维护和扩展
5. **异常处理**：统一的异常处理机制，提供友好的错误信息

## 数据库设计

系统包含以下核心表：
- `tb_user_account`: 用户账号信息
- `tb_user_info`: 用户详细信息
- `tb_project_info`: 项目基本信息
- `tb_funds_log`: 经费申请记录
- `tb_project_log`: 项目状态变更记录

## 快速开始

### 环境要求
- JDK 21+
- MySQL 8.0+
- Redis 7.0+
- Maven 3.8+

### 部署步骤

1. **克隆仓库**
   ```bash
   git clone https://github.com/yourusername/InvEntropy.git
   cd InvEntropy
   ```

2. **配置数据库**
   - 创建数据库 `inventropy`
   - 执行 `src/main/resources/sql/inventropy.sql` 创建表结构并导入初始数据

3. **配置Redis**
   - 确保Redis服务运行在默认端口6379

4. **构建项目**
   ```bash
   mvn clean package -DskipTests
   ```

5. **启动服务**
   ```bash
   java -jar target/InvEntropy-0.0.1-SNAPSHOT.jar
   ```

## 接口文档

系统提供RESTful API接口，主要包括：

- 用户认证接口：`/login`, `/login/auth/publicKey`
- 项目管理接口：`/project/*`
- 经费管理接口：`/funds/*`
- 统计分析接口：`/statistics/*`

## 学习与收获

通过该项目，我深入理解了：
- Spring Boot框架的核心原理和最佳实践
- 企业级应用的安全认证机制设计
- 复杂业务流程的事务管理
- 性能优化和系统架构设计
- 版本控制