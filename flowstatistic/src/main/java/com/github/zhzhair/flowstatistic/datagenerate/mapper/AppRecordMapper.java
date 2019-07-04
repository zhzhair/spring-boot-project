package com.github.zhzhair.flowstatistic.datagenerate.mapper;

import com.github.zhzhair.flowstatistic.datagenerate.dto.request.StartRecordMapperRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppRecordMapper {

    void insertStartFromNew();

    void truncateTable(@Param("tableName") String tableName);

    void insertStartDataOrigin(@Param("list") List<StartRecordMapperRequest> list);

    void insertStartDataBefore(@Param("dateDayStr") String dateDayStr, @Param("dateStr") String dateStr, @Param("size") Integer size);

    void dropTableIfExists(@Param("tableName") String tableName);

    void createAppStartTable(@Param("tableName") String tableName);

    void createAppStartMonthTable(@Param("tableName") String tableName);

    void createAppStartDayTable(@Param("dateStr") String dateStr);

    void insertRecordToday(@Param("item") StartRecordMapperRequest startRecordMapperRequest, @Param("dateStr") String dateStr);

    void insertDataTables(@Param("tableFromName") String tableFromName,@Param("tableToName") String tableToName, @Param("rem") String rem);

    void insertStartDataMonth(@Param("tableNameFrom") String tableNameFrom, @Param("tableName") String tableName, @Param("dayBegin") String dayBegin, @Param("dayEnd") String dayEnd);
}