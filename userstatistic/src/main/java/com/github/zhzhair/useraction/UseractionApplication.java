package com.github.zhzhair.useraction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * cmd命令：
 *  启动rabbitMQ：D:\rabbitMQ\rabbitmq_server-3.7.7\sbin\rabbitmq-server.bat
 */
@ComponentScan({"springfox", "com.github.zhzhair.useraction"})//swagger扫描路径
@MapperScan(basePackages = "com.github.zhzhair.useraction.*.mapper")//mybatis扫描路径
@SpringBootApplication
public class UseractionApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseractionApplication.class, args);
    }

}
