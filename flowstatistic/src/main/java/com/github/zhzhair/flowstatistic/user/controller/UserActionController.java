package com.github.zhzhair.flowstatistic.user.controller;

import com.github.zhzhair.flowstatistic.common.base.dto.BaseResponse;
import com.github.zhzhair.flowstatistic.user.service.UserActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("userAction")
@Api(description = "用户行为")
public class UserActionController {

    @Resource
    private UserActionService userService;

    @ApiOperation(value = "清空用户表", notes = "清空用户表")
    @RequestMapping(value = "/truncateTables", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> truncateTables() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        userService.truncateTable();
        baseResponse.ok();
        return baseResponse;
    }

}
