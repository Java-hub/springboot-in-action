package com.deepinsea.springbootmybatisplus;

import com.deepinsea.springbootmybatisplus.entity.User;
import com.deepinsea.springbootmybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.SocketException;
import java.util.List;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void contextLoads() throws SocketException {
    }

}
