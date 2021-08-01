package com.example.ex4;

import com.example.ex4.filters.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * For the filter class
 */
@EnableWebMvc
@Configuration
public class MyConfig implements WebMvcConfigurer {


    /**
     * For the filter class
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // if you want to apply filter only for REST controller: change the "/**" pattern
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/","/search");
    }

    /*
    this shows you how to control the static folder where you should put your CSS/JS/images
    it will be accessible directy, for example  http://localhost:8080/static/some-file-in-static-folder.css
    So in your html file you can reference all static files as
         src="/static/yourfile"

    you may also configure this in the application.properties.
    note: the "/" at the end is required.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}