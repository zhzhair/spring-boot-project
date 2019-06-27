package com.github.zhzhair.main;

import io.netty.util.NettyRuntime;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"springfox", "com.github.zhzhair.main"})//swagger扫描路径
@MapperScan(basePackages = "com.github.zhzhair.main.*.mapper")//mybatis扫描路径
@EnableScheduling//开启定时任务
@EnableAsync//开启异步方法调用
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        NettyRuntime.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        SpringApplication.run(MainApplication.class, args);
    }

}
