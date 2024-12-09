package com.ecommerce.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecommerce.ecommerce.interceptors.AuthenticationInterceptor;
import com.ecommerce.ecommerce.interceptors.LoggingInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private Environment environment;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(loggingInterceptor)
            .addPathPatterns("/**");
        if(isDevProfileActive()) {
            System.out.println("DEV PROFILE ACTIVATED");
            interceptorRegistry.addInterceptor(authenticationInterceptor)
                .excludePathPatterns("/register","/login");
                // .addPathPatterns("/**");
        }
    }

    private boolean isDevProfileActive() {
        String activeProfile = environment.getProperty("spring.profiles.active");
        return "dev".equals(activeProfile);
    }
}
