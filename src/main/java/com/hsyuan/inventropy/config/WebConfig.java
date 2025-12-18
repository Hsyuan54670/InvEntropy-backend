package com.hsyuan.inventropy.config;


import com.hsyuan.inventropy.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 注册拦截器
     */
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") // 所有路径都被拦截
                .excludePathPatterns( // 排除以下路径
                        "/login"  // 登录路径
                        , "/project/submit"
                        , "/upload"
                );
    }
}
