package com.ququ.star.auth.entity;

import lombok.Data;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * 权限角色关联表
 */
@Data
public class PermissionRole {
    // 权限角色关联ID
    private Long id;
    
    // 权限ID
    private Long permissionId;
    
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
