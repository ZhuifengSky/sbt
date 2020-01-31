package com.stu.sbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author admin
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@EnableSwagger2
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SbtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbtApplication.class, args);
    }

}
