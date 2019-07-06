package com.github.zhzhair.stepscount.step.service;

/**
 * Created by 49535
 * on 2018/4/8.
 */
public interface CollectStepService {

    /**
     * 执行JavaScript脚本
     */
    void executeScript();

    /**
     * 执行json语句查重
     */
    void getDistinctCountWithJsonSql();
}
