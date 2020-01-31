package com.stu.sbt.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.stu.sbt.common.bean.ResponseBean;

public class ReturnUtil {

    /**
     * 返回json对象
     * @param code
     * @param info
     * @return
     */
    public static String returnJson(String code, String info) {
        ResponseBean response = new ResponseBean();
        response.setCode(code);
        response.setInfo(info);
        return JSONObject.toJSON(response).toString();
    }

    /**
     * 返回json对象
     * @param code
     * @param info
     * @param data
     * @return
     */
    public static String returnJson(String code, String info,Object data) {
        ResponseBean response = new ResponseBean();
        response.setCode(code);
        response.setInfo(info);
        response.setData(data);
        return JSONObject.toJSON(response).toString();
    }

}
