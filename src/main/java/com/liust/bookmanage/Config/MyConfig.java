package com.liust.bookmanage.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liuyulong
 * @create 2022-06-09 13:54
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {


    @Value("${file.path}")
    private String StaticPath;

    /**
     * 静态资源放行
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:"+StaticPath+"/static/");
    }

}
