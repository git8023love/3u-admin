package com._3u;

import com._3u.filter.PermissionsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

//@SpringBootApplication指定这是一个 spring boot的应用程序.
@SpringBootApplication
public class AdminApplication {
    public static void main( String[] args )
    {
    	// SpringApplication 用于从main方法启动Spring应用的类。
        SpringApplication.run(AdminApplication.class, args);
    }

    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(permissionsFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("permissionsFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "permissionsFilter")
    public Filter permissionsFilter() {
        return new PermissionsFilter();
    }
}