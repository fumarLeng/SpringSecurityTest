package com.example.securitytest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")//設定允許的路徑
                .allowedOriginPatterns("*")//設定允許的域名
                .allowCredentials(true)//設定是否允許cookie
                .allowedMethods("GET", "POST", "PUT", "DELETE")//設定允許的請求方式
                .allowedHeaders("*")//設定允許的header
                .maxAge(3600);//設定跨域允許時間
    }
}
