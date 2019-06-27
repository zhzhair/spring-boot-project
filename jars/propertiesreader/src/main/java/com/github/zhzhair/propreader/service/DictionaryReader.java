package com.github.zhzhair.propreader.service;

import com.github.zhzhair.propreader.properties.ConstReaderProperties;
import com.github.zhzhair.propreader.util.DicUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class DictionaryReader {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(DictionaryReader.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public DictionaryBean getDictionaryReader(){
        DictionaryBean dictionaryBean = new DictionaryBean();
        Boolean bool = constReaderProperties.getDicEnable();
        if(bool){
            String path = constReaderProperties.getDicLocation();
            String[] paths = path.split(",");
            dictionaryBean.setJsonObject(DicUtil.getJSONObject(paths[0]));
            logger.log(Level.INFO, "初始化字典数据 ===== 文件路径: " + path);
        }
        return dictionaryBean;
    }

}
