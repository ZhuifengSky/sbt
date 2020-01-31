package com.stu.sbt.common.config.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author admin
 */
public class DataSourceContextHolder {

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "master";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DataSourceContextHolder.class);


    // 设置数据源名
    public static void setDB(String dbType) {
        log.info("切换到{"+dbType+"}数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}