package com.deepinsea.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepinsea.springbootmybatisplus.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * @auther deepinsea
 * @date 2022/3/1 18:35
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Delete("delete from user group by id desc")
    void delUser(Long id);

    void delete(QueryWrapper<Object> id);
}
