package com.ququ.star.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ququ.star.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 彭淳
 * @Date: 2023/11/20 19:28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
