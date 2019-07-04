package com.github.zhzhair.flowstatistic.dataview.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongo_day_record")
public class AppDayCountMongo {
    private Integer activeCount;
    private String dayStr;

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }
}
