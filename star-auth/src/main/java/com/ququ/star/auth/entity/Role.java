package com.ququ.star.auth.entity;


import lombok.Data;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * 角色表
 */
@Data
public class Role {
    // 角色ID
    private Long id;
    
    // 角色名
    private String name;

    private String code;
    
    // 角色描述
    private String description;
    
    // 创建时间
    private Long createTime;
    
    // 修改时间
    private Long updateTime;
    
    // 删除标志
    private boolean isDeleted;

    // getters and setters
}
