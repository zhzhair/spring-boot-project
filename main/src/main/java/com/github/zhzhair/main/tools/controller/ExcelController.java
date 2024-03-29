package com.github.zhzhair.main.tools.controller;

import com.github.zhzhair.main.common.annotations.AccessLimit;
import com.github.zhzhair.main.common.annotations.NotAutoLog;
import com.github.zhzhair.main.common.base.dto.BaseResponse;
import com.github.zhzhair.main.common.util.GeneratorUtil;
import com.github.zhzhair.main.tools.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by xuyf22
 * on 2017/6/20.
 */
@Api(description = "常用公共接口 -- 导入导出excel明细")
@Controller
@RequestMapping("excel")
class ExcelController{
    @Resource
    private ExcelService excelService;

    @AccessLimit(maxCount = 5)
    @ApiOperation(value = "导出明细到excel", notes = "导出明细到excel,type: 0 -- .xls; 其他 -- .xlsx")
    @RequestMapping(value = "output/{type}",method = RequestMethod.GET)
    public void outputExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("type") int type) {
        List<HashMap<String,Object>> list = GeneratorUtil.getList();//模拟数据库或其他数据源获取用户信息列表
        String fileName = "导出用户信息明细"+ new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String[] heads = {"姓名","性别","月收入"};
        String[] cols = {"name","sex","income"};
        String[] numerics = {"sex","income"};//数字对应的列
        excelService.outputExcel(type,httpServletRequest,httpServletResponse,list,fileName,heads,cols,numerics);
    }

    @NotAutoLog
    @AccessLimit(maxCount = 5)
    @ApiOperation(value = "导入明细", notes = "导入明细,type: 0 -- .xls; 其他 -- .xlsx")
    @RequestMapping(value = "upload/{type}",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<Map<String, Object>>> uploadExcel(@RequestParam("file") MultipartFile multipartFile, @PathVariable("type") int type) {
        String[] mapKey = {"name","sex","income"};
        List<Map<String, Object>> list = excelService.uploadExcel(multipartFile,mapKey,type);
        BaseResponse<List<Map<String, Object>>> baseResponse = new BaseResponse<>();
        if(list.size() > 0){
            baseResponse.setCode(0);
            baseResponse.setMsg("请求数据成功");
            baseResponse.setData(list);
        }else{
            baseResponse.setCode(-3);
            baseResponse.setMsg("请求数据为空");
        }
        return baseResponse;
    }

}
