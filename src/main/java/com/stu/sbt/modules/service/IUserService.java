package com.stu.sbt.modules.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.stu.sbt.modules.model.User;

import java.util.List;


/**
 * @author admin
 */
public interface IUserService {

    /**
     * 用户列表
     * @param user
     * @return
     */
     List<User> findList(User user);

    /**
     * 分页用户列表
     * @param user
     * @return
     */
    PageInfo<User> findPage(Page<User> page, User user);

    /**
     * 分页用户列表
     * @param user
     * @return
     */
    PageInfo<User> findPage2(Page<User> page, User user);
}
