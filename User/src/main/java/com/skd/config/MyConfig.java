package com.skd.config;

import com.skd.filter.RoleAllowedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public FilterRegistrationBean<RoleAllowedFilter> roleAllowedFilterRegistrationBean() {
        FilterRegistrationBean<RoleAllowedFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RoleAllowedFilter());
        registrationBean.addUrlPatterns("/users/*"); // replace with your desired URL pattern
        return registrationBean;
    }
}
