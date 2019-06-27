package com.github.zhzhair.main.propreader.controller;

import com.github.zhzhair.main.common.base.dto.BaseResponse;
import com.github.zhzhair.propreader.service.DFAsensitiveWordBean;
import com.github.zhzhair.propreader.service.SensitiveWordBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/sensitiveWord")
@Api(description = "读敏感词库 -- 敏感词过滤")
public class SensitiveWordController {

    @Resource
    private SensitiveWordBean sensitiveWordBean;
    @Resource
    private DFAsensitiveWordBean dfaSensitiveWordBean;

    @ApiOperation(value = "获取敏感词", notes = "获取敏感词")
    @RequestMapping(value = "/getSensorWord",method = {RequestMethod.GET})
    public BaseResponse<Set<String>> getSensorWord(String text) {
        BaseResponse<Set<String>> baseResponse = new BaseResponse<>();
        Set<String> set = sensitiveWordBean.getCensorWord(text);
        if(set.isEmpty()){
            baseResponse.setCode(0);
            baseResponse.setMsg("没有查询到敏感词");
            return baseResponse;
        }
        baseResponse.setCode(1);
        baseResponse.setMsg("已查询到敏感词");
        baseResponse.setData(set);
        return baseResponse;
    }

    @ApiOperation(value = "脱敏", notes = "脱敏")
    @RequestMapping(value = "/sensorWordText",method = {RequestMethod.GET})
    public BaseResponse<String> sensorWordText(String text) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        String string = sensitiveWordBean.getCensorWordText(text);
        baseResponse.ok(string);
        return baseResponse;
    }

    @ApiOperation(value = "dfa算法获取敏感词", notes = "dfa算法获取敏感词")
    @RequestMapping(value = "/getDFASensorWord",method = {RequestMethod.GET})
    public BaseResponse<Set<String>> getDFASensorWord(String text) {
        BaseResponse<Set<String>> baseResponse = new BaseResponse<>();
        Set<String> set = dfaSensitiveWordBean.getCensorWord(text);
        if(set.isEmpty()){
            baseResponse.setCode(0);
            baseResponse.setMsg("没有查询到敏感词");
        }else{
            baseResponse.setCode(1);
            baseResponse.setMsg("已查询到敏感词");
            baseResponse.setData(set);
        }
        return baseResponse;
    }

    @ApiOperation(value = "dfa算法脱敏", notes = "dfa算法脱敏")
    @RequestMapping(value = "/getDFASensorWordText",method = {RequestMethod.GET})
    public BaseResponse<String> getDFASensorWordText(String text) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        String str = dfaSensitiveWordBean.getCensorWordText(text);
        baseResponse.ok(str);
        return baseResponse;
    }
}
