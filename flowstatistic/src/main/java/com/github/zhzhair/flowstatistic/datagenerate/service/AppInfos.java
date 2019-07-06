package com.github.zhzhair.flowstatistic.datagenerate.service;

import com.github.zhzhair.flowstatistic.common.constant.AppInfoDetail;
import com.github.zhzhair.flowstatistic.common.util.DateUtil;
import com.github.zhzhair.flowstatistic.common.util.GeneratorUtil;
import com.github.zhzhair.flowstatistic.datagenerate.dto.request.StartRecordMapperRequest;

import java.sql.Timestamp;
import java.util.*;

public class AppInfos {

    /**
     * 随机获取第k天的一个版本号
     * @param n 测试数据的总天数
     * @param k 开始第k天
     * @return 版本号
     */
    private static String getAppVersionByDate(int n, int k){
        int part0 = (n - k) * AppInfoDetail.appVersion.length / n;
        if(k == 0) part0 = part0 - 1;
        int rand = new Random().nextInt(10);
        if(rand > 0){
            return AppInfoDetail.appVersion[part0];
        }else{
            String[] versions = Arrays.copyOfRange(AppInfoDetail.appVersion, 0, part0);
            if(versions.length == 0){
                return "1.1.1";
            }
            return GeneratorUtil.getRandomStr(versions);
        }
    }

    /**
     * 获取某一天的新增设备
     */
    public static List<StartRecordMapperRequest> getNewDevice(int n, int k, int tableCount){
        int num = 160 + new Random().nextInt(1600);
        List<StartRecordMapperRequest> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            String deviceId = UUID.randomUUID().toString().replace("-","");
            StartRecordMapperRequest mapperRequest = new StartRecordMapperRequest();
            String appChannel = GeneratorUtil.getRandomStr(AppInfoDetail.appChannel);
            mapperRequest.setDeviceId(deviceId);
            mapperRequest.setAppChannel(appChannel);
            mapperRequest.setAppVersion(getAppVersionByDate(n,k));
            mapperRequest.setDeviceHashMod(Math.abs(deviceId.hashCode()) % tableCount);
            mapperRequest.setRecordInTime(new Timestamp(DateUtil.parseDate(DateUtil.getDateStr(k)).getTime()));
            list.add(mapperRequest);
        }
        return list;
    }
}
