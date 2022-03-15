# SpringBoot集成Spring Task快速定时任务

<div align=center>
<img src="http://pic.deepinsea.top/images/2022/03/15/202203152208642.jpg">
</div>


## 背景

> 业务场景

思考下面几个业务场景的解决方案:

- 支付系统每天凌晨1点跑批，进行一天清算，每月1号进行上个月清算
- 电商整点抢购，商品价格8点整开始优惠
- 12306购票系统，超过30分钟没有成功支付订单的，进行回收处理
- 商品成功发货后，需要向客户发送短信提醒

> 类似的业务场景非常多，我们怎么解决？

很多业务场景需要我们**某一特定的时刻**去做某件任务，定时任务解决的就是这种业务场景。

一般来说，系统可以使用**消息传递**代替部分定时任务，两者有很多相似之处，可以相互替换场景。

但在某些场景下不能互换：

- 时间驱动/事件驱动：内部系统一般可以通过时间来驱动，但涉及到外部系统，则只能使用时间驱动。如怕取外部网站价格，每小时爬一次
- 批量处理/逐条处理：批量处理堆积的数据更加高效，在不需要实时性的情况下比消息中间件更有优势。而且有的业务逻辑只能批量处理。如移动每个月结算我们的话费
- 实时性/非实时性：消息中间件能够做到实时处理数据，但是有些情况下并不需要实时，比如：vip升级
- 系统内部/系统解耦：定时任务调度一般是在系统内部，而消息中间件可用于两个系统间

## 技术选型

因此从上面可知，这种周期性的任务场景，可以考虑使用定时任务进行解决。因此，考虑进行定时任务技术选型：

> 快速实现的定时任务

- Timer：是一个定时器类，通过该类可以为指定的定时任务进行配置。TimerTask类是一个定时任务类，该类实现了Runnable接口，缺点异常未检查会中止线程
- ScheduledExecutorService：相对延迟或者周期作为定时任务调度，缺点没有绝对的日期或者时间
- Spring定时框架：配置简单功能较多，如果系统使用单机的话可以优先考虑spring定时器

> 复杂业务场景(分布式)

- Quartz:  Java事实上的定时任务标准。但Quartz关注点在于定时任务而非数据，并无一套根据数据处理而定制化的流程。虽然Quartz可以基于数据库实现作业的高可用，但缺少分布式并行调度的功能
- TBSchedule：阿里早期开源的分布式任务调度系统。代码略陈旧，使用timer而非线程池执行任务调度。众所周知，timer在处理异常状况时是有缺陷的。而且TBSchedule作业类型较为单一，只能是获取/处理数据一种模式。还有就是文档缺失比较严重
- Elastic-job：当当开发的弹性分布式任务调度系统，功能丰富强大，采用Zookeeper实现分布式协调，实现任务高可用以及分片，目前是版本2.15，并且可以支持云开发。
- Saturn：是唯品会自主研发的分布式的定时任务的调度平台，基于当当的elastic-job 版本1开发，并且可以很好的部署到docker容器上。
- xxl-job: 是大众点评员工徐雪里于2015年发布的分布式任务调度平台，是一个轻量级分布式任务调度框架，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。

## 快速开始

### 1.创建SpringBoot项目

> Spring定时任务是SpringBoot内置的框架，因此初始化SpringBoot项目后无需再次引入其他依赖。

pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### 2.开启定时任务

在 application 启动类中使用 @EnableScheduling 注解开启定时任务，会自动扫描，相当于一个开关，把这个开关开完之后，那么只要在相应的任务类中做相应的任务，那么就会被 spring boot 容器扫描到，扫描到后，根据任务定义的时间会自动运行。

SpringbootScheduletaskApplication.java

```java
@SpringBootApplication
@EnableScheduling
public class SpringbootScheduletaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduletaskApplication.class, args);
    }

}
```

### 3.编写定时任务配置类

ScheduleTaskConfig.java

```java
/**
 * @Auther deepinsea
 * @Date 2022/3/14
 * 定时任务配置类
 */
@Component
public class ScheduleTaskConfig {

    // 每隔5s执行一次
    @Scheduled(cron = "0 0 0 * * ?")
    public void printSay() {
        System.out.println("每隔5s执行一次：" + new Date());
    }
}
```

### 4.测试使用

```bash
每隔5s执行一次：Tue Mar 15 22:48:30 CST 2022
每隔5s执行一次：Tue Mar 15 22:48:35 CST 2022

Process finished with exit code 130
```

## 参考链接

* [Spring的@Scheduled注解实现定时任务](https://blog.csdn.net/qidasheng2012/article/details/84386662)
* [分布式定时任务框架选型，写得太好了！](https://blog.csdn.net/weixin_36380516/article/details/119066904)

* [springboot整合springtask - 菩提树下的丁春秋 - 博客园](https://www.cnblogs.com/xiufengchen/p/10327609.html)
* [SpringTask的入门使用_到什么地方才能停下的博客-CSDN博客_spring task](https://blog.csdn.net/javaxiaibai0414/article/details/91551712)
* [分布式定时任务框架选型，写得太好了！_Java知音_的博客-CSDN博客](https://blog.csdn.net/weixin_36380516/article/details/119066904)
*  [分布式定时任务调度框架选型 - 博客园](https://www.cnblogs.com/ssslinppp/p/12485273.html)
* [定时任务的场景_百度搜索](https://www.baidu.com/s?tn=44004473_18_oem_dg&ie=utf-8&wd=定时任务的场景)
*  [定时器的使用场景分析_再写三行的博客-CSDN博客_定时任务应用场景](https://blog.csdn.net/baidu_33403616/article/details/89225285)
*  [笔记---ScheduledExecutorService - 简书](https://www.jianshu.com/p/aaab48e5902d)
*  [ScheduledExecutorService定时周期执行指定的任务_涛涛_2009的博客-CSDN博客](https://blog.csdn.net/tsyj810883979/article/details/8481621)

 
