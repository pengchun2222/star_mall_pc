package com.ququ.star.auth.sms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ququ.star.auth.entity.Role;
import com.ququ.star.auth.entity.User;
import com.ququ.star.auth.entity.UserRole;
import com.ququ.star.auth.service.RoleService;
import com.ququ.star.auth.sms.service.SmsCodeUserDetailService;
import com.ququ.star.auth.service.UserRoleService;
import com.ququ.star.auth.service.UserService;
import com.ququ.star.common.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ququ.star.common.constent.SysConstant.ROLE_PREFIX;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 11:03
 */
@Service
@RequiredArgsConstructor
public class SmsCodeUserDetailServiceImpl implements SmsCodeUserDetailService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getPhone, mobile));
        if (ObjectUtil.isEmpty(user)) {
            throw new UsernameNotFoundException("手机号不存在");
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
