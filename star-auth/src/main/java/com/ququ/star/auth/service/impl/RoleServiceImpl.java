package com.ququ.star.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.auth.entity.Role;
import com.ququ.star.auth.mapper.RoleMapper;
import com.ququ.star.auth.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 10:28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
