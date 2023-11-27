package com.ququ.star.auth.entity;

import lombok.Data;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * 权限表
 */
@Data
public class Permission {
    // 权限ID
    private Long id;
    
    // 权限名
    private String name;

    // 路径
    private String url;
    
    // 权限描述
    private String description;
    
    // 创建时间
    private Long createTime;
    
    // 修改时间
    private Long updateTime;
    
    // 删除标志
    private boolean isDeleted;

    // getters and setters
}
