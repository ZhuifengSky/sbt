package com.stu.sbt.modules.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.stu.sbt.common.utils.ReturnUtil;
import com.stu.sbt.modules.model.User;
import com.stu.sbt.modules.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@Api(value = "用户信息管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
    @PostMapping("/findPage")
    public String findPage(User user,Integer pageNum,Integer pageSize){
        logger.info("开始分页获取用户列表");
        StopWatch stopWatch = new StopWatch("分页获取用户列表");
        stopWatch.start("开始");
        Page<User> page = new Page<>();
        if(pageNum==null){
            page.setPageNum(1);
        }else{
            page.setPageNum(pageNum);
        }
        if(pageSize==null){
            page.setPageSize(10);
        }else{
            page.setPageSize(pageSize);
        }
        stopWatch.stop();
        stopWatch.start("获取数据");
        PageInfo<User> userPage = userService.findPage(page, user);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return ReturnUtil.returnJson("00","操作成功",userPage);
    }

    @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
    @PostMapping("/findPage2")
    public String findPage2(User user,Integer pageNum,Integer pageSize){
        logger.info("开始分页获取用户列表");
        StopWatch stopWatch = new StopWatch("分页获取用户列表");
        stopWatch.start("开始");
        Page<User> page = new Page<>();
        if(pageNum==null){
            page.setPageNum(1);
        }else{
            page.setPageNum(pageNum);
        }
        if(pageSize==null){
            page.setPageSize(10);
        }else{
            page.setPageSize(pageSize);
        }
        stopWatch.stop();
        stopWatch.start("获取数据");
        PageInfo<User> userPage = userService.findPage2(page, user);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return ReturnUtil.returnJson("00","操作成功",userPage);
    }
}
