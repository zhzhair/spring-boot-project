package com.github.zhzhair.flowstatistic.dataview.service;

import com.github.zhzhair.flowstatistic.dataview.dto.response.AppChannelCount;
import org.bson.Document;

import java.util.List;

public interface AppRecordCountService {

    List<Document> getActiveCount();

    List<Document> getNewCount();

    List<AppChannelCount> getActiveCountDays();

    List<AppChannelCount> getMonthActiveCountDays();

}
