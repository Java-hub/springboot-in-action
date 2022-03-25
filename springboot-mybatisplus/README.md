# SpringBoot集成Mybatis-plus

<img src="https://baomidou.com/img/logo.svg" alt="img" style="zoom:25%;" />

## 简介

[MyBatis-Plus](https://github.com/baomidou/mybatis-plus)（简称 MP）是一个 [MyBatis](https://www.mybatis.org/mybatis-3/)的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

因此，mybatis-plus包含mybatis的所有功能，因此无需再次引入mybatis。

>  功能

- 无侵入：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- 损耗小：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- 强大的 CRUD 操作：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- 支持 Lambda 形式调用：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- 支持 ActiveRecord 模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- 支持自定义全局通用操作：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- 分页插件支持多种数据库：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

MyBatis-plus 是一款 Mybatis 增强工具，用于简化开发，提高效率。下文使用缩写 mp来简化表示 MyBatis-plus，本文主要介绍 mp 搭配 Spring Boot 的使用。

## 安装

> Maven Repository仓库官方最新依赖

**Maven**

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.1</version>
</dependency>
```

**Gradle**

```groovy
compile group: 'com.baomidou', name: 'mybatis-plus-boot-starter', version: '3.5.1'
```

## 快速开始

官方文档推荐的快速集成文档，一共分为5步，分别为创建sql文件、添加pom依赖、配置jdbc连接、继承BaseMapper生成CRUD方法与测试使用。

### 1.创建一个User表

有一张 `User` 表，其表结构如下：

| id   | name   | age  | email              |
| ---- | ------ | ---- | ------------------ |
| 1    | Jone   | 18   | test1@baomidou.com |
| 2    | Jack   | 20   | test2@baomidou.com |
| 3    | Tom    | 28   | test3@baomidou.com |
| 4    | Sandy  | 21   | test4@baomidou.com |
| 5    | Billie | 24   | test5@baomidou.com |

创建对应的数据表 Schema 的表结构和表数据：

schema_user.sql

```sql
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);
```

user_data.sql

```sql
DELETE FROM user;
INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

### 2.添加Mybatis-Plus依赖

引入 Spring Boot Starter 父工程：

pom.xml

```xml
<parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.6.4</version>
      <relativePath/>
  </parent>

<dependencies>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter</artifactId>
   </dependency>
   <!-- mybatis-plus -->
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-boot-starter</artifactId>
       <version>3.5.1</version>
   </dependency>
   <!-- mysql连接器 -->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <scope>runtime</scope>
   </dependency>
   <!-- spring自动配置依赖 -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-configuration-processor</artifactId>
       <optional>true</optional>
   </dependency>
   <!-- lombok -->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <optional>true</optional>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-test</artifactId>
       <scope>test</scope>
   </dependency>
</dependencies>
```

### 3.配置数据库

在 `application.yml` 配置文件中添加 H2 数据库的相关配置：

```yaml
server:
  port: 8080
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot-in-action?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=true
    username: root
    password: 123456
```

在 Spring Boot 启动类中添加 `@MapperScan` 注解，扫描 Mapper 文件夹：

```java
@SpringBootApplication
@MapperScan("com.deepinsea.springbootmybatisplus.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

### 4.创建一个实体类

编写实体类 `User.java`（此处使用了 [Lombok](https://www.projectlombok.org/)简化代码）

```java
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

### 5.创建一个Mapper接口

编写 Mapper 类 `UserMapper.java`

```java
public interface UserMapper extends BaseMapper<User> {

}
```

### 6.测试使用

添加测试类，进行功能测试：

```java
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
```

> UserMapper 中的 `selectList()` 方法的参数为 MP 内置的条件封装器 `Wrapper`，所以不填写就是无任何条件

控制台输出：

```bash
User(id=1, name=Jone, age=18, email=test1@baomidou.com)
User(id=2, name=Jack, age=20, email=test2@baomidou.com)
User(id=3, name=Tom, age=28, email=test3@baomidou.com)
User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
User(id=5, name=Billie, age=24, email=test5@baomidou.com)
```

