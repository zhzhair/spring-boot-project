package com.github.zhzhair.flowstatistic.datagenerate.task;

import com.github.zhzhair.flowstatistic.datagenerate.service.AppStartTestService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TaskDataCollect {

    @Resource
    private AppStartTestService testService;

    @Scheduled(cron = "0 40 22 * * ?")//每天22点40分执行该定时任务
    public void createDayTables(){
        testService.createAppStartDayTable(0);
    }

    @Scheduled(cron = "0 40 21 * * ?")//每天22点40分执行该定时任务
    public void dropDayTables(){
        testService.dropAppStartDayTable(0);
    }
}
