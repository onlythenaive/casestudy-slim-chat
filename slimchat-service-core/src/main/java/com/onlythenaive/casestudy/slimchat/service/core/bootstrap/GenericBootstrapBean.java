package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

/**
 * Generic bootstrap component implementation.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericBootstrapBean extends GenericComponentBean {

    protected abstract String getBootstrapName();

    protected abstract void executeBootstrap();

    protected void logBootstrap(String message, Object... objects) {
        logger().info("Bootstrap: " + message, objects);
    }

    protected <T> List<T> parseList(String filename, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, type);
        InputStream inputStream = TypeReference.class.getResourceAsStream(filename);
        try {
            return mapper.readValue(inputStream, collectionType);
        } catch (IOException e) {
            throw OperationException.builder()
                    .comment("Unable to parse bootstrap data")
                    .category(ExceptionCategory.UNPREDICTED)
                    .cause(e)
                    .build();
        }
    }

    @PostConstruct
    private void init() {
        logBootstrap("preparing to execute {} bootstrapping...", getBootstrapName());
        executeBootstrap();
        logBootstrap("bootstrapping of {} finished successfully", getBootstrapName());
    }
}
