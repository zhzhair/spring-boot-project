package com.github.zhzhair.propreader.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * 读取json配置文件工具类
 */
public class DictionaryReaderUtil {

    public static JSONObject getJSONObject(String path){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream(), Charsets.UTF_8));
            String var = FileCopyUtils.copyToString(reader);
            String newVar = var.replaceAll("\\s*","");
            reader.close();
            return JSONObject.parseObject(newVar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
