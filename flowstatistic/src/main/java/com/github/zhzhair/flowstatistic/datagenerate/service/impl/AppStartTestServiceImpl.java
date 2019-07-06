package com.github.zhzhair.flowstatistic.datagenerate.service.impl;

import com.github.zhzhair.flowstatistic.common.constant.AppInfoDetail;
import com.github.zhzhair.flowstatistic.common.util.DateUtil;
import com.github.zhzhair.flowstatistic.datagenerate.service.AppInfos;
import com.github.zhzhair.flowstatistic.datagenerate.dto.request.StartRecordMapperRequest;
import com.github.zhzhair.flowstatistic.datagenerate.mapper.AppRecordMapper;
import com.github.zhzhair.flowstatistic.datagenerate.service.AppStartTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 添加测试数据
 */
@Service
public class AppStartTestServiceImpl implements AppStartTestService {

    @Resource
    private AppRecordMapper appRecordMapper;

    @Override
    public void insertDataOrigin() {
        appRecordMapper.truncateTable("appnew_record_original");
        appRecordMapper.truncateTable("appstart_record_original");
        for (int i = 360; i >=0 ; i--) {
            appRecordMapper.insertStartDataOrigin(AppInfos.getNewDevice(360,i,AppInfoDetail.tableCount));
            String dateDayStr = DateUtil.getDateStr(i);
            for (int j = Math.min(i + 90, 360); j >= i; j--) {
                String dateStr = DateUtil.getDateStr(j);
                int size;
                if(j > i + 80){
                    size = new Random().nextInt(10);
                }else if(j <= i + 80 && j > i + 70){
                    size = new Random().nextInt(20);
                }else if(j <= i + 70 && j > i + 60){
                    size = new Random().nextInt(40);
                }else if(j <= i + 60 && j > i + 50){
                    size = new Random().nextInt(100);
                }else if(j <= i + 50 && j > i + 40){
                    size = new Random().nextInt(200);
                }else if(j <= i + 40 && j > i + 30){
                    size = new Random().nextInt(400);
                }else if(j <= i + 30 && j > i + 20){
                    size = new Random().nextInt(600);
                }else if(j <= i + 20 && j > i + 10){
                    size = new Random().nextInt(800);
                }else{
                    size = new Random().nextInt(1000);
                }
                appRecordMapper.insertStartDataBefore(dateDayStr, dateStr, size);
            }
        }
        appRecordMapper.insertStartFromNew();
    }

    @Override
    public void insertDataTables() {
        IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> {
            appRecordMapper.truncateTable("appnew_record_" + i);
            appRecordMapper.truncateTable("appstart_record_" + i);
            appRecordMapper.insertDataTables("appnew_record_original","appnew_record_" + i,i);
            appRecordMapper.insertDataTables("appstart_record_original","appstart_record_" + i,i);
        });
    }

    @Override
    public void createTables() {
        IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> appRecordMapper.createAppNewTable("appstart_record_" + i));
        IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> appRecordMapper.createAppStartTable("appnew_record_" + i));
    }

    @Override
    public void dropTables() {
        IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> appRecordMapper.dropTableIfExists("appstart_record_" + i));
        IntStream.range(0, AppInfoDetail.tableCount).parallel().forEach(i -> appRecordMapper.dropTableIfExists("appnew_record_" + i));
    }

    @Override
    public void createAppStartDayTable(int i) {
        appRecordMapper.createAppStartDayTable(DateUtil.getDateStr(i-1).replace("-",""));
        appRecordMapper.createAppStartDayTable(DateUtil.getDateStr(i-2).replace("-",""));
        appRecordMapper.createAppStartDayTable(DateUtil.getDateStr(i-3).replace("-",""));
        appRecordMapper.createAppStartDayTable(DateUtil.getDateStr(i-4).replace("-",""));
    }

    @Override
    public void dropAppStartDayTable(int i) {
        appRecordMapper.dropTableIfExists(DateUtil.getDateStr(i + 8).replace("-",""));
        appRecordMapper.dropTableIfExists(DateUtil.getDateStr(i + 8).replace("-",""));
    }

    @Override
    public void insertRecordToday(StartRecordMapperRequest startRecordMapperRequest) {
        startRecordMapperRequest.setRecordInTime(new Timestamp(System.currentTimeMillis()));
        startRecordMapperRequest.setRecordOutTime(new Timestamp(System.currentTimeMillis() + 60000));
        appRecordMapper.insertRecordToday(startRecordMapperRequest,DateUtil.getDateStr(0).replace("-",""));
    }

    @Override
    public void createAppStartMonthTable() {
        IntStream.rangeClosed(1,90).parallel().forEach(i -> appRecordMapper.createAppStartMonthOrDayTable("appmonth_record_" + DateUtil.getDateStr(i).replace("-","")));
    }

    @Override
    public void dropAppStartMonthTable() {
        IntStream.rangeClosed(1,180).parallel().forEach(i -> appRecordMapper.dropTableIfExists("appmonth_record_" + DateUtil.getDateStr(i).replace("-","")));
    }

    @Override
    public void insertMonthRecord() {
        IntStream.range(0,AppInfoDetail.tableCount).parallel().forEach(k -> {
            String tableNameFrom = "appstart_record_" + k;
            IntStream.rangeClosed(1,90).forEach(i -> appRecordMapper.insertStartDataMonth(tableNameFrom, "appmonth_record_" + DateUtil.getDateStr(i).replace("-",""), DateUtil.getDateStr(i + 29), DateUtil.getDateStr(i)));
        });
    }

    @Override
    public void createAppStartDayTable() {
        IntStream.rangeClosed(1,90).parallel().forEach(i -> appRecordMapper.createAppStartMonthOrDayTable("appday_record_" + DateUtil.getDateStr(i).replace("-","")));
    }

    @Override
    public void dropAppStartDayTable() {
        IntStream.rangeClosed(1,180).parallel().forEach(i -> appRecordMapper.dropTableIfExists("appday_record_" + DateUtil.getDateStr(i).replace("-","")));
    }

    @Override
    public void insertDayRecord() {
        IntStream.rangeClosed(1,90).parallel().forEach(i -> appRecordMapper.truncateTable("appday_record_" + DateUtil.getDateStr(i).replace("-","")));
        IntStream.range(0,AppInfoDetail.tableCount).parallel().forEach(k -> IntStream.rangeClosed(1,90).forEach(i -> appRecordMapper.insertStartDataDay("appstart_record_" + k, "appday_record_" + DateUtil.getDateStr(i).replace("-",""), DateUtil.getDateStr(i))));
    }

}
