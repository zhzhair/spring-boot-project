package com.github.zhzhair.stepscount.step.service;

import com.github.zhzhair.stepscount.step.dto.response.StepsRankAllResp;

import java.util.List;

public interface StepService {

    /**
     * 干掉mysql表
     */
    void dropTables();

    /**
     * 创建mysql表
     */
    void createTables();

    /**
     * 步数上传，定时任务添加步数模拟大量用户
     */
    void uploadStep();

    /**
     * redis缓存第200名的总步数，并初始化mongodb集合的数据
     */
    void recordTopAll();

    /**
     * 定时删除mongo集合中多余的数据
     */
    void flushRankAll();

    /**
     * 定时记录排名数据到redis队列
     */
    void recordRankAll();

    /**
     * 从mongo查询排名记录
     * @param begin 开始记录
     * @param pageSize 每页条数
     * @return 步数排名
     */
    List<StepsRankAllResp> getRankAll(int begin, int pageSize);

    /**
     * 从redis的list中查询排名记录
     * @param begin 开始记录
     * @param pageSize 每页条数
     * @return 步数排名
     */
    List<StepsRankAllResp> getRankAllFromRedis(int begin, int pageSize);
}
