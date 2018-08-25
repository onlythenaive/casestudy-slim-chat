package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.ArrayList;
import java.util.Collection;

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
            if (entity.getConnectedUserIds() == null) {
                entity.setConnectedUserIds(new ArrayList<>());
            }
            if (entity.getRestricted() == null) {
                entity.setRestricted(false);
            }
            if (entity.getLastSpottedAt() == null) {
                entity.setLastSpottedAt(now());
            }
            if (entity.getRegisteredAt() == null) {
                entity.setRegisteredAt(now());
            }
            insertBootstrappedEntity(entity);
        });
    }
}
