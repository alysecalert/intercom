package org.intercom.init;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("org.intercom")
@EnableWebMvc
@EnableScheduling
@Import({ SecurityConfig.class })
public class WebAppConfig extends WebMvcConfigurerAdapter {

}