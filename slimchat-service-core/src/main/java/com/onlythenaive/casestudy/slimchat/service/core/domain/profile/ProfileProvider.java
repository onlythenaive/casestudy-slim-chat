package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.Optional;

public interface ProfileProvider {

    Profile getProfile(String id);

    Optional<Profile> findProfile(String id);

    Collection<Profile> findProfiles(Collection<String> ids);
}
