package com.github.zhzhair.main;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

    @Test
    public void contextLoads() {
        getJSONObject("data/dic.json");
    }

    public static JSONObject getJSONObject(String path) {
        try {
            File file = ResourceUtils.getFile("classpath:" + path);
            InputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
            String var = FileCopyUtils.copyToString(reader);
            String newVar = var.replaceAll("\\s*", "");
            inputStream.close();
            return JSONObject.parseObject(newVar);
        } catch (IOException var6) {
            var6.printStackTrace();
            return null;
        }
    }
}
