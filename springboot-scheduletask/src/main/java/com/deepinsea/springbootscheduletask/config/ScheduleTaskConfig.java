package com.deepinsea.springbootscheduletask.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther deepinsea
 * @Date 2022/3/14
 * 定时任务配置类
 */
@Component
public class ScheduleTaskConfig {

    // 每隔5s执行一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void printSay() {
        System.out.println("每隔5s执行一次：" + new Date());
    }
}
