package com.ququ.star.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.auth.entity.Permission;
import com.ququ.star.auth.entity.PermissionRole;
import com.ququ.star.auth.entity.Role;
import com.ququ.star.auth.mapper.PermissionMapper;
import com.ququ.star.auth.model.PermissionRoleVo;
import com.ququ.star.auth.service.PermissionRoleService;
import com.ququ.star.auth.service.PermissionService;
import com.ququ.star.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 10:16
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private final PermissionRoleService permissionRoleService;

    private final RoleService roleService;

    @Override
    public List<PermissionRoleVo> listRolePermission() {
        List<Permission> permissionList = list();

        // 查询所有权限
        ArrayList<PermissionRoleVo> permissionRoleVos = new ArrayList<>();
        for (Permission permission : permissionList) {
            // 根据权限查询权限角色中间表
            List<PermissionRole> permissionRoleList = permissionRoleService.list(Wrappers.lambdaQuery(PermissionRole.class).eq(PermissionRole::getPermissionId, permission.getId()));
            // 查询角色
            Set<Long> roleIdSet = permissionRoleList.stream().map(PermissionRole::getRoleId).collect(Collectors.toSet());
            List<Role> roleList = null;
            if(CollUtil.isNotEmpty(roleIdSet)) {
                roleList = roleService.listByIds(roleIdSet);
            }
            // 数据构造
            PermissionRoleVo permissionRoleVo = PermissionRoleVo.builder()
                    .permissionId(permission.getId())
                    .url(permission.getUrl())
                    .permissionName(permission.getName())
                    .roleList(roleList).build();
            permissionRoleVos.add(permissionRoleVo);
        }
        return permissionRoleVos;
    }
}
