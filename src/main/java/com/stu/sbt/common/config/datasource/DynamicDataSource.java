package com.stu.sbt.common.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LogManager.getLogger(DynamicDataSource.class);


    @Override
    protected Object determineCurrentLookupKey() {
        log.info("数据源为"+DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }

}

