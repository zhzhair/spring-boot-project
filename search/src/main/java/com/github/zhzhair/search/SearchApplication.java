package com.github.zhzhair.search;

import io.netty.util.NettyRuntime;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"springfox", "com.github.zhzhair.search"})//swagger扫描路径
@MapperScan(basePackages = "com.github.zhzhair.search.*.mapper")//mybatis扫描路径
@EnableScheduling//开启定时任务
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        NettyRuntime.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        SpringApplication.run(SearchApplication.class, args);
    }

}
