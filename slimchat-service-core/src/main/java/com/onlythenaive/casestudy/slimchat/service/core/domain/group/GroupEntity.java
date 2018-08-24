package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.time.Instant;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainEntity;

/**
 * Chat group entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "groups")
public class GroupEntity implements DomainEntity {

    @Id
    private String id;

    @Field
    @Nullable
    private String caption;

    @Field
    private Collection<String> participantIds;

    @Field
    private Collection<String> moderatorIds;

    @Field
    private Instant lastModifiedAt;

    @Field
    private Instant createdAt;
}
