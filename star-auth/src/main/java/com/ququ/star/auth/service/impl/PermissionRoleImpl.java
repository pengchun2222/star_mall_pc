package com.ququ.star.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.auth.entity.PermissionRole;
import com.ququ.star.auth.mapper.PermissionRoleMapper;
import com.ququ.star.auth.service.PermissionRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 10:29
 */
@Service
public class PermissionRoleImpl extends ServiceImpl<PermissionRoleMapper,PermissionRole> implements PermissionRoleService {
}
