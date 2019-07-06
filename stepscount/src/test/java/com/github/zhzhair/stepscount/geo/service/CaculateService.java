package com.github.zhzhair.stepscount.geo.service;

/**
 * Created by 49535
 * on 2018/4/17.
 */
public interface CaculateService {
    /**
     * 插入样本数据
     */
    void insertSample();

    /**
     * 圆形查询
     */
    void circleTest();

    /**
     * 球形查询
     */
    void sphereTest();

    /**
     * 矩形查询
     */
    void boxTest();

    /**
     * 点到点查询(固定半径内的点)
     */
    void pointTest();

    /**
     * 空间距离查询(固定球面半径内的点)
     */
    void spaceTest();

    /**
     * 最近点查询
     */
    void nearestTest();

}
