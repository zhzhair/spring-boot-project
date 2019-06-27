package com.github.zhzhair.main.rabbitproducer.asynctask;

import com.github.zhzhair.main.rabbitproducer.service.AsyncDemoService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 异步发送邮件
 */
@Component
public class AsyncTask {
    @Resource
    private AsyncDemoService asyncDemoService;

    /**
     * 给注册成功的用户发送简单的邮件
     */
    @Async//异步执行的注解，需要启动内加上@EnableAsync
    public void asyncMethod(){
        long begin = System.currentTimeMillis();
        asyncDemoService.asyncJob();
        System.out.println("sendSimpleEmail use: " + (System.currentTimeMillis() - begin) + " milliSeconds");
    }

}
