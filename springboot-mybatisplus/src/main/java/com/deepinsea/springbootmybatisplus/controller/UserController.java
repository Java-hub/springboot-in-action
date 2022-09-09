package com.deepinsea.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepinsea.springbootmybatisplus.entity.User;
import com.deepinsea.springbootmybatisplus.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther deepinsea
 * @Date 2022/3/9 22:06
 */
@RestController
@Api(tags = "首页模块")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description 查询所有用户
     */
    @GetMapping("/")
    @ApiOperation(value = "查询所有用户")
    public List<User> getAll(){
        List<User> list = userMapper.selectList(null);
        return list;
    }

    /**
     * @Description 条件构造器使用
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除单个用户")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,example = "11")
    public void delUser(@PathVariable("id") Integer id){
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
        wrapper.eq("id",id);
        userMapper.delete(wrapper);
        // ②lambda表达式条件构造器
//        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
//        lambdaQuery.eq(User::getId, 7).or().eq(User::getName, "testIdType");
//        userMapper.delete(lambdaQuery);
    }

    /**
     * @Description 自动生成主键id(雪花算法)
     */
    @PostMapping("/add")
    @ApiOperation("添加单个用户")
    public void addUser(){
        User user = new User();
//        user.setId(System.currentTimeMillis());
        user.setName("testIdType");
        userMapper.insert(user);
        System.out.println("主键id为：" + user.getId());
    }

    /**
     * @Description 内置分页插件的使用
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Page<User> getByPage() {
        Page<User> users = userMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(users.getRecords());
        return users;
    }
}
