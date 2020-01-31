package com.stu.sbt.modules.dao;

import com.stu.sbt.modules.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface IUserMapper {

    /**
     * 查询用户
     * @param user
     * @return
     */
    List<User> findList(User user);
}
