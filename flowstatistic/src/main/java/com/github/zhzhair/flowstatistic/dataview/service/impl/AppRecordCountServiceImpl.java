package com.github.zhzhair.flowstatistic.dataview.service.impl;

import com.github.zhzhair.flowstatistic.common.constant.AppInfoDetail;
import com.github.zhzhair.flowstatistic.common.util.DateUtil;
import com.github.zhzhair.flowstatistic.dataview.document.AppDayCountMongo;
import com.github.zhzhair.flowstatistic.dataview.document.AppNewChannelCountMongo;
import com.github.zhzhair.flowstatistic.dataview.mapper.AppRecordCountMapper;
import com.github.zhzhair.flowstatistic.dataview.document.AppChannelCountMongo;
import com.github.zhzhair.flowstatistic.dataview.dto.response.AppChannelCount;
import com.github.zhzhair.flowstatistic.dataview.service.AppRecordCountService;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class AppRecordCountServiceImpl implements AppRecordCountService {

    @Resource
    private AppRecordCountMapper viewMapper;
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<Document> getActiveCount(String[] appVersions) {
        mongoTemplate.dropCollection(AppChannelCountMongo.class);
        if (!mongoTemplate.collectionExists(AppChannelCountMongo.class))
            IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> getActiveCountOne(i,appVersions));
        TypedAggregation<AppChannelCountMongo> aggregation = Aggregation.newAggregation(
                AppChannelCountMongo.class,
                project("appChannel", "activeCount"),//查询用到的字段
//                match(Criteria.where("dateTime").lte(Date.valueOf(todayZero).getTime()).gte(Date.valueOf(yesterday).getTime())),
                group("appChannel").sum("activeCount").as("activeCount"),
                sort(Sort.Direction.DESC, "activeCount"),
                project("appChannel", "activeCount").and("appChannel").previousOperation()//输出字段,后面是取别名
        ).withOptions(newAggregationOptions().allowDiskUse(true).build());
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, AppChannelCountMongo.class, Document.class);
        return results.getMappedResults();
    }

    @Override
    public List<Document> getNewCount(String[] appVersions) {
        mongoTemplate.dropCollection(AppNewChannelCountMongo.class);
        if (!mongoTemplate.collectionExists(AppNewChannelCountMongo.class))
            IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> getNewCountOne(i,appVersions));
        TypedAggregation<AppNewChannelCountMongo> aggregation = Aggregation.newAggregation(
                AppNewChannelCountMongo.class,
                project("appChannel", "newCount"),//查询用到的字段
//                match(Criteria.where("dateTime").lte(Date.valueOf(todayZero).getTime()).gte(Date.valueOf(yesterday).getTime())),
                group("appChannel").sum("newCount").as("newCount"),
                sort(Sort.Direction.DESC, "newCount"),
                project("appChannel", "newCount").and("appChannel").previousOperation()//输出字段,后面是取别名
        ).withOptions(newAggregationOptions().allowDiskUse(true).build());
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, AppNewChannelCountMongo.class, Document.class);
        return results.getMappedResults();
    }

    @Override
    public List<AppChannelCount> getActiveCountDays() {
        List<AppChannelCount> list = new CopyOnWriteArrayList<>();
        IntStream.rangeClosed(1,30).parallel().forEach(i -> {
            String dayStr = DateUtil.getDateStr(i);
            String tableName = "appday_record_" + dayStr.replace("-","");
            Integer count = viewMapper.getMonthOrDayActiveCountDays(tableName);
            AppChannelCount appChannelCount = new AppChannelCount();
            appChannelCount.setActiveCount(count);
            appChannelCount.setDayStr(dayStr);
            list.add(appChannelCount);
        });
        return list;
    }

    @Override
    public List<AppChannelCount> getMonthActiveCountDays() {
        List<AppChannelCount> list = new CopyOnWriteArrayList<>();
        IntStream.rangeClosed(1,30).parallel().forEach(i -> {
            String dayStr = DateUtil.getDateStr(i);
            String tableName = "appmonth_record_" + dayStr.replace("-","");
            Integer count = viewMapper.getMonthOrDayActiveCountDays(tableName);
            AppChannelCount appChannelCount = new AppChannelCount();
            appChannelCount.setActiveCount(count);
            appChannelCount.setDayStr(dayStr);
            list.add(appChannelCount);
        });
        return list;
    }

    private void getActiveCountOne(int i,String[] appVersions) {
        List<AppChannelCount> list = viewMapper.getActiveCount("appstart_record_" + i, appVersions);
        mongoTemplate.insert(list, AppChannelCountMongo.class);
    }

    private void getNewCountOne(int i,String[] appVersions) {
        List<AppChannelCount> list = viewMapper.getActiveCount("appnew_record_" + i, appVersions);
        mongoTemplate.insert(list, AppNewChannelCountMongo.class);
    }

}
