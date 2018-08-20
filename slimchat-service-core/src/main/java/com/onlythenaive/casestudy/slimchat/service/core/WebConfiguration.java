package com.onlythenaive.casestudy.slimchat.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityInterceptor;

/**
 * Configuration of web components.
 *
 * @author Ilia Gubarev
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.securityInterceptor);
    }

    /**
     * Creates a custom view resolver based on Handlebars template engine.
     *
     * @return custom Handlebars view resolver.
     */
    @Bean
    public ViewResolver viewResolver() {
        TemplateLoader templateLoader = new ClassPathTemplateLoader("/templates/views/");
        Handlebars handlebars = new Handlebars(templateLoader);
        return new HandlebarsViewResolver(handlebars);
    }
}
