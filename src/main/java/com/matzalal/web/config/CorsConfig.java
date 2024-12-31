package com.matzalal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer config = new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("http://localhost:8000/", "http://192.168.0.73:5500","http://192.168.0.73:5501",
                        "http://127.0.0.1:5500","http://127.0.0.1:5501")
                        .allowedMethods("POST","PUT","DELETE","GET");
            };
        };
        return config;
    }
//    여기서부터 추가
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://matzalal.site");
    }
}