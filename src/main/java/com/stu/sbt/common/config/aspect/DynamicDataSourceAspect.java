package com.stu.sbt.common.config.aspect;

import com.stu.sbt.common.config.annotation.TargetDataSource;
import com.stu.sbt.common.config.datasource.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author admin
 */
@Aspect
@Component
@ComponentScan(basePackages = "com.stu.sbt.modules.service.*")
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.stu.sbt.common.config.annotation.TargetDataSource)")
    public void point(){
        System.out.println("**执行了**");
    }

    @Before("@annotation(com.stu.sbt.common.config.annotation.TargetDataSource)")
    public void beforeSwitchDS(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@TargetDataSource注解
            if (method.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(com.stu.sbt.common.config.annotation.TargetDataSource)")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDB();
    }
}
