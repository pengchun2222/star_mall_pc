package com.ququ.star.auth.entity;


import lombok.Data;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * 用户角色关联表
 */
@Data
public class UserRole {
    // 用户角色关联ID
    private Long id;
    
    // 用户ID
    private Long userId;
    
    // 角色ID
    private Long roleId;
    
    // 创建时间
    private Long createTime;
    
    // 修改时间
    private Long updateTime;
    
    // 删除标志
    private boolean isDeleted;

    // getters and setters
}
