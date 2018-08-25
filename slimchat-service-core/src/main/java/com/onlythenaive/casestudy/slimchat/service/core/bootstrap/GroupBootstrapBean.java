package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;

import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupEntity;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class GroupBootstrapBean extends GenericEntityBootstrapBean<GroupEntity> {

    @Override
    protected String getBootstrapName() {
        return "chat groups";
    }

    @Override
    protected void executeBootstrap() {
        Collection<GroupEntity> entities = parseList("/bootstrap/dev/groups.json", GroupEntity.class);
        entities.forEach(this::insertBootstrappedEntity);
    }
}
