package com.github.zhzhair.flowstatistic.dataview.mapper;

import com.github.zhzhair.flowstatistic.dataview.dto.response.AppChannelCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppRecordCountMapper {

    List<AppChannelCount> getActiveCount(@Param("tableName") String tableName);

    Integer getMonthOrDayActiveCountDays(@Param("tableName") String tableName);
}