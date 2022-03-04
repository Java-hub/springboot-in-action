package com.deepinsea.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepinsea.springbootmybatisplus.model.User;
import org.springframework.stereotype.Repository;

/**
 * @auther deepinsea
 * @date 2022/3/1 18:35
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
