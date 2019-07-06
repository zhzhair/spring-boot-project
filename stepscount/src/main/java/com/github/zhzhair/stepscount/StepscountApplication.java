package com.github.zhzhair.stepscount;

import com.github.zhzhair.stepscount.step.service.StepService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * cmd命令：
 *  启动mongo：mongod --dbpath D:\database\mongodb\data
 */
@ComponentScan({"springfox", "com.github.zhzhair.stepscount"})//swagger扫描路径
@MapperScan(basePackages = "com.github.zhzhair.stepscount.*.mapper")//mybatis扫描路径
@EnableScheduling//开启定时任务
@SpringBootApplication
public class StepscountApplication {

    @Resource
    private StepService stepService;
    private static StepService service;

    @PostConstruct
    public void init() {
        service = this.stepService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StepscountApplication.class, args);
        //启动项目初始化排名 -- redis缓存第200名的总步数，并初始化mongodb集合的数据
        service.createTables();
        service.recordTopAll();
    }

}
