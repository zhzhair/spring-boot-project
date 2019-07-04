package com.github.zhzhair.flowstatistic.dataview.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongo_new_record")
public class AppNewChannelCountMongo {
    private String appChannel;
    private Integer newCount;

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public Integer getNewCount() {
        return newCount;
    }

    public void setNewCount(Integer newCount) {
        this.newCount = newCount;
    }
}
