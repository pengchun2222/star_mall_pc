package com.ququ.star.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ququ.star.auth.constent.UserStatusConstant;
import com.ququ.star.auth.entity.Role;
import com.ququ.star.auth.entity.User;
import com.ququ.star.auth.entity.UserRole;
import com.ququ.star.auth.mapper.UserMapper;
import com.ququ.star.auth.service.RoleService;
import com.ququ.star.auth.service.UserRoleService;
import com.ququ.star.common.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ququ.star.common.constent.SysConstant.ROLE_PREFIX;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:23
 * UserDetailsService的实现类，从数据库加载用户的信息，比如密码、角色。。。。
 */
@Service
@RequiredArgsConstructor
public class JwtTokenUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查询用户信息
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username).eq(User::getStatus, UserStatusConstant.NORMAL.getValue()));

        if(ObjectUtil.isEmpty(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 通过userId查询角色信息
        List<UserRole> userRoles = userRoleService.list(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, user.getId()));

        // 通过角色id查询角色code
        List<String> roleCodeList = new ArrayList<>();
        if (CollUtil.isNotEmpty(userRoles)) {
            List<Role> roles = roleService.listByIds(userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet()));
            roleCodeList = roles.stream().map(role -> ROLE_PREFIX + role.getCode()).collect(Collectors.toList());
        }

        // 构建返回体
        return SecurityUser.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(AuthorityUtils.createAuthorityList(ArrayUtil.toArray(roleCodeList, String.class)))
                .build();
    }
}
