package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

public interface ProfilePersister {

    void insert(ProfileEntity entity);

    void update(ProfileEntity entity);
}
