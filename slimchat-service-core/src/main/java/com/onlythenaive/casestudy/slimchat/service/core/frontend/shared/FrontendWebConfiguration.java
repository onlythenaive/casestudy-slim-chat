package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared;

import java.nio.charset.Charset;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.helpers.TemplateHelper;

/**
 * Configuration of frontend web components.
 *
 * @author Ilia Gubarev
 */
@Configuration
public class FrontendWebConfiguration implements WebMvcConfigurer {

    @Autowired(required = false)
    private Collection<TemplateHelper<?>> templateHelpers;

    /**
     * Creates a custom view resolver based on Handlebars template engine.
     *
     * @return custom Handlebars view resolver.
     */
    @Bean
    public ViewResolver viewResolver() {
        TemplateLoader templateLoader = new ClassPathTemplateLoader("/frontend/");
        Handlebars handlebars = new Handlebars(templateLoader);
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver(handlebars);
        viewResolver.setCache(false);
        viewResolver.setCharset(Charset.forName("UTF-8"));
        if (this.templateHelpers != null) {
            this.templateHelpers.forEach(helper -> viewResolver.registerHelper(helper.getName(), helper));
        }
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ui/plain/**")
                .addResourceLocations("classpath:/frontend/plain/assets/")
                .addResourceLocations("classpath:/frontend/shared/assets/")
                .setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
