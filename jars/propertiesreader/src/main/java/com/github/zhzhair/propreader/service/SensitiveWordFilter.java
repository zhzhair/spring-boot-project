package com.github.zhzhair.propreader.service;

import com.github.zhzhair.propreader.properties.ConstReaderProperties;
import com.github.zhzhair.propreader.util.CensorWordReaderUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class SensitiveWordFilter {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(SensitiveWordFilter.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public SensitiveWordBean getSensitiveWord(){
        boolean bool = constReaderProperties.getCensorWordEnable();
        SensitiveWordBean sensitiveWordBean = new SensitiveWordBean();
        if(bool){
            ConstReaderProperties.CensorWord censorWord = constReaderProperties.getCensorWord();
            if(censorWord.getIteratorEnabled()){
                Set<String> set = CensorWordReaderUtil.toSet(censorWord.getCensorWordsLocation(),censorWord.getWordCount());
                sensitiveWordBean.setSet(set);
                logger.log(Level.INFO,"加载敏感词到set，文件加载路径：" + censorWord.getCensorWordsLocation());
            }
        }
        return sensitiveWordBean;
    }
}
