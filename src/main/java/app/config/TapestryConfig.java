package app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Configuration
public class TapestryConfig {


    @Bean
    public FilterRegistrationBean<org.apache.tapestry5.spring.TapestrySpringFilter> tapestryFilter() {
        FilterRegistrationBean<org.apache.tapestry5.spring.TapestrySpringFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new org.apache.tapestry5.spring.TapestrySpringFilter());
        registrationBean.setName("app");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registrationBean;
    }

}
