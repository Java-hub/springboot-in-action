package com.deepinsea.springbootmybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.deepinsea.springbootmybatisplus.mapper")
public class SpringbootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisPlusApplication.class, args);

//        List<String> schemas = new ArrayList<>(); // 数据表列表
//        schemas.add("user");
//        FastAutoGenerator.create("jdbc:mysql://localhost:3306/springboot-in-action?serverTimezone=Asia/Shanghai",
//                        "root", "123456")
//                //全局配置
//                .globalConfig(builder -> {
//                    builder.author("deepinsea")                                              // 设置作者
//                            .enableSwagger()                                                // 开启 swagger 模式
//                            .fileOverride()                                                 // 覆盖已生成文件
//                            .disableOpenDir()                                               // 禁止打开输出目录
//                            .dateType(DateType.TIME_PACK)                                   // 时间策略
//                            .commentDate("yyyy-MM-dd")                                      // 注释日期
//                            .outputDir(System.getProperty("user.dir")
//                                    + "/src/main/java");                           // 指定输出目录
//                })
//                //包配置
//                .packageConfig(builder -> {
//                    builder.parent("generator")                                    // 设置父包名(也可以生成目录)
//                            //.moduleName("system")                                     // 设置父包模块名
////                            .entity("model")
////                            .service("service")
////                            .controller("controller")
////                            .mapper("mapper")                                           // Mapper 包名
////                            .xml("mapper")                                              // Mapper XML 包名
//                            .pathInfo(Collections.singletonMap(
//                                    OutputFile.xml,
//                                    System.getProperty("user.dir")
//                                            + "/src/main/resources/mapper"));           // 设置mapperXml生成路径
//
//                })
//                //策略配置
//                .strategyConfig(builder -> {
//                    builder.addInclude(schemas)                                          // 设置需要生成的表名
//                            .addTablePrefix("")                                       // 表前缀过滤
//                            .entityBuilder()                                            // 切换至Entity设置
//                            .versionColumnName("version")                               // 乐观锁字段名(数据库)
//                            .logicDeleteColumnName("isDeleted")                         // 逻辑删除字段名(数据库)
//                            .enableLombok()                                             // lombok生效
//                            .enableTableFieldAnnotation()                               // 所有实体类加注解
//                            .serviceBuilder()                                           // 切换至Service层设置
//                            .formatServiceFileName("%sService")                         // 设定后缀名
//                            .formatServiceImplFileName("%sServiceImpl");                // 设定后缀名
//                })
//                //模板配置
//                .templateEngine(new FreemarkerTemplateEngine())                         // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
    }
}
