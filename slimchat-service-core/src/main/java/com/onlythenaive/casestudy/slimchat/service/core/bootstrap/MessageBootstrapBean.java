package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;

import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageEntity;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class MessageBootstrapBean extends GenericEntityBootstrapBean<MessageEntity> {

    @Override
    protected String getBootstrapName() {
        return "chat messages";
    }

    @Override
    protected void executeBootstrap() {
        Collection<MessageEntity> entities = parseList("/bootstrap/dev/messages.json", MessageEntity.class);
        entities.forEach(this::insertBootstrappedEntity);
    }
}
