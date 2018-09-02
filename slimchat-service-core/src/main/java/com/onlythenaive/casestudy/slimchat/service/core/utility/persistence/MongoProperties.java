package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MongoDB properties.
 *
 * @author Ilia Gubarev
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "persistence.mongodb")
public class MongoProperties {

    private String database;
    private String host;
    private Integer port;
}
