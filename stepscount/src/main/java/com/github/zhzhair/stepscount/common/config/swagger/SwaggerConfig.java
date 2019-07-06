package com.github.zhzhair.stepscount.common.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements BeanPostProcessor {

    @Resource
    private Environment environment;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
//                .apis(basePackage("com.github.zhzhair.main"))//启动类已经注解了可访问路径
                .apis(input -> "dev".equals(environment.getActiveProfiles()[0]))
                .apis(input -> input != null && input.isAnnotatedWith(ApiOperation.class))
                .paths(PathSelectors.any())//.paths(PathSelectors.regex("/rest/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("流量统计平台后端接口文档")
                .description("如无特殊说明，所有接口均不需要在query中附带userId及token参数")
                .termsOfServiceUrl("***")
                .contact(new Contact("zhzhair","https://github.com/zhzhair","495359612@qq.com"))
                .version("1.0-beta")
                .build();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
