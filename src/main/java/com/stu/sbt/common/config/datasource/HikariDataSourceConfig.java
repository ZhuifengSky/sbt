package com.stu.sbt.common.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author admin
 */
@Configuration
@PropertySource(value = {"classpath:application.yml"}, encoding = "UTF-8", name = "application.yml")
public class HikariDataSourceConfig {
    public static final String hikariConfigPrefix="spring:datasource:hikari";

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private Integer minNumIdle;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private Long idleTimeout;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private Integer maximumPoolSize;
    @Value("${spring.datasource.hikari.connection-test-query}")
    private String connectionTestQuery;
    @Value("${spring.datasource.hikari.connection-timeout}")
    private Long connectionTimeout;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private Long maxLifetime;


    @Bean(name = "master")
    public DataSource dataSource() {
        //jdbc配置
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //连接池配置
        dataSource.setMinimumIdle(minNumIdle);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setConnectionTestQuery(connectionTestQuery);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setMaxLifetime(maxLifetime);
        return dataSource;
    }



}
