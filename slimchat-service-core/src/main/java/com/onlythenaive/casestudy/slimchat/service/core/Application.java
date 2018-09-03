package com.onlythenaive.casestudy.slimchat.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Main application class with its entry point.
 *
 * @author Ilia Gubarev
 */
@EnableWebMvc
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application implements WebMvcConfigurer {

    /**
     * Application entry point.
     *
     * @param arguments command line arguments passed to the application during its startup.
     */
    public static void main(String... arguments) {
        SpringApplication.run(Application.class, arguments);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper webObjectMapper = objectMapper.copy();
        webObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        webObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        webObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converters.add(new MappingJackson2HttpMessageConverter(webObjectMapper));
    }
}
