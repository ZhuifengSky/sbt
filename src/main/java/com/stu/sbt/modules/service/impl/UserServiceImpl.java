package com.stu.sbt.modules.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.sbt.common.config.annotation.TargetDataSource;
import com.stu.sbt.modules.dao.IUserMapper;
import com.stu.sbt.modules.model.User;
import com.stu.sbt.modules.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public List<User> findList(User user) {
        return userMapper.findList(user);
    }

    @Override
    @TargetDataSource
    public PageInfo<User> findPage(Page<User> page,User user) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<User> list = userMapper.findList(user);
        return new PageInfo<>(list);
    }

    @Override
    @TargetDataSource("slave")
    public PageInfo<User> findPage2(Page<User> page,User user) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<User> list = userMapper.findList(user);
        return new PageInfo<>(list);
    }
}
