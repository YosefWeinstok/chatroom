package com.example.ex4;

import com.example.ex4.filters.AjaxInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * For the filter class
 */
@EnableWebMvc
@Configuration
public class  MyConfig2 implements WebMvcConfigurer {

    /**
     * For the filter class
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // if you want to apply filter only for REST controller: change the "/**" pattern
        registry.addInterceptor(new AjaxInterceptor()).addPathPatterns("/addMessage","/getjson","/getUserjson");

    }
}
