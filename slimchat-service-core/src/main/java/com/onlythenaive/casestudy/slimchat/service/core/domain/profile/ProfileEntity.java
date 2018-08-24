package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.time.Instant;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainEntity;

/**
 * User profile entity.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class ProfileEntity implements DomainEntity {

    @Field
    @Id
    private String id;

    @Field
    @Indexed
    private String accountName;

    @Field
    private String email;

    @Field
    @Nullable
    private String firstname;

    @Field
    @Nullable
    private String lastname;

    @Field
    private Instant lastSpottedAt;

    @Field
    private Instant registeredAt;

    @Field
    private Instant createdAt;

    @Field
    private Instant lastModifiedAt;

    @Field
    private Boolean restricted;

    @Field
    private Collection<String> connectedUserIds;

    @Field
    @Nullable
    private String status;

    public boolean acceptsMessagesFrom(String anotherProfileId) {
        return !restricted || connectedWith(anotherProfileId);
    }

    public boolean connectedWith(String anotherProfileId) {
        return this.connectedUserIds.contains(anotherProfileId);
    }
}
