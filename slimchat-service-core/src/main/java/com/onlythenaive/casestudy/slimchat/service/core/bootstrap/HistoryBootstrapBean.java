package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;

import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryEntity;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class HistoryBootstrapBean extends GenericEntityBootstrapBean<HistoryEntity> {

    @Override
    protected String getBootstrapName() {
        return "chat histories";
    }

    @Override
    protected void executeBootstrap() {
        Collection<HistoryEntity> entities = parseList("/bootstrap/dev/histories.json", HistoryEntity.class);
        entities.forEach(this::insertBootstrappedEntity);
    }
}
