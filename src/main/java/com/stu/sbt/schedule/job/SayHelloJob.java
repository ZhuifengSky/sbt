package com.stu.sbt.schedule.job;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class SayHelloJob {

    //@Scheduled(cron = "0/5 * * * * ? ")
    @Async
    public void sayHello(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("定时问好");
        System.out.println("hello!");
        for (int i=0; i<5;i++){
            System.out.println("----"+i+"----");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println("任务名称:"+stopWatch.getLastTaskName()+"执行时间"+stopWatch.getLastTaskTimeMillis()+"毫秒");
    }
}
