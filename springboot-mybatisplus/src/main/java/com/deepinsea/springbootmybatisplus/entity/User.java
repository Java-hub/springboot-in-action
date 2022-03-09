package com.deepinsea.springbootmybatisplus.entity;

import lombok.Data;

/**
 * @auther deepinsea
 * @date 2022/3/1 18:33
 */
@Data
public class User{
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
