package com.github.zhzhair.propreader.service;

import com.github.zhzhair.propreader.properties.ConstReaderProperties;
import com.github.zhzhair.propreader.util.CensorWordReaderUtil;
import com.github.zhzhair.propreader.util.DFAfilterUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class DFAsensitiveWordFilter {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(DFAsensitiveWordFilter.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public DFAsensitiveWordBean getDFAsensitiveWord(){
        boolean bool = constReaderProperties.getCensorWordEnable();
        DFAsensitiveWordBean dfaSensitiveWordBean = new DFAsensitiveWordBean();
        if(bool){
            ConstReaderProperties.CensorWord censorWord = constReaderProperties.getCensorWord();
            if(censorWord.getDfaEnabled()){
                Set<String> set = CensorWordReaderUtil.toSet(censorWord.getCensorWordsLocation(),censorWord.getWordCount());
                Map<String,Object> map = DFAfilterUtil.getSensitiveWordToHashMap(set);
                dfaSensitiveWordBean.setMap(map);
                logger.log(Level.INFO,"加载敏感词到map，文件加载路径：" + censorWord.getCensorWordsLocation());
            }
        }
        return dfaSensitiveWordBean;
    }
}
