package com.ququ.star.auth.model;

import com.ququ.star.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 10:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRoleVo {
    // 权限id
    private Long permissionId;

    // url
    private String url;

    // 权限名称
    private String permissionName;

    // 角色集合
    private List<Role> roleList;
}
