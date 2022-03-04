# SpringBoot集成Mybatis-Plus

> 介绍

Mybatis-Plus是一款基于Mybatis的快速生成建表SQL与数据库增、删、改、查语句的ORM框架。

## 安装

> 这里`spring-boot-starter-parent`版本为2.6.4

```xml
<!-- mybatis-plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.1</version>
</dependency>
```

## 快速开始

### 创建数据表

schema_user.sql

```sql
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id    BIGINT(20) NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DELETE
FROM user;
INSERT INTO user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Tom', 28, 'test3@baomidou.com'),
       (4, 'Sandy', 21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');
```