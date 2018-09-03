package com.onlythenaive.casestudy.slimchat.service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Main application class with its entry point.
 *
 * @author Ilia Gubarev
 */
@EnableWebMvc
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application {

    /**
     * Application entry point.
     *
     * @param arguments command line arguments passed to the application during its startup.
     */
    public static void main(String... arguments) {
        SpringApplication.run(Application.class, arguments);
    }
}
