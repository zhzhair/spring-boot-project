package com.github.zhzhair.flowstatistic.datagenerate.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

@ApiModel
public class StartRecordMapperRequest {
    @ApiModelProperty(value = "统计使用时长用：跟踪号,应用一次启动+退出对应一个唯一的跟踪号")
    private String traceId;
    @ApiModelProperty(value = "应用的下载渠道")
    private String appChannel;
    @ApiModelProperty(value = "移动设备标识号")
    private String deviceId;
    @ApiModelProperty(value = "移动设备标识号的哈希值除以1024的余数的绝对值")
    private String ip;
    @ApiModelProperty(value = "移动设备标识号的哈希值除以分表个数的余数的绝对值")
    private Integer deviceHashMod;
    @ApiModelProperty(value = "app的版本号")
    private String appVersion;
    @ApiModelProperty(value = "是否app启动记录:0-否,1-是",example = "1")
    private Integer ifStartRecord;
    @ApiModelProperty(value = "统计使用时长等用：启动app的时间，如果启动的跟踪号没有退出时间，以当天24点为退出时间（定时任务修改退出时间）")
    private Timestamp recordInTime;
    @ApiModelProperty(value = "统计使用时长用：启动app的时间，如果退出的跟踪号在当天没有启动记录，以当天0点为启动时间")
    private Timestamp recordOutTime;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getDeviceHashMod() {
        return deviceHashMod;
    }

    public void setDeviceHashMod(Integer deviceHashMod) {
        this.deviceHashMod = deviceHashMod;
    }

    public Integer getIfStartRecord() {
        return ifStartRecord;
    }

    public void setIfStartRecord(Integer ifStartRecord) {
        this.ifStartRecord = ifStartRecord;
    }

    public Timestamp getRecordInTime() {
        return recordInTime;
    }

    public void setRecordInTime(Timestamp recordInTime) {
        this.recordInTime = recordInTime;
    }

    public Timestamp getRecordOutTime() {
        return recordOutTime;
    }

    public void setRecordOutTime(Timestamp recordOutTime) {
        this.recordOutTime = recordOutTime;
    }
}
