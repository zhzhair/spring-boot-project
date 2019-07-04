package com.github.zhzhair.flowstatistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * cmd命令：
 *  启动mongo：mongod --dbpath D:\database\mongodb\data
 *  启动rabbitMQ：D:\rabbitMQ\rabbitmq_server-3.7.7\sbin\rabbitmq-server.bat
 */
@ComponentScan({"springfox", "com.github.zhzhair.flowstatistic"})//swagger扫描路径
@MapperScan(basePackages = "com.github.zhzhair.flowstatistic.*.mapper")//mybatis扫描路径
@EnableScheduling//开启定时任务
@SpringBootApplication
public class FlowstatisticApplication {

    public static void main(String[] args) {
        System.err.println("1".compareTo("2"));
        SpringApplication.run(FlowstatisticApplication.class, args);
    }

}
