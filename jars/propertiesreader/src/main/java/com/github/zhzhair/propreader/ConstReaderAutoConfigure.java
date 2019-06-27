package com.github.zhzhair.propreader;

import com.github.zhzhair.propreader.properties.ConstReaderProperties;
import com.github.zhzhair.propreader.service.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnClass(value = {DictionaryBean.class, SensitiveWordBean.class})
@EnableConfigurationProperties({ConstReaderProperties.class})
@Import({DictionaryReader.class, MultiDictionaryReader.class,
        SensitiveWordFilter.class, DFAsensitiveWordFilter.class})
public class ConstReaderAutoConfigure {

}