> 完整的代码示例请移步：[Spring Boot 快速启动示例](https://github.com/baomidou/mybatis-plus-samples/tree/master/mybatis-plus-sample-quickstart)| [Spring MVC 快速启动示例](https://github.com/baomidou/mybatis-plus-samples/tree/master/mybatis-plus-sample-quickstart-springmvc)

**小结**

通过以上几个简单的步骤，我们就实现了 User 表的 CRUD 功能，甚至连 XML 文件都不用编写！

从以上步骤中，我们可以看到集成`MyBatis-Plus`非常的简单，只需要引入 starter 工程，并配置 mapper 扫描路径即可。

## 插件使用

### 1.自动生成主键

> 实现原理：除了雪花id，其他自增和UUID算法都是在表ID本身设置了自增的情况下传递一个null值自动生成id，然后再使用mybatis-plus的id生成器生成的id替换数据库id(自增就不用替换了)；而雪花算法，则是在内存中直接生成然后就设置为数据库id的值。

在使用自动生成主键功能前，**先设置数据id主键为自增ID**，否则会导致主键id插入null错误(主键设置了不允许null的情况下)。

两种id生成配置策略：1. 全局配置生成id类型；2. 局部表实体配置生成id类型。

**全局id生成策略**

```yaml
mybatis-plus:
  global-config:
    db-config:
      #主键类型(auto:"自增id"，assign_id:"全局唯一id(雪花算法,Long或者String类型)"，assign_uuid:"全局唯一id(
      #       无中划线的uuid)",input:"自行设置id,默认null",none:"不设置主键id")
      id-type: assign_id
```

**局部id生成策略**

> 使用@TableId注解配置id生成类型

user.java

```java
@EqualsAndHashCode(callSuper = false)  
  @Data  
  public class User extends Model<User> {  
   @TableId(type = IdType.AUTO)  
   private Long id;  
   @TableField(condition = SqlCondition.LIKE)  
   private String name;  
   @TableField(condition = "%s &gt; #{%s}")  
   private Integer age;  
   private String email;  
   private Long managerId;  
   private LocalDateTime createTime;  
  
```

同时配置了这两种策略时，**局部**生成id类型配置优先级**大于全局**id生成类型配置。

**测试使用**

UserController.java

```java
    /**
     * @Description 自动生成主键id(雪花算法)
     */
    @PostMapping("/add")
    public void addUser(){
        User user = new User();
        user.setName("testIdType");
        userMapper.insert(user);
        System.out.println("主键id为：" + user.getId());
    }
```

**注意的点**

* 局部字段的ID生成策略优先级高于全局的id生成策略
* 使用雪花算法生成ID后，再次切换为主键自增的ID生成策略后会导致起始序列过大(没有重置起始序列值)
* 另外如果原先指定了@TableId(type = IdType.AUTO)，然后去除这部分代码，会发生Tuncate操作(即清空表并重置ID起始值)

### 2.分页插件使用

> mybatis-plus分页插件旧版API为PaginationInterceptor，在3.5.1新版API为MybatisPlusInterceptor。

mybatis-plus的分页插件也是内置的，无需添加其他依赖。

**旧版分页插件**

MybatisPlusPageConfig.java

```java
@Configuration
public class MybatisPlusPageConfig {

    /**
     * 旧版本配置(已过时)
     */
    /*@Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }*/

    /**
     * 新的分页插件（推荐）
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

**新版分页插件**

MybatisPlusConfig.java

```java
/**
 * Mybatis-plus配置类
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.deepinsea.springbootmybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * Mybatis-Plus 3.5.1新版分页插件API
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页锁
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

**测试使用**

UserController.java

```java
    /**
     * @Description 内置分页插件的使用
     */
    @GetMapping("/page")
    public Page<User> getByPage() {
        Page<User> users = userMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(users.getRecords());
        return users;
    }
```

### 3.条件构造器使用

> mybatis-plus中有查询条件构造器QueryWrapper，可以在对常见的CRUD条件进行一些常见的构造，真实API在AbstractWrapper类中(采用了工厂模式进行顶层API设计)。

可以封装sql对象，包括where条件，order by排序，select哪些字段等等 查询包装类，可以封装多数查询条件，泛型指定返回的实体类。

**测试使用**

SpringbootMybatisplusApplicationTests.java

```java
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null); # 查询全表数据
        userList.forEach(System.out::println);
    }
