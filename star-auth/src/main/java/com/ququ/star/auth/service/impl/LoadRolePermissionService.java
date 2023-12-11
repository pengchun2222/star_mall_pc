package com.ququ.star.auth.service.impl;

import com.ququ.star.auth.model.PermissionRoleVo;
import com.ququ.star.auth.service.PermissionService;
import com.ququ.star.common.constent.SysConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 10:13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoadRolePermissionService {

    private final RedisTemplate redisTemplate;
    private final PermissionService permissionService;

    /**
     * 初始化权限角色信息
     */
    @PostConstruct
    public void init() {

        redisTemplate.delete(SysConstant.OAUTH_URLS);

        List<PermissionRoleVo> permissionRoleVos = permissionService.listRolePermission();
        permissionRoleVos.stream().forEach(permissionRoleVo -> {
            ArrayList<String> roleList = new ArrayList<>();
            permissionRoleVo.getRoleList().stream().forEach(role -> {
                roleList.add(SysConstant.ROLE_PREFIX + role.getCode());
            });
            redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,permissionRoleVo.getUrl(),roleList);
        });
    }
}
