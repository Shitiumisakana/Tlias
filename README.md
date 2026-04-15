# Tlias 教学管理系统 (Tlias Teaching Management System)

本项目是基于 **Spring Cloud** 微服务架构开发的综合性教学管理平台。系统采用敏捷开发模式，旨在为高校或培训机构提供高效的学生管理、选课及教务自动化解决方案。
前端链接https://github.com/Shitiumisakana/Tlias-front

---

## 🚀 核心技术栈

### 后端架构

* **基础框架**: Spring Boot 2.7.x
* **注册中心/配置中心**: Nacos
* **服务网关**: Spring Cloud Gateway
* **持久层**: MySQL 8.0 + MyBatis-Plus

### 中间件 & 增强

* **消息中间件**: RabbitMQ (实现业务解耦与超时处理)
* **分布式事务**: Seata (AT 模式保障数据一致性)
* **缓存方案**: Redis (多级缓存策略)
* **安全认证**: JWT (无状态令牌验证)

---

## ✨ 核心亮点

### 1\. 微服务架构演进

![](https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcRBXCx277eW-GKW-B30oXpNXheRj7WyD5tx9BR05cS0RPPXaozpstaZ6aoyBuMjaVSM07gVwLsRfR83OoAXSyuNL4s_MxGq8QipGYbU9KocrAx9LDk)  

> 主导系统从单体架构向微服务化的深度拆分，划分为 **5 个核心微服务**。通过规范化接口设计，实现了业务的高度解耦和各司其职。

### 2\. 高并发缓存治理

> 针对高频访问的课程与师资信息，采用 **Redis 多级缓存策略**。
> 
> * 有效解决缓存击穿、穿透与雪崩问题。
> * 热点数据查询响应时间成功控制在 **5ms 以内**。

### 3\. 分布式事务一致性

> 在学生选课、资源扣减等跨库场景中，集成 **Seata AT 模式**。利用全局锁与 `undo_log` 回滚机制，彻底解决分布式环境下的数据强一致性痛点。

### 4\. 基于延迟队列的业务闭环

> 应用 **RabbitMQ 延迟队列** 实现超时自动处理：
> 
> * 用户提交选课后 30 分钟未确认，系统自动取消订单并回滚资源。
> * 有效提升了系统资源的自动化利用率。

---

## 📂 项目模块说明

Plaintext

```
tlias-parent
├── tlias-gateway        # 统一入口、路由转发与权限校验
├── tlias-auth           # 认证中心、JWT 令牌签发
├── tlias-student        # 学生模块：信息管理、个人画像、搜索
├── tlias-course         # 课程模块：选课逻辑、资源调度
└── tlias-common         # 全局通用组件、异常处理、Result 封装
```

---

## 🛠️ 快速开始

### 1\. 环境准备

在运行本项目前，请确保本地已安装并配置以下环境：

* **核心组件**:

* JDK: 11+
* Maven: 3.6+
* **中间件**:

* 数据库: MySQL 8.0
* 缓存: Redis
* 消息队列: RabbitMQ
* **服务治理**:

* 启动 **Nacos Server** 作为服务注册与配置中心。

### 2\. 配置与启动

请严格按照以下步骤进行部署：

1. **初始化数据库**

* 执行项目根目录下 `/sql` 文件夹内的 SQL 脚本，确保表结构及初始数据加载成功。
2. **修改配置文件**

* 检查各微服务模块 `src/main/resources` 目录下的 `application.yml` 或 `bootstrap.yml`。
* **必填项**: 修改数据库连接（MySQL URL）、账号密码以及各中间件（Redis/RabbitMQ/Nacos）的访问地址。
3. **依次启动服务**

* **Step 1**: 启动 Nacos 环境。
* **Step 2**: 启动 Gateway 网关服务。
* **Step 3**: 启动各业务微服务。
