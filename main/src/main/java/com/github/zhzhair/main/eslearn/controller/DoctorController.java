package com.github.zhzhair.main.eslearn.controller;

import com.github.zhzhair.main.common.base.dto.BaseResponse;
import com.github.zhzhair.main.eslearn.service.DoctorGroupByService;
import com.github.zhzhair.main.eslearn.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("es/doctor")
@Api(description = "elasticsearch添加测试数据 -- 医生搜索")
public class DoctorController {

    @Resource
    private DoctorService doctorService;
    @Resource
    private DoctorGroupByService doctorGroupByService;

    @ApiOperation(value = "删除es中的数据", notes = "删除es中的数据")
    @RequestMapping(value = "/drop", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> drop() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        doctorService.drop();
        baseResponse.ok();
        return baseResponse;
    }

    @ApiOperation(value = "保存医生数据到es", notes = "读取MySQL的数据，并保存数据到es")
    @RequestMapping(value = "/saveDoctor", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> saveDoctor() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        doctorService.saveDoctor();
        baseResponse.ok();
        return baseResponse;
    }

    @ApiOperation(value = "查询医生数据", notes = "查询医生数据")
    @RequestMapping(value = "/findByHospitalNameOrDoctorName", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> findByHospitalNameOrDoctorName(String context) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.ok(doctorService.findByHospitalNameOrDoctorName(context));
        return baseResponse;
    }

    @ApiOperation(value = "查询医生数据", notes = "查询医生数据")
    @RequestMapping(value = "/searchDoctor", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> searchDoctor(String searchContent) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.ok(doctorService.searchDoctor(0,20,searchContent));
        return baseResponse;
    }

    @ApiOperation(value = "查询各个医院的医生数", notes = "聚合查询各个医院的医生数")
    @RequestMapping(value = "/searchDoctorCountByHospital", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> searchDoctorCountByHospital() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.ok(doctorGroupByService.getGroupByQuery());
        return baseResponse;
    }
}
