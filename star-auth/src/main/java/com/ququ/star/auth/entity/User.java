package com.ququ.star.auth.entity;

import lombok.Data;


/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * 用户表
 */
@Data
public class User {
    // 用户ID
    private Long id;
    
    // 用户名
    private String username;
    
    // 密码
    private String password;

    private String phone;
    
    // 电子邮箱
    private String email;
    
    // 创建时间
    private Long createTime;
    
    // 修改时间
    private Long updateTime;
    
    // 用户状态
    private Integer status;
    
    // 删除标志
    private boolean isDeleted;

    // getters and setters
}
