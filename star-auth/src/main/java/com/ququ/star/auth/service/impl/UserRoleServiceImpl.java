package com.ququ.star.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.auth.entity.UserRole;
import com.ququ.star.auth.mapper.UserRoleMapper;
import com.ququ.star.auth.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 11:07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService {
}
