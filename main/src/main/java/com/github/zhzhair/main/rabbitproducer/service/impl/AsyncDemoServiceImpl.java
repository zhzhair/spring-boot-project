package com.github.zhzhair.main.rabbitproducer.service.impl;

import com.github.zhzhair.main.rabbitproducer.service.AsyncDemoService;
import org.springframework.stereotype.Service;

@Service
public class AsyncDemoServiceImpl implements AsyncDemoService {

    @Override
    public void asyncJob() {
        try {
            Thread.sleep(20_000);//睡觉20秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
