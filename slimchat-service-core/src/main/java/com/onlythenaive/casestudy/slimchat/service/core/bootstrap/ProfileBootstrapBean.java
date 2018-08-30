package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class ProfileBootstrapBean extends GenericEntityBootstrapBean<ProfileEntity> {

    @Override
    protected String getBootstrapName() {
        return "user profiles";
    }

    @Override
    protected void executeBootstrap() {
        Collection<ProfileEntity> entities = parseList("/bootstrap/dev/profiles.json", ProfileEntity.class);
        entities.forEach(entity -> {
            if (entity.getConnectedProfileIds() == null) {
                entity.setConnectedProfileIds(new HashSet<>());
            }
            if (entity.getRestricted() == null) {
                entity.setRestricted(false);
            }
            if (entity.getLastSpottedAt() == null) {
                entity.setLastSpottedAt(now());
            }
            insertBootstrappedEntity(entity);
        });
    }
}
