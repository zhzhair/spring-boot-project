package com.github.zhzhair.main.tools.service.impl;

import com.github.zhzhair.main.common.util.POIExcelExport;
import com.github.zhzhair.main.common.util.POIExcelUpload;
import com.github.zhzhair.main.tools.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void outputExcel(int type, HttpServletRequest req, HttpServletResponse resp, List<HashMap<String,Object>> list,
                            String fileName, String[] heads, String[] cols, String[] numerics) {

        resp.setContentType("application/vnd.ms-excel");
        String contentDisposition = null;
        try {
            if (req.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                contentDisposition = "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1")
                        + "\"";// firefox浏览器
            } else {
                contentDisposition = "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"";// IE浏览器
            }
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        resp.setHeader("Content-Disposition", contentDisposition);
        resp.setCharacterEncoding("UTF-8");

        OutputStream out = null;
        try {
            out = resp.getOutputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        POIExcelExport pee = new POIExcelExport(fileName, heads, cols, list, numerics, out);
        if(type == 0){
            pee.exportExcel();
        }else{
            pee.exportExcelExtend();
        }
    }

    public List<Map<String, Object>> uploadExcel(MultipartFile multipartFile, String[] mapKey, int type) {
        //这里直接返回接收到的列表，省略入库的操作
        return new POIExcelUpload().uploadExcel(multipartFile,type,mapKey);
    }

}
