package com.github.zhzhair.main.propreader.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.zhzhair.main.common.base.dto.BaseResponse;
import com.github.zhzhair.main.propreader.enums.Gender;
import com.github.zhzhair.main.propreader.enums.GenderEnum;
import com.github.zhzhair.propreader.service.DictionaryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jsonReader")
@Api(description = "常量/枚举 -- 读取json或xml字典数据")
public class JSONFileReaderController {

    @Resource
    private DictionaryBean dictionaryBean;

    @ApiOperation(value = "根据编号查名称", notes = "根据编号查名称")
    @RequestMapping(value = "/findNameByCode", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<String> findNameByCode(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        JSONObject jsonObject = dictionaryBean.getKeyObject(key);
        baseResponse.ok(String.valueOf(jsonObject.get(code)));
        return baseResponse;
    }

    @ApiOperation(value = "查下拉菜单列表数据", notes = "查下拉菜单列表数据")
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<List<Map<String,String>>> findAll(@RequestParam(value = "key") String key) {
        BaseResponse<List<Map<String,String>>> baseResponse = new BaseResponse<>();
        baseResponse.ok(dictionaryBean.getListMap(key));
        return baseResponse;
    }

    @ApiOperation(value = "枚举方法根据编号查名称", notes = "枚举方法根据编号查名称")
    @RequestMapping(value = "/findNameByCodeWithEnum", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<String> findNameByCodeWithEnum(@RequestParam(value = "code") Integer code) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        int sexIndex;
        switch (code){
            case 1 : sexIndex = Gender.MALE; break;
            case 2 : sexIndex = Gender.FEMALE; break;
            default : sexIndex = Gender.UNKNOWN;
        }
        //常量和枚举案例
        GenderEnum genderEnum = GenderEnum.fromStatus(String.valueOf(sexIndex));
        if(genderEnum == null){
            baseResponse.ok("未知");
        }else{
            baseResponse.ok(genderEnum.getDescription());
        }
        return baseResponse;
    }

    @ApiOperation(value = "枚举方法查下拉菜单列表数据", notes = "枚举方法查下拉菜单列表数据")
    @RequestMapping(value = "/findAllWithEnum", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<List<Map<String,String>>> findAllWithEnum() {
        BaseResponse<List<Map<String,String>>> baseResponse = new BaseResponse<>();
        List<Map<String,String>> list = new ArrayList<>();
        for (GenderEnum genderEnum : GenderEnum.values()) {
            Map<String,String> map = new HashMap<>();
            map.put("name",genderEnum.getDescription());
            map.put("code",genderEnum.getCode());
            list.add(map);
        }
        baseResponse.ok(list);
        return baseResponse;
    }
}
