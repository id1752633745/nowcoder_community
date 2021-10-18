package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 根据 id 查找用户
     */
    User selectById(int id);

    /**
     * 根据 用户名 查找用户
     */
    User selectByName(String username);

    /**
     * 根据 邮箱 查找用户
     */
    User selectByEmail(String email);

    /**
     * 插入新的用户
     */
    int insertUser(User user);

    /**
     * 修改指定 id 用户的 状态
     */
    int updateStatus(int id, int status);

    /**
     * 修改指定 id 用户的 头像
     */
    int updateHeader(int id, String headerUrl);

    /**
     * 修改指定 id 用户的 密码
     */
    int updatePassword(int id, String password);
}
