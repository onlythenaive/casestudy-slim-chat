package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import java.time.Instant;

/**
 * Generic domain entity.
 *
 * @author Ilia Gubarev
 */
public interface DomainEntity {

    String getId();

    void setId(String id);

    Instant getCreatedAt();

    void setCreatedAt(Instant createdAt);

    Instant getLastModifiedAt();

    void setLastModifiedAt(Instant lastModifiedAt);
}
