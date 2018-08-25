package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@BootstrapComponent
public class MongoDataStorageBootstrapBean extends GenericBootstrapBean {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    protected String getBootstrapName() {
        return "MongoDB storage";
    }

    @Override
    protected void executeBootstrap() {
        this.mongoTemplate.getCollectionNames().forEach(name -> {
            this.mongoTemplate.dropCollection(name);
            logger().debug("Bootstrap: collection \"{}\" dropped", name);
        });
    }
}
