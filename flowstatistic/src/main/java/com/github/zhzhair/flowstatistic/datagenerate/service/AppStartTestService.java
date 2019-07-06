package com.github.zhzhair.flowstatistic.datagenerate.service;

import com.github.zhzhair.flowstatistic.datagenerate.dto.request.StartRecordMapperRequest;

/**
 * 添加测试数据
 */
public interface AppStartTestService {

    void insertDataOrigin();

    void insertDataTables();

    void createTables();

    void dropTables();

    void createAppStartDayTable(int i);

    void dropAppStartDayTable(int i);

    void insertRecordToday(StartRecordMapperRequest startRecordMapperRequest);

    void createAppStartMonthTable();

    void dropAppStartMonthTable();

    void insertMonthRecord();

    void createAppStartDayTable();

    void dropAppStartDayTable();

    void insertDayRecord();

}
