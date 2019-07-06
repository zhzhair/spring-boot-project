package com.github.zhzhair.stepscount.step.task;

import com.github.zhzhair.stepscount.common.annotations.LogForTask;
import com.github.zhzhair.stepscount.step.service.StepService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.IntStream;

@Component
public class StepTask {
    @Resource
    private StepService stepService;

    @LogForTask(milliseconds = 1500)
    @Scheduled(cron = "0/1 * * * * ?")
    public void uploadStep() {
        IntStream.range(0, 300).parallel().forEach(i -> stepService.uploadStep());
    }

    @LogForTask(milliseconds = 50)
    @Scheduled(cron = "0/1 * * * * ?")
    public void recordRankAll() {
        stepService.recordRankAll();
    }

    @LogForTask(milliseconds = 50)
    @Scheduled(cron = "0/10 * * * * ?")
    public void recordRank() {
        stepService.flushRankAll();
    }
}
