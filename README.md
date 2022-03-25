# 🎉前言

[![licence](https://img.shields.io/badge/licence-MIT-blue)](https://img.shields.io/badge/licence-MIT-blue)
[![release](https://img.shields.io/badge/release-v1.0-blue)](https://img.shields.io/badge/release-v1.0-blue)
[![](https://img.shields.io/badge/切面-aop-brightgreen)](https://img.shields.io/badge/%E5%88%87%E9%9D%A2-aop-brightgreen)
[![](https://img.shields.io/badge/日志-slf4j-yellow)](https://img.shields.io/badge/%E6%97%A5%E5%BF%97-slf4j-yellow)
[![数据库连接池](https://img.shields.io/badge/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5%E6%B1%A0-druid-orange)](https://img.shields.io/badge/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5%E6%B1%A0-druid-orange)
[![分页工具](https://img.shields.io/badge/%E5%88%86%E9%A1%B5-pagehelper-blue)](https://img.shields.io/badge/%E5%88%86%E9%A1%B5-pagehelper-blue)
[![本地缓存](https://img.shields.io/badge/%E6%9C%AC%E5%9C%B0%E7%BC%93%E5%AD%98-caffine-red)](https://img.shields.io/badge/%E6%9C%AC%E5%9C%B0%E7%BC%93%E5%AD%98-caffine-red)
[![安全](https://img.shields.io/badge/%E5%AE%89%E5%85%A8-springsecurity-green)](https://img.shields.io/badge/%E5%AE%89%E5%85%A8-springsecurity-green)
[![过滤器](https://img.shields.io/badge/%E8%BF%87%E6%BB%A4%E5%99%A8-filter-blue)](https://img.shields.io/badge/%E8%BF%87%E6%BB%A4%E5%99%A8-filter-blue)
[![拦截器](https://img.shields.io/badge/%E6%8B%A6%E6%88%AA%E5%99%A8-intercepter-blue)](https://img.shields.io/badge/%E6%8B%A6%E6%88%AA%E5%99%A8-intercepter-blue)
[![单点登录](https://img.shields.io/badge/%E5%8D%95%E7%82%B9%E7%99%BB%E5%BD%95-cas-orange)](https://img.shields.io/badge/%E5%8D%95%E7%82%B9%E7%99%BB%E5%BD%95-cas-orange)

​        项目技术栈包括SpringBoot的AOP、Slf4j日志门面以及对应日志实现、全局异常处理、自定义注解、自定义Banner、Druid数据库连接池、MyBatis数据库映射、PageHelper分页、Caffine本地缓存、SpringSecurity安全、Filter过滤器、Interceptor拦截器、SSO单点登录等功能的集成实现，自定义线程池、多线程、延时队列、定时任务、JVM调优、Redisson分布式锁、Lua限流、LRU缓存、雪花算法，同时包括springboot生态组件、Redis缓存、ElasticSearch搜索、Kafka消息队列、Prometheus监控、MinIO文件系统相关的代码以及源码级别的集成开发，Dockerfile与Docker-compose部署、Jenkins管道、Shell脚本、Nginx反向代理，配套对应的思维导图、文档和对应的代码。

​        你也可以理解为这是一个源码理解、技术组件实战和技术组件集成的项目。

## 目录🚥
* springboot-aop
* springboot-log
* springboot-custom-dev
* springboot-mybatis
* springboot-cache
* springboot-security
* springboot-sso-cas
* springboot-threadPoolExecutor
* springboot-jvm
* springboot-quartz
* springboot-redisson
* springboot-lua
* springboot-components
* springboot-redis
* springboot-elasticsearch
* springboot-kafka
* springboot-prometheus
* springboot-minio
* springboot-docker
* springboot-jenkins
* springboot-nginx
* springboot-shells

## 详细文档

| 序号 | 组件         | 文档                                                         |
| ---- | ------------ | ------------------------------------------------------------ |
| 01   | mybatis-plus | [SpringBoot快速集成Mybatis-plus](springboot-mybatisplus/README.md) |
| 02   | spring task  | [SpringBoot集成Spring Task快速定时任务](springboot-scheduletask/README.md) |
|      |              |                                                              |
|      |              |                                                              |

## 功能🎨

> 由点及面，由一个个小的功能组件组建起生产级的完整功能。-- 你所看到的惊艳，都曾被平庸历练。

* 登录功能
* 搜索功能
* 社交功能
* 存储功能
* 办公功能
* 视频功能
* 推荐功能
* 支付功能
* 游戏功能
## 架构🗼

| 部件           | 简介                                                         |
| -------------- | ------------------------------------------------------------ |
| core基础       | 包括AOP、日志、自定义注解、异常、全局异常、数据库连接池、Cookie、Seesion等基础功能 |
| custom-dev进阶 | 包括多线程开发、自定义线程池、JVM调优、JVM诊断、分布式ID、限流算法、分布式锁、延时队列、定时任务、RPC、异步非阻塞编程、Reactot模型等 |
| springboot组件 | 包括数据库映射、安全、SSO、本地缓存、消息队列、搜索等核心组件 |
| other核心组件   | 包括Docker、Jenkins、Nginx等SpringBoot生态外的组件           |
## 参考链接
[springboot-example项目](https://github.com/L316476844/springbootexample) <br>
[JeeSpringCloudV2.5-互联网云快速开发框架](https://github.com/2014shijina2014/jeeSpringCloud)
