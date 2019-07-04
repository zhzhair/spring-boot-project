package com.github.zhzhair.flowstatistic.dataview.controller;

import com.github.zhzhair.flowstatistic.common.base.dto.BaseResponse;
import com.github.zhzhair.flowstatistic.dataview.dto.response.AppChannelCount;
import com.github.zhzhair.flowstatistic.dataview.service.AppRecordCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 添加测试数据，对于月活跃，一个月去重后的数据放一个表
 */
@RestController
@RequestMapping("/appRecordCount")
@Api(description = "查询app使用情况的数据")
public class AppRecordCountController {

    @Resource
    private AppRecordCountService viewService;

    @RequestMapping(value = "/getActiveCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询活跃设备数", notes = "查询活跃设备数")
    public BaseResponse<Object> getActiveCount() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        List<Document> list = viewService.getActiveCount();
        baseResponse.ok(list);
        return baseResponse;
    }

    @RequestMapping(value = "/getNewCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询新增设备数", notes = "查询新增设备数")
    public BaseResponse<Object> getNewCount() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        List<Document> list = viewService.getNewCount();
        baseResponse.ok(list);
        return baseResponse;
    }

    @RequestMapping(value = "/getActiveCountDays", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询30天内的日活跃设备数", notes = "查询30天内的日活跃设备数")
    public BaseResponse<Object> getActiveCountDays() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        List<AppChannelCount> list = viewService.getActiveCountDays();
        baseResponse.ok(list);
        return baseResponse;
    }

    @RequestMapping(value = "/getMonthActiveCountDays", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询30天内月活跃设备数", notes = "查询30天内月活跃设备数")
    public BaseResponse<Object> getMonthActiveCountDays() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        //每天做定时任务将前30天去重后的记录单独放到一张表
        List<AppChannelCount> list = viewService.getMonthActiveCountDays();
        baseResponse.ok(list);
        return baseResponse;
    }
}