```

UserController.java

```java
    /**
     * @Description 条件构造器使用
     */
    @DeleteMapping("/delete")
    public void delUser(){
        // 1. 根据ID删除
//        userMapper.deleteById(6);
//        userMapper.deleteBatchIds(Collections.singleton(7));
        // 2. 根据map中的参数作为条件删除
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "testIdType");
//        map.put("id", 7);
//        userMapper.deleteByMap(map);
        // 3. 条件构造器为参数的进行删除
        // ①普通条件构造器(注意不要添加<>，Entity转Object会报错)
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",6);
        userMapper.delete(wrapper);
        // ②lambda表达式条件构造器
//        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
//        lambdaQuery.eq(User::getId, 7).or().eq(User::getName, "testIdType");
//        userMapper.delete(lambdaQuery);
    }
```

### 4.代码生成器使用

**引入依赖**

> 说明：freemarker是作为代码生成器的模板依赖，必须存在；而knife4j是兼容生成的代码带Swagger注释

pom.xml

```xml
        <!-- mybatis-plus代码生成器 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.2</version>
        </dependency>
        <!-- freemarker模板 -->
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.31</version>
        </dependency>
        <!-- knife4j依赖 -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>2.0.7</version>
        </dependency>
```

**mybatis-plus 3.x版本**

SpringbootMybatisPlusApplication.java

```java
        List<String> schemas = new ArrayList<>(); // 数据表列表
        schemas.add("user");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/springboot-in-action?serverTimezone=Asia/Shanghai",
                        "root", "123456")
                //全局配置
                .globalConfig(builder -> {
                    builder.author("deepinsea")                                              // 设置作者
                            .enableSwagger()                                                // 开启 swagger 模式
                            .fileOverride()                                                 // 覆盖已生成文件
                            .disableOpenDir()                                               // 禁止打开输出目录
                            .dateType(DateType.TIME_PACK)                                   // 时间策略
                            .commentDate("yyyy-MM-dd")                                      // 注释日期
                            .outputDir(System.getProperty("user.dir")
                                    + "/src/main/java");                           // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("generator")                                    // 设置父包名(也可以生成目录)
                            //.moduleName("system")                                     // 设置父包模块名
//                            .entity("model")
//                            .service("service")
//                            .controller("controller")
//                            .mapper("mapper")                                           // Mapper 包名
//                            .xml("mapper")                                              // Mapper XML 包名
                            .pathInfo(Collections.singletonMap(
                                    OutputFile.xml,
                                    System.getProperty("user.dir")
                                            + "/src/main/resources/mapper"));           // 设置mapperXml生成路径

                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(schemas)                                          // 设置需要生成的表名
                            .addTablePrefix("")                                       // 表前缀过滤
                            .entityBuilder()                                            // 切换至Entity设置
                            .versionColumnName("version")                               // 乐观锁字段名(数据库)
                            .logicDeleteColumnName("isDeleted")                         // 逻辑删除字段名(数据库)
                            .enableLombok()                                             // lombok生效
                            .enableTableFieldAnnotation()                               // 所有实体类加注解
                            .serviceBuilder()                                           // 切换至Service层设置
                            .formatServiceFileName("%sService")                         // 设定后缀名
                            .formatServiceImplFileName("%sServiceImpl");                // 设定后缀名
                })
                //模板配置
                .templateEngine(new FreemarkerTemplateEngine())                         // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
```

**测试使用**

> 点击运行项目即可后台静默在src主目录生成一个generate文件夹和resource目录生成一个mapper文件夹，代码文件都在这两个文件夹里

![image-20220326034859941](http://pic.deepinsea.top/images/2022/03/26/202203260349107.png)

> 注意：因为上面关闭了打开目录的开关，所以要看到文件目录生成，需要手动关闭项目才能刷新看到！

