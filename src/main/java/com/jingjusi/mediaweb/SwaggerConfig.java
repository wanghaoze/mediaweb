package com.jingjusi.mediaweb;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket(){
        boolean b = true;               // 是否选择启用swagger
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .enable(b)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jingjusi.mediaweb"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("账户后端微服务Api文档")
                .description("后端微服务应用\n")
                .termsOfServiceUrl("")
                .contact(new Contact("HaoZe", "", "1070390210@qq.com.com"))
                .build();
    }
}