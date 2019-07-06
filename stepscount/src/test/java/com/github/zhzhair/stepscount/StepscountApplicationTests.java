package com.github.zhzhair.stepscount;

import com.github.zhzhair.stepscount.geo.service.CaculateService;
import com.github.zhzhair.stepscount.step.service.CollectStepService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepscountApplicationTests {

    @Resource
    private CollectStepService collectStepService;
    @Resource
    private CaculateService caculateService;
    @Test
    public void contextLoads() {
        System.err.println("===========================begin========================");
        long begin = System.currentTimeMillis();
        caculateService.insertSample();
        caculateService.boxTest();
        caculateService.circleTest();
        caculateService.nearestTest();
        caculateService.pointTest();
        caculateService.spaceTest();
        caculateService.sphereTest();
        collectStepService.executeScript();
        collectStepService.getDistinctCountWithJsonSql();
        System.out.println("耗时（毫秒）：" + (System.currentTimeMillis() - begin));
        System.err.println("============================end=========================");
    }

}
