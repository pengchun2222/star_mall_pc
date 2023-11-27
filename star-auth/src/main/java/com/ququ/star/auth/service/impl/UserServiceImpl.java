package com.ququ.star.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.auth.entity.User;
import com.ququ.star.auth.mapper.UserMapper;
import com.ququ.star.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: 彭淳
 * @Date: 2023/11/23 11:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
