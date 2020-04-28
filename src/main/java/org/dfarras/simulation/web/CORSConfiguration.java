package org.dfarras.simulation.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CORSConfiguration {
    @Bean
    public FilterRegistrationBean<CORSFilter> corsFilterRegistration() {
        FilterRegistrationBean<CORSFilter> registrationBean = new FilterRegistrationBean<>(new CORSFilter());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
