package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Optional;

public interface GroupProvider {

    Group getGroup(String id);

    Optional<Group> findGroup(String id);
}
