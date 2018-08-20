package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {

    Collection<ProfileEntity> findAllById(Collection<String> id);

    ProfileEntity getByAccountName(String accountName);

    ProfileEntity getById(String id);
}
