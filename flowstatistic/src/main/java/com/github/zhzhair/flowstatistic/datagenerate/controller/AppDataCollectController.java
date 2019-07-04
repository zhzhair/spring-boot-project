package com.github.zhzhair.flowstatistic.datagenerate.controller;

import com.github.zhzhair.flowstatistic.common.base.dto.BaseResponse;
import com.github.zhzhair.flowstatistic.common.util.WebUtil;
import com.github.zhzhair.flowstatistic.datagenerate.dto.request.StartRecordMapperRequest;
import com.github.zhzhair.flowstatistic.datagenerate.service.AppStartTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 添加测试数据
 */
@RestController
@RequestMapping("/appDataCollect")
@Api(description = "添加测试数据")
public class AppDataCollectController {

    @Resource
    private AppStartTestService testService;

    @RequestMapping(value = "/createTables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新建表", notes = "新建tableCount个分表")
    public BaseResponse<Object> createTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.createTables();
        baseResponse.ok();
        return baseResponse;
    }

    @RequestMapping(value = "/dropTables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除表", notes = "删除表")
    public BaseResponse<Object> dropTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.dropTables();
        baseResponse.ok();
        return baseResponse;
    }

    @RequestMapping(value = "/insertDataRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加一条记录", notes = "添加一条记录")
    public BaseResponse<Object> insertDataRecord(StartRecordMapperRequest startRecordMapperRequest, HttpServletRequest request) {
        startRecordMapperRequest.setIp(WebUtil.getRemoteAddr(request));
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.insertRecordToday(startRecordMapperRequest);
        baseResponse.ok();
        return baseResponse;
    }

    @RequestMapping(value = "/insertDataOrigin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加原始数据", notes = "添加原始数据")
    public BaseResponse<Object> insertDataOrigin() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.insertDataOrigin();
        baseResponse.ok();
        return baseResponse;
    }

    @RequestMapping(value = "/createMonthTables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新建月活记录表", notes = "新建月活记录表")
    public BaseResponse<Object> createMonthTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.createAppStartMonthTable();//实际上是由定时任务创建
        baseResponse.ok();
        return baseResponse;
    }

    @RequestMapping(value = "/dropMonthTables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除月活记录表", notes = "删除月活记录表")
    public BaseResponse<Object> dropMonthTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.dropAppStartMonthTable();
        baseResponse.ok();
        return baseResponse;
    }

    @RequestMapping(value = "/insertMonthRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "插入月活记录", notes = "插入月活记录")
    public BaseResponse<Object> insertMonthRecord() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        testService.insertMonthRecord();
        baseResponse.ok();
        return baseResponse;
    }
}
