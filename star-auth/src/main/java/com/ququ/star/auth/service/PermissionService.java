package com.ququ.star.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ququ.star.auth.entity.Permission;
import com.ququ.star.auth.model.PermissionRoleVo;

import java.util.List;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 10:15
 */
public interface PermissionService extends IService<Permission> {

    // 查询所有权限对应角色
    public List<PermissionRoleVo> listRolePermission();
}
