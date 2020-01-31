package com.stu.sbt.common.config.datasource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author admin
 */
@Configuration
@PropertySource(value = {"classpath:application.yml"}, encoding = "UTF-8", name = "application.yml")
//@ConfigurationProperties(prefix = "druid", ignoreInvalidFields = true)
public class DruidDataSourceConfig {


    @Value("${druid.url}")
    private String url;
    @Value("${druid.username}")
    private String user;
    @Value("${druid.password}")
    private String password;
    @Value("${druid.driver-class-name}")
    private String driverClassName;

    @Value("${druid.max-active}")
    private Integer maxActive;
    @Value("${druid.min-idle}")
    private Integer minIdle;
    @Value("${druid.initial-size}")
    private Integer initialSize;
    @Value("${druid.max-wait}")
    private Long maxWait;
    @Value("${druid.time-between-eviction-runs-millis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${druid.min-evictable-idle-time-millis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${druid.test-while-idle}")
    private Boolean testWhileIdle;
    @Value("${druid.test-on-borrow}")
    private Boolean testOnBorrow;
    @Value("${druid.test-on-return}")
    private Boolean testOnReturn;

    @Value("${druid.validation-query}")
    private String validationQuery;

    @Value("${druid.pool-prepared-statements}")
    private Boolean poolPreparedStatements;

    @Value("${druid.connect-properties}")
    private String connectionPropertiesStr;

    @Value("${druid.filters}")
    private String filters;

    @Value("${druid.use-global-data-source-stat}")
    private Boolean useGlobalDataSourceStat;

    @Value("${druid.publicKey}")
    private String publicKey;

    @Value("${druid.web-stat-filter.url-pattern}")
    private String webStatUrlPattern;

    @Value("${druid.web-stat-filter.enabled}")
    private Boolean webStatEnable;

    @Value("${druid.web-stat-filter.exclusions}")
    private String exclusions;

    @Value("${druid.web-stat-filter.session-stat-enable}")
    private Boolean sessionStatEnable;

    @Value("${druid.stat-view-servlet.enabled}")
    private Boolean statViewServletEnable;

    @Value("${druid.stat-view-servlet.reset-enable}")
    private Boolean resetEnable;

    @Value("${druid.stat-view-servlet.url-pattern}")
    private String statViewUrlPattern;

    @Value("${druid.stat-view-servlet.login-username}")
    private String loginUserName;
    @Value("${druid.stat-view-servlet.login-password}")
    private String loginPassword;

    @Value("${druid.stat-view-servlet.allow}")
    private String allow;

    @Value("${druid.stat-view-servlet.deny}")
    private String deny;

    @Bean(name = "slave")
    public DataSource dataSource() {
        //jdbc配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        //连接池配置
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        if(connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)){
            Properties connectProperties = new Properties();
            String[] propertiesList = connectionPropertiesStr.split(";");
            for(String propertiesTmp:propertiesList){
                String[] obj = propertiesTmp.split("=");
                String key = obj[0];
                String value = obj[1];
                connectProperties.put(key,value);
                if(key.equals("config.decrypt") && value.equals("true")){
                    connectProperties.put("config.decrypt.key",publicKey);
                }
            }
            dataSource.setConnectProperties(connectProperties);
        }
        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            /**
             * 这是最关键的,否则SQL监控无法生效
             */
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings(statViewUrlPattern);
        Map<String, String> initParameters = new HashMap<String, String>(6);
        /**
         * 用户名
         * */
        initParameters.put("loginUsername", loginUserName);
        /**
         * 密码
         */
        initParameters.put("loginPassword", loginPassword);
        /**禁用HTML页面上的“Reset All”功能*/
        initParameters.put("resetEnable", resetEnable.toString());
        /** IP白名单 (没有配置或者为空，则允许所有访问)*/
        initParameters.put("allow", allow);
        /**IP黑名单 (存在共同时，deny优先于allow)*/
        initParameters.put("deny", deny);
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(webStatUrlPattern);
        filterRegistrationBean.addInitParameter("exclusions", exclusions);
        filterRegistrationBean.addInitParameter("session-stat-enable", sessionStatEnable.toString());
        filterRegistrationBean.setEnabled(webStatEnable);
        return filterRegistrationBean;
    }

}
