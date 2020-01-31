package com.stu.sbt.modules.model;

import lombok.Data;

@Data
public class User {
    /**
     主键Id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String trueName;

    private String mobile;

    private String email;
}
