package com.github.zhzhair.propreader.service;

import com.alibaba.fastjson.JSONObject;
import com.github.zhzhair.propreader.properties.ConstReaderProperties;
import com.github.zhzhair.propreader.util.DicUtil;
import com.github.zhzhair.propreader.util.StringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class MultiDictionaryReader {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(MultiDictionaryReader.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public MultiDictionaryBean getMultiDictionaryReader(){
        MultiDictionaryBean dictionaryBean = new MultiDictionaryBean();
        Boolean bool = constReaderProperties.getDicEnable();
        if(bool){
            String path = constReaderProperties.getDicLocation();
            String[] paths = path.split(",");
            JSONObject multiJsonObject = new JSONObject();
            multiJsonObject.put(StringUtil.getFileName(paths[0]),DicUtil.getJSONObject(paths[0]));
            if(paths.length > 1){
                for (int i = 1; i < paths.length; i++) {
                    multiJsonObject.put(StringUtil.getFileName(paths[i]),DicUtil.getJSONObject(paths[i]));
                }
            }else{
                logger.info("只有单个json配置文件建议注入对象：DictionaryBean");
            }
            dictionaryBean.setMultiJsonObject(multiJsonObject);
            logger.log(Level.INFO, "初始化字典数据 ===== 文件路径: " + path);
        }
        return dictionaryBean;
    }

}
