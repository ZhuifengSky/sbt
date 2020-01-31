package com.stu.sbt.common.bean;

import lombok.Data;

@Data
public class ResponseBean {

    private String code;  //code符号
    private String info;  //信息
    private Object data;  //数据
}
