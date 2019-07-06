package com.github.zhzhair.stepscount.step.controller;

import com.github.zhzhair.stepscount.common.base.dto.BaseResponse;
import com.github.zhzhair.stepscount.step.dto.response.StepsRankAllResp;
import com.github.zhzhair.stepscount.step.service.StepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("step")
@Api(description = "步步健康")
public class StepController {

    @Resource
    private StepService stepService;

    @ApiOperation(value = "查询当日总步数排名", notes = "查询当日总步数排名")
    @RequestMapping(value = "/getRankAll", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<List<StepsRankAllResp>> getRankAll(int begin, int pageSize) {
        BaseResponse<List<StepsRankAllResp>> baseResponse = new BaseResponse<>();
        List<StepsRankAllResp> list = stepService.getRankAllFromRedis(begin, pageSize);
        if (list.isEmpty()) list = stepService.getRankAll(begin, pageSize);
        baseResponse.ok(list);
        return baseResponse;
    }

    @ApiOperation(value = "新建表", notes = "新建表")
    @RequestMapping(value = "/createTables", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> createTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        stepService.createTables();
        baseResponse.ok(0);
        return baseResponse;
    }

    @ApiOperation(value = "删除表", notes = "删除表")
    @RequestMapping(value = "/dropTables", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> dropTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        stepService.dropTables();
        baseResponse.ok();
        return baseResponse;
    }
}
